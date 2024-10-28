package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;

public class CMSSignedDataGenerator extends CMSSignedGenerator {
    private boolean isDefiniteLength = false;
    private List signerInfs = new ArrayList();

    public CMSSignedDataGenerator() {
    }

    public CMSSignedDataGenerator(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        super(digestAlgorithmIdentifierFinder);
    }

    public CMSSignedData generate(CMSTypedData cMSTypedData) throws CMSException {
        return generate(cMSTypedData, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.cms.CMSSignedData generate(org.bouncycastle.cms.CMSTypedData r12, boolean r13) throws org.bouncycastle.cms.CMSException {
        /*
            r11 = this;
            java.util.List r0 = r11.signerInfs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x011d
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            org.bouncycastle.asn1.ASN1EncodableVector r1 = new org.bouncycastle.asn1.ASN1EncodableVector
            r1.<init>()
            java.util.Map r2 = r11.digests
            r2.clear()
            java.util.List r2 = r11._signers
            java.util.Iterator r2 = r2.iterator()
        L_0x001d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0036
            java.lang.Object r3 = r2.next()
            org.bouncycastle.cms.SignerInformation r3 = (org.bouncycastle.cms.SignerInformation) r3
            org.bouncycastle.operator.DigestAlgorithmIdentifierFinder r4 = r11.digestAlgIdFinder
            org.bouncycastle.cms.CMSUtils.addDigestAlgs(r0, r3, r4)
            org.bouncycastle.asn1.cms.SignerInfo r3 = r3.toASN1Structure()
            r1.add(r3)
            goto L_0x001d
        L_0x0036:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r12.getContentType()
            java.lang.Object r3 = r12.getContent()
            r4 = 0
            if (r3 == 0) goto L_0x008d
            if (r13 == 0) goto L_0x0049
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>()
            goto L_0x004a
        L_0x0049:
            r3 = r4
        L_0x004a:
            java.util.List r5 = r11.signerGens
            java.io.OutputStream r5 = org.bouncycastle.cms.CMSUtils.attachSignersToOutputStream(r5, r3)
            java.io.OutputStream r5 = org.bouncycastle.cms.CMSUtils.getSafeOutputStream(r5)
            r12.write(r5)     // Catch:{ IOException -> 0x0074 }
            r5.close()     // Catch:{ IOException -> 0x0074 }
            if (r13 == 0) goto L_0x008d
            boolean r13 = r11.isDefiniteLength
            if (r13 == 0) goto L_0x006a
            org.bouncycastle.asn1.DEROctetString r13 = new org.bouncycastle.asn1.DEROctetString
            byte[] r3 = r3.toByteArray()
            r13.<init>((byte[]) r3)
            goto L_0x008e
        L_0x006a:
            org.bouncycastle.asn1.BEROctetString r13 = new org.bouncycastle.asn1.BEROctetString
            byte[] r3 = r3.toByteArray()
            r13.<init>((byte[]) r3)
            goto L_0x008e
        L_0x0074:
            r12 = move-exception
            org.bouncycastle.cms.CMSException r13 = new org.bouncycastle.cms.CMSException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "data processing exception: "
            r0.<init>(r1)
            java.lang.String r1 = r12.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r13.<init>(r0, r12)
            throw r13
        L_0x008d:
            r13 = r4
        L_0x008e:
            java.util.List r3 = r11.signerGens
            java.util.Iterator r3 = r3.iterator()
        L_0x0094:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x00c6
            java.lang.Object r5 = r3.next()
            org.bouncycastle.cms.SignerInfoGenerator r5 = (org.bouncycastle.cms.SignerInfoGenerator) r5
            org.bouncycastle.asn1.cms.SignerInfo r6 = r5.generate(r2)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r7 = r6.getDigestAlgorithm()
            r0.add(r7)
            r1.add(r6)
            byte[] r5 = r5.getCalculatedDigest()
            if (r5 == 0) goto L_0x0094
            java.util.Map r7 = r11.digests
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r6 = r6.getDigestAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r6.getAlgorithm()
            java.lang.String r6 = r6.getId()
            r7.put(r6, r5)
            goto L_0x0094
        L_0x00c6:
            java.util.List r3 = r11.certs
            int r3 = r3.size()
            if (r3 == 0) goto L_0x00e1
            boolean r3 = r11.isDefiniteLength
            if (r3 == 0) goto L_0x00d9
            java.util.List r3 = r11.certs
            org.bouncycastle.asn1.ASN1Set r3 = org.bouncycastle.cms.CMSUtils.createDlSetFromList(r3)
            goto L_0x00df
        L_0x00d9:
            java.util.List r3 = r11.certs
            org.bouncycastle.asn1.ASN1Set r3 = org.bouncycastle.cms.CMSUtils.createBerSetFromList(r3)
        L_0x00df:
            r8 = r3
            goto L_0x00e2
        L_0x00e1:
            r8 = r4
        L_0x00e2:
            java.util.List r3 = r11.crls
            int r3 = r3.size()
            if (r3 == 0) goto L_0x00fb
            boolean r3 = r11.isDefiniteLength
            if (r3 == 0) goto L_0x00f5
            java.util.List r3 = r11.crls
            org.bouncycastle.asn1.ASN1Set r4 = org.bouncycastle.cms.CMSUtils.createDlSetFromList(r3)
            goto L_0x00fb
        L_0x00f5:
            java.util.List r3 = r11.crls
            org.bouncycastle.asn1.ASN1Set r4 = org.bouncycastle.cms.CMSUtils.createBerSetFromList(r3)
        L_0x00fb:
            r9 = r4
            org.bouncycastle.asn1.cms.ContentInfo r7 = new org.bouncycastle.asn1.cms.ContentInfo
            r7.<init>(r2, r13)
            org.bouncycastle.asn1.cms.SignedData r13 = new org.bouncycastle.asn1.cms.SignedData
            org.bouncycastle.asn1.ASN1Set r6 = org.bouncycastle.cms.CMSUtils.convertToDlSet(r0)
            org.bouncycastle.asn1.DERSet r10 = new org.bouncycastle.asn1.DERSet
            r10.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r1)
            r5 = r13
            r5.<init>(r6, r7, r8, r9, r10)
            org.bouncycastle.asn1.cms.ContentInfo r0 = new org.bouncycastle.asn1.cms.ContentInfo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.cms.CMSObjectIdentifiers.signedData
            r0.<init>(r1, r13)
            org.bouncycastle.cms.CMSSignedData r13 = new org.bouncycastle.cms.CMSSignedData
            r13.<init>((org.bouncycastle.cms.CMSProcessable) r12, (org.bouncycastle.asn1.cms.ContentInfo) r0)
            return r13
        L_0x011d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "this method can only be used with SignerInfoGenerator"
            r12.<init>(r13)
            goto L_0x0126
        L_0x0125:
            throw r12
        L_0x0126:
            goto L_0x0125
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.CMSSignedDataGenerator.generate(org.bouncycastle.cms.CMSTypedData, boolean):org.bouncycastle.cms.CMSSignedData");
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signerInformation) throws CMSException {
        return generate(new CMSProcessableByteArray((ASN1ObjectIdentifier) null, signerInformation.getSignature()), false).getSignerInfos();
    }

    public void setDefiniteLengthEncoding(boolean z) {
        this.isDefiniteLength = z;
    }
}
