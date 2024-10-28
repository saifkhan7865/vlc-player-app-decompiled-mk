package j$.time.temporal;

public enum ChronoField implements TemporalField {
    NANO_OF_SECOND("NanoOfSecond", r4, r17, ValueRange.of(0, 999999999)),
    NANO_OF_DAY("NanoOfDay", r4, r27, ValueRange.of(0, 86399999999999L)),
    MICRO_OF_SECOND("MicroOfSecond", r4, r17, ValueRange.of(0, 999999)),
    MICRO_OF_DAY("MicroOfDay", r4, r27, ValueRange.of(0, 86399999999L)),
    MILLI_OF_SECOND("MilliOfSecond", r4, r17, ValueRange.of(0, 999)),
    MILLI_OF_DAY("MilliOfDay", r4, r27, ValueRange.of(0, 86399999)),
    SECOND_OF_MINUTE("SecondOfMinute", r17, r32, ValueRange.of(0, 59), "second"),
    SECOND_OF_DAY("SecondOfDay", r17, r5, ValueRange.of(0, 86399)),
    MINUTE_OF_HOUR("MinuteOfHour", r32, r13, ValueRange.of(0, 59), "minute"),
    MINUTE_OF_DAY("MinuteOfDay", r32, r5, ValueRange.of(0, 1439)),
    HOUR_OF_AMPM("HourOfAmPm", r13, r16, ValueRange.of(0, 11)),
    CLOCK_HOUR_OF_AMPM("ClockHourOfAmPm", r4, r16, ValueRange.of(1, 12)),
    HOUR_OF_DAY("HourOfDay", r13, r24, ValueRange.of(0, 23), "hour"),
    CLOCK_HOUR_OF_DAY("ClockHourOfDay", r4, r27, ValueRange.of(1, 24)),
    AMPM_OF_DAY("AmPmOfDay", r16, r24, ValueRange.of(0, 1), "dayperiod"),
    DAY_OF_WEEK("DayOfWeek", r23, r36, ValueRange.of(1, 7), "weekday"),
    ALIGNED_DAY_OF_WEEK_IN_MONTH("AlignedDayOfWeekInMonth", r4, r36, ValueRange.of(1, 7)),
    ALIGNED_DAY_OF_WEEK_IN_YEAR("AlignedDayOfWeekInYear", r4, r36, ValueRange.of(1, 7)),
    DAY_OF_MONTH("DayOfMonth", r23, r24, ValueRange.of(1, 28, 31), "day"),
    DAY_OF_YEAR("DayOfYear", r4, r41, ValueRange.of(1, 365, 366)),
    EPOCH_DAY("EpochDay", r4, r44, ValueRange.of(-365243219162L, 365241780471L)),
    ALIGNED_WEEK_OF_MONTH("AlignedWeekOfMonth", r23, r24, ValueRange.of(1, 4, 5)),
    ALIGNED_WEEK_OF_YEAR("AlignedWeekOfYear", r23, r24, ValueRange.of(1, 53)),
    MONTH_OF_YEAR("MonthOfYear", r15, r24, ValueRange.of(1, 12), "month"),
    PROLEPTIC_MONTH("ProlepticMonth", r15, r44, ValueRange.of(-11999999988L, 11999999999L)),
    YEAR_OF_ERA("YearOfEra", r41, r5, ValueRange.of(1, 999999999, 1000000000)),
    YEAR("Year", r41, r24, ValueRange.of(-999999999, 999999999), "year"),
    ERA("Era", ChronoUnit.ERAS, r24, ValueRange.of(0, 1), "era"),
    INSTANT_SECONDS("InstantSeconds", r4, r5, ValueRange.of(Long.MIN_VALUE, Long.MAX_VALUE)),
    OFFSET_SECONDS("OffsetSeconds", r4, r5, ValueRange.of(-64800, 64800));
    
    private final TemporalUnit baseUnit;
    private final String displayNameKey;
    private final String name;
    private final ValueRange range;
    private final TemporalUnit rangeUnit;

    private ChronoField(String str, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
        this.name = str;
        this.baseUnit = temporalUnit;
        this.rangeUnit = temporalUnit2;
        this.range = valueRange;
        this.displayNameKey = null;
    }

    private ChronoField(String str, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange, String str2) {
        this.name = str;
        this.baseUnit = temporalUnit;
        this.rangeUnit = temporalUnit2;
        this.range = valueRange;
        this.displayNameKey = str2;
    }

    public Temporal adjustInto(Temporal temporal, long j) {
        return temporal.with(this, j);
    }

    public int checkValidIntValue(long j) {
        return range().checkValidIntValue(j, this);
    }

    public long checkValidValue(long j) {
        return range().checkValidValue(j, this);
    }

    public long getFrom(TemporalAccessor temporalAccessor) {
        return temporalAccessor.getLong(this);
    }

    public boolean isDateBased() {
        return ordinal() >= DAY_OF_WEEK.ordinal() && ordinal() <= ERA.ordinal();
    }

    public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
        return temporalAccessor.isSupported(this);
    }

    public boolean isTimeBased() {
        return ordinal() < DAY_OF_WEEK.ordinal();
    }

    public ValueRange range() {
        return this.range;
    }

    public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
        return temporalAccessor.range(this);
    }

    public String toString() {
        return this.name;
    }
}
