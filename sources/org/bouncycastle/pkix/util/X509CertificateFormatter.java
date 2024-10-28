package org.bouncycastle.pkix.util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.encoders.Hex;

public class X509CertificateFormatter {
    private static Map<KeyPurposeId, String> extUsageMap = new HashMap();
    private static Map<ASN1ObjectIdentifier, String> keyAlgMap = new HashMap();
    private static Map<ASN1ObjectIdentifier, String> oidMap = new HashMap();
    private static final String spaceStr = "                                                              ";
    private static Map<Integer, String> usageMap = new HashMap();

    static {
        oidMap.put(Extension.subjectDirectoryAttributes, "subjectDirectoryAttributes");
        oidMap.put(Extension.subjectKeyIdentifier, "subjectKeyIdentifier");
        oidMap.put(Extension.keyUsage, "keyUsage");
        oidMap.put(Extension.privateKeyUsagePeriod, "privateKeyUsagePeriod");
        oidMap.put(Extension.subjectAlternativeName, "subjectAlternativeName");
        oidMap.put(Extension.issuerAlternativeName, "issuerAlternativeName");
        oidMap.put(Extension.basicConstraints, "basicConstraints");
        oidMap.put(Extension.cRLNumber, "cRLNumber");
        oidMap.put(Extension.reasonCode, "reasonCode");
        oidMap.put(Extension.instructionCode, "instructionCode");
        oidMap.put(Extension.invalidityDate, "invalidityDate");
        oidMap.put(Extension.deltaCRLIndicator, "deltaCRLIndicator");
        oidMap.put(Extension.issuingDistributionPoint, "issuingDistributionPoint");
        oidMap.put(Extension.certificateIssuer, "certificateIssuer");
        oidMap.put(Extension.nameConstraints, "nameConstraints");
        oidMap.put(Extension.cRLDistributionPoints, "cRLDistributionPoints");
        oidMap.put(Extension.certificatePolicies, "certificatePolicies");
        oidMap.put(Extension.policyMappings, "policyMappings");
        oidMap.put(Extension.authorityKeyIdentifier, "authorityKeyIdentifier");
        oidMap.put(Extension.policyConstraints, "policyConstraints");
        oidMap.put(Extension.extendedKeyUsage, "extendedKeyUsage");
        oidMap.put(Extension.freshestCRL, "freshestCRL");
        oidMap.put(Extension.inhibitAnyPolicy, "inhibitAnyPolicy");
        oidMap.put(Extension.authorityInfoAccess, "authorityInfoAccess");
        oidMap.put(Extension.subjectInfoAccess, "subjectInfoAccess");
        oidMap.put(Extension.logoType, "logoType");
        oidMap.put(Extension.biometricInfo, "biometricInfo");
        oidMap.put(Extension.qCStatements, "qCStatements");
        oidMap.put(Extension.auditIdentity, "auditIdentity");
        oidMap.put(Extension.noRevAvail, "noRevAvail");
        oidMap.put(Extension.targetInformation, "targetInformation");
        oidMap.put(Extension.expiredCertsOnCRL, "expiredCertsOnCRL");
        usageMap.put(128, "digitalSignature");
        usageMap.put(64, "nonRepudiation");
        usageMap.put(32, "keyEncipherment");
        usageMap.put(16, "dataEncipherment");
        usageMap.put(8, "keyAgreement");
        usageMap.put(4, "keyCertSign");
        usageMap.put(2, "cRLSign");
        usageMap.put(1, "encipherOnly");
        usageMap.put(32768, "decipherOnly");
        extUsageMap.put(KeyPurposeId.anyExtendedKeyUsage, "anyExtendedKeyUsage");
        extUsageMap.put(KeyPurposeId.id_kp_serverAuth, "id_kp_serverAuth");
        extUsageMap.put(KeyPurposeId.id_kp_clientAuth, "id_kp_clientAuth");
        extUsageMap.put(KeyPurposeId.id_kp_codeSigning, "id_kp_codeSigning");
        extUsageMap.put(KeyPurposeId.id_kp_emailProtection, "id_kp_emailProtection");
        extUsageMap.put(KeyPurposeId.id_kp_ipsecEndSystem, "id_kp_ipsecEndSystem");
        extUsageMap.put(KeyPurposeId.id_kp_ipsecTunnel, "id_kp_ipsecTunnel");
        extUsageMap.put(KeyPurposeId.id_kp_ipsecUser, "id_kp_ipsecUser");
        extUsageMap.put(KeyPurposeId.id_kp_timeStamping, "id_kp_timeStamping");
        extUsageMap.put(KeyPurposeId.id_kp_OCSPSigning, "id_kp_OCSPSigning");
        extUsageMap.put(KeyPurposeId.id_kp_dvcs, "id_kp_dvcs");
        extUsageMap.put(KeyPurposeId.id_kp_sbgpCertAAServerAuth, "id_kp_sbgpCertAAServerAuth");
        extUsageMap.put(KeyPurposeId.id_kp_scvp_responder, "id_kp_scvp_responder");
        extUsageMap.put(KeyPurposeId.id_kp_eapOverPPP, "id_kp_eapOverPPP");
        extUsageMap.put(KeyPurposeId.id_kp_eapOverLAN, "id_kp_eapOverLAN");
        extUsageMap.put(KeyPurposeId.id_kp_scvpServer, "id_kp_scvpServer");
        extUsageMap.put(KeyPurposeId.id_kp_scvpClient, "id_kp_scvpClient");
        extUsageMap.put(KeyPurposeId.id_kp_ipsecIKE, "id_kp_ipsecIKE");
        extUsageMap.put(KeyPurposeId.id_kp_capwapAC, "id_kp_capwapAC");
        extUsageMap.put(KeyPurposeId.id_kp_capwapWTP, "id_kp_capwapWTP");
        extUsageMap.put(KeyPurposeId.id_kp_cmcCA, "id_kp_cmcCA");
        extUsageMap.put(KeyPurposeId.id_kp_cmcRA, "id_kp_cmcRA");
        extUsageMap.put(KeyPurposeId.id_kp_cmKGA, "id_kp_cmKGA");
        extUsageMap.put(KeyPurposeId.id_kp_smartcardlogon, "id_kp_smartcardlogon");
        extUsageMap.put(KeyPurposeId.id_kp_macAddress, "id_kp_macAddress");
        extUsageMap.put(KeyPurposeId.id_kp_msSGC, "id_kp_msSGC");
        extUsageMap.put(KeyPurposeId.id_kp_nsSGC, "id_kp_nsSGC");
        keyAlgMap.put(PKCSObjectIdentifiers.rsaEncryption, "rsaEncryption");
        keyAlgMap.put(X9ObjectIdentifiers.id_ecPublicKey, "id_ecPublicKey");
        keyAlgMap.put(EdECObjectIdentifiers.id_Ed25519, "id_Ed25519");
        keyAlgMap.put(EdECObjectIdentifiers.id_Ed448, "id_Ed448");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0245 A[EDGE_INSN: B:49:0x0245->B:46:0x0245 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x00c5  */
    public static java.lang.String asString(org.bouncycastle.cert.X509CertificateHolder r14) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = org.bouncycastle.util.Strings.lineSeparator()
            org.bouncycastle.operator.DefaultSignatureNameFinder r2 = new org.bouncycastle.operator.DefaultSignatureNameFinder
            r2.<init>()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r14.getSignatureAlgorithm()
            java.lang.String r2 = r2.getAlgorithmName((org.bouncycastle.asn1.x509.AlgorithmIdentifier) r3)
            java.lang.String r3 = "WITH"
            java.lang.String r4 = "with"
            java.lang.String r2 = r2.replace(r3, r4)
            org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r3 = r14.getSubjectPublicKeyInfo()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r3.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r3.getAlgorithm()
            java.lang.String r3 = keyAlgToLabel(r3)
            java.lang.String r4 = "  [0]         Version: "
            r0.append(r4)
            int r4 = r14.getVersionNumber()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = "         SerialNumber: "
            r0.append(r4)
            java.math.BigInteger r4 = r14.getSerialNumber()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = "             IssuerDN: "
            r0.append(r4)
            org.bouncycastle.asn1.x500.X500Name r4 = r14.getIssuer()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = "           Start Date: "
            r0.append(r4)
            java.util.Date r4 = r14.getNotBefore()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = "           Final Date: "
            r0.append(r4)
            java.util.Date r4 = r14.getNotAfter()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = "            SubjectDN: "
            r0.append(r4)
            org.bouncycastle.asn1.x500.X500Name r4 = r14.getSubject()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = "           Public Key: "
            r0.append(r4)
            r0.append(r3)
            r0.append(r1)
            java.lang.String r3 = "                       "
            r0.append(r3)
            org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r4 = r14.getSubjectPublicKeyInfo()
            org.bouncycastle.asn1.ASN1BitString r4 = r4.getPublicKeyData()
            byte[] r4 = r4.getOctets()
            prettyPrintData(r4, r0, r1)
            org.bouncycastle.asn1.x509.Extensions r4 = r14.getExtensions()
            if (r4 == 0) goto L_0x0245
            java.util.Enumeration r5 = r4.oids()
            boolean r6 = r5.hasMoreElements()
            if (r6 == 0) goto L_0x00bf
            java.lang.String r6 = "           Extensions: "
        L_0x00b9:
            r0.append(r6)
        L_0x00bc:
            r0.append(r1)
        L_0x00bf:
            boolean r6 = r5.hasMoreElements()
            if (r6 == 0) goto L_0x0245
            java.lang.Object r6 = r5.nextElement()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r6
            org.bouncycastle.asn1.x509.Extension r7 = r4.getExtension(r6)
            org.bouncycastle.asn1.ASN1OctetString r8 = r7.getExtnValue()
            if (r8 == 0) goto L_0x00bc
            org.bouncycastle.asn1.ASN1OctetString r8 = r7.getExtnValue()
            byte[] r8 = r8.getOctets()
            org.bouncycastle.asn1.ASN1InputStream r9 = new org.bouncycastle.asn1.ASN1InputStream
            r9.<init>((byte[]) r8)
            java.lang.String r8 = oidToLabel(r6)     // Catch:{ Exception -> 0x0231 }
            r0.append(r3)     // Catch:{ Exception -> 0x0231 }
            r0.append(r8)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r10 = ": critical("
            r0.append(r10)     // Catch:{ Exception -> 0x0231 }
            boolean r7 = r7.isCritical()     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r7 = ") "
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            r0.append(r1)     // Catch:{ Exception -> 0x0231 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0231 }
            r7.<init>()     // Catch:{ Exception -> 0x0231 }
            r7.append(r3)     // Catch:{ Exception -> 0x0231 }
            int r10 = r8.length()     // Catch:{ Exception -> 0x0231 }
            int r10 = r10 + 2
            java.lang.String r10 = spaces(r10)     // Catch:{ Exception -> 0x0231 }
            r7.append(r10)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0231 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = org.bouncycastle.asn1.x509.Extension.basicConstraints     // Catch:{ Exception -> 0x0231 }
            boolean r10 = r6.equals((org.bouncycastle.asn1.ASN1Primitive) r10)     // Catch:{ Exception -> 0x0231 }
            if (r10 == 0) goto L_0x0177
            org.bouncycastle.asn1.ASN1Primitive r9 = r9.readObject()     // Catch:{ Exception -> 0x0231 }
            org.bouncycastle.asn1.x509.BasicConstraints r9 = org.bouncycastle.asn1.x509.BasicConstraints.getInstance(r9)     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0231 }
            r7.<init>()     // Catch:{ Exception -> 0x0231 }
            java.lang.String r10 = "isCA : "
            r7.append(r10)     // Catch:{ Exception -> 0x0231 }
            boolean r10 = r9.isCA()     // Catch:{ Exception -> 0x0231 }
            r7.append(r10)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            r0.append(r1)     // Catch:{ Exception -> 0x0231 }
            boolean r7 = r9.isCA()     // Catch:{ Exception -> 0x0231 }
            if (r7 == 0) goto L_0x00bf
            int r7 = r8.length()     // Catch:{ Exception -> 0x0231 }
            int r7 = r7 + 2
            java.lang.String r7 = spaces(r7)     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0231 }
            r7.<init>()     // Catch:{ Exception -> 0x0231 }
            java.lang.String r8 = "pathLenConstraint : "
            r7.append(r8)     // Catch:{ Exception -> 0x0231 }
            java.math.BigInteger r8 = r9.getPathLenConstraint()     // Catch:{ Exception -> 0x0231 }
            r7.append(r8)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
        L_0x0172:
            r0.append(r1)     // Catch:{ Exception -> 0x0231 }
            goto L_0x00bf
        L_0x0177:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r8 = org.bouncycastle.asn1.x509.Extension.keyUsage     // Catch:{ Exception -> 0x0231 }
            boolean r8 = r6.equals((org.bouncycastle.asn1.ASN1Primitive) r8)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r10 = ", "
            r11 = 0
            r12 = 1
            if (r8 == 0) goto L_0x01c1
            org.bouncycastle.asn1.ASN1Primitive r8 = r9.readObject()     // Catch:{ Exception -> 0x0231 }
            org.bouncycastle.asn1.x509.KeyUsage r8 = org.bouncycastle.asn1.x509.KeyUsage.getInstance(r8)     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.util.Map<java.lang.Integer, java.lang.String> r7 = usageMap     // Catch:{ Exception -> 0x0231 }
            java.util.Set r7 = r7.keySet()     // Catch:{ Exception -> 0x0231 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x0231 }
        L_0x0198:
            boolean r9 = r7.hasNext()     // Catch:{ Exception -> 0x0231 }
            if (r9 == 0) goto L_0x0172
            java.lang.Object r9 = r7.next()     // Catch:{ Exception -> 0x0231 }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ Exception -> 0x0231 }
            int r13 = r9.intValue()     // Catch:{ Exception -> 0x0231 }
            boolean r13 = r8.hasUsages(r13)     // Catch:{ Exception -> 0x0231 }
            if (r13 == 0) goto L_0x0198
            if (r12 != 0) goto L_0x01b4
            r0.append(r10)     // Catch:{ Exception -> 0x0231 }
            goto L_0x01b5
        L_0x01b4:
            r12 = 0
        L_0x01b5:
            java.util.Map<java.lang.Integer, java.lang.String> r13 = usageMap     // Catch:{ Exception -> 0x0231 }
            java.lang.Object r9 = r13.get(r9)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x0231 }
            r0.append(r9)     // Catch:{ Exception -> 0x0231 }
            goto L_0x0198
        L_0x01c1:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r8 = org.bouncycastle.asn1.x509.Extension.extendedKeyUsage     // Catch:{ Exception -> 0x0231 }
            boolean r8 = r6.equals((org.bouncycastle.asn1.ASN1Primitive) r8)     // Catch:{ Exception -> 0x0231 }
            if (r8 == 0) goto L_0x0203
            org.bouncycastle.asn1.ASN1Primitive r8 = r9.readObject()     // Catch:{ Exception -> 0x0231 }
            org.bouncycastle.asn1.x509.ExtendedKeyUsage r8 = org.bouncycastle.asn1.x509.ExtendedKeyUsage.getInstance(r8)     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.util.Map<org.bouncycastle.asn1.x509.KeyPurposeId, java.lang.String> r7 = extUsageMap     // Catch:{ Exception -> 0x0231 }
            java.util.Set r7 = r7.keySet()     // Catch:{ Exception -> 0x0231 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x0231 }
        L_0x01de:
            boolean r9 = r7.hasNext()     // Catch:{ Exception -> 0x0231 }
            if (r9 == 0) goto L_0x0172
            java.lang.Object r9 = r7.next()     // Catch:{ Exception -> 0x0231 }
            org.bouncycastle.asn1.x509.KeyPurposeId r9 = (org.bouncycastle.asn1.x509.KeyPurposeId) r9     // Catch:{ Exception -> 0x0231 }
            boolean r13 = r8.hasKeyPurposeId(r9)     // Catch:{ Exception -> 0x0231 }
            if (r13 == 0) goto L_0x01de
            if (r12 != 0) goto L_0x01f6
            r0.append(r10)     // Catch:{ Exception -> 0x0231 }
            goto L_0x01f7
        L_0x01f6:
            r12 = 0
        L_0x01f7:
            java.util.Map<org.bouncycastle.asn1.x509.KeyPurposeId, java.lang.String> r13 = extUsageMap     // Catch:{ Exception -> 0x0231 }
            java.lang.Object r9 = r13.get(r9)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x0231 }
            r0.append(r9)     // Catch:{ Exception -> 0x0231 }
            goto L_0x01de
        L_0x0203:
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r8 = "value = "
            r0.append(r8)     // Catch:{ Exception -> 0x0231 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0231 }
            r8.<init>()     // Catch:{ Exception -> 0x0231 }
            r8.append(r7)     // Catch:{ Exception -> 0x0231 }
            r7 = 8
            java.lang.String r7 = spaces(r7)     // Catch:{ Exception -> 0x0231 }
            r8.append(r7)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x0231 }
            org.bouncycastle.asn1.ASN1Primitive r8 = r9.readObject()     // Catch:{ Exception -> 0x0231 }
            java.lang.String r8 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r8)     // Catch:{ Exception -> 0x0231 }
            java.lang.String r7 = indent(r7, r8, r1)     // Catch:{ Exception -> 0x0231 }
            r0.append(r7)     // Catch:{ Exception -> 0x0231 }
            goto L_0x0172
        L_0x0231:
            r7 = move-exception
            r7.printStackTrace()
            java.lang.String r6 = r6.getId()
            r0.append(r6)
            java.lang.String r6 = " value = "
            r0.append(r6)
            java.lang.String r6 = "*****"
            goto L_0x00b9
        L_0x0245:
            java.lang.String r3 = "  Signature Algorithm: "
            r0.append(r3)
            r0.append(r2)
            r0.append(r1)
            java.lang.String r2 = "            Signature: "
            r0.append(r2)
            byte[] r14 = r14.getSignature()
            prettyPrintData(r14, r0, r1)
            java.lang.String r14 = r0.toString()
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.util.X509CertificateFormatter.asString(org.bouncycastle.cert.X509CertificateHolder):java.lang.String");
    }

    static void format(StringBuilder sb, byte[] bArr, String str) {
        int i = 20;
        while (i < bArr.length) {
            int length = bArr.length - 20;
            sb.append("                       ");
            sb.append(i < length ? Hex.toHexString(bArr, i, 20) : Hex.toHexString(bArr, i, bArr.length - i));
            sb.append(str);
            i += 20;
        }
    }

    private static String indent(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        String substring = str2.substring(0, str2.length() - str3.length());
        while (true) {
            int indexOf = substring.indexOf(str3);
            if (indexOf <= 0) {
                break;
            }
            sb.append(substring.substring(0, indexOf));
            sb.append(str3);
            sb.append(str);
            if (substring.length() > 0) {
                substring = substring.substring(indexOf + str3.length());
            }
        }
        if (sb.length() == 0) {
            return substring;
        }
        sb.append(substring);
        return sb.toString();
    }

    private static String keyAlgToLabel(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = keyAlgMap.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println(asString((X509CertificateHolder) new PEMParser(new FileReader(strArr[0])).readObject()));
    }

    private static String oidToLabel(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = oidMap.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    static void prettyPrintData(byte[] bArr, StringBuilder sb, String str) {
        if (bArr.length > 20) {
            sb.append(Hex.toHexString(bArr, 0, 20));
            sb.append(str);
            format(sb, bArr, str);
            return;
        }
        sb.append(Hex.toHexString(bArr));
        sb.append(str);
    }

    private static String spaces(int i) {
        return spaceStr.substring(0, i);
    }
}
