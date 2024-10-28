package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "Landroidx/paging/PageEvent;", "R", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$2$1", f = "PagingDataTransforms.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PagingDataTransforms.kt */
final class PagingDataTransforms$flatMap$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<R>>, Object> {
    final /* synthetic */ PageEvent<T> $event;
    final /* synthetic */ Function1<T, Iterable<R>> $transform;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataTransforms$flatMap$2$1(PageEvent<T> pageEvent, Function1<? super T, ? extends Iterable<? extends R>> function1, Continuation<? super PagingDataTransforms$flatMap$2$1> continuation) {
        super(2, continuation);
        this.$event = pageEvent;
        this.$transform = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PagingDataTransforms$flatMap$2$1(this.$event, this.$transform, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PageEvent<R>> continuation) {
        return ((PagingDataTransforms$flatMap$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H@"}, d2 = {"<anonymous>", "", "R", "T", "", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$2$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.paging.PagingDataTransforms$flatMap$2$1$1  reason: invalid class name */
    /* compiled from: PagingDataTransforms.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<T, Continuation<? super Iterable<? extends R>>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(function1, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(T t, Continuation<? super Iterable<? extends R>> continuation) {
            return ((AnonymousClass1) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return function1.invoke(this.L$0);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PageEvent<T> pageEvent = this.$event;
            final Function1<T, Iterable<R>> function1 = this.$transform;
            this.label = 1;
            obj = pageEvent.flatMap(new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
