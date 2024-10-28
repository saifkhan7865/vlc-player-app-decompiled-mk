package j$.time.chrono;

import j$.time.Clock$OffsetClock$$ExternalSyntheticBackport0;
import j$.time.Duration$$ExternalSyntheticBackport1;
import j$.time.LocalTime;
import j$.time.chrono.ChronoLocalDate;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import java.io.Serializable;
import org.fusesource.jansi.AnsiRenderer;

abstract class ChronoLocalDateImpl implements ChronoLocalDate, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    /* renamed from: j$.time.chrono.ChronoLocalDateImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                j$.time.temporal.ChronoUnit[] r0 = j$.time.temporal.ChronoUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$time$temporal$ChronoUnit = r0
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.DAYS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x001d }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.WEEKS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0028 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.MONTHS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0033 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.YEARS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x003e }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.DECADES     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0049 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.CENTURIES     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0054 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.MILLENNIA     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0060 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.ERAS     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ChronoLocalDateImpl.AnonymousClass1.<clinit>():void");
        }
    }

    ChronoLocalDateImpl() {
    }

    static ChronoLocalDate ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDate chronoLocalDate = (ChronoLocalDate) temporal;
        if (chronology.equals(chronoLocalDate.getChronology())) {
            return chronoLocalDate;
        }
        String id = chronology.getId();
        String id2 = chronoLocalDate.getChronology().getId();
        throw new ClassCastException("Chronology mismatch, expected: " + id + ", actual: " + id2);
    }

    public /* synthetic */ Temporal adjustInto(Temporal temporal) {
        return ChronoLocalDate.CC.$default$adjustInto(this, temporal);
    }

    public /* synthetic */ ChronoLocalDateTime atTime(LocalTime localTime) {
        return ChronoLocalDate.CC.$default$atTime(this, localTime);
    }

    public /* synthetic */ int compareTo(ChronoLocalDate chronoLocalDate) {
        return ChronoLocalDate.CC.$default$compareTo((ChronoLocalDate) this, chronoLocalDate);
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((ChronoLocalDate) obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDate) && compareTo((ChronoLocalDate) obj) == 0;
    }

    public /* synthetic */ int get(TemporalField temporalField) {
        return TemporalAccessor.CC.$default$get(this, temporalField);
    }

    public /* synthetic */ Era getEra() {
        return ChronoLocalDate.CC.$default$getEra(this);
    }

    public int hashCode() {
        long epochDay = toEpochDay();
        return getChronology().hashCode() ^ ((int) (epochDay ^ (epochDay >>> 32)));
    }

    public /* synthetic */ boolean isLeapYear() {
        return ChronoLocalDate.CC.$default$isLeapYear(this);
    }

    public /* synthetic */ boolean isSupported(TemporalField temporalField) {
        return ChronoLocalDate.CC.$default$isSupported(this, temporalField);
    }

    public /* synthetic */ int lengthOfYear() {
        return ChronoLocalDate.CC.$default$lengthOfYear(this);
    }

    public ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return ChronoLocalDate.CC.$default$minus(this, j, temporalUnit);
    }

    public ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return ChronoLocalDate.CC.$default$plus(this, j, temporalUnit);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusDays(j);
            case 2:
                return plusDays(Duration$$ExternalSyntheticBackport1.m(j, (long) 7));
            case 3:
                return plusMonths(j);
            case 4:
                return plusYears(j);
            case 5:
                return plusYears(Duration$$ExternalSyntheticBackport1.m(j, (long) 10));
            case 6:
                return plusYears(Duration$$ExternalSyntheticBackport1.m(j, (long) 100));
            case 7:
                return plusYears(Duration$$ExternalSyntheticBackport1.m(j, (long) 1000));
            case 8:
                ChronoField chronoField = ChronoField.ERA;
                return with((TemporalField) chronoField, Clock$OffsetClock$$ExternalSyntheticBackport0.m(getLong(chronoField), j));
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
    }

    public ChronoLocalDate plus(TemporalAmount temporalAmount) {
        return ChronoLocalDate.CC.$default$plus(this, temporalAmount);
    }

    /* access modifiers changed from: package-private */
    public abstract ChronoLocalDate plusDays(long j);

    /* access modifiers changed from: package-private */
    public abstract ChronoLocalDate plusMonths(long j);

    /* access modifiers changed from: package-private */
    public abstract ChronoLocalDate plusYears(long j);

    public /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return ChronoLocalDate.CC.$default$query(this, temporalQuery);
    }

    public /* synthetic */ ValueRange range(TemporalField temporalField) {
        return TemporalAccessor.CC.$default$range(this, temporalField);
    }

    public /* synthetic */ long toEpochDay() {
        return ChronoLocalDate.CC.$default$toEpochDay(this);
    }

    public String toString() {
        long j = getLong(ChronoField.YEAR_OF_ERA);
        long j2 = getLong(ChronoField.MONTH_OF_YEAR);
        long j3 = getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(getChronology().toString());
        sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
        sb.append(getEra());
        sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
        sb.append(j);
        String str = "-";
        sb.append(j2 < 10 ? "-0" : str);
        sb.append(j2);
        if (j3 < 10) {
            str = "-0";
        }
        sb.append(str);
        sb.append(j3);
        return sb.toString();
    }

    public ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return ChronoLocalDate.CC.$default$with(this, temporalAdjuster);
    }

    public ChronoLocalDate with(TemporalField temporalField, long j) {
        return ChronoLocalDate.CC.$default$with(this, temporalField, j);
    }
}
