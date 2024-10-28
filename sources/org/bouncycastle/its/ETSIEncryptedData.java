package org.bouncycastle.its;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.bouncycastle.oer.Element;
import org.bouncycastle.oer.OEREncoder;
import org.bouncycastle.oer.OERInputStream;
import org.bouncycastle.oer.its.etsi103097.EtsiTs103097DataEncrypted;
import org.bouncycastle.oer.its.ieee1609dot2.EncryptedData;
import org.bouncycastle.oer.its.ieee1609dot2.Ieee1609Dot2Content;
import org.bouncycastle.oer.its.ieee1609dot2.RecipientInfo;
import org.bouncycastle.oer.its.template.etsi103097.EtsiTs103097Module;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;

public class ETSIEncryptedData {
    private static final Element oerDef = EtsiTs103097Module.EtsiTs103097Data_Encrypted.build();
    private final EncryptedData encryptedData;

    public ETSIEncryptedData(InputStream inputStream) throws IOException {
        Ieee1609Dot2Content content = EtsiTs103097DataEncrypted.getInstance((inputStream instanceof OERInputStream ? (OERInputStream) inputStream : new OERInputStream(inputStream)).parse(oerDef)).getContent();
        if (content.getChoice() == 2) {
            this.encryptedData = EncryptedData.getInstance(content.getIeee1609Dot2Content());
            return;
        }
        throw new IllegalStateException("EtsiTs103097Data-Encrypted did not have encrypted data content");
    }

    ETSIEncryptedData(EncryptedData encryptedData2) {
        this.encryptedData = encryptedData2;
    }

    public ETSIEncryptedData(byte[] bArr) throws IOException {
        this((InputStream) new ByteArrayInputStream(bArr));
    }

    public byte[] getEncoded() {
        return OEREncoder.toByteArray(new EtsiTs103097DataEncrypted(Ieee1609Dot2Content.encryptedData(this.encryptedData)), oerDef);
    }

    public EncryptedData getEncryptedData() {
        return this.encryptedData;
    }

    public Store<ETSIRecipientInfo> getRecipients() {
        ArrayList arrayList = new ArrayList();
        for (RecipientInfo eTSIRecipientInfo : this.encryptedData.getRecipients().getRecipientInfos()) {
            arrayList.add(new ETSIRecipientInfo(this.encryptedData, eTSIRecipientInfo));
        }
        return new CollectionStore(arrayList);
    }
}
