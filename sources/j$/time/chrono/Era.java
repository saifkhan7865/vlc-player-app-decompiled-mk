package j$.time.chrono;

import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;

public interface Era extends TemporalAccessor, TemporalAdjuster {

    /* renamed from: j$.time.chrono.Era$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Temporal $default$adjustInto(Era era, Temporal temporal) {
            return temporal.with(ChronoField.ERA, (long) era.getValue());
        }

        public static int $default$get(Era era, TemporalField temporalField) {
            return temporalField == ChronoField.ERA ? era.getValue() : TemporalAccessor.CC.$default$get(era, temporalField);
        }

        public static long $default$getLong(Era era, TemporalField temporalField) {
            if (temporalField == ChronoField.ERA) {
                return (long) era.getValue();
            }
            if (!(temporalField instanceof ChronoField)) {
                return temporalField.getFrom(era);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }

        public static boolean $default$isSupported(Era era, TemporalField temporalField) {
            return temporalField instanceof ChronoField ? temporalField == ChronoField.ERA : temporalField != null && temporalField.isSupportedBy(era);
        }

        public static Object $default$query(Era era, TemporalQuery temporalQuery) {
            return temporalQuery == TemporalQueries.precision() ? ChronoUnit.ERAS : TemporalAccessor.CC.$default$query(era, temporalQuery);
        }

        public static ValueRange $default$range(Era era, TemporalField temporalField) {
            return TemporalAccessor.CC.$default$range(era, temporalField);
        }
    }

    int getValue();
}
