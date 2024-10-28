package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.DayOfWeek;
import j$.time.Instant;
import j$.time.Instant$$ExternalSyntheticBackport6;
import j$.time.LocalDate$$ExternalSyntheticBackport5;
import j$.time.ZoneId;
import j$.time.chrono.Chronology;
import j$.time.format.ResolverStyle;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjusters;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import org.fusesource.jansi.AnsiRenderer;

public abstract class AbstractChronology implements Chronology {
    private static final ConcurrentHashMap CHRONOS_BY_ID = new ConcurrentHashMap();
    private static final ConcurrentHashMap CHRONOS_BY_TYPE = new ConcurrentHashMap();
    private static final Locale JAPANESE_CALENDAR_LOCALE = new Locale("ja", "JP", "JP");

    protected AbstractChronology() {
    }

    private static boolean initCache() {
        if (CHRONOS_BY_ID.get("ISO") != null) {
            return false;
        }
        registerChrono(HijrahChronology.INSTANCE);
        registerChrono(JapaneseChronology.INSTANCE);
        registerChrono(MinguoChronology.INSTANCE);
        registerChrono(ThaiBuddhistChronology.INSTANCE);
        Iterator<S> it = ServiceLoader.load(AbstractChronology.class, (ClassLoader) null).iterator();
        while (it.hasNext()) {
            AbstractChronology abstractChronology = (AbstractChronology) it.next();
            if (!abstractChronology.getId().equals("ISO")) {
                registerChrono(abstractChronology);
            }
        }
        registerChrono(IsoChronology.INSTANCE);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static j$.time.chrono.Chronology of(java.lang.String r3) {
        /*
            java.lang.String r0 = "id"
            j$.util.Objects.requireNonNull(r3, r0)
        L_0x0005:
            j$.time.chrono.Chronology r0 = of0(r3)
            if (r0 == 0) goto L_0x000c
            return r0
        L_0x000c:
            boolean r0 = initCache()
            if (r0 != 0) goto L_0x0005
            java.lang.Class<j$.time.chrono.Chronology> r0 = j$.time.chrono.Chronology.class
            java.util.ServiceLoader r0 = java.util.ServiceLoader.load(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x001c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x003d
            java.lang.Object r1 = r0.next()
            j$.time.chrono.Chronology r1 = (j$.time.chrono.Chronology) r1
            java.lang.String r2 = r1.getId()
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x003c
            java.lang.String r2 = r1.getCalendarType()
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x001c
        L_0x003c:
            return r1
        L_0x003d:
            j$.time.DateTimeException r0 = new j$.time.DateTimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown chronology: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            goto L_0x0055
        L_0x0054:
            throw r0
        L_0x0055:
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.AbstractChronology.of(java.lang.String):j$.time.chrono.Chronology");
    }

    private static Chronology of0(String str) {
        Chronology chronology = (Chronology) CHRONOS_BY_ID.get(str);
        return chronology == null ? (Chronology) CHRONOS_BY_TYPE.get(str) : chronology;
    }

    static Chronology readExternal(DataInput dataInput) {
        return Chronology.CC.of(dataInput.readUTF());
    }

    static Chronology registerChrono(Chronology chronology) {
        return registerChrono(chronology, chronology.getId());
    }

    static Chronology registerChrono(Chronology chronology, String str) {
        String calendarType;
        Chronology chronology2 = (Chronology) CHRONOS_BY_ID.putIfAbsent(str, chronology);
        if (chronology2 == null && (calendarType = chronology.getCalendarType()) != null) {
            CHRONOS_BY_TYPE.putIfAbsent(calendarType, chronology);
        }
        return chronology2;
    }

    /* access modifiers changed from: package-private */
    public void addFieldValue(Map map, ChronoField chronoField, long j) {
        Long l = (Long) map.get(chronoField);
        if (l == null || l.longValue() == j) {
            map.put(chronoField, Long.valueOf(j));
            return;
        }
        throw new DateTimeException("Conflict found: " + chronoField + AnsiRenderer.CODE_TEXT_SEPARATOR + l + " differs from " + chronoField + AnsiRenderer.CODE_TEXT_SEPARATOR + j);
    }

    public int compareTo(Chronology chronology) {
        return getId().compareTo(chronology.getId());
    }

    public abstract /* synthetic */ ChronoLocalDate dateNow();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AbstractChronology) && compareTo((Chronology) (AbstractChronology) obj) == 0;
    }

    public int hashCode() {
        return getClass().hashCode() ^ getId().hashCode();
    }

    public /* synthetic */ ChronoLocalDateTime localDateTime(TemporalAccessor temporalAccessor) {
        return Chronology.CC.$default$localDateTime(this, temporalAccessor);
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveAligned(ChronoLocalDate chronoLocalDate, long j, long j2, long j3) {
        long j4;
        ChronoLocalDate plus = chronoLocalDate.plus(j, ChronoUnit.MONTHS);
        ChronoUnit chronoUnit = ChronoUnit.WEEKS;
        ChronoLocalDate plus2 = plus.plus(j2, chronoUnit);
        if (j3 > 7) {
            j4 = j3 - 1;
            plus2 = plus2.plus(j4 / 7, chronoUnit);
        } else {
            if (j3 < 1) {
                plus2 = plus2.plus(Instant$$ExternalSyntheticBackport6.m(j3, 7) / 7, chronoUnit);
                j4 = j3 + 6;
            }
            return plus2.with(TemporalAdjusters.nextOrSame(DayOfWeek.of((int) j3)));
        }
        j3 = (j4 % 7) + 1;
        return plus2.with(TemporalAdjusters.nextOrSame(DayOfWeek.of((int) j3)));
    }

    public ChronoLocalDate resolveDate(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.EPOCH_DAY;
        if (map.containsKey(chronoField)) {
            return dateEpochDay(((Long) map.remove(chronoField)).longValue());
        }
        resolveProlepticMonth(map, resolverStyle);
        ChronoLocalDate resolveYearOfEra = resolveYearOfEra(map, resolverStyle);
        if (resolveYearOfEra != null) {
            return resolveYearOfEra;
        }
        if (!map.containsKey(ChronoField.YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.MONTH_OF_YEAR)) {
            if (map.containsKey(ChronoField.DAY_OF_MONTH)) {
                return resolveYMD(map, resolverStyle);
            }
            if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    return resolveYMAA(map, resolverStyle);
                }
                if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    return resolveYMAD(map, resolverStyle);
                }
            }
        }
        if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
            return resolveYD(map, resolverStyle);
        }
        if (!map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
            return resolveYAA(map, resolverStyle);
        }
        if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
            return resolveYAD(map, resolverStyle);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void resolveProlepticMonth(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.PROLEPTIC_MONTH;
        Long l = (Long) map.remove(chronoField);
        if (l != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                chronoField.checkValidValue(l.longValue());
            }
            ChronoLocalDate with = dateNow().with(ChronoField.DAY_OF_MONTH, 1).with(chronoField, l.longValue());
            ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
            addFieldValue(map, chronoField2, (long) with.get(chronoField2));
            ChronoField chronoField3 = ChronoField.YEAR;
            addFieldValue(map, chronoField3, (long) with.get(chronoField3));
        }
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYAA(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = range(chronoField).checkValidIntValue(((Long) map.remove(chronoField)).longValue(), chronoField);
        if (resolverStyle == ResolverStyle.LENIENT) {
            long m = Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR)).longValue(), 1);
            return dateYearDay(checkValidIntValue, 1).plus(m, ChronoUnit.WEEKS).plus(Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)).longValue(), 1), ChronoUnit.DAYS);
        }
        ChronoField chronoField2 = ChronoField.ALIGNED_WEEK_OF_YEAR;
        int checkValidIntValue2 = range(chronoField2).checkValidIntValue(((Long) map.remove(chronoField2)).longValue(), chronoField2);
        ChronoField chronoField3 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
        ChronoLocalDate plus = dateYearDay(checkValidIntValue, 1).plus((long) (((checkValidIntValue2 - 1) * 7) + (range(chronoField3).checkValidIntValue(((Long) map.remove(chronoField3)).longValue(), chronoField3) - 1)), ChronoUnit.DAYS);
        if (resolverStyle != ResolverStyle.STRICT || plus.get(chronoField) == checkValidIntValue) {
            return plus;
        }
        throw new DateTimeException("Strict mode rejected resolved date as it is in a different year");
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYAD(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = range(chronoField).checkValidIntValue(((Long) map.remove(chronoField)).longValue(), chronoField);
        if (resolverStyle == ResolverStyle.LENIENT) {
            return resolveAligned(dateYearDay(checkValidIntValue, 1), 0, Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR)).longValue(), 1), Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.DAY_OF_WEEK)).longValue(), 1));
        }
        ChronoField chronoField2 = ChronoField.ALIGNED_WEEK_OF_YEAR;
        int checkValidIntValue2 = range(chronoField2).checkValidIntValue(((Long) map.remove(chronoField2)).longValue(), chronoField2);
        ChronoField chronoField3 = ChronoField.DAY_OF_WEEK;
        ChronoLocalDate with = dateYearDay(checkValidIntValue, 1).plus((long) ((checkValidIntValue2 - 1) * 7), ChronoUnit.DAYS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(range(chronoField3).checkValidIntValue(((Long) map.remove(chronoField3)).longValue(), chronoField3))));
        if (resolverStyle != ResolverStyle.STRICT || with.get(chronoField) == checkValidIntValue) {
            return with;
        }
        throw new DateTimeException("Strict mode rejected resolved date as it is in a different year");
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYD(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = range(chronoField).checkValidIntValue(((Long) map.remove(chronoField)).longValue(), chronoField);
        if (resolverStyle == ResolverStyle.LENIENT) {
            return dateYearDay(checkValidIntValue, 1).plus(Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.DAY_OF_YEAR)).longValue(), 1), ChronoUnit.DAYS);
        }
        ChronoField chronoField2 = ChronoField.DAY_OF_YEAR;
        return dateYearDay(checkValidIntValue, range(chronoField2).checkValidIntValue(((Long) map.remove(chronoField2)).longValue(), chronoField2));
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYMAA(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = range(chronoField).checkValidIntValue(((Long) map.remove(chronoField)).longValue(), chronoField);
        if (resolverStyle == ResolverStyle.LENIENT) {
            long m = Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.MONTH_OF_YEAR)).longValue(), 1);
            long m2 = Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH)).longValue(), 1);
            return date(checkValidIntValue, 1, 1).plus(m, ChronoUnit.MONTHS).plus(m2, ChronoUnit.WEEKS).plus(Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)).longValue(), 1), ChronoUnit.DAYS);
        }
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        int checkValidIntValue2 = range(chronoField2).checkValidIntValue(((Long) map.remove(chronoField2)).longValue(), chronoField2);
        ChronoField chronoField3 = ChronoField.ALIGNED_WEEK_OF_MONTH;
        int checkValidIntValue3 = range(chronoField3).checkValidIntValue(((Long) map.remove(chronoField3)).longValue(), chronoField3);
        ChronoField chronoField4 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
        ChronoLocalDate plus = date(checkValidIntValue, checkValidIntValue2, 1).plus((long) (((checkValidIntValue3 - 1) * 7) + (range(chronoField4).checkValidIntValue(((Long) map.remove(chronoField4)).longValue(), chronoField4) - 1)), ChronoUnit.DAYS);
        if (resolverStyle != ResolverStyle.STRICT || plus.get(chronoField2) == checkValidIntValue2) {
            return plus;
        }
        throw new DateTimeException("Strict mode rejected resolved date as it is in a different month");
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYMAD(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = range(chronoField).checkValidIntValue(((Long) map.remove(chronoField)).longValue(), chronoField);
        if (resolverStyle == ResolverStyle.LENIENT) {
            return resolveAligned(date(checkValidIntValue, 1, 1), Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.MONTH_OF_YEAR)).longValue(), 1), Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH)).longValue(), 1), Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.DAY_OF_WEEK)).longValue(), 1));
        }
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        int checkValidIntValue2 = range(chronoField2).checkValidIntValue(((Long) map.remove(chronoField2)).longValue(), chronoField2);
        ChronoField chronoField3 = ChronoField.ALIGNED_WEEK_OF_MONTH;
        int checkValidIntValue3 = range(chronoField3).checkValidIntValue(((Long) map.remove(chronoField3)).longValue(), chronoField3);
        ChronoField chronoField4 = ChronoField.DAY_OF_WEEK;
        ChronoLocalDate with = date(checkValidIntValue, checkValidIntValue2, 1).plus((long) ((checkValidIntValue3 - 1) * 7), ChronoUnit.DAYS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(range(chronoField4).checkValidIntValue(((Long) map.remove(chronoField4)).longValue(), chronoField4))));
        if (resolverStyle != ResolverStyle.STRICT || with.get(chronoField2) == checkValidIntValue2) {
            return with;
        }
        throw new DateTimeException("Strict mode rejected resolved date as it is in a different month");
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYMD(Map map, ResolverStyle resolverStyle) {
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = range(chronoField).checkValidIntValue(((Long) map.remove(chronoField)).longValue(), chronoField);
        if (resolverStyle == ResolverStyle.LENIENT) {
            long m = Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.MONTH_OF_YEAR)).longValue(), 1);
            return date(checkValidIntValue, 1, 1).plus(m, ChronoUnit.MONTHS).plus(Instant$$ExternalSyntheticBackport6.m(((Long) map.remove(ChronoField.DAY_OF_MONTH)).longValue(), 1), ChronoUnit.DAYS);
        }
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        int checkValidIntValue2 = range(chronoField2).checkValidIntValue(((Long) map.remove(chronoField2)).longValue(), chronoField2);
        ChronoField chronoField3 = ChronoField.DAY_OF_MONTH;
        int checkValidIntValue3 = range(chronoField3).checkValidIntValue(((Long) map.remove(chronoField3)).longValue(), chronoField3);
        if (resolverStyle != ResolverStyle.SMART) {
            return date(checkValidIntValue, checkValidIntValue2, checkValidIntValue3);
        }
        try {
            return date(checkValidIntValue, checkValidIntValue2, checkValidIntValue3);
        } catch (DateTimeException unused) {
            return date(checkValidIntValue, checkValidIntValue2, 1).with(TemporalAdjusters.lastDayOfMonth());
        }
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDate resolveYearOfEra(Map map, ResolverStyle resolverStyle) {
        Era era;
        long j;
        ChronoField chronoField = ChronoField.YEAR_OF_ERA;
        Long l = (Long) map.remove(chronoField);
        if (l != null) {
            ChronoField chronoField2 = ChronoField.ERA;
            Long l2 = (Long) map.remove(chronoField2);
            int checkValidIntValue = resolverStyle != ResolverStyle.LENIENT ? range(chronoField).checkValidIntValue(l.longValue(), chronoField) : LocalDate$$ExternalSyntheticBackport5.m(l.longValue());
            if (l2 != null) {
                addFieldValue(map, ChronoField.YEAR, (long) prolepticYear(eraOf(range(chronoField2).checkValidIntValue(l2.longValue(), chronoField2)), checkValidIntValue));
                return null;
            }
            ChronoField chronoField3 = ChronoField.YEAR;
            if (map.containsKey(chronoField3)) {
                era = dateYearDay(range(chronoField3).checkValidIntValue(((Long) map.get(chronoField3)).longValue(), chronoField3), 1).getEra();
            } else if (resolverStyle == ResolverStyle.STRICT) {
                map.put(chronoField, l);
                return null;
            } else {
                List eras = eras();
                if (eras.isEmpty()) {
                    j = (long) checkValidIntValue;
                    addFieldValue(map, chronoField3, j);
                    return null;
                }
                era = (Era) eras.get(eras.size() - 1);
            }
            j = (long) prolepticYear(era, checkValidIntValue);
            addFieldValue(map, chronoField3, j);
            return null;
        }
        ChronoField chronoField4 = ChronoField.ERA;
        if (!map.containsKey(chronoField4)) {
            return null;
        }
        range(chronoField4).checkValidValue(((Long) map.get(chronoField4)).longValue(), chronoField4);
        return null;
    }

    public String toString() {
        return getId();
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeUTF(getId());
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    public /* synthetic */ ChronoZonedDateTime zonedDateTime(Instant instant, ZoneId zoneId) {
        return Chronology.CC.$default$zonedDateTime(this, instant, zoneId);
    }
}
