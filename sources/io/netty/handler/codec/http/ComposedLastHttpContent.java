package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;

final class ComposedLastHttpContent implements LastHttpContent {
    private DecoderResult result;
    private final HttpHeaders trailingHeaders;

    public int refCnt() {
        return 1;
    }

    public boolean release() {
        return false;
    }

    public boolean release(int i) {
        return false;
    }

    public LastHttpContent retain() {
        return this;
    }

    public LastHttpContent retain(int i) {
        return this;
    }

    public LastHttpContent touch() {
        return this;
    }

    public LastHttpContent touch(Object obj) {
        return this;
    }

    ComposedLastHttpContent(HttpHeaders httpHeaders) {
        this.trailingHeaders = httpHeaders;
    }

    ComposedLastHttpContent(HttpHeaders httpHeaders, DecoderResult decoderResult) {
        this(httpHeaders);
        this.result = decoderResult;
    }

    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }

    public LastHttpContent copy() {
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        defaultLastHttpContent.trailingHeaders().set(trailingHeaders());
        return defaultLastHttpContent;
    }

    public LastHttpContent duplicate() {
        return copy();
    }

    public LastHttpContent retainedDuplicate() {
        return copy();
    }

    public LastHttpContent replace(ByteBuf byteBuf) {
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(byteBuf);
        defaultLastHttpContent.trailingHeaders().setAll(trailingHeaders());
        return defaultLastHttpContent;
    }

    public ByteBuf content() {
        return Unpooled.EMPTY_BUFFER;
    }

    public DecoderResult decoderResult() {
        return this.result;
    }

    public DecoderResult getDecoderResult() {
        return decoderResult();
    }

    public void setDecoderResult(DecoderResult decoderResult) {
        this.result = decoderResult;
    }
}
