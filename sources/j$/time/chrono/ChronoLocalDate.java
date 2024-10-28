package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;

public interface ChronoLocalDate extends Temporal, TemporalAdjuster, Comparable {

    /* renamed from: j$.time.chrono.ChronoLocalDate$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Temporal $default$adjustInto(ChronoLocalDate chronoLocalDate, Temporal temporal) {
            return temporal.with(ChronoField.EPOCH_DAY, chronoLocalDate.toEpochDay());
        }

        public static ChronoLocalDateTime $default$atTime(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
            return ChronoLocalDateTimeImpl.of(chronoLocalDate, localTime);
        }

        public static int $default$compareTo(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
            int i = (chronoLocalDate.toEpochDay() > chronoLocalDate2.toEpochDay() ? 1 : (chronoLocalDate.toEpochDay() == chronoLocalDate2.toEpochDay() ? 0 : -1));
            return i == 0 ? chronoLocalDate.getChronology().compareTo(chronoLocalDate2.getChronology()) : i;
        }

        public static Era $default$getEra(ChronoLocalDate chronoLocalDate) {
            return chronoLocalDate.getChronology().eraOf(chronoLocalDate.get(ChronoField.ERA));
        }

        public static boolean $default$isBefore(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
            return chronoLocalDate.toEpochDay() < chronoLocalDate2.toEpochDay();
        }

        public static boolean $default$isLeapYear(ChronoLocalDate chronoLocalDate) {
            return chronoLocalDate.getChronology().isLeapYear(chronoLocalDate.getLong(ChronoField.YEAR));
        }

        public static boolean $default$isSupported(ChronoLocalDate chronoLocalDate, TemporalField temporalField) {
            return temporalField instanceof ChronoField ? temporalField.isDateBased() : temporalField != null && temporalField.isSupportedBy(chronoLocalDate);
        }

        public static int $default$lengthOfYear(ChronoLocalDate chronoLocalDate) {
            return chronoLocalDate.isLeapYear() ? 366 : 365;
        }

        public static ChronoLocalDate $default$minus(ChronoLocalDate chronoLocalDate, long j, TemporalUnit temporalUnit) {
            return ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), Temporal.CC.$default$minus(chronoLocalDate, j, temporalUnit));
        }

        public static ChronoLocalDate $default$plus(ChronoLocalDate chronoLocalDate, long j, TemporalUnit temporalUnit) {
            if (!(temporalUnit instanceof ChronoUnit)) {
                return ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), temporalUnit.addTo(chronoLocalDate, j));
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }

        public static ChronoLocalDate $default$plus(ChronoLocalDate chronoLocalDate, TemporalAmount temporalAmount) {
            return ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), Temporal.CC.$default$plus(chronoLocalDate, temporalAmount));
        }

        public static Object $default$query(ChronoLocalDate chronoLocalDate, TemporalQuery temporalQuery) {
            if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset() || temporalQuery == TemporalQueries.localTime()) {
                return null;
            }
            return temporalQuery == TemporalQueries.chronology() ? chronoLocalDate.getChronology() : temporalQuery == TemporalQueries.precision() ? ChronoUnit.DAYS : temporalQuery.queryFrom(chronoLocalDate);
        }

        public static long $default$toEpochDay(ChronoLocalDate chronoLocalDate) {
            return chronoLocalDate.getLong(ChronoField.EPOCH_DAY);
        }

        public static ChronoLocalDate $default$with(ChronoLocalDate chronoLocalDate, TemporalAdjuster temporalAdjuster) {
            return ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), Temporal.CC.$default$with(chronoLocalDate, temporalAdjuster));
        }

        public static ChronoLocalDate $default$with(ChronoLocalDate chronoLocalDate, TemporalField temporalField, long j) {
            if (!(temporalField instanceof ChronoField)) {
                return ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), temporalField.adjustInto(chronoLocalDate, j));
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
    }

    ChronoLocalDateTime atTime(LocalTime localTime);

    int compareTo(ChronoLocalDate chronoLocalDate);

    boolean equals(Object obj);

    Chronology getChronology();

    Era getEra();

    int hashCode();

    boolean isLeapYear();

    boolean isSupported(TemporalField temporalField);

    int lengthOfYear();

    ChronoLocalDate minus(long j, TemporalUnit temporalUnit);

    ChronoLocalDate plus(long j, TemporalUnit temporalUnit);

    ChronoLocalDate plus(TemporalAmount temporalAmount);

    long toEpochDay();

    String toString();

    ChronoLocalDate with(TemporalAdjuster temporalAdjuster);

    ChronoLocalDate with(TemporalField temporalField, long j);
}
