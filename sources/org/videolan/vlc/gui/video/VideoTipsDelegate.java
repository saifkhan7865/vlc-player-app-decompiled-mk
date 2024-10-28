package org.videolan.vlc.gui.video;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.TipsUtils;
import org.videolan.vlc.gui.view.PlayerProgress;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u00106\u001a\u000207H\u0002J\u0006\u00108\u001a\u000207J2\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00172\b\b\u0002\u0010<\u001a\u00020=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0017H\u0002J\u0013\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00170AH\u0002¢\u0006\u0002\u0010BJ\u0006\u0010C\u001a\u000207J\u0006\u0010D\u001a\u000207J\u0012\u0010E\u001a\u0002072\b\u0010F\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u0002002\u0006\u0010J\u001a\u00020\u001bH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020$X.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020$X.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000200X.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoTipsDelegate;", "Landroid/view/View$OnClickListener;", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;)V", "currentAnimations", "Ljava/util/ArrayList;", "Landroid/animation/Animator;", "Lkotlin/collections/ArrayList;", "currentControl", "", "getCurrentControl", "()Ljava/lang/Integer;", "setCurrentControl", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "currentTip", "Lorg/videolan/vlc/gui/video/VideoPlayerTipsStep;", "getCurrentTip", "()Lorg/videolan/vlc/gui/video/VideoPlayerTipsStep;", "setCurrentTip", "(Lorg/videolan/vlc/gui/video/VideoPlayerTipsStep;)V", "doubleTapCenter", "Landroid/view/View;", "doubleTapLeft", "doubleTapRight", "helpDescription", "Landroid/widget/TextView;", "helpTitle", "initialConstraintSet", "Landroidx/constraintlayout/widget/ConstraintSet;", "nextButton", "Landroid/widget/Button;", "overlayTipsLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "seekForwardFirst", "Landroid/widget/ImageView;", "seekForwardSecond", "seekRewindFirst", "seekRewindSecond", "tapGesture", "tapGestureHorizontal", "tapIndicatorAdvanced", "tapIndicatorOrientation", "tapIndicatorPlay", "tapIndicatorRatio", "tapIndicatorTracks", "tipsBrightnessProgress", "Lorg/videolan/vlc/gui/view/PlayerProgress;", "tipsBrightnessText", "tipsVolumeProgress", "tipsVolumeText", "transition", "Landroidx/transition/Fade;", "clearAllAnimations", "", "close", "doubleTap", "Landroid/animation/AnimatorSet;", "view", "repeat", "", "firstSeek", "secondSeek", "getTapIndicators", "", "()[Landroid/view/View;", "init", "next", "onClick", "v", "swipe", "Landroid/animation/ObjectAnimator;", "progress", "textView", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTipsDelegate.kt */
public final class VideoTipsDelegate implements View.OnClickListener {
    private final ArrayList<Animator> currentAnimations = new ArrayList<>();
    private Integer currentControl;
    private VideoPlayerTipsStep currentTip;
    private View doubleTapCenter;
    private View doubleTapLeft;
    private View doubleTapRight;
    private TextView helpDescription;
    private TextView helpTitle;
    private ConstraintSet initialConstraintSet;
    private Button nextButton;
    private ConstraintLayout overlayTipsLayout;
    private final VideoPlayerActivity player;
    private ImageView seekForwardFirst;
    private ImageView seekForwardSecond;
    private ImageView seekRewindFirst;
    private ImageView seekRewindSecond;
    private View tapGesture;
    private View tapGestureHorizontal;
    private View tapIndicatorAdvanced;
    private View tapIndicatorOrientation;
    private View tapIndicatorPlay;
    private View tapIndicatorRatio;
    private View tapIndicatorTracks;
    private PlayerProgress tipsBrightnessProgress;
    private TextView tipsBrightnessText;
    private PlayerProgress tipsVolumeProgress;
    private TextView tipsVolumeText;
    private final Fade transition;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoTipsDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.gui.video.VideoPlayerTipsStep[] r0 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.CONTROLS     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.BRIGHTNESS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.VOLUME     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.PAUSE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.SEEK_TAP     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = org.videolan.vlc.gui.video.VideoPlayerTipsStep.SEEK     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoTipsDelegate.WhenMappings.<clinit>():void");
        }
    }

    public VideoTipsDelegate(VideoPlayerActivity videoPlayerActivity) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        this.player = videoPlayerActivity;
        Fade fade = new Fade();
        fade.setInterpolator(new AccelerateDecelerateInterpolator());
        fade.setDuration(300);
        this.transition = fade;
    }

    public final VideoPlayerTipsStep getCurrentTip() {
        return this.currentTip;
    }

    public final void setCurrentTip(VideoPlayerTipsStep videoPlayerTipsStep) {
        this.currentTip = videoPlayerTipsStep;
    }

    public final Integer getCurrentControl() {
        return this.currentControl;
    }

    public final void setCurrentControl(Integer num) {
        this.currentControl = num;
    }

    public final void init() {
        View findViewById = this.player.findViewById(R.id.player_overlay_tips);
        ConstraintLayout constraintLayout = null;
        ViewStubCompat viewStubCompat = findViewById instanceof ViewStubCompat ? (ViewStubCompat) findViewById : null;
        if (viewStubCompat != null) {
            viewStubCompat.inflate();
        }
        View findViewById2 = this.player.findViewById(R.id.tipsBrightnessProgress);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tipsBrightnessProgress = (PlayerProgress) findViewById2;
        View findViewById3 = this.player.findViewById(R.id.tipsVolumeProgress);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tipsVolumeProgress = (PlayerProgress) findViewById3;
        View findViewById4 = this.player.findViewById(R.id.overlayTipsLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.overlayTipsLayout = (ConstraintLayout) findViewById4;
        View findViewById5 = this.player.findViewById(R.id.tapIndicatorTracks);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tapIndicatorTracks = findViewById5;
        View findViewById6 = this.player.findViewById(R.id.tapIndicatorOrientation);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tapIndicatorOrientation = findViewById6;
        View findViewById7 = this.player.findViewById(R.id.tapIndicatorPlay);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tapIndicatorPlay = findViewById7;
        View findViewById8 = this.player.findViewById(R.id.tapIndicatorRatio);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.tapIndicatorRatio = findViewById8;
        View findViewById9 = this.player.findViewById(R.id.tapIndicatorAdvanced);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.tapIndicatorAdvanced = findViewById9;
        View findViewById10 = this.player.findViewById(R.id.tapGesture);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.tapGesture = findViewById10;
        View findViewById11 = this.player.findViewById(R.id.nextButton);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.nextButton = (Button) findViewById11;
        View findViewById12 = this.player.findViewById(R.id.tipsBrightnessText);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.tipsBrightnessText = (TextView) findViewById12;
        View findViewById13 = this.player.findViewById(R.id.tipsVolumeText);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.tipsVolumeText = (TextView) findViewById13;
        View findViewById14 = this.player.findViewById(R.id.doubleTapCenter);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.doubleTapCenter = findViewById14;
        View findViewById15 = this.player.findViewById(R.id.doubleTapLeft);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.doubleTapLeft = findViewById15;
        View findViewById16 = this.player.findViewById(R.id.doubleTapRight);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.doubleTapRight = findViewById16;
        View findViewById17 = this.player.findViewById(R.id.seekRewindFirst);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.seekRewindFirst = (ImageView) findViewById17;
        View findViewById18 = this.player.findViewById(R.id.seekRewindSecond);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.seekRewindSecond = (ImageView) findViewById18;
        View findViewById19 = this.player.findViewById(R.id.seekForwardFirst);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.seekForwardFirst = (ImageView) findViewById19;
        View findViewById20 = this.player.findViewById(R.id.seekForwardSecond);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        this.seekForwardSecond = (ImageView) findViewById20;
        View findViewById21 = this.player.findViewById(R.id.tapGestureHorizontal);
        Intrinsics.checkNotNullExpressionValue(findViewById21, "findViewById(...)");
        this.tapGestureHorizontal = findViewById21;
        View findViewById22 = this.player.findViewById(R.id.helpTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById22, "findViewById(...)");
        this.helpTitle = (TextView) findViewById22;
        View findViewById23 = this.player.findViewById(R.id.helpDescription);
        Intrinsics.checkNotNullExpressionValue(findViewById23, "findViewById(...)");
        this.helpDescription = (TextView) findViewById23;
        PlayerProgress playerProgress = this.tipsBrightnessProgress;
        if (playerProgress == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tipsBrightnessProgress");
            playerProgress = null;
        }
        playerProgress.setValue(50);
        PlayerProgress playerProgress2 = this.tipsVolumeProgress;
        if (playerProgress2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tipsVolumeProgress");
            playerProgress2 = null;
        }
        playerProgress2.setValue(50);
        if (this.initialConstraintSet == null) {
            ConstraintSet constraintSet = new ConstraintSet();
            this.initialConstraintSet = constraintSet;
            ConstraintLayout constraintLayout2 = this.overlayTipsLayout;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayTipsLayout");
                constraintLayout2 = null;
            }
            constraintSet.clone(constraintLayout2);
        }
        ConstraintLayout constraintLayout3 = this.overlayTipsLayout;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayTipsLayout");
        } else {
            constraintLayout = constraintLayout3;
        }
        KotlinExtensionsKt.setVisible(constraintLayout);
        next();
        for (View onClickListener : getTapIndicators()) {
            onClickListener.setOnClickListener(this);
        }
    }

    private final View[] getTapIndicators() {
        View view = this.tapIndicatorTracks;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorTracks");
            view = null;
        }
        View view3 = this.tapIndicatorOrientation;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorOrientation");
            view3 = null;
        }
        View view4 = this.tapIndicatorPlay;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorPlay");
            view4 = null;
        }
        View view5 = this.tapIndicatorRatio;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorRatio");
            view5 = null;
        }
        View view6 = this.tapIndicatorAdvanced;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorAdvanced");
        } else {
            view2 = view6;
        }
        return new View[]{view, view3, view4, view5, view2};
    }

    static /* synthetic */ AnimatorSet doubleTap$default(VideoTipsDelegate videoTipsDelegate, View view, boolean z, View view2, View view3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            view2 = null;
        }
        if ((i & 8) != 0) {
            view3 = null;
        }
        return videoTipsDelegate.doubleTap(view, z, view2, view3);
    }

    private final AnimatorSet doubleTap(View view, boolean z, View view2, View view3) {
        view.clearAnimation();
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setAlpha(0.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 0.9f, 1.0f, 0.9f, 1.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 0.9f, 1.0f, 0.9f, 1.0f, 1.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f});
        ofFloat.setStartDelay(500);
        ofFloat2.setStartDelay(500);
        ofFloat3.setStartDelay(500);
        ofFloat.setDuration(1600);
        ofFloat2.setDuration(1600);
        ofFloat3.setDuration(1600);
        Intrinsics.checkNotNull(ofFloat);
        Intrinsics.checkNotNull(ofFloat2);
        Intrinsics.checkNotNull(ofFloat3);
        List arrayListOf = CollectionsKt.arrayListOf(ofFloat, ofFloat2, ofFloat3);
        if (view2 != null) {
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view2, View.ALPHA, new float[]{0.0f, 1.0f, 0.0f});
            ofFloat4.setDuration(500);
            ofFloat4.setStartDelay(1000);
            Intrinsics.checkNotNull(ofFloat4);
            arrayListOf.add(ofFloat4);
        }
        if (view3 != null) {
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view3, View.ALPHA, new float[]{0.0f, 1.0f, 0.0f});
            ofFloat5.setDuration(750);
            ofFloat5.setStartDelay(1000);
            Intrinsics.checkNotNull(ofFloat5);
            arrayListOf.add(ofFloat5);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayListOf);
        if (z) {
            animatorSet.addListener(new VideoTipsDelegate$doubleTap$$inlined$doOnEnd$1(animatorSet));
            animatorSet.start();
        }
        return animatorSet;
    }

    private final ObjectAnimator swipe(PlayerProgress playerProgress, TextView textView) {
        View view = this.tapGesture;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapGesture");
            view = null;
        }
        view.setTranslationY(0.0f);
        View view3 = this.tapGesture;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapGesture");
        } else {
            view2 = view3;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Y, new float[]{0.0f});
        ofFloat.setDuration(1600);
        ofFloat.setFloatValues(new float[]{0.0f, 30.0f, -30.0f, 0.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setStartDelay(1000);
        ofFloat.addUpdateListener(new VideoTipsDelegate$$ExternalSyntheticLambda0(playerProgress, textView));
        Intrinsics.checkNotNull(ofFloat);
        ofFloat.addListener(new VideoTipsDelegate$swipe$$inlined$doOnEnd$1(ofFloat));
        ofFloat.start();
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public static final void swipe$lambda$5(PlayerProgress playerProgress, TextView textView, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(playerProgress, "$progress");
        Intrinsics.checkNotNullParameter(textView, "$textView");
        Intrinsics.checkNotNullParameter(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        int floatValue = 50 - ((int) ((((Float) animatedValue).floatValue() * ((float) 2)) / ((float) 3)));
        playerProgress.setValue(floatValue);
        StringBuilder sb = new StringBuilder();
        sb.append(floatValue);
        sb.append('%');
        textView.setText(sb.toString());
    }

    public final void next() {
        VideoPlayerTipsStep videoPlayerTipsStep;
        View view;
        if (this.currentTip == VideoPlayerTipsStep.SEEK) {
            close();
            return;
        }
        VideoPlayerTipsStep videoPlayerTipsStep2 = this.currentTip;
        if (videoPlayerTipsStep2 == null || (videoPlayerTipsStep = videoPlayerTipsStep2.next()) == null) {
            videoPlayerTipsStep = VideoPlayerTipsStep.CONTROLS;
        }
        this.currentTip = videoPlayerTipsStep;
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintSet constraintSet2 = this.initialConstraintSet;
        TextView textView = null;
        if (constraintSet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialConstraintSet");
            constraintSet2 = null;
        }
        constraintSet.clone(constraintSet2);
        ConstraintLayout constraintLayout = this.overlayTipsLayout;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayTipsLayout");
            constraintLayout = null;
        }
        TransitionManager.beginDelayedTransition(constraintLayout, this.transition);
        for (View id : getTapIndicators()) {
            constraintSet.setVisibility(id.getId(), 8);
        }
        clearAllAnimations();
        Button button = this.nextButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            button = null;
        }
        button.setText(R.string.next_step);
        VideoPlayerTipsStep videoPlayerTipsStep3 = this.currentTip;
        switch (videoPlayerTipsStep3 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[videoPlayerTipsStep3.ordinal()]) {
            case 1:
                for (View id2 : getTapIndicators()) {
                    constraintSet.setVisibility(id2.getId(), 0);
                }
                TipsUtils.startTapAnimation$default(TipsUtils.INSTANCE, ArraysKt.toList((T[]) getTapIndicators()), false, 2, (Object) null);
                break;
            case 2:
                ConstraintSet constraintSet3 = constraintSet;
                constraintSet3.connect(R.id.helpTitle, 6, R.id.tap_gesture_background, 7, KotlinExtensionsKt.getDp(16));
                constraintSet3.connect(R.id.helpTitle, 7, R.id.tipsBrightnessProgress, 6, KotlinExtensionsKt.getDp(16));
                constraintSet.setHorizontalBias(R.id.helpTitle, 1.0f);
                constraintSet3.connect(R.id.helpDescription, 6, R.id.tap_gesture_background, 7, KotlinExtensionsKt.getDp(16));
                constraintSet3.connect(R.id.helpDescription, 7, R.id.tipsBrightnessProgress, 6, KotlinExtensionsKt.getDp(16));
                constraintSet.setHorizontalBias(R.id.helpDescription, 1.0f);
                constraintSet.setVisibility(R.id.tapGesture, 0);
                constraintSet.setVisibility(R.id.tap_gesture_background, 0);
                constraintSet.setVisibility(R.id.tipsBrightnessText, 0);
                constraintSet.setVisibility(R.id.tips_brightness_icon, 0);
                constraintSet.setVisibility(R.id.tipsBrightnessProgress, 0);
                this.currentAnimations.clear();
                ArrayList<Animator> arrayList = this.currentAnimations;
                PlayerProgress playerProgress = this.tipsBrightnessProgress;
                if (playerProgress == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tipsBrightnessProgress");
                    playerProgress = null;
                }
                TextView textView2 = this.tipsBrightnessText;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tipsBrightnessText");
                    textView2 = null;
                }
                arrayList.add(swipe(playerProgress, textView2));
                break;
            case 3:
                constraintSet.clear(R.id.tap_gesture_background, 6);
                ConstraintSet constraintSet4 = constraintSet;
                constraintSet4.connect(R.id.tap_gesture_background, 7, 0, 7, KotlinExtensionsKt.getDp(32));
                constraintSet4.connect(R.id.helpTitle, 6, R.id.tipsVolumeProgress, 7, KotlinExtensionsKt.getDp(16));
                constraintSet4.connect(R.id.helpTitle, 7, R.id.tap_gesture_background, 6, KotlinExtensionsKt.getDp(16));
                constraintSet.setHorizontalBias(R.id.helpTitle, 0.0f);
                constraintSet4.connect(R.id.helpDescription, 6, R.id.tipsVolumeProgress, 7, KotlinExtensionsKt.getDp(16));
                constraintSet4.connect(R.id.helpDescription, 7, R.id.tap_gesture_background, 6, KotlinExtensionsKt.getDp(16));
                constraintSet.setHorizontalBias(R.id.helpDescription, 0.0f);
                constraintSet.setVisibility(R.id.tapGesture, 0);
                constraintSet.setVisibility(R.id.tap_gesture_background, 0);
                constraintSet.setVisibility(R.id.tipsVolumeText, 0);
                constraintSet.setVisibility(R.id.tips_volume_icon, 0);
                constraintSet.setVisibility(R.id.tipsVolumeProgress, 0);
                this.currentAnimations.clear();
                ArrayList<Animator> arrayList2 = this.currentAnimations;
                PlayerProgress playerProgress2 = this.tipsVolumeProgress;
                if (playerProgress2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tipsVolumeProgress");
                    playerProgress2 = null;
                }
                TextView textView3 = this.tipsVolumeText;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tipsVolumeText");
                    textView3 = null;
                }
                arrayList2.add(swipe(playerProgress2, textView3));
                break;
            case 4:
                constraintSet.setVisibility(R.id.doubleTapCenter, 0);
                this.currentAnimations.clear();
                ArrayList<Animator> arrayList3 = this.currentAnimations;
                View view2 = this.doubleTapCenter;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("doubleTapCenter");
                    view = null;
                } else {
                    view = view2;
                }
                arrayList3.add(doubleTap$default(this, view, false, (View) null, (View) null, 14, (Object) null));
                break;
            case 5:
                constraintSet.setVisibility(R.id.doubleTapLeft, 0);
                constraintSet.setVisibility(R.id.doubleTapRight, 0);
                constraintSet.setVisibility(R.id.seekRewindFirst, 0);
                constraintSet.setVisibility(R.id.seekRewindSecond, 0);
                constraintSet.setVisibility(R.id.seekForwardFirst, 0);
                constraintSet.setVisibility(R.id.seekForwardSecond, 0);
                this.currentAnimations.clear();
                View view3 = this.doubleTapLeft;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("doubleTapLeft");
                    view3 = null;
                }
                ImageView imageView = this.seekRewindFirst;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("seekRewindFirst");
                    imageView = null;
                }
                View view4 = imageView;
                ImageView imageView2 = this.seekRewindSecond;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("seekRewindSecond");
                    imageView2 = null;
                }
                AnimatorSet doubleTap = doubleTap(view3, false, view4, imageView2);
                View view5 = this.doubleTapRight;
                if (view5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("doubleTapRight");
                    view5 = null;
                }
                ImageView imageView3 = this.seekForwardFirst;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("seekForwardFirst");
                    imageView3 = null;
                }
                View view6 = imageView3;
                ImageView imageView4 = this.seekForwardSecond;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("seekForwardSecond");
                    imageView4 = null;
                }
                AnimatorSet doubleTap2 = doubleTap(view5, false, view6, imageView4);
                doubleTap.addListener(new VideoTipsDelegate$next$$inlined$doOnEnd$1(doubleTap2));
                doubleTap2.addListener(new VideoTipsDelegate$next$$inlined$doOnEnd$2(doubleTap));
                this.currentAnimations.add(doubleTap);
                this.currentAnimations.add(doubleTap2);
                doubleTap.start();
                break;
            case 6:
                constraintSet.connect(R.id.tapGesture, 7, R.id.tap_gesture_horizontal_background, 7);
                constraintSet.connect(R.id.tapGesture, 6, R.id.tap_gesture_horizontal_background, 6);
                constraintSet.connect(R.id.tapGesture, 3, R.id.tap_gesture_horizontal_background, 3);
                constraintSet.connect(R.id.tapGesture, 4, R.id.tap_gesture_horizontal_background, 4);
                constraintSet.setVisibility(R.id.tap_gesture_horizontal_background, 0);
                constraintSet.setVisibility(R.id.tapGestureHorizontal, 0);
                this.currentAnimations.clear();
                ArrayList<Animator> arrayList4 = this.currentAnimations;
                TipsUtils tipsUtils = TipsUtils.INSTANCE;
                View view7 = this.tapGestureHorizontal;
                if (view7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tapGestureHorizontal");
                    view7 = null;
                }
                arrayList4.add(TipsUtils.horizontalSwipe$default(tipsUtils, view7, (Function1) null, 2, (Object) null));
                Button button2 = this.nextButton;
                if (button2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nextButton");
                    button2 = null;
                }
                button2.setText(R.string.close);
                break;
        }
        ConstraintLayout constraintLayout2 = this.overlayTipsLayout;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayTipsLayout");
            constraintLayout2 = null;
        }
        constraintSet.applyTo(constraintLayout2);
        TextView textView4 = this.helpTitle;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
            textView4 = null;
        }
        VideoPlayerTipsStep videoPlayerTipsStep4 = this.currentTip;
        Intrinsics.checkNotNull(videoPlayerTipsStep4);
        textView4.setText(videoPlayerTipsStep4.getTitleText());
        TextView textView5 = this.helpDescription;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
        } else {
            textView = textView5;
        }
        VideoPlayerTipsStep videoPlayerTipsStep5 = this.currentTip;
        Intrinsics.checkNotNull(videoPlayerTipsStep5);
        textView.setText(videoPlayerTipsStep5.getDescriptionText());
    }

    private final void clearAllAnimations() {
        for (View animate : getTapIndicators()) {
            animate.animate().cancel();
        }
        View view = this.tapGesture;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapGesture");
            view = null;
        }
        view.clearAnimation();
        View view3 = this.tapGestureHorizontal;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapGestureHorizontal");
        } else {
            view2 = view3;
        }
        view2.clearAnimation();
        for (Animator animator : this.currentAnimations) {
            animator.cancel();
            animator.removeAllListeners();
        }
    }

    public final void close() {
        clearAllAnimations();
        ConstraintLayout constraintLayout = this.overlayTipsLayout;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayTipsLayout");
            constraintLayout = null;
        }
        KotlinExtensionsKt.setGone(constraintLayout);
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.player), SettingsKt.PREF_TIPS_SHOWN, true);
        this.currentTip = null;
        this.player.play();
    }

    public void onClick(View view) {
        for (View backgroundResource : getTapIndicators()) {
            backgroundResource.setBackgroundResource(0);
        }
        TextView textView = null;
        if (Intrinsics.areEqual((Object) this.currentControl, (Object) view != null ? Integer.valueOf(view.getId()) : null)) {
            TextView textView2 = this.helpTitle;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
                textView2 = null;
            }
            textView2.setText(R.string.tips_player_controls);
            TextView textView3 = this.helpDescription;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
                textView3 = null;
            }
            textView3.setText(R.string.tips_player_controls_description);
            this.currentControl = null;
            return;
        }
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        int i = R.id.tapIndicatorTracks;
        if (valueOf != null && valueOf.intValue() == i) {
            TextView textView4 = this.helpTitle;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
                textView4 = null;
            }
            textView4.setText(R.string.tips_audio_sub);
            TextView textView5 = this.helpDescription;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
            } else {
                textView = textView5;
            }
            textView.setText(R.string.tap);
            view.setBackground(ContextCompat.getDrawable(this.player, R.drawable.tips_tap));
            this.currentControl = Integer.valueOf(view.getId());
            return;
        }
        int i2 = R.id.tapIndicatorOrientation;
        if (valueOf != null && valueOf.intValue() == i2) {
            TextView textView6 = this.helpTitle;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
                textView6 = null;
            }
            textView6.setText(R.string.lock_orientation);
            TextView textView7 = this.helpDescription;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
            } else {
                textView = textView7;
            }
            textView.setText(R.string.lock_orientation_description);
            view.setBackground(ContextCompat.getDrawable(this.player, R.drawable.tips_tap));
            this.currentControl = Integer.valueOf(view.getId());
            return;
        }
        int i3 = R.id.tapIndicatorPlay;
        if (valueOf != null && valueOf.intValue() == i3) {
            TextView textView8 = this.helpTitle;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
                textView8 = null;
            }
            textView8.setText(R.string.play);
            TextView textView9 = this.helpDescription;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
            } else {
                textView = textView9;
            }
            textView.setText(R.string.tips_play_description);
            view.setBackground(ContextCompat.getDrawable(this.player, R.drawable.tips_tap));
            this.currentControl = Integer.valueOf(view.getId());
            return;
        }
        int i4 = R.id.tapIndicatorRatio;
        if (valueOf != null && valueOf.intValue() == i4) {
            TextView textView10 = this.helpTitle;
            if (textView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
                textView10 = null;
            }
            textView10.setText(R.string.aspect_ratio);
            TextView textView11 = this.helpDescription;
            if (textView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
            } else {
                textView = textView11;
            }
            textView.setText(R.string.aspect_ratio_description);
            view.setBackground(ContextCompat.getDrawable(this.player, R.drawable.tips_tap));
            this.currentControl = Integer.valueOf(view.getId());
            return;
        }
        int i5 = R.id.tapIndicatorAdvanced;
        if (valueOf != null && valueOf.intValue() == i5) {
            TextView textView12 = this.helpTitle;
            if (textView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
                textView12 = null;
            }
            textView12.setText(R.string.advanced_options);
            TextView textView13 = this.helpDescription;
            if (textView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
            } else {
                textView = textView13;
            }
            textView.setText(R.string.advanced_options_description);
            view.setBackground(ContextCompat.getDrawable(this.player, R.drawable.tips_tap));
            this.currentControl = Integer.valueOf(view.getId());
        }
    }
}
