package io.ktor.network.tls.cipher;

import io.ktor.network.tls.CipherSuite;
import io.ktor.network.tls.KeysKt;
import io.ktor.network.tls.TLSException;
import io.ktor.network.tls.TLSRecord;
import io.ktor.network.tls.TLSVersion;
import io.ktor.util.CryptoKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J\f\u0010\u001f\u001a\u00020\u001a*\u00020 H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lio/ktor/network/tls/cipher/CBCCipher;", "Lio/ktor/network/tls/cipher/TLSCipher;", "suite", "Lio/ktor/network/tls/CipherSuite;", "keyMaterial", "", "(Lio/ktor/network/tls/CipherSuite;[B)V", "inputCounter", "", "outputCounter", "receiveCipher", "Ljavax/crypto/Cipher;", "receiveKey", "Ljavax/crypto/spec/SecretKeySpec;", "receiveMac", "Ljavax/crypto/Mac;", "sendCipher", "sendKey", "sendMac", "decrypt", "Lio/ktor/network/tls/TLSRecord;", "record", "encrypt", "prepareMac", "content", "validateMac", "", "macOffset", "", "validatePadding", "paddingStart", "writePadding", "Lio/ktor/utils/io/core/BytePacketBuilder;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CBCCipher.kt */
public final class CBCCipher implements TLSCipher {
    private long inputCounter;
    private final byte[] keyMaterial;
    private long outputCounter;
    private final Cipher receiveCipher;
    private final SecretKeySpec receiveKey;
    private final Mac receiveMac;
    /* access modifiers changed from: private */
    public final Cipher sendCipher;
    private final SecretKeySpec sendKey;
    private final Mac sendMac;
    private final CipherSuite suite;

    public CBCCipher(CipherSuite cipherSuite, byte[] bArr) {
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        Intrinsics.checkNotNullParameter(bArr, "keyMaterial");
        this.suite = cipherSuite;
        this.keyMaterial = bArr;
        Cipher instance = Cipher.getInstance(cipherSuite.getJdkCipherName());
        Intrinsics.checkNotNull(instance);
        this.sendCipher = instance;
        this.sendKey = KeysKt.clientKey(bArr, cipherSuite);
        Mac instance2 = Mac.getInstance(cipherSuite.getMacName());
        Intrinsics.checkNotNull(instance2);
        this.sendMac = instance2;
        Cipher instance3 = Cipher.getInstance(cipherSuite.getJdkCipherName());
        Intrinsics.checkNotNull(instance3);
        this.receiveCipher = instance3;
        this.receiveKey = KeysKt.serverKey(bArr, cipherSuite);
        Mac instance4 = Mac.getInstance(cipherSuite.getMacName());
        Intrinsics.checkNotNull(instance4);
        this.receiveMac = instance4;
    }

    public TLSRecord encrypt(TLSRecord tLSRecord) {
        Intrinsics.checkNotNullParameter(tLSRecord, "record");
        this.sendCipher.init(1, this.sendKey, new IvParameterSpec(CryptoKt.generateNonce(this.suite.getFixedIvLength())));
        byte[] readBytes$default = StringsKt.readBytes$default(tLSRecord.getPacket(), 0, 1, (Object) null);
        byte[] prepareMac = prepareMac(tLSRecord, readBytes$default);
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            OutputKt.writeFully$default((Output) bytePacketBuilder, readBytes$default, 0, 0, 6, (Object) null);
            OutputKt.writeFully$default((Output) bytePacketBuilder, prepareMac, 0, 0, 6, (Object) null);
            writePadding(bytePacketBuilder);
            return new TLSRecord(tLSRecord.getType(), (TLSVersion) null, CipherUtilsKt.cipherLoop(bytePacketBuilder.build(), this.sendCipher, new CBCCipher$encrypt$packet$1(this)), 2, (DefaultConstructorMarker) null);
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public TLSRecord decrypt(TLSRecord tLSRecord) {
        Intrinsics.checkNotNullParameter(tLSRecord, "record");
        ByteReadPacket packet = tLSRecord.getPacket();
        this.receiveCipher.init(2, this.receiveKey, new IvParameterSpec(StringsKt.readBytes(packet, this.suite.getFixedIvLength())));
        byte[] readBytes$default = StringsKt.readBytes$default(CipherUtilsKt.cipherLoop$default(packet, this.receiveCipher, (Function1) null, 2, (Object) null), 0, 1, (Object) null);
        int length = (readBytes$default.length - (readBytes$default[readBytes$default.length - 1] & 255)) - 1;
        int macStrengthInBytes = length - this.suite.getMacStrengthInBytes();
        validatePadding(readBytes$default, length);
        validateMac(tLSRecord, readBytes$default, macStrengthInBytes);
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            OutputKt.writeFully((Output) bytePacketBuilder, readBytes$default, 0, macStrengthInBytes);
            return new TLSRecord(tLSRecord.getType(), tLSRecord.getVersion(), bytePacketBuilder.build());
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    private final byte[] prepareMac(TLSRecord tLSRecord, byte[] bArr) {
        this.sendMac.reset();
        this.sendMac.init(KeysKt.clientMacKey(this.keyMaterial, this.suite));
        byte[] bArr2 = new byte[13];
        CipherKt.set(bArr2, 0, this.outputCounter);
        bArr2[8] = (byte) tLSRecord.getType().getCode();
        bArr2[9] = 3;
        bArr2[10] = 3;
        CipherKt.set(bArr2, 11, (short) bArr.length);
        this.outputCounter++;
        this.sendMac.update(bArr2);
        byte[] doFinal = this.sendMac.doFinal(bArr);
        Intrinsics.checkNotNullExpressionValue(doFinal, "sendMac.doFinal(content)");
        return doFinal;
    }

    private final void writePadding(BytePacketBuilder bytePacketBuilder) {
        byte blockSize = (byte) (this.sendCipher.getBlockSize() - ((bytePacketBuilder.getSize() + 1) % this.sendCipher.getBlockSize()));
        int i = blockSize + 1;
        for (int i2 = 0; i2 < i; i2++) {
            bytePacketBuilder.writeByte(blockSize);
        }
    }

    private final void validatePadding(byte[] bArr, int i) {
        byte b = bArr[bArr.length - 1] & 255;
        int length = bArr.length;
        while (i < length) {
            byte b2 = bArr[i] & 255;
            if (b == b2) {
                i++;
            } else {
                throw new TLSException("Padding invalid: expected " + b + ", actual " + b2, (Throwable) null, 2, (DefaultConstructorMarker) null);
            }
        }
    }

    private final void validateMac(TLSRecord tLSRecord, byte[] bArr, int i) {
        this.receiveMac.reset();
        this.receiveMac.init(KeysKt.serverMacKey(this.keyMaterial, this.suite));
        byte[] bArr2 = new byte[13];
        CipherKt.set(bArr2, 0, this.inputCounter);
        bArr2[8] = (byte) tLSRecord.getType().getCode();
        bArr2[9] = 3;
        bArr2[10] = 3;
        CipherKt.set(bArr2, 11, (short) i);
        this.inputCounter++;
        this.receiveMac.update(bArr2);
        this.receiveMac.update(bArr, 0, i);
        byte[] doFinal = this.receiveMac.doFinal();
        Intrinsics.checkNotNull(doFinal);
        if (!MessageDigest.isEqual(doFinal, ArraysKt.sliceArray(bArr, RangesKt.until(i, this.suite.getMacStrengthInBytes() + i)))) {
            throw new TLSException("Failed to verify MAC content", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }
}
