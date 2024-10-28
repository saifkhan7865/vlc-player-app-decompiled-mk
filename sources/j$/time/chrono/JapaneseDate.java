package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.ChronoField;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public final class JapaneseDate extends ChronoLocalDateImpl implements ChronoLocalDate, Serializable {
    static final LocalDate MEIJI_6_ISODATE = LocalDate.of(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private transient JapaneseEra era;
    private final transient LocalDate isoDate;
    private transient int yearOfEra;

    /* renamed from: j$.time.chrono.JapaneseDate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                j$.time.temporal.ChronoField[] r0 = j$.time.temporal.ChronoField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$time$temporal$ChronoField = r0
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.DAY_OF_MONTH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x001d }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.DAY_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0028 }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.YEAR_OF_ERA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0033 }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x003e }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0049 }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.ALIGNED_WEEK_OF_MONTH     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0054 }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0060 }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.ERA     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x006c }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.YEAR     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.JapaneseDate.AnonymousClass1.<clinit>():void");
        }
    }

    JapaneseDate(LocalDate localDate) {
        if (!localDate.isBefore(MEIJI_6_ISODATE)) {
            this.era = JapaneseEra.from(localDate);
            this.yearOfEra = (localDate.getYear() - this.era.getSince().getYear()) + 1;
            this.isoDate = localDate;
            return;
        }
        throw new DateTimeException("JapaneseDate before Meiji 6 is not supported");
    }

    JapaneseDate(JapaneseEra japaneseEra, int i, LocalDate localDate) {
        if (!localDate.isBefore(MEIJI_6_ISODATE)) {
            this.era = japaneseEra;
            this.yearOfEra = i;
            this.isoDate = localDate;
            return;
        }
        throw new DateTimeException("JapaneseDate before Meiji 6 is not supported");
    }

    public static JapaneseDate of(JapaneseEra japaneseEra, int i, int i2, int i3) {
        Objects.requireNonNull(japaneseEra, "era");
        LocalDate of = LocalDate.of((japaneseEra.getSince().getYear() + i) - 1, i2, i3);
        if (!of.isBefore(japaneseEra.getSince()) && japaneseEra == JapaneseEra.from(of)) {
            return new JapaneseDate(japaneseEra, i, of);
        }
        throw new DateTimeException("year, month, and day not valid for Era");
    }

    static JapaneseDate ofYearDay(JapaneseEra japaneseEra, int i, int i2) {
        Objects.requireNonNull(japaneseEra, "era");
        int year = japaneseEra.getSince().getYear();
        LocalDate ofYearDay = i == 1 ? LocalDate.ofYearDay(year, (japaneseEra.getSince().getDayOfYear() + i2) - 1) : LocalDate.ofYearDay((year + i) - 1, i2);
        if (!ofYearDay.isBefore(japaneseEra.getSince()) && japaneseEra == JapaneseEra.from(ofYearDay)) {
            return new JapaneseDate(japaneseEra, i, ofYearDay);
        }
        throw new DateTimeException("Invalid parameters");
    }

    static JapaneseDate readExternal(DataInput dataInput) {
        return JapaneseChronology.INSTANCE.date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private JapaneseDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new JapaneseDate(localDate);
    }

    private JapaneseDate withYear(int i) {
        return withYear(getEra(), i);
    }

    private JapaneseDate withYear(JapaneseEra japaneseEra, int i) {
        return with(this.isoDate.withYear(JapaneseChronology.INSTANCE.prolepticYear(japaneseEra, i)));
    }

    private Object writeReplace() {
        return new Ser((byte) 4, this);
    }

    public final ChronoLocalDateTime atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JapaneseDate) {
            return this.isoDate.equals(((JapaneseDate) obj).isoDate);
        }
        return false;
    }

    public JapaneseChronology getChronology() {
        return JapaneseChronology.INSTANCE;
    }

    public JapaneseEra getEra() {
        return this.era;
    }

    public long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()]) {
            case 2:
                return this.yearOfEra == 1 ? (long) ((this.isoDate.getDayOfYear() - this.era.getSince().getDayOfYear()) + 1) : (long) this.isoDate.getDayOfYear();
            case 3:
                return (long) this.yearOfEra;
            case 4:
            case 5:
            case 6:
            case 7:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            case 8:
                return (long) this.era.getValue();
            default:
                return this.isoDate.getLong(temporalField);
        }
    }

    public int hashCode() {
        return getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    public boolean isSupported(TemporalField temporalField) {
        if (temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH || temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR || temporalField == ChronoField.ALIGNED_WEEK_OF_MONTH || temporalField == ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        return temporalField instanceof ChronoField ? temporalField.isDateBased() : temporalField != null && temporalField.isSupportedBy(this);
    }

    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    public int lengthOfYear() {
        JapaneseEra next = this.era.next();
        int lengthOfYear = (next == null || next.getSince().getYear() != this.isoDate.getYear()) ? this.isoDate.lengthOfYear() : next.getSince().getDayOfYear() - 1;
        return this.yearOfEra == 1 ? lengthOfYear - (this.era.getSince().getDayOfYear() - 1) : lengthOfYear;
    }

    public JapaneseDate minus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.minus(j, temporalUnit);
    }

    public JapaneseDate plus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.plus(j, temporalUnit);
    }

    public JapaneseDate plus(TemporalAmount temporalAmount) {
        return (JapaneseDate) super.plus(temporalAmount);
    }

    /* access modifiers changed from: package-private */
    public JapaneseDate plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    /* access modifiers changed from: package-private */
    public JapaneseDate plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    /* access modifiers changed from: package-private */
    public JapaneseDate plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    public ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        if (isSupported(temporalField)) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            if (i == 1) {
                return ValueRange.of(1, (long) lengthOfMonth());
            }
            if (i == 2) {
                return ValueRange.of(1, (long) lengthOfYear());
            }
            if (i != 3) {
                return getChronology().range(chronoField);
            }
            int year = this.era.getSince().getYear();
            JapaneseEra next = this.era.next();
            return next != null ? ValueRange.of(1, (long) ((next.getSince().getYear() - year) + 1)) : ValueRange.of(1, (long) (999999999 - year));
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public JapaneseDate with(TemporalAdjuster temporalAdjuster) {
        return (JapaneseDate) super.with(temporalAdjuster);
    }

    public JapaneseDate with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return (JapaneseDate) super.with(temporalField, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        if (getLong(chronoField) == j) {
            return this;
        }
        int[] iArr = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField;
        int i = iArr[chronoField.ordinal()];
        if (i == 3 || i == 8 || i == 9) {
            int checkValidIntValue = getChronology().range(chronoField).checkValidIntValue(j, chronoField);
            int i2 = iArr[chronoField.ordinal()];
            if (i2 == 3) {
                return withYear(checkValidIntValue);
            }
            if (i2 == 8) {
                return withYear(JapaneseEra.of(checkValidIntValue), this.yearOfEra);
            }
            if (i2 == 9) {
                return with(this.isoDate.withYear(checkValidIntValue));
            }
        }
        return with(this.isoDate.with(temporalField, j));
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }
}
