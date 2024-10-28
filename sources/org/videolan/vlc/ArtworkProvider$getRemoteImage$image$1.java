package org.videolan.vlc;

import android.content.Context;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getRemoteImage$image$1 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ String $path;
    final /* synthetic */ int $width;
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getRemoteImage$image$1(String str, int i, ArtworkProvider artworkProvider, Context context) {
        super(0);
        this.$path = str;
        this.$width = i;
        this.this$0 = artworkProvider;
        this.$ctx = context;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getRemoteImage$image$1$1", f = "ArtworkProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.ArtworkProvider$getRemoteImage$image$1$1  reason: invalid class name */
    /* compiled from: ArtworkProvider.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(str, i, artworkProvider, context, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Bitmap readCoverBitmap = AudioUtil.INSTANCE.readCoverBitmap(str, i);
                if (readCoverBitmap != null) {
                    readCoverBitmap = artworkProvider.padSquare(readCoverBitmap);
                }
                if (readCoverBitmap == null) {
                    Context context = context;
                    int i = R.drawable.ic_no_media;
                    int i2 = i;
                    readCoverBitmap = BitmapUtilKt.getBitmapFromDrawable(context, i, i2, i2);
                }
                BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
                final ArtworkProvider artworkProvider = artworkProvider;
                return bitmapUtil.encodeImage(readCoverBitmap, false, new Function0<String>() {
                    public final String invoke() {
                        return artworkProvider.getTimestamp();
                    }
                });
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final byte[] invoke() {
        final String str = this.$path;
        final int i = this.$width;
        final ArtworkProvider artworkProvider = this.this$0;
        final Context context = this.$ctx;
        return (byte[]) BuildersKt.runBlocking(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
