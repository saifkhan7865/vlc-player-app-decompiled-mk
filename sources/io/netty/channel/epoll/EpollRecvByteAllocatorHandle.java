package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.unix.PreferredDirectByteBufAllocator;
import io.netty.util.UncheckedBooleanSupplier;

class EpollRecvByteAllocatorHandle extends RecvByteBufAllocator.DelegatingHandle implements RecvByteBufAllocator.ExtendedHandle {
    private final UncheckedBooleanSupplier defaultMaybeMoreDataSupplier = new UncheckedBooleanSupplier() {
        public boolean get() {
            return EpollRecvByteAllocatorHandle.this.maybeMoreDataToRead();
        }
    };
    private boolean isEdgeTriggered;
    private final PreferredDirectByteBufAllocator preferredDirectByteBufAllocator = new PreferredDirectByteBufAllocator();
    private boolean receivedRdHup;

    EpollRecvByteAllocatorHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
        super(extendedHandle);
    }

    /* access modifiers changed from: package-private */
    public final void receivedRdHup() {
        this.receivedRdHup = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean isReceivedRdHup() {
        return this.receivedRdHup;
    }

    /* access modifiers changed from: package-private */
    public boolean maybeMoreDataToRead() {
        return (this.isEdgeTriggered && lastBytesRead() > 0) || (!this.isEdgeTriggered && lastBytesRead() == attemptedBytesRead());
    }

    /* access modifiers changed from: package-private */
    public final void edgeTriggered(boolean z) {
        this.isEdgeTriggered = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean isEdgeTriggered() {
        return this.isEdgeTriggered;
    }

    public final ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
        this.preferredDirectByteBufAllocator.updateAllocator(byteBufAllocator);
        return delegate().allocate(this.preferredDirectByteBufAllocator);
    }

    public final boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
        return ((RecvByteBufAllocator.ExtendedHandle) delegate()).continueReading(uncheckedBooleanSupplier);
    }

    public final boolean continueReading() {
        return continueReading(this.defaultMaybeMoreDataSupplier);
    }
}
