package io.ktor.server.plugins.partialcontent;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.WriterScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.LongRange;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.partialcontent.MultipleRangeWriterKt$writeMultipleRangesImpl$1", f = "MultipleRangeWriter.kt", i = {0, 0, 1, 2, 3}, l = {27, 28, 29, 32, 33}, m = "invokeSuspend", n = {"$this$writer", "current", "$this$writer", "$this$writer", "$this$writer"}, s = {"L$0", "L$2", "L$0", "L$0", "L$0"})
/* compiled from: MultipleRangeWriter.kt */
final class MultipleRangeWriterKt$writeMultipleRangesImpl$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $boundary;
    final /* synthetic */ Function1<LongRange, ByteReadChannel> $channelProducer;
    final /* synthetic */ String $contentType;
    final /* synthetic */ Long $fullLength;
    final /* synthetic */ List<LongRange> $ranges;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipleRangeWriterKt$writeMultipleRangesImpl$1(List<LongRange> list, Function1<? super LongRange, ? extends ByteReadChannel> function1, String str, String str2, Long l, Continuation<? super MultipleRangeWriterKt$writeMultipleRangesImpl$1> continuation) {
        super(2, continuation);
        this.$ranges = list;
        this.$channelProducer = function1;
        this.$boundary = str;
        this.$contentType = str2;
        this.$fullLength = l;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MultipleRangeWriterKt$writeMultipleRangesImpl$1 multipleRangeWriterKt$writeMultipleRangesImpl$1 = new MultipleRangeWriterKt$writeMultipleRangesImpl$1(this.$ranges, this.$channelProducer, this.$boundary, this.$contentType, this.$fullLength, continuation);
        multipleRangeWriterKt$writeMultipleRangesImpl$1.L$0 = obj;
        return multipleRangeWriterKt$writeMultipleRangesImpl$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((MultipleRangeWriterKt$writeMultipleRangesImpl$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0102 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0118 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 0
            if (r2 == 0) goto L_0x005d
            if (r2 == r7) goto L_0x004d
            if (r2 == r6) goto L_0x0041
            if (r2 == r5) goto L_0x0030
            if (r2 == r4) goto L_0x0027
            if (r2 != r3) goto L_0x001f
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x0119
        L_0x001f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0027:
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x0103
        L_0x0030:
            java.lang.Object r2 = r0.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.WriterScope r9 = (io.ktor.utils.io.WriterScope) r9
            kotlin.ResultKt.throwOnFailure(r19)
        L_0x003b:
            r17 = r9
            r9 = r2
            r2 = r17
            goto L_0x006a
        L_0x0041:
            java.lang.Object r2 = r0.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.WriterScope r9 = (io.ktor.utils.io.WriterScope) r9
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x00b9
        L_0x004d:
            java.lang.Object r2 = r0.L$2
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            java.lang.Object r9 = r0.L$1
            java.util.Iterator r9 = (java.util.Iterator) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.WriterScope r10 = (io.ktor.utils.io.WriterScope) r10
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x00a1
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r19)
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            java.util.List<kotlin.ranges.LongRange> r9 = r0.$ranges
            java.util.Iterator r9 = r9.iterator()
        L_0x006a:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00d1
            java.lang.Object r10 = r9.next()
            r12 = r10
            kotlin.ranges.LongRange r12 = (kotlin.ranges.LongRange) r12
            kotlin.jvm.functions.Function1<kotlin.ranges.LongRange, io.ktor.utils.io.ByteReadChannel> r10 = r0.$channelProducer
            java.lang.Object r10 = r10.invoke(r12)
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            io.ktor.utils.io.ByteWriteChannel r11 = r2.getChannel()
            java.lang.String r13 = r0.$boundary
            java.lang.String r14 = r0.$contentType
            java.lang.Long r15 = r0.$fullLength
            r16 = r0
            kotlin.coroutines.Continuation r16 = (kotlin.coroutines.Continuation) r16
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r7
            java.lang.Object r11 = io.ktor.server.plugins.partialcontent.MultipleRangeWriterKt.writeHeaders(r11, r12, r13, r14, r15, r16)
            if (r11 != r1) goto L_0x009c
            return r1
        L_0x009c:
            r17 = r10
            r10 = r2
            r2 = r17
        L_0x00a1:
            io.ktor.utils.io.ByteWriteChannel r11 = r10.getChannel()
            r12 = r0
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r0.L$0 = r10
            r0.L$1 = r9
            r0.L$2 = r8
            r0.label = r6
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannelKt.copyTo(r2, r11, r12)
            if (r2 != r1) goto L_0x00b7
            return r1
        L_0x00b7:
            r2 = r9
            r9 = r10
        L_0x00b9:
            io.ktor.utils.io.ByteWriteChannel r10 = r9.getChannel()
            byte[] r11 = io.ktor.server.plugins.partialcontent.MultipleRangeWriterKt.NEWLINE
            r12 = r0
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r0.L$0 = r9
            r0.L$1 = r2
            r0.label = r5
            java.lang.Object r10 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r10, r11, r12)
            if (r10 != r1) goto L_0x003b
            return r1
        L_0x00d1:
            io.ktor.utils.io.ByteWriteChannel r5 = r2.getChannel()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "--"
            r6.<init>(r7)
            java.lang.String r9 = r0.$boundary
            r6.append(r9)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.nio.charset.Charset r7 = kotlin.text.Charsets.ISO_8859_1
            byte[] r6 = r6.getBytes(r7)
            java.lang.String r7 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r7 = r0
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r0.L$0 = r2
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r4 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r5, r6, r7)
            if (r4 != r1) goto L_0x0103
            return r1
        L_0x0103:
            io.ktor.utils.io.ByteWriteChannel r2 = r2.getChannel()
            byte[] r4 = io.ktor.server.plugins.partialcontent.MultipleRangeWriterKt.NEWLINE
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r2 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r2, r4, r5)
            if (r2 != r1) goto L_0x0119
            return r1
        L_0x0119:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.partialcontent.MultipleRangeWriterKt$writeMultipleRangesImpl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
