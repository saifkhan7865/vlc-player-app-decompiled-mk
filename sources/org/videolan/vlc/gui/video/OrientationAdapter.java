package org.videolan.vlc.gui.video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.vlc.databinding.VideoScaleItemBinding;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001#B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0015\u001a\u00020\u0004H\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001c\u0010\u0019\u001a\u00020\u00102\n\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0004H\u0016J\u001c\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0004H\u0016J\u0014\u0010 \u001a\u00020\u00102\n\u0010\u001a\u001a\u00060\u0002R\u00020\u0000H\u0016J\u001a\u0010!\u001a\u00020\u00102\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0005R&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/video/OrientationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/video/OrientationAdapter$ViewHolder;", "currentOrientation", "", "(I)V", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "value", "selectedSize", "getSelectedSize", "()I", "setSelectedSize", "sizeSelectedListener", "Lkotlin/Function1;", "Lorg/videolan/vlc/gui/video/OrientationMode;", "", "getSizeSelectedListener", "()Lkotlin/jvm/functions/Function1;", "setSizeSelectedListener", "(Lkotlin/jvm/functions/Function1;)V", "getItemCount", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setOnSizeSelectedListener", "listener", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOrientationDelegate.kt */
public final class OrientationAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LifecycleAwareScheduler scheduler;
    private int selectedSize;
    public Function1<? super OrientationMode, Unit> sizeSelectedListener;

    public OrientationAdapter(int i) {
        this.selectedSize = OrientationMode.Companion.findByValue(i).ordinal();
    }

    public final int getSelectedSize() {
        return this.selectedSize;
    }

    public final void setSelectedSize(int i) {
        notifyItemChanged(this.selectedSize);
        this.selectedSize = i;
        notifyItemChanged(i);
    }

    public final Function1<OrientationMode, Unit> getSizeSelectedListener() {
        Function1<? super OrientationMode, Unit> function1 = this.sizeSelectedListener;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sizeSelectedListener");
        return null;
    }

    public final void setSizeSelectedListener(Function1<? super OrientationMode, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.sizeSelectedListener = function1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        VideoScaleItemBinding inflate = VideoScaleItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public final void setOnSizeSelectedListener(Function1<? super OrientationMode, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        setSizeSelectedListener(function1);
    }

    public int getItemCount() {
        return OrientationMode.values().length;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        viewHolder.bind(OrientationMode.values()[i], i == this.selectedSize);
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

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/video/OrientationAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/VideoScaleItemBinding;", "(Lorg/videolan/vlc/gui/video/OrientationAdapter;Lorg/videolan/vlc/databinding/VideoScaleItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/VideoScaleItemBinding;", "bind", "", "orientationMode", "Lorg/videolan/vlc/gui/video/OrientationMode;", "selected", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerOrientationDelegate.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final VideoScaleItemBinding binding;
        final /* synthetic */ OrientationAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(OrientationAdapter orientationAdapter, VideoScaleItemBinding videoScaleItemBinding) {
            super(videoScaleItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(videoScaleItemBinding, "binding");
            this.this$0 = orientationAdapter;
            this.binding = videoScaleItemBinding;
            this.itemView.setOnClickListener(new OrientationAdapter$ViewHolder$$ExternalSyntheticLambda0(orientationAdapter, this));
        }

        public final VideoScaleItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(OrientationAdapter orientationAdapter, ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(orientationAdapter, "this$0");
            Intrinsics.checkNotNullParameter(viewHolder, "this$1");
            orientationAdapter.setSelectedSize(viewHolder.getLayoutPosition());
            orientationAdapter.getSizeSelectedListener().invoke(OrientationMode.values()[viewHolder.getLayoutPosition()]);
        }

        public final void bind(OrientationMode orientationMode, boolean z) {
            Intrinsics.checkNotNullParameter(orientationMode, "orientationMode");
            VideoScaleItemBinding videoScaleItemBinding = this.binding;
            videoScaleItemBinding.setScaleName(videoScaleItemBinding.getRoot().getContext().getString(orientationMode.getTitle()));
            this.binding.setSelected(Boolean.valueOf(z));
            this.binding.executePendingBindings();
        }
    }
}
