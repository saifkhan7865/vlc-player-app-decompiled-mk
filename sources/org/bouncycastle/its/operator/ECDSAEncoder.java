package org.bouncycastle.its.operator;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP384CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EcdsaP256Signature;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EcdsaP384Signature;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Signature;
import org.bouncycastle.util.BigIntegers;

public class ECDSAEncoder {
    public static Signature toITS(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        ASN1Sequence instance = ASN1Sequence.getInstance(bArr);
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) SECObjectIdentifiers.secp256r1)) {
            return new Signature(0, new EcdsaP256Signature(new EccP256CurvePoint(0, new DEROctetString(BigIntegers.asUnsignedByteArray(32, ASN1Integer.getInstance(instance.getObjectAt(0)).getValue()))), new DEROctetString(BigIntegers.asUnsignedByteArray(32, ASN1Integer.getInstance(instance.getObjectAt(1)).getValue()))));
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP256r1)) {
            return new Signature(1, new EcdsaP256Signature(new EccP256CurvePoint(0, new DEROctetString(BigIntegers.asUnsignedByteArray(32, ASN1Integer.getInstance(instance.getObjectAt(0)).getValue()))), new DEROctetString(BigIntegers.asUnsignedByteArray(32, ASN1Integer.getInstance(instance.getObjectAt(1)).getValue()))));
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP384r1)) {
            return new Signature(2, new EcdsaP384Signature(new EccP384CurvePoint(0, new DEROctetString(BigIntegers.asUnsignedByteArray(48, ASN1Integer.getInstance(instance.getObjectAt(0)).getValue()))), new DEROctetString(BigIntegers.asUnsignedByteArray(48, ASN1Integer.getInstance(instance.getObjectAt(1)).getValue()))));
        }
        throw new IllegalArgumentException("unknown curveID");
    }

    public static byte[] toX962(Signature signature) {
        ASN1OctetString aSN1OctetString;
        byte[] bArr;
        if (signature.getChoice() == 0 || signature.getChoice() == 1) {
            EcdsaP256Signature instance = EcdsaP256Signature.getInstance(signature.getSignature());
            bArr = ASN1OctetString.getInstance(instance.getRSig().getEccp256CurvePoint()).getOctets();
            aSN1OctetString = instance.getSSig();
        } else {
            EcdsaP384Signature instance2 = EcdsaP384Signature.getInstance(signature.getSignature());
            bArr = ASN1OctetString.getInstance(instance2.getRSig().getEccP384CurvePoint()).getOctets();
            aSN1OctetString = instance2.getSSig();
        }
        try {
            return new DERSequence(new ASN1Encodable[]{new ASN1Integer(BigIntegers.fromUnsignedByteArray(bArr)), new ASN1Integer(BigIntegers.fromUnsignedByteArray(aSN1OctetString.getOctets()))}).getEncoded();
        } catch (IOException unused) {
            throw new RuntimeException("der encoding r & s");
        }
    }
}
