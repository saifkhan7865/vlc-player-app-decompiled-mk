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
import org.bouncycastle.its.ITSPublicVerificationKey;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccCurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP384CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Point256;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Point384;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;

public class BcITSPublicVerificationKey extends ITSPublicVerificationKey {
    public BcITSPublicVerificationKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        super(fromKeyParameters((ECPublicKeyParameters) asymmetricKeyParameter));
    }

    public BcITSPublicVerificationKey(PublicVerificationKey publicVerificationKey) {
        super(publicVerificationKey);
    }

    static PublicVerificationKey fromKeyParameters(ECPublicKeyParameters eCPublicKeyParameters) {
        ASN1ObjectIdentifier name = ((ECNamedDomainParameters) eCPublicKeyParameters.getParameters()).getName();
        ECPoint q = eCPublicKeyParameters.getQ();
        if (name.equals((ASN1Primitive) SECObjectIdentifiers.secp256r1)) {
            return new PublicVerificationKey(0, EccP256CurvePoint.uncompressedP256(Point256.builder().setX(q.getAffineXCoord().toBigInteger()).setY(q.getAffineYCoord().toBigInteger()).createPoint256()));
        }
        if (name.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP256r1)) {
            return new PublicVerificationKey(1, EccP256CurvePoint.uncompressedP256(Point256.builder().setX(q.getAffineXCoord().toBigInteger()).setY(q.getAffineYCoord().toBigInteger()).createPoint256()));
        }
        if (name.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP384r1)) {
            return new PublicVerificationKey(2, EccP384CurvePoint.uncompressedP384(Point384.builder().setX(q.getAffineXCoord().toBigInteger()).setY(q.getAffineYCoord().toBigInteger()).createPoint384()));
        }
        throw new IllegalArgumentException("unknown curve in public encryption key");
    }

    public AsymmetricKeyParameter getKey() {
        X9ECParameters x9ECParameters;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2;
        int choice = this.verificationKey.getChoice();
        if (choice != 0) {
            if (choice == 1) {
                aSN1ObjectIdentifier = TeleTrusTObjectIdentifiers.brainpoolP256r1;
                aSN1ObjectIdentifier2 = TeleTrusTObjectIdentifiers.brainpoolP256r1;
            } else if (choice == 2) {
                aSN1ObjectIdentifier = TeleTrusTObjectIdentifiers.brainpoolP384r1;
                aSN1ObjectIdentifier2 = TeleTrusTObjectIdentifiers.brainpoolP384r1;
            } else {
                throw new IllegalStateException("unknown key type");
            }
            x9ECParameters = TeleTrusTNamedCurves.getByOID(aSN1ObjectIdentifier2);
        } else {
            aSN1ObjectIdentifier = SECObjectIdentifiers.secp256r1;
            x9ECParameters = NISTNamedCurves.getByOID(SECObjectIdentifiers.secp256r1);
        }
        ECCurve curve = x9ECParameters.getCurve();
        if (this.verificationKey.getPublicVerificationKey() instanceof EccCurvePoint) {
            EccCurvePoint eccCurvePoint = (EccCurvePoint) this.verificationKey.getPublicVerificationKey();
            if ((eccCurvePoint instanceof EccP256CurvePoint) || (eccCurvePoint instanceof EccP384CurvePoint)) {
                return new ECPublicKeyParameters(curve.decodePoint(eccCurvePoint.getEncodedPoint()).normalize(), new ECNamedDomainParameters(aSN1ObjectIdentifier, x9ECParameters));
            }
            throw new IllegalStateException("unknown key type");
        }
        throw new IllegalStateException("extension to public verification key not supported");
    }
}
