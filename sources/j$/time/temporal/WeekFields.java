package j$.time.temporal;

import j$.time.Clock$OffsetClock$$ExternalSyntheticBackport0;
import j$.time.DateTimeException;
import j$.time.DayOfWeek;
import j$.time.Duration$$ExternalSyntheticBackport1;
import j$.time.Instant$$ExternalSyntheticBackport6;
import j$.time.LocalDate$$ExternalSyntheticBackport5;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.format.ResolverStyle;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import org.fusesource.jansi.AnsiRenderer;

public final class WeekFields implements Serializable {
    private static final ConcurrentMap CACHE = new ConcurrentHashMap(4, 0.75f, 2);
    public static final WeekFields ISO = new WeekFields(DayOfWeek.MONDAY, 4);
    public static final WeekFields SUNDAY_START = of(DayOfWeek.SUNDAY, 1);
    public static final TemporalUnit WEEK_BASED_YEARS = IsoFields.WEEK_BASED_YEARS;
    private static final long serialVersionUID = -1177360819670808121L;
    /* access modifiers changed from: private */
    public final transient TemporalField dayOfWeek = ComputedDayOfField.ofDayOfWeekField(this);
    private final DayOfWeek firstDayOfWeek;
    private final int minimalDays;
    /* access modifiers changed from: private */
    public final transient TemporalField weekBasedYear = ComputedDayOfField.ofWeekBasedYearField(this);
    private final transient TemporalField weekOfMonth = ComputedDayOfField.ofWeekOfMonthField(this);
    /* access modifiers changed from: private */
    public final transient TemporalField weekOfWeekBasedYear = ComputedDayOfField.ofWeekOfWeekBasedYearField(this);
    private final transient TemporalField weekOfYear = ComputedDayOfField.ofWeekOfYearField(this);

    static class ComputedDayOfField implements TemporalField {
        private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(1, 7);
        private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.of(0, 1, 4, 6);
        private static final ValueRange WEEK_OF_WEEK_BASED_YEAR_RANGE = ValueRange.of(1, 52, 53);
        private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.of(0, 1, 52, 54);
        private final TemporalUnit baseUnit;
        private final String name;
        private final ValueRange range;
        private final TemporalUnit rangeUnit;
        private final WeekFields weekDef;

        private ComputedDayOfField(String str, WeekFields weekFields, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
            this.name = str;
            this.weekDef = weekFields;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = valueRange;
        }

        private int computeWeek(int i, int i2) {
            return ((i + 7) + (i2 - 1)) / 7;
        }

        private int localizedDayOfWeek(int i) {
            return WeekFields$ComputedDayOfField$$ExternalSyntheticBackport0.m(i - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
        }

        private int localizedDayOfWeek(TemporalAccessor temporalAccessor) {
            return WeekFields$ComputedDayOfField$$ExternalSyntheticBackport0.m(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
        }

        private int localizedWeekBasedYear(TemporalAccessor temporalAccessor) {
            int localizedDayOfWeek = localizedDayOfWeek(temporalAccessor);
            int i = temporalAccessor.get(ChronoField.YEAR);
            ChronoField chronoField = ChronoField.DAY_OF_YEAR;
            int i2 = temporalAccessor.get(chronoField);
            int startOfWeekOffset = startOfWeekOffset(i2, localizedDayOfWeek);
            int computeWeek = computeWeek(startOfWeekOffset, i2);
            return computeWeek == 0 ? i - 1 : computeWeek >= computeWeek(startOfWeekOffset, ((int) temporalAccessor.range(chronoField).getMaximum()) + this.weekDef.getMinimalDaysInFirstWeek()) ? i + 1 : i;
        }

        private long localizedWeekOfMonth(TemporalAccessor temporalAccessor) {
            int localizedDayOfWeek = localizedDayOfWeek(temporalAccessor);
            int i = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
            return (long) computeWeek(startOfWeekOffset(i, localizedDayOfWeek), i);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x002c, code lost:
            r5 = computeWeek(r0, ((int) r5.range(r1).getMaximum()) + r4.weekDef.getMinimalDaysInFirstWeek());
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int localizedWeekOfWeekBasedYear(j$.time.temporal.TemporalAccessor r5) {
            /*
                r4 = this;
                int r0 = r4.localizedDayOfWeek((j$.time.temporal.TemporalAccessor) r5)
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.DAY_OF_YEAR
                int r2 = r5.get(r1)
                int r0 = r4.startOfWeekOffset(r2, r0)
                int r3 = r4.computeWeek(r0, r2)
                if (r3 != 0) goto L_0x0028
                j$.time.chrono.Chronology r0 = j$.time.chrono.Chronology.CC.from(r5)
                j$.time.chrono.ChronoLocalDate r5 = r0.date(r5)
                long r0 = (long) r2
                j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.DAYS
                j$.time.chrono.ChronoLocalDate r5 = r5.minus(r0, r2)
                int r5 = r4.localizedWeekOfWeekBasedYear(r5)
                return r5
            L_0x0028:
                r2 = 50
                if (r3 <= r2) goto L_0x0045
                j$.time.temporal.ValueRange r5 = r5.range(r1)
                long r1 = r5.getMaximum()
                int r5 = (int) r1
                j$.time.temporal.WeekFields r1 = r4.weekDef
                int r1 = r1.getMinimalDaysInFirstWeek()
                int r5 = r5 + r1
                int r5 = r4.computeWeek(r0, r5)
                if (r3 < r5) goto L_0x0045
                int r3 = r3 - r5
                int r3 = r3 + 1
            L_0x0045:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.time.temporal.WeekFields.ComputedDayOfField.localizedWeekOfWeekBasedYear(j$.time.temporal.TemporalAccessor):int");
        }

        private long localizedWeekOfYear(TemporalAccessor temporalAccessor) {
            int localizedDayOfWeek = localizedDayOfWeek(temporalAccessor);
            int i = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
            return (long) computeWeek(startOfWeekOffset(i, localizedDayOfWeek), i);
        }

        static ComputedDayOfField ofDayOfWeekField(WeekFields weekFields) {
            return new ComputedDayOfField("DayOfWeek", weekFields, ChronoUnit.DAYS, ChronoUnit.WEEKS, DAY_OF_WEEK_RANGE);
        }

        private ChronoLocalDate ofWeekBasedYear(Chronology chronology, int i, int i2, int i3) {
            ChronoLocalDate date = chronology.date(i, 1, 1);
            int startOfWeekOffset = startOfWeekOffset(1, localizedDayOfWeek((TemporalAccessor) date));
            return date.plus((long) ((-startOfWeekOffset) + (i3 - 1) + ((Math.min(i2, computeWeek(startOfWeekOffset, date.lengthOfYear() + this.weekDef.getMinimalDaysInFirstWeek()) - 1) - 1) * 7)), ChronoUnit.DAYS);
        }

        static ComputedDayOfField ofWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekBasedYear", weekFields, IsoFields.WEEK_BASED_YEARS, ChronoUnit.FOREVER, ChronoField.YEAR.range());
        }

        static ComputedDayOfField ofWeekOfMonthField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfMonth", weekFields, ChronoUnit.WEEKS, ChronoUnit.MONTHS, WEEK_OF_MONTH_RANGE);
        }

        static ComputedDayOfField ofWeekOfWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfWeekBasedYear", weekFields, ChronoUnit.WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfYear", weekFields, ChronoUnit.WEEKS, ChronoUnit.YEARS, WEEK_OF_YEAR_RANGE);
        }

        private ValueRange rangeByWeek(TemporalAccessor temporalAccessor, TemporalField temporalField) {
            int startOfWeekOffset = startOfWeekOffset(temporalAccessor.get(temporalField), localizedDayOfWeek(temporalAccessor));
            ValueRange range2 = temporalAccessor.range(temporalField);
            return ValueRange.of((long) computeWeek(startOfWeekOffset, (int) range2.getMinimum()), (long) computeWeek(startOfWeekOffset, (int) range2.getMaximum()));
        }

        private ValueRange rangeWeekOfWeekBasedYear(TemporalAccessor temporalAccessor) {
            ChronoField chronoField = ChronoField.DAY_OF_YEAR;
            if (!temporalAccessor.isSupported(chronoField)) {
                return WEEK_OF_YEAR_RANGE;
            }
            int localizedDayOfWeek = localizedDayOfWeek(temporalAccessor);
            int i = temporalAccessor.get(chronoField);
            int startOfWeekOffset = startOfWeekOffset(i, localizedDayOfWeek);
            int computeWeek = computeWeek(startOfWeekOffset, i);
            if (computeWeek == 0) {
                return rangeWeekOfWeekBasedYear(Chronology.CC.from(temporalAccessor).date(temporalAccessor).minus((long) (i + 7), ChronoUnit.DAYS));
            }
            int maximum = (int) temporalAccessor.range(chronoField).getMaximum();
            int computeWeek2 = computeWeek(startOfWeekOffset, this.weekDef.getMinimalDaysInFirstWeek() + maximum);
            return computeWeek >= computeWeek2 ? rangeWeekOfWeekBasedYear(Chronology.CC.from(temporalAccessor).date(temporalAccessor).plus((long) ((maximum - i) + 8), ChronoUnit.DAYS)) : ValueRange.of(1, (long) (computeWeek2 - 1));
        }

        private ChronoLocalDate resolveWBY(Map map, Chronology chronology, int i, ResolverStyle resolverStyle) {
            ChronoLocalDate chronoLocalDate;
            int checkValidIntValue = this.weekDef.weekBasedYear.range().checkValidIntValue(((Long) map.get(this.weekDef.weekBasedYear)).longValue(), this.weekDef.weekBasedYear);
            if (resolverStyle == ResolverStyle.LENIENT) {
                chronoLocalDate = ofWeekBasedYear(chronology, checkValidIntValue, 1, i).plus(Instant$$ExternalSyntheticBackport6.m(((Long) map.get(this.weekDef.weekOfWeekBasedYear)).longValue(), 1), ChronoUnit.WEEKS);
            } else {
                chronoLocalDate = ofWeekBasedYear(chronology, checkValidIntValue, this.weekDef.weekOfWeekBasedYear.range().checkValidIntValue(((Long) map.get(this.weekDef.weekOfWeekBasedYear)).longValue(), this.weekDef.weekOfWeekBasedYear), i);
                if (resolverStyle == ResolverStyle.STRICT && localizedWeekBasedYear(chronoLocalDate) != checkValidIntValue) {
                    throw new DateTimeException("Strict mode rejected resolved date as it is in a different week-based-year");
                }
            }
            map.remove(this);
            map.remove(this.weekDef.weekBasedYear);
            map.remove(this.weekDef.weekOfWeekBasedYear);
            map.remove(ChronoField.DAY_OF_WEEK);
            return chronoLocalDate;
        }

        private ChronoLocalDate resolveWoM(Map map, Chronology chronology, int i, long j, long j2, int i2, ResolverStyle resolverStyle) {
            ChronoLocalDate chronoLocalDate;
            if (resolverStyle == ResolverStyle.LENIENT) {
                ChronoLocalDate plus = chronology.date(i, 1, 1).plus(Instant$$ExternalSyntheticBackport6.m(j, 1), ChronoUnit.MONTHS);
                chronoLocalDate = plus.plus(Clock$OffsetClock$$ExternalSyntheticBackport0.m(Duration$$ExternalSyntheticBackport1.m(Instant$$ExternalSyntheticBackport6.m(j2, localizedWeekOfMonth(plus)), (long) 7), (long) (i2 - localizedDayOfWeek((TemporalAccessor) plus))), ChronoUnit.DAYS);
            } else {
                ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
                ChronoLocalDate date = chronology.date(i, chronoField.checkValidIntValue(j), 1);
                chronoLocalDate = date.plus((long) ((((int) (((long) this.range.checkValidIntValue(j2, this)) - localizedWeekOfMonth(date))) * 7) + (i2 - localizedDayOfWeek((TemporalAccessor) date))), ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && chronoLocalDate.getLong(chronoField) != j) {
                    throw new DateTimeException("Strict mode rejected resolved date as it is in a different month");
                }
            }
            map.remove(this);
            map.remove(ChronoField.YEAR);
            map.remove(ChronoField.MONTH_OF_YEAR);
            map.remove(ChronoField.DAY_OF_WEEK);
            return chronoLocalDate;
        }

        private ChronoLocalDate resolveWoY(Map map, Chronology chronology, int i, long j, int i2, ResolverStyle resolverStyle) {
            ChronoLocalDate chronoLocalDate;
            ChronoLocalDate date = chronology.date(i, 1, 1);
            if (resolverStyle == ResolverStyle.LENIENT) {
                chronoLocalDate = date.plus(Clock$OffsetClock$$ExternalSyntheticBackport0.m(Duration$$ExternalSyntheticBackport1.m(Instant$$ExternalSyntheticBackport6.m(j, localizedWeekOfYear(date)), (long) 7), (long) (i2 - localizedDayOfWeek((TemporalAccessor) date))), ChronoUnit.DAYS);
            } else {
                chronoLocalDate = date.plus((long) ((((int) (((long) this.range.checkValidIntValue(j, this)) - localizedWeekOfYear(date))) * 7) + (i2 - localizedDayOfWeek((TemporalAccessor) date))), ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && chronoLocalDate.getLong(ChronoField.YEAR) != ((long) i)) {
                    throw new DateTimeException("Strict mode rejected resolved date as it is in a different year");
                }
            }
            map.remove(this);
            map.remove(ChronoField.YEAR);
            map.remove(ChronoField.DAY_OF_WEEK);
            return chronoLocalDate;
        }

        private int startOfWeekOffset(int i, int i2) {
            int m = WeekFields$ComputedDayOfField$$ExternalSyntheticBackport0.m(i - i2, 7);
            return m + 1 > this.weekDef.getMinimalDaysInFirstWeek() ? 7 - m : -m;
        }

        public Temporal adjustInto(Temporal temporal, long j) {
            int checkValidIntValue = this.range.checkValidIntValue(j, this);
            int i = temporal.get(this);
            if (checkValidIntValue == i) {
                return temporal;
            }
            if (this.rangeUnit != ChronoUnit.FOREVER) {
                return temporal.plus((long) (checkValidIntValue - i), this.baseUnit);
            }
            int i2 = temporal.get(this.weekDef.dayOfWeek);
            return ofWeekBasedYear(Chronology.CC.from(temporal), (int) j, temporal.get(this.weekDef.weekOfWeekBasedYear), i2);
        }

        public long getFrom(TemporalAccessor temporalAccessor) {
            int localizedWeekBasedYear;
            TemporalUnit temporalUnit = this.rangeUnit;
            if (temporalUnit == ChronoUnit.WEEKS) {
                localizedWeekBasedYear = localizedDayOfWeek(temporalAccessor);
            } else if (temporalUnit == ChronoUnit.MONTHS) {
                return localizedWeekOfMonth(temporalAccessor);
            } else {
                if (temporalUnit == ChronoUnit.YEARS) {
                    return localizedWeekOfYear(temporalAccessor);
                }
                if (temporalUnit == WeekFields.WEEK_BASED_YEARS) {
                    localizedWeekBasedYear = localizedWeekOfWeekBasedYear(temporalAccessor);
                } else if (temporalUnit == ChronoUnit.FOREVER) {
                    localizedWeekBasedYear = localizedWeekBasedYear(temporalAccessor);
                } else {
                    TemporalUnit temporalUnit2 = this.rangeUnit;
                    throw new IllegalStateException("unreachable, rangeUnit: " + temporalUnit2 + ", this: " + this);
                }
            }
            return (long) localizedWeekBasedYear;
        }

        public boolean isDateBased() {
            return true;
        }

        public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            ChronoField chronoField;
            if (!temporalAccessor.isSupported(ChronoField.DAY_OF_WEEK)) {
                return false;
            }
            TemporalUnit temporalUnit = this.rangeUnit;
            if (temporalUnit == ChronoUnit.WEEKS) {
                return true;
            }
            if (temporalUnit == ChronoUnit.MONTHS) {
                chronoField = ChronoField.DAY_OF_MONTH;
            } else if (temporalUnit == ChronoUnit.YEARS || temporalUnit == WeekFields.WEEK_BASED_YEARS) {
                chronoField = ChronoField.DAY_OF_YEAR;
            } else if (temporalUnit != ChronoUnit.FOREVER) {
                return false;
            } else {
                chronoField = ChronoField.YEAR;
            }
            return temporalAccessor.isSupported(chronoField);
        }

        public boolean isTimeBased() {
            return false;
        }

        public ValueRange range() {
            return this.range;
        }

        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            TemporalUnit temporalUnit = this.rangeUnit;
            if (temporalUnit == ChronoUnit.WEEKS) {
                return this.range;
            }
            if (temporalUnit == ChronoUnit.MONTHS) {
                return rangeByWeek(temporalAccessor, ChronoField.DAY_OF_MONTH);
            }
            if (temporalUnit == ChronoUnit.YEARS) {
                return rangeByWeek(temporalAccessor, ChronoField.DAY_OF_YEAR);
            }
            if (temporalUnit == WeekFields.WEEK_BASED_YEARS) {
                return rangeWeekOfWeekBasedYear(temporalAccessor);
            }
            if (temporalUnit == ChronoUnit.FOREVER) {
                return ChronoField.YEAR.range();
            }
            TemporalUnit temporalUnit2 = this.rangeUnit;
            throw new IllegalStateException("unreachable, rangeUnit: " + temporalUnit2 + ", this: " + this);
        }

        public ChronoLocalDate resolve(Map map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            long longValue = ((Long) map.get(this)).longValue();
            int m = LocalDate$$ExternalSyntheticBackport5.m(longValue);
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                long m2 = (long) (WeekFields$ComputedDayOfField$$ExternalSyntheticBackport0.m((this.weekDef.getFirstDayOfWeek().getValue() - 1) + (this.range.checkValidIntValue(longValue, this) - 1), 7) + 1);
                map.remove(this);
                map.put(ChronoField.DAY_OF_WEEK, Long.valueOf(m2));
                return null;
            }
            ChronoField chronoField = ChronoField.DAY_OF_WEEK;
            if (!map.containsKey(chronoField)) {
                return null;
            }
            int localizedDayOfWeek = localizedDayOfWeek(chronoField.checkValidIntValue(((Long) map.get(chronoField)).longValue()));
            Chronology from = Chronology.CC.from(temporalAccessor);
            ChronoField chronoField2 = ChronoField.YEAR;
            if (map.containsKey(chronoField2)) {
                int checkValidIntValue = chronoField2.checkValidIntValue(((Long) map.get(chronoField2)).longValue());
                if (this.rangeUnit == ChronoUnit.MONTHS) {
                    ChronoField chronoField3 = ChronoField.MONTH_OF_YEAR;
                    if (map.containsKey(chronoField3)) {
                        long j = (long) m;
                        return resolveWoM(map, from, checkValidIntValue, ((Long) map.get(chronoField3)).longValue(), j, localizedDayOfWeek, resolverStyle);
                    }
                }
                if (this.rangeUnit == ChronoUnit.YEARS) {
                    return resolveWoY(map, from, checkValidIntValue, (long) m, localizedDayOfWeek, resolverStyle);
                }
            } else {
                TemporalUnit temporalUnit = this.rangeUnit;
                if ((temporalUnit == WeekFields.WEEK_BASED_YEARS || temporalUnit == ChronoUnit.FOREVER) && map.containsKey(this.weekDef.weekBasedYear) && map.containsKey(this.weekDef.weekOfWeekBasedYear)) {
                    return resolveWBY(map, from, localizedDayOfWeek, resolverStyle);
                }
            }
            return null;
        }

        public String toString() {
            String str = this.name;
            String weekFields = this.weekDef.toString();
            return str + "[" + weekFields + "]";
        }
    }

    private WeekFields(DayOfWeek dayOfWeek2, int i) {
        Objects.requireNonNull(dayOfWeek2, "firstDayOfWeek");
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Minimal number of days is invalid");
        }
        this.firstDayOfWeek = dayOfWeek2;
        this.minimalDays = i;
    }

    public static WeekFields of(DayOfWeek dayOfWeek2, int i) {
        String str = dayOfWeek2.toString() + i;
        ConcurrentMap concurrentMap = CACHE;
        WeekFields weekFields = (WeekFields) concurrentMap.get(str);
        if (weekFields != null) {
            return weekFields;
        }
        concurrentMap.putIfAbsent(str, new WeekFields(dayOfWeek2, i));
        return (WeekFields) concurrentMap.get(str);
    }

    public static WeekFields of(Locale locale) {
        Objects.requireNonNull(locale, "locale");
        Calendar instance = Calendar.getInstance(new Locale(locale.getLanguage(), locale.getCountry()));
        return of(DayOfWeek.SUNDAY.plus((long) (instance.getFirstDayOfWeek() - 1)), instance.getMinimalDaysInFirstWeek());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        if (this.firstDayOfWeek != null) {
            int i = this.minimalDays;
            if (i < 1 || i > 7) {
                throw new InvalidObjectException("Minimal number of days is invalid");
            }
            return;
        }
        throw new InvalidObjectException("firstDayOfWeek is null");
    }

    private Object readResolve() {
        try {
            return of(this.firstDayOfWeek, this.minimalDays);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            throw new InvalidObjectException("Invalid serialized WeekFields: " + message);
        }
    }

    public TemporalField dayOfWeek() {
        return this.dayOfWeek;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WeekFields) && hashCode() == obj.hashCode();
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDays;
    }

    public int hashCode() {
        return (this.firstDayOfWeek.ordinal() * 7) + this.minimalDays;
    }

    public String toString() {
        DayOfWeek dayOfWeek2 = this.firstDayOfWeek;
        int i = this.minimalDays;
        return "WeekFields[" + dayOfWeek2 + AnsiRenderer.CODE_LIST_SEPARATOR + i + "]";
    }

    public TemporalField weekBasedYear() {
        return this.weekBasedYear;
    }

    public TemporalField weekOfMonth() {
        return this.weekOfMonth;
    }

    public TemporalField weekOfWeekBasedYear() {
        return this.weekOfWeekBasedYear;
    }
}
