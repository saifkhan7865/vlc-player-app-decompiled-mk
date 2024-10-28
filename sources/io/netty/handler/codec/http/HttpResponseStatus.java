package io.netty.handler.codec.http;

import androidx.car.app.hardware.common.CarUnit;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ObjectUtil;

public class HttpResponseStatus implements Comparable<HttpResponseStatus> {
    public static final HttpResponseStatus ACCEPTED = newStatus(CarUnit.LITER, "Accepted");
    public static final HttpResponseStatus BAD_GATEWAY = newStatus(TypedValues.PositionType.TYPE_DRAWPATH, "Bad Gateway");
    public static final HttpResponseStatus BAD_REQUEST = newStatus(400, "Bad Request");
    public static final HttpResponseStatus CONFLICT = newStatus(409, "Conflict");
    public static final HttpResponseStatus CONTINUE = newStatus(100, "Continue");
    public static final HttpResponseStatus CREATED = newStatus(201, "Created");
    public static final HttpResponseStatus EARLY_HINTS = newStatus(103, "Early Hints");
    public static final HttpResponseStatus EXPECTATION_FAILED = newStatus(417, "Expectation Failed");
    public static final HttpResponseStatus FAILED_DEPENDENCY = newStatus(TypedValues.CycleType.TYPE_WAVE_OFFSET, "Failed Dependency");
    public static final HttpResponseStatus FORBIDDEN = newStatus(TypedValues.CycleType.TYPE_ALPHA, "Forbidden");
    public static final HttpResponseStatus FOUND = newStatus(302, "Found");
    public static final HttpResponseStatus GATEWAY_TIMEOUT = newStatus(TypedValues.PositionType.TYPE_PERCENT_HEIGHT, "Gateway Timeout");
    public static final HttpResponseStatus GONE = newStatus(410, "Gone");
    public static final HttpResponseStatus HTTP_VERSION_NOT_SUPPORTED = newStatus(TypedValues.PositionType.TYPE_SIZE_PERCENT, "HTTP Version Not Supported");
    public static final HttpResponseStatus INSUFFICIENT_STORAGE = newStatus(TypedValues.PositionType.TYPE_PERCENT_Y, "Insufficient Storage");
    public static final HttpResponseStatus INTERNAL_SERVER_ERROR = newStatus(500, "Internal Server Error");
    public static final HttpResponseStatus LENGTH_REQUIRED = newStatus(411, "Length Required");
    public static final HttpResponseStatus LOCKED = newStatus(TypedValues.CycleType.TYPE_WAVE_PERIOD, "Locked");
    public static final HttpResponseStatus METHOD_NOT_ALLOWED = newStatus(405, "Method Not Allowed");
    public static final HttpResponseStatus MISDIRECTED_REQUEST = newStatus(TypedValues.CycleType.TYPE_WAVE_SHAPE, "Misdirected Request");
    public static final HttpResponseStatus MOVED_PERMANENTLY = newStatus(301, "Moved Permanently");
    public static final HttpResponseStatus MULTIPLE_CHOICES = newStatus(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, "Multiple Choices");
    public static final HttpResponseStatus MULTI_STATUS = newStatus(207, "Multi-Status");
    public static final HttpResponseStatus NETWORK_AUTHENTICATION_REQUIRED = newStatus(FrameMetricsAggregator.EVERY_DURATION, "Network Authentication Required");
    public static final HttpResponseStatus NON_AUTHORITATIVE_INFORMATION = newStatus(CarUnit.US_GALLON, "Non-Authoritative Information");
    public static final HttpResponseStatus NOT_ACCEPTABLE = newStatus(406, "Not Acceptable");
    public static final HttpResponseStatus NOT_EXTENDED = newStatus(TypedValues.PositionType.TYPE_POSITION_TYPE, "Not Extended");
    public static final HttpResponseStatus NOT_FOUND = newStatus(404, "Not Found");
    public static final HttpResponseStatus NOT_IMPLEMENTED = newStatus(TypedValues.PositionType.TYPE_TRANSITION_EASING, "Not Implemented");
    public static final HttpResponseStatus NOT_MODIFIED = newStatus(304, "Not Modified");
    public static final HttpResponseStatus NO_CONTENT = newStatus(CarUnit.IMPERIAL_GALLON, "No Content");
    public static final HttpResponseStatus OK = newStatus(200, "OK");
    public static final HttpResponseStatus PARTIAL_CONTENT = newStatus(206, "Partial Content");
    public static final HttpResponseStatus PAYMENT_REQUIRED = newStatus(TypedValues.CycleType.TYPE_VISIBILITY, "Payment Required");
    public static final HttpResponseStatus PERMANENT_REDIRECT = newStatus(308, "Permanent Redirect");
    public static final HttpResponseStatus PRECONDITION_FAILED = newStatus(412, "Precondition Failed");
    public static final HttpResponseStatus PRECONDITION_REQUIRED = newStatus(428, "Precondition Required");
    public static final HttpResponseStatus PROCESSING = newStatus(102, "Processing");
    public static final HttpResponseStatus PROXY_AUTHENTICATION_REQUIRED = newStatus(407, "Proxy Authentication Required");
    public static final HttpResponseStatus REQUESTED_RANGE_NOT_SATISFIABLE = newStatus(416, "Requested Range Not Satisfiable");
    public static final HttpResponseStatus REQUEST_ENTITY_TOO_LARGE = newStatus(413, "Request Entity Too Large");
    public static final HttpResponseStatus REQUEST_HEADER_FIELDS_TOO_LARGE = newStatus(431, "Request Header Fields Too Large");
    public static final HttpResponseStatus REQUEST_TIMEOUT = newStatus(408, "Request Timeout");
    public static final HttpResponseStatus REQUEST_URI_TOO_LONG = newStatus(414, "Request-URI Too Long");
    public static final HttpResponseStatus RESET_CONTENT = newStatus(205, "Reset Content");
    public static final HttpResponseStatus SEE_OTHER = newStatus(303, "See Other");
    public static final HttpResponseStatus SERVICE_UNAVAILABLE = newStatus(TypedValues.PositionType.TYPE_PERCENT_WIDTH, "Service Unavailable");
    public static final HttpResponseStatus SWITCHING_PROTOCOLS = newStatus(101, "Switching Protocols");
    public static final HttpResponseStatus TEMPORARY_REDIRECT = newStatus(307, "Temporary Redirect");
    public static final HttpResponseStatus TOO_MANY_REQUESTS = newStatus(429, "Too Many Requests");
    public static final HttpResponseStatus UNAUTHORIZED = newStatus(TypedValues.CycleType.TYPE_CURVE_FIT, "Unauthorized");
    public static final HttpResponseStatus UNORDERED_COLLECTION = newStatus(TypedValues.CycleType.TYPE_WAVE_PHASE, "Unordered Collection");
    public static final HttpResponseStatus UNPROCESSABLE_ENTITY = newStatus(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, "Unprocessable Entity");
    public static final HttpResponseStatus UNSUPPORTED_MEDIA_TYPE = newStatus(415, "Unsupported Media Type");
    public static final HttpResponseStatus UPGRADE_REQUIRED = newStatus(426, "Upgrade Required");
    public static final HttpResponseStatus USE_PROXY = newStatus(305, "Use Proxy");
    public static final HttpResponseStatus VARIANT_ALSO_NEGOTIATES = newStatus(TypedValues.PositionType.TYPE_PERCENT_X, "Variant Also Negotiates");
    private final byte[] bytes;
    private final int code;
    private final AsciiString codeAsText;
    private final HttpStatusClass codeClass;
    private final String reasonPhrase;

    private static HttpResponseStatus newStatus(int i, String str) {
        return new HttpResponseStatus(i, str, true);
    }

    public static HttpResponseStatus valueOf(int i) {
        HttpResponseStatus valueOf0 = valueOf0(i);
        return valueOf0 != null ? valueOf0 : new HttpResponseStatus(i);
    }

    private static HttpResponseStatus valueOf0(int i) {
        if (i == 307) {
            return TEMPORARY_REDIRECT;
        }
        if (i == 308) {
            return PERMANENT_REDIRECT;
        }
        if (i == 428) {
            return PRECONDITION_REQUIRED;
        }
        if (i == 429) {
            return TOO_MANY_REQUESTS;
        }
        if (i == 431) {
            return REQUEST_HEADER_FIELDS_TOO_LARGE;
        }
        if (i == 510) {
            return NOT_EXTENDED;
        }
        if (i == 511) {
            return NETWORK_AUTHENTICATION_REQUIRED;
        }
        switch (i) {
            case 100:
                return CONTINUE;
            case 101:
                return SWITCHING_PROTOCOLS;
            case 102:
                return PROCESSING;
            case 103:
                return EARLY_HINTS;
            default:
                switch (i) {
                    case 200:
                        return OK;
                    case 201:
                        return CREATED;
                    case CarUnit.LITER:
                        return ACCEPTED;
                    case CarUnit.US_GALLON:
                        return NON_AUTHORITATIVE_INFORMATION;
                    case CarUnit.IMPERIAL_GALLON:
                        return NO_CONTENT;
                    case 205:
                        return RESET_CONTENT;
                    case 206:
                        return PARTIAL_CONTENT;
                    case 207:
                        return MULTI_STATUS;
                    default:
                        switch (i) {
                            case MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION:
                                return MULTIPLE_CHOICES;
                            case 301:
                                return MOVED_PERMANENTLY;
                            case 302:
                                return FOUND;
                            case 303:
                                return SEE_OTHER;
                            case 304:
                                return NOT_MODIFIED;
                            case 305:
                                return USE_PROXY;
                            default:
                                switch (i) {
                                    case 400:
                                        return BAD_REQUEST;
                                    case TypedValues.CycleType.TYPE_CURVE_FIT:
                                        return UNAUTHORIZED;
                                    case TypedValues.CycleType.TYPE_VISIBILITY:
                                        return PAYMENT_REQUIRED;
                                    case TypedValues.CycleType.TYPE_ALPHA:
                                        return FORBIDDEN;
                                    case 404:
                                        return NOT_FOUND;
                                    case 405:
                                        return METHOD_NOT_ALLOWED;
                                    case 406:
                                        return NOT_ACCEPTABLE;
                                    case 407:
                                        return PROXY_AUTHENTICATION_REQUIRED;
                                    case 408:
                                        return REQUEST_TIMEOUT;
                                    case 409:
                                        return CONFLICT;
                                    case 410:
                                        return GONE;
                                    case 411:
                                        return LENGTH_REQUIRED;
                                    case 412:
                                        return PRECONDITION_FAILED;
                                    case 413:
                                        return REQUEST_ENTITY_TOO_LARGE;
                                    case 414:
                                        return REQUEST_URI_TOO_LONG;
                                    case 415:
                                        return UNSUPPORTED_MEDIA_TYPE;
                                    case 416:
                                        return REQUESTED_RANGE_NOT_SATISFIABLE;
                                    case 417:
                                        return EXPECTATION_FAILED;
                                    default:
                                        switch (i) {
                                            case TypedValues.CycleType.TYPE_WAVE_SHAPE:
                                                return MISDIRECTED_REQUEST;
                                            case TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE:
                                                return UNPROCESSABLE_ENTITY;
                                            case TypedValues.CycleType.TYPE_WAVE_PERIOD:
                                                return LOCKED;
                                            case TypedValues.CycleType.TYPE_WAVE_OFFSET:
                                                return FAILED_DEPENDENCY;
                                            case TypedValues.CycleType.TYPE_WAVE_PHASE:
                                                return UNORDERED_COLLECTION;
                                            case 426:
                                                return UPGRADE_REQUIRED;
                                            default:
                                                switch (i) {
                                                    case 500:
                                                        return INTERNAL_SERVER_ERROR;
                                                    case TypedValues.PositionType.TYPE_TRANSITION_EASING:
                                                        return NOT_IMPLEMENTED;
                                                    case TypedValues.PositionType.TYPE_DRAWPATH:
                                                        return BAD_GATEWAY;
                                                    case TypedValues.PositionType.TYPE_PERCENT_WIDTH:
                                                        return SERVICE_UNAVAILABLE;
                                                    case TypedValues.PositionType.TYPE_PERCENT_HEIGHT:
                                                        return GATEWAY_TIMEOUT;
                                                    case TypedValues.PositionType.TYPE_SIZE_PERCENT:
                                                        return HTTP_VERSION_NOT_SUPPORTED;
                                                    case TypedValues.PositionType.TYPE_PERCENT_X:
                                                        return VARIANT_ALSO_NEGOTIATES;
                                                    case TypedValues.PositionType.TYPE_PERCENT_Y:
                                                        return INSUFFICIENT_STORAGE;
                                                    default:
                                                        return null;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static HttpResponseStatus valueOf(int i, String str) {
        HttpResponseStatus valueOf0 = valueOf0(i);
        return (valueOf0 == null || !valueOf0.reasonPhrase().contentEquals(str)) ? new HttpResponseStatus(i, str) : valueOf0;
    }

    public static HttpResponseStatus parseLine(CharSequence charSequence) {
        return charSequence instanceof AsciiString ? parseLine((AsciiString) charSequence) : parseLine(charSequence.toString());
    }

    public static HttpResponseStatus parseLine(String str) {
        try {
            int indexOf = str.indexOf(32);
            if (indexOf == -1) {
                return valueOf(Integer.parseInt(str));
            }
            return valueOf(Integer.parseInt(str.substring(0, indexOf)), str.substring(indexOf + 1));
        } catch (Exception e) {
            throw new IllegalArgumentException("malformed status line: " + str, e);
        }
    }

    public static HttpResponseStatus parseLine(AsciiString asciiString) {
        try {
            int forEachByte = asciiString.forEachByte(ByteProcessor.FIND_ASCII_SPACE);
            return forEachByte == -1 ? valueOf(asciiString.parseInt()) : valueOf(asciiString.parseInt(0, forEachByte), asciiString.toString(forEachByte + 1));
        } catch (Exception e) {
            throw new IllegalArgumentException("malformed status line: " + asciiString, e);
        }
    }

    private HttpResponseStatus(int i) {
        this(i, HttpStatusClass.valueOf(i).defaultReasonPhrase() + " (" + i + ')', false);
    }

    public HttpResponseStatus(int i, String str) {
        this(i, str, false);
    }

    private HttpResponseStatus(int i, String str, boolean z) {
        ObjectUtil.checkPositiveOrZero(i, OAuth2RequestParameters.Code);
        ObjectUtil.checkNotNull(str, "reasonPhrase");
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == 10 || charAt == 13) {
                throw new IllegalArgumentException("reasonPhrase contains one of the following prohibited characters: \\r\\n: " + str);
            }
        }
        this.code = i;
        this.codeClass = HttpStatusClass.valueOf(i);
        String num = Integer.toString(i);
        this.codeAsText = new AsciiString((CharSequence) num);
        this.reasonPhrase = str;
        if (z) {
            this.bytes = (num + ' ' + str).getBytes(CharsetUtil.US_ASCII);
            return;
        }
        this.bytes = null;
    }

    public int code() {
        return this.code;
    }

    public AsciiString codeAsText() {
        return this.codeAsText;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public HttpStatusClass codeClass() {
        return this.codeClass;
    }

    public int hashCode() {
        return code();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof HttpResponseStatus) && code() == ((HttpResponseStatus) obj).code()) {
            return true;
        }
        return false;
    }

    public int compareTo(HttpResponseStatus httpResponseStatus) {
        return code() - httpResponseStatus.code();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.reasonPhrase.length() + 4);
        sb.append(this.codeAsText);
        sb.append(' ');
        sb.append(this.reasonPhrase);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void encode(ByteBuf byteBuf) {
        byte[] bArr = this.bytes;
        if (bArr == null) {
            ByteBufUtil.copy(this.codeAsText, byteBuf);
            byteBuf.writeByte(32);
            byteBuf.writeCharSequence(this.reasonPhrase, CharsetUtil.US_ASCII);
            return;
        }
        byteBuf.writeBytes(bArr);
    }
}
