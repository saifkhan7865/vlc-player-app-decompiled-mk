package org.bouncycastle.its.bc;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.its.ITSPublicEncryptionKey;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.BasePublicEncryptionKey;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccCurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP384CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.SymmAlgorithm;

public class BcITSPublicEncryptionKey extends ITSPublicEncryptionKey {
    public BcITSPublicEncryptionKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        super(fromKeyParameters((ECPublicKeyParameters) asymmetricKeyParameter));
    }

    public BcITSPublicEncryptionKey(PublicEncryptionKey publicEncryptionKey) {
        super(publicEncryptionKey);
    }

    static PublicEncryptionKey fromKeyParameters(ECPublicKeyParameters eCPublicKeyParameters) {
        ASN1ObjectIdentifier name = ((ECNamedDomainParameters) eCPublicKeyParameters.getParameters()).getName();
        ECPoint q = eCPublicKeyParameters.getQ();
        if (name.equals((ASN1Primitive) SECObjectIdentifiers.secp256r1)) {
            return new PublicEncryptionKey(SymmAlgorithm.aes128Ccm, new BasePublicEncryptionKey.Builder().setChoice(0).setValue(EccP256CurvePoint.uncompressedP256(q.getAffineXCoord().toBigInteger(), q.getAffineYCoord().toBigInteger())).createBasePublicEncryptionKey());
        }
        if (name.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP256r1)) {
            return new PublicEncryptionKey(SymmAlgorithm.aes128Ccm, new BasePublicEncryptionKey.Builder().setChoice(1).setValue(EccP256CurvePoint.uncompressedP256(q.getAffineXCoord().toBigInteger(), q.getAffineYCoord().toBigInteger())).createBasePublicEncryptionKey());
        }
        throw new IllegalArgumentException("unknown curve in public encryption key");
    }

    public AsymmetricKeyParameter getKey() {
        X9ECParameters x9ECParameters;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        BasePublicEncryptionKey publicKey = this.encryptionKey.getPublicKey();
        int choice = publicKey.getChoice();
        if (choice == 0) {
            aSN1ObjectIdentifier = SECObjectIdentifiers.secp256r1;
            x9ECParameters = NISTNamedCurves.getByOID(SECObjectIdentifiers.secp256r1);
        } else if (choice == 1) {
            aSN1ObjectIdentifier = TeleTrusTObjectIdentifiers.brainpoolP256r1;
            x9ECParameters = TeleTrusTNamedCurves.getByOID(TeleTrusTObjectIdentifiers.brainpoolP256r1);
        } else {
            throw new IllegalStateException("unknown key type");
        }
        ECCurve curve = x9ECParameters.getCurve();
        if (this.encryptionKey.getPublicKey().getBasePublicEncryptionKey() instanceof EccCurvePoint) {
            EccCurvePoint eccCurvePoint = (EccCurvePoint) publicKey.getBasePublicEncryptionKey();
            if ((eccCurvePoint instanceof EccP256CurvePoint) || (eccCurvePoint instanceof EccP384CurvePoint)) {
                return new ECPublicKeyParameters(curve.decodePoint(eccCurvePoint.getEncodedPoint()).normalize(), new ECNamedDomainParameters(aSN1ObjectIdentifier, x9ECParameters));
            }
            throw new IllegalStateException("unknown key type");
        }
        throw new IllegalStateException("extension to public verification key not supported");
    }
}
