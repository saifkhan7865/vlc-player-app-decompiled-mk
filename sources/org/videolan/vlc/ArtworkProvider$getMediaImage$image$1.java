package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getMediaImage$image$1 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Integer $fallbackIcon;
    final /* synthetic */ int $height169;
    final /* synthetic */ boolean $isLarge;
    final /* synthetic */ MediaLibraryItem $mw;
    final /* synthetic */ boolean $nonTransparent;
    final /* synthetic */ boolean $padSquare;
    final /* synthetic */ int $width;
    final /* synthetic */ int $width169;
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getMediaImage$image$1(MediaLibraryItem mediaLibraryItem, int i, ArtworkProvider artworkProvider, boolean z, Context context, Integer num, boolean z2, int i2, int i3, boolean z3) {
        super(0);
        this.$mw = mediaLibraryItem;
        this.$width = i;
        this.this$0 = artworkProvider;
        this.$padSquare = z;
        this.$ctx = context;
        this.$fallbackIcon = num;
        this.$isLarge = z2;
        this.$width169 = i2;
        this.$height169 = i3;
        this.$nonTransparent = z3;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getMediaImage$image$1$1", f = "ArtworkProvider.kt", i = {}, l = {251}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.ArtworkProvider$getMediaImage$image$1$1  reason: invalid class name */
    /* compiled from: ArtworkProvider.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaLibraryItem, i, artworkProvider, z, context, num, z2, i2, i3, z3, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x004e  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x008b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L_0x0017
                if (r1 != r2) goto L_0x000f
                kotlin.ResultKt.throwOnFailure(r6)
                goto L_0x0030
            L_0x000f:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L_0x0017:
                kotlin.ResultKt.throwOnFailure(r6)
                org.videolan.medialibrary.media.MediaLibraryItem r6 = r2
                if (r6 == 0) goto L_0x0033
                org.videolan.vlc.util.ThumbnailsProvider r6 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
                org.videolan.medialibrary.media.MediaLibraryItem r1 = r2
                int r3 = r3
                r4 = r5
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r5.label = r2
                java.lang.Object r6 = r6.obtainBitmap(r1, r3, r4)
                if (r6 != r0) goto L_0x0030
                return r0
            L_0x0030:
                android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
                goto L_0x0034
            L_0x0033:
                r6 = 0
            L_0x0034:
                if (r6 != 0) goto L_0x0040
                org.videolan.vlc.ArtworkProvider r6 = r4
                org.videolan.medialibrary.media.MediaLibraryItem r0 = r2
                int r1 = r3
                android.graphics.Bitmap r6 = r6.readEmbeddedArtwork(r0, r1)
            L_0x0040:
                boolean r0 = r5
                if (r0 == 0) goto L_0x004c
                if (r6 == 0) goto L_0x004c
                org.videolan.vlc.ArtworkProvider r0 = r4
                android.graphics.Bitmap r6 = r0.padSquare(r6)
            L_0x004c:
                if (r6 != 0) goto L_0x0087
                android.content.Context r6 = r6
                java.lang.Integer r0 = r7
                if (r0 == 0) goto L_0x0059
                int r0 = r0.intValue()
                goto L_0x005b
            L_0x0059:
                int r0 = org.videolan.vlc.R.drawable.ic_no_media
            L_0x005b:
                int r1 = r3
                android.graphics.Bitmap r6 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable(r6, r0, r1, r1)
                boolean r0 = r8
                if (r0 == 0) goto L_0x0087
                if (r6 == 0) goto L_0x0087
                android.graphics.Paint r0 = new android.graphics.Paint
                r0.<init>()
                int r1 = r9
                int r2 = r10
                android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
                android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r1, r2, r3)
                java.lang.String r2 = "createBitmap(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                android.graphics.Canvas r2 = new android.graphics.Canvas
                r2.<init>(r1)
                r3 = 1130364928(0x43600000, float:224.0)
                r4 = 0
                r2.drawBitmap(r6, r3, r4, r0)
                r6 = r1
            L_0x0087:
                boolean r0 = r11
                if (r0 == 0) goto L_0x0091
                org.videolan.vlc.ArtworkProvider r0 = r4
                android.graphics.Bitmap r6 = r0.removeTransparency(r6)
            L_0x0091:
                org.videolan.vlc.gui.helpers.BitmapUtil r0 = org.videolan.vlc.gui.helpers.BitmapUtil.INSTANCE
                org.videolan.vlc.ArtworkProvider$getMediaImage$image$1$1$1 r1 = new org.videolan.vlc.ArtworkProvider$getMediaImage$image$1$1$1
                org.videolan.vlc.ArtworkProvider r2 = r4
                r1.<init>(r2)
                kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
                r2 = 0
                byte[] r6 = r0.encodeImage(r6, r2, r1)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider$getMediaImage$image$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final byte[] invoke() {
        final MediaLibraryItem mediaLibraryItem = this.$mw;
        final int i = this.$width;
        final ArtworkProvider artworkProvider = this.this$0;
        final boolean z = this.$padSquare;
        final Context context = this.$ctx;
        final Integer num = this.$fallbackIcon;
        final boolean z2 = this.$isLarge;
        final int i2 = this.$width169;
        final int i3 = this.$height169;
        final boolean z3 = this.$nonTransparent;
        return (byte[]) BuildersKt.runBlocking(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
