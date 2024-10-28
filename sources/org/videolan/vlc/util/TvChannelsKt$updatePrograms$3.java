package org.videolan.vlc.util;

import android.content.Context;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$updatePrograms$3", f = "TvChannels.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$updatePrograms$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ PreviewProgram $program;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$updatePrograms$3(Context context, PreviewProgram previewProgram, Continuation<? super TvChannelsKt$updatePrograms$3> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$program = previewProgram;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvChannelsKt$updatePrograms$3(this.$context, this.$program, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TvChannelsKt$updatePrograms$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$context.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, this.$program.toContentValues());
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
