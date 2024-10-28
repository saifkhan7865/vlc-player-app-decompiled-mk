package org.videolan.television.ui.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.R;
import org.videolan.television.databinding.ActivityMediaListTvItemBinding;
import org.videolan.television.ui.TvFocusableAdapter;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.interfaces.ITVEventsHandler;
import org.videolan.vlc.util.ModelsHelper;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\u00020\u0004:\u0001!B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u001c\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u001c\u0010\u001c\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u0012\u0010 \u001a\u00020\u00192\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/videolan/television/ui/details/MediaListAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lorg/videolan/television/ui/details/MediaListAdapter$MediaListViewHolder;", "Lorg/videolan/television/ui/TvFocusableAdapter;", "type", "", "listener", "Lorg/videolan/vlc/interfaces/ITVEventsHandler;", "(ILorg/videolan/vlc/interfaces/ITVEventsHandler;)V", "focusListener", "Lorg/videolan/resources/interfaces/FocusListener;", "lastMovedItemFrom", "getLastMovedItemFrom", "()I", "setLastMovedItemFrom", "(I)V", "lastMovedItemTo", "getLastMovedItemTo", "setLastMovedItemTo", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "detectMoves", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnFocusChangeListener", "MediaListViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaListAdapter.kt */
public final class MediaListAdapter extends DiffUtilAdapter<MediaWrapper, MediaListViewHolder> implements TvFocusableAdapter {
    /* access modifiers changed from: private */
    public FocusListener focusListener;
    private int lastMovedItemFrom = -1;
    private int lastMovedItemTo = -1;
    /* access modifiers changed from: private */
    public final ITVEventsHandler listener;
    private final int type;

    /* access modifiers changed from: protected */
    public boolean detectMoves() {
        return true;
    }

    public MediaListAdapter(int i, ITVEventsHandler iTVEventsHandler) {
        Intrinsics.checkNotNullParameter(iTVEventsHandler, "listener");
        this.type = i;
        this.listener = iTVEventsHandler;
    }

    public void setOnFocusChangeListener(FocusListener focusListener2) {
        this.focusListener = focusListener2;
    }

    public final int getLastMovedItemFrom() {
        return this.lastMovedItemFrom;
    }

    public final void setLastMovedItemFrom(int i) {
        this.lastMovedItemFrom = i;
    }

    public final int getLastMovedItemTo() {
        return this.lastMovedItemTo;
    }

    public final void setLastMovedItemTo(int i) {
        this.lastMovedItemTo = i;
    }

    public MediaListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ActivityMediaListTvItemBinding inflate = ActivityMediaListTvItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new MediaListViewHolder(this, inflate, this.type);
    }

    public void onBindViewHolder(MediaListViewHolder mediaListViewHolder, int i) {
        String str;
        Intrinsics.checkNotNullParameter(mediaListViewHolder, "holder");
        MediaWrapper mediaWrapper = (MediaWrapper) getItem(i);
        ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).setItem(mediaWrapper);
        ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).setHolder(mediaListViewHolder);
        ActivityMediaListTvItemBinding activityMediaListTvItemBinding = (ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding();
        MediaLibraryItem mediaLibraryItem = mediaWrapper;
        if (ModelsHelper.INSTANCE.getDiscNumberString(mediaLibraryItem) != null) {
            str = mediaWrapper.getArtist() + " · " + ModelsHelper.INSTANCE.getDiscNumberString(mediaLibraryItem);
        } else {
            str = mediaWrapper.getArtist();
        }
        activityMediaListTvItemBinding.setSubtitle(str);
        int i2 = this.type == 2 ? 8 : 0;
        int i3 = 4;
        ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).itemMoveDown.setVisibility((i2 == 0 && i == getItemCount() - 1) ? 4 : i2);
        AppCompatImageButton appCompatImageButton = ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).itemMoveUp;
        if (!(i2 == 0 && i == 0)) {
            i3 = i2;
        }
        appCompatImageButton.setVisibility(i3);
        ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).itemRemove.setVisibility(i2);
        ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).itemSelector.setContentDescription(((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).itemSelector.getContext().getString(R.string.play_media, new Object[]{mediaWrapper.getTitle()}));
        if (this.type == 2) {
            ViewGroup.LayoutParams layoutParams = ((ActivityMediaListTvItemBinding) mediaListViewHolder.getBinding()).itemAddPlaylist.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ((ConstraintLayout.LayoutParams) layoutParams).setMarginEnd(0);
        }
    }

    /* access modifiers changed from: protected */
    public DiffUtilAdapter.DiffCallback<MediaWrapper> createCB() {
        return new MediaListAdapter$createCB$1(this);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u0011"}, d2 = {"Lorg/videolan/television/ui/details/MediaListAdapter$MediaListViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/television/databinding/ActivityMediaListTvItemBinding;", "binding", "type", "", "(Lorg/videolan/television/ui/details/MediaListAdapter;Lorg/videolan/television/databinding/ActivityMediaListTvItemBinding;I)V", "onClickAddToPlaylist", "", "v", "Landroid/view/View;", "onClickAppend", "onClickMoveDown", "onClickMoveUp", "onClickPlay", "onClickPlayNext", "onClickRemove", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaListAdapter.kt */
    public final class MediaListViewHolder extends SelectorViewHolder<ActivityMediaListTvItemBinding> {
        final /* synthetic */ MediaListAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaListViewHolder(MediaListAdapter mediaListAdapter, ActivityMediaListTvItemBinding activityMediaListTvItemBinding, int i) {
            super(activityMediaListTvItemBinding);
            Intrinsics.checkNotNullParameter(activityMediaListTvItemBinding, "binding");
            this.this$0 = mediaListAdapter;
            View[] viewArr = {activityMediaListTvItemBinding.itemMoveDown, activityMediaListTvItemBinding.itemMoveUp, activityMediaListTvItemBinding.itemAddPlaylist, activityMediaListTvItemBinding.itemInsertNext, activityMediaListTvItemBinding.itemAppend, activityMediaListTvItemBinding.itemSelector, activityMediaListTvItemBinding.itemRemove};
            MediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0 mediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0 = new MediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0(viewArr, activityMediaListTvItemBinding, mediaListAdapter, this);
            for (int i2 = 0; i2 < 7; i2++) {
                viewArr[i2].setOnFocusChangeListener(mediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0);
            }
            activityMediaListTvItemBinding.itemPlay.setOnFocusChangeListener(mediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0);
        }

        public final void onClickPlay(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.listener.onClickPlay(view, getLayoutPosition());
        }

        public final void onClickMoveDown(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.setLastMovedItemFrom(getLayoutPosition());
            this.this$0.setLastMovedItemTo(getLayoutPosition() + 1);
            this.this$0.listener.onClickMoveDown(view, getLayoutPosition());
        }

        public final void onClickMoveUp(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.setLastMovedItemFrom(getLayoutPosition());
            this.this$0.setLastMovedItemTo(getLayoutPosition() - 1);
            this.this$0.listener.onClickMoveUp(view, getLayoutPosition());
        }

        public final void onClickAppend(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.listener.onClickAppend(view, getLayoutPosition());
        }

        public final void onClickAddToPlaylist(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.listener.onClickAddToPlaylist(view, getLayoutPosition());
        }

        public final void onClickRemove(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.listener.onClickRemove(view, getLayoutPosition());
        }

        public final void onClickPlayNext(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.listener.onClickPlayNext(view, getLayoutPosition());
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(View[] viewArr, ActivityMediaListTvItemBinding activityMediaListTvItemBinding, MediaListAdapter mediaListAdapter, MediaListViewHolder mediaListViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(viewArr, "$fadableViews");
            Intrinsics.checkNotNullParameter(activityMediaListTvItemBinding, "$binding");
            Intrinsics.checkNotNullParameter(mediaListAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaListViewHolder, "this$1");
            float f = 0.0f;
            float f2 = !z ? 0.0f : 1.0f;
            for (View animate : viewArr) {
                animate.animate().alpha(f2);
            }
            if (Intrinsics.areEqual((Object) view, (Object) activityMediaListTvItemBinding.itemSelector) && z) {
                f = 1.0f;
            }
            activityMediaListTvItemBinding.itemPlay.animate().alpha(f);
            if (z) {
                mediaListAdapter.listener.onFocusChanged((MediaLibraryItem) mediaListAdapter.getItem(mediaListViewHolder.getLayoutPosition()));
                FocusListener access$getFocusListener$p = mediaListAdapter.focusListener;
                if (access$getFocusListener$p != null) {
                    access$getFocusListener$p.onFocusChanged(mediaListViewHolder.getLayoutPosition());
                }
            }
        }
    }
}
