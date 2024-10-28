package j$.time.temporal;

import j$.time.DateTimeException;
import j$.time.Instant$$ExternalSyntheticBackport6;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.format.ResolverStyle;
import java.util.Map;
import org.fusesource.jansi.AnsiRenderer;

public abstract class JulianFields {
    public static final TemporalField JULIAN_DAY = Field.JULIAN_DAY;
    public static final TemporalField MODIFIED_JULIAN_DAY = Field.MODIFIED_JULIAN_DAY;
    public static final TemporalField RATA_DIE = Field.RATA_DIE;

    private enum Field implements TemporalField {
        JULIAN_DAY("JulianDay", r4, r5, 2440588),
        MODIFIED_JULIAN_DAY("ModifiedJulianDay", r4, r5, 40587),
        RATA_DIE("RataDie", r4, r5, 719163);
        
        private static final long serialVersionUID = -7501623920830201812L;
        private final transient TemporalUnit baseUnit;
        private final transient String name;
        private final transient long offset;
        private final transient ValueRange range;
        private final transient TemporalUnit rangeUnit;

        private Field(String str, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, long j) {
            this.name = str;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = ValueRange.of(-365243219162L + j, 365241780471L + j);
            this.offset = j;
        }

        public Temporal adjustInto(Temporal temporal, long j) {
            if (range().isValidValue(j)) {
                return temporal.with(ChronoField.EPOCH_DAY, Instant$$ExternalSyntheticBackport6.m(j, this.offset));
            }
            String str = this.name;
            throw new DateTimeException("Invalid value: " + str + AnsiRenderer.CODE_TEXT_SEPARATOR + j);
        }

        public long getFrom(TemporalAccessor temporalAccessor) {
            return temporalAccessor.getLong(ChronoField.EPOCH_DAY) + this.offset;
        }

        public boolean isDateBased() {
            return true;
        }

        public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
        }

        public boolean isTimeBased() {
            return false;
        }

        public ValueRange range() {
            return this.range;
        }

        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            if (isSupportedBy(temporalAccessor)) {
                return range();
            }
            throw new DateTimeException("Unsupported field: " + this);
        }

        public ChronoLocalDate resolve(Map map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            long longValue = ((Long) map.remove(this)).longValue();
            Chronology from = Chronology.CC.from(temporalAccessor);
            if (resolverStyle == ResolverStyle.LENIENT) {
                return from.dateEpochDay(Instant$$ExternalSyntheticBackport6.m(longValue, this.offset));
            }
            range().checkValidValue(longValue, this);
            return from.dateEpochDay(longValue - this.offset);
        }

        public String toString() {
            return this.name;
        }
    }
}
