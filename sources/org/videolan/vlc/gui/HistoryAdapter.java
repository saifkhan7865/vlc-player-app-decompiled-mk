package org.videolan.vlc.gui;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.MultiSelectAdapter;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.vlc.BR;
import org.videolan.vlc.databinding.HistoryItemBinding;
import org.videolan.vlc.databinding.HistoryItemCardBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.Click;
import org.videolan.vlc.gui.helpers.EventsSource;
import org.videolan.vlc.gui.helpers.IEventsSource;
import org.videolan.vlc.gui.helpers.ImageClick;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.LongClick;
import org.videolan.vlc.gui.helpers.MarqueeViewHolder;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.helpers.SimpleClick;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.interfaces.SwipeDragHelperAdapter;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 E2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00020\u00042\b\u0012\u0004\u0012\u00020\u00060\u00052\u00020\u0007:\u0002EFB\u001b\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020)H\u0014J\b\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020+H\u0016J\u0010\u0010/\u001a\u00020%2\u0006\u00100\u001a\u000201H\u0016J\u001c\u00102\u001a\u00020%2\n\u00103\u001a\u00060\u0003R\u00020\u00002\u0006\u00104\u001a\u00020+H\u0016J*\u00102\u001a\u00020%2\n\u00103\u001a\u00060\u0003R\u00020\u00002\u0006\u00104\u001a\u00020+2\f\u00105\u001a\b\u0012\u0004\u0012\u00020706H\u0016J\u001c\u00108\u001a\u00060\u0003R\u00020\u00002\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020+H\u0016J\u0010\u0010<\u001a\u00020%2\u0006\u00104\u001a\u00020+H\u0016J\u0018\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020+H\u0016J\u0018\u0010@\u001a\u00020%2\u0006\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020+H\u0016J\b\u0010C\u001a\u00020%H\u0014J\u0014\u0010D\u001a\u00020%2\n\u00103\u001a\u00060\u0003R\u00020\u0000H\u0016R\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eX\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006G"}, d2 = {"Lorg/videolan/vlc/gui/HistoryAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lorg/videolan/vlc/gui/HistoryAdapter$ViewHolder;", "Lorg/videolan/tools/MultiSelectAdapter;", "Lorg/videolan/vlc/gui/helpers/IEventsSource;", "Lorg/videolan/vlc/gui/helpers/Click;", "Lorg/videolan/vlc/interfaces/SwipeDragHelperAdapter;", "inCards", "", "listEventsHandler", "Lorg/videolan/vlc/interfaces/IListEventsHandler;", "(ZLorg/videolan/vlc/interfaces/IListEventsHandler;)V", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "eventsChannel", "Lkotlinx/coroutines/channels/Channel;", "getEventsChannel", "()Lkotlinx/coroutines/channels/Channel;", "layoutInflater", "Landroid/view/LayoutInflater;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "getMultiSelectHelper", "()Lorg/videolan/tools/MultiSelectHelper;", "setMultiSelectHelper", "(Lorg/videolan/tools/MultiSelectHelper;)V", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "updateEvt", "Landroidx/lifecycle/LiveData;", "", "getUpdateEvt", "()Landroidx/lifecycle/LiveData;", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "getItemCount", "", "getItemId", "", "arg0", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "position", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "onItemMoved", "dragFrom", "dragTo", "onUpdateFinished", "onViewRecycled", "Companion", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HistoryAdapter.kt */
public final class HistoryAdapter extends DiffUtilAdapter<MediaWrapper, ViewHolder> implements MultiSelectAdapter<MediaWrapper>, IEventsSource<Click>, SwipeDragHelperAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/HistoryAdapter";
    private final /* synthetic */ EventsSource<Click> $$delegate_0;
    /* access modifiers changed from: private */
    public final boolean inCards;
    private LayoutInflater layoutInflater;
    private final IListEventsHandler listEventsHandler;
    private MultiSelectHelper<MediaWrapper> multiSelectHelper;
    private LifecycleAwareScheduler scheduler;
    private final LiveData<Unit> updateEvt;

    public HistoryAdapter() {
        this(false, (IListEventsHandler) null, 3, (DefaultConstructorMarker) null);
    }

    public Flow<Click> getEvents() {
        return this.$$delegate_0.getEvents();
    }

    public Channel<Click> getEventsChannel() {
        return this.$$delegate_0.getEventsChannel();
    }

    public long getItemId(int i) {
        return 0;
    }

    public void onItemMove(int i, int i2) {
    }

    public void onItemMoved(int i, int i2) {
    }

    public HistoryAdapter(boolean z, IListEventsHandler iListEventsHandler) {
        this.inCards = z;
        this.listEventsHandler = iListEventsHandler;
        this.$$delegate_0 = new EventsSource<>();
        this.updateEvt = new MutableLiveData();
        this.multiSelectHelper = new MultiSelectHelper<>(this, 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HistoryAdapter(boolean z, IListEventsHandler iListEventsHandler, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : iListEventsHandler);
    }

    public final LiveData<Unit> getUpdateEvt() {
        return this.updateEvt;
    }

    public final MultiSelectHelper<MediaWrapper> getMultiSelectHelper() {
        return this.multiSelectHelper;
    }

    public final void setMultiSelectHelper(MultiSelectHelper<MediaWrapper> multiSelectHelper2) {
        Intrinsics.checkNotNullParameter(multiSelectHelper2, "<set-?>");
        this.multiSelectHelper = multiSelectHelper2;
    }

    public final LifecycleAwareScheduler getScheduler() {
        return this.scheduler;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        this.scheduler = lifecycleAwareScheduler;
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0014J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\rR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/HistoryAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "binding", "(Lorg/videolan/vlc/gui/HistoryAdapter;Landroidx/databinding/ViewDataBinding;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "isSelected", "", "onClick", "", "v", "Landroid/view/View;", "onImageClick", "onLongClick", "recycle", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HistoryAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<ViewDataBinding> implements MarqueeViewHolder {
        final /* synthetic */ HistoryAdapter this$0;
        private final TextView titleView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(HistoryAdapter historyAdapter, ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            TextView textView;
            Intrinsics.checkNotNullParameter(viewDataBinding, "binding");
            this.this$0 = historyAdapter;
            boolean z = viewDataBinding instanceof HistoryItemBinding;
            if (z) {
                textView = ((HistoryItemBinding) viewDataBinding).title;
            } else {
                textView = viewDataBinding instanceof HistoryItemCardBinding ? ((HistoryItemCardBinding) viewDataBinding).title : null;
            }
            this.titleView = textView;
            setBinding(viewDataBinding);
            if (z) {
                ((HistoryItemBinding) viewDataBinding).setHolder(this);
            } else if (viewDataBinding instanceof HistoryItemCardBinding) {
                ((HistoryItemCardBinding) viewDataBinding).setHolder(this);
            }
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        public final void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.this$0.getEventsChannel().m1139trySendJP2dKIU(new SimpleClick(getLayoutPosition()));
        }

        public final boolean onLongClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            return ChannelResult.m2346isSuccessimpl(this.this$0.getEventsChannel().m1139trySendJP2dKIU(new LongClick(getLayoutPosition())));
        }

        public final void onImageClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            if (this.this$0.inCards) {
                this.this$0.getEventsChannel().m1139trySendJP2dKIU(new SimpleClick(getLayoutPosition()));
            } else {
                this.this$0.getEventsChannel().m1139trySendJP2dKIU(new ImageClick(getLayoutPosition()));
            }
        }

        /* access modifiers changed from: protected */
        public boolean isSelected() {
            return ((MediaWrapper) this.this$0.getItem(getLayoutPosition())).hasStateFlags(1);
        }

        public final void recycle() {
            ViewDataBinding binding = getBinding();
            if (binding instanceof HistoryItemBinding) {
                ViewDataBinding binding2 = getBinding();
                Intrinsics.checkNotNull(binding2, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemBinding");
                ((HistoryItemBinding) binding2).title.setSelected(false);
            } else if (binding instanceof HistoryItemCardBinding) {
                ViewDataBinding binding3 = getBinding();
                Intrinsics.checkNotNull(binding3, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemCardBinding");
                ((HistoryItemCardBinding) binding3).title.setSelected(false);
            }
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (this.inCards && Settings.INSTANCE.getListTitleEllipsize() == 4) {
            this.scheduler = UiToolsKt.enableMarqueeEffect(recyclerView);
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
        }
        viewHolder.recycle();
        super.onViewRecycled(viewHolder);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewDataBinding viewDataBinding;
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (this.layoutInflater == null) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            Intrinsics.checkNotNullExpressionValue(from, "from(...)");
            this.layoutInflater = from;
        }
        LayoutInflater layoutInflater2 = null;
        if (this.inCards) {
            LayoutInflater layoutInflater3 = this.layoutInflater;
            if (layoutInflater3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutInflater");
            } else {
                layoutInflater2 = layoutInflater3;
            }
            viewDataBinding = HistoryItemCardBinding.inflate(layoutInflater2, viewGroup, false);
        } else {
            LayoutInflater layoutInflater4 = this.layoutInflater;
            if (layoutInflater4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutInflater");
            } else {
                layoutInflater2 = layoutInflater4;
            }
            viewDataBinding = HistoryItemBinding.inflate(layoutInflater2, viewGroup, false);
        }
        Intrinsics.checkNotNullExpressionValue(viewDataBinding, "inflate(...)");
        return new ViewHolder(this, viewDataBinding);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        MediaWrapper mediaWrapper = (MediaWrapper) getItem(i);
        ViewDataBinding binding = viewHolder.getBinding();
        String str = "16:10";
        if (binding instanceof HistoryItemBinding) {
            ViewDataBinding binding2 = viewHolder.getBinding();
            Intrinsics.checkNotNull(binding2, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemBinding");
            ((HistoryItemBinding) binding2).setMedia(mediaWrapper);
            viewHolder.getBinding().setVariable(BR.isNetwork, Boolean.valueOf(BrowserutilsKt.isSchemeNetwork(mediaWrapper.getUri().getScheme())));
            ViewDataBinding binding3 = viewHolder.getBinding();
            int i2 = BR.isSD;
            Uri uri = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            binding3.setVariable(i2, Boolean.valueOf(BrowserutilsKt.isSD(uri)));
            ViewDataBinding binding4 = viewHolder.getBinding();
            int i3 = BR.isOTG;
            Uri uri2 = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
            binding4.setVariable(i3, Boolean.valueOf(BrowserutilsKt.isOTG(uri2)));
            ViewDataBinding binding5 = viewHolder.getBinding();
            Intrinsics.checkNotNull(binding5, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemBinding");
            Context context = viewHolder.itemView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            ((HistoryItemBinding) binding5).setCover(ImageLoaderKt.getMediaIconDrawable(context, mediaWrapper.getType()));
            ViewDataBinding binding6 = viewHolder.getBinding();
            Intrinsics.checkNotNull(binding6, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemBinding");
            ViewGroup.LayoutParams layoutParams = ((HistoryItemBinding) binding6).icon.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (mediaWrapper.getType() != 0) {
                str = DiskLruCache.VERSION_1;
            }
            layoutParams2.dimensionRatio = str;
        } else if (binding instanceof HistoryItemCardBinding) {
            ViewDataBinding binding7 = viewHolder.getBinding();
            Intrinsics.checkNotNull(binding7, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemCardBinding");
            ((HistoryItemCardBinding) binding7).setMedia(mediaWrapper);
            viewHolder.getBinding().setVariable(BR.isNetwork, Boolean.valueOf(BrowserutilsKt.isSchemeNetwork(mediaWrapper.getUri().getScheme())));
            ViewDataBinding binding8 = viewHolder.getBinding();
            int i4 = BR.isSD;
            Uri uri3 = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri3, "getUri(...)");
            binding8.setVariable(i4, Boolean.valueOf(BrowserutilsKt.isSD(uri3)));
            ViewDataBinding binding9 = viewHolder.getBinding();
            int i5 = BR.isOTG;
            Uri uri4 = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri4, "getUri(...)");
            binding9.setVariable(i5, Boolean.valueOf(BrowserutilsKt.isOTG(uri4)));
            ViewDataBinding binding10 = viewHolder.getBinding();
            Intrinsics.checkNotNull(binding10, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemCardBinding");
            Context context2 = viewHolder.itemView.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            ((HistoryItemCardBinding) binding10).setCover(ImageLoaderKt.getMediaIconDrawable(context2, mediaWrapper.getType()));
            ViewDataBinding binding11 = viewHolder.getBinding();
            Intrinsics.checkNotNull(binding11, "null cannot be cast to non-null type org.videolan.vlc.databinding.HistoryItemCardBinding");
            ViewGroup.LayoutParams layoutParams3 = ((HistoryItemCardBinding) binding11).icon.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
            if (mediaWrapper.getType() != 0) {
                str = DiskLruCache.VERSION_1;
            }
            layoutParams4.dimensionRatio = str;
        }
        viewHolder.selectView(this.multiSelectHelper.isSelected(i));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i, list);
        } else {
            viewHolder.selectView(this.multiSelectHelper.isSelected(i));
        }
    }

    public int getItemCount() {
        return getDataset().size();
    }

    /* access modifiers changed from: protected */
    public void onUpdateFinished() {
        LiveData<Unit> liveData = this.updateEvt;
        Intrinsics.checkNotNull(liveData, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<kotlin.Unit>");
        ((MutableLiveData) liveData).setValue(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/HistoryAdapter$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HistoryAdapter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onItemDismiss(int i) {
        MediaWrapper mediaWrapper = (MediaWrapper) getItem(i);
        IListEventsHandler iListEventsHandler = this.listEventsHandler;
        if (iListEventsHandler != null) {
            iListEventsHandler.onRemove(i, mediaWrapper);
        }
    }

    /* access modifiers changed from: protected */
    public DiffUtilAdapter.DiffCallback<MediaWrapper> createCB() {
        return new HistoryAdapter$createCB$1();
    }
}
