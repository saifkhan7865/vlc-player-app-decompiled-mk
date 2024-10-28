package io.netty.handler.codec.http2;

import io.netty.handler.codec.Headers;
import io.netty.util.AsciiString;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.http2.Header;

public interface Http2Headers extends Headers<CharSequence, CharSequence, Http2Headers> {
    Http2Headers authority(CharSequence charSequence);

    CharSequence authority();

    boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z);

    Iterator<Map.Entry<CharSequence, CharSequence>> iterator();

    Http2Headers method(CharSequence charSequence);

    CharSequence method();

    Http2Headers path(CharSequence charSequence);

    CharSequence path();

    Http2Headers scheme(CharSequence charSequence);

    CharSequence scheme();

    Http2Headers status(CharSequence charSequence);

    CharSequence status();

    Iterator<CharSequence> valueIterator(CharSequence charSequence);

    public enum PseudoHeaderName {
        METHOD(Header.TARGET_METHOD_UTF8, true),
        SCHEME(Header.TARGET_SCHEME_UTF8, true),
        AUTHORITY(Header.TARGET_AUTHORITY_UTF8, true),
        PATH(Header.TARGET_PATH_UTF8, true),
        STATUS(Header.RESPONSE_STATUS_UTF8, false),
        PROTOCOL(":protocol", true);
        
        private static final CharSequenceMap<PseudoHeaderName> PSEUDO_HEADERS = null;
        private static final char PSEUDO_HEADER_PREFIX = ':';
        private static final byte PSEUDO_HEADER_PREFIX_BYTE = 58;
        private final boolean requestOnly;
        private final AsciiString value;

        static {
            int i;
            PSEUDO_HEADERS = new CharSequenceMap<>();
            for (PseudoHeaderName pseudoHeaderName : values()) {
                PSEUDO_HEADERS.add(pseudoHeaderName.value(), pseudoHeaderName);
            }
        }

        private PseudoHeaderName(String str, boolean z) {
            this.value = AsciiString.cached(str);
            this.requestOnly = z;
        }

        public AsciiString value() {
            return this.value;
        }

        public static boolean hasPseudoHeaderFormat(CharSequence charSequence) {
            if (charSequence instanceof AsciiString) {
                AsciiString asciiString = (AsciiString) charSequence;
                if (asciiString.length() <= 0 || asciiString.byteAt(0) != 58) {
                    return false;
                }
                return true;
            } else if (charSequence.length() <= 0 || charSequence.charAt(0) != ':') {
                return false;
            } else {
                return true;
            }
        }

        public static boolean isPseudoHeader(CharSequence charSequence) {
            return PSEUDO_HEADERS.contains(charSequence);
        }

        public static PseudoHeaderName getPseudoHeader(CharSequence charSequence) {
            return PSEUDO_HEADERS.get(charSequence);
        }

        public boolean isRequestOnly() {
            return this.requestOnly;
        }
    }
}
