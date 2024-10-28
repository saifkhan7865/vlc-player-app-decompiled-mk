package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;
import java.util.AbstractMap;
import java.util.Map;

public class DefaultMaxBytesRecvByteBufAllocator implements MaxBytesRecvByteBufAllocator {
    private volatile int maxBytesPerIndividualRead;
    private volatile int maxBytesPerRead;

    private final class HandleImpl implements RecvByteBufAllocator.ExtendedHandle {
        /* access modifiers changed from: private */
        public int attemptBytesRead;
        private int bytesToRead;
        private final UncheckedBooleanSupplier defaultMaybeMoreSupplier;
        private int individualReadMax;
        /* access modifiers changed from: private */
        public int lastBytesRead;

        public void incMessagesRead(int i) {
        }

        public void readComplete() {
        }

        private HandleImpl() {
            this.defaultMaybeMoreSupplier = new UncheckedBooleanSupplier() {
                public boolean get() {
                    return HandleImpl.this.attemptBytesRead == HandleImpl.this.lastBytesRead;
                }
            };
        }

        public ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
            return byteBufAllocator.ioBuffer(guess());
        }

        public int guess() {
            return Math.min(this.individualReadMax, this.bytesToRead);
        }

        public void reset(ChannelConfig channelConfig) {
            this.bytesToRead = DefaultMaxBytesRecvByteBufAllocator.this.maxBytesPerRead();
            this.individualReadMax = DefaultMaxBytesRecvByteBufAllocator.this.maxBytesPerIndividualRead();
        }

        public void lastBytesRead(int i) {
            this.lastBytesRead = i;
            this.bytesToRead -= i;
        }

        public int lastBytesRead() {
            return this.lastBytesRead;
        }

        public boolean continueReading() {
            return continueReading(this.defaultMaybeMoreSupplier);
        }

        public boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
            return this.bytesToRead > 0 && uncheckedBooleanSupplier.get();
        }

        public void attemptedBytesRead(int i) {
            this.attemptBytesRead = i;
        }

        public int attemptedBytesRead() {
            return this.attemptBytesRead;
        }
    }

    public DefaultMaxBytesRecvByteBufAllocator() {
        this(65536, 65536);
    }

    public DefaultMaxBytesRecvByteBufAllocator(int i, int i2) {
        checkMaxBytesPerReadPair(i, i2);
        this.maxBytesPerRead = i;
        this.maxBytesPerIndividualRead = i2;
    }

    public RecvByteBufAllocator.Handle newHandle() {
        return new HandleImpl();
    }

    public int maxBytesPerRead() {
        return this.maxBytesPerRead;
    }

    public DefaultMaxBytesRecvByteBufAllocator maxBytesPerRead(int i) {
        ObjectUtil.checkPositive(i, "maxBytesPerRead");
        synchronized (this) {
            int maxBytesPerIndividualRead2 = maxBytesPerIndividualRead();
            if (i >= maxBytesPerIndividualRead2) {
                this.maxBytesPerRead = i;
            } else {
                throw new IllegalArgumentException("maxBytesPerRead cannot be less than maxBytesPerIndividualRead (" + maxBytesPerIndividualRead2 + "): " + i);
            }
        }
        return this;
    }

    public int maxBytesPerIndividualRead() {
        return this.maxBytesPerIndividualRead;
    }

    public DefaultMaxBytesRecvByteBufAllocator maxBytesPerIndividualRead(int i) {
        ObjectUtil.checkPositive(i, "maxBytesPerIndividualRead");
        synchronized (this) {
            int maxBytesPerRead2 = maxBytesPerRead();
            if (i <= maxBytesPerRead2) {
                this.maxBytesPerIndividualRead = i;
            } else {
                throw new IllegalArgumentException("maxBytesPerIndividualRead cannot be greater than maxBytesPerRead (" + maxBytesPerRead2 + "): " + i);
            }
        }
        return this;
    }

    public synchronized Map.Entry<Integer, Integer> maxBytesPerReadPair() {
        return new AbstractMap.SimpleEntry(Integer.valueOf(this.maxBytesPerRead), Integer.valueOf(this.maxBytesPerIndividualRead));
    }

    private static void checkMaxBytesPerReadPair(int i, int i2) {
        ObjectUtil.checkPositive(i, "maxBytesPerRead");
        ObjectUtil.checkPositive(i2, "maxBytesPerIndividualRead");
        if (i < i2) {
            throw new IllegalArgumentException("maxBytesPerRead cannot be less than maxBytesPerIndividualRead (" + i2 + "): " + i);
        }
    }

    public DefaultMaxBytesRecvByteBufAllocator maxBytesPerReadPair(int i, int i2) {
        checkMaxBytesPerReadPair(i, i2);
        synchronized (this) {
            this.maxBytesPerRead = i;
            this.maxBytesPerIndividualRead = i2;
        }
        return this;
    }
}
