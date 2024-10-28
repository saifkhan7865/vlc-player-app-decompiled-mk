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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import videolan.org.commontools.TvChannelUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$setChannel$1$channelId$1", f = "TvChannels.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$setChannel$1$channelId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$setChannel$1$channelId$1(Context context, Continuation<? super TvChannelsKt$setChannel$1$channelId$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvChannelsKt$setChannel$1$channelId$1(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((TvChannelsKt$setChannel$1$channelId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String string = this.$context.getString(R.string.tv_my_new_videos);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return Boxing.boxLong(TvChannelUtilsKt.createOrUpdateChannel((SharedPreferences) Settings.INSTANCE.getInstance(this.$context), this.$context, string, R.drawable.ic_channel_icon));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
