package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingContentFragment$onViewCreated$2$1", f = "RemoteAccessOnboardingContentFragment.kt", i = {}, l = {67}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessOnboardingContentFragment.kt */
final class RemoteAccessOnboardingContentFragment$onViewCreated$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteAccessOnboardingContentFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessOnboardingContentFragment$onViewCreated$2$1(RemoteAccessOnboardingContentFragment remoteAccessOnboardingContentFragment, Continuation<? super RemoteAccessOnboardingContentFragment$onViewCreated$2$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessOnboardingContentFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessOnboardingContentFragment$onViewCreated$2$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessOnboardingContentFragment$onViewCreated$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingContentFragment$onViewCreated$2$1$1", f = "RemoteAccessOnboardingContentFragment.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingContentFragment$onViewCreated$2$1$1  reason: invalid class name */
    /* compiled from: RemoteAccessOnboardingContentFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(remoteAccessOnboardingContentFragment, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(2000, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            remoteAccessOnboardingContentFragment.animationLoop.start();
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner viewLifecycleOwner = this.this$0.getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
            Lifecycle.State state = Lifecycle.State.STARTED;
            final RemoteAccessOnboardingContentFragment remoteAccessOnboardingContentFragment = this.this$0;
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1((Continuation<? super AnonymousClass1>) null), (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
