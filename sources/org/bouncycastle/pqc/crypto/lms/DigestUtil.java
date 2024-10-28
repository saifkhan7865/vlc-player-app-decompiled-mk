package org.bouncycastle.pqc.crypto.lms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

class DigestUtil {
    private static Map<String, ASN1ObjectIdentifier> nameToOid = new HashMap();
    private static Map<ASN1ObjectIdentifier, String> oidToName = new HashMap();

    static class WrapperDigest implements Digest {
        private final Digest dig;
        private final int length;

        WrapperDigest(Digest digest, int i) {
            this.dig = digest;
            this.length = i;
        }

        public int doFinal(byte[] bArr, int i) {
            byte[] bArr2 = new byte[this.dig.getDigestSize()];
            this.dig.doFinal(bArr2, 0);
            System.arraycopy(bArr2, 0, bArr, i, this.length);
            return this.length;
        }

        public String getAlgorithmName() {
            return this.dig.getAlgorithmName() + "/" + (this.length * 8);
        }

        public int getDigestSize() {
            return this.length;
        }

        public void reset() {
            this.dig.reset();
        }

        public void update(byte b) {
            this.dig.update(b);
        }

        public void update(byte[] bArr, int i, int i2) {
            this.dig.update(bArr, i, i2);
        }
    }

    static {
        nameToOid.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        nameToOid.put("SHA-512", NISTObjectIdentifiers.id_sha512);
        nameToOid.put("SHAKE128", NISTObjectIdentifiers.id_shake128);
        nameToOid.put("SHAKE256", NISTObjectIdentifiers.id_shake256);
        oidToName.put(NISTObjectIdentifiers.id_sha256, "SHA-256");
        oidToName.put(NISTObjectIdentifiers.id_sha512, "SHA-512");
        oidToName.put(NISTObjectIdentifiers.id_shake128, "SHAKE128");
        oidToName.put(NISTObjectIdentifiers.id_shake256, "SHAKE256");
    }

    DigestUtil() {
    }

    private static Digest createDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256_len)) {
            return new SHAKEDigest(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }

    private static Digest createDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        Digest createDigest = createDigest(aSN1ObjectIdentifier);
        return aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256_len) ? new WrapperDigest(createDigest, i) : i == 24 ? new WrapperDigest(createDigest, i) : createDigest;
    }

    static Digest getDigest(LMOtsParameters lMOtsParameters) {
        return createDigest(lMOtsParameters.getDigestOID(), lMOtsParameters.getN());
    }

    static Digest getDigest(LMSigParameters lMSigParameters) {
        return createDigest(lMSigParameters.getDigestOID(), lMSigParameters.getM());
    }
}
