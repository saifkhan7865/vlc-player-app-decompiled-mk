package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/providers/BrowserAction;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$browserActor$1", f = "BrowserProvider.kt", i = {0, 1, 2, 3, 4, 5, 6, 7, 8}, l = {140, 142, 143, 145, 146, 148, 149, 150, 157}, m = "invokeSuspend", n = {"$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$0"})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$browserActor$1 extends SuspendLambda implements Function2<ActorScope<BrowserAction>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$browserActor$1(BrowserProvider browserProvider, Continuation<? super BrowserProvider$browserActor$1> continuation) {
        super(2, continuation);
        this.this$0 = browserProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BrowserProvider$browserActor$1 browserProvider$browserActor$1 = new BrowserProvider$browserActor$1(this.this$0, continuation);
        browserProvider$browserActor$1.L$0 = obj;
        return browserProvider$browserActor$1;
    }

    public final Object invoke(ActorScope<BrowserAction> actorScope, Continuation<? super Unit> continuation) {
        return ((BrowserProvider$browserActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0060, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0061, code lost:
        r9 = r4;
        r4 = r11;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006a, code lost:
        if (((java.lang.Boolean) r11).booleanValue() == false) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006c, code lost:
        r11 = (org.videolan.vlc.providers.BrowserAction) r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0079, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r4) == false) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007d, code lost:
        if ((r11 instanceof org.videolan.vlc.providers.Browse) == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007f, code lost:
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0095, code lost:
        if (r10.this$0.browseImpl(((org.videolan.vlc.providers.Browse) r11).getUrl(), r10) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0097, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x009e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) org.videolan.vlc.providers.BrowseRoot.INSTANCE) == false) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a0, code lost:
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b0, code lost:
        if (r10.this$0.browseRootImpl(r10) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b2, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b9, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) org.videolan.vlc.providers.Refresh.INSTANCE) == false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c1, code lost:
        if (r10.this$0.getUrl() == null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c3, code lost:
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d3, code lost:
        if (r10.this$0.refreshImpl(r10) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d5, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d6, code lost:
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e6, code lost:
        if (org.videolan.vlc.providers.BrowserProvider.browseImpl$default(r10.this$0, (java.lang.String) null, r10, 1, (java.lang.Object) null) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00eb, code lost:
        if ((r11 instanceof org.videolan.vlc.providers.ParseSubDirectories) == false) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ed, code lost:
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0103, code lost:
        if (r10.this$0.parseSubDirectoriesImpl(((org.videolan.vlc.providers.ParseSubDirectories) r11).getList(), r10) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0105, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x010c, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) org.videolan.vlc.providers.ClearListener.INSTANCE) == false) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010e, code lost:
        r6 = r10.this$0;
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0131, code lost:
        if (kotlinx.coroutines.BuildersKt.withContext(r10.this$0.getCoroutineContextProvider().getIO(), new org.videolan.vlc.providers.BrowserProvider$browserActor$1.AnonymousClass1((kotlin.coroutines.Continuation<? super org.videolan.vlc.providers.BrowserProvider$browserActor$1.AnonymousClass1>) null), r10) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0133, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x013a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) org.videolan.vlc.providers.Release.INSTANCE) == false) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x013c, code lost:
        r6 = r10.this$0;
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0160, code lost:
        if (kotlinx.coroutines.BuildersKt.withContext(r10.this$0.getCoroutineContextProvider().getIO(), new org.videolan.vlc.providers.BrowserProvider$browserActor$1.AnonymousClass2((kotlin.coroutines.Continuation<? super org.videolan.vlc.providers.BrowserProvider$browserActor$1.AnonymousClass2>) null), r10) != r0) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0162, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0165, code lost:
        if ((r11 instanceof org.videolan.vlc.providers.BrowseUrl) == false) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0167, code lost:
        r11 = (org.videolan.vlc.providers.BrowseUrl) r11;
        r5 = r11.getDeferred();
        r10.L$0 = r4;
        r10.L$1 = r1;
        r10.L$2 = r5;
        r10.label = 9;
        r11 = r10.this$0.browseUrlImpl(r11.getUrl(), r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0184, code lost:
        if (r11 != r0) goto L_0x0187;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0186, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0187, code lost:
        r9 = r5;
        r5 = r1;
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x018a, code lost:
        r1.complete(r11);
        r11 = r4;
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0191, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0196, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004f, code lost:
        r10.L$0 = r11;
        r10.L$1 = r1;
        r10.L$2 = null;
        r10.label = 1;
        r4 = r1.hasNext(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x005e, code lost:
        if (r4 != r0) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 1
            r3 = 0
            switch(r1) {
                case 0: goto L_0x0040;
                case 1: goto L_0x0034;
                case 2: goto L_0x0027;
                case 3: goto L_0x0027;
                case 4: goto L_0x0027;
                case 5: goto L_0x0027;
                case 6: goto L_0x0027;
                case 7: goto L_0x0027;
                case 8: goto L_0x0027;
                case 9: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0013:
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.CompletableDeferred r1 = (kotlinx.coroutines.CompletableDeferred) r1
            java.lang.Object r4 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r5 = (kotlinx.coroutines.channels.ActorScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r5
            r5 = r4
            r4 = r9
            goto L_0x018a
        L_0x0027:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r4 = (kotlinx.coroutines.channels.ActorScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0191
        L_0x0034:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r4 = (kotlinx.coroutines.channels.ActorScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0064
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r11 = (kotlinx.coroutines.channels.ActorScope) r11
            kotlinx.coroutines.channels.Channel r1 = r11.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x004f:
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.L$0 = r11
            r10.L$1 = r1
            r10.L$2 = r3
            r10.label = r2
            java.lang.Object r4 = r1.hasNext(r4)
            if (r4 != r0) goto L_0x0061
            return r0
        L_0x0061:
            r9 = r4
            r4 = r11
            r11 = r9
        L_0x0064:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0194
            java.lang.Object r11 = r1.next()
            org.videolan.vlc.providers.BrowserAction r11 = (org.videolan.vlc.providers.BrowserAction) r11
            r5 = r4
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r5)
            if (r5 == 0) goto L_0x0191
            boolean r5 = r11 instanceof org.videolan.vlc.providers.Browse
            if (r5 == 0) goto L_0x0098
            org.videolan.vlc.providers.BrowserProvider r5 = r10.this$0
            org.videolan.vlc.providers.Browse r11 = (org.videolan.vlc.providers.Browse) r11
            java.lang.String r11 = r11.getUrl()
            r6 = r10
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r10.L$0 = r4
            r10.L$1 = r1
            r7 = 2
            r10.label = r7
            java.lang.Object r11 = r5.browseImpl(r11, r6)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x0098:
            org.videolan.vlc.providers.BrowseRoot r5 = org.videolan.vlc.providers.BrowseRoot.INSTANCE
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x00b3
            org.videolan.vlc.providers.BrowserProvider r11 = r10.this$0
            r5 = r10
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r10.L$0 = r4
            r10.L$1 = r1
            r6 = 3
            r10.label = r6
            java.lang.Object r11 = r11.browseRootImpl(r5)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x00b3:
            org.videolan.vlc.providers.Refresh r5 = org.videolan.vlc.providers.Refresh.INSTANCE
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x00e9
            org.videolan.vlc.providers.BrowserProvider r11 = r10.this$0
            java.lang.String r11 = r11.getUrl()
            if (r11 == 0) goto L_0x00d6
            org.videolan.vlc.providers.BrowserProvider r11 = r10.this$0
            r5 = r10
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r10.L$0 = r4
            r10.L$1 = r1
            r6 = 4
            r10.label = r6
            java.lang.Object r11 = r11.refreshImpl(r5)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x00d6:
            org.videolan.vlc.providers.BrowserProvider r11 = r10.this$0
            r5 = r10
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r10.L$0 = r4
            r10.L$1 = r1
            r6 = 5
            r10.label = r6
            java.lang.Object r11 = org.videolan.vlc.providers.BrowserProvider.browseImpl$default(r11, r3, r5, r2, r3)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x00e9:
            boolean r5 = r11 instanceof org.videolan.vlc.providers.ParseSubDirectories
            if (r5 == 0) goto L_0x0106
            org.videolan.vlc.providers.BrowserProvider r5 = r10.this$0
            org.videolan.vlc.providers.ParseSubDirectories r11 = (org.videolan.vlc.providers.ParseSubDirectories) r11
            java.util.List r11 = r11.getList()
            r6 = r10
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r10.L$0 = r4
            r10.L$1 = r1
            r7 = 6
            r10.label = r7
            java.lang.Object r11 = r5.parseSubDirectoriesImpl(r11, r6)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x0106:
            org.videolan.vlc.providers.ClearListener r5 = org.videolan.vlc.providers.ClearListener.INSTANCE
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0134
            org.videolan.vlc.providers.BrowserProvider r11 = r10.this$0
            org.videolan.tools.CoroutineContextProvider r11 = r11.getCoroutineContextProvider()
            kotlinx.coroutines.CoroutineDispatcher r11 = r11.getIO()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.vlc.providers.BrowserProvider$browserActor$1$1 r5 = new org.videolan.vlc.providers.BrowserProvider$browserActor$1$1
            org.videolan.vlc.providers.BrowserProvider r6 = r10.this$0
            r5.<init>(r6, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = r10
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r10.L$0 = r4
            r10.L$1 = r1
            r7 = 7
            r10.label = r7
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r5, r6)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x0134:
            org.videolan.vlc.providers.Release r5 = org.videolan.vlc.providers.Release.INSTANCE
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0163
            org.videolan.vlc.providers.BrowserProvider r11 = r10.this$0
            org.videolan.tools.CoroutineContextProvider r11 = r11.getCoroutineContextProvider()
            kotlinx.coroutines.CoroutineDispatcher r11 = r11.getIO()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.vlc.providers.BrowserProvider$browserActor$1$2 r5 = new org.videolan.vlc.providers.BrowserProvider$browserActor$1$2
            org.videolan.vlc.providers.BrowserProvider r6 = r10.this$0
            r5.<init>(r6, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = r10
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r10.L$0 = r4
            r10.L$1 = r1
            r7 = 8
            r10.label = r7
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r5, r6)
            if (r11 != r0) goto L_0x0191
            return r0
        L_0x0163:
            boolean r5 = r11 instanceof org.videolan.vlc.providers.BrowseUrl
            if (r5 == 0) goto L_0x0191
            org.videolan.vlc.providers.BrowseUrl r11 = (org.videolan.vlc.providers.BrowseUrl) r11
            kotlinx.coroutines.CompletableDeferred r5 = r11.getDeferred()
            org.videolan.vlc.providers.BrowserProvider r6 = r10.this$0
            java.lang.String r11 = r11.getUrl()
            r7 = r10
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r10.L$0 = r4
            r10.L$1 = r1
            r10.L$2 = r5
            r8 = 9
            r10.label = r8
            java.lang.Object r11 = r6.browseUrlImpl(r11, r7)
            if (r11 != r0) goto L_0x0187
            return r0
        L_0x0187:
            r9 = r5
            r5 = r1
            r1 = r9
        L_0x018a:
            r1.complete(r11)
            r11 = r4
            r1 = r5
            goto L_0x004f
        L_0x0191:
            r11 = r4
            goto L_0x004f
        L_0x0194:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider$browserActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
