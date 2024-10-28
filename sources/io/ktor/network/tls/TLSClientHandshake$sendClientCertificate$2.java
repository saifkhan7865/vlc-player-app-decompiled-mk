package io.ktor.network.tls;

import io.ktor.utils.io.core.BytePacketBuilder;
import java.security.cert.X509Certificate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$sendClientCertificate$2 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ CertificateAndKey $chainAndKey;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$sendClientCertificate$2(CertificateAndKey certificateAndKey) {
        super(1);
        this.$chainAndKey = certificateAndKey;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        X509Certificate[] x509CertificateArr;
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$sendHandshakeRecord");
        CertificateAndKey certificateAndKey = this.$chainAndKey;
        if (certificateAndKey == null || (x509CertificateArr = certificateAndKey.getCertificateChain()) == null) {
            x509CertificateArr = new X509Certificate[0];
        }
        RenderKt.writeTLSCertificates(bytePacketBuilder, x509CertificateArr);
    }
}
