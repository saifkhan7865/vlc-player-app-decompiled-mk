package org.videolan.vlc.gui.view;

import android.graphics.Bitmap;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.helpers.AudioUtil;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Triple;", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$2", f = "AudioMediaSwitcher.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioMediaSwitcher.kt */
final class AudioMediaSwitcher$updateMedia$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Triple<? extends Bitmap, ? extends Bitmap, ? extends Bitmap>>, Object> {
    final /* synthetic */ String $artMrl;
    final /* synthetic */ String $nextArtMrl;
    final /* synthetic */ String $prevArtMrl;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioMediaSwitcher$updateMedia$2(String str, String str2, String str3, Continuation<? super AudioMediaSwitcher$updateMedia$2> continuation) {
        super(2, continuation);
        this.$artMrl = str;
        this.$prevArtMrl = str2;
        this.$nextArtMrl = str3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioMediaSwitcher$updateMedia$2(this.$artMrl, this.$prevArtMrl, this.$nextArtMrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Triple<Bitmap, Bitmap, Bitmap>> continuation) {
        return ((AudioMediaSwitcher$updateMedia$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Bitmap readCoverBitmap = AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(this.$artMrl), 512);
            Bitmap readCoverBitmap2 = AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(this.$prevArtMrl), 512);
            String str = this.$nextArtMrl;
            return new Triple(readCoverBitmap, readCoverBitmap2, str != null ? AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(str), 512) : null);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
