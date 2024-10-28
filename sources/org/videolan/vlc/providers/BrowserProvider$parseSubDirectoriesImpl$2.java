package org.videolan.vlc.providers;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2", f = "BrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$parseSubDirectoriesImpl$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<MediaLibraryItem> $currentMediaList;
    final /* synthetic */ List<MediaWrapper> $directories;
    final /* synthetic */ List<MediaWrapper> $files;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$parseSubDirectoriesImpl$2(BrowserProvider browserProvider, List<? extends MediaLibraryItem> list, List<MediaWrapper> list2, List<MediaWrapper> list3, Continuation<? super BrowserProvider$parseSubDirectoriesImpl$2> continuation) {
        super(2, continuation);
        this.this$0 = browserProvider;
        this.$currentMediaList = list;
        this.$directories = list2;
        this.$files = list3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BrowserProvider$parseSubDirectoriesImpl$2 browserProvider$parseSubDirectoriesImpl$2 = new BrowserProvider$parseSubDirectoriesImpl$2(this.this$0, this.$currentMediaList, this.$directories, this.$files, continuation);
        browserProvider$parseSubDirectoriesImpl$2.L$0 = obj;
        return browserProvider$parseSubDirectoriesImpl$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserProvider$parseSubDirectoriesImpl$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1", f = "BrowserProvider.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5}, l = {317, 334, 334, 336, 346, 353}, m = "invokeSuspend", n = {"$this$launch", "currentParsedPosition", "$this$launch", "currentParsedPosition", "item", "$this$launch", "currentParsedPosition", "item", "$this$launch", "currentParsedPosition", "item", "media", "$this$launch", "currentParsedPosition", "item", "$this$launch", "currentParsedPosition"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1"})
    /* renamed from: org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1  reason: invalid class name */
    /* compiled from: BrowserProvider.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(browserProvider2, list, list2, list3, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.util.List} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.util.List} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.util.List} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.util.List} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.util.List} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.util.List} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.util.List} */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x00b1, code lost:
            r2.element += r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x00be, code lost:
            if (r2.element >= r4.size()) goto L_0x02c9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x00c4, code lost:
            if (kotlinx.coroutines.CoroutineScopeKt.isActive(r7) == false) goto L_0x02c9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00c6, code lost:
            r10 = r4.get(r2.element);
            r8 = r10.getItemType();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x00d7, code lost:
            if (r8 == 32) goto L_0x00f5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00db, code lost:
            if (r8 == 128) goto L_0x00e0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x00dd, code lost:
            r10 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e0, code lost:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
            r8 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper(((org.videolan.medialibrary.media.Storage) r10).getUri());
            r8.setType(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x00f5, code lost:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0101, code lost:
            if (r8.getType() == r5) goto L_0x014d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0107, code lost:
            if (r8.getType() == 5) goto L_0x014d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0111, code lost:
            if (r8.getLength() != 0) goto L_0x00b1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0113, code lost:
            r8 = r3.parseMediaSize(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0119, code lost:
            if (r8 == null) goto L_0x00b1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x011b, code lost:
            r13 = r3;
            r11 = r8.longValue();
            r0.L$0 = r7;
            r0.L$1 = r2;
            r0.L$2 = r6;
            r0.L$3 = r6;
            r0.L$4 = r6;
            r0.label = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x014a, code lost:
            if (kotlinx.coroutines.BuildersKt.withContext(r13.getCoroutineContextProvider().getMain(), new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1(r10, r11, r13, r2, (kotlin.coroutines.Continuation<? super org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1>) null), r0) != r1) goto L_0x00b1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x014c, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x015b, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8.getUri().getScheme(), (java.lang.Object) org.videolan.vlc.gui.helpers.hf.OtgAccessKt.OTG_SCHEME) != false) goto L_0x00dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x016b, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8.getUri().getScheme(), (java.lang.Object) "content") == false) goto L_0x016f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x016f, code lost:
            r0.L$0 = r7;
            r0.L$1 = r2;
            r0.L$2 = r10;
            r0.L$3 = r6;
            r0.L$4 = r6;
            r0.label = 2;
            r8 = r3.filesFlow(r8.getUri().toString(), false, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x018e, code lost:
            if (r8 != r1) goto L_0x0191;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0190, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0191, code lost:
            r9 = r7;
            r7 = r2;
            r2 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0194, code lost:
            r0.L$0 = r9;
            r0.L$1 = r7;
            r0.L$2 = r2;
            r0.label = r5;
            r8 = kotlinx.coroutines.flow.FlowKt__CollectionKt.toList$default((kotlinx.coroutines.flow.Flow) r8, r6, r0, r4, r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x01a5, code lost:
            if (r8 != r1) goto L_0x01a8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x01a7, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x01a8, code lost:
            r19 = r9;
            r9 = r7;
            r7 = ((java.util.List) r8).iterator();
            r8 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x01b8, code lost:
            if (r7.hasNext() == false) goto L_0x0212;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x01ba, code lost:
            r10 = (org.videolan.libvlc.interfaces.IMedia) r7.next();
            r0.L$0 = r8;
            r0.L$1 = r9;
            r0.L$2 = r2;
            r0.L$3 = r7;
            r0.L$4 = r10;
            r0.label = 4;
            r11 = r3.findMedia(r10, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x01d6, code lost:
            if (r11 != r1) goto L_0x01d9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x01d8, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x01d9, code lost:
            r19 = r8;
            r8 = r2;
            r2 = r10;
            r10 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x01df, code lost:
            r11 = (org.videolan.medialibrary.media.MediaLibraryItem) r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x01e1, code lost:
            if (r11 != null) goto L_0x01e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x01e6, code lost:
            if ((r11 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper) == false) goto L_0x01fd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x01ef, code lost:
            if (((org.videolan.medialibrary.interfaces.media.MediaWrapper) r11).getType() != r5) goto L_0x01f7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x01f1, code lost:
            r5.add(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x01f7, code lost:
            r6.add(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x01ff, code lost:
            if ((r11 instanceof org.videolan.medialibrary.media.Storage) == false) goto L_0x020f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0201, code lost:
            r11 = r5;
            r2 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper(r2);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "getAbstractMediaWrapper(...)");
            r11.add(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x020f, code lost:
            r2 = r8;
            r8 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x0212, code lost:
            r7 = r3.getDescription(r5.size(), r6.size());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x022b, code lost:
            if (r7.length() <= 0) goto L_0x022f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x022d, code lost:
            r12 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x022f, code lost:
            r12 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x0230, code lost:
            if (r12 == 0) goto L_0x02b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x0232, code lost:
            r7 = r3;
            r15 = r5;
            r14 = r6;
            r18 = r9.element;
            r5 = r14;
            r6 = r15;
            r0.L$0 = r8;
            r0.L$1 = r9;
            r0.L$2 = r2;
            r0.L$3 = r7;
            r0.L$4 = r6;
            r0.L$5 = r5;
            r0.label = 5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x026f, code lost:
            if (kotlinx.coroutines.BuildersKt.withContext(r7.getCoroutineContextProvider().getMain(), new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$1(r2, r12, r7, r18, (kotlin.coroutines.Continuation<? super org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$1>) null), r0) != r1) goto L_0x0272;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0271, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x0272, code lost:
            r4 = r7;
            r7 = r6;
            r19 = r5;
            r5 = r2;
            r2 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0279, code lost:
            r7.addAll(r2);
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, "null cannot be cast to non-null type kotlin.collections.MutableList<org.videolan.medialibrary.media.MediaLibraryItem>");
            r4.sort(kotlin.jvm.internal.TypeIntrinsics.asMutableList(r7));
            r10 = null;
            r0.L$0 = r8;
            r0.L$1 = r9;
            r0.L$2 = null;
            r0.L$3 = null;
            r0.L$4 = null;
            r0.L$5 = null;
            r0.label = 6;
            r2 = kotlinx.coroutines.BuildersKt.withContext(r4.getCoroutineContextProvider().getMain(), new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$2(r4, r5, r7, (kotlin.coroutines.Continuation<? super org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$2>) null), r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x02af, code lost:
            if (r2 != r1) goto L_0x02b2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x02b1, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x02b2, code lost:
            r7 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x02b3, code lost:
            r2 = (java.util.List) r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x02b5, code lost:
            r2 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x02b7, code lost:
            r10 = r6;
            r7 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x02ba, code lost:
            r5.clear();
            r6.clear();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x02c4, code lost:
            r6 = r10;
            r4 = 1;
            r5 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x02cb, code lost:
            return kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                r20 = this;
                r0 = r20
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 5
                r4 = 1
                r5 = 3
                r6 = 0
                switch(r2) {
                    case 0: goto L_0x0098;
                    case 1: goto L_0x008c;
                    case 2: goto L_0x0078;
                    case 3: goto L_0x0064;
                    case 4: goto L_0x0049;
                    case 5: goto L_0x0028;
                    case 6: goto L_0x0017;
                    default: goto L_0x000f;
                }
            L_0x000f:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L_0x0017:
                java.lang.Object r2 = r0.L$1
                kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
                java.lang.Object r7 = r0.L$0
                kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
                kotlin.ResultKt.throwOnFailure(r21)
                r9 = r2
                r10 = r6
                r2 = r21
                goto L_0x02b3
            L_0x0028:
                java.lang.Object r2 = r0.L$5
                java.util.List r2 = (java.util.List) r2
                java.lang.Object r7 = r0.L$4
                java.util.List r7 = (java.util.List) r7
                java.lang.Object r8 = r0.L$3
                org.videolan.vlc.providers.BrowserProvider r8 = (org.videolan.vlc.providers.BrowserProvider) r8
                java.lang.Object r9 = r0.L$2
                org.videolan.medialibrary.media.MediaLibraryItem r9 = (org.videolan.medialibrary.media.MediaLibraryItem) r9
                java.lang.Object r10 = r0.L$1
                kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
                java.lang.Object r11 = r0.L$0
                kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
                kotlin.ResultKt.throwOnFailure(r21)
                r4 = r8
                r5 = r9
                r9 = r10
                r8 = r11
                goto L_0x0279
            L_0x0049:
                java.lang.Object r2 = r0.L$4
                org.videolan.libvlc.interfaces.IMedia r2 = (org.videolan.libvlc.interfaces.IMedia) r2
                java.lang.Object r7 = r0.L$3
                java.util.Iterator r7 = (java.util.Iterator) r7
                java.lang.Object r8 = r0.L$2
                org.videolan.medialibrary.media.MediaLibraryItem r8 = (org.videolan.medialibrary.media.MediaLibraryItem) r8
                java.lang.Object r9 = r0.L$1
                kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
                java.lang.Object r10 = r0.L$0
                kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
                kotlin.ResultKt.throwOnFailure(r21)
                r11 = r21
                goto L_0x01df
            L_0x0064:
                java.lang.Object r2 = r0.L$2
                org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
                java.lang.Object r7 = r0.L$1
                kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
                java.lang.Object r8 = r0.L$0
                kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
                kotlin.ResultKt.throwOnFailure(r21)
                r9 = r8
                r8 = r21
                goto L_0x01a8
            L_0x0078:
                java.lang.Object r2 = r0.L$2
                org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
                java.lang.Object r7 = r0.L$1
                kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
                java.lang.Object r8 = r0.L$0
                kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
                kotlin.ResultKt.throwOnFailure(r21)
                r9 = r8
                r8 = r21
                goto L_0x0194
            L_0x008c:
                java.lang.Object r2 = r0.L$1
                kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
                java.lang.Object r7 = r0.L$0
                kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
                kotlin.ResultKt.throwOnFailure(r21)
                goto L_0x00b1
            L_0x0098:
                kotlin.ResultKt.throwOnFailure(r21)
                java.lang.Object r2 = r0.L$0
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                org.videolan.vlc.providers.BrowserProvider r7 = r3
                r7.initBrowser()
                kotlin.jvm.internal.Ref$IntRef r7 = new kotlin.jvm.internal.Ref$IntRef
                r7.<init>()
                r8 = -1
                r7.element = r8
                r19 = r7
                r7 = r2
                r2 = r19
            L_0x00b1:
                int r8 = r2.element
                int r8 = r8 + r4
                r2.element = r8
                int r8 = r2.element
                java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r9 = r4
                int r9 = r9.size()
                if (r8 >= r9) goto L_0x02c9
                boolean r8 = kotlinx.coroutines.CoroutineScopeKt.isActive(r7)
                if (r8 == 0) goto L_0x02c9
                java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r8 = r4
                int r9 = r2.element
                java.lang.Object r8 = r8.get(r9)
                r10 = r8
                org.videolan.medialibrary.media.MediaLibraryItem r10 = (org.videolan.medialibrary.media.MediaLibraryItem) r10
                int r8 = r10.getItemType()
                r9 = 32
                if (r8 == r9) goto L_0x00f5
                r9 = 128(0x80, float:1.794E-43)
                if (r8 == r9) goto L_0x00e0
            L_0x00dd:
                r10 = r6
                goto L_0x02c4
            L_0x00e0:
                java.lang.String r8 = "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r8)
                r8 = r10
                org.videolan.medialibrary.media.Storage r8 = (org.videolan.medialibrary.media.Storage) r8
                android.net.Uri r8 = r8.getUri()
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((android.net.Uri) r8)
                r8.setType(r5)
                goto L_0x016f
            L_0x00f5:
                java.lang.String r8 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r8)
                r8 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
                int r9 = r8.getType()
                if (r9 == r5) goto L_0x014d
                int r9 = r8.getType()
                if (r9 == r3) goto L_0x014d
                long r11 = r8.getLength()
                r13 = 0
                int r9 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r9 != 0) goto L_0x00b1
                org.videolan.vlc.providers.BrowserProvider r9 = r3
                java.lang.Long r8 = r9.parseMediaSize(r8)
                if (r8 == 0) goto L_0x00b1
                org.videolan.vlc.providers.BrowserProvider r13 = r3
                java.lang.Number r8 = (java.lang.Number) r8
                long r11 = r8.longValue()
                org.videolan.tools.CoroutineContextProvider r8 = r13.getCoroutineContextProvider()
                kotlinx.coroutines.CoroutineDispatcher r8 = r8.getMain()
                kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
                org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1 r16 = new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1
                r15 = 0
                r9 = r16
                r14 = r2
                r9.<init>(r10, r11, r13, r14, r15)
                r9 = r16
                kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
                r0.L$0 = r7
                r0.L$1 = r2
                r0.L$2 = r6
                r0.L$3 = r6
                r0.L$4 = r6
                r0.label = r4
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r9, r0)
                if (r8 != r1) goto L_0x00b1
                return r1
            L_0x014d:
                android.net.Uri r9 = r8.getUri()
                java.lang.String r9 = r9.getScheme()
                java.lang.String r11 = "otg"
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r11)
                if (r9 != 0) goto L_0x00dd
                android.net.Uri r9 = r8.getUri()
                java.lang.String r9 = r9.getScheme()
                java.lang.String r11 = "content"
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r11)
                if (r9 == 0) goto L_0x016f
                goto L_0x00dd
            L_0x016f:
                org.videolan.vlc.providers.BrowserProvider r9 = r3
                android.net.Uri r8 = r8.getUri()
                java.lang.String r8 = r8.toString()
                r11 = r0
                kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
                r0.L$0 = r7
                r0.L$1 = r2
                r0.L$2 = r10
                r0.L$3 = r6
                r0.L$4 = r6
                r12 = 2
                r0.label = r12
                r12 = 0
                java.lang.Object r8 = r9.filesFlow(r8, r12, r11)
                if (r8 != r1) goto L_0x0191
                return r1
            L_0x0191:
                r9 = r7
                r7 = r2
                r2 = r10
            L_0x0194:
                kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
                r10 = r0
                kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
                r0.L$0 = r9
                r0.L$1 = r7
                r0.L$2 = r2
                r0.label = r5
                java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt__CollectionKt.toList$default(r8, r6, r10, r4, r6)
                if (r8 != r1) goto L_0x01a8
                return r1
            L_0x01a8:
                java.util.List r8 = (java.util.List) r8
                java.util.Iterator r8 = r8.iterator()
                r19 = r9
                r9 = r7
                r7 = r8
                r8 = r19
            L_0x01b4:
                boolean r10 = r7.hasNext()
                if (r10 == 0) goto L_0x0212
                java.lang.Object r10 = r7.next()
                org.videolan.libvlc.interfaces.IMedia r10 = (org.videolan.libvlc.interfaces.IMedia) r10
                org.videolan.vlc.providers.BrowserProvider r11 = r3
                r12 = r0
                kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
                r0.L$0 = r8
                r0.L$1 = r9
                r0.L$2 = r2
                r0.L$3 = r7
                r0.L$4 = r10
                r13 = 4
                r0.label = r13
                java.lang.Object r11 = r11.findMedia(r10, r12)
                if (r11 != r1) goto L_0x01d9
                return r1
            L_0x01d9:
                r19 = r8
                r8 = r2
                r2 = r10
                r10 = r19
            L_0x01df:
                org.videolan.medialibrary.media.MediaLibraryItem r11 = (org.videolan.medialibrary.media.MediaLibraryItem) r11
                if (r11 != 0) goto L_0x01e4
                goto L_0x020f
            L_0x01e4:
                boolean r12 = r11 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
                if (r12 == 0) goto L_0x01fd
                r2 = r11
                org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
                int r2 = r2.getType()
                if (r2 != r5) goto L_0x01f7
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r2 = r5
                r2.add(r11)
                goto L_0x020f
            L_0x01f7:
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r2 = r6
                r2.add(r11)
                goto L_0x020f
            L_0x01fd:
                boolean r11 = r11 instanceof org.videolan.medialibrary.media.Storage
                if (r11 == 0) goto L_0x020f
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r11 = r5
                org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((org.videolan.libvlc.interfaces.IMedia) r2)
                java.lang.String r12 = "getAbstractMediaWrapper(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r12)
                r11.add(r2)
            L_0x020f:
                r2 = r8
                r8 = r10
                goto L_0x01b4
            L_0x0212:
                org.videolan.vlc.providers.BrowserProvider r7 = r3
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r10 = r5
                int r10 = r10.size()
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r11 = r6
                int r11 = r11.size()
                java.lang.String r7 = r7.getDescription(r10, r11)
                r10 = r7
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10
                int r10 = r10.length()
                if (r10 <= 0) goto L_0x022f
                r12 = r7
                goto L_0x0230
            L_0x022f:
                r12 = r6
            L_0x0230:
                if (r12 == 0) goto L_0x02b7
                org.videolan.vlc.providers.BrowserProvider r7 = r3
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r15 = r5
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r14 = r6
                int r13 = r9.element
                org.videolan.tools.CoroutineContextProvider r10 = r7.getCoroutineContextProvider()
                kotlinx.coroutines.CoroutineDispatcher r10 = r10.getMain()
                r11 = r10
                kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
                org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$1 r16 = new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$1
                r17 = 0
                r10 = r16
                r4 = r11
                r11 = r2
                r18 = r13
                r13 = r7
                r5 = r14
                r14 = r18
                r6 = r15
                r15 = r17
                r10.<init>(r11, r12, r13, r14, r15)
                r10 = r16
                kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
                r0.L$0 = r8
                r0.L$1 = r9
                r0.L$2 = r2
                r0.L$3 = r7
                r0.L$4 = r6
                r0.L$5 = r5
                r0.label = r3
                java.lang.Object r4 = kotlinx.coroutines.BuildersKt.withContext(r4, r10, r0)
                if (r4 != r1) goto L_0x0272
                return r1
            L_0x0272:
                r4 = r7
                r7 = r6
                r19 = r5
                r5 = r2
                r2 = r19
            L_0x0279:
                java.util.Collection r2 = (java.util.Collection) r2
                r7.addAll(r2)
                java.lang.String r2 = "null cannot be cast to non-null type kotlin.collections.MutableList<org.videolan.medialibrary.media.MediaLibraryItem>"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r2)
                java.util.List r2 = kotlin.jvm.internal.TypeIntrinsics.asMutableList(r7)
                r4.sort(r2)
                org.videolan.tools.CoroutineContextProvider r2 = r4.getCoroutineContextProvider()
                kotlinx.coroutines.CoroutineDispatcher r2 = r2.getMain()
                kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
                org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$2 r6 = new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$2
                r10 = 0
                r6.<init>(r4, r5, r7, r10)
                kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                r0.L$0 = r8
                r0.L$1 = r9
                r0.L$2 = r10
                r0.L$3 = r10
                r0.L$4 = r10
                r0.L$5 = r10
                r4 = 6
                r0.label = r4
                java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                if (r2 != r1) goto L_0x02b2
                return r1
            L_0x02b2:
                r7 = r8
            L_0x02b3:
                java.util.List r2 = (java.util.List) r2
            L_0x02b5:
                r2 = r9
                goto L_0x02ba
            L_0x02b7:
                r10 = r6
                r7 = r8
                goto L_0x02b5
            L_0x02ba:
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r4 = r5
                r4.clear()
                java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r4 = r6
                r4.clear()
            L_0x02c4:
                r6 = r10
                r4 = 1
                r5 = 3
                goto L_0x00b1
            L_0x02c9:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BrowserProvider browserProvider = this.this$0;
            final BrowserProvider browserProvider2 = this.this$0;
            final List<MediaLibraryItem> list = this.$currentMediaList;
            final List<MediaWrapper> list2 = this.$directories;
            final List<MediaWrapper> list3 = this.$files;
            browserProvider.parsingJob = BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, browserProvider.getCoroutineContextProvider().getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
