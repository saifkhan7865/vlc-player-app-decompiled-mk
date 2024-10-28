package io.ktor.server.plugins.partialcontent;

import io.ktor.http.ContentRangeKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.RangeUnits;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\f\u001a5\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u0010\u001a7\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001aM\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u00192\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"FIXED_HEADERS_PART_LENGTH", "", "NEWLINE", "", "calculateHeadersLength", "range", "Lkotlin/ranges/LongRange;", "boundary", "", "contentType", "fullLength", "", "(Lkotlin/ranges/LongRange;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)I", "calculateMultipleRangesBodyLength", "ranges", "", "(Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)J", "writeHeaders", "", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/ranges/LongRange;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeMultipleRangesImpl", "Lio/ktor/utils/io/ByteReadChannel;", "Lkotlinx/coroutines/CoroutineScope;", "channelProducer", "Lkotlin/Function1;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-partial-content"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MultipleRangeWriter.kt */
public final class MultipleRangeWriterKt {
    private static final int FIXED_HEADERS_PART_LENGTH = ((HttpHeaders.INSTANCE.getContentType().length() + 14) + HttpHeaders.INSTANCE.getContentRange().length());
    /* access modifiers changed from: private */
    public static final byte[] NEWLINE;

    static {
        byte[] bytes = "\r\n".getBytes(Charsets.ISO_8859_1);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        NEWLINE = bytes;
    }

    public static final ByteReadChannel writeMultipleRangesImpl(CoroutineScope coroutineScope, Function1<? super LongRange, ? extends ByteReadChannel> function1, List<LongRange> list, Long l, String str, String str2) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(function1, "channelProducer");
        Intrinsics.checkNotNullParameter(list, "ranges");
        Intrinsics.checkNotNullParameter(str, HttpHeaders.Values.BOUNDARY);
        Intrinsics.checkNotNullParameter(str2, CMSAttributeTableGenerator.CONTENT_TYPE);
        return CoroutinesKt.writer(coroutineScope, (CoroutineContext) Dispatchers.getUnconfined(), true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new MultipleRangeWriterKt$writeMultipleRangesImpl$1(list, function1, str, str2, l, (Continuation<? super MultipleRangeWriterKt$writeMultipleRangesImpl$1>) null)).getChannel();
    }

    /* access modifiers changed from: private */
    public static final Object writeHeaders(ByteWriteChannel byteWriteChannel, LongRange longRange, String str, String str2, Long l, Continuation<? super Unit> continuation) {
        String contentRangeHeaderValue = ContentRangeKt.contentRangeHeaderValue(longRange, l, RangeUnits.Bytes);
        StringBuilder sb = new StringBuilder(str.length() + str2.length() + contentRangeHeaderValue.length() + FIXED_HEADERS_PART_LENGTH);
        sb.append("--");
        sb.append(str);
        sb.append("\r\n");
        sb.append(io.ktor.http.HttpHeaders.INSTANCE.getContentType());
        sb.append(": ");
        sb.append(str2);
        sb.append("\r\n");
        sb.append(io.ktor.http.HttpHeaders.INSTANCE.getContentRange());
        sb.append(": ");
        sb.append(contentRangeHeaderValue);
        sb.append("\r\n\r\n");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        byte[] bytes = sb2.getBytes(Charsets.ISO_8859_1);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        Object writeFully = ByteWriteChannelKt.writeFully(byteWriteChannel, bytes, continuation);
        return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
    }

    public static final long calculateMultipleRangesBodyLength(List<LongRange> list, Long l, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "ranges");
        Intrinsics.checkNotNullParameter(str, HttpHeaders.Values.BOUNDARY);
        Intrinsics.checkNotNullParameter(str2, CMSAttributeTableGenerator.CONTENT_TYPE);
        long j = 0;
        for (LongRange longRange : list) {
            j += ((((long) calculateHeadersLength(longRange, str, str2, l)) + longRange.getLast()) - longRange.getFirst()) + 3;
        }
        return j + ((long) str.length()) + ((long) 6);
    }

    private static final int calculateHeadersLength(LongRange longRange, String str, String str2, Long l) {
        return str.length() + str2.length() + ContentRangeKt.contentRangeHeaderValue(longRange, l, RangeUnits.Bytes).length() + FIXED_HEADERS_PART_LENGTH;
    }
}
