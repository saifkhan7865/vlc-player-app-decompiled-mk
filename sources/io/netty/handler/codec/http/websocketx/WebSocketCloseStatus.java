package io.netty.handler.codec.http.websocketx;

import androidx.core.view.PointerIconCompat;
import io.netty.util.internal.ObjectUtil;
import okhttp3.internal.ws.WebSocketProtocol;
import org.fusesource.jansi.AnsiRenderer;

public final class WebSocketCloseStatus implements Comparable<WebSocketCloseStatus> {
    public static final WebSocketCloseStatus ABNORMAL_CLOSURE = new WebSocketCloseStatus(PointerIconCompat.TYPE_CELL, "Abnormal closure", false);
    public static final WebSocketCloseStatus BAD_GATEWAY = new WebSocketCloseStatus(PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, "Bad Gateway");
    public static final WebSocketCloseStatus EMPTY = new WebSocketCloseStatus(WebSocketProtocol.CLOSE_NO_STATUS_CODE, "Empty", false);
    public static final WebSocketCloseStatus ENDPOINT_UNAVAILABLE = new WebSocketCloseStatus(1001, "Endpoint unavailable");
    public static final WebSocketCloseStatus INTERNAL_SERVER_ERROR = new WebSocketCloseStatus(PointerIconCompat.TYPE_COPY, "Internal server error");
    public static final WebSocketCloseStatus INVALID_MESSAGE_TYPE = new WebSocketCloseStatus(PointerIconCompat.TYPE_HELP, "Invalid message type");
    public static final WebSocketCloseStatus INVALID_PAYLOAD_DATA = new WebSocketCloseStatus(PointerIconCompat.TYPE_CROSSHAIR, "Invalid payload data");
    public static final WebSocketCloseStatus MANDATORY_EXTENSION = new WebSocketCloseStatus(PointerIconCompat.TYPE_ALIAS, "Mandatory extension");
    public static final WebSocketCloseStatus MESSAGE_TOO_BIG = new WebSocketCloseStatus(PointerIconCompat.TYPE_VERTICAL_TEXT, "Message too big");
    public static final WebSocketCloseStatus NORMAL_CLOSURE = new WebSocketCloseStatus(1000, "Bye");
    public static final WebSocketCloseStatus POLICY_VIOLATION = new WebSocketCloseStatus(PointerIconCompat.TYPE_TEXT, "Policy violation");
    public static final WebSocketCloseStatus PROTOCOL_ERROR = new WebSocketCloseStatus(PointerIconCompat.TYPE_HAND, "Protocol error");
    public static final WebSocketCloseStatus SERVICE_RESTART = new WebSocketCloseStatus(PointerIconCompat.TYPE_NO_DROP, "Service Restart");
    public static final WebSocketCloseStatus TLS_HANDSHAKE_FAILED = new WebSocketCloseStatus(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, "TLS handshake failed", false);
    public static final WebSocketCloseStatus TRY_AGAIN_LATER = new WebSocketCloseStatus(PointerIconCompat.TYPE_ALL_SCROLL, "Try Again Later");
    private final String reasonText;
    private final int statusCode;
    private String text;

    public static boolean isValidStatusCode(int i) {
        return i < 0 || (1000 <= i && i <= 1003) || ((1007 <= i && i <= 1014) || 3000 <= i);
    }

    public WebSocketCloseStatus(int i, String str) {
        this(i, str, true);
    }

    public WebSocketCloseStatus(int i, String str, boolean z) {
        if (!z || isValidStatusCode(i)) {
            this.statusCode = i;
            this.reasonText = (String) ObjectUtil.checkNotNull(str, "reasonText");
            return;
        }
        throw new IllegalArgumentException("WebSocket close status code does NOT comply with RFC-6455: " + i);
    }

    public int code() {
        return this.statusCode;
    }

    public String reasonText() {
        return this.reasonText;
    }

    public int compareTo(WebSocketCloseStatus webSocketCloseStatus) {
        return code() - webSocketCloseStatus.code();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && this.statusCode == ((WebSocketCloseStatus) obj).statusCode) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.statusCode;
    }

    public String toString() {
        String str = this.text;
        if (str != null) {
            return str;
        }
        String str2 = code() + AnsiRenderer.CODE_TEXT_SEPARATOR + reasonText();
        this.text = str2;
        return str2;
    }

    public static WebSocketCloseStatus valueOf(int i) {
        switch (i) {
            case 1000:
                return NORMAL_CLOSURE;
            case 1001:
                return ENDPOINT_UNAVAILABLE;
            case PointerIconCompat.TYPE_HAND:
                return PROTOCOL_ERROR;
            case PointerIconCompat.TYPE_HELP:
                return INVALID_MESSAGE_TYPE;
            case WebSocketProtocol.CLOSE_NO_STATUS_CODE /*1005*/:
                return EMPTY;
            case PointerIconCompat.TYPE_CELL:
                return ABNORMAL_CLOSURE;
            case PointerIconCompat.TYPE_CROSSHAIR:
                return INVALID_PAYLOAD_DATA;
            case PointerIconCompat.TYPE_TEXT:
                return POLICY_VIOLATION;
            case PointerIconCompat.TYPE_VERTICAL_TEXT:
                return MESSAGE_TOO_BIG;
            case PointerIconCompat.TYPE_ALIAS:
                return MANDATORY_EXTENSION;
            case PointerIconCompat.TYPE_COPY:
                return INTERNAL_SERVER_ERROR;
            case PointerIconCompat.TYPE_NO_DROP:
                return SERVICE_RESTART;
            case PointerIconCompat.TYPE_ALL_SCROLL:
                return TRY_AGAIN_LATER;
            case PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW:
                return BAD_GATEWAY;
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW:
                return TLS_HANDSHAKE_FAILED;
            default:
                return new WebSocketCloseStatus(i, "Close status #" + i);
        }
    }
}
