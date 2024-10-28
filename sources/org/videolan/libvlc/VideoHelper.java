package org.videolan.libvlc;

import android.os.Handler;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import org.videolan.R;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.libvlc.util.DisplayManager;
import org.videolan.libvlc.util.VLCVideoLayout;

class VideoHelper implements IVLCVout.OnNewVideoLayoutListener {
    private static final String TAG = "LibVLC/VideoHelper";
    private MediaPlayer.ScaleType mCurrentScaleType = MediaPlayer.ScaleType.SURFACE_BEST_FIT;
    private DisplayManager mDisplayManager;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    private MediaPlayer mMediaPlayer;
    /* access modifiers changed from: private */
    public View.OnLayoutChangeListener mOnLayoutChangeListener = null;
    private SurfaceView mSubtitlesSurface = null;
    private int mVideoHeight = 0;
    private int mVideoSarDen = 0;
    private int mVideoSarNum = 0;
    private SurfaceView mVideoSurface = null;
    /* access modifiers changed from: private */
    public FrameLayout mVideoSurfaceFrame;
    private TextureView mVideoTexture = null;
    private int mVideoVisibleHeight = 0;
    private int mVideoVisibleWidth = 0;
    private int mVideoWidth = 0;

    VideoHelper(MediaPlayer mediaPlayer, VLCVideoLayout vLCVideoLayout, DisplayManager displayManager, boolean z, boolean z2) {
        init(mediaPlayer, vLCVideoLayout, displayManager, z, !z2);
    }

    private void init(MediaPlayer mediaPlayer, VLCVideoLayout vLCVideoLayout, DisplayManager displayManager, boolean z, boolean z2) {
        this.mMediaPlayer = mediaPlayer;
        this.mDisplayManager = displayManager;
        if (displayManager == null || displayManager.isPrimary()) {
            FrameLayout frameLayout = (FrameLayout) vLCVideoLayout.findViewById(R.id.player_surface_frame);
            this.mVideoSurfaceFrame = frameLayout;
            if (z2) {
                ViewStub viewStub = (ViewStub) frameLayout.findViewById(R.id.surface_stub);
                this.mVideoSurface = (SurfaceView) (viewStub != null ? viewStub.inflate() : this.mVideoSurfaceFrame.findViewById(R.id.surface_video));
                if (z) {
                    ViewStub viewStub2 = (ViewStub) this.mVideoSurfaceFrame.findViewById(R.id.subtitles_surface_stub);
                    SurfaceView surfaceView = (SurfaceView) (viewStub2 != null ? viewStub2.inflate() : this.mVideoSurfaceFrame.findViewById(R.id.surface_subtitles));
                    this.mSubtitlesSurface = surfaceView;
                    surfaceView.setZOrderMediaOverlay(true);
                    this.mSubtitlesSurface.getHolder().setFormat(-3);
                    return;
                }
                return;
            }
            ViewStub viewStub3 = (ViewStub) frameLayout.findViewById(R.id.texture_stub);
            this.mVideoTexture = (TextureView) (viewStub3 != null ? viewStub3.inflate() : this.mVideoSurfaceFrame.findViewById(R.id.texture_video));
        } else if (this.mDisplayManager.getPresentation() != null) {
            this.mVideoSurfaceFrame = this.mDisplayManager.getPresentation().getSurfaceFrame();
            this.mVideoSurface = this.mDisplayManager.getPresentation().getSurfaceView();
            this.mSubtitlesSurface = this.mDisplayManager.getPresentation().getSubtitlesSurfaceView();
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        if (this.mMediaPlayer.getVLCVout().areViewsAttached()) {
            detachViews();
        }
        this.mMediaPlayer = null;
        this.mVideoSurfaceFrame = null;
        this.mHandler.removeCallbacks((Runnable) null);
        this.mVideoSurface = null;
        this.mSubtitlesSurface = null;
        this.mVideoTexture = null;
    }

    /* access modifiers changed from: package-private */
    public void attachViews() {
        if (this.mVideoSurface != null || this.mVideoTexture != null) {
            IVLCVout vLCVout = this.mMediaPlayer.getVLCVout();
            SurfaceView surfaceView = this.mVideoSurface;
            if (surfaceView != null) {
                vLCVout.setVideoView(surfaceView);
                SurfaceView surfaceView2 = this.mSubtitlesSurface;
                if (surfaceView2 != null) {
                    vLCVout.setSubtitlesView(surfaceView2);
                }
            } else {
                TextureView textureView = this.mVideoTexture;
                if (textureView != null) {
                    vLCVout.setVideoView(textureView);
                } else {
                    return;
                }
            }
            vLCVout.attachViews(this);
            if (this.mOnLayoutChangeListener == null) {
                this.mOnLayoutChangeListener = new View.OnLayoutChangeListener() {
                    private final Runnable runnable = new Runnable() {
                        public void run() {
                            if (VideoHelper.this.mVideoSurfaceFrame != null && VideoHelper.this.mOnLayoutChangeListener != null) {
                                VideoHelper.this.updateVideoSurfaces();
                            }
                        }
                    };

                    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                        if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
                            VideoHelper.this.mHandler.removeCallbacks(this.runnable);
                            VideoHelper.this.mHandler.post(this.runnable);
                        }
                    }
                };
            }
            this.mVideoSurfaceFrame.addOnLayoutChangeListener(this.mOnLayoutChangeListener);
            this.mMediaPlayer.setVideoTrackEnabled(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void detachViews() {
        FrameLayout frameLayout;
        View.OnLayoutChangeListener onLayoutChangeListener = this.mOnLayoutChangeListener;
        if (!(onLayoutChangeListener == null || (frameLayout = this.mVideoSurfaceFrame) == null)) {
            frameLayout.removeOnLayoutChangeListener(onLayoutChangeListener);
            this.mOnLayoutChangeListener = null;
        }
        this.mMediaPlayer.setVideoTrackEnabled(false);
        this.mMediaPlayer.getVLCVout().detachViews();
    }

    private void changeMediaPlayerLayout(int i, int i2) {
        String str;
        if (!this.mMediaPlayer.isReleased()) {
            switch (AnonymousClass2.$SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[this.mCurrentScaleType.ordinal()]) {
                case 1:
                    this.mMediaPlayer.setAspectRatio((String) null);
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 2:
                case 3:
                    IMedia.VideoTrack currentVideoTrack = this.mMediaPlayer.getCurrentVideoTrack();
                    if (currentVideoTrack != null) {
                        boolean z = currentVideoTrack.orientation == 5 || currentVideoTrack.orientation == 6;
                        if (this.mCurrentScaleType == MediaPlayer.ScaleType.SURFACE_FIT_SCREEN) {
                            int i3 = currentVideoTrack.width;
                            int i4 = currentVideoTrack.height;
                            if (z) {
                                int i5 = i4;
                                i4 = i3;
                                i3 = i5;
                            }
                            if (currentVideoTrack.sarNum != currentVideoTrack.sarDen) {
                                i3 = (i3 * currentVideoTrack.sarNum) / currentVideoTrack.sarDen;
                            }
                            float f = (float) i3;
                            float f2 = (float) i4;
                            float f3 = (float) i;
                            float f4 = (float) i2;
                            this.mMediaPlayer.setScale(f3 / f4 >= f / f2 ? f3 / f : f4 / f2);
                            this.mMediaPlayer.setAspectRatio((String) null);
                            return;
                        }
                        this.mMediaPlayer.setScale(0.0f);
                        MediaPlayer mediaPlayer = this.mMediaPlayer;
                        if (!z) {
                            str = "" + i + ":" + i2;
                        } else {
                            str = "" + i2 + ":" + i;
                        }
                        mediaPlayer.setAspectRatio(str);
                        return;
                    }
                    return;
                case 4:
                    this.mMediaPlayer.setAspectRatio("16:9");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 5:
                    this.mMediaPlayer.setAspectRatio("16:10");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 6:
                    this.mMediaPlayer.setAspectRatio("2.21:1");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 7:
                    this.mMediaPlayer.setAspectRatio("2.35:1");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 8:
                    this.mMediaPlayer.setAspectRatio("2.39:1");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 9:
                    this.mMediaPlayer.setAspectRatio("5:4");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 10:
                    this.mMediaPlayer.setAspectRatio("4:3");
                    this.mMediaPlayer.setScale(0.0f);
                    return;
                case 11:
                    this.mMediaPlayer.setAspectRatio((String) null);
                    this.mMediaPlayer.setScale(1.0f);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: org.videolan.libvlc.VideoHelper$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.videolan.libvlc.MediaPlayer$ScaleType[] r0 = org.videolan.libvlc.MediaPlayer.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType = r0
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_BEST_FIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_FIT_SCREEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_FILL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_16_9     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_16_10     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_221_1     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_235_1     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_239_1     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_5_4     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_4_3     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_ORIGINAL     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.VideoHelper.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0167, code lost:
        if (r12 >= r1) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x016c, code lost:
        if (r12 < r1) goto L_0x016e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateVideoSurfaces() {
        /*
            r18 = this;
            r0 = r18
            org.videolan.libvlc.MediaPlayer r1 = r0.mMediaPlayer
            if (r1 == 0) goto L_0x01dc
            boolean r1 = r1.isReleased()
            if (r1 != 0) goto L_0x01dc
            org.videolan.libvlc.MediaPlayer r1 = r0.mMediaPlayer
            org.videolan.libvlc.interfaces.IVLCVout r1 = r1.getVLCVout()
            boolean r1 = r1.areViewsAttached()
            if (r1 != 0) goto L_0x001a
            goto L_0x01dc
        L_0x001a:
            org.videolan.libvlc.util.DisplayManager r1 = r0.mDisplayManager
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0029
            boolean r1 = r1.isPrimary()
            if (r1 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r1 = 0
            goto L_0x002a
        L_0x0029:
            r1 = 1
        L_0x002a:
            r4 = 0
            if (r1 != 0) goto L_0x002f
            r5 = r4
            goto L_0x0039
        L_0x002f:
            android.widget.FrameLayout r5 = r0.mVideoSurfaceFrame
            android.content.Context r5 = r5.getContext()
            android.app.Activity r5 = org.videolan.libvlc.util.AndroidUtil.resolveActivity(r5)
        L_0x0039:
            if (r5 == 0) goto L_0x0048
            android.widget.FrameLayout r6 = r0.mVideoSurfaceFrame
            int r6 = r6.getWidth()
            android.widget.FrameLayout r7 = r0.mVideoSurfaceFrame
            int r7 = r7.getHeight()
            goto L_0x0082
        L_0x0048:
            org.videolan.libvlc.util.DisplayManager r6 = r0.mDisplayManager
            if (r6 == 0) goto L_0x01dc
            org.videolan.libvlc.util.DisplayManager$SecondaryDisplay r6 = r6.getPresentation()
            if (r6 == 0) goto L_0x01dc
            org.videolan.libvlc.util.DisplayManager r6 = r0.mDisplayManager
            org.videolan.libvlc.util.DisplayManager$SecondaryDisplay r6 = r6.getPresentation()
            android.view.Window r6 = r6.getWindow()
            if (r6 == 0) goto L_0x01dc
            org.videolan.libvlc.util.DisplayManager r6 = r0.mDisplayManager
            org.videolan.libvlc.util.DisplayManager$SecondaryDisplay r6 = r6.getPresentation()
            android.view.Window r6 = r6.getWindow()
            android.view.View r6 = r6.getDecorView()
            int r6 = r6.getWidth()
            org.videolan.libvlc.util.DisplayManager r7 = r0.mDisplayManager
            org.videolan.libvlc.util.DisplayManager$SecondaryDisplay r7 = r7.getPresentation()
            android.view.Window r7 = r7.getWindow()
            android.view.View r7 = r7.getDecorView()
            int r7 = r7.getHeight()
        L_0x0082:
            int r8 = r6 * r7
            if (r8 != 0) goto L_0x008e
            java.lang.String r1 = "LibVLC/VideoHelper"
            java.lang.String r2 = "Invalid surface size"
            android.util.Log.e(r1, r2)
            return
        L_0x008e:
            org.videolan.libvlc.MediaPlayer r8 = r0.mMediaPlayer
            org.videolan.libvlc.interfaces.IVLCVout r8 = r8.getVLCVout()
            r8.setWindowSize(r6, r7)
            android.view.SurfaceView r8 = r0.mVideoSurface
            if (r8 != 0) goto L_0x009d
            android.view.TextureView r8 = r0.mVideoTexture
        L_0x009d:
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            int r10 = r0.mVideoWidth
            int r11 = r0.mVideoHeight
            int r10 = r10 * r11
            r11 = -1
            if (r10 == 0) goto L_0x01b4
            boolean r10 = org.videolan.libvlc.util.AndroidUtil.isNougatOrLater
            if (r10 == 0) goto L_0x00b8
            if (r5 == 0) goto L_0x00b8
            boolean r5 = r5.isInPictureInPictureMode()
            if (r5 == 0) goto L_0x00b8
            goto L_0x01b4
        L_0x00b8:
            int r5 = r9.width
            int r10 = r9.height
            if (r5 != r10) goto L_0x00cd
            int r5 = r9.width
            if (r5 != r11) goto L_0x00cd
            org.videolan.libvlc.MediaPlayer r5 = r0.mMediaPlayer
            r5.setAspectRatio(r4)
            org.videolan.libvlc.MediaPlayer r4 = r0.mMediaPlayer
            r5 = 0
            r4.setScale(r5)
        L_0x00cd:
            double r4 = (double) r6
            double r10 = (double) r7
            android.widget.FrameLayout r12 = r0.mVideoSurfaceFrame
            android.content.res.Resources r12 = r12.getResources()
            android.content.res.Configuration r12 = r12.getConfiguration()
            int r12 = r12.orientation
            if (r12 != r3) goto L_0x00df
            r12 = 1
            goto L_0x00e0
        L_0x00df:
            r12 = 0
        L_0x00e0:
            org.videolan.libvlc.MediaPlayer r13 = r0.mMediaPlayer
            java.lang.Boolean r13 = r13.useOrientationFromBounds()
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00f1
            if (r7 <= r6) goto L_0x00f0
            r12 = 1
            goto L_0x00f1
        L_0x00f0:
            r12 = 0
        L_0x00f1:
            if (r1 == 0) goto L_0x00f6
            if (r12 == 0) goto L_0x00f6
            r2 = 1
        L_0x00f6:
            if (r6 <= r7) goto L_0x00fa
            if (r2 != 0) goto L_0x00fe
        L_0x00fa:
            if (r6 >= r7) goto L_0x0103
            if (r2 != 0) goto L_0x0103
        L_0x00fe:
            r16 = r4
            r4 = r10
            r10 = r16
        L_0x0103:
            int r1 = r0.mVideoSarDen
            int r2 = r0.mVideoSarNum
            if (r1 != r2) goto L_0x0118
            int r1 = r0.mVideoVisibleWidth
            double r6 = (double) r1
            double r1 = (double) r1
            int r12 = r0.mVideoVisibleHeight
            double r12 = (double) r12
            java.lang.Double.isNaN(r1)
            java.lang.Double.isNaN(r12)
            double r1 = r1 / r12
            goto L_0x0131
        L_0x0118:
            int r6 = r0.mVideoVisibleWidth
            double r6 = (double) r6
            double r12 = (double) r2
            java.lang.Double.isNaN(r6)
            java.lang.Double.isNaN(r12)
            double r6 = r6 * r12
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r6 = r6 / r1
            int r1 = r0.mVideoVisibleHeight
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r1 = r6 / r1
        L_0x0131:
            double r12 = r4 / r10
            int[] r14 = org.videolan.libvlc.VideoHelper.AnonymousClass2.$SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType
            org.videolan.libvlc.MediaPlayer$ScaleType r15 = r0.mCurrentScaleType
            int r15 = r15.ordinal()
            r14 = r14[r15]
            if (r14 == r3) goto L_0x016a
            r3 = 2
            if (r14 == r3) goto L_0x0165
            r1 = 3
            if (r14 == r1) goto L_0x0173
            r1 = 11
            if (r14 == r1) goto L_0x0160
            org.videolan.libvlc.MediaPlayer$ScaleType r1 = r0.mCurrentScaleType
            java.lang.Float r1 = r1.getRatio()
            float r1 = r1.floatValue()
            double r1 = (double) r1
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x015c
            java.lang.Double.isNaN(r1)
            goto L_0x016e
        L_0x015c:
            java.lang.Double.isNaN(r1)
            goto L_0x0171
        L_0x0160:
            int r1 = r0.mVideoVisibleHeight
            double r10 = (double) r1
            r4 = r6
            goto L_0x0173
        L_0x0165:
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 < 0) goto L_0x0171
            goto L_0x016e
        L_0x016a:
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x0171
        L_0x016e:
            double r10 = r4 / r1
            goto L_0x0173
        L_0x0171:
            double r4 = r10 * r1
        L_0x0173:
            int r1 = r0.mVideoWidth
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r4 = r4 * r1
            int r1 = r0.mVideoVisibleWidth
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r4 = r4 / r1
            double r1 = java.lang.Math.ceil(r4)
            int r1 = (int) r1
            r9.width = r1
            int r1 = r0.mVideoHeight
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r10 = r10 * r1
            int r1 = r0.mVideoVisibleHeight
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r10 = r10 / r1
            double r1 = java.lang.Math.ceil(r10)
            int r1 = (int) r1
            r9.height = r1
            r8.setLayoutParams(r9)
            android.view.SurfaceView r1 = r0.mSubtitlesSurface
            if (r1 == 0) goto L_0x01a9
            r1.setLayoutParams(r9)
        L_0x01a9:
            r8.invalidate()
            android.view.SurfaceView r1 = r0.mSubtitlesSurface
            if (r1 == 0) goto L_0x01b3
            r1.invalidate()
        L_0x01b3:
            return
        L_0x01b4:
            r9.width = r11
            r9.height = r11
            r8.setLayoutParams(r9)
            android.view.SurfaceView r1 = r0.mSubtitlesSurface
            if (r1 == 0) goto L_0x01c2
            r1.setLayoutParams(r9)
        L_0x01c2:
            android.widget.FrameLayout r1 = r0.mVideoSurfaceFrame
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            r1.width = r11
            r1.height = r11
            android.widget.FrameLayout r2 = r0.mVideoSurfaceFrame
            r2.setLayoutParams(r1)
            int r1 = r0.mVideoWidth
            int r2 = r0.mVideoHeight
            int r1 = r1 * r2
            if (r1 != 0) goto L_0x01dc
            r0.changeMediaPlayerLayout(r6, r7)
        L_0x01dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.VideoHelper.updateVideoSurfaces():void");
    }

    public void onNewVideoLayout(IVLCVout iVLCVout, int i, int i2, int i3, int i4, int i5, int i6) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        this.mVideoVisibleWidth = i3;
        this.mVideoVisibleHeight = i4;
        this.mVideoSarNum = i5;
        this.mVideoSarDen = i6;
        updateVideoSurfaces();
    }

    /* access modifiers changed from: package-private */
    public void setVideoScale(MediaPlayer.ScaleType scaleType) {
        this.mCurrentScaleType = scaleType;
        updateVideoSurfaces();
    }

    /* access modifiers changed from: package-private */
    public MediaPlayer.ScaleType getVideoScale() {
        return this.mCurrentScaleType;
    }
}
