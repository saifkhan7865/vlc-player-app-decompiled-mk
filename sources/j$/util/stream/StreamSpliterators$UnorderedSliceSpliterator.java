package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.StreamSpliterators$ArrayBuffer;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

abstract class StreamSpliterators$UnorderedSliceSpliterator {
    protected final int chunkSize;
    private final AtomicLong permits;
    protected final Spliterator s;
    private final long skipThreshold;
    protected final boolean unlimited;

    static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble, DoubleConsumer {
        double tmpValue;

        OfDouble(Spliterator.OfDouble ofDouble, long j, long j2) {
            super(ofDouble, j, j2);
        }

        OfDouble(Spliterator.OfDouble ofDouble, OfDouble ofDouble2) {
            super(ofDouble, ofDouble2);
        }

        public void accept(double d) {
            this.tmpValue = d;
        }

        /* access modifiers changed from: protected */
        public void acceptConsumed(DoubleConsumer doubleConsumer) {
            doubleConsumer.accept(this.tmpValue);
        }

        public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        /* access modifiers changed from: protected */
        public StreamSpliterators$ArrayBuffer.OfDouble bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfDouble(i);
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            super.forEachRemaining(doubleConsumer);
        }

        /* access modifiers changed from: protected */
        public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble ofDouble) {
            return new OfDouble(ofDouble, this);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return super.tryAdvance(doubleConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) StreamSpliterators$UnorderedSliceSpliterator.super.trySplit();
        }
    }

    static final class OfInt extends OfPrimitive implements Spliterator.OfInt, IntConsumer {
        int tmpValue;

        OfInt(Spliterator.OfInt ofInt, long j, long j2) {
            super(ofInt, j, j2);
        }

        OfInt(Spliterator.OfInt ofInt, OfInt ofInt2) {
            super(ofInt, ofInt2);
        }

        public void accept(int i) {
            this.tmpValue = i;
        }

        /* access modifiers changed from: protected */
        public void acceptConsumed(IntConsumer intConsumer) {
            intConsumer.accept(this.tmpValue);
        }

        public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return IntConsumer$CC.$default$andThen(this, intConsumer);
        }

        /* access modifiers changed from: protected */
        public StreamSpliterators$ArrayBuffer.OfInt bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfInt(i);
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            super.forEachRemaining(intConsumer);
        }

        /* access modifiers changed from: protected */
        public Spliterator.OfInt makeSpliterator(Spliterator.OfInt ofInt) {
            return new OfInt(ofInt, this);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return super.tryAdvance(intConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) StreamSpliterators$UnorderedSliceSpliterator.super.trySplit();
        }
    }

    static final class OfLong extends OfPrimitive implements Spliterator.OfLong, LongConsumer {
        long tmpValue;

        OfLong(Spliterator.OfLong ofLong, long j, long j2) {
            super(ofLong, j, j2);
        }

        OfLong(Spliterator.OfLong ofLong, OfLong ofLong2) {
            super(ofLong, ofLong2);
        }

        public void accept(long j) {
            this.tmpValue = j;
        }

        /* access modifiers changed from: protected */
        public void acceptConsumed(LongConsumer longConsumer) {
            longConsumer.accept(this.tmpValue);
        }

        public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return LongConsumer$CC.$default$andThen(this, longConsumer);
        }

        /* access modifiers changed from: protected */
        public StreamSpliterators$ArrayBuffer.OfLong bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfLong(i);
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            super.forEachRemaining(longConsumer);
        }

        /* access modifiers changed from: protected */
        public Spliterator.OfLong makeSpliterator(Spliterator.OfLong ofLong) {
            return new OfLong(ofLong, this);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return super.tryAdvance(longConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) StreamSpliterators$UnorderedSliceSpliterator.super.trySplit();
        }
    }

    static abstract class OfPrimitive extends StreamSpliterators$UnorderedSliceSpliterator implements Spliterator.OfPrimitive {
        OfPrimitive(Spliterator.OfPrimitive ofPrimitive, long j, long j2) {
            super(ofPrimitive, j, j2);
        }

        OfPrimitive(Spliterator.OfPrimitive ofPrimitive, OfPrimitive ofPrimitive2) {
            super(ofPrimitive, ofPrimitive2);
        }

        /* access modifiers changed from: protected */
        public abstract void acceptConsumed(Object obj);

        /* access modifiers changed from: protected */
        public abstract StreamSpliterators$ArrayBuffer.OfPrimitive bufferCreate(int i);

        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
            StreamSpliterators$ArrayBuffer.OfPrimitive ofPrimitive = null;
            while (true) {
                PermitStatus permitStatus = permitStatus();
                if (permitStatus == PermitStatus.NO_MORE) {
                    return;
                }
                if (permitStatus == PermitStatus.MAYBE_MORE) {
                    if (ofPrimitive == null) {
                        ofPrimitive = bufferCreate(this.chunkSize);
                    } else {
                        ofPrimitive.reset();
                    }
                    long j = 0;
                    while (((Spliterator.OfPrimitive) this.s).tryAdvance(ofPrimitive)) {
                        j++;
                        if (j >= ((long) this.chunkSize)) {
                            break;
                        }
                    }
                    if (j != 0) {
                        ofPrimitive.forEach(obj, acquirePermits(j));
                    } else {
                        return;
                    }
                } else {
                    ((Spliterator.OfPrimitive) this.s).forEachRemaining(obj);
                    return;
                }
            }
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Object obj) {
            Objects.requireNonNull(obj);
            while (permitStatus() != PermitStatus.NO_MORE && ((Spliterator.OfPrimitive) this.s).tryAdvance(this)) {
                if (acquirePermits(1) == 1) {
                    acceptConsumed(obj);
                    return true;
                }
            }
            return false;
        }

        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) StreamSpliterators$UnorderedSliceSpliterator.super.trySplit();
        }
    }

    static final class OfRef extends StreamSpliterators$UnorderedSliceSpliterator implements Spliterator, Consumer {
        Object tmpSlot;

        OfRef(Spliterator spliterator, long j, long j2) {
            super(spliterator, j, j2);
        }

        OfRef(Spliterator spliterator, OfRef ofRef) {
            super(spliterator, ofRef);
        }

        public final void accept(Object obj) {
            this.tmpSlot = obj;
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            StreamSpliterators$ArrayBuffer.OfRef ofRef = null;
            while (true) {
                PermitStatus permitStatus = permitStatus();
                if (permitStatus == PermitStatus.NO_MORE) {
                    return;
                }
                if (permitStatus == PermitStatus.MAYBE_MORE) {
                    if (ofRef == null) {
                        ofRef = new StreamSpliterators$ArrayBuffer.OfRef(this.chunkSize);
                    } else {
                        ofRef.reset();
                    }
                    long j = 0;
                    while (this.s.tryAdvance(ofRef)) {
                        j++;
                        if (j >= ((long) this.chunkSize)) {
                            break;
                        }
                    }
                    if (j != 0) {
                        ofRef.forEach(consumer, acquirePermits(j));
                    } else {
                        return;
                    }
                } else {
                    this.s.forEachRemaining(consumer);
                    return;
                }
            }
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        /* access modifiers changed from: protected */
        public Spliterator makeSpliterator(Spliterator spliterator) {
            return new OfRef(spliterator, this);
        }

        public boolean tryAdvance(Consumer consumer) {
            Objects.requireNonNull(consumer);
            while (permitStatus() != PermitStatus.NO_MORE && this.s.tryAdvance(this)) {
                if (acquirePermits(1) == 1) {
                    consumer.accept(this.tmpSlot);
                    this.tmpSlot = null;
                    return true;
                }
            }
            return false;
        }
    }

    enum PermitStatus {
        NO_MORE,
        MAYBE_MORE,
        UNLIMITED
    }

    StreamSpliterators$UnorderedSliceSpliterator(Spliterator spliterator, long j, long j2) {
        this.s = spliterator;
        this.unlimited = j2 < 0;
        this.skipThreshold = j2 >= 0 ? j2 : 0;
        this.chunkSize = 128;
        this.permits = new AtomicLong(j2 >= 0 ? j + j2 : j);
    }

    StreamSpliterators$UnorderedSliceSpliterator(Spliterator spliterator, StreamSpliterators$UnorderedSliceSpliterator streamSpliterators$UnorderedSliceSpliterator) {
        this.s = spliterator;
        this.unlimited = streamSpliterators$UnorderedSliceSpliterator.unlimited;
        this.permits = streamSpliterators$UnorderedSliceSpliterator.permits;
        this.skipThreshold = streamSpliterators$UnorderedSliceSpliterator.skipThreshold;
        this.chunkSize = streamSpliterators$UnorderedSliceSpliterator.chunkSize;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long acquirePermits(long r10) {
        /*
            r9 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicLong r0 = r9.permits
            long r0 = r0.get()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0013
            boolean r0 = r9.unlimited
            if (r0 == 0) goto L_0x0011
            goto L_0x0012
        L_0x0011:
            r10 = r2
        L_0x0012:
            return r10
        L_0x0013:
            long r4 = java.lang.Math.min(r0, r10)
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0025
            java.util.concurrent.atomic.AtomicLong r6 = r9.permits
            long r7 = r0 - r4
            boolean r6 = r6.compareAndSet(r0, r7)
            if (r6 == 0) goto L_0x0000
        L_0x0025:
            boolean r6 = r9.unlimited
            if (r6 == 0) goto L_0x002f
            long r10 = r10 - r4
            long r10 = java.lang.Math.max(r10, r2)
            return r10
        L_0x002f:
            long r10 = r9.skipThreshold
            int r6 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x003c
            long r0 = r0 - r10
            long r4 = r4 - r0
            long r10 = java.lang.Math.max(r4, r2)
            return r10
        L_0x003c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.acquirePermits(long):long");
    }

    public final int characteristics() {
        return this.s.characteristics() & -16465;
    }

    public final long estimateSize() {
        return this.s.estimateSize();
    }

    /* access modifiers changed from: protected */
    public abstract Spliterator makeSpliterator(Spliterator spliterator);

    /* access modifiers changed from: protected */
    public final PermitStatus permitStatus() {
        return this.permits.get() > 0 ? PermitStatus.MAYBE_MORE : this.unlimited ? PermitStatus.UNLIMITED : PermitStatus.NO_MORE;
    }

    public final Spliterator trySplit() {
        Spliterator trySplit;
        if (this.permits.get() == 0 || (trySplit = this.s.trySplit()) == null) {
            return null;
        }
        return makeSpliterator(trySplit);
    }
}
