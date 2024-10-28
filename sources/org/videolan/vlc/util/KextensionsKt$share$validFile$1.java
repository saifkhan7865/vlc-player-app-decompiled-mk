package org.videolan.vlc.util;

import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$share$validFile$1", f = "Kextensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Kextensions.kt */
final class KextensionsKt$share$validFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ File $fileWithinMyDir;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KextensionsKt$share$validFile$1(File file, Continuation<? super KextensionsKt$share$validFile$1> continuation) {
        super(2, continuation);
        this.$fileWithinMyDir = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new KextensionsKt$share$validFile$1(this.$fileWithinMyDir, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((KextensionsKt$share$validFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.$fileWithinMyDir.exists());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
