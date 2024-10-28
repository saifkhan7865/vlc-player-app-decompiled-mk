package org.videolan.vlc.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.ModelsHelper$splitList$2", f = "ModelsHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ModelsHelper.kt */
final class ModelsHelper$splitList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, List<MediaLibraryItem>>>, Object> {
    final /* synthetic */ Collection<MediaLibraryItem> $items;
    final /* synthetic */ int $sort;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ModelsHelper$splitList$2(int i, Collection<? extends MediaLibraryItem> collection, Continuation<? super ModelsHelper$splitList$2> continuation) {
        super(2, continuation);
        this.$sort = i;
        this.$items = collection;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ModelsHelper$splitList$2(this.$sort, this.$items, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, List<MediaLibraryItem>>> continuation) {
        return ((ModelsHelper$splitList$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r0 != 10) goto L_0x0207;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r10.label
            if (r0 != 0) goto L_0x0216
            kotlin.ResultKt.throwOnFailure(r11)
            java.util.LinkedHashMap r11 = new java.util.LinkedHashMap
            r11.<init>()
            java.util.Map r11 = (java.util.Map) r11
            int r0 = r10.$sort
            r1 = 10
            r2 = 1
            r3 = 64
            r4 = 0
            if (r0 == 0) goto L_0x017c
            if (r0 == r2) goto L_0x017c
            r5 = 2
            if (r0 == r5) goto L_0x012b
            r5 = 5
            if (r0 == r5) goto L_0x00de
            r5 = 7
            java.lang.String r6 = ""
            java.lang.String r7 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            if (r0 == r5) goto L_0x0088
            r5 = 9
            if (r0 == r5) goto L_0x0032
            if (r0 == r1) goto L_0x017c
            goto L_0x0207
        L_0x0032:
            java.util.Collection<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r10.$items
            java.util.Iterator r0 = r0.iterator()
        L_0x0038:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0207
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            int r8 = r5.getItemType()
            if (r8 == r3) goto L_0x0038
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r7)
            r8 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            java.lang.String r8 = r8.getAlbum()
            if (r8 != 0) goto L_0x0057
            r8 = r6
        L_0x0057:
            if (r4 == 0) goto L_0x005f
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r8)
            if (r9 != 0) goto L_0x0078
        L_0x005f:
            java.lang.Object r4 = r11.get(r8)
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L_0x006d
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x0077
        L_0x006d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            r11.put(r8, r4)
        L_0x0077:
            r4 = r8
        L_0x0078:
            java.lang.Object r8 = r11.get(r4)
            java.util.List r8 = (java.util.List) r8
            if (r8 == 0) goto L_0x0038
            boolean r5 = r8.add(r5)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            goto L_0x0038
        L_0x0088:
            java.util.Collection<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r10.$items
            java.util.Iterator r0 = r0.iterator()
        L_0x008e:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0207
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            int r8 = r5.getItemType()
            if (r8 == r3) goto L_0x008e
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r7)
            r8 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            java.lang.String r8 = r8.getArtist()
            if (r8 != 0) goto L_0x00ad
            r8 = r6
        L_0x00ad:
            if (r4 == 0) goto L_0x00b5
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r8)
            if (r9 != 0) goto L_0x00ce
        L_0x00b5:
            java.lang.Object r4 = r11.get(r8)
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L_0x00c3
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x00cd
        L_0x00c3:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            r11.put(r8, r4)
        L_0x00cd:
            r4 = r8
        L_0x00ce:
            java.lang.Object r8 = r11.get(r4)
            java.util.List r8 = (java.util.List) r8
            if (r8 == 0) goto L_0x008e
            boolean r5 = r8.add(r5)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            goto L_0x008e
        L_0x00de:
            java.util.Collection<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r10.$items
            java.util.Iterator r0 = r0.iterator()
        L_0x00e4:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0207
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            int r6 = r5.getItemType()
            if (r6 == r3) goto L_0x00e4
            java.lang.String r6 = org.videolan.resources.util.HelpersKt.getYear(r5)
            if (r4 == 0) goto L_0x0102
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r7 != 0) goto L_0x011b
        L_0x0102:
            java.lang.Object r4 = r11.get(r6)
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L_0x0110
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x011a
        L_0x0110:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            r11.put(r6, r4)
        L_0x011a:
            r4 = r6
        L_0x011b:
            java.lang.Object r6 = r11.get(r4)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x00e4
            boolean r5 = r6.add(r5)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            goto L_0x00e4
        L_0x012b:
            java.util.Collection<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r10.$items
            java.util.Iterator r0 = r0.iterator()
        L_0x0131:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0207
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            int r6 = r5.getItemType()
            if (r6 == r3) goto L_0x0131
            long r6 = org.videolan.resources.util.HelpersKt.getLength(r5)
            java.lang.String r6 = org.videolan.vlc.util.ModelsHelperKt.lengthToCategory(r6)
            if (r4 == 0) goto L_0x0153
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r7 != 0) goto L_0x016c
        L_0x0153:
            java.lang.Object r4 = r11.get(r6)
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L_0x0161
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x016b
        L_0x0161:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            r11.put(r6, r4)
        L_0x016b:
            r4 = r6
        L_0x016c:
            java.lang.Object r6 = r11.get(r4)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0131
            boolean r5 = r6.add(r5)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            goto L_0x0131
        L_0x017c:
            java.util.Collection<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r10.$items
            java.util.Iterator r0 = r0.iterator()
        L_0x0182:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0207
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            int r6 = r5.getItemType()
            if (r6 == r3) goto L_0x0182
            java.lang.String r6 = r5.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r7 = r6
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            int r7 = r7.length()
            if (r7 != 0) goto L_0x01a5
            goto L_0x01d3
        L_0x01a5:
            r7 = 0
            char r8 = r6.charAt(r7)
            boolean r8 = java.lang.Character.isLetter(r8)
            if (r8 == 0) goto L_0x01d3
            boolean r8 = org.videolan.resources.util.HelpersKt.isSpecialItem(r5)
            if (r8 == 0) goto L_0x01b7
            goto L_0x01d3
        L_0x01b7:
            java.lang.String r6 = r6.substring(r7, r2)
            java.lang.String r7 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.util.Locale r7 = java.util.Locale.getDefault()
            java.lang.String r8 = "getDefault(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            java.lang.String r6 = r6.toUpperCase(r7)
            java.lang.String r7 = "toUpperCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            goto L_0x01d5
        L_0x01d3:
            java.lang.String r6 = "#"
        L_0x01d5:
            if (r4 == 0) goto L_0x01dd
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r7 != 0) goto L_0x01f6
        L_0x01dd:
            java.lang.Object r4 = r11.get(r6)
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L_0x01eb
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x01f5
        L_0x01eb:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            r11.put(r6, r4)
        L_0x01f5:
            r4 = r6
        L_0x01f6:
            java.lang.Object r6 = r11.get(r6)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0182
            boolean r5 = r6.add(r5)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            goto L_0x0182
        L_0x0207:
            int r0 = r10.$sort
            if (r0 == 0) goto L_0x020f
            if (r0 == r1) goto L_0x020f
            if (r0 != r2) goto L_0x0215
        L_0x020f:
            java.util.SortedMap r11 = kotlin.collections.MapsKt.toSortedMap(r11)
            java.util.Map r11 = (java.util.Map) r11
        L_0x0215:
            return r11
        L_0x0216:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            goto L_0x021f
        L_0x021e:
            throw r11
        L_0x021f:
            goto L_0x021e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ModelsHelper$splitList$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
