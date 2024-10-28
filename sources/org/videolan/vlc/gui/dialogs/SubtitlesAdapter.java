package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.SubtitleDownloadItemBinding;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0016\u0010\u0014\u001a\u00020\r2\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bR\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SubtitlesAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/dialogs/SubtitlesAdapter$ViewHolder;", "eventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/dialogs/SubtitleEvent;", "(Lkotlinx/coroutines/channels/SendChannel;)V", "dataset", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setList", "list", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitlesAdapter.kt */
public final class SubtitlesAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public List<SubtitleItem> dataset;
    /* access modifiers changed from: private */
    public final SendChannel<SubtitleEvent> eventActor;

    public SubtitlesAdapter(SendChannel<? super SubtitleEvent> sendChannel) {
        Intrinsics.checkNotNullParameter(sendChannel, "eventActor");
        this.eventActor = sendChannel;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        SubtitleDownloadItemBinding inflate = SubtitleDownloadItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        List<SubtitleItem> list = this.dataset;
        viewHolder.bind(list != null ? list.get(i) : null);
    }

    public final void setList(List<SubtitleItem> list) {
        this.dataset = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        List<SubtitleItem> list = this.dataset;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SubtitlesAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/SubtitleDownloadItemBinding;", "(Lorg/videolan/vlc/gui/dialogs/SubtitlesAdapter;Lorg/videolan/vlc/databinding/SubtitleDownloadItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/SubtitleDownloadItemBinding;", "bind", "", "subtitleItem", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SubtitlesAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final SubtitleDownloadItemBinding binding;
        final /* synthetic */ SubtitlesAdapter this$0;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* compiled from: SubtitlesAdapter.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    org.videolan.vlc.gui.dialogs.State[] r0 = org.videolan.vlc.gui.dialogs.State.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    org.videolan.vlc.gui.dialogs.State r1 = org.videolan.vlc.gui.dialogs.State.Downloaded     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    org.videolan.vlc.gui.dialogs.State r1 = org.videolan.vlc.gui.dialogs.State.NotDownloaded     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.SubtitlesAdapter.ViewHolder.WhenMappings.<clinit>():void");
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(SubtitlesAdapter subtitlesAdapter, SubtitleDownloadItemBinding subtitleDownloadItemBinding) {
            super(subtitleDownloadItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(subtitleDownloadItemBinding, "binding");
            this.this$0 = subtitlesAdapter;
            this.binding = subtitleDownloadItemBinding;
            this.itemView.setOnClickListener(new SubtitlesAdapter$ViewHolder$$ExternalSyntheticLambda1(subtitlesAdapter, this));
            this.itemView.setOnLongClickListener(new SubtitlesAdapter$ViewHolder$$ExternalSyntheticLambda2(subtitlesAdapter, this));
        }

        public final SubtitleDownloadItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(SubtitlesAdapter subtitlesAdapter, ViewHolder viewHolder, View view) {
            SubtitleItem subtitleItem;
            Intrinsics.checkNotNullParameter(subtitlesAdapter, "this$0");
            Intrinsics.checkNotNullParameter(viewHolder, "this$1");
            List access$getDataset$p = subtitlesAdapter.dataset;
            if (access$getDataset$p != null && (subtitleItem = (SubtitleItem) access$getDataset$p.get(viewHolder.getLayoutPosition())) != null && !subtitlesAdapter.eventActor.isClosedForSend()) {
                subtitlesAdapter.eventActor.m1139trySendJP2dKIU(new SubtitleClick(subtitleItem));
            }
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$3(SubtitlesAdapter subtitlesAdapter, ViewHolder viewHolder, View view) {
            SubtitleItem subtitleItem;
            Intrinsics.checkNotNullParameter(subtitlesAdapter, "this$0");
            Intrinsics.checkNotNullParameter(viewHolder, "this$1");
            List access$getDataset$p = subtitlesAdapter.dataset;
            if (access$getDataset$p == null || (subtitleItem = (SubtitleItem) access$getDataset$p.get(viewHolder.getLayoutPosition())) == null || subtitlesAdapter.eventActor.isClosedForSend()) {
                return true;
            }
            subtitlesAdapter.eventActor.m1139trySendJP2dKIU(new SubtitleLongClick(subtitleItem));
            return true;
        }

        public final void bind(SubtitleItem subtitleItem) {
            int i;
            String str;
            String movieReleaseName;
            this.binding.setSubtitleItem(subtitleItem);
            this.binding.downloadSub.setOnClickListener(new SubtitlesAdapter$ViewHolder$$ExternalSyntheticLambda0(this));
            this.binding.executePendingBindings();
            Context context = this.binding.getRoot().getContext();
            State state = subtitleItem != null ? subtitleItem.getState() : null;
            int i2 = state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i2 == 1) {
                i = R.string.downloaded;
            } else if (i2 != 2) {
                i = R.string.downloading;
            } else {
                i = R.string.not_downloaded;
            }
            String string = context.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            View view = this.itemView;
            int i3 = R.string.talkback_subtitle_dowload_item;
            String str2 = "";
            if (subtitleItem == null || (str = subtitleItem.getSubLanguageID()) == null) {
                str = str2;
            }
            String displayLanguage = new Locale(str).getDisplayLanguage();
            if (!(subtitleItem == null || (movieReleaseName = subtitleItem.getMovieReleaseName()) == null)) {
                str2 = movieReleaseName;
            }
            view.setContentDescription(context.getString(i3, new Object[]{displayLanguage, string, str2}));
        }

        /* access modifiers changed from: private */
        public static final void bind$lambda$4(ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(viewHolder, "this$0");
            viewHolder.itemView.performClick();
        }
    }
}
