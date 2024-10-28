package org.videolan.vlc.gui.helpers;

import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt$loadPlaylistImageWithWidth$2", f = "ImageLoader.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$loadPlaylistImageWithWidth$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ViewDataBinding $binding;
    final /* synthetic */ boolean $card;
    final /* synthetic */ int $imageWidth;
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ ImageView $v;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderKt$loadPlaylistImageWithWidth$2(ImageView imageView, MediaLibraryItem mediaLibraryItem, ViewDataBinding viewDataBinding, int i, boolean z, Continuation<? super ImageLoaderKt$loadPlaylistImageWithWidth$2> continuation) {
        super(2, continuation);
        this.$v = imageView;
        this.$item = mediaLibraryItem;
        this.$binding = viewDataBinding;
        this.$imageWidth = i;
        this.$card = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageLoaderKt$loadPlaylistImageWithWidth$2(this.$v, this.$item, this.$binding, this.$imageWidth, this.$card, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImageLoaderKt$loadPlaylistImageWithWidth$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (ImageLoaderKt.getPlaylistOrGenreImage(this.$v, this.$item, this.$binding, this.$imageWidth, this.$card, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
