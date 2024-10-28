package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToByteEncoder;
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
import io.netty.handler.codec.compression.Zstd;
import io.netty.handler.codec.compression.ZstdEncoder;
import io.netty.handler.codec.compression.ZstdOptions;
import io.netty.handler.codec.http.HttpContentEncoder;
import io.netty.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Map;
import org.fusesource.jansi.AnsiRenderer;

public class HttpContentCompressor extends HttpContentEncoder {
    /* access modifiers changed from: private */
    public final BrotliOptions brotliOptions;
    private final int compressionLevel;
    private final int contentSizeThreshold;
    private ChannelHandlerContext ctx;
    /* access modifiers changed from: private */
    public final DeflateOptions deflateOptions;
    private final Map<String, CompressionEncoderFactory> factories;
    /* access modifiers changed from: private */
    public final GzipOptions gzipOptions;
    private final int memLevel;
    private final SnappyOptions snappyOptions;
    private final boolean supportsCompressionOptions;
    private final int windowBits;
    /* access modifiers changed from: private */
    public final ZstdOptions zstdOptions;

    public HttpContentCompressor() {
        this(6);
    }

    @Deprecated
    public HttpContentCompressor(int i) {
        this(i, 15, 8, 0);
    }

    @Deprecated
    public HttpContentCompressor(int i, int i2, int i3) {
        this(i, i2, i3, 0);
    }

    @Deprecated
    public HttpContentCompressor(int i, int i2, int i3, int i4) {
        this.compressionLevel = ObjectUtil.checkInRange(i, 0, 9, "compressionLevel");
        this.windowBits = ObjectUtil.checkInRange(i2, 9, 15, "windowBits");
        this.memLevel = ObjectUtil.checkInRange(i3, 1, 9, "memLevel");
        this.contentSizeThreshold = ObjectUtil.checkPositiveOrZero(i4, "contentSizeThreshold");
        this.brotliOptions = null;
        this.gzipOptions = null;
        this.deflateOptions = null;
        this.zstdOptions = null;
        this.snappyOptions = null;
        this.factories = null;
        this.supportsCompressionOptions = false;
    }

    public HttpContentCompressor(CompressionOptions... compressionOptionsArr) {
        this(0, compressionOptionsArr);
    }

    public HttpContentCompressor(int i, CompressionOptions... compressionOptionsArr) {
        SnappyOptions snappyOptions2;
        ZstdOptions zstdOptions2;
        DeflateOptions deflateOptions2;
        GzipOptions gzipOptions2;
        BrotliOptions brotliOptions2;
        this.contentSizeThreshold = ObjectUtil.checkPositiveOrZero(i, "contentSizeThreshold");
        if (compressionOptionsArr == null || compressionOptionsArr.length == 0) {
            brotliOptions2 = Brotli.isAvailable() ? StandardCompressionOptions.brotli() : null;
            gzipOptions2 = StandardCompressionOptions.gzip();
            deflateOptions2 = StandardCompressionOptions.deflate();
            zstdOptions2 = Zstd.isAvailable() ? StandardCompressionOptions.zstd() : null;
            snappyOptions2 = StandardCompressionOptions.snappy();
        } else {
            ObjectUtil.deepCheckNotNull("compressionOptions", compressionOptionsArr);
            brotliOptions2 = null;
            gzipOptions2 = null;
            deflateOptions2 = null;
            zstdOptions2 = null;
            snappyOptions2 = null;
            for (BrotliOptions brotliOptions3 : compressionOptionsArr) {
                if (Brotli.isAvailable() && (brotliOptions3 instanceof BrotliOptions)) {
                    brotliOptions2 = brotliOptions3;
                } else if (brotliOptions3 instanceof GzipOptions) {
                    gzipOptions2 = brotliOptions3;
                } else if (brotliOptions3 instanceof DeflateOptions) {
                    deflateOptions2 = brotliOptions3;
                } else if (brotliOptions3 instanceof ZstdOptions) {
                    zstdOptions2 = brotliOptions3;
                } else if (brotliOptions3 instanceof SnappyOptions) {
                    snappyOptions2 = brotliOptions3;
                } else {
                    StringBuilder sb = new StringBuilder("Unsupported CompressionOptions: ");
                    Class<CompressionOptions> cls = CompressionOptions.class;
                    sb.append(brotliOptions3);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        this.gzipOptions = gzipOptions2;
        this.deflateOptions = deflateOptions2;
        this.brotliOptions = brotliOptions2;
        this.zstdOptions = zstdOptions2;
        this.snappyOptions = snappyOptions2;
        HashMap hashMap = new HashMap();
        this.factories = hashMap;
        if (gzipOptions2 != null) {
            hashMap.put("gzip", new GzipEncoderFactory(this, (AnonymousClass1) null));
        }
        if (deflateOptions2 != null) {
            hashMap.put("deflate", new DeflateEncoderFactory(this, (AnonymousClass1) null));
        }
        if (Brotli.isAvailable() && brotliOptions2 != null) {
            hashMap.put("br", new BrEncoderFactory(this, (AnonymousClass1) null));
        }
        if (zstdOptions2 != null) {
            hashMap.put("zstd", new ZstdEncoderFactory(this, (AnonymousClass1) null));
        }
        if (snappyOptions2 != null) {
            hashMap.put("snappy", new SnappyEncoderFactory((AnonymousClass1) null));
        }
        this.compressionLevel = -1;
        this.windowBits = -1;
        this.memLevel = -1;
        this.supportsCompressionOptions = true;
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    /* access modifiers changed from: protected */
    public HttpContentEncoder.Result beginEncode(HttpResponse httpResponse, String str) throws Exception {
        String str2;
        if ((this.contentSizeThreshold > 0 && (httpResponse instanceof HttpContent) && ((HttpContent) httpResponse).content().readableBytes() < this.contentSizeThreshold) || httpResponse.headers().get((CharSequence) HttpHeaderNames.CONTENT_ENCODING) != null) {
            return null;
        }
        if (this.supportsCompressionOptions) {
            String determineEncoding = determineEncoding(str);
            if (determineEncoding == null) {
                return null;
            }
            CompressionEncoderFactory compressionEncoderFactory = this.factories.get(determineEncoding);
            if (compressionEncoderFactory != null) {
                return new HttpContentEncoder.Result(determineEncoding, new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx.channel().config(), compressionEncoderFactory.createEncoder()));
            }
            throw new Error();
        }
        ZlibWrapper determineWrapper = determineWrapper(str);
        if (determineWrapper == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[determineWrapper.ordinal()];
        if (i == 1) {
            str2 = "gzip";
        } else if (i == 2) {
            str2 = "deflate";
        } else {
            throw new Error();
        }
        return new HttpContentEncoder.Result(str2, new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx.channel().config(), ZlibCodecFactory.newZlibEncoder(determineWrapper, this.compressionLevel, this.windowBits, this.memLevel)));
    }

    /* renamed from: io.netty.handler.codec.http.HttpContentCompressor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.netty.handler.codec.compression.ZlibWrapper[] r0 = io.netty.handler.codec.compression.ZlibWrapper.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = r0
                io.netty.handler.codec.compression.ZlibWrapper r1 = io.netty.handler.codec.compression.ZlibWrapper.GZIP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.compression.ZlibWrapper r1 = io.netty.handler.codec.compression.ZlibWrapper.ZLIB     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.HttpContentCompressor.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public String determineEncoding(String str) {
        float f;
        String[] split = str.split(AnsiRenderer.CODE_LIST_SEPARATOR);
        int length = split.length;
        int i = 0;
        float f2 = -1.0f;
        float f3 = -1.0f;
        float f4 = -1.0f;
        float f5 = -1.0f;
        float f6 = -1.0f;
        float f7 = -1.0f;
        while (true) {
            float f8 = 0.0f;
            if (i >= length) {
                break;
            }
            String str2 = split[i];
            String[] strArr = split;
            int indexOf = str2.indexOf(61);
            int i2 = length;
            if (indexOf != -1) {
                try {
                    f8 = Float.parseFloat(str2.substring(indexOf + 1));
                } catch (NumberFormatException unused) {
                }
            } else {
                f8 = 1.0f;
            }
            if (str2.contains("*")) {
                f7 = f8;
            } else if (str2.contains("br") && f8 > f2) {
                f2 = f8;
            } else if (str2.contains("zstd") && f8 > f3) {
                f3 = f8;
            } else if (str2.contains("snappy") && f8 > f4) {
                f4 = f8;
            } else if (str2.contains("gzip") && f8 > f5) {
                f5 = f8;
            } else if (str2.contains("deflate") && f8 > f6) {
                f6 = f8;
            }
            i++;
            split = strArr;
            length = i2;
        }
        if (f2 > 0.0f || f3 > 0.0f || f4 > 0.0f || f5 > 0.0f || f6 > 0.0f) {
            f = -1.0f;
            if (f2 != -1.0f && f2 >= f3 && this.brotliOptions != null) {
                return "br";
            }
            if (f3 != -1.0f && f3 >= f4 && this.zstdOptions != null) {
                return "zstd";
            }
            if (f4 != -1.0f && f4 >= f5 && this.snappyOptions != null) {
                return "snappy";
            }
            if (f5 != -1.0f && f5 >= f6 && this.gzipOptions != null) {
                return "gzip";
            }
            if (!(f6 == -1.0f || this.deflateOptions == null)) {
                return "deflate";
            }
        } else {
            f = -1.0f;
        }
        if (f7 <= 0.0f) {
            return null;
        }
        if (f2 == f && this.brotliOptions != null) {
            return "br";
        }
        if (f3 == f && this.zstdOptions != null) {
            return "zstd";
        }
        if (f4 == f && this.snappyOptions != null) {
            return "snappy";
        }
        if (f5 == f && this.gzipOptions != null) {
            return "gzip";
        }
        if (f6 != f || this.deflateOptions == null) {
            return null;
        }
        return "deflate";
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public ZlibWrapper determineWrapper(String str) {
        String[] split = str.split(AnsiRenderer.CODE_LIST_SEPARATOR);
        int length = split.length;
        int i = 0;
        float f = -1.0f;
        float f2 = -1.0f;
        float f3 = -1.0f;
        while (true) {
            float f4 = 0.0f;
            if (i >= length) {
                break;
            }
            String str2 = split[i];
            int indexOf = str2.indexOf(61);
            if (indexOf != -1) {
                try {
                    f4 = Float.parseFloat(str2.substring(indexOf + 1));
                } catch (NumberFormatException unused) {
                }
            } else {
                f4 = 1.0f;
            }
            if (str2.contains("*")) {
                f3 = f4;
            } else if (str2.contains("gzip") && f4 > f) {
                f = f4;
            } else if (str2.contains("deflate") && f4 > f2) {
                f2 = f4;
            }
            i++;
        }
        if (f > 0.0f || f2 > 0.0f) {
            if (f >= f2) {
                return ZlibWrapper.GZIP;
            }
            return ZlibWrapper.ZLIB;
        } else if (f3 <= 0.0f) {
            return null;
        } else {
            if (f == -1.0f) {
                return ZlibWrapper.GZIP;
            }
            if (f2 == -1.0f) {
                return ZlibWrapper.ZLIB;
            }
            return null;
        }
    }

    private final class GzipEncoderFactory implements CompressionEncoderFactory {
        private GzipEncoderFactory() {
        }

        /* synthetic */ GzipEncoderFactory(HttpContentCompressor httpContentCompressor, AnonymousClass1 r2) {
            this();
        }

        public MessageToByteEncoder<ByteBuf> createEncoder() {
            return ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP, HttpContentCompressor.this.gzipOptions.compressionLevel(), HttpContentCompressor.this.gzipOptions.windowBits(), HttpContentCompressor.this.gzipOptions.memLevel());
        }
    }

    private final class DeflateEncoderFactory implements CompressionEncoderFactory {
        private DeflateEncoderFactory() {
        }

        /* synthetic */ DeflateEncoderFactory(HttpContentCompressor httpContentCompressor, AnonymousClass1 r2) {
            this();
        }

        public MessageToByteEncoder<ByteBuf> createEncoder() {
            return ZlibCodecFactory.newZlibEncoder(ZlibWrapper.ZLIB, HttpContentCompressor.this.deflateOptions.compressionLevel(), HttpContentCompressor.this.deflateOptions.windowBits(), HttpContentCompressor.this.deflateOptions.memLevel());
        }
    }

    private final class BrEncoderFactory implements CompressionEncoderFactory {
        private BrEncoderFactory() {
        }

        /* synthetic */ BrEncoderFactory(HttpContentCompressor httpContentCompressor, AnonymousClass1 r2) {
            this();
        }

        public MessageToByteEncoder<ByteBuf> createEncoder() {
            return new BrotliEncoder(HttpContentCompressor.this.brotliOptions.parameters());
        }
    }

    private final class ZstdEncoderFactory implements CompressionEncoderFactory {
        private ZstdEncoderFactory() {
        }

        /* synthetic */ ZstdEncoderFactory(HttpContentCompressor httpContentCompressor, AnonymousClass1 r2) {
            this();
        }

        public MessageToByteEncoder<ByteBuf> createEncoder() {
            return new ZstdEncoder(HttpContentCompressor.this.zstdOptions.compressionLevel(), HttpContentCompressor.this.zstdOptions.blockSize(), HttpContentCompressor.this.zstdOptions.maxEncodeSize());
        }
    }

    private static final class SnappyEncoderFactory implements CompressionEncoderFactory {
        private SnappyEncoderFactory() {
        }

        /* synthetic */ SnappyEncoderFactory(AnonymousClass1 r1) {
            this();
        }

        public MessageToByteEncoder<ByteBuf> createEncoder() {
            return new SnappyFrameEncoder();
        }
    }
}
