package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0003 \u0000*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "V", "K", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cache.kt */
final class BaseCache$getOrCompute$2 extends Lambda implements Function0<Deferred<? extends V>> {
    final /* synthetic */ CoroutineContext $coroutineContext;
    final /* synthetic */ K $key;
    final /* synthetic */ BaseCache<K, V> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseCache$getOrCompute$2(CoroutineContext coroutineContext, BaseCache<? super K, V> baseCache, K k) {
        super(0);
        this.$coroutineContext = coroutineContext;
        this.this$0 = baseCache;
        this.$key = k;
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0002 \u0000*\u00020\u0003\"\b\b\u0001\u0010\u0001*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "V", "K", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.sessions.BaseCache$getOrCompute$2$1", f = "Cache.kt", i = {}, l = {51}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.sessions.BaseCache$getOrCompute$2$1  reason: invalid class name */
    /* compiled from: Cache.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super V>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(baseCache, k, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super V> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function2<K, Continuation<? super V>, Object> calc = baseCache.getCalc();
                K k = k;
                this.label = 1;
                obj = calc.invoke(k, this);
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

    public final Deferred<V> invoke() {
        final BaseCache<K, V> baseCache = this.this$0;
        final K k = this.$key;
        return BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(this.$coroutineContext.minusKey(Job.Key)), Dispatchers.getUnconfined(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
    }
}
