package io.ktor.network.tls;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.pool.ObjectPool;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0002\u001a\"\u0010\u0003\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\t"}, d2 = {"Digest", "Lio/ktor/network/tls/Digest;", "()Lio/ktor/utils/io/core/BytePacketBuilder;", "plusAssign", "", "record", "Lio/ktor/network/tls/TLSHandshake;", "plusAssign-Hh8V18w", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lio/ktor/network/tls/TLSHandshake;)V", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class UtilsKt {
    public static final BytePacketBuilder Digest() {
        return Digest.m1464constructorimpl(new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null));
    }

    /* renamed from: plusAssign-Hh8V18w  reason: not valid java name */
    public static final void m1472plusAssignHh8V18w(BytePacketBuilder bytePacketBuilder, TLSHandshake tLSHandshake) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$plusAssign");
        Intrinsics.checkNotNullParameter(tLSHandshake, "record");
        if (tLSHandshake.getType() != TLSHandshakeType.HelloRequest) {
            BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            try {
                RenderKt.writeTLSHandshakeType(bytePacketBuilder2, tLSHandshake.getType(), (int) tLSHandshake.getPacket().getRemaining());
                if (tLSHandshake.getPacket().getRemaining() > 0) {
                    bytePacketBuilder2.writePacket(tLSHandshake.getPacket().copy());
                }
                Digest.m1470updateimpl(bytePacketBuilder, bytePacketBuilder2.build());
            } catch (Throwable th) {
                bytePacketBuilder2.release();
                throw th;
            }
        } else {
            throw new IllegalStateException("Check failed.".toString());
        }
    }
}
