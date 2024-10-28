package io.ktor.network.tls.certificates;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.pool.ObjectPool;
import j$.time.Instant;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.PublicKey;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Certificates.kt */
final class CertificatesKt$writeX509Info$1 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ String $algorithm;
    final /* synthetic */ List<String> $domains;
    final /* synthetic */ List<InetAddress> $ipAddresses;
    final /* synthetic */ X500Principal $issuer;
    final /* synthetic */ KeyType $keyType;
    final /* synthetic */ PublicKey $publicKey;
    final /* synthetic */ X500Principal $subject;
    final /* synthetic */ Instant $validFrom;
    final /* synthetic */ Instant $validUntil;
    final /* synthetic */ BigInteger $version;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CertificatesKt$writeX509Info$1(BigInteger bigInteger, String str, X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, Instant instant, Instant instant2, KeyType keyType, List<String> list, List<? extends InetAddress> list2) {
        super(1);
        this.$version = bigInteger;
        this.$algorithm = str;
        this.$issuer = x500Principal;
        this.$subject = x500Principal2;
        this.$publicKey = publicKey;
        this.$validFrom = instant;
        this.$validUntil = instant2;
        this.$keyType = keyType;
        this.$domains = list;
        this.$ipAddresses = list2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
        CertificatesKt.writeVersion(bytePacketBuilder, 2);
        CertificatesKt.writeAsnInt(bytePacketBuilder, this.$version);
        CertificatesKt.writeAlgorithmIdentifier(bytePacketBuilder, this.$algorithm);
        CertificatesKt.writeX500Principal(bytePacketBuilder, this.$issuer);
        final Instant instant = this.$validFrom;
        final Instant instant2 = this.$validUntil;
        CertificatesKt.writeDerSequence(bytePacketBuilder, new Function1<BytePacketBuilder, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((BytePacketBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(BytePacketBuilder bytePacketBuilder) {
                Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
                CertificatesKt.writeDerUTCTime(bytePacketBuilder, instant);
                CertificatesKt.writeDerGeneralizedTime(bytePacketBuilder, instant2);
            }
        });
        CertificatesKt.writeX500Principal(bytePacketBuilder, this.$subject);
        byte[] encoded = this.$publicKey.getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "publicKey.encoded");
        OutputKt.writeFully$default((Output) bytePacketBuilder, encoded, 0, 0, 6, (Object) null);
        bytePacketBuilder.writeByte((byte) -93);
        KeyType keyType = this.$keyType;
        List<String> list = this.$domains;
        List<InetAddress> list2 = this.$ipAddresses;
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            CertificatesKt.writeDerSequence(bytePacketBuilder2, new CertificatesKt$writeX509Info$1$extensions$1$1(keyType, list, list2));
            ByteReadPacket build = bytePacketBuilder2.build();
            CertificatesKt.writeDerLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }
}
