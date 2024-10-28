package org.videolan.resources.opensubtitles;

import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.videolan.resources.opensubtitles.IOpenSubtitleService;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH@¢\u0006\u0002\u0010\rJ6\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006H@¢\u0006\u0002\u0010\u000fJD\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u000bH@¢\u0006\u0002\u0010\u0016JJ\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006H@¢\u0006\u0002\u0010\u0017J:\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u000bH@¢\u0006\u0002\u0010\u001aJ@\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006H@¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/videolan/resources/opensubtitles/OpenSubtitleRepository;", "", "openSubtitleService", "Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;", "(Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;)V", "queryWithHash", "", "Lorg/videolan/resources/opensubtitles/OpenSubtitle;", "movieByteSize", "", "movieHash", "", "languageId", "(JLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "languageIds", "(JLjava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryWithImdbid", "imdbId", "", "tag", "episode", "season", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryWithName", "name", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OpenSubtitleRepository.kt */
public final class OpenSubtitleRepository {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static Lazy<OpenSubtitleRepository> instance = LazyKt.lazy(OpenSubtitleRepository$Companion$instance$1.INSTANCE);
    private final IOpenSubtitleService openSubtitleService;

    public OpenSubtitleRepository(IOpenSubtitleService iOpenSubtitleService) {
        Intrinsics.checkNotNullParameter(iOpenSubtitleService, "openSubtitleService");
        this.openSubtitleService = iOpenSubtitleService;
    }

    public final Object queryWithImdbid(int i, String str, Integer num, Integer num2, String str2, Continuation<? super List<OpenSubtitle>> continuation) {
        String str3;
        OpenSubtitleRepository openSubtitleRepository;
        int intValue = num != null ? num.intValue() : 0;
        int intValue2 = num2 != null ? num2.intValue() : 0;
        String str4 = str2 == null ? "" : str2;
        if (str == null) {
            str3 = "";
            openSubtitleRepository = this;
        } else {
            openSubtitleRepository = this;
            str3 = str;
        }
        IOpenSubtitleService iOpenSubtitleService = openSubtitleRepository.openSubtitleService;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%07d", Arrays.copyOf(new Object[]{Boxing.boxInt(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return IOpenSubtitleService.DefaultImpls.query$default(iOpenSubtitleService, (String) null, (String) null, (String) null, format, str3, intValue, intValue2, str4, continuation, 7, (Object) null);
    }

    public final Object queryWithHash(long j, String str, String str2, Continuation<? super List<OpenSubtitle>> continuation) {
        String str3;
        OpenSubtitleRepository openSubtitleRepository;
        if (str2 == null) {
            str3 = "";
            openSubtitleRepository = this;
        } else {
            openSubtitleRepository = this;
            str3 = str2;
        }
        return IOpenSubtitleService.DefaultImpls.query$default(openSubtitleRepository.openSubtitleService, String.valueOf(j), str, (String) null, (String) null, (String) null, 0, 0, str3, continuation, 124, (Object) null);
    }

    public final Object queryWithName(String str, Integer num, Integer num2, String str2, Continuation<? super List<OpenSubtitle>> continuation) {
        String str3;
        OpenSubtitleRepository openSubtitleRepository;
        int intValue = num != null ? num.intValue() : 0;
        int intValue2 = num2 != null ? num2.intValue() : 0;
        if (str2 == null) {
            str3 = "";
            openSubtitleRepository = this;
        } else {
            openSubtitleRepository = this;
            str3 = str2;
        }
        return IOpenSubtitleService.DefaultImpls.query$default(openSubtitleRepository.openSubtitleService, (String) null, (String) null, str, (String) null, (String) null, intValue, intValue2, str3, continuation, 27, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        if (r8 == null) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object queryWithImdbid(int r27, java.lang.String r28, java.lang.Integer r29, java.lang.Integer r30, java.util.List<java.lang.String> r31, kotlin.coroutines.Continuation<? super java.util.List<org.videolan.resources.opensubtitles.OpenSubtitle>> r32) {
        /*
            r26 = this;
            r0 = r32
            boolean r1 = r0 instanceof org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithImdbid$2
            if (r1 == 0) goto L_0x0018
            r1 = r0
            org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithImdbid$2 r1 = (org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithImdbid$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r26
            goto L_0x001f
        L_0x0018:
            org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithImdbid$2 r1 = new org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithImdbid$2
            r2 = r26
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0058
            if (r4 != r6) goto L_0x0050
            int r4 = r1.I$2
            int r7 = r1.I$1
            int r8 = r1.I$0
            java.lang.Object r9 = r1.L$3
            java.util.Iterator r9 = (java.util.Iterator) r9
            java.lang.Object r10 = r1.L$2
            java.util.Collection r10 = (java.util.Collection) r10
            java.lang.Object r11 = r1.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r1.L$0
            org.videolan.resources.opensubtitles.OpenSubtitleRepository r12 = (org.videolan.resources.opensubtitles.OpenSubtitleRepository) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r15 = r7
            r14 = r9
            r13 = r10
            r25 = r12
            r12 = r11
            r11 = r25
            goto L_0x0116
        L_0x0050:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0058:
            kotlin.ResultKt.throwOnFailure(r0)
            if (r29 == 0) goto L_0x0062
            int r0 = r29.intValue()
            goto L_0x0063
        L_0x0062:
            r0 = 0
        L_0x0063:
            if (r30 == 0) goto L_0x006a
            int r4 = r30.intValue()
            goto L_0x006b
        L_0x006a:
            r4 = 0
        L_0x006b:
            java.lang.String r7 = ""
            if (r31 == 0) goto L_0x008b
            r8 = r31
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Set r8 = kotlin.collections.CollectionsKt.toSet(r8)
            if (r8 == 0) goto L_0x008b
            boolean r9 = r8.contains(r7)
            if (r9 != 0) goto L_0x0085
            boolean r9 = r8.isEmpty()
            if (r9 == 0) goto L_0x0089
        L_0x0085:
            java.util.Set r8 = kotlin.collections.SetsKt.setOf(r7)
        L_0x0089:
            if (r8 != 0) goto L_0x008f
        L_0x008b:
            java.util.Set r8 = kotlin.collections.SetsKt.setOf(r7)
        L_0x008f:
            if (r28 != 0) goto L_0x0092
            goto L_0x0094
        L_0x0092:
            r7 = r28
        L_0x0094:
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r8 = r8.iterator()
            r15 = r0
            r11 = r2
            r12 = r7
            r14 = r8
            r13 = r9
            r0 = r27
        L_0x00a8:
            boolean r7 = r14.hasNext()
            if (r7 == 0) goto L_0x011f
            java.lang.Object r7 = r14.next()
            r16 = r7
            java.lang.String r16 = (java.lang.String) r16
            org.videolan.resources.opensubtitles.IOpenSubtitleService r7 = r11.openSubtitleService
            kotlin.jvm.internal.StringCompanionObject r8 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            java.lang.Object[] r9 = new java.lang.Object[r6]
            r9[r5] = r8
            java.lang.Object[] r8 = java.util.Arrays.copyOf(r9, r6)
            java.lang.String r9 = "%07d"
            java.lang.String r10 = java.lang.String.format(r9, r8)
            java.lang.String r8 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r8)
            r1.L$0 = r11
            r1.L$1 = r12
            r1.L$2 = r13
            r1.L$3 = r14
            r1.I$0 = r0
            r1.I$1 = r15
            r1.I$2 = r4
            r1.label = r6
            r8 = 0
            r9 = 0
            r17 = 0
            r18 = 7
            r19 = 0
            r20 = r10
            r10 = r17
            r21 = r11
            r11 = r20
            r20 = r12
            r22 = r13
            r13 = r15
            r23 = r14
            r14 = r4
            r24 = r15
            r15 = r16
            r16 = r1
            r17 = r18
            r18 = r19
            java.lang.Object r7 = org.videolan.resources.opensubtitles.IOpenSubtitleService.DefaultImpls.query$default(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            if (r7 != r3) goto L_0x010a
            return r3
        L_0x010a:
            r8 = r0
            r0 = r7
            r12 = r20
            r11 = r21
            r13 = r22
            r14 = r23
            r15 = r24
        L_0x0116:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll(r13, r0)
            r0 = r8
            goto L_0x00a8
        L_0x011f:
            r22 = r13
            r13 = r22
            java.util.List r13 = (java.util.List) r13
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.opensubtitles.OpenSubtitleRepository.queryWithImdbid(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        if (r0 == null) goto L_0x0073;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object queryWithHash(long r28, java.lang.String r30, java.util.List<java.lang.String> r31, kotlin.coroutines.Continuation<? super java.util.List<org.videolan.resources.opensubtitles.OpenSubtitle>> r32) {
        /*
            r27 = this;
            r0 = r32
            boolean r1 = r0 instanceof org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithHash$2
            if (r1 == 0) goto L_0x0018
            r1 = r0
            org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithHash$2 r1 = (org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithHash$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r27
            goto L_0x001f
        L_0x0018:
            org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithHash$2 r1 = new org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithHash$2
            r2 = r27
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            java.lang.String r6 = ""
            if (r4 == 0) goto L_0x0052
            if (r4 != r5) goto L_0x004a
            long r7 = r1.J$0
            java.lang.Object r4 = r1.L$3
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r9 = r1.L$2
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.Object r10 = r1.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r1.L$0
            org.videolan.resources.opensubtitles.OpenSubtitleRepository r11 = (org.videolan.resources.opensubtitles.OpenSubtitleRepository) r11
            kotlin.ResultKt.throwOnFailure(r0)
            r15 = r1
            r14 = r4
            r13 = r9
            r4 = r10
            r12 = r11
            goto L_0x00e7
        L_0x004a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r0)
            if (r31 == 0) goto L_0x0073
            r0 = r31
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Set r0 = kotlin.collections.CollectionsKt.toSet(r0)
            if (r0 == 0) goto L_0x0073
            boolean r4 = r0.contains(r6)
            if (r4 != 0) goto L_0x006d
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L_0x0071
        L_0x006d:
            java.util.Set r0 = kotlin.collections.SetsKt.setOf(r6)
        L_0x0071:
            if (r0 != 0) goto L_0x0077
        L_0x0073:
            java.util.Set r0 = kotlin.collections.SetsKt.setOf(r6)
        L_0x0077:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r0 = r0.iterator()
            r14 = r0
            r15 = r1
            r12 = r2
            r13 = r4
            r0 = r28
            r4 = r30
        L_0x008c:
            boolean r7 = r14.hasNext()
            if (r7 == 0) goto L_0x00f0
            java.lang.Object r7 = r14.next()
            r16 = r7
            java.lang.String r16 = (java.lang.String) r16
            org.videolan.resources.opensubtitles.IOpenSubtitleService r7 = r12.openSubtitleService
            java.lang.String r8 = java.lang.String.valueOf(r0)
            if (r4 != 0) goto L_0x00a4
            r9 = r6
            goto L_0x00a5
        L_0x00a4:
            r9 = r4
        L_0x00a5:
            r15.L$0 = r12
            r15.L$1 = r4
            r15.L$2 = r13
            r15.L$3 = r14
            r15.J$0 = r0
            r15.label = r5
            r10 = 0
            r11 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 124(0x7c, float:1.74E-43)
            r21 = 0
            r22 = r12
            r12 = r17
            r23 = r13
            r13 = r18
            r24 = r14
            r14 = r19
            r19 = r15
            r15 = r16
            r16 = r19
            r17 = r20
            r18 = r21
            java.lang.Object r7 = org.videolan.resources.opensubtitles.IOpenSubtitleService.DefaultImpls.query$default(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            if (r7 != r3) goto L_0x00da
            return r3
        L_0x00da:
            r15 = r19
            r12 = r22
            r13 = r23
            r14 = r24
            r25 = r0
            r0 = r7
            r7 = r25
        L_0x00e7:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll(r13, r0)
            r0 = r7
            goto L_0x008c
        L_0x00f0:
            r23 = r13
            r13 = r23
            java.util.List r13 = (java.util.List) r13
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.opensubtitles.OpenSubtitleRepository.queryWithHash(long, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        if (r7 == null) goto L_0x0081;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object queryWithName(java.lang.String r23, java.lang.Integer r24, java.lang.Integer r25, java.util.List<java.lang.String> r26, kotlin.coroutines.Continuation<? super java.util.List<org.videolan.resources.opensubtitles.OpenSubtitle>> r27) {
        /*
            r22 = this;
            r0 = r27
            boolean r1 = r0 instanceof org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithName$2
            if (r1 == 0) goto L_0x0018
            r1 = r0
            org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithName$2 r1 = (org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithName$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r22
            goto L_0x001f
        L_0x0018:
            org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithName$2 r1 = new org.videolan.resources.opensubtitles.OpenSubtitleRepository$queryWithName$2
            r2 = r22
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            if (r4 == 0) goto L_0x004f
            if (r4 != r5) goto L_0x0047
            int r4 = r1.I$1
            int r6 = r1.I$0
            java.lang.Object r7 = r1.L$3
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r1.L$2
            java.util.Collection r8 = (java.util.Collection) r8
            java.lang.Object r9 = r1.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r1.L$0
            org.videolan.resources.opensubtitles.OpenSubtitleRepository r10 = (org.videolan.resources.opensubtitles.OpenSubtitleRepository) r10
            kotlin.ResultKt.throwOnFailure(r0)
            r15 = r8
            r14 = r10
            goto L_0x00d4
        L_0x0047:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = 0
            if (r24 == 0) goto L_0x005a
            int r4 = r24.intValue()
            goto L_0x005b
        L_0x005a:
            r4 = 0
        L_0x005b:
            if (r25 == 0) goto L_0x0061
            int r0 = r25.intValue()
        L_0x0061:
            java.lang.String r6 = ""
            if (r26 == 0) goto L_0x0081
            r7 = r26
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Set r7 = kotlin.collections.CollectionsKt.toSet(r7)
            if (r7 == 0) goto L_0x0081
            boolean r8 = r7.contains(r6)
            if (r8 != 0) goto L_0x007b
            boolean r8 = r7.isEmpty()
            if (r8 == 0) goto L_0x007f
        L_0x007b:
            java.util.Set r7 = kotlin.collections.SetsKt.setOf(r6)
        L_0x007f:
            if (r7 != 0) goto L_0x0085
        L_0x0081:
            java.util.Set r7 = kotlin.collections.SetsKt.setOf(r6)
        L_0x0085:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r7 = r7.iterator()
            r14 = r2
            r15 = r6
            r6 = r4
            r4 = r0
            r0 = r23
        L_0x0098:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00dd
            java.lang.Object r8 = r7.next()
            r16 = r8
            java.lang.String r16 = (java.lang.String) r16
            org.videolan.resources.opensubtitles.IOpenSubtitleService r8 = r14.openSubtitleService
            r1.L$0 = r14
            r1.L$1 = r0
            r1.L$2 = r15
            r1.L$3 = r7
            r1.I$0 = r6
            r1.I$1 = r4
            r1.label = r5
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r18 = 27
            r19 = 0
            r11 = r0
            r20 = r14
            r14 = r6
            r21 = r15
            r15 = r4
            r17 = r1
            java.lang.Object r8 = org.videolan.resources.opensubtitles.IOpenSubtitleService.DefaultImpls.query$default(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            if (r8 != r3) goto L_0x00ce
            return r3
        L_0x00ce:
            r9 = r0
            r0 = r8
            r14 = r20
            r15 = r21
        L_0x00d4:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll(r15, r0)
            r0 = r9
            goto L_0x0098
        L_0x00dd:
            r21 = r15
            r15 = r21
            java.util.List r15 = (java.util.List) r15
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.opensubtitles.OpenSubtitleRepository.queryWithName(java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0005R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/videolan/resources/opensubtitles/OpenSubtitleRepository$Companion;", "", "()V", "instance", "Lkotlin/Lazy;", "Lorg/videolan/resources/opensubtitles/OpenSubtitleRepository;", "getInstance", "()Lkotlin/Lazy;", "setInstance", "(Lkotlin/Lazy;)V", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OpenSubtitleRepository.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Lazy<OpenSubtitleRepository> getInstance() {
            return OpenSubtitleRepository.instance;
        }

        public final void setInstance(Lazy<OpenSubtitleRepository> lazy) {
            Intrinsics.checkNotNullParameter(lazy, "<set-?>");
            OpenSubtitleRepository.instance = lazy;
        }

        /* renamed from: getInstance  reason: collision with other method in class */
        public final OpenSubtitleRepository m2434getInstance() {
            return getInstance().getValue();
        }
    }
}
