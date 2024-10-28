package io.netty.handler.codec.http.websocketx;

import io.netty.util.AsciiString;
import org.videolan.resources.Constants;

public enum WebSocketVersion {
    UNKNOWN(AsciiString.cached("")),
    V00(AsciiString.cached(Constants.GROUP_VIDEOS_FOLDER)),
    V07(AsciiString.cached("7")),
    V08(AsciiString.cached("8")),
    V13(AsciiString.cached("13"));
    
    private final AsciiString headerValue;

    private WebSocketVersion(AsciiString asciiString) {
        this.headerValue = asciiString;
    }

    public String toHttpHeaderValue() {
        return toAsciiString().toString();
    }

    /* access modifiers changed from: package-private */
    public AsciiString toAsciiString() {
        if (this != UNKNOWN) {
            return this.headerValue;
        }
        throw new IllegalStateException("Unknown web socket version: " + this);
    }
}
