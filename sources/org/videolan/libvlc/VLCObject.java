package org.videolan.libvlc;

import android.os.Handler;
import android.os.Looper;
import org.videolan.libvlc.interfaces.AbstractVLCEvent;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IVLCObject;

abstract class VLCObject<T extends AbstractVLCEvent> implements IVLCObject<T> {
    private AbstractVLCEvent.Listener<T> mEventListener;
    private Handler mHandler;
    final ILibVLC mILibVLC;
    private long mInstance;
    private int mNativeRefCount;

    private native void nativeDetachEvents();

    public native long getInstance();

    /* access modifiers changed from: protected */
    public abstract T onEventNative(int i, long j, long j2, float f, String str);

    /* access modifiers changed from: protected */
    public abstract void onReleaseNative();

    protected VLCObject(ILibVLC iLibVLC) {
        this.mEventListener = null;
        this.mHandler = null;
        this.mNativeRefCount = 1;
        this.mInstance = 0;
        this.mILibVLC = iLibVLC;
    }

    protected VLCObject(IVLCObject iVLCObject) {
        this.mEventListener = null;
        this.mHandler = null;
        this.mNativeRefCount = 1;
        this.mInstance = 0;
        this.mILibVLC = iVLCObject.getLibVLC();
    }

    protected VLCObject() {
        this.mEventListener = null;
        this.mHandler = null;
        this.mNativeRefCount = 1;
        this.mInstance = 0;
        this.mILibVLC = null;
    }

    public synchronized boolean isReleased() {
        return this.mNativeRefCount == 0;
    }

    public final synchronized boolean retain() {
        int i = this.mNativeRefCount;
        if (i <= 0) {
            return false;
        }
        this.mNativeRefCount = i + 1;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        if (r0 != 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        nativeDetachEvents();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        onReleaseNative();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.mNativeRefCount     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            return
        L_0x0007:
            if (r0 <= 0) goto L_0x000e
            int r0 = r0 + -1
            r2.mNativeRefCount = r0     // Catch:{ all -> 0x0025 }
            goto L_0x000f
        L_0x000e:
            r0 = -1
        L_0x000f:
            if (r0 != 0) goto L_0x0015
            r1 = 0
            r2.setEventListener(r1)     // Catch:{ all -> 0x0025 }
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0024
            r2.nativeDetachEvents()
            monitor-enter(r2)
            r2.onReleaseNative()     // Catch:{ all -> 0x0021 }
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            goto L_0x0024
        L_0x0021:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            throw r0
        L_0x0024:
            return
        L_0x0025:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.VLCObject.release():void");
    }

    /* access modifiers changed from: protected */
    public synchronized void finalize() {
        if (!isReleased()) {
            throw new AssertionError("VLCObject (" + getClass().getName() + ") finalized but not natively released (" + this.mNativeRefCount + " refs)");
        }
    }

    public ILibVLC getLibVLC() {
        return this.mILibVLC;
    }

    /* access modifiers changed from: protected */
    public synchronized void setEventListener(AbstractVLCEvent.Listener<T> listener) {
        setEventListener(listener, (Handler) null);
    }

    /* access modifiers changed from: protected */
    public synchronized void setEventListener(AbstractVLCEvent.Listener<T> listener, Handler handler) {
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.mEventListener = listener;
        if (listener == null) {
            this.mHandler = null;
        } else if (this.mHandler == null) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            this.mHandler = handler;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void dispatchEventFromNative(int r2, long r3, long r5, float r7, java.lang.String r8) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isReleased()     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            org.videolan.libvlc.interfaces.AbstractVLCEvent r2 = r1.onEventNative(r2, r3, r5, r7, r8)     // Catch:{ all -> 0x0021 }
            if (r2 == 0) goto L_0x001f
            org.videolan.libvlc.interfaces.AbstractVLCEvent$Listener<T> r3 = r1.mEventListener     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x001f
            android.os.Handler r4 = r1.mHandler     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x001f
            org.videolan.libvlc.VLCObject$1EventRunnable r5 = new org.videolan.libvlc.VLCObject$1EventRunnable     // Catch:{ all -> 0x0021 }
            r5.<init>(r3, r2)     // Catch:{ all -> 0x0021 }
            r4.post(r5)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r1)
            return
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.VLCObject.dispatchEventFromNative(int, long, long, float, java.lang.String):void");
    }
}
