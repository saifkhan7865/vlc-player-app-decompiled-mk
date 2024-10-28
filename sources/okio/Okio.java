package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.io.path.LinkFollowing$$ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007\u001a\n\u0010\b\u001a\u00020\u0006*\u00020\t\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u0006\u001a\n\u0010\n\u001a\u00020\f*\u00020\r\u001a\u0016\u0010\u000e\u001a\u00020\u0006*\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0001H\u0007\u001a\n\u0010\u000e\u001a\u00020\u0006*\u00020\u0010\u001a\n\u0010\u000e\u001a\u00020\u0006*\u00020\u0011\u001a%\u0010\u000e\u001a\u00020\u0006*\u00020\u00122\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a\n\u0010\u0017\u001a\u00020\r*\u00020\t\u001a\n\u0010\u0017\u001a\u00020\r*\u00020\u0018\u001a\n\u0010\u0017\u001a\u00020\r*\u00020\u0011\u001a%\u0010\u0017\u001a\u00020\r*\u00020\u00122\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0019\"\u001c\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038@X\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0004¨\u0006\u001a"}, d2 = {"isAndroidGetsocknameError", "", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "(Ljava/lang/AssertionError;)Z", "blackholeSink", "Lokio/Sink;", "blackhole", "appendingSink", "Ljava/io/File;", "buffer", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "Lokio/Source;", "sink", "append", "Ljava/io/OutputStream;", "Ljava/net/Socket;", "Ljava/nio/file/Path;", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Sink;", "source", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Source;", "jvm"}, k = 2, mv = {1, 1, 11})
/* compiled from: Okio.kt */
public final class Okio {
    public static final Sink sink(File file) throws FileNotFoundException {
        return sink$default(file, false, 1, (Object) null);
    }

    public static final BufferedSource buffer(Source source) {
        Intrinsics.checkParameterIsNotNull(source, "$receiver");
        return new RealBufferedSource(source);
    }

    public static final BufferedSink buffer(Sink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "$receiver");
        return new RealBufferedSink(sink);
    }

    public static final Sink sink(OutputStream outputStream) {
        Intrinsics.checkParameterIsNotNull(outputStream, "$receiver");
        return new OutputStreamSink(outputStream, new Timeout());
    }

    public static final Source source(InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(inputStream, "$receiver");
        return new InputStreamSource(inputStream, new Timeout());
    }

    public static final Sink blackhole() {
        return new BlackholeSink();
    }

    public static final Sink sink(Socket socket) throws IOException {
        Intrinsics.checkParameterIsNotNull(socket, "$receiver");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        OutputStream outputStream = socket.getOutputStream();
        Intrinsics.checkExpressionValueIsNotNull(outputStream, "getOutputStream()");
        return socketAsyncTimeout.sink(new OutputStreamSink(outputStream, socketAsyncTimeout));
    }

    public static final Source source(Socket socket) throws IOException {
        Intrinsics.checkParameterIsNotNull(socket, "$receiver");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        InputStream inputStream = socket.getInputStream();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "getInputStream()");
        return socketAsyncTimeout.source(new InputStreamSource(inputStream, socketAsyncTimeout));
    }

    public static final Sink sink(File file, boolean z) throws FileNotFoundException {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return sink((OutputStream) new FileOutputStream(file, z));
    }

    public static /* bridge */ /* synthetic */ Sink sink$default(File file, boolean z, int i, Object obj) throws FileNotFoundException {
        if ((i & 1) != 0) {
            z = false;
        }
        return sink(file, z);
    }

    public static final Sink appendingSink(File file) throws FileNotFoundException {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return sink((OutputStream) new FileOutputStream(file, true));
    }

    public static final Source source(File file) throws FileNotFoundException {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return source((InputStream) new FileInputStream(file));
    }

    public static final Sink sink(Path path, OpenOption... openOptionArr) throws IOException {
        Intrinsics.checkParameterIsNotNull(path, "$receiver");
        Intrinsics.checkParameterIsNotNull(openOptionArr, "options");
        OutputStream m = LinkFollowing$$ExternalSyntheticApiModelOutline0.m(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkExpressionValueIsNotNull(m, "Files.newOutputStream(this, *options)");
        return sink(m);
    }

    public static final Source source(Path path, OpenOption... openOptionArr) throws IOException {
        Intrinsics.checkParameterIsNotNull(path, "$receiver");
        Intrinsics.checkParameterIsNotNull(openOptionArr, "options");
        InputStream m = LinkFollowing$$ExternalSyntheticApiModelOutline0.m(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkExpressionValueIsNotNull(m, "Files.newInputStream(this, *options)");
        return source(m);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r4 = r4.getMessage();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isAndroidGetsocknameError(java.lang.AssertionError r4) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.Throwable r0 = r4.getCause()
            r1 = 0
            if (r0 == 0) goto L_0x0021
            java.lang.String r4 = r4.getMessage()
            if (r4 == 0) goto L_0x0021
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r0 = "getsockname failed"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2 = 2
            r3 = 0
            boolean r4 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r4, (java.lang.CharSequence) r0, (boolean) r1, (int) r2, (java.lang.Object) r3)
            if (r4 == 0) goto L_0x0021
            r1 = 1
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Okio.isAndroidGetsocknameError(java.lang.AssertionError):boolean");
    }
}
