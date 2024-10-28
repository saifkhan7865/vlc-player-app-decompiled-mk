package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$onItemRemoved$1", f = "PlaylistManager.kt", i = {}, l = {640, 644, 649}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$onItemRemoved$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $currentRemoved;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$onItemRemoved$1(PlaylistManager playlistManager, boolean z, Continuation<? super PlaylistManager$onItemRemoved$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
        this.$currentRemoved = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$onItemRemoved$1(this.this$0, this.$currentRemoved, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$onItemRemoved$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 3
            r5 = 0
            r6 = 0
            r7 = 1
            if (r2 == 0) goto L_0x002a
            if (r2 == r7) goto L_0x0026
            if (r2 == r3) goto L_0x0022
            if (r2 != r4) goto L_0x001a
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x008f
        L_0x001a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x007c
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x003b
        L_0x002a:
            kotlin.ResultKt.throwOnFailure(r18)
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            r8 = r0
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r0.label = r7
            java.lang.Object r2 = org.videolan.vlc.media.PlaylistManager.determinePrevAndNextIndices$default(r2, r6, r8, r7, r5)
            if (r2 != r1) goto L_0x003b
            return r1
        L_0x003b:
            boolean r2 = r0.$currentRemoved
            if (r2 == 0) goto L_0x007c
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            boolean r2 = r2.expanding
            if (r2 != 0) goto L_0x007c
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            int r2 = r2.nextIndex
            r8 = -1
            if (r2 == r8) goto L_0x0056
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            org.videolan.vlc.media.PlaylistManager.next$default(r2, r6, r7, r5)
            goto L_0x007c
        L_0x0056:
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            int r2 = r2.getCurrentIndex()
            if (r2 == r8) goto L_0x0077
            org.videolan.vlc.media.PlaylistManager r9 = r0.this$0
            int r10 = r9.getCurrentIndex()
            r14 = r0
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r0.label = r3
            r11 = 0
            r12 = 0
            r13 = 0
            r15 = 12
            r16 = 0
            java.lang.Object r2 = org.videolan.vlc.media.PlaylistManager.playIndex$default(r9, r10, r11, r12, r13, r14, r15, r16)
            if (r2 != r1) goto L_0x007c
            return r1
        L_0x0077:
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            org.videolan.vlc.media.PlaylistManager.stop$default(r2, r6, r6, r4, r5)
        L_0x007c:
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            r2.executeUpdate()
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            r3 = r0
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r0.label = r4
            java.lang.Object r2 = org.videolan.vlc.media.PlaylistManager.saveMediaList$default(r2, r6, r3, r7, r5)
            if (r2 != r1) goto L_0x008f
            return r1
        L_0x008f:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager$onItemRemoved$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
