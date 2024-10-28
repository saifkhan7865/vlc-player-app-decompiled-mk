package org.videolan.vlc.gui.helpers;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import androidx.databinding.DataBindingUtil;
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
import org.videolan.tools.HttpImageLoader;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt$downloadIcon$4", f = "ImageLoader.kt", i = {}, l = {211}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$downloadIcon$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Uri $imageUri;
    final /* synthetic */ boolean $tv;
    final /* synthetic */ View $v;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderKt$downloadIcon$4(Uri uri, View view, boolean z, Continuation<? super ImageLoaderKt$downloadIcon$4> continuation) {
        super(2, continuation);
        this.$imageUri = uri;
        this.$v = view;
        this.$tv = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageLoaderKt$downloadIcon$4(this.$imageUri, this.$v, this.$tv, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImageLoaderKt$downloadIcon$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HttpImageLoader httpImageLoader = HttpImageLoader.INSTANCE;
            String uri = this.$imageUri.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            this.label = 1;
            obj = httpImageLoader.downloadBitmap(uri, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        View view = this.$v;
        ImageLoaderKt.updateImageView$default((Bitmap) obj, view, DataBindingUtil.findBinding(view), false, this.$tv, false, 40, (Object) null);
        return Unit.INSTANCE;
    }
}
