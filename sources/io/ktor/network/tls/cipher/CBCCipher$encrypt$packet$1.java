package io.ktor.network.tls.cipher;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CBCCipher.kt */
final class CBCCipher$encrypt$packet$1 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ CBCCipher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CBCCipher$encrypt$packet$1(CBCCipher cBCCipher) {
        super(1);
        this.this$0 = cBCCipher;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$cipherLoop");
        byte[] iv = this.this$0.sendCipher.getIV();
        Intrinsics.checkNotNullExpressionValue(iv, "sendCipher.iv");
        OutputKt.writeFully$default((Output) bytePacketBuilder, iv, 0, 0, 6, (Object) null);
    }
}
