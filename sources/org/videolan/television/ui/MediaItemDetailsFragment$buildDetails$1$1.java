package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$1", f = "MediaItemDetailsFragment.kt", i = {}, l = {327}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaItemDetailsFragment.kt */
final class MediaItemDetailsFragment$buildDetails$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MediaItemDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaItemDetailsFragment$buildDetails$1$1(MediaItemDetailsFragment mediaItemDetailsFragment, Continuation<? super MediaItemDetailsFragment$buildDetails$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaItemDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaItemDetailsFragment$buildDetails$1$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaItemDetailsFragment$buildDetails$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$1$1", f = "MediaItemDetailsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$1$1  reason: invalid class name */
    /* compiled from: MediaItemDetailsFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaItemDetailsFragment, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MediaItemDetailsFragment mediaItemDetailsFragment = mediaItemDetailsFragment;
                mediaItemDetailsFragment.fromHistory = !mediaItemDetailsFragment.getViewModel().getMedia().removeFromHistory();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final MediaItemDetailsFragment mediaItemDetailsFragment = this.this$0;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!this.this$0.fromHistory) {
            this.this$0.actionsAdapter.clear(12);
        }
        return Unit.INSTANCE;
    }
}
