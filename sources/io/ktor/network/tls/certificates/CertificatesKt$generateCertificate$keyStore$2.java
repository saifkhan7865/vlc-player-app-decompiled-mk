package io.ktor.network.tls.certificates;

import io.ktor.network.tls.extensions.HashAlgorithm;
import io.ktor.network.tls.extensions.SignatureAlgorithm;
import java.security.KeyPair;
import java.security.cert.Certificate;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/network/tls/certificates/KeyStoreBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Certificates.kt */
final class CertificatesKt$generateCertificate$keyStore$2 extends Lambda implements Function1<KeyStoreBuilder, Unit> {
    final /* synthetic */ String $algorithm;
    final /* synthetic */ Certificate $caCert;
    final /* synthetic */ KeyPair $caKeys;
    final /* synthetic */ String $keyAlias;
    final /* synthetic */ String $keyPassword;
    final /* synthetic */ int $keySizeInBits;
    final /* synthetic */ KeyType $keyType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CertificatesKt$generateCertificate$keyStore$2(String str, String str2, String str3, int i, KeyType keyType, KeyPair keyPair, Certificate certificate) {
        super(1);
        this.$keyAlias = str;
        this.$algorithm = str2;
        this.$keyPassword = str3;
        this.$keySizeInBits = i;
        this.$keyType = keyType;
        this.$caKeys = keyPair;
        this.$caCert = certificate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((KeyStoreBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(KeyStoreBuilder keyStoreBuilder) {
        Intrinsics.checkNotNullParameter(keyStoreBuilder, "$this$buildKeyStore");
        String str = this.$keyAlias;
        final String str2 = this.$algorithm;
        final String str3 = this.$keyPassword;
        final int i = this.$keySizeInBits;
        final KeyType keyType = this.$keyType;
        final KeyPair keyPair = this.$caKeys;
        final Certificate certificate = this.$caCert;
        keyStoreBuilder.certificate(str, new Function1<CertificateBuilder, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((CertificateBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(CertificateBuilder certificateBuilder) {
                Intrinsics.checkNotNullParameter(certificateBuilder, "$this$certificate");
                List split$default = StringsKt.split$default((CharSequence) str2, new String[]{"with"}, false, 0, 6, (Object) null);
                certificateBuilder.setHash(HashAlgorithm.valueOf((String) split$default.get(0)));
                certificateBuilder.setSign(SignatureAlgorithm.valueOf((String) split$default.get(1)));
                certificateBuilder.setPassword(str3);
                certificateBuilder.setKeySizeInBits(i);
                certificateBuilder.setKeyType(keyType);
                certificateBuilder.setSubject(CertificatesKt.getDEFAULT_PRINCIPAL());
                certificateBuilder.setDomains(CollectionsKt.listOf("127.0.0.1", "localhost"));
                KeyPair keyPair = keyPair;
                Certificate certificate = certificate;
                Intrinsics.checkNotNullExpressionValue(certificate, "caCert");
                certificateBuilder.signWith(keyPair, certificate, CertificatesKt.DEFAULT_CA_PRINCIPAL);
            }
        });
    }
}
