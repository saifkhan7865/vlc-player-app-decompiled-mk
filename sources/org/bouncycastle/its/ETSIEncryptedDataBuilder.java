package org.bouncycastle.its;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.its.operator.ETSIDataEncryptor;
import org.bouncycastle.oer.its.ieee1609dot2.AesCcmCiphertext;
import org.bouncycastle.oer.its.ieee1609dot2.EncryptedData;
import org.bouncycastle.oer.its.ieee1609dot2.SequenceOfRecipientInfo;
import org.bouncycastle.oer.its.ieee1609dot2.SymmetricCiphertext;

public class ETSIEncryptedDataBuilder {
    private final List<ETSIRecipientInfoBuilder> recipientInfoBuilders = new ArrayList();

    public void addRecipientInfoBuilder(ETSIRecipientInfoBuilder eTSIRecipientInfoBuilder) {
        this.recipientInfoBuilders.add(eTSIRecipientInfoBuilder);
    }

    public ETSIEncryptedData build(ETSIDataEncryptor eTSIDataEncryptor, byte[] bArr) {
        byte[] encrypt = eTSIDataEncryptor.encrypt(bArr);
        byte[] key = eTSIDataEncryptor.getKey();
        byte[] nonce = eTSIDataEncryptor.getNonce();
        SequenceOfRecipientInfo.Builder builder = SequenceOfRecipientInfo.builder();
        for (ETSIRecipientInfoBuilder build : this.recipientInfoBuilders) {
            builder.addRecipients(build.build(key));
        }
        return new ETSIEncryptedData(EncryptedData.builder().setRecipients(builder.createSequenceOfRecipientInfo()).setCiphertext(SymmetricCiphertext.aes128ccm(AesCcmCiphertext.builder().setCcmCiphertext(encrypt).setNonce(nonce).createAesCcmCiphertext())).createEncryptedData());
    }
}
