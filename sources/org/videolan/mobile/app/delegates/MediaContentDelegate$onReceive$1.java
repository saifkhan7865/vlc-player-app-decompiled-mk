package org.videolan.mobile.app.delegates;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.mobile.app.delegates.MediaContentDelegate$onReceive$1", f = "IMediaContentDelegate.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: IMediaContentDelegate.kt */
final class MediaContentDelegate$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $id;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaContentDelegate$onReceive$1(Context context, String str, Continuation<? super MediaContentDelegate$onReceive$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$id = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaContentDelegate$onReceive$1(this.$context, this.$id, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaContentDelegate$onReceive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: org.videolan.resources.interfaces.IMediaContentResolver} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: org.videolan.resources.interfaces.IMediaContentResolver} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: org.videolan.resources.interfaces.IMediaContentResolver} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: org.videolan.resources.interfaces.IMediaContentResolver} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x005a
        L_0x000f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r9)
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$Companion r9 = org.videolan.moviepedia.provider.MediaScrapingTvshowProvider.Companion
            java.util.List r9 = r9.getProviders()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.lang.String r1 = r8.$id
            java.util.Iterator r9 = r9.iterator()
        L_0x0028:
            boolean r3 = r9.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0043
            java.lang.Object r3 = r9.next()
            r5 = r3
            org.videolan.resources.interfaces.IMediaContentResolver r5 = (org.videolan.resources.interfaces.IMediaContentResolver) r5
            java.lang.String r5 = r5.getPrefix()
            r6 = 0
            r7 = 2
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r1, r5, r6, r7, r4)
            if (r4 == 0) goto L_0x0028
            r4 = r3
        L_0x0043:
            org.videolan.resources.interfaces.IMediaContentResolver r4 = (org.videolan.resources.interfaces.IMediaContentResolver) r4
            if (r4 != 0) goto L_0x004a
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x004a:
            android.content.Context r9 = r8.$context
            java.lang.String r1 = r8.$id
            r3 = r8
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r8.label = r2
            java.lang.Object r9 = r4.getList(r9, r1, r3)
            if (r9 != r0) goto L_0x005a
            return r0
        L_0x005a:
            kotlin.Pair r9 = (kotlin.Pair) r9
            if (r9 == 0) goto L_0x0079
            android.content.Context r1 = r8.$context
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            java.lang.Object r2 = r9.getFirst()
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r9 = r9.getSecond()
            java.lang.Number r9 = (java.lang.Number) r9
            int r3 = r9.intValue()
            r5 = 8
            r6 = 0
            r4 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r0, r1, r2, r3, r4, r5, r6)
        L_0x0079:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.mobile.app.delegates.MediaContentDelegate$onReceive$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
