package org.videolan.vlc.util;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import videolan.org.commontools.TvChannelUtilsKt;
import videolan.org.commontools.TvPreviewProgram;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lvideolan/org/commontools/TvPreviewProgram;", "Lvideolan/org/commontools/ProgramsList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$updatePrograms$programs$1", f = "TvChannels.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$updatePrograms$programs$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<TvPreviewProgram>>, Object> {
    final /* synthetic */ long $channelId;
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$updatePrograms$programs$1(Context context, long j, Continuation<? super TvChannelsKt$updatePrograms$programs$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$channelId = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvChannelsKt$updatePrograms$programs$1(this.$context, this.$channelId, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<TvPreviewProgram>> continuation) {
        return ((TvChannelsKt$updatePrograms$programs$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return TvChannelUtilsKt.existingPrograms(this.$context, this.$channelId);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
