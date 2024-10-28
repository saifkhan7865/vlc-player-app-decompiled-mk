package io.ktor.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ReaderJob;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/AWritable;", "", "attachForWriting", "Lio/ktor/utils/io/ReaderJob;", "channel", "Lio/ktor/utils/io/ByteChannel;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sockets.kt */
public interface AWritable {
    ReaderJob attachForWriting(ByteChannel byteChannel);
}
