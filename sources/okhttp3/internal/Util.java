package okhttp3.internal;

import io.ktor.http.ContentDisposition;
import j$.nio.charset.StandardCharsets;
import j$.util.DesugarTimeZone;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Options;
import okio.Source;
import org.fusesource.jansi.AnsiConsole;
import org.videolan.vlc.gui.dialogs.PickTimeFragment;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u001a\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0013\u001a'\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d\"\u00020\u001e¢\u0006\u0002\u0010\u001f\u001a\u0017\u0010 \u001a\u00020\u00172\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00170\"H\b\u001a-\u0010#\u001a\b\u0012\u0004\u0012\u0002H%0$\"\u0004\b\u0000\u0010%2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u0002H%0\u001d\"\u0002H%H\u0007¢\u0006\u0002\u0010'\u001a1\u0010(\u001a\u0004\u0018\u0001H%\"\u0004\b\u0000\u0010%2\u0006\u0010)\u001a\u00020\u001e2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H%0+2\u0006\u0010,\u001a\u00020\u0011¢\u0006\u0002\u0010-\u001a\u0016\u0010.\u001a\u00020/2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00100\u001a\u000201\u001a\u001f\u00102\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00170\"H\b\u001a\u0015\u00103\u001a\u00020\u000f*\u0002042\u0006\u00105\u001a\u00020\u000fH\u0004\u001a\u0015\u00103\u001a\u00020\u0013*\u00020\u000f2\u0006\u00105\u001a\u00020\u0013H\u0004\u001a\u0015\u00103\u001a\u00020\u000f*\u0002062\u0006\u00105\u001a\u00020\u000fH\u0004\u001a\n\u00107\u001a\u000208*\u000209\u001a\n\u0010:\u001a\u000201*\u00020\u0011\u001a\u0012\u0010;\u001a\u000201*\u00020<2\u0006\u0010=\u001a\u00020<\u001a\n\u0010>\u001a\u00020\u0017*\u00020?\u001a\n\u0010>\u001a\u00020\u0017*\u00020@\u001a\n\u0010>\u001a\u00020\u0017*\u00020A\u001a#\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00110\u001d*\b\u0012\u0004\u0012\u00020\u00110\u001d2\u0006\u0010C\u001a\u00020\u0011¢\u0006\u0002\u0010D\u001a\n\u0010E\u001a\u00020\u0011*\u00020A\u001a&\u0010F\u001a\u00020\u000f*\u00020\u00112\u0006\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020\u000f2\b\b\u0002\u0010J\u001a\u00020\u000f\u001a&\u0010F\u001a\u00020\u000f*\u00020\u00112\u0006\u0010K\u001a\u00020\u00112\b\b\u0002\u0010I\u001a\u00020\u000f2\b\b\u0002\u0010J\u001a\u00020\u000f\u001a\u001a\u0010L\u001a\u000201*\u00020M2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u0015\u001a%\u0010P\u001a\u00020\u0017*\u00020Q2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0004\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00170\"H\b\u001a5\u0010R\u001a\u000201*\b\u0012\u0004\u0012\u00020\u00110\u001d2\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001d2\u000e\u0010S\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00110T¢\u0006\u0002\u0010U\u001a\n\u0010V\u001a\u00020\u0013*\u00020W\u001a+\u0010X\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u00110\u001d2\u0006\u0010C\u001a\u00020\u00112\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00110T¢\u0006\u0002\u0010Y\u001a\n\u0010Z\u001a\u00020\u000f*\u00020\u0011\u001a\u001e\u0010[\u001a\u00020\u000f*\u00020\u00112\b\b\u0002\u0010I\u001a\u00020\u000f2\b\b\u0002\u0010J\u001a\u00020\u000f\u001a\u001e\u0010\\\u001a\u00020\u000f*\u00020\u00112\b\b\u0002\u0010I\u001a\u00020\u000f2\b\b\u0002\u0010J\u001a\u00020\u000f\u001a\u0014\u0010]\u001a\u00020\u000f*\u00020\u00112\b\b\u0002\u0010I\u001a\u00020\u000f\u001a9\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00110\u001d*\b\u0012\u0004\u0012\u00020\u00110\u001d2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00110\u001d2\u000e\u0010S\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00110T¢\u0006\u0002\u0010_\u001a\u0012\u0010`\u001a\u00020\u0017*\u00020\u001e2\u0006\u0010a\u001a\u00020\u0013\u001a\r\u0010b\u001a\u00020\u0017*\u00020\u001eH\b\u001a\r\u0010c\u001a\u00020\u0017*\u00020\u001eH\b\u001a\n\u0010d\u001a\u00020\u000f*\u00020H\u001a\u0012\u0010e\u001a\u00020f*\u00020g2\u0006\u0010h\u001a\u00020f\u001a\n\u0010i\u001a\u00020\u000f*\u00020g\u001a\u0012\u0010j\u001a\u00020\u000f*\u00020k2\u0006\u0010l\u001a\u000204\u001a\u001a\u0010j\u001a\u000201*\u00020M2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u0015\u001a\u0010\u0010m\u001a\b\u0012\u0004\u0012\u00020n0$*\u00020\u0003\u001a\u0010\u0010o\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020n0$\u001a\n\u0010p\u001a\u00020\u0011*\u00020\u000f\u001a\n\u0010p\u001a\u00020\u0011*\u00020\u0013\u001a\u0014\u0010q\u001a\u00020\u0011*\u00020<2\b\b\u0002\u0010r\u001a\u000201\u001a\u001c\u0010s\u001a\b\u0012\u0004\u0012\u0002H%0$\"\u0004\b\u0000\u0010%*\b\u0012\u0004\u0012\u0002H%0$\u001a.\u0010t\u001a\u000e\u0012\u0004\u0012\u0002Hv\u0012\u0004\u0012\u0002Hw0u\"\u0004\b\u0000\u0010v\"\u0004\b\u0001\u0010w*\u000e\u0012\u0004\u0012\u0002Hv\u0012\u0004\u0012\u0002Hw0u\u001a\u0012\u0010x\u001a\u00020\u0013*\u00020\u00112\u0006\u0010y\u001a\u00020\u0013\u001a\u0014\u0010z\u001a\u00020\u000f*\u0004\u0018\u00010\u00112\u0006\u0010y\u001a\u00020\u000f\u001a\u001e\u0010{\u001a\u00020\u0011*\u00020\u00112\b\b\u0002\u0010I\u001a\u00020\u000f2\b\b\u0002\u0010J\u001a\u00020\u000f\u001a%\u0010|\u001a\u00020\u0017*\u00020Q2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0004\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00170\"H\b\u001a\r\u0010}\u001a\u00020\u0017*\u00020\u001eH\b\u001a\u001c\u0010~\u001a\u00020\u0017*\u00020\u001e2\u0006\u0010N\u001a\u00020\u00132\b\b\u0002\u0010a\u001a\u00020\u000f\u001a\u0014\u0010\u001a\u00020\u0017*\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u000f\"\u0010\u0010\u0000\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001"}, d2 = {"EMPTY_BYTE_ARRAY", "", "EMPTY_HEADERS", "Lokhttp3/Headers;", "EMPTY_REQUEST", "Lokhttp3/RequestBody;", "EMPTY_RESPONSE", "Lokhttp3/ResponseBody;", "UNICODE_BOMS", "Lokio/Options;", "UTC", "Ljava/util/TimeZone;", "VERIFY_AS_IP_ADDRESS", "Lkotlin/text/Regex;", "checkDuration", "", "name", "", "duration", "", "unit", "Ljava/util/concurrent/TimeUnit;", "checkOffsetAndCount", "", "arrayLength", "offset", "count", "format", "args", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "ignoreIoExceptions", "block", "Lkotlin/Function0;", "immutableListOf", "", "T", "elements", "([Ljava/lang/Object;)Ljava/util/List;", "readFieldOrNull", "instance", "fieldType", "Ljava/lang/Class;", "fieldName", "(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "daemon", "", "threadName", "and", "", "mask", "", "asFactory", "Lokhttp3/EventListener$Factory;", "Lokhttp3/EventListener;", "canParseAsIpAddress", "canReuseConnectionFor", "Lokhttp3/HttpUrl;", "other", "closeQuietly", "Ljava/io/Closeable;", "Ljava/net/ServerSocket;", "Ljava/net/Socket;", "concat", "value", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "connectionName", "delimiterOffset", "delimiter", "", "startIndex", "endIndex", "delimiters", "discard", "Lokio/Source;", "timeout", "timeUnit", "execute", "Ljava/util/concurrent/Executor;", "hasIntersection", "comparator", "Ljava/util/Comparator;", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)Z", "headersContentLength", "Lokhttp3/Response;", "indexOf", "([Ljava/lang/String;Ljava/lang/String;Ljava/util/Comparator;)I", "indexOfControlOrNonAscii", "indexOfFirstNonAsciiWhitespace", "indexOfLastNonAsciiWhitespace", "indexOfNonWhitespace", "intersect", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)[Ljava/lang/String;", "lockAndWaitNanos", "nanos", "notify", "notifyAll", "parseHexDigit", "readBomAsCharset", "Ljava/nio/charset/Charset;", "Lokio/BufferedSource;", "default", "readMedium", "skipAll", "Lokio/Buffer;", "b", "toHeaderList", "Lokhttp3/internal/http2/Header;", "toHeaders", "toHexString", "toHostHeader", "includeDefaultPort", "toImmutableList", "toImmutableMap", "", "K", "V", "toLongOrDefault", "defaultValue", "toNonNegativeInt", "trimSubstring", "tryExecute", "wait", "waitMillis", "writeMedium", "Lokio/BufferedSink;", "medium", "okhttp"}, k = 2, mv = {1, 1, 15})
/* compiled from: Util.kt */
public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final Headers EMPTY_HEADERS = Headers.Companion.of(new String[0]);
    public static final RequestBody EMPTY_REQUEST;
    public static final ResponseBody EMPTY_RESPONSE;
    private static final Options UNICODE_BOMS = Options.Companion.of(ByteString.Companion.decodeHex("efbbbf"), ByteString.Companion.decodeHex("feff"), ByteString.Companion.decodeHex("fffe"), ByteString.Companion.decodeHex("0000ffff"), ByteString.Companion.decodeHex("ffff0000"));
    public static final TimeZone UTC;
    private static final Regex VERIFY_AS_IP_ADDRESS = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static final int and(byte b, int i) {
        return b & i;
    }

    public static final int and(short s, int i) {
        return s & i;
    }

    public static final long and(int i, long j) {
        return j & ((long) i);
    }

    public static final int parseHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        if ('a' <= c && 'f' >= c) {
            return c - 'W';
        }
        if ('A' <= c && 'F' >= c) {
            return c - '7';
        }
        return -1;
    }

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_RESPONSE = ResponseBody.Companion.create$default(ResponseBody.Companion, bArr, (MediaType) null, 1, (Object) null);
        EMPTY_REQUEST = RequestBody.Companion.create$default(RequestBody.Companion, bArr, (MediaType) null, 0, 0, 7, (Object) null);
        TimeZone timeZone = DesugarTimeZone.getTimeZone("GMT");
        if (timeZone == null) {
            Intrinsics.throwNpe();
        }
        UTC = timeZone;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final ThreadFactory threadFactory(String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, ContentDisposition.Parameters.Name);
        return new Util$threadFactory$1(str, z);
    }

    public static final String[] intersect(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        Intrinsics.checkParameterIsNotNull(strArr, "$this$intersect");
        Intrinsics.checkParameterIsNotNull(strArr2, "other");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        List arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (comparator.compare(str, strArr2[i]) == 0) {
                    arrayList.add(str);
                    break;
                } else {
                    i++;
                }
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final boolean hasIntersection(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        Intrinsics.checkParameterIsNotNull(strArr, "$this$hasIntersection");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (!(strArr.length == 0 || strArr2 == null || strArr2.length == 0)) {
            for (String str : strArr) {
                for (String compare : strArr2) {
                    if (comparator.compare(str, compare) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static /* synthetic */ String toHostHeader$default(HttpUrl httpUrl, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toHostHeader(httpUrl, z);
    }

    public static final String toHostHeader(HttpUrl httpUrl, boolean z) {
        String str;
        Intrinsics.checkParameterIsNotNull(httpUrl, "$this$toHostHeader");
        if (StringsKt.contains$default((CharSequence) httpUrl.host(), (CharSequence) ":", false, 2, (Object) null)) {
            str = "[" + httpUrl.host() + AbstractJsonLexerKt.END_LIST;
        } else {
            str = httpUrl.host();
        }
        if (!z && httpUrl.port() == HttpUrl.Companion.defaultPort(httpUrl.scheme())) {
            return str;
        }
        return str + AbstractJsonLexerKt.COLON + httpUrl.port();
    }

    public static final String[] concat(String[] strArr, String str) {
        Intrinsics.checkParameterIsNotNull(strArr, "$this$concat");
        Intrinsics.checkParameterIsNotNull(str, "value");
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length + 1);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        String[] strArr2 = (String[]) copyOf;
        strArr2[ArraysKt.getLastIndex((T[]) strArr2)] = str;
        if (strArr2 != null) {
            return strArr2;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
    }

    public static /* synthetic */ int indexOfFirstNonAsciiWhitespace$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return indexOfFirstNonAsciiWhitespace(str, i, i2);
    }

    public static final int indexOfFirstNonAsciiWhitespace(String str, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "$this$indexOfFirstNonAsciiWhitespace");
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt != 9 && charAt != 10 && charAt != 12 && charAt != 13 && charAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int indexOfLastNonAsciiWhitespace$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return indexOfLastNonAsciiWhitespace(str, i, i2);
    }

    public static final int indexOfLastNonAsciiWhitespace(String str, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "$this$indexOfLastNonAsciiWhitespace");
        int i3 = i2 - 1;
        if (i3 >= i) {
            while (true) {
                char charAt = str.charAt(i3);
                if (charAt == 9 || charAt == 10 || charAt == 12 || charAt == 13 || charAt == ' ') {
                    if (i3 == i) {
                        break;
                    }
                    i3--;
                } else {
                    return i3 + 1;
                }
            }
        }
        return i;
    }

    public static /* synthetic */ String trimSubstring$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return trimSubstring(str, i, i2);
    }

    public static final String trimSubstring(String str, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "$this$trimSubstring");
        int indexOfFirstNonAsciiWhitespace = indexOfFirstNonAsciiWhitespace(str, i, i2);
        String substring = str.substring(indexOfFirstNonAsciiWhitespace, indexOfLastNonAsciiWhitespace(str, indexOfFirstNonAsciiWhitespace, i2));
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ int delimiterOffset$default(String str, String str2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return delimiterOffset(str, str2, i, i2);
    }

    public static final int delimiterOffset(String str, String str2, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "$this$delimiterOffset");
        Intrinsics.checkParameterIsNotNull(str2, "delimiters");
        while (i < i2) {
            if (StringsKt.contains$default((CharSequence) str2, str.charAt(i), false, 2, (Object) null)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int delimiterOffset$default(String str, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return delimiterOffset(str, c, i, i2);
    }

    public static final int delimiterOffset(String str, char c, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "$this$delimiterOffset");
        while (i < i2) {
            if (str.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int indexOfControlOrNonAscii(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$indexOfControlOrNonAscii");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                return i;
            }
        }
        return -1;
    }

    public static final boolean canParseAsIpAddress(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$canParseAsIpAddress");
        return VERIFY_AS_IP_ADDRESS.matches(str);
    }

    public static final String format(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "format");
        Intrinsics.checkParameterIsNotNull(objArr, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        String format = String.format(locale, str, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        return format;
    }

    public static final Charset readBomAsCharset(BufferedSource bufferedSource, Charset charset) throws IOException {
        Intrinsics.checkParameterIsNotNull(bufferedSource, "$this$readBomAsCharset");
        Intrinsics.checkParameterIsNotNull(charset, AnsiConsole.JANSI_MODE_DEFAULT);
        int select = bufferedSource.select(UNICODE_BOMS);
        if (select == -1) {
            return charset;
        }
        if (select == 0) {
            Charset charset2 = StandardCharsets.UTF_8;
            Intrinsics.checkExpressionValueIsNotNull(charset2, "UTF_8");
            return charset2;
        } else if (select == 1) {
            Charset charset3 = StandardCharsets.UTF_16BE;
            Intrinsics.checkExpressionValueIsNotNull(charset3, "UTF_16BE");
            return charset3;
        } else if (select == 2) {
            Charset charset4 = StandardCharsets.UTF_16LE;
            Intrinsics.checkExpressionValueIsNotNull(charset4, "UTF_16LE");
            return charset4;
        } else if (select == 3) {
            return Charsets.INSTANCE.UTF32_BE();
        } else {
            if (select == 4) {
                return Charsets.INSTANCE.UTF32_LE();
            }
            throw new AssertionError();
        }
    }

    public static final int checkDuration(String str, long j, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(str, ContentDisposition.Parameters.Name);
        boolean z = true;
        if (j >= 0) {
            if (timeUnit != null) {
                long millis = timeUnit.toMillis(j);
                if (millis <= ((long) Integer.MAX_VALUE)) {
                    if (millis == 0 && j > 0) {
                        z = false;
                    }
                    if (z) {
                        return (int) millis;
                    }
                    throw new IllegalArgumentException((str + " too small.").toString());
                }
                throw new IllegalArgumentException((str + " too large.").toString());
            }
            throw new IllegalStateException("unit == null".toString());
        }
        throw new IllegalStateException((str + " < 0").toString());
    }

    public static final Headers toHeaders(List<Header> list) {
        Intrinsics.checkParameterIsNotNull(list, "$this$toHeaders");
        Headers.Builder builder = new Headers.Builder();
        for (Header next : list) {
            builder.addLenient$okhttp(next.component1().utf8(), next.component2().utf8());
        }
        return builder.build();
    }

    public static final List<Header> toHeaderList(Headers headers) {
        Intrinsics.checkParameterIsNotNull(headers, "$this$toHeaderList");
        Iterable until = RangesKt.until(0, headers.size());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            arrayList.add(new Header(headers.name(nextInt), headers.value(nextInt)));
        }
        return (List) arrayList;
    }

    public static final boolean canReuseConnectionFor(HttpUrl httpUrl, HttpUrl httpUrl2) {
        Intrinsics.checkParameterIsNotNull(httpUrl, "$this$canReuseConnectionFor");
        Intrinsics.checkParameterIsNotNull(httpUrl2, "other");
        return Intrinsics.areEqual((Object) httpUrl.host(), (Object) httpUrl2.host()) && httpUrl.port() == httpUrl2.port() && Intrinsics.areEqual((Object) httpUrl.scheme(), (Object) httpUrl2.scheme());
    }

    public static final EventListener.Factory asFactory(EventListener eventListener) {
        Intrinsics.checkParameterIsNotNull(eventListener, "$this$asFactory");
        return new Util$asFactory$1(eventListener);
    }

    public static final void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        Intrinsics.checkParameterIsNotNull(bufferedSink, "$this$writeMedium");
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    public static final int readMedium(BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkParameterIsNotNull(bufferedSource, "$this$readMedium");
        return and(bufferedSource.readByte(), 255) | (and(bufferedSource.readByte(), 255) << 16) | (and(bufferedSource.readByte(), 255) << 8);
    }

    public static final boolean skipAll(Source source, int i, TimeUnit timeUnit) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, "$this$skipAll");
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        long nanoTime = System.nanoTime();
        long deadlineNanoTime = source.timeout().hasDeadline() ? source.timeout().deadlineNanoTime() - nanoTime : Long.MAX_VALUE;
        source.timeout().deadlineNanoTime(Math.min(deadlineNanoTime, timeUnit.toNanos((long) i)) + nanoTime);
        try {
            Buffer buffer = new Buffer();
            while (source.read(buffer, 8192) != -1) {
                buffer.clear();
            }
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(nanoTime + deadlineNanoTime);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(nanoTime + deadlineNanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(nanoTime + deadlineNanoTime);
            }
            throw th;
        }
    }

    public static final boolean discard(Source source, int i, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(source, "$this$discard");
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        try {
            return skipAll(source, i, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String connectionName(Socket socket) {
        Intrinsics.checkParameterIsNotNull(socket, "$this$connectionName");
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        if (!(remoteSocketAddress instanceof InetSocketAddress)) {
            return remoteSocketAddress.toString();
        }
        String hostName = ((InetSocketAddress) remoteSocketAddress).getHostName();
        Intrinsics.checkExpressionValueIsNotNull(hostName, "address.hostName");
        return hostName;
    }

    public static final void ignoreIoExceptions(Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "block");
        try {
            function0.invoke();
        } catch (IOException unused) {
        }
    }

    public static final void threadName(String str, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkParameterIsNotNull(function0, "block");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            currentThread.setName(name);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void execute(Executor executor, String str, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(executor, "$this$execute");
        Intrinsics.checkParameterIsNotNull(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkParameterIsNotNull(function0, "block");
        executor.execute(new Util$execute$1(str, function0));
    }

    public static final int skipAll(Buffer buffer, byte b) {
        Intrinsics.checkParameterIsNotNull(buffer, "$this$skipAll");
        int i = 0;
        while (!buffer.exhausted() && buffer.getByte(0) == b) {
            i++;
            buffer.readByte();
        }
        return i;
    }

    public static /* synthetic */ int indexOfNonWhitespace$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return indexOfNonWhitespace(str, i);
    }

    public static final int indexOfNonWhitespace(String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$this$indexOfNonWhitespace");
        int length = str.length();
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != 9) {
                return i;
            }
            i++;
        }
        return str.length();
    }

    public static final long headersContentLength(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "$this$headersContentLength");
        String str = response.headers().get("Content-Length");
        if (str != null) {
            return toLongOrDefault(str, -1);
        }
        return -1;
    }

    public static final long toLongOrDefault(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "$this$toLongOrDefault");
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static final int toNonNegativeInt(String str, int i) {
        if (str != null) {
            try {
                long parseLong = Long.parseLong(str);
                if (parseLong > ((long) Integer.MAX_VALUE)) {
                    return Integer.MAX_VALUE;
                }
                if (parseLong < 0) {
                    return 0;
                }
                return (int) parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public static final <T> List<T> toImmutableList(List<? extends T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$this$toImmutableList");
        List<T> unmodifiableList = Collections.unmodifiableList(CollectionsKt.toMutableList(list));
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiableList(toMutableList())");
        return unmodifiableList;
    }

    @SafeVarargs
    public static final <T> List<T> immutableListOf(T... tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "elements");
        Object[] objArr = (Object[]) tArr.clone();
        List<T> unmodifiableList = Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(objArr, objArr.length)));
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiable…sList(*elements.clone()))");
        return unmodifiableList;
    }

    public static final <K, V> Map<K, V> toImmutableMap(Map<K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "$this$toImmutableMap");
        if (map.isEmpty()) {
            return MapsKt.emptyMap();
        }
        Map<K, V> unmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(map));
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableMap, "Collections.unmodifiableMap(LinkedHashMap(this))");
        return unmodifiableMap;
    }

    public static final void closeQuietly(Closeable closeable) {
        Intrinsics.checkParameterIsNotNull(closeable, "$this$closeQuietly");
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final void closeQuietly(Socket socket) {
        Intrinsics.checkParameterIsNotNull(socket, "$this$closeQuietly");
        try {
            socket.close();
        } catch (AssertionError e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception unused) {
        }
    }

    public static final void closeQuietly(ServerSocket serverSocket) {
        Intrinsics.checkParameterIsNotNull(serverSocket, "$this$closeQuietly");
        try {
            serverSocket.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final String toHexString(long j) {
        String hexString = Long.toHexString(j);
        Intrinsics.checkExpressionValueIsNotNull(hexString, "java.lang.Long.toHexString(this)");
        return hexString;
    }

    public static final String toHexString(int i) {
        String hexString = Integer.toHexString(i);
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(this)");
        return hexString;
    }

    public static final void lockAndWaitNanos(Object obj, long j) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(obj, "$this$lockAndWaitNanos");
        long j2 = j / PickTimeFragment.SECONDS_IN_MICROS;
        long j3 = j - (PickTimeFragment.SECONDS_IN_MICROS * j2);
        synchronized (obj) {
            waitMillis(obj, j2, (int) j3);
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void wait(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$wait");
        obj.wait();
    }

    public static /* synthetic */ void waitMillis$default(Object obj, long j, int i, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        waitMillis(obj, j, i);
    }

    public static final void waitMillis(Object obj, long j, int i) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$waitMillis");
        if (j > 0 || i > 0) {
            obj.wait(j, i);
        }
    }

    public static final void notify(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$notify");
        obj.notify();
    }

    public static final void notifyAll(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$notifyAll");
        obj.notifyAll();
    }

    public static final <T> T readFieldOrNull(Object obj, Class<T> cls, String str) {
        Object readFieldOrNull;
        Intrinsics.checkParameterIsNotNull(obj, "instance");
        Intrinsics.checkParameterIsNotNull(cls, "fieldType");
        Intrinsics.checkParameterIsNotNull(str, "fieldName");
        Class cls2 = obj.getClass();
        while (!Intrinsics.areEqual((Object) cls2, (Object) Object.class)) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                Intrinsics.checkExpressionValueIsNotNull(declaredField, "field");
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (!cls.isInstance(obj2)) {
                    return null;
                }
                return cls.cast(obj2);
            } catch (NoSuchFieldException unused) {
                cls2 = cls2.getSuperclass();
                Intrinsics.checkExpressionValueIsNotNull(cls2, "c.superclass");
            }
        }
        if (!(!Intrinsics.areEqual((Object) str, (Object) "delegate")) || (readFieldOrNull = readFieldOrNull(obj, Object.class, "delegate")) == null) {
            return null;
        }
        return readFieldOrNull(readFieldOrNull, cls, str);
    }

    public static final int indexOf(String[] strArr, String str, Comparator<String> comparator) {
        Intrinsics.checkParameterIsNotNull(strArr, "$this$indexOf");
        Intrinsics.checkParameterIsNotNull(str, "value");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (comparator.compare(strArr[i], str) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static final void tryExecute(Executor executor, String str, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(executor, "$this$tryExecute");
        Intrinsics.checkParameterIsNotNull(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkParameterIsNotNull(function0, "block");
        try {
            executor.execute(new Util$execute$1(str, function0));
        } catch (RejectedExecutionException unused) {
        }
    }
}
