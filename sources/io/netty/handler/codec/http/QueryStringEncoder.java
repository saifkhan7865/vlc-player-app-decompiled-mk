package io.netty.handler.codec.http;

import io.ktor.http.ContentDisposition;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import kotlin.text.Typography;
import org.bouncycastle.asn1.BERTags;

public class QueryStringEncoder {
    private static final char[] CHAR_MAP = "0123456789ABCDEF".toCharArray();
    private static final byte WRITE_UTF_UNKNOWN = 63;
    private final Charset charset;
    private boolean hasParams;
    private final StringBuilder uriBuilder;

    private static boolean dontNeedEncoding(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.' || c == '*' || c == '~');
    }

    public QueryStringEncoder(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringEncoder(String str, Charset charset2) {
        ObjectUtil.checkNotNull(charset2, "charset");
        this.uriBuilder = new StringBuilder(str);
        this.charset = CharsetUtil.UTF_8.equals(charset2) ? null : charset2;
    }

    public void addParam(String str, String str2) {
        ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
        if (this.hasParams) {
            this.uriBuilder.append(Typography.amp);
        } else {
            this.uriBuilder.append('?');
            this.hasParams = true;
        }
        encodeComponent(str);
        if (str2 != null) {
            this.uriBuilder.append('=');
            encodeComponent(str2);
        }
    }

    private void encodeComponent(CharSequence charSequence) {
        if (this.charset == null) {
            encodeUtf8Component(charSequence);
        } else {
            encodeNonUtf8Component(charSequence);
        }
    }

    public URI toUri() throws URISyntaxException {
        return new URI(toString());
    }

    public String toString() {
        return this.uriBuilder.toString();
    }

    private void encodeNonUtf8Component(CharSequence charSequence) {
        int length = charSequence.length();
        char[] cArr = null;
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (dontNeedEncoding(charAt)) {
                this.uriBuilder.append(charAt);
                i++;
            } else {
                if (cArr == null) {
                    cArr = new char[(charSequence.length() - i)];
                }
                int i2 = 0;
                do {
                    cArr[i2] = charAt;
                    i2++;
                    i++;
                    if (i >= charSequence.length()) {
                        break;
                    }
                    charAt = charSequence.charAt(i);
                } while (!dontNeedEncoding(charAt));
                for (byte appendEncoded : new String(cArr, 0, i2).getBytes(this.charset)) {
                    appendEncoded(appendEncoded);
                }
            }
        }
    }

    private void encodeUtf8Component(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!dontNeedEncoding(charSequence.charAt(i))) {
                encodeUtf8Component(charSequence, i, length);
                return;
            }
        }
        this.uriBuilder.append(charSequence);
    }

    private void encodeUtf8Component(CharSequence charSequence, int i, int i2) {
        if (i > 0) {
            this.uriBuilder.append(charSequence, 0, i);
        }
        encodeUtf8ComponentSlow(charSequence, i, i2);
    }

    private void encodeUtf8ComponentSlow(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                if (dontNeedEncoding(charAt)) {
                    this.uriBuilder.append(charAt);
                } else {
                    appendEncoded(charAt);
                }
            } else if (charAt < 2048) {
                appendEncoded((charAt >> 6) | 192);
                appendEncoded((charAt & '?') | 128);
            } else if (!StringUtil.isSurrogate(charAt)) {
                appendEncoded((charAt >> 12) | BERTags.FLAGS);
                appendEncoded(((charAt >> 6) & 63) | 128);
                appendEncoded((charAt & '?') | 128);
            } else if (!Character.isHighSurrogate(charAt)) {
                appendEncoded(63);
            } else {
                i++;
                if (i == charSequence.length()) {
                    appendEncoded(63);
                    return;
                }
                writeUtf8Surrogate(charAt, charSequence.charAt(i));
            }
            i++;
        }
    }

    private void writeUtf8Surrogate(char c, char c2) {
        if (!Character.isLowSurrogate(c2)) {
            appendEncoded(63);
            if (Character.isHighSurrogate(c2)) {
                c2 = '?';
            }
            appendEncoded(c2);
            return;
        }
        int codePoint = Character.toCodePoint(c, c2);
        appendEncoded((codePoint >> 18) | 240);
        appendEncoded(((codePoint >> 12) & 63) | 128);
        appendEncoded(((codePoint >> 6) & 63) | 128);
        appendEncoded((codePoint & 63) | 128);
    }

    private void appendEncoded(int i) {
        StringBuilder sb = this.uriBuilder;
        sb.append('%');
        sb.append(forDigit(i >> 4));
        sb.append(forDigit(i));
    }

    private static char forDigit(int i) {
        return CHAR_MAP[i & 15];
    }
}
