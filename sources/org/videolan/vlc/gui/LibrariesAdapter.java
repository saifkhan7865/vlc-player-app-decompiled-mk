package org.videolan.vlc.gui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.databinding.LibraryItemBinding;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0014B(\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\t2\n\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u001c\u0010\u0010\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/LibrariesAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/vlc/gui/LibraryWithLicense;", "Lorg/videolan/vlc/gui/LibrariesAdapter$ViewHolder;", "itemClickHandler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "license", "", "(Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class LibrariesAdapter extends DiffUtilAdapter<LibraryWithLicense, ViewHolder> {
    /* access modifiers changed from: private */
    public final Function1<LibraryWithLicense, Unit> itemClickHandler;

    public LibrariesAdapter(Function1<? super LibraryWithLicense, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "itemClickHandler");
        this.itemClickHandler = function1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LibraryItemBinding inflate = LibraryItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ((LibraryItemBinding) viewHolder.getBinding()).setLibrary((LibraryWithLicense) getDataset().get(i));
    }

    public int getItemCount() {
        return getDataset().size();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/LibrariesAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/databinding/LibraryItemBinding;", "vdb", "(Lorg/videolan/vlc/gui/LibrariesAdapter;Lorg/videolan/vlc/databinding/LibraryItemBinding;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: LibrariesActivity.kt */
    public final class ViewHolder extends SelectorViewHolder<LibraryItemBinding> {
        final /* synthetic */ LibrariesAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(LibrariesAdapter librariesAdapter, LibraryItemBinding libraryItemBinding) {
            super(libraryItemBinding);
            Intrinsics.checkNotNullParameter(libraryItemBinding, "vdb");
            this.this$0 = librariesAdapter;
            libraryItemBinding.getRoot().setOnClickListener(new LibrariesAdapter$ViewHolder$$ExternalSyntheticLambda0(librariesAdapter, this));
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(LibrariesAdapter librariesAdapter, ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(librariesAdapter, "this$0");
            Intrinsics.checkNotNullParameter(viewHolder, "this$1");
            librariesAdapter.itemClickHandler.invoke(librariesAdapter.getDataset().get(viewHolder.getLayoutPosition()));
        }
    }
}
