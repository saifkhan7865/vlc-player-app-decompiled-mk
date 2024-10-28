package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.UnsupportedValueConverter;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

public final class HttpConversionUtil {
    private static final AsciiString EMPTY_REQUEST_PATH = AsciiString.cached("/");
    private static final CharSequenceMap<AsciiString> HTTP_TO_HTTP2_HEADER_BLACKLIST;
    public static final HttpMethod OUT_OF_MESSAGE_SEQUENCE_METHOD = HttpMethod.OPTIONS;
    public static final String OUT_OF_MESSAGE_SEQUENCE_PATH = "";
    public static final HttpResponseStatus OUT_OF_MESSAGE_SEQUENCE_RETURN_CODE = HttpResponseStatus.OK;

    static {
        CharSequenceMap<AsciiString> charSequenceMap = new CharSequenceMap<>();
        HTTP_TO_HTTP2_HEADER_BLACKLIST = charSequenceMap;
        charSequenceMap.add(HttpHeaderNames.CONNECTION, AsciiString.EMPTY_STRING);
        charSequenceMap.add(HttpHeaderNames.KEEP_ALIVE, AsciiString.EMPTY_STRING);
        charSequenceMap.add(HttpHeaderNames.PROXY_CONNECTION, AsciiString.EMPTY_STRING);
        charSequenceMap.add(HttpHeaderNames.TRANSFER_ENCODING, AsciiString.EMPTY_STRING);
        charSequenceMap.add(HttpHeaderNames.HOST, AsciiString.EMPTY_STRING);
        charSequenceMap.add(HttpHeaderNames.UPGRADE, AsciiString.EMPTY_STRING);
        charSequenceMap.add(ExtensionHeaderNames.STREAM_ID.text(), AsciiString.EMPTY_STRING);
        charSequenceMap.add(ExtensionHeaderNames.SCHEME.text(), AsciiString.EMPTY_STRING);
        charSequenceMap.add(ExtensionHeaderNames.PATH.text(), AsciiString.EMPTY_STRING);
    }

    private HttpConversionUtil() {
    }

    public enum ExtensionHeaderNames {
        STREAM_ID("x-http2-stream-id"),
        SCHEME("x-http2-scheme"),
        PATH("x-http2-path"),
        STREAM_PROMISE_ID("x-http2-stream-promise-id"),
        STREAM_DEPENDENCY_ID("x-http2-stream-dependency-id"),
        STREAM_WEIGHT("x-http2-stream-weight");
        
        private final AsciiString text;

        private ExtensionHeaderNames(String str) {
            this.text = AsciiString.cached(str);
        }

        public AsciiString text() {
            return this.text;
        }
    }

    public static HttpResponseStatus parseStatus(CharSequence charSequence) throws Http2Exception {
        try {
            HttpResponseStatus parseLine = HttpResponseStatus.parseLine(charSequence);
            if (parseLine != HttpResponseStatus.SWITCHING_PROTOCOLS) {
                return parseLine;
            }
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 status code '%d'", Integer.valueOf(parseLine.code()));
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, th, "Unrecognized HTTP status code '%s' encountered in translation to HTTP/1.x", charSequence);
        }
    }

    public static FullHttpResponse toFullHttpResponse(int i, Http2Headers http2Headers, ByteBufAllocator byteBufAllocator, boolean z) throws Http2Exception {
        return toFullHttpResponse(i, http2Headers, byteBufAllocator.buffer(), z);
    }

    public static FullHttpResponse toFullHttpResponse(int i, Http2Headers http2Headers, ByteBuf byteBuf, boolean z) throws Http2Exception {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, parseStatus(http2Headers.status()), byteBuf, z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultFullHttpResponse, false);
            return defaultFullHttpResponse;
        } catch (Http2Exception e) {
            defaultFullHttpResponse.release();
            throw e;
        } catch (Throwable th) {
            defaultFullHttpResponse.release();
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static FullHttpRequest toFullHttpRequest(int i, Http2Headers http2Headers, ByteBufAllocator byteBufAllocator, boolean z) throws Http2Exception {
        return toFullHttpRequest(i, http2Headers, byteBufAllocator.buffer(), z);
    }

    private static String extractPath(CharSequence charSequence, Http2Headers http2Headers) {
        if (HttpMethod.CONNECT.asciiName().contentEqualsIgnoreCase(charSequence)) {
            return ((CharSequence) ObjectUtil.checkNotNull(http2Headers.authority(), "authority header cannot be null in the conversion to HTTP/1.x")).toString();
        }
        return ((CharSequence) ObjectUtil.checkNotNull(http2Headers.path(), "path header cannot be null in conversion to HTTP/1.x")).toString();
    }

    public static FullHttpRequest toFullHttpRequest(int i, Http2Headers http2Headers, ByteBuf byteBuf, boolean z) throws Http2Exception {
        CharSequence charSequence = (CharSequence) ObjectUtil.checkNotNull(http2Headers.method(), "method header cannot be null in conversion to HTTP/1.x");
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.valueOf(charSequence.toString()), extractPath(charSequence, http2Headers).toString(), byteBuf, z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultFullHttpRequest, false);
            return defaultFullHttpRequest;
        } catch (Http2Exception e) {
            defaultFullHttpRequest.release();
            throw e;
        } catch (Throwable th) {
            defaultFullHttpRequest.release();
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static HttpRequest toHttpRequest(int i, Http2Headers http2Headers, boolean z) throws Http2Exception {
        CharSequence charSequence = (CharSequence) ObjectUtil.checkNotNull(http2Headers.method(), "method header cannot be null in conversion to HTTP/1.x");
        DefaultHttpRequest defaultHttpRequest = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.valueOf(charSequence.toString()), extractPath(charSequence, http2Headers).toString(), z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultHttpRequest.headers(), defaultHttpRequest.protocolVersion(), false, true);
            return defaultHttpRequest;
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static HttpResponse toHttpResponse(int i, Http2Headers http2Headers, boolean z) throws Http2Exception {
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, parseStatus(http2Headers.status()), z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultHttpResponse.headers(), defaultHttpResponse.protocolVersion(), false, false);
            return defaultHttpResponse;
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static void addHttp2ToHttpHeaders(int i, Http2Headers http2Headers, FullHttpMessage fullHttpMessage, boolean z) throws Http2Exception {
        addHttp2ToHttpHeaders(i, http2Headers, z ? fullHttpMessage.trailingHeaders() : fullHttpMessage.headers(), fullHttpMessage.protocolVersion(), z, fullHttpMessage instanceof HttpRequest);
    }

    public static void addHttp2ToHttpHeaders(int i, Http2Headers http2Headers, HttpHeaders httpHeaders, HttpVersion httpVersion, boolean z, boolean z2) throws Http2Exception {
        try {
            new Http2ToHttpHeaderTranslator(i, httpHeaders, z2).translateHeaders(http2Headers);
            httpHeaders.remove((CharSequence) HttpHeaderNames.TRANSFER_ENCODING);
            httpHeaders.remove((CharSequence) HttpHeaderNames.TRAILER);
            if (!z) {
                httpHeaders.setInt(ExtensionHeaderNames.STREAM_ID.text(), i);
                HttpUtil.setKeepAlive(httpHeaders, httpVersion, true);
            }
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static Http2Headers toHttp2Headers(HttpMessage httpMessage, boolean z) {
        HttpHeaders headers = httpMessage.headers();
        DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers(z, headers.size());
        if (httpMessage instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpMessage;
            String asString = headers.getAsString(HttpHeaderNames.HOST);
            if (HttpUtil.isOriginForm(httpRequest.uri()) || HttpUtil.isAsteriskForm(httpRequest.uri())) {
                defaultHttp2Headers.path(new AsciiString((CharSequence) httpRequest.uri()));
                setHttp2Scheme(headers, defaultHttp2Headers);
            } else {
                URI create = URI.create(httpRequest.uri());
                defaultHttp2Headers.path(toHttp2Path(create));
                if (StringUtil.isNullOrEmpty(asString)) {
                    asString = create.getAuthority();
                }
                setHttp2Scheme(headers, create, defaultHttp2Headers);
            }
            setHttp2Authority(asString, defaultHttp2Headers);
            defaultHttp2Headers.method(httpRequest.method().asciiName());
        } else if (httpMessage instanceof HttpResponse) {
            defaultHttp2Headers.status(((HttpResponse) httpMessage).status().codeAsText());
        }
        toHttp2Headers(headers, (Http2Headers) defaultHttp2Headers);
        return defaultHttp2Headers;
    }

    public static Http2Headers toHttp2Headers(HttpHeaders httpHeaders, boolean z) {
        if (httpHeaders.isEmpty()) {
            return EmptyHttp2Headers.INSTANCE;
        }
        DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers(z, httpHeaders.size());
        toHttp2Headers(httpHeaders, (Http2Headers) defaultHttp2Headers);
        return defaultHttp2Headers;
    }

    private static CharSequenceMap<AsciiString> toLowercaseMap(Iterator<? extends CharSequence> it, int i) {
        CharSequenceMap<AsciiString> charSequenceMap = new CharSequenceMap<>(true, UnsupportedValueConverter.instance(), i);
        while (it.hasNext()) {
            AsciiString lowerCase = AsciiString.of((CharSequence) it.next()).toLowerCase();
            try {
                int forEachByte = lowerCase.forEachByte(ByteProcessor.FIND_COMMA);
                if (forEachByte != -1) {
                    int i2 = 0;
                    do {
                        charSequenceMap.add(lowerCase.subSequence(i2, forEachByte, false).trim(), AsciiString.EMPTY_STRING);
                        i2 = forEachByte + 1;
                        if (i2 >= lowerCase.length() || (forEachByte = lowerCase.forEachByte(i2, lowerCase.length() - i2, ByteProcessor.FIND_COMMA)) == -1) {
                            charSequenceMap.add(lowerCase.subSequence(i2, lowerCase.length(), false).trim(), AsciiString.EMPTY_STRING);
                        }
                        charSequenceMap.add(lowerCase.subSequence(i2, forEachByte, false).trim(), AsciiString.EMPTY_STRING);
                        i2 = forEachByte + 1;
                        break;
                    } while ((forEachByte = lowerCase.forEachByte(i2, lowerCase.length() - i2, ByteProcessor.FIND_COMMA)) == -1);
                    charSequenceMap.add(lowerCase.subSequence(i2, lowerCase.length(), false).trim(), AsciiString.EMPTY_STRING);
                } else {
                    charSequenceMap.add(lowerCase.trim(), AsciiString.EMPTY_STRING);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return charSequenceMap;
    }

    private static void toHttp2HeadersFilterTE(Map.Entry<CharSequence, CharSequence> entry, Http2Headers http2Headers) {
        if (AsciiString.indexOf(entry.getValue(), ',', 0) != -1) {
            for (CharSequence trim : StringUtil.unescapeCsvFields(entry.getValue())) {
                if (AsciiString.contentEqualsIgnoreCase(AsciiString.trim(trim), HttpHeaderValues.TRAILERS)) {
                    http2Headers.add(HttpHeaderNames.TE, HttpHeaderValues.TRAILERS);
                    return;
                }
            }
        } else if (AsciiString.contentEqualsIgnoreCase(AsciiString.trim(entry.getValue()), HttpHeaderValues.TRAILERS)) {
            http2Headers.add(HttpHeaderNames.TE, HttpHeaderValues.TRAILERS);
        }
    }

    public static void toHttp2Headers(HttpHeaders httpHeaders, Http2Headers http2Headers) {
        Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence = httpHeaders.iteratorCharSequence();
        CharSequenceMap<AsciiString> lowercaseMap = toLowercaseMap(httpHeaders.valueCharSequenceIterator(HttpHeaderNames.CONNECTION), 8);
        while (iteratorCharSequence.hasNext()) {
            Map.Entry next = iteratorCharSequence.next();
            AsciiString lowerCase = AsciiString.of((CharSequence) next.getKey()).toLowerCase();
            if (!HTTP_TO_HTTP2_HEADER_BLACKLIST.contains(lowerCase) && !lowercaseMap.contains(lowerCase)) {
                if (lowerCase.contentEqualsIgnoreCase(HttpHeaderNames.TE)) {
                    toHttp2HeadersFilterTE(next, http2Headers);
                } else if (lowerCase.contentEqualsIgnoreCase(HttpHeaderNames.COOKIE)) {
                    AsciiString of = AsciiString.of((CharSequence) next.getValue());
                    try {
                        int forEachByte = of.forEachByte(ByteProcessor.FIND_SEMI_COLON);
                        if (forEachByte != -1) {
                            int i = 0;
                            do {
                                http2Headers.add(HttpHeaderNames.COOKIE, of.subSequence(i, forEachByte, false));
                                i = forEachByte + 2;
                                if (i >= of.length() || (forEachByte = of.forEachByte(i, of.length() - i, ByteProcessor.FIND_SEMI_COLON)) == -1) {
                                }
                                http2Headers.add(HttpHeaderNames.COOKIE, of.subSequence(i, forEachByte, false));
                                i = forEachByte + 2;
                                break;
                            } while ((forEachByte = of.forEachByte(i, of.length() - i, ByteProcessor.FIND_SEMI_COLON)) == -1);
                            if (i < of.length()) {
                                http2Headers.add(HttpHeaderNames.COOKIE, of.subSequence(i, of.length(), false));
                            } else {
                                throw new IllegalArgumentException("cookie value is of unexpected format: " + of);
                            }
                        } else {
                            http2Headers.add(HttpHeaderNames.COOKIE, of);
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                } else {
                    http2Headers.add(lowerCase, next.getValue());
                }
            }
        }
    }

    private static AsciiString toHttp2Path(URI uri) {
        StringBuilder sb = new StringBuilder(StringUtil.length(uri.getRawPath()) + StringUtil.length(uri.getRawQuery()) + StringUtil.length(uri.getRawFragment()) + 2);
        if (!StringUtil.isNullOrEmpty(uri.getRawPath())) {
            sb.append(uri.getRawPath());
        }
        if (!StringUtil.isNullOrEmpty(uri.getRawQuery())) {
            sb.append('?');
            sb.append(uri.getRawQuery());
        }
        if (!StringUtil.isNullOrEmpty(uri.getRawFragment())) {
            sb.append('#');
            sb.append(uri.getRawFragment());
        }
        String sb2 = sb.toString();
        return sb2.isEmpty() ? EMPTY_REQUEST_PATH : new AsciiString((CharSequence) sb2);
    }

    static void setHttp2Authority(String str, Http2Headers http2Headers) {
        if (str == null) {
            return;
        }
        if (str.isEmpty()) {
            http2Headers.authority(AsciiString.EMPTY_STRING);
            return;
        }
        int indexOf = str.indexOf(64) + 1;
        int length = str.length() - indexOf;
        if (length != 0) {
            http2Headers.authority(new AsciiString((CharSequence) str, indexOf, length));
            return;
        }
        throw new IllegalArgumentException("authority: " + str);
    }

    private static void setHttp2Scheme(HttpHeaders httpHeaders, Http2Headers http2Headers) {
        setHttp2Scheme(httpHeaders, URI.create(""), http2Headers);
    }

    private static void setHttp2Scheme(HttpHeaders httpHeaders, URI uri, Http2Headers http2Headers) {
        String scheme = uri.getScheme();
        if (!StringUtil.isNullOrEmpty(scheme)) {
            http2Headers.scheme(new AsciiString((CharSequence) scheme));
            return;
        }
        String str = httpHeaders.get((CharSequence) ExtensionHeaderNames.SCHEME.text());
        if (str != null) {
            http2Headers.scheme(AsciiString.of(str));
        } else if (uri.getPort() == HttpScheme.HTTPS.port()) {
            http2Headers.scheme(HttpScheme.HTTPS.name());
        } else if (uri.getPort() == HttpScheme.HTTP.port()) {
            http2Headers.scheme(HttpScheme.HTTP.name());
        } else {
            throw new IllegalArgumentException(":scheme must be specified. see https://tools.ietf.org/html/rfc7540#section-8.1.2.3");
        }
    }

    private static final class Http2ToHttpHeaderTranslator {
        private static final CharSequenceMap<AsciiString> REQUEST_HEADER_TRANSLATIONS;
        private static final CharSequenceMap<AsciiString> RESPONSE_HEADER_TRANSLATIONS;
        private final HttpHeaders output;
        private final int streamId;
        private final CharSequenceMap<AsciiString> translations;

        static {
            CharSequenceMap<AsciiString> charSequenceMap = new CharSequenceMap<>();
            REQUEST_HEADER_TRANSLATIONS = charSequenceMap;
            CharSequenceMap<AsciiString> charSequenceMap2 = new CharSequenceMap<>();
            RESPONSE_HEADER_TRANSLATIONS = charSequenceMap2;
            charSequenceMap2.add(Http2Headers.PseudoHeaderName.AUTHORITY.value(), HttpHeaderNames.HOST);
            charSequenceMap2.add(Http2Headers.PseudoHeaderName.SCHEME.value(), ExtensionHeaderNames.SCHEME.text());
            charSequenceMap.add(charSequenceMap2);
            charSequenceMap2.add(Http2Headers.PseudoHeaderName.PATH.value(), ExtensionHeaderNames.PATH.text());
        }

        Http2ToHttpHeaderTranslator(int i, HttpHeaders httpHeaders, boolean z) {
            this.streamId = i;
            this.output = httpHeaders;
            this.translations = z ? REQUEST_HEADER_TRANSLATIONS : RESPONSE_HEADER_TRANSLATIONS;
        }

        /* access modifiers changed from: package-private */
        public void translateHeaders(Iterable<Map.Entry<CharSequence, CharSequence>> iterable) throws Http2Exception {
            StringBuilder sb = null;
            for (Map.Entry next : iterable) {
                CharSequence charSequence = (CharSequence) next.getKey();
                CharSequence charSequence2 = (CharSequence) next.getValue();
                AsciiString asciiString = this.translations.get(charSequence);
                if (asciiString != null) {
                    this.output.add((CharSequence) asciiString, (Object) AsciiString.of(charSequence2));
                } else if (Http2Headers.PseudoHeaderName.isPseudoHeader(charSequence)) {
                    continue;
                } else if (charSequence.length() == 0 || charSequence.charAt(0) == ':') {
                    throw Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 header '%s' encountered in translation to HTTP/1.x", charSequence);
                } else if (HttpHeaderNames.COOKIE.equals(charSequence)) {
                    if (sb == null) {
                        sb = InternalThreadLocalMap.get().stringBuilder();
                    } else if (sb.length() > 0) {
                        sb.append("; ");
                    }
                    sb.append(charSequence2);
                } else {
                    this.output.add(charSequence, (Object) charSequence2);
                }
            }
            if (sb != null) {
                this.output.add((CharSequence) HttpHeaderNames.COOKIE, (Object) sb.toString());
            }
        }
    }
}