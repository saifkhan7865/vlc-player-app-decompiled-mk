package org.bouncycastle.cms.jcajce;

import com.google.android.material.internal.ViewUtils;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.CMSORIforKEMOtherInfo;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.KEMKeyWrapper;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.pqc.jcajce.interfaces.KyberPublicKey;
import org.bouncycastle.pqc.jcajce.interfaces.NTRUKey;
import org.bouncycastle.pqc.jcajce.spec.KyberParameterSpec;
import org.bouncycastle.pqc.jcajce.spec.NTRUParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

class JceCMSKEMKeyWrapper extends KEMKeyWrapper {
    private static Map encLengths;
    private byte[] encapsulation;
    private Map extraMappings;
    private JcaJceExtHelper helper;
    private AlgorithmIdentifier kdfAlgorithm;
    private final int kekLength;
    private PublicKey publicKey;
    private SecureRandom random;
    private final AlgorithmIdentifier symWrapAlgorithm;

    static {
        HashMap hashMap = new HashMap();
        encLengths = hashMap;
        hashMap.put(KyberParameterSpec.kyber512.getName(), Integers.valueOf(ViewUtils.EDGE_TO_EDGE_FLAGS));
        encLengths.put(KyberParameterSpec.kyber768.getName(), Integers.valueOf(1088));
        encLengths.put(KyberParameterSpec.kyber1024.getName(), Integers.valueOf(1568));
        encLengths.put(NTRUParameterSpec.ntruhps2048509.getName(), Integers.valueOf(699));
        encLengths.put(NTRUParameterSpec.ntruhps2048677.getName(), Integers.valueOf(930));
        encLengths.put(NTRUParameterSpec.ntruhps4096821.getName(), Integers.valueOf(1230));
        encLengths.put(NTRUParameterSpec.ntruhrss701.getName(), Integers.valueOf(1138));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JceCMSKEMKeyWrapper(PublicKey publicKey2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        super(publicKey2 instanceof RSAPublicKey ? new AlgorithmIdentifier(ISOIECObjectIdentifiers.id_kem_rsa) : SubjectPublicKeyInfo.getInstance(publicKey2.getEncoded()).getAlgorithm());
        this.helper = new DefaultJcaJceExtHelper();
        this.extraMappings = new HashMap();
        this.kdfAlgorithm = new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE));
        this.publicKey = publicKey2;
        this.symWrapAlgorithm = new AlgorithmIdentifier(aSN1ObjectIdentifier);
        this.kekLength = CMSUtils.getKekSize(aSN1ObjectIdentifier);
    }

    private int getKemEncLength(PublicKey publicKey2) {
        Map map;
        String name;
        if (publicKey2 instanceof KyberPublicKey) {
            map = encLengths;
            name = ((KyberPublicKey) publicKey2).getParameterSpec().getName();
        } else if (!(publicKey2 instanceof NTRUKey)) {
            return 0;
        } else {
            map = encLengths;
            name = ((NTRUKey) publicKey2).getParameterSpec().getName();
        }
        return ((Integer) map.get(name)).intValue();
    }

    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        try {
            byte[] encoded = new CMSORIforKEMOtherInfo(this.symWrapAlgorithm, this.kekLength).getEncoded();
            if (this.publicKey instanceof RSAPublicKey) {
                Cipher createAsymmetricWrapper = CMSUtils.createAsymmetricWrapper(this.helper, getAlgorithmIdentifier().getAlgorithm(), new HashMap());
                createAsymmetricWrapper.init(3, this.publicKey, new KTSParameterSpec.Builder(CMSUtils.getWrapAlgorithmName(this.symWrapAlgorithm.getAlgorithm()), this.kekLength * 8, encoded).withKdfAlgorithm(this.kdfAlgorithm).build(), this.random);
                byte[] wrap = createAsymmetricWrapper.wrap(CMSUtils.getJceKey(genericKey));
                int bitLength = (((RSAPublicKey) this.publicKey).getModulus().bitLength() + 7) / 8;
                this.encapsulation = Arrays.copyOfRange(wrap, 0, bitLength);
                return Arrays.copyOfRange(wrap, bitLength, wrap.length);
            }
            Cipher createAsymmetricWrapper2 = CMSUtils.createAsymmetricWrapper(this.helper, getAlgorithmIdentifier().getAlgorithm(), new HashMap());
            createAsymmetricWrapper2.init(3, this.publicKey, new KTSParameterSpec.Builder(CMSUtils.getWrapAlgorithmName(this.symWrapAlgorithm.getAlgorithm()), this.kekLength * 8, encoded).withKdfAlgorithm(this.kdfAlgorithm).build(), this.random);
            byte[] wrap2 = createAsymmetricWrapper2.wrap(CMSUtils.getJceKey(genericKey));
            int kemEncLength = getKemEncLength(this.publicKey);
            this.encapsulation = Arrays.copyOfRange(wrap2, 0, kemEncLength);
            return Arrays.copyOfRange(wrap2, kemEncLength, wrap2.length);
        } catch (Exception e) {
            throw new OperatorException("Unable to wrap contents key: " + e.getMessage(), e);
        } catch (Exception e2) {
            throw new OperatorException("Unable to wrap contents key: " + e2.getMessage(), e2);
        } catch (Exception e3) {
            throw new OperatorException("unable to wrap contents key: " + e3.getMessage(), e3);
        }
    }

    public byte[] getEncapsulation() {
        return this.encapsulation;
    }

    public AlgorithmIdentifier getKdfAlgorithmIdentifier() {
        return this.kdfAlgorithm;
    }

    public int getKekLength() {
        return this.kekLength;
    }

    public AlgorithmIdentifier getWrapAlgorithmIdentifier() {
        return this.symWrapAlgorithm;
    }

    public JceCMSKEMKeyWrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceCMSKEMKeyWrapper setKDF(AlgorithmIdentifier algorithmIdentifier) {
        this.kdfAlgorithm = algorithmIdentifier;
        return this;
    }

    public JceCMSKEMKeyWrapper setProvider(String str) {
        this.helper = new NamedJcaJceExtHelper(str);
        return this;
    }

    public JceCMSKEMKeyWrapper setProvider(Provider provider) {
        this.helper = new ProviderJcaJceExtHelper(provider);
        return this;
    }

    public JceCMSKEMKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
