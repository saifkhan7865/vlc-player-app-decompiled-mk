package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSORIforKEMOtherInfo;
import org.bouncycastle.asn1.cms.KEMRecipientInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceGenericKey;
import org.bouncycastle.util.Arrays;

class JceCMSKEMKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private Map extraMappings = new HashMap();
    private JcaJceExtHelper helper = new DefaultJcaJceExtHelper();
    private final int kekLength;
    private PrivateKey privateKey;
    private final AlgorithmIdentifier symWrapAlgorithm;

    public JceCMSKEMKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey2) {
        super(PrivateKeyInfo.getInstance(privateKey2.getEncoded()).getPrivateKeyAlgorithm());
        KEMRecipientInfo instance = KEMRecipientInfo.getInstance(algorithmIdentifier.getParameters());
        this.privateKey = privateKey2;
        this.symWrapAlgorithm = algorithmIdentifier;
        this.kekLength = CMSUtils.getKekSize(instance.getWrap().getAlgorithm());
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        KEMRecipientInfo instance = KEMRecipientInfo.getInstance(this.symWrapAlgorithm.getParameters());
        AlgorithmIdentifier wrap = instance.getWrap();
        try {
            byte[] encoded = new CMSORIforKEMOtherInfo(wrap, this.kekLength, instance.getUkm()).getEncoded();
            if (this.privateKey instanceof RSAPrivateKey) {
                Cipher createAsymmetricWrapper = CMSUtils.createAsymmetricWrapper(this.helper, instance.getKem().getAlgorithm(), new HashMap());
                String wrapAlgorithmName = CMSUtils.getWrapAlgorithmName(wrap.getAlgorithm());
                createAsymmetricWrapper.init(4, this.privateKey, new KTSParameterSpec.Builder(wrapAlgorithmName, this.kekLength * 8, encoded).withKdfAlgorithm(instance.getKdf()).build());
                return new JceGenericKey(algorithmIdentifier, createAsymmetricWrapper.unwrap(Arrays.concatenate(instance.getKemct().getOctets(), instance.getEncryptedKey().getOctets()), wrapAlgorithmName, 3));
            }
            Cipher createAsymmetricWrapper2 = CMSUtils.createAsymmetricWrapper(this.helper, instance.getKem().getAlgorithm(), new HashMap());
            String wrapAlgorithmName2 = CMSUtils.getWrapAlgorithmName(wrap.getAlgorithm());
            createAsymmetricWrapper2.init(4, this.privateKey, new KTSParameterSpec.Builder(wrapAlgorithmName2, this.kekLength * 8, encoded).withKdfAlgorithm(instance.getKdf()).build());
            return new JceGenericKey(algorithmIdentifier, createAsymmetricWrapper2.unwrap(Arrays.concatenate(instance.getKemct().getOctets(), instance.getEncryptedKey().getOctets()), wrapAlgorithmName2, 3));
        } catch (Exception e) {
            throw new OperatorException("Unable to wrap contents key: " + e.getMessage(), e);
        } catch (Exception e2) {
            throw new OperatorException("exception encrypting key: " + e2.getMessage(), e2);
        }
    }

    public int getKekLength() {
        return this.kekLength;
    }

    public JceCMSKEMKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceCMSKEMKeyUnwrapper setProvider(String str) {
        this.helper = new NamedJcaJceExtHelper(str);
        return this;
    }

    public JceCMSKEMKeyUnwrapper setProvider(Provider provider) {
        this.helper = new ProviderJcaJceExtHelper(provider);
        return this;
    }
}
