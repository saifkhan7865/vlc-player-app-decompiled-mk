package org.videolan.vlc.gui.audio;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;
import org.videolan.vlc.BR;
import org.videolan.vlc.databinding.AudioAlbumTrackItemBinding;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\"\u0010\n\u001a\f\u0012\u0004\u0012\u00020\f0\u000bR\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioAlbumTracksAdapter;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "type", "", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "listEventsHandler", "Lorg/videolan/vlc/interfaces/IListEventsHandler;", "(ILorg/videolan/vlc/interfaces/IEventsHandler;Lorg/videolan/vlc/interfaces/IListEventsHandler;)V", "onCreateViewHolder", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$AbstractMediaItemViewHolder;", "Landroidx/databinding/ViewDataBinding;", "parent", "Landroid/view/ViewGroup;", "viewType", "TrackItemViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioAlbumTracksAdapter.kt */
public final class AudioAlbumTracksAdapter extends AudioBrowserAdapter {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioAlbumTracksAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler) {
        this(i, iEventsHandler, (IListEventsHandler) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AudioAlbumTracksAdapter(int i, IEventsHandler iEventsHandler, IListEventsHandler iListEventsHandler, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, iEventsHandler, (i2 & 4) != 0 ? null : iListEventsHandler);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AudioAlbumTracksAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler, IListEventsHandler iListEventsHandler) {
        super(i, iEventsHandler, iListEventsHandler, false, 0, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
    }

    public AudioBrowserAdapter.AbstractMediaItemViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (!inflaterInitialized()) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            Intrinsics.checkNotNullExpressionValue(from, "from(...)");
            setInflater(from);
        }
        AudioAlbumTrackItemBinding inflate = AudioAlbumTrackItemBinding.inflate(getInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new TrackItemViewHolder(this, inflate);
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001R\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\u0012\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioAlbumTracksAdapter$TrackItemViewHolder;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/vlc/databinding/AudioAlbumTrackItemBinding;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "binding", "(Lorg/videolan/vlc/gui/audio/AudioAlbumTracksAdapter;Lorg/videolan/vlc/databinding/AudioAlbumTrackItemBinding;)V", "onTouchListener", "Landroid/view/View$OnTouchListener;", "getOnTouchListener", "()Landroid/view/View$OnTouchListener;", "setOnTouchListener", "(Landroid/view/View$OnTouchListener;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "changePlayingVisibility", "", "isCurrent", "", "getMiniVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "recycle", "selectView", "selected", "setItem", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioAlbumTracksAdapter.kt */
    public final class TrackItemViewHolder extends AudioBrowserAdapter.AbstractMediaItemViewHolder<AudioAlbumTrackItemBinding> {
        private View.OnTouchListener onTouchListener;
        final /* synthetic */ AudioAlbumTracksAdapter this$0;
        private final TextView titleView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TrackItemViewHolder(final AudioAlbumTracksAdapter audioAlbumTracksAdapter, AudioAlbumTrackItemBinding audioAlbumTrackItemBinding) {
            super(audioAlbumTracksAdapter, audioAlbumTrackItemBinding);
            Intrinsics.checkNotNullParameter(audioAlbumTrackItemBinding, "binding");
            this.this$0 = audioAlbumTracksAdapter;
            TextView textView = audioAlbumTrackItemBinding.title;
            Intrinsics.checkNotNullExpressionValue(textView, "title");
            this.titleView = textView;
            audioAlbumTrackItemBinding.setHolder(this);
            BitmapDrawable defaultCover = audioAlbumTracksAdapter.getDefaultCover();
            if (defaultCover != null) {
                audioAlbumTrackItemBinding.setCover(defaultCover);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new AudioAlbumTracksAdapter$TrackItemViewHolder$$ExternalSyntheticLambda0(this));
            }
            this.onTouchListener = new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Intrinsics.checkNotNullParameter(view, "v");
                    Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
                    if (audioAlbumTracksAdapter.getListEventsHandler() == null || audioAlbumTracksAdapter.getMultiSelectHelper().getSelectionCount() != 0 || MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    audioAlbumTracksAdapter.getListEventsHandler().onStartDrag(this);
                    return true;
                }
            };
            audioAlbumTrackItemBinding.setImageWidth(audioAlbumTracksAdapter.getListImageWidth());
        }

        public final View.OnTouchListener getOnTouchListener() {
            return this.onTouchListener;
        }

        public final void setOnTouchListener(View.OnTouchListener onTouchListener2) {
            Intrinsics.checkNotNullParameter(onTouchListener2, "<set-?>");
            this.onTouchListener = onTouchListener2;
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$1(TrackItemViewHolder trackItemViewHolder, View view) {
            Intrinsics.checkNotNullParameter(trackItemViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            trackItemViewHolder.onMoreClick(view);
            return true;
        }

        public void selectView(boolean z) {
            ((AudioAlbumTrackItemBinding) getBinding()).setVariable(BR.selected, Boolean.valueOf(z));
            ((AudioAlbumTrackItemBinding) getBinding()).itemMore.setVisibility(this.this$0.getMultiSelectHelper().getInActionMode() ? 4 : 0);
        }

        public void setItem(MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            ((AudioAlbumTrackItemBinding) getBinding()).setItem(mediaWrapper);
            if (mediaWrapper.getTrackNumber() <= 0 || !Settings.INSTANCE.getShowTrackNumber()) {
                ((AudioAlbumTrackItemBinding) getBinding()).trackNumber.setVisibility(8);
            } else {
                TextView textView = ((AudioAlbumTrackItemBinding) getBinding()).trackNumber;
                StringBuilder sb = new StringBuilder();
                sb.append(mediaWrapper.getTrackNumber());
                sb.append('.');
                textView.setText(sb.toString());
                ((AudioAlbumTrackItemBinding) getBinding()).trackNumber.setVisibility(0);
            }
            ((AudioAlbumTrackItemBinding) getBinding()).subtitle.setText(MediaUtils.INSTANCE.getMediaSubtitle(mediaWrapper));
        }

        public void recycle() {
            ((AudioAlbumTrackItemBinding) getBinding()).setCover(this.this$0.getDefaultCover());
            ((AudioAlbumTrackItemBinding) getBinding()).title.setSelected(false);
        }

        public MiniVisualizer getMiniVisu() {
            MiniVisualizer miniVisualizer = ((AudioAlbumTrackItemBinding) getBinding()).playing;
            Intrinsics.checkNotNullExpressionValue(miniVisualizer, "playing");
            return miniVisualizer;
        }

        public void changePlayingVisibility(boolean z) {
            ((AudioAlbumTrackItemBinding) getBinding()).trackNumber.setVisibility(z ? 4 : 0);
        }
    }
}
