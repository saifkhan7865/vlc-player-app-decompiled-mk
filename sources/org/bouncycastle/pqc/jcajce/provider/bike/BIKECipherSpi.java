package org.bouncycastle.pqc.jcajce.provider.bike;

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
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jcajce.spec.KEMParameterSpec;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.pqc.crypto.bike.BIKEKEMExtractor;
import org.bouncycastle.pqc.crypto.bike.BIKEKEMGenerator;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.jcajce.provider.util.WrapUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;
import org.bouncycastle.util.Strings;
import org.fusesource.jansi.AnsiRenderer;

class BIKECipherSpi extends CipherSpi {
    private final String algorithmName;
    private BIKEParameters bikeParameters;
    private AlgorithmParameters engineParams;
    private BIKEKEMGenerator kemGen;
    private KTSParameterSpec kemParameterSpec;
    private BCBIKEPrivateKey unwrapKey;
    private BCBIKEPublicKey wrapKey;

    public static class BIKE128 extends BIKECipherSpi {
        public BIKE128() {
            super(BIKEParameters.bike128);
        }
    }

    public static class BIKE192 extends BIKECipherSpi {
        public BIKE192() {
            super(BIKEParameters.bike192);
        }
    }

    public static class BIKE256 extends BIKECipherSpi {
        public BIKE256() {
            super(BIKEParameters.bike256);
        }
    }

    public static class Base extends BIKECipherSpi {
        public Base() throws NoSuchAlgorithmException {
            super("BIKE");
        }
    }

    BIKECipherSpi(String str) throws NoSuchAlgorithmException {
        this.bikeParameters = null;
        this.algorithmName = str;
    }

    BIKECipherSpi(BIKEParameters bIKEParameters) {
        this.bikeParameters = bIKEParameters;
        this.algorithmName = Strings.toUpperCase(bIKEParameters.getName());
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
            if (key instanceof BCBIKEPublicKey) {
                this.wrapKey = (BCBIKEPublicKey) key;
                this.kemGen = new BIKEKEMGenerator(CryptoServicesRegistrar.getSecureRandom(secureRandom));
            } else {
                throw new InvalidKeyException("Only a " + this.algorithmName + " public key can be used for wrapping");
            }
        } else if (i != 4) {
            throw new InvalidParameterException("Cipher only valid for wrapping/unwrapping");
        } else if (key instanceof BCBIKEPrivateKey) {
            this.unwrapKey = (BCBIKEPrivateKey) key;
        } else {
            throw new InvalidKeyException("Only a " + this.algorithmName + " private key can be used for unwrapping");
        }
        BIKEParameters bIKEParameters = this.bikeParameters;
        if (bIKEParameters != null) {
            String upperCase = Strings.toUpperCase(bIKEParameters.getName());
            if (!upperCase.equals(key.getAlgorithm())) {
                throw new InvalidKeyException("cipher locked to " + upperCase + AnsiRenderer.CODE_TEXT_SEPARATOR + key.getAlgorithm());
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
            try {
                BIKEKEMExtractor bIKEKEMExtractor = new BIKEKEMExtractor(this.unwrapKey.getKeyParams());
                byte[] extractSecret = bIKEKEMExtractor.extractSecret(Arrays.copyOfRange(bArr, 0, bIKEKEMExtractor.getEncapsulationLength()));
                Wrapper wrapper = WrapUtil.getWrapper(this.kemParameterSpec.getKeyAlgorithmName());
                KeyParameter keyParameter = new KeyParameter(extractSecret);
                Arrays.clear(extractSecret);
                wrapper.init(false, keyParameter);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, bIKEKEMExtractor.getEncapsulationLength(), bArr.length);
                SecretKeySpec secretKeySpec = new SecretKeySpec(wrapper.unwrap(copyOfRange, 0, copyOfRange.length), str);
                Arrays.clear(keyParameter.getKey());
                return secretKeySpec;
            } catch (IllegalArgumentException e) {
                throw new NoSuchAlgorithmException("unable to extract KTS secret: " + e.getMessage());
            } catch (InvalidCipherTextException e2) {
                throw new InvalidKeyException("unable to extract KTS secret: " + e2.getMessage());
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
            try {
                SecretWithEncapsulation generateEncapsulated = this.kemGen.generateEncapsulated(this.wrapKey.getKeyParams());
                Wrapper wrapper = WrapUtil.getWrapper(this.kemParameterSpec.getKeyAlgorithmName());
                wrapper.init(true, new KeyParameter(generateEncapsulated.getSecret()));
                byte[] encapsulation = generateEncapsulated.getEncapsulation();
                generateEncapsulated.destroy();
                byte[] encoded = key.getEncoded();
                byte[] concatenate = Arrays.concatenate(encapsulation, wrapper.wrap(encoded, 0, encoded.length));
                Arrays.clear(encoded);
                return concatenate;
            } catch (IllegalArgumentException e) {
                throw new IllegalBlockSizeException("unable to generate KTS secret: " + e.getMessage());
            } catch (DestroyFailedException e2) {
                throw new IllegalBlockSizeException("unable to destroy interim values: " + e2.getMessage());
            }
        } else {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
    }
}
