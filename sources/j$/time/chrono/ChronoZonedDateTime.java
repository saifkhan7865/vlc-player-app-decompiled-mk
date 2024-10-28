package j$.time.chrono;

import io.ktor.server.plugins.cors.CORSConfig;
import j$.time.Instant;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;

public interface ChronoZonedDateTime extends Temporal, Comparable {

    /* renamed from: j$.time.chrono.ChronoZonedDateTime$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static int $default$compareTo(ChronoZonedDateTime chronoZonedDateTime, ChronoZonedDateTime chronoZonedDateTime2) {
            int i = (chronoZonedDateTime.toEpochSecond() > chronoZonedDateTime2.toEpochSecond() ? 1 : (chronoZonedDateTime.toEpochSecond() == chronoZonedDateTime2.toEpochSecond() ? 0 : -1));
            if (i != 0) {
                return i;
            }
            int nano = chronoZonedDateTime.toLocalTime().getNano() - chronoZonedDateTime2.toLocalTime().getNano();
            if (nano != 0) {
                return nano;
            }
            int compareTo = chronoZonedDateTime.toLocalDateTime().compareTo(chronoZonedDateTime2.toLocalDateTime());
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = chronoZonedDateTime.getZone().getId().compareTo(chronoZonedDateTime2.getZone().getId());
            return compareTo2 == 0 ? chronoZonedDateTime.getChronology().compareTo(chronoZonedDateTime2.getChronology()) : compareTo2;
        }

        public static int $default$get(ChronoZonedDateTime chronoZonedDateTime, TemporalField temporalField) {
            if (!(temporalField instanceof ChronoField)) {
                return TemporalAccessor.CC.$default$get(chronoZonedDateTime, temporalField);
            }
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i != 1) {
                return i != 2 ? chronoZonedDateTime.toLocalDateTime().get(temporalField) : chronoZonedDateTime.getOffset().getTotalSeconds();
            }
            throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        }

        public static Chronology $default$getChronology(ChronoZonedDateTime chronoZonedDateTime) {
            return chronoZonedDateTime.toLocalDate().getChronology();
        }

        public static long $default$getLong(ChronoZonedDateTime chronoZonedDateTime, TemporalField temporalField) {
            if (!(temporalField instanceof ChronoField)) {
                return temporalField.getFrom(chronoZonedDateTime);
            }
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            return i != 1 ? i != 2 ? chronoZonedDateTime.toLocalDateTime().getLong(temporalField) : (long) chronoZonedDateTime.getOffset().getTotalSeconds() : chronoZonedDateTime.toEpochSecond();
        }

        public static ChronoZonedDateTime $default$minus(ChronoZonedDateTime chronoZonedDateTime, long j, TemporalUnit temporalUnit) {
            return ChronoZonedDateTimeImpl.ensureValid(chronoZonedDateTime.getChronology(), Temporal.CC.$default$minus(chronoZonedDateTime, j, temporalUnit));
        }

        public static Object $default$query(ChronoZonedDateTime chronoZonedDateTime, TemporalQuery temporalQuery) {
            return (temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.zoneId()) ? chronoZonedDateTime.getZone() : temporalQuery == TemporalQueries.offset() ? chronoZonedDateTime.getOffset() : temporalQuery == TemporalQueries.localTime() ? chronoZonedDateTime.toLocalTime() : temporalQuery == TemporalQueries.chronology() ? chronoZonedDateTime.getChronology() : temporalQuery == TemporalQueries.precision() ? ChronoUnit.NANOS : temporalQuery.queryFrom(chronoZonedDateTime);
        }

        public static ValueRange $default$range(ChronoZonedDateTime chronoZonedDateTime, TemporalField temporalField) {
            return temporalField instanceof ChronoField ? (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.OFFSET_SECONDS) ? temporalField.range() : chronoZonedDateTime.toLocalDateTime().range(temporalField) : temporalField.rangeRefinedBy(chronoZonedDateTime);
        }

        public static long $default$toEpochSecond(ChronoZonedDateTime chronoZonedDateTime) {
            return ((chronoZonedDateTime.toLocalDate().toEpochDay() * CORSConfig.CORS_DEFAULT_MAX_AGE) + ((long) chronoZonedDateTime.toLocalTime().toSecondOfDay())) - ((long) chronoZonedDateTime.getOffset().getTotalSeconds());
        }

        public static Instant $default$toInstant(ChronoZonedDateTime chronoZonedDateTime) {
            return Instant.ofEpochSecond(chronoZonedDateTime.toEpochSecond(), (long) chronoZonedDateTime.toLocalTime().getNano());
        }

        public static ChronoLocalDate $default$toLocalDate(ChronoZonedDateTime chronoZonedDateTime) {
            return chronoZonedDateTime.toLocalDateTime().toLocalDate();
        }

        public static LocalTime $default$toLocalTime(ChronoZonedDateTime chronoZonedDateTime) {
            return chronoZonedDateTime.toLocalDateTime().toLocalTime();
        }

        public static ChronoZonedDateTime $default$with(ChronoZonedDateTime chronoZonedDateTime, TemporalAdjuster temporalAdjuster) {
            return ChronoZonedDateTimeImpl.ensureValid(chronoZonedDateTime.getChronology(), Temporal.CC.$default$with(chronoZonedDateTime, temporalAdjuster));
        }
    }

    /* renamed from: j$.time.chrono.ChronoZonedDateTime$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                j$.time.temporal.ChronoField[] r0 = j$.time.temporal.ChronoField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$time$temporal$ChronoField = r0
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.INSTANT_SECONDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x001d }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.OFFSET_SECONDS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ChronoZonedDateTime.AnonymousClass1.<clinit>():void");
        }
    }

    int compareTo(ChronoZonedDateTime chronoZonedDateTime);

    Chronology getChronology();

    ZoneOffset getOffset();

    ZoneId getZone();

    ChronoZonedDateTime minus(long j, TemporalUnit temporalUnit);

    long toEpochSecond();

    ChronoLocalDate toLocalDate();

    ChronoLocalDateTime toLocalDateTime();

    LocalTime toLocalTime();

    ChronoZonedDateTime with(TemporalAdjuster temporalAdjuster);

    ChronoZonedDateTime withZoneSameLocal(ZoneId zoneId);
}
