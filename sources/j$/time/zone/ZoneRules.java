package j$.time.zone;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.server.plugins.cors.CORSConfig;
import j$.time.Clock;
import j$.time.DesugarLocalDate$$ExternalSyntheticBackport0;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentMap;
import org.videolan.resources.util.HelpersKt;

public final class ZoneRules implements Serializable {
    private static final ZoneOffsetTransitionRule[] EMPTY_LASTRULES = new ZoneOffsetTransitionRule[0];
    private static final LocalDateTime[] EMPTY_LDT_ARRAY = new LocalDateTime[0];
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final ZoneOffsetTransition[] NO_TRANSITIONS = new ZoneOffsetTransition[0];
    private static final long serialVersionUID = 3044319355680032515L;
    private final ZoneOffsetTransitionRule[] lastRules;
    private final transient ConcurrentMap lastRulesCache = new ConcurrentHashMap();
    private final long[] savingsInstantTransitions;
    private final LocalDateTime[] savingsLocalTransitions;
    private final ZoneOffset[] standardOffsets;
    private final long[] standardTransitions;
    private final TimeZone timeZone;
    private final ZoneOffset[] wallOffsets;

    private ZoneRules(ZoneOffset zoneOffset) {
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[1];
        this.standardOffsets = zoneOffsetArr;
        zoneOffsetArr[0] = zoneOffset;
        long[] jArr = EMPTY_LONG_ARRAY;
        this.standardTransitions = jArr;
        this.savingsInstantTransitions = jArr;
        this.savingsLocalTransitions = EMPTY_LDT_ARRAY;
        this.wallOffsets = zoneOffsetArr;
        this.lastRules = EMPTY_LASTRULES;
        this.timeZone = null;
    }

    ZoneRules(TimeZone timeZone2) {
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[1];
        this.standardOffsets = zoneOffsetArr;
        zoneOffsetArr[0] = offsetFromMillis(timeZone2.getRawOffset());
        long[] jArr = EMPTY_LONG_ARRAY;
        this.standardTransitions = jArr;
        this.savingsInstantTransitions = jArr;
        this.savingsLocalTransitions = EMPTY_LDT_ARRAY;
        this.wallOffsets = zoneOffsetArr;
        this.lastRules = EMPTY_LASTRULES;
        this.timeZone = timeZone2;
    }

    private ZoneRules(long[] jArr, ZoneOffset[] zoneOffsetArr, long[] jArr2, ZoneOffset[] zoneOffsetArr2, ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr) {
        LocalDateTime dateTimeBefore;
        this.standardTransitions = jArr;
        this.standardOffsets = zoneOffsetArr;
        this.savingsInstantTransitions = jArr2;
        this.wallOffsets = zoneOffsetArr2;
        this.lastRules = zoneOffsetTransitionRuleArr;
        if (jArr2.length == 0) {
            this.savingsLocalTransitions = EMPTY_LDT_ARRAY;
        } else {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < jArr2.length) {
                int i2 = i + 1;
                ZoneOffsetTransition zoneOffsetTransition = new ZoneOffsetTransition(jArr2[i], zoneOffsetArr2[i], zoneOffsetArr2[i2]);
                if (zoneOffsetTransition.isGap()) {
                    arrayList.add(zoneOffsetTransition.getDateTimeBefore());
                    dateTimeBefore = zoneOffsetTransition.getDateTimeAfter();
                } else {
                    arrayList.add(zoneOffsetTransition.getDateTimeAfter());
                    dateTimeBefore = zoneOffsetTransition.getDateTimeBefore();
                }
                arrayList.add(dateTimeBefore);
                i = i2;
            }
            this.savingsLocalTransitions = (LocalDateTime[]) arrayList.toArray(new LocalDateTime[arrayList.size()]);
        }
        this.timeZone = null;
    }

    private Object findOffsetInfo(LocalDateTime localDateTime, ZoneOffsetTransition zoneOffsetTransition) {
        LocalDateTime dateTimeBefore = zoneOffsetTransition.getDateTimeBefore();
        boolean isGap = zoneOffsetTransition.isGap();
        boolean isBefore = localDateTime.isBefore(dateTimeBefore);
        return isGap ? isBefore ? zoneOffsetTransition.getOffsetBefore() : localDateTime.isBefore(zoneOffsetTransition.getDateTimeAfter()) ? zoneOffsetTransition : zoneOffsetTransition.getOffsetAfter() : !isBefore ? zoneOffsetTransition.getOffsetAfter() : localDateTime.isBefore(zoneOffsetTransition.getDateTimeAfter()) ? zoneOffsetTransition.getOffsetBefore() : zoneOffsetTransition;
    }

    private ZoneOffsetTransition[] findTransitionArray(int i) {
        long j;
        long j2;
        int i2 = i;
        Integer valueOf = Integer.valueOf(i);
        ZoneOffsetTransition[] zoneOffsetTransitionArr = (ZoneOffsetTransition[]) this.lastRulesCache.get(valueOf);
        if (zoneOffsetTransitionArr != null) {
            return zoneOffsetTransitionArr;
        }
        if (this.timeZone == null) {
            ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = this.lastRules;
            ZoneOffsetTransition[] zoneOffsetTransitionArr2 = new ZoneOffsetTransition[zoneOffsetTransitionRuleArr.length];
            for (int i3 = 0; i3 < zoneOffsetTransitionRuleArr.length; i3++) {
                zoneOffsetTransitionArr2[i3] = zoneOffsetTransitionRuleArr[i3].createTransition(i2);
            }
            if (i2 < 2100) {
                this.lastRulesCache.putIfAbsent(valueOf, zoneOffsetTransitionArr2);
            }
            return zoneOffsetTransitionArr2;
        } else if (i2 < 1800) {
            return NO_TRANSITIONS;
        } else {
            long epochSecond = LocalDateTime.of(i2 - 1, 12, 31, 0, 0).toEpochSecond(this.standardOffsets[0]);
            long j3 = 1000;
            int offset = this.timeZone.getOffset(epochSecond * 1000);
            long j4 = 31968000 + epochSecond;
            ZoneOffsetTransition[] zoneOffsetTransitionArr3 = NO_TRANSITIONS;
            while (epochSecond < j4) {
                long j5 = 7776000 + epochSecond;
                long j6 = epochSecond;
                if (offset != this.timeZone.getOffset(j5 * j3)) {
                    epochSecond = j6;
                    while (j5 - epochSecond > 1) {
                        long j7 = j4;
                        long m = DesugarLocalDate$$ExternalSyntheticBackport0.m(j5 + epochSecond, 2);
                        long j8 = j5;
                        if (this.timeZone.getOffset(m * 1000) == offset) {
                            epochSecond = m;
                            j5 = j8;
                        } else {
                            j5 = m;
                        }
                        j3 = 1000;
                        j4 = j7;
                    }
                    j = j4;
                    long j9 = j5;
                    j2 = j3;
                    if (this.timeZone.getOffset(epochSecond * j2) == offset) {
                        epochSecond = j9;
                    }
                    ZoneOffset offsetFromMillis = offsetFromMillis(offset);
                    int offset2 = this.timeZone.getOffset(epochSecond * j2);
                    ZoneOffset offsetFromMillis2 = offsetFromMillis(offset2);
                    if (findYear(epochSecond, offsetFromMillis2) == i2) {
                        ZoneOffsetTransition[] zoneOffsetTransitionArr4 = (ZoneOffsetTransition[]) Arrays.copyOf(zoneOffsetTransitionArr3, zoneOffsetTransitionArr3.length + 1);
                        zoneOffsetTransitionArr4[zoneOffsetTransitionArr4.length - 1] = new ZoneOffsetTransition(epochSecond, offsetFromMillis, offsetFromMillis2);
                        offset = offset2;
                        zoneOffsetTransitionArr3 = zoneOffsetTransitionArr4;
                    } else {
                        offset = offset2;
                    }
                } else {
                    j = j4;
                    j2 = j3;
                    epochSecond = j5;
                }
                j3 = j2;
                j4 = j;
            }
            if (1916 <= i2 && i2 < 2100) {
                this.lastRulesCache.putIfAbsent(valueOf, zoneOffsetTransitionArr3);
            }
            return zoneOffsetTransitionArr3;
        }
    }

    private int findYear(long j, ZoneOffset zoneOffset) {
        return LocalDate.ofEpochDay(DesugarLocalDate$$ExternalSyntheticBackport0.m(j + ((long) zoneOffset.getTotalSeconds()), (long) HelpersKt.LENGTH_DAY)).getYear();
    }

    private Object getOffsetInfo(LocalDateTime localDateTime) {
        Object obj = null;
        int i = 0;
        if (this.timeZone != null) {
            ZoneOffsetTransition[] findTransitionArray = findTransitionArray(localDateTime.getYear());
            if (findTransitionArray.length == 0) {
                return offsetFromMillis(this.timeZone.getOffset(localDateTime.toEpochSecond(this.standardOffsets[0]) * 1000));
            }
            int length = findTransitionArray.length;
            while (i < length) {
                ZoneOffsetTransition zoneOffsetTransition = findTransitionArray[i];
                Object findOffsetInfo = findOffsetInfo(localDateTime, zoneOffsetTransition);
                if ((findOffsetInfo instanceof ZoneOffsetTransition) || findOffsetInfo.equals(zoneOffsetTransition.getOffsetBefore())) {
                    return findOffsetInfo;
                }
                i++;
                obj = findOffsetInfo;
            }
            return obj;
        } else if (this.savingsInstantTransitions.length == 0) {
            return this.standardOffsets[0];
        } else {
            if (this.lastRules.length > 0) {
                LocalDateTime[] localDateTimeArr = this.savingsLocalTransitions;
                if (localDateTime.isAfter(localDateTimeArr[localDateTimeArr.length - 1])) {
                    ZoneOffsetTransition[] findTransitionArray2 = findTransitionArray(localDateTime.getYear());
                    int length2 = findTransitionArray2.length;
                    while (i < length2) {
                        ZoneOffsetTransition zoneOffsetTransition2 = findTransitionArray2[i];
                        Object findOffsetInfo2 = findOffsetInfo(localDateTime, zoneOffsetTransition2);
                        if ((findOffsetInfo2 instanceof ZoneOffsetTransition) || findOffsetInfo2.equals(zoneOffsetTransition2.getOffsetBefore())) {
                            return findOffsetInfo2;
                        }
                        i++;
                        obj = findOffsetInfo2;
                    }
                    return obj;
                }
            }
            int binarySearch = Arrays.binarySearch(this.savingsLocalTransitions, localDateTime);
            if (binarySearch == -1) {
                return this.wallOffsets[0];
            }
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 2;
            } else {
                LocalDateTime[] localDateTimeArr2 = this.savingsLocalTransitions;
                if (binarySearch < localDateTimeArr2.length - 1) {
                    int i2 = binarySearch + 1;
                    if (localDateTimeArr2[binarySearch].equals(localDateTimeArr2[i2])) {
                        binarySearch = i2;
                    }
                }
            }
            if ((binarySearch & 1) != 0) {
                return this.wallOffsets[(binarySearch / 2) + 1];
            }
            LocalDateTime[] localDateTimeArr3 = this.savingsLocalTransitions;
            LocalDateTime localDateTime2 = localDateTimeArr3[binarySearch];
            LocalDateTime localDateTime3 = localDateTimeArr3[binarySearch + 1];
            ZoneOffset[] zoneOffsetArr = this.wallOffsets;
            int i3 = binarySearch / 2;
            ZoneOffset zoneOffset = zoneOffsetArr[i3];
            ZoneOffset zoneOffset2 = zoneOffsetArr[i3 + 1];
            return zoneOffset2.getTotalSeconds() > zoneOffset.getTotalSeconds() ? new ZoneOffsetTransition(localDateTime2, zoneOffset, zoneOffset2) : new ZoneOffsetTransition(localDateTime3, zoneOffset, zoneOffset2);
        }
    }

    public static ZoneRules of(ZoneOffset zoneOffset) {
        Objects.requireNonNull(zoneOffset, TypedValues.CycleType.S_WAVE_OFFSET);
        return new ZoneRules(zoneOffset);
    }

    private static ZoneOffset offsetFromMillis(int i) {
        return ZoneOffset.ofTotalSeconds(i / 1000);
    }

    static ZoneRules readExternal(DataInput dataInput) {
        int readInt = dataInput.readInt();
        long[] jArr = readInt == 0 ? EMPTY_LONG_ARRAY : new long[readInt];
        for (int i = 0; i < readInt; i++) {
            jArr[i] = Ser.readEpochSec(dataInput);
        }
        int i2 = readInt + 1;
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            zoneOffsetArr[i3] = Ser.readOffset(dataInput);
        }
        int readInt2 = dataInput.readInt();
        long[] jArr2 = readInt2 == 0 ? EMPTY_LONG_ARRAY : new long[readInt2];
        for (int i4 = 0; i4 < readInt2; i4++) {
            jArr2[i4] = Ser.readEpochSec(dataInput);
        }
        int i5 = readInt2 + 1;
        ZoneOffset[] zoneOffsetArr2 = new ZoneOffset[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            zoneOffsetArr2[i6] = Ser.readOffset(dataInput);
        }
        int readByte = dataInput.readByte();
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = readByte == 0 ? EMPTY_LASTRULES : new ZoneOffsetTransitionRule[readByte];
        for (int i7 = 0; i7 < readByte; i7++) {
            zoneOffsetTransitionRuleArr[i7] = ZoneOffsetTransitionRule.readExternal(dataInput);
        }
        return new ZoneRules(jArr, zoneOffsetArr, jArr2, zoneOffsetArr2, zoneOffsetTransitionRuleArr);
    }

    static ZoneRules readExternalTimeZone(DataInput dataInput) {
        return new ZoneRules(TimeZone.getTimeZone(dataInput.readUTF()));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser(this.timeZone != null ? (byte) 100 : 1, this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZoneRules)) {
            return false;
        }
        ZoneRules zoneRules = (ZoneRules) obj;
        return Objects.equals(this.timeZone, zoneRules.timeZone) && Arrays.equals(this.standardTransitions, zoneRules.standardTransitions) && Arrays.equals(this.standardOffsets, zoneRules.standardOffsets) && Arrays.equals(this.savingsInstantTransitions, zoneRules.savingsInstantTransitions) && Arrays.equals(this.wallOffsets, zoneRules.wallOffsets) && Arrays.equals(this.lastRules, zoneRules.lastRules);
    }

    public ZoneOffset getOffset(Instant instant) {
        TimeZone timeZone2 = this.timeZone;
        if (timeZone2 != null) {
            return offsetFromMillis(timeZone2.getOffset(instant.toEpochMilli()));
        }
        if (this.savingsInstantTransitions.length == 0) {
            return this.standardOffsets[0];
        }
        long epochSecond = instant.getEpochSecond();
        if (this.lastRules.length > 0) {
            long[] jArr = this.savingsInstantTransitions;
            if (epochSecond > jArr[jArr.length - 1]) {
                ZoneOffset[] zoneOffsetArr = this.wallOffsets;
                ZoneOffsetTransition[] findTransitionArray = findTransitionArray(findYear(epochSecond, zoneOffsetArr[zoneOffsetArr.length - 1]));
                ZoneOffsetTransition zoneOffsetTransition = null;
                for (int i = 0; i < findTransitionArray.length; i++) {
                    zoneOffsetTransition = findTransitionArray[i];
                    if (epochSecond < zoneOffsetTransition.toEpochSecond()) {
                        return zoneOffsetTransition.getOffsetBefore();
                    }
                }
                return zoneOffsetTransition.getOffsetAfter();
            }
        }
        int binarySearch = Arrays.binarySearch(this.savingsInstantTransitions, epochSecond);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        return this.wallOffsets[binarySearch + 1];
    }

    public ZoneOffset getOffset(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        return offsetInfo instanceof ZoneOffsetTransition ? ((ZoneOffsetTransition) offsetInfo).getOffsetBefore() : (ZoneOffset) offsetInfo;
    }

    public ZoneOffset getStandardOffset(Instant instant) {
        TimeZone timeZone2 = this.timeZone;
        if (timeZone2 != null) {
            return offsetFromMillis(timeZone2.getRawOffset());
        }
        if (this.savingsInstantTransitions.length == 0) {
            return this.standardOffsets[0];
        }
        int binarySearch = Arrays.binarySearch(this.standardTransitions, instant.getEpochSecond());
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        return this.standardOffsets[binarySearch + 1];
    }

    public ZoneOffsetTransition getTransition(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        if (offsetInfo instanceof ZoneOffsetTransition) {
            return (ZoneOffsetTransition) offsetInfo;
        }
        return null;
    }

    public List getValidOffsets(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        return offsetInfo instanceof ZoneOffsetTransition ? ((ZoneOffsetTransition) offsetInfo).getValidOffsets() : Collections.singletonList((ZoneOffset) offsetInfo);
    }

    public int hashCode() {
        return ((((Objects.hashCode(this.timeZone) ^ Arrays.hashCode(this.standardTransitions)) ^ Arrays.hashCode(this.standardOffsets)) ^ Arrays.hashCode(this.savingsInstantTransitions)) ^ Arrays.hashCode(this.wallOffsets)) ^ Arrays.hashCode(this.lastRules);
    }

    public boolean isDaylightSavings(Instant instant) {
        return !getStandardOffset(instant).equals(getOffset(instant));
    }

    public boolean isFixedOffset() {
        TimeZone timeZone2 = this.timeZone;
        return timeZone2 != null ? !timeZone2.useDaylightTime() && this.timeZone.getDSTSavings() == 0 && previousTransition(Instant.now()) == null : this.savingsInstantTransitions.length == 0;
    }

    public boolean isValidOffset(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return getValidOffsets(localDateTime).contains(zoneOffset);
    }

    public ZoneOffsetTransition previousTransition(Instant instant) {
        if (this.timeZone != null) {
            long epochSecond = instant.getEpochSecond();
            if (instant.getNano() > 0 && epochSecond < Long.MAX_VALUE) {
                epochSecond++;
            }
            int findYear = findYear(epochSecond, getOffset(instant));
            ZoneOffsetTransition[] findTransitionArray = findTransitionArray(findYear);
            for (int length = findTransitionArray.length - 1; length >= 0; length--) {
                if (epochSecond > findTransitionArray[length].toEpochSecond()) {
                    return findTransitionArray[length];
                }
            }
            if (findYear > 1800) {
                ZoneOffsetTransition[] findTransitionArray2 = findTransitionArray(findYear - 1);
                for (int length2 = findTransitionArray2.length - 1; length2 >= 0; length2--) {
                    if (epochSecond > findTransitionArray2[length2].toEpochSecond()) {
                        return findTransitionArray2[length2];
                    }
                }
                int offset = this.timeZone.getOffset((epochSecond - 1) * 1000);
                long epochDay = LocalDate.of(1800, 1, 1).toEpochDay() * CORSConfig.CORS_DEFAULT_MAX_AGE;
                for (long min = Math.min(epochSecond - 31104000, (Clock.systemUTC().millis() / 1000) + 31968000); epochDay <= min; min -= 7776000) {
                    int offset2 = this.timeZone.getOffset(min * 1000);
                    if (offset != offset2) {
                        int findYear2 = findYear(min, offsetFromMillis(offset2));
                        ZoneOffsetTransition[] findTransitionArray3 = findTransitionArray(findYear2 + 1);
                        for (int length3 = findTransitionArray3.length - 1; length3 >= 0; length3--) {
                            if (epochSecond > findTransitionArray3[length3].toEpochSecond()) {
                                return findTransitionArray3[length3];
                            }
                        }
                        ZoneOffsetTransition[] findTransitionArray4 = findTransitionArray(findYear2);
                        return findTransitionArray4[findTransitionArray4.length - 1];
                    }
                }
            }
            return null;
        } else if (this.savingsInstantTransitions.length == 0) {
            return null;
        } else {
            long epochSecond2 = instant.getEpochSecond();
            if (instant.getNano() > 0 && epochSecond2 < Long.MAX_VALUE) {
                epochSecond2++;
            }
            long[] jArr = this.savingsInstantTransitions;
            long j = jArr[jArr.length - 1];
            if (this.lastRules.length > 0 && epochSecond2 > j) {
                ZoneOffset[] zoneOffsetArr = this.wallOffsets;
                ZoneOffset zoneOffset = zoneOffsetArr[zoneOffsetArr.length - 1];
                int findYear3 = findYear(epochSecond2, zoneOffset);
                ZoneOffsetTransition[] findTransitionArray5 = findTransitionArray(findYear3);
                for (int length4 = findTransitionArray5.length - 1; length4 >= 0; length4--) {
                    if (epochSecond2 > findTransitionArray5[length4].toEpochSecond()) {
                        return findTransitionArray5[length4];
                    }
                }
                int i = findYear3 - 1;
                if (i > findYear(j, zoneOffset)) {
                    ZoneOffsetTransition[] findTransitionArray6 = findTransitionArray(i);
                    return findTransitionArray6[findTransitionArray6.length - 1];
                }
            }
            int binarySearch = Arrays.binarySearch(this.savingsInstantTransitions, epochSecond2);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 1;
            }
            if (binarySearch <= 0) {
                return null;
            }
            int i2 = binarySearch - 1;
            long j2 = this.savingsInstantTransitions[i2];
            ZoneOffset[] zoneOffsetArr2 = this.wallOffsets;
            return new ZoneOffsetTransition(j2, zoneOffsetArr2[i2], zoneOffsetArr2[binarySearch]);
        }
    }

    public String toString() {
        StringBuilder sb;
        TimeZone timeZone2 = this.timeZone;
        if (timeZone2 != null) {
            String id = timeZone2.getID();
            sb = new StringBuilder();
            sb.append("ZoneRules[timeZone=");
            sb.append(id);
        } else {
            ZoneOffset[] zoneOffsetArr = this.standardOffsets;
            ZoneOffset zoneOffset = zoneOffsetArr[zoneOffsetArr.length - 1];
            sb = new StringBuilder();
            sb.append("ZoneRules[currentStandardOffset=");
            sb.append(zoneOffset);
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(this.standardTransitions.length);
        for (long writeEpochSec : this.standardTransitions) {
            Ser.writeEpochSec(writeEpochSec, dataOutput);
        }
        for (ZoneOffset writeOffset : this.standardOffsets) {
            Ser.writeOffset(writeOffset, dataOutput);
        }
        dataOutput.writeInt(this.savingsInstantTransitions.length);
        for (long writeEpochSec2 : this.savingsInstantTransitions) {
            Ser.writeEpochSec(writeEpochSec2, dataOutput);
        }
        for (ZoneOffset writeOffset2 : this.wallOffsets) {
            Ser.writeOffset(writeOffset2, dataOutput);
        }
        dataOutput.writeByte(this.lastRules.length);
        for (ZoneOffsetTransitionRule writeExternal : this.lastRules) {
            writeExternal.writeExternal(dataOutput);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeExternalTimeZone(DataOutput dataOutput) {
        dataOutput.writeUTF(this.timeZone.getID());
    }
}
