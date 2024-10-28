package org.videolan.vlc.providers;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2", f = "FileBrowserProvider.kt", i = {}, l = {139, 147}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileBrowserProvider.kt */
final class FileBrowserProvider$browseByUrl$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList<MediaWrapper> $result;
    final /* synthetic */ String $url;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ FileBrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserProvider$browseByUrl$2(FileBrowserProvider fileBrowserProvider, ArrayList<MediaWrapper> arrayList, String str, Continuation<? super FileBrowserProvider$browseByUrl$2> continuation) {
        super(2, continuation);
        this.this$0 = fileBrowserProvider;
        this.$result = arrayList;
        this.$url = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileBrowserProvider$browseByUrl$2(this.this$0, this.$result, this.$url, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileBrowserProvider$browseByUrl$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 == r3) goto L_0x002b
            if (r1 != r2) goto L_0x0023
            java.lang.Object r1 = r8.L$3
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            java.lang.Object r3 = r8.L$2
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r8.L$1
            org.videolan.vlc.providers.FileBrowserProvider r4 = (org.videolan.vlc.providers.FileBrowserProvider) r4
            java.lang.Object r5 = r8.L$0
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x010d
        L_0x0023:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x002b:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0056
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r9)
            org.videolan.vlc.providers.FileBrowserProvider r9 = r8.this$0
            org.videolan.tools.CoroutineContextProvider r9 = r9.getCoroutineContextProvider()
            kotlinx.coroutines.CoroutineDispatcher r9 = r9.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2$files$1 r1 = new org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2$files$1
            org.videolan.vlc.providers.FileBrowserProvider r4 = r8.this$0
            java.lang.String r5 = r8.$url
            r6 = 0
            r1.<init>(r4, r5, r6)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r4 = r8
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r8.label = r3
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r1, r4)
            if (r9 != r0) goto L_0x0056
            return r0
        L_0x0056:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r1 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r3)
            r1.<init>(r3)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r9 = r9.iterator()
        L_0x0069:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L_0x0080
            java.lang.Object r3 = r9.next()
            org.videolan.medialibrary.media.MediaLibraryItem r3 = (org.videolan.medialibrary.media.MediaLibraryItem) r3
            java.lang.String r4 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r4)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            r1.add(r3)
            goto L_0x0069
        L_0x0080:
            java.util.List r1 = (java.util.List) r1
            java.util.ArrayList<org.videolan.medialibrary.interfaces.media.MediaWrapper> r9 = r8.$result
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r4 = r1.iterator()
        L_0x0091:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00aa
            java.lang.Object r5 = r4.next()
            r6 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r6
            int r6 = r6.getItemType()
            r7 = 32
            if (r6 != r7) goto L_0x0091
            r3.add(r5)
            goto L_0x0091
        L_0x00aa:
            java.util.List r3 = (java.util.List) r3
            java.util.Collection r3 = (java.util.Collection) r3
            r9.addAll(r3)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r1 = r1.iterator()
        L_0x00bc:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00d4
            java.lang.Object r3 = r1.next()
            r4 = r3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
            int r4 = r4.getItemType()
            r5 = 3
            if (r4 != r5) goto L_0x00bc
            r9.add(r3)
            goto L_0x00bc
        L_0x00d4:
            java.util.List r9 = (java.util.List) r9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList<org.videolan.medialibrary.interfaces.media.MediaWrapper> r1 = r8.$result
            org.videolan.vlc.providers.FileBrowserProvider r3 = r8.this$0
            java.util.Iterator r9 = r9.iterator()
            r4 = r3
            r3 = r9
        L_0x00e2:
            boolean r9 = r3.hasNext()
            if (r9 == 0) goto L_0x0114
            java.lang.Object r9 = r3.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r9
            android.net.Uri r9 = r9.getUri()
            java.lang.String r9 = r9.toString()
            java.lang.String r5 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r5)
            r8.L$0 = r1
            r8.L$1 = r4
            r8.L$2 = r3
            r8.L$3 = r1
            r8.label = r2
            java.lang.Object r9 = r4.browseByUrl(r9, r8)
            if (r9 != r0) goto L_0x010c
            return r0
        L_0x010c:
            r5 = r1
        L_0x010d:
            java.util.Collection r9 = (java.util.Collection) r9
            r1.addAll(r9)
            r1 = r5
            goto L_0x00e2
        L_0x0114:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
