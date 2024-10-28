package org.bouncycastle.operator.bc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.Blake3Digest;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_256Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_512Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.RIPEMD256Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.operator.OperatorCreationException;

public class BcDefaultDigestProvider implements BcDigestProvider {
    public static final BcDigestProvider INSTANCE = new BcDefaultDigestProvider();
    private static final Map lookup = createTable();

    private static class AdjustedXof implements Xof {
        private final int length;
        private final Xof xof;

        AdjustedXof(Xof xof2, int i) {
            this.xof = xof2;
            this.length = i;
        }

        public int doFinal(byte[] bArr, int i) {
            return doFinal(bArr, i, getDigestSize());
        }

        public int doFinal(byte[] bArr, int i, int i2) {
            return this.xof.doFinal(bArr, i, i2);
        }

        public int doOutput(byte[] bArr, int i, int i2) {
            return this.xof.doOutput(bArr, i, i2);
        }

        public String getAlgorithmName() {
            return this.xof.getAlgorithmName() + "-" + this.length;
        }

        public int getByteLength() {
            return this.xof.getByteLength();
        }

        public int getDigestSize() {
            return (this.length + 7) / 8;
        }

        public void reset() {
            this.xof.reset();
        }

        public void update(byte b) {
            this.xof.update(b);
        }

        public void update(byte[] bArr, int i, int i2) {
            this.xof.update(bArr, i, i2);
        }
    }

    private BcDefaultDigestProvider() {
    }

    private static Map createTable() {
        HashMap hashMap = new HashMap();
        hashMap.put(OIWObjectIdentifiers.idSHA1, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA1Digest();
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha224, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA224Digest();
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha256, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA256Digest();
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha384, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA384Digest();
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha512, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA512Digest();
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha3_224, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest((int) BERTags.FLAGS);
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha3_256, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest(256);
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha3_384, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest((int) KyberEngine.KyberPolyBytes);
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_sha3_512, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest(512);
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_shake128, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHAKEDigest(128);
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_shake256, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHAKEDigest(256);
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_shake128_len, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new AdjustedXof(new SHAKEDigest(128), ASN1Integer.getInstance(algorithmIdentifier.getParameters()).intValueExact());
            }
        });
        hashMap.put(NISTObjectIdentifiers.id_shake256_len, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new AdjustedXof(new SHAKEDigest(256), ASN1Integer.getInstance(algorithmIdentifier.getParameters()).intValueExact());
            }
        });
        hashMap.put(PKCSObjectIdentifiers.md5, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new MD5Digest();
            }
        });
        hashMap.put(PKCSObjectIdentifiers.md4, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new MD4Digest();
            }
        });
        hashMap.put(PKCSObjectIdentifiers.md2, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new MD2Digest();
            }
        });
        hashMap.put(CryptoProObjectIdentifiers.gostR3411, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new GOST3411Digest();
            }
        });
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new GOST3411_2012_256Digest();
            }
        });
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new GOST3411_2012_512Digest();
            }
        });
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd128, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new RIPEMD128Digest();
            }
        });
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd160, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new RIPEMD160Digest();
            }
        });
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd256, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new RIPEMD256Digest();
            }
        });
        hashMap.put(GMObjectIdentifiers.sm3, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SM3Digest();
            }
        });
        hashMap.put(MiscObjectIdentifiers.blake3_256, new BcDigestProvider() {
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new Blake3Digest(256);
            }
        });
        return Collections.unmodifiableMap(hashMap);
    }

    public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        BcDigestProvider bcDigestProvider = (BcDigestProvider) lookup.get(algorithmIdentifier.getAlgorithm());
        if (bcDigestProvider != null) {
            return bcDigestProvider.get(algorithmIdentifier);
        }
        throw new OperatorCreationException("cannot recognise digest");
    }
}
