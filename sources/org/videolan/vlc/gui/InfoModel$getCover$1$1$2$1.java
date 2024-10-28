package org.videolan.vlc.gui;

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
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoModel$getCover$1$1$2$1", f = "InfoActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: InfoActivity.kt */
final class InfoModel$getCover$1$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ MediaWrapper $media;
    final /* synthetic */ int $width;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoModel$getCover$1$1$2$1(MediaWrapper mediaWrapper, int i, Continuation<? super InfoModel$getCover$1$1$2$1> continuation) {
        super(2, continuation);
        this.$media = mediaWrapper;
        this.$width = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new InfoModel$getCover$1$1$2$1(this.$media, this.$width, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((InfoModel$getCover$1$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return ThumbnailsProvider.INSTANCE.getVideoThumbnail(this.$media, this.$width);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
