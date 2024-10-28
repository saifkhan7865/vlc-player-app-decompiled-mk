package j$.time.temporal;

public interface Temporal extends TemporalAccessor {

    /* renamed from: j$.time.temporal.Temporal$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Temporal $default$minus(Temporal temporal, long j, TemporalUnit temporalUnit) {
            long j2;
            if (j == Long.MIN_VALUE) {
                temporal = temporal.plus(Long.MAX_VALUE, temporalUnit);
                j2 = 1;
            } else {
                j2 = -j;
            }
            return temporal.plus(j2, temporalUnit);
        }

        public static Temporal $default$plus(Temporal temporal, TemporalAmount temporalAmount) {
            return temporalAmount.addTo(temporal);
        }

        public static Temporal $default$with(Temporal temporal, TemporalAdjuster temporalAdjuster) {
            return temporalAdjuster.adjustInto(temporal);
        }
    }

    Temporal minus(long j, TemporalUnit temporalUnit);

    Temporal plus(long j, TemporalUnit temporalUnit);

    Temporal with(TemporalAdjuster temporalAdjuster);

    Temporal with(TemporalField temporalField, long j);
}
