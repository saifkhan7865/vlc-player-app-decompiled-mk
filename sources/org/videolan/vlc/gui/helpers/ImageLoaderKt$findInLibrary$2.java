package org.videolan.vlc.gui.helpers;

import android.net.Uri;
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

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$2", f = "ImageLoader.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$findInLibrary$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaWrapper>, Object> {
    final /* synthetic */ Uri $uri;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderKt$findInLibrary$2(Uri uri, Continuation<? super ImageLoaderKt$findInLibrary$2> continuation) {
        super(2, continuation);
        this.$uri = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageLoaderKt$findInLibrary$2(this.$uri, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaWrapper> continuation) {
        return ((ImageLoaderKt$findInLibrary$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return ImageLoaderKt.sMedialibrary.getMedia(this.$uri);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
