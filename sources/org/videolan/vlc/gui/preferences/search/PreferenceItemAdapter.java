package org.videolan.vlc.gui.preferences.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.databinding.PreferenceItemBinding;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002#$B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0016\u001a\u00020\u0010J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0016J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006%"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ViewHolder;", "handler", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;", "(Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;)V", "getHandler", "()Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;", "value", "", "query", "getQuery", "()Ljava/lang/String;", "setQuery", "(Ljava/lang/String;)V", "", "showTranslation", "getShowTranslation", "()Z", "setShowTranslation", "(Z)V", "isEmpty", "onBindViewHolder", "", "holder", "position", "", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ClickHandler", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceItemAdapter.kt */
public final class PreferenceItemAdapter extends ListAdapter<PreferenceItem, ViewHolder> {
    private final ClickHandler handler;
    private String query = "";
    private boolean showTranslation;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;", "", "onClick", "", "item", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PreferenceItemAdapter.kt */
    public interface ClickHandler {
        void onClick(PreferenceItem preferenceItem);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PreferenceItemAdapter(ClickHandler clickHandler) {
        super(PreferenceItemAdapterKt.cb);
        Intrinsics.checkNotNullParameter(clickHandler, "handler");
        this.handler = clickHandler;
    }

    public final ClickHandler getHandler() {
        return this.handler;
    }

    public final boolean getShowTranslation() {
        return this.showTranslation;
    }

    public final void setShowTranslation(boolean z) {
        this.showTranslation = z;
        notifyItemRangeChanged(0, getItemCount());
    }

    public final String getQuery() {
        return this.query;
    }

    public final void setQuery(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.query = str;
        notifyItemRangeChanged(0, getItemCount());
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ClickHandler clickHandler = this.handler;
        PreferenceItemBinding inflate = PreferenceItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(clickHandler, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        PreferenceItem preferenceItem = (PreferenceItem) getItem(i);
        viewHolder.getBinding().setItem(preferenceItem);
        viewHolder.getBinding().setTitle(this.showTranslation ? preferenceItem.getTitleEng() : preferenceItem.getTitle());
        viewHolder.getBinding().setDescription(this.showTranslation ? preferenceItem.getSummaryEng() : preferenceItem.getSummary());
        viewHolder.getBinding().setCategory(this.showTranslation ? preferenceItem.getCategoryEng() : preferenceItem.getCategory());
        viewHolder.getBinding().setQuery(this.query);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        super.onBindViewHolder(viewHolder2, i, list);
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder2, i, list);
            return;
        }
        PreferenceItem preferenceItem = (PreferenceItem) getItem(i);
        viewHolder.getBinding().setTitle(this.showTranslation ? preferenceItem.getTitleEng() : preferenceItem.getTitle());
        viewHolder.getBinding().setDescription(this.showTranslation ? preferenceItem.getSummaryEng() : preferenceItem.getSummary());
        viewHolder.getBinding().setCategory(this.showTranslation ? preferenceItem.getCategoryEng() : preferenceItem.getCategory());
    }

    public final boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "handler", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;", "binding", "Lorg/videolan/vlc/databinding/PreferenceItemBinding;", "(Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;Lorg/videolan/vlc/databinding/PreferenceItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/PreferenceItemBinding;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PreferenceItemAdapter.kt */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final PreferenceItemBinding binding;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(ClickHandler clickHandler, PreferenceItemBinding preferenceItemBinding) {
            super(preferenceItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(clickHandler, "handler");
            Intrinsics.checkNotNullParameter(preferenceItemBinding, "binding");
            this.binding = preferenceItemBinding;
            preferenceItemBinding.setHandler(clickHandler);
        }

        public final PreferenceItemBinding getBinding() {
            return this.binding;
        }
    }
}
