package org.videolan.vlc.gui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.databinding.AboutAuthorsItemBinding;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0013B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u001c\u0010\u000f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/gui/AuthorsAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "", "Lorg/videolan/vlc/gui/AuthorsAdapter$ViewHolder;", "authors", "", "(Ljava/util/List;)V", "getAuthors", "()Ljava/util/List;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AuthorsActivity.kt */
public final class AuthorsAdapter extends DiffUtilAdapter<String, ViewHolder> {
    private final List<String> authors;

    public AuthorsAdapter(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "authors");
        this.authors = list;
    }

    public final List<String> getAuthors() {
        return this.authors;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        AboutAuthorsItemBinding inflate = AboutAuthorsItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ((AboutAuthorsItemBinding) viewHolder.getBinding()).setAuthor(this.authors.get(i));
    }

    public int getItemCount() {
        return this.authors.size();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/AuthorsAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/databinding/AboutAuthorsItemBinding;", "vdb", "(Lorg/videolan/vlc/gui/AuthorsAdapter;Lorg/videolan/vlc/databinding/AboutAuthorsItemBinding;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AuthorsActivity.kt */
    public final class ViewHolder extends SelectorViewHolder<AboutAuthorsItemBinding> {
        final /* synthetic */ AuthorsAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(AuthorsAdapter authorsAdapter, AboutAuthorsItemBinding aboutAuthorsItemBinding) {
            super(aboutAuthorsItemBinding);
            Intrinsics.checkNotNullParameter(aboutAuthorsItemBinding, "vdb");
            this.this$0 = authorsAdapter;
        }
    }
}
