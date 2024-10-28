package io.netty.handler.codec.http2;

public final class Http2Flags {
    public static final short ACK = 1;
    public static final short END_HEADERS = 4;
    public static final short END_STREAM = 1;
    public static final short PADDED = 8;
    public static final short PRIORITY = 32;
    private short value;

    public Http2Flags() {
    }

    public Http2Flags(short s) {
        this.value = s;
    }

    public short value() {
        return this.value;
    }

    public boolean endOfStream() {
        return isFlagSet(1);
    }

    public boolean endOfHeaders() {
        return isFlagSet(4);
    }

    public boolean priorityPresent() {
        return isFlagSet(32);
    }

    public boolean ack() {
        return isFlagSet(1);
    }

    public boolean paddingPresent() {
        return isFlagSet(8);
    }

    public int getNumPriorityBytes() {
        return priorityPresent() ? 5 : 0;
    }

    public int getPaddingPresenceFieldLength() {
        return paddingPresent() ? 1 : 0;
    }

    public Http2Flags endOfStream(boolean z) {
        return setFlag(z, 1);
    }

    public Http2Flags endOfHeaders(boolean z) {
        return setFlag(z, 4);
    }

    public Http2Flags priorityPresent(boolean z) {
        return setFlag(z, 32);
    }

    public Http2Flags paddingPresent(boolean z) {
        return setFlag(z, 8);
    }

    public Http2Flags ack(boolean z) {
        return setFlag(z, 1);
    }

    public Http2Flags setFlag(boolean z, short s) {
        if (z) {
            this.value = (short) (this.value | s);
        } else {
            this.value = (short) (this.value & (s ^ -1));
        }
        return this;
    }

    public boolean isFlagSet(short s) {
        return (s & this.value) != 0;
    }

    public int hashCode() {
        return 31 + this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && this.value == ((Http2Flags) obj).value) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("value = ");
        sb.append(this.value);
        sb.append(" (");
        if (ack()) {
            sb.append("ACK,");
        }
        if (endOfHeaders()) {
            sb.append("END_OF_HEADERS,");
        }
        if (endOfStream()) {
            sb.append("END_OF_STREAM,");
        }
        if (priorityPresent()) {
            sb.append("PRIORITY_PRESENT,");
        }
        if (paddingPresent()) {
            sb.append("PADDING_PRESENT,");
        }
        sb.append(')');
        return sb.toString();
    }
}
