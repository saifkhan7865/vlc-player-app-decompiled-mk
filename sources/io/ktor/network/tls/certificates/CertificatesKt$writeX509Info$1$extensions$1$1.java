package io.ktor.network.tls.certificates;

import io.ktor.utils.io.core.BytePacketBuilder;
import java.net.InetAddress;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Certificates.kt */
final class CertificatesKt$writeX509Info$1$extensions$1$1 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ List<String> $domains;
    final /* synthetic */ List<InetAddress> $ipAddresses;
    final /* synthetic */ KeyType $keyType;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Certificates.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                io.ktor.network.tls.certificates.KeyType[] r0 = io.ktor.network.tls.certificates.KeyType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.network.tls.certificates.KeyType r1 = io.ktor.network.tls.certificates.KeyType.CA     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.network.tls.certificates.KeyType r1 = io.ktor.network.tls.certificates.KeyType.Server     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.network.tls.certificates.KeyType r1 = io.ktor.network.tls.certificates.KeyType.Client     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.certificates.CertificatesKt$writeX509Info$1$extensions$1$1.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CertificatesKt$writeX509Info$1$extensions$1$1(KeyType keyType, List<String> list, List<? extends InetAddress> list2) {
        super(1);
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
        int i = WhenMappings.$EnumSwitchMapping$0[this.$keyType.ordinal()];
        if (i == 1) {
            CertificatesKt.caExtension(bytePacketBuilder);
        } else if (i == 2) {
            CertificatesKt.extKeyUsage(bytePacketBuilder, AnonymousClass1.INSTANCE);
            CertificatesKt.subjectAlternativeNames(bytePacketBuilder, this.$domains, this.$ipAddresses);
        } else if (i == 3) {
            CertificatesKt.extKeyUsage(bytePacketBuilder, AnonymousClass2.INSTANCE);
        }
    }
}
