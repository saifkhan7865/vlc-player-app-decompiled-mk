package io.ktor.network.tls;

import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/network/tls/TLSHandshake;", "", "()V", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "getPacket", "()Lio/ktor/utils/io/core/ByteReadPacket;", "setPacket", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "type", "Lio/ktor/network/tls/TLSHandshakeType;", "getType", "()Lio/ktor/network/tls/TLSHandshakeType;", "setType", "(Lio/ktor/network/tls/TLSHandshakeType;)V", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Headers.kt */
public final class TLSHandshake {
    private ByteReadPacket packet = ByteReadPacket.Companion.getEmpty();
    private TLSHandshakeType type = TLSHandshakeType.HelloRequest;

    public final TLSHandshakeType getType() {
        return this.type;
    }

    public final void setType(TLSHandshakeType tLSHandshakeType) {
        Intrinsics.checkNotNullParameter(tLSHandshakeType, "<set-?>");
        this.type = tLSHandshakeType;
    }

    public final ByteReadPacket getPacket() {
        return this.packet;
    }

    public final void setPacket(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<set-?>");
        this.packet = byteReadPacket;
    }
}
