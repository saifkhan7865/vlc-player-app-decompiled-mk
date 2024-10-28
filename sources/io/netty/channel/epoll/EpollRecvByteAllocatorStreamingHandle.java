package io.netty.channel.epoll;

import io.netty.channel.RecvByteBufAllocator;

final class EpollRecvByteAllocatorStreamingHandle extends EpollRecvByteAllocatorHandle {
    EpollRecvByteAllocatorStreamingHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
        super(extendedHandle);
    }

    /* access modifiers changed from: package-private */
    public boolean maybeMoreDataToRead() {
        return lastBytesRead() == attemptedBytesRead() || isReceivedRdHup();
    }
}
