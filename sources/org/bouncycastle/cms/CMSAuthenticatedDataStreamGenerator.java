package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.AuthenticatedData;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.util.io.TeeOutputStream;

public class CMSAuthenticatedDataStreamGenerator extends CMSAuthenticatedGenerator {
    private boolean berEncodeRecipientSet;
    private int bufferSize;
    private MacCalculator macCalculator;

    private class CmsAuthenticatedDataOutputStream extends OutputStream {
        private BERSequenceGenerator cGen;
        private ASN1ObjectIdentifier contentType;
        private OutputStream dataStream;
        private DigestCalculator digestCalculator;
        private BERSequenceGenerator eiGen;
        private BERSequenceGenerator envGen;
        private MacCalculator macCalculator;

        public CmsAuthenticatedDataOutputStream(MacCalculator macCalculator2, DigestCalculator digestCalculator2, ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this.macCalculator = macCalculator2;
            this.digestCalculator = digestCalculator2;
            this.contentType = aSN1ObjectIdentifier;
            this.dataStream = outputStream;
            this.cGen = bERSequenceGenerator;
            this.envGen = bERSequenceGenerator2;
            this.eiGen = bERSequenceGenerator3;
        }

        public void close() throws IOException {
            Map map;
            this.dataStream.close();
            this.eiGen.close();
            DigestCalculator digestCalculator2 = this.digestCalculator;
            if (digestCalculator2 != null) {
                map = Collections.unmodifiableMap(CMSAuthenticatedDataStreamGenerator.this.getBaseParameters(this.contentType, digestCalculator2.getAlgorithmIdentifier(), this.macCalculator.getAlgorithmIdentifier(), this.digestCalculator.getDigest()));
                if (CMSAuthenticatedDataStreamGenerator.this.authGen == null) {
                    CMSAuthenticatedDataStreamGenerator.this.authGen = new DefaultAuthenticatedAttributeTableGenerator();
                }
                DERSet dERSet = new DERSet(CMSAuthenticatedDataStreamGenerator.this.authGen.getAttributes(map).toASN1EncodableVector());
                OutputStream outputStream = this.macCalculator.getOutputStream();
                outputStream.write(dERSet.getEncoded(ASN1Encoding.DER));
                outputStream.close();
                this.envGen.addObject((ASN1Primitive) new DERTaggedObject(false, 2, (ASN1Encodable) dERSet));
            } else {
                map = Collections.EMPTY_MAP;
            }
            this.envGen.addObject((ASN1Primitive) new DEROctetString(this.macCalculator.getMac()));
            if (CMSAuthenticatedDataStreamGenerator.this.unauthGen != null) {
                this.envGen.addObject((ASN1Primitive) new DERTaggedObject(false, 3, (ASN1Encodable) new BERSet(CMSAuthenticatedDataStreamGenerator.this.unauthGen.getAttributes(map).toASN1EncodableVector())));
            }
            this.envGen.close();
            this.cGen.close();
        }

        public void write(int i) throws IOException {
            this.dataStream.write(i);
        }

        public void write(byte[] bArr) throws IOException {
            this.dataStream.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.dataStream.write(bArr, i, i2);
        }
    }

    public OutputStream open(OutputStream outputStream, MacCalculator macCalculator2) throws CMSException {
        return open(CMSObjectIdentifiers.data, outputStream, macCalculator2);
    }

    public OutputStream open(OutputStream outputStream, MacCalculator macCalculator2, DigestCalculator digestCalculator) throws CMSException {
        return open(CMSObjectIdentifiers.data, outputStream, macCalculator2, digestCalculator);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, MacCalculator macCalculator2) throws CMSException {
        return open(aSN1ObjectIdentifier, outputStream, macCalculator2, (DigestCalculator) null);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, MacCalculator macCalculator2, DigestCalculator digestCalculator) throws CMSException {
        this.macCalculator = macCalculator2;
        try {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (RecipientInfoGenerator generate : this.recipientInfoGenerators) {
                aSN1EncodableVector.add(generate.generate(macCalculator2.getKey()));
            }
            BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
            bERSequenceGenerator.addObject((ASN1Primitive) CMSObjectIdentifiers.authenticatedData);
            BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
            bERSequenceGenerator2.addObject((ASN1Primitive) new ASN1Integer((long) AuthenticatedData.calculateVersion(this.originatorInfo)));
            if (this.originatorInfo != null) {
                bERSequenceGenerator2.addObject((ASN1Primitive) new DERTaggedObject(false, 0, (ASN1Encodable) this.originatorInfo));
            }
            if (this.berEncodeRecipientSet) {
                bERSequenceGenerator2.getRawOutputStream().write(new BERSet(aSN1EncodableVector).getEncoded());
            } else {
                bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
            }
            bERSequenceGenerator2.getRawOutputStream().write(macCalculator2.getAlgorithmIdentifier().getEncoded());
            if (digestCalculator != null) {
                bERSequenceGenerator2.addObject((ASN1Primitive) new DERTaggedObject(false, 1, (ASN1Encodable) digestCalculator.getAlgorithmIdentifier()));
            }
            BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
            bERSequenceGenerator3.addObject((ASN1Primitive) aSN1ObjectIdentifier);
            OutputStream createBEROctetOutputStream = CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, true, this.bufferSize);
            return new CmsAuthenticatedDataOutputStream(macCalculator2, digestCalculator, aSN1ObjectIdentifier, digestCalculator != null ? new TeeOutputStream(createBEROctetOutputStream, digestCalculator.getOutputStream()) : new TeeOutputStream(createBEROctetOutputStream, macCalculator2.getOutputStream()), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
        } catch (IOException e) {
            throw new CMSException("exception decoding algorithm parameters.", e);
        }
    }

    public void setBEREncodeRecipients(boolean z) {
        this.berEncodeRecipientSet = z;
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }
}
