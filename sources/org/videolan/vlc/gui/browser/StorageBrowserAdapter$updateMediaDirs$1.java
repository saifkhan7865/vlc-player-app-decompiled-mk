package org.videolan.vlc.gui.browser;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateMediaDirs$1", f = "StorageBrowserAdapter.kt", i = {}, l = {156, 162}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StorageBrowserAdapter.kt */
final class StorageBrowserAdapter$updateMediaDirs$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    int label;
    final /* synthetic */ StorageBrowserAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageBrowserAdapter$updateMediaDirs$1(StorageBrowserAdapter storageBrowserAdapter, Context context, Continuation<? super StorageBrowserAdapter$updateMediaDirs$1> continuation) {
        super(2, continuation);
        this.this$0 = storageBrowserAdapter;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StorageBrowserAdapter$updateMediaDirs$1(this.this$0, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StorageBrowserAdapter$updateMediaDirs$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0066 A[LOOP:0: B:16:0x0064->B:17:0x0066, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b7 A[LOOP:1: B:23:0x00b1->B:25:0x00b7, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0024
            if (r1 == r4) goto L_0x0020
            if (r1 != r3) goto L_0x0018
            java.lang.Object r0 = r8.L$0
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = (org.videolan.vlc.gui.browser.StorageBrowserAdapter) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x009e
        L_0x0018:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x005c
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r9)
            org.videolan.medialibrary.interfaces.Medialibrary r9 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            boolean r9 = r9.isInitiated()
            if (r9 != 0) goto L_0x0042
            org.videolan.vlc.MediaParsingService$Companion r9 = org.videolan.vlc.MediaParsingService.Companion
            java.util.List r9 = r9.getPreselectedStorages()
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.String[] r1 = new java.lang.String[r2]
            java.lang.Object[] r9 = r9.toArray(r1)
            java.lang.String[] r9 = (java.lang.String[]) r9
            goto L_0x005e
        L_0x0042:
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateMediaDirs$1$folders$1 r1 = new org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateMediaDirs$1$folders$1
            r5 = 0
            r1.<init>(r5)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r5 = r8
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r8.label = r4
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r1, r5)
            if (r9 != r0) goto L_0x005c
            return r0
        L_0x005c:
            java.lang.String[] r9 = (java.lang.String[]) r9
        L_0x005e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r1 = r8.this$0
            int r4 = r9.length
        L_0x0064:
            if (r2 >= r4) goto L_0x0082
            r5 = r9[r2]
            java.util.List r6 = r1.mediaDirsLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.lang.String r5 = org.videolan.tools.Strings.removeFileScheme(r5)
            java.lang.String r5 = android.net.Uri.decode(r5)
            java.lang.String r7 = "decode(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
            r6.add(r5)
            int r2 = r2 + 1
            goto L_0x0064
        L_0x0082:
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r9 = r8.this$0
            org.videolan.vlc.repository.DirectoryRepository$Companion r1 = org.videolan.vlc.repository.DirectoryRepository.Companion
            android.content.Context r2 = r8.$context
            java.lang.Object r1 = r1.getInstance(r2)
            org.videolan.vlc.repository.DirectoryRepository r1 = (org.videolan.vlc.repository.DirectoryRepository) r1
            r2 = r8
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r8.L$0 = r9
            r8.label = r3
            java.lang.Object r1 = r1.getCustomDirectories(r2)
            if (r1 != r0) goto L_0x009c
            return r0
        L_0x009c:
            r0 = r9
            r9 = r1
        L_0x009e:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r2)
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r9 = r9.iterator()
        L_0x00b1:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x00c5
            java.lang.Object r2 = r9.next()
            org.videolan.vlc.mediadb.models.CustomDirectory r2 = (org.videolan.vlc.mediadb.models.CustomDirectory) r2
            java.lang.String r2 = r2.getPath()
            r1.add(r2)
            goto L_0x00b1
        L_0x00c5:
            java.util.List r1 = (java.util.List) r1
            r0.customDirsLocation = r1
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateMediaDirs$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
