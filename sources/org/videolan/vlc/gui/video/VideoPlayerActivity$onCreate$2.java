package org.videolan.vlc.gui.video;

import android.app.Activity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$onCreate$2", f = "VideoPlayerActivity.kt", i = {}, l = {619}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$onCreate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$onCreate$2(VideoPlayerActivity videoPlayerActivity, Continuation<? super VideoPlayerActivity$onCreate$2> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$onCreate$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$onCreate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$onCreate$2$1", f = "VideoPlayerActivity.kt", i = {}, l = {622}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.video.VideoPlayerActivity$onCreate$2$1  reason: invalid class name */
    /* compiled from: VideoPlayerActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(videoPlayerActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<WindowLayoutInfo> windowLayoutInfo = WindowInfoTracker.Companion.getOrCreate(videoPlayerActivity).windowLayoutInfo((Activity) videoPlayerActivity);
                final VideoPlayerActivity videoPlayerActivity = videoPlayerActivity;
                this.label = 1;
                if (windowLayoutInfo.collect(new FlowCollector() {
                    public final Object emit(WindowLayoutInfo windowLayoutInfo, Continuation<? super Unit> continuation) {
                        VideoPlayerOverlayDelegate overlayDelegate = videoPlayerActivity.getOverlayDelegate();
                        Object firstOrNull = CollectionsKt.firstOrNull(windowLayoutInfo.getDisplayFeatures());
                        overlayDelegate.setFoldingFeature(firstOrNull instanceof FoldingFeature ? (FoldingFeature) firstOrNull : null);
                        videoPlayerActivity.setWindowLayoutInfo(windowLayoutInfo);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle lifecycle = this.this$0.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
            Lifecycle.State state = Lifecycle.State.STARTED;
            final VideoPlayerActivity videoPlayerActivity = this.this$0;
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, state, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1((Continuation<? super AnonymousClass1>) null), (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
