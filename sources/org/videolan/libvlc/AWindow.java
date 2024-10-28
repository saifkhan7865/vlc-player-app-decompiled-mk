package org.videolan.libvlc;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.videolan.libvlc.interfaces.IVLCVout;

public class AWindow implements IVLCVout {
    private static final int AWINDOW_REGISTER_ERROR = 0;
    private static final int AWINDOW_REGISTER_FLAGS_HAS_VIDEO_LAYOUT_LISTENER = 2;
    private static final int AWINDOW_REGISTER_FLAGS_SUCCESS = 1;
    private static final int ID_MAX = 2;
    private static final int ID_SUBTITLES = 1;
    private static final int ID_VIDEO = 0;
    private static final int SURFACE_STATE_ATTACHED = 1;
    private static final int SURFACE_STATE_INIT = 0;
    private static final int SURFACE_STATE_READY = 2;
    private static final String TAG = "AWindow";
    private long mCallbackNativeHandle = 0;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ArrayList<IVLCVout.Callback> mIVLCVoutCallbacks = new ArrayList<>();
    private int mMouseAction = -1;
    private int mMouseButton = -1;
    private int mMouseX = -1;
    private int mMouseY = -1;
    private final NativeLock mNativeLock = new NativeLock();
    /* access modifiers changed from: private */
    public IVLCVout.OnNewVideoLayoutListener mOnNewVideoLayoutListener = null;
    private final SurfaceCallback mSurfaceCallback;
    private final SurfaceHelper[] mSurfaceHelpers;
    private SurfaceTextureThread mSurfaceTextureThread = new SurfaceTextureThread();
    private final Surface[] mSurfaces;
    private final AtomicInteger mSurfacesState = new AtomicInteger(0);
    private int mWindowHeight = -1;
    private int mWindowWidth = -1;

    public interface SurfaceCallback {
        void onSurfacesCreated(AWindow aWindow);

        void onSurfacesDestroyed(AWindow aWindow);
    }

    private static native void nativeOnMouseEvent(long j, int i, int i2, int i3, int i4);

    private static native void nativeOnWindowSize(long j, int i, int i2);

    private boolean setBuffersGeometry(Surface surface, int i, int i2, int i3) {
        return false;
    }

    private class SurfaceHelper {
        private final int mId;
        private Surface mSurface;
        /* access modifiers changed from: private */
        public final SurfaceHolder mSurfaceHolder;
        private final SurfaceHolder.Callback mSurfaceHolderCallback;
        private final TextureView.SurfaceTextureListener mSurfaceTextureListener;
        private final SurfaceView mSurfaceView;
        private final TextureView mTextureView;

        private SurfaceHelper(int i, SurfaceView surfaceView) {
            this.mSurfaceHolderCallback = new SurfaceHolder.Callback() {
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                }

                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (surfaceHolder == SurfaceHelper.this.mSurfaceHolder) {
                        SurfaceHelper.this.setSurface(surfaceHolder.getSurface());
                        return;
                    }
                    throw new IllegalStateException("holders are different");
                }

                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    AWindow.this.onSurfaceDestroyed();
                }
            };
            this.mSurfaceTextureListener = createSurfaceTextureListener();
            this.mId = i;
            this.mTextureView = null;
            this.mSurfaceView = surfaceView;
            this.mSurfaceHolder = surfaceView.getHolder();
        }

        private SurfaceHelper(int i, TextureView textureView) {
            this.mSurfaceHolderCallback = new SurfaceHolder.Callback() {
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                }

                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (surfaceHolder == SurfaceHelper.this.mSurfaceHolder) {
                        SurfaceHelper.this.setSurface(surfaceHolder.getSurface());
                        return;
                    }
                    throw new IllegalStateException("holders are different");
                }

                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    AWindow.this.onSurfaceDestroyed();
                }
            };
            this.mSurfaceTextureListener = createSurfaceTextureListener();
            this.mId = i;
            this.mSurfaceView = null;
            this.mSurfaceHolder = null;
            this.mTextureView = textureView;
        }

        private SurfaceHelper(int i, Surface surface, SurfaceHolder surfaceHolder) {
            this.mSurfaceHolderCallback = new SurfaceHolder.Callback() {
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                }

                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (surfaceHolder == SurfaceHelper.this.mSurfaceHolder) {
                        SurfaceHelper.this.setSurface(surfaceHolder.getSurface());
                        return;
                    }
                    throw new IllegalStateException("holders are different");
                }

                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    AWindow.this.onSurfaceDestroyed();
                }
            };
            this.mSurfaceTextureListener = createSurfaceTextureListener();
            this.mId = i;
            this.mSurfaceView = null;
            this.mTextureView = null;
            this.mSurfaceHolder = surfaceHolder;
            this.mSurface = surface;
        }

        /* access modifiers changed from: private */
        public void setSurface(Surface surface) {
            if (surface.isValid() && AWindow.this.getNativeSurface(this.mId) == null) {
                this.mSurface = surface;
                AWindow.this.setNativeSurface(this.mId, surface);
                AWindow.this.onSurfaceCreated();
            }
        }

        private void attachSurfaceView() {
            this.mSurfaceHolder.addCallback(this.mSurfaceHolderCallback);
            setSurface(this.mSurfaceHolder.getSurface());
        }

        private void attachTextureView() {
            this.mTextureView.setSurfaceTextureListener(this.mSurfaceTextureListener);
            SurfaceTexture surfaceTexture = this.mTextureView.getSurfaceTexture();
            if (surfaceTexture != null) {
                this.mSurfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mTextureView.getWidth(), this.mTextureView.getHeight());
            }
        }

        private void attachSurface() {
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            if (surfaceHolder != null) {
                surfaceHolder.addCallback(this.mSurfaceHolderCallback);
            }
            setSurface(this.mSurface);
        }

        public void attach() {
            if (this.mSurfaceView != null) {
                attachSurfaceView();
            } else if (this.mTextureView != null) {
                attachTextureView();
            } else if (this.mSurface != null) {
                attachSurface();
            } else {
                throw new IllegalStateException();
            }
        }

        private void releaseTextureView() {
            TextureView textureView = this.mTextureView;
            if (textureView != null) {
                textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            }
        }

        public void release() {
            this.mSurface = null;
            AWindow.this.setNativeSurface(this.mId, (Surface) null);
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            if (surfaceHolder != null) {
                surfaceHolder.removeCallback(this.mSurfaceHolderCallback);
            }
            releaseTextureView();
        }

        public boolean isReady() {
            return this.mSurfaceView == null || this.mSurface != null;
        }

        public Surface getSurface() {
            return this.mSurface;
        }

        /* access modifiers changed from: package-private */
        public SurfaceHolder getSurfaceHolder() {
            return this.mSurfaceHolder;
        }

        private TextureView.SurfaceTextureListener createSurfaceTextureListener() {
            return new TextureView.SurfaceTextureListener() {
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }

                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    SurfaceHelper.this.setSurface(new Surface(surfaceTexture));
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    AWindow.this.onSurfaceDestroyed();
                    return true;
                }
            };
        }
    }

    public AWindow(SurfaceCallback surfaceCallback) {
        this.mSurfaceCallback = surfaceCallback;
        SurfaceHelper[] surfaceHelperArr = new SurfaceHelper[2];
        this.mSurfaceHelpers = surfaceHelperArr;
        surfaceHelperArr[0] = null;
        surfaceHelperArr[1] = null;
        Surface[] surfaceArr = new Surface[2];
        this.mSurfaces = surfaceArr;
        surfaceArr[0] = null;
        surfaceArr[1] = null;
    }

    private void ensureInitState() throws IllegalStateException {
        if (this.mSurfacesState.get() != 0) {
            throw new IllegalStateException("Can't set view when already attached. Current state: " + this.mSurfacesState.get() + ", mSurfaces[ID_VIDEO]: " + this.mSurfaceHelpers[0] + " / " + this.mSurfaces[0] + ", mSurfaces[ID_SUBTITLES]: " + this.mSurfaceHelpers[1] + " / " + this.mSurfaces[1]);
        }
    }

    private void setView(int i, SurfaceView surfaceView) {
        ensureInitState();
        if (surfaceView != null) {
            SurfaceHelper surfaceHelper = this.mSurfaceHelpers[i];
            if (surfaceHelper != null) {
                surfaceHelper.release();
            }
            this.mSurfaceHelpers[i] = new SurfaceHelper(i, surfaceView);
            return;
        }
        throw new NullPointerException("view is null");
    }

    private void setView(int i, TextureView textureView) {
        ensureInitState();
        if (textureView != null) {
            SurfaceHelper surfaceHelper = this.mSurfaceHelpers[i];
            if (surfaceHelper != null) {
                surfaceHelper.release();
            }
            this.mSurfaceHelpers[i] = new SurfaceHelper(i, textureView);
            return;
        }
        throw new NullPointerException("view is null");
    }

    private void setSurface(int i, Surface surface, SurfaceHolder surfaceHolder) {
        ensureInitState();
        if (surface.isValid() || surfaceHolder != null) {
            SurfaceHelper surfaceHelper = this.mSurfaceHelpers[i];
            if (surfaceHelper != null) {
                surfaceHelper.release();
            }
            this.mSurfaceHelpers[i] = new SurfaceHelper(i, surface, surfaceHolder);
            return;
        }
        throw new IllegalStateException("surface is not attached and holder is null");
    }

    public void setVideoView(SurfaceView surfaceView) {
        setView(0, surfaceView);
    }

    public void setVideoView(TextureView textureView) {
        setView(0, textureView);
    }

    public void setVideoSurface(Surface surface, SurfaceHolder surfaceHolder) {
        setSurface(0, surface, surfaceHolder);
    }

    public void setVideoSurface(SurfaceTexture surfaceTexture) {
        setSurface(0, new Surface(surfaceTexture), (SurfaceHolder) null);
    }

    public void setSubtitlesView(SurfaceView surfaceView) {
        setView(1, surfaceView);
    }

    public void setSubtitlesView(TextureView textureView) {
        setView(1, textureView);
    }

    public void setSubtitlesSurface(Surface surface, SurfaceHolder surfaceHolder) {
        setSurface(1, surface, surfaceHolder);
    }

    public void setSubtitlesSurface(SurfaceTexture surfaceTexture) {
        setSurface(1, new Surface(surfaceTexture), (SurfaceHolder) null);
    }

    public void attachViews(IVLCVout.OnNewVideoLayoutListener onNewVideoLayoutListener) {
        if (this.mSurfacesState.get() == 0) {
            if (this.mSurfaceHelpers[0] != null) {
                this.mSurfacesState.set(1);
                synchronized (this.mNativeLock) {
                    this.mOnNewVideoLayoutListener = onNewVideoLayoutListener;
                    boolean unused = this.mNativeLock.buffersGeometryConfigured = false;
                    boolean unused2 = this.mNativeLock.buffersGeometryAbort = false;
                }
                for (int i = 0; i < 2; i++) {
                    SurfaceHelper surfaceHelper = this.mSurfaceHelpers[i];
                    if (surfaceHelper != null) {
                        surfaceHelper.attach();
                    }
                }
                return;
            }
        }
        throw new IllegalStateException("already attached or video view not configured");
    }

    public void attachViews() {
        attachViews((IVLCVout.OnNewVideoLayoutListener) null);
    }

    public void detachViews() {
        if (this.mSurfacesState.get() != 0) {
            this.mSurfacesState.set(0);
            this.mHandler.removeCallbacksAndMessages((Object) null);
            synchronized (this.mNativeLock) {
                this.mOnNewVideoLayoutListener = null;
                boolean unused = this.mNativeLock.buffersGeometryAbort = true;
                this.mNativeLock.notifyAll();
            }
            for (int i = 0; i < 2; i++) {
                SurfaceHelper surfaceHelper = this.mSurfaceHelpers[i];
                if (surfaceHelper != null) {
                    surfaceHelper.release();
                }
                this.mSurfaceHelpers[i] = null;
            }
            Iterator<IVLCVout.Callback> it = this.mIVLCVoutCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onSurfacesDestroyed(this);
            }
            SurfaceCallback surfaceCallback = this.mSurfaceCallback;
            if (surfaceCallback != null) {
                surfaceCallback.onSurfacesDestroyed(this);
            }
            this.mSurfaceTextureThread.release();
        }
    }

    public boolean areViewsAttached() {
        return this.mSurfacesState.get() != 0;
    }

    /* access modifiers changed from: private */
    public void onSurfaceCreated() {
        if (this.mSurfacesState.get() == 1) {
            SurfaceHelper[] surfaceHelperArr = this.mSurfaceHelpers;
            SurfaceHelper surfaceHelper = surfaceHelperArr[0];
            SurfaceHelper surfaceHelper2 = surfaceHelperArr[1];
            if (surfaceHelper == null) {
                throw new NullPointerException("videoHelper shouldn't be null here");
            } else if (!surfaceHelper.isReady()) {
            } else {
                if (surfaceHelper2 == null || surfaceHelper2.isReady()) {
                    this.mSurfacesState.set(2);
                    Iterator<IVLCVout.Callback> it = this.mIVLCVoutCallbacks.iterator();
                    while (it.hasNext()) {
                        it.next().onSurfacesCreated(this);
                    }
                    SurfaceCallback surfaceCallback = this.mSurfaceCallback;
                    if (surfaceCallback != null) {
                        surfaceCallback.onSurfacesCreated(this);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("invalid state");
        }
    }

    /* access modifiers changed from: private */
    public void onSurfaceDestroyed() {
        detachViews();
    }

    /* access modifiers changed from: package-private */
    public boolean areSurfacesWaiting() {
        return this.mSurfacesState.get() == 1;
    }

    public void sendMouseEvent(int i, int i2, int i3, int i4) {
        synchronized (this.mNativeLock) {
            long j = this.mCallbackNativeHandle;
            if (!(j == 0 || (this.mMouseAction == i && this.mMouseButton == i2 && this.mMouseX == i3 && this.mMouseY == i4))) {
                nativeOnMouseEvent(j, i, i2, i3, i4);
            }
            this.mMouseAction = i;
            this.mMouseButton = i2;
            this.mMouseX = i3;
            this.mMouseY = i4;
        }
    }

    public void setWindowSize(int i, int i2) {
        synchronized (this.mNativeLock) {
            long j = this.mCallbackNativeHandle;
            if (!(j == 0 || (this.mWindowWidth == i && this.mWindowHeight == i2))) {
                nativeOnWindowSize(j, i, i2);
            }
            this.mWindowWidth = i;
            this.mWindowHeight = i2;
        }
    }

    /* access modifiers changed from: private */
    public void setNativeSurface(int i, Surface surface) {
        synchronized (this.mNativeLock) {
            this.mSurfaces[i] = surface;
        }
    }

    /* access modifiers changed from: private */
    public Surface getNativeSurface(int i) {
        Surface surface;
        synchronized (this.mNativeLock) {
            surface = this.mSurfaces[i];
        }
        return surface;
    }

    private static class NativeLock {
        /* access modifiers changed from: private */
        public boolean buffersGeometryAbort;
        /* access modifiers changed from: private */
        public boolean buffersGeometryConfigured;

        private NativeLock() {
            this.buffersGeometryConfigured = false;
            this.buffersGeometryAbort = false;
        }
    }

    public void addCallback(IVLCVout.Callback callback) {
        if (!this.mIVLCVoutCallbacks.contains(callback)) {
            this.mIVLCVoutCallbacks.add(callback);
        }
    }

    public void removeCallback(IVLCVout.Callback callback) {
        this.mIVLCVoutCallbacks.remove(callback);
    }

    private Surface getVideoSurface() {
        return getNativeSurface(0);
    }

    private Surface getSubtitlesSurface() {
        return getNativeSurface(1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        return r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int registerNative(long r10) {
        /*
            r9 = this;
            r0 = 0
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x003c
            org.videolan.libvlc.AWindow$NativeLock r2 = r9.mNativeLock
            monitor-enter(r2)
            long r3 = r9.mCallbackNativeHandle     // Catch:{ all -> 0x0039 }
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 == 0) goto L_0x0012
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            r10 = 0
            return r10
        L_0x0012:
            r9.mCallbackNativeHandle = r10     // Catch:{ all -> 0x0039 }
            int r5 = r9.mMouseAction     // Catch:{ all -> 0x0039 }
            r0 = -1
            if (r5 == r0) goto L_0x0023
            int r6 = r9.mMouseButton     // Catch:{ all -> 0x0039 }
            int r7 = r9.mMouseX     // Catch:{ all -> 0x0039 }
            int r8 = r9.mMouseY     // Catch:{ all -> 0x0039 }
            r3 = r10
            nativeOnMouseEvent(r3, r5, r6, r7, r8)     // Catch:{ all -> 0x0039 }
        L_0x0023:
            int r10 = r9.mWindowWidth     // Catch:{ all -> 0x0039 }
            if (r10 == r0) goto L_0x0030
            int r11 = r9.mWindowHeight     // Catch:{ all -> 0x0039 }
            if (r11 == r0) goto L_0x0030
            long r0 = r9.mCallbackNativeHandle     // Catch:{ all -> 0x0039 }
            nativeOnWindowSize(r0, r10, r11)     // Catch:{ all -> 0x0039 }
        L_0x0030:
            org.videolan.libvlc.interfaces.IVLCVout$OnNewVideoLayoutListener r10 = r9.mOnNewVideoLayoutListener     // Catch:{ all -> 0x0039 }
            if (r10 == 0) goto L_0x0036
            r10 = 3
            goto L_0x0037
        L_0x0036:
            r10 = 1
        L_0x0037:
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            return r10
        L_0x0039:
            r10 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            throw r10
        L_0x003c:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "nativeHandle is null"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.AWindow.registerNative(long):int");
    }

    private void unregisterNative() {
        synchronized (this.mNativeLock) {
            if (this.mCallbackNativeHandle != 0) {
                this.mCallbackNativeHandle = 0;
            } else {
                throw new IllegalArgumentException("unregister called when not registered");
            }
        }
    }

    private void setVideoLayout(int i, int i2, int i3, int i4, int i5, int i6) {
        final int i7 = i;
        final int i8 = i2;
        final int i9 = i3;
        final int i10 = i4;
        final int i11 = i5;
        final int i12 = i6;
        this.mHandler.post(new Runnable() {
            public void run() {
                if (AWindow.this.mOnNewVideoLayoutListener != null) {
                    AWindow.this.mOnNewVideoLayoutListener.onNewVideoLayout(AWindow.this, i7, i8, i9, i10, i11, i12);
                }
            }
        });
    }

    private static class SurfaceTextureThread implements Runnable, SurfaceTexture.OnFrameAvailableListener {
        private boolean mDoRelease;
        private boolean mFrameAvailable;
        private boolean mIsAttached;
        private Looper mLooper;
        private Surface mSurface;
        private SurfaceTexture mSurfaceTexture;
        private Thread mThread;

        private SurfaceTextureThread() {
            this.mSurfaceTexture = null;
            this.mSurface = null;
            this.mFrameAvailable = false;
            this.mLooper = null;
            this.mThread = null;
            this.mIsAttached = false;
            this.mDoRelease = false;
        }

        private synchronized boolean createSurface() {
            if (this.mSurfaceTexture == null) {
                Thread thread = new Thread(this);
                this.mThread = thread;
                thread.start();
                while (this.mSurfaceTexture == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        return false;
                    }
                }
                this.mSurface = new Surface(this.mSurfaceTexture);
            }
            return true;
        }

        /* access modifiers changed from: private */
        public synchronized boolean attachToGLContext(int i) {
            if (!createSurface()) {
                return false;
            }
            this.mSurfaceTexture.attachToGLContext(i);
            this.mFrameAvailable = false;
            this.mIsAttached = true;
            return true;
        }

        public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
            if (surfaceTexture == this.mSurfaceTexture) {
                if (!this.mFrameAvailable) {
                    this.mFrameAvailable = true;
                    notify();
                } else {
                    throw new IllegalStateException("An available frame was not updated");
                }
            }
        }

        public void run() {
            Looper.prepare();
            synchronized (this) {
                this.mLooper = Looper.myLooper();
                SurfaceTexture surfaceTexture = new SurfaceTexture(0);
                this.mSurfaceTexture = surfaceTexture;
                surfaceTexture.detachFromGLContext();
                this.mSurfaceTexture.setOnFrameAvailableListener(this);
                notify();
            }
            Looper.loop();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:4|5|6|7|8) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0013 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void detachFromGLContext() {
            /*
                r3 = this;
                monitor-enter(r3)
                boolean r0 = r3.mDoRelease     // Catch:{ all -> 0x002f }
                r1 = 0
                if (r0 == 0) goto L_0x0026
                android.os.Looper r0 = r3.mLooper     // Catch:{ all -> 0x002f }
                r0.quit()     // Catch:{ all -> 0x002f }
                r0 = 0
                r3.mLooper = r0     // Catch:{ all -> 0x002f }
                java.lang.Thread r2 = r3.mThread     // Catch:{ InterruptedException -> 0x0013 }
                r2.join()     // Catch:{ InterruptedException -> 0x0013 }
            L_0x0013:
                r3.mThread = r0     // Catch:{ all -> 0x002f }
                android.view.Surface r2 = r3.mSurface     // Catch:{ all -> 0x002f }
                r2.release()     // Catch:{ all -> 0x002f }
                r3.mSurface = r0     // Catch:{ all -> 0x002f }
                android.graphics.SurfaceTexture r2 = r3.mSurfaceTexture     // Catch:{ all -> 0x002f }
                r2.release()     // Catch:{ all -> 0x002f }
                r3.mSurfaceTexture = r0     // Catch:{ all -> 0x002f }
                r3.mDoRelease = r1     // Catch:{ all -> 0x002f }
                goto L_0x002b
            L_0x0026:
                android.graphics.SurfaceTexture r0 = r3.mSurfaceTexture     // Catch:{ all -> 0x002f }
                r0.detachFromGLContext()     // Catch:{ all -> 0x002f }
            L_0x002b:
                r3.mIsAttached = r1     // Catch:{ all -> 0x002f }
                monitor-exit(r3)
                return
            L_0x002f:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.AWindow.SurfaceTextureThread.detachFromGLContext():void");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0001, code lost:
            continue;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
        /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:20:0x0001, LOOP_START, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean waitAndUpdateTexImage(float[] r5) {
            /*
                r4 = this;
                monitor-enter(r4)
            L_0x0001:
                boolean r0 = r4.mFrameAvailable     // Catch:{ all -> 0x0020 }
                r1 = 0
                if (r0 != 0) goto L_0x0011
                r2 = 500(0x1f4, double:2.47E-321)
                r4.wait(r2)     // Catch:{ InterruptedException -> 0x0001 }
                boolean r0 = r4.mFrameAvailable     // Catch:{ InterruptedException -> 0x0001 }
                if (r0 != 0) goto L_0x0001
                monitor-exit(r4)     // Catch:{ all -> 0x0020 }
                return r1
            L_0x0011:
                r4.mFrameAvailable = r1     // Catch:{ all -> 0x0020 }
                monitor-exit(r4)     // Catch:{ all -> 0x0020 }
                android.graphics.SurfaceTexture r0 = r4.mSurfaceTexture
                r0.updateTexImage()
                android.graphics.SurfaceTexture r0 = r4.mSurfaceTexture
                r0.getTransformMatrix(r5)
                r5 = 1
                return r5
            L_0x0020:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0020 }
                goto L_0x0024
            L_0x0023:
                throw r5
            L_0x0024:
                goto L_0x0023
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.AWindow.SurfaceTextureThread.waitAndUpdateTexImage(float[]):boolean");
        }

        /* access modifiers changed from: private */
        public synchronized Surface getSurface() {
            if (!createSurface()) {
                return null;
            }
            return this.mSurface;
        }

        /* access modifiers changed from: private */
        public synchronized void release() {
            if (this.mSurfaceTexture != null) {
                if (this.mIsAttached) {
                    this.mDoRelease = true;
                } else {
                    this.mSurface.release();
                    this.mSurface = null;
                    this.mSurfaceTexture.release();
                    this.mSurfaceTexture = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean SurfaceTexture_attachToGLContext(int i) {
        return this.mSurfaceTextureThread.attachToGLContext(i);
    }

    private void SurfaceTexture_detachFromGLContext() {
        this.mSurfaceTextureThread.detachFromGLContext();
    }

    private boolean SurfaceTexture_waitAndUpdateTexImage(float[] fArr) {
        return this.mSurfaceTextureThread.waitAndUpdateTexImage(fArr);
    }

    private Surface SurfaceTexture_getSurface() {
        return this.mSurfaceTextureThread.getSurface();
    }
}
