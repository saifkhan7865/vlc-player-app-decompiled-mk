package io.ktor.network.tls;

import io.ktor.network.util.PoolsKt;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.PreviewKt;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b@\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\"¢\u0006\u0004\b#\u0010$R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0001\u0003ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lio/ktor/network/tls/Digest;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "state", "Lio/ktor/utils/io/core/BytePacketBuilder;", "constructor-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;)Lio/ktor/utils/io/core/BytePacketBuilder;", "getState", "()Lio/ktor/utils/io/core/BytePacketBuilder;", "close", "", "close-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;)V", "doHash", "", "hashName", "", "doHash-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/lang/String;)[B", "equals", "", "other", "", "equals-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;)I", "toString", "toString-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;)Ljava/lang/String;", "update", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "update-impl", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lio/ktor/utils/io/core/ByteReadPacket;)V", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* compiled from: Utils.kt */
public final class Digest implements Closeable {
    private final BytePacketBuilder state;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Digest m1462boximpl(BytePacketBuilder bytePacketBuilder) {
        return new Digest(bytePacketBuilder);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static BytePacketBuilder m1464constructorimpl(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, OAuth2RequestParameters.State);
        return bytePacketBuilder;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1466equalsimpl(BytePacketBuilder bytePacketBuilder, Object obj) {
        return (obj instanceof Digest) && Intrinsics.areEqual((Object) bytePacketBuilder, (Object) ((Digest) obj).m1471unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1467equalsimpl0(BytePacketBuilder bytePacketBuilder, BytePacketBuilder bytePacketBuilder2) {
        return Intrinsics.areEqual((Object) bytePacketBuilder, (Object) bytePacketBuilder2);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1468hashCodeimpl(BytePacketBuilder bytePacketBuilder) {
        return bytePacketBuilder.hashCode();
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1469toStringimpl(BytePacketBuilder bytePacketBuilder) {
        return "Digest(state=" + bytePacketBuilder + ')';
    }

    public boolean equals(Object obj) {
        return m1466equalsimpl(this.state, obj);
    }

    public int hashCode() {
        return m1468hashCodeimpl(this.state);
    }

    public String toString() {
        return m1469toStringimpl(this.state);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ BytePacketBuilder m1471unboximpl() {
        return this.state;
    }

    private /* synthetic */ Digest(BytePacketBuilder bytePacketBuilder) {
        this.state = bytePacketBuilder;
    }

    public final BytePacketBuilder getState() {
        return this.state;
    }

    /* renamed from: update-impl  reason: not valid java name */
    public static final void m1470updateimpl(BytePacketBuilder bytePacketBuilder, ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "packet");
        synchronized (bytePacketBuilder) {
            if (!byteReadPacket.getEndOfInput()) {
                bytePacketBuilder.writePacket(byteReadPacket.copy());
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* renamed from: doHash-impl  reason: not valid java name */
    public static final byte[] m1465doHashimpl(BytePacketBuilder bytePacketBuilder, String str) {
        ByteBuffer borrow;
        byte[] digest;
        Intrinsics.checkNotNullParameter(str, "hashName");
        synchronized (bytePacketBuilder) {
            ByteReadPacket preview = PreviewKt.preview(bytePacketBuilder);
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                Intrinsics.checkNotNull(instance);
                borrow = PoolsKt.getDefaultByteBufferPool().borrow();
                while (!preview.getEndOfInput() && ByteBuffersKt.readAvailable(preview, borrow) != -1) {
                    borrow.flip();
                    instance.update(borrow);
                    borrow.clear();
                }
                digest = instance.digest();
                PoolsKt.getDefaultByteBufferPool().recycle(borrow);
                preview.release();
            } catch (Throwable th) {
                preview.release();
                throw th;
            }
        }
        Intrinsics.checkNotNullExpressionValue(digest, "synchronized(state) {\n  …        }\n        }\n    }");
        return digest;
    }

    public void close() {
        m1463closeimpl(this.state);
    }

    /* renamed from: close-impl  reason: not valid java name */
    public static void m1463closeimpl(BytePacketBuilder bytePacketBuilder) {
        bytePacketBuilder.release();
    }
}
