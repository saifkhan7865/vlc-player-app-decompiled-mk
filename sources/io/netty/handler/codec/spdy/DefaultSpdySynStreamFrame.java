package io.netty.handler.codec.spdy;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

public class DefaultSpdySynStreamFrame extends DefaultSpdyHeadersFrame implements SpdySynStreamFrame {
    private int associatedStreamId;
    private byte priority;
    private boolean unidirectional;

    public DefaultSpdySynStreamFrame(int i, int i2, byte b) {
        this(i, i2, b, true);
    }

    public DefaultSpdySynStreamFrame(int i, int i2, byte b, boolean z) {
        super(i, z);
        setAssociatedStreamId(i2);
        setPriority(b);
    }

    public SpdySynStreamFrame setStreamId(int i) {
        super.setStreamId(i);
        return this;
    }

    public SpdySynStreamFrame setLast(boolean z) {
        super.setLast(z);
        return this;
    }

    public SpdySynStreamFrame setInvalid() {
        super.setInvalid();
        return this;
    }

    public int associatedStreamId() {
        return this.associatedStreamId;
    }

    public SpdySynStreamFrame setAssociatedStreamId(int i) {
        ObjectUtil.checkPositiveOrZero(i, "associatedStreamId");
        this.associatedStreamId = i;
        return this;
    }

    public byte priority() {
        return this.priority;
    }

    public SpdySynStreamFrame setPriority(byte b) {
        if (b < 0 || b > 7) {
            throw new IllegalArgumentException("Priority must be between 0 and 7 inclusive: " + b);
        }
        this.priority = b;
        return this;
    }

    public boolean isUnidirectional() {
        return this.unidirectional;
    }

    public SpdySynStreamFrame setUnidirectional(boolean z) {
        this.unidirectional = z;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName((Object) this));
        sb.append("(last: ");
        sb.append(isLast());
        sb.append("; unidirectional: ");
        sb.append(isUnidirectional());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(streamId());
        sb.append(StringUtil.NEWLINE);
        if (this.associatedStreamId != 0) {
            sb.append("--> Associated-To-Stream-ID = ");
            sb.append(associatedStreamId());
            sb.append(StringUtil.NEWLINE);
        }
        sb.append("--> Priority = ");
        sb.append(priority());
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Headers:");
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }
}
