package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import java.util.Map;

public class DefaultSpdyHeadersFrame extends DefaultSpdyStreamFrame implements SpdyHeadersFrame {
    private final SpdyHeaders headers;
    private boolean invalid;
    private boolean truncated;

    public DefaultSpdyHeadersFrame(int i) {
        this(i, true);
    }

    public DefaultSpdyHeadersFrame(int i, boolean z) {
        super(i);
        this.headers = new DefaultSpdyHeaders(z);
    }

    public SpdyHeadersFrame setStreamId(int i) {
        super.setStreamId(i);
        return this;
    }

    public SpdyHeadersFrame setLast(boolean z) {
        super.setLast(z);
        return this;
    }

    public boolean isInvalid() {
        return this.invalid;
    }

    public SpdyHeadersFrame setInvalid() {
        this.invalid = true;
        return this;
    }

    public boolean isTruncated() {
        return this.truncated;
    }

    public SpdyHeadersFrame setTruncated() {
        this.truncated = true;
        return this;
    }

    public SpdyHeaders headers() {
        return this.headers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName((Object) this));
        sb.append("(last: ");
        sb.append(isLast());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(streamId());
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Headers:");
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void appendHeaders(StringBuilder sb) {
        for (Map.Entry entry : headers()) {
            sb.append("    ");
            sb.append((CharSequence) entry.getKey());
            sb.append(": ");
            sb.append((CharSequence) entry.getValue());
            sb.append(StringUtil.NEWLINE);
        }
    }
}
