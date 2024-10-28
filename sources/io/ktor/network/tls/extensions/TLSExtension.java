package io.ktor.network.tls.extensions;

import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/network/tls/extensions/TLSExtension;", "", "type", "Lio/ktor/network/tls/extensions/TLSExtensionType;", "length", "", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Lio/ktor/network/tls/extensions/TLSExtensionType;ILio/ktor/utils/io/core/ByteReadPacket;)V", "getLength", "()I", "getPacket", "()Lio/ktor/utils/io/core/ByteReadPacket;", "getType", "()Lio/ktor/network/tls/extensions/TLSExtensionType;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSExtension.kt */
public final class TLSExtension {
    private final int length;
    private final ByteReadPacket packet;
    private final TLSExtensionType type;

    public TLSExtension(TLSExtensionType tLSExtensionType, int i, ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(tLSExtensionType, "type");
        Intrinsics.checkNotNullParameter(byteReadPacket, "packet");
        this.type = tLSExtensionType;
        this.length = i;
        this.packet = byteReadPacket;
    }

    public final TLSExtensionType getType() {
        return this.type;
    }

    public final int getLength() {
        return this.length;
    }

    public final ByteReadPacket getPacket() {
        return this.packet;
    }
}
