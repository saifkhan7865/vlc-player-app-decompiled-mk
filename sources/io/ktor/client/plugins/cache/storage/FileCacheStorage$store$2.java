package io.ktor.client.plugins.cache.storage;

import io.ktor.http.Url;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage$store$2", f = "FileCacheStorage.kt", i = {0}, l = {72, 73}, m = "invokeSuspend", n = {"urlHex"}, s = {"L$0"})
/* compiled from: FileCacheStorage.kt */
final class FileCacheStorage$store$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CachedResponseData $data;
    final /* synthetic */ Url $url;
    Object L$0;
    int label;
    final /* synthetic */ FileCacheStorage this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileCacheStorage$store$2(FileCacheStorage fileCacheStorage, Url url, CachedResponseData cachedResponseData, Continuation<? super FileCacheStorage$store$2> continuation) {
        super(2, continuation);
        this.this$0 = fileCacheStorage;
        this.$url = url;
        this.$data = cachedResponseData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileCacheStorage$store$2(this.this$0, this.$url, this.$data, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileCacheStorage$store$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            str = this.this$0.key(this.$url);
            this.L$0 = str;
            this.label = 1;
            obj = this.this$0.readCache(str, (Continuation<? super Set<CachedResponseData>>) this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            str = (String) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        CachedResponseData cachedResponseData = this.$data;
        Collection arrayList = new ArrayList();
        for (Object next : (Iterable) obj) {
            if (!Intrinsics.areEqual((Object) ((CachedResponseData) next).getVaryKeys(), (Object) cachedResponseData.getVaryKeys())) {
                arrayList.add(next);
            }
        }
        List plus = CollectionsKt.plus((List) arrayList, this.$data);
        this.L$0 = null;
        this.label = 2;
        if (this.this$0.writeCache(str, (List<CachedResponseData>) plus, (Continuation<Object>) this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
