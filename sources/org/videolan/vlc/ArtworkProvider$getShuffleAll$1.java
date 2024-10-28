package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getShuffleAll$1", f = "ArtworkProvider.kt", i = {}, l = {644, 646, 364}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getShuffleAll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    final /* synthetic */ Context $ctx;
    int label;
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getShuffleAll$1(Context context, ArtworkProvider artworkProvider, Continuation<? super ArtworkProvider$getShuffleAll$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.this$0 = artworkProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getShuffleAll$1(this.$ctx, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
        return ((ArtworkProvider$getShuffleAll$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0087 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0088 A[PHI: r8 
      PHI: (r8v1 java.lang.Object) = (r8v4 java.lang.Object), (r8v0 java.lang.Object) binds: [B:17:0x0085, B:5:0x0012] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r5) goto L_0x0022
            if (r1 == r4) goto L_0x001e
            if (r1 != r3) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0088
        L_0x0016:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0074
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0044
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r8)
            android.content.Context r8 = r7.$ctx
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.ArtworkProvider$getShuffleAll$1$invokeSuspend$$inlined$getFromMl$1 r6 = new org.videolan.vlc.ArtworkProvider$getShuffleAll$1$invokeSuspend$$inlined$getFromMl$1
            r6.<init>(r8, r2)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r5
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r1, r6, r8)
            if (r8 != r0) goto L_0x0044
            return r0
        L_0x0044:
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            java.security.SecureRandom r1 = new java.security.SecureRandom
            r1.<init>()
            int r8 = r8 + -50
            int r8 = kotlin.ranges.RangesKt.coerceAtLeast((int) r8, (int) r5)
            int r8 = r1.nextInt(r8)
            android.content.Context r1 = r7.$ctx
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            org.videolan.vlc.ArtworkProvider$getShuffleAll$1$invokeSuspend$$inlined$getFromMl$2 r6 = new org.videolan.vlc.ArtworkProvider$getShuffleAll$1$invokeSuspend$$inlined$getFromMl$2
            r6.<init>(r1, r2, r8)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r4
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r8)
            if (r8 != r0) goto L_0x0074
            return r0
        L_0x0074:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r8
            org.videolan.vlc.ArtworkProvider r1 = r7.this$0
            android.content.Context r2 = r7.$ctx
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.label = r3
            java.lang.String r3 = "shuffle_all"
            java.lang.Object r8 = r1.getHomeImage(r2, r3, r8, r4)
            if (r8 != r0) goto L_0x0088
            return r0
        L_0x0088:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider$getShuffleAll$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
