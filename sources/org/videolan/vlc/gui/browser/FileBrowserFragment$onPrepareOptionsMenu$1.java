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
@DebugMetadata(c = "org.videolan.vlc.gui.browser.FileBrowserFragment$onPrepareOptionsMenu$1", f = "FileBrowserFragment.kt", i = {0}, l = {145, 148}, m = "invokeSuspend", n = {"it"}, s = {"L$2"})
/* compiled from: FileBrowserFragment.kt */
final class FileBrowserFragment$onPrepareOptionsMenu$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MenuItem $item;
    final /* synthetic */ Menu $menu;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ FileBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserFragment$onPrepareOptionsMenu$1(FileBrowserFragment fileBrowserFragment, MenuItem menuItem, Menu menu, Continuation<? super FileBrowserFragment$onPrepareOptionsMenu$1> continuation) {
        super(2, continuation);
        this.this$0 = fileBrowserFragment;
        this.$item = menuItem;
        this.$menu = menu;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileBrowserFragment$onPrepareOptionsMenu$1(this.this$0, this.$item, this.$menu, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileBrowserFragment$onPrepareOptionsMenu$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x002d
            if (r1 == r4) goto L_0x001d
            if (r1 != r3) goto L_0x0015
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00aa
        L_0x0015:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001d:
            java.lang.Object r1 = r9.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r6 = r9.L$1
            org.videolan.vlc.gui.browser.FileBrowserFragment r6 = (org.videolan.vlc.gui.browser.FileBrowserFragment) r6
            java.lang.Object r7 = r9.L$0
            android.view.Menu r7 = (android.view.Menu) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0058
        L_0x002d:
            kotlin.ResultKt.throwOnFailure(r10)
            org.videolan.vlc.gui.browser.FileBrowserFragment r10 = r9.this$0
            java.lang.String r1 = r10.getMrl()
            if (r1 == 0) goto L_0x007d
            android.view.Menu r7 = r9.$menu
            org.videolan.vlc.gui.browser.FileBrowserFragment r6 = r9.this$0
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.vlc.gui.browser.FileBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1 r8 = new org.videolan.vlc.gui.browser.FileBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1
            r8.<init>(r1, r5)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9.L$0 = r7
            r9.L$1 = r6
            r9.L$2 = r1
            r9.label = r4
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r8, r9)
            if (r10 != r0) goto L_0x0058
            return r0
        L_0x0058:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            int r8 = org.videolan.vlc.R.id.ml_menu_scan
            android.view.MenuItem r7 = r7.findItem(r8)
            if (r7 != 0) goto L_0x0067
            goto L_0x007d
        L_0x0067:
            boolean r6 = r6.isRootDirectory()
            if (r6 != 0) goto L_0x0079
            java.lang.String r6 = "file"
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r1, r6, r2, r3, r5)
            if (r1 == 0) goto L_0x0079
            if (r10 != 0) goto L_0x0079
            r10 = 1
            goto L_0x007a
        L_0x0079:
            r10 = 0
        L_0x007a:
            r7.setVisible(r10)
        L_0x007d:
            org.videolan.vlc.gui.browser.FileBrowserFragment r10 = r9.this$0
            java.lang.String r10 = r10.getMrl()
            if (r10 == 0) goto L_0x00b3
            org.videolan.vlc.gui.browser.FileBrowserFragment r10 = r9.this$0
            org.videolan.vlc.repository.BrowserFavRepository r10 = r10.getBrowserFavRepository()
            org.videolan.vlc.gui.browser.FileBrowserFragment r1 = r9.this$0
            java.lang.String r1 = r1.getMrl()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r5
            r9.L$1 = r5
            r9.L$2 = r5
            r9.label = r3
            java.lang.Object r10 = r10.browserFavExists(r1, r6)
            if (r10 != r0) goto L_0x00aa
            return r0
        L_0x00aa:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00b3
            r2 = 1
        L_0x00b3:
            android.view.MenuItem r10 = r9.$item
            if (r2 == 0) goto L_0x00ba
            int r0 = org.videolan.vlc.R.drawable.ic_fav_remove
            goto L_0x00bc
        L_0x00ba:
            int r0 = org.videolan.vlc.R.drawable.ic_fav_add
        L_0x00bc:
            r10.setIcon(r0)
            android.view.MenuItem r10 = r9.$item
            if (r2 == 0) goto L_0x00c6
            int r0 = org.videolan.vlc.R.string.favorites_remove
            goto L_0x00c8
        L_0x00c6:
            int r0 = org.videolan.vlc.R.string.favorites_add
        L_0x00c8:
            r10.setTitle(r0)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.FileBrowserFragment$onPrepareOptionsMenu$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
