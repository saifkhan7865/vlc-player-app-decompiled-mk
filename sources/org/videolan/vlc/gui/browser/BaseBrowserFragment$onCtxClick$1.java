package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onCtxClick$1", f = "BaseBrowserFragment.kt", i = {0, 0, 0}, l = {752}, m = "invokeSuspend", n = {"mw", "$this$invokeSuspend_u24lambda_u240", "isFileBrowser"}, s = {"L$0", "L$4", "I$0"})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onCtxClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ int $position;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onCtxClick$1(MediaLibraryItem mediaLibraryItem, BaseBrowserFragment baseBrowserFragment, int i, Continuation<? super BaseBrowserFragment$onCtxClick$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.this$0 = baseBrowserFragment;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$onCtxClick$1(this.$item, this.this$0, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$onCtxClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: type inference failed for: r11v34, types: [org.videolan.medialibrary.media.MediaLibraryItem, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01bf  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 1
            if (r1 == 0) goto L_0x002e
            if (r1 != r2) goto L_0x0026
            int r0 = r10.I$0
            java.lang.Object r1 = r10.L$4
            org.videolan.vlc.util.FlagSet r1 = (org.videolan.vlc.util.FlagSet) r1
            java.lang.Object r2 = r10.L$3
            org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
            java.lang.Object r3 = r10.L$2
            org.videolan.vlc.gui.browser.BaseBrowserFragment r3 = (org.videolan.vlc.gui.browser.BaseBrowserFragment) r3
            java.lang.Object r4 = r10.L$1
            org.videolan.vlc.util.FlagSet r4 = (org.videolan.vlc.util.FlagSet) r4
            java.lang.Object r5 = r10.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00f4
        L_0x0026:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r10.$item
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r1)
            r5 = r11
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            android.net.Uri r11 = r5.getUri()
            java.lang.String r11 = r11.getScheme()
            java.lang.String r1 = "content"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r1)
            if (r11 != 0) goto L_0x01d8
            android.net.Uri r11 = r5.getUri()
            java.lang.String r11 = r11.getScheme()
            java.lang.String r1 = "otg"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r1)
            if (r11 == 0) goto L_0x005d
            goto L_0x01d8
        L_0x005d:
            org.videolan.vlc.util.FlagSet r1 = new org.videolan.vlc.util.FlagSet
            java.lang.Class<org.videolan.vlc.util.ContextOption> r11 = org.videolan.vlc.util.ContextOption.class
            r1.<init>(r11)
            org.videolan.vlc.gui.browser.BaseBrowserFragment r3 = r10.this$0
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r10.$item
            org.videolan.vlc.util.ContextOption r4 = org.videolan.vlc.util.ContextOption.CTX_RENAME
            java.lang.Enum r4 = (java.lang.Enum) r4
            r1.add(r4)
            boolean r4 = r3.isRootDirectory()
            if (r4 != 0) goto L_0x0080
            boolean r4 = r3 instanceof org.videolan.vlc.gui.browser.FileBrowserFragment
            if (r4 == 0) goto L_0x0080
            org.videolan.vlc.util.ContextOption r4 = org.videolan.vlc.util.ContextOption.CTX_DELETE
            java.lang.Enum r4 = (java.lang.Enum) r4
            r1.add(r4)
        L_0x0080:
            boolean r4 = r3.isRootDirectory()
            if (r4 != 0) goto L_0x0091
            boolean r4 = r1 instanceof org.videolan.vlc.gui.browser.FileBrowserFragment
            if (r4 == 0) goto L_0x0091
            org.videolan.vlc.util.ContextOption r4 = org.videolan.vlc.util.ContextOption.CTX_DELETE
            java.lang.Enum r4 = (java.lang.Enum) r4
            r1.add(r4)
        L_0x0091:
            int r4 = r5.getType()
            r6 = 3
            r7 = 0
            if (r4 != r6) goto L_0x0167
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r4 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r4
            boolean r4 = r4.isFolderEmpty(r5)
            if (r4 != 0) goto L_0x00ac
            org.videolan.vlc.util.ContextOption r4 = org.videolan.vlc.util.ContextOption.CTX_PLAY
            java.lang.Enum r4 = (java.lang.Enum) r4
            r1.add(r4)
        L_0x00ac:
            boolean r4 = r3 instanceof org.videolan.vlc.gui.browser.FileBrowserFragment
            if (r4 == 0) goto L_0x00c4
            r4 = r11
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
            android.net.Uri r4 = r4.getUri()
            java.lang.String r4 = r4.getScheme()
            java.lang.String r6 = "file"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r4 == 0) goto L_0x00c4
            r7 = 1
        L_0x00c4:
            boolean r4 = r3 instanceof org.videolan.vlc.gui.browser.NetworkBrowserFragment
            if (r7 != 0) goto L_0x00cd
            if (r4 == 0) goto L_0x00cb
            goto L_0x00cd
        L_0x00cb:
            r4 = r1
            goto L_0x0108
        L_0x00cd:
            org.videolan.vlc.repository.BrowserFavRepository r4 = r3.getBrowserFavRepository()
            android.net.Uri r6 = r5.getUri()
            java.lang.String r8 = "getUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)
            r10.L$0 = r5
            r10.L$1 = r1
            r10.L$2 = r3
            r10.L$3 = r11
            r10.L$4 = r1
            r10.I$0 = r7
            r10.label = r2
            java.lang.Object r2 = r4.browserFavExists(r6, r10)
            if (r2 != r0) goto L_0x00ef
            return r0
        L_0x00ef:
            r4 = r1
            r0 = r7
            r9 = r2
            r2 = r11
            r11 = r9
        L_0x00f4:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00ff
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            goto L_0x0101
        L_0x00ff:
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
        L_0x0101:
            java.lang.Enum r11 = (java.lang.Enum) r11
            r1.add(r11)
            r7 = r0
            r11 = r2
        L_0x0108:
            if (r7 == 0) goto L_0x012e
            boolean r0 = r3.isRootDirectory()
            if (r0 != 0) goto L_0x012e
            org.videolan.vlc.gui.helpers.MedialibraryUtils r0 = org.videolan.vlc.gui.helpers.MedialibraryUtils.INSTANCE
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r11
            android.net.Uri r11 = r11.getUri()
            java.lang.String r11 = r11.toString()
            java.lang.String r2 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            boolean r11 = r0.isScanned(r11)
            if (r11 != 0) goto L_0x012e
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_ADD_SCANNED
            java.lang.Enum r11 = (java.lang.Enum) r11
            r1.add(r11)
        L_0x012e:
            if (r7 == 0) goto L_0x0165
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_APPEND
            java.lang.Enum r11 = (java.lang.Enum) r11
            r1.add(r11)
            org.videolan.vlc.viewmodels.SortableModel r11 = r3.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r11 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r11
            org.videolan.vlc.providers.BrowserProvider r11 = r11.getProvider()
            boolean r11 = r11.hasMedias(r5)
            if (r11 == 0) goto L_0x014e
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_PLAYLIST
            java.lang.Enum r11 = (java.lang.Enum) r11
            r1.add(r11)
        L_0x014e:
            org.videolan.vlc.viewmodels.SortableModel r11 = r3.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r11 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r11
            org.videolan.vlc.providers.BrowserProvider r11 = r11.getProvider()
            boolean r11 = r11.hasSubfolders(r5)
            if (r11 == 0) goto L_0x0165
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_AND_SUB_PLAYLIST
            java.lang.Enum r11 = (java.lang.Enum) r11
            r1.add(r11)
        L_0x0165:
            r1 = r4
            goto L_0x01b9
        L_0x0167:
            int r11 = r5.getType()
            if (r11 != 0) goto L_0x016f
            r11 = 1
            goto L_0x0170
        L_0x016f:
            r11 = 0
        L_0x0170:
            int r0 = r5.getType()
            if (r0 != r2) goto L_0x0178
            r0 = 1
            goto L_0x0179
        L_0x0178:
            r0 = 0
        L_0x0179:
            if (r11 != 0) goto L_0x0180
            if (r0 == 0) goto L_0x017e
            goto L_0x0180
        L_0x017e:
            r3 = 0
            goto L_0x0181
        L_0x0180:
            r3 = 1
        L_0x0181:
            if (r3 == 0) goto L_0x019c
            r4 = 4
            org.videolan.vlc.util.ContextOption[] r4 = new org.videolan.vlc.util.ContextOption[r4]
            org.videolan.vlc.util.ContextOption r5 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST
            r4[r7] = r5
            org.videolan.vlc.util.ContextOption r5 = org.videolan.vlc.util.ContextOption.CTX_APPEND
            r4[r2] = r5
            r2 = 2
            org.videolan.vlc.util.ContextOption r5 = org.videolan.vlc.util.ContextOption.CTX_INFORMATION
            r4[r2] = r5
            org.videolan.vlc.util.ContextOption r2 = org.videolan.vlc.util.ContextOption.CTX_PLAY_ALL
            r4[r6] = r2
            java.lang.Enum[] r4 = (java.lang.Enum[]) r4
            r1.addAll(r4)
        L_0x019c:
            if (r0 != 0) goto L_0x01a7
            if (r3 == 0) goto L_0x01a7
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_PLAY_AS_AUDIO
            java.lang.Enum r0 = (java.lang.Enum) r0
            r1.add(r0)
        L_0x01a7:
            if (r3 != 0) goto L_0x01b0
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_PLAY
            java.lang.Enum r0 = (java.lang.Enum) r0
            r1.add(r0)
        L_0x01b0:
            if (r11 == 0) goto L_0x01b9
            org.videolan.vlc.util.ContextOption r11 = org.videolan.vlc.util.ContextOption.CTX_DOWNLOAD_SUBTITLES
            java.lang.Enum r11 = (java.lang.Enum) r11
            r1.add(r11)
        L_0x01b9:
            boolean r11 = r1.isNotEmpty()
            if (r11 == 0) goto L_0x01d5
            org.videolan.vlc.gui.browser.BaseBrowserFragment r11 = r10.this$0
            androidx.fragment.app.FragmentActivity r11 = r11.requireActivity()
            java.lang.String r0 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r0)
            org.videolan.vlc.gui.browser.BaseBrowserFragment r0 = r10.this$0
            org.videolan.vlc.gui.dialogs.CtxActionReceiver r0 = (org.videolan.vlc.gui.dialogs.CtxActionReceiver) r0
            int r2 = r10.$position
            org.videolan.medialibrary.media.MediaLibraryItem r3 = r10.$item
            org.videolan.vlc.gui.dialogs.ContextSheetKt.showContext(r11, r0, r2, r3, r1)
        L_0x01d5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x01d8:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment$onCtxClick$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
