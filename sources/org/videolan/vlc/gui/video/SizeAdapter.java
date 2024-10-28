package org.videolan.vlc.gui.video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.VideoScaleItemBinding;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0007H\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001c\u0010\u0019\u001a\u00020\u00102\n\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J\u001c\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0007H\u0016J\u0014\u0010 \u001a\u00020\u00102\n\u0010\u001a\u001a\u00060\u0002R\u00020\u0000H\u0016J\u001a\u0010!\u001a\u00020\u00102\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/video/SizeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/video/SizeAdapter$ViewHolder;", "()V", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "value", "", "selectedSize", "getSelectedSize", "()I", "setSelectedSize", "(I)V", "sizeSelectedListener", "Lkotlin/Function1;", "Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "", "getSizeSelectedListener", "()Lkotlin/jvm/functions/Function1;", "setSizeSelectedListener", "(Lkotlin/jvm/functions/Function1;)V", "getItemCount", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setOnSizeSelectedListener", "listener", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerResizeDelegate.kt */
public final class SizeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LifecycleAwareScheduler scheduler;
    private int selectedSize = ArraysKt.indexOf((T[]) MediaPlayer.ScaleType.values(), MediaPlayer.ScaleType.SURFACE_BEST_FIT);
    public Function1<? super MediaPlayer.ScaleType, Unit> sizeSelectedListener;

    public final int getSelectedSize() {
        return this.selectedSize;
    }

    public final void setSelectedSize(int i) {
        notifyItemChanged(this.selectedSize);
        this.selectedSize = i;
        notifyItemChanged(i);
    }

    public final Function1<MediaPlayer.ScaleType, Unit> getSizeSelectedListener() {
        Function1<? super MediaPlayer.ScaleType, Unit> function1 = this.sizeSelectedListener;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sizeSelectedListener");
        return null;
    }

    public final void setSizeSelectedListener(Function1<? super MediaPlayer.ScaleType, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.sizeSelectedListener = function1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        VideoScaleItemBinding inflate = VideoScaleItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public final void setOnSizeSelectedListener(Function1<? super MediaPlayer.ScaleType, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        setSizeSelectedListener(function1);
    }

    public int getItemCount() {
        return MediaPlayer.ScaleType.values().length;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        viewHolder.bind(MediaPlayer.ScaleType.values()[i], i == this.selectedSize);
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

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/video/SizeAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/VideoScaleItemBinding;", "(Lorg/videolan/vlc/gui/video/SizeAdapter;Lorg/videolan/vlc/databinding/VideoScaleItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/VideoScaleItemBinding;", "bind", "", "scaleType", "Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "selected", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerResizeDelegate.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final VideoScaleItemBinding binding;
        final /* synthetic */ SizeAdapter this$0;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* compiled from: VideoPlayerResizeDelegate.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|25) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
            /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
            /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    org.videolan.libvlc.MediaPlayer$ScaleType[] r0 = org.videolan.libvlc.MediaPlayer.ScaleType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_BEST_FIT     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_FIT_SCREEN     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_FILL     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_ORIGINAL     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_16_9     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_4_3     // Catch:{ NoSuchFieldError -> 0x003d }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                    r2 = 6
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
                L_0x003d:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_16_10     // Catch:{ NoSuchFieldError -> 0x0046 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                    r2 = 7
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
                L_0x0046:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_221_1     // Catch:{ NoSuchFieldError -> 0x0050 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                    r2 = 8
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
                L_0x0050:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_235_1     // Catch:{ NoSuchFieldError -> 0x005a }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                    r2 = 9
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
                L_0x005a:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_239_1     // Catch:{ NoSuchFieldError -> 0x0064 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                    r2 = 10
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
                L_0x0064:
                    org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_5_4     // Catch:{ NoSuchFieldError -> 0x006e }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                    r2 = 11
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
                L_0x006e:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.SizeAdapter.ViewHolder.WhenMappings.<clinit>():void");
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(SizeAdapter sizeAdapter, VideoScaleItemBinding videoScaleItemBinding) {
            super(videoScaleItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(videoScaleItemBinding, "binding");
            this.this$0 = sizeAdapter;
            this.binding = videoScaleItemBinding;
            this.itemView.setOnClickListener(new SizeAdapter$ViewHolder$$ExternalSyntheticLambda0(sizeAdapter, this));
        }

        public final VideoScaleItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(SizeAdapter sizeAdapter, ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(sizeAdapter, "this$0");
            Intrinsics.checkNotNullParameter(viewHolder, "this$1");
            sizeAdapter.setSelectedSize(viewHolder.getLayoutPosition());
            sizeAdapter.getSizeSelectedListener().invoke(MediaPlayer.ScaleType.values()[viewHolder.getLayoutPosition()]);
        }

        public final void bind(MediaPlayer.ScaleType scaleType, boolean z) {
            String str;
            Intrinsics.checkNotNullParameter(scaleType, "scaleType");
            VideoScaleItemBinding videoScaleItemBinding = this.binding;
            switch (WhenMappings.$EnumSwitchMapping$0[scaleType.ordinal()]) {
                case 1:
                    str = this.binding.trackTitle.getContext().getString(R.string.surface_best_fit);
                    break;
                case 2:
                    str = this.binding.trackTitle.getContext().getString(R.string.surface_fit_screen);
                    break;
                case 3:
                    str = this.binding.trackTitle.getContext().getString(R.string.surface_fill);
                    break;
                case 4:
                    str = this.binding.trackTitle.getContext().getString(R.string.surface_original);
                    break;
                case 5:
                    str = "16:9";
                    break;
                case 6:
                    str = "4:3";
                    break;
                case 7:
                    str = "16:10";
                    break;
                case 8:
                    str = "2.21:1";
                    break;
                case 9:
                    str = "2.35:1";
                    break;
                case 10:
                    str = "2.39:1";
                    break;
                case 11:
                    str = "5:4";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            videoScaleItemBinding.setScaleName(str);
            this.binding.setSelected(Boolean.valueOf(z));
            this.binding.executePendingBindings();
        }
    }
}
