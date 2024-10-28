package org.videolan.vlc.gui.helpers;

import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.helpers.BookmarkAdapter;
import org.videolan.vlc.viewmodels.BookmarkModel;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u00102\u001a\u00020)J\u0018\u00103\u001a\u00020)2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J\"\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020\u001b2\u0006\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u000e\u0010:\u001a\u00020)2\u0006\u0010;\u001a\u00020<J\u0006\u0010=\u001a\u00020)R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u001dX.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020)0(X.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b0\u00101¨\u0006>"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BookmarkListDelegate;", "Landroidx/lifecycle/LifecycleObserver;", "Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$IBookmarkManager;", "activity", "Landroidx/fragment/app/FragmentActivity;", "service", "Lorg/videolan/vlc/PlaybackService;", "bookmarkModel", "Lorg/videolan/vlc/viewmodels/BookmarkModel;", "(Landroidx/fragment/app/FragmentActivity;Lorg/videolan/vlc/PlaybackService;Lorg/videolan/vlc/viewmodels/BookmarkModel;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "adapter", "Lorg/videolan/vlc/gui/helpers/BookmarkAdapter;", "addBookmarButton", "Landroid/widget/ImageView;", "getAddBookmarButton", "()Landroid/widget/ImageView;", "setAddBookmarButton", "(Landroid/widget/ImageView;)V", "bookmarkList", "Landroidx/recyclerview/widget/RecyclerView;", "getBookmarkList", "()Landroidx/recyclerview/widget/RecyclerView;", "setBookmarkList", "(Landroidx/recyclerview/widget/RecyclerView;)V", "emptyView", "Landroid/view/View;", "markerContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getMarkerContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setMarkerContainer", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "rootView", "getRootView", "setRootView", "getService", "()Lorg/videolan/vlc/PlaybackService;", "visibilityListener", "Lkotlin/Function0;", "", "getVisibilityListener", "()Lkotlin/jvm/functions/Function0;", "setVisibilityListener", "(Lkotlin/jvm/functions/Function0;)V", "visible", "", "getVisible", "()Z", "hide", "onBookmarkClick", "position", "", "bookmark", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "onPopupMenu", "view", "setProgressHeight", "y", "", "show", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BookmarkListDelegate.kt */
public final class BookmarkListDelegate implements LifecycleObserver, BookmarkAdapter.IBookmarkManager {
    private final FragmentActivity activity;
    /* access modifiers changed from: private */
    public BookmarkAdapter adapter;
    public ImageView addBookmarButton;
    public RecyclerView bookmarkList;
    /* access modifiers changed from: private */
    public final BookmarkModel bookmarkModel;
    /* access modifiers changed from: private */
    public View emptyView;
    public ConstraintLayout markerContainer;
    public ConstraintLayout rootView;
    private final PlaybackService service;
    public Function0<Unit> visibilityListener;

    public BookmarkListDelegate(FragmentActivity fragmentActivity, PlaybackService playbackService, BookmarkModel bookmarkModel2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkNotNullParameter(bookmarkModel2, "bookmarkModel");
        this.activity = fragmentActivity;
        this.service = playbackService;
        this.bookmarkModel = bookmarkModel2;
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public final PlaybackService getService() {
        return this.service;
    }

    public final ImageView getAddBookmarButton() {
        ImageView imageView = this.addBookmarButton;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("addBookmarButton");
        return null;
    }

    public final void setAddBookmarButton(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.addBookmarButton = imageView;
    }

    public final ConstraintLayout getMarkerContainer() {
        ConstraintLayout constraintLayout = this.markerContainer;
        if (constraintLayout != null) {
            return constraintLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("markerContainer");
        return null;
    }

    public final void setMarkerContainer(ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
        this.markerContainer = constraintLayout;
    }

    public final RecyclerView getBookmarkList() {
        RecyclerView recyclerView = this.bookmarkList;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bookmarkList");
        return null;
    }

    public final void setBookmarkList(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.bookmarkList = recyclerView;
    }

    public final ConstraintLayout getRootView() {
        ConstraintLayout constraintLayout = this.rootView;
        if (constraintLayout != null) {
            return constraintLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    public final void setRootView(ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
        this.rootView = constraintLayout;
    }

    public final Function0<Unit> getVisibilityListener() {
        Function0<Unit> function0 = this.visibilityListener;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("visibilityListener");
        return null;
    }

    public final void setVisibilityListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.visibilityListener = function0;
    }

    public final boolean getVisible() {
        return getRootView().getVisibility() != 8;
    }

    public final void show() {
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.activity.findViewById(R.id.bookmarks_stub);
        if (viewStubCompat != null) {
            View inflate = viewStubCompat.inflate();
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
            setRootView((ConstraintLayout) inflate);
            View findViewById = getRootView().findViewById(R.id.bookmark_list);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            setBookmarkList((RecyclerView) findViewById);
            ((ImageView) getRootView().findViewById(R.id.close)).setOnClickListener(new BookmarkListDelegate$$ExternalSyntheticLambda1(this));
            View findViewById2 = getRootView().findViewById(R.id.add_bookmark);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            setAddBookmarButton((ImageView) findViewById2);
            getAddBookmarButton().setOnClickListener(new BookmarkListDelegate$$ExternalSyntheticLambda2(this));
            getRootView().findViewById(R.id.top_bar).setOnTouchListener(new BookmarkListDelegate$$ExternalSyntheticLambda3());
            View findViewById3 = getRootView().findViewById(R.id.empty_view);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.emptyView = findViewById3;
            LifecycleObserver lifecycleObserver = this;
            this.service.getLifecycle().addObserver(lifecycleObserver);
            this.activity.getLifecycle().addObserver(lifecycleObserver);
            if (getBookmarkList().getLayoutManager() == null) {
                getBookmarkList().setLayoutManager(new LinearLayoutManager(this.activity, 1, false));
            }
            this.adapter = new BookmarkAdapter(this);
            RecyclerView bookmarkList2 = getBookmarkList();
            BookmarkAdapter bookmarkAdapter = this.adapter;
            if (bookmarkAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                bookmarkAdapter = null;
            }
            bookmarkList2.setAdapter(bookmarkAdapter);
            getBookmarkList().setItemAnimator((RecyclerView.ItemAnimator) null);
            this.bookmarkModel.getDataset().observe(this.activity, new BookmarkListDelegate$sam$androidx_lifecycle_Observer$0(new BookmarkListDelegate$show$1$4(this)));
            this.bookmarkModel.refresh();
        }
        this.bookmarkModel.refresh();
        KotlinExtensionsKt.setVisible(getRootView());
        KotlinExtensionsKt.setVisible(getMarkerContainer());
        getVisibilityListener().invoke();
    }

    /* access modifiers changed from: private */
    public static final void show$lambda$3$lambda$0(BookmarkListDelegate bookmarkListDelegate, View view) {
        Intrinsics.checkNotNullParameter(bookmarkListDelegate, "this$0");
        bookmarkListDelegate.hide();
    }

    /* access modifiers changed from: private */
    public static final void show$lambda$3$lambda$1(BookmarkListDelegate bookmarkListDelegate, View view) {
        Intrinsics.checkNotNullParameter(bookmarkListDelegate, "this$0");
        bookmarkListDelegate.bookmarkModel.addBookmark(bookmarkListDelegate.activity);
        bookmarkListDelegate.getAddBookmarButton().announceForAccessibility(bookmarkListDelegate.activity.getString(R.string.bookmark_added));
    }

    /* access modifiers changed from: private */
    public static final boolean show$lambda$3$lambda$2(View view, MotionEvent motionEvent) {
        view.getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }

    public final void hide() {
        KotlinExtensionsKt.setGone(getRootView());
        KotlinExtensionsKt.setGone(getMarkerContainer());
        getVisibilityListener().invoke();
    }

    public void onPopupMenu(View view, int i, Bookmark bookmark) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (bookmark != null) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.bookmark_options);
            popupMenu.setOnMenuItemClickListener(new BookmarkListDelegate$$ExternalSyntheticLambda0(bookmark, this));
            popupMenu.show();
        }
    }

    /* access modifiers changed from: private */
    public static final boolean onPopupMenu$lambda$4(Bookmark bookmark, BookmarkListDelegate bookmarkListDelegate, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(bookmarkListDelegate, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.bookmark_rename) {
            RenameDialog newInstance$default = RenameDialog.Companion.newInstance$default(RenameDialog.Companion, bookmark, false, 2, (Object) null);
            newInstance$default.show(bookmarkListDelegate.activity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
            newInstance$default.setListener(new BookmarkListDelegate$onPopupMenu$1$1(bookmarkListDelegate));
            return true;
        } else if (itemId != R.id.bookmark_delete) {
            return false;
        } else {
            bookmarkListDelegate.bookmarkModel.delete(bookmark);
            return true;
        }
    }

    public void onBookmarkClick(int i, Bookmark bookmark) {
        Intrinsics.checkNotNullParameter(bookmark, "bookmark");
        PlaybackService.setTime$default(this.service, bookmark.getTime(), false, 2, (Object) null);
    }

    public final void setProgressHeight(float f) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(getRootView());
        constraintSet.setGuidelineBegin(R.id.progressbar_guideline, (int) f);
        constraintSet.applyTo(getRootView());
    }
}
