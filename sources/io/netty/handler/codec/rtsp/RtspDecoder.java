package io.netty.handler.codec.rtsp;

import androidx.room.RoomDatabase;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.regex.Pattern;

public class RtspDecoder extends HttpObjectDecoder {
    public static final int DEFAULT_MAX_CONTENT_LENGTH = 8192;
    private static final HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(RoomDatabase.MAX_BIND_PARAMETER_CNT, "Unknown");
    private static final Pattern versionPattern = Pattern.compile("RTSP/\\d\\.\\d");
    private boolean isDecodingRequest;

    public RtspDecoder() {
        this(4096, 8192, 8192);
    }

    public RtspDecoder(int i, int i2, int i3) {
        super(i, i2, i3 * 2, false);
    }

    public RtspDecoder(int i, int i2, int i3, boolean z) {
        super(i, i2, i3 * 2, false, z);
    }

    /* access modifiers changed from: protected */
    public HttpMessage createMessage(String[] strArr) throws Exception {
        if (versionPattern.matcher(strArr[0]).matches()) {
            this.isDecodingRequest = false;
            return new DefaultHttpResponse(RtspVersions.valueOf(strArr[0]), new HttpResponseStatus(Integer.parseInt(strArr[1]), strArr[2]), this.validateHeaders);
        }
        this.isDecodingRequest = true;
        return new DefaultHttpRequest(RtspVersions.valueOf(strArr[2]), RtspMethods.valueOf(strArr[0]), strArr[1], this.validateHeaders);
    }

    /* access modifiers changed from: protected */
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        return super.isContentAlwaysEmpty(httpMessage) || !httpMessage.headers().contains((CharSequence) RtspHeaderNames.CONTENT_LENGTH);
    }

    /* access modifiers changed from: protected */
    public HttpMessage createInvalidMessage() {
        if (this.isDecodingRequest) {
            return new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.OPTIONS, "/bad-request", this.validateHeaders);
        }
        return new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, UNKNOWN_STATUS, this.validateHeaders);
    }

    /* access modifiers changed from: protected */
    public boolean isDecodingRequest() {
        return this.isDecodingRequest;
    }
}
