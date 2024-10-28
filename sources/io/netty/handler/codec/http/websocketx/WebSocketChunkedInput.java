package io.netty.handler.codec.http.websocketx;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.internal.ObjectUtil;

public final class WebSocketChunkedInput implements ChunkedInput<WebSocketFrame> {
    private final ChunkedInput<ByteBuf> input;
    private final int rsv;

    public WebSocketChunkedInput(ChunkedInput<ByteBuf> chunkedInput) {
        this(chunkedInput, 0);
    }

    public WebSocketChunkedInput(ChunkedInput<ByteBuf> chunkedInput, int i) {
        this.input = (ChunkedInput) ObjectUtil.checkNotNull(chunkedInput, TvContractCompat.PARAM_INPUT);
        this.rsv = i;
    }

    public boolean isEndOfInput() throws Exception {
        return this.input.isEndOfInput();
    }

    public void close() throws Exception {
        this.input.close();
    }

    @Deprecated
    public WebSocketFrame readChunk(ChannelHandlerContext channelHandlerContext) throws Exception {
        return readChunk(channelHandlerContext.alloc());
    }

    public WebSocketFrame readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
        ByteBuf readChunk = this.input.readChunk(byteBufAllocator);
        if (readChunk == null) {
            return null;
        }
        return new ContinuationWebSocketFrame(this.input.isEndOfInput(), this.rsv, readChunk);
    }

    public long length() {
        return this.input.length();
    }

    public long progress() {
        return this.input.progress();
    }
}
