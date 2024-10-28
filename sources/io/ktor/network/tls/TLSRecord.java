package io.ktor.network.tls;

import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/network/tls/TLSRecord;", "", "type", "Lio/ktor/network/tls/TLSRecordType;", "version", "Lio/ktor/network/tls/TLSVersion;", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Lio/ktor/network/tls/TLSRecordType;Lio/ktor/network/tls/TLSVersion;Lio/ktor/utils/io/core/ByteReadPacket;)V", "getPacket", "()Lio/ktor/utils/io/core/ByteReadPacket;", "getType", "()Lio/ktor/network/tls/TLSRecordType;", "getVersion", "()Lio/ktor/network/tls/TLSVersion;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Headers.kt */
public final class TLSRecord {
    private final ByteReadPacket packet;
    private final TLSRecordType type;
    private final TLSVersion version;

    public TLSRecord() {
        this((TLSRecordType) null, (TLSVersion) null, (ByteReadPacket) null, 7, (DefaultConstructorMarker) null);
    }

    public TLSRecord(TLSRecordType tLSRecordType, TLSVersion tLSVersion, ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(tLSRecordType, "type");
        Intrinsics.checkNotNullParameter(tLSVersion, "version");
        Intrinsics.checkNotNullParameter(byteReadPacket, "packet");
        this.type = tLSRecordType;
        this.version = tLSVersion;
        this.packet = byteReadPacket;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TLSRecord(TLSRecordType tLSRecordType, TLSVersion tLSVersion, ByteReadPacket byteReadPacket, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TLSRecordType.Handshake : tLSRecordType, (i & 2) != 0 ? TLSVersion.TLS12 : tLSVersion, (i & 4) != 0 ? ByteReadPacket.Companion.getEmpty() : byteReadPacket);
    }

    public final TLSRecordType getType() {
        return this.type;
    }

    public final TLSVersion getVersion() {
        return this.version;
    }

    public final ByteReadPacket getPacket() {
        return this.packet;
    }
}
