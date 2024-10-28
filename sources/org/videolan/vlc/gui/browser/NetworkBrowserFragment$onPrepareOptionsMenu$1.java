package org.videolan.vlc.gui.browser;

import android.view.Menu;
import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.NetworkBrowserFragment$onPrepareOptionsMenu$1", f = "NetworkBrowserFragment.kt", i = {1}, l = {90, 94}, m = "invokeSuspend", n = {"it"}, s = {"L$2"})
/* compiled from: NetworkBrowserFragment.kt */
final class NetworkBrowserFragment$onPrepareOptionsMenu$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MenuItem $item;
    final /* synthetic */ Menu $menu;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ NetworkBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkBrowserFragment$onPrepareOptionsMenu$1(NetworkBrowserFragment networkBrowserFragment, MenuItem menuItem, Menu menu, Continuation<? super NetworkBrowserFragment$onPrepareOptionsMenu$1> continuation) {
        super(2, continuation);
        this.this$0 = networkBrowserFragment;
        this.$item = menuItem;
        this.$menu = menu;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetworkBrowserFragment$onPrepareOptionsMenu$1(this.this$0, this.$item, this.$menu, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NetworkBrowserFragment$onPrepareOptionsMenu$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 0
            r3 = 0
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x002d
            if (r1 == r5) goto L_0x0029
            if (r1 != r4) goto L_0x0021
            java.lang.Object r0 = r9.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r9.L$1
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r1 = (org.videolan.vlc.gui.browser.NetworkBrowserFragment) r1
            java.lang.Object r6 = r9.L$0
            android.view.Menu r6 = (android.view.Menu) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00a4
        L_0x0021:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0057
        L_0x002d:
            kotlin.ResultKt.throwOnFailure(r10)
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r10 = r9.this$0
            java.lang.String r10 = r10.getMrl()
            if (r10 == 0) goto L_0x0061
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r10 = r9.this$0
            org.videolan.vlc.repository.BrowserFavRepository r10 = r10.getBrowserFavRepository()
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r1 = r9.this$0
            java.lang.String r1 = r1.getMrl()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.label = r5
            java.lang.Object r10 = r10.browserFavExists(r1, r6)
            if (r10 != r0) goto L_0x0057
            return r0
        L_0x0057:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x0061
            r10 = 1
            goto L_0x0062
        L_0x0061:
            r10 = 0
        L_0x0062:
            android.view.MenuItem r1 = r9.$item
            if (r10 == 0) goto L_0x0069
            int r6 = org.videolan.vlc.R.drawable.ic_fav_remove
            goto L_0x006b
        L_0x0069:
            int r6 = org.videolan.vlc.R.drawable.ic_fav_add
        L_0x006b:
            r1.setIcon(r6)
            android.view.MenuItem r1 = r9.$item
            if (r10 == 0) goto L_0x0075
            int r10 = org.videolan.vlc.R.string.favorites_remove
            goto L_0x0077
        L_0x0075:
            int r10 = org.videolan.vlc.R.string.favorites_add
        L_0x0077:
            r1.setTitle(r10)
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r10 = r9.this$0
            java.lang.String r10 = r10.getMrl()
            if (r10 == 0) goto L_0x00c7
            android.view.Menu r6 = r9.$menu
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r1 = r9.this$0
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            org.videolan.vlc.gui.browser.NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1 r8 = new org.videolan.vlc.gui.browser.NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1
            r8.<init>(r10, r2)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9.L$0 = r6
            r9.L$1 = r1
            r9.L$2 = r10
            r9.label = r4
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r9)
            if (r7 != r0) goto L_0x00a2
            return r0
        L_0x00a2:
            r0 = r10
            r10 = r7
        L_0x00a4:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            int r7 = org.videolan.vlc.R.id.ml_menu_scan
            android.view.MenuItem r6 = r6.findItem(r7)
            if (r6 != 0) goto L_0x00b3
            goto L_0x00c7
        L_0x00b3:
            boolean r1 = r1.isRootDirectory()
            if (r1 != 0) goto L_0x00c4
            java.lang.String r1 = "smb"
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r3, r4, r2)
            if (r0 == 0) goto L_0x00c4
            if (r10 != 0) goto L_0x00c4
            r3 = 1
        L_0x00c4:
            r6.setVisible(r3)
        L_0x00c7:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.NetworkBrowserFragment$onPrepareOptionsMenu$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
