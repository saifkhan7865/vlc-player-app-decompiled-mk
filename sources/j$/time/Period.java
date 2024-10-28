package j$.time;

import com.google.common.base.Ascii;
import io.ktor.util.date.GMTDateParser;
import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalQueries;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

public final class Period implements TemporalAmount, Serializable {
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
    private static final List SUPPORTED_UNITS = Duration$DurationUnits$$ExternalSyntheticBackport0.m(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    public static final Period ZERO = new Period(0, 0, 0);
    private static final long serialVersionUID = -3587258372562876L;
    private final int days;
    private final int months;
    private final int years;

    private Period(int i, int i2, int i3) {
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    private static Period create(int i, int i2, int i3) {
        return ((i | i2) | i3) == 0 ? ZERO : new Period(i, i2, i3);
    }

    public static Period of(int i, int i2, int i3) {
        return create(i, i2, i3);
    }

    public static Period ofDays(int i) {
        return create(0, 0, i);
    }

    public static Period ofMonths(int i) {
        return create(0, i, 0);
    }

    public static Period ofWeeks(int i) {
        return create(0, 0, Period$$ExternalSyntheticBackport0.m(i, 7));
    }

    public static Period ofYears(int i) {
        return create(i, 0, 0);
    }

    static Period readExternal(DataInput dataInput) {
        return of(dataInput.readInt(), dataInput.readInt(), dataInput.readInt());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private void validateChrono(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null && !IsoChronology.INSTANCE.equals(chronology)) {
            String id = chronology.getId();
            throw new DateTimeException("Chronology mismatch, expected: ISO, actual: " + id);
        }
    }

    private Object writeReplace() {
        return new Ser(Ascii.SO, this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public j$.time.temporal.Temporal addTo(j$.time.temporal.Temporal r6) {
        /*
            r5 = this;
            r5.validateChrono(r6)
            int r0 = r5.months
            if (r0 != 0) goto L_0x0013
            int r0 = r5.years
            if (r0 == 0) goto L_0x0020
            long r0 = (long) r0
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.YEARS
        L_0x000e:
            j$.time.temporal.Temporal r6 = r6.plus(r0, r2)
            goto L_0x0020
        L_0x0013:
            long r0 = r5.toTotalMonths()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0020
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.MONTHS
            goto L_0x000e
        L_0x0020:
            int r0 = r5.days
            if (r0 == 0) goto L_0x002b
            long r0 = (long) r0
            j$.time.temporal.ChronoUnit r2 = j$.time.temporal.ChronoUnit.DAYS
            j$.time.temporal.Temporal r6 = r6.plus(r0, r2)
        L_0x002b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.Period.addTo(j$.time.temporal.Temporal):j$.time.temporal.Temporal");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        return this.years == period.years && this.months == period.months && this.days == period.days;
    }

    public int getDays() {
        return this.days;
    }

    public int hashCode() {
        return this.years + Integer.rotateLeft(this.months, 8) + Integer.rotateLeft(this.days, 16);
    }

    public boolean isZero() {
        return this == ZERO;
    }

    public String toString() {
        if (this == ZERO) {
            return "P0D";
        }
        StringBuilder sb = new StringBuilder();
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

    public long toTotalMonths() {
        return (((long) this.years) * 12) + ((long) this.months);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(this.years);
        dataOutput.writeInt(this.months);
        dataOutput.writeInt(this.days);
    }
}
