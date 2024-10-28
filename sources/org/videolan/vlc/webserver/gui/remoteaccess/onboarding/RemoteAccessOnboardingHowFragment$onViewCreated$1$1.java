package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.view.View;
import android.widget.ImageView;
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
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingHowFragment$onViewCreated$1$1", f = "RemoteAccessOnboardingHowFragment.kt", i = {}, l = {90}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessOnboardingHowFragment.kt */
final class RemoteAccessOnboardingHowFragment$onViewCreated$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteAccessOnboardingHowFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessOnboardingHowFragment$onViewCreated$1$1(RemoteAccessOnboardingHowFragment remoteAccessOnboardingHowFragment, Continuation<? super RemoteAccessOnboardingHowFragment$onViewCreated$1$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessOnboardingHowFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessOnboardingHowFragment$onViewCreated$1$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessOnboardingHowFragment$onViewCreated$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingHowFragment$onViewCreated$1$1$1", f = "RemoteAccessOnboardingHowFragment.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingHowFragment$onViewCreated$1$1$1  reason: invalid class name */
    /* compiled from: RemoteAccessOnboardingHowFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(remoteAccessOnboardingHowFragment, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                remoteAccessOnboardingHowFragment.animSet.cancel();
                this.label = 1;
                if (DelayKt.delay(1500, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            View access$getBrowserLink$p = remoteAccessOnboardingHowFragment.browserLink;
            ImageView imageView = null;
            if (access$getBrowserLink$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
                access$getBrowserLink$p = null;
            }
            KotlinExtensionsKt.setVisible(access$getBrowserLink$p);
            ImageView access$getPlayPause$p = remoteAccessOnboardingHowFragment.playPause;
            if (access$getPlayPause$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPause");
            } else {
                imageView = access$getPlayPause$p;
            }
            KotlinExtensionsKt.setVisible(imageView);
            remoteAccessOnboardingHowFragment.animSet.start();
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
            final RemoteAccessOnboardingHowFragment remoteAccessOnboardingHowFragment = this.this$0;
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
