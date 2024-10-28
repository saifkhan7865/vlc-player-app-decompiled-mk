package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.netty.handler.codec.rtsp.RtspHeaders;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

public final class OffsetTime implements Temporal, TemporalAdjuster, Comparable, Serializable {
    public static final OffsetTime MAX = LocalTime.MAX.atOffset(ZoneOffset.MIN);
    public static final OffsetTime MIN = LocalTime.MIN.atOffset(ZoneOffset.MAX);
    private static final long serialVersionUID = 7264499704384272492L;
    private final ZoneOffset offset;
    private final LocalTime time;

    private OffsetTime(LocalTime localTime, ZoneOffset zoneOffset) {
        this.time = (LocalTime) Objects.requireNonNull(localTime, RtspHeaders.Values.TIME);
        this.offset = (ZoneOffset) Objects.requireNonNull(zoneOffset, TypedValues.CycleType.S_WAVE_OFFSET);
    }

    public static OffsetTime of(LocalTime localTime, ZoneOffset zoneOffset) {
        return new OffsetTime(localTime, zoneOffset);
    }

    static OffsetTime readExternal(ObjectInput objectInput) {
        return of(LocalTime.readExternal(objectInput), ZoneOffset.readExternal(objectInput));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private long toEpochNano() {
        return this.time.toNanoOfDay() - (((long) this.offset.getTotalSeconds()) * 1000000000);
    }

    private OffsetTime with(LocalTime localTime, ZoneOffset zoneOffset) {
        return (this.time != localTime || !this.offset.equals(zoneOffset)) ? new OffsetTime(localTime, zoneOffset) : this;
    }

    private Object writeReplace() {
        return new Ser((byte) 9, this);
    }

    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.NANO_OF_DAY, this.time.toNanoOfDay()).with(ChronoField.OFFSET_SECONDS, (long) this.offset.getTotalSeconds());
    }

    public int compareTo(OffsetTime offsetTime) {
        if (this.offset.equals(offsetTime.offset)) {
            return this.time.compareTo(offsetTime.time);
        }
        int i = (toEpochNano() > offsetTime.toEpochNano() ? 1 : (toEpochNano() == offsetTime.toEpochNano() ? 0 : -1));
        return i == 0 ? this.time.compareTo(offsetTime.time) : i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetTime)) {
            return false;
        }
        OffsetTime offsetTime = (OffsetTime) obj;
        return this.time.equals(offsetTime.time) && this.offset.equals(offsetTime.offset);
    }

    public int get(TemporalField temporalField) {
        return TemporalAccessor.CC.$default$get(this, temporalField);
    }

    public long getLong(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.OFFSET_SECONDS ? (long) this.offset.getTotalSeconds() : this.time.getLong(temporalField) : temporalField.getFrom(this);
    }

    public int hashCode() {
        return this.time.hashCode() ^ this.offset.hashCode();
    }

    public boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField.isTimeBased() || temporalField == ChronoField.OFFSET_SECONDS : temporalField != null && temporalField.isSupportedBy(this);
    }

    public OffsetTime minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, temporalUnit).plus(1, temporalUnit) : plus(-j, temporalUnit);
    }

    public OffsetTime plus(long j, TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? with(this.time.plus(j, temporalUnit), this.offset) : (OffsetTime) temporalUnit.addTo(this, j);
    }

    public Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.offset() || temporalQuery == TemporalQueries.zone()) {
            return this.offset;
        }
        boolean z = false;
        boolean z2 = temporalQuery == TemporalQueries.zoneId();
        if (temporalQuery == TemporalQueries.chronology()) {
            z = true;
        }
        if ((z2 || z) || temporalQuery == TemporalQueries.localDate()) {
            return null;
        }
        return temporalQuery == TemporalQueries.localTime() ? this.time : temporalQuery == TemporalQueries.precision() ? ChronoUnit.NANOS : temporalQuery.queryFrom(this);
    }

    public ValueRange range(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.OFFSET_SECONDS ? temporalField.range() : this.time.range(temporalField) : temporalField.rangeRefinedBy(this);
    }

    public String toString() {
        String localTime = this.time.toString();
        String zoneOffset = this.offset.toString();
        return localTime + zoneOffset;
    }

    public OffsetTime with(TemporalAdjuster temporalAdjuster) {
        return temporalAdjuster instanceof LocalTime ? with((LocalTime) temporalAdjuster, this.offset) : temporalAdjuster instanceof ZoneOffset ? with(this.time, (ZoneOffset) temporalAdjuster) : temporalAdjuster instanceof OffsetTime ? (OffsetTime) temporalAdjuster : (OffsetTime) temporalAdjuster.adjustInto(this);
    }

    public OffsetTime with(TemporalField temporalField, long j) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.OFFSET_SECONDS ? with(this.time, ZoneOffset.ofTotalSeconds(((ChronoField) temporalField).checkValidIntValue(j))) : with(this.time.with(temporalField, j), this.offset) : (OffsetTime) temporalField.adjustInto(this, j);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) {
        this.time.writeExternal(objectOutput);
        this.offset.writeExternal(objectOutput);
    }
}
