package io.netty.util.internal;

import io.netty.util.concurrent.FastThreadLocalThread;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObjectCleaner {
    /* access modifiers changed from: private */
    public static final AtomicBoolean CLEANER_RUNNING = new AtomicBoolean(false);
    private static final Runnable CLEANER_TASK = new Runnable() {
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
        /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 0
                r1 = 0
            L_0x0002:
                java.util.Set r2 = io.netty.util.internal.ObjectCleaner.LIVE_SET
                boolean r2 = r2.isEmpty()
                r3 = 1
                if (r2 != 0) goto L_0x002c
                java.lang.ref.ReferenceQueue r2 = io.netty.util.internal.ObjectCleaner.REFERENCE_QUEUE     // Catch:{ InterruptedException -> 0x0029 }
                int r4 = io.netty.util.internal.ObjectCleaner.REFERENCE_QUEUE_POLL_TIMEOUT_MS     // Catch:{ InterruptedException -> 0x0029 }
                long r4 = (long) r4     // Catch:{ InterruptedException -> 0x0029 }
                java.lang.ref.Reference r2 = r2.remove(r4)     // Catch:{ InterruptedException -> 0x0029 }
                io.netty.util.internal.ObjectCleaner$AutomaticCleanerReference r2 = (io.netty.util.internal.ObjectCleaner.AutomaticCleanerReference) r2     // Catch:{ InterruptedException -> 0x0029 }
                if (r2 == 0) goto L_0x0002
                r2.cleanup()     // Catch:{ all -> 0x0021 }
            L_0x0021:
                java.util.Set r3 = io.netty.util.internal.ObjectCleaner.LIVE_SET
                r3.remove(r2)
                goto L_0x0002
            L_0x0029:
                r1 = 1
                goto L_0x0002
            L_0x002c:
                java.util.concurrent.atomic.AtomicBoolean r2 = io.netty.util.internal.ObjectCleaner.CLEANER_RUNNING
                r2.set(r0)
                java.util.Set r2 = io.netty.util.internal.ObjectCleaner.LIVE_SET
                boolean r2 = r2.isEmpty()
                if (r2 != 0) goto L_0x0047
                java.util.concurrent.atomic.AtomicBoolean r2 = io.netty.util.internal.ObjectCleaner.CLEANER_RUNNING
                boolean r2 = r2.compareAndSet(r0, r3)
                if (r2 != 0) goto L_0x0002
            L_0x0047:
                if (r1 == 0) goto L_0x0050
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x0050:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.util.internal.ObjectCleaner.AnonymousClass1.run():void");
        }
    };
    static final String CLEANER_THREAD_NAME = "ObjectCleanerThread";
    /* access modifiers changed from: private */
    public static final Set<AutomaticCleanerReference> LIVE_SET = new ConcurrentSet();
    /* access modifiers changed from: private */
    public static final ReferenceQueue<Object> REFERENCE_QUEUE = new ReferenceQueue<>();
    /* access modifiers changed from: private */
    public static final int REFERENCE_QUEUE_POLL_TIMEOUT_MS = Math.max(500, SystemPropertyUtil.getInt("io.netty.util.internal.ObjectCleaner.refQueuePollTimeout", 10000));

    public static void register(Object obj, Runnable runnable) {
        LIVE_SET.add(new AutomaticCleanerReference(obj, (Runnable) ObjectUtil.checkNotNull(runnable, "cleanupTask")));
        if (CLEANER_RUNNING.compareAndSet(false, true)) {
            final FastThreadLocalThread fastThreadLocalThread = new FastThreadLocalThread(CLEANER_TASK);
            fastThreadLocalThread.setPriority(1);
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    fastThreadLocalThread.setContextClassLoader((ClassLoader) null);
                    return null;
                }
            });
            fastThreadLocalThread.setName(CLEANER_THREAD_NAME);
            fastThreadLocalThread.setDaemon(true);
            fastThreadLocalThread.start();
        }
    }

    public static int getLiveSetCount() {
        return LIVE_SET.size();
    }

    private ObjectCleaner() {
    }

    private static final class AutomaticCleanerReference extends WeakReference<Object> {
        private final Runnable cleanupTask;

        public Thread get() {
            return null;
        }

        AutomaticCleanerReference(Object obj, Runnable runnable) {
            super(obj, ObjectCleaner.REFERENCE_QUEUE);
            this.cleanupTask = runnable;
        }

        /* access modifiers changed from: package-private */
        public void cleanup() {
            this.cleanupTask.run();
        }

        public void clear() {
            ObjectCleaner.LIVE_SET.remove(this);
            super.clear();
        }
    }
}
