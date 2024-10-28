package io.netty.handler.codec.http2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.HttpConversionUtil;

public class HttpToHttp2ConnectionHandler extends Http2ConnectionHandler {
    private int currentStreamId;
    private HttpScheme httpScheme;
    private final boolean validateHeaders;

    protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        this.validateHeaders = z;
    }

    protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z, boolean z2) {
        this(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, z, z2, (HttpScheme) null);
    }

    protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z, boolean z2, HttpScheme httpScheme2) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, z2);
        this.validateHeaders = z;
        this.httpScheme = httpScheme2;
    }

    protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z, boolean z2, boolean z3, HttpScheme httpScheme2) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, z2, z3);
        this.validateHeaders = z;
        this.httpScheme = httpScheme2;
    }

    private int getStreamId(HttpHeaders httpHeaders) throws Exception {
        return httpHeaders.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), connection().local().incrementAndGetNextStreamId());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007e, code lost:
        if (r10 == false) goto L_0x0080;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(io.netty.channel.ChannelHandlerContext r13, java.lang.Object r14, io.netty.channel.ChannelPromise r15) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof io.netty.handler.codec.http.HttpMessage
            if (r0 != 0) goto L_0x000c
            boolean r0 = r14 instanceof io.netty.handler.codec.http.HttpContent
            if (r0 != 0) goto L_0x000c
            r13.write(r14, r15)
            return
        L_0x000c:
            io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator r0 = new io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator
            io.netty.channel.Channel r1 = r13.channel()
            io.netty.util.concurrent.EventExecutor r2 = r13.executor()
            r0.<init>(r15, r1, r2)
            r15 = 1
            io.netty.handler.codec.http2.Http2ConnectionEncoder r8 = r12.encoder()     // Catch:{ all -> 0x00d2 }
            boolean r1 = r14 instanceof io.netty.handler.codec.http.HttpMessage     // Catch:{ all -> 0x00d2 }
            r9 = 0
            if (r1 == 0) goto L_0x0080
            r1 = r14
            io.netty.handler.codec.http.HttpMessage r1 = (io.netty.handler.codec.http.HttpMessage) r1     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http.HttpHeaders r2 = r1.headers()     // Catch:{ all -> 0x00d2 }
            int r2 = r12.getStreamId(r2)     // Catch:{ all -> 0x00d2 }
            r12.currentStreamId = r2     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http.HttpScheme r2 = r12.httpScheme     // Catch:{ all -> 0x00d2 }
            if (r2 == 0) goto L_0x0057
            io.netty.handler.codec.http.HttpHeaders r2 = r1.headers()     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http2.HttpConversionUtil$ExtensionHeaderNames r3 = io.netty.handler.codec.http2.HttpConversionUtil.ExtensionHeaderNames.SCHEME     // Catch:{ all -> 0x00d2 }
            io.netty.util.AsciiString r3 = r3.text()     // Catch:{ all -> 0x00d2 }
            boolean r2 = r2.contains((java.lang.CharSequence) r3)     // Catch:{ all -> 0x00d2 }
            if (r2 != 0) goto L_0x0057
            io.netty.handler.codec.http.HttpHeaders r2 = r1.headers()     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http2.HttpConversionUtil$ExtensionHeaderNames r3 = io.netty.handler.codec.http2.HttpConversionUtil.ExtensionHeaderNames.SCHEME     // Catch:{ all -> 0x00d2 }
            io.netty.util.AsciiString r3 = r3.text()     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http.HttpScheme r4 = r12.httpScheme     // Catch:{ all -> 0x00d2 }
            io.netty.util.AsciiString r4 = r4.name()     // Catch:{ all -> 0x00d2 }
            r2.set((java.lang.CharSequence) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x00d2 }
        L_0x0057:
            boolean r2 = r12.validateHeaders     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http2.Http2Headers r5 = io.netty.handler.codec.http2.HttpConversionUtil.toHttp2Headers((io.netty.handler.codec.http.HttpMessage) r1, (boolean) r2)     // Catch:{ all -> 0x00d2 }
            boolean r2 = r14 instanceof io.netty.handler.codec.http.FullHttpMessage     // Catch:{ all -> 0x00d2 }
            if (r2 == 0) goto L_0x0070
            r2 = r14
            io.netty.handler.codec.http.FullHttpMessage r2 = (io.netty.handler.codec.http.FullHttpMessage) r2     // Catch:{ all -> 0x00d2 }
            io.netty.buffer.ByteBuf r2 = r2.content()     // Catch:{ all -> 0x00d2 }
            boolean r2 = r2.isReadable()     // Catch:{ all -> 0x00d2 }
            if (r2 != 0) goto L_0x0070
            r10 = 1
            goto L_0x0071
        L_0x0070:
            r10 = 0
        L_0x0071:
            int r3 = r12.currentStreamId     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http.HttpHeaders r4 = r1.headers()     // Catch:{ all -> 0x00d2 }
            r1 = r13
            r2 = r8
            r6 = r10
            r7 = r0
            writeHeaders(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00d2 }
            if (r10 != 0) goto L_0x00dc
        L_0x0080:
            boolean r1 = r14 instanceof io.netty.handler.codec.http.HttpContent     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x00dc
            io.netty.handler.codec.http.EmptyHttpHeaders r1 = io.netty.handler.codec.http.EmptyHttpHeaders.INSTANCE     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http2.EmptyHttp2Headers r2 = io.netty.handler.codec.http2.EmptyHttp2Headers.INSTANCE     // Catch:{ all -> 0x00d2 }
            boolean r3 = r14 instanceof io.netty.handler.codec.http.LastHttpContent     // Catch:{ all -> 0x00d2 }
            if (r3 == 0) goto L_0x009d
            r1 = r14
            io.netty.handler.codec.http.LastHttpContent r1 = (io.netty.handler.codec.http.LastHttpContent) r1     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http.HttpHeaders r1 = r1.trailingHeaders()     // Catch:{ all -> 0x00d2 }
            boolean r2 = r12.validateHeaders     // Catch:{ all -> 0x00d2 }
            io.netty.handler.codec.http2.Http2Headers r2 = io.netty.handler.codec.http2.HttpConversionUtil.toHttp2Headers((io.netty.handler.codec.http.HttpHeaders) r1, (boolean) r2)     // Catch:{ all -> 0x00d2 }
            r10 = r1
            r11 = r2
            r1 = 1
            goto L_0x00a0
        L_0x009d:
            r10 = r1
            r11 = r2
            r1 = 0
        L_0x00a0:
            r2 = r14
            io.netty.handler.codec.http.HttpContent r2 = (io.netty.handler.codec.http.HttpContent) r2     // Catch:{ all -> 0x00d2 }
            io.netty.buffer.ByteBuf r4 = r2.content()     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r10.isEmpty()     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x00b1
            r6 = 1
            goto L_0x00b2
        L_0x00b1:
            r6 = 0
        L_0x00b2:
            int r3 = r12.currentStreamId     // Catch:{ all -> 0x00d2 }
            io.netty.channel.ChannelPromise r7 = r0.newPromise()     // Catch:{ all -> 0x00d2 }
            r5 = 0
            r1 = r8
            r2 = r13
            r1.writeData(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00d2 }
            boolean r1 = r10.isEmpty()     // Catch:{ all -> 0x00d0 }
            if (r1 != 0) goto L_0x00df
            int r3 = r12.currentStreamId     // Catch:{ all -> 0x00d0 }
            r6 = 1
            r1 = r13
            r2 = r8
            r4 = r10
            r5 = r11
            r7 = r0
            writeHeaders(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00d0 }
            goto L_0x00df
        L_0x00d0:
            r1 = move-exception
            goto L_0x00d4
        L_0x00d2:
            r1 = move-exception
            r9 = 1
        L_0x00d4:
            r12.onError(r13, r15, r1)     // Catch:{ all -> 0x00e3 }
            r0.setFailure((java.lang.Throwable) r1)     // Catch:{ all -> 0x00e3 }
            if (r9 == 0) goto L_0x00df
        L_0x00dc:
            io.netty.util.ReferenceCountUtil.release(r14)
        L_0x00df:
            r0.doneAllocatingPromises()
            return
        L_0x00e3:
            r13 = move-exception
            if (r9 == 0) goto L_0x00e9
            io.netty.util.ReferenceCountUtil.release(r14)
        L_0x00e9:
            r0.doneAllocatingPromises()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.HttpToHttp2ConnectionHandler.write(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.channel.ChannelPromise):void");
    }

    private static void writeHeaders(ChannelHandlerContext channelHandlerContext, Http2ConnectionEncoder http2ConnectionEncoder, int i, HttpHeaders httpHeaders, Http2Headers http2Headers, boolean z, Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator) {
        HttpHeaders httpHeaders2 = httpHeaders;
        http2ConnectionEncoder.writeHeaders(channelHandlerContext, i, http2Headers, httpHeaders2.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_DEPENDENCY_ID.text(), 0), httpHeaders2.getShort(HttpConversionUtil.ExtensionHeaderNames.STREAM_WEIGHT.text(), 16), false, 0, z, simpleChannelPromiseAggregator.newPromise());
    }
}
