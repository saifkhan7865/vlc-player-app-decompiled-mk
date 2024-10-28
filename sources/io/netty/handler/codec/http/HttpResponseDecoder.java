package io.netty.handler.codec.http;

import androidx.room.RoomDatabase;

public class HttpResponseDecoder extends HttpObjectDecoder {
    private static final HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(RoomDatabase.MAX_BIND_PARAMETER_CNT, "Unknown");

    /* access modifiers changed from: protected */
    public boolean isDecodingRequest() {
        return false;
    }

    public HttpResponseDecoder() {
    }

    public HttpResponseDecoder(int i, int i2, int i3) {
        super(i, i2, i3, true);
    }

    public HttpResponseDecoder(int i, int i2, int i3, boolean z) {
        super(i, i2, i3, true, z);
    }

    public HttpResponseDecoder(int i, int i2, int i3, boolean z, int i4) {
        super(i, i2, i3, true, z, i4);
    }

    public HttpResponseDecoder(int i, int i2, int i3, boolean z, int i4, boolean z2) {
        super(i, i2, i3, true, z, i4, z2);
    }

    public HttpResponseDecoder(int i, int i2, int i3, boolean z, int i4, boolean z2, boolean z3) {
        super(i, i2, i3, true, z, i4, z2, z3);
    }

    /* access modifiers changed from: protected */
    public HttpMessage createMessage(String[] strArr) {
        return new DefaultHttpResponse(HttpVersion.valueOf(strArr[0]), HttpResponseStatus.valueOf(Integer.parseInt(strArr[1]), strArr[2]), this.validateHeaders);
    }

    /* access modifiers changed from: protected */
    public HttpMessage createInvalidMessage() {
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, UNKNOWN_STATUS, this.validateHeaders);
    }
}
