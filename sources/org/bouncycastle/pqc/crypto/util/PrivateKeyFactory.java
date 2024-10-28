package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.CMCEPrivateKey;
import org.bouncycastle.pqc.asn1.FalconPrivateKey;
import org.bouncycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.SPHINCSPLUSPrivateKey;
import org.bouncycastle.pqc.asn1.SPHINCSPLUSPublicKey;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPrivateKey;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.bike.BIKEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.saber.SABERPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.util.PublicKeyFactory;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLAPrivateKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class PrivateKeyFactory {
    private static short[] convert(byte[] bArr) {
        int length = bArr.length / 2;
        short[] sArr = new short[length];
        for (int i = 0; i != length; i++) {
            sArr[i] = Pack.littleEndianToShort(bArr, i * 2);
        }
        return sArr;
    }

    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    public static AsymmetricKeyParameter createKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        if (privateKeyInfo != null) {
            AlgorithmIdentifier privateKeyAlgorithm = privateKeyInfo.getPrivateKeyAlgorithm();
            ASN1ObjectIdentifier algorithm = privateKeyAlgorithm.getAlgorithm();
            if (algorithm.on(PQCObjectIdentifiers.qTESLA)) {
                return new QTESLAPrivateKeyParameters(Utils.qTeslaLookupSecurityCategory(privateKeyAlgorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
            } else if (algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.sphincs256)) {
                return new SPHINCSPrivateKeyParameters(ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets(), Utils.sphincs256LookupTreeAlgName(SPHINCS256KeyParams.getInstance(privateKeyAlgorithm.getParameters())));
            } else {
                if (algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.newHope)) {
                    return new NHPrivateKeyParameters(convert(ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets()));
                }
                if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_hss_lms_hashsig)) {
                    byte[] octets = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
                    ASN1BitString publicKeyData = privateKeyInfo.getPublicKeyData();
                    if (Pack.bigEndianToInt(octets, 0) == 1) {
                        if (publicKeyData == null) {
                            return LMSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length));
                        }
                        byte[] octets2 = publicKeyData.getOctets();
                        return LMSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length), Arrays.copyOfRange(octets2, 4, octets2.length));
                    } else if (publicKeyData == null) {
                        return HSSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length));
                    } else {
                        return HSSPrivateKeyParameters.getInstance(Arrays.copyOfRange(octets, 4, octets.length), publicKeyData.getOctets());
                    }
                } else if (algorithm.on(BCObjectIdentifiers.sphincsPlus) || algorithm.on(BCObjectIdentifiers.sphincsPlus_interop)) {
                    SPHINCSPlusParameters sphincsPlusParamsLookup = Utils.sphincsPlusParamsLookup(algorithm);
                    ASN1Encodable parsePrivateKey = privateKeyInfo.parsePrivateKey();
                    if (!(parsePrivateKey instanceof ASN1Sequence)) {
                        return new SPHINCSPlusPrivateKeyParameters(sphincsPlusParamsLookup, ASN1OctetString.getInstance(parsePrivateKey).getOctets());
                    }
                    SPHINCSPLUSPrivateKey instance = SPHINCSPLUSPrivateKey.getInstance(parsePrivateKey);
                    SPHINCSPLUSPublicKey publicKey = instance.getPublicKey();
                    return new SPHINCSPlusPrivateKeyParameters(sphincsPlusParamsLookup, instance.getSkseed(), instance.getSkprf(), publicKey.getPkseed(), publicKey.getPkroot());
                } else if (algorithm.on(BCObjectIdentifiers.picnic)) {
                    return new PicnicPrivateKeyParameters(Utils.picnicParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_mceliece)) {
                    CMCEPrivateKey instance2 = CMCEPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    return new CMCEPrivateKeyParameters(Utils.mcElieceParamsLookup(algorithm), instance2.getDelta(), instance2.getC(), instance2.getG(), instance2.getAlpha(), instance2.getS());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_frodo)) {
                    return new FrodoPrivateKeyParameters(Utils.frodoParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_saber)) {
                    return new SABERPrivateKeyParameters(Utils.saberParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_ntru)) {
                    return new NTRUPrivateKeyParameters(Utils.ntruParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_kyber)) {
                    return new KyberPrivateKeyParameters(Utils.kyberParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_ntrulprime)) {
                    ASN1Sequence instance3 = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                    return new NTRULPRimePrivateKeyParameters(Utils.ntrulprimeParamsLookup(algorithm), ASN1OctetString.getInstance(instance3.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(instance3.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(instance3.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(instance3.getObjectAt(3)).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_sntruprime)) {
                    ASN1Sequence instance4 = ASN1Sequence.getInstance(privateKeyInfo.parsePrivateKey());
                    return new SNTRUPrimePrivateKeyParameters(Utils.sntruprimeParamsLookup(algorithm), ASN1OctetString.getInstance(instance4.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(instance4.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(instance4.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(instance4.getObjectAt(3)).getOctets(), ASN1OctetString.getInstance(instance4.getObjectAt(4)).getOctets());
                } else if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium2) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium3) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.dilithium5)) {
                    ASN1Encodable parsePrivateKey2 = privateKeyInfo.parsePrivateKey();
                    DilithiumParameters dilithiumParamsLookup = Utils.dilithiumParamsLookup(algorithm);
                    if (parsePrivateKey2 instanceof ASN1Sequence) {
                        ASN1Sequence instance5 = ASN1Sequence.getInstance(parsePrivateKey2);
                        int intValueExact = ASN1Integer.getInstance(instance5.getObjectAt(0)).intValueExact();
                        if (intValueExact != 0) {
                            throw new IOException("unknown private key version: " + intValueExact);
                        } else if (privateKeyInfo.getPublicKeyData() == null) {
                            return new DilithiumPrivateKeyParameters(dilithiumParamsLookup, ASN1BitString.getInstance(instance5.getObjectAt(1)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(2)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(3)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(4)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(5)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(6)).getOctets(), (byte[]) null);
                        } else {
                            return new DilithiumPrivateKeyParameters(dilithiumParamsLookup, ASN1BitString.getInstance(instance5.getObjectAt(1)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(2)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(3)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(4)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(5)).getOctets(), ASN1BitString.getInstance(instance5.getObjectAt(6)).getOctets(), PublicKeyFactory.DilithiumConverter.getPublicKeyParams(dilithiumParamsLookup, privateKeyInfo.getPublicKeyData()).getT1());
                        }
                    } else if (parsePrivateKey2 instanceof DEROctetString) {
                        byte[] octets3 = ASN1OctetString.getInstance(parsePrivateKey2).getOctets();
                        return privateKeyInfo.getPublicKeyData() != null ? new DilithiumPrivateKeyParameters(dilithiumParamsLookup, octets3, PublicKeyFactory.DilithiumConverter.getPublicKeyParams(dilithiumParamsLookup, privateKeyInfo.getPublicKeyData())) : new DilithiumPrivateKeyParameters(dilithiumParamsLookup, octets3, (DilithiumPublicKeyParameters) null);
                    } else {
                        throw new IOException("not supported");
                    }
                } else if (algorithm.equals((ASN1Primitive) BCObjectIdentifiers.falcon_512) || algorithm.equals((ASN1Primitive) BCObjectIdentifiers.falcon_1024)) {
                    FalconPrivateKey instance6 = FalconPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    return new FalconPrivateKeyParameters(Utils.falconParamsLookup(algorithm), instance6.getf(), instance6.getG(), instance6.getF(), instance6.getPublicKey().getH());
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_bike)) {
                    byte[] octets4 = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
                    BIKEParameters bikeParamsLookup = Utils.bikeParamsLookup(algorithm);
                    return new BIKEPrivateKeyParameters(bikeParamsLookup, Arrays.copyOfRange(octets4, 0, bikeParamsLookup.getRByte()), Arrays.copyOfRange(octets4, bikeParamsLookup.getRByte(), bikeParamsLookup.getRByte() * 2), Arrays.copyOfRange(octets4, bikeParamsLookup.getRByte() * 2, octets4.length));
                } else if (algorithm.on(BCObjectIdentifiers.pqc_kem_hqc)) {
                    return new HQCPrivateKeyParameters(Utils.hqcParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.on(BCObjectIdentifiers.rainbow)) {
                    return new RainbowPrivateKeyParameters(Utils.rainbowParamsLookup(algorithm), ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets());
                } else if (algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.xmss)) {
                    XMSSKeyParams instance7 = XMSSKeyParams.getInstance(privateKeyAlgorithm.getParameters());
                    ASN1ObjectIdentifier algorithm2 = instance7.getTreeDigest().getAlgorithm();
                    XMSSPrivateKey instance8 = XMSSPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    try {
                        XMSSPrivateKeyParameters.Builder withRoot = new XMSSPrivateKeyParameters.Builder(new XMSSParameters(instance7.getHeight(), Utils.getDigest(algorithm2))).withIndex(instance8.getIndex()).withSecretKeySeed(instance8.getSecretKeySeed()).withSecretKeyPRF(instance8.getSecretKeyPRF()).withPublicSeed(instance8.getPublicSeed()).withRoot(instance8.getRoot());
                        if (instance8.getVersion() != 0) {
                            withRoot.withMaxIndex(instance8.getMaxIndex());
                        }
                        if (instance8.getBdsState() != null) {
                            withRoot.withBDSState(((BDS) XMSSUtil.deserialize(instance8.getBdsState(), BDS.class)).withWOTSDigest(algorithm2));
                        }
                        return withRoot.build();
                    } catch (ClassNotFoundException e) {
                        throw new IOException("ClassNotFoundException processing BDS state: " + e.getMessage());
                    }
                } else if (algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.xmss_mt)) {
                    XMSSMTKeyParams instance9 = XMSSMTKeyParams.getInstance(privateKeyAlgorithm.getParameters());
                    ASN1ObjectIdentifier algorithm3 = instance9.getTreeDigest().getAlgorithm();
                    try {
                        XMSSMTPrivateKey instance10 = XMSSMTPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                        XMSSMTPrivateKeyParameters.Builder withRoot2 = new XMSSMTPrivateKeyParameters.Builder(new XMSSMTParameters(instance9.getHeight(), instance9.getLayers(), Utils.getDigest(algorithm3))).withIndex(instance10.getIndex()).withSecretKeySeed(instance10.getSecretKeySeed()).withSecretKeyPRF(instance10.getSecretKeyPRF()).withPublicSeed(instance10.getPublicSeed()).withRoot(instance10.getRoot());
                        if (instance10.getVersion() != 0) {
                            withRoot2.withMaxIndex(instance10.getMaxIndex());
                        }
                        if (instance10.getBdsState() != null) {
                            withRoot2.withBDSState(((BDSStateMap) XMSSUtil.deserialize(instance10.getBdsState(), BDSStateMap.class)).withWOTSDigest(algorithm3));
                        }
                        return withRoot2.build();
                    } catch (ClassNotFoundException e2) {
                        throw new IOException("ClassNotFoundException processing BDS state: " + e2.getMessage());
                    }
                } else if (algorithm.equals((ASN1Primitive) PQCObjectIdentifiers.mcElieceCca2)) {
                    McElieceCCA2PrivateKey instance11 = McElieceCCA2PrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    return new McElieceCCA2PrivateKeyParameters(instance11.getN(), instance11.getK(), instance11.getField(), instance11.getGoppaPoly(), instance11.getP(), Utils.getDigestName(instance11.getDigest().getAlgorithm()));
                } else {
                    throw new RuntimeException("algorithm identifier in private key not recognised");
                }
            }
        } else {
            throw new IllegalArgumentException("keyInfo array null");
        }
    }

    public static AsymmetricKeyParameter createKey(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("privateKeyInfoData array null");
        } else if (bArr.length != 0) {
            return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
        } else {
            throw new IllegalArgumentException("privateKeyInfoData array empty");
        }
    }
}
