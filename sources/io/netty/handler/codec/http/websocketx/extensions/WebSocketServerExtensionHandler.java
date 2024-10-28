package io.netty.handler.codec.http.websocketx.extensions;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class WebSocketServerExtensionHandler extends ChannelDuplexHandler {
    private final List<WebSocketServerExtensionHandshaker> extensionHandshakers;
    private final Queue<List<WebSocketServerExtension>> validExtensions = new ArrayDeque(4);

    public WebSocketServerExtensionHandler(WebSocketServerExtensionHandshaker... webSocketServerExtensionHandshakerArr) {
        this.extensionHandshakers = Arrays.asList(ObjectUtil.checkNonEmpty((T[]) webSocketServerExtensionHandshakerArr, "extensionHandshakers"));
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj == LastHttpContent.EMPTY_LAST_CONTENT) {
            super.channelRead(channelHandlerContext, obj);
        } else if (obj instanceof DefaultHttpRequest) {
            onHttpRequestChannelRead(channelHandlerContext, (DefaultHttpRequest) obj);
        } else if (obj instanceof HttpRequest) {
            onHttpRequestChannelRead(channelHandlerContext, (HttpRequest) obj);
        } else {
            super.channelRead(channelHandlerContext, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void onHttpRequestChannelRead(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        String asString;
        List list = null;
        if (WebSocketExtensionUtil.isWebsocketUpgrade(httpRequest.headers()) && (asString = httpRequest.headers().getAsString(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS)) != null) {
            int i = 0;
            ArrayList arrayList = null;
            for (WebSocketExtensionData next : WebSocketExtensionUtil.extractExtensions(asString)) {
                Iterator<WebSocketServerExtensionHandshaker> it = this.extensionHandshakers.iterator();
                WebSocketServerExtension webSocketServerExtension = null;
                while (webSocketServerExtension == null && it.hasNext()) {
                    webSocketServerExtension = it.next().handshakeExtension(next);
                }
                if (webSocketServerExtension != null && (webSocketServerExtension.rsv() & i) == 0) {
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    i |= webSocketServerExtension.rsv();
                    arrayList.add(webSocketServerExtension);
                }
            }
            list = arrayList;
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        this.validExtensions.offer(list);
        super.channelRead(channelHandlerContext, httpRequest);
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (obj == Unpooled.EMPTY_BUFFER || (obj instanceof ByteBuf)) {
            super.write(channelHandlerContext, obj, channelPromise);
        } else if (obj instanceof DefaultHttpResponse) {
            onHttpResponseWrite(channelHandlerContext, (DefaultHttpResponse) obj, channelPromise);
        } else if (obj instanceof HttpResponse) {
            onHttpResponseWrite(channelHandlerContext, (HttpResponse) obj, channelPromise);
        } else {
            super.write(channelHandlerContext, obj, channelPromise);
        }
    }

    /* access modifiers changed from: protected */
    public void onHttpResponseWrite(ChannelHandlerContext channelHandlerContext, HttpResponse httpResponse, ChannelPromise channelPromise) throws Exception {
        List poll = this.validExtensions.poll();
        if (HttpResponseStatus.SWITCHING_PROTOCOLS.equals(httpResponse.status())) {
            handlePotentialUpgrade(channelHandlerContext, channelPromise, httpResponse, poll);
        }
        super.write(channelHandlerContext, httpResponse, channelPromise);
    }

    private void handlePotentialUpgrade(final ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise, HttpResponse httpResponse, final List<WebSocketServerExtension> list) {
        HttpHeaders headers = httpResponse.headers();
        if (WebSocketExtensionUtil.isWebsocketUpgrade(headers)) {
            if (list != null && !list.isEmpty()) {
                String asString = headers.getAsString(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS);
                ArrayList arrayList = new ArrayList(this.extensionHandshakers.size());
                for (WebSocketServerExtension newReponseData : list) {
                    arrayList.add(newReponseData.newReponseData());
                }
                String computeMergeExtensionsHeaderValue = WebSocketExtensionUtil.computeMergeExtensionsHeaderValue(asString, arrayList);
                channelPromise.addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture channelFuture) {
                        if (channelFuture.isSuccess()) {
                            for (WebSocketServerExtension webSocketServerExtension : list) {
                                WebSocketExtensionDecoder newExtensionDecoder = webSocketServerExtension.newExtensionDecoder();
                                WebSocketExtensionEncoder newExtensionEncoder = webSocketServerExtension.newExtensionEncoder();
                                String name = channelHandlerContext.name();
                                channelHandlerContext.pipeline().addAfter(name, newExtensionDecoder.getClass().getName(), newExtensionDecoder).addAfter(name, newExtensionEncoder.getClass().getName(), newExtensionEncoder);
                            }
                        }
                    }
                });
                if (computeMergeExtensionsHeaderValue != null) {
                    headers.set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS, (Object) computeMergeExtensionsHeaderValue);
                }
            }
            channelPromise.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) {
                    if (channelFuture.isSuccess()) {
                        channelHandlerContext.pipeline().remove((ChannelHandler) WebSocketServerExtensionHandler.this);
                    }
                }
            });
        }
    }
}
