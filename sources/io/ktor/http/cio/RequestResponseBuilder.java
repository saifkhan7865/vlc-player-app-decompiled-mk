package io.ktor.http.cio;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpMethod;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\bJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\bJ\u001e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0012J\u001e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lio/ktor/http/cio/RequestResponseBuilder;", "", "()V", "packet", "Lio/ktor/utils/io/core/BytePacketBuilder;", "build", "Lio/ktor/utils/io/core/ByteReadPacket;", "bytes", "", "content", "Ljava/nio/ByteBuffer;", "", "offset", "", "length", "emptyLine", "headerLine", "name", "", "value", "line", "release", "requestLine", "method", "Lio/ktor/http/HttpMethod;", "uri", "version", "responseLine", "status", "statusText", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestResponseBuilder.kt */
public final class RequestResponseBuilder {
    private final BytePacketBuilder packet = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);

    public final void responseLine(CharSequence charSequence, int i, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, "version");
        Intrinsics.checkNotNullParameter(charSequence2, "statusText");
        StringsKt.writeText$default((Output) this.packet, charSequence, 0, 0, (Charset) null, 14, (Object) null);
        this.packet.writeByte((byte) 32);
        StringsKt.writeText$default((Output) this.packet, (CharSequence) String.valueOf(i), 0, 0, (Charset) null, 14, (Object) null);
        this.packet.writeByte((byte) 32);
        StringsKt.writeText$default((Output) this.packet, charSequence2, 0, 0, (Charset) null, 14, (Object) null);
        this.packet.writeByte((byte) 13);
        this.packet.writeByte((byte) 10);
    }

    public static /* synthetic */ void bytes$default(RequestResponseBuilder requestResponseBuilder, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        requestResponseBuilder.bytes(bArr, i, i2);
    }

    public final void requestLine(HttpMethod httpMethod, CharSequence charSequence, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(charSequence, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(charSequence2, "version");
        StringsKt.writeText$default((Output) this.packet, (CharSequence) httpMethod.getValue(), 0, 0, (Charset) null, 14, (Object) null);
        this.packet.writeByte((byte) 32);
        StringsKt.writeText$default((Output) this.packet, charSequence, 0, 0, (Charset) null, 14, (Object) null);
        this.packet.writeByte((byte) 32);
        StringsKt.writeText$default((Output) this.packet, charSequence2, 0, 0, (Charset) null, 14, (Object) null);
        this.packet.writeByte((byte) 13);
        this.packet.writeByte((byte) 10);
    }

    public final void line(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "line");
        this.packet.append(charSequence);
        this.packet.writeByte((byte) 13);
        this.packet.writeByte((byte) 10);
    }

    public final void bytes(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "content");
        OutputKt.writeFully((Output) this.packet, bArr, i, i2);
    }

    public final void bytes(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "content");
        OutputArraysJVMKt.writeFully(this.packet, byteBuffer);
    }

    public final void headerLine(CharSequence charSequence, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(charSequence2, "value");
        this.packet.append(charSequence);
        this.packet.append((CharSequence) ": ");
        this.packet.append(charSequence2);
        this.packet.writeByte((byte) 13);
        this.packet.writeByte((byte) 10);
    }

    public final void emptyLine() {
        this.packet.writeByte((byte) 13);
        this.packet.writeByte((byte) 10);
    }

    public final ByteReadPacket build() {
        return this.packet.build();
    }

    public final void release() {
        this.packet.release();
    }
}
