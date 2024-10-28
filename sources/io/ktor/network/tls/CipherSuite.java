package io.ktor.network.tls;

import io.ktor.http.ContentDisposition;
import io.ktor.network.tls.extensions.HashAlgorithm;
import io.ktor.network.tls.extensions.SignatureAlgorithm;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u000bHÆ\u0003J\t\u00104\u001a\u00020\u0012HÆ\u0003J\t\u00105\u001a\u00020\u0014HÆ\u0003J\t\u00106\u001a\u00020\u0016HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\bHÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u000bHÆ\u0003J\t\u0010<\u001a\u00020\u000bHÆ\u0003J\t\u0010=\u001a\u00020\u000bHÆ\u0003J\t\u0010>\u001a\u00020\u000bHÆ\u0003J\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016HÆ\u0001J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020\u000bHÖ\u0001J\t\u0010D\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010'\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0011\u0010\u0010\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u0011\u0010+\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010%R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006E"}, d2 = {"Lio/ktor/network/tls/CipherSuite;", "", "code", "", "name", "", "openSSLName", "exchangeType", "Lio/ktor/network/tls/SecretExchangeType;", "jdkCipherName", "keyStrength", "", "fixedIvLength", "ivLength", "cipherTagSizeInBytes", "macName", "macStrength", "hash", "Lio/ktor/network/tls/extensions/HashAlgorithm;", "signatureAlgorithm", "Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "cipherType", "Lio/ktor/network/tls/CipherType;", "(SLjava/lang/String;Ljava/lang/String;Lio/ktor/network/tls/SecretExchangeType;Ljava/lang/String;IIIILjava/lang/String;ILio/ktor/network/tls/extensions/HashAlgorithm;Lio/ktor/network/tls/extensions/SignatureAlgorithm;Lio/ktor/network/tls/CipherType;)V", "getCipherTagSizeInBytes", "()I", "getCipherType", "()Lio/ktor/network/tls/CipherType;", "getCode", "()S", "getExchangeType", "()Lio/ktor/network/tls/SecretExchangeType;", "getFixedIvLength", "getHash", "()Lio/ktor/network/tls/extensions/HashAlgorithm;", "getIvLength", "getJdkCipherName", "()Ljava/lang/String;", "getKeyStrength", "keyStrengthInBytes", "getKeyStrengthInBytes", "getMacName", "getMacStrength", "macStrengthInBytes", "getMacStrengthInBytes", "getName", "getOpenSSLName", "getSignatureAlgorithm", "()Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CipherSuites.kt */
public final class CipherSuite {
    private final int cipherTagSizeInBytes;
    private final CipherType cipherType;
    private final short code;
    private final SecretExchangeType exchangeType;
    private final int fixedIvLength;
    private final HashAlgorithm hash;
    private final int ivLength;
    private final String jdkCipherName;
    private final int keyStrength;
    private final int keyStrengthInBytes;
    private final String macName;
    private final int macStrength;
    private final int macStrengthInBytes;
    private final String name;
    private final String openSSLName;
    private final SignatureAlgorithm signatureAlgorithm;

    public static /* synthetic */ CipherSuite copy$default(CipherSuite cipherSuite, short s, String str, String str2, SecretExchangeType secretExchangeType, String str3, int i, int i2, int i3, int i4, String str4, int i5, HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm2, CipherType cipherType2, int i6, Object obj) {
        CipherSuite cipherSuite2 = cipherSuite;
        int i7 = i6;
        return cipherSuite.copy((i7 & 1) != 0 ? cipherSuite2.code : s, (i7 & 2) != 0 ? cipherSuite2.name : str, (i7 & 4) != 0 ? cipherSuite2.openSSLName : str2, (i7 & 8) != 0 ? cipherSuite2.exchangeType : secretExchangeType, (i7 & 16) != 0 ? cipherSuite2.jdkCipherName : str3, (i7 & 32) != 0 ? cipherSuite2.keyStrength : i, (i7 & 64) != 0 ? cipherSuite2.fixedIvLength : i2, (i7 & 128) != 0 ? cipherSuite2.ivLength : i3, (i7 & 256) != 0 ? cipherSuite2.cipherTagSizeInBytes : i4, (i7 & 512) != 0 ? cipherSuite2.macName : str4, (i7 & 1024) != 0 ? cipherSuite2.macStrength : i5, (i7 & 2048) != 0 ? cipherSuite2.hash : hashAlgorithm, (i7 & 4096) != 0 ? cipherSuite2.signatureAlgorithm : signatureAlgorithm2, (i7 & 8192) != 0 ? cipherSuite2.cipherType : cipherType2);
    }

    public final short component1() {
        return this.code;
    }

    public final String component10() {
        return this.macName;
    }

    public final int component11() {
        return this.macStrength;
    }

    public final HashAlgorithm component12() {
        return this.hash;
    }

    public final SignatureAlgorithm component13() {
        return this.signatureAlgorithm;
    }

    public final CipherType component14() {
        return this.cipherType;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.openSSLName;
    }

    public final SecretExchangeType component4() {
        return this.exchangeType;
    }

    public final String component5() {
        return this.jdkCipherName;
    }

    public final int component6() {
        return this.keyStrength;
    }

    public final int component7() {
        return this.fixedIvLength;
    }

    public final int component8() {
        return this.ivLength;
    }

    public final int component9() {
        return this.cipherTagSizeInBytes;
    }

    public final CipherSuite copy(short s, String str, String str2, SecretExchangeType secretExchangeType, String str3, int i, int i2, int i3, int i4, String str4, int i5, HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm2, CipherType cipherType2) {
        String str5 = str;
        Intrinsics.checkNotNullParameter(str5, ContentDisposition.Parameters.Name);
        String str6 = str2;
        Intrinsics.checkNotNullParameter(str6, "openSSLName");
        SecretExchangeType secretExchangeType2 = secretExchangeType;
        Intrinsics.checkNotNullParameter(secretExchangeType2, "exchangeType");
        String str7 = str3;
        Intrinsics.checkNotNullParameter(str7, "jdkCipherName");
        String str8 = str4;
        Intrinsics.checkNotNullParameter(str8, "macName");
        HashAlgorithm hashAlgorithm2 = hashAlgorithm;
        Intrinsics.checkNotNullParameter(hashAlgorithm2, "hash");
        SignatureAlgorithm signatureAlgorithm3 = signatureAlgorithm2;
        Intrinsics.checkNotNullParameter(signatureAlgorithm3, "signatureAlgorithm");
        CipherType cipherType3 = cipherType2;
        Intrinsics.checkNotNullParameter(cipherType3, "cipherType");
        return new CipherSuite(s, str5, str6, secretExchangeType2, str7, i, i2, i3, i4, str8, i5, hashAlgorithm2, signatureAlgorithm3, cipherType3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CipherSuite)) {
            return false;
        }
        CipherSuite cipherSuite = (CipherSuite) obj;
        return this.code == cipherSuite.code && Intrinsics.areEqual((Object) this.name, (Object) cipherSuite.name) && Intrinsics.areEqual((Object) this.openSSLName, (Object) cipherSuite.openSSLName) && this.exchangeType == cipherSuite.exchangeType && Intrinsics.areEqual((Object) this.jdkCipherName, (Object) cipherSuite.jdkCipherName) && this.keyStrength == cipherSuite.keyStrength && this.fixedIvLength == cipherSuite.fixedIvLength && this.ivLength == cipherSuite.ivLength && this.cipherTagSizeInBytes == cipherSuite.cipherTagSizeInBytes && Intrinsics.areEqual((Object) this.macName, (Object) cipherSuite.macName) && this.macStrength == cipherSuite.macStrength && this.hash == cipherSuite.hash && this.signatureAlgorithm == cipherSuite.signatureAlgorithm && this.cipherType == cipherSuite.cipherType;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.code * 31) + this.name.hashCode()) * 31) + this.openSSLName.hashCode()) * 31) + this.exchangeType.hashCode()) * 31) + this.jdkCipherName.hashCode()) * 31) + this.keyStrength) * 31) + this.fixedIvLength) * 31) + this.ivLength) * 31) + this.cipherTagSizeInBytes) * 31) + this.macName.hashCode()) * 31) + this.macStrength) * 31) + this.hash.hashCode()) * 31) + this.signatureAlgorithm.hashCode()) * 31) + this.cipherType.hashCode();
    }

    public String toString() {
        return "CipherSuite(code=" + this.code + ", name=" + this.name + ", openSSLName=" + this.openSSLName + ", exchangeType=" + this.exchangeType + ", jdkCipherName=" + this.jdkCipherName + ", keyStrength=" + this.keyStrength + ", fixedIvLength=" + this.fixedIvLength + ", ivLength=" + this.ivLength + ", cipherTagSizeInBytes=" + this.cipherTagSizeInBytes + ", macName=" + this.macName + ", macStrength=" + this.macStrength + ", hash=" + this.hash + ", signatureAlgorithm=" + this.signatureAlgorithm + ", cipherType=" + this.cipherType + ')';
    }

    public CipherSuite(short s, String str, String str2, SecretExchangeType secretExchangeType, String str3, int i, int i2, int i3, int i4, String str4, int i5, HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm2, CipherType cipherType2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "openSSLName");
        Intrinsics.checkNotNullParameter(secretExchangeType, "exchangeType");
        Intrinsics.checkNotNullParameter(str3, "jdkCipherName");
        Intrinsics.checkNotNullParameter(str4, "macName");
        Intrinsics.checkNotNullParameter(hashAlgorithm, "hash");
        Intrinsics.checkNotNullParameter(signatureAlgorithm2, "signatureAlgorithm");
        Intrinsics.checkNotNullParameter(cipherType2, "cipherType");
        this.code = s;
        this.name = str;
        this.openSSLName = str2;
        this.exchangeType = secretExchangeType;
        this.jdkCipherName = str3;
        this.keyStrength = i;
        this.fixedIvLength = i2;
        this.ivLength = i3;
        this.cipherTagSizeInBytes = i4;
        this.macName = str4;
        this.macStrength = i5;
        this.hash = hashAlgorithm;
        this.signatureAlgorithm = signatureAlgorithm2;
        this.cipherType = cipherType2;
        this.keyStrengthInBytes = i / 8;
        this.macStrengthInBytes = i5 / 8;
    }

    public final short getCode() {
        return this.code;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOpenSSLName() {
        return this.openSSLName;
    }

    public final SecretExchangeType getExchangeType() {
        return this.exchangeType;
    }

    public final String getJdkCipherName() {
        return this.jdkCipherName;
    }

    public final int getKeyStrength() {
        return this.keyStrength;
    }

    public final int getFixedIvLength() {
        return this.fixedIvLength;
    }

    public final int getIvLength() {
        return this.ivLength;
    }

    public final int getCipherTagSizeInBytes() {
        return this.cipherTagSizeInBytes;
    }

    public final String getMacName() {
        return this.macName;
    }

    public final int getMacStrength() {
        return this.macStrength;
    }

    public final HashAlgorithm getHash() {
        return this.hash;
    }

    public final SignatureAlgorithm getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CipherSuite(short r17, java.lang.String r18, java.lang.String r19, io.ktor.network.tls.SecretExchangeType r20, java.lang.String r21, int r22, int r23, int r24, int r25, java.lang.String r26, int r27, io.ktor.network.tls.extensions.HashAlgorithm r28, io.ktor.network.tls.extensions.SignatureAlgorithm r29, io.ktor.network.tls.CipherType r30, int r31, kotlin.jvm.internal.DefaultConstructorMarker r32) {
        /*
            r16 = this;
            r0 = r31
            r0 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r0 == 0) goto L_0x000a
            io.ktor.network.tls.CipherType r0 = io.ktor.network.tls.CipherType.GCM
            r15 = r0
            goto L_0x000c
        L_0x000a:
            r15 = r30
        L_0x000c:
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r12 = r27
            r13 = r28
            r14 = r29
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.CipherSuite.<init>(short, java.lang.String, java.lang.String, io.ktor.network.tls.SecretExchangeType, java.lang.String, int, int, int, int, java.lang.String, int, io.ktor.network.tls.extensions.HashAlgorithm, io.ktor.network.tls.extensions.SignatureAlgorithm, io.ktor.network.tls.CipherType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final CipherType getCipherType() {
        return this.cipherType;
    }

    public final int getKeyStrengthInBytes() {
        return this.keyStrengthInBytes;
    }

    public final int getMacStrengthInBytes() {
        return this.macStrengthInBytes;
    }
}
