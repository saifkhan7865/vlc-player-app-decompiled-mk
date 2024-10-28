package org.bouncycastle.its;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.its.operator.ECDSAEncoder;
import org.bouncycastle.its.operator.ITSContentSigner;
import org.bouncycastle.oer.Element;
import org.bouncycastle.oer.OEREncoder;
import org.bouncycastle.oer.its.ieee1609dot2.Certificate;
import org.bouncycastle.oer.its.ieee1609dot2.HashedData;
import org.bouncycastle.oer.its.ieee1609dot2.HeaderInfo;
import org.bouncycastle.oer.its.ieee1609dot2.Ieee1609Dot2Content;
import org.bouncycastle.oer.its.ieee1609dot2.Ieee1609Dot2Data;
import org.bouncycastle.oer.its.ieee1609dot2.Opaque;
import org.bouncycastle.oer.its.ieee1609dot2.SequenceOfCertificate;
import org.bouncycastle.oer.its.ieee1609dot2.SignedData;
import org.bouncycastle.oer.its.ieee1609dot2.SignedDataPayload;
import org.bouncycastle.oer.its.ieee1609dot2.SignerIdentifier;
import org.bouncycastle.oer.its.ieee1609dot2.ToBeSignedData;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.HashedId8;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Psid;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Time64;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.UINT8;
import org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2;

public class ETSISignedDataBuilder {
    private static final Element def = IEEE1609dot2.ToBeSignedData.build();
    private Ieee1609Dot2Data data;
    private HashedData extDataHash;
    private final HeaderInfo headerInfo;

    private ETSISignedDataBuilder(HeaderInfo headerInfo2) {
        this.headerInfo = headerInfo2;
    }

    private ETSISignedDataBuilder(Psid psid) {
        this(HeaderInfo.builder().setPsid(psid).setGenerationTime(Time64.now()).createHeaderInfo());
    }

    public static ETSISignedDataBuilder builder(HeaderInfo headerInfo2) {
        return new ETSISignedDataBuilder(headerInfo2);
    }

    public static ETSISignedDataBuilder builder(Psid psid) {
        return new ETSISignedDataBuilder(psid);
    }

    private ToBeSignedData getToBeSignedData() {
        return ToBeSignedData.builder().setPayload(new SignedDataPayload(this.data, this.extDataHash)).setHeaderInfo(this.headerInfo).createToBeSignedData();
    }

    private static void write(OutputStream outputStream, byte[] bArr) {
        try {
            outputStream.write(bArr);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public ETSISignedData build(ITSContentSigner iTSContentSigner) {
        ToBeSignedData toBeSignedData = getToBeSignedData();
        write(iTSContentSigner.getOutputStream(), OEREncoder.toByteArray(toBeSignedData, def));
        return new ETSISignedData(SignedData.builder().setHashId(ITSAlgorithmUtils.getHashAlgorithm(iTSContentSigner.getDigestAlgorithm().getAlgorithm())).setTbsData(toBeSignedData).setSigner(SignerIdentifier.self()).setSignature(ECDSAEncoder.toITS(iTSContentSigner.getCurveID(), iTSContentSigner.getSignature())).createSignedData());
    }

    public ETSISignedData build(ITSContentSigner iTSContentSigner, List<ITSCertificate> list) {
        ToBeSignedData toBeSignedData = getToBeSignedData();
        write(iTSContentSigner.getOutputStream(), OEREncoder.toByteArray(toBeSignedData, def));
        ArrayList arrayList = new ArrayList();
        for (ITSCertificate aSN1Structure : list) {
            arrayList.add(Certificate.getInstance(aSN1Structure.toASN1Structure()));
        }
        return new ETSISignedData(SignedData.builder().setHashId(ITSAlgorithmUtils.getHashAlgorithm(iTSContentSigner.getDigestAlgorithm().getAlgorithm())).setTbsData(toBeSignedData).setSigner(SignerIdentifier.certificate(new SequenceOfCertificate((List<Certificate>) arrayList))).setSignature(ECDSAEncoder.toITS(iTSContentSigner.getCurveID(), iTSContentSigner.getSignature())).createSignedData());
    }

    public ETSISignedData build(ITSContentSigner iTSContentSigner, HashedId8 hashedId8) {
        ToBeSignedData toBeSignedData = getToBeSignedData();
        write(iTSContentSigner.getOutputStream(), OEREncoder.toByteArray(toBeSignedData, def));
        return new ETSISignedData(SignedData.builder().setHashId(ITSAlgorithmUtils.getHashAlgorithm(iTSContentSigner.getDigestAlgorithm().getAlgorithm())).setTbsData(toBeSignedData).setSigner(SignerIdentifier.digest(hashedId8)).setSignature(ECDSAEncoder.toITS(iTSContentSigner.getCurveID(), iTSContentSigner.getSignature())).createSignedData());
    }

    public ETSISignedDataBuilder setData(Ieee1609Dot2Content ieee1609Dot2Content) {
        this.data = Ieee1609Dot2Data.builder().setProtocolVersion(new UINT8(3)).setContent(ieee1609Dot2Content).createIeee1609Dot2Data();
        return this;
    }

    public ETSISignedDataBuilder setExtDataHash(HashedData hashedData) {
        this.extDataHash = hashedData;
        return this;
    }

    public ETSISignedDataBuilder setUnsecuredData(byte[] bArr) {
        this.data = Ieee1609Dot2Data.builder().setProtocolVersion(new UINT8(3)).setContent(Ieee1609Dot2Content.unsecuredData(new Opaque(bArr))).createEtsiTs103097Data();
        return this;
    }
}
