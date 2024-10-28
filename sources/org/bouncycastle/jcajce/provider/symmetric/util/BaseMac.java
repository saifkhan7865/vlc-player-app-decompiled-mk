package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.MacSpi;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.RC2Parameters;
import org.bouncycastle.crypto.params.SkeinParameters;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.jcajce.spec.SkeinParameterSpec;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public class BaseMac extends MacSpi implements PBE {
    private int keySize;
    private Mac macEngine;
    private int pbeHash;
    private int scheme;

    protected BaseMac(Mac mac) {
        this.scheme = 2;
        this.pbeHash = 1;
        this.keySize = 160;
        this.macEngine = mac;
    }

    protected BaseMac(Mac mac, int i, int i2, int i3) {
        this.macEngine = mac;
        this.scheme = i;
        this.pbeHash = i2;
        this.keySize = i3;
    }

    private static Hashtable copyMap(Map map) {
        Hashtable hashtable = new Hashtable();
        for (Object next : map.keySet()) {
            hashtable.put(next, map.get(next));
        }
        return hashtable;
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal() {
        byte[] bArr = new byte[engineGetMacLength()];
        this.macEngine.doFinal(bArr, 0);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public int engineGetMacLength() {
        return this.macEngine.getMacSize();
    }

    /* access modifiers changed from: protected */
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters cipherParameters;
        CipherParameters cipherParameters2;
        int i;
        if (key != null) {
            if (key instanceof PKCS12Key) {
                try {
                    SecretKey secretKey = (SecretKey) key;
                    try {
                        PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
                        if ((secretKey instanceof PBEKey) && pBEParameterSpec == null) {
                            PBEKey pBEKey = (PBEKey) secretKey;
                            pBEParameterSpec = new PBEParameterSpec(pBEKey.getSalt(), pBEKey.getIterationCount());
                        }
                        int i2 = 256;
                        if (this.macEngine.getAlgorithmName().startsWith("GOST")) {
                            i = 6;
                        } else {
                            Mac mac = this.macEngine;
                            if (!(mac instanceof HMac) || mac.getAlgorithmName().startsWith(McElieceCCA2KeyGenParameterSpec.SHA1)) {
                                i = 1;
                            } else if (this.macEngine.getAlgorithmName().startsWith(McElieceCCA2KeyGenParameterSpec.SHA224)) {
                                i = 7;
                                i2 = BERTags.FLAGS;
                            } else if (this.macEngine.getAlgorithmName().startsWith("SHA-256")) {
                                i = 4;
                            } else if (this.macEngine.getAlgorithmName().startsWith(McElieceCCA2KeyGenParameterSpec.SHA384)) {
                                i = 8;
                                i2 = KyberEngine.KyberPolyBytes;
                            } else if (this.macEngine.getAlgorithmName().startsWith("SHA-512")) {
                                i = 9;
                                i2 = 512;
                            } else if (this.macEngine.getAlgorithmName().startsWith("RIPEMD160")) {
                                i = 2;
                            } else {
                                throw new InvalidAlgorithmParameterException("no PKCS12 mapping for HMAC: " + this.macEngine.getAlgorithmName());
                            }
                            i2 = 160;
                        }
                        cipherParameters = PBE.Util.makePBEMacParameters(secretKey, 2, i, i2, pBEParameterSpec);
                    } catch (Exception unused) {
                        throw new InvalidAlgorithmParameterException("PKCS12 requires a PBEParameterSpec");
                    }
                } catch (Exception unused2) {
                    throw new InvalidKeyException("PKCS12 requires a SecretKey/PBEKey");
                }
            } else if (key instanceof BCPBEKey) {
                BCPBEKey bCPBEKey = (BCPBEKey) key;
                if (bCPBEKey.getParam() != null) {
                    cipherParameters = bCPBEKey.getParam();
                } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    cipherParameters = PBE.Util.makePBEMacParameters(bCPBEKey, algorithmParameterSpec);
                } else {
                    throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                }
            } else if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                cipherParameters = new KeyParameter(key.getEncoded());
            } else {
                throw new InvalidAlgorithmParameterException("inappropriate parameter type: " + algorithmParameterSpec.getClass().getName());
            }
            KeyParameter keyParameter = cipherParameters instanceof ParametersWithIV ? (KeyParameter) ((ParametersWithIV) cipherParameters).getParameters() : (KeyParameter) cipherParameters;
            if (algorithmParameterSpec instanceof AEADParameterSpec) {
                AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                cipherParameters2 = new AEADParameters(keyParameter, aEADParameterSpec.getMacSizeInBits(), aEADParameterSpec.getNonce(), aEADParameterSpec.getAssociatedData());
            } else if (algorithmParameterSpec instanceof IvParameterSpec) {
                cipherParameters2 = new ParametersWithIV(keyParameter, ((IvParameterSpec) algorithmParameterSpec).getIV());
            } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
                RC2ParameterSpec rC2ParameterSpec = (RC2ParameterSpec) algorithmParameterSpec;
                cipherParameters2 = new ParametersWithIV(new RC2Parameters(keyParameter.getKey(), rC2ParameterSpec.getEffectiveKeyBits()), rC2ParameterSpec.getIV());
            } else if (algorithmParameterSpec instanceof SkeinParameterSpec) {
                cipherParameters2 = new SkeinParameters.Builder(copyMap(((SkeinParameterSpec) algorithmParameterSpec).getParameters())).setKey(keyParameter.getKey()).build();
            } else if (algorithmParameterSpec == null) {
                cipherParameters2 = new KeyParameter(key.getEncoded());
            } else if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                cipherParameters2 = GcmSpecUtil.extractAeadParameters(keyParameter, algorithmParameterSpec);
            } else {
                cipherParameters2 = cipherParameters;
                if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                    throw new InvalidAlgorithmParameterException("unknown parameter type: " + algorithmParameterSpec.getClass().getName());
                }
            }
            try {
                this.macEngine.init(cipherParameters2);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot initialize MAC: " + e.getMessage());
            }
        } else {
            throw new InvalidKeyException("key is null");
        }
    }

    /* access modifiers changed from: protected */
    public void engineReset() {
        this.macEngine.reset();
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte b) {
        this.macEngine.update(b);
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte[] bArr, int i, int i2) {
        this.macEngine.update(bArr, i, i2);
    }
}
