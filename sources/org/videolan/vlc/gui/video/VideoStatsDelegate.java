package org.videolan.vlc.gui.video;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.liveplotgraph.LineGraph;
import org.videolan.liveplotgraph.PlotView;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PlayerHudBinding;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\bJ \u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0002J\u000e\u0010+\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010,\u001a\u00020\u0006J\b\u0010-\u001a\u00020\u0006H\u0002J\u0006\u0010.\u001a\u00020\u0006J\u0006\u0010/\u001a\u00020\u0006R\u001a\u0010\t\u001a\u00020\nX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!8\u0002X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u000e\u0010#\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoStatsDelegate;", "", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "scrolling", "Lkotlin/Function0;", "", "idle", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "binding", "Lorg/videolan/vlc/databinding/PlayerHudBinding;", "getBinding", "()Lorg/videolan/vlc/databinding/PlayerHudBinding;", "setBinding", "(Lorg/videolan/vlc/databinding/PlayerHudBinding;)V", "constraintSet", "Landroidx/constraintlayout/widget/ConstraintSet;", "constraintSetLarge", "container", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setContainer", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "firstTimecode", "", "getIdle", "()Lkotlin/jvm/functions/Function0;", "lastMediaUri", "Landroid/net/Uri;", "plotHandler", "Landroid/os/Handler;", "runnable", "Ljava/lang/Runnable;", "getScrolling", "started", "", "addStreamGridView", "grid", "Landroid/widget/GridLayout;", "titleString", "", "valueString", "initPlotView", "onConfigurationChanged", "setupLayout", "start", "stop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoStatsDelegate.kt */
public final class VideoStatsDelegate {
    public PlayerHudBinding binding;
    private ConstraintSet constraintSet;
    private ConstraintSet constraintSetLarge;
    public ConstraintLayout container;
    private final long firstTimecode = System.currentTimeMillis();
    private final Function0<Unit> idle;
    private Uri lastMediaUri;
    private final VideoPlayerActivity player;
    private final Handler plotHandler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new VideoStatsDelegate$$ExternalSyntheticLambda0(this);
    private final Function0<Unit> scrolling;
    private boolean started;

    public VideoStatsDelegate(VideoPlayerActivity videoPlayerActivity, Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        Intrinsics.checkNotNullParameter(function0, "scrolling");
        Intrinsics.checkNotNullParameter(function02, "idle");
        this.player = videoPlayerActivity;
        this.scrolling = function0;
        this.idle = function02;
    }

    public final Function0<Unit> getIdle() {
        return this.idle;
    }

    public final Function0<Unit> getScrolling() {
        return this.scrolling;
    }

    public final ConstraintLayout getContainer() {
        ConstraintLayout constraintLayout = this.container;
        if (constraintLayout != null) {
            return constraintLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("container");
        return null;
    }

    public final void setContainer(ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
        this.container = constraintLayout;
    }

    public final PlayerHudBinding getBinding() {
        PlayerHudBinding playerHudBinding = this.binding;
        if (playerHudBinding != null) {
            return playerHudBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(PlayerHudBinding playerHudBinding) {
        Intrinsics.checkNotNullParameter(playerHudBinding, "<set-?>");
        this.binding = playerHudBinding;
    }

    public final void stop() {
        this.started = false;
        this.plotHandler.removeCallbacks(this.runnable);
        getContainer().setVisibility(8);
        getBinding().plotView.clear();
    }

    public final void start() {
        this.started = true;
        this.plotHandler.postDelayed(this.runnable, 300);
        getContainer().setVisibility(0);
    }

    public final void initPlotView(PlayerHudBinding playerHudBinding) {
        Intrinsics.checkNotNullParameter(playerHudBinding, "binding");
        setBinding(playerHudBinding);
        if (this.constraintSet == null) {
            this.constraintSet = new ConstraintSet();
            this.constraintSetLarge = new ConstraintSet();
            ConstraintSet constraintSet2 = this.constraintSet;
            ConstraintSet constraintSet3 = null;
            if (constraintSet2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSet");
                constraintSet2 = null;
            }
            constraintSet2.clone(playerHudBinding.statsScrollviewContent);
            ConstraintSet constraintSet4 = this.constraintSetLarge;
            if (constraintSet4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSetLarge");
                constraintSet4 = null;
            }
            constraintSet4.clone(playerHudBinding.statsScrollviewContent);
            ConstraintSet constraintSet5 = this.constraintSetLarge;
            if (constraintSet5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSetLarge");
                constraintSet5 = null;
            }
            constraintSet5.connect(R.id.stats_graphs, 3, 0, 3);
            ConstraintSet constraintSet6 = this.constraintSetLarge;
            if (constraintSet6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSetLarge");
                constraintSet6 = null;
            }
            constraintSet6.clear(R.id.info_grids, 7);
            ConstraintSet constraintSet7 = this.constraintSetLarge;
            if (constraintSet7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSetLarge");
                constraintSet7 = null;
            }
            constraintSet7.connect(R.id.info_grids, 7, R.id.stats_graphs, 6);
            ConstraintSet constraintSet8 = this.constraintSetLarge;
            if (constraintSet8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSetLarge");
            } else {
                constraintSet3 = constraintSet8;
            }
            constraintSet3.connect(R.id.stats_graphs, 6, R.id.info_grids, 7);
        }
        NestedScrollView nestedScrollView = playerHudBinding.statsScrollview;
        Intrinsics.checkNotNullExpressionValue(nestedScrollView, "statsScrollview");
        nestedScrollView.setOnTouchListener(new VideoStatsDelegate$initPlotView$$inlined$scrollState$1(this, this));
        PlotView plotView = playerHudBinding.plotView;
        int ordinal = StatIndex.DEMUX_BITRATE.ordinal();
        String string = this.player.getString(R.string.demux_bitrate);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        plotView.addLine(new LineGraph(ordinal, string, ContextCompat.getColor(this.player, R.color.material_blue), (HashMap) null, 8, (DefaultConstructorMarker) null));
        PlotView plotView2 = playerHudBinding.plotView;
        int ordinal2 = StatIndex.INPUT_BITRATE.ordinal();
        String string2 = this.player.getString(R.string.input_bitrate);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        plotView2.addLine(new LineGraph(ordinal2, string2, ContextCompat.getColor(this.player, R.color.material_pink), (HashMap) null, 8, (DefaultConstructorMarker) null));
        setupLayout();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r1 = r1.getMediaplayer();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void runnable$lambda$6(org.videolan.vlc.gui.video.VideoStatsDelegate r16) {
        /*
            r0 = r16
            java.lang.String r1 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r0.player
            org.videolan.vlc.PlaybackService r1 = r1.getService()
            if (r1 == 0) goto L_0x001a
            org.videolan.libvlc.MediaPlayer r1 = r1.getMediaplayer()
            if (r1 == 0) goto L_0x001a
            org.videolan.libvlc.interfaces.IMedia r1 = r1.getMedia()
            goto L_0x001b
        L_0x001a:
            r1 = 0
        L_0x001b:
            boolean r3 = r1 instanceof org.videolan.libvlc.Media
            if (r3 == 0) goto L_0x0022
            org.videolan.libvlc.Media r1 = (org.videolan.libvlc.Media) r1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r1 != 0) goto L_0x0026
            return
        L_0x0026:
            org.videolan.libvlc.interfaces.IMedia$Stats r3 = r1.getStats()
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r0.firstTimecode
            long r4 = r4 - r6
            r6 = 1024(0x400, float:1.435E-42)
            r7 = 8
            if (r3 == 0) goto L_0x005b
            float r8 = r3.demuxBitrate
            org.videolan.vlc.databinding.PlayerHudBinding r9 = r16.getBinding()
            org.videolan.liveplotgraph.PlotView r9 = r9.plotView
            org.videolan.vlc.gui.video.StatIndex r10 = org.videolan.vlc.gui.video.StatIndex.DEMUX_BITRATE
            int r10 = r10.ordinal()
            kotlin.Pair r11 = new kotlin.Pair
            java.lang.Long r12 = java.lang.Long.valueOf(r4)
            float r13 = (float) r7
            float r8 = r8 * r13
            float r13 = (float) r6
            float r8 = r8 * r13
            java.lang.Float r8 = java.lang.Float.valueOf(r8)
            r11.<init>(r12, r8)
            r9.addData(r10, r11)
        L_0x005b:
            if (r3 == 0) goto L_0x0081
            float r3 = r3.inputBitrate
            org.videolan.vlc.databinding.PlayerHudBinding r8 = r16.getBinding()
            org.videolan.liveplotgraph.PlotView r8 = r8.plotView
            org.videolan.vlc.gui.video.StatIndex r9 = org.videolan.vlc.gui.video.StatIndex.INPUT_BITRATE
            int r9 = r9.ordinal()
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            float r5 = (float) r7
            float r3 = r3 * r5
            float r5 = (float) r6
            float r3 = r3 * r5
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            r10.<init>(r4, r3)
            r8.addData(r9, r10)
        L_0x0081:
            android.net.Uri r3 = r0.lastMediaUri
            android.net.Uri r4 = r1.getUri()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 != 0) goto L_0x0275
            android.net.Uri r3 = r1.getUri()
            r0.lastMediaUri = r3
            org.videolan.vlc.databinding.PlayerHudBinding r3 = r16.getBinding()
            android.widget.LinearLayout r3 = r3.infoGrids
            r3.removeAllViews()
            org.videolan.libvlc.interfaces.IMedia r1 = (org.videolan.libvlc.interfaces.IMedia) r1
            java.util.List r1 = org.videolan.vlc.VersionDependantKt.getAllTracks(r1)
            java.util.Iterator r1 = r1.iterator()
        L_0x00a6:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0275
            java.lang.Object r3 = r1.next()
            org.videolan.libvlc.interfaces.IMedia$Track r3 = (org.videolan.libvlc.interfaces.IMedia.Track) r3
            android.widget.GridLayout r4 = new android.widget.GridLayout
            org.videolan.vlc.gui.video.VideoPlayerActivity r5 = r0.player
            android.content.Context r5 = (android.content.Context) r5
            r4.<init>(r5)
            r5 = 2
            r4.setColumnCount(r5)
            int r6 = r3.bitrate
            r8 = 0
            r9 = 1
            java.lang.String r10 = "getString(...)"
            if (r6 <= 0) goto L_0x00eb
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r0.player
            int r11 = org.videolan.vlc.R.string.bitrate
            java.lang.String r6 = r6.getString(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            org.videolan.vlc.gui.video.VideoPlayerActivity r11 = r0.player
            int r12 = org.videolan.vlc.R.string.bitrate_value
            int r13 = r3.bitrate
            long r13 = (long) r13
            java.lang.String r13 = org.videolan.tools.Strings.readableSize(r13)
            java.lang.Object[] r14 = new java.lang.Object[r9]
            r14[r8] = r13
            java.lang.String r11 = r11.getString(r12, r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r10)
            r0.addStreamGridView(r4, r6, r11)
        L_0x00eb:
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r0.player
            int r11 = org.videolan.vlc.R.string.codec
            java.lang.String r6 = r6.getString(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            java.lang.String r11 = r3.codec
            java.lang.String r12 = "codec"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)
            r0.addStreamGridView(r4, r6, r11)
            java.lang.String r6 = r3.language
            if (r6 == 0) goto L_0x0129
            java.lang.String r6 = r3.language
            java.lang.String r11 = "und"
            boolean r6 = kotlin.text.StringsKt.equals(r6, r11, r9)
            if (r6 != 0) goto L_0x0129
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r0.player
            int r11 = org.videolan.vlc.R.string.language
            java.lang.String r6 = r6.getString(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            org.videolan.vlc.util.LocaleUtil r11 = org.videolan.vlc.util.LocaleUtil.INSTANCE
            java.lang.String r12 = r3.language
            java.lang.String r13 = "language"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)
            java.lang.String r11 = r11.getLocaleName(r12)
            r0.addStreamGridView(r4, r6, r11)
        L_0x0129:
            int r6 = r3.type
            if (r6 == 0) goto L_0x01a7
            if (r6 == r9) goto L_0x0131
            goto L_0x01ec
        L_0x0131:
            boolean r6 = r3 instanceof org.videolan.libvlc.interfaces.IMedia.VideoTrack
            if (r6 == 0) goto L_0x0139
            r6 = r3
            org.videolan.libvlc.interfaces.IMedia$VideoTrack r6 = (org.videolan.libvlc.interfaces.IMedia.VideoTrack) r6
            goto L_0x013a
        L_0x0139:
            r6 = 0
        L_0x013a:
            if (r6 == 0) goto L_0x01ec
            r6 = r3
            org.videolan.libvlc.interfaces.IMedia$VideoTrack r6 = (org.videolan.libvlc.interfaces.IMedia.VideoTrack) r6
            int r11 = r6.frameRateNum
            double r11 = (double) r11
            int r13 = r6.frameRateDen
            double r13 = (double) r13
            java.lang.Double.isNaN(r11)
            java.lang.Double.isNaN(r13)
            double r11 = r11 / r13
            int r13 = r6.width
            if (r13 == 0) goto L_0x017f
            int r13 = r6.height
            if (r13 == 0) goto L_0x017f
            org.videolan.vlc.gui.video.VideoPlayerActivity r13 = r0.player
            int r14 = org.videolan.vlc.R.string.resolution
            java.lang.String r13 = r13.getString(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r10)
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r0.player
            int r15 = org.videolan.vlc.R.string.resolution_value
            int r7 = r6.width
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            int r6 = r6.height
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r2 = new java.lang.Object[r5]
            r2[r8] = r7
            r2[r9] = r6
            java.lang.String r2 = r14.getString(r15, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            r0.addStreamGridView(r4, r13, r2)
        L_0x017f:
            boolean r2 = java.lang.Double.isNaN(r11)
            if (r2 != 0) goto L_0x01ec
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r0.player
            int r6 = org.videolan.vlc.R.string.framerate
            java.lang.String r2 = r2.getString(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r0.player
            int r7 = org.videolan.vlc.R.string.framerate_value
            java.lang.Double r11 = java.lang.Double.valueOf(r11)
            java.lang.Object[] r12 = new java.lang.Object[r9]
            r12[r8] = r11
            java.lang.String r6 = r6.getString(r7, r12)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            r0.addStreamGridView(r4, r2, r6)
            goto L_0x01ec
        L_0x01a7:
            boolean r2 = r3 instanceof org.videolan.libvlc.interfaces.IMedia.AudioTrack
            if (r2 == 0) goto L_0x01af
            r2 = r3
            org.videolan.libvlc.interfaces.IMedia$AudioTrack r2 = (org.videolan.libvlc.interfaces.IMedia.AudioTrack) r2
            goto L_0x01b0
        L_0x01af:
            r2 = 0
        L_0x01b0:
            if (r2 == 0) goto L_0x01ec
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r0.player
            int r6 = org.videolan.vlc.R.string.channels
            java.lang.String r2 = r2.getString(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            r6 = r3
            org.videolan.libvlc.interfaces.IMedia$AudioTrack r6 = (org.videolan.libvlc.interfaces.IMedia.AudioTrack) r6
            int r7 = r6.channels
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r0.addStreamGridView(r4, r2, r7)
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r0.player
            int r7 = org.videolan.vlc.R.string.track_samplerate
            java.lang.String r2 = r2.getString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            org.videolan.vlc.gui.video.VideoPlayerActivity r7 = r0.player
            int r11 = org.videolan.vlc.R.string.track_samplerate_value
            int r6 = r6.rate
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r12 = new java.lang.Object[r9]
            r12[r8] = r6
            java.lang.String r6 = r7.getString(r11, r12)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            r0.addStreamGridView(r4, r2, r6)
        L_0x01ec:
            android.widget.TextView r2 = new android.widget.TextView
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r0.player
            android.content.Context r6 = (android.content.Context) r6
            int r7 = org.videolan.vlc.R.style.TextAppearance_MaterialComponents_Headline2
            r8 = 0
            r2.<init>(r6, r8, r7)
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r0.player
            android.content.Context r6 = (android.content.Context) r6
            int r7 = org.videolan.vlc.R.color.orange500
            int r6 = androidx.core.content.ContextCompat.getColor(r6, r7)
            r2.setTextColor(r6)
            int r3 = r3.type
            if (r3 == 0) goto L_0x022e
            if (r3 == r9) goto L_0x0223
            if (r3 == r5) goto L_0x0218
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r0.player
            int r5 = org.videolan.vlc.R.string.unknown
            java.lang.String r3 = r3.getString(r5)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x0238
        L_0x0218:
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r0.player
            int r5 = org.videolan.vlc.R.string.text
            java.lang.String r3 = r3.getString(r5)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x0238
        L_0x0223:
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r0.player
            int r5 = org.videolan.vlc.R.string.video
            java.lang.String r3 = r3.getString(r5)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x0238
        L_0x022e:
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r0.player
            int r5 = org.videolan.vlc.R.string.audio
            java.lang.String r3 = r3.getString(r5)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
        L_0x0238:
            r2.setText(r3)
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r5 = -2
            r3.<init>(r5, r5)
            r5 = 4
            int r5 = org.videolan.tools.KotlinExtensionsKt.getDp(r5)
            r3.bottomMargin = r5
            r5 = 8
            int r6 = org.videolan.tools.KotlinExtensionsKt.getDp(r5)
            r3.topMargin = r6
            android.view.ViewGroup$LayoutParams r3 = (android.view.ViewGroup.LayoutParams) r3
            r2.setLayoutParams(r3)
            int r3 = r4.getChildCount()
            if (r3 == 0) goto L_0x0271
            org.videolan.vlc.databinding.PlayerHudBinding r3 = r16.getBinding()
            android.widget.LinearLayout r3 = r3.infoGrids
            android.view.View r2 = (android.view.View) r2
            r3.addView(r2)
            org.videolan.vlc.databinding.PlayerHudBinding r2 = r16.getBinding()
            android.widget.LinearLayout r2 = r2.infoGrids
            android.view.View r4 = (android.view.View) r4
            r2.addView(r4)
        L_0x0271:
            r7 = 8
            goto L_0x00a6
        L_0x0275:
            boolean r1 = r0.started
            if (r1 == 0) goto L_0x027c
            r16.start()
        L_0x027c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoStatsDelegate.runnable$lambda$6(org.videolan.vlc.gui.video.VideoStatsDelegate):void");
    }

    private final void addStreamGridView(GridLayout gridLayout, String str, String str2) {
        TextView textView = new TextView(this.player, (AttributeSet) null, R.style.TextAppearance_MaterialComponents_Subtitle1);
        textView.setText(str);
        gridLayout.addView(textView);
        TextView textView2 = new TextView(this.player);
        textView2.setText(str2);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.leftMargin = KotlinExtensionsKt.getDp(4);
        textView2.setLayoutParams(layoutParams);
        gridLayout.addView(textView2);
    }

    public final void onConfigurationChanged() {
        setupLayout();
    }

    private final void setupLayout() {
        if (this.constraintSetLarge != null) {
            ConstraintSet constraintSet2 = null;
            if (UiTools.INSTANCE.isTablet(this.player)) {
                ConstraintSet constraintSet3 = this.constraintSetLarge;
                if (constraintSet3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("constraintSetLarge");
                } else {
                    constraintSet2 = constraintSet3;
                }
                constraintSet2.applyTo(getBinding().statsScrollviewContent);
                return;
            }
            ConstraintSet constraintSet4 = this.constraintSet;
            if (constraintSet4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constraintSet");
            } else {
                constraintSet2 = constraintSet4;
            }
            constraintSet2.applyTo(getBinding().statsScrollviewContent);
        }
    }
}
