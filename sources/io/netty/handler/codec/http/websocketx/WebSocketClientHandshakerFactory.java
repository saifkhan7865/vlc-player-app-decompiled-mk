package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.http.HttpHeaders;
import java.net.URI;

public final class WebSocketClientHandshakerFactory {
    private WebSocketClientHandshakerFactory() {
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders) {
        return newHandshaker(uri, webSocketVersion, str, z, httpHeaders, 65536);
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i) {
        return newHandshaker(uri, webSocketVersion, str, z, httpHeaders, i, true, false);
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3) {
        return newHandshaker(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, -1);
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j) {
        WebSocketVersion webSocketVersion2 = webSocketVersion;
        if (webSocketVersion2 == WebSocketVersion.V13) {
            return new WebSocketClientHandshaker13(uri, WebSocketVersion.V13, str, z, httpHeaders, i, z2, z3, j);
        } else if (webSocketVersion2 == WebSocketVersion.V08) {
            return new WebSocketClientHandshaker08(uri, WebSocketVersion.V08, str, z, httpHeaders, i, z2, z3, j);
        } else if (webSocketVersion2 == WebSocketVersion.V07) {
            return new WebSocketClientHandshaker07(uri, WebSocketVersion.V07, str, z, httpHeaders, i, z2, z3, j);
        } else if (webSocketVersion2 == WebSocketVersion.V00) {
            return new WebSocketClientHandshaker00(uri, WebSocketVersion.V00, str, httpHeaders, i, j);
        } else {
            throw new WebSocketClientHandshakeException("Protocol version " + webSocketVersion + " not supported.");
        }
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j, boolean z4) {
        WebSocketVersion webSocketVersion2 = webSocketVersion;
        if (webSocketVersion2 == WebSocketVersion.V13) {
            return new WebSocketClientHandshaker13(uri, WebSocketVersion.V13, str, z, httpHeaders, i, z2, z3, j, z4);
        } else if (webSocketVersion2 == WebSocketVersion.V08) {
            return new WebSocketClientHandshaker08(uri, WebSocketVersion.V08, str, z, httpHeaders, i, z2, z3, j, z4);
        } else if (webSocketVersion2 == WebSocketVersion.V07) {
            return new WebSocketClientHandshaker07(uri, WebSocketVersion.V07, str, z, httpHeaders, i, z2, z3, j, z4);
        } else if (webSocketVersion2 == WebSocketVersion.V00) {
            return new WebSocketClientHandshaker00(uri, WebSocketVersion.V00, str, httpHeaders, i, j, z4);
        } else {
            throw new WebSocketClientHandshakeException("Protocol version " + webSocketVersion + " not supported.");
        }
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j, boolean z4, boolean z5) {
        WebSocketVersion webSocketVersion2 = webSocketVersion;
        if (webSocketVersion2 == WebSocketVersion.V13) {
            return new WebSocketClientHandshaker13(uri, WebSocketVersion.V13, str, z, httpHeaders, i, z2, z3, j, z4, z5);
        } else if (webSocketVersion2 == WebSocketVersion.V08) {
            return new WebSocketClientHandshaker08(uri, WebSocketVersion.V08, str, z, httpHeaders, i, z2, z3, j, z4, z5);
        } else if (webSocketVersion2 == WebSocketVersion.V07) {
            return new WebSocketClientHandshaker07(uri, WebSocketVersion.V07, str, z, httpHeaders, i, z2, z3, j, z4, z5);
        } else if (webSocketVersion2 == WebSocketVersion.V00) {
            return new WebSocketClientHandshaker00(uri, WebSocketVersion.V00, str, httpHeaders, i, j, z4, z5);
        } else {
            throw new WebSocketClientHandshakeException("Protocol version " + webSocketVersion2 + " not supported.");
        }
    }
}
