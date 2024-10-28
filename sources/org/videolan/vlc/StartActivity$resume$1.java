package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.TvChannelsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.StartActivity$resume$1", f = "StartActivity.kt", i = {}, l = {190, 191}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StartActivity.kt */
final class StartActivity$resume$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ StartActivity $ctx;
    final /* synthetic */ Ref.ObjectRef<Long> $id;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StartActivity$resume$1(Ref.ObjectRef<Long> objectRef, StartActivity startActivity, Continuation<? super StartActivity$resume$1> continuation) {
        super(2, continuation);
        this.$id = objectRef;
        this.$ctx = startActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StartActivity$resume$1(this.$id, this.$ctx, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StartActivity$resume$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(T t) {
        Ref.ObjectRef<Long> objectRef;
        T coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(t);
            objectRef = this.$id;
            T t2 = objectRef.element;
            Intrinsics.checkNotNullExpressionValue(t2, "element");
            this.L$0 = objectRef;
            this.label = 1;
            t = TvChannelsKt.checkWatchNextId(this.$ctx, ((Number) t2).longValue(), this);
            if (t == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(t);
        } else if (i == 2) {
            ResultKt.throwOnFailure(t);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef.element = t;
        final StartActivity startActivity = this.$ctx;
        final Ref.ObjectRef<Long> objectRef2 = this.$id;
        this.L$0 = null;
        this.label = 2;
        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.StartActivity$resume$1$1", f = "StartActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.StartActivity$resume$1$1  reason: invalid class name */
    /* compiled from: StartActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(startActivity, objectRef2, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                T t = objectRef2.element;
                Intrinsics.checkNotNullExpressionValue(t, "element");
                MediaUtils.INSTANCE.openMediaNoUi((Context) startActivity, ((Number) t).longValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
