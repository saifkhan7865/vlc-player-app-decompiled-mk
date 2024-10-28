package org.bouncycastle.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Exceptions;

public class PKCS10CertificationRequest {
    private static Attribute[] EMPTY_ARRAY = new Attribute[0];
    private final SubjectPublicKeyInfo altPublicKey;
    private final AlgorithmIdentifier altSignature;
    private final ASN1BitString altSignatureValue;
    private final CertificationRequest certificationRequest;
    private final boolean isAltRequest;

    public PKCS10CertificationRequest(CertificationRequest certificationRequest2) {
        ASN1BitString aSN1BitString;
        SubjectPublicKeyInfo subjectPublicKeyInfo;
        if (certificationRequest2 != null) {
            this.certificationRequest = certificationRequest2;
            ASN1Set attributes = certificationRequest2.getCertificationRequestInfo().getAttributes();
            AlgorithmIdentifier algorithmIdentifier = null;
            if (attributes != null) {
                Enumeration objects = attributes.getObjects();
                subjectPublicKeyInfo = null;
                aSN1BitString = null;
                while (objects.hasMoreElements()) {
                    Attribute instance = Attribute.getInstance(objects.nextElement());
                    algorithmIdentifier = Extension.altSignatureAlgorithm.equals((ASN1Primitive) instance.getAttrType()) ? AlgorithmIdentifier.getInstance(getSingleValue(instance)) : algorithmIdentifier;
                    subjectPublicKeyInfo = Extension.subjectAltPublicKeyInfo.equals((ASN1Primitive) instance.getAttrType()) ? SubjectPublicKeyInfo.getInstance(getSingleValue(instance)) : subjectPublicKeyInfo;
                    if (Extension.altSignatureValue.equals((ASN1Primitive) instance.getAttrType())) {
                        aSN1BitString = ASN1BitString.getInstance(getSingleValue(instance));
                    }
                }
            } else {
                subjectPublicKeyInfo = null;
                aSN1BitString = null;
            }
            boolean z = true;
            boolean z2 = (algorithmIdentifier != null) | (subjectPublicKeyInfo != null) | (aSN1BitString != null);
            this.isAltRequest = z2;
            if (z2) {
                if (!(aSN1BitString == null ? false : z) || !((algorithmIdentifier != null) & (subjectPublicKeyInfo != null))) {
                    throw new IllegalArgumentException("invalid alternate public key details found");
                }
            }
            this.altSignature = algorithmIdentifier;
            this.altPublicKey = subjectPublicKeyInfo;
            this.altSignatureValue = aSN1BitString;
            return;
        }
        throw new NullPointerException("certificationRequest cannot be null");
    }

    public PKCS10CertificationRequest(byte[] bArr) throws IOException {
        this(parseBytes(bArr));
    }

    private static ASN1Encodable getSingleValue(Attribute attribute) {
        ASN1Encodable[] attributeValues = attribute.getAttributeValues();
        if (attributeValues.length == 1) {
            return attributeValues[0];
        }
        throw new IllegalArgumentException("single value attribute value not size of 1");
    }

    private static CertificationRequest parseBytes(byte[] bArr) throws IOException {
        try {
            CertificationRequest instance = CertificationRequest.getInstance(ASN1Primitive.fromByteArray(bArr));
            if (instance != null) {
                return instance;
            }
            throw new PKCSIOException("empty data passed to constructor");
        } catch (ClassCastException e) {
            throw new PKCSIOException("malformed data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new PKCSIOException("malformed data: " + e2.getMessage(), e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PKCS10CertificationRequest)) {
            return false;
        }
        return toASN1Structure().equals(((PKCS10CertificationRequest) obj).toASN1Structure());
    }

    public Attribute[] getAttributes() {
        ASN1Set attributes = this.certificationRequest.getCertificationRequestInfo().getAttributes();
        if (attributes == null) {
            return EMPTY_ARRAY;
        }
        Attribute[] attributeArr = new Attribute[attributes.size()];
        for (int i = 0; i != attributes.size(); i++) {
            attributeArr[i] = Attribute.getInstance(attributes.getObjectAt(i));
        }
        return attributeArr;
    }

    public Attribute[] getAttributes(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        ASN1Set attributes = this.certificationRequest.getCertificationRequestInfo().getAttributes();
        if (attributes == null) {
            return EMPTY_ARRAY;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != attributes.size(); i++) {
            Attribute instance = Attribute.getInstance(attributes.getObjectAt(i));
            if (instance.getAttrType().equals((ASN1Primitive) aSN1ObjectIdentifier)) {
                arrayList.add(instance);
            }
        }
        return arrayList.size() == 0 ? EMPTY_ARRAY : (Attribute[]) arrayList.toArray(new Attribute[arrayList.size()]);
    }

    public byte[] getEncoded() throws IOException {
        return this.certificationRequest.getEncoded();
    }

    public Extensions getRequestedExtensions() {
        Attribute[] attributes = getAttributes();
        int i = 0;
        while (i != attributes.length) {
            Attribute attribute = attributes[i];
            if (PKCSObjectIdentifiers.pkcs_9_at_extensionRequest.equals((ASN1Primitive) attribute.getAttrType())) {
                ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
                ASN1Set attrValues = attribute.getAttrValues();
                if (attrValues == null || attrValues.size() == 0) {
                    throw new IllegalStateException("pkcs_9_at_extensionRequest present but has no value");
                }
                try {
                    Enumeration objects = ASN1Sequence.getInstance(attrValues.getObjectAt(0)).getObjects();
                    while (objects.hasMoreElements()) {
                        ASN1Sequence instance = ASN1Sequence.getInstance(objects.nextElement());
                        boolean z = instance.size() == 3 && ASN1Boolean.getInstance((Object) instance.getObjectAt(1)).isTrue();
                        if (instance.size() == 2) {
                            extensionsGenerator.addExtension(ASN1ObjectIdentifier.getInstance(instance.getObjectAt(0)), false, ASN1OctetString.getInstance(instance.getObjectAt(1)).getOctets());
                        } else if (instance.size() == 3) {
                            extensionsGenerator.addExtension(ASN1ObjectIdentifier.getInstance(instance.getObjectAt(0)), z, ASN1OctetString.getInstance(instance.getObjectAt(2)).getOctets());
                        } else {
                            throw new IllegalStateException("incorrect sequence size of Extension get " + instance.size() + " expected 2 or three");
                        }
                    }
                    return extensionsGenerator.generate();
                } catch (IllegalArgumentException e) {
                    throw Exceptions.illegalStateException("asn1 processing issue: " + e.getMessage(), e);
                }
            } else {
                i++;
            }
        }
        return null;
    }

    public byte[] getSignature() {
        return this.certificationRequest.getSignature().getOctets();
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.certificationRequest.getSignatureAlgorithm();
    }

    public X500Name getSubject() {
        return X500Name.getInstance(this.certificationRequest.getCertificationRequestInfo().getSubject());
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.certificationRequest.getCertificationRequestInfo().getSubjectPublicKeyInfo();
    }

    public boolean hasAltPublicKey() {
        return this.isAltRequest;
    }

    public int hashCode() {
        return toASN1Structure().hashCode();
    }

    public boolean isAltSignatureValid(ContentVerifierProvider contentVerifierProvider) throws PKCSException {
        if (this.isAltRequest) {
            CertificationRequestInfo certificationRequestInfo = this.certificationRequest.getCertificationRequestInfo();
            ASN1Set attributes = certificationRequestInfo.getAttributes();
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            Enumeration objects = attributes.getObjects();
            while (objects.hasMoreElements()) {
                Attribute instance = Attribute.getInstance(objects.nextElement());
                if (!Extension.altSignatureValue.equals((ASN1Primitive) instance.getAttrType())) {
                    aSN1EncodableVector.add(instance);
                }
            }
            CertificationRequestInfo certificationRequestInfo2 = new CertificationRequestInfo(certificationRequestInfo.getSubject(), certificationRequestInfo.getSubjectPublicKeyInfo(), (ASN1Set) new DERSet(aSN1EncodableVector));
            try {
                ContentVerifier contentVerifier = contentVerifierProvider.get(this.altSignature);
                OutputStream outputStream = contentVerifier.getOutputStream();
                outputStream.write(certificationRequestInfo2.getEncoded(ASN1Encoding.DER));
                outputStream.close();
                return contentVerifier.verify(this.altSignatureValue.getOctets());
            } catch (Exception e) {
                throw new PKCSException("unable to process signature: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalStateException("no alternate public key present");
        }
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws PKCSException {
        CertificationRequestInfo certificationRequestInfo = this.certificationRequest.getCertificationRequestInfo();
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.certificationRequest.getSignatureAlgorithm());
            OutputStream outputStream = contentVerifier.getOutputStream();
            outputStream.write(certificationRequestInfo.getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return contentVerifier.verify(getSignature());
        } catch (Exception e) {
            throw new PKCSException("unable to process signature: " + e.getMessage(), e);
        }
    }

    public CertificationRequest toASN1Structure() {
        return this.certificationRequest;
    }
}
