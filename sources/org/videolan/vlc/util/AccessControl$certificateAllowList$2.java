package org.videolan.vlc.util;

import java.util.Map;
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

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "Lorg/videolan/vlc/util/CertInfo;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessControl.kt */
final class AccessControl$certificateAllowList$2 extends Lambda implements Function0<Map<String, ? extends CertInfo>> {
    public static final AccessControl$certificateAllowList$2 INSTANCE = new AccessControl$certificateAllowList$2();

    AccessControl$certificateAllowList$2() {
        super(0);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "", "Lorg/videolan/vlc/util/CertInfo;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.util.AccessControl$certificateAllowList$2$1", f = "AccessControl.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.util.AccessControl$certificateAllowList$2$1  reason: invalid class name */
    /* compiled from: AccessControl.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, ? extends CertInfo>>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, CertInfo>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = AccessControl.INSTANCE.loadAuthorizedKeys(this);
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

    public final Map<String, CertInfo> invoke() {
        return (Map) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 1, (Object) null);
    }
}
