package io.netty.channel.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.EventLoopException;
import io.netty.channel.EventLoopTaskQueueFactory;
import io.netty.channel.SelectStrategy;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.util.IntSupplier;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReflectionUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.videolan.vlc.gui.dialogs.PickTimeFragment;

public final class NioEventLoop extends SingleThreadEventLoop {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long AWAKE = -1;
    private static final int CLEANUP_INTERVAL = 256;
    private static final boolean DISABLE_KEY_SET_OPTIMIZATION = SystemPropertyUtil.getBoolean("io.netty.noKeySetOptimization", false);
    private static final int MIN_PREMATURE_SELECTOR_RETURNS = 3;
    private static final long NONE = Long.MAX_VALUE;
    private static final int SELECTOR_AUTO_REBUILD_THRESHOLD;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioEventLoop.class);
    private int cancelledKeys;
    private volatile int ioRatio = 50;
    private boolean needsToSelectAgain;
    private final AtomicLong nextWakeupNanos = new AtomicLong(-1);
    private final SelectorProvider provider;
    private final IntSupplier selectNowSupplier = new IntSupplier() {
        public int get() throws Exception {
            return NioEventLoop.this.selectNow();
        }
    };
    private final SelectStrategy selectStrategy;
    private SelectedSelectionKeySet selectedKeys;
    private Selector selector;
    private Selector unwrappedSelector;

    static {
        int i = 0;
        if (PlatformDependent.javaVersion() < 7 && SystemPropertyUtil.get("sun.nio.ch.bugLevel") == null) {
            try {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        System.setProperty("sun.nio.ch.bugLevel", "");
                        return null;
                    }
                });
            } catch (SecurityException e) {
                logger.debug("Unable to get/set System Property: sun.nio.ch.bugLevel", (Throwable) e);
            }
        }
        int i2 = SystemPropertyUtil.getInt("io.netty.selectorAutoRebuildThreshold", 512);
        if (i2 >= 3) {
            i = i2;
        }
        SELECTOR_AUTO_REBUILD_THRESHOLD = i;
        InternalLogger internalLogger = logger;
        if (internalLogger.isDebugEnabled()) {
            internalLogger.debug("-Dio.netty.noKeySetOptimization: {}", (Object) Boolean.valueOf(DISABLE_KEY_SET_OPTIMIZATION));
            internalLogger.debug("-Dio.netty.selectorAutoRebuildThreshold: {}", (Object) Integer.valueOf(i));
        }
    }

    NioEventLoop(NioEventLoopGroup nioEventLoopGroup, Executor executor, SelectorProvider selectorProvider, SelectStrategy selectStrategy2, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory, EventLoopTaskQueueFactory eventLoopTaskQueueFactory2) {
        super(nioEventLoopGroup, executor, false, newTaskQueue(eventLoopTaskQueueFactory), newTaskQueue(eventLoopTaskQueueFactory2), rejectedExecutionHandler);
        this.provider = (SelectorProvider) ObjectUtil.checkNotNull(selectorProvider, "selectorProvider");
        this.selectStrategy = (SelectStrategy) ObjectUtil.checkNotNull(selectStrategy2, "selectStrategy");
        SelectorTuple openSelector = openSelector();
        this.selector = openSelector.selector;
        this.unwrappedSelector = openSelector.unwrappedSelector;
    }

    private static Queue<Runnable> newTaskQueue(EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        if (eventLoopTaskQueueFactory == null) {
            return newTaskQueue0(DEFAULT_MAX_PENDING_TASKS);
        }
        return eventLoopTaskQueueFactory.newTaskQueue(DEFAULT_MAX_PENDING_TASKS);
    }

    private static final class SelectorTuple {
        final Selector selector;
        final Selector unwrappedSelector;

        SelectorTuple(Selector selector2) {
            this.unwrappedSelector = selector2;
            this.selector = selector2;
        }

        SelectorTuple(Selector selector2, Selector selector3) {
            this.unwrappedSelector = selector2;
            this.selector = selector3;
        }
    }

    private SelectorTuple openSelector() {
        try {
            final AbstractSelector openSelector = this.provider.openSelector();
            if (DISABLE_KEY_SET_OPTIMIZATION) {
                return new SelectorTuple(openSelector);
            }
            Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    try {
                        return Class.forName("sun.nio.ch.SelectorImpl", false, PlatformDependent.getSystemClassLoader());
                    } catch (Throwable th) {
                        return th;
                    }
                }
            });
            if (doPrivileged instanceof Class) {
                final Class cls = (Class) doPrivileged;
                if (cls.isAssignableFrom(openSelector.getClass())) {
                    final SelectedSelectionKeySet selectedSelectionKeySet = new SelectedSelectionKeySet();
                    Object doPrivileged2 = AccessController.doPrivileged(new PrivilegedAction<Object>() {
                        public Object run() {
                            try {
                                Field declaredField = cls.getDeclaredField("selectedKeys");
                                Field declaredField2 = cls.getDeclaredField("publicSelectedKeys");
                                if (PlatformDependent.javaVersion() >= 9 && PlatformDependent.hasUnsafe()) {
                                    long objectFieldOffset = PlatformDependent.objectFieldOffset(declaredField);
                                    long objectFieldOffset2 = PlatformDependent.objectFieldOffset(declaredField2);
                                    if (!(objectFieldOffset == -1 || objectFieldOffset2 == -1)) {
                                        PlatformDependent.putObject(openSelector, objectFieldOffset, selectedSelectionKeySet);
                                        PlatformDependent.putObject(openSelector, objectFieldOffset2, selectedSelectionKeySet);
                                        return null;
                                    }
                                }
                                Throwable trySetAccessible = ReflectionUtil.trySetAccessible(declaredField, true);
                                if (trySetAccessible != null) {
                                    return trySetAccessible;
                                }
                                Throwable trySetAccessible2 = ReflectionUtil.trySetAccessible(declaredField2, true);
                                if (trySetAccessible2 != null) {
                                    return trySetAccessible2;
                                }
                                declaredField.set(openSelector, selectedSelectionKeySet);
                                declaredField2.set(openSelector, selectedSelectionKeySet);
                                return null;
                            } catch (NoSuchFieldException e) {
                                return e;
                            } catch (IllegalAccessException e2) {
                                return e2;
                            }
                        }
                    });
                    if (doPrivileged2 instanceof Exception) {
                        this.selectedKeys = null;
                        logger.trace("failed to instrument a special java.util.Set into: {}", openSelector, (Exception) doPrivileged2);
                        return new SelectorTuple(openSelector);
                    }
                    this.selectedKeys = selectedSelectionKeySet;
                    logger.trace("instrumented a special java.util.Set into: {}", (Object) openSelector);
                    return new SelectorTuple(openSelector, new SelectedSelectionKeySetSelector(openSelector, selectedSelectionKeySet));
                }
            }
            if (doPrivileged instanceof Throwable) {
                logger.trace("failed to instrument a special java.util.Set into: {}", openSelector, (Throwable) doPrivileged);
            }
            return new SelectorTuple(openSelector);
        } catch (IOException e) {
            throw new ChannelException("failed to open a new selector", e);
        }
    }

    public SelectorProvider selectorProvider() {
        return this.provider;
    }

    /* access modifiers changed from: protected */
    public Queue<Runnable> newTaskQueue(int i) {
        return newTaskQueue0(i);
    }

    private static Queue<Runnable> newTaskQueue0(int i) {
        if (i == Integer.MAX_VALUE) {
            return PlatformDependent.newMpscQueue();
        }
        return PlatformDependent.newMpscQueue(i);
    }

    public void register(final SelectableChannel selectableChannel, final int i, final NioTask<?> nioTask) {
        ObjectUtil.checkNotNull(selectableChannel, "ch");
        if (i == 0) {
            throw new IllegalArgumentException("interestOps must be non-zero.");
        } else if (((selectableChannel.validOps() ^ -1) & i) == 0) {
            ObjectUtil.checkNotNull(nioTask, "task");
            if (isShutdown()) {
                throw new IllegalStateException("event loop shut down");
            } else if (inEventLoop()) {
                register0(selectableChannel, i, nioTask);
            } else {
                try {
                    submit((Runnable) new Runnable() {
                        public void run() {
                            NioEventLoop.this.register0(selectableChannel, i, nioTask);
                        }
                    }).sync();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        } else {
            throw new IllegalArgumentException("invalid interestOps: " + i + "(validOps: " + selectableChannel.validOps() + ')');
        }
    }

    /* access modifiers changed from: private */
    public void register0(SelectableChannel selectableChannel, int i, NioTask<?> nioTask) {
        try {
            selectableChannel.register(this.unwrappedSelector, i, nioTask);
        } catch (Exception e) {
            throw new EventLoopException("failed to register a channel", e);
        }
    }

    public int getIoRatio() {
        return this.ioRatio;
    }

    public void setIoRatio(int i) {
        if (i <= 0 || i > 100) {
            throw new IllegalArgumentException("ioRatio: " + i + " (expected: 0 < ioRatio <= 100)");
        }
        this.ioRatio = i;
    }

    public void rebuildSelector() {
        if (!inEventLoop()) {
            execute(new Runnable() {
                public void run() {
                    NioEventLoop.this.rebuildSelector0();
                }
            });
        } else {
            rebuildSelector0();
        }
    }

    public int registeredChannels() {
        return this.selector.keys().size() - this.cancelledKeys;
    }

    public Iterator<Channel> registeredChannelsIterator() {
        Set<SelectionKey> keys = this.selector.keys();
        if (keys.isEmpty()) {
            return SingleThreadEventLoop.ChannelsReadOnlyIterator.empty();
        }
        return new Iterator<Channel>(keys) {
            boolean isDone;
            Channel next;
            final Iterator<SelectionKey> selectionKeyIterator;
            final /* synthetic */ Set val$keys;

            {
                this.val$keys = r2;
                this.selectionKeyIterator = ((Set) ObjectUtil.checkNotNull(r2, "selectionKeys")).iterator();
            }

            public boolean hasNext() {
                if (this.isDone) {
                    return false;
                }
                if (this.next != null) {
                    return true;
                }
                Channel nextOrDone = nextOrDone();
                this.next = nextOrDone;
                if (nextOrDone != null) {
                    return true;
                }
                return false;
            }

            public Channel next() {
                if (!this.isDone) {
                    Channel channel = this.next;
                    if (channel == null && (channel = nextOrDone()) == null) {
                        throw new NoSuchElementException();
                    }
                    this.next = nextOrDone();
                    return channel;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException("remove");
            }

            private Channel nextOrDone() {
                Iterator<SelectionKey> it = this.selectionKeyIterator;
                while (it.hasNext()) {
                    SelectionKey next2 = it.next();
                    if (next2.isValid()) {
                        Object attachment = next2.attachment();
                        if (attachment instanceof AbstractNioChannel) {
                            return (AbstractNioChannel) attachment;
                        }
                    }
                }
                this.isDone = true;
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    public void rebuildSelector0() {
        Selector selector2 = this.selector;
        if (selector2 != null) {
            try {
                SelectorTuple openSelector = openSelector();
                int i = 0;
                for (SelectionKey next : selector2.keys()) {
                    Object attachment = next.attachment();
                    try {
                        if (next.isValid()) {
                            if (next.channel().keyFor(openSelector.unwrappedSelector) == null) {
                                int interestOps = next.interestOps();
                                next.cancel();
                                SelectionKey register = next.channel().register(openSelector.unwrappedSelector, interestOps, attachment);
                                if (attachment instanceof AbstractNioChannel) {
                                    ((AbstractNioChannel) attachment).selectionKey = register;
                                }
                                i++;
                            }
                        }
                    } catch (Exception e) {
                        logger.warn("Failed to re-register a Channel to the new Selector.", (Throwable) e);
                        if (attachment instanceof AbstractNioChannel) {
                            AbstractNioChannel abstractNioChannel = (AbstractNioChannel) attachment;
                            abstractNioChannel.unsafe().close(abstractNioChannel.unsafe().voidPromise());
                        } else {
                            invokeChannelUnregistered((NioTask) attachment, next, e);
                        }
                    }
                }
                this.selector = openSelector.selector;
                this.unwrappedSelector = openSelector.unwrappedSelector;
                try {
                    selector2.close();
                } catch (Throwable th) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Failed to close the old Selector.", th);
                    }
                }
                InternalLogger internalLogger = logger;
                if (internalLogger.isInfoEnabled()) {
                    internalLogger.info("Migrated " + i + " channel(s) to the new Selector.");
                }
            } catch (Exception e2) {
                logger.warn("Failed to create a new Selector.", (Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x010a, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x010b, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x010d, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x010e, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0119, code lost:
        closeAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0120, code lost:
        if (confirmShutdown() != false) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0122, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0135, code lost:
        r4 = new java.lang.StringBuilder();
        r5 = java.nio.channels.CancelledKeyException.class;
        r4.append("CancelledKeyException");
        r4.append(" raised by a Selector {} - JDK bug?");
        r2.debug(r4.toString(), r9.selector, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0155, code lost:
        closeAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x015c, code lost:
        if (confirmShutdown() != false) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x015e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0163, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0168, code lost:
        if (isShuttingDown() != false) goto L_0x016a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x016a, code lost:
        closeAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0171, code lost:
        if (confirmShutdown() != false) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0173, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0174, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0175, code lost:
        handleLoopException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0178, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0179, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x017b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00e1, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00e2, code lost:
        r3 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00e5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x00e7, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00e8, code lost:
        r3 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r2 != -1) goto L_0x0052;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0119 A[Catch:{ Error -> 0x012a, all -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0135 A[Catch:{ CancelledKeyException -> 0x010d, Error -> 0x00e5, all -> 0x010a, all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0155 A[Catch:{ Error -> 0x0161, all -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00c8 A[Catch:{ Error -> 0x00d4, all -> 0x00d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00e5 A[ExcHandler: Error (r0v6 'e' java.lang.Error A[CUSTOM_DECLARE]), Splitter:B:32:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r9 = this;
            r0 = 0
        L_0x0001:
            r1 = 0
        L_0x0002:
            io.netty.channel.SelectStrategy r2 = r9.selectStrategy     // Catch:{ IOException -> 0x00eb }
            io.netty.util.IntSupplier r3 = r9.selectNowSupplier     // Catch:{ IOException -> 0x00eb }
            boolean r4 = r9.hasTasks()     // Catch:{ IOException -> 0x00eb }
            int r2 = r2.calculateStrategy(r3, r4)     // Catch:{ IOException -> 0x00eb }
            r3 = -3
            if (r2 == r3) goto L_0x002f
            r3 = -2
            if (r2 == r3) goto L_0x0018
            r3 = -1
            if (r2 == r3) goto L_0x002f
            goto L_0x0052
        L_0x0018:
            boolean r2 = r9.isShuttingDown()     // Catch:{ Error -> 0x002d, all -> 0x0028 }
            if (r2 == 0) goto L_0x0002
            r9.closeAll()     // Catch:{ Error -> 0x002d, all -> 0x0028 }
            boolean r2 = r9.confirmShutdown()     // Catch:{ Error -> 0x002d, all -> 0x0028 }
            if (r2 == 0) goto L_0x0002
            return
        L_0x0028:
            r2 = move-exception
            handleLoopException(r2)
            goto L_0x0002
        L_0x002d:
            r0 = move-exception
            throw r0
        L_0x002f:
            long r3 = r9.nextScheduledTaskDeadlineNanos()     // Catch:{ IOException -> 0x00eb }
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x003e
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x003e:
            java.util.concurrent.atomic.AtomicLong r7 = r9.nextWakeupNanos     // Catch:{ IOException -> 0x00eb }
            r7.set(r3)     // Catch:{ IOException -> 0x00eb }
            boolean r7 = r9.hasTasks()     // Catch:{ all -> 0x00da }
            if (r7 != 0) goto L_0x004d
            int r2 = r9.select(r3)     // Catch:{ all -> 0x00da }
        L_0x004d:
            java.util.concurrent.atomic.AtomicLong r3 = r9.nextWakeupNanos     // Catch:{ IOException -> 0x00eb }
            r3.lazySet(r5)     // Catch:{ IOException -> 0x00eb }
        L_0x0052:
            int r3 = r1 + 1
            r9.cancelledKeys = r0     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            r9.needsToSelectAgain = r0     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            int r4 = r9.ioRatio     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            r5 = 100
            if (r4 != r5) goto L_0x006e
            if (r2 <= 0) goto L_0x0069
            r9.processSelectedKeys()     // Catch:{ all -> 0x0064 }
            goto L_0x0069
        L_0x0064:
            r1 = move-exception
            r9.runAllTasks()     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            throw r1     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
        L_0x0069:
            boolean r4 = r9.runAllTasks()     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            goto L_0x009f
        L_0x006e:
            if (r2 <= 0) goto L_0x0099
            long r5 = java.lang.System.nanoTime()     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            r9.processSelectedKeys()     // Catch:{ all -> 0x0088 }
            long r7 = java.lang.System.nanoTime()     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            long r7 = r7 - r5
            int r5 = 100 - r4
            long r5 = (long) r5     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            long r7 = r7 * r5
            long r4 = (long) r4     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            long r7 = r7 / r4
            boolean r4 = r9.runAllTasks(r7)     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            goto L_0x009f
        L_0x0088:
            r1 = move-exception
            long r7 = java.lang.System.nanoTime()     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            long r7 = r7 - r5
            int r2 = 100 - r4
            long r5 = (long) r2     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            long r7 = r7 * r5
            long r4 = (long) r4     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            long r7 = r7 / r4
            r9.runAllTasks(r7)     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            throw r1     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
        L_0x0099:
            r4 = 0
            boolean r4 = r9.runAllTasks(r4)     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
        L_0x009f:
            if (r4 != 0) goto L_0x00ab
            if (r2 <= 0) goto L_0x00a4
            goto L_0x00ab
        L_0x00a4:
            boolean r1 = r9.unexpectedSelectorWakeup(r3)     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            if (r1 == 0) goto L_0x00c2
            goto L_0x00c1
        L_0x00ab:
            r2 = 3
            if (r3 <= r2) goto L_0x00c1
            io.netty.util.internal.logging.InternalLogger r2 = logger     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            boolean r4 = r2.isDebugEnabled()     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            if (r4 == 0) goto L_0x00c1
            java.lang.String r4 = "Selector.select() returned prematurely {} times in a row for Selector {}."
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            java.nio.channels.Selector r5 = r9.selector     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
            r2.debug(r4, r1, r5)     // Catch:{ CancelledKeyException -> 0x00d8, Error -> 0x00e5, all -> 0x00d6 }
        L_0x00c1:
            r3 = 0
        L_0x00c2:
            boolean r1 = r9.isShuttingDown()     // Catch:{ Error -> 0x00d4, all -> 0x00d2 }
            if (r1 == 0) goto L_0x0127
            r9.closeAll()     // Catch:{ Error -> 0x00d4, all -> 0x00d2 }
            boolean r1 = r9.confirmShutdown()     // Catch:{ Error -> 0x00d4, all -> 0x00d2 }
            if (r1 == 0) goto L_0x0127
            return
        L_0x00d2:
            r1 = move-exception
            goto L_0x0124
        L_0x00d4:
            r0 = move-exception
            throw r0
        L_0x00d6:
            r1 = move-exception
            goto L_0x0110
        L_0x00d8:
            r1 = move-exception
            goto L_0x012d
        L_0x00da:
            r2 = move-exception
            java.util.concurrent.atomic.AtomicLong r3 = r9.nextWakeupNanos     // Catch:{ IOException -> 0x00eb }
            r3.lazySet(r5)     // Catch:{ IOException -> 0x00eb }
            throw r2     // Catch:{ IOException -> 0x00eb }
        L_0x00e1:
            r2 = move-exception
            r3 = r1
            r1 = r2
            goto L_0x0110
        L_0x00e5:
            r0 = move-exception
            goto L_0x012c
        L_0x00e7:
            r2 = move-exception
            r3 = r1
            r1 = r2
            goto L_0x012d
        L_0x00eb:
            r2 = move-exception
            r9.rebuildSelector0()     // Catch:{ CancelledKeyException -> 0x00e7, Error -> 0x00e5, all -> 0x00e1 }
            handleLoopException(r2)     // Catch:{ CancelledKeyException -> 0x010d, Error -> 0x00e5, all -> 0x010a }
            boolean r1 = r9.isShuttingDown()     // Catch:{ Error -> 0x0108, all -> 0x0102 }
            if (r1 == 0) goto L_0x0001
            r9.closeAll()     // Catch:{ Error -> 0x0108, all -> 0x0102 }
            boolean r1 = r9.confirmShutdown()     // Catch:{ Error -> 0x0108, all -> 0x0102 }
            if (r1 == 0) goto L_0x0001
            return
        L_0x0102:
            r1 = move-exception
            handleLoopException(r1)
            goto L_0x0001
        L_0x0108:
            r0 = move-exception
            throw r0
        L_0x010a:
            r1 = move-exception
            r3 = 0
            goto L_0x0110
        L_0x010d:
            r1 = move-exception
            r3 = 0
            goto L_0x012d
        L_0x0110:
            handleLoopException(r1)     // Catch:{ all -> 0x0163 }
            boolean r1 = r9.isShuttingDown()     // Catch:{ Error -> 0x012a, all -> 0x0123 }
            if (r1 == 0) goto L_0x0127
            r9.closeAll()     // Catch:{ Error -> 0x012a, all -> 0x0123 }
            boolean r1 = r9.confirmShutdown()     // Catch:{ Error -> 0x012a, all -> 0x0123 }
            if (r1 == 0) goto L_0x0127
            return
        L_0x0123:
            r1 = move-exception
        L_0x0124:
            handleLoopException(r1)
        L_0x0127:
            r1 = r3
            goto L_0x0002
        L_0x012a:
            r0 = move-exception
            throw r0
        L_0x012c:
            throw r0     // Catch:{ all -> 0x0163 }
        L_0x012d:
            io.netty.util.internal.logging.InternalLogger r2 = logger     // Catch:{ all -> 0x0163 }
            boolean r4 = r2.isDebugEnabled()     // Catch:{ all -> 0x0163 }
            if (r4 == 0) goto L_0x014f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0163 }
            r4.<init>()     // Catch:{ all -> 0x0163 }
            java.lang.Class<java.nio.channels.CancelledKeyException> r5 = java.nio.channels.CancelledKeyException.class
            java.lang.String r5 = "CancelledKeyException"
            r4.append(r5)     // Catch:{ all -> 0x0163 }
            java.lang.String r5 = " raised by a Selector {} - JDK bug?"
            r4.append(r5)     // Catch:{ all -> 0x0163 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0163 }
            java.nio.channels.Selector r5 = r9.selector     // Catch:{ all -> 0x0163 }
            r2.debug(r4, r5, r1)     // Catch:{ all -> 0x0163 }
        L_0x014f:
            boolean r1 = r9.isShuttingDown()     // Catch:{ Error -> 0x0161, all -> 0x015f }
            if (r1 == 0) goto L_0x0127
            r9.closeAll()     // Catch:{ Error -> 0x0161, all -> 0x015f }
            boolean r1 = r9.confirmShutdown()     // Catch:{ Error -> 0x0161, all -> 0x015f }
            if (r1 == 0) goto L_0x0127
            return
        L_0x015f:
            r1 = move-exception
            goto L_0x0124
        L_0x0161:
            r0 = move-exception
            throw r0
        L_0x0163:
            r0 = move-exception
            boolean r1 = r9.isShuttingDown()     // Catch:{ Error -> 0x0179, all -> 0x0174 }
            if (r1 == 0) goto L_0x0178
            r9.closeAll()     // Catch:{ Error -> 0x0179, all -> 0x0174 }
            boolean r1 = r9.confirmShutdown()     // Catch:{ Error -> 0x0179, all -> 0x0174 }
            if (r1 == 0) goto L_0x0178
            return
        L_0x0174:
            r1 = move-exception
            handleLoopException(r1)
        L_0x0178:
            throw r0
        L_0x0179:
            r0 = move-exception
            goto L_0x017c
        L_0x017b:
            throw r0
        L_0x017c:
            goto L_0x017b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.nio.NioEventLoop.run():void");
    }

    private boolean unexpectedSelectorWakeup(int i) {
        if (Thread.interrupted()) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled()) {
                internalLogger.debug("Selector.select() returned prematurely because Thread.currentThread().interrupt() was called. Use NioEventLoop.shutdownGracefully() to shutdown the NioEventLoop.");
            }
            return true;
        }
        int i2 = SELECTOR_AUTO_REBUILD_THRESHOLD;
        if (i2 <= 0 || i < i2) {
            return false;
        }
        logger.warn("Selector.select() returned prematurely {} times in a row; rebuilding Selector {}.", Integer.valueOf(i), this.selector);
        rebuildSelector();
        return true;
    }

    private static void handleLoopException(Throwable th) {
        logger.warn("Unexpected exception in the selector loop.", th);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException unused) {
        }
    }

    private void processSelectedKeys() {
        if (this.selectedKeys != null) {
            processSelectedKeysOptimized();
        } else {
            processSelectedKeysPlain(this.selector.selectedKeys());
        }
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        try {
            this.selector.close();
        } catch (IOException e) {
            logger.warn("Failed to close a selector.", (Throwable) e);
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel(SelectionKey selectionKey) {
        selectionKey.cancel();
        int i = this.cancelledKeys + 1;
        this.cancelledKeys = i;
        if (i >= 256) {
            this.cancelledKeys = 0;
            this.needsToSelectAgain = true;
        }
    }

    private void processSelectedKeysPlain(Set<SelectionKey> set) {
        if (!set.isEmpty()) {
            Iterator<SelectionKey> it = set.iterator();
            while (true) {
                SelectionKey next = it.next();
                Object attachment = next.attachment();
                it.remove();
                if (attachment instanceof AbstractNioChannel) {
                    processSelectedKey(next, (AbstractNioChannel) attachment);
                } else {
                    processSelectedKey(next, (NioTask<SelectableChannel>) (NioTask) attachment);
                }
                if (it.hasNext()) {
                    if (this.needsToSelectAgain) {
                        selectAgain();
                        Set<SelectionKey> selectedKeys2 = this.selector.selectedKeys();
                        if (!selectedKeys2.isEmpty()) {
                            it = selectedKeys2.iterator();
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void processSelectedKeysOptimized() {
        int i = 0;
        while (i < this.selectedKeys.size) {
            SelectionKey selectionKey = this.selectedKeys.keys[i];
            this.selectedKeys.keys[i] = null;
            Object attachment = selectionKey.attachment();
            if (attachment instanceof AbstractNioChannel) {
                processSelectedKey(selectionKey, (AbstractNioChannel) attachment);
            } else {
                processSelectedKey(selectionKey, (NioTask<SelectableChannel>) (NioTask) attachment);
            }
            if (this.needsToSelectAgain) {
                this.selectedKeys.reset(i + 1);
                selectAgain();
                i = -1;
            }
            i++;
        }
    }

    private void processSelectedKey(SelectionKey selectionKey, AbstractNioChannel abstractNioChannel) {
        AbstractNioChannel.NioUnsafe unsafe = abstractNioChannel.unsafe();
        if (!selectionKey.isValid()) {
            try {
                if (abstractNioChannel.eventLoop() == this) {
                    unsafe.close(unsafe.voidPromise());
                }
            } catch (Throwable unused) {
            }
        } else {
            try {
                int readyOps = selectionKey.readyOps();
                if ((readyOps & 8) != 0) {
                    selectionKey.interestOps(selectionKey.interestOps() & -9);
                    unsafe.finishConnect();
                }
                if ((readyOps & 4) != 0) {
                    unsafe.forceFlush();
                }
                if ((readyOps & 17) != 0 || readyOps == 0) {
                    unsafe.read();
                }
            } catch (CancelledKeyException unused2) {
                unsafe.close(unsafe.voidPromise());
            }
        }
    }

    private static void processSelectedKey(SelectionKey selectionKey, NioTask<SelectableChannel> nioTask) {
        try {
            nioTask.channelReady(selectionKey.channel(), selectionKey);
            if (!selectionKey.isValid()) {
                invokeChannelUnregistered(nioTask, selectionKey, (Throwable) null);
            }
        } catch (Exception e) {
            selectionKey.cancel();
            invokeChannelUnregistered(nioTask, selectionKey, e);
        } catch (Throwable th) {
            selectionKey.cancel();
            invokeChannelUnregistered(nioTask, selectionKey, (Throwable) null);
            throw th;
        }
    }

    private void closeAll() {
        selectAgain();
        Set<SelectionKey> keys = this.selector.keys();
        ArrayList<AbstractNioChannel> arrayList = new ArrayList<>(keys.size());
        for (SelectionKey next : keys) {
            Object attachment = next.attachment();
            if (attachment instanceof AbstractNioChannel) {
                arrayList.add((AbstractNioChannel) attachment);
            } else {
                next.cancel();
                invokeChannelUnregistered((NioTask) attachment, next, (Throwable) null);
            }
        }
        for (AbstractNioChannel abstractNioChannel : arrayList) {
            abstractNioChannel.unsafe().close(abstractNioChannel.unsafe().voidPromise());
        }
    }

    private static void invokeChannelUnregistered(NioTask<SelectableChannel> nioTask, SelectionKey selectionKey, Throwable th) {
        try {
            nioTask.channelUnregistered(selectionKey.channel(), th);
        } catch (Exception e) {
            logger.warn("Unexpected exception while running NioTask.channelUnregistered()", (Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void wakeup(boolean z) {
        if (!z && this.nextWakeupNanos.getAndSet(-1) != -1) {
            this.selector.wakeup();
        }
    }

    /* access modifiers changed from: protected */
    public boolean beforeScheduledTaskSubmitted(long j) {
        return j < this.nextWakeupNanos.get();
    }

    /* access modifiers changed from: protected */
    public boolean afterScheduledTaskSubmitted(long j) {
        return j < this.nextWakeupNanos.get();
    }

    /* access modifiers changed from: package-private */
    public Selector unwrappedSelector() {
        return this.unwrappedSelector;
    }

    /* access modifiers changed from: package-private */
    public int selectNow() throws IOException {
        return this.selector.selectNow();
    }

    private int select(long j) throws IOException {
        if (j == Long.MAX_VALUE) {
            return this.selector.select();
        }
        long deadlineToDelayNanos = deadlineToDelayNanos(j + 995000) / PickTimeFragment.SECONDS_IN_MICROS;
        return deadlineToDelayNanos <= 0 ? this.selector.selectNow() : this.selector.select(deadlineToDelayNanos);
    }

    private void selectAgain() {
        this.needsToSelectAgain = false;
        try {
            this.selector.selectNow();
        } catch (Throwable th) {
            logger.warn("Failed to update SelectionKeys.", th);
        }
    }
}
