package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi;

public class Kyber {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.kyber.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi");
            addKeyFactoryAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi$Kyber512", BCObjectIdentifiers.kyber512, new KyberKeyFactorySpi.Kyber512());
            addKeyFactoryAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi$Kyber768", BCObjectIdentifiers.kyber768, new KyberKeyFactorySpi.Kyber768());
            addKeyFactoryAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi$Kyber1024", BCObjectIdentifiers.kyber1024, new KyberKeyFactorySpi.Kyber1024());
            configurableProvider.addAlgorithm("KeyPairGenerator.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi");
            addKeyPairGeneratorAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi$Kyber512", BCObjectIdentifiers.kyber512);
            addKeyPairGeneratorAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi$Kyber768", BCObjectIdentifiers.kyber768);
            addKeyPairGeneratorAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi$Kyber1024", BCObjectIdentifiers.kyber1024);
            configurableProvider.addAlgorithm("KeyGenerator.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi");
            addKeyGeneratorAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi$Kyber512", BCObjectIdentifiers.kyber512);
            addKeyGeneratorAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi$Kyber768", BCObjectIdentifiers.kyber768);
            addKeyGeneratorAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi$Kyber1024", BCObjectIdentifiers.kyber1024);
            KyberKeyFactorySpi kyberKeyFactorySpi = new KyberKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_kyber, "KYBER");
            addCipherAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Kyber512", BCObjectIdentifiers.kyber512);
            addCipherAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Kyber768", BCObjectIdentifiers.kyber768);
            addCipherAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Kyber1024", BCObjectIdentifiers.kyber1024);
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_kyber, "KYBER", kyberKeyFactorySpi);
        }
    }
}
