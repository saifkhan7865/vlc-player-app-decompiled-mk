package org.videolan.television.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.R;
import org.videolan.television.databinding.SongHeaderItemBinding;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002 !B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0011J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u001c\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0011H\u0016J\u001c\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0011H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\b0\nj\b\u0012\u0004\u0012\u00020\b`\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lorg/videolan/television/ui/MediaHeaderAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/television/ui/MediaHeaderAdapter$ViewHolder;", "onHeaderSelected", "Lorg/videolan/television/ui/MediaHeaderAdapter$OnHeaderSelected;", "(Lorg/videolan/television/ui/MediaHeaderAdapter$OnHeaderSelected;)V", "alphaItems", "", "", "items", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getItems", "()Ljava/util/ArrayList;", "setItems", "(Ljava/util/ArrayList;)V", "sortType", "", "getSortType", "()I", "setSortType", "(I)V", "getItem", "position", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "OnHeaderSelected", "ViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaHeaderAdapter.kt */
public final class MediaHeaderAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<String> alphaItems = CollectionsKt.listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#");
    private ArrayList<String> items = new ArrayList<>();
    /* access modifiers changed from: private */
    public final OnHeaderSelected onHeaderSelected;
    private int sortType = 1;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/videolan/television/ui/MediaHeaderAdapter$OnHeaderSelected;", "", "onHeaderSelected", "", "header", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaHeaderAdapter.kt */
    public interface OnHeaderSelected {
        void onHeaderSelected(String str);
    }

    public MediaHeaderAdapter(OnHeaderSelected onHeaderSelected2) {
        Intrinsics.checkNotNullParameter(onHeaderSelected2, "onHeaderSelected");
        this.onHeaderSelected = onHeaderSelected2;
    }

    public final int getSortType() {
        return this.sortType;
    }

    public final void setSortType(int i) {
        this.sortType = i;
    }

    public final ArrayList<String> getItems() {
        return this.items;
    }

    public final void setItems(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.items = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.song_header_item, viewGroup, false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type org.videolan.television.databinding.SongHeaderItemBinding");
        return new ViewHolder(this, (SongHeaderItemBinding) inflate);
    }

    public int getItemCount() {
        int i = this.sortType;
        if (i == 0 || i == 1) {
            return this.alphaItems.size();
        }
        return this.items.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        int i2 = this.sortType;
        if (i2 == 1 || i2 == 0) {
            viewHolder.getBinding().setHeaderText(this.alphaItems.get(i));
            viewHolder.getBinding().setHasContent(Boolean.valueOf(this.items.contains(this.alphaItems.get(i))));
            return;
        }
        viewHolder.getBinding().setHeaderText(this.items.get(i));
        viewHolder.getBinding().setHasContent(true);
    }

    public final String getItem(int i) {
        int i2 = this.sortType;
        if (i2 == 1 || i2 == 0) {
            return this.alphaItems.get(i);
        }
        String str = this.items.get(i);
        Intrinsics.checkNotNull(str);
        return str;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/videolan/television/ui/MediaHeaderAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/television/databinding/SongHeaderItemBinding;", "(Lorg/videolan/television/ui/MediaHeaderAdapter;Lorg/videolan/television/databinding/SongHeaderItemBinding;)V", "getBinding", "()Lorg/videolan/television/databinding/SongHeaderItemBinding;", "setBinding", "(Lorg/videolan/television/databinding/SongHeaderItemBinding;)V", "onClick", "", "v", "Landroid/view/View;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaHeaderAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private SongHeaderItemBinding binding;
        final /* synthetic */ MediaHeaderAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(MediaHeaderAdapter mediaHeaderAdapter, SongHeaderItemBinding songHeaderItemBinding) {
            super(songHeaderItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(songHeaderItemBinding, "binding");
            this.this$0 = mediaHeaderAdapter;
            this.binding = songHeaderItemBinding;
            songHeaderItemBinding.setHolder(this);
        }

        public final SongHeaderItemBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(SongHeaderItemBinding songHeaderItemBinding) {
            Intrinsics.checkNotNullParameter(songHeaderItemBinding, "<set-?>");
            this.binding = songHeaderItemBinding;
        }

        public final void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.onHeaderSelected.onHeaderSelected(this.this$0.getItem(getLayoutPosition()));
        }
    }
}
