package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.ntru.NTRUKeyFactorySpi;

public class NTRUPrime {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.ntruprime.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.NTRULPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.NTRULPRimeKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.NTRULPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.NTRULPRimeKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.NTRULPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.NTRULPRimeKeyGeneratorSpi");
            NTRUKeyFactorySpi nTRUKeyFactorySpi = new NTRUKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.NTRULPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.NTRULPRimeCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_ntrulprime, "NTRU");
            registerOid(configurableProvider, BCObjectIdentifiers.ntrulpr653, "NTRULPRIME", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntrulpr761, "NTRULPRIME", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntrulpr857, "NTRULPRIME", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntrulpr953, "NTRULPRIME", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntrulpr1013, "NTRULPRIME", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntrulpr1277, "NTRULPRIME", nTRUKeyFactorySpi);
            configurableProvider.addAlgorithm("KeyFactory.SNTRUPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.SNTRUPrimeKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SNTRUPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.SNTRUPrimeKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.SNTRUPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.SNTRUPrimeKeyGeneratorSpi");
            NTRUKeyFactorySpi nTRUKeyFactorySpi2 = new NTRUKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.SNTRUPRIME", "org.bouncycastle.pqc.jcajce.provider.ntruprime.SNTRUPrimeCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_sntruprime, "NTRU");
            registerOid(configurableProvider, BCObjectIdentifiers.sntrup653, "SNTRUPRIME", nTRUKeyFactorySpi2);
            registerOid(configurableProvider, BCObjectIdentifiers.sntrup761, "SNTRUPRIME", nTRUKeyFactorySpi2);
            registerOid(configurableProvider, BCObjectIdentifiers.sntrup857, "SNTRUPRIME", nTRUKeyFactorySpi2);
            registerOid(configurableProvider, BCObjectIdentifiers.sntrup953, "SNTRUPRIME", nTRUKeyFactorySpi2);
            registerOid(configurableProvider, BCObjectIdentifiers.sntrup1013, "SNTRUPRIME", nTRUKeyFactorySpi2);
            registerOid(configurableProvider, BCObjectIdentifiers.sntrup1277, "SNTRUPRIME", nTRUKeyFactorySpi2);
        }
    }
}
