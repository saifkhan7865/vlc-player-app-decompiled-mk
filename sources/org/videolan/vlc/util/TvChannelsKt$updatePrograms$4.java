package org.videolan.vlc.util;

import android.content.Context;
import androidx.tvprovider.media.tv.TvContractCompat;
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
import videolan.org.commontools.TvPreviewProgram;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$updatePrograms$4", f = "TvChannels.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$updatePrograms$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ TvPreviewProgram $program;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$updatePrograms$4(Context context, TvPreviewProgram tvPreviewProgram, Continuation<? super TvChannelsKt$updatePrograms$4> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$program = tvPreviewProgram;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvChannelsKt$updatePrograms$4(this.$context, this.$program, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((TvChannelsKt$updatePrograms$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(this.$context.getContentResolver().delete(TvContractCompat.buildPreviewProgramUri(this.$program.getProgramId()), (String) null, (String[]) null));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
