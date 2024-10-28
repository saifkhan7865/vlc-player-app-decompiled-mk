package j$.time.chrono;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoZonedDateTime;
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

final class ChronoZonedDateTimeImpl implements ChronoZonedDateTime, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final transient ChronoLocalDateTimeImpl dateTime;
    private final transient ZoneOffset offset;
    private final transient ZoneId zone;

    /* renamed from: j$.time.chrono.ChronoZonedDateTimeImpl$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ChronoZonedDateTimeImpl.AnonymousClass1.<clinit>():void");
        }
    }

    private ChronoZonedDateTimeImpl(ChronoLocalDateTimeImpl chronoLocalDateTimeImpl, ZoneOffset zoneOffset, ZoneId zoneId) {
        this.dateTime = (ChronoLocalDateTimeImpl) Objects.requireNonNull(chronoLocalDateTimeImpl, "dateTime");
        this.offset = (ZoneOffset) Objects.requireNonNull(zoneOffset, TypedValues.CycleType.S_WAVE_OFFSET);
        this.zone = (ZoneId) Objects.requireNonNull(zoneId, "zone");
    }

    private ChronoZonedDateTimeImpl create(Instant instant, ZoneId zoneId) {
        return ofInstant(getChronology(), instant, zoneId);
    }

    static ChronoZonedDateTimeImpl ensureValid(Chronology chronology, Temporal temporal) {
        ChronoZonedDateTimeImpl chronoZonedDateTimeImpl = (ChronoZonedDateTimeImpl) temporal;
        if (chronology.equals(chronoZonedDateTimeImpl.getChronology())) {
            return chronoZonedDateTimeImpl;
        }
        String id = chronology.getId();
        String id2 = chronoZonedDateTimeImpl.getChronology().getId();
        throw new ClassCastException("Chronology mismatch, required: " + id + ", actual: " + id2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0053, code lost:
        if (r2.contains(r8) != false) goto L_0x0055;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static j$.time.chrono.ChronoZonedDateTime ofBest(j$.time.chrono.ChronoLocalDateTimeImpl r6, j$.time.ZoneId r7, j$.time.ZoneOffset r8) {
        /*
            java.lang.String r0 = "localDateTime"
            j$.util.Objects.requireNonNull(r6, r0)
            java.lang.String r0 = "zone"
            j$.util.Objects.requireNonNull(r7, r0)
            boolean r0 = r7 instanceof j$.time.ZoneOffset
            if (r0 == 0) goto L_0x0017
            j$.time.chrono.ChronoZonedDateTimeImpl r8 = new j$.time.chrono.ChronoZonedDateTimeImpl
            r0 = r7
            j$.time.ZoneOffset r0 = (j$.time.ZoneOffset) r0
            r8.<init>(r6, r0, r7)
            return r8
        L_0x0017:
            j$.time.zone.ZoneRules r0 = r7.getRules()
            j$.time.LocalDateTime r1 = j$.time.LocalDateTime.from(r6)
            java.util.List r2 = r0.getValidOffsets(r1)
            int r3 = r2.size()
            r4 = 1
            r5 = 0
            if (r3 != r4) goto L_0x0032
        L_0x002b:
            java.lang.Object r8 = r2.get(r5)
            j$.time.ZoneOffset r8 = (j$.time.ZoneOffset) r8
            goto L_0x0055
        L_0x0032:
            int r3 = r2.size()
            if (r3 != 0) goto L_0x004d
            j$.time.zone.ZoneOffsetTransition r8 = r0.getTransition(r1)
            j$.time.Duration r0 = r8.getDuration()
            long r0 = r0.getSeconds()
            j$.time.chrono.ChronoLocalDateTimeImpl r6 = r6.plusSeconds(r0)
            j$.time.ZoneOffset r8 = r8.getOffsetAfter()
            goto L_0x0055
        L_0x004d:
            if (r8 == 0) goto L_0x002b
            boolean r0 = r2.contains(r8)
            if (r0 == 0) goto L_0x002b
        L_0x0055:
            java.lang.String r0 = "offset"
            j$.util.Objects.requireNonNull(r8, r0)
            j$.time.chrono.ChronoZonedDateTimeImpl r0 = new j$.time.chrono.ChronoZonedDateTimeImpl
            r0.<init>(r6, r8, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ChronoZonedDateTimeImpl.ofBest(j$.time.chrono.ChronoLocalDateTimeImpl, j$.time.ZoneId, j$.time.ZoneOffset):j$.time.chrono.ChronoZonedDateTime");
    }

    static ChronoZonedDateTimeImpl ofInstant(Chronology chronology, Instant instant, ZoneId zoneId) {
        ZoneOffset offset2 = zoneId.getRules().getOffset(instant);
        Objects.requireNonNull(offset2, TypedValues.CycleType.S_WAVE_OFFSET);
        return new ChronoZonedDateTimeImpl((ChronoLocalDateTimeImpl) chronology.localDateTime(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset2)), offset2, zoneId);
    }

    static ChronoZonedDateTime readExternal(ObjectInput objectInput) {
        return ((ChronoLocalDateTime) objectInput.readObject()).atZone((ZoneOffset) objectInput.readObject()).withZoneSameLocal((ZoneId) objectInput.readObject());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    public /* synthetic */ int compareTo(ChronoZonedDateTime chronoZonedDateTime) {
        return ChronoZonedDateTime.CC.$default$compareTo((ChronoZonedDateTime) this, chronoZonedDateTime);
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((ChronoZonedDateTime) obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoZonedDateTime) && compareTo((ChronoZonedDateTime) obj) == 0;
    }

    public /* synthetic */ int get(TemporalField temporalField) {
        return ChronoZonedDateTime.CC.$default$get(this, temporalField);
    }

    public /* synthetic */ Chronology getChronology() {
        return ChronoZonedDateTime.CC.$default$getChronology(this);
    }

    public /* synthetic */ long getLong(TemporalField temporalField) {
        return ChronoZonedDateTime.CC.$default$getLong(this, temporalField);
    }

    public ZoneOffset getOffset() {
        return this.offset;
    }

    public ZoneId getZone() {
        return this.zone;
    }

    public int hashCode() {
        return (toLocalDateTime().hashCode() ^ getOffset().hashCode()) ^ Integer.rotateLeft(getZone().hashCode(), 3);
    }

    public boolean isSupported(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.isSupportedBy(this));
    }

    public ChronoZonedDateTime plus(long j, TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? with((TemporalAdjuster) this.dateTime.plus(j, temporalUnit)) : ensureValid(getChronology(), temporalUnit.addTo(this, j));
    }

    public /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return ChronoZonedDateTime.CC.$default$query(this, temporalQuery);
    }

    public /* synthetic */ ValueRange range(TemporalField temporalField) {
        return ChronoZonedDateTime.CC.$default$range(this, temporalField);
    }

    public /* synthetic */ long toEpochSecond() {
        return ChronoZonedDateTime.CC.$default$toEpochSecond(this);
    }

    public /* synthetic */ ChronoLocalDate toLocalDate() {
        return ChronoZonedDateTime.CC.$default$toLocalDate(this);
    }

    public ChronoLocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    public /* synthetic */ LocalTime toLocalTime() {
        return ChronoZonedDateTime.CC.$default$toLocalTime(this);
    }

    public String toString() {
        String str = toLocalDateTime().toString() + getOffset().toString();
        if (getOffset() == getZone()) {
            return str;
        }
        return str + "[" + getZone().toString() + "]";
    }

    public ChronoZonedDateTime with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return ensureValid(getChronology(), temporalField.adjustInto(this, j));
        }
        ChronoField chronoField = (ChronoField) temporalField;
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        if (i == 1) {
            return plus(j - toEpochSecond(), (TemporalUnit) ChronoUnit.SECONDS);
        }
        if (i != 2) {
            return ofBest(this.dateTime.with(temporalField, j), this.zone, this.offset);
        }
        return create(this.dateTime.toInstant(ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j))), this.zone);
    }

    public ChronoZonedDateTime withZoneSameLocal(ZoneId zoneId) {
        return ofBest(this.dateTime, zoneId, this.offset);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.dateTime);
        objectOutput.writeObject(this.offset);
        objectOutput.writeObject(this.zone);
    }
}
