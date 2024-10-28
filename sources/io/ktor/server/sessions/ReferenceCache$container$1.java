package io.ktor.server.sessions;

import java.lang.ref.ReferenceQueue;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\u001a\b\u0002\u0010\u0001 \u0001*\b\u0012\u0004\u0012\u0002H\u00040\u0005*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0007\u001a\u0002H\u0002H@"}, d2 = {"<anonymous>", "R", "K", "", "V", "Ljava/lang/ref/Reference;", "Lio/ktor/server/sessions/CacheReference;", "key"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.ReferenceCache$container$1", f = "CacheJvm.kt", i = {}, l = {25}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CacheJvm.kt */
final class ReferenceCache$container$1 extends SuspendLambda implements Function2<K, Continuation<? super R>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ReferenceCache<K, V, R> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReferenceCache$container$1(ReferenceCache<K, V, ? extends R> referenceCache, Continuation<? super ReferenceCache$container$1> continuation) {
        super(2, continuation);
        this.this$0 = referenceCache;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ReferenceCache$container$1 referenceCache$container$1 = new ReferenceCache$container$1(this.this$0, continuation);
        referenceCache$container$1.L$0 = obj;
        return referenceCache$container$1;
    }

    public final Object invoke(K k, Continuation<? super R> continuation) {
        return ((ReferenceCache$container$1) create(k, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Function3<K, V, ReferenceQueue<V>, R> function3;
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj3 = this.L$0;
            this.this$0.forkThreadIfNeeded();
            function3 = this.this$0.getWrapFunction();
            Function2<K, Continuation<? super V>, Object> calc = this.this$0.getCalc();
            this.L$0 = function3;
            this.L$1 = obj3;
            this.label = 1;
            Object invoke = calc.invoke(obj3, this);
            if (invoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj2 = obj3;
            obj = invoke;
        } else if (i == 1) {
            obj2 = this.L$1;
            function3 = (Function3) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return function3.invoke(obj2, obj, this.this$0.queue);
    }
}
