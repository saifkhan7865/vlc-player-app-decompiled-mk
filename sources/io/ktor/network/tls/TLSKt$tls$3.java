package io.ktor.network.tls;

import java.security.SecureRandom;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/network/tls/TLSConfigBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLS.kt */
final class TLSKt$tls$3 extends Lambda implements Function1<TLSConfigBuilder, Unit> {
    final /* synthetic */ List<CipherSuite> $cipherSuites;
    final /* synthetic */ String $randomAlgorithm;
    final /* synthetic */ String $serverName;
    final /* synthetic */ X509TrustManager $trustManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSKt$tls$3(X509TrustManager x509TrustManager, String str, List<CipherSuite> list, String str2) {
        super(1);
        this.$trustManager = x509TrustManager;
        this.$randomAlgorithm = str;
        this.$cipherSuites = list;
        this.$serverName = str2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TLSConfigBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(TLSConfigBuilder tLSConfigBuilder) {
        Intrinsics.checkNotNullParameter(tLSConfigBuilder, "$this$tls");
        tLSConfigBuilder.setTrustManager(this.$trustManager);
        tLSConfigBuilder.setRandom(SecureRandom.getInstance(this.$randomAlgorithm));
        tLSConfigBuilder.setCipherSuites(this.$cipherSuites);
        tLSConfigBuilder.setServerName(this.$serverName);
    }
}
