package org.videolan.vlc.gui;

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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.LibrariesActivity$onCreate$3", f = "LibrariesActivity.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: LibrariesActivity.kt */
final class LibrariesActivity$onCreate$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ LibrariesActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LibrariesActivity$onCreate$3(LibrariesActivity librariesActivity, Continuation<? super LibrariesActivity$onCreate$3> continuation) {
        super(2, continuation);
        this.this$0 = librariesActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibrariesActivity$onCreate$3(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibrariesActivity$onCreate$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LicenseModel access$getModel$p = this.this$0.model;
            if (access$getModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                access$getModel$p = null;
            }
            this.label = 1;
            if (access$getModel$p.refresh(this) == coroutine_suspended) {
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
