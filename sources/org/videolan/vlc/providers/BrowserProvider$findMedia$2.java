package org.videolan.vlc.providers;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$findMedia$2", f = "BrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$findMedia$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaWrapper>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$findMedia$2(BrowserProvider browserProvider, Uri uri, MediaWrapper mediaWrapper, Continuation<? super BrowserProvider$findMedia$2> continuation) {
        super(2, continuation);
        this.this$0 = browserProvider;
        this.$uri = uri;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserProvider$findMedia$2(this.this$0, this.$uri, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaWrapper> continuation) {
        return ((BrowserProvider$findMedia$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CharSequence artworkURL;
        String artworkURL2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper media = this.this$0.getMedialibrary$vlc_android_release().getMedia(this.$uri);
            MediaWrapper mediaWrapper = this.$mw;
            if (media != null && (((artworkURL = media.getArtworkURL()) == null || artworkURL.length() == 0) && (artworkURL2 = mediaWrapper.getArtworkURL()) != null)) {
                Intrinsics.checkNotNull(artworkURL2);
                if (artworkURL2.length() > 0) {
                    media.setArtworkURL(mediaWrapper.getArtworkURL());
                }
            }
            return media == null ? this.$mw : media;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
