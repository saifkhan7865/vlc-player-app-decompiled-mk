package j$.util.concurrent;

import j$.util.Spliterator;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.StreamSupport;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class ThreadLocalRandom extends Random {
    private static final ThreadLocal instances = new ThreadLocal() {
        /* access modifiers changed from: protected */
        public ThreadLocalRandom initialValue() {
            return new ThreadLocalRandom();
        }
    };
    private static final ThreadLocal nextLocalGaussian = new ThreadLocal();
    private static final AtomicInteger probeGenerator = new AtomicInteger();
    private static final AtomicLong seeder = new AtomicLong(mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime()));
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("rnd", Long.TYPE), new ObjectStreamField("initialized", Boolean.TYPE)};
    private static final long serialVersionUID = -5851777807851030925L;
    boolean initialized;
    int threadLocalRandomProbe;
    long threadLocalRandomSeed;

    private static final class RandomDoublesSpliterator implements Spliterator.OfDouble {
        final double bound;
        final long fence;
        long index;
        final double origin;

        RandomDoublesSpliterator(long j, long j2, double d, double d2) {
            this.index = j;
            this.fence = j2;
            this.origin = d;
            this.bound = d2;
        }

        public int characteristics() {
            return 17728;
        }

        public long estimateSize() {
            return this.fence - this.index;
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
        }

        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                double d = this.origin;
                double d2 = this.bound;
                ThreadLocalRandom current = ThreadLocalRandom.current();
                do {
                    doubleConsumer.accept(current.internalNextDouble(d, d2));
                    j++;
                } while (j < j2);
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

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
        }

        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            doubleConsumer.accept(ThreadLocalRandom.current().internalNextDouble(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        public RandomDoublesSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomDoublesSpliterator(j, j2, this.origin, this.bound);
        }
    }

    private static final class RandomIntsSpliterator implements Spliterator.OfInt {
        final int bound;
        final long fence;
        long index;
        final int origin;

        RandomIntsSpliterator(long j, long j2, int i, int i2) {
            this.index = j;
            this.fence = j2;
            this.origin = i;
            this.bound = i2;
        }

        public int characteristics() {
            return 17728;
        }

        public long estimateSize() {
            return this.fence - this.index;
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
        }

        public void forEachRemaining(IntConsumer intConsumer) {
            intConsumer.getClass();
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                int i = this.origin;
                int i2 = this.bound;
                ThreadLocalRandom current = ThreadLocalRandom.current();
                do {
                    intConsumer.accept(current.internalNextInt(i, i2));
                    j++;
                } while (j < j2);
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

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
        }

        public boolean tryAdvance(IntConsumer intConsumer) {
            intConsumer.getClass();
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            intConsumer.accept(ThreadLocalRandom.current().internalNextInt(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        public RandomIntsSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomIntsSpliterator(j, j2, this.origin, this.bound);
        }
    }

    private static final class RandomLongsSpliterator implements Spliterator.OfLong {
        final long bound;
        final long fence;
        long index;
        final long origin;

        RandomLongsSpliterator(long j, long j2, long j3, long j4) {
            this.index = j;
            this.fence = j2;
            this.origin = j3;
            this.bound = j4;
        }

        public int characteristics() {
            return 17728;
        }

        public long estimateSize() {
            return this.fence - this.index;
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
        }

        public void forEachRemaining(LongConsumer longConsumer) {
            longConsumer.getClass();
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                long j3 = this.origin;
                long j4 = this.bound;
                ThreadLocalRandom current = ThreadLocalRandom.current();
                do {
                    longConsumer.accept(current.internalNextLong(j3, j4));
                    j++;
                } while (j < j2);
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

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
        }

        public boolean tryAdvance(LongConsumer longConsumer) {
            longConsumer.getClass();
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            longConsumer.accept(ThreadLocalRandom.current().internalNextLong(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        public RandomLongsSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomLongsSpliterator(j, j2, this.origin, this.bound);
        }
    }

    static {
        if (((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
            public Boolean run() {
                return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
            }
        })).booleanValue()) {
            byte[] seed = SecureRandom.getSeed(8);
            long j = ((long) seed[0]) & 255;
            for (int i = 1; i < 8; i++) {
                j = (j << 8) | (((long) seed[i]) & 255);
            }
            seeder.set(j);
        }
    }

    private ThreadLocalRandom() {
        this.initialized = true;
    }

    static final int advanceProbe(int i) {
        int i2 = i ^ (i << 13);
        int i3 = i2 ^ (i2 >>> 17);
        int i4 = i3 ^ (i3 << 5);
        ((ThreadLocalRandom) instances.get()).threadLocalRandomProbe = i4;
        return i4;
    }

    public static ThreadLocalRandom current() {
        ThreadLocalRandom threadLocalRandom = (ThreadLocalRandom) instances.get();
        if (threadLocalRandom.threadLocalRandomProbe == 0) {
            localInit();
        }
        return threadLocalRandom;
    }

    static final int getProbe() {
        return ((ThreadLocalRandom) instances.get()).threadLocalRandomProbe;
    }

    static final void localInit() {
        int addAndGet = probeGenerator.addAndGet(-1640531527);
        if (addAndGet == 0) {
            addAndGet = 1;
        }
        long mix64 = mix64(seeder.getAndAdd(-4942790177534073029L));
        ThreadLocalRandom threadLocalRandom = (ThreadLocalRandom) instances.get();
        threadLocalRandom.threadLocalRandomSeed = mix64;
        threadLocalRandom.threadLocalRandomProbe = addAndGet;
    }

    private static int mix32(long j) {
        long j2 = (j ^ (j >>> 33)) * -49064778989728563L;
        return (int) (((j2 ^ (j2 >>> 33)) * -4265267296055464877L) >>> 32);
    }

    private static long mix64(long j) {
        long j2 = (j ^ (j >>> 33)) * -49064778989728563L;
        long j3 = (j2 ^ (j2 >>> 33)) * -4265267296055464877L;
        return j3 ^ (j3 >>> 33);
    }

    private Object readResolve() {
        return current();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        ObjectOutputStream.PutField putFields = objectOutputStream.putFields();
        putFields.put("rnd", this.threadLocalRandomSeed);
        putFields.put("initialized", true);
        objectOutputStream.writeFields();
    }

    public DoubleStream doubles() {
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, Long.MAX_VALUE, Double.MAX_VALUE, 0.0d), false);
    }

    public DoubleStream doubles(double d, double d2) {
        if (d < d2) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, Long.MAX_VALUE, d, d2), false);
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    public DoubleStream doubles(long j) {
        if (j >= 0) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, j, Double.MAX_VALUE, 0.0d), false);
        }
        throw new IllegalArgumentException("size must be non-negative");
    }

    public DoubleStream doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        } else if (d < d2) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, j, d, d2), false);
        } else {
            throw new IllegalArgumentException("bound must be greater than origin");
        }
    }

    /* access modifiers changed from: package-private */
    public final double internalNextDouble(double d, double d2) {
        double nextLong = (double) (nextLong() >>> 11);
        Double.isNaN(nextLong);
        double d3 = nextLong * 1.1102230246251565E-16d;
        if (d >= d2) {
            return d3;
        }
        double d4 = (d3 * (d2 - d)) + d;
        return d4 >= d2 ? Double.longBitsToDouble(Double.doubleToLongBits(d2) - 1) : d4;
    }

    /* access modifiers changed from: package-private */
    public final int internalNextInt(int i, int i2) {
        int i3;
        int mix32 = mix32(nextSeed());
        if (i >= i2) {
            return mix32;
        }
        int i4 = i2 - i;
        int i5 = i4 - 1;
        if ((i4 & i5) == 0) {
            i3 = mix32 & i5;
        } else if (i4 > 0) {
            int i6 = mix32 >>> 1;
            while (true) {
                int i7 = i6 + i5;
                i3 = i6 % i4;
                if (i7 - i3 >= 0) {
                    break;
                }
                i6 = mix32(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (mix32 >= i && mix32 < i2) {
                    return mix32;
                }
                mix32 = mix32(nextSeed());
            }
        }
        return i3 + i;
    }

    /* access modifiers changed from: package-private */
    public final long internalNextLong(long j, long j2) {
        long mix64 = mix64(nextSeed());
        if (j >= j2) {
            return mix64;
        }
        long j3 = j2 - j;
        long j4 = j3 - 1;
        if ((j3 & j4) == 0) {
            return (mix64 & j4) + j;
        }
        if (j3 > 0) {
            while (true) {
                long j5 = mix64 >>> 1;
                long j6 = j5 + j4;
                long j7 = j5 % j3;
                if (j6 - j7 >= 0) {
                    return j7 + j;
                }
                mix64 = mix64(nextSeed());
            }
        } else {
            while (true) {
                if (mix64 >= j && mix64 < j2) {
                    return mix64;
                }
                mix64 = mix64(nextSeed());
            }
        }
    }

    public IntStream ints() {
        return StreamSupport.intStream(new RandomIntsSpliterator(0, Long.MAX_VALUE, Integer.MAX_VALUE, 0), false);
    }

    public IntStream ints(int i, int i2) {
        if (i < i2) {
            return StreamSupport.intStream(new RandomIntsSpliterator(0, Long.MAX_VALUE, i, i2), false);
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    public IntStream ints(long j) {
        if (j >= 0) {
            return StreamSupport.intStream(new RandomIntsSpliterator(0, j, Integer.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException("size must be non-negative");
    }

    public IntStream ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        } else if (i < i2) {
            return StreamSupport.intStream(new RandomIntsSpliterator(0, j, i, i2), false);
        } else {
            throw new IllegalArgumentException("bound must be greater than origin");
        }
    }

    public LongStream longs() {
        return StreamSupport.longStream(new RandomLongsSpliterator(0, Long.MAX_VALUE, Long.MAX_VALUE, 0), false);
    }

    public LongStream longs(long j) {
        if (j >= 0) {
            return StreamSupport.longStream(new RandomLongsSpliterator(0, j, Long.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException("size must be non-negative");
    }

    public LongStream longs(long j, long j2) {
        if (j < j2) {
            return StreamSupport.longStream(new RandomLongsSpliterator(0, Long.MAX_VALUE, j, j2), false);
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    public LongStream longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        } else if (j2 < j3) {
            return StreamSupport.longStream(new RandomLongsSpliterator(0, j, j2, j3), false);
        } else {
            throw new IllegalArgumentException("bound must be greater than origin");
        }
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        return nextInt() >>> (32 - i);
    }

    public boolean nextBoolean() {
        return mix32(nextSeed()) < 0;
    }

    public double nextDouble() {
        double mix64 = (double) (mix64(nextSeed()) >>> 11);
        Double.isNaN(mix64);
        return mix64 * 1.1102230246251565E-16d;
    }

    public double nextDouble(double d) {
        if (d > 0.0d) {
            double mix64 = (double) (mix64(nextSeed()) >>> 11);
            Double.isNaN(mix64);
            double d2 = mix64 * 1.1102230246251565E-16d * d;
            return d2 < d ? d2 : Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
        }
        throw new IllegalArgumentException("bound must be positive");
    }

    public float nextFloat() {
        return ((float) (mix32(nextSeed()) >>> 8)) * 5.9604645E-8f;
    }

    public double nextGaussian() {
        ThreadLocal threadLocal = nextLocalGaussian;
        Double d = (Double) threadLocal.get();
        if (d != null) {
            threadLocal.set((Object) null);
            return d.doubleValue();
        }
        while (true) {
            double nextDouble = (nextDouble() * 2.0d) - 1.0d;
            double nextDouble2 = (nextDouble() * 2.0d) - 1.0d;
            double d2 = (nextDouble * nextDouble) + (nextDouble2 * nextDouble2);
            if (d2 < 1.0d && d2 != 0.0d) {
                double sqrt = StrictMath.sqrt((StrictMath.log(d2) * -2.0d) / d2);
                nextLocalGaussian.set(Double.valueOf(nextDouble2 * sqrt));
                return nextDouble * sqrt;
            }
        }
    }

    public int nextInt() {
        return mix32(nextSeed());
    }

    public int nextInt(int i) {
        if (i > 0) {
            int mix32 = mix32(nextSeed());
            int i2 = i - 1;
            if ((i & i2) == 0) {
                return mix32 & i2;
            }
            while (true) {
                int i3 = mix32 >>> 1;
                int i4 = i3 + i2;
                int i5 = i3 % i;
                if (i4 - i5 >= 0) {
                    return i5;
                }
                mix32 = mix32(nextSeed());
            }
        } else {
            throw new IllegalArgumentException("bound must be positive");
        }
    }

    public int nextInt(int i, int i2) {
        if (i < i2) {
            return internalNextInt(i, i2);
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    public long nextLong() {
        return mix64(nextSeed());
    }

    public long nextLong(long j) {
        if (j > 0) {
            long mix64 = mix64(nextSeed());
            long j2 = j - 1;
            if ((j & j2) == 0) {
                return mix64 & j2;
            }
            while (true) {
                long j3 = mix64 >>> 1;
                long j4 = j3 + j2;
                long j5 = j3 % j;
                if (j4 - j5 >= 0) {
                    return j5;
                }
                mix64 = mix64(nextSeed());
            }
        } else {
            throw new IllegalArgumentException("bound must be positive");
        }
    }

    public long nextLong(long j, long j2) {
        if (j < j2) {
            return internalNextLong(j, j2);
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    /* access modifiers changed from: package-private */
    public final long nextSeed() {
        long j = this.threadLocalRandomSeed - 7046029254386353131L;
        this.threadLocalRandomSeed = j;
        return j;
    }

    public void setSeed(long j) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
    }
}
