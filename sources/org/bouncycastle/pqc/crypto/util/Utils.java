package org.bouncycastle.pqc.crypto.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Integers;

class Utils {
    static final AlgorithmIdentifier AlgID_qTESLA_p_I = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_I);
    static final AlgorithmIdentifier AlgID_qTESLA_p_III = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_III);
    static final AlgorithmIdentifier SPHINCS_SHA3_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
    static final AlgorithmIdentifier SPHINCS_SHA512_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);
    static final AlgorithmIdentifier XMSS_SHA256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
    static final AlgorithmIdentifier XMSS_SHA512 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
    static final AlgorithmIdentifier XMSS_SHAKE128 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake128);
    static final AlgorithmIdentifier XMSS_SHAKE256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake256);
    static final Map bikeOids;
    static final Map bikeParams;
    static final Map categories;
    static final Map dilithiumOids;
    static final Map dilithiumParams;
    static final Map falconOids;
    static final Map falconParams;
    static final Map frodoOids;
    static final Map frodoParams;
    static final Map hqcOids;
    static final Map hqcParams;
    static final Map kyberOids;
    static final Map kyberParams;
    static final Map mcElieceOids;
    static final Map mcElieceParams;
    static final Map ntruOids;
    static final Map ntruParams;
    static final Map ntruprimeOids;
    static final Map ntruprimeParams;
    static final Map picnicOids;
    static final Map picnicParams;
    static final Map rainbowOids;
    static final Map rainbowParams;
    static final Map saberOids;
    static final Map saberParams;
    static final Map sikeOids = new HashMap();
    static final Map sikeParams = new HashMap();
    static final Map sntruprimeOids;
    static final Map sntruprimeParams;
    static final Map sphincsPlusOids;
    static final Map sphincsPlusParams;

    static {
        HashMap hashMap = new HashMap();
        categories = hashMap;
        HashMap hashMap2 = new HashMap();
        picnicOids = hashMap2;
        HashMap hashMap3 = new HashMap();
        picnicParams = hashMap3;
        HashMap hashMap4 = new HashMap();
        frodoOids = hashMap4;
        HashMap hashMap5 = new HashMap();
        frodoParams = hashMap5;
        HashMap hashMap6 = new HashMap();
        saberOids = hashMap6;
        HashMap hashMap7 = new HashMap();
        saberParams = hashMap7;
        HashMap hashMap8 = new HashMap();
        mcElieceOids = hashMap8;
        HashMap hashMap9 = new HashMap();
        mcElieceParams = hashMap9;
        HashMap hashMap10 = new HashMap();
        sphincsPlusOids = hashMap10;
        HashMap hashMap11 = new HashMap();
        sphincsPlusParams = hashMap11;
        HashMap hashMap12 = new HashMap();
        ntruOids = hashMap12;
        HashMap hashMap13 = new HashMap();
        ntruParams = hashMap13;
        HashMap hashMap14 = new HashMap();
        falconOids = hashMap14;
        HashMap hashMap15 = new HashMap();
        falconParams = hashMap15;
        HashMap hashMap16 = new HashMap();
        kyberOids = hashMap16;
        HashMap hashMap17 = hashMap11;
        HashMap hashMap18 = new HashMap();
        kyberParams = hashMap18;
        HashMap hashMap19 = hashMap10;
        HashMap hashMap20 = new HashMap();
        ntruprimeOids = hashMap20;
        HashMap hashMap21 = hashMap20;
        HashMap hashMap22 = new HashMap();
        ntruprimeParams = hashMap22;
        HashMap hashMap23 = hashMap22;
        HashMap hashMap24 = new HashMap();
        sntruprimeOids = hashMap24;
        HashMap hashMap25 = hashMap24;
        HashMap hashMap26 = new HashMap();
        sntruprimeParams = hashMap26;
        HashMap hashMap27 = hashMap26;
        HashMap hashMap28 = new HashMap();
        dilithiumOids = hashMap28;
        HashMap hashMap29 = hashMap28;
        HashMap hashMap30 = new HashMap();
        dilithiumParams = hashMap30;
        HashMap hashMap31 = hashMap30;
        HashMap hashMap32 = new HashMap();
        bikeOids = hashMap32;
        HashMap hashMap33 = hashMap32;
        HashMap hashMap34 = new HashMap();
        bikeParams = hashMap34;
        HashMap hashMap35 = hashMap34;
        HashMap hashMap36 = new HashMap();
        hqcOids = hashMap36;
        HashMap hashMap37 = hashMap36;
        HashMap hashMap38 = new HashMap();
        hqcParams = hashMap38;
        HashMap hashMap39 = hashMap38;
        HashMap hashMap40 = new HashMap();
        rainbowOids = hashMap40;
        HashMap hashMap41 = hashMap40;
        HashMap hashMap42 = new HashMap();
        rainbowParams = hashMap42;
        hashMap.put(PQCObjectIdentifiers.qTESLA_p_I, Integers.valueOf(5));
        hashMap.put(PQCObjectIdentifiers.qTESLA_p_III, Integers.valueOf(6));
        hashMap8.put(CMCEParameters.mceliece348864r3, BCObjectIdentifiers.mceliece348864_r3);
        hashMap8.put(CMCEParameters.mceliece348864fr3, BCObjectIdentifiers.mceliece348864f_r3);
        hashMap8.put(CMCEParameters.mceliece460896r3, BCObjectIdentifiers.mceliece460896_r3);
        hashMap8.put(CMCEParameters.mceliece460896fr3, BCObjectIdentifiers.mceliece460896f_r3);
        hashMap8.put(CMCEParameters.mceliece6688128r3, BCObjectIdentifiers.mceliece6688128_r3);
        hashMap8.put(CMCEParameters.mceliece6688128fr3, BCObjectIdentifiers.mceliece6688128f_r3);
        hashMap8.put(CMCEParameters.mceliece6960119r3, BCObjectIdentifiers.mceliece6960119_r3);
        hashMap8.put(CMCEParameters.mceliece6960119fr3, BCObjectIdentifiers.mceliece6960119f_r3);
        hashMap8.put(CMCEParameters.mceliece8192128r3, BCObjectIdentifiers.mceliece8192128_r3);
        hashMap8.put(CMCEParameters.mceliece8192128fr3, BCObjectIdentifiers.mceliece8192128f_r3);
        hashMap9.put(BCObjectIdentifiers.mceliece348864_r3, CMCEParameters.mceliece348864r3);
        hashMap9.put(BCObjectIdentifiers.mceliece348864f_r3, CMCEParameters.mceliece348864fr3);
        hashMap9.put(BCObjectIdentifiers.mceliece460896_r3, CMCEParameters.mceliece460896r3);
        hashMap9.put(BCObjectIdentifiers.mceliece460896f_r3, CMCEParameters.mceliece460896fr3);
        hashMap9.put(BCObjectIdentifiers.mceliece6688128_r3, CMCEParameters.mceliece6688128r3);
        hashMap9.put(BCObjectIdentifiers.mceliece6688128f_r3, CMCEParameters.mceliece6688128fr3);
        hashMap9.put(BCObjectIdentifiers.mceliece6960119_r3, CMCEParameters.mceliece6960119r3);
        hashMap9.put(BCObjectIdentifiers.mceliece6960119f_r3, CMCEParameters.mceliece6960119fr3);
        hashMap9.put(BCObjectIdentifiers.mceliece8192128_r3, CMCEParameters.mceliece8192128r3);
        hashMap9.put(BCObjectIdentifiers.mceliece8192128f_r3, CMCEParameters.mceliece8192128fr3);
        hashMap4.put(FrodoParameters.frodokem640aes, BCObjectIdentifiers.frodokem640aes);
        hashMap4.put(FrodoParameters.frodokem640shake, BCObjectIdentifiers.frodokem640shake);
        hashMap4.put(FrodoParameters.frodokem976aes, BCObjectIdentifiers.frodokem976aes);
        hashMap4.put(FrodoParameters.frodokem976shake, BCObjectIdentifiers.frodokem976shake);
        hashMap4.put(FrodoParameters.frodokem1344aes, BCObjectIdentifiers.frodokem1344aes);
        hashMap4.put(FrodoParameters.frodokem1344shake, BCObjectIdentifiers.frodokem1344shake);
        hashMap5.put(BCObjectIdentifiers.frodokem640aes, FrodoParameters.frodokem640aes);
        hashMap5.put(BCObjectIdentifiers.frodokem640shake, FrodoParameters.frodokem640shake);
        hashMap5.put(BCObjectIdentifiers.frodokem976aes, FrodoParameters.frodokem976aes);
        hashMap5.put(BCObjectIdentifiers.frodokem976shake, FrodoParameters.frodokem976shake);
        hashMap5.put(BCObjectIdentifiers.frodokem1344aes, FrodoParameters.frodokem1344aes);
        hashMap5.put(BCObjectIdentifiers.frodokem1344shake, FrodoParameters.frodokem1344shake);
        hashMap6.put(SABERParameters.lightsaberkem128r3, BCObjectIdentifiers.lightsaberkem128r3);
        hashMap6.put(SABERParameters.saberkem128r3, BCObjectIdentifiers.saberkem128r3);
        hashMap6.put(SABERParameters.firesaberkem128r3, BCObjectIdentifiers.firesaberkem128r3);
        hashMap6.put(SABERParameters.lightsaberkem192r3, BCObjectIdentifiers.lightsaberkem192r3);
        hashMap6.put(SABERParameters.saberkem192r3, BCObjectIdentifiers.saberkem192r3);
        hashMap6.put(SABERParameters.firesaberkem192r3, BCObjectIdentifiers.firesaberkem192r3);
        hashMap6.put(SABERParameters.lightsaberkem256r3, BCObjectIdentifiers.lightsaberkem256r3);
        hashMap6.put(SABERParameters.saberkem256r3, BCObjectIdentifiers.saberkem256r3);
        hashMap6.put(SABERParameters.firesaberkem256r3, BCObjectIdentifiers.firesaberkem256r3);
        hashMap6.put(SABERParameters.ulightsaberkemr3, BCObjectIdentifiers.ulightsaberkemr3);
        hashMap6.put(SABERParameters.usaberkemr3, BCObjectIdentifiers.usaberkemr3);
        hashMap6.put(SABERParameters.ufiresaberkemr3, BCObjectIdentifiers.ufiresaberkemr3);
        hashMap6.put(SABERParameters.lightsaberkem90sr3, BCObjectIdentifiers.lightsaberkem90sr3);
        hashMap6.put(SABERParameters.saberkem90sr3, BCObjectIdentifiers.saberkem90sr3);
        hashMap6.put(SABERParameters.firesaberkem90sr3, BCObjectIdentifiers.firesaberkem90sr3);
        hashMap6.put(SABERParameters.ulightsaberkem90sr3, BCObjectIdentifiers.ulightsaberkem90sr3);
        hashMap6.put(SABERParameters.usaberkem90sr3, BCObjectIdentifiers.usaberkem90sr3);
        hashMap6.put(SABERParameters.ufiresaberkem90sr3, BCObjectIdentifiers.ufiresaberkem90sr3);
        hashMap7.put(BCObjectIdentifiers.lightsaberkem128r3, SABERParameters.lightsaberkem128r3);
        hashMap7.put(BCObjectIdentifiers.saberkem128r3, SABERParameters.saberkem128r3);
        hashMap7.put(BCObjectIdentifiers.firesaberkem128r3, SABERParameters.firesaberkem128r3);
        hashMap7.put(BCObjectIdentifiers.lightsaberkem192r3, SABERParameters.lightsaberkem192r3);
        hashMap7.put(BCObjectIdentifiers.saberkem192r3, SABERParameters.saberkem192r3);
        hashMap7.put(BCObjectIdentifiers.firesaberkem192r3, SABERParameters.firesaberkem192r3);
        hashMap7.put(BCObjectIdentifiers.lightsaberkem256r3, SABERParameters.lightsaberkem256r3);
        hashMap7.put(BCObjectIdentifiers.saberkem256r3, SABERParameters.saberkem256r3);
        hashMap7.put(BCObjectIdentifiers.firesaberkem256r3, SABERParameters.firesaberkem256r3);
        hashMap7.put(BCObjectIdentifiers.ulightsaberkemr3, SABERParameters.ulightsaberkemr3);
        hashMap7.put(BCObjectIdentifiers.usaberkemr3, SABERParameters.usaberkemr3);
        hashMap7.put(BCObjectIdentifiers.ufiresaberkemr3, SABERParameters.ufiresaberkemr3);
        hashMap7.put(BCObjectIdentifiers.lightsaberkem90sr3, SABERParameters.lightsaberkem90sr3);
        hashMap7.put(BCObjectIdentifiers.saberkem90sr3, SABERParameters.saberkem90sr3);
        hashMap7.put(BCObjectIdentifiers.firesaberkem90sr3, SABERParameters.firesaberkem90sr3);
        hashMap7.put(BCObjectIdentifiers.ulightsaberkem90sr3, SABERParameters.ulightsaberkem90sr3);
        hashMap7.put(BCObjectIdentifiers.usaberkem90sr3, SABERParameters.usaberkem90sr3);
        hashMap7.put(BCObjectIdentifiers.ufiresaberkem90sr3, SABERParameters.ufiresaberkem90sr3);
        hashMap2.put(PicnicParameters.picnicl1fs, BCObjectIdentifiers.picnicl1fs);
        hashMap2.put(PicnicParameters.picnicl1ur, BCObjectIdentifiers.picnicl1ur);
        hashMap2.put(PicnicParameters.picnicl3fs, BCObjectIdentifiers.picnicl3fs);
        hashMap2.put(PicnicParameters.picnicl3ur, BCObjectIdentifiers.picnicl3ur);
        hashMap2.put(PicnicParameters.picnicl5fs, BCObjectIdentifiers.picnicl5fs);
        hashMap2.put(PicnicParameters.picnicl5ur, BCObjectIdentifiers.picnicl5ur);
        hashMap2.put(PicnicParameters.picnic3l1, BCObjectIdentifiers.picnic3l1);
        hashMap2.put(PicnicParameters.picnic3l3, BCObjectIdentifiers.picnic3l3);
        hashMap2.put(PicnicParameters.picnic3l5, BCObjectIdentifiers.picnic3l5);
        hashMap2.put(PicnicParameters.picnicl1full, BCObjectIdentifiers.picnicl1full);
        hashMap2.put(PicnicParameters.picnicl3full, BCObjectIdentifiers.picnicl3full);
        hashMap2.put(PicnicParameters.picnicl5full, BCObjectIdentifiers.picnicl5full);
        hashMap3.put(BCObjectIdentifiers.picnicl1fs, PicnicParameters.picnicl1fs);
        hashMap3.put(BCObjectIdentifiers.picnicl1ur, PicnicParameters.picnicl1ur);
        hashMap3.put(BCObjectIdentifiers.picnicl3fs, PicnicParameters.picnicl3fs);
        hashMap3.put(BCObjectIdentifiers.picnicl3ur, PicnicParameters.picnicl3ur);
        hashMap3.put(BCObjectIdentifiers.picnicl5fs, PicnicParameters.picnicl5fs);
        hashMap3.put(BCObjectIdentifiers.picnicl5ur, PicnicParameters.picnicl5ur);
        hashMap3.put(BCObjectIdentifiers.picnic3l1, PicnicParameters.picnic3l1);
        hashMap3.put(BCObjectIdentifiers.picnic3l3, PicnicParameters.picnic3l3);
        hashMap3.put(BCObjectIdentifiers.picnic3l5, PicnicParameters.picnic3l5);
        hashMap3.put(BCObjectIdentifiers.picnicl1full, PicnicParameters.picnicl1full);
        hashMap3.put(BCObjectIdentifiers.picnicl3full, PicnicParameters.picnicl3full);
        hashMap3.put(BCObjectIdentifiers.picnicl5full, PicnicParameters.picnicl5full);
        hashMap12.put(NTRUParameters.ntruhps2048509, BCObjectIdentifiers.ntruhps2048509);
        hashMap12.put(NTRUParameters.ntruhps2048677, BCObjectIdentifiers.ntruhps2048677);
        hashMap12.put(NTRUParameters.ntruhps4096821, BCObjectIdentifiers.ntruhps4096821);
        hashMap12.put(NTRUParameters.ntruhrss701, BCObjectIdentifiers.ntruhrss701);
        hashMap13.put(BCObjectIdentifiers.ntruhps2048509, NTRUParameters.ntruhps2048509);
        hashMap13.put(BCObjectIdentifiers.ntruhps2048677, NTRUParameters.ntruhps2048677);
        hashMap13.put(BCObjectIdentifiers.ntruhps4096821, NTRUParameters.ntruhps4096821);
        hashMap13.put(BCObjectIdentifiers.ntruhrss701, NTRUParameters.ntruhrss701);
        hashMap14.put(FalconParameters.falcon_512, BCObjectIdentifiers.falcon_512);
        hashMap14.put(FalconParameters.falcon_1024, BCObjectIdentifiers.falcon_1024);
        hashMap15.put(BCObjectIdentifiers.falcon_512, FalconParameters.falcon_512);
        hashMap15.put(BCObjectIdentifiers.falcon_1024, FalconParameters.falcon_1024);
        hashMap16.put(KyberParameters.kyber512, BCObjectIdentifiers.kyber512);
        hashMap16.put(KyberParameters.kyber768, BCObjectIdentifiers.kyber768);
        hashMap16.put(KyberParameters.kyber1024, BCObjectIdentifiers.kyber1024);
        HashMap hashMap43 = hashMap18;
        hashMap43.put(BCObjectIdentifiers.kyber512, KyberParameters.kyber512);
        hashMap43.put(BCObjectIdentifiers.kyber768, KyberParameters.kyber768);
        hashMap43.put(BCObjectIdentifiers.kyber1024, KyberParameters.kyber1024);
        HashMap hashMap44 = hashMap21;
        hashMap44.put(NTRULPRimeParameters.ntrulpr653, BCObjectIdentifiers.ntrulpr653);
        hashMap44.put(NTRULPRimeParameters.ntrulpr761, BCObjectIdentifiers.ntrulpr761);
        hashMap44.put(NTRULPRimeParameters.ntrulpr857, BCObjectIdentifiers.ntrulpr857);
        hashMap44.put(NTRULPRimeParameters.ntrulpr953, BCObjectIdentifiers.ntrulpr953);
        hashMap44.put(NTRULPRimeParameters.ntrulpr1013, BCObjectIdentifiers.ntrulpr1013);
        hashMap44.put(NTRULPRimeParameters.ntrulpr1277, BCObjectIdentifiers.ntrulpr1277);
        HashMap hashMap45 = hashMap23;
        hashMap45.put(BCObjectIdentifiers.ntrulpr653, NTRULPRimeParameters.ntrulpr653);
        hashMap45.put(BCObjectIdentifiers.ntrulpr761, NTRULPRimeParameters.ntrulpr761);
        hashMap45.put(BCObjectIdentifiers.ntrulpr857, NTRULPRimeParameters.ntrulpr857);
        hashMap45.put(BCObjectIdentifiers.ntrulpr953, NTRULPRimeParameters.ntrulpr953);
        hashMap45.put(BCObjectIdentifiers.ntrulpr1013, NTRULPRimeParameters.ntrulpr1013);
        hashMap45.put(BCObjectIdentifiers.ntrulpr1277, NTRULPRimeParameters.ntrulpr1277);
        HashMap hashMap46 = hashMap25;
        hashMap46.put(SNTRUPrimeParameters.sntrup653, BCObjectIdentifiers.sntrup653);
        hashMap46.put(SNTRUPrimeParameters.sntrup761, BCObjectIdentifiers.sntrup761);
        hashMap46.put(SNTRUPrimeParameters.sntrup857, BCObjectIdentifiers.sntrup857);
        hashMap46.put(SNTRUPrimeParameters.sntrup953, BCObjectIdentifiers.sntrup953);
        hashMap46.put(SNTRUPrimeParameters.sntrup1013, BCObjectIdentifiers.sntrup1013);
        hashMap46.put(SNTRUPrimeParameters.sntrup1277, BCObjectIdentifiers.sntrup1277);
        HashMap hashMap47 = hashMap27;
        hashMap47.put(BCObjectIdentifiers.sntrup653, SNTRUPrimeParameters.sntrup653);
        hashMap47.put(BCObjectIdentifiers.sntrup761, SNTRUPrimeParameters.sntrup761);
        hashMap47.put(BCObjectIdentifiers.sntrup857, SNTRUPrimeParameters.sntrup857);
        hashMap47.put(BCObjectIdentifiers.sntrup953, SNTRUPrimeParameters.sntrup953);
        hashMap47.put(BCObjectIdentifiers.sntrup1013, SNTRUPrimeParameters.sntrup1013);
        hashMap47.put(BCObjectIdentifiers.sntrup1277, SNTRUPrimeParameters.sntrup1277);
        HashMap hashMap48 = hashMap29;
        hashMap48.put(DilithiumParameters.dilithium2, BCObjectIdentifiers.dilithium2);
        hashMap48.put(DilithiumParameters.dilithium3, BCObjectIdentifiers.dilithium3);
        hashMap48.put(DilithiumParameters.dilithium5, BCObjectIdentifiers.dilithium5);
        HashMap hashMap49 = hashMap31;
        hashMap49.put(BCObjectIdentifiers.dilithium2, DilithiumParameters.dilithium2);
        hashMap49.put(BCObjectIdentifiers.dilithium3, DilithiumParameters.dilithium3);
        hashMap49.put(BCObjectIdentifiers.dilithium5, DilithiumParameters.dilithium5);
        HashMap hashMap50 = hashMap35;
        hashMap50.put(BCObjectIdentifiers.bike128, BIKEParameters.bike128);
        hashMap50.put(BCObjectIdentifiers.bike192, BIKEParameters.bike192);
        hashMap50.put(BCObjectIdentifiers.bike256, BIKEParameters.bike256);
        HashMap hashMap51 = hashMap33;
        hashMap51.put(BIKEParameters.bike128, BCObjectIdentifiers.bike128);
        hashMap51.put(BIKEParameters.bike192, BCObjectIdentifiers.bike192);
        hashMap51.put(BIKEParameters.bike256, BCObjectIdentifiers.bike256);
        HashMap hashMap52 = hashMap39;
        hashMap52.put(BCObjectIdentifiers.hqc128, HQCParameters.hqc128);
        hashMap52.put(BCObjectIdentifiers.hqc192, HQCParameters.hqc192);
        hashMap52.put(BCObjectIdentifiers.hqc256, HQCParameters.hqc256);
        HashMap hashMap53 = hashMap37;
        hashMap53.put(HQCParameters.hqc128, BCObjectIdentifiers.hqc128);
        hashMap53.put(HQCParameters.hqc192, BCObjectIdentifiers.hqc192);
        hashMap53.put(HQCParameters.hqc256, BCObjectIdentifiers.hqc256);
        HashMap hashMap54 = hashMap42;
        hashMap54.put(BCObjectIdentifiers.rainbow_III_classic, RainbowParameters.rainbowIIIclassic);
        hashMap54.put(BCObjectIdentifiers.rainbow_III_circumzenithal, RainbowParameters.rainbowIIIcircumzenithal);
        hashMap54.put(BCObjectIdentifiers.rainbow_III_compressed, RainbowParameters.rainbowIIIcompressed);
        hashMap54.put(BCObjectIdentifiers.rainbow_V_classic, RainbowParameters.rainbowVclassic);
        hashMap54.put(BCObjectIdentifiers.rainbow_V_circumzenithal, RainbowParameters.rainbowVcircumzenithal);
        hashMap54.put(BCObjectIdentifiers.rainbow_V_compressed, RainbowParameters.rainbowVcompressed);
        HashMap hashMap55 = hashMap41;
        hashMap55.put(RainbowParameters.rainbowIIIclassic, BCObjectIdentifiers.rainbow_III_classic);
        hashMap55.put(RainbowParameters.rainbowIIIcircumzenithal, BCObjectIdentifiers.rainbow_III_circumzenithal);
        hashMap55.put(RainbowParameters.rainbowIIIcompressed, BCObjectIdentifiers.rainbow_III_compressed);
        hashMap55.put(RainbowParameters.rainbowVclassic, BCObjectIdentifiers.rainbow_V_classic);
        hashMap55.put(RainbowParameters.rainbowVcircumzenithal, BCObjectIdentifiers.rainbow_V_circumzenithal);
        hashMap55.put(RainbowParameters.rainbowVcompressed, BCObjectIdentifiers.rainbow_V_compressed);
        HashMap hashMap56 = hashMap19;
        hashMap56.put(SPHINCSPlusParameters.sha2_128s_robust, BCObjectIdentifiers.sphincsPlus_sha2_128s_r3);
        hashMap56.put(SPHINCSPlusParameters.sha2_128f_robust, BCObjectIdentifiers.sphincsPlus_sha2_128f_r3);
        hashMap56.put(SPHINCSPlusParameters.shake_128s_robust, BCObjectIdentifiers.sphincsPlus_shake_128s_r3);
        hashMap56.put(SPHINCSPlusParameters.shake_128f_robust, BCObjectIdentifiers.sphincsPlus_shake_128f_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_128s, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_128f, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3);
        hashMap56.put(SPHINCSPlusParameters.sha2_192s_robust, BCObjectIdentifiers.sphincsPlus_sha2_192s_r3);
        hashMap56.put(SPHINCSPlusParameters.sha2_192f_robust, BCObjectIdentifiers.sphincsPlus_sha2_192f_r3);
        hashMap56.put(SPHINCSPlusParameters.shake_192s_robust, BCObjectIdentifiers.sphincsPlus_shake_192s_r3);
        hashMap56.put(SPHINCSPlusParameters.shake_192f_robust, BCObjectIdentifiers.sphincsPlus_shake_192f_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_192s, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_192f, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3);
        hashMap56.put(SPHINCSPlusParameters.sha2_256s_robust, BCObjectIdentifiers.sphincsPlus_sha2_256s_r3);
        hashMap56.put(SPHINCSPlusParameters.sha2_256f_robust, BCObjectIdentifiers.sphincsPlus_sha2_256f_r3);
        hashMap56.put(SPHINCSPlusParameters.shake_256s_robust, BCObjectIdentifiers.sphincsPlus_shake_256s_r3);
        hashMap56.put(SPHINCSPlusParameters.shake_256f_robust, BCObjectIdentifiers.sphincsPlus_shake_256f_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_256s, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_256f, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3);
        hashMap56.put(SPHINCSPlusParameters.haraka_128s_simple, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple);
        hashMap56.put(SPHINCSPlusParameters.haraka_128f_simple, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple);
        hashMap56.put(SPHINCSPlusParameters.haraka_192s_simple, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple);
        hashMap56.put(SPHINCSPlusParameters.haraka_192f_simple, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple);
        hashMap56.put(SPHINCSPlusParameters.haraka_256s_simple, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple);
        hashMap56.put(SPHINCSPlusParameters.haraka_256f_simple, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple);
        hashMap56.put(SPHINCSPlusParameters.sha2_128s, BCObjectIdentifiers.sphincsPlus_sha2_128s);
        hashMap56.put(SPHINCSPlusParameters.sha2_128f, BCObjectIdentifiers.sphincsPlus_sha2_128f);
        hashMap56.put(SPHINCSPlusParameters.shake_128s, BCObjectIdentifiers.sphincsPlus_shake_128s);
        hashMap56.put(SPHINCSPlusParameters.shake_128f, BCObjectIdentifiers.sphincsPlus_shake_128f);
        hashMap56.put(SPHINCSPlusParameters.sha2_192s, BCObjectIdentifiers.sphincsPlus_sha2_192s);
        hashMap56.put(SPHINCSPlusParameters.sha2_192f, BCObjectIdentifiers.sphincsPlus_sha2_192f);
        hashMap56.put(SPHINCSPlusParameters.shake_192s, BCObjectIdentifiers.sphincsPlus_shake_192s);
        hashMap56.put(SPHINCSPlusParameters.shake_192f, BCObjectIdentifiers.sphincsPlus_shake_192f);
        hashMap56.put(SPHINCSPlusParameters.sha2_256s, BCObjectIdentifiers.sphincsPlus_sha2_256s);
        hashMap56.put(SPHINCSPlusParameters.sha2_256f, BCObjectIdentifiers.sphincsPlus_sha2_256f);
        hashMap56.put(SPHINCSPlusParameters.shake_256s, BCObjectIdentifiers.sphincsPlus_shake_256s);
        hashMap56.put(SPHINCSPlusParameters.shake_256f, BCObjectIdentifiers.sphincsPlus_shake_256f);
        HashMap hashMap57 = hashMap17;
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_128s, SPHINCSPlusParameters.sha2_128s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_128f, SPHINCSPlusParameters.sha2_128f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_128s, SPHINCSPlusParameters.shake_128s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_128f, SPHINCSPlusParameters.shake_128f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_192s, SPHINCSPlusParameters.sha2_192s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_192f, SPHINCSPlusParameters.sha2_192f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_192s, SPHINCSPlusParameters.shake_192s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_192f, SPHINCSPlusParameters.shake_192f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_256s, SPHINCSPlusParameters.sha2_256s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_256f, SPHINCSPlusParameters.sha2_256f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_256s, SPHINCSPlusParameters.shake_256s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_256f, SPHINCSPlusParameters.shake_256f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3, SPHINCSPlusParameters.sha2_128s_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3, SPHINCSPlusParameters.sha2_128f_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_128s_r3, SPHINCSPlusParameters.shake_128s_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_128f_r3, SPHINCSPlusParameters.shake_128f_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3, SPHINCSPlusParameters.haraka_128s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3, SPHINCSPlusParameters.haraka_128f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3, SPHINCSPlusParameters.sha2_192s_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3, SPHINCSPlusParameters.sha2_192f_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_192s_r3, SPHINCSPlusParameters.shake_192s_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_192f_r3, SPHINCSPlusParameters.shake_192f_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3, SPHINCSPlusParameters.haraka_192s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3, SPHINCSPlusParameters.haraka_192f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3, SPHINCSPlusParameters.sha2_256s_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3, SPHINCSPlusParameters.sha2_256f_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_256s_r3, SPHINCSPlusParameters.shake_256s_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_256f_r3, SPHINCSPlusParameters.shake_256f_robust);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3, SPHINCSPlusParameters.haraka_256s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3, SPHINCSPlusParameters.haraka_256f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3_simple, SPHINCSPlusParameters.sha2_128s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3_simple, SPHINCSPlusParameters.sha2_128f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_128s_r3_simple, SPHINCSPlusParameters.shake_128s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_128f_r3_simple, SPHINCSPlusParameters.shake_128f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple, SPHINCSPlusParameters.haraka_128s_simple);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple, SPHINCSPlusParameters.haraka_128f_simple);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3_simple, SPHINCSPlusParameters.sha2_192s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3_simple, SPHINCSPlusParameters.sha2_192f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_192s_r3_simple, SPHINCSPlusParameters.shake_192s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_192f_r3_simple, SPHINCSPlusParameters.shake_192f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple, SPHINCSPlusParameters.haraka_192s_simple);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple, SPHINCSPlusParameters.haraka_192f_simple);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3_simple, SPHINCSPlusParameters.sha2_256s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3_simple, SPHINCSPlusParameters.sha2_256f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_256s_r3_simple, SPHINCSPlusParameters.shake_256s);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_shake_256f_r3_simple, SPHINCSPlusParameters.shake_256f);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple, SPHINCSPlusParameters.haraka_256s_simple);
        hashMap57.put(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple, SPHINCSPlusParameters.haraka_256f_simple);
    }

    Utils() {
    }

    static ASN1ObjectIdentifier bikeOidLookup(BIKEParameters bIKEParameters) {
        return (ASN1ObjectIdentifier) bikeOids.get(bIKEParameters);
    }

    static BIKEParameters bikeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (BIKEParameters) bikeParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier dilithiumOidLookup(DilithiumParameters dilithiumParameters) {
        return (ASN1ObjectIdentifier) dilithiumOids.get(dilithiumParameters);
    }

    static DilithiumParameters dilithiumParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (DilithiumParameters) dilithiumParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier falconOidLookup(FalconParameters falconParameters) {
        return (ASN1ObjectIdentifier) falconOids.get(falconParameters);
    }

    static FalconParameters falconParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (FalconParameters) falconParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier frodoOidLookup(FrodoParameters frodoParameters) {
        return (ASN1ObjectIdentifier) frodoOids.get(frodoParameters);
    }

    static FrodoParameters frodoParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (FrodoParameters) frodoParams.get(aSN1ObjectIdentifier);
    }

    public static AlgorithmIdentifier getAlgorithmIdentifier(String str) {
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA1)) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA224)) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
        }
        if (str.equals("SHA-256")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA384)) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
        }
        if (str.equals("SHA-512")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }

    static Digest getDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) OIWObjectIdentifiers.idSHA1)) {
            return McElieceCCA2KeyGenParameterSpec.SHA1;
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha224)) {
            return McElieceCCA2KeyGenParameterSpec.SHA224;
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return "SHA-256";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha384)) {
            return McElieceCCA2KeyGenParameterSpec.SHA384;
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return "SHA-512";
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier hqcOidLookup(HQCParameters hQCParameters) {
        return (ASN1ObjectIdentifier) hqcOids.get(hQCParameters);
    }

    static HQCParameters hqcParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (HQCParameters) hqcParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier kyberOidLookup(KyberParameters kyberParameters) {
        return (ASN1ObjectIdentifier) kyberOids.get(kyberParameters);
    }

    static KyberParameters kyberParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (KyberParameters) kyberParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier mcElieceOidLookup(CMCEParameters cMCEParameters) {
        return (ASN1ObjectIdentifier) mcElieceOids.get(cMCEParameters);
    }

    static CMCEParameters mcElieceParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (CMCEParameters) mcElieceParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier ntruOidLookup(NTRUParameters nTRUParameters) {
        return (ASN1ObjectIdentifier) ntruOids.get(nTRUParameters);
    }

    static NTRUParameters ntruParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (NTRUParameters) ntruParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier ntrulprimeOidLookup(NTRULPRimeParameters nTRULPRimeParameters) {
        return (ASN1ObjectIdentifier) ntruprimeOids.get(nTRULPRimeParameters);
    }

    static NTRULPRimeParameters ntrulprimeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (NTRULPRimeParameters) ntruprimeParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier picnicOidLookup(PicnicParameters picnicParameters) {
        return (ASN1ObjectIdentifier) picnicOids.get(picnicParameters);
    }

    static PicnicParameters picnicParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (PicnicParameters) picnicParams.get(aSN1ObjectIdentifier);
    }

    static AlgorithmIdentifier qTeslaLookupAlgID(int i) {
        if (i == 5) {
            return AlgID_qTESLA_p_I;
        }
        if (i == 6) {
            return AlgID_qTESLA_p_III;
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }

    static int qTeslaLookupSecurityCategory(AlgorithmIdentifier algorithmIdentifier) {
        return ((Integer) categories.get(algorithmIdentifier.getAlgorithm())).intValue();
    }

    static ASN1ObjectIdentifier rainbowOidLookup(RainbowParameters rainbowParameters) {
        return (ASN1ObjectIdentifier) rainbowOids.get(rainbowParameters);
    }

    static RainbowParameters rainbowParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (RainbowParameters) rainbowParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier saberOidLookup(SABERParameters sABERParameters) {
        return (ASN1ObjectIdentifier) saberOids.get(sABERParameters);
    }

    static SABERParameters saberParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SABERParameters) saberParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier sntruprimeOidLookup(SNTRUPrimeParameters sNTRUPrimeParameters) {
        return (ASN1ObjectIdentifier) sntruprimeOids.get(sNTRUPrimeParameters);
    }

    static SNTRUPrimeParameters sntruprimeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SNTRUPrimeParameters) sntruprimeParams.get(aSN1ObjectIdentifier);
    }

    static AlgorithmIdentifier sphincs256LookupTreeAlgID(String str) {
        if (str.equals("SHA3-256")) {
            return SPHINCS_SHA3_256;
        }
        if (str.equals(SPHINCSKeyParameters.SHA512_256)) {
            return SPHINCS_SHA512_256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }

    static String sphincs256LookupTreeAlgName(SPHINCS256KeyParams sPHINCS256KeyParams) {
        AlgorithmIdentifier treeDigest = sPHINCS256KeyParams.getTreeDigest();
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA3_256.getAlgorithm())) {
            return "SHA3-256";
        }
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA512_256.getAlgorithm())) {
            return SPHINCSKeyParameters.SHA512_256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + treeDigest.getAlgorithm());
    }

    static ASN1ObjectIdentifier sphincsPlusOidLookup(SPHINCSPlusParameters sPHINCSPlusParameters) {
        return (ASN1ObjectIdentifier) sphincsPlusOids.get(sPHINCSPlusParameters);
    }

    static SPHINCSPlusParameters sphincsPlusParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SPHINCSPlusParameters) sphincsPlusParams.get(aSN1ObjectIdentifier);
    }

    static AlgorithmIdentifier xmssLookupTreeAlgID(String str) {
        if (str.equals("SHA-256")) {
            return XMSS_SHA256;
        }
        if (str.equals("SHA-512")) {
            return XMSS_SHA512;
        }
        if (str.equals("SHAKE128")) {
            return XMSS_SHAKE128;
        }
        if (str.equals("SHAKE256")) {
            return XMSS_SHAKE256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }
}
