package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

abstract class StreamSpliterators$SliceSpliterator {
    long fence;
    long index;
    Spliterator s;
    final long sliceFence;
    final long sliceOrigin;

    static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
        OfDouble(Spliterator.OfDouble ofDouble, long j, long j2) {
            super(ofDouble, j, j2);
        }

        OfDouble(Spliterator.OfDouble ofDouble, long j, long j2, long j3, long j4) {
            super(ofDouble, j, j2, j3, j4);
        }

        static /* synthetic */ void lambda$emptyConsumer$0(double d) {
        }

        /* access modifiers changed from: protected */
        public DoubleConsumer emptyConsumer() {
            return new StreamSpliterators$SliceSpliterator$OfDouble$$ExternalSyntheticLambda0();
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            super.forEachRemaining(doubleConsumer);
        }

        /* access modifiers changed from: protected */
        public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble ofDouble, long j, long j2, long j3, long j4) {
            return new OfDouble(ofDouble, j, j2, j3, j4);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return super.tryAdvance(doubleConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) StreamSpliterators$SliceSpliterator.super.trySplit();
        }
    }

    static final class OfInt extends OfPrimitive implements Spliterator.OfInt {
        OfInt(Spliterator.OfInt ofInt, long j, long j2) {
            super(ofInt, j, j2);
        }

        OfInt(Spliterator.OfInt ofInt, long j, long j2, long j3, long j4) {
            super(ofInt, j, j2, j3, j4);
        }

        static /* synthetic */ void lambda$emptyConsumer$0(int i) {
        }

        /* access modifiers changed from: protected */
        public IntConsumer emptyConsumer() {
            return new StreamSpliterators$SliceSpliterator$OfInt$$ExternalSyntheticLambda0();
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            super.forEachRemaining(intConsumer);
        }

        /* access modifiers changed from: protected */
        public Spliterator.OfInt makeSpliterator(Spliterator.OfInt ofInt, long j, long j2, long j3, long j4) {
            return new OfInt(ofInt, j, j2, j3, j4);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return super.tryAdvance(intConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) StreamSpliterators$SliceSpliterator.super.trySplit();
        }
    }

    static final class OfLong extends OfPrimitive implements Spliterator.OfLong {
        OfLong(Spliterator.OfLong ofLong, long j, long j2) {
            super(ofLong, j, j2);
        }

        OfLong(Spliterator.OfLong ofLong, long j, long j2, long j3, long j4) {
            super(ofLong, j, j2, j3, j4);
        }

        static /* synthetic */ void lambda$emptyConsumer$0(long j) {
        }

        /* access modifiers changed from: protected */
        public LongConsumer emptyConsumer() {
            return new StreamSpliterators$SliceSpliterator$OfLong$$ExternalSyntheticLambda0();
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            super.forEachRemaining(longConsumer);
        }

        /* access modifiers changed from: protected */
        public Spliterator.OfLong makeSpliterator(Spliterator.OfLong ofLong, long j, long j2, long j3, long j4) {
            return new OfLong(ofLong, j, j2, j3, j4);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return super.tryAdvance(longConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) StreamSpliterators$SliceSpliterator.super.trySplit();
        }
    }

    static abstract class OfPrimitive extends StreamSpliterators$SliceSpliterator implements Spliterator.OfPrimitive {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        OfPrimitive(j$.util.Spliterator.OfPrimitive r13, long r14, long r16) {
            /*
                r12 = this;
                long r0 = r13.estimateSize()
                r6 = r16
                long r10 = java.lang.Math.min(r0, r6)
                r8 = 0
                r2 = r12
                r3 = r13
                r4 = r14
                r2.<init>(r3, r4, r6, r8, r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.StreamSpliterators$SliceSpliterator.OfPrimitive.<init>(j$.util.Spliterator$OfPrimitive, long, long):void");
        }

        private OfPrimitive(Spliterator.OfPrimitive ofPrimitive, long j, long j2, long j3, long j4) {
            super(ofPrimitive, j, j2, j3, j4);
        }

        /* access modifiers changed from: protected */
        public abstract Object emptyConsumer();

        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
            long j = this.sliceOrigin;
            long j2 = this.fence;
            if (j < j2) {
                long j3 = this.index;
                if (j3 < j2) {
                    if (j3 < j || j3 + ((Spliterator.OfPrimitive) this.s).estimateSize() > this.sliceFence) {
                        while (this.sliceOrigin > this.index) {
                            ((Spliterator.OfPrimitive) this.s).tryAdvance(emptyConsumer());
                            this.index++;
                        }
                        while (this.index < this.fence) {
                            ((Spliterator.OfPrimitive) this.s).tryAdvance(obj);
                            this.index++;
                        }
                        return;
                    }
                    ((Spliterator.OfPrimitive) this.s).forEachRemaining(obj);
                    this.index = this.fence;
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
            long j;
            Objects.requireNonNull(obj);
            if (this.sliceOrigin >= this.fence) {
                return false;
            }
            while (true) {
                long j2 = this.sliceOrigin;
                j = this.index;
                if (j2 <= j) {
                    break;
                }
                ((Spliterator.OfPrimitive) this.s).tryAdvance(emptyConsumer());
                this.index++;
            }
            if (j >= this.fence) {
                return false;
            }
            this.index = j + 1;
            return ((Spliterator.OfPrimitive) this.s).tryAdvance(obj);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) StreamSpliterators$SliceSpliterator.super.trySplit();
        }
    }

    static final class OfRef extends StreamSpliterators$SliceSpliterator implements Spliterator {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        OfRef(j$.util.Spliterator r13, long r14, long r16) {
            /*
                r12 = this;
                long r0 = r13.estimateSize()
                r6 = r16
                long r10 = java.lang.Math.min(r0, r6)
                r8 = 0
                r2 = r12
                r3 = r13
                r4 = r14
                r2.<init>(r3, r4, r6, r8, r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.StreamSpliterators$SliceSpliterator.OfRef.<init>(j$.util.Spliterator, long, long):void");
        }

        private OfRef(Spliterator spliterator, long j, long j2, long j3, long j4) {
            super(spliterator, j, j2, j3, j4);
        }

        static /* synthetic */ void lambda$forEachRemaining$1(Object obj) {
        }

        static /* synthetic */ void lambda$tryAdvance$0(Object obj) {
        }

        public void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            long j = this.sliceOrigin;
            long j2 = this.fence;
            if (j < j2) {
                long j3 = this.index;
                if (j3 < j2) {
                    if (j3 < j || j3 + this.s.estimateSize() > this.sliceFence) {
                        while (this.sliceOrigin > this.index) {
                            this.s.tryAdvance(new StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda1());
                            this.index++;
                        }
                        while (this.index < this.fence) {
                            this.s.tryAdvance(consumer);
                            this.index++;
                        }
                        return;
                    }
                    this.s.forEachRemaining(consumer);
                    this.index = this.fence;
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
        public Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
            return new OfRef(spliterator, j, j2, j3, j4);
        }

        public boolean tryAdvance(Consumer consumer) {
            long j;
            Objects.requireNonNull(consumer);
            if (this.sliceOrigin >= this.fence) {
                return false;
            }
            while (true) {
                long j2 = this.sliceOrigin;
                j = this.index;
                if (j2 <= j) {
                    break;
                }
                this.s.tryAdvance(new StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda0());
                this.index++;
            }
            if (j >= this.fence) {
                return false;
            }
            this.index = j + 1;
            return this.s.tryAdvance(consumer);
        }
    }

    StreamSpliterators$SliceSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
        this.s = spliterator;
        this.sliceOrigin = j;
        this.sliceFence = j2;
        this.index = j3;
        this.fence = j4;
    }

    public int characteristics() {
        return this.s.characteristics();
    }

    public long estimateSize() {
        long j = this.sliceOrigin;
        long j2 = this.fence;
        if (j < j2) {
            return j2 - Math.max(j, this.index);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4);

    public Spliterator trySplit() {
        long j = this.sliceOrigin;
        long j2 = this.fence;
        if (j >= j2 || this.index >= j2) {
            return null;
        }
        while (true) {
            Spliterator trySplit = this.s.trySplit();
            if (trySplit == null) {
                return null;
            }
            long estimateSize = this.index + trySplit.estimateSize();
            long min = Math.min(estimateSize, this.sliceFence);
            long j3 = this.sliceOrigin;
            if (j3 >= min) {
                this.index = min;
            } else {
                long j4 = this.sliceFence;
                if (min >= j4) {
                    this.s = trySplit;
                    this.fence = min;
                } else {
                    long j5 = this.index;
                    if (j5 < j3 || estimateSize > j4) {
                        this.index = min;
                        return makeSpliterator(trySplit, j3, j4, j5, min);
                    }
                    this.index = min;
                    return trySplit;
                }
            }
        }
    }
}
