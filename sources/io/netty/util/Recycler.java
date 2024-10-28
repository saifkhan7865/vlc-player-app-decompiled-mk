package io.netty.util;

import io.ktor.server.auth.OAuth2RequestParameters;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import java.lang.Thread;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public abstract class Recycler<T> {
    /* access modifiers changed from: private */
    public static final boolean BATCH_FAST_TL_ONLY;
    /* access modifiers changed from: private */
    public static final boolean BLOCKING_POOL;
    private static final int DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD = 4096;
    private static final int DEFAULT_MAX_CAPACITY_PER_THREAD;
    private static final int DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD;
    private static final EnhancedHandle<?> NOOP_HANDLE = new EnhancedHandle<Object>() {
        public void recycle(Object obj) {
        }

        public void unguardedRecycle(Object obj) {
        }

        public String toString() {
            return "NOOP_HANDLE";
        }
    };
    private static final int RATIO;
    private static final InternalLogger logger;
    /* access modifiers changed from: private */
    public final int chunkSize;
    /* access modifiers changed from: private */
    public final int interval;
    /* access modifiers changed from: private */
    public final int maxCapacityPerThread;
    private final FastThreadLocal<LocalPool<T>> threadLocal;

    public interface Handle<T> extends ObjectPool.Handle<T> {
    }

    /* access modifiers changed from: protected */
    public abstract T newObject(Handle<T> handle);

    static {
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) Recycler.class);
        logger = instance;
        int i = 4096;
        int i2 = SystemPropertyUtil.getInt("io.netty.recycler.maxCapacityPerThread", SystemPropertyUtil.getInt("io.netty.recycler.maxCapacity", 4096));
        if (i2 >= 0) {
            i = i2;
        }
        DEFAULT_MAX_CAPACITY_PER_THREAD = i;
        int i3 = SystemPropertyUtil.getInt("io.netty.recycler.chunkSize", 32);
        DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD = i3;
        int max = Math.max(0, SystemPropertyUtil.getInt("io.netty.recycler.ratio", 8));
        RATIO = max;
        boolean z = SystemPropertyUtil.getBoolean("io.netty.recycler.blocking", false);
        BLOCKING_POOL = z;
        boolean z2 = SystemPropertyUtil.getBoolean("io.netty.recycler.batchFastThreadLocalOnly", true);
        BATCH_FAST_TL_ONLY = z2;
        if (!instance.isDebugEnabled()) {
            return;
        }
        if (i == 0) {
            instance.debug("-Dio.netty.recycler.maxCapacityPerThread: disabled");
            instance.debug("-Dio.netty.recycler.ratio: disabled");
            instance.debug("-Dio.netty.recycler.chunkSize: disabled");
            instance.debug("-Dio.netty.recycler.blocking: disabled");
            instance.debug("-Dio.netty.recycler.batchFastThreadLocalOnly: disabled");
            return;
        }
        instance.debug("-Dio.netty.recycler.maxCapacityPerThread: {}", (Object) Integer.valueOf(i));
        instance.debug("-Dio.netty.recycler.ratio: {}", (Object) Integer.valueOf(max));
        instance.debug("-Dio.netty.recycler.chunkSize: {}", (Object) Integer.valueOf(i3));
        instance.debug("-Dio.netty.recycler.blocking: {}", (Object) Boolean.valueOf(z));
        instance.debug("-Dio.netty.recycler.batchFastThreadLocalOnly: {}", (Object) Boolean.valueOf(z2));
    }

    protected Recycler() {
        this(DEFAULT_MAX_CAPACITY_PER_THREAD);
    }

    protected Recycler(int i) {
        this(i, RATIO, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
    }

    @Deprecated
    protected Recycler(int i, int i2) {
        this(i, RATIO, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
    }

    @Deprecated
    protected Recycler(int i, int i2, int i3, int i4) {
        this(i, i3, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
    }

    @Deprecated
    protected Recycler(int i, int i2, int i3, int i4, int i5) {
        this(i, i3, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
    }

    protected Recycler(int i, int i2, int i3) {
        this.threadLocal = new FastThreadLocal<LocalPool<T>>() {
            /* access modifiers changed from: protected */
            public LocalPool<T> initialValue() {
                return new LocalPool<>(Recycler.this.maxCapacityPerThread, Recycler.this.interval, Recycler.this.chunkSize);
            }

            /* access modifiers changed from: protected */
            public void onRemoval(LocalPool<T> localPool) throws Exception {
                super.onRemoval(localPool);
                MessagePassingQueue access$400 = localPool.pooledHandles;
                MessagePassingQueue unused = localPool.pooledHandles = null;
                Thread unused2 = localPool.owner = null;
                access$400.clear();
            }
        };
        this.interval = Math.max(0, i2);
        if (i <= 0) {
            this.maxCapacityPerThread = 0;
            this.chunkSize = 0;
            return;
        }
        int max = Math.max(4, i);
        this.maxCapacityPerThread = max;
        this.chunkSize = Math.max(2, Math.min(i3, max >> 1));
    }

    public final T get() {
        if (this.maxCapacityPerThread == 0) {
            return newObject(NOOP_HANDLE);
        }
        LocalPool localPool = this.threadLocal.get();
        DefaultHandle claim = localPool.claim();
        if (claim != null) {
            return claim.get();
        }
        DefaultHandle newHandle = localPool.newHandle();
        if (newHandle == null) {
            return newObject(NOOP_HANDLE);
        }
        T newObject = newObject(newHandle);
        newHandle.set(newObject);
        return newObject;
    }

    @Deprecated
    public final boolean recycle(T t, Handle<T> handle) {
        if (handle == NOOP_HANDLE) {
            return false;
        }
        handle.recycle(t);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int threadLocalSize() {
        LocalPool ifExists = this.threadLocal.getIfExists();
        if (ifExists == null) {
            return 0;
        }
        return ifExists.batch.size() + ifExists.pooledHandles.size();
    }

    public static abstract class EnhancedHandle<T> implements Handle<T> {
        public abstract void unguardedRecycle(Object obj);

        private EnhancedHandle() {
        }
    }

    private static final class DefaultHandle<T> extends EnhancedHandle<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int STATE_AVAILABLE = 1;
        private static final int STATE_CLAIMED = 0;
        private static final AtomicIntegerFieldUpdater<DefaultHandle<?>> STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(DefaultHandle.class, OAuth2RequestParameters.State);
        private final LocalPool<T> localPool;
        private volatile int state;
        private T value;

        static {
            Class<Recycler> cls = Recycler.class;
        }

        DefaultHandle(LocalPool<T> localPool2) {
            super();
            this.localPool = localPool2;
        }

        public void recycle(Object obj) {
            if (obj == this.value) {
                this.localPool.release(this, true);
                return;
            }
            throw new IllegalArgumentException("object does not belong to handle");
        }

        public void unguardedRecycle(Object obj) {
            if (obj == this.value) {
                this.localPool.release(this, false);
                return;
            }
            throw new IllegalArgumentException("object does not belong to handle");
        }

        /* access modifiers changed from: package-private */
        public T get() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void set(T t) {
            this.value = t;
        }

        /* access modifiers changed from: package-private */
        public void toClaimed() {
            STATE_UPDATER.lazySet(this, 0);
        }

        /* access modifiers changed from: package-private */
        public void toAvailable() {
            if (STATE_UPDATER.getAndSet(this, 1) == 1) {
                throw new IllegalStateException("Object has been recycled already.");
            }
        }

        /* access modifiers changed from: package-private */
        public void unguardedToAvailable() {
            if (this.state != 1) {
                STATE_UPDATER.lazySet(this, 1);
                return;
            }
            throw new IllegalStateException("Object has been recycled already.");
        }
    }

    private static final class LocalPool<T> implements MessagePassingQueue.Consumer<DefaultHandle<T>> {
        /* access modifiers changed from: private */
        public final ArrayDeque<DefaultHandle<T>> batch;
        private final int chunkSize;
        /* access modifiers changed from: private */
        public volatile Thread owner;
        /* access modifiers changed from: private */
        public volatile MessagePassingQueue<DefaultHandle<T>> pooledHandles;
        private int ratioCounter;
        private final int ratioInterval;

        LocalPool(int i, int i2, int i3) {
            this.ratioInterval = i2;
            this.chunkSize = i3;
            this.batch = new ArrayDeque<>(i3);
            Thread currentThread = Thread.currentThread();
            if (Recycler.BATCH_FAST_TL_ONLY && !(currentThread instanceof FastThreadLocalThread)) {
                currentThread = null;
            }
            this.owner = currentThread;
            if (Recycler.BLOCKING_POOL) {
                this.pooledHandles = new BlockingMessageQueue(i);
            } else {
                this.pooledHandles = (MessagePassingQueue) PlatformDependent.newMpscQueue(i3, i);
            }
            this.ratioCounter = i2;
        }

        /* access modifiers changed from: package-private */
        public DefaultHandle<T> claim() {
            MessagePassingQueue<DefaultHandle<T>> messagePassingQueue = this.pooledHandles;
            if (messagePassingQueue == null) {
                return null;
            }
            if (this.batch.isEmpty()) {
                messagePassingQueue.drain(this, this.chunkSize);
            }
            DefaultHandle<T> pollFirst = this.batch.pollFirst();
            if (pollFirst != null) {
                pollFirst.toClaimed();
            }
            return pollFirst;
        }

        /* access modifiers changed from: package-private */
        public void release(DefaultHandle<T> defaultHandle, boolean z) {
            if (z) {
                defaultHandle.toAvailable();
            } else {
                defaultHandle.unguardedToAvailable();
            }
            Thread thread = this.owner;
            if (thread != null && Thread.currentThread() == thread && this.batch.size() < this.chunkSize) {
                accept(defaultHandle);
            } else if (thread == null || !isTerminated(thread)) {
                MessagePassingQueue<DefaultHandle<T>> messagePassingQueue = this.pooledHandles;
                if (messagePassingQueue != null) {
                    messagePassingQueue.relaxedOffer(defaultHandle);
                }
            } else {
                this.owner = null;
                this.pooledHandles = null;
            }
        }

        private static boolean isTerminated(Thread thread) {
            if (PlatformDependent.isJ9Jvm()) {
                if (!thread.isAlive()) {
                    return true;
                }
            } else if (thread.getState() == Thread.State.TERMINATED) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public DefaultHandle<T> newHandle() {
            int i = this.ratioCounter + 1;
            this.ratioCounter = i;
            if (i < this.ratioInterval) {
                return null;
            }
            this.ratioCounter = 0;
            return new DefaultHandle<>(this);
        }

        public void accept(DefaultHandle<T> defaultHandle) {
            this.batch.addLast(defaultHandle);
        }
    }

    private static final class BlockingMessageQueue<T> implements MessagePassingQueue<T> {
        private final Queue<T> deque = new ArrayDeque();
        private final int maxCapacity;

        BlockingMessageQueue(int i) {
            this.maxCapacity = i;
        }

        public synchronized boolean offer(T t) {
            if (this.deque.size() == this.maxCapacity) {
                return false;
            }
            return this.deque.offer(t);
        }

        public synchronized T poll() {
            return this.deque.poll();
        }

        public synchronized T peek() {
            return this.deque.peek();
        }

        public synchronized int size() {
            return this.deque.size();
        }

        public synchronized void clear() {
            this.deque.clear();
        }

        public synchronized boolean isEmpty() {
            return this.deque.isEmpty();
        }

        public int capacity() {
            return this.maxCapacity;
        }

        public boolean relaxedOffer(T t) {
            return offer(t);
        }

        public T relaxedPoll() {
            return poll();
        }

        public T relaxedPeek() {
            return peek();
        }

        public int drain(MessagePassingQueue.Consumer<T> consumer, int i) {
            int i2 = 0;
            while (i2 < i) {
                Object poll = poll();
                if (poll == null) {
                    break;
                }
                consumer.accept(poll);
                i2++;
            }
            return i2;
        }

        public int fill(MessagePassingQueue.Supplier<T> supplier, int i) {
            throw new UnsupportedOperationException();
        }

        public int drain(MessagePassingQueue.Consumer<T> consumer) {
            throw new UnsupportedOperationException();
        }

        public int fill(MessagePassingQueue.Supplier<T> supplier) {
            throw new UnsupportedOperationException();
        }

        public void drain(MessagePassingQueue.Consumer<T> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
            throw new UnsupportedOperationException();
        }

        public void fill(MessagePassingQueue.Supplier<T> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
            throw new UnsupportedOperationException();
        }
    }
}
