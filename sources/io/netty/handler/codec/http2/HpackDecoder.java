package io.netty.handler.codec.http2;

import com.google.common.base.Ascii;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaderValidationUtil;
import io.netty.handler.codec.http2.HpackUtil;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.netty.util.internal.ObjectUtil;

final class HpackDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Http2Exception DECODE_ILLEGAL_INDEX_VALUE;
    private static final Http2Exception DECODE_ULE_128_DECOMPRESSION_EXCEPTION;
    private static final Http2Exception DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION;
    private static final Http2Exception DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION;
    private static final Http2Exception INDEX_HEADER_ILLEGAL_INDEX_VALUE;
    private static final Http2Exception INVALID_MAX_DYNAMIC_TABLE_SIZE;
    private static final Http2Exception MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED;
    private static final byte READ_HEADER_REPRESENTATION = 0;
    private static final byte READ_INDEXED_HEADER = 1;
    private static final byte READ_INDEXED_HEADER_NAME = 2;
    private static final byte READ_LITERAL_HEADER_NAME = 5;
    private static final byte READ_LITERAL_HEADER_NAME_LENGTH = 4;
    private static final byte READ_LITERAL_HEADER_NAME_LENGTH_PREFIX = 3;
    private static final byte READ_LITERAL_HEADER_VALUE = 8;
    private static final byte READ_LITERAL_HEADER_VALUE_LENGTH = 7;
    private static final byte READ_LITERAL_HEADER_VALUE_LENGTH_PREFIX = 6;
    private static final Http2Exception READ_NAME_ILLEGAL_INDEX_VALUE;
    private long encoderMaxDynamicTableSize;
    private final HpackDynamicTable hpackDynamicTable;
    private final HpackHuffmanDecoder huffmanDecoder;
    private long maxDynamicTableSize;
    private boolean maxDynamicTableSizeChangeRequired;
    private long maxHeaderListSize;

    private enum HeaderType {
        REGULAR_HEADER,
        REQUEST_PSEUDO_HEADER,
        RESPONSE_PSEUDO_HEADER
    }

    static {
        Class<HpackDecoder> cls = HpackDecoder.class;
        DECODE_ULE_128_DECOMPRESSION_EXCEPTION = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - decompression failure", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "decodeULE128(..)");
        DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - long overflow", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "decodeULE128(..)");
        DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - int overflow", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "decodeULE128ToInt(..)");
        DECODE_ILLEGAL_INDEX_VALUE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "decode(..)");
        INDEX_HEADER_ILLEGAL_INDEX_VALUE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "indexHeader(..)");
        READ_NAME_ILLEGAL_INDEX_VALUE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "readName(..)");
        INVALID_MAX_DYNAMIC_TABLE_SIZE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - invalid max dynamic table size", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "setDynamicTableSize(..)");
        MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - max dynamic table size change required", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "decode(..)");
    }

    HpackDecoder(long j) {
        this(j, 4096);
    }

    HpackDecoder(long j, int i) {
        this.huffmanDecoder = new HpackHuffmanDecoder();
        this.maxHeaderListSize = ObjectUtil.checkPositive(j, "maxHeaderListSize");
        long j2 = (long) i;
        this.encoderMaxDynamicTableSize = j2;
        this.maxDynamicTableSize = j2;
        this.maxDynamicTableSizeChangeRequired = false;
        this.hpackDynamicTable = new HpackDynamicTable(j2);
    }

    /* access modifiers changed from: package-private */
    public void decode(int i, ByteBuf byteBuf, Http2Headers http2Headers, boolean z) throws Http2Exception {
        Http2HeadersSink http2HeadersSink = new Http2HeadersSink(i, http2Headers, this.maxHeaderListSize, z);
        decodeDynamicTableSizeUpdates(byteBuf);
        decode(byteBuf, http2HeadersSink);
        http2HeadersSink.finish();
    }

    private void decodeDynamicTableSizeUpdates(ByteBuf byteBuf) throws Http2Exception {
        while (byteBuf.isReadable()) {
            byte b = byteBuf.getByte(byteBuf.readerIndex());
            if ((b & 32) == 32 && (b & 192) == 0) {
                byteBuf.readByte();
                byte b2 = b & Ascii.US;
                if (b2 == 31) {
                    setDynamicTableSize(decodeULE128(byteBuf, (long) b2));
                } else {
                    setDynamicTableSize((long) b2);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        r6 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b5, code lost:
        r6 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decode(io.netty.buffer.ByteBuf r17, io.netty.handler.codec.http2.HpackDecoder.Http2HeadersSink r18) throws io.netty.handler.codec.http2.Http2Exception {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            io.netty.handler.codec.http2.HpackUtil$IndexType r3 = io.netty.handler.codec.http2.HpackUtil.IndexType.NONE
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x000f:
            boolean r11 = r17.isReadable()
            if (r11 == 0) goto L_0x0133
            r12 = 5
            r13 = 1
            r14 = 128(0x80, float:1.794E-43)
            r15 = 6
            r11 = 127(0x7f, float:1.78E-43)
            switch(r6) {
                case 0: goto L_0x00b8;
                case 1: goto L_0x00a2;
                case 2: goto L_0x0093;
                case 3: goto L_0x007f;
                case 4: goto L_0x0079;
                case 5: goto L_0x0069;
                case 6: goto L_0x004e;
                case 7: goto L_0x0047;
                case 8: goto L_0x0033;
                default: goto L_0x001f;
            }
        L_0x001f:
            java.lang.Error r1 = new java.lang.Error
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "should not reach here state: "
            r2.<init>(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0033:
            int r6 = r17.readableBytes()
            if (r6 < r9) goto L_0x0042
            io.netty.util.AsciiString r6 = r0.readStringLiteral(r1, r9, r10)
            r0.insertHeader(r2, r5, r6, r3)
            goto L_0x00b5
        L_0x0042:
            java.lang.IllegalArgumentException r1 = notEnoughDataException(r17)
            throw r1
        L_0x0047:
            int r9 = decodeULE128((io.netty.buffer.ByteBuf) r1, (int) r7)
        L_0x004b:
            r6 = 8
            goto L_0x000f
        L_0x004e:
            byte r6 = r17.readByte()
            r7 = r6 & 128(0x80, float:1.794E-43)
            if (r7 != r14) goto L_0x0058
            r10 = 1
            goto L_0x0059
        L_0x0058:
            r10 = 0
        L_0x0059:
            r7 = r6 & 127(0x7f, float:1.78E-43)
            if (r7 == 0) goto L_0x0063
            if (r7 == r11) goto L_0x0061
            r9 = r7
            goto L_0x004b
        L_0x0061:
            r6 = 7
            goto L_0x000f
        L_0x0063:
            io.netty.util.AsciiString r6 = io.netty.util.AsciiString.EMPTY_STRING
            r0.insertHeader(r2, r5, r6, r3)
            goto L_0x00b5
        L_0x0069:
            int r5 = r17.readableBytes()
            if (r5 < r8) goto L_0x0074
            io.netty.util.AsciiString r5 = r0.readStringLiteral(r1, r8, r10)
            goto L_0x009f
        L_0x0074:
            java.lang.IllegalArgumentException r1 = notEnoughDataException(r17)
            throw r1
        L_0x0079:
            int r8 = decodeULE128((io.netty.buffer.ByteBuf) r1, (int) r7)
        L_0x007d:
            r6 = 5
            goto L_0x000f
        L_0x007f:
            byte r6 = r17.readByte()
            r7 = r6 & 128(0x80, float:1.794E-43)
            if (r7 != r14) goto L_0x0089
            r10 = 1
            goto L_0x008a
        L_0x0089:
            r10 = 0
        L_0x008a:
            r7 = r6 & 127(0x7f, float:1.78E-43)
            if (r7 != r11) goto L_0x0091
            r6 = 4
            goto L_0x000f
        L_0x0091:
            r8 = r7
            goto L_0x007d
        L_0x0093:
            int r5 = decodeULE128((io.netty.buffer.ByteBuf) r1, (int) r7)
            io.netty.util.AsciiString r5 = r0.readName(r5)
            int r8 = r5.length()
        L_0x009f:
            r6 = 6
            goto L_0x000f
        L_0x00a2:
            int r6 = decodeULE128((io.netty.buffer.ByteBuf) r1, (int) r7)
            io.netty.handler.codec.http2.HpackHeaderField r6 = r0.getIndexedHeader(r6)
            java.lang.CharSequence r11 = r6.name
            io.netty.util.AsciiString r11 = (io.netty.util.AsciiString) r11
            java.lang.CharSequence r6 = r6.value
            io.netty.util.AsciiString r6 = (io.netty.util.AsciiString) r6
            r2.appendToHeaderList(r11, r6)
        L_0x00b5:
            r6 = 0
            goto L_0x000f
        L_0x00b8:
            byte r7 = r17.readByte()
            boolean r12 = r0.maxDynamicTableSizeChangeRequired
            r14 = 32
            if (r12 == 0) goto L_0x00ca
            r12 = r7 & 224(0xe0, float:3.14E-43)
            if (r12 != r14) goto L_0x00c7
            goto L_0x00ca
        L_0x00c7:
            io.netty.handler.codec.http2.Http2Exception r1 = MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED
            throw r1
        L_0x00ca:
            if (r7 >= 0) goto L_0x00e9
            r7 = r7 & 127(0x7f, float:1.78E-43)
            if (r7 == 0) goto L_0x00e6
            if (r7 == r11) goto L_0x00e3
            io.netty.handler.codec.http2.HpackHeaderField r11 = r0.getIndexedHeader(r7)
            java.lang.CharSequence r12 = r11.name
            io.netty.util.AsciiString r12 = (io.netty.util.AsciiString) r12
            java.lang.CharSequence r11 = r11.value
            io.netty.util.AsciiString r11 = (io.netty.util.AsciiString) r11
            r2.appendToHeaderList(r12, r11)
            goto L_0x000f
        L_0x00e3:
            r6 = 1
            goto L_0x000f
        L_0x00e6:
            io.netty.handler.codec.http2.Http2Exception r1 = DECODE_ILLEGAL_INDEX_VALUE
            throw r1
        L_0x00e9:
            r3 = r7 & 64
            r6 = 2
            r11 = 3
            r12 = 64
            if (r3 != r12) goto L_0x0107
            io.netty.handler.codec.http2.HpackUtil$IndexType r3 = io.netty.handler.codec.http2.HpackUtil.IndexType.INCREMENTAL
            r7 = r7 & 63
            if (r7 == 0) goto L_0x0104
            r11 = 63
            if (r7 == r11) goto L_0x000f
            io.netty.util.AsciiString r5 = r0.readName(r7)
            int r8 = r5.length()
            goto L_0x009f
        L_0x0104:
            r6 = 3
            goto L_0x000f
        L_0x0107:
            r3 = r7 & 32
            if (r3 == r14) goto L_0x0128
            r3 = r7 & 16
            r12 = 16
            if (r3 != r12) goto L_0x0114
            io.netty.handler.codec.http2.HpackUtil$IndexType r3 = io.netty.handler.codec.http2.HpackUtil.IndexType.NEVER
            goto L_0x0116
        L_0x0114:
            io.netty.handler.codec.http2.HpackUtil$IndexType r3 = io.netty.handler.codec.http2.HpackUtil.IndexType.NONE
        L_0x0116:
            r7 = r7 & 15
            if (r7 == 0) goto L_0x0104
            r11 = 15
            if (r7 == r11) goto L_0x000f
            io.netty.util.AsciiString r5 = r0.readName(r7)
            int r8 = r5.length()
            goto L_0x009f
        L_0x0128:
            io.netty.handler.codec.http2.Http2Error r1 = io.netty.handler.codec.http2.Http2Error.COMPRESSION_ERROR
            java.lang.String r2 = "Dynamic table size update must happen at the beginning of the header block"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            io.netty.handler.codec.http2.Http2Exception r1 = io.netty.handler.codec.http2.Http2Exception.connectionError(r1, r2, r3)
            throw r1
        L_0x0133:
            if (r6 != 0) goto L_0x0136
            return
        L_0x0136:
            io.netty.handler.codec.http2.Http2Error r1 = io.netty.handler.codec.http2.Http2Error.COMPRESSION_ERROR
            java.lang.String r2 = "Incomplete header block fragment."
            java.lang.Object[] r3 = new java.lang.Object[r4]
            io.netty.handler.codec.http2.Http2Exception r1 = io.netty.handler.codec.http2.Http2Exception.connectionError(r1, r2, r3)
            goto L_0x0142
        L_0x0141:
            throw r1
        L_0x0142:
            goto L_0x0141
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.HpackDecoder.decode(io.netty.buffer.ByteBuf, io.netty.handler.codec.http2.HpackDecoder$Http2HeadersSink):void");
    }

    /* access modifiers changed from: package-private */
    public void setMaxHeaderTableSize(long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxDynamicTableSize = j;
        if (j < this.encoderMaxDynamicTableSize) {
            this.maxDynamicTableSizeChangeRequired = true;
            this.hpackDynamicTable.setCapacity(j);
        }
    }

    /* access modifiers changed from: package-private */
    public void setMaxHeaderListSize(long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxHeaderListSize = j;
    }

    /* access modifiers changed from: package-private */
    public long getMaxHeaderListSize() {
        return this.maxHeaderListSize;
    }

    /* access modifiers changed from: package-private */
    public long getMaxHeaderTableSize() {
        return this.hpackDynamicTable.capacity();
    }

    /* access modifiers changed from: package-private */
    public int length() {
        return this.hpackDynamicTable.length();
    }

    /* access modifiers changed from: package-private */
    public long size() {
        return this.hpackDynamicTable.size();
    }

    /* access modifiers changed from: package-private */
    public HpackHeaderField getHeaderField(int i) {
        return this.hpackDynamicTable.getEntry(i + 1);
    }

    private void setDynamicTableSize(long j) throws Http2Exception {
        if (j <= this.maxDynamicTableSize) {
            this.encoderMaxDynamicTableSize = j;
            this.maxDynamicTableSizeChangeRequired = false;
            this.hpackDynamicTable.setCapacity(j);
            return;
        }
        throw INVALID_MAX_DYNAMIC_TABLE_SIZE;
    }

    /* access modifiers changed from: private */
    public static HeaderType validateHeader(int i, AsciiString asciiString, CharSequence charSequence, HeaderType headerType) throws Http2Exception {
        if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(asciiString)) {
            if (headerType != HeaderType.REGULAR_HEADER) {
                HeaderType headerType2 = Http2Headers.PseudoHeaderName.getPseudoHeader(asciiString).isRequestOnly() ? HeaderType.REQUEST_PSEUDO_HEADER : HeaderType.RESPONSE_PSEUDO_HEADER;
                if (headerType == null || headerType2 == headerType) {
                    return headerType2;
                }
                throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Mix of request and response pseudo-headers.", new Object[0]);
            }
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Pseudo-header field '%s' found after regular header.", asciiString);
        } else if (HttpHeaderValidationUtil.isConnectionHeader(asciiString, true)) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Illegal connection-specific header '%s' encountered.", asciiString);
        } else if (!HttpHeaderValidationUtil.isTeNotTrailers(asciiString, charSequence)) {
            return HeaderType.REGULAR_HEADER;
        } else {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Illegal value specified for the 'TE' header (only 'trailers' is allowed).", new Object[0]);
        }
    }

    private AsciiString readName(int i) throws Http2Exception {
        if (i <= HpackStaticTable.length) {
            return (AsciiString) HpackStaticTable.getEntry(i).name;
        }
        if (i - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
            return (AsciiString) this.hpackDynamicTable.getEntry(i - HpackStaticTable.length).name;
        }
        throw READ_NAME_ILLEGAL_INDEX_VALUE;
    }

    private HpackHeaderField getIndexedHeader(int i) throws Http2Exception {
        if (i <= HpackStaticTable.length) {
            return HpackStaticTable.getEntry(i);
        }
        if (i - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
            return this.hpackDynamicTable.getEntry(i - HpackStaticTable.length);
        }
        throw INDEX_HEADER_ILLEGAL_INDEX_VALUE;
    }

    private void insertHeader(Http2HeadersSink http2HeadersSink, AsciiString asciiString, AsciiString asciiString2, HpackUtil.IndexType indexType) {
        http2HeadersSink.appendToHeaderList(asciiString, asciiString2);
        int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[indexType.ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3) {
                this.hpackDynamicTable.add(new HpackHeaderField(asciiString, asciiString2));
                return;
            }
            throw new Error("should not reach here");
        }
    }

    /* renamed from: io.netty.handler.codec.http2.HpackDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                io.netty.handler.codec.http2.HpackUtil$IndexType[] r0 = io.netty.handler.codec.http2.HpackUtil.IndexType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType = r0
                io.netty.handler.codec.http2.HpackUtil$IndexType r1 = io.netty.handler.codec.http2.HpackUtil.IndexType.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http2.HpackUtil$IndexType r1 = io.netty.handler.codec.http2.HpackUtil.IndexType.NEVER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http2.HpackUtil$IndexType r1 = io.netty.handler.codec.http2.HpackUtil.IndexType.INCREMENTAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.HpackDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    private AsciiString readStringLiteral(ByteBuf byteBuf, int i, boolean z) throws Http2Exception {
        if (z) {
            return this.huffmanDecoder.decode(byteBuf, i);
        }
        byte[] bArr = new byte[i];
        byteBuf.readBytes(bArr);
        return new AsciiString(bArr, false);
    }

    private static IllegalArgumentException notEnoughDataException(ByteBuf byteBuf) {
        return new IllegalArgumentException("decode only works with an entire header block! " + byteBuf);
    }

    static int decodeULE128(ByteBuf byteBuf, int i) throws Http2Exception {
        int readerIndex = byteBuf.readerIndex();
        long decodeULE128 = decodeULE128(byteBuf, (long) i);
        if (decodeULE128 <= 2147483647L) {
            return (int) decodeULE128;
        }
        byteBuf.readerIndex(readerIndex);
        throw DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION;
    }

    static long decodeULE128(ByteBuf byteBuf, long j) throws Http2Exception {
        int i = 0;
        boolean z = j == 0;
        int writerIndex = byteBuf.writerIndex();
        int readerIndex = byteBuf.readerIndex();
        while (readerIndex < writerIndex) {
            byte b = byteBuf.getByte(readerIndex);
            if (i == 56 && ((b & 128) != 0 || (b == Byte.MAX_VALUE && !z))) {
                throw DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION;
            } else if ((b & 128) == 0) {
                byteBuf.readerIndex(readerIndex + 1);
                return j + ((((long) b) & 127) << i);
            } else {
                j += (((long) b) & 127) << i;
                readerIndex++;
                i += 7;
            }
        }
        throw DECODE_ULE_128_DECOMPRESSION_EXCEPTION;
    }

    private static final class Http2HeadersSink {
        private boolean exceededMaxLength;
        private final Http2Headers headers;
        private long headersLength;
        private final long maxHeaderListSize;
        private HeaderType previousType;
        private final int streamId;
        private final boolean validateHeaders;
        private Http2Exception validationException;

        Http2HeadersSink(int i, Http2Headers http2Headers, long j, boolean z) {
            this.headers = http2Headers;
            this.maxHeaderListSize = j;
            this.streamId = i;
            this.validateHeaders = z;
        }

        /* access modifiers changed from: package-private */
        public void finish() throws Http2Exception {
            if (this.exceededMaxLength) {
                Http2CodecUtil.headerListSizeExceeded(this.streamId, this.maxHeaderListSize, true);
                return;
            }
            Http2Exception http2Exception = this.validationException;
            if (http2Exception != null) {
                throw http2Exception;
            }
        }

        /* access modifiers changed from: package-private */
        public void appendToHeaderList(AsciiString asciiString, AsciiString asciiString2) {
            long sizeOf = this.headersLength + HpackHeaderField.sizeOf(asciiString, asciiString2);
            this.headersLength = sizeOf;
            boolean z = (sizeOf > this.maxHeaderListSize) | this.exceededMaxLength;
            this.exceededMaxLength = z;
            if (!z && this.validationException == null) {
                try {
                    this.headers.add(asciiString, asciiString2);
                    if (this.validateHeaders) {
                        this.previousType = HpackDecoder.validateHeader(this.streamId, asciiString, asciiString2, this.previousType);
                    }
                } catch (IllegalArgumentException e) {
                    this.validationException = Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, e, "Validation failed for header '%s': %s", asciiString, e.getMessage());
                } catch (Http2Exception e2) {
                    this.validationException = Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, e2, e2.getMessage(), new Object[0]);
                }
            }
        }
    }
}
