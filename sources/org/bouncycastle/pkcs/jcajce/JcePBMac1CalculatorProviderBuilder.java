package org.bouncycastle.pkcs.jcajce;

import java.security.Provider;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PBMAC1Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.PBEMacCalculatorProvider;

public class JcePBMac1CalculatorProviderBuilder {
    /* access modifiers changed from: private */
    public JcaJceHelper helper = new DefaultJcaJceHelper();

    public PBEMacCalculatorProvider build() {
        return new PBEMacCalculatorProvider() {
            public MacCalculator get(AlgorithmIdentifier algorithmIdentifier, char[] cArr) throws OperatorCreationException {
                if (PKCSObjectIdentifiers.id_PBMAC1.equals((ASN1Primitive) algorithmIdentifier.getAlgorithm())) {
                    return new JcePBMac1CalculatorBuilder(PBMAC1Params.getInstance(algorithmIdentifier.getParameters())).setHelper(JcePBMac1CalculatorProviderBuilder.this.helper).build(cArr);
                }
                throw new OperatorCreationException("protection algorithm not PB mac based");
            }
        };
    }

    public JcePBMac1CalculatorProviderBuilder setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePBMac1CalculatorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }
}
