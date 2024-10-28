package org.videolan.television.ui;

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
import org.videolan.vlc.gui.helpers.AudioUtil;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2$cover$1", f = "MediaItemDetailsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaItemDetailsFragment.kt */
final class MediaItemDetailsFragment$buildDetails$2$cover$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    int label;
    final /* synthetic */ MediaItemDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaItemDetailsFragment$buildDetails$2$cover$1(MediaItemDetailsFragment mediaItemDetailsFragment, Continuation<? super MediaItemDetailsFragment$buildDetails$2$cover$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaItemDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaItemDetailsFragment$buildDetails$2$cover$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((MediaItemDetailsFragment$buildDetails$2$cover$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return AudioUtil.INSTANCE.readCoverBitmap(this.this$0.getViewModel().getMediaItemDetails().getArtworkUrl(), 512);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
