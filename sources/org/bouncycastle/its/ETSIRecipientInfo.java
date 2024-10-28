package org.bouncycastle.its;

import org.bouncycastle.its.operator.ETSIDataDecryptor;
import org.bouncycastle.oer.its.ieee1609dot2.AesCcmCiphertext;
import org.bouncycastle.oer.its.ieee1609dot2.EncryptedData;
import org.bouncycastle.oer.its.ieee1609dot2.PKRecipientInfo;
import org.bouncycastle.oer.its.ieee1609dot2.RecipientInfo;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EciesP256EncryptedKey;
import org.bouncycastle.util.Arrays;

public class ETSIRecipientInfo {
    private final EncryptedData encryptedData;
    private final RecipientInfo recipientInfo;

    public ETSIRecipientInfo(EncryptedData encryptedData2, RecipientInfo recipientInfo2) {
        this.recipientInfo = recipientInfo2;
        this.encryptedData = encryptedData2;
    }

    public ETSIRecipientInfo(RecipientInfo recipientInfo2) {
        this.recipientInfo = recipientInfo2;
        this.encryptedData = null;
    }

    public byte[] getContent(ETSIDataDecryptor eTSIDataDecryptor) {
        if (this.encryptedData.getCiphertext().getChoice() == 0) {
            AesCcmCiphertext instance = AesCcmCiphertext.getInstance(this.encryptedData.getCiphertext().getSymmetricCiphertext());
            EciesP256EncryptedKey instance2 = EciesP256EncryptedKey.getInstance(PKRecipientInfo.getInstance(this.recipientInfo.getRecipientInfo()).getEncKey().getEncryptedDataEncryptionKey());
            return eTSIDataDecryptor.decrypt(Arrays.concatenate(EccP256CurvePoint.getInstance(instance2.getV()).getEncodedPoint(), instance2.getC().getOctets(), instance2.getT().getOctets()), instance.getCcmCiphertext().getContent(), instance.getNonce().getOctets());
        }
        throw new IllegalArgumentException("Encrypted data is no AES 128 CCM");
    }

    public EncryptedData getEncryptedData() {
        return this.encryptedData;
    }

    public RecipientInfo getRecipientInfo() {
        return this.recipientInfo;
    }
}
