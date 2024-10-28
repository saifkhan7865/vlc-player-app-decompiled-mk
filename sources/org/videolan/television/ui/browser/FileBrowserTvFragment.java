package org.videolan.television.ui.browser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.Dialog;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.television.R;
import org.videolan.television.ui.FileTvItemAdapter;
import org.videolan.television.ui.MediaBrowserAnimatorDelegate;
import org.videolan.television.ui.TvItemAdapter;
import org.videolan.television.ui.TvUtil;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.gui.browser.PathAdapter;
import org.videolan.vlc.gui.browser.PathAdapterListener;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.providers.BrowserProvider;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.IDialogManager;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.browser.IPathOperationDelegate;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 R2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001RB\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001fH\u0016J\u0016\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0018J\b\u0010*\u001a\u00020+H\u0016J\u0012\u0010,\u001a\u00020\u001c2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0010\u0010/\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u001fH\u0016J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020\u001fH\u0016J\b\u00108\u001a\u00020\u001cH\u0002J\u0012\u00109\u001a\u00020\u001c2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J \u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020\u001b2\u0006\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020\u0002H\u0016J\u0012\u0010@\u001a\u00020\u001c2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010A\u001a\u00020\u001cH\u0016J\u0010\u0010B\u001a\u00020\u00182\u0006\u0010C\u001a\u000203H\u0016J\b\u0010D\u001a\u00020\u001cH\u0016J\u0010\u0010E\u001a\u00020\u001c2\u0006\u0010F\u001a\u00020;H\u0016J\b\u0010G\u001a\u00020\u001cH\u0016J\b\u0010H\u001a\u00020\u001cH\u0016J\u001a\u0010I\u001a\u00020\u001c2\u0006\u0010J\u001a\u00020\u001b2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u001e\u0010K\u001a\u00020\u00072\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00020M2\u0006\u0010N\u001a\u000203H\u0016J\b\u0010O\u001a\u00020\u0018H\u0016J\b\u0010P\u001a\u00020\u001cH\u0002J\u000e\u0010Q\u001a\u00020\u001c*\u0004\u0018\u00010(H\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006S"}, d2 = {"Lorg/videolan/television/ui/browser/FileBrowserTvFragment;", "Lorg/videolan/television/ui/browser/BaseBrowserTvFragment;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/browser/PathAdapterListener;", "Lorg/videolan/vlc/util/IDialogManager;", "()V", "adapter", "Lorg/videolan/television/ui/TvItemAdapter;", "getAdapter", "()Lorg/videolan/television/ui/TvItemAdapter;", "setAdapter", "(Lorg/videolan/television/ui/TvItemAdapter;)V", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "currentItem", "dataObserver", "Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;", "dialogsDelegate", "Lorg/videolan/vlc/util/DialogDelegate;", "getDialogsDelegate", "()Lorg/videolan/vlc/util/DialogDelegate;", "dialogsDelegate$delegate", "Lkotlin/Lazy;", "favExists", "", "favoriteClickListener", "Lkotlin/Function1;", "Landroid/view/View;", "", "isRootLevel", "mrl", "", "getMrl", "()Ljava/lang/String;", "setMrl", "(Ljava/lang/String;)V", "backTo", "tag", "browse", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "save", "currentContext", "Landroid/content/Context;", "dialogCanceled", "dialog", "Lorg/videolan/libvlc/Dialog;", "fireDialog", "getCategory", "", "getColumnNumber", "", "getDisplayPrefId", "getPathOperationDelegate", "Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "getTitle", "goBack", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onClick", "v", "position", "item", "onCreate", "onDestroy", "onKeyPressed", "keyCode", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "onViewCreated", "view", "provideAdapter", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "itemSize", "showRoot", "togglefavorite", "setBreadcrumb", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserTvFragment.kt */
public final class FileBrowserTvFragment extends BaseBrowserTvFragment<MediaLibraryItem> implements PathAdapterListener, IDialogManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public TvItemAdapter adapter;
    /* access modifiers changed from: private */
    public BrowserFavRepository browserFavRepository;
    /* access modifiers changed from: private */
    public MediaLibraryItem currentItem;
    private RecyclerView.AdapterDataObserver dataObserver;
    private final Lazy dialogsDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, FileBrowserTvFragment$dialogsDelegate$2.INSTANCE);
    /* access modifiers changed from: private */
    public boolean favExists;
    private final Function1<View, Unit> favoriteClickListener = new FileBrowserTvFragment$favoriteClickListener$1(this);
    private boolean isRootLevel;
    private String mrl;

    public boolean showRoot() {
        return true;
    }

    public TvItemAdapter getAdapter() {
        TvItemAdapter tvItemAdapter = this.adapter;
        if (tvItemAdapter != null) {
            return tvItemAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public void setAdapter(TvItemAdapter tvItemAdapter) {
        Intrinsics.checkNotNullParameter(tvItemAdapter, "<set-?>");
        this.adapter = tvItemAdapter;
    }

    private final DialogDelegate getDialogsDelegate() {
        return (DialogDelegate) this.dialogsDelegate$delegate.getValue();
    }

    public final String getMrl() {
        return this.mrl;
    }

    public final void setMrl(String str) {
        this.mrl = str;
    }

    public String getTitle() {
        String str;
        long category = getCategory();
        if (category == 0) {
            str = getString(R.string.directories);
        } else if (category == 1) {
            str = getString(R.string.network_browsing);
        } else {
            str = getString(R.string.video);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    public int getColumnNumber() {
        return getResources().getInteger(R.integer.tv_songs_col_count);
    }

    public TvItemAdapter provideAdapter(IEventsHandler<MediaLibraryItem> iEventsHandler, int i) {
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        FileTvItemAdapter fileTvItemAdapter = new FileTvItemAdapter(this, i, this.isRootLevel && getCategory() == 1, false, 8, (DefaultConstructorMarker) null);
        this.dataObserver = KextensionsKt.onAnyChange(fileTvItemAdapter, new FileBrowserTvFragment$provideAdapter$1(this, fileTvItemAdapter));
        return fileTvItemAdapter;
    }

    public String getDisplayPrefId() {
        return "display_tv_file_" + getCategory();
    }

    /* JADX WARNING: type inference failed for: r10v4, types: [org.videolan.medialibrary.media.MediaLibraryItem] */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        if ((r10 instanceof org.videolan.medialibrary.media.MediaLibraryItem) != false) goto L_0x0051;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r10) {
        /*
            r9 = this;
            super.onCreate(r10)
            r0 = 33
            java.lang.String r1 = "item"
            r2 = 0
            if (r10 == 0) goto L_0x0027
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r0) goto L_0x0017
            java.lang.Class<android.os.Parcelable> r0 = android.os.Parcelable.class
            java.lang.Object r10 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r10, (java.lang.String) r1, (java.lang.Class) r0)
            android.os.Parcelable r10 = (android.os.Parcelable) r10
            goto L_0x0020
        L_0x0017:
            android.os.Parcelable r10 = r10.getParcelable(r1)
            boolean r0 = r10 instanceof android.os.Parcelable
            if (r0 != 0) goto L_0x0020
            r10 = r2
        L_0x0020:
            boolean r0 = r10 instanceof org.videolan.medialibrary.media.MediaLibraryItem
            if (r0 == 0) goto L_0x0050
            org.videolan.medialibrary.media.MediaLibraryItem r10 = (org.videolan.medialibrary.media.MediaLibraryItem) r10
            goto L_0x0051
        L_0x0027:
            android.os.Bundle r10 = r9.getArguments()
            if (r10 == 0) goto L_0x004a
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r0) goto L_0x003a
            java.lang.Class<org.videolan.medialibrary.media.MediaLibraryItem> r0 = org.videolan.medialibrary.media.MediaLibraryItem.class
            java.lang.Object r10 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r10, (java.lang.String) r1, (java.lang.Class) r0)
            android.os.Parcelable r10 = (android.os.Parcelable) r10
            goto L_0x0047
        L_0x003a:
            android.os.Parcelable r10 = r10.getParcelable(r1)
            boolean r0 = r10 instanceof org.videolan.medialibrary.media.MediaLibraryItem
            if (r0 != 0) goto L_0x0043
            r10 = r2
        L_0x0043:
            org.videolan.medialibrary.media.MediaLibraryItem r10 = (org.videolan.medialibrary.media.MediaLibraryItem) r10
            android.os.Parcelable r10 = (android.os.Parcelable) r10
        L_0x0047:
            org.videolan.medialibrary.media.MediaLibraryItem r10 = (org.videolan.medialibrary.media.MediaLibraryItem) r10
            goto L_0x004b
        L_0x004a:
            r10 = r2
        L_0x004b:
            boolean r0 = r10 instanceof org.videolan.medialibrary.media.MediaLibraryItem
            if (r0 == 0) goto L_0x0050
            goto L_0x0051
        L_0x0050:
            r10 = r2
        L_0x0051:
            r9.currentItem = r10
            android.os.Bundle r10 = r9.getArguments()
            if (r10 == 0) goto L_0x0060
            java.lang.String r0 = "rootLevel"
            boolean r10 = r10.getBoolean(r0)
            goto L_0x0061
        L_0x0060:
            r10 = 0
        L_0x0061:
            r9.isRootLevel = r10
            org.videolan.medialibrary.media.MediaLibraryItem r10 = r9.currentItem
            boolean r0 = r10 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r0 == 0) goto L_0x006c
            r2 = r10
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
        L_0x006c:
            if (r2 == 0) goto L_0x0074
            java.lang.String r10 = r2.getLocation()
            r9.mrl = r10
        L_0x0074:
            android.os.Bundle r10 = r9.getArguments()
            r0 = 0
            if (r10 == 0) goto L_0x0082
            java.lang.String r2 = "category"
            long r0 = r10.getLong(r2, r0)
        L_0x0082:
            r3 = r0
            r2 = r9
            androidx.fragment.app.Fragment r2 = (androidx.fragment.app.Fragment) r2
            java.lang.String r5 = r9.mrl
            r7 = 4
            r8 = 0
            r6 = 0
            org.videolan.vlc.viewmodels.browser.BrowserModel r10 = org.videolan.vlc.viewmodels.browser.BrowserModelKt.getBrowserModel$default(r2, r3, r5, r6, r7, r8)
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r10 = (org.videolan.vlc.viewmodels.tv.TvBrowserModel) r10
            r9.setViewModel(r10)
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r10 = r9.getViewModel()
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r9.currentItem
            r10.setCurrentItem(r0)
            org.videolan.vlc.repository.BrowserFavRepository$Companion r10 = org.videolan.vlc.repository.BrowserFavRepository.Companion
            android.content.Context r0 = r9.requireContext()
            java.lang.String r1 = "requireContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object r10 = r10.getInstance(r0)
            org.videolan.vlc.repository.BrowserFavRepository r10 = (org.videolan.vlc.repository.BrowserFavRepository) r10
            r9.browserFavRepository = r10
            long r0 = r9.getCategory()
            r2 = 1
            int r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 != 0) goto L_0x00c7
            org.videolan.vlc.util.DialogDelegate r10 = r9.getDialogsDelegate()
            r0 = r9
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            r1 = r9
            org.videolan.vlc.util.IDialogManager r1 = (org.videolan.vlc.util.IDialogManager) r1
            r10.observeDialogs(r0, r1)
        L_0x00c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.FileBrowserTvFragment.onCreate(android.os.Bundle):void");
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
        ((BrowserModel) viewModel).getDataset().observe(getViewLifecycleOwner(), new FileBrowserTvFragmentKt$sam$androidx_lifecycle_Observer$0(new FileBrowserTvFragment$onViewCreated$1(this)));
        getViewModel().getProvider().getLiveHeaders().observe(getViewLifecycleOwner(), new FileBrowserTvFragmentKt$sam$androidx_lifecycle_Observer$0(new FileBrowserTvFragment$onViewCreated$2(this)));
        HeaderProvider provider = getViewModel().getProvider();
        Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.BrowserProvider");
        ((BrowserProvider) provider).getLoading().observe(getViewLifecycleOwner(), new FileBrowserTvFragmentKt$sam$androidx_lifecycle_Observer$0(new FileBrowserTvFragment$onViewCreated$3(this)));
        TvBrowserModel viewModel2 = getViewModel();
        Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
        ((BrowserModel) viewModel2).getDescriptionUpdate().observe(getViewLifecycleOwner(), new FileBrowserTvFragmentKt$sam$androidx_lifecycle_Observer$0(new FileBrowserTvFragment$onViewCreated$4(this)));
    }

    public void onStart() {
        super.onStart();
        Object currentItem2 = getViewModel().getCurrentItem();
        setBreadcrumb(currentItem2 instanceof MediaWrapper ? (MediaWrapper) currentItem2 : null);
    }

    private final void setBreadcrumb(MediaWrapper mediaWrapper) {
        if (mediaWrapper != null) {
            Uri uri = mediaWrapper.getUri();
            int i = 8;
            if (BrowserutilsKt.isSchemeSupported(uri != null ? uri.getScheme() : null)) {
                getBinding().ariane.setVisibility(0);
                getBinding().ariane.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
                getBinding().ariane.setAdapter(new PathAdapter(this, mediaWrapper));
                if (getBinding().ariane.getItemDecorationCount() == 0) {
                    FragmentActivity requireActivity = requireActivity();
                    VectorDrawableCompat create = VectorDrawableCompat.create(requireActivity().getResources(), R.drawable.ic_divider, requireActivity().getTheme());
                    Intrinsics.checkNotNull(create);
                    FileBrowserTvFragment$setBreadcrumb$did$1 fileBrowserTvFragment$setBreadcrumb$did$1 = new FileBrowserTvFragment$setBreadcrumb$did$1(requireActivity, create);
                    VectorDrawableCompat create2 = VectorDrawableCompat.create(requireActivity().getResources(), R.drawable.ic_divider, requireActivity().getTheme());
                    Intrinsics.checkNotNull(create2);
                    fileBrowserTvFragment$setBreadcrumb$did$1.setDrawable(create2);
                    getBinding().ariane.addItemDecoration(fileBrowserTvFragment$setBreadcrumb$did$1);
                }
                RecyclerView recyclerView = getBinding().ariane;
                RecyclerView.Adapter adapter2 = getBinding().ariane.getAdapter();
                Intrinsics.checkNotNull(adapter2);
                recyclerView.scrollToPosition(adapter2.getItemCount() - 1);
            } else {
                getBinding().ariane.setVisibility(8);
            }
            MediaBrowserAnimatorDelegate animationDelegate$television_release = getAnimationDelegate$television_release();
            TextView textView = getBinding().title;
            Intrinsics.checkNotNullExpressionValue(textView, "title");
            View view = textView;
            if (getBinding().ariane.getVisibility() == 8) {
                i = 0;
            }
            animationDelegate$television_release.setVisibility(view, i);
        }
    }

    public void backTo(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        if (Intrinsics.areEqual((Object) str, (Object) "root")) {
            requireActivity().finish();
            return;
        }
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackEntryCount; i++) {
            if (Intrinsics.areEqual((Object) str, (Object) supportFragmentManager.getBackStackEntryAt(i).getName())) {
                supportFragmentManager.popBackStack(str, 1);
                return;
            }
        }
        if (supportFragmentManager.getBackStackEntryCount() == 0) {
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(Uri.parse(str));
            Intrinsics.checkNotNullExpressionValue(abstractMediaWrapper, "getAbstractMediaWrapper(...)");
            browse(abstractMediaWrapper, false);
            return;
        }
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.IPathOperationDelegate");
        ((IPathOperationDelegate) viewModel).setDestination(MLServiceLocator.getAbstractMediaWrapper(Uri.parse(str)));
        TvBrowserModel viewModel2 = getViewModel();
        Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.IPathOperationDelegate");
        ((IPathOperationDelegate) viewModel2).setSource(this.currentItem);
        supportFragmentManager.popBackStack();
    }

    public Context currentContext() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return requireActivity;
    }

    public IPathOperationDelegate getPathOperationDelegate() {
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.IPathOperationDelegate");
        return (IPathOperationDelegate) viewModel;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new FileBrowserTvFragment$onActivityCreated$1(this, (Continuation<? super FileBrowserTvFragment$onActivityCreated$1>) null), 3, (Object) null);
        getBinding().favoriteButton.setOnClickListener(new FileBrowserTvFragment$$ExternalSyntheticLambda0(this.favoriteClickListener));
        getBinding().imageButtonFavorite.setOnClickListener(new FileBrowserTvFragment$$ExternalSyntheticLambda1(this.favoriteClickListener));
        getBinding().emptyLoading.setShowNoMedia(false);
    }

    /* access modifiers changed from: private */
    public static final void onActivityCreated$lambda$1(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    /* access modifiers changed from: private */
    public static final void onActivityCreated$lambda$2(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    public void onResume() {
        super.onResume();
        if (this.currentItem == null) {
            HeaderProvider provider = getViewModel().getProvider();
            Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.BrowserProvider");
            ((BrowserProvider) provider).browseRoot();
        } else if (getRestarted()) {
            refresh();
        }
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.IPathOperationDelegate");
        MediaWrapper andRemoveDestination = ((IPathOperationDelegate) viewModel).getAndRemoveDestination();
        if (andRemoveDestination != null) {
            browse(andRemoveDestination, true);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putParcelable("item", this.currentItem);
        bundle.putLong(Constants.CATEGORY, getCategory());
        super.onSaveInstanceState(bundle);
    }

    public void onStop() {
        super.onStop();
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
        ((BrowserModel) viewModel).stop();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.dataObserver != null) {
            TvItemAdapter adapter2 = getAdapter();
            Intrinsics.checkNotNull(adapter2, "null cannot be cast to non-null type org.videolan.television.ui.FileTvItemAdapter");
            FileTvItemAdapter fileTvItemAdapter = (FileTvItemAdapter) adapter2;
            RecyclerView.AdapterDataObserver adapterDataObserver = this.dataObserver;
            if (adapterDataObserver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataObserver");
                adapterDataObserver = null;
            }
            fileTvItemAdapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
    }

    public long getCategory() {
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
        return ((BrowserModel) viewModel).getType();
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        mediaWrapper.removeFlags(8);
        if (mediaWrapper.getType() == 3) {
            browse(mediaWrapper, true);
            return;
        }
        TvUtil tvUtil = TvUtil.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
        tvUtil.openMedia(requireActivity, mediaLibraryItem, (BrowserModel) viewModel);
    }

    public final void browse(MediaWrapper mediaWrapper, boolean z) {
        String str;
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        FragmentActivity activity = getActivity();
        if (activity != null && isResumed() && !isRemoving()) {
            FragmentTransaction beginTransaction = activity.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
            FileBrowserTvFragment newInstance$default = Companion.newInstance$default(Companion, getCategory(), mediaWrapper, false, 4, (Object) null);
            TvBrowserModel viewModel = getViewModel();
            Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
            ((BrowserModel) viewModel).saveList(mediaWrapper);
            if (z) {
                if (this.mrl == null) {
                    str = "root";
                } else {
                    MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) getViewModel().getCurrentItem();
                    str = mediaLibraryItem != null ? mediaLibraryItem.getTitle() : null;
                    if (str == null) {
                        str = FileUtils.INSTANCE.getFileNameFromPath(this.mrl);
                    }
                }
                beginTransaction.addToBackStack(str);
            }
            beginTransaction.replace(R.id.tv_fragment_placeholder, (Fragment) newInstance$default, mediaWrapper.getTitle());
            beginTransaction.commit();
        }
    }

    public boolean onKeyPressed(int i) {
        if (i != 174) {
            return super.onKeyPressed(i);
        }
        togglefavorite();
        return true;
    }

    /* access modifiers changed from: private */
    public final void togglefavorite() {
        MediaLibraryItem mediaLibraryItem = this.currentItem;
        if (mediaLibraryItem != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new FileBrowserTvFragment$togglefavorite$1$1(mediaLibraryItem, this, (Continuation<? super FileBrowserTvFragment$togglefavorite$1$1>) null), 3, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lorg/videolan/television/ui/browser/FileBrowserTvFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/television/ui/browser/FileBrowserTvFragment;", "type", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "root", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileBrowserTvFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ FileBrowserTvFragment newInstance$default(Companion companion, long j, MediaLibraryItem mediaLibraryItem, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.newInstance(j, mediaLibraryItem, z);
        }

        public final FileBrowserTvFragment newInstance(long j, MediaLibraryItem mediaLibraryItem, boolean z) {
            FileBrowserTvFragment fileBrowserTvFragment = new FileBrowserTvFragment();
            fileBrowserTvFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(Constants.CATEGORY, Long.valueOf(j)), TuplesKt.to("item", mediaLibraryItem), TuplesKt.to("rootLevel", Boolean.valueOf(z))));
            return fileBrowserTvFragment;
        }
    }

    public void fireDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogActivity.Companion.setDialog(dialog);
        startActivity(new Intent(DialogActivity.KEY_DIALOG, (Uri) null, getActivity(), DialogActivity.class));
    }

    public void dialogCanceled(Dialog dialog) {
        if (dialog instanceof Dialog.LoginDialog) {
            goBack();
        } else if (dialog instanceof Dialog.ErrorMessage) {
            View view = getView();
            if (view != null) {
                StringBuilder sb = new StringBuilder();
                Dialog.ErrorMessage errorMessage = (Dialog.ErrorMessage) dialog;
                sb.append(errorMessage.getTitle());
                sb.append(": ");
                sb.append(errorMessage.getText());
                Snackbar.make(view, (CharSequence) sb.toString(), 0).show();
            }
            goBack();
        }
    }

    private final void goBack() {
        FragmentActivity activity = getActivity();
        if (activity != null && KotlinExtensionsKt.isStarted(activity)) {
            if (Intrinsics.areEqual((Object) getTag(), (Object) "root")) {
                activity.finish();
            } else if (!activity.isFinishing() && !activity.isDestroyed()) {
                activity.getSupportFragmentManager().popBackStack();
            }
        }
    }
}
