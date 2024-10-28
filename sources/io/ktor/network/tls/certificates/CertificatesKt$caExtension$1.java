package io.ktor.network.tls.certificates;

import io.ktor.network.tls.OID;
import io.ktor.utils.io.core.BytePacketBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Certificates.kt */
final class CertificatesKt$caExtension$1 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    public static final CertificatesKt$caExtension$1 INSTANCE = new CertificatesKt$caExtension$1();

    CertificatesKt$caExtension$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
        CertificatesKt.writeDerObjectIdentifier(bytePacketBuilder, OID.Companion.getBasicConstraints());
        CertificatesKt.writeDerBoolean(bytePacketBuilder, true);
        CertificatesKt.writeDerOctetString(bytePacketBuilder, AnonymousClass1.INSTANCE);
    }
}
