package io.ktor.http.cio;

import io.ktor.http.HttpMethod;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.cio.internals.CharsKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\"\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t\u001a\u000e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001aG\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a)\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a=\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"expectHttpBody", "", "method", "Lio/ktor/http/HttpMethod;", "contentLength", "", "transferEncoding", "", "connectionOptions", "Lio/ktor/http/cio/ConnectionOptions;", "contentType", "request", "Lio/ktor/http/cio/Request;", "expectHttpUpgrade", "upgrade", "isTransferEncodingChunked", "parseHttpBody", "", "version", "Lio/ktor/http/HttpProtocolVersion;", "input", "Lio/ktor/utils/io/ByteReadChannel;", "out", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/http/HttpProtocolVersion;JLjava/lang/CharSequence;Lio/ktor/http/cio/ConnectionOptions;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "(Lio/ktor/http/cio/HttpHeadersMap;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(JLjava/lang/CharSequence;Lio/ktor/http/cio/ConnectionOptions;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpBody.kt */
public final class HttpBodyKt {
    public static final boolean expectHttpUpgrade(HttpMethod httpMethod, CharSequence charSequence, ConnectionOptions connectionOptions) {
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        return Intrinsics.areEqual((Object) httpMethod, (Object) HttpMethod.Companion.getGet()) && charSequence != null && connectionOptions != null && connectionOptions.getUpgrade();
    }

    public static final boolean expectHttpUpgrade(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return expectHttpUpgrade(request.getMethod(), request.getHeaders().get("Upgrade"), ConnectionOptions.Companion.parse(request.getHeaders().get("Connection")));
    }

    public static final boolean expectHttpBody(HttpMethod httpMethod, long j, CharSequence charSequence, ConnectionOptions connectionOptions, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        if (charSequence != null) {
            isTransferEncodingChunked(charSequence);
            return true;
        } else if (j != -1) {
            return j > 0;
        } else {
            if (charSequence2 != null) {
                return true;
            }
            return !Intrinsics.areEqual((Object) httpMethod, (Object) HttpMethod.Companion.getGet()) && !Intrinsics.areEqual((Object) httpMethod, (Object) HttpMethod.Companion.getHead()) && !Intrinsics.areEqual((Object) httpMethod, (Object) HttpMethod.Companion.getOptions()) && connectionOptions != null && connectionOptions.getClose();
        }
    }

    public static final boolean expectHttpBody(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        HttpMethod method = request.getMethod();
        CharSequence charSequence = request.getHeaders().get("Content-Length");
        return expectHttpBody(method, charSequence != null ? CharsKt.parseDecLong(charSequence) : -1, request.getHeaders().get("Transfer-Encoding"), ConnectionOptions.Companion.parse(request.getHeaders().get("Connection")), request.getHeaders().get("Content-Type"));
    }

    public static final Object parseHttpBody(HttpProtocolVersion httpProtocolVersion, long j, CharSequence charSequence, ConnectionOptions connectionOptions, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        if (charSequence != null && isTransferEncodingChunked(charSequence)) {
            Object decodeChunked = ChunkedTransferEncodingKt.decodeChunked(byteReadChannel, byteWriteChannel, continuation);
            return decodeChunked == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? decodeChunked : Unit.INSTANCE;
        } else if (j != -1) {
            Object copyTo = ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, j, continuation);
            if (copyTo == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return copyTo;
            }
            return Unit.INSTANCE;
        } else if ((connectionOptions == null || !connectionOptions.getClose()) && (connectionOptions != null || !Intrinsics.areEqual((Object) httpProtocolVersion, (Object) HttpProtocolVersion.Companion.getHTTP_1_0()))) {
            byteWriteChannel.close(new IllegalStateException("Failed to parse request body: request body length should be specified,\nchunked transfer encoding should be used or\nkeep-alive should be disabled (connection: close)"));
            return Unit.INSTANCE;
        } else {
            Object copyTo2 = ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, Long.MAX_VALUE, continuation);
            if (copyTo2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return copyTo2;
            }
            return Unit.INSTANCE;
        }
    }

    @Deprecated(message = "Please use method with version parameter")
    public static final Object parseHttpBody(long j, CharSequence charSequence, ConnectionOptions connectionOptions, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        Object parseHttpBody = parseHttpBody((HttpProtocolVersion) null, j, charSequence, connectionOptions, byteReadChannel, byteWriteChannel, continuation);
        return parseHttpBody == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? parseHttpBody : Unit.INSTANCE;
    }

    public static final Object parseHttpBody(HttpHeadersMap httpHeadersMap, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        CharSequence charSequence = httpHeadersMap.get("Content-Length");
        Object parseHttpBody = parseHttpBody((HttpProtocolVersion) null, charSequence != null ? CharsKt.parseDecLong(charSequence) : -1, httpHeadersMap.get("Transfer-Encoding"), ConnectionOptions.Companion.parse(httpHeadersMap.get("Connection")), byteReadChannel, byteWriteChannel, continuation);
        return parseHttpBody == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? parseHttpBody : Unit.INSTANCE;
    }

    private static final boolean isTransferEncodingChunked(CharSequence charSequence) {
        if (CharsKt.equalsLowerCase$default(charSequence, 0, 0, HttpHeaders.Values.CHUNKED, 3, (Object) null)) {
            return true;
        }
        boolean z = false;
        if (CharsKt.equalsLowerCase$default(charSequence, 0, 0, "identity", 3, (Object) null)) {
            return false;
        }
        for (String trim : StringsKt.split$default(charSequence, new String[]{AnsiRenderer.CODE_LIST_SEPARATOR}, false, 0, 6, (Object) null)) {
            String lowerCase = StringsKt.trim((CharSequence) trim).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual((Object) lowerCase, (Object) HttpHeaders.Values.CHUNKED)) {
                if (!z) {
                    z = true;
                } else {
                    throw new IllegalArgumentException("Double-chunked TE is not supported: " + charSequence);
                }
            } else if (!Intrinsics.areEqual((Object) lowerCase, (Object) "identity")) {
                throw new IllegalArgumentException("Unsupported transfer encoding " + lowerCase);
            }
        }
        return z;
    }
}
