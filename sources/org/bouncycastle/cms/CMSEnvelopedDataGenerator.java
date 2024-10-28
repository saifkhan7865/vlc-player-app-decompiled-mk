package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputAEADEncryptor;
import org.bouncycastle.operator.OutputEncryptor;

public class CMSEnvelopedDataGenerator extends CMSEnvelopedGenerator {
    private CMSEnvelopedData doGenerate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            OutputStream outputStream = outputEncryptor.getOutputStream(byteArrayOutputStream);
            cMSTypedData.write(outputStream);
            outputStream.close();
            if (outputEncryptor instanceof OutputAEADEncryptor) {
                byte[] mac = ((OutputAEADEncryptor) outputEncryptor).getMAC();
                byteArrayOutputStream.write(mac, 0, mac.length);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
            BEROctetString bEROctetString = new BEROctetString(byteArray);
            GenericKey key = outputEncryptor.getKey();
            for (RecipientInfoGenerator generate : this.recipientInfoGenerators) {
                aSN1EncodableVector.add(generate.generate(key));
            }
            return new CMSEnvelopedData(new ContentInfo(CMSObjectIdentifiers.envelopedData, new EnvelopedData(this.originatorInfo, (ASN1Set) new DERSet(aSN1EncodableVector), new EncryptedContentInfo(cMSTypedData.getContentType(), algorithmIdentifier, bEROctetString), (ASN1Set) this.unprotectedAttributeGenerator != null ? new BERSet(this.unprotectedAttributeGenerator.getAttributes(Collections.EMPTY_MAP).toASN1EncodableVector()) : null)));
        } catch (IOException unused) {
            throw new CMSException("");
        }
    }

    public CMSEnvelopedData generate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        return doGenerate(cMSTypedData, outputEncryptor);
    }
}
