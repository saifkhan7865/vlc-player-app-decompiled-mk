package io.ktor.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.WriterJob;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002 \u0001*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/WriterJob;", "S", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NIOSocketImpl.kt */
final class NIOSocketImpl$attachForReading$1 extends Lambda implements Function0<WriterJob> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ NIOSocketImpl<S> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NIOSocketImpl$attachForReading$1(NIOSocketImpl<? extends S> nIOSocketImpl, ByteChannel byteChannel) {
        super(0);
        this.this$0 = nIOSocketImpl;
        this.$channel = byteChannel;
    }

    public final WriterJob invoke() {
        if (this.this$0.getPool() != null) {
            NIOSocketImpl<S> nIOSocketImpl = this.this$0;
            NIOSocketImpl<S> nIOSocketImpl2 = this.this$0;
            return CIOReaderKt.attachForReadingImpl(nIOSocketImpl, this.$channel, (ReadableByteChannel) nIOSocketImpl.getChannel(), nIOSocketImpl2, nIOSocketImpl2.getSelector(), this.this$0.getPool(), this.this$0.socketOptions);
        }
        NIOSocketImpl<S> nIOSocketImpl3 = this.this$0;
        NIOSocketImpl<S> nIOSocketImpl4 = this.this$0;
        return CIOReaderKt.attachForReadingDirectImpl(nIOSocketImpl3, this.$channel, (ReadableByteChannel) nIOSocketImpl3.getChannel(), nIOSocketImpl4, nIOSocketImpl4.getSelector(), this.this$0.socketOptions);
    }
}
