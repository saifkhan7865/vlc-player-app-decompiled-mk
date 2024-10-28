package org.bouncycastle.pqc.jcajce.provider.kyber;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.jcajce.spec.KEMParameterSpec;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMExtractor;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.jcajce.provider.util.WrapUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;
import org.bouncycastle.util.Strings;

class KyberCipherSpi extends CipherSpi {
    private final String algorithmName;
    private AlgorithmParameters engineParams;
    private KyberKEMGenerator kemGen;
    private KTSParameterSpec kemParameterSpec;
    private KyberParameters kyberParameters;
    private BCKyberPrivateKey unwrapKey;
    private BCKyberPublicKey wrapKey;

    public static class Base extends KyberCipherSpi {
        public Base() throws NoSuchAlgorithmException {
            super("KYBER");
        }
    }

    public static class Kyber1024 extends KyberCipherSpi {
        public Kyber1024() {
            super(KyberParameters.kyber1024);
        }
    }

    public static class Kyber512 extends KyberCipherSpi {
        public Kyber512() {
            super(KyberParameters.kyber512);
        }
    }

    public static class Kyber768 extends KyberCipherSpi {
        public Kyber768() {
            super(KyberParameters.kyber768);
        }
    }

    KyberCipherSpi(String str) {
        this.algorithmName = str;
        this.kyberParameters = null;
    }

    KyberCipherSpi(KyberParameters kyberParameters2) {
        this.kyberParameters = kyberParameters2;
        this.algorithmName = Strings.toUpperCase(kyberParameters2.getName());
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineGetKeySize(Key key) {
        return 2048;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int i) {
        return -1;
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            try {
                AlgorithmParameters instance = AlgorithmParameters.getInstance(this.algorithmName, "BCPQC");
                this.engineParams = instance;
                instance.init(this.kemParameterSpec);
            } catch (Exception e) {
                throw Exceptions.illegalStateException(e.toString(), e);
            }
        }
        return this.engineParams;
    }

    /* access modifiers changed from: protected */
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algorithmParameterSpec;
        if (algorithmParameters != null) {
            try {
                algorithmParameterSpec = algorithmParameters.getParameterSpec(KEMParameterSpec.class);
            } catch (Exception unused) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        } else {
            algorithmParameterSpec = null;
        }
        engineInit(i, key, algorithmParameterSpec, secureRandom);
    }

    /* access modifiers changed from: protected */
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            AlgorithmParameterSpec algorithmParameterSpec = null;
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw Exceptions.illegalArgumentException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        KTSParameterSpec kTSParameterSpec;
        if (algorithmParameterSpec == null) {
            kTSParameterSpec = new KEMParameterSpec("AES-KWP");
        } else if (algorithmParameterSpec instanceof KTSParameterSpec) {
            kTSParameterSpec = (KTSParameterSpec) algorithmParameterSpec;
        } else {
            throw new InvalidAlgorithmParameterException(this.algorithmName + " can only accept KTSParameterSpec");
        }
        this.kemParameterSpec = kTSParameterSpec;
        if (i == 3) {
            if (key instanceof BCKyberPublicKey) {
                this.wrapKey = (BCKyberPublicKey) key;
                this.kemGen = new KyberKEMGenerator(CryptoServicesRegistrar.getSecureRandom(secureRandom));
            } else {
                throw new InvalidKeyException("Only a " + this.algorithmName + " public key can be used for wrapping");
            }
        } else if (i != 4) {
            throw new InvalidParameterException("Cipher only valid for wrapping/unwrapping");
        } else if (key instanceof BCKyberPrivateKey) {
            this.unwrapKey = (BCKyberPrivateKey) key;
        } else {
            throw new InvalidKeyException("Only a " + this.algorithmName + " private key can be used for unwrapping");
        }
        KyberParameters kyberParameters2 = this.kyberParameters;
        if (kyberParameters2 != null) {
            String upperCase = Strings.toUpperCase(kyberParameters2.getName());
            if (!upperCase.equals(key.getAlgorithm())) {
                throw new InvalidKeyException("cipher locked to " + upperCase);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("Cannot support mode " + str);
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Padding " + str + " unknown");
    }

    /* access modifiers changed from: protected */
    public Key engineUnwrap(byte[] bArr, String str, int i) throws InvalidKeyException, NoSuchAlgorithmException {
        if (i == 3) {
            byte[] bArr2 = null;
            try {
                KyberKEMExtractor kyberKEMExtractor = new KyberKEMExtractor(this.unwrapKey.getKeyParams());
                bArr2 = kyberKEMExtractor.extractSecret(Arrays.copyOfRange(bArr, 0, kyberKEMExtractor.getEncapsulationLength()));
                Wrapper keyUnwrapper = WrapUtil.getKeyUnwrapper(this.kemParameterSpec, bArr2);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, kyberKEMExtractor.getEncapsulationLength(), bArr.length);
                SecretKeySpec secretKeySpec = new SecretKeySpec(keyUnwrapper.unwrap(copyOfRange, 0, copyOfRange.length), str);
                if (bArr2 != null) {
                    Arrays.clear(bArr2);
                }
                return secretKeySpec;
            } catch (IllegalArgumentException e) {
                throw new NoSuchAlgorithmException("unable to extract KTS secret: " + e.getMessage());
            } catch (InvalidCipherTextException e2) {
                throw new InvalidKeyException("unable to extract KTS secret: " + e2.getMessage());
            } catch (Throwable th) {
                if (bArr2 != null) {
                    Arrays.clear(bArr2);
                }
                throw th;
            }
        } else {
            throw new InvalidKeyException("only SECRET_KEY supported");
        }
    }

    /* access modifiers changed from: protected */
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    /* access modifiers changed from: protected */
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        if (key.getEncoded() != null) {
            SecretWithEncapsulation secretWithEncapsulation = null;
            try {
                secretWithEncapsulation = this.kemGen.generateEncapsulated(this.wrapKey.getKeyParams());
                Wrapper keyWrapper = WrapUtil.getKeyWrapper(this.kemParameterSpec, secretWithEncapsulation.getSecret());
                byte[] encapsulation = secretWithEncapsulation.getEncapsulation();
                byte[] encoded = key.getEncoded();
                byte[] concatenate = Arrays.concatenate(encapsulation, keyWrapper.wrap(encoded, 0, encoded.length));
                Arrays.clear(encoded);
                if (secretWithEncapsulation != null) {
                    try {
                        secretWithEncapsulation.destroy();
                    } catch (DestroyFailedException e) {
                        throw new IllegalBlockSizeException("unable to destroy interim values: " + e.getMessage());
                    }
                }
                return concatenate;
            } catch (IllegalArgumentException e2) {
                throw new IllegalBlockSizeException("unable to generate KTS secret: " + e2.getMessage());
            } catch (Throwable th) {
                if (secretWithEncapsulation != null) {
                    try {
                        secretWithEncapsulation.destroy();
                    } catch (DestroyFailedException e3) {
                        throw new IllegalBlockSizeException("unable to destroy interim values: " + e3.getMessage());
                    }
                }
                throw th;
            }
        } else {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
    }
}
