package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class FalconPrivateKey extends ASN1Object {
    private byte[] F;
    private byte[] f;
    private byte[] g;
    private FalconPublicKey publicKey;
    private int version;

    public FalconPrivateKey(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this(i, bArr, bArr2, bArr3, (FalconPublicKey) null);
    }

    public FalconPrivateKey(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, FalconPublicKey falconPublicKey) {
        this.version = i;
        this.f = bArr;
        this.g = bArr2;
        this.F = bArr3;
        this.publicKey = falconPublicKey;
    }

    private FalconPrivateKey(ASN1Sequence aSN1Sequence) {
        int intValueExact = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).intValueExact();
        this.version = intValueExact;
        if (intValueExact == 0) {
            this.f = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
            this.g = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets());
            this.F = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(3)).getOctets());
            if (aSN1Sequence.size() == 5) {
                this.publicKey = FalconPublicKey.getInstance(aSN1Sequence.getObjectAt(4));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("unrecognized version");
    }

    public static FalconPrivateKey getInstance(Object obj) {
        if (obj instanceof FalconPrivateKey) {
            return (FalconPrivateKey) obj;
        }
        if (obj != null) {
            return new FalconPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getF() {
        return Arrays.clone(this.F);
    }

    public byte[] getG() {
        return Arrays.clone(this.g);
    }

    public FalconPublicKey getPublicKey() {
        return this.publicKey;
    }

    public int getVersion() {
        return this.version;
    }

    public byte[] getf() {
        return Arrays.clone(this.f);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer((long) this.version));
        aSN1EncodableVector.add(new DEROctetString(this.f));
        aSN1EncodableVector.add(new DEROctetString(this.g));
        aSN1EncodableVector.add(new DEROctetString(this.F));
        FalconPublicKey falconPublicKey = this.publicKey;
        if (falconPublicKey != null) {
            aSN1EncodableVector.add(new FalconPublicKey(falconPublicKey.getH()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
