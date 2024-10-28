package j$.time.chrono;

import io.ktor.util.date.GMTDateParser;
import j$.time.DateTimeException;
import j$.time.Duration$DurationUnits$$ExternalSyntheticBackport0;
import j$.time.chrono.Chronology;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

final class ChronoPeriodImpl implements TemporalAmount, Serializable {
    private static final List SUPPORTED_UNITS = Duration$DurationUnits$$ExternalSyntheticBackport0.m(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    private static final long serialVersionUID = 57387258289L;
    private final Chronology chrono;
    final int days;
    final int months;
    final int years;

    ChronoPeriodImpl(Chronology chronology, int i, int i2, int i3) {
        Objects.requireNonNull(chronology, "chrono");
        this.chrono = chronology;
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    private long monthRange() {
        ValueRange range = this.chrono.range(ChronoField.MONTH_OF_YEAR);
        if (!range.isFixed() || !range.isIntValue()) {
            return -1;
        }
        return (range.getMaximum() - range.getMinimum()) + 1;
    }

    static ChronoPeriodImpl readExternal(DataInput dataInput) {
        return new ChronoPeriodImpl(Chronology.CC.of(dataInput.readUTF()), dataInput.readInt(), dataInput.readInt(), dataInput.readInt());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private void validateChrono(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null && !this.chrono.equals(chronology)) {
            String id = this.chrono.getId();
            String id2 = chronology.getId();
            throw new DateTimeException("Chronology mismatch, expected: " + id + ", actual: " + id2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public j$.time.temporal.Temporal addTo(j$.time.temporal.Temporal r6) {
        /*
            r5 = this;
            r5.validateChrono(r6)
            int r0 = r5.months
            if (r0 != 0) goto L_0x0013
            int r0 = r5.years
            if (r0 == 0) goto L_0x003e
            long r0 = (long) r0
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.YEARS
        L_0x000e:
            j$.time.temporal.Temporal r6 = r6.plus(r0, r2)
            goto L_0x003e
        L_0x0013:
            long r0 = r5.monthRange()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x002d
            int r2 = r5.years
            long r2 = (long) r2
            long r2 = r2 * r0
            int r0 = r5.months
            long r0 = (long) r0
            long r2 = r2 + r0
            j$.time.temporal.ChronoUnit r0 = j$.time.temporal.ChronoUnit.MONTHS
            j$.time.temporal.Temporal r6 = r6.plus(r2, r0)
            goto L_0x003e
        L_0x002d:
            int r0 = r5.years
            if (r0 == 0) goto L_0x0038
            long r0 = (long) r0
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.YEARS
            j$.time.temporal.Temporal r6 = r6.plus(r0, r2)
        L_0x0038:
            int r0 = r5.months
            long r0 = (long) r0
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.MONTHS
            goto L_0x000e
        L_0x003e:
            int r0 = r5.days
            if (r0 == 0) goto L_0x0049
            long r0 = (long) r0
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.DAYS
            j$.time.temporal.Temporal r6 = r6.plus(r0, r2)
        L_0x0049:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ChronoPeriodImpl.addTo(j$.time.temporal.Temporal):j$.time.temporal.Temporal");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoPeriodImpl)) {
            return false;
        }
        ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) obj;
        return this.years == chronoPeriodImpl.years && this.months == chronoPeriodImpl.months && this.days == chronoPeriodImpl.days && this.chrono.equals(chronoPeriodImpl.chrono);
    }

    public Chronology getChronology() {
        return this.chrono;
    }

    public int hashCode() {
        return ((this.years + Integer.rotateLeft(this.months, 8)) + Integer.rotateLeft(this.days, 16)) ^ this.chrono.hashCode();
    }

    public boolean isZero() {
        return this.years == 0 && this.months == 0 && this.days == 0;
    }

    public String toString() {
        if (isZero()) {
            String chronology = getChronology().toString();
            return chronology + " P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getChronology().toString());
        sb.append(' ');
        sb.append('P');
        int i = this.years;
        if (i != 0) {
            sb.append(i);
            sb.append(GMTDateParser.YEAR);
        }
        int i2 = this.months;
        if (i2 != 0) {
            sb.append(i2);
            sb.append(GMTDateParser.MONTH);
        }
        int i3 = this.days;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('D');
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeUTF(this.chrono.getId());
        dataOutput.writeInt(this.years);
        dataOutput.writeInt(this.months);
        dataOutput.writeInt(this.days);
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        return new Ser((byte) 9, this);
    }
}
