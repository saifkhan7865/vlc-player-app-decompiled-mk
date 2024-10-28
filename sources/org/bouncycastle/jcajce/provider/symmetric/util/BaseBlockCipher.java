package org.bouncycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DefaultBufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.fpe.FPEEngine;
import org.bouncycastle.crypto.fpe.FPEFF1Engine;
import org.bouncycastle.crypto.fpe.FPEFF3_1Engine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.CTSBlockCipher;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.modes.GCFBBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.GCMSIVBlockCipher;
import org.bouncycastle.crypto.modes.GOFBBlockCipher;
import org.bouncycastle.crypto.modes.KCCMBlockCipher;
import org.bouncycastle.crypto.modes.KCTRBlockCipher;
import org.bouncycastle.crypto.modes.KGCMBlockCipher;
import org.bouncycastle.crypto.modes.OCBBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.bouncycastle.crypto.modes.PGPCFBBlockCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.ISO10126d2Padding;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.TBCPadding;
import org.bouncycastle.crypto.paddings.X923Padding;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.internal.asn1.cms.GCMParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class BaseBlockCipher extends BaseWrapCipher implements PBE {
    private static final int BUF_SIZE = 512;
    private static final Class[] availableSpecs = {RC2ParameterSpec.class, RC5ParameterSpec.class, GcmSpecUtil.gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
    private AEADParameters aeadParams;
    private BlockCipher baseEngine;
    private GenericBlockCipher cipher;
    private int digest;
    private BlockCipherProvider engineProvider;
    private boolean fixedIv;
    private int ivLength;
    private ParametersWithIV ivParam;
    private int keySizeInBits;
    private String modeName;
    private boolean padded;
    private String pbeAlgorithm;
    private PBEParameterSpec pbeSpec;
    private int scheme;

    private static class AEADGenericBlockCipher implements GenericBlockCipher {
        private static final Constructor aeadBadTagConstructor;
        /* access modifiers changed from: private */
        public AEADCipher cipher;

        static {
            Class loadClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.AEADBadTagException");
            aeadBadTagConstructor = loadClass != null ? findExceptionConstructor(loadClass) : null;
        }

        AEADGenericBlockCipher(AEADCipher aEADCipher) {
            this.cipher = aEADCipher;
        }

        private static Constructor findExceptionConstructor(Class cls) {
            try {
                return cls.getConstructor(new Class[]{String.class});
            } catch (Exception unused) {
                return null;
            }
        }

        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            BadPaddingException badPaddingException;
            try {
                return this.cipher.doFinal(bArr, i);
            } catch (InvalidCipherTextException e) {
                Constructor constructor = aeadBadTagConstructor;
                if (constructor != null) {
                    try {
                        badPaddingException = (BadPaddingException) constructor.newInstance(new Object[]{e.getMessage()});
                    } catch (Exception unused) {
                        badPaddingException = null;
                    }
                    if (badPaddingException != null) {
                        throw badPaddingException;
                    }
                }
                throw new BadPaddingException(e.getMessage());
            }
        }

        public String getAlgorithmName() {
            AEADCipher aEADCipher = this.cipher;
            return aEADCipher instanceof AEADBlockCipher ? ((AEADBlockCipher) aEADCipher).getUnderlyingCipher().getAlgorithmName() : aEADCipher.getAlgorithmName();
        }

        public int getOutputSize(int i) {
            return this.cipher.getOutputSize(i);
        }

        public BlockCipher getUnderlyingCipher() {
            AEADCipher aEADCipher = this.cipher;
            if (aEADCipher instanceof AEADBlockCipher) {
                return ((AEADBlockCipher) aEADCipher).getUnderlyingCipher();
            }
            return null;
        }

        public int getUpdateOutputSize(int i) {
            return this.cipher.getUpdateOutputSize(i);
        }

        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(z, cipherParameters);
        }

        public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
            return this.cipher.processByte(b, bArr, i);
        }

        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            return this.cipher.processBytes(bArr, i, i2, bArr2, i3);
        }

        public void updateAAD(byte[] bArr, int i, int i2) {
            this.cipher.processAADBytes(bArr, i, i2);
        }

        public boolean wrapOnNoPadding() {
            return false;
        }
    }

    private static class BufferedFPEBlockCipher implements GenericBlockCipher {
        private FPEEngine cipher;
        private BaseWrapCipher.ErasableOutputStream eOut = new BaseWrapCipher.ErasableOutputStream();

        BufferedFPEBlockCipher(FPEEngine fPEEngine) {
            this.cipher = fPEEngine;
        }

        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.processBlock(this.eOut.getBuf(), 0, this.eOut.size(), bArr, i);
            } finally {
                this.eOut.erase();
            }
        }

        public String getAlgorithmName() {
            return this.cipher.getAlgorithmName();
        }

        public int getOutputSize(int i) {
            return this.eOut.size() + i;
        }

        public BlockCipher getUnderlyingCipher() {
            throw new IllegalStateException("not applicable for FPE");
        }

        public int getUpdateOutputSize(int i) {
            return 0;
        }

        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(z, cipherParameters);
        }

        public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
            this.eOut.write(b);
            return 0;
        }

        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            this.eOut.write(bArr, i, i2);
            return 0;
        }

        public void updateAAD(byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        public boolean wrapOnNoPadding() {
            return false;
        }
    }

    private static class BufferedGenericBlockCipher implements GenericBlockCipher {
        private BufferedBlockCipher cipher;

        BufferedGenericBlockCipher(BlockCipher blockCipher) {
            this(blockCipher, new PKCS7Padding());
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher, blockCipherPadding);
        }

        BufferedGenericBlockCipher(BufferedBlockCipher bufferedBlockCipher) {
            this.cipher = bufferedBlockCipher;
        }

        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.doFinal(bArr, i);
            } catch (InvalidCipherTextException e) {
                throw new BadPaddingException(e.getMessage());
            }
        }

        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public int getOutputSize(int i) {
            return this.cipher.getOutputSize(i);
        }

        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        public int getUpdateOutputSize(int i) {
            return this.cipher.getUpdateOutputSize(i);
        }

        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(z, cipherParameters);
        }

        public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
            return this.cipher.processByte(b, bArr, i);
        }

        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            return this.cipher.processBytes(bArr, i, i2, bArr2, i3);
        }

        public void updateAAD(byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        public boolean wrapOnNoPadding() {
            return !(this.cipher instanceof CTSBlockCipher);
        }
    }

    private interface GenericBlockCipher {
        int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException;

        String getAlgorithmName();

        int getOutputSize(int i);

        BlockCipher getUnderlyingCipher();

        int getUpdateOutputSize(int i);

        void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

        int processByte(byte b, byte[] bArr, int i) throws DataLengthException;

        int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;

        void updateAAD(byte[] bArr, int i, int i2);

        boolean wrapOnNoPadding();
    }

    protected BaseBlockCipher(BlockCipher blockCipher) {
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int i) {
        this(blockCipher, true, i);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int i, int i2, int i3, int i4) {
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.scheme = i;
        this.digest = i2;
        this.keySizeInBits = i3;
        this.ivLength = i4;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, boolean z, int i) {
        this.scheme = -1;
        this.ivLength = 0;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.fixedIv = z;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
        this.ivLength = i / 8;
    }

    protected BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, int i) {
        this(bufferedBlockCipher, true, i);
    }

    protected BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, boolean z, int i) {
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = bufferedBlockCipher.getUnderlyingCipher();
        this.cipher = new BufferedGenericBlockCipher(bufferedBlockCipher);
        this.fixedIv = z;
        this.ivLength = i / 8;
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher) {
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = aEADBlockCipher.getUnderlyingCipher();
        this.ivLength = aEADBlockCipher.getAlgorithmName().indexOf("GCM") >= 0 ? 12 : this.baseEngine.getBlockSize();
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher, boolean z, int i) {
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = aEADBlockCipher.getUnderlyingCipher();
        this.fixedIv = z;
        this.ivLength = i;
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(AEADCipher aEADCipher, boolean z, int i) {
        this.scheme = -1;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = null;
        this.fixedIv = z;
        this.ivLength = i;
        this.cipher = new AEADGenericBlockCipher(aEADCipher);
    }

    protected BaseBlockCipher(BlockCipherProvider blockCipherProvider) {
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipherProvider.get();
        this.engineProvider = blockCipherProvider;
        this.cipher = new BufferedGenericBlockCipher(blockCipherProvider.get());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: org.bouncycastle.crypto.params.ParametersWithSBox} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: org.bouncycastle.crypto.params.ParametersWithIV} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: org.bouncycastle.crypto.params.ParametersWithSBox} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: org.bouncycastle.crypto.params.ParametersWithSBox} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: org.bouncycastle.crypto.params.ParametersWithSBox} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.bouncycastle.crypto.CipherParameters adjustParameters(java.security.spec.AlgorithmParameterSpec r4, org.bouncycastle.crypto.CipherParameters r5) {
        /*
            r3 = this;
            boolean r0 = r5 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r0 == 0) goto L_0x0042
            r0 = r5
            org.bouncycastle.crypto.params.ParametersWithIV r0 = (org.bouncycastle.crypto.params.ParametersWithIV) r0
            org.bouncycastle.crypto.CipherParameters r0 = r0.getParameters()
            boolean r1 = r4 instanceof javax.crypto.spec.IvParameterSpec
            if (r1 == 0) goto L_0x001d
            javax.crypto.spec.IvParameterSpec r4 = (javax.crypto.spec.IvParameterSpec) r4
            org.bouncycastle.crypto.params.ParametersWithIV r5 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r4 = r4.getIV()
            r5.<init>(r0, r4)
        L_0x001a:
            r3.ivParam = r5
            goto L_0x0077
        L_0x001d:
            boolean r1 = r4 instanceof org.bouncycastle.jcajce.spec.GOST28147ParameterSpec
            if (r1 == 0) goto L_0x0077
            org.bouncycastle.jcajce.spec.GOST28147ParameterSpec r4 = (org.bouncycastle.jcajce.spec.GOST28147ParameterSpec) r4
            org.bouncycastle.crypto.params.ParametersWithSBox r1 = new org.bouncycastle.crypto.params.ParametersWithSBox
            byte[] r2 = r4.getSBox()
            r1.<init>(r5, r2)
            byte[] r5 = r4.getIV()
            if (r5 == 0) goto L_0x0040
            int r5 = r3.ivLength
            if (r5 == 0) goto L_0x0040
            org.bouncycastle.crypto.params.ParametersWithIV r5 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r4 = r4.getIV()
            r5.<init>(r0, r4)
            goto L_0x001a
        L_0x0040:
            r5 = r1
            goto L_0x0077
        L_0x0042:
            boolean r0 = r4 instanceof javax.crypto.spec.IvParameterSpec
            if (r0 == 0) goto L_0x0055
            javax.crypto.spec.IvParameterSpec r4 = (javax.crypto.spec.IvParameterSpec) r4
            org.bouncycastle.crypto.params.ParametersWithIV r0 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r4 = r4.getIV()
            r0.<init>(r5, r4)
            r3.ivParam = r0
        L_0x0053:
            r5 = r0
            goto L_0x0077
        L_0x0055:
            boolean r0 = r4 instanceof org.bouncycastle.jcajce.spec.GOST28147ParameterSpec
            if (r0 == 0) goto L_0x0077
            org.bouncycastle.jcajce.spec.GOST28147ParameterSpec r4 = (org.bouncycastle.jcajce.spec.GOST28147ParameterSpec) r4
            org.bouncycastle.crypto.params.ParametersWithSBox r0 = new org.bouncycastle.crypto.params.ParametersWithSBox
            byte[] r1 = r4.getSBox()
            r0.<init>(r5, r1)
            byte[] r5 = r4.getIV()
            if (r5 == 0) goto L_0x0053
            int r5 = r3.ivLength
            if (r5 == 0) goto L_0x0053
            org.bouncycastle.crypto.params.ParametersWithIV r5 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r4 = r4.getIV()
            r5.<init>(r0, r4)
        L_0x0077:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.adjustParameters(java.security.spec.AlgorithmParameterSpec, org.bouncycastle.crypto.CipherParameters):org.bouncycastle.crypto.CipherParameters");
    }

    private boolean isAEADModeName(String str) {
        return "CCM".equals(str) || "EAX".equals(str) || "GCM".equals(str) || "GCM-SIV".equals(str) || "OCB".equals(str);
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        int i4;
        if (engineGetOutputSize(i2) + i3 <= bArr2.length) {
            if (i2 != 0) {
                try {
                    i4 = this.cipher.processBytes(bArr, i, i2, bArr2, i3);
                } catch (OutputLengthException e) {
                    throw new IllegalBlockSizeException(e.getMessage());
                } catch (DataLengthException e2) {
                    throw new IllegalBlockSizeException(e2.getMessage());
                }
            } else {
                i4 = 0;
            }
            return i4 + this.cipher.doFinal(bArr2, i3 + i4);
        }
        throw new ShortBufferException("output buffer too short for input.");
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        int engineGetOutputSize = engineGetOutputSize(i2);
        byte[] bArr2 = new byte[engineGetOutputSize];
        int processBytes = i2 != 0 ? this.cipher.processBytes(bArr, i, i2, bArr2, 0) : 0;
        try {
            int doFinal = processBytes + this.cipher.doFinal(bArr2, processBytes);
            if (doFinal == engineGetOutputSize) {
                return bArr2;
            }
            if (doFinal <= engineGetOutputSize) {
                byte[] bArr3 = new byte[doFinal];
                System.arraycopy(bArr2, 0, bArr3, 0, doFinal);
                return bArr3;
            }
            throw new IllegalBlockSizeException("internal buffer overflow");
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        BlockCipher blockCipher = this.baseEngine;
        if (blockCipher == null) {
            return -1;
        }
        return blockCipher.getBlockSize();
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        AEADParameters aEADParameters = this.aeadParams;
        if (aEADParameters != null) {
            return aEADParameters.getNonce();
        }
        ParametersWithIV parametersWithIV = this.ivParam;
        if (parametersWithIV != null) {
            return parametersWithIV.getIV();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int i) {
        return this.cipher.getOutputSize(i);
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            if (this.pbeSpec != null) {
                try {
                    this.engineParams = createParametersInstance(this.pbeAlgorithm);
                    this.engineParams.init(this.pbeSpec);
                } catch (Exception unused) {
                    return null;
                }
            } else if (this.aeadParams != null) {
                if (this.baseEngine == null) {
                    try {
                        this.engineParams = createParametersInstance(PKCSObjectIdentifiers.id_alg_AEADChaCha20Poly1305.getId());
                        this.engineParams.init(new DEROctetString(this.aeadParams.getNonce()).getEncoded());
                    } catch (Exception e) {
                        throw new RuntimeException(e.toString());
                    }
                } else {
                    try {
                        this.engineParams = createParametersInstance("GCM");
                        this.engineParams.init(new GCMParameters(this.aeadParams.getNonce(), this.aeadParams.getMacSize() / 8).getEncoded());
                    } catch (Exception e2) {
                        throw new RuntimeException(e2.toString());
                    }
                }
            } else if (this.ivParam != null) {
                String algorithmName = this.cipher.getUnderlyingCipher().getAlgorithmName();
                if (algorithmName.indexOf(47) >= 0) {
                    algorithmName = algorithmName.substring(0, algorithmName.indexOf(47));
                }
                try {
                    this.engineParams = createParametersInstance(algorithmName);
                    this.engineParams.init(new IvParameterSpec(this.ivParam.getIV()));
                } catch (Exception e3) {
                    throw new RuntimeException(e3.toString());
                }
            }
        }
        return this.engineParams;
    }

    /* access modifiers changed from: protected */
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algorithmParameterSpec;
        if (algorithmParameters != null) {
            algorithmParameterSpec = SpecUtil.extractSpec(algorithmParameters, availableSpecs);
            if (algorithmParameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        } else {
            algorithmParameterSpec = null;
        }
        engineInit(i, key, algorithmParameterSpec, secureRandom);
        this.engineParams = algorithmParameters;
    }

    /* access modifiers changed from: protected */
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            AlgorithmParameterSpec algorithmParameterSpec = null;
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: org.bouncycastle.crypto.params.ParametersWithRandom} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: org.bouncycastle.crypto.params.ParametersWithRandom} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: org.bouncycastle.crypto.params.ParametersWithRandom} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: org.bouncycastle.crypto.params.ParametersWithIV} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: org.bouncycastle.crypto.params.FPEParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: org.bouncycastle.crypto.params.ParametersWithIV} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v53, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v64, resolved type: org.bouncycastle.crypto.params.RC5Parameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v65, resolved type: org.bouncycastle.crypto.params.RC5Parameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v66, resolved type: org.bouncycastle.crypto.params.RC2Parameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: org.bouncycastle.crypto.params.RC2Parameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v68, resolved type: org.bouncycastle.crypto.params.ParametersWithSBox} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v69, resolved type: org.bouncycastle.crypto.params.ParametersWithSBox} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v70, resolved type: org.bouncycastle.crypto.params.ParametersWithIV} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v71, resolved type: org.bouncycastle.crypto.params.AEADParameters} */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [org.bouncycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r6v73 */
    /* JADX WARNING: type inference failed for: r6v76 */
    /* JADX WARNING: type inference failed for: r6v77 */
    /* JADX WARNING: type inference failed for: r6v78 */
    /* JADX WARNING: type inference failed for: r6v80 */
    /* JADX WARNING: type inference failed for: r6v85 */
    /* JADX WARNING: type inference failed for: r6v86 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0448, code lost:
        if (r3 == null) goto L_0x0457;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x044a, code lost:
        r6 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x044c, code lost:
        if ((r3 instanceof javax.crypto.spec.PBEParameterSpec) == false) goto L_0x044f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0456, code lost:
        throw new java.security.InvalidAlgorithmParameterException("unknown parameter type.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ad, code lost:
        if (r8 != false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fa, code lost:
        if (r8 != false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0148, code lost:
        if (r8 != false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0204, code lost:
        if (r8 != false) goto L_0x00af;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x04ac  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x04e0 A[Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }] */
    /* JADX WARNING: Removed duplicated region for block: B:248:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void engineInit(int r22, java.security.Key r23, java.security.spec.AlgorithmParameterSpec r24, java.security.SecureRandom r25) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            java.lang.String r5 = "unknown opmode "
            r6 = 0
            r1.pbeSpec = r6
            r1.pbeAlgorithm = r6
            r1.engineParams = r6
            r1.aeadParams = r6
            boolean r7 = r2 instanceof javax.crypto.SecretKey
            if (r7 != 0) goto L_0x0038
            java.security.InvalidKeyException r0 = new java.security.InvalidKeyException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Key for algorithm "
            r3.<init>(r4)
            if (r2 == 0) goto L_0x0028
            java.lang.String r6 = r23.getAlgorithm()
        L_0x0028:
            r3.append(r6)
            java.lang.String r2 = " not suitable for symmetric enryption."
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.<init>(r2)
            throw r0
        L_0x0038:
            java.lang.String r7 = "RC5-64"
            if (r3 != 0) goto L_0x0053
            org.bouncycastle.crypto.BlockCipher r8 = r1.baseEngine
            if (r8 == 0) goto L_0x0053
            java.lang.String r8 = r8.getAlgorithmName()
            boolean r8 = r8.startsWith(r7)
            if (r8 != 0) goto L_0x004b
            goto L_0x0053
        L_0x004b:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "RC5 requires an RC5ParametersSpec to be passed in."
            r0.<init>(r2)
            throw r0
        L_0x0053:
            int r8 = r1.scheme
            r9 = 4
            java.lang.String r10 = "Algorithm requires a PBE key"
            r11 = 2
            r12 = 1
            if (r8 == r11) goto L_0x016a
            boolean r13 = r2 instanceof org.bouncycastle.jcajce.PKCS12Key
            if (r13 == 0) goto L_0x0062
            goto L_0x016a
        L_0x0062:
            boolean r13 = r2 instanceof org.bouncycastle.jcajce.PBKDF1Key
            if (r13 == 0) goto L_0x00b6
            r6 = r2
            org.bouncycastle.jcajce.PBKDF1Key r6 = (org.bouncycastle.jcajce.PBKDF1Key) r6
            boolean r8 = r3 instanceof javax.crypto.spec.PBEParameterSpec
            if (r8 == 0) goto L_0x0072
            r8 = r3
            javax.crypto.spec.PBEParameterSpec r8 = (javax.crypto.spec.PBEParameterSpec) r8
            r1.pbeSpec = r8
        L_0x0072:
            boolean r8 = r6 instanceof org.bouncycastle.jcajce.PBKDF1KeyWithParameters
            if (r8 == 0) goto L_0x008c
            javax.crypto.spec.PBEParameterSpec r8 = r1.pbeSpec
            if (r8 != 0) goto L_0x008c
            javax.crypto.spec.PBEParameterSpec r8 = new javax.crypto.spec.PBEParameterSpec
            r10 = r6
            org.bouncycastle.jcajce.PBKDF1KeyWithParameters r10 = (org.bouncycastle.jcajce.PBKDF1KeyWithParameters) r10
            byte[] r13 = r10.getSalt()
            int r10 = r10.getIterationCount()
            r8.<init>(r13, r10)
            r1.pbeSpec = r8
        L_0x008c:
            byte[] r14 = r6.getEncoded()
            int r6 = r1.digest
            int r8 = r1.keySizeInBits
            int r10 = r1.ivLength
            int r18 = r10 * 8
            javax.crypto.spec.PBEParameterSpec r10 = r1.pbeSpec
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r13 = r1.cipher
            java.lang.String r20 = r13.getAlgorithmName()
            r15 = 0
            r16 = r6
            r17 = r8
            r19 = r10
            org.bouncycastle.crypto.CipherParameters r6 = org.bouncycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r14, r15, r16, r17, r18, r19, r20)
            boolean r8 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r8 == 0) goto L_0x0208
        L_0x00af:
            r8 = r6
            org.bouncycastle.crypto.params.ParametersWithIV r8 = (org.bouncycastle.crypto.params.ParametersWithIV) r8
            r1.ivParam = r8
            goto L_0x0208
        L_0x00b6:
            boolean r13 = r2 instanceof org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey
            if (r13 == 0) goto L_0x0105
            r6 = r2
            org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey r6 = (org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey) r6
            org.bouncycastle.asn1.ASN1ObjectIdentifier r8 = r6.getOID()
            if (r8 == 0) goto L_0x00cc
            org.bouncycastle.asn1.ASN1ObjectIdentifier r8 = r6.getOID()
            java.lang.String r8 = r8.getId()
            goto L_0x00d0
        L_0x00cc:
            java.lang.String r8 = r6.getAlgorithm()
        L_0x00d0:
            r1.pbeAlgorithm = r8
            org.bouncycastle.crypto.CipherParameters r8 = r6.getParam()
            if (r8 == 0) goto L_0x00e1
            org.bouncycastle.crypto.CipherParameters r6 = r6.getParam()
            org.bouncycastle.crypto.CipherParameters r6 = r1.adjustParameters(r3, r6)
            goto L_0x00f8
        L_0x00e1:
            boolean r8 = r3 instanceof javax.crypto.spec.PBEParameterSpec
            if (r8 == 0) goto L_0x00fd
            r8 = r3
            javax.crypto.spec.PBEParameterSpec r8 = (javax.crypto.spec.PBEParameterSpec) r8
            r1.pbeSpec = r8
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r8 = r1.cipher
            org.bouncycastle.crypto.BlockCipher r8 = r8.getUnderlyingCipher()
            java.lang.String r8 = r8.getAlgorithmName()
            org.bouncycastle.crypto.CipherParameters r6 = org.bouncycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r6, r3, r8)
        L_0x00f8:
            boolean r8 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r8 == 0) goto L_0x0208
            goto L_0x00af
        L_0x00fd:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "PBE requires PBE parameters to be set."
            r0.<init>(r2)
            throw r0
        L_0x0105:
            boolean r13 = r2 instanceof javax.crypto.interfaces.PBEKey
            if (r13 == 0) goto L_0x014c
            r6 = r2
            javax.crypto.interfaces.PBEKey r6 = (javax.crypto.interfaces.PBEKey) r6
            r8 = r3
            javax.crypto.spec.PBEParameterSpec r8 = (javax.crypto.spec.PBEParameterSpec) r8
            r1.pbeSpec = r8
            boolean r10 = r6 instanceof org.bouncycastle.jcajce.PKCS12KeyWithParameters
            if (r10 == 0) goto L_0x0126
            if (r8 != 0) goto L_0x0126
            javax.crypto.spec.PBEParameterSpec r8 = new javax.crypto.spec.PBEParameterSpec
            byte[] r10 = r6.getSalt()
            int r13 = r6.getIterationCount()
            r8.<init>(r10, r13)
            r1.pbeSpec = r8
        L_0x0126:
            byte[] r14 = r6.getEncoded()
            int r15 = r1.scheme
            int r6 = r1.digest
            int r8 = r1.keySizeInBits
            int r10 = r1.ivLength
            int r18 = r10 * 8
            javax.crypto.spec.PBEParameterSpec r10 = r1.pbeSpec
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r13 = r1.cipher
            java.lang.String r20 = r13.getAlgorithmName()
            r16 = r6
            r17 = r8
            r19 = r10
            org.bouncycastle.crypto.CipherParameters r6 = org.bouncycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r14, r15, r16, r17, r18, r19, r20)
            boolean r8 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r8 == 0) goto L_0x0208
            goto L_0x00af
        L_0x014c:
            boolean r13 = r2 instanceof org.bouncycastle.jcajce.spec.RepeatedSecretKeySpec
            if (r13 != 0) goto L_0x0208
            if (r8 == 0) goto L_0x0164
            if (r8 == r9) goto L_0x0164
            if (r8 == r12) goto L_0x0164
            r6 = 5
            if (r8 == r6) goto L_0x0164
            org.bouncycastle.crypto.params.KeyParameter r6 = new org.bouncycastle.crypto.params.KeyParameter
            byte[] r8 = r23.getEncoded()
            r6.<init>((byte[]) r8)
            goto L_0x0208
        L_0x0164:
            java.security.InvalidKeyException r0 = new java.security.InvalidKeyException
            r0.<init>(r10)
            throw r0
        L_0x016a:
            r6 = r2
            javax.crypto.SecretKey r6 = (javax.crypto.SecretKey) r6     // Catch:{ Exception -> 0x0519 }
            boolean r8 = r3 instanceof javax.crypto.spec.PBEParameterSpec
            if (r8 == 0) goto L_0x0176
            r8 = r3
            javax.crypto.spec.PBEParameterSpec r8 = (javax.crypto.spec.PBEParameterSpec) r8
            r1.pbeSpec = r8
        L_0x0176:
            boolean r8 = r6 instanceof javax.crypto.interfaces.PBEKey
            if (r8 == 0) goto L_0x019f
            javax.crypto.spec.PBEParameterSpec r13 = r1.pbeSpec
            if (r13 != 0) goto L_0x019f
            r13 = r6
            javax.crypto.interfaces.PBEKey r13 = (javax.crypto.interfaces.PBEKey) r13
            byte[] r14 = r13.getSalt()
            if (r14 == 0) goto L_0x0197
            javax.crypto.spec.PBEParameterSpec r14 = new javax.crypto.spec.PBEParameterSpec
            byte[] r15 = r13.getSalt()
            int r13 = r13.getIterationCount()
            r14.<init>(r15, r13)
            r1.pbeSpec = r14
            goto L_0x019f
        L_0x0197:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "PBEKey requires parameters to specify salt"
            r0.<init>(r2)
            throw r0
        L_0x019f:
            javax.crypto.spec.PBEParameterSpec r13 = r1.pbeSpec
            if (r13 != 0) goto L_0x01ac
            if (r8 == 0) goto L_0x01a6
            goto L_0x01ac
        L_0x01a6:
            java.security.InvalidKeyException r0 = new java.security.InvalidKeyException
            r0.<init>(r10)
            throw r0
        L_0x01ac:
            boolean r8 = r2 instanceof org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey
            if (r8 == 0) goto L_0x01e5
            r8 = r2
            org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey r8 = (org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey) r8
            org.bouncycastle.crypto.CipherParameters r8 = r8.getParam()
            boolean r10 = r8 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r10 == 0) goto L_0x01bc
            goto L_0x01db
        L_0x01bc:
            if (r8 != 0) goto L_0x01dd
            byte[] r13 = r6.getEncoded()
            int r15 = r1.digest
            int r6 = r1.keySizeInBits
            int r8 = r1.ivLength
            int r17 = r8 * 8
            javax.crypto.spec.PBEParameterSpec r8 = r1.pbeSpec
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r10 = r1.cipher
            java.lang.String r19 = r10.getAlgorithmName()
            r14 = 2
            r16 = r6
            r18 = r8
            org.bouncycastle.crypto.CipherParameters r8 = org.bouncycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r13, r14, r15, r16, r17, r18, r19)
        L_0x01db:
            r6 = r8
            goto L_0x0202
        L_0x01dd:
            java.security.InvalidKeyException r0 = new java.security.InvalidKeyException
            java.lang.String r2 = "Algorithm requires a PBE key suitable for PKCS12"
            r0.<init>(r2)
            throw r0
        L_0x01e5:
            byte[] r13 = r6.getEncoded()
            int r15 = r1.digest
            int r6 = r1.keySizeInBits
            int r8 = r1.ivLength
            int r17 = r8 * 8
            javax.crypto.spec.PBEParameterSpec r8 = r1.pbeSpec
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r10 = r1.cipher
            java.lang.String r19 = r10.getAlgorithmName()
            r14 = 2
            r16 = r6
            r18 = r8
            org.bouncycastle.crypto.CipherParameters r6 = org.bouncycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r13, r14, r15, r16, r17, r18, r19)
        L_0x0202:
            boolean r8 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r8 == 0) goto L_0x0208
            goto L_0x00af
        L_0x0208:
            boolean r8 = r3 instanceof org.bouncycastle.jcajce.spec.AEADParameterSpec
            if (r8 == 0) goto L_0x024b
            java.lang.String r2 = r1.modeName
            boolean r2 = r1.isAEADModeName(r2)
            if (r2 != 0) goto L_0x0223
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r2 = r1.cipher
            boolean r2 = r2 instanceof org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher
            if (r2 == 0) goto L_0x021b
            goto L_0x0223
        L_0x021b:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "AEADParameterSpec can only be used with AEAD modes."
            r0.<init>(r2)
            throw r0
        L_0x0223:
            r2 = r3
            org.bouncycastle.jcajce.spec.AEADParameterSpec r2 = (org.bouncycastle.jcajce.spec.AEADParameterSpec) r2
            boolean r3 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x0233
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            org.bouncycastle.crypto.CipherParameters r3 = r6.getParameters()
            org.bouncycastle.crypto.params.KeyParameter r3 = (org.bouncycastle.crypto.params.KeyParameter) r3
            goto L_0x0236
        L_0x0233:
            r3 = r6
            org.bouncycastle.crypto.params.KeyParameter r3 = (org.bouncycastle.crypto.params.KeyParameter) r3
        L_0x0236:
            org.bouncycastle.crypto.params.AEADParameters r6 = new org.bouncycastle.crypto.params.AEADParameters
            int r7 = r2.getMacSizeInBits()
            byte[] r8 = r2.getNonce()
            byte[] r2 = r2.getAssociatedData()
            r6.<init>(r3, r7, r8, r2)
        L_0x0247:
            r1.aeadParams = r6
            goto L_0x0457
        L_0x024b:
            boolean r8 = r3 instanceof javax.crypto.spec.IvParameterSpec
            if (r8 == 0) goto L_0x02c0
            int r2 = r1.ivLength
            if (r2 == 0) goto L_0x02aa
            r2 = r3
            javax.crypto.spec.IvParameterSpec r2 = (javax.crypto.spec.IvParameterSpec) r2
            byte[] r3 = r2.getIV()
            int r3 = r3.length
            int r7 = r1.ivLength
            if (r3 == r7) goto L_0x0285
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r1.cipher
            boolean r3 = r3 instanceof org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher
            if (r3 != 0) goto L_0x0285
            boolean r3 = r1.fixedIv
            if (r3 != 0) goto L_0x026a
            goto L_0x0285
        L_0x026a:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "IV must be "
            r2.<init>(r3)
            int r3 = r1.ivLength
            r2.append(r3)
            java.lang.String r3 = " bytes long."
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0285:
            boolean r3 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x0299
            org.bouncycastle.crypto.params.ParametersWithIV r3 = new org.bouncycastle.crypto.params.ParametersWithIV
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            org.bouncycastle.crypto.CipherParameters r6 = r6.getParameters()
            byte[] r2 = r2.getIV()
            r3.<init>(r6, r2)
            goto L_0x02a2
        L_0x0299:
            org.bouncycastle.crypto.params.ParametersWithIV r3 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r2 = r2.getIV()
            r3.<init>(r6, r2)
        L_0x02a2:
            r6 = r3
            r2 = r6
            org.bouncycastle.crypto.params.ParametersWithIV r2 = (org.bouncycastle.crypto.params.ParametersWithIV) r2
            r1.ivParam = r2
            goto L_0x0457
        L_0x02aa:
            java.lang.String r2 = r1.modeName
            if (r2 == 0) goto L_0x0457
            java.lang.String r3 = "ECB"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02b8
            goto L_0x0457
        L_0x02b8:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "ECB mode does not use an IV"
            r0.<init>(r2)
            throw r0
        L_0x02c0:
            boolean r8 = r3 instanceof org.bouncycastle.jcajce.spec.GOST28147ParameterSpec
            if (r8 == 0) goto L_0x0307
            org.bouncycastle.jcajce.spec.GOST28147ParameterSpec r3 = (org.bouncycastle.jcajce.spec.GOST28147ParameterSpec) r3
            org.bouncycastle.crypto.params.ParametersWithSBox r6 = new org.bouncycastle.crypto.params.ParametersWithSBox
            org.bouncycastle.crypto.params.KeyParameter r7 = new org.bouncycastle.crypto.params.KeyParameter
            byte[] r2 = r23.getEncoded()
            r7.<init>((byte[]) r2)
            byte[] r2 = r3.getSBox()
            r6.<init>(r7, r2)
            byte[] r2 = r3.getIV()
            if (r2 == 0) goto L_0x0457
            int r2 = r1.ivLength
            if (r2 == 0) goto L_0x0457
            boolean r2 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r2 == 0) goto L_0x02f6
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            org.bouncycastle.crypto.CipherParameters r6 = r6.getParameters()
            byte[] r3 = r3.getIV()
            r2.<init>(r6, r3)
            goto L_0x02ff
        L_0x02f6:
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r3 = r3.getIV()
            r2.<init>(r6, r3)
        L_0x02ff:
            r3 = r2
            org.bouncycastle.crypto.params.ParametersWithIV r3 = (org.bouncycastle.crypto.params.ParametersWithIV) r3
            r1.ivParam = r3
            r6 = r2
            goto L_0x0457
        L_0x0307:
            boolean r8 = r3 instanceof javax.crypto.spec.RC2ParameterSpec
            if (r8 == 0) goto L_0x0342
            javax.crypto.spec.RC2ParameterSpec r3 = (javax.crypto.spec.RC2ParameterSpec) r3
            org.bouncycastle.crypto.params.RC2Parameters r6 = new org.bouncycastle.crypto.params.RC2Parameters
            byte[] r2 = r23.getEncoded()
            int r7 = r3.getEffectiveKeyBits()
            r6.<init>(r2, r7)
            byte[] r2 = r3.getIV()
            if (r2 == 0) goto L_0x0457
            int r2 = r1.ivLength
            if (r2 == 0) goto L_0x0457
            boolean r2 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r2 == 0) goto L_0x0338
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            org.bouncycastle.crypto.CipherParameters r6 = r6.getParameters()
            byte[] r3 = r3.getIV()
            r2.<init>(r6, r3)
            goto L_0x02ff
        L_0x0338:
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r3 = r3.getIV()
            r2.<init>(r6, r3)
            goto L_0x02ff
        L_0x0342:
            boolean r8 = r3 instanceof javax.crypto.spec.RC5ParameterSpec
            if (r8 == 0) goto L_0x03f9
            javax.crypto.spec.RC5ParameterSpec r3 = (javax.crypto.spec.RC5ParameterSpec) r3
            org.bouncycastle.crypto.params.RC5Parameters r6 = new org.bouncycastle.crypto.params.RC5Parameters
            byte[] r2 = r23.getEncoded()
            int r8 = r3.getRounds()
            r6.<init>(r2, r8)
            org.bouncycastle.crypto.BlockCipher r2 = r1.baseEngine
            java.lang.String r2 = r2.getAlgorithmName()
            java.lang.String r8 = "RC5"
            boolean r2 = r2.startsWith(r8)
            if (r2 == 0) goto L_0x03f1
            org.bouncycastle.crypto.BlockCipher r2 = r1.baseEngine
            java.lang.String r2 = r2.getAlgorithmName()
            java.lang.String r8 = "RC5-32"
            boolean r2 = r2.equals(r8)
            java.lang.String r8 = "."
            if (r2 == 0) goto L_0x0397
            int r2 = r3.getWordSize()
            r7 = 32
            if (r2 != r7) goto L_0x037c
            goto L_0x03c7
        L_0x037c:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "RC5 already set up for a word size of 32 not "
            r2.<init>(r4)
            int r3 = r3.getWordSize()
            r2.append(r3)
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0397:
            org.bouncycastle.crypto.BlockCipher r2 = r1.baseEngine
            java.lang.String r2 = r2.getAlgorithmName()
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x03c7
            int r2 = r3.getWordSize()
            r7 = 64
            if (r2 != r7) goto L_0x03ac
            goto L_0x03c7
        L_0x03ac:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "RC5 already set up for a word size of 64 not "
            r2.<init>(r4)
            int r3 = r3.getWordSize()
            r2.append(r3)
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x03c7:
            byte[] r2 = r3.getIV()
            if (r2 == 0) goto L_0x0457
            int r2 = r1.ivLength
            if (r2 == 0) goto L_0x0457
            boolean r2 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r2 == 0) goto L_0x03e6
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            org.bouncycastle.crypto.CipherParameters r6 = r6.getParameters()
            byte[] r3 = r3.getIV()
            r2.<init>(r6, r3)
            goto L_0x02ff
        L_0x03e6:
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            byte[] r3 = r3.getIV()
            r2.<init>(r6, r3)
            goto L_0x02ff
        L_0x03f1:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "RC5 parameters passed to a cipher that is not RC5."
            r0.<init>(r2)
            throw r0
        L_0x03f9:
            boolean r2 = r3 instanceof org.bouncycastle.jcajce.spec.FPEParameterSpec
            if (r2 == 0) goto L_0x0415
            r2 = r3
            org.bouncycastle.jcajce.spec.FPEParameterSpec r2 = (org.bouncycastle.jcajce.spec.FPEParameterSpec) r2
            org.bouncycastle.crypto.params.FPEParameters r3 = new org.bouncycastle.crypto.params.FPEParameters
            org.bouncycastle.crypto.params.KeyParameter r6 = (org.bouncycastle.crypto.params.KeyParameter) r6
            org.bouncycastle.crypto.util.RadixConverter r7 = r2.getRadixConverter()
            byte[] r8 = r2.getTweak()
            boolean r2 = r2.isUsingInverseFunction()
            r3.<init>((org.bouncycastle.crypto.params.KeyParameter) r6, (org.bouncycastle.crypto.util.RadixConverter) r7, (byte[]) r8, (boolean) r2)
            r6 = r3
            goto L_0x0457
        L_0x0415:
            boolean r2 = org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil.isGcmSpec((java.security.spec.AlgorithmParameterSpec) r24)
            if (r2 == 0) goto L_0x0448
            java.lang.String r2 = r1.modeName
            boolean r2 = r1.isAEADModeName(r2)
            if (r2 != 0) goto L_0x0432
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r2 = r1.cipher
            boolean r2 = r2 instanceof org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher
            if (r2 == 0) goto L_0x042a
            goto L_0x0432
        L_0x042a:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "GCMParameterSpec can only be used with AEAD modes."
            r0.<init>(r2)
            throw r0
        L_0x0432:
            boolean r2 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r2 == 0) goto L_0x043f
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            org.bouncycastle.crypto.CipherParameters r2 = r6.getParameters()
            org.bouncycastle.crypto.params.KeyParameter r2 = (org.bouncycastle.crypto.params.KeyParameter) r2
            goto L_0x0442
        L_0x043f:
            r2 = r6
            org.bouncycastle.crypto.params.KeyParameter r2 = (org.bouncycastle.crypto.params.KeyParameter) r2
        L_0x0442:
            org.bouncycastle.crypto.params.AEADParameters r6 = org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil.extractAeadParameters(r2, r3)
            goto L_0x0247
        L_0x0448:
            if (r3 == 0) goto L_0x0457
            boolean r2 = r3 instanceof javax.crypto.spec.PBEParameterSpec
            if (r2 == 0) goto L_0x044f
            goto L_0x0457
        L_0x044f:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "unknown parameter type."
            r0.<init>(r2)
            throw r0
        L_0x0457:
            int r2 = r1.ivLength
            r3 = 3
            if (r2 == 0) goto L_0x049e
            boolean r2 = r6 instanceof org.bouncycastle.crypto.params.ParametersWithIV
            if (r2 != 0) goto L_0x049e
            boolean r2 = r6 instanceof org.bouncycastle.crypto.params.AEADParameters
            if (r2 != 0) goto L_0x049e
            if (r4 != 0) goto L_0x046b
            java.security.SecureRandom r2 = org.bouncycastle.crypto.CryptoServicesRegistrar.getSecureRandom()
            goto L_0x046c
        L_0x046b:
            r2 = r4
        L_0x046c:
            if (r0 == r12) goto L_0x048c
            if (r0 != r3) goto L_0x0471
            goto L_0x048c
        L_0x0471:
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r2 = r1.cipher
            org.bouncycastle.crypto.BlockCipher r2 = r2.getUnderlyingCipher()
            java.lang.String r2 = r2.getAlgorithmName()
            java.lang.String r7 = "PGPCFB"
            int r2 = r2.indexOf(r7)
            if (r2 < 0) goto L_0x0484
            goto L_0x049e
        L_0x0484:
            java.security.InvalidAlgorithmParameterException r0 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "no IV set when one expected"
            r0.<init>(r2)
            throw r0
        L_0x048c:
            int r7 = r1.ivLength
            byte[] r7 = new byte[r7]
            r2.nextBytes(r7)
            org.bouncycastle.crypto.params.ParametersWithIV r2 = new org.bouncycastle.crypto.params.ParametersWithIV
            r2.<init>(r6, r7)
            r6 = r2
            org.bouncycastle.crypto.params.ParametersWithIV r6 = (org.bouncycastle.crypto.params.ParametersWithIV) r6
            r1.ivParam = r2
            r6 = r2
        L_0x049e:
            if (r4 == 0) goto L_0x04aa
            boolean r2 = r1.padded
            if (r2 == 0) goto L_0x04aa
            org.bouncycastle.crypto.params.ParametersWithRandom r2 = new org.bouncycastle.crypto.params.ParametersWithRandom
            r2.<init>(r6, r4)
            r6 = r2
        L_0x04aa:
            if (r0 == r12) goto L_0x04d1
            if (r0 == r11) goto L_0x04ca
            if (r0 == r3) goto L_0x04d1
            if (r0 != r9) goto L_0x04b3
            goto L_0x04ca
        L_0x04b3:
            java.security.InvalidParameterException r2 = new java.security.InvalidParameterException     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r3.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r3.append(r0)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            java.lang.String r0 = " passed"
            r3.append(r0)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            java.lang.String r0 = r3.toString()     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r2.<init>(r0)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
        L_0x04ca:
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r0 = r1.cipher     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r2 = 0
            r0.init(r2, r6)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            goto L_0x04d6
        L_0x04d1:
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r0 = r1.cipher     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r0.init(r12, r6)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
        L_0x04d6:
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r0 = r1.cipher     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            boolean r2 = r0 instanceof org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            if (r2 == 0) goto L_0x0502
            org.bouncycastle.crypto.params.AEADParameters r2 = r1.aeadParams     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            if (r2 != 0) goto L_0x0502
            org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher$AEADGenericBlockCipher r0 = (org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher) r0     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            org.bouncycastle.crypto.modes.AEADCipher r0 = r0.cipher     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            org.bouncycastle.crypto.params.AEADParameters r2 = new org.bouncycastle.crypto.params.AEADParameters     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            org.bouncycastle.crypto.params.ParametersWithIV r3 = r1.ivParam     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            org.bouncycastle.crypto.CipherParameters r3 = r3.getParameters()     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            org.bouncycastle.crypto.params.KeyParameter r3 = (org.bouncycastle.crypto.params.KeyParameter) r3     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            byte[] r0 = r0.getMac()     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            int r0 = r0.length     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            int r0 = r0 * 8
            org.bouncycastle.crypto.params.ParametersWithIV r4 = r1.ivParam     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            byte[] r4 = r4.getIV()     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r2.<init>(r3, r0, r4)     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
            r1.aeadParams = r2     // Catch:{ IllegalArgumentException -> 0x050e, Exception -> 0x0503 }
        L_0x0502:
            return
        L_0x0503:
            r0 = move-exception
            org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher$InvalidKeyOrParametersException r2 = new org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher$InvalidKeyOrParametersException
            java.lang.String r3 = r0.getMessage()
            r2.<init>(r3, r0)
            throw r2
        L_0x050e:
            r0 = move-exception
            java.security.InvalidAlgorithmParameterException r2 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r3 = r0.getMessage()
            r2.<init>(r3, r0)
            throw r2
        L_0x0519:
            java.security.InvalidKeyException r0 = new java.security.InvalidKeyException
            java.lang.String r2 = "PKCS12 requires a SecretKey/PBEKey"
            r0.<init>(r2)
            goto L_0x0522
        L_0x0521:
            throw r0
        L_0x0522:
            goto L_0x0521
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.engineInit(int, java.security.Key, java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        GenericBlockCipher aEADGenericBlockCipher;
        BufferedGenericBlockCipher bufferedGenericBlockCipher;
        if (this.baseEngine != null) {
            String upperCase = Strings.toUpperCase(str);
            this.modeName = upperCase;
            if (upperCase.equals("ECB")) {
                this.ivLength = 0;
                aEADGenericBlockCipher = new BufferedGenericBlockCipher(this.baseEngine);
            } else if (this.modeName.equals("CBC")) {
                this.ivLength = this.baseEngine.getBlockSize();
                aEADGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) CBCBlockCipher.newInstance(this.baseEngine));
            } else {
                if (this.modeName.startsWith("OFB")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    if (this.modeName.length() != 3) {
                        bufferedGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) new OFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                    } else {
                        BlockCipher blockCipher = this.baseEngine;
                        aEADGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) new OFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8));
                    }
                } else if (this.modeName.startsWith("CFB")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    if (this.modeName.length() != 3) {
                        bufferedGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) CFBBlockCipher.newInstance(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                    } else {
                        BlockCipher blockCipher2 = this.baseEngine;
                        aEADGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) CFBBlockCipher.newInstance(blockCipher2, blockCipher2.getBlockSize() * 8));
                    }
                } else if (this.modeName.startsWith("PGPCFB")) {
                    boolean equals = this.modeName.equals("PGPCFBWITHIV");
                    if (equals || this.modeName.length() == 6) {
                        this.ivLength = this.baseEngine.getBlockSize();
                        bufferedGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) new PGPCFBBlockCipher(this.baseEngine, equals));
                    } else {
                        throw new NoSuchAlgorithmException("no mode support for " + this.modeName);
                    }
                } else if (this.modeName.equals("OPENPGPCFB")) {
                    this.ivLength = 0;
                    aEADGenericBlockCipher = new BufferedGenericBlockCipher((BlockCipher) new OpenPGPCFBBlockCipher(this.baseEngine));
                } else if (this.modeName.equals("FF1")) {
                    this.ivLength = 0;
                    aEADGenericBlockCipher = new BufferedFPEBlockCipher(new FPEFF1Engine(this.baseEngine));
                } else if (this.modeName.equals("FF3-1")) {
                    this.ivLength = 0;
                    aEADGenericBlockCipher = new BufferedFPEBlockCipher(new FPEFF3_1Engine(this.baseEngine));
                } else if (this.modeName.equals("SIC")) {
                    int blockSize = this.baseEngine.getBlockSize();
                    this.ivLength = blockSize;
                    if (blockSize >= 16) {
                        this.fixedIv = false;
                        aEADGenericBlockCipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new DefaultBufferedBlockCipher(SICBlockCipher.newInstance(this.baseEngine)));
                    } else {
                        throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
                    }
                } else if (this.modeName.equals("CTR")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    this.fixedIv = false;
                    BlockCipher blockCipher3 = this.baseEngine;
                    bufferedGenericBlockCipher = blockCipher3 instanceof DSTU7624Engine ? new BufferedGenericBlockCipher((BufferedBlockCipher) new DefaultBufferedBlockCipher(new KCTRBlockCipher(blockCipher3))) : new BufferedGenericBlockCipher((BufferedBlockCipher) new DefaultBufferedBlockCipher(SICBlockCipher.newInstance(blockCipher3)));
                } else if (this.modeName.equals("GOFB")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    aEADGenericBlockCipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new DefaultBufferedBlockCipher(new GOFBBlockCipher(this.baseEngine)));
                } else if (this.modeName.equals("GCFB")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    aEADGenericBlockCipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new DefaultBufferedBlockCipher(new GCFBBlockCipher(this.baseEngine)));
                } else if (this.modeName.equals("CTS")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    aEADGenericBlockCipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new CTSBlockCipher(CBCBlockCipher.newInstance(this.baseEngine)));
                } else if (this.modeName.equals("CCM")) {
                    this.ivLength = 12;
                    aEADGenericBlockCipher = this.baseEngine instanceof DSTU7624Engine ? new AEADGenericBlockCipher(new KCCMBlockCipher(this.baseEngine)) : new AEADGenericBlockCipher(CCMBlockCipher.newInstance(this.baseEngine));
                } else if (this.modeName.equals("OCB")) {
                    if (this.engineProvider != null) {
                        this.ivLength = 15;
                        aEADGenericBlockCipher = new AEADGenericBlockCipher(new OCBBlockCipher(this.baseEngine, this.engineProvider.get()));
                    } else {
                        throw new NoSuchAlgorithmException("can't support mode " + str);
                    }
                } else if (this.modeName.equals("EAX")) {
                    this.ivLength = this.baseEngine.getBlockSize();
                    aEADGenericBlockCipher = new AEADGenericBlockCipher(new EAXBlockCipher(this.baseEngine));
                } else if (this.modeName.equals("GCM-SIV")) {
                    this.ivLength = 12;
                    aEADGenericBlockCipher = new AEADGenericBlockCipher(new GCMSIVBlockCipher(this.baseEngine));
                } else if (this.modeName.equals("GCM")) {
                    BlockCipher blockCipher4 = this.baseEngine;
                    if (blockCipher4 instanceof DSTU7624Engine) {
                        this.ivLength = blockCipher4.getBlockSize();
                        aEADGenericBlockCipher = new AEADGenericBlockCipher(new KGCMBlockCipher(this.baseEngine));
                    } else {
                        this.ivLength = 12;
                        aEADGenericBlockCipher = new AEADGenericBlockCipher(GCMBlockCipher.newInstance(this.baseEngine));
                    }
                } else {
                    throw new NoSuchAlgorithmException("can't support mode " + str);
                }
                this.cipher = bufferedGenericBlockCipher;
                return;
            }
            this.cipher = aEADGenericBlockCipher;
            return;
        }
        throw new NoSuchAlgorithmException("no mode supported for this algorithm");
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        BufferedGenericBlockCipher bufferedGenericBlockCipher;
        if (this.baseEngine != null) {
            String upperCase = Strings.toUpperCase(str);
            if (upperCase.equals("NOPADDING")) {
                if (this.cipher.wrapOnNoPadding()) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new DefaultBufferedBlockCipher(this.cipher.getUnderlyingCipher()));
                } else {
                    return;
                }
            } else if (upperCase.equals("WITHCTS") || upperCase.equals("CTSPADDING") || upperCase.equals("CS3PADDING")) {
                bufferedGenericBlockCipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new CTSBlockCipher(this.cipher.getUnderlyingCipher()));
            } else {
                this.padded = true;
                if (isAEADModeName(this.modeName)) {
                    throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
                } else if (upperCase.equals("PKCS5PADDING") || upperCase.equals("PKCS7PADDING")) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher());
                } else if (upperCase.equals("ZEROBYTEPADDING")) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ZeroBytePadding());
                } else if (upperCase.equals("ISO10126PADDING") || upperCase.equals("ISO10126-2PADDING")) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO10126d2Padding());
                } else if (upperCase.equals("X9.23PADDING") || upperCase.equals("X923PADDING")) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new X923Padding());
                } else if (upperCase.equals("ISO7816-4PADDING") || upperCase.equals("ISO9797-1PADDING")) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO7816d4Padding());
                } else if (upperCase.equals("TBCPADDING")) {
                    bufferedGenericBlockCipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new TBCPadding());
                } else {
                    throw new NoSuchPaddingException("Padding " + str + " unknown.");
                }
            }
            this.cipher = bufferedGenericBlockCipher;
            return;
        }
        throw new NoSuchPaddingException("no padding supported for this algorithm");
    }

    /* access modifiers changed from: protected */
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException {
        if (this.cipher.getUpdateOutputSize(i2) + i3 <= bArr2.length) {
            try {
                return this.cipher.processBytes(bArr, i, i2, bArr2, i3);
            } catch (DataLengthException e) {
                throw new IllegalStateException(e.toString());
            }
        } else {
            throw new ShortBufferException("output buffer too short for input.");
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        int updateOutputSize = this.cipher.getUpdateOutputSize(i2);
        if (updateOutputSize > 0) {
            byte[] bArr2 = new byte[updateOutputSize];
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr2, 0);
            if (processBytes == 0) {
                return null;
            }
            if (processBytes == updateOutputSize) {
                return bArr2;
            }
            byte[] bArr3 = new byte[processBytes];
            System.arraycopy(bArr2, 0, bArr3, 0, processBytes);
            return bArr3;
        }
        this.cipher.processBytes(bArr, i, i2, (byte[]) null, 0);
        return null;
    }

    /* access modifiers changed from: protected */
    public void engineUpdateAAD(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (remaining >= 1) {
            if (byteBuffer.hasArray()) {
                engineUpdateAAD(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), remaining);
                byteBuffer.position(byteBuffer.limit());
            } else if (remaining <= 512) {
                byte[] bArr = new byte[remaining];
                byteBuffer.get(bArr);
                engineUpdateAAD(bArr, 0, remaining);
                Arrays.fill(bArr, (byte) 0);
            } else {
                byte[] bArr2 = new byte[512];
                do {
                    int min = Math.min(512, remaining);
                    byteBuffer.get(bArr2, 0, min);
                    engineUpdateAAD(bArr2, 0, min);
                    remaining -= min;
                } while (remaining > 0);
                Arrays.fill(bArr2, (byte) 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void engineUpdateAAD(byte[] bArr, int i, int i2) {
        this.cipher.updateAAD(bArr, i, i2);
    }
}
