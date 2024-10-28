package org.videolan.resources;

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
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.VLCInstance$restart$2", f = "VLCInstance.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VLCInstance.kt */
final class VLCInstance$restart$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    VLCInstance$restart$2(Continuation<? super VLCInstance$restart$2> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VLCInstance$restart$2(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VLCInstance$restart$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ILibVLC access$getSLibVLC$p = VLCInstance.sLibVLC;
            ILibVLC iLibVLC = null;
            if (access$getSLibVLC$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
                access$getSLibVLC$p = null;
            }
            access$getSLibVLC$p.release();
            VLCInstance vLCInstance = VLCInstance.INSTANCE;
            ILibVLC fromOptions = VLCInstance.libVLCFactory.getFromOptions(AppContextProvider.INSTANCE.getAppContext(), VLCOptions.INSTANCE.getLibOptions());
            Intrinsics.checkNotNullExpressionValue(fromOptions, "getFromOptions(...)");
            VLCInstance.sLibVLC = fromOptions;
            VLCInstance vLCInstance2 = VLCInstance.INSTANCE;
            ILibVLC access$getSLibVLC$p2 = VLCInstance.sLibVLC;
            if (access$getSLibVLC$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
                access$getSLibVLC$p2 = null;
            }
            vLCInstance2.setInstance(access$getSLibVLC$p2);
            Medialibrary instance = Medialibrary.getInstance();
            ILibVLC access$getSLibVLC$p3 = VLCInstance.sLibVLC;
            if (access$getSLibVLC$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
            } else {
                iLibVLC = access$getSLibVLC$p3;
            }
            instance.setLibVLCInstance(((LibVLC) iLibVLC).getInstance());
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
