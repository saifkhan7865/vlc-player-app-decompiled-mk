package org.videolan.vlc.gui.audio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.BundleKt;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PlaylistItemBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.MarqueeViewHolder;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.interfaces.SwipeDragHelperAdapter;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.MediaItemDiffCallback;
import org.videolan.vlc.util.SchedulerCallback;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\u00020\u00042\u00020\u0005:\u0002PQB\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020/H\u0014J\u0010\u00100\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0017J\b\u00101\u001a\u00020\nH\u0016J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0016J\u001c\u00106\u001a\u0002032\n\u00107\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u00108\u001a\u00060\u0003R\u00020\u00002\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\nH\u0016J\u0010\u0010<\u001a\u0002032\u0006\u00104\u001a\u000205H\u0016J\u0010\u0010=\u001a\u0002032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\nH\u0016J\u0018\u0010A\u001a\u0002032\u0006\u0010B\u001a\u00020\n2\u0006\u0010C\u001a\u00020\nH\u0016J\u0018\u0010D\u001a\u0002032\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0016J\b\u0010I\u001a\u000203H\u0014J\u0014\u0010J\u001a\u0002032\n\u00107\u001a\u00060\u0003R\u00020\u0000H\u0016J\u0010\u0010K\u001a\u0002032\u0006\u0010\t\u001a\u00020\nH\u0007J\u000e\u0010L\u001a\u0002032\u0006\u0010M\u001a\u00020NJ\u000e\u0010O\u001a\u0002032\u0006\u0010\"\u001a\u00020#R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u0014\u0010\u0018\u001a\u00020\u00198VX\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\u001dX.¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001f\"\u0004\b&\u0010!R$\u0010(\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\r\"\u0004\b*\u0010\u000fR\u001a\u0010+\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\r\"\u0004\b-\u0010\u000f¨\u0006R"}, d2 = {"Lorg/videolan/vlc/gui/audio/PlaylistAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lorg/videolan/vlc/gui/audio/PlaylistAdapter$ViewHolder;", "Lorg/videolan/vlc/interfaces/SwipeDragHelperAdapter;", "Lorg/videolan/vlc/util/SchedulerCallback;", "player", "Lorg/videolan/vlc/gui/audio/PlaylistAdapter$IPlayer;", "(Lorg/videolan/vlc/gui/audio/PlaylistAdapter$IPlayer;)V", "position", "", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "currentPlayingVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "defaultCoverAudio", "Landroid/graphics/drawable/BitmapDrawable;", "defaultCoverVideo", "from", "getFrom", "setFrom", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "marqueeScheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getMarqueeScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setMarqueeScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "model", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "scheduler", "getScheduler", "setScheduler", "value", "stopAfter", "getStopAfter", "setStopAfter", "to", "getTo", "setTo", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "getItem", "getItemCount", "onAttachedToRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onDetachedFromRecyclerView", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "onItemMoved", "dragFrom", "dragTo", "onTaskTriggered", "id", "", "data", "Landroid/os/Bundle;", "onUpdateFinished", "onViewRecycled", "remove", "setCurrentlyPlaying", "playing", "", "setModel", "IPlayer", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistAdapter.kt */
public final class PlaylistAdapter extends DiffUtilAdapter<MediaWrapper, ViewHolder> implements SwipeDragHelperAdapter, SchedulerCallback {
    private int currentIndex;
    private MiniVisualizer currentPlayingVisu;
    private BitmapDrawable defaultCoverAudio;
    private BitmapDrawable defaultCoverVideo;
    private int from;
    private LifecycleAwareScheduler marqueeScheduler;
    /* access modifiers changed from: private */
    public PlaylistModel model;
    /* access modifiers changed from: private */
    public final IPlayer player;
    public LifecycleAwareScheduler scheduler;
    private int stopAfter = -1;
    private int to;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/audio/PlaylistAdapter$IPlayer;", "", "getLifeCycle", "Landroidx/lifecycle/Lifecycle;", "onPopupMenu", "", "view", "Landroid/view/View;", "position", "", "item", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "onSelectionSet", "playItem", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistAdapter.kt */
    public interface IPlayer {
        Lifecycle getLifeCycle();

        void onPopupMenu(View view, int i, MediaWrapper mediaWrapper);

        void onSelectionSet(int i);

        void playItem(int i, MediaWrapper mediaWrapper);
    }

    public void onItemMoved(int i, int i2) {
    }

    public PlaylistAdapter(IPlayer iPlayer) {
        Context context;
        Intrinsics.checkNotNullParameter(iPlayer, "player");
        this.player = iPlayer;
        if (iPlayer instanceof Context) {
            context = (Context) iPlayer;
        } else if (iPlayer instanceof Fragment) {
            context = ((Fragment) iPlayer).requireContext();
        } else {
            context = AppContextProvider.INSTANCE.getAppContext();
        }
        Intrinsics.checkNotNull(context);
        this.defaultCoverAudio = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_song_background));
        this.defaultCoverVideo = UiTools.INSTANCE.getDefaultVideoDrawable(context);
        setScheduler(new LifecycleAwareScheduler(this));
        this.from = -1;
        this.to = -1;
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public final LifecycleAwareScheduler getScheduler() {
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            return lifecycleAwareScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        return null;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    public final LifecycleAwareScheduler getMarqueeScheduler() {
        return this.marqueeScheduler;
    }

    public final void setMarqueeScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        this.marqueeScheduler = lifecycleAwareScheduler;
    }

    public final int getStopAfter() {
        return this.stopAfter;
    }

    public final void setStopAfter(int i) {
        int i2 = this.stopAfter;
        this.stopAfter = i;
        if (1 <= i2 && i2 < getItemCount()) {
            notifyItemChanged(i2);
        }
        if (1 <= i && i < getItemCount()) {
            notifyItemChanged(i);
        }
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        if (i != this.currentIndex && i < getItemCount()) {
            int i2 = this.currentIndex;
            this.currentIndex = i;
            if (i2 >= 0) {
                notifyItemChanged(i2);
            }
            if (i >= 0) {
                notifyItemChanged(i);
                this.player.onSelectionSet(i);
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.playlist_item, viewGroup, false);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        MediaWrapper item = getItem(i);
        viewHolder.getBinding().setMedia(item);
        viewHolder.getBinding().setSubTitle(MediaUtils.INSTANCE.getMediaSubtitle(item));
        viewHolder.getBinding().setScaleType(ImageView.ScaleType.CENTER_CROP);
        boolean z = false;
        viewHolder.getBinding().stopAfter.setVisibility(this.stopAfter == i ? 0 : 8);
        viewHolder.getBinding().setStopAfterThis(Boolean.valueOf(i == this.stopAfter));
        if (this.currentIndex == i) {
            PlaylistModel playlistModel = this.model;
            if (playlistModel == null || playlistModel.getPlaying()) {
                viewHolder.getBinding().playing.start();
            } else {
                viewHolder.getBinding().playing.stop();
            }
            viewHolder.getBinding().playing.setVisibility(0);
            viewHolder.getBinding().coverImage.setVisibility(4);
            viewHolder.getBinding().audioItemTitle.setTypeface((Typeface) null, 1);
            viewHolder.getBinding().audioItemSubtitle.setTypeface((Typeface) null, 1);
            this.currentPlayingVisu = viewHolder.getBinding().playing;
        } else {
            viewHolder.getBinding().playing.stop();
            viewHolder.getBinding().playing.setVisibility(4);
            viewHolder.getBinding().audioItemTitle.setTypeface((Typeface) null);
            viewHolder.getBinding().coverImage.setVisibility(0);
        }
        if (item.getType() == 0) {
            ViewGroup.LayoutParams layoutParams = viewHolder.getBinding().coverImage.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ((ConstraintLayout.LayoutParams) layoutParams).dimensionRatio = "16:10";
            viewHolder.getBinding().setCover(this.defaultCoverVideo);
        } else {
            ViewGroup.LayoutParams layoutParams2 = viewHolder.getBinding().coverImage.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ((ConstraintLayout.LayoutParams) layoutParams2).dimensionRatio = DiskLruCache.VERSION_1;
            viewHolder.getBinding().setCover(this.defaultCoverAudio);
        }
        UiTools uiTools = UiTools.INSTANCE;
        Context context = viewHolder.getBinding().itemDelete.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (uiTools.isTablet(context) || AndroidDevices.INSTANCE.isTv()) {
            z = true;
        }
        View view = viewHolder.getBinding().itemDelete;
        if (z) {
            KotlinExtensionsKt.setVisible(view);
        } else {
            KotlinExtensionsKt.setGone(view);
        }
        View view2 = viewHolder.getBinding().itemMoveDown;
        if (z) {
            KotlinExtensionsKt.setVisible(view2);
        } else {
            KotlinExtensionsKt.setGone(view2);
        }
        View view3 = viewHolder.getBinding().itemMoveUp;
        if (z) {
            KotlinExtensionsKt.setVisible(view3);
        } else {
            KotlinExtensionsKt.setGone(view3);
        }
        viewHolder.getBinding().executePendingBindings();
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        LifecycleAwareScheduler lifecycleAwareScheduler = this.marqueeScheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction("");
        }
        MiniVisualizer miniVisualizer = this.currentPlayingVisu;
        if (miniVisualizer != null) {
            miniVisualizer.stop();
        }
        this.currentPlayingVisu = null;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            this.marqueeScheduler = UiToolsKt.enableMarqueeEffect(recyclerView);
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.marqueeScheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
        }
        super.onViewRecycled(viewHolder);
    }

    public int getItemCount() {
        return getDataset().size();
    }

    public MediaWrapper getItem(int i) {
        return (MediaWrapper) getDataset().get(i);
    }

    /* access modifiers changed from: protected */
    public void onUpdateFinished() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel != null) {
            setCurrentIndex(playlistModel.getSelection());
        }
    }

    public final void remove(int i) {
        PlaylistModel playlistModel = this.model;
        if (playlistModel != null) {
            playlistModel.remove(i);
        }
    }

    public void onItemMove(int i, int i2) {
        Collections.swap(getDataset(), i, i2);
        notifyItemMoved(i, i2);
        getScheduler().startAction("action_move", BundleKt.bundleOf(TuplesKt.to(TypedValues.TransitionType.S_FROM, Integer.valueOf(i)), TuplesKt.to(TypedValues.TransitionType.S_TO, Integer.valueOf(i2))));
    }

    public void onItemDismiss(int i) {
        MediaWrapper item = getItem(i);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = AppContextProvider.INSTANCE.getAppResources().getString(R.string.remove_playlist_item);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String format = String.format(string, Arrays.copyOf(new Object[]{item.getTitle()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        IPlayer iPlayer = this.player;
        if (iPlayer instanceof Fragment) {
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = ((Fragment) this.player).requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            uiTools.snackerWithCancel(requireActivity, format, true, PlaylistAdapter$onItemDismiss$1.INSTANCE, new PlaylistAdapter$onItemDismiss$2(this, i, item));
        } else if (iPlayer instanceof Activity) {
            UiTools.snackerWithCancel$default(UiTools.INSTANCE, (Activity) this.player, format, false, PlaylistAdapter$onItemDismiss$3.INSTANCE, new PlaylistAdapter$onItemDismiss$4(this, i, item), 4, (Object) null);
        }
        remove(i);
    }

    public final void setModel(PlaylistModel playlistModel) {
        Intrinsics.checkNotNullParameter(playlistModel, "model");
        this.model = playlistModel;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/audio/PlaylistAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "v", "Landroid/view/View;", "(Lorg/videolan/vlc/gui/audio/PlaylistAdapter;Landroid/view/View;)V", "binding", "Lorg/videolan/vlc/databinding/PlaylistItemBinding;", "getBinding", "()Lorg/videolan/vlc/databinding/PlaylistItemBinding;", "setBinding", "(Lorg/videolan/vlc/databinding/PlaylistItemBinding;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "onClick", "", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "onDeleteClick", "onMoreClick", "onMoveDownClick", "onMoveUpClick", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder implements MarqueeViewHolder {
        private PlaylistItemBinding binding;
        final /* synthetic */ PlaylistAdapter this$0;
        private final TextView titleView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(PlaylistAdapter playlistAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0 = playlistAdapter;
            ViewDataBinding bind = DataBindingUtil.bind(view);
            Intrinsics.checkNotNull(bind);
            PlaylistItemBinding playlistItemBinding = (PlaylistItemBinding) bind;
            this.binding = playlistItemBinding;
            TextView textView = playlistItemBinding.audioItemTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "audioItemTitle");
            this.titleView = textView;
            this.binding.setHolder(this);
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new PlaylistAdapter$ViewHolder$$ExternalSyntheticLambda0(this));
            }
        }

        public final PlaylistItemBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(PlaylistItemBinding playlistItemBinding) {
            Intrinsics.checkNotNullParameter(playlistItemBinding, "<set-?>");
            this.binding = playlistItemBinding;
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(viewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            viewHolder.onMoreClick(view);
            return true;
        }

        public final void onClick(View view, MediaWrapper mediaWrapper) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaWrapper, "media");
            this.this$0.player.playItem(getLayoutPosition(), mediaWrapper);
        }

        public final void onMoreClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            this.this$0.player.onPopupMenu(view, layoutPosition, this.this$0.getItem(layoutPosition));
        }

        public final void onDeleteClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.onItemDismiss(getLayoutPosition());
        }

        public final void onMoveUpClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            if (getLayoutPosition() != 0) {
                this.this$0.onItemMove(getLayoutPosition(), getLayoutPosition() - 1);
            }
        }

        public final void onMoveDownClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            if (getLayoutPosition() != this.this$0.getItemCount() - 1) {
                this.this$0.onItemMove(getLayoutPosition(), getLayoutPosition() + 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public DiffUtilAdapter.DiffCallback<MediaWrapper> createCB() {
        return new MediaItemDiffCallback<>();
    }

    public final void setCurrentlyPlaying(boolean z) {
        if (z) {
            MiniVisualizer miniVisualizer = this.currentPlayingVisu;
            if (miniVisualizer != null) {
                miniVisualizer.start();
                return;
            }
            return;
        }
        MiniVisualizer miniVisualizer2 = this.currentPlayingVisu;
        if (miniVisualizer2 != null) {
            miniVisualizer2.stop();
        }
    }

    public final int getFrom() {
        return this.from;
    }

    public final void setFrom(int i) {
        this.from = i;
    }

    public final int getTo() {
        return this.to;
    }

    public final void setTo(int i) {
        this.to = i;
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        PlaylistModel playlistModel;
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (Intrinsics.areEqual((Object) str, (Object) "action_move")) {
            getScheduler().cancelAction("action_moved");
            if (this.from == -1) {
                this.from = bundle.getInt(TypedValues.TransitionType.S_FROM);
            }
            this.to = bundle.getInt(TypedValues.TransitionType.S_TO);
            LifecycleAwareScheduler.scheduleAction$default(getScheduler(), "action_moved", 1000, (Bundle) null, 4, (Object) null);
        } else if (Intrinsics.areEqual((Object) str, (Object) "action_moved") && (playlistModel = this.model) != null) {
            int i = this.to;
            int i2 = this.from;
            if (i > i2) {
                this.to = i + 1;
            }
            playlistModel.move(i2, this.to);
            this.to = -1;
            this.from = -1;
        }
    }

    public Lifecycle getLifecycle() {
        return this.player.getLifeCycle();
    }
}
