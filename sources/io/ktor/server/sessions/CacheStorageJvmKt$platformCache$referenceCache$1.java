package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "id"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.CacheStorageJvmKt$platformCache$referenceCache$1", f = "CacheStorageJvm.kt", i = {}, l = {8}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CacheStorageJvm.kt */
final class CacheStorageJvmKt$platformCache$referenceCache$1 extends SuspendLambda implements Function2<String, Continuation<? super String>, Object> {
    final /* synthetic */ SessionStorage $delegate;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CacheStorageJvmKt$platformCache$referenceCache$1(SessionStorage sessionStorage, Continuation<? super CacheStorageJvmKt$platformCache$referenceCache$1> continuation) {
        super(2, continuation);
        this.$delegate = sessionStorage;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CacheStorageJvmKt$platformCache$referenceCache$1 cacheStorageJvmKt$platformCache$referenceCache$1 = new CacheStorageJvmKt$platformCache$referenceCache$1(this.$delegate, continuation);
        cacheStorageJvmKt$platformCache$referenceCache$1.L$0 = obj;
        return cacheStorageJvmKt$platformCache$referenceCache$1;
    }

    public final Object invoke(String str, Continuation<? super String> continuation) {
        return ((CacheStorageJvmKt$platformCache$referenceCache$1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SessionStorage sessionStorage = this.$delegate;
            this.label = 1;
            obj = sessionStorage.read((String) this.L$0, this);
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
