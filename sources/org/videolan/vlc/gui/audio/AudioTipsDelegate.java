package org.videolan.vlc.gui.audio;

import android.animation.Animator;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.helpers.TipsUtils;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010%\u001a\u00020&H\u0002J\u0006\u0010'\u001a\u00020&J\u0010\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010*J\u0006\u0010+\u001a\u00020&J\u0010\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020.H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioTipsDelegate;", "", "activity", "Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;", "(Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;)V", "audioPlayerTips", "Landroidx/constraintlayout/widget/ConstraintLayout;", "audioTipsBackground", "Landroid/view/View;", "currentAnimations", "Ljava/util/ArrayList;", "Landroid/animation/Animator;", "Lkotlin/collections/ArrayList;", "currentTip", "Lorg/videolan/vlc/gui/audio/AudioPlayerTipsStep;", "getCurrentTip", "()Lorg/videolan/vlc/gui/audio/AudioPlayerTipsStep;", "setCurrentTip", "(Lorg/videolan/vlc/gui/audio/AudioPlayerTipsStep;)V", "headerLargePlayPause", "Landroid/widget/ImageView;", "headerPrevious", "helpDescription", "Landroid/widget/TextView;", "helpTitle", "initialConstraintSet", "Landroidx/constraintlayout/widget/ConstraintSet;", "nextButton", "Landroid/widget/Button;", "rightGuidelineEndBound", "", "tapGestureHorizontal", "tapIndicatorPlaylist", "tapIndicatorStop", "topGuidelineEndBound", "transition", "Landroidx/transition/Fade;", "clearAllAnimations", "", "close", "init", "vsc", "Landroidx/appcompat/widget/ViewStubCompat;", "next", "updateBackgroundPosition", "peek", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioTipsDelegate.kt */
public final class AudioTipsDelegate {
    private final AudioPlayerContainerActivity activity;
    private ConstraintLayout audioPlayerTips;
    private View audioTipsBackground;
    private final ArrayList<Animator> currentAnimations;
    private AudioPlayerTipsStep currentTip;
    private ImageView headerLargePlayPause;
    private ImageView headerPrevious;
    private TextView helpDescription;
    private TextView helpTitle;
    private ConstraintSet initialConstraintSet;
    private Button nextButton;
    private float rightGuidelineEndBound = 1.0f;
    private View tapGestureHorizontal;
    private View tapIndicatorPlaylist;
    private View tapIndicatorStop;
    private float topGuidelineEndBound;
    private final Fade transition;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioTipsDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.gui.audio.AudioPlayerTipsStep[] r0 = org.videolan.vlc.gui.audio.AudioPlayerTipsStep.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.audio.AudioPlayerTipsStep r1 = org.videolan.vlc.gui.audio.AudioPlayerTipsStep.SWIPE_NEXT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.audio.AudioPlayerTipsStep r1 = org.videolan.vlc.gui.audio.AudioPlayerTipsStep.TAP_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.audio.AudioPlayerTipsStep r1 = org.videolan.vlc.gui.audio.AudioPlayerTipsStep.HOLD_STOP     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioTipsDelegate.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static final boolean init$lambda$1(View view, MotionEvent motionEvent) {
        return true;
    }

    public AudioTipsDelegate(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "activity");
        this.activity = audioPlayerContainerActivity;
        Fade fade = new Fade();
        fade.setInterpolator(new AccelerateDecelerateInterpolator());
        fade.setDuration(300);
        this.transition = fade;
        this.currentAnimations = new ArrayList<>();
    }

    public final AudioPlayerTipsStep getCurrentTip() {
        return this.currentTip;
    }

    public final void setCurrentTip(AudioPlayerTipsStep audioPlayerTipsStep) {
        this.currentTip = audioPlayerTipsStep;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0117, code lost:
        r9 = r9.getDisplayFeatures();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init(androidx.appcompat.widget.ViewStubCompat r9) {
        /*
            r8 = this;
            if (r9 == 0) goto L_0x0005
            r9.inflate()
        L_0x0005:
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r0 = org.videolan.vlc.R.id.audio_tips_background
            android.view.View r9 = r9.findViewById(r0)
            java.lang.String r0 = "findViewById(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            r8.audioTipsBackground = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.header_previous
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r8.headerPrevious = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.audioPlayerTips
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            androidx.constraintlayout.widget.ConstraintLayout r9 = (androidx.constraintlayout.widget.ConstraintLayout) r9
            r8.audioPlayerTips = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.tapIndicatorPlaylist
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            r8.tapIndicatorPlaylist = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.header_large_play_pause
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r8.headerLargePlayPause = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.tapIndicatorStop
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            r8.tapIndicatorStop = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.nextButton
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            android.widget.Button r9 = (android.widget.Button) r9
            r8.nextButton = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.tapGestureHorizontal
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            r8.tapGestureHorizontal = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.helpTitle
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r8.helpTitle = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            int r1 = org.videolan.vlc.R.id.helpDescription
            android.view.View r9 = r9.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r8.helpDescription = r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            org.videolan.vlc.gui.helpers.PlayerBehavior r9 = r9.getPlayerBehavior()
            r0 = 4
            r9.setState(r0)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            org.videolan.vlc.gui.helpers.PlayerBehavior r9 = r9.getPlayerBehavior()
            r0 = 1
            r9.lock(r0)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            org.videolan.vlc.gui.helpers.PlayerBehavior r9 = r9.getPlayerBehavior()
            org.videolan.vlc.gui.audio.AudioTipsDelegate$init$1 r0 = new org.videolan.vlc.gui.audio.AudioTipsDelegate$init$1
            r0.<init>(r8)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            r9.setPeekHeightListener(r0)
            androidx.constraintlayout.widget.ConstraintSet r9 = r8.initialConstraintSet
            java.lang.String r0 = "audioPlayerTips"
            r1 = 0
            if (r9 != 0) goto L_0x00d2
            androidx.constraintlayout.widget.ConstraintSet r9 = new androidx.constraintlayout.widget.ConstraintSet
            r9.<init>()
            r8.initialConstraintSet = r9
            androidx.constraintlayout.widget.ConstraintLayout r2 = r8.audioPlayerTips
            if (r2 != 0) goto L_0x00cf
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r2 = r1
        L_0x00cf:
            r9.clone((androidx.constraintlayout.widget.ConstraintLayout) r2)
        L_0x00d2:
            androidx.constraintlayout.widget.ConstraintLayout r9 = r8.audioPlayerTips
            if (r9 != 0) goto L_0x00da
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r9 = r1
        L_0x00da:
            android.view.View r9 = (android.view.View) r9
            org.videolan.tools.KotlinExtensionsKt.setVisible(r9)
            androidx.constraintlayout.widget.ConstraintLayout r9 = r8.audioPlayerTips
            if (r9 != 0) goto L_0x00e7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r9 = r1
        L_0x00e7:
            org.videolan.vlc.gui.audio.AudioTipsDelegate$$ExternalSyntheticLambda0 r0 = new org.videolan.vlc.gui.audio.AudioTipsDelegate$$ExternalSyntheticLambda0
            r0.<init>()
            r9.setOnTouchListener(r0)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            androidx.lifecycle.LifecycleOwner r9 = (androidx.lifecycle.LifecycleOwner) r9
            androidx.lifecycle.LifecycleCoroutineScope r9 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)
            r2 = r9
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getMain()
            r3 = r9
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            org.videolan.vlc.gui.audio.AudioTipsDelegate$init$3 r9 = new org.videolan.vlc.gui.audio.AudioTipsDelegate$init$3
            r9.<init>(r8, r1)
            r5 = r9
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 2
            r7 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r9 = r8.activity
            androidx.window.layout.WindowLayoutInfo r9 = r9.getWindowLayoutInfo()
            if (r9 == 0) goto L_0x0124
            java.util.List r9 = r9.getDisplayFeatures()
            if (r9 == 0) goto L_0x0124
            java.lang.Object r9 = kotlin.collections.CollectionsKt.firstOrNull(r9)
            androidx.window.layout.DisplayFeature r9 = (androidx.window.layout.DisplayFeature) r9
            goto L_0x0125
        L_0x0124:
            r9 = r1
        L_0x0125:
            boolean r0 = r9 instanceof androidx.window.layout.FoldingFeature
            if (r0 == 0) goto L_0x012c
            r1 = r9
            androidx.window.layout.FoldingFeature r1 = (androidx.window.layout.FoldingFeature) r1
        L_0x012c:
            if (r1 == 0) goto L_0x016d
            androidx.window.layout.FoldingFeature$OcclusionType r9 = r1.getOcclusionType()
            androidx.window.layout.FoldingFeature$OcclusionType r0 = androidx.window.layout.FoldingFeature.OcclusionType.FULL
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r0)
            if (r9 == 0) goto L_0x016d
            androidx.window.layout.FoldingFeature$Orientation r9 = r1.getOrientation()
            androidx.window.layout.FoldingFeature$Orientation r0 = androidx.window.layout.FoldingFeature.Orientation.VERTICAL
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r0)
            if (r9 == 0) goto L_0x015a
            android.graphics.Rect r9 = r1.getBounds()
            int r9 = r9.left
            float r9 = (float) r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r8.activity
            android.app.Activity r0 = (android.app.Activity) r0
            int r0 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r0)
            float r0 = (float) r0
            float r9 = r9 / r0
            r8.rightGuidelineEndBound = r9
            goto L_0x016d
        L_0x015a:
            android.graphics.Rect r9 = r1.getBounds()
            int r9 = r9.bottom
            float r9 = (float) r9
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r8.activity
            android.app.Activity r0 = (android.app.Activity) r0
            int r0 = org.videolan.vlc.util.KextensionsKt.getScreenHeight(r0)
            float r0 = (float) r0
            float r9 = r9 / r0
            r8.topGuidelineEndBound = r9
        L_0x016d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioTipsDelegate.init(androidx.appcompat.widget.ViewStubCompat):void");
    }

    /* access modifiers changed from: private */
    public final void updateBackgroundPosition(int i) {
        View view = this.audioTipsBackground;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioTipsBackground");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.bottomMargin = i;
        View view3 = this.audioTipsBackground;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioTipsBackground");
        } else {
            view2 = view3;
        }
        view2.setLayoutParams(layoutParams2);
    }

    public final void next() {
        AudioPlayerTipsStep audioPlayerTipsStep;
        int i;
        if (this.currentTip == AudioPlayerTipsStep.HOLD_STOP) {
            close();
            return;
        }
        AudioPlayerTipsStep audioPlayerTipsStep2 = this.currentTip;
        if (audioPlayerTipsStep2 == null || (audioPlayerTipsStep = audioPlayerTipsStep2.next()) == null) {
            audioPlayerTipsStep = AudioPlayerTipsStep.SWIPE_NEXT;
        }
        this.currentTip = audioPlayerTipsStep;
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintSet constraintSet2 = this.initialConstraintSet;
        TextView textView = null;
        if (constraintSet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialConstraintSet");
            constraintSet2 = null;
        }
        constraintSet.clone(constraintSet2);
        ConstraintLayout constraintLayout = this.audioPlayerTips;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayerTips");
            constraintLayout = null;
        }
        TransitionManager.beginDelayedTransition(constraintLayout, this.transition);
        clearAllAnimations();
        Button button = this.nextButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            button = null;
        }
        button.setText(R.string.next_step);
        constraintSet.setGuidelinePercent(R.id.endGuideline, this.rightGuidelineEndBound);
        constraintSet.setGuidelinePercent(R.id.topGuideline, this.topGuidelineEndBound);
        AudioPlayerTipsStep audioPlayerTipsStep3 = this.currentTip;
        int i2 = audioPlayerTipsStep3 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[audioPlayerTipsStep3.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                constraintSet.setVisibility(R.id.tapIndicatorPlaylist, 0);
                TipsUtils tipsUtils = TipsUtils.INSTANCE;
                View view = this.tapIndicatorPlaylist;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorPlaylist");
                    view = null;
                }
                TipsUtils.startTapAnimation$default(tipsUtils, CollectionsKt.listOf(view), false, 2, (Object) null);
            } else if (i2 == 3) {
                if (UiTools.INSTANCE.isTablet(this.activity)) {
                    ImageView imageView = this.headerLargePlayPause;
                    if (imageView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("headerLargePlayPause");
                        imageView = null;
                    }
                    int left = imageView.getLeft();
                    ImageView imageView2 = this.headerLargePlayPause;
                    if (imageView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("headerLargePlayPause");
                        imageView2 = null;
                    }
                    int width = (left + (imageView2.getWidth() / 2)) - KotlinExtensionsKt.getDp(24);
                    constraintSet.connect(R.id.tapIndicatorStop, 6, 0, 6);
                    constraintSet.setMargin(R.id.tapIndicatorStop, 6, width);
                    constraintSet.clear(R.id.tapIndicatorStop, 7);
                    TipsUtils tipsUtils2 = TipsUtils.INSTANCE;
                    View view2 = this.tapIndicatorPlaylist;
                    if (view2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorPlaylist");
                        view2 = null;
                    }
                    TipsUtils.startTapAnimation$default(tipsUtils2, CollectionsKt.listOf(view2), false, 2, (Object) null);
                }
                constraintSet.setVisibility(R.id.tapIndicatorStop, 0);
                TipsUtils tipsUtils3 = TipsUtils.INSTANCE;
                View view3 = this.tapIndicatorStop;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorStop");
                    view3 = null;
                }
                tipsUtils3.startTapAnimation(CollectionsKt.listOf(view3), true);
                Button button2 = this.nextButton;
                if (button2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nextButton");
                    button2 = null;
                }
                button2.setText(R.string.close);
            }
        } else if (UiTools.INSTANCE.isTablet(this.activity)) {
            this.currentAnimations.clear();
            constraintSet.setVisibility(R.id.tapIndicatorPlaylist, 0);
            ImageView imageView3 = this.headerPrevious;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerPrevious");
                imageView3 = null;
            }
            int left2 = imageView3.getLeft();
            ImageView imageView4 = this.headerPrevious;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerPrevious");
                imageView4 = null;
            }
            constraintSet.setMargin(R.id.tapIndicatorPlaylist, 6, (left2 + (imageView4.getWidth() / 2)) - KotlinExtensionsKt.getDp(24));
            TipsUtils tipsUtils4 = TipsUtils.INSTANCE;
            View view4 = this.tapIndicatorPlaylist;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorPlaylist");
                view4 = null;
            }
            TipsUtils.startTapAnimation$default(tipsUtils4, CollectionsKt.listOf(view4), false, 2, (Object) null);
        } else {
            constraintSet.setVisibility(R.id.tap_gesture_horizontal_background, 0);
            constraintSet.setVisibility(R.id.tapGestureHorizontal, 0);
            this.currentAnimations.clear();
            ArrayList<Animator> arrayList = this.currentAnimations;
            TipsUtils tipsUtils5 = TipsUtils.INSTANCE;
            View view5 = this.tapGestureHorizontal;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tapGestureHorizontal");
                view5 = null;
            }
            arrayList.add(TipsUtils.horizontalSwipe$default(tipsUtils5, view5, (Function1) null, 2, (Object) null));
        }
        ConstraintLayout constraintLayout2 = this.audioPlayerTips;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayerTips");
            constraintLayout2 = null;
        }
        constraintSet.applyTo(constraintLayout2);
        updateBackgroundPosition(this.activity.getPlayerBehavior().getPeekHeight());
        TextView textView2 = this.helpTitle;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
            textView2 = null;
        }
        AudioPlayerTipsStep audioPlayerTipsStep4 = this.currentTip;
        Intrinsics.checkNotNull(audioPlayerTipsStep4);
        textView2.setText(audioPlayerTipsStep4.getTitleText());
        TextView textView3 = this.helpDescription;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
        } else {
            textView = textView3;
        }
        if (UiTools.INSTANCE.isTablet(this.activity)) {
            AudioPlayerTipsStep audioPlayerTipsStep5 = this.currentTip;
            Intrinsics.checkNotNull(audioPlayerTipsStep5);
            i = audioPlayerTipsStep5.getDescriptionTextTablet();
        } else {
            AudioPlayerTipsStep audioPlayerTipsStep6 = this.currentTip;
            Intrinsics.checkNotNull(audioPlayerTipsStep6);
            i = audioPlayerTipsStep6.getDescriptionText();
        }
        textView.setText(i);
    }

    public final void close() {
        clearAllAnimations();
        ConstraintLayout constraintLayout = this.audioPlayerTips;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayerTips");
            constraintLayout = null;
        }
        KotlinExtensionsKt.setGone(constraintLayout);
        this.activity.getPlayerBehavior().removePeekHeightListener();
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.activity), SettingsKt.PREF_AUDIOPLAYER_TIPS_SHOWN, true);
        this.currentTip = null;
        PlaybackService service = this.activity.getAudioPlayer().getPlaylistModel().getService();
        if (service != null) {
            service.play();
        }
        this.activity.getShownTips().add(Integer.valueOf(R.id.audio_player_tips));
        this.activity.getPlayerBehavior().lock(false);
    }

    private final void clearAllAnimations() {
        for (Animator animator : this.currentAnimations) {
            animator.cancel();
            animator.removeAllListeners();
        }
        View view = this.tapIndicatorPlaylist;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorPlaylist");
            view = null;
        }
        view.animate().cancel();
        View view3 = this.tapIndicatorStop;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorStop");
        } else {
            view2 = view3;
        }
        view2.animate().cancel();
    }
}
