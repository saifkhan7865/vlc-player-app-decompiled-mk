package org.videolan.vlc.gui.dialogs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.VideoTrackItemBinding;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001)B%\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001c\u0010\u001f\u001a\u00020\u00122\n\u0010 \u001a\u00060\u0002R\u00020\u00002\u0006\u0010!\u001a\u00020\u001bH\u0016J\u001c\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001bH\u0016J\u0014\u0010&\u001a\u00020\u00122\n\u0010 \u001a\u00060\u0002R\u00020\u0000H\u0016J\u001a\u0010'\u001a\u00020\u00122\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00120\u0011R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00120\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006*"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/adapters/TrackAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/dialogs/adapters/TrackAdapter$ViewHolder;", "tracks", "", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "selectedTrack", "trackTypePrefix", "", "([Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;Ljava/lang/String;)V", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getSelectedTrack", "()Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "setSelectedTrack", "(Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;)V", "trackSelectedListener", "Lkotlin/Function1;", "", "getTrackSelectedListener", "()Lkotlin/jvm/functions/Function1;", "setTrackSelectedListener", "(Lkotlin/jvm/functions/Function1;)V", "getTrackTypePrefix", "()Ljava/lang/String;", "[Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "getItemCount", "", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setOnTrackSelectedListener", "listener", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TrackAdapter.kt */
public final class TrackAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LifecycleAwareScheduler scheduler;
    private VlcTrack selectedTrack;
    public Function1<? super VlcTrack, Unit> trackSelectedListener;
    private final String trackTypePrefix;
    /* access modifiers changed from: private */
    public final VlcTrack[] tracks;

    public TrackAdapter(VlcTrack[] vlcTrackArr, VlcTrack vlcTrack, String str) {
        Intrinsics.checkNotNullParameter(vlcTrackArr, "tracks");
        Intrinsics.checkNotNullParameter(str, "trackTypePrefix");
        this.tracks = vlcTrackArr;
        this.selectedTrack = vlcTrack;
        this.trackTypePrefix = str;
    }

    public final VlcTrack getSelectedTrack() {
        return this.selectedTrack;
    }

    public final String getTrackTypePrefix() {
        return this.trackTypePrefix;
    }

    public final void setSelectedTrack(VlcTrack vlcTrack) {
        this.selectedTrack = vlcTrack;
    }

    public final Function1<VlcTrack, Unit> getTrackSelectedListener() {
        Function1<? super VlcTrack, Unit> function1 = this.trackSelectedListener;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("trackSelectedListener");
        return null;
    }

    public final void setTrackSelectedListener(Function1<? super VlcTrack, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.trackSelectedListener = function1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        VideoTrackItemBinding inflate = VideoTrackItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public final void setOnTrackSelectedListener(Function1<? super VlcTrack, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        setTrackSelectedListener(function1);
    }

    public int getItemCount() {
        return this.tracks.length;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        VlcTrack vlcTrack = this.tracks[i];
        viewHolder.bind(vlcTrack, Intrinsics.areEqual((Object) vlcTrack, (Object) this.selectedTrack));
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

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/adapters/TrackAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/VideoTrackItemBinding;", "(Lorg/videolan/vlc/gui/dialogs/adapters/TrackAdapter;Lorg/videolan/vlc/databinding/VideoTrackItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/VideoTrackItemBinding;", "bind", "", "trackDescription", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "selected", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TrackAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final VideoTrackItemBinding binding;
        final /* synthetic */ TrackAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(TrackAdapter trackAdapter, VideoTrackItemBinding videoTrackItemBinding) {
            super(videoTrackItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(videoTrackItemBinding, "binding");
            this.this$0 = trackAdapter;
            this.binding = videoTrackItemBinding;
            this.itemView.setOnClickListener(new TrackAdapter$ViewHolder$$ExternalSyntheticLambda0(trackAdapter, this));
        }

        public final VideoTrackItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(TrackAdapter trackAdapter, ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(trackAdapter, "this$0");
            Intrinsics.checkNotNullParameter(viewHolder, "this$1");
            int indexOf = ArraysKt.indexOf((T[]) trackAdapter.tracks, trackAdapter.getSelectedTrack());
            trackAdapter.setSelectedTrack(trackAdapter.tracks[viewHolder.getLayoutPosition()]);
            trackAdapter.notifyItemChanged(indexOf);
            trackAdapter.notifyItemChanged(viewHolder.getLayoutPosition());
            trackAdapter.getTrackSelectedListener().invoke(trackAdapter.tracks[viewHolder.getLayoutPosition()]);
        }

        public final void bind(VlcTrack vlcTrack, boolean z) {
            Intrinsics.checkNotNullParameter(vlcTrack, "trackDescription");
            this.binding.setTrack(vlcTrack);
            Context context = this.binding.getRoot().getContext();
            this.binding.setContentDescription(context.getString(R.string.talkback_track, new Object[]{this.this$0.getTrackTypePrefix(), Intrinsics.areEqual((Object) vlcTrack.getId(), (Object) Constants.GROUP_VIDEOS_NONE) ? context.getString(R.string.disable_track) : vlcTrack.getName(), z ? context.getString(R.string.selected) : ""}));
            this.binding.setSelected(Boolean.valueOf(z));
            this.binding.executePendingBindings();
        }
    }
}
