package io.ktor.server.http.content;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentType;
import io.ktor.http.HeaderValue;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.routing.Route;
import io.ktor.util.AttributeKey;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u001aV\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00022\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015H\u0000\u001a0\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00022\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0000\u001a\u0001\u0010\u001a\u001a\u00020\u001b*\u00020\r2\u0006\u0010\u001c\u001a\u00020\u00192\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\u00152\u001a\b\u0002\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00020\u00152*\b\u0002\u0010\u001f\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0!\u0012\u0006\u0012\u0004\u0018\u00010\"0 H@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a«\u0001\u0010$\u001a\u00020\u001b*\u00020\r2\u0006\u0010%\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00152\u001a\b\u0002\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00020\u00152*\b\u0002\u0010&\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0!\u0012\u0006\u0012\u0004\u0018\u00010\"0 2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020(0\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010)\" \u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\" \u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"compressedKey", "Lio/ktor/util/AttributeKey;", "", "Lio/ktor/server/http/content/CompressedFileType;", "getCompressedKey", "()Lio/ktor/util/AttributeKey;", "staticContentEncodedTypes", "Lio/ktor/server/routing/Route;", "getStaticContentEncodedTypes", "(Lio/ktor/server/routing/Route;)Ljava/util/List;", "bestCompressionFit", "Lio/ktor/server/http/content/CompressedResource;", "call", "Lio/ktor/server/application/ApplicationCall;", "resource", "", "packageName", "acceptEncoding", "Lio/ktor/http/HeaderValue;", "compressedTypes", "contentType", "Lkotlin/Function1;", "Ljava/net/URL;", "Lio/ktor/http/ContentType;", "file", "Ljava/io/File;", "respondStaticFile", "", "requestedFile", "cacheControl", "Lio/ktor/http/CacheControl;", "modify", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/io/File;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondStaticResource", "requestedResource", "modifier", "exclude", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
public final class PreCompressedKt {
    private static final AttributeKey<List<CompressedFileType>> compressedKey = new AttributeKey<>("StaticContentCompressed");

    public static final AttributeKey<List<CompressedFileType>> getCompressedKey() {
        return compressedKey;
    }

    public static final List<CompressedFileType> getStaticContentEncodedTypes(Route route) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        List<CompressedFileType> list = (List) route.getAttributes().getOrNull(compressedKey);
        if (list != null) {
            return list;
        }
        Route parent = route.getParent();
        if (parent != null) {
            return getStaticContentEncodedTypes(parent);
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: io.ktor.server.http.content.CompressedFileType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: io.ktor.server.http.content.CompressedFileType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: io.ktor.server.http.content.CompressedFileType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: io.ktor.server.http.content.CompressedFileType} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.server.http.content.CompressedFileType bestCompressionFit(java.io.File r4, java.util.List<io.ktor.http.HeaderValue> r5, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r6) {
        /*
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "acceptEncoding"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r1)
            r0.<init>(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r5 = r5.iterator()
        L_0x001d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0031
            java.lang.Object r1 = r5.next()
            io.ktor.http.HeaderValue r1 = (io.ktor.http.HeaderValue) r1
            java.lang.String r1 = r1.getValue()
            r0.add(r1)
            goto L_0x001d
        L_0x0031:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Set r5 = kotlin.collections.CollectionsKt.toSet(r0)
            r0 = 0
            if (r6 == 0) goto L_0x0086
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r6 = r6.iterator()
        L_0x0049:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L_0x0064
            java.lang.Object r2 = r6.next()
            r3 = r2
            io.ktor.server.http.content.CompressedFileType r3 = (io.ktor.server.http.content.CompressedFileType) r3
            java.lang.String r3 = r3.getEncoding()
            boolean r3 = r5.contains(r3)
            if (r3 == 0) goto L_0x0049
            r1.add(r2)
            goto L_0x0049
        L_0x0064:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r5 = r1.iterator()
        L_0x006c:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0084
            java.lang.Object r6 = r5.next()
            r1 = r6
            io.ktor.server.http.content.CompressedFileType r1 = (io.ktor.server.http.content.CompressedFileType) r1
            java.io.File r1 = r1.file(r4)
            boolean r1 = r1.isFile()
            if (r1 == 0) goto L_0x006c
            r0 = r6
        L_0x0084:
            io.ktor.server.http.content.CompressedFileType r0 = (io.ktor.server.http.content.CompressedFileType) r0
        L_0x0086:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.PreCompressedKt.bestCompressionFit(java.io.File, java.util.List, java.util.List):io.ktor.server.http.content.CompressedFileType");
    }

    public static final CompressedResource bestCompressionFit(ApplicationCall applicationCall, String str, String str2, List<HeaderValue> list, List<? extends CompressedFileType> list2, Function1<? super URL, ContentType> function1) {
        Sequence asSequence;
        Sequence filter;
        Sequence mapNotNull;
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(str, "resource");
        Intrinsics.checkNotNullParameter(list, "acceptEncoding");
        Intrinsics.checkNotNullParameter(function1, CMSAttributeTableGenerator.CONTENT_TYPE);
        Iterable<HeaderValue> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (HeaderValue value : iterable) {
            arrayList.add(value.getValue());
        }
        Set set = CollectionsKt.toSet((List) arrayList);
        if (list2 == null || (asSequence = CollectionsKt.asSequence(list2)) == null || (filter = SequencesKt.filter(asSequence, new PreCompressedKt$bestCompressionFit$3(set))) == null || (mapNotNull = SequencesKt.mapNotNull(filter, new PreCompressedKt$bestCompressionFit$4(str, applicationCall, str2, function1))) == null) {
            return null;
        }
        return (CompressedResource) SequencesKt.firstOrNull(mapNotNull);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0121 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01bc A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondStaticFile(io.ktor.server.application.ApplicationCall r23, java.io.File r24, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r25, kotlin.jvm.functions.Function1<? super java.io.File, io.ktor.http.ContentType> r26, kotlin.jvm.functions.Function1<? super java.io.File, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r27, kotlin.jvm.functions.Function3<? super java.io.File, ? super io.ktor.server.application.ApplicationCall, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            r0 = r23
            r1 = r24
            r2 = r26
            r3 = r28
            r4 = r29
            boolean r5 = r4 instanceof io.ktor.server.http.content.PreCompressedKt$respondStaticFile$1
            if (r5 == 0) goto L_0x001e
            r5 = r4
            io.ktor.server.http.content.PreCompressedKt$respondStaticFile$1 r5 = (io.ktor.server.http.content.PreCompressedKt$respondStaticFile$1) r5
            int r6 = r5.label
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r6 & r7
            if (r6 == 0) goto L_0x001e
            int r4 = r5.label
            int r4 = r4 - r7
            r5.label = r4
            goto L_0x0023
        L_0x001e:
            io.ktor.server.http.content.PreCompressedKt$respondStaticFile$1 r5 = new io.ktor.server.http.content.PreCompressedKt$respondStaticFile$1
            r5.<init>(r4)
        L_0x0023:
            java.lang.Object r4 = r5.result
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r7 = r5.label
            r8 = 4
            r9 = 3
            r10 = 2
            r11 = 1
            r12 = 0
            if (r7 == 0) goto L_0x007e
            if (r7 == r11) goto L_0x0069
            if (r7 == r10) goto L_0x0064
            if (r7 == r9) goto L_0x0047
            if (r7 != r8) goto L_0x003f
            kotlin.ResultKt.throwOnFailure(r4)
            goto L_0x01bd
        L_0x003f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0047:
            java.lang.Object r0 = r5.L$4
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r1 = r5.L$3
            io.ktor.server.http.content.CompressedFileType r1 = (io.ktor.server.http.content.CompressedFileType) r1
            java.lang.Object r2 = r5.L$2
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r3 = r5.L$1
            java.io.File r3 = (java.io.File) r3
            java.lang.Object r7 = r5.L$0
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            kotlin.ResultKt.throwOnFailure(r4)
            r10 = r0
            r4 = r1
            r1 = r3
            r0 = r7
            goto L_0x0167
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r4)
            goto L_0x0122
        L_0x0069:
            java.lang.Object r0 = r5.L$2
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            java.lang.Object r1 = r5.L$1
            java.io.File r1 = (java.io.File) r1
            java.lang.Object r2 = r5.L$0
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            kotlin.ResultKt.throwOnFailure(r4)
            r22 = r2
            r2 = r0
            r0 = r22
            goto L_0x00db
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r4)
            io.ktor.server.request.ApplicationRequest r4 = r23.getRequest()
            java.util.List r4 = io.ktor.server.request.ApplicationRequestPropertiesKt.acceptEncodingItems(r4)
            r7 = r25
            io.ktor.server.http.content.CompressedFileType r4 = bestCompressionFit(r1, r4, r7)
            r7 = r27
            java.lang.Object r7 = r7.invoke(r1)
            r13 = r7
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.lang.String r7 = ", "
            r14 = r7
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            r20 = 62
            r21 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            java.lang.String r7 = kotlin.collections.CollectionsKt.joinToString$default(r13, r14, r15, r16, r17, r18, r19, r20, r21)
            if (r4 != 0) goto L_0x0125
            boolean r4 = r24.isFile()
            if (r4 == 0) goto L_0x0122
            r4 = r7
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x00cc
            io.ktor.server.response.ApplicationResponse r4 = r23.getResponse()
            io.ktor.http.HttpHeaders r8 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r8 = r8.getCacheControl()
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r4, (java.lang.String) r8, (java.lang.String) r7)
        L_0x00cc:
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.label = r11
            java.lang.Object r3 = r3.invoke(r1, r0, r5)
            if (r3 != r6) goto L_0x00db
            return r6
        L_0x00db:
            io.ktor.server.http.content.LocalFileContent r3 = new io.ktor.server.http.content.LocalFileContent
            java.lang.Object r2 = r2.invoke(r1)
            io.ktor.http.ContentType r2 = (io.ktor.http.ContentType) r2
            r3.<init>(r1, r2)
            boolean r1 = r3 instanceof io.ktor.http.content.OutgoingContent
            if (r1 != 0) goto L_0x0109
            boolean r1 = r3 instanceof byte[]
            if (r1 != 0) goto L_0x0109
            io.ktor.server.response.ApplicationResponse r1 = r0.getResponse()
            java.lang.Class<io.ktor.server.http.content.LocalFileContent> r2 = io.ktor.server.http.content.LocalFileContent.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)
            java.lang.Class<io.ktor.server.http.content.LocalFileContent> r7 = io.ktor.server.http.content.LocalFileContent.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r7, r2)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r1, r2)
        L_0x0109:
            io.ktor.server.response.ApplicationResponse r1 = r0.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r1 = r1.getPipeline()
            java.lang.Object r3 = (java.lang.Object) r3
            r5.L$0 = r12
            r5.L$1 = r12
            r5.L$2 = r12
            r5.label = r10
            java.lang.Object r0 = r1.execute(r0, r3, r5)
            if (r0 != r6) goto L_0x0122
            return r6
        L_0x0122:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0125:
            io.ktor.util.Attributes r10 = r23.getAttributes()
            io.ktor.util.AttributeKey r13 = io.ktor.server.http.content.SuppressionAttributeKt.getSuppressionAttribute()
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            r10.put(r13, r11)
            java.io.File r10 = r4.file(r1)
            boolean r11 = r10.isFile()
            if (r11 == 0) goto L_0x01c0
            r11 = r7
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            int r11 = r11.length()
            if (r11 <= 0) goto L_0x0154
            io.ktor.server.response.ApplicationResponse r11 = r23.getResponse()
            io.ktor.http.HttpHeaders r13 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r13 = r13.getCacheControl()
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r11, (java.lang.String) r13, (java.lang.String) r7)
        L_0x0154:
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.L$3 = r4
            r5.L$4 = r10
            r5.label = r9
            java.lang.Object r3 = r3.invoke(r1, r0, r5)
            if (r3 != r6) goto L_0x0167
            return r6
        L_0x0167:
            io.ktor.server.http.content.LocalFileContent r3 = new io.ktor.server.http.content.LocalFileContent
            java.lang.Object r1 = r2.invoke(r1)
            io.ktor.http.ContentType r1 = (io.ktor.http.ContentType) r1
            r3.<init>(r10, r1)
            io.ktor.server.http.content.PreCompressedResponse r1 = new io.ktor.server.http.content.PreCompressedResponse
            io.ktor.http.content.OutgoingContent$ReadChannelContent r3 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r3
            java.lang.String r2 = r4.getEncoding()
            r1.<init>(r3, r2)
            boolean r2 = r1 instanceof io.ktor.http.content.OutgoingContent
            if (r2 != 0) goto L_0x01a0
            boolean r2 = r1 instanceof byte[]
            if (r2 != 0) goto L_0x01a0
            io.ktor.server.response.ApplicationResponse r2 = r0.getResponse()
            java.lang.Class<io.ktor.server.http.content.PreCompressedResponse> r3 = io.ktor.server.http.content.PreCompressedResponse.class
            kotlin.reflect.KType r3 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r3)
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r3)
            java.lang.Class<io.ktor.server.http.content.PreCompressedResponse> r7 = io.ktor.server.http.content.PreCompressedResponse.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            io.ktor.util.reflect.TypeInfo r3 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r7, r3)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r2, r3)
        L_0x01a0:
            io.ktor.server.response.ApplicationResponse r2 = r0.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r2 = r2.getPipeline()
            java.lang.Object r1 = (java.lang.Object) r1
            r5.L$0 = r12
            r5.L$1 = r12
            r5.L$2 = r12
            r5.L$3 = r12
            r5.L$4 = r12
            r5.label = r8
            java.lang.Object r0 = r2.execute(r0, r1, r5)
            if (r0 != r6) goto L_0x01bd
            return r6
        L_0x01bd:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01c0:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.PreCompressedKt.respondStaticFile(io.ktor.server.application.ApplicationCall, java.io.File, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object respondStaticFile$default(ApplicationCall applicationCall, File file, List list, Function1 function1, Function1 function12, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = PreCompressedKt$respondStaticFile$2.INSTANCE;
        }
        Function1 function13 = function1;
        if ((i & 8) != 0) {
            function12 = PreCompressedKt$respondStaticFile$3.INSTANCE;
        }
        Function1 function14 = function12;
        if ((i & 16) != 0) {
            function3 = new PreCompressedKt$respondStaticFile$4((Continuation<? super PreCompressedKt$respondStaticFile$4>) null);
        }
        return respondStaticFile(applicationCall, file, list, function13, function14, function3, continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d6, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x012f, code lost:
        r0 = new io.ktor.server.http.content.PreCompressedResponse(r1.getContent(), r1.getCompression().getEncoding());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0142, code lost:
        if ((r0 instanceof io.ktor.http.content.OutgoingContent) != false) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0146, code lost:
        if ((r0 instanceof byte[]) != false) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0148, code lost:
        r1 = r2.getResponse();
        r3 = kotlin.jvm.internal.Reflection.typeOf(io.ktor.server.http.content.PreCompressedResponse.class);
        io.ktor.server.response.ResponseTypeKt.setResponseType(r1, io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(kotlin.reflect.TypesJVMKt.getJavaType(r3), kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(io.ktor.server.http.content.PreCompressedResponse.class), r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0163, code lost:
        r10.L$0 = null;
        r10.L$1 = null;
        r10.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0178, code lost:
        if (r2.getResponse().getPipeline().execute(r2, r0, r10) != r11) goto L_0x017b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x017a, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x017d, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01e6, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0230, code lost:
        r0 = r1.getSecond();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0236, code lost:
        if ((r0 instanceof io.ktor.http.content.OutgoingContent) != false) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x023a, code lost:
        if ((r0 instanceof byte[]) != false) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x023c, code lost:
        r1 = r2.getResponse();
        r3 = kotlin.jvm.internal.Reflection.typeOf(io.ktor.http.content.OutgoingContent.ReadChannelContent.class);
        io.ktor.server.response.ResponseTypeKt.setResponseType(r1, io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(kotlin.reflect.TypesJVMKt.getJavaType(r3), kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(io.ktor.http.content.OutgoingContent.ReadChannelContent.class), r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0257, code lost:
        r1 = r2.getResponse().getPipeline();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0, "null cannot be cast to non-null type kotlin.Any");
        r10.L$0 = null;
        r10.L$1 = null;
        r10.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x026d, code lost:
        if (r1.execute(r2, r0, r10) != r11) goto L_0x0270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x026f, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0272, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondStaticResource(io.ktor.server.application.ApplicationCall r23, java.lang.String r24, java.lang.String r25, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r26, kotlin.jvm.functions.Function1<? super java.net.URL, io.ktor.http.ContentType> r27, kotlin.jvm.functions.Function1<? super java.net.URL, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r28, kotlin.jvm.functions.Function3<? super java.net.URL, ? super io.ktor.server.application.ApplicationCall, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r29, kotlin.jvm.functions.Function1<? super java.net.URL, java.lang.Boolean> r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            r6 = r23
            r7 = r28
            r8 = r29
            r9 = r30
            r0 = r31
            boolean r1 = r0 instanceof io.ktor.server.http.content.PreCompressedKt$respondStaticResource$1
            if (r1 == 0) goto L_0x001e
            r1 = r0
            io.ktor.server.http.content.PreCompressedKt$respondStaticResource$1 r1 = (io.ktor.server.http.content.PreCompressedKt$respondStaticResource$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x001e
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x0023
        L_0x001e:
            io.ktor.server.http.content.PreCompressedKt$respondStaticResource$1 r1 = new io.ktor.server.http.content.PreCompressedKt$respondStaticResource$1
            r1.<init>(r0)
        L_0x0023:
            r10 = r1
            java.lang.Object r0 = r10.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            java.lang.String r12 = "null cannot be cast to non-null type kotlin.Any"
            r13 = 0
            switch(r1) {
                case 0: goto L_0x0067;
                case 1: goto L_0x0063;
                case 2: goto L_0x0056;
                case 3: goto L_0x0051;
                case 4: goto L_0x004c;
                case 5: goto L_0x003f;
                case 6: goto L_0x003a;
                default: goto L_0x0032;
            }
        L_0x0032:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0270
        L_0x003f:
            java.lang.Object r1 = r10.L$1
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r2 = r10.L$0
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0230
        L_0x004c:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x01e4
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x017b
        L_0x0056:
            java.lang.Object r1 = r10.L$1
            io.ktor.server.http.content.CompressedResource r1 = (io.ktor.server.http.content.CompressedResource) r1
            java.lang.Object r2 = r10.L$0
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x012f
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00d4
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.server.request.ApplicationRequest r0 = r23.getRequest()
            java.util.List r3 = io.ktor.server.request.ApplicationRequestPropertiesKt.acceptEncodingItems(r0)
            r0 = r23
            r1 = r24
            r2 = r25
            r4 = r26
            r5 = r27
            io.ktor.server.http.content.CompressedResource r1 = bestCompressionFit(r0, r1, r2, r3, r4, r5)
            java.lang.String r0 = ", "
            if (r1 == 0) goto L_0x017e
            java.net.URL r2 = r1.getUrl()
            java.lang.Object r2 = r9.invoke(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r3 = 1
            if (r2 == 0) goto L_0x00d7
            io.ktor.http.HttpStatusCode$Companion r0 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r0 = r0.getForbidden()
            boolean r1 = r0 instanceof io.ktor.http.content.OutgoingContent
            if (r1 != 0) goto L_0x00be
            boolean r1 = r0 instanceof byte[]
            if (r1 != 0) goto L_0x00be
            io.ktor.server.response.ApplicationResponse r1 = r23.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r2 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)
            java.lang.Class<io.ktor.http.HttpStatusCode> r5 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r5, r2)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r1, r2)
        L_0x00be:
            io.ktor.server.response.ApplicationResponse r1 = r23.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r1 = r1.getPipeline()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r12)
            java.lang.Object r0 = (java.lang.Object) r0
            r10.label = r3
            java.lang.Object r0 = r1.execute(r6, r0, r10)
            if (r0 != r11) goto L_0x00d4
            return r11
        L_0x00d4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00d7:
            io.ktor.util.Attributes r2 = r23.getAttributes()
            io.ktor.util.AttributeKey r4 = io.ktor.server.http.content.SuppressionAttributeKt.getSuppressionAttribute()
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r2.put(r4, r3)
            java.net.URL r2 = r1.getUrl()
            java.lang.Object r2 = r7.invoke(r2)
            r14 = r2
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            r15 = r0
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            r21 = 62
            r22 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x011c
            io.ktor.server.response.ApplicationResponse r2 = r23.getResponse()
            io.ktor.http.HttpHeaders r3 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r3 = r3.getCacheControl()
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r2, (java.lang.String) r3, (java.lang.String) r0)
        L_0x011c:
            java.net.URL r0 = r1.getUrl()
            r10.L$0 = r6
            r10.L$1 = r1
            r2 = 2
            r10.label = r2
            java.lang.Object r0 = r8.invoke(r0, r6, r10)
            if (r0 != r11) goto L_0x012e
            return r11
        L_0x012e:
            r2 = r6
        L_0x012f:
            io.ktor.server.http.content.PreCompressedResponse r0 = new io.ktor.server.http.content.PreCompressedResponse
            io.ktor.http.content.OutgoingContent$ReadChannelContent r3 = r1.getContent()
            io.ktor.server.http.content.CompressedFileType r1 = r1.getCompression()
            java.lang.String r1 = r1.getEncoding()
            r0.<init>(r3, r1)
            boolean r1 = r0 instanceof io.ktor.http.content.OutgoingContent
            if (r1 != 0) goto L_0x0163
            boolean r1 = r0 instanceof byte[]
            if (r1 != 0) goto L_0x0163
            io.ktor.server.response.ApplicationResponse r1 = r2.getResponse()
            java.lang.Class<io.ktor.server.http.content.PreCompressedResponse> r3 = io.ktor.server.http.content.PreCompressedResponse.class
            kotlin.reflect.KType r3 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r3)
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r3)
            java.lang.Class<io.ktor.server.http.content.PreCompressedResponse> r5 = io.ktor.server.http.content.PreCompressedResponse.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            io.ktor.util.reflect.TypeInfo r3 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r5, r3)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r1, r3)
        L_0x0163:
            io.ktor.server.response.ApplicationResponse r1 = r2.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r1 = r1.getPipeline()
            java.lang.Object r0 = (java.lang.Object) r0
            r10.L$0 = r13
            r10.L$1 = r13
            r3 = 3
            r10.label = r3
            java.lang.Object r0 = r1.execute(r2, r0, r10)
            if (r0 != r11) goto L_0x017b
            return r11
        L_0x017b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x017e:
            io.ktor.server.application.Application r14 = r23.getApplication()
            r19 = 4
            r20 = 0
            r17 = 0
            r15 = r24
            r16 = r25
            r18 = r27
            kotlin.Pair r1 = io.ktor.server.http.content.StaticContentResolutionKt.resolveResource$default((io.ktor.server.application.Application) r14, (java.lang.String) r15, (java.lang.String) r16, (java.lang.ClassLoader) r17, (kotlin.jvm.functions.Function1) r18, (int) r19, (java.lang.Object) r20)
            if (r1 == 0) goto L_0x0273
            java.lang.Object r2 = r1.getFirst()
            java.lang.Object r2 = r9.invoke(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x01e7
            io.ktor.http.HttpStatusCode$Companion r0 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r0 = r0.getForbidden()
            boolean r1 = r0 instanceof io.ktor.http.content.OutgoingContent
            if (r1 != 0) goto L_0x01cd
            boolean r1 = r0 instanceof byte[]
            if (r1 != 0) goto L_0x01cd
            io.ktor.server.response.ApplicationResponse r1 = r23.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r2 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)
            java.lang.reflect.Type r3 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)
            java.lang.Class<io.ktor.http.HttpStatusCode> r4 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r3, r4, r2)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r1, r2)
        L_0x01cd:
            io.ktor.server.response.ApplicationResponse r1 = r23.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r1 = r1.getPipeline()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r12)
            java.lang.Object r0 = (java.lang.Object) r0
            r2 = 4
            r10.label = r2
            java.lang.Object r0 = r1.execute(r6, r0, r10)
            if (r0 != r11) goto L_0x01e4
            return r11
        L_0x01e4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01e7:
            java.lang.Object r2 = r1.getFirst()
            java.lang.Object r2 = r7.invoke(r2)
            r14 = r2
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            r15 = r0
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            r21 = 62
            r22 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x021d
            io.ktor.server.response.ApplicationResponse r2 = r23.getResponse()
            io.ktor.http.HttpHeaders r3 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r3 = r3.getCacheControl()
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r2, (java.lang.String) r3, (java.lang.String) r0)
        L_0x021d:
            java.lang.Object r0 = r1.getFirst()
            r10.L$0 = r6
            r10.L$1 = r1
            r2 = 5
            r10.label = r2
            java.lang.Object r0 = r8.invoke(r0, r6, r10)
            if (r0 != r11) goto L_0x022f
            return r11
        L_0x022f:
            r2 = r6
        L_0x0230:
            java.lang.Object r0 = r1.getSecond()
            boolean r1 = r0 instanceof io.ktor.http.content.OutgoingContent
            if (r1 != 0) goto L_0x0257
            boolean r1 = r0 instanceof byte[]
            if (r1 != 0) goto L_0x0257
            io.ktor.server.response.ApplicationResponse r1 = r2.getResponse()
            java.lang.Class<io.ktor.http.content.OutgoingContent$ReadChannelContent> r3 = io.ktor.http.content.OutgoingContent.ReadChannelContent.class
            kotlin.reflect.KType r3 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r3)
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r3)
            java.lang.Class<io.ktor.http.content.OutgoingContent$ReadChannelContent> r5 = io.ktor.http.content.OutgoingContent.ReadChannelContent.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            io.ktor.util.reflect.TypeInfo r3 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r5, r3)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r1, r3)
        L_0x0257:
            io.ktor.server.response.ApplicationResponse r1 = r2.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r1 = r1.getPipeline()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r12)
            r10.L$0 = r13
            r10.L$1 = r13
            r3 = 6
            r10.label = r3
            java.lang.Object r0 = r1.execute(r2, r0, r10)
            if (r0 != r11) goto L_0x0270
            return r11
        L_0x0270:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0273:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.PreCompressedKt.respondStaticResource(io.ktor.server.application.ApplicationCall, java.lang.String, java.lang.String, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object respondStaticResource$default(ApplicationCall applicationCall, String str, String str2, List list, Function1 function1, Function1 function12, Function3 function3, Function1 function13, Continuation continuation, int i, Object obj) {
        Function1 function14;
        Function1 function15 = (i & 8) != 0 ? PreCompressedKt$respondStaticResource$2.INSTANCE : function1;
        Function1 function16 = (i & 16) != 0 ? PreCompressedKt$respondStaticResource$3.INSTANCE : function12;
        Function3 preCompressedKt$respondStaticResource$4 = (i & 32) != 0 ? new PreCompressedKt$respondStaticResource$4((Continuation<? super PreCompressedKt$respondStaticResource$4>) null) : function3;
        if ((i & 64) != 0) {
            function14 = PreCompressedKt$respondStaticResource$5.INSTANCE;
        } else {
            function14 = function13;
        }
        return respondStaticResource(applicationCall, str, str2, list, function15, function16, preCompressedKt$respondStaticResource$4, function14, continuation);
    }
}
