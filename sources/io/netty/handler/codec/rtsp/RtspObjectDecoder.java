package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectDecoder;

@Deprecated
public abstract class RtspObjectDecoder extends HttpObjectDecoder {
    protected RtspObjectDecoder() {
        this(4096, 8192, 8192);
    }

    protected RtspObjectDecoder(int i, int i2, int i3) {
        super(i, i2, i3 * 2, false);
    }

    protected RtspObjectDecoder(int i, int i2, int i3, boolean z) {
        super(i, i2, i3 * 2, false, z);
    }

    /* access modifiers changed from: protected */
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        boolean isContentAlwaysEmpty = super.isContentAlwaysEmpty(httpMessage);
        if (!isContentAlwaysEmpty && httpMessage.headers().contains((CharSequence) RtspHeaderNames.CONTENT_LENGTH)) {
            return isContentAlwaysEmpty;
        }
        return true;
    }
}
