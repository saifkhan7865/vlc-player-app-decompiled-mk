package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.internal.ObjectUtil;

public final class RtspVersions {
    public static final HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);

    public static HttpVersion valueOf(String str) {
        ObjectUtil.checkNotNull(str, "text");
        String upperCase = str.trim().toUpperCase();
        if ("RTSP/1.0".equals(upperCase)) {
            return RTSP_1_0;
        }
        return new HttpVersion(upperCase, true);
    }

    private RtspVersions() {
    }
}
