package org.bouncycastle.its.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.its.ITSCertificate;
import org.bouncycastle.its.operator.ITSContentVerifierProvider;
import org.bouncycastle.oer.OEREncoder;
import org.bouncycastle.oer.its.ieee1609dot2.VerificationKeyIndicator;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDefaultDigestProvider;
import org.bouncycastle.util.Arrays;

public class BcITSContentVerifierProvider implements ITSContentVerifierProvider {
    /* access modifiers changed from: private */
    public final AlgorithmIdentifier digestAlgo;
    private final ITSCertificate issuer;
    private final byte[] parentData;
    /* access modifiers changed from: private */
    public final ECPublicKeyParameters pubParams;
    private final int sigChoice;

    public BcITSContentVerifierProvider(ITSCertificate iTSCertificate) throws IOException {
        AlgorithmIdentifier algorithmIdentifier;
        this.issuer = iTSCertificate;
        this.parentData = iTSCertificate.getEncoded();
        VerificationKeyIndicator verifyKeyIndicator = iTSCertificate.toASN1Structure().getToBeSigned().getVerifyKeyIndicator();
        if (verifyKeyIndicator.getVerificationKeyIndicator() instanceof PublicVerificationKey) {
            PublicVerificationKey instance = PublicVerificationKey.getInstance(verifyKeyIndicator.getVerificationKeyIndicator());
            this.sigChoice = instance.getChoice();
            int choice = instance.getChoice();
            if (choice == 0) {
                algorithmIdentifier = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
            } else if (choice == 1) {
                algorithmIdentifier = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
            } else if (choice == 2) {
                algorithmIdentifier = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
            } else {
                throw new IllegalStateException("unknown key type");
            }
            this.digestAlgo = algorithmIdentifier;
            this.pubParams = (ECPublicKeyParameters) new BcITSPublicVerificationKey(instance).getKey();
            return;
        }
        throw new IllegalStateException("not public verification key");
    }

    public ContentVerifier get(int i) throws OperatorCreationException {
        if (this.sigChoice == i) {
            final ExtendedDigest extendedDigest = BcDefaultDigestProvider.INSTANCE.get(this.digestAlgo);
            final byte[] bArr = new byte[extendedDigest.getDigestSize()];
            byte[] bArr2 = this.parentData;
            extendedDigest.update(bArr2, 0, bArr2.length);
            extendedDigest.doFinal(bArr, 0);
            final byte[] bArr3 = this.issuer.getIssuer().isSelf() ? new byte[extendedDigest.getDigestSize()] : null;
            if (bArr3 != null) {
                byte[] byteArray = OEREncoder.toByteArray(this.issuer.toASN1Structure().getToBeSigned(), IEEE1609dot2.ToBeSignedCertificate.build());
                extendedDigest.update(byteArray, 0, byteArray.length);
                extendedDigest.doFinal(bArr3, 0);
            }
            final AnonymousClass1 r3 = new OutputStream() {
                public void write(int i) throws IOException {
                    extendedDigest.update((byte) i);
                }

                public void write(byte[] bArr) throws IOException {
                    extendedDigest.update(bArr, 0, bArr.length);
                }

                public void write(byte[] bArr, int i, int i2) throws IOException {
                    extendedDigest.update(bArr, i, i2);
                }
            };
            return new ContentVerifier() {
                final DSADigestSigner signer;

                {
                    this.signer = new DSADigestSigner(new ECDSASigner(), BcDefaultDigestProvider.INSTANCE.get(BcITSContentVerifierProvider.this.digestAlgo));
                }

                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return null;
                }

                public OutputStream getOutputStream() {
                    return r3;
                }

                public boolean verify(byte[] bArr) {
                    int digestSize = extendedDigest.getDigestSize();
                    byte[] bArr2 = new byte[digestSize];
                    extendedDigest.doFinal(bArr2, 0);
                    this.signer.init(false, BcITSContentVerifierProvider.this.pubParams);
                    this.signer.update(bArr2, 0, digestSize);
                    byte[] bArr3 = bArr3;
                    if (bArr3 == null || !Arrays.areEqual(bArr2, bArr3)) {
                        DSADigestSigner dSADigestSigner = this.signer;
                        byte[] bArr4 = bArr;
                        dSADigestSigner.update(bArr4, 0, bArr4.length);
                    } else {
                        int digestSize2 = extendedDigest.getDigestSize();
                        byte[] bArr5 = new byte[digestSize2];
                        extendedDigest.doFinal(bArr5, 0);
                        this.signer.update(bArr5, 0, digestSize2);
                    }
                    return this.signer.verifySignature(bArr);
                }
            };
        }
        throw new OperatorCreationException("wrong verifier for algorithm: " + i);
    }

    public ITSCertificate getAssociatedCertificate() {
        return this.issuer;
    }

    public boolean hasAssociatedCertificate() {
        return this.issuer != null;
    }
}
