package org.videolan.television.ui.audioplayer;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.R;
import org.videolan.television.databinding.TvPlaylistItemBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 %2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002%&B\u0017\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J*\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u001c\u0010\u001b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0010H\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0015H\u0014J\u000e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lorg/videolan/television/ui/audioplayer/PlaylistAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lorg/videolan/television/ui/audioplayer/PlaylistAdapter$ViewHolder;", "audioPlayerActivity", "Lorg/videolan/television/ui/audioplayer/AudioPlayerActivity;", "model", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "(Lorg/videolan/television/ui/audioplayer/AudioPlayerActivity;Lorg/videolan/vlc/viewmodels/PlaylistModel;)V", "currentPlayingVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "defaultCoverAudio", "Landroid/graphics/drawable/BitmapDrawable;", "getModel", "()Lorg/videolan/vlc/viewmodels/PlaylistModel;", "<set-?>", "", "selectedItem", "getSelectedItem", "()I", "onBindViewHolder", "", "holder", "position", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onDetachedFromRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onUpdateFinished", "setSelection", "pos", "Companion", "ViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistAdapter.kt */
public final class PlaylistAdapter extends DiffUtilAdapter<MediaWrapper, ViewHolder> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/PlaylistAdapter";
    /* access modifiers changed from: private */
    public final AudioPlayerActivity audioPlayerActivity;
    private MiniVisualizer currentPlayingVisu;
    private BitmapDrawable defaultCoverAudio;
    private final PlaylistModel model;
    private int selectedItem = -1;

    public PlaylistAdapter(AudioPlayerActivity audioPlayerActivity2, PlaylistModel playlistModel) {
        Intrinsics.checkNotNullParameter(audioPlayerActivity2, "audioPlayerActivity");
        Intrinsics.checkNotNullParameter(playlistModel, "model");
        this.audioPlayerActivity = audioPlayerActivity2;
        this.model = playlistModel;
        this.defaultCoverAudio = new BitmapDrawable(audioPlayerActivity2.getResources(), ImageLoaderKt.getBitmapFromDrawable(audioPlayerActivity2, R.drawable.ic_song_background));
    }

    public final PlaylistModel getModel() {
        return this.model;
    }

    public final int getSelectedItem() {
        return this.selectedItem;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/audioplayer/PlaylistAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/television/databinding/TvPlaylistItemBinding;", "Landroid/view/View$OnClickListener;", "vdb", "(Lorg/videolan/television/ui/audioplayer/PlaylistAdapter;Lorg/videolan/television/databinding/TvPlaylistItemBinding;)V", "onClick", "", "v", "Landroid/view/View;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<TvPlaylistItemBinding> implements View.OnClickListener {
        final /* synthetic */ PlaylistAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(PlaylistAdapter playlistAdapter, TvPlaylistItemBinding tvPlaylistItemBinding) {
            super(tvPlaylistItemBinding);
            Intrinsics.checkNotNullParameter(tvPlaylistItemBinding, "vdb");
            this.this$0 = playlistAdapter;
            this.itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.setSelection(getLayoutPosition());
            this.this$0.audioPlayerActivity.playSelection();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        TvPlaylistItemBinding inflate = TvPlaylistItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ((TvPlaylistItemBinding) viewHolder.getBinding()).setMedia((MediaWrapper) getDataset().get(i));
        if (this.selectedItem == i) {
            if (this.model.getPlaying()) {
                ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.start();
            } else {
                ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.stop();
            }
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.setVisibility(0);
            ((TvPlaylistItemBinding) viewHolder.getBinding()).coverImage.setVisibility(4);
            this.currentPlayingVisu = ((TvPlaylistItemBinding) viewHolder.getBinding()).playing;
        } else {
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.stop();
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.setVisibility(4);
            ((TvPlaylistItemBinding) viewHolder.getBinding()).coverImage.setVisibility(0);
        }
        ((TvPlaylistItemBinding) viewHolder.getBinding()).setScaleType(ImageView.ScaleType.CENTER_CROP);
        ((TvPlaylistItemBinding) viewHolder.getBinding()).setCover(this.defaultCoverAudio);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i, list);
            return;
        }
        Object obj = list.get(0);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (!booleanValue || !this.model.getPlaying()) {
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.stop();
        } else {
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.start();
        }
        if (booleanValue) {
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.setVisibility(0);
            ((TvPlaylistItemBinding) viewHolder.getBinding()).coverImage.setVisibility(4);
        } else {
            ((TvPlaylistItemBinding) viewHolder.getBinding()).playing.setVisibility(4);
            ((TvPlaylistItemBinding) viewHolder.getBinding()).coverImage.setVisibility(0);
        }
        ((TvPlaylistItemBinding) viewHolder.getBinding()).setScaleType(ImageView.ScaleType.CENTER_CROP);
        ((TvPlaylistItemBinding) viewHolder.getBinding()).setCover(this.defaultCoverAudio);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.currentPlayingVisu = null;
    }

    public final void setSelection(int i) {
        int i2 = this.selectedItem;
        if (i != i2) {
            this.selectedItem = i;
            if (i2 != -1) {
                notifyItemChanged(i2, false);
            }
            if (i != -1) {
                notifyItemChanged(this.selectedItem, true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateFinished() {
        this.audioPlayerActivity.onUpdateFinished();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/audioplayer/PlaylistAdapter$Companion;", "", "()V", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistAdapter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
