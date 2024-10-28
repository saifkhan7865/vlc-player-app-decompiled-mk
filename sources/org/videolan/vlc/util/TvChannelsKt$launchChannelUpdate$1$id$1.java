package org.videolan.vlc.util;

import android.content.Context;
import android.content.SharedPreferences;
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
import org.videolan.tools.Settings;
import videolan.org.commontools.TvChannelUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$launchChannelUpdate$1$id$1", f = "TvChannels.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$launchChannelUpdate$1$id$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    final /* synthetic */ Context $this_launchChannelUpdate;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$launchChannelUpdate$1$id$1(Context context, Continuation<? super TvChannelsKt$launchChannelUpdate$1$id$1> continuation) {
        super(2, continuation);
        this.$this_launchChannelUpdate = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvChannelsKt$launchChannelUpdate$1$id$1(this.$this_launchChannelUpdate, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((TvChannelsKt$launchChannelUpdate$1$id$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxLong(((SharedPreferences) Settings.INSTANCE.getInstance(this.$this_launchChannelUpdate)).getLong(TvChannelUtilsKt.KEY_TV_CHANNEL_ID, -1));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}