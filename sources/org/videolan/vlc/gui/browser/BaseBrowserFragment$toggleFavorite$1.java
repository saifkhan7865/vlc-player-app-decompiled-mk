package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$toggleFavorite$1", f = "BaseBrowserFragment.kt", i = {0}, l = {686, 686, 687, 688}, m = "invokeSuspend", n = {"mw"}, s = {"L$0"})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$toggleFavorite$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$toggleFavorite$1(BaseBrowserFragment baseBrowserFragment, Continuation<? super BaseBrowserFragment$toggleFavorite$1> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$toggleFavorite$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$toggleFavorite$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            java.lang.String r6 = "getUri(...)"
            if (r1 == 0) goto L_0x002c
            if (r1 == r5) goto L_0x0024
            if (r1 == r4) goto L_0x001f
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            goto L_0x001f
        L_0x0017:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00d7
        L_0x0024:
            java.lang.Object r1 = r9.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0055
        L_0x002c:
            kotlin.ResultKt.throwOnFailure(r10)
            org.videolan.vlc.gui.browser.BaseBrowserFragment r10 = r9.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r10.getCurrentMedia()
            if (r1 != 0) goto L_0x003a
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x003a:
            org.videolan.vlc.gui.browser.BaseBrowserFragment r10 = r9.this$0
            org.videolan.vlc.repository.BrowserFavRepository r10 = r10.getBrowserFavRepository()
            android.net.Uri r7 = r1.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)
            r8 = r9
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r9.L$0 = r1
            r9.label = r5
            java.lang.Object r10 = r10.browserFavExists(r7, r8)
            if (r10 != r0) goto L_0x0055
            return r0
        L_0x0055:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            r5 = 0
            if (r10 == 0) goto L_0x0079
            org.videolan.vlc.gui.browser.BaseBrowserFragment r10 = r9.this$0
            org.videolan.vlc.repository.BrowserFavRepository r10 = r10.getBrowserFavRepository()
            android.net.Uri r1 = r1.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
            r2 = r9
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r9.L$0 = r5
            r9.label = r4
            java.lang.Object r10 = r10.deleteBrowserFav(r1, r2)
            if (r10 != r0) goto L_0x00d7
            return r0
        L_0x0079:
            android.net.Uri r10 = r1.getUri()
            java.lang.String r10 = r10.getScheme()
            java.lang.String r4 = "file"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r4)
            java.lang.String r4 = "getTitle(...)"
            if (r10 == 0) goto L_0x00b1
            org.videolan.vlc.gui.browser.BaseBrowserFragment r10 = r9.this$0
            org.videolan.vlc.repository.BrowserFavRepository r10 = r10.getBrowserFavRepository()
            android.net.Uri r2 = r1.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r6 = r1.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            java.lang.String r1 = r1.getArtworkURL()
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.L$0 = r5
            r9.label = r3
            java.lang.Object r10 = r10.addLocalFavItem(r2, r6, r1, r4)
            if (r10 != r0) goto L_0x00d7
            return r0
        L_0x00b1:
            org.videolan.vlc.gui.browser.BaseBrowserFragment r10 = r9.this$0
            org.videolan.vlc.repository.BrowserFavRepository r10 = r10.getBrowserFavRepository()
            android.net.Uri r3 = r1.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
            java.lang.String r6 = r1.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            java.lang.String r1 = r1.getArtworkURL()
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.L$0 = r5
            r9.label = r2
            java.lang.Object r10 = r10.addNetworkFavItem(r3, r6, r1, r4)
            if (r10 != r0) goto L_0x00d7
            return r0
        L_0x00d7:
            org.videolan.vlc.gui.browser.BaseBrowserFragment r10 = r9.this$0
            androidx.fragment.app.FragmentActivity r10 = r10.getActivity()
            if (r10 == 0) goto L_0x00e2
            r10.invalidateOptionsMenu()
        L_0x00e2:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment$toggleFavorite$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
