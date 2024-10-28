package org.videolan.vlc.gui.network;

import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.MrlCardItemBinding;
import org.videolan.vlc.databinding.MrlDummyItemBinding;
import org.videolan.vlc.databinding.MrlItemBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.MarqueeViewHolder;
import org.videolan.vlc.gui.helpers.UiToolsKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003()*B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J&\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0016H\u0016J\u0010\u0010$\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010%\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0003H\u0016J\u0014\u0010&\u001a\u00020\f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/videolan/vlc/gui/network/MRLAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "eventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/network/MrlAction;", "inCards", "", "(Lkotlinx/coroutines/channels/SendChannel;Z)V", "dummyClickListener", "Lkotlin/Function0;", "", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "getItemViewType", "", "position", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onDetachedFromRecyclerView", "onViewRecycled", "setOnDummyClickListener", "dummyClickLisener", "CardViewHolder", "DummyViewHolder", "ListViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MRLAdapter.kt */
public final class MRLAdapter extends DiffUtilAdapter<MediaWrapper, RecyclerView.ViewHolder> {
    /* access modifiers changed from: private */
    public Function0<Unit> dummyClickListener;
    /* access modifiers changed from: private */
    public final SendChannel<MrlAction> eventActor;
    private final Lazy handler$delegate;
    private final boolean inCards;

    public MRLAdapter(SendChannel<? super MrlAction> sendChannel, boolean z) {
        Intrinsics.checkNotNullParameter(sendChannel, "eventActor");
        this.eventActor = sendChannel;
        this.inCards = z;
        this.handler$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, MRLAdapter$handler$2.INSTANCE);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MRLAdapter(SendChannel sendChannel, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sendChannel, (i & 2) != 0 ? false : z);
    }

    private final Handler getHandler() {
        return (Handler) this.handler$delegate.getValue();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            ViewDataBinding inflate = DataBindingUtil.inflate(from, R.layout.mrl_item, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ListViewHolder(this, (MrlItemBinding) inflate);
        } else if (i != 1) {
            ViewDataBinding inflate2 = DataBindingUtil.inflate(from, R.layout.mrl_dummy_item, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
            return new DummyViewHolder(this, (MrlDummyItemBinding) inflate2);
        } else {
            ViewDataBinding inflate3 = DataBindingUtil.inflate(from, R.layout.mrl_card_item, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate3, "inflate(...)");
            return new CardViewHolder(this, (MrlCardItemBinding) inflate3);
        }
    }

    public int getItemViewType(int i) {
        MediaWrapper mediaWrapper = (MediaWrapper) CollectionsKt.getOrNull(getDataset(), i);
        if ((mediaWrapper != null ? mediaWrapper.getId() : 0) < 0) {
            return 2;
        }
        return this.inCards ? 1 : 0;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        MediaWrapper mediaWrapper = (MediaWrapper) CollectionsKt.getOrNull(getDataset(), i);
        if (mediaWrapper != null) {
            if (viewHolder instanceof ListViewHolder) {
                ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
                listViewHolder.getBinding().mrlItemUri.setText(Uri.decode(mediaWrapper.getLocation()));
                listViewHolder.getBinding().mrlItemTitle.setText(Uri.decode(mediaWrapper.getTitle()));
                listViewHolder.getBinding().setItem(mediaWrapper);
            } else if (viewHolder instanceof CardViewHolder) {
                CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
                cardViewHolder.getBinding().mrlItemUri.setText(Uri.decode(mediaWrapper.getLocation()));
                cardViewHolder.getBinding().mrlItemTitle.setText(Uri.decode(mediaWrapper.getTitle()));
                cardViewHolder.getBinding().setItem(mediaWrapper);
            }
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            UiToolsKt.enableMarqueeEffect(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            getHandler().removeCallbacksAndMessages((Object) null);
        }
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            getHandler().removeCallbacksAndMessages((Object) null);
        }
        if (viewHolder instanceof ListViewHolder) {
            ((ListViewHolder) viewHolder).recycle();
        } else if (viewHolder instanceof CardViewHolder) {
            ((CardViewHolder) viewHolder).recycle();
        }
        super.onViewRecycled(viewHolder);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        for (Object next : list) {
            if (next instanceof String) {
                if (viewHolder instanceof ListViewHolder) {
                    ((ListViewHolder) viewHolder).getBinding().mrlItemTitle.setText((CharSequence) next);
                } else if (viewHolder instanceof CardViewHolder) {
                    ((CardViewHolder) viewHolder).getBinding().mrlItemTitle.setText((CharSequence) next);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/network/MRLAdapter$DummyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/MrlDummyItemBinding;", "(Lorg/videolan/vlc/gui/network/MRLAdapter;Lorg/videolan/vlc/databinding/MrlDummyItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/MrlDummyItemBinding;", "recycle", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MRLAdapter.kt */
    public final class DummyViewHolder extends RecyclerView.ViewHolder {
        private final MrlDummyItemBinding binding;
        final /* synthetic */ MRLAdapter this$0;

        public final void recycle() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DummyViewHolder(MRLAdapter mRLAdapter, MrlDummyItemBinding mrlDummyItemBinding) {
            super(mrlDummyItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(mrlDummyItemBinding, "binding");
            this.this$0 = mRLAdapter;
            this.binding = mrlDummyItemBinding;
            mrlDummyItemBinding.container.setOnClickListener(new MRLAdapter$DummyViewHolder$$ExternalSyntheticLambda0(mRLAdapter));
        }

        public final MrlDummyItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(MRLAdapter mRLAdapter, View view) {
            Intrinsics.checkNotNullParameter(mRLAdapter, "this$0");
            Function0 access$getDummyClickListener$p = mRLAdapter.dummyClickListener;
            if (access$getDummyClickListener$p != null) {
                access$getDummyClickListener$p.invoke();
            }
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0006\u0010\f\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/network/MRLAdapter$ListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "binding", "Lorg/videolan/vlc/databinding/MrlItemBinding;", "(Lorg/videolan/vlc/gui/network/MRLAdapter;Lorg/videolan/vlc/databinding/MrlItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/MrlItemBinding;", "onClick", "", "v", "Landroid/view/View;", "recycle", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MRLAdapter.kt */
    public final class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MrlItemBinding binding;
        final /* synthetic */ MRLAdapter this$0;

        public final void recycle() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ListViewHolder(MRLAdapter mRLAdapter, MrlItemBinding mrlItemBinding) {
            super(mrlItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(mrlItemBinding, "binding");
            this.this$0 = mRLAdapter;
            this.binding = mrlItemBinding;
            this.itemView.setOnClickListener(this);
            this.itemView.setOnLongClickListener(new MRLAdapter$ListViewHolder$$ExternalSyntheticLambda0(mRLAdapter, this));
            mrlItemBinding.mrlCtx.setOnClickListener(new MRLAdapter$ListViewHolder$$ExternalSyntheticLambda1(mRLAdapter, this));
            mrlItemBinding.selector.setOnClickListener(new MRLAdapter$ListViewHolder$$ExternalSyntheticLambda2(this));
        }

        public final MrlItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MRLAdapter mRLAdapter, ListViewHolder listViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mRLAdapter, "this$0");
            Intrinsics.checkNotNullParameter(listViewHolder, "this$1");
            return ChannelResult.m2346isSuccessimpl(mRLAdapter.eventActor.m1139trySendJP2dKIU(new ShowContext(listViewHolder.getLayoutPosition())));
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(MRLAdapter mRLAdapter, ListViewHolder listViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mRLAdapter, "this$0");
            Intrinsics.checkNotNullParameter(listViewHolder, "this$1");
            mRLAdapter.eventActor.m1139trySendJP2dKIU(new ShowContext(listViewHolder.getLayoutPosition()));
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$2(ListViewHolder listViewHolder, View view) {
            Intrinsics.checkNotNullParameter(listViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            listViewHolder.onClick(view);
        }

        public void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaWrapper mediaWrapper = (MediaWrapper) CollectionsKt.getOrNull(this.this$0.getDataset(), getLayoutPosition());
            if (mediaWrapper != null) {
                ChannelResult.m2336boximpl(this.this$0.eventActor.m1139trySendJP2dKIU(new Playmedia(mediaWrapper)));
            }
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/network/MRLAdapter$CardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "binding", "Lorg/videolan/vlc/databinding/MrlCardItemBinding;", "(Lorg/videolan/vlc/gui/network/MRLAdapter;Lorg/videolan/vlc/databinding/MrlCardItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/MrlCardItemBinding;", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "onClick", "", "v", "Landroid/view/View;", "recycle", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MRLAdapter.kt */
    public final class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MarqueeViewHolder {
        private final MrlCardItemBinding binding;
        final /* synthetic */ MRLAdapter this$0;
        private final TextView titleView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CardViewHolder(MRLAdapter mRLAdapter, MrlCardItemBinding mrlCardItemBinding) {
            super(mrlCardItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(mrlCardItemBinding, "binding");
            this.this$0 = mRLAdapter;
            this.binding = mrlCardItemBinding;
            mrlCardItemBinding.container.setOnClickListener(this);
            mrlCardItemBinding.container.setOnLongClickListener(new MRLAdapter$CardViewHolder$$ExternalSyntheticLambda0(mRLAdapter, this));
            mrlCardItemBinding.mrlCtx.setOnClickListener(new MRLAdapter$CardViewHolder$$ExternalSyntheticLambda1(mRLAdapter, this));
            TextView textView = mrlCardItemBinding.mrlItemTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "mrlItemTitle");
            this.titleView = textView;
        }

        public final MrlCardItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MRLAdapter mRLAdapter, CardViewHolder cardViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mRLAdapter, "this$0");
            Intrinsics.checkNotNullParameter(cardViewHolder, "this$1");
            return ChannelResult.m2346isSuccessimpl(mRLAdapter.eventActor.m1139trySendJP2dKIU(new ShowContext(cardViewHolder.getLayoutPosition())));
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(MRLAdapter mRLAdapter, CardViewHolder cardViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mRLAdapter, "this$0");
            Intrinsics.checkNotNullParameter(cardViewHolder, "this$1");
            mRLAdapter.eventActor.m1139trySendJP2dKIU(new ShowContext(cardViewHolder.getLayoutPosition()));
        }

        public void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaWrapper mediaWrapper = (MediaWrapper) CollectionsKt.getOrNull(this.this$0.getDataset(), getLayoutPosition());
            if (mediaWrapper != null) {
                ChannelResult.m2336boximpl(this.this$0.eventActor.m1139trySendJP2dKIU(new Playmedia(mediaWrapper)));
            }
        }

        public final void recycle() {
            this.binding.mrlItemTitle.setSelected(false);
        }

        public TextView getTitleView() {
            return this.titleView;
        }
    }

    /* access modifiers changed from: protected */
    public DiffUtilAdapter.DiffCallback<MediaWrapper> createCB() {
        return new MRLAdapter$createCB$1();
    }

    public final void setOnDummyClickListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "dummyClickLisener");
        this.dummyClickListener = function0;
    }
}
