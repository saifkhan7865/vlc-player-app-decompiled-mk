package io.netty.handler.codec.http.cors;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.List;

public class CorsHandler extends ChannelDuplexHandler {
    private static final String ANY_ORIGIN = "*";
    private static final String NULL_ORIGIN = "null";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CorsHandler.class);
    private CorsConfig config;
    private final List<CorsConfig> configList;
    private final boolean isShortCircuit;
    private HttpRequest request;

    public CorsHandler(CorsConfig corsConfig) {
        this(Collections.singletonList(ObjectUtil.checkNotNull(corsConfig, "config")), corsConfig.isShortCircuit());
    }

    public CorsHandler(List<CorsConfig> list, boolean z) {
        ObjectUtil.checkNonEmpty(list, "configList");
        this.configList = list;
        this.isShortCircuit = z;
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) obj;
            this.request = httpRequest;
            String str = httpRequest.headers().get((CharSequence) HttpHeaderNames.ORIGIN);
            this.config = getForOrigin(str);
            if (isPreflightRequest(this.request)) {
                handlePreflight(channelHandlerContext, this.request);
                return;
            } else if (this.isShortCircuit && str != null && this.config == null) {
                forbidden(channelHandlerContext, this.request);
                return;
            }
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    private void handlePreflight(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.OK, true, true);
        if (setOrigin(defaultFullHttpResponse)) {
            setAllowMethods(defaultFullHttpResponse);
            setAllowHeaders(defaultFullHttpResponse);
            setAllowCredentials(defaultFullHttpResponse);
            setMaxAge(defaultFullHttpResponse);
            setPreflightHeaders(defaultFullHttpResponse);
            setAllowPrivateNetwork(defaultFullHttpResponse);
        }
        if (!defaultFullHttpResponse.headers().contains((CharSequence) HttpHeaderNames.CONTENT_LENGTH)) {
            defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) HttpHeaderValues.ZERO);
        }
        ReferenceCountUtil.release(httpRequest);
        respond(channelHandlerContext, httpRequest, defaultFullHttpResponse);
    }

    private void setPreflightHeaders(HttpResponse httpResponse) {
        httpResponse.headers().add(this.config.preflightResponseHeaders());
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.netty.handler.codec.http.cors.CorsConfig getForOrigin(java.lang.String r4) {
        /*
            r3 = this;
            java.util.List<io.netty.handler.codec.http.cors.CorsConfig> r0 = r3.configList
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0033
            java.lang.Object r1 = r0.next()
            io.netty.handler.codec.http.cors.CorsConfig r1 = (io.netty.handler.codec.http.cors.CorsConfig) r1
            boolean r2 = r1.isAnyOriginSupported()
            if (r2 == 0) goto L_0x0019
            return r1
        L_0x0019:
            java.util.Set r2 = r1.origins()
            boolean r2 = r2.contains(r4)
            if (r2 == 0) goto L_0x0024
            return r1
        L_0x0024:
            boolean r2 = r1.isNullOriginAllowed()
            if (r2 != 0) goto L_0x0032
            java.lang.String r2 = "null"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0006
        L_0x0032:
            return r1
        L_0x0033:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.cors.CorsHandler.getForOrigin(java.lang.String):io.netty.handler.codec.http.cors.CorsConfig");
    }

    private boolean setOrigin(HttpResponse httpResponse) {
        String str = this.request.headers().get((CharSequence) HttpHeaderNames.ORIGIN);
        if (str == null || this.config == null) {
            return false;
        }
        if ("null".equals(str) && this.config.isNullOriginAllowed()) {
            setNullOrigin(httpResponse);
            return true;
        } else if (this.config.isAnyOriginSupported()) {
            if (this.config.isCredentialsAllowed()) {
                echoRequestOrigin(httpResponse);
                setVaryHeader(httpResponse);
            } else {
                setAnyOrigin(httpResponse);
            }
            return true;
        } else if (this.config.origins().contains(str)) {
            setOrigin(httpResponse, str);
            setVaryHeader(httpResponse);
            return true;
        } else {
            logger.debug("Request origin [{}]] was not among the configured origins [{}]", str, this.config.origins());
            return false;
        }
    }

    private void echoRequestOrigin(HttpResponse httpResponse) {
        setOrigin(httpResponse, this.request.headers().get((CharSequence) HttpHeaderNames.ORIGIN));
    }

    private static void setVaryHeader(HttpResponse httpResponse) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.VARY, (Object) HttpHeaderNames.ORIGIN);
    }

    private static void setAnyOrigin(HttpResponse httpResponse) {
        setOrigin(httpResponse, "*");
    }

    private static void setNullOrigin(HttpResponse httpResponse) {
        setOrigin(httpResponse, "null");
    }

    private static void setOrigin(HttpResponse httpResponse, String str) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, (Object) str);
    }

    private void setAllowCredentials(HttpResponse httpResponse) {
        if (this.config.isCredentialsAllowed() && !httpResponse.headers().get((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN).equals("*")) {
            httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, (Object) "true");
        }
    }

    private static boolean isPreflightRequest(HttpRequest httpRequest) {
        HttpHeaders headers = httpRequest.headers();
        return HttpMethod.OPTIONS.equals(httpRequest.method()) && headers.contains((CharSequence) HttpHeaderNames.ORIGIN) && headers.contains((CharSequence) HttpHeaderNames.ACCESS_CONTROL_REQUEST_METHOD);
    }

    private void setExposeHeaders(HttpResponse httpResponse) {
        if (!this.config.exposedHeaders().isEmpty()) {
            httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_EXPOSE_HEADERS, (Iterable<?>) this.config.exposedHeaders());
        }
    }

    private void setAllowMethods(HttpResponse httpResponse) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, (Iterable<?>) this.config.allowedRequestMethods());
    }

    private void setAllowHeaders(HttpResponse httpResponse) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, (Iterable<?>) this.config.allowedRequestHeaders());
    }

    private void setMaxAge(HttpResponse httpResponse) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_MAX_AGE, (Object) Long.valueOf(this.config.maxAge()));
    }

    private void setAllowPrivateNetwork(HttpResponse httpResponse) {
        if (!this.request.headers().contains((CharSequence) HttpHeaderNames.ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK)) {
            return;
        }
        if (this.config.isPrivateNetworkAllowed()) {
            httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK, (Object) "true");
        } else {
            httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK, (Object) "false");
        }
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        CorsConfig corsConfig = this.config;
        if (corsConfig != null && corsConfig.isCorsSupportEnabled() && (obj instanceof HttpResponse)) {
            HttpResponse httpResponse = (HttpResponse) obj;
            if (setOrigin(httpResponse)) {
                setAllowCredentials(httpResponse);
                setExposeHeaders(httpResponse);
            }
        }
        channelHandlerContext.write(obj, channelPromise);
    }

    private static void forbidden(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.FORBIDDEN, channelHandlerContext.alloc().buffer(0));
        defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) HttpHeaderValues.ZERO);
        ReferenceCountUtil.release(httpRequest);
        respond(channelHandlerContext, httpRequest, defaultFullHttpResponse);
    }

    private static void respond(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, HttpResponse httpResponse) {
        boolean isKeepAlive = HttpUtil.isKeepAlive(httpRequest);
        HttpUtil.setKeepAlive(httpResponse, isKeepAlive);
        ChannelFuture writeAndFlush = channelHandlerContext.writeAndFlush(httpResponse);
        if (!isKeepAlive) {
            writeAndFlush.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
