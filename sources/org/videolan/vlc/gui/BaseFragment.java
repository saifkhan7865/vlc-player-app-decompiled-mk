package org.videolan.vlc.gui;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.FloatingActionButtonBehavior;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u001fJ\b\u00103\u001a\u00020\u001fH&J\b\u00104\u001a\u00020\u0011H\u0014J\u0006\u00105\u001a\u00020-J\u0006\u00106\u001a\u00020-J\u0012\u00107\u001a\u00020-2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0010\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020<H\u0016J\u0018\u0010=\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010?\u001a\u00020-H\u0016J\b\u0010@\u001a\u00020-H\u0016J\u001a\u0010A\u001a\u00020-2\u0006\u0010;\u001a\u00020<2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0010\u0010B\u001a\u00020-2\u0006\u0010C\u001a\u00020\u0011H\u0016J7\u0010D\u001a\u00020-2\u0006\u0010E\u001a\u00020\u00112%\b\u0002\u0010F\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020-\u0018\u00010GH\u0004J\u0010\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020MH\u0004J\u0006\u0010N\u001a\u00020-J\u0006\u0010O\u001a\u00020-J\b\u0010P\u001a\u00020-H\u0002J\b\u0010Q\u001a\u00020-H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0011XD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006R"}, d2 = {"Lorg/videolan/vlc/gui/BaseFragment;", "Landroidx/fragment/app/Fragment;", "Landroidx/appcompat/view/ActionMode$Callback;", "()V", "actionMode", "Landroidx/appcompat/view/ActionMode;", "getActionMode", "()Landroidx/appcompat/view/ActionMode;", "setActionMode", "(Landroidx/appcompat/view/ActionMode;)V", "fabPlay", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "getFabPlay", "()Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "setFabPlay", "(Lcom/google/android/material/floatingactionbutton/FloatingActionButton;)V", "hasTabs", "", "getHasTabs", "()Z", "isMainNavigationPoint", "menu", "Landroid/view/Menu;", "getMenu", "()Landroid/view/Menu;", "value", "Lkotlinx/coroutines/Job;", "refreshJob", "setRefreshJob", "(Lkotlinx/coroutines/Job;)V", "subTitle", "", "getSubTitle", "()Ljava/lang/String;", "swipeFilter", "Landroid/view/View$OnTouchListener;", "getSwipeFilter", "()Landroid/view/View$OnTouchListener;", "swipeRefreshLayout", "Lorg/videolan/vlc/gui/view/SwipeRefreshLayout;", "getSwipeRefreshLayout", "()Lorg/videolan/vlc/gui/view/SwipeRefreshLayout;", "setSwipeRefreshLayout", "(Lorg/videolan/vlc/gui/view/SwipeRefreshLayout;)V", "browse", "", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "scanned", "next", "backstackName", "getTitle", "hasFAB", "invalidateActionMode", "manageFabNeverShow", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFabPlayClick", "view", "Landroid/view/View;", "onPrepareActionMode", "mode", "onStart", "onStop", "onViewCreated", "setFabPlayVisibility", "enable", "setRefreshing", "refreshing", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "loading", "showInfoDialog", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "startActionMode", "stopActionMode", "updateActionBar", "updateFabPlayView", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseFragment.kt */
public abstract class BaseFragment extends Fragment implements ActionMode.Callback {
    private ActionMode actionMode;
    private FloatingActionButton fabPlay;
    private final boolean hasTabs;
    private final boolean isMainNavigationPoint = true;
    private Job refreshJob;
    private final View.OnTouchListener swipeFilter = new BaseFragment$$ExternalSyntheticLambda1(this);
    public SwipeRefreshLayout swipeRefreshLayout;

    public String getSubTitle() {
        return null;
    }

    public abstract String getTitle();

    public void onFabPlayClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public boolean onPrepareActionMode(ActionMode actionMode2, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode2, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    public final ActionMode getActionMode() {
        return this.actionMode;
    }

    public final void setActionMode(ActionMode actionMode2) {
        this.actionMode = actionMode2;
    }

    public final FloatingActionButton getFabPlay() {
        return this.fabPlay;
    }

    public final void setFabPlay(FloatingActionButton floatingActionButton) {
        this.fabPlay = floatingActionButton;
    }

    public final SwipeRefreshLayout getSwipeRefreshLayout() {
        SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
        if (swipeRefreshLayout2 != null) {
            return swipeRefreshLayout2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
        return null;
    }

    public final void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout2) {
        Intrinsics.checkNotNullParameter(swipeRefreshLayout2, "<set-?>");
        this.swipeRefreshLayout = swipeRefreshLayout2;
    }

    public final View.OnTouchListener getSwipeFilter() {
        return this.swipeFilter;
    }

    /* access modifiers changed from: private */
    public static final boolean swipeFilter$lambda$0(BaseFragment baseFragment, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(baseFragment, "this$0");
        SwipeRefreshLayout swipeRefreshLayout2 = baseFragment.getSwipeRefreshLayout();
        boolean z = true;
        if (motionEvent.getAction() != 1) {
            z = false;
        }
        swipeRefreshLayout2.setEnabled(z);
        return false;
    }

    public boolean getHasTabs() {
        return this.hasTabs;
    }

    private final void setRefreshJob(Job job) {
        Job job2 = this.refreshJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.refreshJob = job;
    }

    public final Menu getMenu() {
        FragmentActivity activity = getActivity();
        AudioPlayerContainerActivity audioPlayerContainerActivity = activity instanceof AudioPlayerContainerActivity ? (AudioPlayerContainerActivity) activity : null;
        if (audioPlayerContainerActivity != null) {
            return audioPlayerContainerActivity.getMenu();
        }
        return null;
    }

    public boolean isMainNavigationPoint() {
        return this.isMainNavigationPoint;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        SwipeRefreshLayout swipeRefreshLayout2 = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        if (swipeRefreshLayout2 != null) {
            setSwipeRefreshLayout(swipeRefreshLayout2);
            TypedArray obtainStyledAttributes = requireActivity().obtainStyledAttributes(new TypedValue().data, new int[]{R.attr.colorPrimary, R.attr.swipe_refresh_background});
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
            int color = obtainStyledAttributes.getColor(0, 0);
            int color2 = obtainStyledAttributes.getColor(1, -1);
            obtainStyledAttributes.recycle();
            swipeRefreshLayout2.setColorSchemeColors(color);
            swipeRefreshLayout2.setProgressBackgroundColorSchemeColor(color2);
        }
        if (isMainNavigationPoint()) {
            manageFabNeverShow();
        }
        updateFabPlayView();
    }

    public final void manageFabNeverShow() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) requireActivity().findViewById(R.id.fab);
        FloatingActionButtonBehavior floatingActionButtonBehavior = null;
        ViewGroup.LayoutParams layoutParams = floatingActionButton != null ? floatingActionButton.getLayoutParams() : null;
        CoordinatorLayout.LayoutParams layoutParams2 = layoutParams instanceof CoordinatorLayout.LayoutParams ? (CoordinatorLayout.LayoutParams) layoutParams : null;
        CoordinatorLayout.Behavior behavior = layoutParams2 != null ? layoutParams2.getBehavior() : null;
        if (behavior instanceof FloatingActionButtonBehavior) {
            floatingActionButtonBehavior = (FloatingActionButtonBehavior) behavior;
        }
        if (floatingActionButtonBehavior != null) {
            floatingActionButtonBehavior.setShouldNeverShow(!hasFAB() && (requireActivity() instanceof MainActivity));
        }
    }

    public void onStart() {
        super.onStart();
        updateActionBar();
        setFabPlayVisibility(hasFAB());
        FloatingActionButton floatingActionButton = this.fabPlay;
        if (floatingActionButton != null) {
            floatingActionButton.setOnClickListener(new BaseFragment$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void onStart$lambda$2(BaseFragment baseFragment, View view) {
        Intrinsics.checkNotNullParameter(baseFragment, "this$0");
        Intrinsics.checkNotNull(view);
        baseFragment.onFabPlayClick(view);
    }

    private final void updateFabPlayView() {
        FloatingActionButton floatingActionButton = this.fabPlay;
        Integer valueOf = floatingActionButton != null ? Integer.valueOf(floatingActionButton.getVisibility()) : null;
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) requireActivity().findViewById(R.id.fab);
        FloatingActionButton floatingActionButton3 = (FloatingActionButton) requireActivity().findViewById(R.id.fab_large);
        KotlinExtensionsKt.setGone(floatingActionButton2);
        KotlinExtensionsKt.setGone(floatingActionButton3);
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (uiTools.isTablet(requireActivity) && (requireActivity() instanceof MainActivity)) {
            floatingActionButton2 = floatingActionButton3;
        }
        this.fabPlay = floatingActionButton2;
        if (valueOf != null) {
            int intValue = valueOf.intValue();
            FloatingActionButton floatingActionButton4 = this.fabPlay;
            if (floatingActionButton4 != null) {
                floatingActionButton4.setVisibility(intValue);
            }
        }
    }

    public void onStop() {
        super.onStop();
        setFabPlayVisibility(false);
    }

    public final void startActionMode() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            this.actionMode = appCompatActivity.startSupportActionMode(this);
            setFabPlayVisibility(false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        if (((org.videolan.vlc.gui.ContentActivity) r2).getDisplayTitle() != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updateActionBar() {
        /*
            r4 = this;
            androidx.fragment.app.Fragment r0 = r4.getParentFragment()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            boolean r1 = r0 instanceof androidx.appcompat.app.AppCompatActivity
            if (r1 == 0) goto L_0x0012
            androidx.appcompat.app.AppCompatActivity r0 = (androidx.appcompat.app.AppCompatActivity) r0
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            if (r0 != 0) goto L_0x0016
            return
        L_0x0016:
            androidx.appcompat.app.ActionBar r1 = r0.getSupportActionBar()
            if (r1 == 0) goto L_0x004e
            androidx.fragment.app.FragmentActivity r2 = r4.requireActivity()
            boolean r2 = r2 instanceof org.videolan.vlc.gui.ContentActivity
            if (r2 == 0) goto L_0x0035
            androidx.fragment.app.FragmentActivity r2 = r4.requireActivity()
            java.lang.String r3 = "null cannot be cast to non-null type org.videolan.vlc.gui.ContentActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)
            org.videolan.vlc.gui.ContentActivity r2 = (org.videolan.vlc.gui.ContentActivity) r2
            boolean r2 = r2.getDisplayTitle()
            if (r2 == 0) goto L_0x0042
        L_0x0035:
            androidx.fragment.app.FragmentActivity r2 = r4.requireActivity()
            java.lang.String r3 = r4.getTitle()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r2.setTitle(r3)
        L_0x0042:
            java.lang.String r2 = r4.getSubTitle()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setSubtitle((java.lang.CharSequence) r2)
            r0.invalidateOptionsMenu()
        L_0x004e:
            boolean r1 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            if (r1 == 0) goto L_0x005b
            org.videolan.vlc.gui.ContentActivity r0 = (org.videolan.vlc.gui.ContentActivity) r0
            boolean r1 = r4.getHasTabs()
            r0.setTabLayoutVisibility(r1)
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.BaseFragment.updateActionBar():void");
    }

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        return this.swipeRefreshLayout != null;
    }

    public void setFabPlayVisibility(boolean z) {
        FloatingActionButton floatingActionButton = this.fabPlay;
        if (floatingActionButton == null) {
            return;
        }
        if (z) {
            floatingActionButton.show();
        } else {
            floatingActionButton.hide();
        }
    }

    /* access modifiers changed from: protected */
    public final void showInfoDialog(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra("ML_ITEM", mediaLibraryItem);
        startActivity(intent);
    }

    public static /* synthetic */ void setRefreshing$default(BaseFragment baseFragment, boolean z, Function1 function1, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                function1 = null;
            }
            baseFragment.setRefreshing(z, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setRefreshing");
    }

    /* access modifiers changed from: protected */
    public final void setRefreshing(boolean z, Function1<? super Boolean, Unit> function1) {
        setRefreshJob(LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new BaseFragment$setRefreshing$1(z, this, function1, (Continuation<? super BaseFragment$setRefreshing$1>) null)));
    }

    public final void stopActionMode() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.finish();
            setFabPlayVisibility(true);
        }
    }

    public final void invalidateActionMode() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.invalidate();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r0 = r0.getSupportFragmentManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void browse(org.videolan.medialibrary.interfaces.media.MediaWrapper r5, boolean r6, androidx.fragment.app.Fragment r7, java.lang.String r8) {
        /*
            r4 = this;
            java.lang.String r0 = "media"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "next"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "backstackName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            if (r0 == 0) goto L_0x0020
            androidx.fragment.app.FragmentManager r0 = r0.getSupportFragmentManager()
            if (r0 == 0) goto L_0x0020
            androidx.fragment.app.FragmentTransaction r0 = r0.beginTransaction()
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            r1 = 2
            kotlin.Pair[] r1 = new kotlin.Pair[r1]
            java.lang.String r2 = "key_media"
            kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r5)
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "key_in_medialib"
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            kotlin.Pair r6 = kotlin.TuplesKt.to(r2, r6)
            r2 = 1
            r1[r2] = r6
            android.os.Bundle r6 = androidx.core.os.BundleKt.bundleOf(r1)
            r7.setArguments(r6)
            if (r0 == 0) goto L_0x004c
            int r6 = org.videolan.vlc.R.id.fragment_placeholder
            java.lang.String r5 = r5.getLocation()
            r0.replace((int) r6, (androidx.fragment.app.Fragment) r7, (java.lang.String) r5)
        L_0x004c:
            if (r0 == 0) goto L_0x0051
            r0.addToBackStack(r8)
        L_0x0051:
            if (r0 == 0) goto L_0x0056
            r0.commit()
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.BaseFragment.browse(org.videolan.medialibrary.interfaces.media.MediaWrapper, boolean, androidx.fragment.app.Fragment, java.lang.String):void");
    }
}
