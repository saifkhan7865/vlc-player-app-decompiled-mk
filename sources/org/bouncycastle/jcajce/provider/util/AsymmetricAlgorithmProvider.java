package org.bouncycastle.jcajce.provider.util;

import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;

public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider {
    /* access modifiers changed from: protected */
    public void addCipherAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("Cipher." + str, str2);
        if (aSN1ObjectIdentifier != null) {
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + aSN1ObjectIdentifier, str);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.OID." + aSN1ObjectIdentifier, str);
        }
    }

    /* access modifiers changed from: protected */
    public void addKeyFactoryAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("KeyFactory." + str, str2);
        if (aSN1ObjectIdentifier != null) {
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, str);
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory.OID." + aSN1ObjectIdentifier, str);
            configurableProvider.addKeyInfoConverter(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
        }
    }

    /* access modifiers changed from: protected */
    public void addKeyGeneratorAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("KeyGenerator." + str, str2);
        if (aSN1ObjectIdentifier != null) {
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + aSN1ObjectIdentifier, str);
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.OID." + aSN1ObjectIdentifier, str);
        }
    }

    /* access modifiers changed from: protected */
    public void addKeyPairGeneratorAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("KeyPairGenerator." + str, str2);
        if (aSN1ObjectIdentifier != null) {
            configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator." + aSN1ObjectIdentifier, str);
            configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator.OID." + aSN1ObjectIdentifier, str);
        }
    }

    /* access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3) {
        addSignatureAlgorithm(configurableProvider, str, str2, str3, (ASN1ObjectIdentifier) null);
    }

    /* access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str4 = str + "WITH" + str2;
        String str5 = str + "with" + str2;
        String str6 = str + "With" + str2;
        configurableProvider.addAlgorithm("Signature." + str4, str3);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str5, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str6, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + (str + "/" + str2), str4);
        if (aSN1ObjectIdentifier != null) {
            configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, str4);
            configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str4);
        }
    }

    /* access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3, ASN1ObjectIdentifier aSN1ObjectIdentifier, Map<String, String> map) {
        String str4 = str + "WITH" + str2;
        String str5 = str + "with" + str2;
        String str6 = str + "With" + str2;
        configurableProvider.addAlgorithm("Signature." + str4, str3);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str5, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str6, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + (str + "/" + str2), str4);
        if (aSN1ObjectIdentifier != null) {
            configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, str4);
            configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str4);
        }
        configurableProvider.addAttributes("Signature." + str4, map);
    }

    /* access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("Signature." + str, str2);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str);
    }

    /* access modifiers changed from: protected */
    public void addSignatureAlias(ConfigurableProvider configurableProvider, String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str);
    }

    /* access modifiers changed from: protected */
    public void registerKeyFactoryOid(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory.OID." + aSN1ObjectIdentifier, str);
        configurableProvider.addKeyInfoConverter(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    /* access modifiers changed from: protected */
    public void registerOid(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator." + aSN1ObjectIdentifier, str);
        configurableProvider.addKeyInfoConverter(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    /* access modifiers changed from: protected */
    public void registerOidAlgorithmParameterGenerator(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, str);
    }

    /* access modifiers changed from: protected */
    public void registerOidAlgorithmParameters(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, str);
    }
}
