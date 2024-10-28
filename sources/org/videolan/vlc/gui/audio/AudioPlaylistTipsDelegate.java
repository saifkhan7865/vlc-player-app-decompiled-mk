package org.videolan.vlc.gui.audio;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PlaylistItemBinding;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.helpers.TipsUtils;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010)\u001a\u00020*H\u0002J\u0006\u0010+\u001a\u00020*J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020 H\u0002J\b\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u000104J \u00105\u001a\u00020-2\u0006\u00106\u001a\u00020 2\u0006\u00107\u001a\u00020 2\u0006\u00108\u001a\u00020\u001bH\u0002J\u0006\u00109\u001a\u00020*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsDelegate;", "", "activity", "Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;", "(Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;)V", "audioPlaylistTips", "Landroidx/constraintlayout/widget/ConstraintLayout;", "currentAnimations", "Ljava/util/ArrayList;", "Landroid/animation/Animator;", "Lkotlin/collections/ArrayList;", "currentTip", "Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsStep;", "getCurrentTip", "()Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsStep;", "setCurrentTip", "(Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsStep;)V", "helpDescription", "Landroid/widget/TextView;", "helpTitle", "initialConstraintSet", "Landroidx/constraintlayout/widget/ConstraintSet;", "middleGuidelineEndBound", "", "nextButton", "Landroid/widget/Button;", "plTipsTimeline", "Landroid/widget/SeekBar;", "rightGuidelineEndBound", "secondItemBinding", "Lorg/videolan/vlc/databinding/PlaylistItemBinding;", "tapGestureHorizontal", "Landroid/view/View;", "tapIndicatorForward", "tapIndicatorRearrange", "tapIndicatorRewind", "thirdItemBinding", "tracksContainer", "Landroid/widget/LinearLayout;", "transition", "Landroidx/transition/Fade;", "clearAllAnimations", "", "close", "dragAndDrop", "Landroid/animation/AnimatorSet;", "indicatorView", "draggedView", "getItemColor", "", "init", "vsc", "Landroidx/appcompat/widget/ViewStubCompat;", "longTapSeek", "rewindIndicator", "forwardIndicator", "seekView", "next", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlaylistTipsDelegate.kt */
public final class AudioPlaylistTipsDelegate {
    private final AudioPlayerContainerActivity activity;
    private ConstraintLayout audioPlaylistTips;
    private final ArrayList<Animator> currentAnimations = new ArrayList<>();
    private AudioPlaylistTipsStep currentTip;
    private TextView helpDescription;
    private TextView helpTitle;
    private ConstraintSet initialConstraintSet;
    private float middleGuidelineEndBound = 0.5f;
    private Button nextButton;
    private SeekBar plTipsTimeline;
    private float rightGuidelineEndBound = 1.0f;
    /* access modifiers changed from: private */
    public PlaylistItemBinding secondItemBinding;
    private View tapGestureHorizontal;
    private View tapIndicatorForward;
    private View tapIndicatorRearrange;
    private View tapIndicatorRewind;
    private PlaylistItemBinding thirdItemBinding;
    private LinearLayout tracksContainer;
    private final Fade transition;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioPlaylistTipsDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.gui.audio.AudioPlaylistTipsStep[] r0 = org.videolan.vlc.gui.audio.AudioPlaylistTipsStep.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.audio.AudioPlaylistTipsStep r1 = org.videolan.vlc.gui.audio.AudioPlaylistTipsStep.REMOVE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.audio.AudioPlaylistTipsStep r1 = org.videolan.vlc.gui.audio.AudioPlaylistTipsStep.REARRANGE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.audio.AudioPlaylistTipsStep r1 = org.videolan.vlc.gui.audio.AudioPlaylistTipsStep.SEEK     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlaylistTipsDelegate.WhenMappings.<clinit>():void");
        }
    }

    public AudioPlaylistTipsDelegate(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "activity");
        this.activity = audioPlayerContainerActivity;
        Fade fade = new Fade();
        fade.setInterpolator(new AccelerateDecelerateInterpolator());
        fade.setDuration(300);
        this.transition = fade;
    }

    public final AudioPlaylistTipsStep getCurrentTip() {
        return this.currentTip;
    }

    public final void setCurrentTip(AudioPlaylistTipsStep audioPlaylistTipsStep) {
        this.currentTip = audioPlaylistTipsStep;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01aa, code lost:
        r12 = r12.getDisplayFeatures();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init(androidx.appcompat.widget.ViewStubCompat r12) {
        /*
            r11 = this;
            if (r12 == 0) goto L_0x0005
            r12.inflate()
        L_0x0005:
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r0 = org.videolan.vlc.R.id.audioPlaylistTips
            android.view.View r12 = r12.findViewById(r0)
            java.lang.String r0 = "findViewById(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            androidx.constraintlayout.widget.ConstraintLayout r12 = (androidx.constraintlayout.widget.ConstraintLayout) r12
            r11.audioPlaylistTips = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.tracksContainer
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            android.widget.LinearLayout r12 = (android.widget.LinearLayout) r12
            r11.tracksContainer = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.nextButton
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            android.widget.Button r12 = (android.widget.Button) r12
            r11.nextButton = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.tapIndicatorRearrange
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            r11.tapIndicatorRearrange = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.tapGestureHorizontal
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            r11.tapGestureHorizontal = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.tapIndicatorRewind
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            r11.tapIndicatorRewind = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.tapIndicatorForward
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            r11.tapIndicatorForward = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.plTipsTimeline
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            android.widget.SeekBar r12 = (android.widget.SeekBar) r12
            r11.plTipsTimeline = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.helpTitle
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            android.widget.TextView r12 = (android.widget.TextView) r12
            r11.helpTitle = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            int r1 = org.videolan.vlc.R.id.helpDescription
            android.view.View r12 = r12.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            android.widget.TextView r12 = (android.widget.TextView) r12
            r11.helpDescription = r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            r0 = 1
            r12.lockPlayer(r0)
            androidx.constraintlayout.widget.ConstraintSet r12 = r11.initialConstraintSet
            java.lang.String r1 = "audioPlaylistTips"
            r2 = 0
            if (r12 != 0) goto L_0x00b4
            androidx.constraintlayout.widget.ConstraintSet r12 = new androidx.constraintlayout.widget.ConstraintSet
            r12.<init>()
            r11.initialConstraintSet = r12
            androidx.constraintlayout.widget.ConstraintLayout r3 = r11.audioPlaylistTips
            if (r3 != 0) goto L_0x00b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r3 = r2
        L_0x00b1:
            r12.clone((androidx.constraintlayout.widget.ConstraintLayout) r3)
        L_0x00b4:
            android.widget.LinearLayout r12 = r11.tracksContainer
            java.lang.String r3 = "tracksContainer"
            if (r12 != 0) goto L_0x00be
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r12 = r2
        L_0x00be:
            int r12 = r12.getChildCount()
            r4 = 2
            if (r12 != 0) goto L_0x0175
            androidx.lifecycle.ViewModelProvider r12 = new androidx.lifecycle.ViewModelProvider
            org.videolan.vlc.gui.AudioPlayerContainerActivity r5 = r11.activity
            androidx.lifecycle.ViewModelStoreOwner r5 = (androidx.lifecycle.ViewModelStoreOwner) r5
            r12.<init>(r5)
            java.lang.Class<org.videolan.vlc.viewmodels.PlaylistModel> r5 = org.videolan.vlc.viewmodels.PlaylistModel.class
            androidx.lifecycle.ViewModel r12 = r12.get(r5)
            org.videolan.vlc.viewmodels.PlaylistModel r12 = (org.videolan.vlc.viewmodels.PlaylistModel) r12
            org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = r12.getCurrentMediaWrapper()
            if (r12 == 0) goto L_0x0175
            r5 = 0
            r6 = 0
        L_0x00de:
            r7 = 11
            if (r6 >= r7) goto L_0x0175
            org.videolan.vlc.gui.AudioPlayerContainerActivity r7 = r11.activity
            android.content.Context r7 = (android.content.Context) r7
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r8 = org.videolan.vlc.R.layout.playlist_item
            android.widget.LinearLayout r9 = r11.tracksContainer
            if (r9 != 0) goto L_0x00f4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r9 = r2
        L_0x00f4:
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
            android.view.View r7 = r7.inflate(r8, r9, r5)
            androidx.databinding.ViewDataBinding r8 = androidx.databinding.DataBindingUtil.bind(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            org.videolan.vlc.databinding.PlaylistItemBinding r8 = (org.videolan.vlc.databinding.PlaylistItemBinding) r8
            r8.setMedia(r12)
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.CENTER_CROP
            r8.setScaleType(r9)
            org.videolan.vlc.media.MediaUtils r9 = org.videolan.vlc.media.MediaUtils.INSTANCE
            java.lang.String r9 = r9.getMediaSubtitle(r12)
            r8.setSubTitle(r9)
            android.widget.LinearLayout r9 = r11.tracksContainer
            if (r9 != 0) goto L_0x011c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r9 = r2
        L_0x011c:
            r9.addView(r7)
            r7 = 4
            if (r6 != r4) goto L_0x013c
            org.videolan.vlc.gui.view.MiniVisualizer r9 = r8.playing
            r9.stop()
            org.videolan.vlc.gui.view.MiniVisualizer r9 = r8.playing
            r9.setVisibility(r5)
            android.widget.ImageView r9 = r8.coverImage
            r9.setVisibility(r7)
            android.widget.TextView r7 = r8.audioItemTitle
            r7.setTypeface(r2, r0)
            android.widget.TextView r7 = r8.audioItemSubtitle
            r7.setTypeface(r2, r0)
            goto L_0x0150
        L_0x013c:
            org.videolan.vlc.gui.view.MiniVisualizer r9 = r8.playing
            r9.stop()
            org.videolan.vlc.gui.view.MiniVisualizer r9 = r8.playing
            r9.setVisibility(r7)
            android.widget.TextView r7 = r8.audioItemTitle
            r7.setTypeface(r2)
            android.widget.ImageView r7 = r8.coverImage
            r7.setVisibility(r5)
        L_0x0150:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)
            r8.setMasked(r7)
            if (r6 == r0) goto L_0x015f
            if (r6 == r4) goto L_0x015c
            goto L_0x0171
        L_0x015c:
            r11.thirdItemBinding = r8
            goto L_0x0171
        L_0x015f:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            r8.setMasked(r7)
            androidx.constraintlayout.widget.ConstraintLayout r7 = r8.itemContainer
            int r9 = r11.getItemColor()
            r7.setBackgroundColor(r9)
            r11.secondItemBinding = r8
        L_0x0171:
            int r6 = r6 + 1
            goto L_0x00de
        L_0x0175:
            androidx.constraintlayout.widget.ConstraintLayout r12 = r11.audioPlaylistTips
            if (r12 != 0) goto L_0x017d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r12 = r2
        L_0x017d:
            android.view.View r12 = (android.view.View) r12
            org.videolan.tools.KotlinExtensionsKt.setVisible(r12)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            androidx.lifecycle.LifecycleOwner r12 = (androidx.lifecycle.LifecycleOwner) r12
            androidx.lifecycle.LifecycleCoroutineScope r12 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r12)
            r5 = r12
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()
            r6 = r12
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            org.videolan.vlc.gui.audio.AudioPlaylistTipsDelegate$init$2 r12 = new org.videolan.vlc.gui.audio.AudioPlaylistTipsDelegate$init$2
            r12.<init>(r11, r2)
            r8 = r12
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 2
            r10 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r12 = r11.activity
            androidx.window.layout.WindowLayoutInfo r12 = r12.getWindowLayoutInfo()
            if (r12 == 0) goto L_0x01b7
            java.util.List r12 = r12.getDisplayFeatures()
            if (r12 == 0) goto L_0x01b7
            java.lang.Object r12 = kotlin.collections.CollectionsKt.firstOrNull(r12)
            androidx.window.layout.DisplayFeature r12 = (androidx.window.layout.DisplayFeature) r12
            goto L_0x01b8
        L_0x01b7:
            r12 = r2
        L_0x01b8:
            boolean r0 = r12 instanceof androidx.window.layout.FoldingFeature
            if (r0 == 0) goto L_0x01bf
            r2 = r12
            androidx.window.layout.FoldingFeature r2 = (androidx.window.layout.FoldingFeature) r2
        L_0x01bf:
            if (r2 == 0) goto L_0x0214
            androidx.window.layout.FoldingFeature$OcclusionType r12 = r2.getOcclusionType()
            androidx.window.layout.FoldingFeature$OcclusionType r0 = androidx.window.layout.FoldingFeature.OcclusionType.FULL
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)
            if (r12 == 0) goto L_0x0214
            androidx.window.layout.FoldingFeature$Orientation r12 = r2.getOrientation()
            androidx.window.layout.FoldingFeature$Orientation r0 = androidx.window.layout.FoldingFeature.Orientation.VERTICAL
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)
            if (r12 == 0) goto L_0x01ed
            android.graphics.Rect r12 = r2.getBounds()
            int r12 = r12.left
            float r12 = (float) r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r11.activity
            android.app.Activity r0 = (android.app.Activity) r0
            int r0 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r0)
            float r0 = (float) r0
            float r12 = r12 / r0
            r11.rightGuidelineEndBound = r12
            goto L_0x0214
        L_0x01ed:
            android.graphics.Rect r12 = r2.getBounds()
            int r12 = r12.bottom
            float r12 = (float) r12
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r11.activity
            android.app.Activity r0 = (android.app.Activity) r0
            int r0 = org.videolan.vlc.util.KextensionsKt.getScreenHeight(r0)
            float r0 = (float) r0
            android.graphics.Rect r1 = r2.getBounds()
            int r1 = r1.bottom
            float r1 = (float) r1
            float r0 = r0 - r1
            float r1 = (float) r4
            float r0 = r0 / r1
            float r12 = r12 + r0
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r11.activity
            android.app.Activity r0 = (android.app.Activity) r0
            int r0 = org.videolan.vlc.util.KextensionsKt.getScreenHeight(r0)
            float r0 = (float) r0
            float r12 = r12 / r0
            r11.middleGuidelineEndBound = r12
        L_0x0214:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlaylistTipsDelegate.init(androidx.appcompat.widget.ViewStubCompat):void");
    }

    private final int getItemColor() {
        TypedValue typedValue = new TypedValue();
        this.activity.getTheme().resolveAttribute(R.attr.tips_item_background, typedValue, true);
        return typedValue.data;
    }

    private final AnimatorSet dragAndDrop(View view, View view2) {
        View view3 = view;
        View view4 = view2;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view3, View.ALPHA, new float[]{1.0f});
        ofFloat.setDuration(200);
        ofFloat.setStartDelay(500);
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat2, "ofFloat(...)");
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat3, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view3, new PropertyValuesHolder[]{ofFloat2, ofFloat3});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder.setDuration(300);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view3, View.TRANSLATION_Y, new float[]{(float) KotlinExtensionsKt.getDp(-48)});
        ofFloat4.setStartDelay(300);
        ofFloat4.setDuration(800);
        ofFloat4.addUpdateListener(new AudioPlaylistTipsDelegate$$ExternalSyntheticLambda0(view4));
        PropertyValuesHolder ofFloat5 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat5, "ofFloat(...)");
        PropertyValuesHolder ofFloat6 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat6, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(view3, new PropertyValuesHolder[]{ofFloat5, ofFloat6});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder2, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder2.setDuration(300);
        ofPropertyValuesHolder2.addListener(new AudioPlaylistTipsDelegate$dragAndDrop$$inlined$doOnEnd$1(view4));
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view3, View.ALPHA, new float[]{0.0f});
        ofFloat7.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{ofFloat, ofPropertyValuesHolder, ofFloat4, ofPropertyValuesHolder2, ofFloat7});
        animatorSet.addListener(new AudioPlaylistTipsDelegate$dragAndDrop$$inlined$doOnEnd$2(animatorSet));
        animatorSet.start();
        return animatorSet;
    }

    /* access modifiers changed from: private */
    public static final void dragAndDrop$lambda$3(View view, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(view, "$draggedView");
        Intrinsics.checkNotNullParameter(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        view.setTranslationY(((Float) animatedValue).floatValue());
    }

    private final AnimatorSet longTapSeek(View view, View view2, SeekBar seekBar) {
        View view3 = view;
        View view4 = view2;
        SeekBar seekBar2 = seekBar;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view3, View.ALPHA, new float[]{1.0f});
        ofFloat.setDuration(200);
        ofFloat.setStartDelay(500);
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat2, "ofFloat(...)");
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat3, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view3, new PropertyValuesHolder[]{ofFloat2, ofFloat3});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder.setDuration(300);
        ofPropertyValuesHolder.addListener(new AudioPlaylistTipsDelegate$longTapSeek$$inlined$doOnStart$1(seekBar2));
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat4, "ofFloat(...)");
        PropertyValuesHolder ofFloat5 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat5, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(view3, new PropertyValuesHolder[]{ofFloat4, ofFloat5});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder2, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder2.setDuration(300);
        ofPropertyValuesHolder2.setStartDelay(1200);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view3, View.ALPHA, new float[]{0.0f});
        ofFloat6.setDuration(200);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view4, View.ALPHA, new float[]{1.0f});
        ofFloat7.setDuration(200);
        ofFloat7.setStartDelay(500);
        PropertyValuesHolder ofFloat8 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat8, "ofFloat(...)");
        PropertyValuesHolder ofFloat9 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat9, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder3 = ObjectAnimator.ofPropertyValuesHolder(view4, new PropertyValuesHolder[]{ofFloat8, ofFloat9});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder3, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder3.setDuration(300);
        ofPropertyValuesHolder3.addListener(new AudioPlaylistTipsDelegate$longTapSeek$$inlined$doOnStart$2(seekBar2));
        PropertyValuesHolder ofFloat10 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat10, "ofFloat(...)");
        PropertyValuesHolder ofFloat11 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat11, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder4 = ObjectAnimator.ofPropertyValuesHolder(view4, new PropertyValuesHolder[]{ofFloat10, ofFloat11});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder4, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder4.setDuration(300);
        ofPropertyValuesHolder4.setStartDelay(1200);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(view4, View.ALPHA, new float[]{0.0f});
        ofFloat12.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{ofFloat, ofPropertyValuesHolder, ofPropertyValuesHolder2, ofFloat6, ofFloat7, ofPropertyValuesHolder3, ofPropertyValuesHolder4, ofFloat12});
        animatorSet.addListener(new AudioPlaylistTipsDelegate$longTapSeek$$inlined$doOnEnd$1(animatorSet));
        animatorSet.start();
        return animatorSet;
    }

    public final void next() {
        AudioPlaylistTipsStep audioPlaylistTipsStep;
        int i;
        if (this.currentTip == AudioPlaylistTipsStep.SEEK) {
            close();
            return;
        }
        AudioPlaylistTipsStep audioPlaylistTipsStep2 = this.currentTip;
        if (audioPlaylistTipsStep2 == null || (audioPlaylistTipsStep = audioPlaylistTipsStep2.next()) == null) {
            audioPlaylistTipsStep = AudioPlaylistTipsStep.REMOVE;
        }
        this.currentTip = audioPlaylistTipsStep;
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintSet constraintSet2 = this.initialConstraintSet;
        TextView textView = null;
        if (constraintSet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialConstraintSet");
            constraintSet2 = null;
        }
        constraintSet.clone(constraintSet2);
        ConstraintLayout constraintLayout = this.audioPlaylistTips;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlaylistTips");
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
        constraintSet.setGuidelinePercent(R.id.middleGuideline, this.middleGuidelineEndBound);
        AudioPlaylistTipsStep audioPlaylistTipsStep3 = this.currentTip;
        int i2 = audioPlaylistTipsStep3 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[audioPlaylistTipsStep3.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                PlaylistItemBinding playlistItemBinding = this.secondItemBinding;
                if (playlistItemBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("secondItemBinding");
                    playlistItemBinding = null;
                }
                playlistItemBinding.itemContainer.setTranslationX(0.0f);
                PlaylistItemBinding playlistItemBinding2 = this.thirdItemBinding;
                if (playlistItemBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                    playlistItemBinding2 = null;
                }
                playlistItemBinding2.setMasked(false);
                PlaylistItemBinding playlistItemBinding3 = this.thirdItemBinding;
                if (playlistItemBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                    playlistItemBinding3 = null;
                }
                playlistItemBinding3.playing.start();
                PlaylistItemBinding playlistItemBinding4 = this.thirdItemBinding;
                if (playlistItemBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                    playlistItemBinding4 = null;
                }
                playlistItemBinding4.itemContainer.setBackgroundColor(getItemColor());
                constraintSet.setVisibility(R.id.tapIndicatorRearrange, 0);
                PlaylistItemBinding playlistItemBinding5 = this.thirdItemBinding;
                if (playlistItemBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                    playlistItemBinding5 = null;
                }
                int top = playlistItemBinding5.itemContainer.getTop();
                PlaylistItemBinding playlistItemBinding6 = this.thirdItemBinding;
                if (playlistItemBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                    playlistItemBinding6 = null;
                }
                constraintSet.setMargin(R.id.tapIndicatorRearrange, 3, (top + (playlistItemBinding6.itemContainer.getHeight() / 2)) - KotlinExtensionsKt.getDp(24));
                this.currentAnimations.clear();
                if (UiTools.INSTANCE.isTablet(this.activity)) {
                    PlaylistItemBinding playlistItemBinding7 = this.thirdItemBinding;
                    if (playlistItemBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                        playlistItemBinding7 = null;
                    }
                    int left = playlistItemBinding7.itemMoveUp.getLeft();
                    PlaylistItemBinding playlistItemBinding8 = this.thirdItemBinding;
                    if (playlistItemBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                        playlistItemBinding8 = null;
                    }
                    constraintSet.setMargin(R.id.tapIndicatorRearrange, 6, (left + (playlistItemBinding8.itemMoveUp.getWidth() / 2)) - KotlinExtensionsKt.getDp(24));
                    constraintSet.clear(R.id.tapIndicatorRearrange, 7);
                    TipsUtils tipsUtils = TipsUtils.INSTANCE;
                    View view = this.tapIndicatorRearrange;
                    if (view == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorRearrange");
                        view = null;
                    }
                    TipsUtils.startTapAnimation$default(tipsUtils, CollectionsKt.listOf(view), false, 2, (Object) null);
                } else {
                    ArrayList<Animator> arrayList = this.currentAnimations;
                    View view2 = this.tapIndicatorRearrange;
                    if (view2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorRearrange");
                        view2 = null;
                    }
                    PlaylistItemBinding playlistItemBinding9 = this.thirdItemBinding;
                    if (playlistItemBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("thirdItemBinding");
                        playlistItemBinding9 = null;
                    }
                    ConstraintLayout constraintLayout2 = playlistItemBinding9.itemContainer;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout2, "itemContainer");
                    arrayList.add(dragAndDrop(view2, constraintLayout2));
                }
            } else if (i2 == 3) {
                LinearLayout linearLayout = this.tracksContainer;
                if (linearLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tracksContainer");
                    linearLayout = null;
                }
                linearLayout.removeAllViews();
                constraintSet.setVisibility(R.id.pl_tips_next, 0);
                constraintSet.setVisibility(R.id.pl_tips_play_pause, 0);
                constraintSet.setVisibility(R.id.pl_tips_previous, 0);
                constraintSet.setVisibility(R.id.pl_tips_repeat, 0);
                constraintSet.setVisibility(R.id.pl_tips_shuffle, 0);
                constraintSet.setVisibility(R.id.plTipsTimeline, 0);
                constraintSet.setVisibility(R.id.tapIndicatorRewind, 0);
                constraintSet.setVisibility(R.id.tapIndicatorForward, 0);
                constraintSet.connect(R.id.helpTitle, 4, R.id.guideline8, 3, KotlinExtensionsKt.getDp(72));
                this.currentAnimations.clear();
                ArrayList<Animator> arrayList2 = this.currentAnimations;
                View view3 = this.tapIndicatorRewind;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorRewind");
                    view3 = null;
                }
                View view4 = this.tapIndicatorForward;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorForward");
                    view4 = null;
                }
                SeekBar seekBar = this.plTipsTimeline;
                if (seekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("plTipsTimeline");
                    seekBar = null;
                }
                arrayList2.add(longTapSeek(view3, view4, seekBar));
                Button button2 = this.nextButton;
                if (button2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nextButton");
                    button2 = null;
                }
                button2.setText(R.string.close);
            }
        } else if (UiTools.INSTANCE.isTablet(this.activity)) {
            PlaylistItemBinding playlistItemBinding10 = this.secondItemBinding;
            if (playlistItemBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("secondItemBinding");
                playlistItemBinding10 = null;
            }
            int top2 = playlistItemBinding10.itemContainer.getTop();
            PlaylistItemBinding playlistItemBinding11 = this.secondItemBinding;
            if (playlistItemBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("secondItemBinding");
                playlistItemBinding11 = null;
            }
            constraintSet.setMargin(R.id.tapIndicatorRearrange, 3, (top2 + (playlistItemBinding11.itemContainer.getHeight() / 2)) - KotlinExtensionsKt.getDp(24));
            this.currentAnimations.clear();
            constraintSet.setVisibility(R.id.tapIndicatorRearrange, 0);
            PlaylistItemBinding playlistItemBinding12 = this.secondItemBinding;
            if (playlistItemBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("secondItemBinding");
                playlistItemBinding12 = null;
            }
            int left2 = playlistItemBinding12.itemDelete.getLeft();
            PlaylistItemBinding playlistItemBinding13 = this.secondItemBinding;
            if (playlistItemBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("secondItemBinding");
                playlistItemBinding13 = null;
            }
            constraintSet.setMargin(R.id.tapIndicatorRearrange, 6, (left2 + (playlistItemBinding13.itemDelete.getWidth() / 2)) - KotlinExtensionsKt.getDp(24));
            constraintSet.clear(R.id.tapIndicatorRearrange, 7);
            TipsUtils tipsUtils2 = TipsUtils.INSTANCE;
            View view5 = this.tapIndicatorRearrange;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tapIndicatorRearrange");
                view5 = null;
            }
            TipsUtils.startTapAnimation$default(tipsUtils2, CollectionsKt.listOf(view5), false, 2, (Object) null);
        } else {
            constraintSet.setVisibility(R.id.tap_gesture_horizontal_background, 0);
            constraintSet.setVisibility(R.id.tapGestureHorizontal, 0);
            this.currentAnimations.clear();
            ArrayList<Animator> arrayList3 = this.currentAnimations;
            TipsUtils tipsUtils3 = TipsUtils.INSTANCE;
            View view6 = this.tapGestureHorizontal;
            if (view6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tapGestureHorizontal");
                view6 = null;
            }
            arrayList3.add(tipsUtils3.horizontalSwipe(view6, new AudioPlaylistTipsDelegate$next$1(this)));
        }
        ConstraintLayout constraintLayout3 = this.audioPlaylistTips;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlaylistTips");
            constraintLayout3 = null;
        }
        constraintSet.applyTo(constraintLayout3);
        TextView textView2 = this.helpTitle;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("helpTitle");
            textView2 = null;
        }
        AudioPlaylistTipsStep audioPlaylistTipsStep4 = this.currentTip;
        Intrinsics.checkNotNull(audioPlaylistTipsStep4);
        textView2.setText(audioPlaylistTipsStep4.getTitleText());
        TextView textView3 = this.helpDescription;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("helpDescription");
        } else {
            textView = textView3;
        }
        if (UiTools.INSTANCE.isTablet(this.activity)) {
            AudioPlaylistTipsStep audioPlaylistTipsStep5 = this.currentTip;
            Intrinsics.checkNotNull(audioPlaylistTipsStep5);
            i = audioPlaylistTipsStep5.getDescriptionTextTablet();
        } else {
            AudioPlaylistTipsStep audioPlaylistTipsStep6 = this.currentTip;
            Intrinsics.checkNotNull(audioPlaylistTipsStep6);
            i = audioPlaylistTipsStep6.getDescriptionText();
        }
        textView.setText(i);
    }

    public final void close() {
        clearAllAnimations();
        ConstraintLayout constraintLayout = this.audioPlaylistTips;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlaylistTips");
            constraintLayout = null;
        }
        KotlinExtensionsKt.setGone(constraintLayout);
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.activity), SettingsKt.PREF_PLAYLIST_TIPS_SHOWN, true);
        this.currentTip = null;
        PlaybackService service = this.activity.getAudioPlayer().getPlaylistModel().getService();
        if (service != null) {
            service.play();
        }
        this.activity.getShownTips().add(Integer.valueOf(R.id.audio_playlist_tips));
        this.activity.getPlayerBehavior().lock(false);
    }

    private final void clearAllAnimations() {
        for (Animator animator : this.currentAnimations) {
            animator.removeAllListeners();
            animator.cancel();
        }
    }
}
