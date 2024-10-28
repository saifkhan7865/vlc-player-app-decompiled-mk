package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.compression.Brotli;
import io.netty.handler.codec.compression.BrotliEncoder;
import io.netty.handler.codec.compression.BrotliOptions;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.compression.DeflateOptions;
import io.netty.handler.codec.compression.GzipOptions;
import io.netty.handler.codec.compression.SnappyFrameEncoder;
import io.netty.handler.codec.compression.SnappyOptions;
import io.netty.handler.codec.compression.StandardCompressionOptions;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.compression.ZstdEncoder;
import io.netty.handler.codec.compression.ZstdOptions;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.util.internal.ObjectUtil;

public class CompressorHttp2ConnectionEncoder extends DecoratingHttp2ConnectionEncoder {
    public static final int DEFAULT_COMPRESSION_LEVEL = 6;
    public static final int DEFAULT_MEM_LEVEL = 8;
    public static final int DEFAULT_WINDOW_BITS = 15;
    private BrotliOptions brotliOptions;
    private int compressionLevel;
    private DeflateOptions deflateOptions;
    private GzipOptions gzipCompressionOptions;
    private int memLevel;
    /* access modifiers changed from: private */
    public final Http2Connection.PropertyKey propertyKey;
    private SnappyOptions snappyOptions;
    private final boolean supportsCompressionOptions;
    private int windowBits;
    private ZstdOptions zstdOptions;

    /* access modifiers changed from: protected */
    public CharSequence getTargetContentEncoding(CharSequence charSequence) throws Http2Exception {
        return charSequence;
    }

    public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder http2ConnectionEncoder) {
        this(http2ConnectionEncoder, defaultCompressionOptions());
    }

    private static CompressionOptions[] defaultCompressionOptions() {
        if (Brotli.isAvailable()) {
            return new CompressionOptions[]{StandardCompressionOptions.brotli(), StandardCompressionOptions.snappy(), StandardCompressionOptions.gzip(), StandardCompressionOptions.deflate()};
        }
        return new CompressionOptions[]{StandardCompressionOptions.snappy(), StandardCompressionOptions.gzip(), StandardCompressionOptions.deflate()};
    }

    @Deprecated
    public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder http2ConnectionEncoder, int i, int i2, int i3) {
        super(http2ConnectionEncoder);
        this.compressionLevel = ObjectUtil.checkInRange(i, 0, 9, "compressionLevel");
        this.windowBits = ObjectUtil.checkInRange(i2, 9, 15, "windowBits");
        this.memLevel = ObjectUtil.checkInRange(i3, 1, 9, "memLevel");
        this.propertyKey = connection().newKey();
        connection().addListener(new Http2ConnectionAdapter() {
            public void onStreamRemoved(Http2Stream http2Stream) {
                EmbeddedChannel embeddedChannel = (EmbeddedChannel) http2Stream.getProperty(CompressorHttp2ConnectionEncoder.this.propertyKey);
                if (embeddedChannel != null) {
                    CompressorHttp2ConnectionEncoder.this.cleanup(http2Stream, embeddedChannel);
                }
            }
        });
        this.supportsCompressionOptions = false;
    }

    public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder http2ConnectionEncoder, CompressionOptions... compressionOptionsArr) {
        super(http2ConnectionEncoder);
        ObjectUtil.checkNotNull(compressionOptionsArr, "CompressionOptions");
        ObjectUtil.deepCheckNotNull("CompressionOptions", compressionOptionsArr);
        for (BrotliOptions brotliOptions2 : compressionOptionsArr) {
            if (Brotli.isAvailable() && (brotliOptions2 instanceof BrotliOptions)) {
                this.brotliOptions = brotliOptions2;
            } else if (brotliOptions2 instanceof GzipOptions) {
                this.gzipCompressionOptions = (GzipOptions) brotliOptions2;
            } else if (brotliOptions2 instanceof DeflateOptions) {
                this.deflateOptions = (DeflateOptions) brotliOptions2;
            } else if (brotliOptions2 instanceof ZstdOptions) {
                this.zstdOptions = (ZstdOptions) brotliOptions2;
            } else if (brotliOptions2 instanceof SnappyOptions) {
                this.snappyOptions = (SnappyOptions) brotliOptions2;
            } else {
                StringBuilder sb = new StringBuilder("Unsupported CompressionOptions: ");
                Class<CompressionOptions> cls = CompressionOptions.class;
                sb.append(brotliOptions2);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        this.supportsCompressionOptions = true;
        this.propertyKey = connection().newKey();
        connection().addListener(new Http2ConnectionAdapter() {
            public void onStreamRemoved(Http2Stream http2Stream) {
                EmbeddedChannel embeddedChannel = (EmbeddedChannel) http2Stream.getProperty(CompressorHttp2ConnectionEncoder.this.propertyKey);
                if (embeddedChannel != null) {
                    CompressorHttp2ConnectionEncoder.this.cleanup(http2Stream, embeddedChannel);
                }
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a1, code lost:
        if (r21 != false) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ab, code lost:
        if (r21 == false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ad, code lost:
        cleanup(r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b0, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.channel.ChannelFuture writeData(io.netty.channel.ChannelHandlerContext r17, int r18, io.netty.buffer.ByteBuf r19, int r20, boolean r21, io.netty.channel.ChannelPromise r22) {
        /*
            r16 = this;
            r8 = r16
            r9 = r22
            io.netty.handler.codec.http2.Http2Connection r0 = r16.connection()
            r10 = r18
            io.netty.handler.codec.http2.Http2Stream r11 = r0.stream(r10)
            if (r11 != 0) goto L_0x0012
            r0 = 0
            goto L_0x001a
        L_0x0012:
            io.netty.handler.codec.http2.Http2Connection$PropertyKey r0 = r8.propertyKey
            java.lang.Object r0 = r11.getProperty(r0)
            io.netty.channel.embedded.EmbeddedChannel r0 = (io.netty.channel.embedded.EmbeddedChannel) r0
        L_0x001a:
            r12 = r0
            if (r12 != 0) goto L_0x0022
            io.netty.channel.ChannelFuture r0 = super.writeData(r17, r18, r19, r20, r21, r22)
            return r0
        L_0x0022:
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]     // Catch:{ all -> 0x00a7 }
            r13 = 0
            r1[r13] = r19     // Catch:{ all -> 0x00a7 }
            r12.writeOutbound(r1)     // Catch:{ all -> 0x00a7 }
            io.netty.buffer.ByteBuf r1 = nextReadableBuf(r12)     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x0062
            if (r21 == 0) goto L_0x0059
            boolean r0 = r12.finish()     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x003d
            io.netty.buffer.ByteBuf r1 = nextReadableBuf(r12)     // Catch:{ all -> 0x00a7 }
        L_0x003d:
            if (r1 != 0) goto L_0x0043
            io.netty.buffer.ByteBuf r0 = io.netty.buffer.Unpooled.EMPTY_BUFFER     // Catch:{ all -> 0x00a7 }
            r4 = r0
            goto L_0x0044
        L_0x0043:
            r4 = r1
        L_0x0044:
            r6 = 1
            r1 = r16
            r2 = r17
            r3 = r18
            r5 = r20
            r7 = r22
            io.netty.channel.ChannelFuture r0 = super.writeData(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00a7 }
            if (r21 == 0) goto L_0x0058
            r8.cleanup(r11, r12)
        L_0x0058:
            return r0
        L_0x0059:
            r22.setSuccess()     // Catch:{ all -> 0x00a7 }
            if (r21 == 0) goto L_0x0061
            r8.cleanup(r11, r12)
        L_0x0061:
            return r9
        L_0x0062:
            io.netty.util.concurrent.PromiseCombiner r14 = new io.netty.util.concurrent.PromiseCombiner     // Catch:{ all -> 0x00a7 }
            io.netty.util.concurrent.EventExecutor r2 = r17.executor()     // Catch:{ all -> 0x00a7 }
            r14.<init>(r2)     // Catch:{ all -> 0x00a7 }
            r5 = r20
            r4 = r1
        L_0x006e:
            io.netty.buffer.ByteBuf r1 = nextReadableBuf(r12)     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x0078
            if (r21 == 0) goto L_0x0078
            r2 = 1
            goto L_0x0079
        L_0x0078:
            r2 = 0
        L_0x0079:
            if (r2 == 0) goto L_0x008a
            boolean r3 = r12.finish()     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x008a
            io.netty.buffer.ByteBuf r1 = nextReadableBuf(r12)     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x0089
            r2 = 1
            goto L_0x008a
        L_0x0089:
            r2 = 0
        L_0x008a:
            r15 = r1
            r6 = r2
            io.netty.channel.ChannelPromise r7 = r17.newPromise()     // Catch:{ all -> 0x00a7 }
            r14.add((io.netty.util.concurrent.Promise) r7)     // Catch:{ all -> 0x00a7 }
            r1 = r16
            r2 = r17
            r3 = r18
            super.writeData(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00a7 }
            if (r15 != 0) goto L_0x00a4
            r14.finish(r9)     // Catch:{ all -> 0x00a7 }
            if (r21 == 0) goto L_0x00b0
            goto L_0x00ad
        L_0x00a4:
            r4 = r15
            r5 = 0
            goto L_0x006e
        L_0x00a7:
            r0 = move-exception
            r9.tryFailure(r0)     // Catch:{ all -> 0x00b1 }
            if (r21 == 0) goto L_0x00b0
        L_0x00ad:
            r8.cleanup(r11, r12)
        L_0x00b0:
            return r9
        L_0x00b1:
            r0 = move-exception
            r1 = r0
            if (r21 == 0) goto L_0x00b8
            r8.cleanup(r11, r12)
        L_0x00b8:
            goto L_0x00ba
        L_0x00b9:
            throw r1
        L_0x00ba:
            goto L_0x00b9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.CompressorHttp2ConnectionEncoder.writeData(io.netty.channel.ChannelHandlerContext, int, io.netty.buffer.ByteBuf, int, boolean, io.netty.channel.ChannelPromise):io.netty.channel.ChannelFuture");
    }

    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        try {
            EmbeddedChannel newCompressor = newCompressor(channelHandlerContext, http2Headers, z);
            ChannelFuture writeHeaders = super.writeHeaders(channelHandlerContext, i, http2Headers, i2, z, channelPromise);
            bindCompressorToStream(newCompressor, i);
            return writeHeaders;
        } catch (Throwable th) {
            channelPromise.tryFailure(th);
            return channelPromise;
        }
    }

    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        try {
            EmbeddedChannel newCompressor = newCompressor(channelHandlerContext, http2Headers, z2);
            ChannelFuture writeHeaders = super.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2, channelPromise);
            bindCompressorToStream(newCompressor, i);
            return writeHeaders;
        } catch (Throwable th) {
            channelPromise.tryFailure(th);
            return channelPromise;
        }
    }

    /* access modifiers changed from: protected */
    public EmbeddedChannel newContentCompressor(ChannelHandlerContext channelHandlerContext, CharSequence charSequence) throws Http2Exception {
        if (HttpHeaderValues.GZIP.contentEqualsIgnoreCase(charSequence) || HttpHeaderValues.X_GZIP.contentEqualsIgnoreCase(charSequence)) {
            return newCompressionChannel(channelHandlerContext, ZlibWrapper.GZIP);
        }
        if (HttpHeaderValues.DEFLATE.contentEqualsIgnoreCase(charSequence) || HttpHeaderValues.X_DEFLATE.contentEqualsIgnoreCase(charSequence)) {
            return newCompressionChannel(channelHandlerContext, ZlibWrapper.ZLIB);
        }
        if (Brotli.isAvailable() && this.brotliOptions != null && HttpHeaderValues.BR.contentEqualsIgnoreCase(charSequence)) {
            return new EmbeddedChannel(channelHandlerContext.channel().id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), new BrotliEncoder(this.brotliOptions.parameters()));
        } else if (this.zstdOptions != null && HttpHeaderValues.ZSTD.contentEqualsIgnoreCase(charSequence)) {
            return new EmbeddedChannel(channelHandlerContext.channel().id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), new ZstdEncoder(this.zstdOptions.compressionLevel(), this.zstdOptions.blockSize(), this.zstdOptions.maxEncodeSize()));
        } else if (this.snappyOptions == null || !HttpHeaderValues.SNAPPY.contentEqualsIgnoreCase(charSequence)) {
            return null;
        } else {
            return new EmbeddedChannel(channelHandlerContext.channel().id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), new SnappyFrameEncoder());
        }
    }

    private EmbeddedChannel newCompressionChannel(ChannelHandlerContext channelHandlerContext, ZlibWrapper zlibWrapper) {
        if (!this.supportsCompressionOptions) {
            return new EmbeddedChannel(channelHandlerContext.channel().id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), ZlibCodecFactory.newZlibEncoder(zlibWrapper, this.compressionLevel, this.windowBits, this.memLevel));
        } else if (zlibWrapper == ZlibWrapper.GZIP && this.gzipCompressionOptions != null) {
            return new EmbeddedChannel(channelHandlerContext.channel().id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), ZlibCodecFactory.newZlibEncoder(zlibWrapper, this.gzipCompressionOptions.compressionLevel(), this.gzipCompressionOptions.windowBits(), this.gzipCompressionOptions.memLevel()));
        } else if (zlibWrapper != ZlibWrapper.ZLIB || this.deflateOptions == null) {
            throw new IllegalArgumentException("Unsupported ZlibWrapper: " + zlibWrapper);
        } else {
            return new EmbeddedChannel(channelHandlerContext.channel().id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), ZlibCodecFactory.newZlibEncoder(zlibWrapper, this.deflateOptions.compressionLevel(), this.deflateOptions.windowBits(), this.deflateOptions.memLevel()));
        }
    }

    private EmbeddedChannel newCompressor(ChannelHandlerContext channelHandlerContext, Http2Headers http2Headers, boolean z) throws Http2Exception {
        if (z) {
            return null;
        }
        CharSequence charSequence = (CharSequence) http2Headers.get(HttpHeaderNames.CONTENT_ENCODING);
        if (charSequence == null) {
            charSequence = HttpHeaderValues.IDENTITY;
        }
        EmbeddedChannel newContentCompressor = newContentCompressor(channelHandlerContext, charSequence);
        if (newContentCompressor != null) {
            CharSequence targetContentEncoding = getTargetContentEncoding(charSequence);
            if (HttpHeaderValues.IDENTITY.contentEqualsIgnoreCase(targetContentEncoding)) {
                http2Headers.remove(HttpHeaderNames.CONTENT_ENCODING);
            } else {
                http2Headers.set(HttpHeaderNames.CONTENT_ENCODING, targetContentEncoding);
            }
            http2Headers.remove(HttpHeaderNames.CONTENT_LENGTH);
        }
        return newContentCompressor;
    }

    private void bindCompressorToStream(EmbeddedChannel embeddedChannel, int i) {
        Http2Stream stream;
        if (embeddedChannel != null && (stream = connection().stream(i)) != null) {
            stream.setProperty(this.propertyKey, embeddedChannel);
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanup(Http2Stream http2Stream, EmbeddedChannel embeddedChannel) {
        embeddedChannel.finishAndReleaseAll();
        http2Stream.removeProperty(this.propertyKey);
    }

    private static ByteBuf nextReadableBuf(EmbeddedChannel embeddedChannel) {
        while (true) {
            ByteBuf byteBuf = (ByteBuf) embeddedChannel.readOutbound();
            if (byteBuf == null) {
                return null;
            }
            if (byteBuf.isReadable()) {
                return byteBuf;
            }
            byteBuf.release();
        }
    }
}
