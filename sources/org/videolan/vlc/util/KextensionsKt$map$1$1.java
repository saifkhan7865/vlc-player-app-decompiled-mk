package org.videolan.vlc.util;

import androidx.lifecycle.MediatorLiveData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "X", "Y", "it", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Kextensions.kt */
final class KextensionsKt$map$1$1 extends Lambda implements Function1<X, Unit> {
    final /* synthetic */ Function2<X, Continuation<? super Y>, Object> $f;
    final /* synthetic */ MediatorLiveData<Y> $this_apply;
    final /* synthetic */ CoroutineScope $this_map;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KextensionsKt$map$1$1(CoroutineScope coroutineScope, MediatorLiveData<Y> mediatorLiveData, Function2<? super X, ? super Continuation<? super Y>, ? extends Object> function2) {
        super(1);
        this.$this_map = coroutineScope;
        this.$this_apply = mediatorLiveData;
        this.$f = function2;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "X", "Y", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$map$1$1$1", f = "Kextensions.kt", i = {}, l = {192}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.util.KextensionsKt$map$1$1$1  reason: invalid class name */
    /* compiled from: Kextensions.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediatorLiveData, function2, x, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            MediatorLiveData<Y> mediatorLiveData;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MediatorLiveData<Y> mediatorLiveData2 = mediatorLiveData;
                Function2<X, Continuation<? super Y>, Object> function2 = function2;
                X x = x;
                this.L$0 = mediatorLiveData2;
                this.label = 1;
                Object invoke = function2.invoke(x, this);
                if (invoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mediatorLiveData = mediatorLiveData2;
                obj = invoke;
            } else if (i == 1) {
                mediatorLiveData = (MediatorLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mediatorLiveData.setValue(obj);
            return Unit.INSTANCE;
        }
    }

    public final void invoke(final X x) {
        CoroutineScope coroutineScope = this.$this_map;
        final MediatorLiveData<Y> mediatorLiveData = this.$this_apply;
        final Function2<X, Continuation<? super Y>, Object> function2 = this.$f;
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }
}
