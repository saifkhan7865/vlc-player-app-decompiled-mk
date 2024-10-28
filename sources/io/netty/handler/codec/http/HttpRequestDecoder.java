package io.netty.handler.codec.http;

import com.google.common.base.Ascii;
import io.netty.util.AsciiString;

public class HttpRequestDecoder extends HttpObjectDecoder {
    private static final long CONNECTION_AS_LONG_0 = 7598807758576447299L;
    private static final short CONNECTION_AS_SHORT_1 = 28271;
    private static final long CONTENT_AS_LONG = 3275364211029339971L;
    private static final AsciiString Connection = AsciiString.cached("Connection");
    private static final AsciiString ContentLength = AsciiString.cached("Content-Length");
    private static final AsciiString ContentType = AsciiString.cached("Content-Type");
    private static final int GET_AS_INT = 5522759;
    private static final int HOST_AS_INT = 1953722184;
    private static final long HTTP_1_0_AS_LONG = 3471766442030158920L;
    private static final long HTTP_1_1_AS_LONG = 3543824036068086856L;
    private static final AsciiString Host = AsciiString.cached("Host");
    private static final long LENGTH_AS_LONG = 114849160783180L;
    private static final int POST_AS_INT = 1414745936;
    private static final int TYPE_AS_INT = 1701869908;

    /* access modifiers changed from: protected */
    public boolean isDecodingRequest() {
        return true;
    }

    public HttpRequestDecoder() {
    }

    public HttpRequestDecoder(int i, int i2, int i3) {
        super(i, i2, i3, true);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z) {
        super(i, i2, i3, true, z);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z, int i4) {
        super(i, i2, i3, true, z, i4);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z, int i4, boolean z2) {
        super(i, i2, i3, true, z, i4, z2);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z, int i4, boolean z2, boolean z3) {
        super(i, i2, i3, true, z, i4, z2, z3);
    }

    /* access modifiers changed from: protected */
    public HttpMessage createMessage(String[] strArr) throws Exception {
        return new DefaultHttpRequest(HttpVersion.valueOf(strArr[2]), HttpMethod.valueOf(strArr[0]), strArr[1], this.validateHeaders);
    }

    /* access modifiers changed from: protected */
    public AsciiString splitHeaderName(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        if (b == 72 && i2 == 4) {
            if (isHost(bArr, i)) {
                return Host;
            }
        } else if (b == 67) {
            if (i2 == 10) {
                if (isConnection(bArr, i)) {
                    return Connection;
                }
            } else if (i2 == 12) {
                if (isContentType(bArr, i)) {
                    return ContentType;
                }
            } else if (i2 == 14 && isContentLength(bArr, i)) {
                return ContentLength;
            }
        }
        return super.splitHeaderName(bArr, i, i2);
    }

    private static boolean isHost(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) | ((bArr[i] | (bArr[i + 1] << 8)) | (bArr[i + 2] << Ascii.DLE))) == HOST_AS_INT;
    }

    private static boolean isConnection(byte[] bArr, int i) {
        if ((((long) (bArr[i] | (bArr[i + 1] << 8) | (bArr[i + 2] << Ascii.DLE) | (bArr[i + 3] << Ascii.CAN))) | (((long) bArr[i + 4]) << 32) | (((long) bArr[i + 5]) << 40) | (((long) bArr[i + 6]) << 48) | (((long) bArr[i + 7]) << 56)) != CONNECTION_AS_LONG_0) {
            return false;
        }
        return ((short) ((bArr[i + 9] << 8) | bArr[i + 8])) == 28271;
    }

    private static boolean isContentType(byte[] bArr, int i) {
        if ((((long) (bArr[i] | (bArr[i + 1] << 8) | (bArr[i + 2] << Ascii.DLE) | (bArr[i + 3] << Ascii.CAN))) | (((long) bArr[i + 4]) << 32) | (((long) bArr[i + 5]) << 40) | (((long) bArr[i + 6]) << 48) | (((long) bArr[i + 7]) << 56)) != CONTENT_AS_LONG) {
            return false;
        }
        return ((bArr[i + 11] << Ascii.CAN) | ((bArr[i + 8] | (bArr[i + 9] << 8)) | (bArr[i + 10] << Ascii.DLE))) == TYPE_AS_INT;
    }

    private static boolean isContentLength(byte[] bArr, int i) {
        if ((((long) (bArr[i] | (bArr[i + 1] << 8) | (bArr[i + 2] << Ascii.DLE) | (bArr[i + 3] << Ascii.CAN))) | (((long) bArr[i + 4]) << 32) | (((long) bArr[i + 5]) << 40) | (((long) bArr[i + 6]) << 48) | (((long) bArr[i + 7]) << 56)) != CONTENT_AS_LONG) {
            return false;
        }
        return ((((long) bArr[i + 13]) << 40) | (((long) (((bArr[i + 8] | (bArr[i + 9] << 8)) | (bArr[i + 10] << Ascii.DLE)) | (bArr[i + 11] << Ascii.CAN))) | (((long) bArr[i + 12]) << 32))) == LENGTH_AS_LONG;
    }

    private static boolean isGetMethod(byte[] bArr, int i) {
        return ((bArr[i + 2] << Ascii.DLE) | (bArr[i] | (bArr[i + 1] << 8))) == GET_AS_INT;
    }

    private static boolean isPostMethod(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) | ((bArr[i] | (bArr[i + 1] << 8)) | (bArr[i + 2] << Ascii.DLE))) == POST_AS_INT;
    }

    /* access modifiers changed from: protected */
    public String splitFirstWordInitialLine(byte[] bArr, int i, int i2) {
        if (i2 == 3) {
            if (isGetMethod(bArr, i)) {
                return HttpMethod.GET.name();
            }
        } else if (i2 == 4 && isPostMethod(bArr, i)) {
            return HttpMethod.POST.name();
        }
        return super.splitFirstWordInitialLine(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public String splitThirdWordInitialLine(byte[] bArr, int i, int i2) {
        if (i2 == 8) {
            long j = ((long) ((bArr[i + 1] << 8) | bArr[i] | (bArr[i + 2] << Ascii.DLE) | (bArr[i + 3] << Ascii.CAN))) | (((long) bArr[i + 4]) << 32) | (((long) bArr[i + 5]) << 40) | (((long) bArr[i + 6]) << 48) | (((long) bArr[i + 7]) << 56);
            if (j == HTTP_1_1_AS_LONG) {
                return "HTTP/1.1";
            }
            if (j == HTTP_1_0_AS_LONG) {
                return "HTTP/1.0";
            }
        }
        return super.splitThirdWordInitialLine(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public HttpMessage createInvalidMessage() {
        return new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, "/bad-request", this.validateHeaders);
    }

    /* access modifiers changed from: protected */
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        if (httpMessage.getClass() == DefaultHttpRequest.class) {
            return false;
        }
        return super.isContentAlwaysEmpty(httpMessage);
    }
}
