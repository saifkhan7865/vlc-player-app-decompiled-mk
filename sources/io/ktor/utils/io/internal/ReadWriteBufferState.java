package io.ktor.utils.io.internal;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0007\u0018\u0019\u001a\u001b\u001c\u001d\u001eB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u0010\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0017R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r\u0001\u0007\u001f !\"#$%¨\u0006&"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState;", "", "backingBuffer", "Ljava/nio/ByteBuffer;", "capacity", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;)V", "idle", "", "getIdle", "()Z", "readBuffer", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "writeBuffer", "getWriteBuffer", "startReading", "startReading$ktor_io", "startWriting", "startWriting$ktor_io", "stopReading", "stopReading$ktor_io", "stopWriting", "stopWriting$ktor_io", "IdleEmpty", "IdleNonEmpty", "Initial", "Reading", "ReadingWriting", "Terminated", "Writing", "Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleEmpty;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleNonEmpty;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Reading;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$ReadingWriting;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Terminated;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Writing;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ReadWriteBufferState.kt */
public abstract class ReadWriteBufferState {
    public final ByteBuffer backingBuffer;
    public final RingBufferCapacity capacity;

    public /* synthetic */ ReadWriteBufferState(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer, ringBufferCapacity);
    }

    public boolean getIdle() {
        return false;
    }

    private ReadWriteBufferState(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity) {
        this.backingBuffer = byteBuffer;
        this.capacity = ringBufferCapacity;
    }

    public ByteBuffer getReadBuffer() {
        throw new IllegalStateException(("read buffer is not available in state " + this).toString());
    }

    public ByteBuffer getWriteBuffer() {
        throw new IllegalStateException(("write buffer is not available in state " + this).toString());
    }

    public ReadWriteBufferState startReading$ktor_io() {
        throw new IllegalStateException(("ByteChannel[state: " + this + "] Concurrent reading is not supported").toString());
    }

    public ReadWriteBufferState startWriting$ktor_io() {
        throw new IllegalStateException(("ByteChannel[state: " + this + "] Concurrent writing is not supported").toString());
    }

    public ReadWriteBufferState stopReading$ktor_io() {
        throw new IllegalStateException(("Unable to stop reading in state " + this).toString());
    }

    public ReadWriteBufferState stopWriting$ktor_io() {
        throw new IllegalStateException(("Unable to stop writing in state " + this).toString());
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleEmpty;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "()V", "idle", "", "getIdle", "()Z", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class IdleEmpty extends ReadWriteBufferState {
        public static final IdleEmpty INSTANCE = new IdleEmpty();

        public boolean getIdle() {
            return true;
        }

        private IdleEmpty() {
            super(ReadWriteBufferStateKt.getEmptyByteBuffer(), ReadWriteBufferStateKt.getEmptyCapacity(), (DefaultConstructorMarker) null);
        }

        public String toString() {
            return "IDLE(empty)";
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010 \u001a\u00020\u0013H\u0010¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u001dH\u0010¢\u0006\u0002\b#J\b\u0010$\u001a\u00020%H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0014\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006&"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "backingBuffer", "Ljava/nio/ByteBuffer;", "reservedSize", "", "(Ljava/nio/ByteBuffer;I)V", "idle", "", "getIdle", "()Z", "idleState", "Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleNonEmpty;", "getIdleState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleNonEmpty;", "readBuffer", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "readingState", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Reading;", "getReadingState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$Reading;", "readingWritingState", "Lio/ktor/utils/io/internal/ReadWriteBufferState$ReadingWriting;", "getReadingWritingState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$ReadingWriting;", "writeBuffer", "getWriteBuffer", "writingState", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Writing;", "getWritingState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$Writing;", "startReading", "startReading$ktor_io", "startWriting", "startWriting$ktor_io", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class Initial extends ReadWriteBufferState {
        private final IdleNonEmpty idleState;
        private final ByteBuffer readBuffer;
        private final Reading readingState;
        private final ReadingWriting readingWritingState;
        private final ByteBuffer writeBuffer;
        private final Writing writingState;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Initial(ByteBuffer byteBuffer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(byteBuffer, (i2 & 2) != 0 ? 8 : i);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Initial(ByteBuffer byteBuffer, int i) {
            super(byteBuffer, new RingBufferCapacity(byteBuffer.capacity() - i), (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(byteBuffer, "backingBuffer");
            if (byteBuffer.position() != 0) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            } else if (byteBuffer.limit() == byteBuffer.capacity()) {
                ByteBuffer duplicate = byteBuffer.duplicate();
                Intrinsics.checkNotNullExpressionValue(duplicate, "backingBuffer.duplicate()");
                this.writeBuffer = duplicate;
                ByteBuffer duplicate2 = byteBuffer.duplicate();
                Intrinsics.checkNotNullExpressionValue(duplicate2, "backingBuffer.duplicate()");
                this.readBuffer = duplicate2;
                this.idleState = new IdleNonEmpty(this);
                this.readingState = new Reading(this);
                this.writingState = new Writing(this);
                this.readingWritingState = new ReadingWriting(this);
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }

        public ByteBuffer getWriteBuffer() {
            return this.writeBuffer;
        }

        public ByteBuffer getReadBuffer() {
            return this.readBuffer;
        }

        public final IdleNonEmpty getIdleState$ktor_io() {
            return this.idleState;
        }

        public final Reading getReadingState$ktor_io() {
            return this.readingState;
        }

        public final Writing getWritingState$ktor_io() {
            return this.writingState;
        }

        public final ReadingWriting getReadingWritingState$ktor_io() {
            return this.readingWritingState;
        }

        public Reading startReading$ktor_io() {
            return this.readingState;
        }

        public Writing startWriting$ktor_io() {
            return this.writingState;
        }

        public boolean getIdle() {
            throw new IllegalStateException("Not available for initial state".toString());
        }

        public String toString() {
            return "Initial";
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleNonEmpty;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "initial", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "idle", "", "getIdle", "()Z", "getInitial", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "startReading", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Reading;", "startReading$ktor_io", "startWriting", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Writing;", "startWriting$ktor_io", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class IdleNonEmpty extends ReadWriteBufferState {
        private final Initial initial;

        public boolean getIdle() {
            return true;
        }

        public final Initial getInitial() {
            return this.initial;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IdleNonEmpty(Initial initial2) {
            super(initial2.backingBuffer, initial2.capacity, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(initial2, "initial");
            this.initial = initial2;
        }

        public Reading startReading$ktor_io() {
            return this.initial.getReadingState$ktor_io();
        }

        public Writing startWriting$ktor_io() {
            return this.initial.getWritingState$ktor_io();
        }

        public String toString() {
            return "IDLE(with buffer)";
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$Reading;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "initial", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "readBuffer", "Ljava/nio/ByteBuffer;", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "startWriting", "Lio/ktor/utils/io/internal/ReadWriteBufferState$ReadingWriting;", "startWriting$ktor_io", "stopReading", "Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleNonEmpty;", "stopReading$ktor_io", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class Reading extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Reading(Initial initial2) {
            super(initial2.backingBuffer, initial2.capacity, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(initial2, "initial");
            this.initial = initial2;
        }

        public ByteBuffer getReadBuffer() {
            return this.initial.getReadBuffer();
        }

        public ReadingWriting startWriting$ktor_io() {
            return this.initial.getReadingWritingState$ktor_io();
        }

        public IdleNonEmpty stopReading$ktor_io() {
            return this.initial.getIdleState$ktor_io();
        }

        public String toString() {
            return "Reading";
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$Writing;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "initial", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "writeBuffer", "Ljava/nio/ByteBuffer;", "getWriteBuffer", "()Ljava/nio/ByteBuffer;", "startReading", "Lio/ktor/utils/io/internal/ReadWriteBufferState$ReadingWriting;", "startReading$ktor_io", "stopWriting", "Lio/ktor/utils/io/internal/ReadWriteBufferState$IdleNonEmpty;", "stopWriting$ktor_io", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class Writing extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Writing(Initial initial2) {
            super(initial2.backingBuffer, initial2.capacity, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(initial2, "initial");
            this.initial = initial2;
        }

        public ByteBuffer getWriteBuffer() {
            return this.initial.getWriteBuffer();
        }

        public ReadingWriting startReading$ktor_io() {
            return this.initial.getReadingWritingState$ktor_io();
        }

        public IdleNonEmpty stopWriting$ktor_io() {
            return this.initial.getIdleState$ktor_io();
        }

        public String toString() {
            return "Writing";
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u0013"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$ReadingWriting;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "initial", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "readBuffer", "Ljava/nio/ByteBuffer;", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "writeBuffer", "getWriteBuffer", "stopReading", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Writing;", "stopReading$ktor_io", "stopWriting", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Reading;", "stopWriting$ktor_io", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class ReadingWriting extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ReadingWriting(Initial initial2) {
            super(initial2.backingBuffer, initial2.capacity, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(initial2, "initial");
            this.initial = initial2;
        }

        public ByteBuffer getReadBuffer() {
            return this.initial.getReadBuffer();
        }

        public ByteBuffer getWriteBuffer() {
            return this.initial.getWriteBuffer();
        }

        public Writing stopReading$ktor_io() {
            return this.initial.getWritingState$ktor_io();
        }

        public Reading stopWriting$ktor_io() {
            return this.initial.getReadingState$ktor_io();
        }

        public String toString() {
            return "Reading+Writing";
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/ktor/utils/io/internal/ReadWriteBufferState$Terminated;", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "()V", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReadWriteBufferState.kt */
    public static final class Terminated extends ReadWriteBufferState {
        public static final Terminated INSTANCE = new Terminated();

        private Terminated() {
            super(ReadWriteBufferStateKt.getEmptyByteBuffer(), ReadWriteBufferStateKt.getEmptyCapacity(), (DefaultConstructorMarker) null);
        }

        public String toString() {
            return "Terminated";
        }
    }
}
