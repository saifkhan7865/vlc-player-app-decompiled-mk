package j$.time.chrono;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.server.plugins.cors.CORSConfig;
import j$.time.Instant;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.util.Objects;

public interface ChronoLocalDateTime extends Temporal, TemporalAdjuster, Comparable {

    /* renamed from: j$.time.chrono.ChronoLocalDateTime$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Temporal $default$adjustInto(ChronoLocalDateTime chronoLocalDateTime, Temporal temporal) {
            return temporal.with(ChronoField.EPOCH_DAY, chronoLocalDateTime.toLocalDate().toEpochDay()).with(ChronoField.NANO_OF_DAY, chronoLocalDateTime.toLocalTime().toNanoOfDay());
        }

        public static int $default$compareTo(ChronoLocalDateTime chronoLocalDateTime, ChronoLocalDateTime chronoLocalDateTime2) {
            int compareTo = chronoLocalDateTime.toLocalDate().compareTo(chronoLocalDateTime2.toLocalDate());
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = chronoLocalDateTime.toLocalTime().compareTo(chronoLocalDateTime2.toLocalTime());
            return compareTo2 == 0 ? chronoLocalDateTime.getChronology().compareTo(chronoLocalDateTime2.getChronology()) : compareTo2;
        }

        public static Chronology $default$getChronology(ChronoLocalDateTime chronoLocalDateTime) {
            return chronoLocalDateTime.toLocalDate().getChronology();
        }

        public static boolean $default$isAfter(ChronoLocalDateTime chronoLocalDateTime, ChronoLocalDateTime chronoLocalDateTime2) {
            long epochDay = chronoLocalDateTime.toLocalDate().toEpochDay();
            long epochDay2 = chronoLocalDateTime2.toLocalDate().toEpochDay();
            return epochDay > epochDay2 || (epochDay == epochDay2 && chronoLocalDateTime.toLocalTime().toNanoOfDay() > chronoLocalDateTime2.toLocalTime().toNanoOfDay());
        }

        public static boolean $default$isBefore(ChronoLocalDateTime chronoLocalDateTime, ChronoLocalDateTime chronoLocalDateTime2) {
            long epochDay = chronoLocalDateTime.toLocalDate().toEpochDay();
            long epochDay2 = chronoLocalDateTime2.toLocalDate().toEpochDay();
            return epochDay < epochDay2 || (epochDay == epochDay2 && chronoLocalDateTime.toLocalTime().toNanoOfDay() < chronoLocalDateTime2.toLocalTime().toNanoOfDay());
        }

        public static ChronoLocalDateTime $default$minus(ChronoLocalDateTime chronoLocalDateTime, long j, TemporalUnit temporalUnit) {
            return ChronoLocalDateTimeImpl.ensureValid(chronoLocalDateTime.getChronology(), Temporal.CC.$default$minus(chronoLocalDateTime, j, temporalUnit));
        }

        public static Object $default$query(ChronoLocalDateTime chronoLocalDateTime, TemporalQuery temporalQuery) {
            if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset()) {
                return null;
            }
            return temporalQuery == TemporalQueries.localTime() ? chronoLocalDateTime.toLocalTime() : temporalQuery == TemporalQueries.chronology() ? chronoLocalDateTime.getChronology() : temporalQuery == TemporalQueries.precision() ? ChronoUnit.NANOS : temporalQuery.queryFrom(chronoLocalDateTime);
        }

        public static long $default$toEpochSecond(ChronoLocalDateTime chronoLocalDateTime, ZoneOffset zoneOffset) {
            Objects.requireNonNull(zoneOffset, TypedValues.CycleType.S_WAVE_OFFSET);
            return ((chronoLocalDateTime.toLocalDate().toEpochDay() * CORSConfig.CORS_DEFAULT_MAX_AGE) + ((long) chronoLocalDateTime.toLocalTime().toSecondOfDay())) - ((long) zoneOffset.getTotalSeconds());
        }

        public static Instant $default$toInstant(ChronoLocalDateTime chronoLocalDateTime, ZoneOffset zoneOffset) {
            return Instant.ofEpochSecond(chronoLocalDateTime.toEpochSecond(zoneOffset), (long) chronoLocalDateTime.toLocalTime().getNano());
        }
    }

    ChronoZonedDateTime atZone(ZoneId zoneId);

    int compareTo(ChronoLocalDateTime chronoLocalDateTime);

    Chronology getChronology();

    int hashCode();

    ChronoLocalDateTime minus(long j, TemporalUnit temporalUnit);

    long toEpochSecond(ZoneOffset zoneOffset);

    ChronoLocalDate toLocalDate();

    LocalTime toLocalTime();

    String toString();
}
