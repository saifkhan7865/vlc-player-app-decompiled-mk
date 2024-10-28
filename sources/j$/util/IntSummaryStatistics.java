package j$.util;

import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

public class IntSummaryStatistics implements IntConsumer {
    private long count;
    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;
    private long sum;

    public void accept(int i) {
        this.count++;
        this.sum += (long) i;
        this.min = Math.min(this.min, i);
        this.max = Math.max(this.max, i);
    }

    public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    public void combine(IntSummaryStatistics intSummaryStatistics) {
        this.count += intSummaryStatistics.count;
        this.sum += intSummaryStatistics.sum;
        this.min = Math.min(this.min, intSummaryStatistics.min);
        this.max = Math.max(this.max, intSummaryStatistics.max);
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

    public final int getMax() {
        return this.max;
    }

    public final int getMin() {
        return this.min;
    }

    public final long getSum() {
        return this.sum;
    }

    public String toString() {
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", new Object[]{getClass().getSimpleName(), Long.valueOf(getCount()), Long.valueOf(getSum()), Integer.valueOf(getMin()), Double.valueOf(getAverage()), Integer.valueOf(getMax())});
    }
}
