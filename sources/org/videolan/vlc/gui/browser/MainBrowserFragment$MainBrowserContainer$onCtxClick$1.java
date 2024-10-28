package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.browser.MainBrowserFragment;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer$onCtxClick$1", f = "MainBrowserFragment.kt", i = {0, 0, 0, 1, 1, 1}, l = {384, 386}, m = "invokeSuspend", n = {"mw", "$this$invokeSuspend_u24lambda_u240", "isFileBrowser", "mw", "$this$invokeSuspend_u24lambda_u240", "isFileBrowser"}, s = {"L$0", "L$3", "I$0", "L$0", "L$3", "I$0"})
/* compiled from: MainBrowserFragment.kt */
final class MainBrowserFragment$MainBrowserContainer$onCtxClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ int $position;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MainBrowserFragment.MainBrowserContainer this$0;
    final /* synthetic */ MainBrowserFragment this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainBrowserFragment$MainBrowserContainer$onCtxClick$1(MainBrowserFragment.MainBrowserContainer mainBrowserContainer, MediaLibraryItem mediaLibraryItem, MainBrowserFragment mainBrowserFragment, int i, Continuation<? super MainBrowserFragment$MainBrowserContainer$onCtxClick$1> continuation) {
        super(2, continuation);
        this.this$0 = mainBrowserContainer;
        this.$item = mediaLibraryItem;
        this.this$1 = mainBrowserFragment;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainBrowserFragment$MainBrowserContainer$onCtxClick$1(this.this$0, this.$item, this.this$1, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainBrowserFragment$MainBrowserContainer$onCtxClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x018c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0046
            if (r1 == r4) goto L_0x002f
            if (r1 != r3) goto L_0x0027
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$3
            org.videolan.vlc.util.FlagSet r1 = (org.videolan.vlc.util.FlagSet) r1
            java.lang.Object r6 = r12.L$2
            org.videolan.vlc.gui.browser.MainBrowserFragment r6 = (org.videolan.vlc.gui.browser.MainBrowserFragment) r6
            java.lang.Object r7 = r12.L$1
            org.videolan.vlc.util.FlagSet r7 = (org.videolan.vlc.util.FlagSet) r7
            java.lang.Object r8 = r12.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x011a
        L_0x0027:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x002f:
            int r1 = r12.I$0
            java.lang.Object r6 = r12.L$3
            org.videolan.vlc.util.FlagSet r6 = (org.videolan.vlc.util.FlagSet) r6
            java.lang.Object r7 = r12.L$2
            org.videolan.vlc.gui.browser.MainBrowserFragment r7 = (org.videolan.vlc.gui.browser.MainBrowserFragment) r7
            java.lang.Object r8 = r12.L$1
            org.videolan.vlc.util.FlagSet r8 = (org.videolan.vlc.util.FlagSet) r8
            java.lang.Object r9 = r12.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r9
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00df
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r13)
            org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer r13 = r12.this$0
            androidx.lifecycle.ViewModel r13 = r13.requireViewModel()
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r12.$item
            java.lang.String r6 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r6)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            android.net.Uri r6 = r1.getUri()
            java.lang.String r6 = r6.getScheme()
            java.lang.String r7 = "content"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r6 != 0) goto L_0x01ac
            android.net.Uri r6 = r1.getUri()
            java.lang.String r6 = r6.getScheme()
            java.lang.String r7 = "otg"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r6 == 0) goto L_0x007a
            goto L_0x01ac
        L_0x007a:
            org.videolan.vlc.util.FlagSet r6 = new org.videolan.vlc.util.FlagSet
            java.lang.Class<org.videolan.vlc.util.ContextOption> r7 = org.videolan.vlc.util.ContextOption.class
            r6.<init>(r7)
            org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer r7 = r12.this$0
            org.videolan.medialibrary.media.MediaLibraryItem r8 = r12.$item
            org.videolan.vlc.gui.browser.MainBrowserFragment r9 = r12.this$1
            boolean r10 = r13 instanceof org.videolan.vlc.viewmodels.browser.BrowserModel
            if (r10 == 0) goto L_0x008e
            org.videolan.vlc.viewmodels.browser.BrowserModel r13 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r13
            goto L_0x008f
        L_0x008e:
            r13 = r5
        L_0x008f:
            if (r13 == 0) goto L_0x009e
            boolean r13 = r13.isFolderEmpty(r1)
            if (r13 != 0) goto L_0x009e
            org.videolan.vlc.util.ContextOption r13 = org.videolan.vlc.util.ContextOption.CTX_PLAY
            java.lang.Enum r13 = (java.lang.Enum) r13
            r6.add(r13)
        L_0x009e:
            boolean r13 = r7.isFile()
            if (r13 == 0) goto L_0x00b8
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            android.net.Uri r13 = r8.getUri()
            java.lang.String r13 = r13.getScheme()
            java.lang.String r7 = "file"
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r7)
            if (r13 == 0) goto L_0x00b8
            r13 = 1
            goto L_0x00b9
        L_0x00b8:
            r13 = 0
        L_0x00b9:
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer$onCtxClick$1$flags$1$favExists$1 r8 = new org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer$onCtxClick$1$flags$1$favExists$1
            r8.<init>(r9, r1, r5)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r12.L$0 = r1
            r12.L$1 = r6
            r12.L$2 = r9
            r12.L$3 = r6
            r12.I$0 = r13
            r12.label = r4
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r12)
            if (r7 != r0) goto L_0x00d9
            return r0
        L_0x00d9:
            r8 = r6
            r11 = r1
            r1 = r13
            r13 = r7
            r7 = r9
            r9 = r11
        L_0x00df:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x0144
            android.net.Uri r13 = r9.getUri()
            java.lang.String r13 = r13.getScheme()
            boolean r13 = org.videolan.vlc.util.BrowserutilsKt.isSchemeFavoriteEditable(r13)
            if (r13 == 0) goto L_0x013c
            kotlinx.coroutines.CoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13
            org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer$onCtxClick$1$flags$1$1 r10 = new org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer$onCtxClick$1$flags$1$1
            r10.<init>(r7, r9, r5)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r12.L$0 = r9
            r12.L$1 = r8
            r12.L$2 = r7
            r12.L$3 = r6
            r12.I$0 = r1
            r12.label = r3
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r13, r10, r12)
            if (r13 != r0) goto L_0x0115
            return r0
        L_0x0115:
            r0 = r1
            r1 = r6
            r6 = r7
            r7 = r8
            r8 = r9
        L_0x011a:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x0137
            org.videolan.vlc.util.ContextOption[] r13 = new org.videolan.vlc.util.ContextOption[r3]
            org.videolan.vlc.util.ContextOption r3 = org.videolan.vlc.util.ContextOption.CTX_FAV_EDIT
            r13[r2] = r3
            org.videolan.vlc.util.ContextOption r2 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            r13[r4] = r2
            java.lang.Enum[] r13 = (java.lang.Enum[]) r13
            r1.addAll(r13)
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r0
            goto L_0x014b
        L_0x0137:
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r0
        L_0x013c:
            org.videolan.vlc.util.ContextOption r13 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            java.lang.Enum r13 = (java.lang.Enum) r13
            r6.add(r13)
            goto L_0x014b
        L_0x0144:
            org.videolan.vlc.util.ContextOption r13 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
            java.lang.Enum r13 = (java.lang.Enum) r13
            r6.add(r13)
        L_0x014b:
            if (r1 == 0) goto L_0x0186
            org.videolan.vlc.viewmodels.browser.BrowserModel r13 = r7.localViewModel
            java.lang.String r0 = "localViewModel"
            if (r13 != 0) goto L_0x0159
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r13 = r5
        L_0x0159:
            org.videolan.vlc.providers.BrowserProvider r13 = r13.getProvider()
            boolean r13 = r13.hasMedias(r9)
            if (r13 == 0) goto L_0x016a
            org.videolan.vlc.util.ContextOption r13 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_PLAYLIST
            java.lang.Enum r13 = (java.lang.Enum) r13
            r6.add(r13)
        L_0x016a:
            org.videolan.vlc.viewmodels.browser.BrowserModel r13 = r7.localViewModel
            if (r13 != 0) goto L_0x0174
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x0175
        L_0x0174:
            r5 = r13
        L_0x0175:
            org.videolan.vlc.providers.BrowserProvider r13 = r5.getProvider()
            boolean r13 = r13.hasSubfolders(r9)
            if (r13 == 0) goto L_0x0186
            org.videolan.vlc.util.ContextOption r13 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_AND_SUB_PLAYLIST
            java.lang.Enum r13 = (java.lang.Enum) r13
            r6.add(r13)
        L_0x0186:
            boolean r13 = r8.isNotEmpty()
            if (r13 == 0) goto L_0x01a9
            org.videolan.vlc.gui.browser.MainBrowserFragment r13 = r12.this$1
            androidx.fragment.app.FragmentActivity r13 = r13.requireActivity()
            java.lang.String r0 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            org.videolan.vlc.gui.browser.MainBrowserFragment r0 = r12.this$1
            org.videolan.vlc.gui.dialogs.CtxActionReceiver r0 = (org.videolan.vlc.gui.dialogs.CtxActionReceiver) r0
            int r1 = r12.$position
            org.videolan.medialibrary.media.MediaLibraryItem r2 = r12.$item
            org.videolan.vlc.gui.dialogs.ContextSheetKt.showContext(r13, r0, r1, r2, r8)
            org.videolan.vlc.gui.browser.MainBrowserFragment r13 = r12.this$1
            org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer r0 = r12.this$0
            r13.currentCtx = r0
        L_0x01a9:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x01ac:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.MainBrowserFragment$MainBrowserContainer$onCtxClick$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
