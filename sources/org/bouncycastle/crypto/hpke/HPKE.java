package org.bouncycastle.crypto.hpke;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;

public class HPKE {
    public static final short aead_AES_GCM128 = 1;
    public static final short aead_AES_GCM256 = 2;
    public static final short aead_CHACHA20_POLY1305 = 3;
    public static final short aead_EXPORT_ONLY = -1;
    public static final short kdf_HKDF_SHA256 = 1;
    public static final short kdf_HKDF_SHA384 = 2;
    public static final short kdf_HKDF_SHA512 = 3;
    public static final short kem_P256_SHA256 = 16;
    public static final short kem_P384_SHA348 = 17;
    public static final short kem_P521_SHA512 = 18;
    public static final short kem_X25519_SHA256 = 32;
    public static final short kem_X448_SHA512 = 33;
    public static final byte mode_auth = 2;
    public static final byte mode_auth_psk = 3;
    public static final byte mode_base = 0;
    public static final byte mode_psk = 1;
    short Nk;
    private final short aeadId;
    private final byte[] default_psk = null;
    private final byte[] default_psk_id = null;
    private final DHKEM dhkem;
    private final HKDF hkdf;
    private final short kdfId;
    private final short kemId;
    private final byte mode;

    public HPKE(byte b, short s, short s2, short s3) {
        this.mode = b;
        this.kemId = s;
        this.kdfId = s2;
        this.aeadId = s3;
        this.hkdf = new HKDF(s2);
        this.dhkem = new DHKEM(s);
        this.Nk = s3 == 1 ? (short) 16 : 32;
    }

    private void VerifyPSKInputs(byte b, byte[] bArr, byte[] bArr2) {
        boolean z = !Arrays.areEqual(bArr, this.default_psk);
        if (z != (!Arrays.areEqual(bArr2, this.default_psk_id))) {
            throw new IllegalArgumentException("Inconsistent PSK inputs");
        } else if (z && b % 2 == 0) {
            throw new IllegalArgumentException("PSK input provided when not needed");
        } else if (!z && b % 2 == 1) {
            throw new IllegalArgumentException("Missing required PSK input");
        }
    }

    private HPKEContext keySchedule(byte b, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        VerifyPSKInputs(b, bArr3, bArr4);
        byte[] concatenate = Arrays.concatenate(Strings.toByteArray("HPKE"), Pack.shortToBigEndian(this.kemId), Pack.shortToBigEndian(this.kdfId), Pack.shortToBigEndian(this.aeadId));
        byte[] bArr5 = {b};
        byte[] concatenate2 = Arrays.concatenate(bArr5, this.hkdf.LabeledExtract((byte[]) null, concatenate, "psk_id_hash", bArr4), this.hkdf.LabeledExtract((byte[]) null, concatenate, "info_hash", bArr2));
        byte[] LabeledExtract = this.hkdf.LabeledExtract(bArr, concatenate, "secret", bArr3);
        byte[] bArr6 = concatenate;
        byte[] bArr7 = concatenate2;
        byte[] LabeledExpand = this.hkdf.LabeledExpand(LabeledExtract, bArr6, LeanbackPreferenceDialogFragment.ARG_KEY, bArr7, this.Nk);
        byte[] LabeledExpand2 = this.hkdf.LabeledExpand(LabeledExtract, bArr6, "base_nonce", bArr7, 12);
        HKDF hkdf2 = this.hkdf;
        return new HPKEContext(new AEAD(this.aeadId, LabeledExpand, LabeledExpand2), this.hkdf, hkdf2.LabeledExpand(LabeledExtract, bArr6, "exp", bArr7, hkdf2.getHashSize()), concatenate);
    }

    public HPKEContextWithEncapsulation SetupPSKS(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[][] Encap = this.dhkem.Encap(asymmetricKeyParameter);
        return new HPKEContextWithEncapsulation(keySchedule((byte) 1, Encap[0], bArr, bArr2, bArr3), Encap[1]);
    }

    public AsymmetricCipherKeyPair deriveKeyPair(byte[] bArr) {
        return this.dhkem.DeriveKeyPair(bArr);
    }

    public AsymmetricCipherKeyPair deserializePrivateKey(byte[] bArr, byte[] bArr2) {
        return this.dhkem.DeserializePrivateKey(bArr, bArr2);
    }

    public AsymmetricKeyParameter deserializePublicKey(byte[] bArr) {
        return this.dhkem.DeserializePublicKey(bArr);
    }

    public AsymmetricCipherKeyPair generatePrivateKey() {
        return this.dhkem.GeneratePrivateKey();
    }

    public byte[] open(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, AsymmetricKeyParameter asymmetricKeyParameter) throws InvalidCipherTextException {
        HPKEContext hPKEContext;
        byte b = this.mode;
        if (b == 0) {
            hPKEContext = setupBaseR(bArr, asymmetricCipherKeyPair, bArr2);
        } else if (b == 1) {
            hPKEContext = setupPSKR(bArr, asymmetricCipherKeyPair, bArr2, bArr5, bArr6);
        } else if (b == 2) {
            hPKEContext = setupAuthR(bArr, asymmetricCipherKeyPair, bArr2, asymmetricKeyParameter);
        } else if (b == 3) {
            hPKEContext = setupAuthPSKR(bArr, asymmetricCipherKeyPair, bArr2, bArr5, bArr6, asymmetricKeyParameter);
        } else {
            throw new IllegalStateException("Unknown mode");
        }
        return hPKEContext.open(bArr3, bArr4);
    }

    public byte[] receiveExport(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5, AsymmetricKeyParameter asymmetricKeyParameter) {
        HPKEContext hPKEContext;
        byte b = this.mode;
        if (b == 0) {
            hPKEContext = setupBaseR(bArr, asymmetricCipherKeyPair, bArr2);
        } else if (b == 1) {
            hPKEContext = setupPSKR(bArr, asymmetricCipherKeyPair, bArr2, bArr4, bArr5);
        } else if (b == 2) {
            hPKEContext = setupAuthR(bArr, asymmetricCipherKeyPair, bArr2, asymmetricKeyParameter);
        } else if (b == 3) {
            hPKEContext = setupAuthPSKR(bArr, asymmetricCipherKeyPair, bArr2, bArr4, bArr5, asymmetricKeyParameter);
        } else {
            throw new IllegalStateException("Unknown mode");
        }
        return hPKEContext.export(bArr3, i);
    }

    public byte[][] seal(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, AsymmetricCipherKeyPair asymmetricCipherKeyPair) throws InvalidCipherTextException {
        HPKEContextWithEncapsulation hPKEContextWithEncapsulation;
        AsymmetricKeyParameter asymmetricKeyParameter2 = asymmetricKeyParameter;
        byte[] bArr6 = bArr;
        byte b = this.mode;
        if (b == 0) {
            hPKEContextWithEncapsulation = setupBaseS(asymmetricKeyParameter, bArr);
        } else if (b == 1) {
            byte[] bArr7 = bArr4;
            byte[] bArr8 = bArr5;
            hPKEContextWithEncapsulation = SetupPSKS(asymmetricKeyParameter, bArr, bArr4, bArr5);
        } else if (b == 2) {
            hPKEContextWithEncapsulation = setupAuthS(asymmetricKeyParameter, bArr, asymmetricCipherKeyPair);
        } else if (b == 3) {
            hPKEContextWithEncapsulation = setupAuthPSKS(asymmetricKeyParameter, bArr, bArr4, bArr5, asymmetricCipherKeyPair);
        } else {
            throw new IllegalStateException("Unknown mode");
        }
        byte[] bArr9 = bArr2;
        byte[] bArr10 = bArr3;
        return new byte[][]{hPKEContextWithEncapsulation.seal(bArr2, bArr3), hPKEContextWithEncapsulation.getEncapsulation()};
    }

    public byte[][] sendExport(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        HPKEContextWithEncapsulation hPKEContextWithEncapsulation;
        AsymmetricKeyParameter asymmetricKeyParameter2 = asymmetricKeyParameter;
        byte[] bArr5 = bArr;
        byte b = this.mode;
        if (b == 0) {
            hPKEContextWithEncapsulation = setupBaseS(asymmetricKeyParameter, bArr);
        } else if (b == 1) {
            byte[] bArr6 = bArr3;
            byte[] bArr7 = bArr4;
            hPKEContextWithEncapsulation = SetupPSKS(asymmetricKeyParameter, bArr, bArr3, bArr4);
        } else if (b == 2) {
            hPKEContextWithEncapsulation = setupAuthS(asymmetricKeyParameter, bArr, asymmetricCipherKeyPair);
        } else if (b == 3) {
            hPKEContextWithEncapsulation = setupAuthPSKS(asymmetricKeyParameter, bArr, bArr3, bArr4, asymmetricCipherKeyPair);
        } else {
            throw new IllegalStateException("Unknown mode");
        }
        byte[] bArr8 = bArr2;
        int i2 = i;
        return new byte[][]{hPKEContextWithEncapsulation.encapsulation, hPKEContextWithEncapsulation.export(bArr2, i)};
    }

    public byte[] serializePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.dhkem.SerializePrivateKey(asymmetricKeyParameter);
    }

    public byte[] serializePublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.dhkem.SerializePublicKey(asymmetricKeyParameter);
    }

    public HPKEContext setupAuthPSKR(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, byte[] bArr2, byte[] bArr3, byte[] bArr4, AsymmetricKeyParameter asymmetricKeyParameter) {
        return keySchedule((byte) 3, this.dhkem.AuthDecap(bArr, asymmetricCipherKeyPair, asymmetricKeyParameter), bArr2, bArr3, bArr4);
    }

    public HPKEContextWithEncapsulation setupAuthPSKS(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr, byte[] bArr2, byte[] bArr3, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        byte[][] AuthEncap = this.dhkem.AuthEncap(asymmetricKeyParameter, asymmetricCipherKeyPair);
        return new HPKEContextWithEncapsulation(keySchedule((byte) 3, AuthEncap[0], bArr, bArr2, bArr3), AuthEncap[1]);
    }

    public HPKEContext setupAuthR(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, byte[] bArr2, AsymmetricKeyParameter asymmetricKeyParameter) {
        return keySchedule((byte) 2, this.dhkem.AuthDecap(bArr, asymmetricCipherKeyPair, asymmetricKeyParameter), bArr2, this.default_psk, this.default_psk_id);
    }

    public HPKEContextWithEncapsulation setupAuthS(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        byte[][] AuthEncap = this.dhkem.AuthEncap(asymmetricKeyParameter, asymmetricCipherKeyPair);
        return new HPKEContextWithEncapsulation(keySchedule((byte) 2, AuthEncap[0], bArr, this.default_psk, this.default_psk_id), AuthEncap[1]);
    }

    public HPKEContext setupBaseR(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, byte[] bArr2) {
        return keySchedule((byte) 0, this.dhkem.Decap(bArr, asymmetricCipherKeyPair), bArr2, this.default_psk, this.default_psk_id);
    }

    public HPKEContextWithEncapsulation setupBaseS(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        byte[][] Encap = this.dhkem.Encap(asymmetricKeyParameter);
        return new HPKEContextWithEncapsulation(keySchedule((byte) 0, Encap[0], bArr, this.default_psk, this.default_psk_id), Encap[1]);
    }

    public HPKEContextWithEncapsulation setupBaseS(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        byte[][] Encap = this.dhkem.Encap(asymmetricKeyParameter, asymmetricCipherKeyPair);
        return new HPKEContextWithEncapsulation(keySchedule((byte) 0, Encap[0], bArr, this.default_psk, this.default_psk_id), Encap[1]);
    }

    public HPKEContext setupPSKR(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        return keySchedule((byte) 1, this.dhkem.Decap(bArr, asymmetricCipherKeyPair), bArr2, bArr3, bArr4);
    }
}
