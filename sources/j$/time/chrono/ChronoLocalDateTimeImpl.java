package j$.time.chrono;

import io.ktor.server.plugins.cors.CORSConfig;
import io.netty.handler.codec.rtsp.RtspHeaders;
import j$.time.Clock$TickClock$$ExternalSyntheticBackport0;
import j$.time.DesugarLocalDate$$ExternalSyntheticBackport0;
import j$.time.Instant;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import org.videolan.vlc.gui.dialogs.PickTimeFragment;

final class ChronoLocalDateTimeImpl implements ChronoLocalDateTime, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    private final transient ChronoLocalDate date;
    private final transient LocalTime time;

    /* renamed from: j$.time.chrono.ChronoLocalDateTimeImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
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
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.NANOS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x001d }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.MICROS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0028 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.MILLIS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0033 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.SECONDS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x003e }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.MINUTES     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0049 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.HOURS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$java$time$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0054 }
                j$.time.temporal.ChronoUnit r1 = j$.time.temporal.ChronoUnit.HALF_DAYS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.<clinit>():void");
        }
    }

    private ChronoLocalDateTimeImpl(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "date");
        Objects.requireNonNull(localTime, RtspHeaders.Values.TIME);
        this.date = chronoLocalDate;
        this.time = localTime;
    }

    static ChronoLocalDateTimeImpl ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDateTimeImpl chronoLocalDateTimeImpl = (ChronoLocalDateTimeImpl) temporal;
        if (chronology.equals(chronoLocalDateTimeImpl.getChronology())) {
            return chronoLocalDateTimeImpl;
        }
        String id = chronology.getId();
        String id2 = chronoLocalDateTimeImpl.getChronology().getId();
        throw new ClassCastException("Chronology mismatch, required: " + id + ", actual: " + id2);
    }

    static ChronoLocalDateTimeImpl of(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        return new ChronoLocalDateTimeImpl(chronoLocalDate, localTime);
    }

    private ChronoLocalDateTimeImpl plusDays(long j) {
        return with((Temporal) this.date.plus(j, ChronoUnit.DAYS), this.time);
    }

    private ChronoLocalDateTimeImpl plusHours(long j) {
        return plusWithOverflow(this.date, j, 0, 0, 0);
    }

    private ChronoLocalDateTimeImpl plusMinutes(long j) {
        return plusWithOverflow(this.date, 0, j, 0, 0);
    }

    private ChronoLocalDateTimeImpl plusNanos(long j) {
        return plusWithOverflow(this.date, 0, 0, 0, j);
    }

    private ChronoLocalDateTimeImpl plusWithOverflow(ChronoLocalDate chronoLocalDate, long j, long j2, long j3, long j4) {
        LocalTime ofNanoOfDay;
        ChronoLocalDate chronoLocalDate2 = chronoLocalDate;
        if ((j | j2 | j3 | j4) == 0) {
            ofNanoOfDay = this.time;
        } else {
            long j5 = (j4 / 86400000000000L) + (j3 / CORSConfig.CORS_DEFAULT_MAX_AGE) + (j2 / 1440) + (j / 24);
            long j6 = (j4 % 86400000000000L) + ((j3 % CORSConfig.CORS_DEFAULT_MAX_AGE) * 1000000000) + ((j2 % 1440) * 60000000000L) + ((j % 24) * 3600000000000L);
            long nanoOfDay = this.time.toNanoOfDay();
            long j7 = j6 + nanoOfDay;
            long m = j5 + DesugarLocalDate$$ExternalSyntheticBackport0.m(j7, 86400000000000L);
            long m2 = Clock$TickClock$$ExternalSyntheticBackport0.m(j7, 86400000000000L);
            ofNanoOfDay = m2 == nanoOfDay ? this.time : LocalTime.ofNanoOfDay(m2);
            chronoLocalDate2 = chronoLocalDate2.plus(m, ChronoUnit.DAYS);
        }
        return with((Temporal) chronoLocalDate2, ofNanoOfDay);
    }

    static ChronoLocalDateTime readExternal(ObjectInput objectInput) {
        return ((ChronoLocalDate) objectInput.readObject()).atTime((LocalTime) objectInput.readObject());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private ChronoLocalDateTimeImpl with(Temporal temporal, LocalTime localTime) {
        ChronoLocalDate chronoLocalDate = this.date;
        return (chronoLocalDate == temporal && this.time == localTime) ? this : new ChronoLocalDateTimeImpl(ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), temporal), localTime);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    public /* synthetic */ Temporal adjustInto(Temporal temporal) {
        return ChronoLocalDateTime.CC.$default$adjustInto(this, temporal);
    }

    public ChronoZonedDateTime atZone(ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.ofBest(this, zoneId, (ZoneOffset) null);
    }

    public /* synthetic */ int compareTo(ChronoLocalDateTime chronoLocalDateTime) {
        return ChronoLocalDateTime.CC.$default$compareTo((ChronoLocalDateTime) this, chronoLocalDateTime);
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((ChronoLocalDateTime) obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDateTime) && compareTo((ChronoLocalDateTime) obj) == 0;
    }

    public int get(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? ((ChronoField) temporalField).isTimeBased() ? this.time.get(temporalField) : this.date.get(temporalField) : range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    public /* synthetic */ Chronology getChronology() {
        return ChronoLocalDateTime.CC.$default$getChronology(this);
    }

    public long getLong(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? ((ChronoField) temporalField).isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField) : temporalField.getFrom(this);
    }

    public int hashCode() {
        return toLocalDate().hashCode() ^ toLocalTime().hashCode();
    }

    public boolean isSupported(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField != null && temporalField.isSupportedBy(this);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        return chronoField.isDateBased() || chronoField.isTimeBased();
    }

    public ChronoLocalDateTimeImpl plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return ensureValid(this.date.getChronology(), temporalUnit.addTo(this, j));
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusNanos(j);
            case 2:
                return plusDays(j / 86400000000L).plusNanos((j % 86400000000L) * 1000);
            case 3:
                return plusDays(j / 86400000).plusNanos((j % 86400000) * PickTimeFragment.SECONDS_IN_MICROS);
            case 4:
                return plusSeconds(j);
            case 5:
                return plusMinutes(j);
            case 6:
                return plusHours(j);
            case 7:
                return plusDays(j / 256).plusHours((j % 256) * 12);
            default:
                return with((Temporal) this.date.plus(j, temporalUnit), this.time);
        }
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDateTimeImpl plusSeconds(long j) {
        return plusWithOverflow(this.date, 0, 0, j, 0);
    }

    public /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return ChronoLocalDateTime.CC.$default$query(this, temporalQuery);
    }

    public ValueRange range(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? ((ChronoField) temporalField).isTimeBased() ? this.time.range(temporalField) : this.date.range(temporalField) : temporalField.rangeRefinedBy(this);
    }

    public /* synthetic */ long toEpochSecond(ZoneOffset zoneOffset) {
        return ChronoLocalDateTime.CC.$default$toEpochSecond(this, zoneOffset);
    }

    public /* synthetic */ Instant toInstant(ZoneOffset zoneOffset) {
        return ChronoLocalDateTime.CC.$default$toInstant(this, zoneOffset);
    }

    public ChronoLocalDate toLocalDate() {
        return this.date;
    }

    public LocalTime toLocalTime() {
        return this.time;
    }

    public String toString() {
        String chronoLocalDate = toLocalDate().toString();
        String localTime = toLocalTime().toString();
        return chronoLocalDate + "T" + localTime;
    }

    public ChronoLocalDateTimeImpl with(TemporalAdjuster temporalAdjuster) {
        return temporalAdjuster instanceof ChronoLocalDate ? with((Temporal) (ChronoLocalDate) temporalAdjuster, this.time) : temporalAdjuster instanceof LocalTime ? with((Temporal) this.date, (LocalTime) temporalAdjuster) : temporalAdjuster instanceof ChronoLocalDateTimeImpl ? ensureValid(this.date.getChronology(), (ChronoLocalDateTimeImpl) temporalAdjuster) : ensureValid(this.date.getChronology(), (ChronoLocalDateTimeImpl) temporalAdjuster.adjustInto(this));
    }

    public ChronoLocalDateTimeImpl with(TemporalField temporalField, long j) {
        return temporalField instanceof ChronoField ? ((ChronoField) temporalField).isTimeBased() ? with((Temporal) this.date, this.time.with(temporalField, j)) : with((Temporal) this.date.with(temporalField, j), this.time) : ensureValid(this.date.getChronology(), temporalField.adjustInto(this, j));
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.date);
        objectOutput.writeObject(this.time);
    }
}
