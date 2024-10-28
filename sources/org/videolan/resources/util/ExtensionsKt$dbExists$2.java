package org.videolan.resources.util;

import android.content.Context;
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
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$dbExists$2", f = "Extensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Extensions.kt */
final class ExtensionsKt$dbExists$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Context $this_dbExists;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtensionsKt$dbExists$2(Context context, Continuation<? super ExtensionsKt$dbExists$2> continuation) {
        super(2, continuation);
        this.$this_dbExists = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$dbExists$2(this.$this_dbExists, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((ExtensionsKt$dbExists$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(new File(this.$this_dbExists.getDir("db", 0).toString() + Medialibrary.VLC_MEDIA_DB_NAME).exists());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
