package org.videolan.vlc.gui.video;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.tools.Strings;
import org.videolan.vlc.R;
import org.videolan.vlc.util.LocaleUtil;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0003J$\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J$\u0010\u0012\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J$\u0010\u0013\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\n2\n\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u001c\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0014\u0010\u001e\u001a\u00020\n2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/video/MediaInfoAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/video/MediaInfoAdapter$ViewHolder;", "()V", "dataset", "", "Lorg/videolan/libvlc/interfaces/IMedia$Track;", "inflater", "Landroid/view/LayoutInflater;", "appendAudio", "", "textBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "res", "Landroid/content/res/Resources;", "track", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", "appendCommon", "appendVideo", "Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setTracks", "tracks", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaInfoAdapter.kt */
public final class MediaInfoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<? extends IMedia.Track> dataset;
    private LayoutInflater inflater;

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (this.inflater == null) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            Intrinsics.checkNotNullExpressionValue(from, "from(...)");
            this.inflater = from;
        }
        LayoutInflater layoutInflater = this.inflater;
        if (layoutInflater == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inflater");
            layoutInflater = null;
        }
        View inflate = layoutInflater.inflate(R.layout.info_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        List<? extends IMedia.Track> list = this.dataset;
        Intrinsics.checkNotNull(list);
        IMedia.Track track = (IMedia.Track) list.get(i);
        StringBuilder sb = new StringBuilder();
        Resources resources = viewHolder.itemView.getContext().getResources();
        int i2 = track.type;
        if (i2 == 0) {
            str2 = resources.getString(R.string.track_audio);
            Intrinsics.checkNotNullExpressionValue(str2, "getString(...)");
            Intrinsics.checkNotNull(resources);
            appendCommon(sb, resources, track);
            Intrinsics.checkNotNull(track, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMedia.AudioTrack");
            appendAudio(sb, resources, (IMedia.AudioTrack) track);
        } else if (i2 == 1) {
            str2 = resources.getString(R.string.track_video);
            Intrinsics.checkNotNullExpressionValue(str2, "getString(...)");
            Intrinsics.checkNotNull(resources);
            appendCommon(sb, resources, track);
            Intrinsics.checkNotNull(track, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMedia.VideoTrack");
            appendVideo(sb, resources, (IMedia.VideoTrack) track);
        } else if (i2 != 2) {
            str = resources.getString(R.string.track_unknown);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
            viewHolder.getTitle().setText(str);
            viewHolder.getText().setText(sb.toString());
        } else {
            str2 = resources.getString(R.string.track_text);
            Intrinsics.checkNotNullExpressionValue(str2, "getString(...)");
            Intrinsics.checkNotNull(resources);
            appendCommon(sb, resources, track);
        }
        str = str2;
        viewHolder.getTitle().setText(str);
        viewHolder.getText().setText(sb.toString());
    }

    public int getItemCount() {
        List<? extends IMedia.Track> list = this.dataset;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final void setTracks(List<? extends IMedia.Track> list) {
        Intrinsics.checkNotNullParameter(list, "tracks");
        int itemCount = getItemCount();
        this.dataset = list;
        if (itemCount > 0) {
            notifyItemRangeRemoved(0, itemCount - 1);
        }
        notifyItemRangeInserted(0, list.size());
    }

    private final void appendCommon(StringBuilder sb, Resources resources, IMedia.Track track) {
        if (track.bitrate != 0) {
            sb.append(resources.getString(R.string.track_bitrate_info, new Object[]{Strings.readableSize((long) track.bitrate)}));
        }
        sb.append(resources.getString(R.string.track_codec_info, new Object[]{track.codec}));
        if (track.language != null && !StringsKt.equals(track.language, "und", true)) {
            int i = R.string.track_language_info;
            LocaleUtil localeUtil = LocaleUtil.INSTANCE;
            String str = track.language;
            Intrinsics.checkNotNullExpressionValue(str, "language");
            sb.append(resources.getString(i, new Object[]{localeUtil.getLocaleName(str)}));
        }
    }

    private final void appendAudio(StringBuilder sb, Resources resources, IMedia.AudioTrack audioTrack) {
        sb.append(resources.getQuantityString(R.plurals.track_channels_info_quantity, audioTrack.channels, new Object[]{Integer.valueOf(audioTrack.channels)}));
        sb.append(resources.getString(R.string.track_samplerate_info, new Object[]{Integer.valueOf(audioTrack.rate)}));
    }

    private final void appendVideo(StringBuilder sb, Resources resources, IMedia.VideoTrack videoTrack) {
        double d = (double) videoTrack.frameRateNum;
        double d2 = (double) videoTrack.frameRateDen;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d / d2;
        if (!(videoTrack.width == 0 || videoTrack.height == 0)) {
            sb.append(resources.getString(R.string.track_resolution_info, new Object[]{Integer.valueOf(videoTrack.width), Integer.valueOf(videoTrack.height)}));
        }
        if (!Double.isNaN(d3)) {
            sb.append(resources.getString(R.string.track_framerate_info, new Object[]{Double.valueOf(d3)}));
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/video/MediaInfoAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lorg/videolan/vlc/gui/video/MediaInfoAdapter;Landroid/view/View;)V", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "title", "getTitle", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaInfoAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text;
        final /* synthetic */ MediaInfoAdapter this$0;
        private final TextView title;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(MediaInfoAdapter mediaInfoAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            this.this$0 = mediaInfoAdapter;
            View findViewById = view.findViewById(R.id.title);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.title = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.subtitle);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.text = (TextView) findViewById2;
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final TextView getText() {
            return this.text;
        }
    }
}
