package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.MessageAggregator;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class HttpObjectAggregator extends MessageAggregator<HttpObject, HttpMessage, HttpContent, FullHttpMessage> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final FullHttpResponse CONTINUE = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);
    private static final FullHttpResponse EXPECTATION_FAILED;
    private static final FullHttpResponse TOO_LARGE;
    private static final FullHttpResponse TOO_LARGE_CLOSE;
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) HttpObjectAggregator.class);
    private final boolean closeOnExpectationFailed;

    static {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.EXPECTATION_FAILED, Unpooled.EMPTY_BUFFER);
        EXPECTATION_FAILED = defaultFullHttpResponse;
        DefaultFullHttpResponse defaultFullHttpResponse2 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE, Unpooled.EMPTY_BUFFER);
        TOO_LARGE_CLOSE = defaultFullHttpResponse2;
        DefaultFullHttpResponse defaultFullHttpResponse3 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE, Unpooled.EMPTY_BUFFER);
        TOO_LARGE = defaultFullHttpResponse3;
        defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        defaultFullHttpResponse3.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        defaultFullHttpResponse2.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        defaultFullHttpResponse2.headers().set((CharSequence) HttpHeaderNames.CONNECTION, (Object) HttpHeaderValues.CLOSE);
    }

    public HttpObjectAggregator(int i) {
        this(i, false);
    }

    public HttpObjectAggregator(int i, boolean z) {
        super(i);
        this.closeOnExpectationFailed = z;
    }

    /* access modifiers changed from: protected */
    public boolean isStartMessage(HttpObject httpObject) throws Exception {
        return httpObject instanceof HttpMessage;
    }

    /* access modifiers changed from: protected */
    public boolean isContentMessage(HttpObject httpObject) throws Exception {
        return httpObject instanceof HttpContent;
    }

    /* access modifiers changed from: protected */
    public boolean isLastContentMessage(HttpContent httpContent) throws Exception {
        return httpContent instanceof LastHttpContent;
    }

    /* access modifiers changed from: protected */
    public boolean isAggregated(HttpObject httpObject) throws Exception {
        return httpObject instanceof FullHttpMessage;
    }

    /* access modifiers changed from: protected */
    public boolean isContentLengthInvalid(HttpMessage httpMessage, int i) {
        try {
            return HttpUtil.getContentLength(httpMessage, -1) > ((long) i);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static Object continueResponse(HttpMessage httpMessage, int i, ChannelPipeline channelPipeline) {
        if (HttpUtil.isUnsupportedExpectation(httpMessage)) {
            channelPipeline.fireUserEventTriggered(HttpExpectationFailedEvent.INSTANCE);
            return EXPECTATION_FAILED.retainedDuplicate();
        } else if (!HttpUtil.is100ContinueExpected(httpMessage)) {
            return null;
        } else {
            if (HttpUtil.getContentLength(httpMessage, -1) <= ((long) i)) {
                return CONTINUE.retainedDuplicate();
            }
            channelPipeline.fireUserEventTriggered(HttpExpectationFailedEvent.INSTANCE);
            return TOO_LARGE.retainedDuplicate();
        }
    }

    /* access modifiers changed from: protected */
    public Object newContinueResponse(HttpMessage httpMessage, int i, ChannelPipeline channelPipeline) {
        Object continueResponse = continueResponse(httpMessage, i, channelPipeline);
        if (continueResponse != null) {
            httpMessage.headers().remove((CharSequence) HttpHeaderNames.EXPECT);
        }
        return continueResponse;
    }

    /* access modifiers changed from: protected */
    public boolean closeAfterContinueResponse(Object obj) {
        return this.closeOnExpectationFailed && ignoreContentAfterContinueResponse(obj);
    }

    /* access modifiers changed from: protected */
    public boolean ignoreContentAfterContinueResponse(Object obj) {
        if (obj instanceof HttpResponse) {
            return ((HttpResponse) obj).status().codeClass().equals(HttpStatusClass.CLIENT_ERROR);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public FullHttpMessage beginAggregation(HttpMessage httpMessage, ByteBuf byteBuf) throws Exception {
        HttpUtil.setTransferEncodingChunked(httpMessage, false);
        if (httpMessage instanceof HttpRequest) {
            return new AggregatedFullHttpRequest((HttpRequest) httpMessage, byteBuf, (HttpHeaders) null);
        }
        if (httpMessage instanceof HttpResponse) {
            return new AggregatedFullHttpResponse((HttpResponse) httpMessage, byteBuf, (HttpHeaders) null);
        }
        throw new Error();
    }

    /* access modifiers changed from: protected */
    public void aggregate(FullHttpMessage fullHttpMessage, HttpContent httpContent) throws Exception {
        if (httpContent instanceof LastHttpContent) {
            ((AggregatedFullHttpMessage) fullHttpMessage).setTrailingHeaders(((LastHttpContent) httpContent).trailingHeaders());
        }
    }

    /* access modifiers changed from: protected */
    public void finishAggregation(FullHttpMessage fullHttpMessage) throws Exception {
        if (!HttpUtil.isContentLengthSet(fullHttpMessage)) {
            fullHttpMessage.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) String.valueOf(fullHttpMessage.content().readableBytes()));
        }
    }

    /* access modifiers changed from: protected */
    public void handleOversizedMessage(final ChannelHandlerContext channelHandlerContext, HttpMessage httpMessage) throws Exception {
        if (httpMessage instanceof HttpRequest) {
            if ((httpMessage instanceof FullHttpMessage) || (!HttpUtil.is100ContinueExpected(httpMessage) && !HttpUtil.isKeepAlive(httpMessage))) {
                channelHandlerContext.writeAndFlush(TOO_LARGE_CLOSE.retainedDuplicate()).addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            HttpObjectAggregator.logger.debug("Failed to send a 413 Request Entity Too Large.", channelFuture.cause());
                        }
                        channelHandlerContext.close();
                    }
                });
            } else {
                channelHandlerContext.writeAndFlush(TOO_LARGE.retainedDuplicate()).addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            HttpObjectAggregator.logger.debug("Failed to send a 413 Request Entity Too Large.", channelFuture.cause());
                            channelHandlerContext.close();
                        }
                    }
                });
            }
        } else if (httpMessage instanceof HttpResponse) {
            channelHandlerContext.close();
            throw new TooLongHttpContentException("Response entity too large: " + httpMessage);
        } else {
            throw new IllegalStateException();
        }
    }

    private static abstract class AggregatedFullHttpMessage implements FullHttpMessage {
        private final ByteBuf content;
        protected final HttpMessage message;
        private HttpHeaders trailingHeaders;

        public abstract FullHttpMessage copy();

        public abstract FullHttpMessage duplicate();

        public abstract FullHttpMessage retainedDuplicate();

        AggregatedFullHttpMessage(HttpMessage httpMessage, ByteBuf byteBuf, HttpHeaders httpHeaders) {
            this.message = httpMessage;
            this.content = byteBuf;
            this.trailingHeaders = httpHeaders;
        }

        public HttpHeaders trailingHeaders() {
            HttpHeaders httpHeaders = this.trailingHeaders;
            return httpHeaders == null ? EmptyHttpHeaders.INSTANCE : httpHeaders;
        }

        /* access modifiers changed from: package-private */
        public void setTrailingHeaders(HttpHeaders httpHeaders) {
            this.trailingHeaders = httpHeaders;
        }

        public HttpVersion getProtocolVersion() {
            return this.message.protocolVersion();
        }

        public HttpVersion protocolVersion() {
            return this.message.protocolVersion();
        }

        public FullHttpMessage setProtocolVersion(HttpVersion httpVersion) {
            this.message.setProtocolVersion(httpVersion);
            return this;
        }

        public HttpHeaders headers() {
            return this.message.headers();
        }

        public DecoderResult decoderResult() {
            return this.message.decoderResult();
        }

        public DecoderResult getDecoderResult() {
            return this.message.decoderResult();
        }

        public void setDecoderResult(DecoderResult decoderResult) {
            this.message.setDecoderResult(decoderResult);
        }

        public ByteBuf content() {
            return this.content;
        }

        public int refCnt() {
            return this.content.refCnt();
        }

        public FullHttpMessage retain() {
            this.content.retain();
            return this;
        }

        public FullHttpMessage retain(int i) {
            this.content.retain(i);
            return this;
        }

        public FullHttpMessage touch(Object obj) {
            this.content.touch(obj);
            return this;
        }

        public FullHttpMessage touch() {
            this.content.touch();
            return this;
        }

        public boolean release() {
            return this.content.release();
        }

        public boolean release(int i) {
            return this.content.release(i);
        }
    }

    private static final class AggregatedFullHttpRequest extends AggregatedFullHttpMessage implements FullHttpRequest {
        AggregatedFullHttpRequest(HttpRequest httpRequest, ByteBuf byteBuf, HttpHeaders httpHeaders) {
            super(httpRequest, byteBuf, httpHeaders);
        }

        public FullHttpRequest copy() {
            return replace(content().copy());
        }

        public FullHttpRequest duplicate() {
            return replace(content().duplicate());
        }

        public FullHttpRequest retainedDuplicate() {
            return replace(content().retainedDuplicate());
        }

        public FullHttpRequest replace(ByteBuf byteBuf) {
            DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(protocolVersion(), method(), uri(), byteBuf, headers().copy(), trailingHeaders().copy());
            defaultFullHttpRequest.setDecoderResult(decoderResult());
            return defaultFullHttpRequest;
        }

        public FullHttpRequest retain(int i) {
            super.retain(i);
            return this;
        }

        public FullHttpRequest retain() {
            super.retain();
            return this;
        }

        public FullHttpRequest touch() {
            super.touch();
            return this;
        }

        public FullHttpRequest touch(Object obj) {
            super.touch(obj);
            return this;
        }

        public FullHttpRequest setMethod(HttpMethod httpMethod) {
            ((HttpRequest) this.message).setMethod(httpMethod);
            return this;
        }

        public FullHttpRequest setUri(String str) {
            ((HttpRequest) this.message).setUri(str);
            return this;
        }

        public HttpMethod getMethod() {
            return ((HttpRequest) this.message).method();
        }

        public String getUri() {
            return ((HttpRequest) this.message).uri();
        }

        public HttpMethod method() {
            return getMethod();
        }

        public String uri() {
            return getUri();
        }

        public FullHttpRequest setProtocolVersion(HttpVersion httpVersion) {
            super.setProtocolVersion(httpVersion);
            return this;
        }

        public String toString() {
            return HttpMessageUtil.appendFullRequest(new StringBuilder(256), this).toString();
        }
    }

    private static final class AggregatedFullHttpResponse extends AggregatedFullHttpMessage implements FullHttpResponse {
        AggregatedFullHttpResponse(HttpResponse httpResponse, ByteBuf byteBuf, HttpHeaders httpHeaders) {
            super(httpResponse, byteBuf, httpHeaders);
        }

        public FullHttpResponse copy() {
            return replace(content().copy());
        }

        public FullHttpResponse duplicate() {
            return replace(content().duplicate());
        }

        public FullHttpResponse retainedDuplicate() {
            return replace(content().retainedDuplicate());
        }

        public FullHttpResponse replace(ByteBuf byteBuf) {
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(getProtocolVersion(), getStatus(), byteBuf, headers().copy(), trailingHeaders().copy());
            defaultFullHttpResponse.setDecoderResult(decoderResult());
            return defaultFullHttpResponse;
        }

        public FullHttpResponse setStatus(HttpResponseStatus httpResponseStatus) {
            ((HttpResponse) this.message).setStatus(httpResponseStatus);
            return this;
        }

        public HttpResponseStatus getStatus() {
            return ((HttpResponse) this.message).status();
        }

        public HttpResponseStatus status() {
            return getStatus();
        }

        public FullHttpResponse setProtocolVersion(HttpVersion httpVersion) {
            super.setProtocolVersion(httpVersion);
            return this;
        }

        public FullHttpResponse retain(int i) {
            super.retain(i);
            return this;
        }

        public FullHttpResponse retain() {
            super.retain();
            return this;
        }

        public FullHttpResponse touch(Object obj) {
            super.touch(obj);
            return this;
        }

        public FullHttpResponse touch() {
            super.touch();
            return this;
        }

        public String toString() {
            return HttpMessageUtil.appendFullResponse(new StringBuilder(256), this).toString();
        }
    }
}
