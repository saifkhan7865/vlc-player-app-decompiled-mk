package io.ktor.network.sockets;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "buffer", "Ljava/nio/ByteBuffer;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOReader.kt */
final class CIOReaderKt$readFrom$2 extends Lambda implements Function1<ByteBuffer, Unit> {
    final /* synthetic */ Ref.IntRef $count;
    final /* synthetic */ ReadableByteChannel $nioChannel;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOReaderKt$readFrom$2(Ref.IntRef intRef, ReadableByteChannel readableByteChannel) {
        super(1);
        this.$count = intRef;
        this.$nioChannel = readableByteChannel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ByteBuffer) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        this.$count.element = this.$nioChannel.read(byteBuffer);
    }
}
