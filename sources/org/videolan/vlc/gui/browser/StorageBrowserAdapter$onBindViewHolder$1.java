package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.StorageBrowserAdapter$onBindViewHolder$1", f = "StorageBrowserAdapter.kt", i = {0, 0}, l = {74}, m = "invokeSuspend", n = {"uri", "storagePath"}, s = {"L$0", "L$1"})
/* compiled from: StorageBrowserAdapter.kt */
final class StorageBrowserAdapter$onBindViewHolder$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $position;
    final /* synthetic */ BaseBrowserAdapter.MediaViewHolder $vh;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ StorageBrowserAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageBrowserAdapter$onBindViewHolder$1(StorageBrowserAdapter storageBrowserAdapter, int i, BaseBrowserAdapter.MediaViewHolder mediaViewHolder, Continuation<? super StorageBrowserAdapter$onBindViewHolder$1> continuation) {
        super(2, continuation);
        this.this$0 = storageBrowserAdapter;
        this.$position = i;
        this.$vh = mediaViewHolder;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StorageBrowserAdapter$onBindViewHolder$1(this.this$0, this.$position, this.$vh, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StorageBrowserAdapter$onBindViewHolder$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x017d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 0
            r3 = 2
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x0023
            if (r1 != r5) goto L_0x001b
            java.lang.Object r0 = r10.L$1
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r10.L$0
            android.net.Uri r1 = (android.net.Uri) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00d3
        L_0x001b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0023:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            int r1 = r10.$position
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r11.getItem((int) r1)
            java.lang.String r1 = r11.getTitle()
            int r6 = r11.getItemType()
            r7 = 32
            if (r6 != r7) goto L_0x004d
            org.videolan.medialibrary.media.Storage r6 = new org.videolan.medialibrary.media.Storage
            java.lang.String r7 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r7)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r11
            android.net.Uri r11 = r11.getUri()
            r6.<init>((android.net.Uri) r11)
            r11 = r6
            org.videolan.medialibrary.media.MediaLibraryItem r11 = (org.videolan.medialibrary.media.MediaLibraryItem) r11
        L_0x004d:
            java.lang.String r6 = "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r6)
            r6 = r11
            org.videolan.medialibrary.media.Storage r6 = (org.videolan.medialibrary.media.Storage) r6
            android.net.Uri r7 = r6.getUri()
            java.lang.String r8 = r7.getScheme()
            boolean r8 = org.videolan.vlc.util.BrowserutilsKt.isSchemeFile(r8)
            if (r8 == 0) goto L_0x006c
            java.lang.String r8 = r7.getPath()
            if (r8 != 0) goto L_0x0074
            java.lang.String r8 = ""
            goto L_0x0074
        L_0x006c:
            java.lang.String r8 = r7.toString()
            java.lang.String r8 = android.net.Uri.decode(r8)
        L_0x0074:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.lang.String r9 = "/"
            boolean r9 = kotlin.text.StringsKt.endsWith$default(r8, r9, r4, r3, r2)
            if (r9 != 0) goto L_0x0090
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            r8 = 47
            r9.append(r8)
            java.lang.String r8 = r9.toString()
        L_0x0090:
            java.lang.String r9 = r6.getTitle()
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            if (r9 == 0) goto L_0x009e
            boolean r9 = kotlin.text.StringsKt.isBlank(r9)
            if (r9 == 0) goto L_0x00a1
        L_0x009e:
            r6.setTitle(r1)
        L_0x00a1:
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r1 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r1 = r1.getBindingContainer()
            r1.setItem(r11)
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            org.videolan.resources.AndroidDevices r1 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r1 = r1.isTv()
            r11.setIsTv(r1)
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            kotlinx.coroutines.Job r11 = r11.updateJob
            if (r11 == 0) goto L_0x00d5
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r10.L$0 = r7
            r10.L$1 = r8
            r10.label = r5
            java.lang.Object r11 = r11.join(r1)
            if (r11 != r0) goto L_0x00d1
            return r0
        L_0x00d1:
            r1 = r7
            r0 = r8
        L_0x00d3:
            r8 = r0
            r7 = r1
        L_0x00d5:
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            kotlinx.coroutines.Job r11 = r11.updateJob
            if (r11 == 0) goto L_0x00e6
            boolean r11 = r11.isCancelled()
            if (r11 != r5) goto L_0x00e6
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00e6:
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            java.util.List r11 = r11.customDirsLocation
            if (r11 != 0) goto L_0x00f4
            java.lang.String r11 = "customDirsLocation"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            goto L_0x00f5
        L_0x00f4:
            r2 = r11
        L_0x00f5:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.lang.String r11 = org.videolan.tools.Strings.stripTrailingSlash(r8)
            boolean r11 = r2.contains(r11)
            if (r11 == 0) goto L_0x0110
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            org.videolan.tools.MultiSelectHelper r11 = r11.getMultiSelectHelper()
            boolean r11 = r11.getInActionMode()
            if (r11 != 0) goto L_0x0110
            r11 = 1
            goto L_0x0111
        L_0x0110:
            r11 = 0
        L_0x0111:
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = r10.this$0
            org.videolan.vlc.gui.browser.BrowserContainer r0 = r0.getBrowserContainer()
            boolean r0 = r0.getScannedDirectory()
            if (r0 != 0) goto L_0x012f
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = r10.this$0
            java.util.List r0 = r0.mediaDirsLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            boolean r0 = org.videolan.tools.PathUtilsKt.containsPath((java.util.List<java.lang.String>) r0, (java.lang.String) r8)
            if (r0 == 0) goto L_0x012d
            goto L_0x012f
        L_0x012d:
            r0 = 0
            goto L_0x0130
        L_0x012f:
            r0 = 1
        L_0x0130:
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r1 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r1 = r1.getBindingContainer()
            r1.setHasContextMenu(r11)
            org.videolan.vlc.gui.helpers.MedialibraryUtils r11 = org.videolan.vlc.gui.helpers.MedialibraryUtils.INSTANCE
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r1 = r10.this$0
            java.util.List r1 = r1.getBannedFolders()
            boolean r11 = r11.isBanned((android.net.Uri) r7, (java.util.List<java.lang.String>) r1)
            if (r11 == 0) goto L_0x015a
            org.videolan.vlc.gui.helpers.MedialibraryUtils r1 = org.videolan.vlc.gui.helpers.MedialibraryUtils.INSTANCE
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r2 = r10.this$0
            java.util.List r2 = r2.getBannedFolders()
            boolean r1 = r1.isStrictlyBanned((android.net.Uri) r7, (java.util.List<java.lang.String>) r2)
            if (r1 != 0) goto L_0x015a
            r1 = 1
            goto L_0x015b
        L_0x015a:
            r1 = 0
        L_0x015b:
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r2 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r2 = r2.getBindingContainer()
            r2.setIsBanned(r11)
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r2 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r2 = r2.getBindingContainer()
            r2.setIsBannedByParent(r1)
            if (r11 == 0) goto L_0x017d
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r11 = r11.getBrowserCheckbox()
            r11.setState(r4)
            goto L_0x01b3
        L_0x017d:
            if (r0 == 0) goto L_0x018d
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r11 = r11.getBrowserCheckbox()
            r11.setState(r5)
            goto L_0x01b3
        L_0x018d:
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            boolean r11 = r11.hasDiscoveredChildren(r8)
            if (r11 == 0) goto L_0x01a6
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r11 = r11.getBrowserCheckbox()
            r11.setState(r3)
            goto L_0x01b3
        L_0x01a6:
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r11 = r11.getBrowserCheckbox()
            r11.setState(r4)
        L_0x01b3:
            org.videolan.resources.AndroidDevices r11 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r11 = r11.isTv()
            if (r11 == 0) goto L_0x01d4
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r11 = r10.this$0
            org.videolan.vlc.gui.browser.BrowserContainer r11 = r11.getBrowserContainer()
            boolean r11 = r11.isRootDirectory()
            if (r11 != 0) goto L_0x01d4
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            android.widget.ImageView r11 = r11.getBanIcon()
            r11.setVisibility(r4)
        L_0x01d4:
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$MediaViewHolder r11 = r10.$vh
            org.videolan.vlc.gui.browser.BrowserItemBindingContainer r11 = r11.getBindingContainer()
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = r10.this$0
            org.videolan.vlc.gui.browser.BrowserContainer r0 = r0.getBrowserContainer()
            boolean r0 = r0.getScannedDirectory()
            r0 = r0 ^ r5
            r11.setCheckEnabled(r0)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.StorageBrowserAdapter$onBindViewHolder$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
