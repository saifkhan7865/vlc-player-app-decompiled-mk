package org.videolan.television.ui;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.tools.Connection;
import org.videolan.tools.NetworkMonitor;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvFragment$onCreate$3$1$1", f = "MediaScrapingTvFragment.kt", i = {}, l = {123}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvFragment.kt */
final class MediaScrapingTvFragment$onCreate$3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MediaScrapingTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvFragment$onCreate$3$1$1(MediaScrapingTvFragment mediaScrapingTvFragment, Continuation<? super MediaScrapingTvFragment$onCreate$3$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaScrapingTvFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvFragment$onCreate$3$1$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvFragment$onCreate$3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/tools/Connection;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvFragment$onCreate$3$1$1$1", f = "MediaScrapingTvFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.television.ui.MediaScrapingTvFragment$onCreate$3$1$1$1  reason: invalid class name */
    /* compiled from: MediaScrapingTvFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Connection, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Connection connection, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(connection, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(((Connection) this.L$0).getConnected());
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NetworkMonitor.Companion companion = NetworkMonitor.Companion;
            Context requireContext = this.this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            this.label = 1;
            if (FlowKt.first(((NetworkMonitor) companion.getInstance(requireContext)).getConnectionFlow(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.refresh();
        return Unit.INSTANCE;
    }
}
