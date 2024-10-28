package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputAEADEncryptor;
import org.bouncycastle.operator.OutputEncryptor;

public class CMSEnvelopedDataStreamGenerator extends CMSEnvelopedGenerator {
    private boolean _berEncodeRecipientSet;
    private int _bufferSize;

    private class CmsEnvelopedDataOutputStream extends OutputStream {
        private BERSequenceGenerator _cGen;
        private final OutputStream _cOut;
        private BERSequenceGenerator _eiGen;
        private final OutputEncryptor _encryptor;
        private BERSequenceGenerator _envGen;
        private OutputStream _octetStream;

        public CmsEnvelopedDataOutputStream(OutputEncryptor outputEncryptor, OutputStream outputStream, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this._encryptor = outputEncryptor;
            this._octetStream = outputStream;
            this._cOut = outputEncryptor.getOutputStream(outputStream);
            this._cGen = bERSequenceGenerator;
            this._envGen = bERSequenceGenerator2;
            this._eiGen = bERSequenceGenerator3;
        }

        public void close() throws IOException {
            this._cOut.close();
            OutputEncryptor outputEncryptor = this._encryptor;
            if (outputEncryptor instanceof OutputAEADEncryptor) {
                this._octetStream.write(((OutputAEADEncryptor) outputEncryptor).getMAC());
                this._octetStream.close();
            }
            this._eiGen.close();
            if (CMSEnvelopedDataStreamGenerator.this.unprotectedAttributeGenerator != null) {
                this._envGen.addObject((ASN1Primitive) new DERTaggedObject(false, 1, (ASN1Encodable) new BERSet(CMSEnvelopedDataStreamGenerator.this.unprotectedAttributeGenerator.getAttributes(Collections.EMPTY_MAP).toASN1EncodableVector())));
            }
            this._envGen.close();
            this._cGen.close();
        }

        public void write(int i) throws IOException {
            this._cOut.write(i);
        }

        public void write(byte[] bArr) throws IOException {
            this._cOut.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this._cOut.write(bArr, i, i2);
        }
    }

    private OutputStream doOpen(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        GenericKey key = outputEncryptor.getKey();
        for (RecipientInfoGenerator generate : this.recipientInfoGenerators) {
            aSN1EncodableVector.add(generate.generate(key));
        }
        return open(aSN1ObjectIdentifier, outputStream, aSN1EncodableVector, outputEncryptor);
    }

    private ASN1Integer getVersion(ASN1EncodableVector aSN1EncodableVector) {
        return this.unprotectedAttributeGenerator != null ? new ASN1Integer((long) EnvelopedData.calculateVersion(this.originatorInfo, new DLSet(aSN1EncodableVector), new DLSet())) : new ASN1Integer((long) EnvelopedData.calculateVersion(this.originatorInfo, new DLSet(aSN1EncodableVector), (ASN1Set) null));
    }

    /* access modifiers changed from: protected */
    public OutputStream open(OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws CMSException {
        try {
            BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
            bERSequenceGenerator.addObject((ASN1Primitive) CMSObjectIdentifiers.envelopedData);
            BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
            ASN1Set bERSet = this._berEncodeRecipientSet ? new BERSet(aSN1EncodableVector) : new DERSet(aSN1EncodableVector);
            bERSequenceGenerator2.addObject((ASN1Primitive) getVersion(aSN1EncodableVector));
            if (this.originatorInfo != null) {
                bERSequenceGenerator2.addObject((ASN1Primitive) new DERTaggedObject(false, 0, (ASN1Encodable) this.originatorInfo));
            }
            bERSequenceGenerator2.getRawOutputStream().write(bERSet.getEncoded());
            BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
            bERSequenceGenerator3.addObject((ASN1Primitive) CMSObjectIdentifiers.data);
            bERSequenceGenerator3.getRawOutputStream().write(outputEncryptor.getAlgorithmIdentifier().getEncoded());
            return new CmsEnvelopedDataOutputStream(outputEncryptor, CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, false, this._bufferSize), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
        } catch (IOException e) {
            throw new CMSException("exception decoding algorithm parameters.", e);
        }
    }

    public OutputStream open(OutputStream outputStream, OutputEncryptor outputEncryptor) throws CMSException, IOException {
        return doOpen(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), outputStream, outputEncryptor);
    }

    /* access modifiers changed from: protected */
    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws IOException {
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject((ASN1Primitive) CMSObjectIdentifiers.envelopedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject((ASN1Primitive) getVersion(aSN1EncodableVector));
        if (this.originatorInfo != null) {
            bERSequenceGenerator2.addObject((ASN1Primitive) new DERTaggedObject(false, 0, (ASN1Encodable) this.originatorInfo));
        }
        if (this._berEncodeRecipientSet) {
            bERSequenceGenerator2.getRawOutputStream().write(new BERSet(aSN1EncodableVector).getEncoded());
        } else {
            bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
        }
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject((ASN1Primitive) aSN1ObjectIdentifier);
        bERSequenceGenerator3.getRawOutputStream().write(outputEncryptor.getAlgorithmIdentifier().getEncoded());
        return new CmsEnvelopedDataOutputStream(outputEncryptor, CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, false, this._bufferSize), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputEncryptor outputEncryptor) throws CMSException, IOException {
        return doOpen(aSN1ObjectIdentifier, outputStream, outputEncryptor);
    }

    public void setBEREncodeRecipients(boolean z) {
        this._berEncodeRecipientSet = z;
    }

    public void setBufferSize(int i) {
        this._bufferSize = i;
    }
}
