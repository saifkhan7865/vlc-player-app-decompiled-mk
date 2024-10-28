package io.netty.handler.codec.http2;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import io.netty.util.AsciiString;
import io.netty.util.internal.PlatformDependent;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.http2.Header;

final class HpackStaticTable {
    private static final HeaderIndex[] HEADERS_WITH_NON_EMPTY_VALUES = new HeaderIndex[64];
    private static final int HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SHIFT;
    private static final int HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SIZE = 64;
    private static final HeaderNameIndex[] HEADER_NAMES = new HeaderNameIndex[512];
    private static final int HEADER_NAMES_TABLE_SHIFT;
    private static final int HEADER_NAMES_TABLE_SIZE = 512;
    static final int NOT_FOUND = -1;
    private static final List<HpackHeaderField> STATIC_TABLE;
    static final int length = STATIC_TABLE.size();

    static {
        int i = 0;
        int i2 = 18;
        List<HpackHeaderField> asList = Arrays.asList(new HpackHeaderField[]{newEmptyHeaderField(Header.TARGET_AUTHORITY_UTF8), newHeaderField(Header.TARGET_METHOD_UTF8, "GET"), newHeaderField(Header.TARGET_METHOD_UTF8, "POST"), newHeaderField(Header.TARGET_PATH_UTF8, "/"), newHeaderField(Header.TARGET_PATH_UTF8, "/index.html"), newHeaderField(Header.TARGET_SCHEME_UTF8, "http"), newHeaderField(Header.TARGET_SCHEME_UTF8, "https"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "200"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "204"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "206"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "304"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "400"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "404"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "500"), newEmptyHeaderField("accept-charset"), newHeaderField("accept-encoding", "gzip, deflate"), newEmptyHeaderField("accept-language"), newEmptyHeaderField("accept-ranges"), newEmptyHeaderField("accept"), newEmptyHeaderField("access-control-allow-origin"), newEmptyHeaderField("age"), newEmptyHeaderField("allow"), newEmptyHeaderField("authorization"), newEmptyHeaderField("cache-control"), newEmptyHeaderField("content-disposition"), newEmptyHeaderField("content-encoding"), newEmptyHeaderField("content-language"), newEmptyHeaderField("content-length"), newEmptyHeaderField("content-location"), newEmptyHeaderField("content-range"), newEmptyHeaderField("content-type"), newEmptyHeaderField("cookie"), newEmptyHeaderField("date"), newEmptyHeaderField("etag"), newEmptyHeaderField("expect"), newEmptyHeaderField("expires"), newEmptyHeaderField(TypedValues.TransitionType.S_FROM), newEmptyHeaderField("host"), newEmptyHeaderField("if-match"), newEmptyHeaderField("if-modified-since"), newEmptyHeaderField("if-none-match"), newEmptyHeaderField("if-range"), newEmptyHeaderField("if-unmodified-since"), newEmptyHeaderField("last-modified"), newEmptyHeaderField("link"), newEmptyHeaderField("location"), newEmptyHeaderField("max-forwards"), newEmptyHeaderField("proxy-authenticate"), newEmptyHeaderField("proxy-authorization"), newEmptyHeaderField("range"), newEmptyHeaderField("referer"), newEmptyHeaderField("refresh"), newEmptyHeaderField("retry-after"), newEmptyHeaderField("server"), newEmptyHeaderField("set-cookie"), newEmptyHeaderField("strict-transport-security"), newEmptyHeaderField("transfer-encoding"), newEmptyHeaderField("user-agent"), newEmptyHeaderField("vary"), newEmptyHeaderField("via"), newEmptyHeaderField("www-authenticate")});
        STATIC_TABLE = asList;
        if (PlatformDependent.BIG_ENDIAN_NATIVE_ORDER) {
            i2 = 22;
        }
        HEADER_NAMES_TABLE_SHIFT = i2;
        int size = asList.size();
        while (size > 0) {
            HpackHeaderField entry = getEntry(size);
            int headerNameBucket = headerNameBucket(entry.name);
            HeaderNameIndex[] headerNameIndexArr = HEADER_NAMES;
            HeaderNameIndex headerNameIndex = headerNameIndexArr[headerNameBucket];
            if (headerNameIndex == null || HpackUtil.equalsVariableTime(headerNameIndex.name, entry.name)) {
                headerNameIndexArr[headerNameBucket] = new HeaderNameIndex(entry.name, size, entry.value.length() == 0);
                size--;
            } else {
                throw new IllegalStateException("Hash bucket collision between " + headerNameIndex.name + " and " + entry.name);
            }
        }
        if (!PlatformDependent.BIG_ENDIAN_NATIVE_ORDER) {
            i = 6;
        }
        HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SHIFT = i;
        for (int size2 = STATIC_TABLE.size(); size2 > 0; size2--) {
            HpackHeaderField entry2 = getEntry(size2);
            if (entry2.value.length() > 0) {
                int headerBucket = headerBucket(entry2.value);
                HeaderIndex[] headerIndexArr = HEADERS_WITH_NON_EMPTY_VALUES;
                HeaderIndex headerIndex = headerIndexArr[headerBucket];
                if (headerIndex == null) {
                    headerIndexArr[headerBucket] = new HeaderIndex(entry2.name, entry2.value, size2);
                } else {
                    throw new IllegalStateException("Hash bucket collision between " + headerIndex.value + " and " + entry2.value);
                }
            }
        }
    }

    private static HpackHeaderField newEmptyHeaderField(String str) {
        return new HpackHeaderField(AsciiString.cached(str), AsciiString.EMPTY_STRING);
    }

    private static HpackHeaderField newHeaderField(String str, String str2) {
        return new HpackHeaderField(AsciiString.cached(str), AsciiString.cached(str2));
    }

    static HpackHeaderField getEntry(int i) {
        return STATIC_TABLE.get(i - 1);
    }

    static int getIndex(CharSequence charSequence) {
        HeaderNameIndex entry = getEntry(charSequence);
        if (entry == null) {
            return -1;
        }
        return entry.index;
    }

    static int getIndexInsensitive(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() == 0) {
            HeaderNameIndex entry = getEntry(charSequence);
            if (entry == null || !entry.emptyValue) {
                return -1;
            }
            return entry.index;
        }
        HeaderIndex headerIndex = HEADERS_WITH_NON_EMPTY_VALUES[headerBucket(charSequence2)];
        if (headerIndex != null && HpackUtil.equalsVariableTime(headerIndex.name, charSequence) && HpackUtil.equalsVariableTime(headerIndex.value, charSequence2)) {
            return headerIndex.index;
        }
        return -1;
    }

    private static HeaderNameIndex getEntry(CharSequence charSequence) {
        HeaderNameIndex headerNameIndex = HEADER_NAMES[headerNameBucket(charSequence)];
        if (headerNameIndex == null) {
            return null;
        }
        if (HpackUtil.equalsVariableTime(headerNameIndex.name, charSequence)) {
            return headerNameIndex;
        }
        return null;
    }

    private static int headerNameBucket(CharSequence charSequence) {
        return bucket(charSequence, HEADER_NAMES_TABLE_SHIFT, FrameMetricsAggregator.EVERY_DURATION);
    }

    private static int headerBucket(CharSequence charSequence) {
        return bucket(charSequence, HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SHIFT, 63);
    }

    private static int bucket(CharSequence charSequence, int i, int i2) {
        return (AsciiString.hashCode(charSequence) >> i) & i2;
    }

    private static final class HeaderNameIndex {
        final boolean emptyValue;
        final int index;
        final CharSequence name;

        HeaderNameIndex(CharSequence charSequence, int i, boolean z) {
            this.name = charSequence;
            this.index = i;
            this.emptyValue = z;
        }
    }

    private static final class HeaderIndex {
        final int index;
        final CharSequence name;
        final CharSequence value;

        HeaderIndex(CharSequence charSequence, CharSequence charSequence2, int i) {
            this.name = charSequence;
            this.value = charSequence2;
            this.index = i;
        }
    }

    private HpackStaticTable() {
    }
}
