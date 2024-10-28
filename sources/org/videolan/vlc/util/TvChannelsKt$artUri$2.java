package org.videolan.vlc.util;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$artUri$2", f = "TvChannels.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$artUri$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ MediaWrapper $this_artUri;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$artUri$2(MediaWrapper mediaWrapper, Continuation<? super TvChannelsKt$artUri$2> continuation) {
        super(2, continuation);
        this.$this_artUri = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvChannelsKt$artUri$2(this.$this_artUri, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((TvChannelsKt$artUri$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return ThumbnailsProvider.INSTANCE.getVideoThumbnail(this.$this_artUri, 512);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
