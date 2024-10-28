package io.ktor.serialization;

import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001c\u0010\f\u001a\u00060\tj\u0002`\n*\u00020\r2\f\b\u0002\u0010\u000e\u001a\u00060\tj\u0002`\n\u001a \u0010\u000f\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n*\u00020\r2\f\b\u0002\u0010\u000e\u001a\u00060\tj\u0002`\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"deserialize", "", "", "Lio/ktor/serialization/ContentConverter;", "body", "Lio/ktor/utils/io/ByteReadChannel;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Ljava/util/List;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/util/reflect/TypeInfo;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suitableCharset", "Lio/ktor/http/Headers;", "defaultCharset", "suitableCharsetOrNull", "ktor-serialization"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ContentConverter.kt */
public final class ContentConverterKt {
    public static /* synthetic */ Charset suitableCharset$default(Headers headers, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return suitableCharset(headers, charset);
    }

    public static final Charset suitableCharset(Headers headers, Charset charset) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        Intrinsics.checkNotNullParameter(charset, "defaultCharset");
        Charset suitableCharsetOrNull = suitableCharsetOrNull(headers, charset);
        return suitableCharsetOrNull == null ? charset : suitableCharsetOrNull;
    }

    public static /* synthetic */ Charset suitableCharsetOrNull$default(Headers headers, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return suitableCharsetOrNull(headers, charset);
    }

    public static final Charset suitableCharsetOrNull(Headers headers, Charset charset) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        Intrinsics.checkNotNullParameter(charset, "defaultCharset");
        for (HeaderValue component1 : HttpHeaderValueParserKt.parseAndSortHeader(headers.get(HttpHeaders.INSTANCE.getAcceptCharset()))) {
            String component12 = component1.component1();
            if (Intrinsics.areEqual((Object) component12, (Object) "*")) {
                return charset;
            }
            if (Charset.isSupported(component12)) {
                return Charset.forName(component12);
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: io.ktor.util.reflect.TypeInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: io.ktor.utils.io.ByteReadChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @io.ktor.util.InternalAPI
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object deserialize(java.util.List<? extends io.ktor.serialization.ContentConverter> r5, io.ktor.utils.io.ByteReadChannel r6, io.ktor.util.reflect.TypeInfo r7, java.nio.charset.Charset r8, kotlin.coroutines.Continuation<java.lang.Object> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.serialization.ContentConverterKt$deserialize$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.serialization.ContentConverterKt$deserialize$1 r0 = (io.ktor.serialization.ContentConverterKt$deserialize$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.serialization.ContentConverterKt$deserialize$1 r0 = new io.ktor.serialization.ContentConverterKt$deserialize$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 != r4) goto L_0x0035
            java.lang.Object r5 = r0.L$1
            r7 = r5
            io.ktor.util.reflect.TypeInfo r7 = (io.ktor.util.reflect.TypeInfo) r7
            java.lang.Object r5 = r0.L$0
            r6 = r5
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0061
        L_0x0035:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            kotlinx.coroutines.flow.Flow r5 = kotlinx.coroutines.flow.FlowKt.asFlow(r5)
            io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1 r9 = new io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1
            r9.<init>(r5, r8, r7, r6)
            kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
            io.ktor.serialization.ContentConverterKt$deserialize$result$2 r5 = new io.ktor.serialization.ContentConverterKt$deserialize$result$2
            r5.<init>(r6, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.firstOrNull(r9, r5, r0)
            if (r9 != r1) goto L_0x0061
            return r1
        L_0x0061:
            if (r9 != 0) goto L_0x008e
            boolean r5 = r6.isClosedForRead()
            if (r5 != 0) goto L_0x006a
            goto L_0x008f
        L_0x006a:
            kotlin.reflect.KType r5 = r7.getKotlinType()
            if (r5 == 0) goto L_0x0079
            boolean r5 = r5.isMarkedNullable()
            if (r5 != r4) goto L_0x0079
            io.ktor.http.content.NullBody r6 = io.ktor.http.content.NullBody.INSTANCE
            goto L_0x008f
        L_0x0079:
            io.ktor.serialization.ContentConvertException r5 = new io.ktor.serialization.ContentConvertException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "No suitable converter found for "
            r6.<init>(r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r7 = 2
            r5.<init>(r6, r3, r7, r3)
            throw r5
        L_0x008e:
            r6 = r9
        L_0x008f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.ContentConverterKt.deserialize(java.util.List, io.ktor.utils.io.ByteReadChannel, io.ktor.util.reflect.TypeInfo, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
