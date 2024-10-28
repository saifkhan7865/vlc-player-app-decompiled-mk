package io.netty.handler.codec.http;

import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class HttpUtil {
    private static final AsciiString CHARSET_EQUALS = AsciiString.of(HttpHeaderValues.CHARSET + "=");
    private static final String COMMA_STRING = String.valueOf(',');
    private static final AsciiString SEMICOLON = AsciiString.cached(";");

    private HttpUtil() {
    }

    public static boolean isOriginForm(URI uri) {
        return isOriginForm(uri.toString());
    }

    public static boolean isOriginForm(String str) {
        return str.startsWith("/");
    }

    public static boolean isAsteriskForm(URI uri) {
        return isAsteriskForm(uri.toString());
    }

    public static boolean isAsteriskForm(String str) {
        return "*".equals(str);
    }

    public static boolean isKeepAlive(HttpMessage httpMessage) {
        if (httpMessage.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE, true) || (!httpMessage.protocolVersion().isKeepAliveDefault() && !httpMessage.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE, true))) {
            return false;
        }
        return true;
    }

    public static void setKeepAlive(HttpMessage httpMessage, boolean z) {
        setKeepAlive(httpMessage.headers(), httpMessage.protocolVersion(), z);
    }

    public static void setKeepAlive(HttpHeaders httpHeaders, HttpVersion httpVersion, boolean z) {
        if (httpVersion.isKeepAliveDefault()) {
            if (z) {
                httpHeaders.remove((CharSequence) HttpHeaderNames.CONNECTION);
            } else {
                httpHeaders.set((CharSequence) HttpHeaderNames.CONNECTION, (Object) HttpHeaderValues.CLOSE);
            }
        } else if (z) {
            httpHeaders.set((CharSequence) HttpHeaderNames.CONNECTION, (Object) HttpHeaderValues.KEEP_ALIVE);
        } else {
            httpHeaders.remove((CharSequence) HttpHeaderNames.CONNECTION);
        }
    }

    public static long getContentLength(HttpMessage httpMessage) {
        String str = httpMessage.headers().get((CharSequence) HttpHeaderNames.CONTENT_LENGTH);
        if (str != null) {
            return Long.parseLong(str);
        }
        long webSocketContentLength = (long) getWebSocketContentLength(httpMessage);
        if (webSocketContentLength >= 0) {
            return webSocketContentLength;
        }
        throw new NumberFormatException("header not found: " + HttpHeaderNames.CONTENT_LENGTH);
    }

    public static long getContentLength(HttpMessage httpMessage, long j) {
        String str = httpMessage.headers().get((CharSequence) HttpHeaderNames.CONTENT_LENGTH);
        if (str != null) {
            return Long.parseLong(str);
        }
        long webSocketContentLength = (long) getWebSocketContentLength(httpMessage);
        return webSocketContentLength >= 0 ? webSocketContentLength : j;
    }

    public static int getContentLength(HttpMessage httpMessage, int i) {
        return (int) Math.min(2147483647L, getContentLength(httpMessage, (long) i));
    }

    private static int getWebSocketContentLength(HttpMessage httpMessage) {
        HttpHeaders headers = httpMessage.headers();
        if (!(httpMessage instanceof HttpRequest)) {
            return (!(httpMessage instanceof HttpResponse) || ((HttpResponse) httpMessage).status().code() != 101 || !headers.contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_ORIGIN) || !headers.contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_LOCATION)) ? -1 : 16;
        }
        if (!HttpMethod.GET.equals(((HttpRequest) httpMessage).method()) || !headers.contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY1) || !headers.contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY2)) {
            return -1;
        }
        return 8;
    }

    public static void setContentLength(HttpMessage httpMessage, long j) {
        httpMessage.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) Long.valueOf(j));
    }

    public static boolean isContentLengthSet(HttpMessage httpMessage) {
        return httpMessage.headers().contains((CharSequence) HttpHeaderNames.CONTENT_LENGTH);
    }

    public static boolean is100ContinueExpected(HttpMessage httpMessage) {
        return isExpectHeaderValid(httpMessage) && httpMessage.headers().contains((CharSequence) HttpHeaderNames.EXPECT, (CharSequence) HttpHeaderValues.CONTINUE, true);
    }

    static boolean isUnsupportedExpectation(HttpMessage httpMessage) {
        String str;
        if (isExpectHeaderValid(httpMessage) && (str = httpMessage.headers().get((CharSequence) HttpHeaderNames.EXPECT)) != null && !HttpHeaderValues.CONTINUE.toString().equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    private static boolean isExpectHeaderValid(HttpMessage httpMessage) {
        return (httpMessage instanceof HttpRequest) && httpMessage.protocolVersion().compareTo(HttpVersion.HTTP_1_1) >= 0;
    }

    public static void set100ContinueExpected(HttpMessage httpMessage, boolean z) {
        if (z) {
            httpMessage.headers().set((CharSequence) HttpHeaderNames.EXPECT, (Object) HttpHeaderValues.CONTINUE);
        } else {
            httpMessage.headers().remove((CharSequence) HttpHeaderNames.EXPECT);
        }
    }

    public static boolean isTransferEncodingChunked(HttpMessage httpMessage) {
        return httpMessage.headers().containsValue(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED, true);
    }

    public static void setTransferEncodingChunked(HttpMessage httpMessage, boolean z) {
        if (z) {
            httpMessage.headers().set((CharSequence) HttpHeaderNames.TRANSFER_ENCODING, (Object) HttpHeaderValues.CHUNKED);
            httpMessage.headers().remove((CharSequence) HttpHeaderNames.CONTENT_LENGTH);
            return;
        }
        List<String> all = httpMessage.headers().getAll((CharSequence) HttpHeaderNames.TRANSFER_ENCODING);
        if (!all.isEmpty()) {
            ArrayList arrayList = new ArrayList(all);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (HttpHeaderValues.CHUNKED.contentEqualsIgnoreCase((CharSequence) it.next())) {
                    it.remove();
                }
            }
            if (arrayList.isEmpty()) {
                httpMessage.headers().remove((CharSequence) HttpHeaderNames.TRANSFER_ENCODING);
            } else {
                httpMessage.headers().set((CharSequence) HttpHeaderNames.TRANSFER_ENCODING, (Iterable<?>) arrayList);
            }
        }
    }

    public static Charset getCharset(HttpMessage httpMessage) {
        return getCharset(httpMessage, CharsetUtil.ISO_8859_1);
    }

    public static Charset getCharset(CharSequence charSequence) {
        if (charSequence != null) {
            return getCharset(charSequence, CharsetUtil.ISO_8859_1);
        }
        return CharsetUtil.ISO_8859_1;
    }

    public static Charset getCharset(HttpMessage httpMessage, Charset charset) {
        String str = httpMessage.headers().get((CharSequence) HttpHeaderNames.CONTENT_TYPE);
        return str != null ? getCharset((CharSequence) str, charset) : charset;
    }

    public static Charset getCharset(CharSequence charSequence, Charset charset) {
        CharSequence charsetAsSequence;
        if (!(charSequence == null || (charsetAsSequence = getCharsetAsSequence(charSequence)) == null)) {
            if (charsetAsSequence.length() > 2 && charsetAsSequence.charAt(0) == '\"' && charsetAsSequence.charAt(charsetAsSequence.length() - 1) == '\"') {
                charsetAsSequence = charsetAsSequence.subSequence(1, charsetAsSequence.length() - 1);
            }
            try {
                return Charset.forName(charsetAsSequence.toString());
            } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            }
        }
        return charset;
    }

    @Deprecated
    public static CharSequence getCharsetAsString(HttpMessage httpMessage) {
        return getCharsetAsSequence(httpMessage);
    }

    public static CharSequence getCharsetAsSequence(HttpMessage httpMessage) {
        String str = httpMessage.headers().get((CharSequence) HttpHeaderNames.CONTENT_TYPE);
        if (str != null) {
            return getCharsetAsSequence((CharSequence) str);
        }
        return null;
    }

    public static CharSequence getCharsetAsSequence(CharSequence charSequence) {
        int length;
        ObjectUtil.checkNotNull(charSequence, "contentTypeValue");
        AsciiString asciiString = CHARSET_EQUALS;
        int indexOfIgnoreCaseAscii = AsciiString.indexOfIgnoreCaseAscii(charSequence, asciiString, 0);
        if (indexOfIgnoreCaseAscii == -1 || (length = indexOfIgnoreCaseAscii + asciiString.length()) >= charSequence.length()) {
            return null;
        }
        CharSequence subSequence = charSequence.subSequence(length, charSequence.length());
        int indexOfIgnoreCaseAscii2 = AsciiString.indexOfIgnoreCaseAscii(subSequence, SEMICOLON, 0);
        if (indexOfIgnoreCaseAscii2 == -1) {
            return subSequence;
        }
        return subSequence.subSequence(0, indexOfIgnoreCaseAscii2);
    }

    public static CharSequence getMimeType(HttpMessage httpMessage) {
        String str = httpMessage.headers().get((CharSequence) HttpHeaderNames.CONTENT_TYPE);
        if (str != null) {
            return getMimeType((CharSequence) str);
        }
        return null;
    }

    public static CharSequence getMimeType(CharSequence charSequence) {
        ObjectUtil.checkNotNull(charSequence, "contentTypeValue");
        int indexOfIgnoreCaseAscii = AsciiString.indexOfIgnoreCaseAscii(charSequence, SEMICOLON, 0);
        if (indexOfIgnoreCaseAscii != -1) {
            return charSequence.subSequence(0, indexOfIgnoreCaseAscii);
        }
        if (charSequence.length() > 0) {
            return charSequence;
        }
        return null;
    }

    public static String formatHostnameForHttp(InetSocketAddress inetSocketAddress) {
        String hostname = NetUtil.getHostname(inetSocketAddress);
        if (!NetUtil.isValidIpV6Address(hostname)) {
            return hostname;
        }
        if (!inetSocketAddress.isUnresolved()) {
            hostname = NetUtil.toAddressString(inetSocketAddress.getAddress());
        }
        return "[" + hostname + AbstractJsonLexerKt.END_LIST;
    }

    public static long normalizeAndGetContentLength(List<? extends CharSequence> list, boolean z, boolean z2) {
        if (list.isEmpty()) {
            return -1;
        }
        String charSequence = ((CharSequence) list.get(0)).toString();
        if ((list.size() > 1 || charSequence.indexOf(44) >= 0) && !z) {
            if (z2) {
                charSequence = null;
                for (CharSequence charSequence2 : list) {
                    String[] split = charSequence2.toString().split(COMMA_STRING, -1);
                    int length = split.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            String trim = split[i].trim();
                            if (charSequence == null) {
                                charSequence = trim;
                            } else if (!trim.equals(charSequence)) {
                                throw new IllegalArgumentException("Multiple Content-Length values found: " + list);
                            }
                            i++;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Multiple Content-Length values found: " + list);
            }
        }
        if (charSequence.isEmpty() || !Character.isDigit(charSequence.charAt(0))) {
            throw new IllegalArgumentException("Content-Length value is not a number: " + charSequence);
        }
        try {
            return ObjectUtil.checkPositiveOrZero(Long.parseLong(charSequence), "Content-Length value");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Content-Length value is not a number: " + charSequence, e);
        }
    }
}
