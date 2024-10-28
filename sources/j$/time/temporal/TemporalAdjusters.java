package j$.time.temporal;

import j$.time.DayOfWeek;

public abstract class TemporalAdjusters {
    static /* synthetic */ Temporal lambda$nextOrSame$10(int i, Temporal temporal) {
        int i2 = temporal.get(ChronoField.DAY_OF_WEEK);
        if (i2 == i) {
            return temporal;
        }
        int i3 = i2 - i;
        return temporal.plus((long) (i3 >= 0 ? 7 - i3 : -i3), ChronoUnit.DAYS);
    }

    static /* synthetic */ Temporal lambda$previousOrSame$12(int i, Temporal temporal) {
        int i2 = temporal.get(ChronoField.DAY_OF_WEEK);
        if (i2 == i) {
            return temporal;
        }
        int i3 = i - i2;
        return temporal.minus((long) (i3 >= 0 ? 7 - i3 : -i3), ChronoUnit.DAYS);
    }

    public static TemporalAdjuster lastDayOfMonth() {
        return new TemporalAdjusters$$ExternalSyntheticLambda9();
    }

    public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
        return new TemporalAdjusters$$ExternalSyntheticLambda6(dayOfWeek.getValue());
    }

    public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
        return new TemporalAdjusters$$ExternalSyntheticLambda8(dayOfWeek.getValue());
    }
}
