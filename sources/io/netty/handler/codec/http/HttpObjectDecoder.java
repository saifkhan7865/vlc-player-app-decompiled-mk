package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.PrematureChannelClosureException;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class HttpObjectDecoder extends ByteToMessageDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final boolean DEFAULT_ALLOW_DUPLICATE_CONTENT_LENGTHS = false;
    public static final boolean DEFAULT_ALLOW_PARTIAL_CHUNKS = true;
    public static final boolean DEFAULT_CHUNKED_SUPPORTED = true;
    public static final int DEFAULT_INITIAL_BUFFER_SIZE = 128;
    public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
    public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
    public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
    public static final boolean DEFAULT_VALIDATE_HEADERS = true;
    /* access modifiers changed from: private */
    public static final boolean[] ISO_CONTROL_OR_WHITESPACE = new boolean[256];
    private static final boolean[] LATIN_WHITESPACE = new boolean[256];
    /* access modifiers changed from: private */
    public static final ByteProcessor SKIP_CONTROL_CHARS_BYTES = new ByteProcessor() {
        public boolean process(byte b) {
            return HttpObjectDecoder.ISO_CONTROL_OR_WHITESPACE[b + 128];
        }
    };
    private static final boolean[] SP_LENIENT_BYTES;
    private final boolean allowDuplicateContentLengths;
    private final boolean allowPartialChunks;
    private long chunkSize;
    private final boolean chunkedSupported;
    private long contentLength;
    /* access modifiers changed from: private */
    public State currentState;
    private final HeaderParser headerParser;
    private final LineParser lineParser;
    private final int maxChunkSize;
    private HttpMessage message;
    private AsciiString name;
    private final ByteBuf parserScratchBuffer;
    private final AtomicBoolean resetRequested;
    private LastHttpContent trailer;
    protected final boolean validateHeaders;
    private String value;

    private enum State {
        SKIP_CONTROL_CHARS,
        READ_INITIAL,
        READ_HEADER,
        READ_VARIABLE_LENGTH_CONTENT,
        READ_FIXED_LENGTH_CONTENT,
        READ_CHUNK_SIZE,
        READ_CHUNKED_CONTENT,
        READ_CHUNK_DELIMITER,
        READ_CHUNK_FOOTER,
        BAD_MESSAGE,
        UPGRADED
    }

    private static boolean isOWS(byte b) {
        return b == 32 || b == 9;
    }

    /* access modifiers changed from: protected */
    public abstract HttpMessage createInvalidMessage();

    /* access modifiers changed from: protected */
    public abstract HttpMessage createMessage(String[] strArr) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isDecodingRequest();

    /* access modifiers changed from: protected */
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            this.parserScratchBuffer.release();
        } finally {
            super.handlerRemoved0(channelHandlerContext);
        }
    }

    protected HttpObjectDecoder() {
        this(4096, 8192, 8192, true);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z) {
        this(i, i2, i3, z, true);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2) {
        this(i, i2, i3, z, z2, 128);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4) {
        this(i, i2, i3, z, z2, i4, false);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4, boolean z3) {
        this(i, i2, i3, z, z2, i4, z3, true);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4, boolean z3, boolean z4) {
        this.contentLength = Long.MIN_VALUE;
        this.resetRequested = new AtomicBoolean();
        this.currentState = State.SKIP_CONTROL_CHARS;
        ObjectUtil.checkPositive(i, "maxInitialLineLength");
        ObjectUtil.checkPositive(i2, "maxHeaderSize");
        ObjectUtil.checkPositive(i3, "maxChunkSize");
        ByteBuf buffer = Unpooled.buffer(i4);
        this.parserScratchBuffer = buffer;
        this.lineParser = new LineParser(buffer, i);
        this.headerParser = new HeaderParser(buffer, i2);
        this.maxChunkSize = i3;
        this.chunkedSupported = z;
        this.validateHeaders = z2;
        this.allowDuplicateContentLengths = z3;
        this.allowPartialChunks = z4;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ca, code lost:
        r8 = java.lang.Math.min((int) r7.chunkSize, r7.maxChunkSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d5, code lost:
        if (r7.allowPartialChunks != false) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00db, code lost:
        if (r9.readableBytes() >= r8) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00dd, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00de, code lost:
        r8 = java.lang.Math.min(r8, r9.readableBytes());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e6, code lost:
        if (r8 != 0) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e8, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e9, code lost:
        r2 = new io.netty.handler.codec.http.DefaultHttpContent(r9.readRetainedSlice(r8));
        r7.chunkSize -= (long) r8;
        r10.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ff, code lost:
        if (r7.chunkSize == 0) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0101, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0102, code lost:
        r7.currentState = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_DELIMITER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0106, code lost:
        r8 = r9.writerIndex();
        r10 = r9.readerIndex();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010e, code lost:
        if (r8 <= r10) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0110, code lost:
        r0 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0118, code lost:
        if (r9.getByte(r10) != 10) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011a, code lost:
        r7.currentState = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_SIZE;
        r10 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0120, code lost:
        r10 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0122, code lost:
        r9.readerIndex(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0125, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        r8 = readHeaders(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014a, code lost:
        if (r8 != null) goto L_0x014d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x014c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x014d, code lost:
        r7.currentState = r8;
        r2 = io.netty.handler.codec.http.HttpObjectDecoder.AnonymousClass2.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[r8.ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0158, code lost:
        if (r2 == 1) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x015b, code lost:
        if (r2 == 2) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x015d, code lost:
        r2 = contentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0163, code lost:
        if (r2 == 0) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0169, code lost:
        if (r2 != -1) goto L_0x0172;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x016f, code lost:
        if (isDecodingRequest() == false) goto L_0x0172;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0172, code lost:
        r10.add(r7.message);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0179, code lost:
        if (r8 != io.netty.handler.codec.http.HttpObjectDecoder.State.READ_FIXED_LENGTH_CONTENT) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017b, code lost:
        r7.chunkSize = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x017e, code lost:
        r10.add(r7.message);
        r10.add(io.netty.handler.codec.http.LastHttpContent.EMPTY_LAST_CONTENT);
        resetNow();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x018b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018e, code lost:
        if (r7.chunkedSupported == false) goto L_0x0196;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0190, code lost:
        r10.add(r7.message);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0195, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x019d, code lost:
        throw new java.lang.IllegalArgumentException("Chunked messages not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x019e, code lost:
        r10.add(r7.message);
        r10.add(io.netty.handler.codec.http.LastHttpContent.EMPTY_LAST_CONTENT);
        resetNow();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ab, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01ac, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01ad, code lost:
        r10.add(invalidMessage(r9, r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01b4, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r8, io.netty.buffer.ByteBuf r9, java.util.List<java.lang.Object> r10) throws java.lang.Exception {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.resetRequested
            boolean r8 = r8.get()
            if (r8 == 0) goto L_0x000b
            r7.resetNow()
        L_0x000b:
            int[] r8 = io.netty.handler.codec.http.HttpObjectDecoder.AnonymousClass2.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State
            io.netty.handler.codec.http.HttpObjectDecoder$State r0 = r7.currentState
            int r0 = r0.ordinal()
            r8 = r8[r0]
            r0 = 0
            switch(r8) {
                case 1: goto L_0x012f;
                case 2: goto L_0x009e;
                case 3: goto L_0x012f;
                case 4: goto L_0x0146;
                case 5: goto L_0x0085;
                case 6: goto L_0x004b;
                case 7: goto L_0x00ca;
                case 8: goto L_0x0106;
                case 9: goto L_0x0034;
                case 10: goto L_0x002b;
                case 11: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01bd
        L_0x001c:
            int r8 = r9.readableBytes()
            if (r8 <= 0) goto L_0x01bd
            io.netty.buffer.ByteBuf r8 = r9.readBytes((int) r8)
            r10.add(r8)
            goto L_0x01bd
        L_0x002b:
            int r8 = r9.readableBytes()
            r9.skipBytes(r8)
            goto L_0x01bd
        L_0x0034:
            io.netty.handler.codec.http.LastHttpContent r8 = r7.readTrailingHeaders(r9)     // Catch:{ Exception -> 0x0042 }
            if (r8 != 0) goto L_0x003b
            return
        L_0x003b:
            r10.add(r8)     // Catch:{ Exception -> 0x0042 }
            r7.resetNow()     // Catch:{ Exception -> 0x0042 }
            return
        L_0x0042:
            r8 = move-exception
            io.netty.handler.codec.http.HttpContent r8 = r7.invalidChunk(r9, r8)
            r10.add(r8)
            return
        L_0x004b:
            int r8 = r9.readableBytes()
            if (r8 != 0) goto L_0x0052
            return
        L_0x0052:
            int r2 = r7.maxChunkSize
            int r8 = java.lang.Math.min(r8, r2)
            long r2 = (long) r8
            long r4 = r7.chunkSize
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0060
            int r8 = (int) r4
        L_0x0060:
            io.netty.buffer.ByteBuf r9 = r9.readRetainedSlice(r8)
            long r2 = r7.chunkSize
            long r4 = (long) r8
            long r2 = r2 - r4
            r7.chunkSize = r2
            int r8 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r8 != 0) goto L_0x007c
            io.netty.handler.codec.http.DefaultLastHttpContent r8 = new io.netty.handler.codec.http.DefaultLastHttpContent
            boolean r0 = r7.validateHeaders
            r8.<init>((io.netty.buffer.ByteBuf) r9, (boolean) r0)
            r10.add(r8)
            r7.resetNow()
            goto L_0x0084
        L_0x007c:
            io.netty.handler.codec.http.DefaultHttpContent r8 = new io.netty.handler.codec.http.DefaultHttpContent
            r8.<init>(r9)
            r10.add(r8)
        L_0x0084:
            return
        L_0x0085:
            int r8 = r9.readableBytes()
            int r0 = r7.maxChunkSize
            int r8 = java.lang.Math.min(r8, r0)
            if (r8 <= 0) goto L_0x009d
            io.netty.buffer.ByteBuf r8 = r9.readRetainedSlice(r8)
            io.netty.handler.codec.http.DefaultHttpContent r9 = new io.netty.handler.codec.http.DefaultHttpContent
            r9.<init>(r8)
            r10.add(r9)
        L_0x009d:
            return
        L_0x009e:
            io.netty.handler.codec.http.HttpObjectDecoder$LineParser r8 = r7.lineParser     // Catch:{ Exception -> 0x0126 }
            io.netty.buffer.ByteBuf r8 = r8.parse(r9)     // Catch:{ Exception -> 0x0126 }
            if (r8 != 0) goto L_0x00a7
            return
        L_0x00a7:
            byte[] r2 = r8.array()     // Catch:{ Exception -> 0x0126 }
            int r3 = r8.arrayOffset()     // Catch:{ Exception -> 0x0126 }
            int r4 = r8.readerIndex()     // Catch:{ Exception -> 0x0126 }
            int r3 = r3 + r4
            int r8 = r8.readableBytes()     // Catch:{ Exception -> 0x0126 }
            int r8 = getChunkSize(r2, r3, r8)     // Catch:{ Exception -> 0x0126 }
            long r2 = (long) r8     // Catch:{ Exception -> 0x0126 }
            r7.chunkSize = r2     // Catch:{ Exception -> 0x0126 }
            if (r8 != 0) goto L_0x00c6
            io.netty.handler.codec.http.HttpObjectDecoder$State r8 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_FOOTER     // Catch:{ Exception -> 0x0126 }
            r7.currentState = r8     // Catch:{ Exception -> 0x0126 }
            return
        L_0x00c6:
            io.netty.handler.codec.http.HttpObjectDecoder$State r8 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNKED_CONTENT     // Catch:{ Exception -> 0x0126 }
            r7.currentState = r8     // Catch:{ Exception -> 0x0126 }
        L_0x00ca:
            long r2 = r7.chunkSize
            int r8 = (int) r2
            int r2 = r7.maxChunkSize
            int r8 = java.lang.Math.min(r8, r2)
            boolean r2 = r7.allowPartialChunks
            if (r2 != 0) goto L_0x00de
            int r2 = r9.readableBytes()
            if (r2 >= r8) goto L_0x00de
            return
        L_0x00de:
            int r2 = r9.readableBytes()
            int r8 = java.lang.Math.min(r8, r2)
            if (r8 != 0) goto L_0x00e9
            return
        L_0x00e9:
            io.netty.handler.codec.http.DefaultHttpContent r2 = new io.netty.handler.codec.http.DefaultHttpContent
            io.netty.buffer.ByteBuf r3 = r9.readRetainedSlice(r8)
            r2.<init>(r3)
            long r3 = r7.chunkSize
            long r5 = (long) r8
            long r3 = r3 - r5
            r7.chunkSize = r3
            r10.add(r2)
            long r2 = r7.chunkSize
            int r8 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x0102
            return
        L_0x0102:
            io.netty.handler.codec.http.HttpObjectDecoder$State r8 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_DELIMITER
            r7.currentState = r8
        L_0x0106:
            int r8 = r9.writerIndex()
            int r10 = r9.readerIndex()
        L_0x010e:
            if (r8 <= r10) goto L_0x0122
            int r0 = r10 + 1
            byte r10 = r9.getByte(r10)
            r1 = 10
            if (r10 != r1) goto L_0x0120
            io.netty.handler.codec.http.HttpObjectDecoder$State r8 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_SIZE
            r7.currentState = r8
            r10 = r0
            goto L_0x0122
        L_0x0120:
            r10 = r0
            goto L_0x010e
        L_0x0122:
            r9.readerIndex(r10)
            return
        L_0x0126:
            r8 = move-exception
            io.netty.handler.codec.http.HttpContent r8 = r7.invalidChunk(r9, r8)
            r10.add(r8)
            return
        L_0x012f:
            io.netty.handler.codec.http.HttpObjectDecoder$LineParser r8 = r7.lineParser     // Catch:{ Exception -> 0x01b5 }
            io.netty.buffer.ByteBuf r8 = r8.parse(r9)     // Catch:{ Exception -> 0x01b5 }
            if (r8 != 0) goto L_0x0138
            return
        L_0x0138:
            java.lang.String[] r8 = r7.splitInitialLine(r8)     // Catch:{ Exception -> 0x01b5 }
            io.netty.handler.codec.http.HttpMessage r8 = r7.createMessage(r8)     // Catch:{ Exception -> 0x01b5 }
            r7.message = r8     // Catch:{ Exception -> 0x01b5 }
            io.netty.handler.codec.http.HttpObjectDecoder$State r8 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_HEADER     // Catch:{ Exception -> 0x01b5 }
            r7.currentState = r8     // Catch:{ Exception -> 0x01b5 }
        L_0x0146:
            io.netty.handler.codec.http.HttpObjectDecoder$State r8 = r7.readHeaders(r9)     // Catch:{ Exception -> 0x01ac }
            if (r8 != 0) goto L_0x014d
            return
        L_0x014d:
            r7.currentState = r8     // Catch:{ Exception -> 0x01ac }
            int[] r2 = io.netty.handler.codec.http.HttpObjectDecoder.AnonymousClass2.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ Exception -> 0x01ac }
            int r3 = r8.ordinal()     // Catch:{ Exception -> 0x01ac }
            r2 = r2[r3]     // Catch:{ Exception -> 0x01ac }
            r3 = 1
            if (r2 == r3) goto L_0x019e
            r3 = 2
            if (r2 == r3) goto L_0x018c
            long r2 = r7.contentLength()     // Catch:{ Exception -> 0x01ac }
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 == 0) goto L_0x017e
            r0 = -1
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 != 0) goto L_0x0172
            boolean r0 = r7.isDecodingRequest()     // Catch:{ Exception -> 0x01ac }
            if (r0 == 0) goto L_0x0172
            goto L_0x017e
        L_0x0172:
            io.netty.handler.codec.http.HttpMessage r0 = r7.message     // Catch:{ Exception -> 0x01ac }
            r10.add(r0)     // Catch:{ Exception -> 0x01ac }
            io.netty.handler.codec.http.HttpObjectDecoder$State r0 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_FIXED_LENGTH_CONTENT     // Catch:{ Exception -> 0x01ac }
            if (r8 != r0) goto L_0x017d
            r7.chunkSize = r2     // Catch:{ Exception -> 0x01ac }
        L_0x017d:
            return
        L_0x017e:
            io.netty.handler.codec.http.HttpMessage r8 = r7.message     // Catch:{ Exception -> 0x01ac }
            r10.add(r8)     // Catch:{ Exception -> 0x01ac }
            io.netty.handler.codec.http.LastHttpContent r8 = io.netty.handler.codec.http.LastHttpContent.EMPTY_LAST_CONTENT     // Catch:{ Exception -> 0x01ac }
            r10.add(r8)     // Catch:{ Exception -> 0x01ac }
            r7.resetNow()     // Catch:{ Exception -> 0x01ac }
            return
        L_0x018c:
            boolean r8 = r7.chunkedSupported     // Catch:{ Exception -> 0x01ac }
            if (r8 == 0) goto L_0x0196
            io.netty.handler.codec.http.HttpMessage r8 = r7.message     // Catch:{ Exception -> 0x01ac }
            r10.add(r8)     // Catch:{ Exception -> 0x01ac }
            return
        L_0x0196:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x01ac }
            java.lang.String r0 = "Chunked messages not supported"
            r8.<init>(r0)     // Catch:{ Exception -> 0x01ac }
            throw r8     // Catch:{ Exception -> 0x01ac }
        L_0x019e:
            io.netty.handler.codec.http.HttpMessage r8 = r7.message     // Catch:{ Exception -> 0x01ac }
            r10.add(r8)     // Catch:{ Exception -> 0x01ac }
            io.netty.handler.codec.http.LastHttpContent r8 = io.netty.handler.codec.http.LastHttpContent.EMPTY_LAST_CONTENT     // Catch:{ Exception -> 0x01ac }
            r10.add(r8)     // Catch:{ Exception -> 0x01ac }
            r7.resetNow()     // Catch:{ Exception -> 0x01ac }
            return
        L_0x01ac:
            r8 = move-exception
            io.netty.handler.codec.http.HttpMessage r8 = r7.invalidMessage(r9, r8)
            r10.add(r8)
            return
        L_0x01b5:
            r8 = move-exception
            io.netty.handler.codec.http.HttpMessage r8 = r7.invalidMessage(r9, r8)
            r10.add(r8)
        L_0x01bd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.HttpObjectDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    /* renamed from: io.netty.handler.codec.http.HttpObjectDecoder$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.handler.codec.http.HttpObjectDecoder$State[] r0 = io.netty.handler.codec.http.HttpObjectDecoder.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State = r0
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.SKIP_CONTROL_CHARS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_SIZE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_INITIAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_HEADER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_VARIABLE_LENGTH_CONTENT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_FIXED_LENGTH_CONTENT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNKED_CONTENT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0060 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_DELIMITER     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x006c }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_FOOTER     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0078 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.BAD_MESSAGE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State     // Catch:{ NoSuchFieldError -> 0x0084 }
                io.netty.handler.codec.http.HttpObjectDecoder$State r1 = io.netty.handler.codec.http.HttpObjectDecoder.State.UPGRADED     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.HttpObjectDecoder.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void decodeLast(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        super.decodeLast(channelHandlerContext, byteBuf, list);
        if (this.resetRequested.get()) {
            resetNow();
        }
        HttpMessage httpMessage = this.message;
        if (httpMessage != null) {
            boolean isTransferEncodingChunked = HttpUtil.isTransferEncodingChunked(httpMessage);
            if (this.currentState == State.READ_VARIABLE_LENGTH_CONTENT && !byteBuf.isReadable() && !isTransferEncodingChunked) {
                list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                resetNow();
            } else if (this.currentState == State.READ_HEADER) {
                list.add(invalidMessage(Unpooled.EMPTY_BUFFER, new PrematureChannelClosureException("Connection closed before received headers")));
                resetNow();
            } else {
                if (!isDecodingRequest() && !isTransferEncodingChunked && contentLength() <= 0) {
                    list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                }
                resetNow();
            }
        }
    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        int i;
        if ((obj instanceof HttpExpectationFailedEvent) && ((i = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[this.currentState.ordinal()]) == 2 || i == 5 || i == 6)) {
            reset();
        }
        super.userEventTriggered(channelHandlerContext, obj);
    }

    /* access modifiers changed from: protected */
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        if (!(httpMessage instanceof HttpResponse)) {
            return false;
        }
        HttpResponse httpResponse = (HttpResponse) httpMessage;
        HttpResponseStatus status = httpResponse.status();
        int code = status.code();
        if (status.codeClass() == HttpStatusClass.INFORMATIONAL) {
            if (code != 101 || httpResponse.headers().contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_ACCEPT) || !httpResponse.headers().contains((CharSequence) HttpHeaderNames.UPGRADE, (CharSequence) HttpHeaderValues.WEBSOCKET, true)) {
                return true;
            }
            return false;
        } else if (code == 204 || code == 304) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSwitchingToNonHttp1Protocol(HttpResponse httpResponse) {
        if (httpResponse.status().code() != HttpResponseStatus.SWITCHING_PROTOCOLS.code()) {
            return false;
        }
        String str = httpResponse.headers().get((CharSequence) HttpHeaderNames.UPGRADE);
        if (str == null || (!str.contains(HttpVersion.HTTP_1_0.text()) && !str.contains(HttpVersion.HTTP_1_1.text()))) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.resetRequested.lazySet(true);
    }

    private void resetNow() {
        HttpResponse httpResponse;
        HttpMessage httpMessage = this.message;
        this.message = null;
        this.name = null;
        this.value = null;
        this.contentLength = Long.MIN_VALUE;
        this.lineParser.reset();
        this.headerParser.reset();
        this.trailer = null;
        if (isDecodingRequest() || (httpResponse = (HttpResponse) httpMessage) == null || !isSwitchingToNonHttp1Protocol(httpResponse)) {
            this.resetRequested.lazySet(false);
            this.currentState = State.SKIP_CONTROL_CHARS;
            return;
        }
        this.currentState = State.UPGRADED;
    }

    private HttpMessage invalidMessage(ByteBuf byteBuf, Exception exc) {
        this.currentState = State.BAD_MESSAGE;
        byteBuf.skipBytes(byteBuf.readableBytes());
        if (this.message == null) {
            this.message = createInvalidMessage();
        }
        this.message.setDecoderResult(DecoderResult.failure(exc));
        HttpMessage httpMessage = this.message;
        this.message = null;
        return httpMessage;
    }

    private HttpContent invalidChunk(ByteBuf byteBuf, Exception exc) {
        this.currentState = State.BAD_MESSAGE;
        byteBuf.skipBytes(byteBuf.readableBytes());
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        defaultLastHttpContent.setDecoderResult(DecoderResult.failure(exc));
        this.message = null;
        this.trailer = null;
        return defaultLastHttpContent;
    }

    private State readHeaders(ByteBuf byteBuf) {
        HttpMessage httpMessage = this.message;
        HttpHeaders headers = httpMessage.headers();
        HeaderParser headerParser2 = this.headerParser;
        ByteBuf parse = headerParser2.parse(byteBuf);
        if (parse == null) {
            return null;
        }
        int readableBytes = parse.readableBytes();
        while (readableBytes > 0) {
            byte[] array = parse.array();
            int arrayOffset = parse.arrayOffset() + parse.readerIndex();
            byte b = array[arrayOffset];
            AsciiString asciiString = this.name;
            if (asciiString == null || !(b == 32 || b == 9)) {
                if (asciiString != null) {
                    headers.add((CharSequence) asciiString, (Object) this.value);
                }
                splitHeader(array, arrayOffset, readableBytes);
            } else {
                String trim = langAsciiString(array, arrayOffset, readableBytes).trim();
                String str = this.value;
                this.value = str + ' ' + trim;
            }
            parse = headerParser2.parse(byteBuf);
            if (parse == null) {
                return null;
            }
            readableBytes = parse.readableBytes();
        }
        AsciiString asciiString2 = this.name;
        if (asciiString2 != null) {
            headers.add((CharSequence) asciiString2, (Object) this.value);
        }
        this.name = null;
        this.value = null;
        httpMessage.setDecoderResult(new HttpMessageDecoderResult(this.lineParser.size, headerParser2.size));
        List<String> all = headers.getAll((CharSequence) HttpHeaderNames.CONTENT_LENGTH);
        if (!all.isEmpty()) {
            HttpVersion protocolVersion = httpMessage.protocolVersion();
            long normalizeAndGetContentLength = HttpUtil.normalizeAndGetContentLength(all, protocolVersion.majorVersion() < 1 || (protocolVersion.majorVersion() == 1 && protocolVersion.minorVersion() == 0), this.allowDuplicateContentLengths);
            this.contentLength = normalizeAndGetContentLength;
            if (normalizeAndGetContentLength != -1) {
                String trim2 = all.get(0).trim();
                if (all.size() > 1 || !trim2.equals(Long.toString(this.contentLength))) {
                    headers.set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) Long.valueOf(this.contentLength));
                }
            }
        }
        if (isContentAlwaysEmpty(httpMessage)) {
            HttpUtil.setTransferEncodingChunked(httpMessage, false);
            return State.SKIP_CONTROL_CHARS;
        } else if (HttpUtil.isTransferEncodingChunked(httpMessage)) {
            if (!all.isEmpty() && httpMessage.protocolVersion() == HttpVersion.HTTP_1_1) {
                handleTransferEncodingChunkedWithContentLength(httpMessage);
            }
            return State.READ_CHUNK_SIZE;
        } else if (contentLength() >= 0) {
            return State.READ_FIXED_LENGTH_CONTENT;
        } else {
            return State.READ_VARIABLE_LENGTH_CONTENT;
        }
    }

    /* access modifiers changed from: protected */
    public void handleTransferEncodingChunkedWithContentLength(HttpMessage httpMessage) {
        httpMessage.headers().remove((CharSequence) HttpHeaderNames.CONTENT_LENGTH);
        this.contentLength = Long.MIN_VALUE;
    }

    private long contentLength() {
        if (this.contentLength == Long.MIN_VALUE) {
            this.contentLength = HttpUtil.getContentLength(this.message, -1);
        }
        return this.contentLength;
    }

    private LastHttpContent readTrailingHeaders(ByteBuf byteBuf) {
        HeaderParser headerParser2 = this.headerParser;
        ByteBuf parse = headerParser2.parse(byteBuf);
        if (parse == null) {
            return null;
        }
        LastHttpContent lastHttpContent = this.trailer;
        int readableBytes = parse.readableBytes();
        if (readableBytes == 0 && lastHttpContent == null) {
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        if (lastHttpContent == null) {
            lastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);
            this.trailer = lastHttpContent;
        }
        AsciiString asciiString = null;
        while (readableBytes > 0) {
            byte[] array = parse.array();
            int arrayOffset = parse.arrayOffset() + parse.readerIndex();
            byte b = array[arrayOffset];
            if (asciiString == null || !(b == 32 || b == 9)) {
                splitHeader(array, arrayOffset, readableBytes);
                AsciiString asciiString2 = this.name;
                if (!HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(asciiString2) && !HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(asciiString2) && !HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(asciiString2)) {
                    lastHttpContent.trailingHeaders().add((CharSequence) asciiString2, (Object) this.value);
                }
                asciiString = this.name;
                this.name = null;
                this.value = null;
            } else {
                List<String> all = lastHttpContent.trailingHeaders().getAll((CharSequence) asciiString);
                if (!all.isEmpty()) {
                    int size = all.size() - 1;
                    all.set(size, all.get(size) + langAsciiString(array, arrayOffset, parse.readableBytes()).trim());
                }
            }
            parse = headerParser2.parse(byteBuf);
            if (parse == null) {
                return null;
            }
            readableBytes = parse.readableBytes();
        }
        this.trailer = null;
        return lastHttpContent;
    }

    private static int skipWhiteSpaces(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (!isWhitespace(bArr[i + i3])) {
                return i3;
            }
        }
        return i2;
    }

    private static int getChunkSize(byte[] bArr, int i, int i2) {
        int skipWhiteSpaces = skipWhiteSpaces(bArr, i, i2);
        if (skipWhiteSpaces != i2) {
            int i3 = i + skipWhiteSpaces;
            int i4 = i2 - skipWhiteSpaces;
            int i5 = 0;
            int i6 = 0;
            while (i5 < i4) {
                int i7 = i3 + i5;
                int decodeHexNibble = StringUtil.decodeHexNibble(bArr[i7]);
                if (decodeHexNibble == -1) {
                    byte b = bArr[i7];
                    if (b != 59 && !isControlOrWhitespaceAsciiChar(b)) {
                        throw new NumberFormatException();
                    } else if (i5 != 0) {
                        return i6;
                    } else {
                        throw new NumberFormatException();
                    }
                } else {
                    i6 = (i6 * 16) + decodeHexNibble;
                    i5++;
                }
            }
            return i6;
        }
        throw new NumberFormatException();
    }

    private String[] splitInitialLine(ByteBuf byteBuf) {
        byte[] array = byteBuf.array();
        int arrayOffset = byteBuf.arrayOffset() + byteBuf.readerIndex();
        int readableBytes = byteBuf.readableBytes() + arrayOffset;
        int findNonSPLenient = findNonSPLenient(array, arrayOffset, readableBytes);
        int findSPLenient = findSPLenient(array, findNonSPLenient, readableBytes);
        int findNonSPLenient2 = findNonSPLenient(array, findSPLenient, readableBytes);
        int findSPLenient2 = findSPLenient(array, findNonSPLenient2, readableBytes);
        int findNonSPLenient3 = findNonSPLenient(array, findSPLenient2, readableBytes);
        int findEndOfString = findEndOfString(array, Math.max(findNonSPLenient3 - 1, arrayOffset), readableBytes);
        return new String[]{splitFirstWordInitialLine(array, findNonSPLenient, findSPLenient - findNonSPLenient), splitSecondWordInitialLine(array, findNonSPLenient2, findSPLenient2 - findNonSPLenient2), findNonSPLenient3 < findEndOfString ? splitThirdWordInitialLine(array, findNonSPLenient3, findEndOfString - findNonSPLenient3) : ""};
    }

    /* access modifiers changed from: protected */
    public String splitFirstWordInitialLine(byte[] bArr, int i, int i2) {
        return langAsciiString(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public String splitSecondWordInitialLine(byte[] bArr, int i, int i2) {
        return langAsciiString(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public String splitThirdWordInitialLine(byte[] bArr, int i, int i2) {
        return langAsciiString(bArr, i, i2);
    }

    private static String langAsciiString(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return "";
        }
        if (i != 0) {
            return new String(bArr, 0, i, i2);
        }
        if (i2 == bArr.length) {
            return new String(bArr, 0, 0, bArr.length);
        }
        return new String(bArr, 0, 0, i2);
    }

    private void splitHeader(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        int findNonWhitespace = findNonWhitespace(bArr, i, i3);
        boolean isDecodingRequest = isDecodingRequest();
        int i4 = findNonWhitespace;
        while (i4 < i3 && (r4 = bArr[i4]) != 58 && (isDecodingRequest || !isOWS(r4))) {
            i4++;
        }
        if (i4 != i3) {
            int i5 = i4;
            while (true) {
                if (i5 >= i3) {
                    break;
                } else if (bArr[i5] == 58) {
                    i5++;
                    break;
                } else {
                    i5++;
                }
            }
            this.name = splitHeaderName(bArr, findNonWhitespace, i4 - findNonWhitespace);
            int findNonWhitespace2 = findNonWhitespace(bArr, i5, i3);
            if (findNonWhitespace2 == i3) {
                this.value = "";
            } else {
                this.value = langAsciiString(bArr, findNonWhitespace2, findEndOfString(bArr, i, i3) - findNonWhitespace2);
            }
        } else {
            throw new IllegalArgumentException("No colon found");
        }
    }

    /* access modifiers changed from: protected */
    public AsciiString splitHeaderName(byte[] bArr, int i, int i2) {
        return new AsciiString(bArr, i, i2, true);
    }

    private static int findNonSPLenient(byte[] bArr, int i, int i2) {
        while (i < i2) {
            byte b = bArr[i];
            if (isSPLenient(b)) {
                i++;
            } else if (!isWhitespace(b)) {
                return i;
            } else {
                throw new IllegalArgumentException("Invalid separator");
            }
        }
        return i2;
    }

    private static int findSPLenient(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (isSPLenient(bArr[i])) {
                return i;
            }
            i++;
        }
        return i2;
    }

    static {
        boolean[] zArr = new boolean[256];
        SP_LENIENT_BYTES = zArr;
        zArr[160] = true;
        zArr[137] = true;
        zArr[139] = true;
        zArr[140] = true;
        zArr[141] = true;
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b = (byte) (b + 1)) {
            LATIN_WHITESPACE[b + 128] = Character.isWhitespace(b);
        }
        for (byte b2 = Byte.MIN_VALUE; b2 < Byte.MAX_VALUE; b2 = (byte) (b2 + 1)) {
            ISO_CONTROL_OR_WHITESPACE[b2 + 128] = Character.isISOControl(b2) || isWhitespace(b2);
        }
    }

    private static boolean isSPLenient(byte b) {
        return SP_LENIENT_BYTES[b + 128];
    }

    private static boolean isWhitespace(byte b) {
        return LATIN_WHITESPACE[b + 128];
    }

    private static int findNonWhitespace(byte[] bArr, int i, int i2) {
        while (i < i2) {
            byte b = bArr[i];
            if (!isWhitespace(b)) {
                return i;
            }
            if (isOWS(b)) {
                i++;
            } else {
                throw new IllegalArgumentException("Invalid separator, only a single space or horizontal tab allowed, but received a '" + b + "' (0x" + Integer.toHexString(b) + ")");
            }
        }
        return i2;
    }

    private static int findEndOfString(byte[] bArr, int i, int i2) {
        for (int i3 = i2 - 1; i3 > i; i3--) {
            if (!isWhitespace(bArr[i3])) {
                return i3 + 1;
            }
        }
        return 0;
    }

    private static class HeaderParser {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        protected final int maxLength;
        protected final ByteBuf seq;
        int size;

        static {
            Class<HttpObjectDecoder> cls = HttpObjectDecoder.class;
        }

        HeaderParser(ByteBuf byteBuf, int i) {
            this.seq = byteBuf;
            this.maxLength = i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
            if (r8.getByte(r0) == 13) goto L_0x003a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public io.netty.buffer.ByteBuf parse(io.netty.buffer.ByteBuf r8) {
            /*
                r7 = this;
                int r0 = r8.readableBytes()
                int r1 = r8.readerIndex()
                int r2 = r7.maxLength
                int r3 = r7.size
                int r2 = r2 - r3
                long r3 = (long) r2
                r5 = 2
                long r3 = r3 + r5
                long r5 = (long) r0
                long r3 = java.lang.Math.min(r3, r5)
                int r4 = (int) r3
                int r4 = r4 + r1
                r3 = 10
                int r3 = r8.indexOf(r1, r4, r3)
                r4 = -1
                if (r3 != r4) goto L_0x002c
                if (r0 > r2) goto L_0x0025
                r8 = 0
                return r8
            L_0x0025:
                int r8 = r7.maxLength
                io.netty.handler.codec.TooLongFrameException r8 = r7.newException(r8)
                throw r8
            L_0x002c:
                if (r3 <= r1) goto L_0x0039
                int r0 = r3 + -1
                byte r2 = r8.getByte(r0)
                r4 = 13
                if (r2 != r4) goto L_0x0039
                goto L_0x003a
            L_0x0039:
                r0 = r3
            L_0x003a:
                int r0 = r0 - r1
                if (r0 != 0) goto L_0x004a
                io.netty.buffer.ByteBuf r0 = r7.seq
                r0.clear()
                int r3 = r3 + 1
                r8.readerIndex(r3)
                io.netty.buffer.ByteBuf r8 = r7.seq
                return r8
            L_0x004a:
                int r2 = r7.size
                int r2 = r2 + r0
                int r4 = r7.maxLength
                if (r2 > r4) goto L_0x0065
                r7.size = r2
                io.netty.buffer.ByteBuf r2 = r7.seq
                r2.clear()
                io.netty.buffer.ByteBuf r2 = r7.seq
                r2.writeBytes((io.netty.buffer.ByteBuf) r8, (int) r1, (int) r0)
                int r3 = r3 + 1
                r8.readerIndex(r3)
                io.netty.buffer.ByteBuf r8 = r7.seq
                return r8
            L_0x0065:
                io.netty.handler.codec.TooLongFrameException r8 = r7.newException(r4)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser.parse(io.netty.buffer.ByteBuf):io.netty.buffer.ByteBuf");
        }

        public void reset() {
            this.size = 0;
        }

        /* access modifiers changed from: protected */
        public TooLongFrameException newException(int i) {
            return new TooLongHttpHeaderException("HTTP header is larger than " + i + " bytes.");
        }
    }

    private final class LineParser extends HeaderParser {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<HttpObjectDecoder> cls = HttpObjectDecoder.class;
        }

        LineParser(ByteBuf byteBuf, int i) {
            super(byteBuf, i);
        }

        public ByteBuf parse(ByteBuf byteBuf) {
            reset();
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes == 0) {
                return null;
            }
            int readerIndex = byteBuf.readerIndex();
            if (HttpObjectDecoder.this.currentState != State.SKIP_CONTROL_CHARS || !skipControlChars(byteBuf, readableBytes, readerIndex)) {
                return super.parse(byteBuf);
            }
            return null;
        }

        private boolean skipControlChars(ByteBuf byteBuf, int i, int i2) {
            int min = Math.min(this.maxLength, i);
            int forEachByte = byteBuf.forEachByte(i2, min, HttpObjectDecoder.SKIP_CONTROL_CHARS_BYTES);
            if (forEachByte == -1) {
                byteBuf.skipBytes(min);
                if (i <= this.maxLength) {
                    return true;
                }
                throw newException(this.maxLength);
            }
            byteBuf.readerIndex(forEachByte);
            State unused = HttpObjectDecoder.this.currentState = State.READ_INITIAL;
            return false;
        }

        /* access modifiers changed from: protected */
        public TooLongFrameException newException(int i) {
            return new TooLongHttpLineException("An HTTP line is larger than " + i + " bytes.");
        }
    }

    private static boolean isControlOrWhitespaceAsciiChar(byte b) {
        return ISO_CONTROL_OR_WHITESPACE[b + 128];
    }
}
