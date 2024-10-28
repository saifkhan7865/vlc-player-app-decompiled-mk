package io.ktor.network.tls.certificates;

import io.ktor.network.tls.OID;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Certificates.kt */
final class CertificatesKt$writeCertificate$2 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ String $algorithm;
    final /* synthetic */ byte[] $certInfoBytes;
    final /* synthetic */ byte[] $signed;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CertificatesKt$writeCertificate$2(byte[] bArr, byte[] bArr2, String str) {
        super(1);
        this.$certInfoBytes = bArr;
        this.$signed = bArr2;
        this.$algorithm = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
        OutputKt.writeFully$default((Output) bytePacketBuilder, this.$certInfoBytes, 0, 0, 6, (Object) null);
        final String str = this.$algorithm;
        CertificatesKt.writeDerSequence(bytePacketBuilder, new Function1<BytePacketBuilder, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((BytePacketBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(BytePacketBuilder bytePacketBuilder) {
                Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
                CertificatesKt.writeDerObjectIdentifier(bytePacketBuilder, OID.Companion.fromAlgorithm(str));
                CertificatesKt.writeDerNull(bytePacketBuilder);
            }
        });
        byte[] bArr = this.$signed;
        Intrinsics.checkNotNullExpressionValue(bArr, "signed");
        CertificatesKt.writeDerBitString$default(bytePacketBuilder, bArr, 0, 2, (Object) null);
    }
}
