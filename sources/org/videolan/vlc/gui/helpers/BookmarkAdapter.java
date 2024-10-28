package org.videolan.vlc.gui.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.BookmarkItemBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u001f B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0017J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001c\u0010\u0015\u001a\u00020\u00122\n\u0010\u0016\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J*\u0010\u0015\u001a\u00020\u00122\n\u0010\u0016\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u001c\u0010\u001a\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\u0014\u0010\u001e\u001a\u00020\u00122\n\u0010\u0016\u001a\u00060\u0003R\u00020\u0000H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BookmarkAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$ViewHolder;", "bookmarkManager", "Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$IBookmarkManager;", "(Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$IBookmarkManager;)V", "getBookmarkManager", "()Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$IBookmarkManager;", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "getItem", "position", "", "getItemCount", "onAttachedToRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "IBookmarkManager", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BookmarkAdapter.kt */
public final class BookmarkAdapter extends DiffUtilAdapter<Bookmark, ViewHolder> {
    private final IBookmarkManager bookmarkManager;
    private LifecycleAwareScheduler scheduler;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\"\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$IBookmarkManager;", "", "onBookmarkClick", "", "position", "", "bookmark", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "onPopupMenu", "view", "Landroid/view/View;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BookmarkAdapter.kt */
    public interface IBookmarkManager {
        void onBookmarkClick(int i, Bookmark bookmark);

        void onPopupMenu(View view, int i, Bookmark bookmark);
    }

    public final IBookmarkManager getBookmarkManager() {
        return this.bookmarkManager;
    }

    public BookmarkAdapter(IBookmarkManager iBookmarkManager) {
        Intrinsics.checkNotNullParameter(iBookmarkManager, "bookmarkManager");
        this.bookmarkManager = iBookmarkManager;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmark_item, viewGroup, false);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        viewHolder.getBinding().setBookmark(getItem(i));
        viewHolder.getBinding().executePendingBindings();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            it.next();
            viewHolder.getBinding().setBookmark((Bookmark) getDataset().get(i));
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            this.scheduler = UiToolsKt.enableMarqueeEffect(recyclerView);
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
        }
        super.onViewRecycled(viewHolder);
    }

    public int getItemCount() {
        return getDataset().size();
    }

    public Bookmark getItem(int i) {
        return (Bookmark) getDataset().get(i);
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BookmarkAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "v", "Landroid/view/View;", "(Lorg/videolan/vlc/gui/helpers/BookmarkAdapter;Landroid/view/View;)V", "binding", "Lorg/videolan/vlc/databinding/BookmarkItemBinding;", "getBinding", "()Lorg/videolan/vlc/databinding/BookmarkItemBinding;", "setBinding", "(Lorg/videolan/vlc/databinding/BookmarkItemBinding;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "onClick", "", "bookmark", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "onMoreClick", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BookmarkAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder implements MarqueeViewHolder {
        private BookmarkItemBinding binding;
        final /* synthetic */ BookmarkAdapter this$0;
        private final TextView titleView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(BookmarkAdapter bookmarkAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0 = bookmarkAdapter;
            ViewDataBinding bind = DataBindingUtil.bind(view);
            Intrinsics.checkNotNull(bind);
            BookmarkItemBinding bookmarkItemBinding = (BookmarkItemBinding) bind;
            this.binding = bookmarkItemBinding;
            TextView textView = bookmarkItemBinding.audioItemTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "audioItemTitle");
            this.titleView = textView;
            this.binding.setHolder(this);
        }

        public final BookmarkItemBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(BookmarkItemBinding bookmarkItemBinding) {
            Intrinsics.checkNotNullParameter(bookmarkItemBinding, "<set-?>");
            this.binding = bookmarkItemBinding;
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        public final void onClick(View view, Bookmark bookmark) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(bookmark, "bookmark");
            this.this$0.getBookmarkManager().onBookmarkClick(getLayoutPosition(), bookmark);
        }

        public final void onMoreClick(View view, Bookmark bookmark) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(bookmark, "bookmark");
            this.this$0.getBookmarkManager().onPopupMenu(view, getLayoutPosition(), bookmark);
        }
    }

    /* access modifiers changed from: protected */
    public DiffUtilAdapter.DiffCallback<Bookmark> createCB() {
        return new BookmarkAdapter$createCB$1();
    }
}
