package io.ktor.network.tls.cipher;

import io.ktor.network.tls.CipherSuite;
import io.ktor.network.tls.TLSRecord;
import io.ktor.network.tls.TLSVersion;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.InputPrimitivesKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/network/tls/cipher/GCMCipher;", "Lio/ktor/network/tls/cipher/TLSCipher;", "suite", "Lio/ktor/network/tls/CipherSuite;", "keyMaterial", "", "(Lio/ktor/network/tls/CipherSuite;[B)V", "inputCounter", "", "outputCounter", "decrypt", "Lio/ktor/network/tls/TLSRecord;", "record", "encrypt", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GCMCipher.kt */
public final class GCMCipher implements TLSCipher {
    private long inputCounter;
    private final byte[] keyMaterial;
    private long outputCounter;
    private final CipherSuite suite;

    public GCMCipher(CipherSuite cipherSuite, byte[] bArr) {
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        Intrinsics.checkNotNullParameter(bArr, "keyMaterial");
        this.suite = cipherSuite;
        this.keyMaterial = bArr;
    }

    public TLSRecord encrypt(TLSRecord tLSRecord) {
        Intrinsics.checkNotNullParameter(tLSRecord, "record");
        long j = this.outputCounter;
        ByteReadPacket cipherLoop = CipherUtilsKt.cipherLoop(tLSRecord.getPacket(), GCMCipherKt.gcmEncryptCipher(this.suite, this.keyMaterial, tLSRecord.getType(), (int) tLSRecord.getPacket().getRemaining(), j, j), new GCMCipher$encrypt$packet$1(this.outputCounter));
        this.outputCounter++;
        return new TLSRecord(tLSRecord.getType(), (TLSVersion) null, cipherLoop, 2, (DefaultConstructorMarker) null);
    }

    public TLSRecord decrypt(TLSRecord tLSRecord) {
        Intrinsics.checkNotNullParameter(tLSRecord, "record");
        ByteReadPacket packet = tLSRecord.getPacket();
        long remaining = packet.getRemaining();
        long readLong = InputPrimitivesKt.readLong(packet);
        long j = this.inputCounter;
        this.inputCounter = 1 + j;
        return new TLSRecord(tLSRecord.getType(), tLSRecord.getVersion(), CipherUtilsKt.cipherLoop$default(packet, GCMCipherKt.gcmDecryptCipher(this.suite, this.keyMaterial, tLSRecord.getType(), (int) remaining, readLong, j), (Function1) null, 2, (Object) null));
    }
}
