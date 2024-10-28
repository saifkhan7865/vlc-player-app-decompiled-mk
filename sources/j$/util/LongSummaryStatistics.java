package j$.util;

import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class LongSummaryStatistics implements LongConsumer, IntConsumer {
    private long count;
    private long max = Long.MIN_VALUE;
    private long min = Long.MAX_VALUE;
    private long sum;

    public void accept(int i) {
        accept((long) i);
    }

    public void accept(long j) {
        this.count++;
        this.sum += j;
        this.min = Math.min(this.min, j);
        this.max = Math.max(this.max, j);
    }

    public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    public void combine(LongSummaryStatistics longSummaryStatistics) {
        this.count += longSummaryStatistics.count;
        this.sum += longSummaryStatistics.sum;
        this.min = Math.min(this.min, longSummaryStatistics.min);
        this.max = Math.max(this.max, longSummaryStatistics.max);
    }

    public final double getAverage() {
        if (getCount() <= 0) {
            return 0.0d;
        }
        double sum2 = (double) getSum();
        double count2 = (double) getCount();
        Double.isNaN(sum2);
        Double.isNaN(count2);
        return sum2 / count2;
    }

    public final long getCount() {
        return this.count;
    }

    public final long getMax() {
        return this.max;
    }

    public final long getMin() {
        return this.min;
    }

    public final long getSum() {
        return this.sum;
    }

    public String toString() {
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", new Object[]{getClass().getSimpleName(), Long.valueOf(getCount()), Long.valueOf(getSum()), Long.valueOf(getMin()), Double.valueOf(getAverage()), Long.valueOf(getMax())});
    }
}
