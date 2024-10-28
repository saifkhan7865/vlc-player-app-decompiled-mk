package org.videolan.television.ui;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.R;
import org.videolan.television.databinding.SongBrowserBinding;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001:B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\r\u0010!\u001a\u00020\"H\u0000¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\"H\u0000¢\u0006\u0002\b%J\r\u0010&\u001a\u00020\"H\u0000¢\u0006\u0002\b'J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)J\u0018\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020)H\u0016J \u0010/\u001a\u00020\"2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000203H\u0016J\u0016\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020-2\u0006\u00107\u001a\u000203J\r\u00108\u001a\u00020\"H\u0000¢\u0006\u0002\b9R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "Landroid/view/View$OnFocusChangeListener;", "binding", "Lorg/videolan/television/databinding/SongBrowserBinding;", "cl", "Landroidx/constraintlayout/widget/ConstraintLayout;", "(Lorg/videolan/television/databinding/SongBrowserBinding;Landroidx/constraintlayout/widget/ConstraintLayout;)V", "getBinding", "()Lorg/videolan/television/databinding/SongBrowserBinding;", "constraintSets", "", "Landroidx/constraintlayout/widget/ConstraintSet;", "[Landroidx/constraintlayout/widget/ConstraintSet;", "value", "Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate$MediaBrowserState;", "currenstate", "setCurrenstate", "(Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate$MediaBrowserState;)V", "fabDisplay", "Landroidx/appcompat/widget/AppCompatImageButton;", "fabFavorite", "fabHeader", "fabSettings", "fabSort", "fakeToolbar", "Landroidx/constraintlayout/widget/Group;", "headerVisibleConstraintSet", "scrolledDownFABCollapsedConstraintSet", "scrolledDownFABExpandedConstraintSet", "scrolledUpConstraintSet", "transition", "Landroidx/transition/ChangeBounds;", "collapseExtendedFAB", "", "collapseExtendedFAB$television_release", "expandExtendedFAB", "expandExtendedFAB$television_release", "hideFAB", "hideFAB$television_release", "isFABExpanded", "", "isScrolled", "onFocusChange", "v", "Landroid/view/View;", "hasFocus", "onScrolled", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "setVisibility", "view", "visibility", "showFAB", "showFAB$television_release", "MediaBrowserState", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserAnimatorDelegate.kt */
public final class MediaBrowserAnimatorDelegate extends RecyclerView.OnScrollListener implements View.OnFocusChangeListener {
    private final SongBrowserBinding binding;
    private final ConstraintLayout cl;
    private final ConstraintSet[] constraintSets;
    /* access modifiers changed from: private */
    public MediaBrowserState currenstate = MediaBrowserState.SCROLLED_UP;
    private final AppCompatImageButton fabDisplay;
    private final AppCompatImageButton fabFavorite;
    private final AppCompatImageButton fabHeader;
    private final AppCompatImageButton fabSettings;
    private final AppCompatImageButton fabSort;
    /* access modifiers changed from: private */
    public final Group fakeToolbar;
    private final ConstraintSet headerVisibleConstraintSet;
    private final ConstraintSet scrolledDownFABCollapsedConstraintSet;
    private final ConstraintSet scrolledDownFABExpandedConstraintSet;
    private final ConstraintSet scrolledUpConstraintSet;
    private final ChangeBounds transition;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaBrowserAnimatorDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                org.videolan.television.ui.MediaBrowserAnimatorDelegate$MediaBrowserState[] r0 = org.videolan.television.ui.MediaBrowserAnimatorDelegate.MediaBrowserState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.television.ui.MediaBrowserAnimatorDelegate$MediaBrowserState r1 = org.videolan.television.ui.MediaBrowserAnimatorDelegate.MediaBrowserState.SCROLLED_UP     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.television.ui.MediaBrowserAnimatorDelegate$MediaBrowserState r1 = org.videolan.television.ui.MediaBrowserAnimatorDelegate.MediaBrowserState.SCROLLED_DOWN_FAB_COLLAPSED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.television.ui.MediaBrowserAnimatorDelegate$MediaBrowserState r1 = org.videolan.television.ui.MediaBrowserAnimatorDelegate.MediaBrowserState.SCROLLED_DOWN_FAB_EXPANDED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.television.ui.MediaBrowserAnimatorDelegate$MediaBrowserState r1 = org.videolan.television.ui.MediaBrowserAnimatorDelegate.MediaBrowserState.HEADER_VISIBLE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaBrowserAnimatorDelegate.WhenMappings.<clinit>():void");
        }
    }

    public MediaBrowserAnimatorDelegate(SongBrowserBinding songBrowserBinding, ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(songBrowserBinding, "binding");
        Intrinsics.checkNotNullParameter(constraintLayout, "cl");
        this.binding = songBrowserBinding;
        this.cl = constraintLayout;
        ConstraintSet constraintSet = new ConstraintSet();
        this.scrolledUpConstraintSet = constraintSet;
        ConstraintSet constraintSet2 = new ConstraintSet();
        this.scrolledDownFABCollapsedConstraintSet = constraintSet2;
        ConstraintSet constraintSet3 = new ConstraintSet();
        this.scrolledDownFABExpandedConstraintSet = constraintSet3;
        ConstraintSet constraintSet4 = new ConstraintSet();
        this.headerVisibleConstraintSet = constraintSet4;
        this.constraintSets = new ConstraintSet[]{constraintSet, constraintSet2, constraintSet3, constraintSet4};
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new AccelerateDecelerateInterpolator());
        changeBounds.setDuration(300);
        this.transition = changeBounds;
        Group group = songBrowserBinding.toolbar;
        Intrinsics.checkNotNullExpressionValue(group, "toolbar");
        this.fakeToolbar = group;
        AppCompatImageButton appCompatImageButton = songBrowserBinding.imageButtonSettings;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton, "imageButtonSettings");
        this.fabSettings = appCompatImageButton;
        AppCompatImageButton appCompatImageButton2 = songBrowserBinding.imageButtonHeader;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton2, "imageButtonHeader");
        this.fabHeader = appCompatImageButton2;
        AppCompatImageButton appCompatImageButton3 = songBrowserBinding.imageButtonFavorite;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton3, "imageButtonFavorite");
        this.fabFavorite = appCompatImageButton3;
        AppCompatImageButton appCompatImageButton4 = songBrowserBinding.imageButtonSort;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton4, "imageButtonSort");
        this.fabSort = appCompatImageButton4;
        AppCompatImageButton appCompatImageButton5 = songBrowserBinding.imageButtonDisplay;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton5, "imageButtonDisplay");
        this.fabDisplay = appCompatImageButton5;
        constraintSet.clone(constraintLayout);
        constraintSet2.clone(constraintLayout);
        constraintSet2.setMargin(R.id.sortButton, 6, 0);
        constraintSet2.setMargin(R.id.sortButton, 7, 0);
        constraintSet2.setMargin(R.id.sortButton, 3, 0);
        constraintSet2.setMargin(R.id.sortButton, 4, 0);
        constraintSet2.setMargin(R.id.displayButton, 6, 0);
        constraintSet2.setMargin(R.id.displayButton, 7, 0);
        constraintSet2.setMargin(R.id.displayButton, 3, 0);
        constraintSet2.setMargin(R.id.displayButton, 4, 0);
        constraintSet2.setMargin(R.id.headerButton, 6, 0);
        constraintSet2.setMargin(R.id.headerButton, 7, 0);
        constraintSet2.setMargin(R.id.headerButton, 3, 0);
        constraintSet2.setMargin(R.id.headerButton, 4, 0);
        constraintSet2.setMargin(R.id.favoriteButton, 6, 0);
        constraintSet2.setMargin(R.id.favoriteButton, 7, 0);
        constraintSet2.setMargin(R.id.favoriteButton, 3, 0);
        constraintSet2.setMargin(R.id.favoriteButton, 4, 0);
        constraintSet2.connect(R.id.sortButton, 6, R.id.imageButtonSettings, 6);
        constraintSet2.connect(R.id.sortButton, 7, R.id.imageButtonSettings, 7);
        constraintSet2.connect(R.id.sortButton, 3, R.id.imageButtonSettings, 3);
        constraintSet2.connect(R.id.sortButton, 4, R.id.imageButtonSettings, 4);
        constraintSet2.connect(R.id.displayButton, 6, R.id.imageButtonSettings, 6);
        constraintSet2.connect(R.id.displayButton, 7, R.id.imageButtonSettings, 7);
        constraintSet2.connect(R.id.displayButton, 3, R.id.imageButtonSettings, 3);
        constraintSet2.connect(R.id.displayButton, 4, R.id.imageButtonSettings, 4);
        constraintSet2.connect(R.id.headerButton, 6, R.id.imageButtonSettings, 6);
        constraintSet2.connect(R.id.headerButton, 7, R.id.imageButtonSettings, 7);
        constraintSet2.connect(R.id.headerButton, 3, R.id.imageButtonSettings, 3);
        constraintSet2.connect(R.id.headerButton, 4, R.id.imageButtonSettings, 4);
        constraintSet2.connect(R.id.favoriteButton, 6, R.id.imageButtonSettings, 6);
        constraintSet2.connect(R.id.favoriteButton, 7, R.id.imageButtonSettings, 7);
        constraintSet2.connect(R.id.favoriteButton, 3, R.id.imageButtonSettings, 3);
        constraintSet2.connect(R.id.favoriteButton, 4, R.id.imageButtonSettings, 4);
        constraintSet2.clear(R.id.sortDescription, 6);
        constraintSet2.connect(R.id.sortDescription, 7, R.id.imageButtonSort, 6);
        constraintSet2.connect(R.id.sortDescription, 3, R.id.imageButtonSort, 3);
        constraintSet2.connect(R.id.sortDescription, 4, R.id.imageButtonSort, 4);
        constraintSet2.clear(R.id.displayDescription, 6);
        constraintSet2.connect(R.id.displayDescription, 7, R.id.imageButtonDisplay, 6);
        constraintSet2.connect(R.id.displayDescription, 3, R.id.imageButtonDisplay, 3);
        constraintSet2.connect(R.id.displayDescription, 4, R.id.imageButtonDisplay, 4);
        constraintSet2.clear(R.id.headerDescription, 6);
        constraintSet2.connect(R.id.headerDescription, 7, R.id.imageButtonHeader, 6);
        constraintSet2.connect(R.id.headerDescription, 3, R.id.imageButtonHeader, 3);
        constraintSet2.connect(R.id.headerDescription, 4, R.id.imageButtonHeader, 4);
        constraintSet2.clear(R.id.favoriteDescription, 6);
        constraintSet2.connect(R.id.favoriteDescription, 7, R.id.imageButtonFavorite, 6);
        constraintSet2.connect(R.id.favoriteDescription, 3, R.id.imageButtonFavorite, 3);
        constraintSet2.connect(R.id.favoriteDescription, 4, R.id.imageButtonFavorite, 4);
        constraintSet2.connect(R.id.title, 4, 0, 3);
        constraintSet2.connect(R.id.ariane, 4, 0, 3);
        constraintSet2.clear(R.id.ariane, 3);
        constraintSet2.connect(R.id.imageButtonSettings, 4, 0, 4);
        constraintSet2.clear(R.id.imageButtonSettings, 3);
        constraintSet3.clone(constraintSet2);
        constraintSet3.clear(R.id.imageButtonHeader, 3);
        constraintSet3.clear(R.id.imageButtonSort, 3);
        constraintSet3.clear(R.id.imageButtonDisplay, 3);
        constraintSet3.clear(R.id.imageButtonFavorite, 3);
        constraintSet3.connect(R.id.imageButtonHeader, 4, R.id.imageButtonSettings, 3);
        constraintSet3.connect(R.id.imageButtonSort, 4, R.id.imageButtonHeader, 3);
        constraintSet3.connect(R.id.imageButtonFavorite, 4, R.id.imageButtonSort, 3);
        constraintSet3.connect(R.id.imageButtonDisplay, 4, R.id.imageButtonFavorite, 3);
        constraintSet3.setAlpha(R.id.displayDescription, 1.0f);
        constraintSet3.setAlpha(R.id.sortDescription, 1.0f);
        constraintSet3.setAlpha(R.id.headerDescription, 1.0f);
        constraintSet3.setAlpha(R.id.favoriteDescription, 1.0f);
        constraintSet4.clone(constraintSet2);
        constraintSet4.setVisibility(R.id.headerListContainer, 0);
        constraintSet4.setVisibility(R.id.list, 8);
        constraintSet4.clear(R.id.imageButtonSettings, 4);
        constraintSet4.connect(R.id.imageButtonSettings, 3, R.id.headerListContainer, 4);
        changeBounds.excludeTarget((Class<?>) ProgressBar.class, true);
        changeBounds.addListener(new Transition.TransitionListener(this) {
            final /* synthetic */ MediaBrowserAnimatorDelegate this$0;

            public void onTransitionCancel(Transition transition) {
                Intrinsics.checkNotNullParameter(transition, "transition");
            }

            public void onTransitionPause(Transition transition) {
                Intrinsics.checkNotNullParameter(transition, "transition");
            }

            public void onTransitionResume(Transition transition) {
                Intrinsics.checkNotNullParameter(transition, "transition");
            }

            public void onTransitionStart(Transition transition) {
                Intrinsics.checkNotNullParameter(transition, "transition");
            }

            {
                this.this$0 = r1;
            }

            public void onTransitionEnd(Transition transition) {
                Intrinsics.checkNotNullParameter(transition, "transition");
                if (this.this$0.currenstate == MediaBrowserState.SCROLLED_UP) {
                    this.this$0.fakeToolbar.setVisibility(0);
                } else {
                    this.this$0.fakeToolbar.setVisibility(8);
                }
                if (this.this$0.currenstate == MediaBrowserState.HEADER_VISIBLE) {
                    this.this$0.getBinding().headerList.requestFocus();
                }
            }
        });
        appCompatImageButton.setOnClickListener(new MediaBrowserAnimatorDelegate$$ExternalSyntheticLambda0(this));
    }

    public final SongBrowserBinding getBinding() {
        return this.binding;
    }

    private final void setCurrenstate(MediaBrowserState mediaBrowserState) {
        ConstraintSet constraintSet;
        if (mediaBrowserState != this.currenstate) {
            TransitionManager.beginDelayedTransition(this.cl, this.transition);
            int i = WhenMappings.$EnumSwitchMapping$0[mediaBrowserState.ordinal()];
            if (i == 1) {
                constraintSet = this.scrolledUpConstraintSet;
            } else if (i == 2) {
                constraintSet = this.scrolledDownFABCollapsedConstraintSet;
            } else if (i == 3) {
                constraintSet = this.scrolledDownFABExpandedConstraintSet;
            } else if (i == 4) {
                constraintSet = this.headerVisibleConstraintSet;
            } else {
                throw new NoWhenBranchMatchedException();
            }
            constraintSet.applyTo(this.cl);
            this.currenstate = mediaBrowserState;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate$MediaBrowserState;", "", "(Ljava/lang/String;I)V", "SCROLLED_UP", "SCROLLED_DOWN_FAB_COLLAPSED", "SCROLLED_DOWN_FAB_EXPANDED", "HEADER_VISIBLE", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaBrowserAnimatorDelegate.kt */
    public enum MediaBrowserState {
        SCROLLED_UP,
        SCROLLED_DOWN_FAB_COLLAPSED,
        SCROLLED_DOWN_FAB_EXPANDED,
        HEADER_VISIBLE;

        public static EnumEntries<MediaBrowserState> getEntries() {
            return $ENTRIES;
        }

        static {
            MediaBrowserState[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        MediaBrowserState mediaBrowserState;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i, i2);
        if (recyclerView.computeVerticalScrollOffset() > 0) {
            mediaBrowserState = MediaBrowserState.SCROLLED_DOWN_FAB_COLLAPSED;
        } else {
            mediaBrowserState = MediaBrowserState.SCROLLED_UP;
        }
        setCurrenstate(mediaBrowserState);
    }

    public void onFocusChange(View view, boolean z) {
        TextView textView;
        ViewPropertyAnimator animate;
        ViewPropertyAnimator animate2;
        Intrinsics.checkNotNullParameter(view, "v");
        if (Intrinsics.areEqual((Object) view, (Object) this.binding.displayButton)) {
            textView = this.binding.displayDescription;
        } else if (Intrinsics.areEqual((Object) view, (Object) this.binding.headerButton)) {
            textView = this.binding.headerDescription;
        } else if (Intrinsics.areEqual((Object) view, (Object) this.binding.sortButton)) {
            textView = this.binding.sortDescription;
        } else {
            textView = Intrinsics.areEqual((Object) view, (Object) this.binding.favoriteButton) ? this.binding.favoriteDescription : null;
        }
        if (!(textView == null || (animate2 = textView.animate()) == null)) {
            animate2.cancel();
        }
        if (!(textView == null || (animate = textView.animate()) == null)) {
            animate.alpha(z ? 1.0f : 0.0f);
        }
        if (this.currenstate != MediaBrowserState.SCROLLED_UP) {
            if (!this.fabSettings.hasFocus() && !this.fabSort.hasFocus() && !this.fabDisplay.hasFocus() && !this.fabDisplay.hasFocus() && !this.fabHeader.hasFocus() && !this.fabFavorite.hasFocus() && this.currenstate != MediaBrowserState.HEADER_VISIBLE) {
                collapseExtendedFAB$television_release();
            }
            if (Intrinsics.areEqual((Object) view, (Object) this.fabSettings) && z) {
                expandExtendedFAB$television_release();
            }
        }
    }

    public final boolean isFABExpanded() {
        return this.currenstate == MediaBrowserState.SCROLLED_DOWN_FAB_EXPANDED;
    }

    public final void expandExtendedFAB$television_release() {
        setCurrenstate(MediaBrowserState.SCROLLED_DOWN_FAB_EXPANDED);
    }

    public final void collapseExtendedFAB$television_release() {
        setCurrenstate(MediaBrowserState.SCROLLED_DOWN_FAB_COLLAPSED);
    }

    public final void hideFAB$television_release() {
        setCurrenstate(MediaBrowserState.HEADER_VISIBLE);
    }

    public final void showFAB$television_release() {
        setCurrenstate(MediaBrowserState.SCROLLED_DOWN_FAB_COLLAPSED);
    }

    public final void setVisibility(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "view");
        for (ConstraintSet visibility : this.constraintSets) {
            visibility.setVisibility(view.getId(), i);
        }
        view.setVisibility(i);
    }

    public final boolean isScrolled() {
        return this.currenstate == MediaBrowserState.SCROLLED_DOWN_FAB_COLLAPSED;
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$2(MediaBrowserAnimatorDelegate mediaBrowserAnimatorDelegate, View view) {
        Intrinsics.checkNotNullParameter(mediaBrowserAnimatorDelegate, "this$0");
        mediaBrowserAnimatorDelegate.expandExtendedFAB$television_release();
    }
}
