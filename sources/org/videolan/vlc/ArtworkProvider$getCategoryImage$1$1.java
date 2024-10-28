package org.videolan.vlc;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getCategoryImage$1$1", f = "ArtworkProvider.kt", i = {}, l = {194}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getCategoryImage$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    final /* synthetic */ MediaLibraryItem $mw;
    int label;
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getCategoryImage$1$1(MediaLibraryItem mediaLibraryItem, ArtworkProvider artworkProvider, Continuation<? super ArtworkProvider$getCategoryImage$1$1> continuation) {
        super(2, continuation);
        this.$mw = mediaLibraryItem;
        this.this$0 = artworkProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getCategoryImage$1$1(this.$mw, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
        return ((ArtworkProvider$getCategoryImage$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = ThumbnailsProvider.INSTANCE.obtainBitmap(this.$mw, 256, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap != null) {
            bitmap = this.this$0.padSquare(bitmap);
        }
        BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
        final ArtworkProvider artworkProvider = this.this$0;
        return bitmapUtil.encodeImage(bitmap, false, new Function0<String>() {
            public final String invoke() {
                return artworkProvider.getTimestamp();
            }
        });
    }
}
