package org.videolan.vlc.gui;

import android.content.Intent;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import kotlin.KotlinNothingValueException;
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
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.videolan.vlc.util.RemoteAccessUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.BaseActivity$onCreate$3", f = "BaseActivity.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseActivity.kt */
final class BaseActivity$onCreate$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ BaseActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseActivity$onCreate$3(BaseActivity baseActivity, Continuation<? super BaseActivity$onCreate$3> continuation) {
        super(2, continuation);
        this.this$0 = baseActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseActivity$onCreate$3(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseActivity$onCreate$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.BaseActivity$onCreate$3$1", f = "BaseActivity.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.BaseActivity$onCreate$3$1  reason: invalid class name */
    /* compiled from: BaseActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(baseActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow<String> otpFlow = RemoteAccessUtils.INSTANCE.getOtpFlow();
                final BaseActivity baseActivity = baseActivity;
                this.label = 1;
                if (otpFlow.collect(new FlowCollector() {
                    public final Object emit(String str, Continuation<? super Unit> continuation) {
                        if (!baseActivity.isOTPActivity() && str != null && !Intrinsics.areEqual((Object) baseActivity.lastDisplayedOTPCode, (Object) str)) {
                            baseActivity.lastDisplayedOTPCode = str;
                            Intent intent = new Intent(baseActivity, OTPCodeActivity.class);
                            intent.setFlags(intent.getFlags() | 1073741824);
                            baseActivity.startActivity(intent);
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.RESUMED;
            final BaseActivity baseActivity = this.this$0;
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle((LifecycleOwner) this.this$0, state, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1((Continuation<? super AnonymousClass1>) null), (Continuation<? super Unit>) this) == coroutine_suspended) {
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
