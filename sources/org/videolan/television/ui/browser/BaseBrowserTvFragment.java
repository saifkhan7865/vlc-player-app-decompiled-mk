package org.videolan.television.ui.browser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BackgroundManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.R;
import org.videolan.television.databinding.SongBrowserBinding;
import org.videolan.television.ui.FocusableRecyclerView;
import org.videolan.television.ui.MediaBrowserAnimatorDelegate;
import org.videolan.television.ui.MediaHeaderAdapter;
import org.videolan.television.ui.TvItemAdapter;
import org.videolan.television.ui.TvUtil;
import org.videolan.television.ui.TvUtilKt;
import org.videolan.television.ui.browser.VerticalGridActivity;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration;
import org.videolan.vlc.interfaces.BrowserFragmentInterface;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.util.RefreshModel;
import org.videolan.vlc.viewmodels.SortableModel;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u00052\u00020\u00062\u00020\u0007B\u0005¢\u0006\u0002\u0010\bJ\b\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020<H\u0002J\b\u0010>\u001a\u00020*H&J\b\u0010?\u001a\u00020,H&J\b\u0010@\u001a\u00020\u001eH&J\b\u0010A\u001a\u00020\u001eH&J\b\u0010B\u001a\u00020<H\u0002J\u0010\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020EH\u0016J\u0012\u0010F\u001a\u00020<2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J&\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010N2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J%\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020J2\u0006\u0010Q\u001a\u00020,2\u0006\u0010R\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u00020<2\u0006\u0010U\u001a\u00020\u001eH\u0016J%\u0010V\u001a\u00020<2\u0006\u0010P\u001a\u00020J2\u0006\u0010Q\u001a\u00020,2\u0006\u0010R\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010SJ\u001d\u0010W\u001a\u00020<2\u0006\u0010P\u001a\u00020J2\u0006\u0010R\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010XJ\u0010\u0010Y\u001a\u00020(2\u0006\u0010Z\u001a\u00020,H\u0016J%\u0010[\u001a\u00020(2\u0006\u0010P\u001a\u00020J2\u0006\u0010Q\u001a\u00020,2\u0006\u0010R\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\\J%\u0010]\u001a\u00020<2\u0006\u0010P\u001a\u00020J2\u0006\u0010Q\u001a\u00020,2\u0006\u0010R\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010SJ\u0010\u0010^\u001a\u00020(2\u0006\u0010R\u001a\u00020_H\u0016J\b\u0010`\u001a\u00020<H\u0016J\b\u0010a\u001a\u00020<H\u0016J\b\u0010b\u001a\u00020<H\u0016J\u0014\u0010c\u001a\u00020<2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030dH\u0016J\u001a\u0010e\u001a\u00020<2\u0006\u0010f\u001a\u00020J2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\u001e\u0010g\u001a\u00020\n2\f\u0010h\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010i\u001a\u00020,H&J\b\u0010j\u001a\u00020<H\u0016J\b\u0010k\u001a\u00020<H\u0002J\b\u0010l\u001a\u00020<H\u0002J\u0010\u0010m\u001a\u00020<2\u0006\u0010P\u001a\u00020JH\u0002J\u0010\u0010n\u001a\u00020<2\u0006\u0010m\u001a\u00020,H\u0002J\u000e\u0010o\u001a\u00020<2\u0006\u0010p\u001a\u00020qJ\u0018\u0010r\u001a\u00020<2\u0010\u0010s\u001a\f\u0012\u0004\u0012\u00020\u001e0tj\u0002`uR\u0018\u0010\t\u001a\u00020\nX¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020(X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u000e¢\u0006\u0002\n\u0000R\u001e\u00100\u001a\u00020(2\u0006\u0010/\u001a\u00020(@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u000e\u00103\u001a\u00020(X\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020,X\u000e¢\u0006\u0002\n\u0000R \u00105\u001a\b\u0012\u0004\u0012\u00028\u000006X.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006v"}, d2 = {"Lorg/videolan/television/ui/browser/BaseBrowserTvFragment;", "T", "Landroidx/fragment/app/Fragment;", "Lorg/videolan/vlc/interfaces/BrowserFragmentInterface;", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Landroid/widget/PopupMenu$OnMenuItemClickListener;", "Lorg/videolan/television/ui/MediaHeaderAdapter$OnHeaderSelected;", "Lorg/videolan/television/ui/browser/VerticalGridActivity$OnKeyPressedListener;", "()V", "adapter", "Lorg/videolan/television/ui/TvItemAdapter;", "getAdapter", "()Lorg/videolan/television/ui/TvItemAdapter;", "setAdapter", "(Lorg/videolan/television/ui/TvItemAdapter;)V", "animationDelegate", "Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate;", "getAnimationDelegate$television_release", "()Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate;", "setAnimationDelegate$television_release", "(Lorg/videolan/television/ui/MediaBrowserAnimatorDelegate;)V", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "binding", "Lorg/videolan/television/databinding/SongBrowserBinding;", "getBinding", "()Lorg/videolan/television/databinding/SongBrowserBinding;", "setBinding", "(Lorg/videolan/television/databinding/SongBrowserBinding;)V", "currentArt", "", "gridLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "headerAdapter", "Lorg/videolan/television/ui/MediaHeaderAdapter;", "getHeaderAdapter", "()Lorg/videolan/television/ui/MediaHeaderAdapter;", "setHeaderAdapter", "(Lorg/videolan/television/ui/MediaHeaderAdapter;)V", "inGrid", "", "lastDpadEventTime", "", "previouslySelectedItem", "", "recyclerSectionItemGridDecoration", "Lorg/videolan/vlc/gui/view/RecyclerSectionItemGridDecoration;", "<set-?>", "restarted", "getRestarted", "()Z", "setFocus", "spacing", "viewModel", "Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;", "getViewModel", "()Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;", "setViewModel", "(Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;)V", "calculateNbColumns", "", "changeDisplayMode", "getCategory", "getColumnNumber", "getDisplayPrefId", "getTitle", "hideHeaderSelectionScreen", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxClick", "v", "position", "item", "(Landroid/view/View;ILjava/lang/Object;)V", "onHeaderSelected", "header", "onImageClick", "onItemFocused", "(Landroid/view/View;Ljava/lang/Object;)V", "onKeyPressed", "keyCode", "onLongClick", "(Landroid/view/View;ILjava/lang/Object;)Z", "onMainActionClick", "onMenuItemClick", "Landroid/view/MenuItem;", "onPause", "onStart", "onStop", "onUpdateFinished", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "onViewCreated", "view", "provideAdapter", "eventsHandler", "itemSize", "refresh", "setupDisplayIcon", "setupLayoutManager", "sort", "sortBy", "submitList", "pagedList", "", "updateHeaders", "it", "Landroidx/collection/SparseArrayCompat;", "Lorg/videolan/resources/util/HeadersIndex;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserTvFragment.kt */
public abstract class BaseBrowserTvFragment<T> extends Fragment implements BrowserFragmentInterface, IEventsHandler<T>, PopupMenu.OnMenuItemClickListener, MediaHeaderAdapter.OnHeaderSelected, VerticalGridActivity.OnKeyPressedListener {
    public MediaBrowserAnimatorDelegate animationDelegate;
    private BackgroundManager backgroundManager;
    public SongBrowserBinding binding;
    private String currentArt;
    private LinearLayoutManager gridLayoutManager;
    public MediaHeaderAdapter headerAdapter;
    private boolean inGrid = true;
    private long lastDpadEventTime;
    /* access modifiers changed from: private */
    public int previouslySelectedItem;
    private RecyclerSectionItemGridDecoration recyclerSectionItemGridDecoration;
    private boolean restarted;
    private boolean setFocus = true;
    private int spacing;
    public TvBrowserModel<T> viewModel;

    public abstract TvItemAdapter getAdapter();

    public abstract long getCategory();

    public abstract int getColumnNumber();

    public abstract String getDisplayPrefId();

    public abstract String getTitle();

    public void onCtxClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onImageClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onMainActionClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
    }

    public abstract TvItemAdapter provideAdapter(IEventsHandler<T> iEventsHandler, int i);

    public abstract void setAdapter(TvItemAdapter tvItemAdapter);

    public final SongBrowserBinding getBinding() {
        SongBrowserBinding songBrowserBinding = this.binding;
        if (songBrowserBinding != null) {
            return songBrowserBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(SongBrowserBinding songBrowserBinding) {
        Intrinsics.checkNotNullParameter(songBrowserBinding, "<set-?>");
        this.binding = songBrowserBinding;
    }

    public final TvBrowserModel<T> getViewModel() {
        TvBrowserModel<T> tvBrowserModel = this.viewModel;
        if (tvBrowserModel != null) {
            return tvBrowserModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(TvBrowserModel<T> tvBrowserModel) {
        Intrinsics.checkNotNullParameter(tvBrowserModel, "<set-?>");
        this.viewModel = tvBrowserModel;
    }

    public final MediaHeaderAdapter getHeaderAdapter() {
        MediaHeaderAdapter mediaHeaderAdapter = this.headerAdapter;
        if (mediaHeaderAdapter != null) {
            return mediaHeaderAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("headerAdapter");
        return null;
    }

    public final void setHeaderAdapter(MediaHeaderAdapter mediaHeaderAdapter) {
        Intrinsics.checkNotNullParameter(mediaHeaderAdapter, "<set-?>");
        this.headerAdapter = mediaHeaderAdapter;
    }

    public final MediaBrowserAnimatorDelegate getAnimationDelegate$television_release() {
        MediaBrowserAnimatorDelegate mediaBrowserAnimatorDelegate = this.animationDelegate;
        if (mediaBrowserAnimatorDelegate != null) {
            return mediaBrowserAnimatorDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("animationDelegate");
        return null;
    }

    public final void setAnimationDelegate$television_release(MediaBrowserAnimatorDelegate mediaBrowserAnimatorDelegate) {
        Intrinsics.checkNotNullParameter(mediaBrowserAnimatorDelegate, "<set-?>");
        this.animationDelegate = mediaBrowserAnimatorDelegate;
    }

    /* access modifiers changed from: protected */
    public final boolean getRestarted() {
        return this.restarted;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        SongBrowserBinding inflate = SongBrowserBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        getBinding().emptyLoading.setState(EmptyLoadingState.NONE);
        return getBinding().getRoot();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BackgroundManager instance = BackgroundManager.getInstance(requireActivity());
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.backgroundManager = instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0230  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onViewCreated(android.view.View r9, android.os.Bundle r10) {
        /*
            r8 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            org.videolan.television.ui.TvUtil r0 = org.videolan.television.ui.TvUtil.INSTANCE
            android.content.Context r1 = r8.requireContext()
            java.lang.String r2 = "requireContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            int r0 = r0.getOverscanHorizontal(r1)
            org.videolan.television.ui.TvUtil r1 = org.videolan.television.ui.TvUtil.INSTANCE
            android.content.Context r3 = r8.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            int r1 = r1.getOverscanVertical(r3)
            org.videolan.television.databinding.SongBrowserBinding r2 = r8.getBinding()
            androidx.recyclerview.widget.RecyclerView r2 = r2.headerList
            org.videolan.television.databinding.SongBrowserBinding r3 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r3 = r3.list
            int r3 = r3.getPaddingLeft()
            int r3 = r3 + r0
            org.videolan.television.databinding.SongBrowserBinding r4 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r4 = r4.list
            int r4 = r4.getPaddingTop()
            int r4 = r4 + r1
            org.videolan.television.databinding.SongBrowserBinding r5 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r5 = r5.list
            int r5 = r5.getPaddingRight()
            int r5 = r5 + r0
            org.videolan.television.databinding.SongBrowserBinding r6 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r6 = r6.list
            int r6 = r6.getPaddingBottom()
            int r6 = r6 + r1
            r2.setPadding(r3, r4, r5, r6)
            org.videolan.television.databinding.SongBrowserBinding r2 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r2 = r2.imageButtonSettings
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            java.lang.String r3 = "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r2 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r2
            int r3 = r2.leftMargin
            int r3 = r3 + r0
            r2.leftMargin = r3
            int r3 = r2.rightMargin
            int r3 = r3 + r0
            r2.rightMargin = r3
            int r0 = r2.topMargin
            int r0 = r0 + r1
            r2.topMargin = r0
            int r0 = r2.bottomMargin
            int r0 = r0 + r1
            r2.bottomMargin = r0
            r8.calculateNbColumns()
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            android.widget.TextView r0 = r0.title
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r1 = r8.getViewModel()
            java.lang.Object r1 = r1.getCurrentItem()
            if (r1 == 0) goto L_0x00b4
            boolean r2 = r1 instanceof org.videolan.medialibrary.media.MediaLibraryItem
            java.lang.String r3 = ""
            if (r2 == 0) goto L_0x00af
            long r4 = r8.getCategory()
            r6 = 1
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x00af
            long r4 = r8.getCategory()
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x00a9
            goto L_0x00af
        L_0x00a9:
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            java.lang.String r3 = r1.getTitle()
        L_0x00af:
            if (r3 == 0) goto L_0x00b4
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x00bb
        L_0x00b4:
            java.lang.String r1 = r8.getTitle()
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
        L_0x00bb:
            r0.setText(r3)
            org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$searchHeaderClick$1 r0 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$searchHeaderClick$1
            r0.<init>(r8)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$displayClick$1 r1 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$displayClick$1
            r1.<init>(r8)
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$sortClick$1 r2 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$sortClick$1
            r2.<init>(r8)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.television.databinding.SongBrowserBinding r3 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r3 = r3.headerButton
            org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda0 r4 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda0
            r4.<init>(r0)
            r3.setOnClickListener(r4)
            org.videolan.television.databinding.SongBrowserBinding r3 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r3 = r3.imageButtonHeader
            org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda1 r4 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda1
            r4.<init>(r0)
            r3.setOnClickListener(r4)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r0 = r0.sortButton
            org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda2 r3 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda2
            r3.<init>(r2)
            r0.setOnClickListener(r3)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r0 = r0.imageButtonSort
            org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda3 r3 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda3
            r3.<init>(r2)
            r0.setOnClickListener(r3)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r0 = r0.displayButton
            org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda4 r2 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda4
            r2.<init>(r1)
            r0.setOnClickListener(r2)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r0 = r0.imageButtonDisplay
            org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda5 r2 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$$ExternalSyntheticLambda5
            r2.<init>(r1)
            r0.setOnClickListener(r2)
            android.content.res.Resources r0 = r8.getResources()
            int r1 = org.videolan.television.R.dimen.kl_small
            int r0 = r0.getDimensionPixelSize(r1)
            r8.spacing = r0
            org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration r0 = new org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration
            android.content.res.Resources r1 = r8.getResources()
            int r2 = org.videolan.television.R.dimen.recycler_section_header_tv_height
            int r2 = r1.getDimensionPixelSize(r2)
            int r4 = r8.spacing
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r1 = r8.getViewModel()
            int r6 = r1.getNbColumns()
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r1 = r8.getViewModel()
            org.videolan.resources.util.HeaderProvider r7 = r1.getProvider()
            r5 = 1
            r1 = r0
            r3 = r4
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.recyclerSectionItemGridDecoration = r0
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            androidx.fragment.app.FragmentActivity r1 = r8.requireActivity()
            java.lang.String r2 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.Object r0 = r0.getInstance(r1)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            java.lang.String r1 = r8.getDisplayPrefId()
            r3 = 1
            boolean r0 = r0.getBoolean(r1, r3)
            r8.inGrid = r0
            r8.setupDisplayIcon()
            r8.setupLayoutManager()
            org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration$Companion r0 = org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration.Companion
            androidx.fragment.app.FragmentActivity r1 = r8.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.app.Activity r1 = (android.app.Activity) r1
            int r1 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r1)
            org.videolan.television.databinding.SongBrowserBinding r2 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r2 = r2.list
            int r2 = r2.getPaddingLeft()
            int r1 = r1 - r2
            org.videolan.television.databinding.SongBrowserBinding r2 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r2 = r2.list
            int r2 = r2.getPaddingRight()
            int r1 = r1 - r2
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r2 = r8.getViewModel()
            int r2 = r2.getNbColumns()
            int r3 = r8.spacing
            int r0 = r0.getItemSize(r1, r2, r3, r3)
            r1 = r8
            org.videolan.vlc.interfaces.IEventsHandler r1 = (org.videolan.vlc.interfaces.IEventsHandler) r1
            org.videolan.television.ui.TvItemAdapter r0 = r8.provideAdapter(r1, r0)
            r8.setAdapter(r0)
            org.videolan.television.ui.TvItemAdapter r0 = r8.getAdapter()
            boolean r1 = r8.inGrid
            r0.displaySwitch(r1)
            org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration r0 = r8.recyclerSectionItemGridDecoration
            if (r0 == 0) goto L_0x01d0
            org.videolan.television.databinding.SongBrowserBinding r1 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r1 = r1.list
            androidx.recyclerview.widget.RecyclerView$ItemDecoration r0 = (androidx.recyclerview.widget.RecyclerView.ItemDecoration) r0
            r1.addItemDecoration(r0)
        L_0x01d0:
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            android.widget.FrameLayout r0 = r0.headerListContainer
            r1 = 8
            r0.setVisibility(r1)
            org.videolan.television.ui.MediaHeaderAdapter r0 = new org.videolan.television.ui.MediaHeaderAdapter
            r1 = r8
            org.videolan.television.ui.MediaHeaderAdapter$OnHeaderSelected r1 = (org.videolan.television.ui.MediaHeaderAdapter.OnHeaderSelected) r1
            r0.<init>(r1)
            r8.setHeaderAdapter(r0)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            androidx.recyclerview.widget.RecyclerView r0 = r0.headerList
            org.videolan.television.ui.MediaHeaderAdapter r1 = r8.getHeaderAdapter()
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = (androidx.recyclerview.widget.RecyclerView.Adapter) r1
            r0.setAdapter(r1)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            androidx.recyclerview.widget.RecyclerView r0 = r0.headerList
            org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$3 r1 = new org.videolan.television.ui.browser.BaseBrowserTvFragment$onViewCreated$3
            r1.<init>()
            androidx.recyclerview.widget.RecyclerView$ItemDecoration r1 = (androidx.recyclerview.widget.RecyclerView.ItemDecoration) r1
            r0.addItemDecoration(r1)
            r0 = r9
            androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
            org.videolan.television.ui.MediaBrowserAnimatorDelegateKt.setAnimator(r8, r0)
            org.videolan.television.databinding.SongBrowserBinding r0 = r8.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r0 = r0.list
            org.videolan.television.ui.TvItemAdapter r1 = r8.getAdapter()
            java.lang.String r2 = "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.Adapter<*>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = (androidx.recyclerview.widget.RecyclerView.Adapter) r1
            r0.setAdapter(r1)
            androidx.leanback.app.BackgroundManager r0 = r8.backgroundManager
            r1 = 0
            java.lang.String r2 = "backgroundManager"
            if (r0 != 0) goto L_0x022a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x022a:
            boolean r0 = r0.isAttached()
            if (r0 != 0) goto L_0x023c
            androidx.leanback.app.BackgroundManager r0 = r8.backgroundManager
            if (r0 != 0) goto L_0x0238
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x0239
        L_0x0238:
            r1 = r0
        L_0x0239:
            r1.attachToView(r9)
        L_0x023c:
            super.onViewCreated(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.BaseBrowserTvFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$4(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    private final void setupLayoutManager() {
        LinearLayoutManager linearLayoutManager = null;
        if (this.inGrid) {
            LinearLayoutManager baseBrowserTvFragment$setupLayoutManager$1 = new BaseBrowserTvFragment$setupLayoutManager$1(this, requireActivity(), getViewModel().getNbColumns());
            this.gridLayoutManager = baseBrowserTvFragment$setupLayoutManager$1;
            GridLayoutManager gridLayoutManager2 = baseBrowserTvFragment$setupLayoutManager$1 instanceof GridLayoutManager ? (GridLayoutManager) baseBrowserTvFragment$setupLayoutManager$1 : null;
            if (gridLayoutManager2 != null) {
                gridLayoutManager2.setSpanSizeLookup(new BaseBrowserTvFragment$setupLayoutManager$2(this));
            }
        } else {
            this.gridLayoutManager = new BaseBrowserTvFragment$setupLayoutManager$3(this, requireActivity());
        }
        RecyclerSectionItemGridDecoration recyclerSectionItemGridDecoration2 = this.recyclerSectionItemGridDecoration;
        if (recyclerSectionItemGridDecoration2 != null) {
            recyclerSectionItemGridDecoration2.setList(!this.inGrid);
        }
        FocusableRecyclerView focusableRecyclerView = getBinding().list;
        LinearLayoutManager linearLayoutManager2 = this.gridLayoutManager;
        if (linearLayoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager");
        } else {
            linearLayoutManager = linearLayoutManager2;
        }
        focusableRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void onPause() {
        RecyclerView.LayoutManager layoutManager = getBinding().list.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        View focusedChild = linearLayoutManager.getFocusedChild();
        this.previouslySelectedItem = focusedChild != null ? linearLayoutManager.getPosition(focusedChild) : 0;
        super.onPause();
    }

    public final void updateHeaders(SparseArrayCompat<String> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "it");
        ArrayList arrayList = new ArrayList();
        int size = sparseArrayCompat.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(sparseArrayCompat.valueAt(i));
        }
        getHeaderAdapter().setItems(arrayList);
        getHeaderAdapter().notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public final void changeDisplayMode() {
        this.inGrid = !this.inGrid;
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), getDisplayPrefId(), Boolean.valueOf(this.inGrid));
        getAdapter().displaySwitch(this.inGrid);
        setupDisplayIcon();
        setupLayoutManager();
    }

    private final void setupDisplayIcon() {
        getBinding().imageButtonDisplay.setImageResource(this.inGrid ? R.drawable.ic_fabtvmini_list : R.drawable.ic_fabtvmini_grid);
        getBinding().displayButton.setImageResource(this.inGrid ? R.drawable.ic_tv_browser_list : R.drawable.ic_tv_browser_grid);
        getBinding().displayDescription.setText(this.inGrid ? R.string.display_in_list : R.string.display_in_grid);
        getBinding().displayButton.setContentDescription(getString(this.inGrid ? R.string.display_in_list : R.string.display_in_grid));
        getBinding().imageButtonDisplay.setContentDescription(getString(this.inGrid ? R.string.display_in_list : R.string.display_in_grid));
    }

    public void onStart() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        BackgroundManager backgroundManager2 = this.backgroundManager;
        if (backgroundManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            backgroundManager2 = null;
        }
        TvUtilKt.clearBackground(requireContext, backgroundManager2);
        super.onStart();
        this.setFocus = true;
    }

    public void onStop() {
        super.onStop();
        this.restarted = true;
    }

    private final void calculateNbColumns() {
        getViewModel().setNbColumns(getColumnNumber());
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        calculateNbColumns();
        LinearLayoutManager linearLayoutManager = this.gridLayoutManager;
        LinearLayoutManager linearLayoutManager2 = null;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager");
            linearLayoutManager = null;
        }
        GridLayoutManager gridLayoutManager2 = linearLayoutManager instanceof GridLayoutManager ? (GridLayoutManager) linearLayoutManager : null;
        if (gridLayoutManager2 != null) {
            gridLayoutManager2.setSpanCount(getViewModel().getNbColumns());
        }
        FocusableRecyclerView focusableRecyclerView = getBinding().list;
        LinearLayoutManager linearLayoutManager3 = this.gridLayoutManager;
        if (linearLayoutManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager");
        } else {
            linearLayoutManager2 = linearLayoutManager3;
        }
        focusableRecyclerView.setLayoutManager(linearLayoutManager2);
    }

    public void refresh() {
        TvBrowserModel viewModel2 = getViewModel();
        Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.vlc.util.RefreshModel");
        ((RefreshModel) viewModel2).refresh();
    }

    public boolean onLongClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (!(t instanceof MediaWrapper)) {
            return true;
        }
        TvUtil tvUtil = TvUtil.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        TvUtil.showMediaDetail$default(tvUtil, requireActivity, (MediaWrapper) t, false, 4, (Object) null);
        return true;
    }

    public void onItemFocused(View view, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
        BackgroundManager backgroundManager2 = null;
        MediaLibraryItem mediaLibraryItem = t instanceof MediaLibraryItem ? (MediaLibraryItem) t : null;
        if (mediaLibraryItem != null && !Intrinsics.areEqual((Object) this.currentArt, (Object) mediaLibraryItem.getArtworkMrl())) {
            this.currentArt = mediaLibraryItem.getArtworkMrl();
            CoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this);
            Context context = view.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            Activity activity = (Activity) context;
            BackgroundManager backgroundManager3 = this.backgroundManager;
            if (backgroundManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            } else {
                backgroundManager2 = backgroundManager3;
            }
            TvUtilKt.updateBackground(lifecycleScope, activity, backgroundManager2, mediaLibraryItem);
        }
    }

    /* access modifiers changed from: private */
    public final void sort(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.sort_options);
        TvBrowserModel viewModel2 = getViewModel();
        Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.SortableModel");
        popupMenu.getMenu().findItem(R.id.ml_menu_sortby_filename).setVisible(((SortableModel) viewModel2).canSortByFileNameName());
        MenuItem findItem = popupMenu.getMenu().findItem(R.id.ml_menu_sortby_length);
        TvBrowserModel viewModel3 = getViewModel();
        Intrinsics.checkNotNull(viewModel3, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.SortableModel");
        findItem.setVisible(((SortableModel) viewModel3).canSortByDuration());
        MenuItem findItem2 = popupMenu.getMenu().findItem(R.id.ml_menu_sortby_insertion_date);
        TvBrowserModel viewModel4 = getViewModel();
        Intrinsics.checkNotNull(viewModel4, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.SortableModel");
        findItem2.setVisible(((SortableModel) viewModel4).canSortByInsertionDate());
        MenuItem findItem3 = popupMenu.getMenu().findItem(R.id.ml_menu_sortby_date);
        TvBrowserModel viewModel5 = getViewModel();
        Intrinsics.checkNotNull(viewModel5, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.SortableModel");
        findItem3.setVisible(((SortableModel) viewModel5).canSortByReleaseDate());
        MenuItem findItem4 = popupMenu.getMenu().findItem(R.id.ml_menu_sortby_last_modified);
        TvBrowserModel viewModel6 = getViewModel();
        Intrinsics.checkNotNull(viewModel6, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.SortableModel");
        findItem4.setVisible(((SortableModel) viewModel6).canSortByLastModified());
        popupMenu.getMenu().findItem(R.id.ml_menu_sortby_number).setVisible(false);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (getAnimationDelegate$television_release().isFABExpanded()) {
            getAnimationDelegate$television_release().collapseExtendedFAB$television_release();
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.ml_menu_sortby_name) {
            sortBy(1);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_filename) {
            sortBy(10);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_length) {
            sortBy(2);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_date) {
            sortBy(5);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_last_modified) {
            sortBy(4);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_artist_name) {
            sortBy(7);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_album_name) {
            sortBy(9);
            return true;
        } else if (itemId != R.id.ml_menu_sortby_number) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            sortBy(6);
            return super.onOptionsItemSelected(menuItem);
        }
    }

    private final void sortBy(int i) {
        TvBrowserModel viewModel2 = getViewModel();
        Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.SortableModel");
        ((SortableModel) viewModel2).sort(i);
    }

    public void onHeaderSelected(String str) {
        Intrinsics.checkNotNullParameter(str, "header");
        hideHeaderSelectionScreen();
        int positionForSectionByName = getViewModel().getProvider().getPositionForSectionByName(str);
        RecyclerView.LayoutManager layoutManager = getBinding().list.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        if (((LinearLayoutManager) layoutManager).findViewByPosition(positionForSectionByName) == null) {
            getAdapter().setFocusNext(positionForSectionByName);
        } else {
            getBinding().list.getChildAt(positionForSectionByName).requestFocus();
        }
        getBinding().list.scrollToPosition(positionForSectionByName);
    }

    private final void hideHeaderSelectionScreen() {
        getBinding().headerListContainer.setVisibility(8);
        getBinding().list.setVisibility(0);
        getAnimationDelegate$television_release().showFAB$television_release();
    }

    public boolean onKeyPressed(int i) {
        if (i != 4) {
            if (i != 82) {
                switch (i) {
                    case 19:
                    case 20:
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.lastDpadEventTime > 200) {
                            this.lastDpadEventTime = currentTimeMillis;
                            return false;
                        }
                        break;
                    case 21:
                    case 22:
                        if (!this.inGrid && getBinding().list.hasFocus() && getAnimationDelegate$television_release().isScrolled()) {
                            getBinding().imageButtonSettings.requestFocusFromTouch();
                            break;
                        } else {
                            return false;
                        }
                    default:
                        return false;
                }
            } else {
                getBinding().imageButtonSettings.requestFocusFromTouch();
                getAnimationDelegate$television_release().expandExtendedFAB$television_release();
            }
        } else if (getBinding().headerListContainer.getVisibility() != 0) {
            return false;
        } else {
            hideHeaderSelectionScreen();
        }
        return true;
    }

    public final void submitList(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "pagedList");
        getAdapter().submitList(obj);
        int i = 0;
        if (this.setFocus) {
            this.setFocus = false;
            LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new BaseBrowserTvFragment$submitList$1(this, (Continuation<? super BaseBrowserTvFragment$submitList$1>) null));
        }
        MediaBrowserAnimatorDelegate animationDelegate$television_release = getAnimationDelegate$television_release();
        AppCompatImageButton appCompatImageButton = getBinding().imageButtonHeader;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton, "imageButtonHeader");
        animationDelegate$television_release.setVisibility(appCompatImageButton, getViewModel().getProvider().getHeaders().isEmpty() ? 8 : 0);
        MediaBrowserAnimatorDelegate animationDelegate$television_release2 = getAnimationDelegate$television_release();
        AppCompatImageButton appCompatImageButton2 = getBinding().headerButton;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton2, "headerButton");
        animationDelegate$television_release2.setVisibility(appCompatImageButton2, getViewModel().getProvider().getHeaders().isEmpty() ? 8 : 0);
        MediaBrowserAnimatorDelegate animationDelegate$television_release3 = getAnimationDelegate$television_release();
        TextView textView = getBinding().headerDescription;
        Intrinsics.checkNotNullExpressionValue(textView, "headerDescription");
        View view = textView;
        if (getViewModel().getProvider().getHeaders().isEmpty()) {
            i = 8;
        }
        animationDelegate$television_release3.setVisibility(view, i);
    }
}
