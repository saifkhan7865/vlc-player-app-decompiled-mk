package io.ktor.network.tls;

import io.ktor.network.tls.extensions.HashAndSign;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.OutputPrimitivesKt;
import io.ktor.utils.io.core.PreviewKt;
import io.ktor.utils.io.core.StringsKt;
import java.security.Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$sendClientCertificateVerify$2 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ HashAndSign $hashAndSign;
    final /* synthetic */ Signature $sign;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$sendClientCertificateVerify$2(HashAndSign hashAndSign, TLSClientHandshake tLSClientHandshake, Signature signature) {
        super(1);
        this.$hashAndSign = hashAndSign;
        this.this$0 = tLSClientHandshake;
        this.$sign = signature;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: finally extract failed */
    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$sendHandshakeRecord");
        bytePacketBuilder.writeByte(this.$hashAndSign.getHash().getCode());
        bytePacketBuilder.writeByte(this.$hashAndSign.getSign().getCode());
        BytePacketBuilder access$getDigest$p = this.this$0.digest;
        Signature signature = this.$sign;
        ByteReadPacket preview = PreviewKt.preview(access$getDigest$p);
        try {
            signature.update(StringsKt.readBytes$default(preview, 0, 1, (Object) null));
            Unit unit = Unit.INSTANCE;
            preview.release();
            byte[] sign = this.$sign.sign();
            Intrinsics.checkNotNull(sign);
            Output output = bytePacketBuilder;
            OutputPrimitivesKt.writeShort(output, (short) sign.length);
            OutputKt.writeFully$default(output, sign, 0, 0, 6, (Object) null);
        } catch (Throwable th) {
            preview.release();
            throw th;
        }
    }
}
