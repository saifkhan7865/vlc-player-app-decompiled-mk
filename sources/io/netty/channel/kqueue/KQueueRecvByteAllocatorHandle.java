package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.unix.PreferredDirectByteBufAllocator;
import io.netty.util.UncheckedBooleanSupplier;

final class KQueueRecvByteAllocatorHandle extends RecvByteBufAllocator.DelegatingHandle implements RecvByteBufAllocator.ExtendedHandle {
    private final UncheckedBooleanSupplier defaultMaybeMoreDataSupplier = new UncheckedBooleanSupplier() {
        public boolean get() {
            return KQueueRecvByteAllocatorHandle.this.maybeMoreDataToRead();
        }
    };
    private long numberBytesPending;
    private boolean overrideGuess;
    private final PreferredDirectByteBufAllocator preferredDirectByteBufAllocator = new PreferredDirectByteBufAllocator();
    private boolean readEOF;

    KQueueRecvByteAllocatorHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
        super(extendedHandle);
    }

    public int guess() {
        return this.overrideGuess ? guess0() : delegate().guess();
    }

    public void reset(ChannelConfig channelConfig) {
        this.overrideGuess = ((KQueueChannelConfig) channelConfig).getRcvAllocTransportProvidesGuess();
        delegate().reset(channelConfig);
    }

    public ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
        this.preferredDirectByteBufAllocator.updateAllocator(byteBufAllocator);
        if (this.overrideGuess) {
            return this.preferredDirectByteBufAllocator.ioBuffer(guess0());
        }
        return delegate().allocate(this.preferredDirectByteBufAllocator);
    }

    public void lastBytesRead(int i) {
        long j = 0;
        if (i >= 0) {
            j = Math.max(0, this.numberBytesPending - ((long) i));
        }
        this.numberBytesPending = j;
        delegate().lastBytesRead(i);
    }

    public boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
        return ((RecvByteBufAllocator.ExtendedHandle) delegate()).continueReading(uncheckedBooleanSupplier);
    }

    public boolean continueReading() {
        return continueReading(this.defaultMaybeMoreDataSupplier);
    }

    /* access modifiers changed from: package-private */
    public void readEOF() {
        this.readEOF = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isReadEOF() {
        return this.readEOF;
    }

    /* access modifiers changed from: package-private */
    public void numberBytesPending(long j) {
        this.numberBytesPending = j;
    }

    /* access modifiers changed from: package-private */
    public boolean maybeMoreDataToRead() {
        return this.numberBytesPending != 0;
    }

    private int guess0() {
        return (int) Math.min(this.numberBytesPending, 2147483647L);
    }
}
