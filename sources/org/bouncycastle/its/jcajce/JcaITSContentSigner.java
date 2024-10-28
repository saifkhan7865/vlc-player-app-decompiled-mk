package org.bouncycastle.its.jcajce;

import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.its.ITSCertificate;
import org.bouncycastle.its.operator.ITSContentSigner;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Arrays;

public class JcaITSContentSigner implements ITSContentSigner {
    private final ASN1ObjectIdentifier curveID;
    private final DigestCalculator digest;
    private final AlgorithmIdentifier digestAlgo;
    private final JcaJceHelper helper;
    private final byte[] parentData;
    private final byte[] parentDigest;
    private final ECPrivateKey privateKey;
    private final String signer;
    private final ITSCertificate signerCert;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();

        public JcaITSContentSigner build(PrivateKey privateKey) {
            return new JcaITSContentSigner((ECPrivateKey) privateKey, (ITSCertificate) null, this.helper);
        }

        public JcaITSContentSigner build(PrivateKey privateKey, ITSCertificate iTSCertificate) {
            return new JcaITSContentSigner((ECPrivateKey) privateKey, iTSCertificate, this.helper);
        }

        public Builder setProvider(String str) {
            this.helper = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder setProvider(Provider provider) {
            this.helper = new ProviderJcaJceHelper(provider);
            return this;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0071 A[SYNTHETIC, Splitter:B:15:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private JcaITSContentSigner(java.security.interfaces.ECPrivateKey r3, org.bouncycastle.its.ITSCertificate r4, org.bouncycastle.jcajce.util.JcaJceHelper r5) {
        /*
            r2 = this;
            r2.<init>()
            r2.privateKey = r3
            r2.signerCert = r4
            r2.helper = r5
            byte[] r3 = r3.getEncoded()
            org.bouncycastle.asn1.pkcs.PrivateKeyInfo r3 = org.bouncycastle.asn1.pkcs.PrivateKeyInfo.getInstance(r3)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r3.getPrivateKeyAlgorithm()
            org.bouncycastle.asn1.ASN1Encodable r3 = r3.getParameters()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.ASN1ObjectIdentifier.getInstance(r3)
            r2.curveID = r3
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.sec.SECObjectIdentifiers.secp256r1
            boolean r0 = r3.equals((org.bouncycastle.asn1.ASN1Primitive) r0)
            java.lang.String r1 = "SHA256withECDSA"
            if (r0 == 0) goto L_0x0035
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.nist.NISTObjectIdentifiers.id_sha256
            r3.<init>((org.bouncycastle.asn1.ASN1ObjectIdentifier) r0)
        L_0x0030:
            r2.digestAlgo = r3
            r2.signer = r1
            goto L_0x005a
        L_0x0035:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers.brainpoolP256r1
            boolean r0 = r3.equals((org.bouncycastle.asn1.ASN1Primitive) r0)
            if (r0 == 0) goto L_0x0045
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.nist.NISTObjectIdentifiers.id_sha256
            r3.<init>((org.bouncycastle.asn1.ASN1ObjectIdentifier) r0)
            goto L_0x0030
        L_0x0045:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers.brainpoolP384r1
            boolean r3 = r3.equals((org.bouncycastle.asn1.ASN1Primitive) r0)
            if (r3 == 0) goto L_0x00d3
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.nist.NISTObjectIdentifiers.id_sha384
            r3.<init>((org.bouncycastle.asn1.ASN1ObjectIdentifier) r0)
            r2.digestAlgo = r3
            java.lang.String r3 = "SHA384withECDSA"
            r2.signer = r3
        L_0x005a:
            org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder r3 = new org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder     // Catch:{ Exception -> 0x00c8 }
            r3.<init>()     // Catch:{ Exception -> 0x00c8 }
            org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder r3 = r3.setHelper(r5)     // Catch:{ Exception -> 0x00c8 }
            org.bouncycastle.operator.DigestCalculatorProvider r3 = r3.build()     // Catch:{ Exception -> 0x00c8 }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r5 = r2.digestAlgo     // Catch:{ OperatorCreationException -> 0x00ad }
            org.bouncycastle.operator.DigestCalculator r3 = r3.get(r5)     // Catch:{ OperatorCreationException -> 0x00ad }
            r2.digest = r3     // Catch:{ OperatorCreationException -> 0x00ad }
            if (r4 == 0) goto L_0x00a3
            byte[] r4 = r4.getEncoded()     // Catch:{ IOException -> 0x008a }
            r2.parentData = r4     // Catch:{ IOException -> 0x008a }
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ IOException -> 0x008a }
            int r0 = r4.length     // Catch:{ IOException -> 0x008a }
            r1 = 0
            r5.write(r4, r1, r0)     // Catch:{ IOException -> 0x008a }
            r5.close()     // Catch:{ IOException -> 0x008a }
            byte[] r3 = r3.getDigest()     // Catch:{ IOException -> 0x008a }
            r2.parentDigest = r3     // Catch:{ IOException -> 0x008a }
            goto L_0x00ac
        L_0x008a:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "signer certificate encoding failed: "
            r5.<init>(r0)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.<init>(r3)
            throw r4
        L_0x00a3:
            r4 = 0
            r2.parentData = r4
            byte[] r3 = r3.getDigest()
            r2.parentDigest = r3
        L_0x00ac:
            return
        L_0x00ad:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "cannot recognise digest type: "
            r5.<init>(r0)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r0 = r2.digestAlgo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r0.getAlgorithm()
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5, r3)
            throw r4
        L_0x00c8:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = r3.getMessage()
            r4.<init>(r5, r3)
            throw r4
        L_0x00d3:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "unknown key type"
            r3.<init>(r4)
            goto L_0x00dc
        L_0x00db:
            throw r3
        L_0x00dc:
            goto L_0x00db
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.its.jcajce.JcaITSContentSigner.<init>(java.security.interfaces.ECPrivateKey, org.bouncycastle.its.ITSCertificate, org.bouncycastle.jcajce.util.JcaJceHelper):void");
    }

    public ITSCertificate getAssociatedCertificate() {
        return this.signerCert;
    }

    public byte[] getAssociatedCertificateDigest() {
        return Arrays.clone(this.parentDigest);
    }

    public ASN1ObjectIdentifier getCurveID() {
        return this.curveID;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgo;
    }

    public OutputStream getOutputStream() {
        return this.digest.getOutputStream();
    }

    public byte[] getSignature() {
        byte[] digest2 = this.digest.getDigest();
        try {
            Signature createSignature = this.helper.createSignature(this.signer);
            createSignature.initSign(this.privateKey);
            createSignature.update(digest2, 0, digest2.length);
            byte[] bArr = this.parentDigest;
            createSignature.update(bArr, 0, bArr.length);
            return createSignature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public boolean isForSelfSigning() {
        return this.parentData == null;
    }
}
