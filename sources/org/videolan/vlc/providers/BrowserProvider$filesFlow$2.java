package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;
import org.videolan.libvlc.interfaces.IMedia;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lorg/videolan/libvlc/interfaces/IMedia;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$filesFlow$2", f = "BrowserProvider.kt", i = {0}, l = {261, 262}, m = "invokeSuspend", n = {"$this$channelFlow"}, s = {"L$0"})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$filesFlow$2 extends SuspendLambda implements Function2<ProducerScope<? super IMedia>, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $interact;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$filesFlow$2(BrowserProvider browserProvider, String str, boolean z, Continuation<? super BrowserProvider$filesFlow$2> continuation) {
        super(2, continuation);
        this.this$0 = browserProvider;
        this.$url = str;
        this.$interact = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BrowserProvider$filesFlow$2 browserProvider$filesFlow$2 = new BrowserProvider$filesFlow$2(this.this$0, this.$url, this.$interact, continuation);
        browserProvider$filesFlow$2.L$0 = obj;
        return browserProvider$filesFlow$2;
    }

    public final Object invoke(ProducerScope<? super IMedia> producerScope, Continuation<? super Unit> continuation) {
        return ((BrowserProvider$filesFlow$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x005f
        L_0x0012:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001a:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0045
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            r1 = r9
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            org.videolan.vlc.providers.BrowserProvider$filesFlow$2$listener$1 r9 = new org.videolan.vlc.providers.BrowserProvider$filesFlow$2$listener$1
            r9.<init>(r1)
            org.videolan.vlc.providers.BrowserProvider r4 = r8.this$0
            java.lang.String r5 = r8.$url
            org.videolan.libvlc.util.MediaBrowser$EventListener r9 = (org.videolan.libvlc.util.MediaBrowser.EventListener) r9
            boolean r6 = r8.$interact
            r7 = r8
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r9 = r4.requestBrowsing(r5, r9, r6, r7)
            if (r9 != r0) goto L_0x0045
            return r0
        L_0x0045:
            org.videolan.vlc.providers.BrowserProvider$filesFlow$2$1 r9 = new org.videolan.vlc.providers.BrowserProvider$filesFlow$2$1
            java.lang.String r3 = r8.$url
            org.videolan.vlc.providers.BrowserProvider r4 = r8.this$0
            r9.<init>(r3, r4)
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            r3 = r8
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 0
            r8.L$0 = r4
            r8.label = r2
            java.lang.Object r9 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r1, r9, r3)
            if (r9 != r0) goto L_0x005f
            return r0
        L_0x005f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider$filesFlow$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
