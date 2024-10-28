package io.ktor.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ReaderJob;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002 \u0001*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/ReaderJob;", "S", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NIOSocketImpl.kt */
final class NIOSocketImpl$attachForWriting$1 extends Lambda implements Function0<ReaderJob> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ NIOSocketImpl<S> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NIOSocketImpl$attachForWriting$1(NIOSocketImpl<? extends S> nIOSocketImpl, ByteChannel byteChannel) {
        super(0);
        this.this$0 = nIOSocketImpl;
        this.$channel = byteChannel;
    }

    public final ReaderJob invoke() {
        NIOSocketImpl<S> nIOSocketImpl = this.this$0;
        NIOSocketImpl<S> nIOSocketImpl2 = this.this$0;
        return CIOWriterKt.attachForWritingDirectImpl(nIOSocketImpl, this.$channel, (WritableByteChannel) nIOSocketImpl.getChannel(), nIOSocketImpl2, nIOSocketImpl2.getSelector(), this.this$0.socketOptions);
    }
}
