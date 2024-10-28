package org.bouncycastle.operator.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;

public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private Map extraMappings = new HashMap();
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private PrivateKey privKey;
    private boolean unwrappedKeyMustBeEncodable;

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.privKey = privateKey;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        if (r4.length != 0) goto L_0x0056;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004c A[SYNTHETIC, Splitter:B:13:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[ExcHandler: IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:13:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.operator.GenericKey generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier r7, byte[] r8) throws org.bouncycastle.operator.OperatorException {
        /*
            r6 = this;
            org.bouncycastle.operator.jcajce.OperatorHelper r0 = r6.helper     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r6.getAlgorithmIdentifier()     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            java.util.Map r2 = r6.extraMappings     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            javax.crypto.Cipher r0 = r0.createAsymmetricWrapper(r1, r2)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            org.bouncycastle.operator.jcajce.OperatorHelper r1 = r6.helper     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = r6.getAlgorithmIdentifier()     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            java.security.AlgorithmParameters r1 = r1.createAlgorithmParameters(r2)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            r2 = 4
            r3 = 0
            if (r1 == 0) goto L_0x0034
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r4 = r6.getAlgorithmIdentifier()     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r4.getAlgorithm()     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = org.bouncycastle.asn1.oiw.OIWObjectIdentifiers.elGamalAlgorithm     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            if (r4 != 0) goto L_0x0034
            java.security.PrivateKey r4 = r6.privKey     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            r0.init(r2, r4, r1)     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            goto L_0x0039
        L_0x0034:
            java.security.PrivateKey r4 = r6.privKey     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            r0.init(r2, r4)     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
        L_0x0039:
            org.bouncycastle.operator.jcajce.OperatorHelper r2 = r6.helper     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r7.getAlgorithm()     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            java.lang.String r2 = r2.getKeyAlgorithmName(r4)     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            r4 = 3
            java.security.Key r2 = r0.unwrap(r8, r2, r4)     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x0058 }
            boolean r4 = r6.unwrappedKeyMustBeEncodable     // Catch:{  }
            if (r4 == 0) goto L_0x0056
            byte[] r4 = r2.getEncoded()     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x005a, IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x005a, IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x005a }
            if (r4 == 0) goto L_0x005c
            int r4 = r4.length     // Catch:{ IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x005a, IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x005a, IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException -> 0x005a }
            if (r4 != 0) goto L_0x0056
            goto L_0x005c
        L_0x0056:
            r3 = r2
            goto L_0x005c
        L_0x0058:
            goto L_0x005c
        L_0x005a:
            goto L_0x0056
        L_0x005c:
            if (r3 != 0) goto L_0x007d
            r2 = 2
            if (r1 == 0) goto L_0x0067
            java.security.PrivateKey r3 = r6.privKey     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            r0.init(r2, r3, r1)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            goto L_0x006c
        L_0x0067:
            java.security.PrivateKey r1 = r6.privKey     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            r0.init(r2, r1)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
        L_0x006c:
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            byte[] r8 = r0.doFinal(r8)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r7.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            java.lang.String r0 = r0.getId()     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            r3.<init>(r8, r0)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
        L_0x007d:
            org.bouncycastle.operator.jcajce.JceGenericKey r8 = new org.bouncycastle.operator.jcajce.JceGenericKey     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            r8.<init>(r7, r3)     // Catch:{ InvalidKeyException -> 0x00ce, IllegalBlockSizeException -> 0x00b5, BadPaddingException -> 0x009c, InvalidAlgorithmParameterException -> 0x0083 }
            return r8
        L_0x0083:
            r7 = move-exception
            org.bouncycastle.operator.OperatorException r8 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "invalid algorithm parameters: "
            r0.<init>(r1)
            java.lang.String r1 = r7.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0, r7)
            throw r8
        L_0x009c:
            r7 = move-exception
            org.bouncycastle.operator.OperatorException r8 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "bad padding: "
            r0.<init>(r1)
            java.lang.String r1 = r7.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0, r7)
            throw r8
        L_0x00b5:
            r7 = move-exception
            org.bouncycastle.operator.OperatorException r8 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "illegal blocksize: "
            r0.<init>(r1)
            java.lang.String r1 = r7.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0, r7)
            throw r8
        L_0x00ce:
            r7 = move-exception
            org.bouncycastle.operator.OperatorException r8 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "key invalid: "
            r0.<init>(r1)
            java.lang.String r1 = r7.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0, r7)
            goto L_0x00e8
        L_0x00e7:
            throw r8
        L_0x00e8:
            goto L_0x00e7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper.generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier, byte[]):org.bouncycastle.operator.GenericKey");
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyUnwrapper setMustProduceEncodableUnwrappedKey(boolean z) {
        this.unwrappedKeyMustBeEncodable = z;
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
