package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class OpenSSHPrivateKeyUtil {
    static final byte[] AUTH_MAGIC = Strings.toByteArray("openssh-key-v1\u0000");

    private OpenSSHPrivateKeyUtil() {
    }

    private static boolean allIntegers(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i < aSN1Sequence.size(); i++) {
            if (!(aSN1Sequence.getObjectAt(i) instanceof ASN1Integer)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("param is null");
        } else if (asymmetricKeyParameter instanceof RSAPrivateCrtKeyParameters) {
            return PrivateKeyInfoFactory.createPrivateKeyInfo(asymmetricKeyParameter).parsePrivateKey().toASN1Primitive().getEncoded();
        } else {
            if (asymmetricKeyParameter instanceof ECPrivateKeyParameters) {
                return PrivateKeyInfoFactory.createPrivateKeyInfo(asymmetricKeyParameter).parsePrivateKey().toASN1Primitive().getEncoded();
            }
            if (asymmetricKeyParameter instanceof DSAPrivateKeyParameters) {
                DSAPrivateKeyParameters dSAPrivateKeyParameters = (DSAPrivateKeyParameters) asymmetricKeyParameter;
                DSAParameters parameters = dSAPrivateKeyParameters.getParameters();
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                aSN1EncodableVector.add(new ASN1Integer(0));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getP()));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getQ()));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getG()));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getG().modPow(dSAPrivateKeyParameters.getX(), parameters.getP())));
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getX()));
                try {
                    return new DERSequence(aSN1EncodableVector).getEncoded();
                } catch (Exception e) {
                    throw new IllegalStateException("unable to encode DSAPrivateKeyParameters " + e.getMessage());
                }
            } else if (asymmetricKeyParameter instanceof Ed25519PrivateKeyParameters) {
                Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = (Ed25519PrivateKeyParameters) asymmetricKeyParameter;
                Ed25519PublicKeyParameters generatePublicKey = ed25519PrivateKeyParameters.generatePublicKey();
                SSHBuilder sSHBuilder = new SSHBuilder();
                sSHBuilder.writeBytes(AUTH_MAGIC);
                sSHBuilder.writeString("none");
                sSHBuilder.writeString("none");
                sSHBuilder.writeString("");
                sSHBuilder.u32(1);
                sSHBuilder.writeBlock(OpenSSHPublicKeyUtil.encodePublicKey(generatePublicKey));
                SSHBuilder sSHBuilder2 = new SSHBuilder();
                int nextInt = CryptoServicesRegistrar.getSecureRandom().nextInt();
                sSHBuilder2.u32(nextInt);
                sSHBuilder2.u32(nextInt);
                sSHBuilder2.writeString("ssh-ed25519");
                byte[] encoded = generatePublicKey.getEncoded();
                sSHBuilder2.writeBlock(encoded);
                sSHBuilder2.writeBlock(Arrays.concatenate(ed25519PrivateKeyParameters.getEncoded(), encoded));
                sSHBuilder2.writeString("");
                sSHBuilder.writeBlock(sSHBuilder2.getPaddedBytes());
                return sSHBuilder.getBytes();
            } else {
                throw new IllegalArgumentException("unable to convert " + asymmetricKeyParameter.getClass().getName() + " to openssh private key");
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [org.bouncycastle.crypto.params.ECPrivateKeyParameters] */
    /* JADX WARNING: type inference failed for: r4v5, types: [org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters] */
    /* JADX WARNING: type inference failed for: r4v6, types: [org.bouncycastle.crypto.params.ECPrivateKeyParameters] */
    /* JADX WARNING: type inference failed for: r4v9, types: [org.bouncycastle.crypto.params.DSAPrivateKeyParameters] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.bouncycastle.crypto.params.AsymmetricKeyParameter parsePrivateKeyBlob(byte[] r14) {
        /*
            r0 = 0
            byte r1 = r14[r0]
            r2 = 48
            r3 = 1
            r4 = 0
            if (r1 != r2) goto L_0x00e7
            org.bouncycastle.asn1.ASN1Sequence r14 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r14)
            int r1 = r14.size()
            r2 = 6
            r5 = 2
            r6 = 3
            if (r1 != r2) goto L_0x0063
            boolean r1 = allIntegers(r14)
            if (r1 == 0) goto L_0x020a
            org.bouncycastle.asn1.ASN1Encodable r0 = r14.getObjectAt(r0)
            org.bouncycastle.asn1.ASN1Integer r0 = (org.bouncycastle.asn1.ASN1Integer) r0
            java.math.BigInteger r0 = r0.getPositiveValue()
            java.math.BigInteger r1 = org.bouncycastle.util.BigIntegers.ZERO
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x020a
            org.bouncycastle.crypto.params.DSAPrivateKeyParameters r4 = new org.bouncycastle.crypto.params.DSAPrivateKeyParameters
            r0 = 5
            org.bouncycastle.asn1.ASN1Encodable r0 = r14.getObjectAt(r0)
            org.bouncycastle.asn1.ASN1Integer r0 = (org.bouncycastle.asn1.ASN1Integer) r0
            java.math.BigInteger r0 = r0.getPositiveValue()
            org.bouncycastle.crypto.params.DSAParameters r1 = new org.bouncycastle.crypto.params.DSAParameters
            org.bouncycastle.asn1.ASN1Encodable r2 = r14.getObjectAt(r3)
            org.bouncycastle.asn1.ASN1Integer r2 = (org.bouncycastle.asn1.ASN1Integer) r2
            java.math.BigInteger r2 = r2.getPositiveValue()
            org.bouncycastle.asn1.ASN1Encodable r3 = r14.getObjectAt(r5)
            org.bouncycastle.asn1.ASN1Integer r3 = (org.bouncycastle.asn1.ASN1Integer) r3
            java.math.BigInteger r3 = r3.getPositiveValue()
            org.bouncycastle.asn1.ASN1Encodable r14 = r14.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1Integer r14 = (org.bouncycastle.asn1.ASN1Integer) r14
            java.math.BigInteger r14 = r14.getPositiveValue()
            r1.<init>(r2, r3, r14)
            r4.<init>(r0, r1)
            goto L_0x020a
        L_0x0063:
            int r1 = r14.size()
            r2 = 9
            if (r1 != r2) goto L_0x00b0
            boolean r1 = allIntegers(r14)
            if (r1 == 0) goto L_0x020a
            org.bouncycastle.asn1.ASN1Encodable r0 = r14.getObjectAt(r0)
            org.bouncycastle.asn1.ASN1Integer r0 = (org.bouncycastle.asn1.ASN1Integer) r0
            java.math.BigInteger r0 = r0.getPositiveValue()
            java.math.BigInteger r1 = org.bouncycastle.util.BigIntegers.ZERO
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x020a
            org.bouncycastle.asn1.pkcs.RSAPrivateKey r14 = org.bouncycastle.asn1.pkcs.RSAPrivateKey.getInstance(r14)
            org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters r9 = new org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters
            java.math.BigInteger r1 = r14.getModulus()
            java.math.BigInteger r2 = r14.getPublicExponent()
            java.math.BigInteger r3 = r14.getPrivateExponent()
            java.math.BigInteger r4 = r14.getPrime1()
            java.math.BigInteger r5 = r14.getPrime2()
            java.math.BigInteger r6 = r14.getExponent1()
            java.math.BigInteger r7 = r14.getExponent2()
            java.math.BigInteger r8 = r14.getCoefficient()
            r0 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r4 = r9
            goto L_0x020a
        L_0x00b0:
            int r0 = r14.size()
            r1 = 4
            if (r0 != r1) goto L_0x020a
            org.bouncycastle.asn1.ASN1Encodable r0 = r14.getObjectAt(r6)
            boolean r0 = r0 instanceof org.bouncycastle.asn1.ASN1TaggedObject
            if (r0 == 0) goto L_0x020a
            org.bouncycastle.asn1.ASN1Encodable r0 = r14.getObjectAt(r5)
            boolean r0 = r0 instanceof org.bouncycastle.asn1.ASN1TaggedObject
            if (r0 == 0) goto L_0x020a
            org.bouncycastle.asn1.sec.ECPrivateKey r14 = org.bouncycastle.asn1.sec.ECPrivateKey.getInstance(r14)
            org.bouncycastle.asn1.ASN1Object r0 = r14.getParametersObject()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.ASN1ObjectIdentifier.getInstance(r0)
            org.bouncycastle.asn1.x9.X9ECParameters r1 = org.bouncycastle.asn1.x9.ECNamedCurveTable.getByOID(r0)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r4 = new org.bouncycastle.crypto.params.ECPrivateKeyParameters
            java.math.BigInteger r14 = r14.getKey()
            org.bouncycastle.crypto.params.ECNamedDomainParameters r2 = new org.bouncycastle.crypto.params.ECNamedDomainParameters
            r2.<init>((org.bouncycastle.asn1.ASN1ObjectIdentifier) r0, (org.bouncycastle.asn1.x9.X9ECParameters) r1)
            r4.<init>(r14, r2)
            goto L_0x020a
        L_0x00e7:
            org.bouncycastle.crypto.util.SSHBuffer r1 = new org.bouncycastle.crypto.util.SSHBuffer
            byte[] r2 = AUTH_MAGIC
            r1.<init>(r2, r14)
            java.lang.String r14 = r1.readString()
            java.lang.String r2 = "none"
            boolean r14 = r2.equals(r14)
            if (r14 == 0) goto L_0x0235
            r1.skipBlock()
            r1.skipBlock()
            int r14 = r1.readU32()
            if (r14 != r3) goto L_0x022d
            byte[] r14 = r1.readBlock()
            org.bouncycastle.crypto.util.OpenSSHPublicKeyUtil.parsePublicKey((byte[]) r14)
            byte[] r14 = r1.readPaddedBlock()
            boolean r1 = r1.hasRemaining()
            if (r1 != 0) goto L_0x0225
            org.bouncycastle.crypto.util.SSHBuffer r1 = new org.bouncycastle.crypto.util.SSHBuffer
            r1.<init>(r14)
            int r14 = r1.readU32()
            int r2 = r1.readU32()
            if (r14 != r2) goto L_0x021d
            java.lang.String r14 = r1.readString()
            java.lang.String r2 = "ssh-ed25519"
            boolean r2 = r2.equals(r14)
            if (r2 == 0) goto L_0x014d
            r1.readBlock()
            byte[] r14 = r1.readBlock()
            int r2 = r14.length
            r3 = 64
            if (r2 != r3) goto L_0x0145
            org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters r4 = new org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters
            r4.<init>(r14, r0)
            goto L_0x0201
        L_0x0145:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "private key value of wrong length"
            r14.<init>(r0)
            throw r14
        L_0x014d:
            java.lang.String r0 = "ecdsa"
            boolean r0 = r14.startsWith(r0)
            if (r0 == 0) goto L_0x01a9
            byte[] r0 = r1.readBlock()
            java.lang.String r0 = org.bouncycastle.util.Strings.fromByteArray(r0)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.crypto.util.SSHNamedCurves.getByName(r0)
            if (r0 == 0) goto L_0x0195
            org.bouncycastle.asn1.x9.X9ECParameters r14 = org.bouncycastle.asn1.nist.NISTNamedCurves.getByOID(r0)
            if (r14 == 0) goto L_0x0181
            r1.readBlock()
            byte[] r2 = r1.readBlock()
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r4 = new org.bouncycastle.crypto.params.ECPrivateKeyParameters
            java.math.BigInteger r5 = new java.math.BigInteger
            r5.<init>(r3, r2)
            org.bouncycastle.crypto.params.ECNamedDomainParameters r2 = new org.bouncycastle.crypto.params.ECNamedDomainParameters
            r2.<init>((org.bouncycastle.asn1.ASN1ObjectIdentifier) r0, (org.bouncycastle.asn1.x9.X9ECParameters) r14)
            r4.<init>(r5, r2)
            goto L_0x0201
        L_0x0181:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Curve not found for: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r14.<init>(r0)
            throw r14
        L_0x0195:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "OID not found for: "
            r1.<init>(r2)
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            r0.<init>(r14)
            throw r0
        L_0x01a9:
            java.lang.String r0 = "ssh-rsa"
            boolean r14 = r14.startsWith(r0)
            if (r14 == 0) goto L_0x0201
            java.math.BigInteger r6 = new java.math.BigInteger
            byte[] r14 = r1.readBlock()
            r6.<init>(r3, r14)
            java.math.BigInteger r7 = new java.math.BigInteger
            byte[] r14 = r1.readBlock()
            r7.<init>(r3, r14)
            java.math.BigInteger r8 = new java.math.BigInteger
            byte[] r14 = r1.readBlock()
            r8.<init>(r3, r14)
            java.math.BigInteger r13 = new java.math.BigInteger
            byte[] r14 = r1.readBlock()
            r13.<init>(r3, r14)
            java.math.BigInteger r9 = new java.math.BigInteger
            byte[] r14 = r1.readBlock()
            r9.<init>(r3, r14)
            java.math.BigInteger r10 = new java.math.BigInteger
            byte[] r14 = r1.readBlock()
            r10.<init>(r3, r14)
            java.math.BigInteger r14 = org.bouncycastle.util.BigIntegers.ONE
            java.math.BigInteger r14 = r9.subtract(r14)
            java.math.BigInteger r0 = org.bouncycastle.util.BigIntegers.ONE
            java.math.BigInteger r0 = r10.subtract(r0)
            java.math.BigInteger r11 = r8.remainder(r14)
            java.math.BigInteger r12 = r8.remainder(r0)
            org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters r4 = new org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters
            r5 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)
        L_0x0201:
            r1.skipBlock()
            boolean r14 = r1.hasRemaining()
            if (r14 != 0) goto L_0x0215
        L_0x020a:
            if (r4 == 0) goto L_0x020d
            return r4
        L_0x020d:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "unable to parse key"
            r14.<init>(r0)
            throw r14
        L_0x0215:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "private key block has trailing data"
            r14.<init>(r0)
            throw r14
        L_0x021d:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "private key check values are not the same"
            r14.<init>(r0)
            throw r14
        L_0x0225:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "decoded key has trailing data"
            r14.<init>(r0)
            throw r14
        L_0x022d:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "multiple keys not supported"
            r14.<init>(r0)
            throw r14
        L_0x0235:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "encrypted keys not supported"
            r14.<init>(r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.util.OpenSSHPrivateKeyUtil.parsePrivateKeyBlob(byte[]):org.bouncycastle.crypto.params.AsymmetricKeyParameter");
    }
}
