package org.bouncycastle.crypto.hpke;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.agreement.ECDHCBasicAgreement;
import org.bouncycastle.crypto.agreement.XDHBasicAgreement;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.generators.X448KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X448KeyGenerationParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.math.ec.custom.sec.SecP256R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP384R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP521R1Curve;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

class DHKEM {
    private int Nsecret;
    private int Nsk;
    private BasicAgreement agreement;
    private byte bitmask;
    ECDomainParameters domainParams;
    private HKDF hkdf;
    private final short kemId;
    private AsymmetricCipherKeyPairGenerator kpGen;

    protected DHKEM(short s) {
        AsymmetricCipherKeyPairGenerator x25519KeyPairGenerator;
        KeyGenerationParameters x25519KeyGenerationParameters;
        this.kemId = s;
        if (s == 32) {
            this.hkdf = new HKDF(1);
            this.agreement = new XDHBasicAgreement();
            this.Nsecret = 32;
            this.Nsk = 32;
            x25519KeyPairGenerator = new X25519KeyPairGenerator();
            this.kpGen = x25519KeyPairGenerator;
            x25519KeyGenerationParameters = new X25519KeyGenerationParameters(new SecureRandom());
        } else if (s != 33) {
            switch (s) {
                case 16:
                    this.hkdf = new HKDF(1);
                    SecP256R1Curve secP256R1Curve = new SecP256R1Curve();
                    this.domainParams = new ECDomainParameters(secP256R1Curve, secP256R1Curve.createPoint(new BigInteger(1, Hex.decode("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296")), new BigInteger(1, Hex.decode("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5"))), secP256R1Curve.getOrder(), secP256R1Curve.getCofactor(), Hex.decode("c49d360886e704936a6678e1139d26b7819f7e90"));
                    this.agreement = new ECDHCBasicAgreement();
                    this.bitmask = -1;
                    this.Nsk = 32;
                    this.Nsecret = 32;
                    x25519KeyPairGenerator = new ECKeyPairGenerator();
                    this.kpGen = x25519KeyPairGenerator;
                    x25519KeyGenerationParameters = new ECKeyGenerationParameters(this.domainParams, new SecureRandom());
                    break;
                case 17:
                    this.hkdf = new HKDF(2);
                    SecP384R1Curve secP384R1Curve = new SecP384R1Curve();
                    this.domainParams = new ECDomainParameters(secP384R1Curve, secP384R1Curve.createPoint(new BigInteger(1, Hex.decode("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7")), new BigInteger(1, Hex.decode("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f"))), secP384R1Curve.getOrder(), secP384R1Curve.getCofactor(), Hex.decode("a335926aa319a27a1d00896a6773a4827acdac73"));
                    this.agreement = new ECDHCBasicAgreement();
                    this.bitmask = -1;
                    this.Nsk = 48;
                    this.Nsecret = 48;
                    x25519KeyPairGenerator = new ECKeyPairGenerator();
                    this.kpGen = x25519KeyPairGenerator;
                    x25519KeyGenerationParameters = new ECKeyGenerationParameters(this.domainParams, new SecureRandom());
                    break;
                case 18:
                    this.hkdf = new HKDF(3);
                    SecP521R1Curve secP521R1Curve = new SecP521R1Curve();
                    this.domainParams = new ECDomainParameters(secP521R1Curve, secP521R1Curve.createPoint(new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16), new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16)), secP521R1Curve.getOrder(), secP521R1Curve.getCofactor(), Hex.decode("d09e8800291cb85396cc6717393284aaa0da64ba"));
                    this.agreement = new ECDHCBasicAgreement();
                    this.bitmask = 1;
                    this.Nsk = 66;
                    this.Nsecret = 64;
                    x25519KeyPairGenerator = new ECKeyPairGenerator();
                    this.kpGen = x25519KeyPairGenerator;
                    x25519KeyGenerationParameters = new ECKeyGenerationParameters(this.domainParams, new SecureRandom());
                    break;
                default:
                    throw new IllegalArgumentException("invalid kem id");
            }
        } else {
            this.hkdf = new HKDF(3);
            this.agreement = new XDHBasicAgreement();
            this.Nsecret = 64;
            this.Nsk = 56;
            x25519KeyPairGenerator = new X448KeyPairGenerator();
            this.kpGen = x25519KeyPairGenerator;
            x25519KeyGenerationParameters = new X448KeyGenerationParameters(new SecureRandom());
        }
        x25519KeyPairGenerator.init(x25519KeyGenerationParameters);
    }

    private byte[] ExtractAndExpand(byte[] bArr, byte[] bArr2) {
        byte[] concatenate = Arrays.concatenate(Strings.toByteArray("KEM"), Pack.shortToBigEndian(this.kemId));
        return this.hkdf.LabeledExpand(this.hkdf.LabeledExtract((byte[]) null, concatenate, "eae_prk", bArr), concatenate, "shared_secret", bArr2, this.Nsecret);
    }

    private boolean ValidateSk(BigInteger bigInteger) {
        BigInteger n = this.domainParams.getN();
        return bigInteger.compareTo(BigInteger.valueOf(1)) >= 0 && bigInteger.compareTo(n) < 0 && WNafUtil.getNafWeight(bigInteger) >= (n.bitLength() >>> 2);
    }

    private byte[] formatBigIntegerBytes(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (bArr.length <= i) {
            System.arraycopy(bArr, 0, bArr2, i - bArr.length, bArr.length);
        } else {
            System.arraycopy(bArr, bArr.length - i, bArr2, 0, i);
        }
        return bArr2;
    }

    /* access modifiers changed from: protected */
    public byte[] AuthDecap(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, AsymmetricKeyParameter asymmetricKeyParameter) {
        AsymmetricKeyParameter DeserializePublicKey = DeserializePublicKey(bArr);
        this.agreement.init(asymmetricCipherKeyPair.getPrivate());
        byte[] formatBigIntegerBytes = formatBigIntegerBytes(this.agreement.calculateAgreement(DeserializePublicKey).toByteArray(), this.agreement.getFieldSize());
        this.agreement.init(asymmetricCipherKeyPair.getPrivate());
        return ExtractAndExpand(Arrays.concatenate(formatBigIntegerBytes, formatBigIntegerBytes(this.agreement.calculateAgreement(asymmetricKeyParameter).toByteArray(), this.agreement.getFieldSize())), Arrays.concatenate(bArr, SerializePublicKey(asymmetricCipherKeyPair.getPublic()), SerializePublicKey(asymmetricKeyParameter)));
    }

    /* access modifiers changed from: protected */
    public byte[][] AuthEncap(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        AsymmetricCipherKeyPair generateKeyPair = this.kpGen.generateKeyPair();
        this.agreement.init(generateKeyPair.getPrivate());
        byte[] formatBigIntegerBytes = formatBigIntegerBytes(this.agreement.calculateAgreement(asymmetricKeyParameter).toByteArray(), this.agreement.getFieldSize());
        this.agreement.init(asymmetricCipherKeyPair.getPrivate());
        byte[] concatenate = Arrays.concatenate(formatBigIntegerBytes, formatBigIntegerBytes(this.agreement.calculateAgreement(asymmetricKeyParameter).toByteArray(), this.agreement.getFieldSize()));
        byte[] SerializePublicKey = SerializePublicKey(generateKeyPair.getPublic());
        return new byte[][]{ExtractAndExpand(concatenate, Arrays.concatenate(SerializePublicKey, SerializePublicKey(asymmetricKeyParameter), SerializePublicKey(asymmetricCipherKeyPair.getPublic()))), SerializePublicKey};
    }

    /* access modifiers changed from: protected */
    public byte[] Decap(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        AsymmetricKeyParameter DeserializePublicKey = DeserializePublicKey(bArr);
        this.agreement.init(asymmetricCipherKeyPair.getPrivate());
        return ExtractAndExpand(formatBigIntegerBytes(this.agreement.calculateAgreement(DeserializePublicKey).toByteArray(), this.agreement.getFieldSize()), Arrays.concatenate(bArr, SerializePublicKey(asymmetricCipherKeyPair.getPublic())));
    }

    public AsymmetricCipherKeyPair DeriveKeyPair(byte[] bArr) {
        if (bArr.length >= this.Nsk) {
            byte[] concatenate = Arrays.concatenate(Strings.toByteArray("KEM"), Pack.shortToBigEndian(this.kemId));
            short s = this.kemId;
            if (s == 32) {
                X25519PrivateKeyParameters x25519PrivateKeyParameters = new X25519PrivateKeyParameters(this.hkdf.LabeledExpand(this.hkdf.LabeledExtract((byte[]) null, concatenate, "dkp_prk", bArr), concatenate, "sk", (byte[]) null, this.Nsk));
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) x25519PrivateKeyParameters.generatePublicKey(), (AsymmetricKeyParameter) x25519PrivateKeyParameters);
            } else if (s != 33) {
                switch (s) {
                    case 16:
                    case 17:
                    case 18:
                        byte[] LabeledExtract = this.hkdf.LabeledExtract((byte[]) null, concatenate, "dkp_prk", bArr);
                        for (int i = 0; i <= 255; i++) {
                            byte[] bArr2 = LabeledExtract;
                            byte[] bArr3 = concatenate;
                            byte[] LabeledExpand = this.hkdf.LabeledExpand(bArr2, bArr3, "candidate", new byte[]{(byte) i}, this.Nsk);
                            LabeledExpand[0] = (byte) (LabeledExpand[0] & this.bitmask);
                            BigInteger bigInteger = new BigInteger(1, LabeledExpand);
                            if (ValidateSk(bigInteger)) {
                                ECPoint multiply = new FixedPointCombMultiplier().multiply(this.domainParams.getG(), bigInteger);
                                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(multiply, this.domainParams), (AsymmetricKeyParameter) new ECPrivateKeyParameters(bigInteger, this.domainParams));
                            }
                        }
                        throw new IllegalStateException("DeriveKeyPairError");
                    default:
                        throw new IllegalStateException("invalid kem id");
                }
            } else {
                X448PrivateKeyParameters x448PrivateKeyParameters = new X448PrivateKeyParameters(this.hkdf.LabeledExpand(this.hkdf.LabeledExtract((byte[]) null, concatenate, "dkp_prk", bArr), concatenate, "sk", (byte[]) null, this.Nsk));
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) x448PrivateKeyParameters.generatePublicKey(), (AsymmetricKeyParameter) x448PrivateKeyParameters);
            }
        } else {
            throw new IllegalArgumentException("input keying material should have length at least " + this.Nsk + " bytes");
        }
    }

    public AsymmetricCipherKeyPair DeserializePrivateKey(byte[] bArr, byte[] bArr2) {
        AsymmetricKeyParameter DeserializePublicKey = DeserializePublicKey(bArr2);
        short s = this.kemId;
        if (s == 32) {
            return new AsymmetricCipherKeyPair(DeserializePublicKey, (AsymmetricKeyParameter) new X25519PrivateKeyParameters(bArr));
        }
        if (s == 33) {
            return new AsymmetricCipherKeyPair(DeserializePublicKey, (AsymmetricKeyParameter) new X448PrivateKeyParameters(bArr));
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                return new AsymmetricCipherKeyPair(DeserializePublicKey, (AsymmetricKeyParameter) new ECPrivateKeyParameters(new BigInteger(1, bArr), ((ECPublicKeyParameters) DeserializePublicKey).getParameters()));
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    public AsymmetricKeyParameter DeserializePublicKey(byte[] bArr) {
        short s = this.kemId;
        if (s == 32) {
            return new X25519PublicKeyParameters(bArr);
        }
        if (s == 33) {
            return new X448PublicKeyParameters(bArr);
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                return new ECPublicKeyParameters(this.domainParams.getCurve().decodePoint(bArr), this.domainParams);
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    /* access modifiers changed from: protected */
    public byte[][] Encap(AsymmetricKeyParameter asymmetricKeyParameter) {
        return Encap(asymmetricKeyParameter, this.kpGen.generateKeyPair());
    }

    /* access modifiers changed from: protected */
    public byte[][] Encap(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        this.agreement.init(asymmetricCipherKeyPair.getPrivate());
        byte[] formatBigIntegerBytes = formatBigIntegerBytes(this.agreement.calculateAgreement(asymmetricKeyParameter).toByteArray(), this.agreement.getFieldSize());
        byte[] SerializePublicKey = SerializePublicKey(asymmetricCipherKeyPair.getPublic());
        return new byte[][]{ExtractAndExpand(formatBigIntegerBytes, Arrays.concatenate(SerializePublicKey, SerializePublicKey(asymmetricKeyParameter))), SerializePublicKey};
    }

    public AsymmetricCipherKeyPair GeneratePrivateKey() {
        return this.kpGen.generateKeyPair();
    }

    public byte[] SerializePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        short s = this.kemId;
        if (s == 32) {
            return ((X25519PrivateKeyParameters) asymmetricKeyParameter).getEncoded();
        }
        if (s == 33) {
            return ((X448PrivateKeyParameters) asymmetricKeyParameter).getEncoded();
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                return formatBigIntegerBytes(((ECPrivateKeyParameters) asymmetricKeyParameter).getD().toByteArray(), this.Nsk);
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    public byte[] SerializePublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        short s = this.kemId;
        if (s == 32) {
            return ((X25519PublicKeyParameters) asymmetricKeyParameter).getEncoded();
        }
        if (s == 33) {
            return ((X448PublicKeyParameters) asymmetricKeyParameter).getEncoded();
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                return ((ECPublicKeyParameters) asymmetricKeyParameter).getQ().getEncoded(false);
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }
}
