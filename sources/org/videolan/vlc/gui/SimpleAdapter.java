package org.videolan.vlc.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectAdapter;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.BR;
import org.videolan.vlc.databinding.SimpleItemBinding;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00020\u0004:\u0002\u001c\u001dB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0006\u0010\u0013\u001a\u00020\u0014J\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0018\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0012H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/gui/SimpleAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/SimpleAdapter$ViewHolder;", "Lorg/videolan/tools/MultiSelectAdapter;", "handler", "Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;", "(Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;)V", "getHandler", "()Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;", "inflater", "Landroid/view/LayoutInflater;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "getMultiSelectHelper", "()Lorg/videolan/tools/MultiSelectHelper;", "getItem", "position", "", "isEmpty", "", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ClickHandler", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SimpleAdapter.kt */
public final class SimpleAdapter extends ListAdapter<MediaLibraryItem, ViewHolder> implements MultiSelectAdapter<MediaLibraryItem> {
    private final ClickHandler handler;
    private LayoutInflater inflater;
    private final MultiSelectHelper<MediaLibraryItem> multiSelectHelper = new MultiSelectHelper<>(this, 0);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;", "", "onClick", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "position", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SimpleAdapter.kt */
    public interface ClickHandler {
        void onClick(MediaLibraryItem mediaLibraryItem, int i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleAdapter(ClickHandler clickHandler) {
        super(SimpleAdapterKt.access$getCb$p());
        Intrinsics.checkNotNullParameter(clickHandler, "handler");
        this.handler = clickHandler;
    }

    public final ClickHandler getHandler() {
        return this.handler;
    }

    public final MultiSelectHelper<MediaLibraryItem> getMultiSelectHelper() {
        return this.multiSelectHelper;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (this.inflater == null) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            Intrinsics.checkNotNullExpressionValue(from, "from(...)");
            this.inflater = from;
        }
        ClickHandler clickHandler = this.handler;
        LayoutInflater layoutInflater = this.inflater;
        if (layoutInflater == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inflater");
            layoutInflater = null;
        }
        SimpleItemBinding inflate = SimpleItemBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, clickHandler, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        viewHolder.selectView(this.multiSelectHelper.isSelected(i));
        ((SimpleItemBinding) viewHolder.getBinding()).setPosition(i);
        ((SimpleItemBinding) viewHolder.getBinding()).setItem(getItem(i));
        ((SimpleItemBinding) viewHolder.getBinding()).setImageWidth(KotlinExtensionsKt.getDp(48));
        MediaLibraryItem item = getItem(i);
        DummyItem dummyItem = item instanceof DummyItem ? (DummyItem) item : null;
        if (dummyItem != null) {
            Context context = viewHolder.itemView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            ((SimpleItemBinding) viewHolder.getBinding()).setCover(ImageLoaderKt.getDummyItemIcon(context, dummyItem));
        }
    }

    public final boolean isEmpty() {
        return getItemCount() == 0;
    }

    public MediaLibraryItem getItem(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return (MediaLibraryItem) super.getItem(i);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/SimpleAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/databinding/SimpleItemBinding;", "handler", "Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;", "binding", "(Lorg/videolan/vlc/gui/SimpleAdapter;Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;Lorg/videolan/vlc/databinding/SimpleItemBinding;)V", "selectView", "", "selected", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SimpleAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<SimpleItemBinding> {
        final /* synthetic */ SimpleAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(SimpleAdapter simpleAdapter, ClickHandler clickHandler, SimpleItemBinding simpleItemBinding) {
            super(simpleItemBinding);
            Intrinsics.checkNotNullParameter(clickHandler, "handler");
            Intrinsics.checkNotNullParameter(simpleItemBinding, "binding");
            this.this$0 = simpleAdapter;
            simpleItemBinding.setHandler(clickHandler);
        }

        public void selectView(boolean z) {
            ((SimpleItemBinding) getBinding()).setVariable(BR.selected, Boolean.valueOf(z));
        }
    }
}
