package j$.time;

import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.format.DateTimeFormatter;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.videolan.resources.Constants;

public final class MonthDay implements TemporalAccessor, TemporalAdjuster, Comparable, Serializable {
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendLiteral("--").appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter();
    private static final long serialVersionUID = -939150713474957432L;
    private final int day;
    private final int month;

    /* renamed from: j$.time.MonthDay$1  reason: invalid class name */
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
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.DAY_OF_MONTH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x001d }
                j$.time.temporal.ChronoField r1 = j$.time.temporal.ChronoField.MONTH_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.time.MonthDay.AnonymousClass1.<clinit>():void");
        }
    }

    private MonthDay(int i, int i2) {
        this.month = i;
        this.day = i2;
    }

    public static MonthDay of(int i, int i2) {
        return of(Month.of(i), i2);
    }

    public static MonthDay of(Month month2, int i) {
        Objects.requireNonNull(month2, "month");
        ChronoField.DAY_OF_MONTH.checkValidValue((long) i);
        if (i <= month2.maxLength()) {
            return new MonthDay(month2.getValue(), i);
        }
        String name = month2.name();
        throw new DateTimeException("Illegal value for DayOfMonth field, value " + i + " is not valid for month " + name);
    }

    static MonthDay readExternal(DataInput dataInput) {
        return of((int) dataInput.readByte(), (int) dataInput.readByte());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 13, this);
    }

    public Temporal adjustInto(Temporal temporal) {
        if (Chronology.CC.from(temporal).equals(IsoChronology.INSTANCE)) {
            Temporal with = temporal.with(ChronoField.MONTH_OF_YEAR, (long) this.month);
            ChronoField chronoField = ChronoField.DAY_OF_MONTH;
            return with.with(chronoField, Math.min(with.range(chronoField).getMaximum(), (long) this.day));
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }

    public int compareTo(MonthDay monthDay) {
        int i = this.month - monthDay.month;
        return i == 0 ? this.day - monthDay.day : i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MonthDay)) {
            return false;
        }
        MonthDay monthDay = (MonthDay) obj;
        return this.month == monthDay.month && this.day == monthDay.day;
    }

    public int get(TemporalField temporalField) {
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    public long getLong(TemporalField temporalField) {
        int i;
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        if (i2 == 1) {
            i = this.day;
        } else if (i2 == 2) {
            i = this.month;
        } else {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return (long) i;
    }

    public Month getMonth() {
        return Month.of(this.month);
    }

    public int hashCode() {
        return (this.month << 6) + this.day;
    }

    public boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.MONTH_OF_YEAR || temporalField == ChronoField.DAY_OF_MONTH : temporalField != null && temporalField.isSupportedBy(this);
    }

    public Object query(TemporalQuery temporalQuery) {
        return temporalQuery == TemporalQueries.chronology() ? IsoChronology.INSTANCE : TemporalAccessor.CC.$default$query(this, temporalQuery);
    }

    public ValueRange range(TemporalField temporalField) {
        return temporalField == ChronoField.MONTH_OF_YEAR ? temporalField.range() : temporalField == ChronoField.DAY_OF_MONTH ? ValueRange.of(1, (long) getMonth().minLength(), (long) getMonth().maxLength()) : TemporalAccessor.CC.$default$range(this, temporalField);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("--");
        sb.append(this.month < 10 ? Constants.GROUP_VIDEOS_FOLDER : "");
        sb.append(this.month);
        sb.append(this.day < 10 ? "-0" : "-");
        sb.append(this.day);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeByte(this.month);
        dataOutput.writeByte(this.day);
    }
}
