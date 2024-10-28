package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.HttpStatusClass;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http2.HttpConversionUtil;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.List;

@ChannelHandler.Sharable
public class Http2StreamFrameToHttpObjectCodec extends MessageToMessageCodec<Http2StreamFrame, HttpObject> {
    private static final AttributeKey<HttpScheme> SCHEME_ATTR_KEY = AttributeKey.valueOf(HttpScheme.class, "STREAMFRAMECODEC_SCHEME");
    private final boolean isServer;
    private final boolean validateHeaders;

    public Http2StreamFrameToHttpObjectCodec(boolean z, boolean z2) {
        this.isServer = z;
        this.validateHeaders = z2;
    }

    public Http2StreamFrameToHttpObjectCodec(boolean z) {
        this(z, true);
    }

    public boolean acceptInboundMessage(Object obj) throws Exception {
        return (obj instanceof Http2HeadersFrame) || (obj instanceof Http2DataFrame);
    }

    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, Http2StreamFrame http2StreamFrame, List<Object> list) throws Exception {
        int i;
        if (http2StreamFrame instanceof Http2HeadersFrame) {
            Http2HeadersFrame http2HeadersFrame = (Http2HeadersFrame) http2StreamFrame;
            Http2Headers headers = http2HeadersFrame.headers();
            Http2FrameStream stream = http2HeadersFrame.stream();
            if (stream == null) {
                i = 0;
            } else {
                i = stream.id();
            }
            CharSequence status = headers.status();
            if (status != null && isInformationalResponseHeaderFrame(status)) {
                list.add(newFullMessage(i, headers, channelHandlerContext.alloc()));
            } else if (!http2HeadersFrame.isEndStream()) {
                HttpMessage newMessage = newMessage(i, headers);
                if ((status == null || !isContentAlwaysEmpty(status)) && !HttpUtil.isContentLengthSet(newMessage)) {
                    newMessage.headers().add((CharSequence) HttpHeaderNames.TRANSFER_ENCODING, (Object) HttpHeaderValues.CHUNKED);
                }
                list.add(newMessage);
            } else if (headers.method() == null && status == null) {
                DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);
                HttpConversionUtil.addHttp2ToHttpHeaders(i, headers, defaultLastHttpContent.trailingHeaders(), HttpVersion.HTTP_1_1, true, true);
                list.add(defaultLastHttpContent);
            } else {
                list.add(newFullMessage(i, headers, channelHandlerContext.alloc()));
            }
        } else if (http2StreamFrame instanceof Http2DataFrame) {
            Http2DataFrame http2DataFrame = (Http2DataFrame) http2StreamFrame;
            if (http2DataFrame.isEndStream()) {
                list.add(new DefaultLastHttpContent(http2DataFrame.content().retain(), this.validateHeaders));
            } else {
                list.add(new DefaultHttpContent(http2DataFrame.content().retain()));
            }
        }
    }

    private void encodeLastContent(LastHttpContent lastHttpContent, List<Object> list) {
        boolean z = !(lastHttpContent instanceof FullHttpMessage) && lastHttpContent.trailingHeaders().isEmpty();
        if (lastHttpContent.content().isReadable() || z) {
            list.add(new DefaultHttp2DataFrame(lastHttpContent.content().retain(), lastHttpContent.trailingHeaders().isEmpty()));
        }
        if (!lastHttpContent.trailingHeaders().isEmpty()) {
            list.add(new DefaultHttp2HeadersFrame(HttpConversionUtil.toHttp2Headers(lastHttpContent.trailingHeaders(), this.validateHeaders), true));
        }
    }

    /* access modifiers changed from: protected */
    public void encode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        boolean z;
        if (httpObject instanceof HttpResponse) {
            HttpResponse httpResponse = (HttpResponse) httpObject;
            HttpResponseStatus status = httpResponse.status();
            int code = status.code();
            if (status.codeClass() == HttpStatusClass.INFORMATIONAL && code != 101) {
                if (httpResponse instanceof FullHttpResponse) {
                    list.add(new DefaultHttp2HeadersFrame(toHttp2Headers(channelHandlerContext, httpResponse), false));
                    return;
                }
                throw new EncoderException(status + " must be a FullHttpResponse");
            }
        }
        if (httpObject instanceof HttpMessage) {
            Http2Headers http2Headers = toHttp2Headers(channelHandlerContext, (HttpMessage) httpObject);
            if (httpObject instanceof FullHttpMessage) {
                FullHttpMessage fullHttpMessage = (FullHttpMessage) httpObject;
                if (!fullHttpMessage.content().isReadable() && fullHttpMessage.trailingHeaders().isEmpty()) {
                    z = true;
                    list.add(new DefaultHttp2HeadersFrame(http2Headers, z));
                }
            }
            z = false;
            list.add(new DefaultHttp2HeadersFrame(http2Headers, z));
        }
        if (httpObject instanceof LastHttpContent) {
            encodeLastContent((LastHttpContent) httpObject, list);
        } else if (httpObject instanceof HttpContent) {
            list.add(new DefaultHttp2DataFrame(((HttpContent) httpObject).content().retain(), false));
        }
    }

    private Http2Headers toHttp2Headers(ChannelHandlerContext channelHandlerContext, HttpMessage httpMessage) {
        if (httpMessage instanceof HttpRequest) {
            httpMessage.headers().set((CharSequence) HttpConversionUtil.ExtensionHeaderNames.SCHEME.text(), (Object) connectionScheme(channelHandlerContext));
        }
        return HttpConversionUtil.toHttp2Headers(httpMessage, this.validateHeaders);
    }

    private HttpMessage newMessage(int i, Http2Headers http2Headers) throws Http2Exception {
        if (this.isServer) {
            return HttpConversionUtil.toHttpRequest(i, http2Headers, this.validateHeaders);
        }
        return HttpConversionUtil.toHttpResponse(i, http2Headers, this.validateHeaders);
    }

    private FullHttpMessage newFullMessage(int i, Http2Headers http2Headers, ByteBufAllocator byteBufAllocator) throws Http2Exception {
        if (this.isServer) {
            return HttpConversionUtil.toFullHttpRequest(i, http2Headers, byteBufAllocator, this.validateHeaders);
        }
        return HttpConversionUtil.toFullHttpResponse(i, http2Headers, byteBufAllocator, this.validateHeaders);
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerAdded(channelHandlerContext);
        Attribute<HttpScheme> connectionSchemeAttribute = connectionSchemeAttribute(channelHandlerContext);
        if (connectionSchemeAttribute.get() == null) {
            connectionSchemeAttribute.set(isSsl(channelHandlerContext) ? HttpScheme.HTTPS : HttpScheme.HTTP);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSsl(ChannelHandlerContext channelHandlerContext) {
        return connectionChannel(channelHandlerContext).pipeline().get(SslHandler.class) != null;
    }

    private static HttpScheme connectionScheme(ChannelHandlerContext channelHandlerContext) {
        HttpScheme httpScheme = connectionSchemeAttribute(channelHandlerContext).get();
        return httpScheme == null ? HttpScheme.HTTP : httpScheme;
    }

    private static Attribute<HttpScheme> connectionSchemeAttribute(ChannelHandlerContext channelHandlerContext) {
        return connectionChannel(channelHandlerContext).attr(SCHEME_ATTR_KEY);
    }

    private static Channel connectionChannel(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        return channel instanceof Http2StreamChannel ? channel.parent() : channel;
    }

    private static boolean isInformationalResponseHeaderFrame(CharSequence charSequence) {
        if (charSequence.length() != 3) {
            return false;
        }
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        char charAt3 = charSequence.charAt(2);
        if (charAt != '1' || charAt2 < '0' || charAt2 > '9' || charAt3 < '0' || charAt3 > '9' || charAt3 == '1') {
            return false;
        }
        return true;
    }

    private static boolean isContentAlwaysEmpty(CharSequence charSequence) {
        if (charSequence.length() != 3) {
            return false;
        }
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        char charAt3 = charSequence.charAt(2);
        if ((charAt == '2' || charAt == '3') && charAt2 == '0' && charAt3 == '4') {
            return true;
        }
        return false;
    }
}
