package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AsyncMapping;
import io.netty.util.DomainNameMapping;
import io.netty.util.Mapping;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;

public class SniHandler extends AbstractSniHandler<SslContext> {
    private static final Selection EMPTY_SELECTION = new Selection((SslContext) null, (String) null);
    protected final AsyncMapping<String, SslContext> mapping;
    private volatile Selection selection;

    public SniHandler(Mapping<? super String, ? extends SslContext> mapping2) {
        this((AsyncMapping<? super String, ? extends SslContext>) new AsyncMappingAdapter(mapping2));
    }

    public SniHandler(Mapping<? super String, ? extends SslContext> mapping2, int i, long j) {
        this((AsyncMapping<? super String, ? extends SslContext>) new AsyncMappingAdapter(mapping2), i, j);
    }

    public SniHandler(DomainNameMapping<? extends SslContext> domainNameMapping) {
        this((Mapping<? super String, ? extends SslContext>) domainNameMapping);
    }

    public SniHandler(AsyncMapping<? super String, ? extends SslContext> asyncMapping) {
        this(asyncMapping, 0, 0);
    }

    public SniHandler(AsyncMapping<? super String, ? extends SslContext> asyncMapping, int i, long j) {
        super(i, j);
        this.selection = EMPTY_SELECTION;
        this.mapping = (AsyncMapping) ObjectUtil.checkNotNull(asyncMapping, "mapping");
    }

    public SniHandler(Mapping<? super String, ? extends SslContext> mapping2, long j) {
        this((AsyncMapping<? super String, ? extends SslContext>) new AsyncMappingAdapter(mapping2), j);
    }

    public SniHandler(AsyncMapping<? super String, ? extends SslContext> asyncMapping, long j) {
        this(asyncMapping, 0, j);
    }

    public String hostname() {
        return this.selection.hostname;
    }

    public SslContext sslContext() {
        return this.selection.context;
    }

    /* access modifiers changed from: protected */
    public Future<SslContext> lookup(ChannelHandlerContext channelHandlerContext, String str) throws Exception {
        return this.mapping.map(str, channelHandlerContext.executor().newPromise());
    }

    /* access modifiers changed from: protected */
    public final void onLookupComplete(ChannelHandlerContext channelHandlerContext, String str, Future<SslContext> future) throws Exception {
        if (!future.isSuccess()) {
            Throwable cause = future.cause();
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new DecoderException("failed to get the SslContext for " + str, cause);
        }
        SslContext now = future.getNow();
        this.selection = new Selection(now, str);
        try {
            replaceHandler(channelHandlerContext, str, now);
        } catch (Throwable th) {
            this.selection = EMPTY_SELECTION;
            PlatformDependent.throwException(th);
        }
    }

    /* access modifiers changed from: protected */
    public void replaceHandler(ChannelHandlerContext channelHandlerContext, String str, SslContext sslContext) throws Exception {
        SslHandler sslHandler = null;
        try {
            sslHandler = newSslHandler(sslContext, channelHandlerContext.alloc());
            channelHandlerContext.pipeline().replace((ChannelHandler) this, SslHandler.class.getName(), (ChannelHandler) sslHandler);
        } catch (Throwable th) {
            if (sslHandler != null) {
                ReferenceCountUtil.safeRelease(sslHandler.engine());
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public SslHandler newSslHandler(SslContext sslContext, ByteBufAllocator byteBufAllocator) {
        SslHandler newHandler = sslContext.newHandler(byteBufAllocator);
        newHandler.setHandshakeTimeoutMillis(this.handshakeTimeoutMillis);
        return newHandler;
    }

    private static final class AsyncMappingAdapter implements AsyncMapping<String, SslContext> {
        private final Mapping<? super String, ? extends SslContext> mapping;

        private AsyncMappingAdapter(Mapping<? super String, ? extends SslContext> mapping2) {
            this.mapping = (Mapping) ObjectUtil.checkNotNull(mapping2, "mapping");
        }

        public Future<SslContext> map(String str, Promise<SslContext> promise) {
            try {
                return promise.setSuccess((SslContext) this.mapping.map(str));
            } catch (Throwable th) {
                return promise.setFailure(th);
            }
        }
    }

    private static final class Selection {
        final SslContext context;
        final String hostname;

        Selection(SslContext sslContext, String str) {
            this.context = sslContext;
            this.hostname = str;
        }
    }
}
