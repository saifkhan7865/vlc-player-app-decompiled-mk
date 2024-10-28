package io.netty.channel.kqueue;

import io.netty.channel.unix.Buffer;
import io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;

final class KQueueEventArray {
    private static final int KQUEUE_DATA_OFFSET = Native.offsetofKeventData();
    private static final int KQUEUE_EVENT_SIZE = Native.sizeofKEvent();
    private static final int KQUEUE_FFLAGS_OFFSET = Native.offsetofKEventFFlags();
    private static final int KQUEUE_FILTER_OFFSET = Native.offsetofKEventFilter();
    private static final int KQUEUE_FLAGS_OFFSET = Native.offsetofKEventFlags();
    private static final int KQUEUE_IDENT_OFFSET = Native.offsetofKEventIdent();
    private int capacity;
    private ByteBuffer memory;
    private long memoryAddress;
    private int size;

    private static native void evSet(long j, int i, short s, short s2, int i2);

    KQueueEventArray(int i) {
        if (i >= 1) {
            ByteBuffer allocateDirectWithNativeOrder = Buffer.allocateDirectWithNativeOrder(calculateBufferCapacity(i));
            this.memory = allocateDirectWithNativeOrder;
            this.memoryAddress = Buffer.memoryAddress(allocateDirectWithNativeOrder);
            this.capacity = i;
            return;
        }
        throw new IllegalArgumentException("capacity must be >= 1 but was " + i);
    }

    /* access modifiers changed from: package-private */
    public long memoryAddress() {
        return this.memoryAddress;
    }

    /* access modifiers changed from: package-private */
    public int capacity() {
        return this.capacity;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.size = 0;
    }

    /* access modifiers changed from: package-private */
    public void evSet(AbstractKQueueChannel abstractKQueueChannel, short s, short s2, int i) {
        reallocIfNeeded();
        int i2 = this.size;
        this.size = i2 + 1;
        evSet(((long) getKEventOffset(i2)) + this.memoryAddress, abstractKQueueChannel.socket.intValue(), s, s2, i);
    }

    private void reallocIfNeeded() {
        if (this.size == this.capacity) {
            realloc(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void realloc(boolean z) {
        int i = this.capacity;
        int i2 = i <= 65536 ? i << 1 : (i + i) >> 1;
        try {
            ByteBuffer allocateDirectWithNativeOrder = Buffer.allocateDirectWithNativeOrder(calculateBufferCapacity(i2));
            this.memory.position(0).limit(this.size);
            allocateDirectWithNativeOrder.put(this.memory);
            allocateDirectWithNativeOrder.position(0);
            Buffer.free(this.memory);
            this.memory = allocateDirectWithNativeOrder;
            this.memoryAddress = Buffer.memoryAddress(allocateDirectWithNativeOrder);
        } catch (OutOfMemoryError e) {
            if (z) {
                OutOfMemoryError outOfMemoryError = new OutOfMemoryError("unable to allocate " + i2 + " new bytes! Existing capacity is: " + this.capacity);
                outOfMemoryError.initCause(e);
                throw outOfMemoryError;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void free() {
        Buffer.free(this.memory);
        this.capacity = 0;
        this.size = 0;
        this.memoryAddress = (long) 0;
    }

    private static int getKEventOffset(int i) {
        return i * KQUEUE_EVENT_SIZE;
    }

    private long getKEventOffsetAddress(int i) {
        return ((long) getKEventOffset(i)) + this.memoryAddress;
    }

    private short getShort(int i, int i2) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.getShort(getKEventOffsetAddress(i) + ((long) i2));
        }
        return this.memory.getShort(getKEventOffset(i) + i2);
    }

    /* access modifiers changed from: package-private */
    public short flags(int i) {
        return getShort(i, KQUEUE_FLAGS_OFFSET);
    }

    /* access modifiers changed from: package-private */
    public short filter(int i) {
        return getShort(i, KQUEUE_FILTER_OFFSET);
    }

    /* access modifiers changed from: package-private */
    public short fflags(int i) {
        return getShort(i, KQUEUE_FFLAGS_OFFSET);
    }

    /* access modifiers changed from: package-private */
    public int fd(int i) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.getInt(getKEventOffsetAddress(i) + ((long) KQUEUE_IDENT_OFFSET));
        }
        return this.memory.getInt(getKEventOffset(i) + KQUEUE_IDENT_OFFSET);
    }

    /* access modifiers changed from: package-private */
    public long data(int i) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.getLong(getKEventOffsetAddress(i) + ((long) KQUEUE_DATA_OFFSET));
        }
        return this.memory.getLong(getKEventOffset(i) + KQUEUE_DATA_OFFSET);
    }

    private static int calculateBufferCapacity(int i) {
        return i * KQUEUE_EVENT_SIZE;
    }
}
