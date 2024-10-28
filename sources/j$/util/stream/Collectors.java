package j$.util.stream;

import j$.util.stream.Collector;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Collectors {
    static final Set CH_CONCURRENT_ID;
    static final Set CH_CONCURRENT_NOID;
    static final Set CH_ID;
    static final Set CH_NOID = Collections.emptySet();
    static final Set CH_UNORDERED_ID;
    static final Set CH_UNORDERED_NOID;

    static class CollectorImpl implements Collector {
        private final BiConsumer accumulator;
        private final Set characteristics;
        private final BinaryOperator combiner;
        private final Function finisher;
        private final Supplier supplier;

        CollectorImpl(Supplier supplier2, BiConsumer biConsumer, BinaryOperator binaryOperator, Set set) {
            this(supplier2, biConsumer, binaryOperator, Collectors.castingIdentity(), set);
        }

        CollectorImpl(Supplier supplier2, BiConsumer biConsumer, BinaryOperator binaryOperator, Function function, Set set) {
            this.supplier = supplier2;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.finisher = function;
            this.characteristics = set;
        }

        public BiConsumer accumulator() {
            return this.accumulator;
        }

        public Set characteristics() {
            return this.characteristics;
        }

        public BinaryOperator combiner() {
            return this.combiner;
        }

        public Function finisher() {
            return this.finisher;
        }

        public Supplier supplier() {
            return this.supplier;
        }
    }

    static {
        Collector.Characteristics characteristics = Collector.Characteristics.CONCURRENT;
        Collector.Characteristics characteristics2 = Collector.Characteristics.UNORDERED;
        Collector.Characteristics characteristics3 = Collector.Characteristics.IDENTITY_FINISH;
        CH_CONCURRENT_ID = Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2, characteristics3));
        CH_CONCURRENT_NOID = Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2));
        CH_ID = Collections.unmodifiableSet(EnumSet.of(characteristics3));
        CH_UNORDERED_ID = Collections.unmodifiableSet(EnumSet.of(characteristics2, characteristics3));
        CH_UNORDERED_NOID = Collections.unmodifiableSet(EnumSet.of(characteristics2));
    }

    /* access modifiers changed from: private */
    public static Function castingIdentity() {
        return new Collectors$$ExternalSyntheticLambda66();
    }

    static double computeFinalSum(double[] dArr) {
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        return (!Double.isNaN(d) || !Double.isInfinite(d2)) ? d : d2;
    }

    static /* synthetic */ Object lambda$castingIdentity$2(Object obj) {
        return obj;
    }

    static double[] sumWithCompensation(double[] dArr, double d) {
        double d2 = d - dArr[1];
        double d3 = dArr[0];
        double d4 = d3 + d2;
        dArr[1] = (d4 - d3) - d2;
        dArr[0] = d4;
        return dArr;
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorImpl(new Collectors$$ExternalSyntheticLambda22(), new Collectors$$ExternalSyntheticLambda23(), new Collectors$$ExternalSyntheticLambda65(), CH_ID);
    }
}
