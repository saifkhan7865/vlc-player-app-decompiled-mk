package org.videolan.vlc.gui.helpers;

import android.view.View;
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
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt$loadImage$2", f = "ImageLoader.kt", i = {}, l = {91, 91}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$loadImage$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ViewDataBinding $binding;
    final /* synthetic */ boolean $card;
    final /* synthetic */ int $imageWidth;
    final /* synthetic */ boolean $isMedia;
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ boolean $tv;
    final /* synthetic */ View $v;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderKt$loadImage$2(View view, MediaLibraryItem mediaLibraryItem, boolean z, ViewDataBinding viewDataBinding, int i, boolean z2, boolean z3, Continuation<? super ImageLoaderKt$loadImage$2> continuation) {
        super(2, continuation);
        this.$v = view;
        this.$item = mediaLibraryItem;
        this.$isMedia = z;
        this.$binding = viewDataBinding;
        this.$imageWidth = i;
        this.$tv = z2;
        this.$card = z3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageLoaderKt$loadImage$2(this.$v, this.$item, this.$isMedia, this.$binding, this.$imageWidth, this.$tv, this.$card, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImageLoaderKt$loadImage$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        View view;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            view = this.$v;
            this.L$0 = view;
            this.label = 1;
            obj = ImageLoaderKt.findInLibrary(this.$item, this.$isMedia, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            view = (View) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.L$0 = null;
        this.label = 2;
        if (ImageLoaderKt.getImage(view, (MediaLibraryItem) obj, this.$binding, this.$imageWidth, this.$tv, this.$card, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
