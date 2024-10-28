package io.netty.handler.codec.http2;

import io.netty.util.collection.CharObjectHashMap;
import io.netty.util.internal.ObjectUtil;

public final class Http2Settings extends CharObjectHashMap<Long> {
    private static final int DEFAULT_CAPACITY = 13;
    private static final Long FALSE = 0L;
    private static final Long TRUE = 1L;

    public Http2Settings() {
        this(13);
    }

    public Http2Settings(int i, float f) {
        super(i, f);
    }

    public Http2Settings(int i) {
        super(i);
    }

    public Long put(char c, Long l) {
        verifyStandardSetting(c, l);
        return (Long) super.put(c, l);
    }

    public Long headerTableSize() {
        return (Long) get(1);
    }

    public Http2Settings headerTableSize(long j) {
        put(1, Long.valueOf(j));
        return this;
    }

    public Boolean pushEnabled() {
        Long l = (Long) get(2);
        if (l == null) {
            return null;
        }
        return Boolean.valueOf(TRUE.equals(l));
    }

    public Http2Settings pushEnabled(boolean z) {
        put(2, z ? TRUE : FALSE);
        return this;
    }

    public Long maxConcurrentStreams() {
        return (Long) get(3);
    }

    public Http2Settings maxConcurrentStreams(long j) {
        put(3, Long.valueOf(j));
        return this;
    }

    public Integer initialWindowSize() {
        return getIntValue(4);
    }

    public Http2Settings initialWindowSize(int i) {
        put(4, Long.valueOf((long) i));
        return this;
    }

    public Integer maxFrameSize() {
        return getIntValue(5);
    }

    public Http2Settings maxFrameSize(int i) {
        put(5, Long.valueOf((long) i));
        return this;
    }

    public Long maxHeaderListSize() {
        return (Long) get(6);
    }

    public Http2Settings maxHeaderListSize(long j) {
        put(6, Long.valueOf(j));
        return this;
    }

    public Http2Settings copyFrom(Http2Settings http2Settings) {
        clear();
        putAll(http2Settings);
        return this;
    }

    public Integer getIntValue(char c) {
        Long l = (Long) get(c);
        if (l == null) {
            return null;
        }
        return Integer.valueOf(l.intValue());
    }

    private static void verifyStandardSetting(int i, Long l) {
        ObjectUtil.checkNotNull(l, "value");
        switch (i) {
            case 1:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Setting HEADER_TABLE_SIZE is invalid: " + l + ", expected [0, 4294967295]");
                }
                return;
            case 2:
                if (l.longValue() != 0 && l.longValue() != 1) {
                    throw new IllegalArgumentException("Setting ENABLE_PUSH is invalid: " + l + ", expected [0, 1]");
                }
                return;
            case 3:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Setting MAX_CONCURRENT_STREAMS is invalid: " + l + ", expected [0, 4294967295]");
                }
                return;
            case 4:
                if (l.longValue() < 0 || l.longValue() > 2147483647L) {
                    throw new IllegalArgumentException("Setting INITIAL_WINDOW_SIZE is invalid: " + l + ", expected [0, 2147483647]");
                }
                return;
            case 5:
                if (!Http2CodecUtil.isMaxFrameSizeValid(l.intValue())) {
                    throw new IllegalArgumentException("Setting MAX_FRAME_SIZE is invalid: " + l + ", expected [16384, 16777215]");
                }
                return;
            case 6:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Setting MAX_HEADER_LIST_SIZE is invalid: " + l + ", expected [0, 4294967295]");
                }
                return;
            default:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Non-standard setting 0x" + Integer.toHexString(i) + " is invalid: " + l + ", expected unsigned 32-bit value");
                }
                return;
        }
    }

    /* access modifiers changed from: protected */
    public String keyToString(char c) {
        switch (c) {
            case 1:
                return "HEADER_TABLE_SIZE";
            case 2:
                return "ENABLE_PUSH";
            case 3:
                return "MAX_CONCURRENT_STREAMS";
            case 4:
                return "INITIAL_WINDOW_SIZE";
            case 5:
                return "MAX_FRAME_SIZE";
            case 6:
                return "MAX_HEADER_LIST_SIZE";
            default:
                return "0x" + Integer.toHexString(c);
        }
    }

    public static Http2Settings defaultSettings() {
        return new Http2Settings().maxHeaderListSize(8192);
    }
}
