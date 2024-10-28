package com.typesafe.config.impl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigMemorySize;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigResolveOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import j$.time.DateTimeException;
import j$.time.Duration;
import j$.time.Period;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalAmount;
import j$.util.Collection;
import j$.util.stream.Collectors;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

final class SimpleConfig implements Config, MergeableValue, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 1;
    private final AbstractConfigObject object;

    SimpleConfig(AbstractConfigObject abstractConfigObject) {
        this.object = abstractConfigObject;
    }

    public AbstractConfigObject root() {
        return this.object;
    }

    public ConfigOrigin origin() {
        return this.object.origin();
    }

    public SimpleConfig resolve() {
        return resolve(ConfigResolveOptions.defaults());
    }

    public SimpleConfig resolve(ConfigResolveOptions configResolveOptions) {
        return resolveWith((Config) this, configResolveOptions);
    }

    public SimpleConfig resolveWith(Config config) {
        return resolveWith(config, ConfigResolveOptions.defaults());
    }

    public SimpleConfig resolveWith(Config config, ConfigResolveOptions configResolveOptions) {
        AbstractConfigValue resolve = ResolveContext.resolve(this.object, ((SimpleConfig) config).object, configResolveOptions);
        if (resolve == this.object) {
            return this;
        }
        return new SimpleConfig((AbstractConfigObject) resolve);
    }

    private ConfigValue hasPathPeek(String str) {
        Path newPath = Path.newPath(str);
        try {
            return this.object.peekPath(newPath);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(newPath, e);
        }
    }

    public boolean hasPath(String str) {
        ConfigValue hasPathPeek = hasPathPeek(str);
        return (hasPathPeek == null || hasPathPeek.valueType() == ConfigValueType.NULL) ? false : true;
    }

    public boolean hasPathOrNull(String str) {
        return hasPathPeek(str) != null;
    }

    public boolean isEmpty() {
        return this.object.isEmpty();
    }

    private static void findPaths(Set<Map.Entry<String, ConfigValue>> set, Path path, AbstractConfigObject abstractConfigObject) {
        for (Map.Entry entry : abstractConfigObject.entrySet()) {
            ConfigValue configValue = (ConfigValue) entry.getValue();
            Path newKey = Path.newKey((String) entry.getKey());
            if (path != null) {
                newKey = newKey.prepend(path);
            }
            if (configValue instanceof AbstractConfigObject) {
                findPaths(set, newKey, (AbstractConfigObject) configValue);
            } else if (!(configValue instanceof ConfigNull)) {
                set.add(new AbstractMap.SimpleImmutableEntry(newKey.render(), configValue));
            }
        }
    }

    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        HashSet hashSet = new HashSet();
        findPaths(hashSet, (Path) null, this.object);
        return hashSet;
    }

    private static AbstractConfigValue throwIfNull(AbstractConfigValue abstractConfigValue, ConfigValueType configValueType, Path path) {
        if (abstractConfigValue.valueType() != ConfigValueType.NULL) {
            return abstractConfigValue;
        }
        throw new ConfigException.Null(abstractConfigValue.origin(), path.render(), configValueType != null ? configValueType.name() : null);
    }

    private static AbstractConfigValue findKey(AbstractConfigObject abstractConfigObject, String str, ConfigValueType configValueType, Path path) {
        return throwIfNull(findKeyOrNull(abstractConfigObject, str, configValueType, path), configValueType, path);
    }

    private static AbstractConfigValue findKeyOrNull(AbstractConfigObject abstractConfigObject, String str, ConfigValueType configValueType, Path path) {
        AbstractConfigValue peekAssumingResolved = abstractConfigObject.peekAssumingResolved(str, path);
        if (peekAssumingResolved != null) {
            if (configValueType != null) {
                peekAssumingResolved = DefaultTransformer.transform(peekAssumingResolved, configValueType);
            }
            if (configValueType == null || peekAssumingResolved.valueType() == configValueType || peekAssumingResolved.valueType() == ConfigValueType.NULL) {
                return peekAssumingResolved;
            }
            throw new ConfigException.WrongType(peekAssumingResolved.origin(), path.render(), configValueType.name(), peekAssumingResolved.valueType().name());
        }
        throw new ConfigException.Missing((ConfigOrigin) abstractConfigObject.origin(), path.render());
    }

    private static AbstractConfigValue findOrNull(AbstractConfigObject abstractConfigObject, Path path, ConfigValueType configValueType, Path path2) {
        try {
            String first = path.first();
            Path remainder = path.remainder();
            if (remainder == null) {
                return findKeyOrNull(abstractConfigObject, first, configValueType, path2);
            }
            return findOrNull((AbstractConfigObject) findKey(abstractConfigObject, first, ConfigValueType.OBJECT, path2.subPath(0, path2.length() - remainder.length())), remainder, configValueType, path2);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    /* access modifiers changed from: package-private */
    public AbstractConfigValue find(Path path, ConfigValueType configValueType, Path path2) {
        return throwIfNull(findOrNull(this.object, path, configValueType, path2), configValueType, path2);
    }

    /* access modifiers changed from: package-private */
    public AbstractConfigValue find(String str, ConfigValueType configValueType) {
        Path newPath = Path.newPath(str);
        return find(newPath, configValueType, newPath);
    }

    private AbstractConfigValue findOrNull(Path path, ConfigValueType configValueType, Path path2) {
        return findOrNull(this.object, path, configValueType, path2);
    }

    private AbstractConfigValue findOrNull(String str, ConfigValueType configValueType) {
        Path newPath = Path.newPath(str);
        return findOrNull(newPath, configValueType, newPath);
    }

    public AbstractConfigValue getValue(String str) {
        return find(str, (ConfigValueType) null);
    }

    public boolean getIsNull(String str) {
        return findOrNull(str, (ConfigValueType) null).valueType() == ConfigValueType.NULL;
    }

    public boolean getBoolean(String str) {
        return ((Boolean) find(str, ConfigValueType.BOOLEAN).unwrapped()).booleanValue();
    }

    private ConfigNumber getConfigNumber(String str) {
        return (ConfigNumber) find(str, ConfigValueType.NUMBER);
    }

    public Number getNumber(String str) {
        return getConfigNumber(str).unwrapped();
    }

    public int getInt(String str) {
        return getConfigNumber(str).intValueRangeChecked(str);
    }

    public long getLong(String str) {
        return getNumber(str).longValue();
    }

    public double getDouble(String str) {
        return getNumber(str).doubleValue();
    }

    public String getString(String str) {
        return (String) find(str, ConfigValueType.STRING).unwrapped();
    }

    public <T extends Enum<T>> T getEnum(Class<T> cls, String str) {
        return getEnumValue(str, cls, find(str, ConfigValueType.STRING));
    }

    public ConfigList getList(String str) {
        return (ConfigList) find(str, ConfigValueType.LIST);
    }

    public AbstractConfigObject getObject(String str) {
        return (AbstractConfigObject) find(str, ConfigValueType.OBJECT);
    }

    public SimpleConfig getConfig(String str) {
        return getObject(str).toConfig();
    }

    public Object getAnyRef(String str) {
        return find(str, (ConfigValueType) null).unwrapped();
    }

    public Long getBytes(String str) {
        return toLong(getBytesBigInteger(str), find(str, ConfigValueType.STRING).origin(), str);
    }

    private BigInteger getBytesBigInteger(String str) {
        BigInteger bigInteger;
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        try {
            bigInteger = BigInteger.valueOf(getLong(str));
        } catch (ConfigException.WrongType unused) {
            bigInteger = parseBytes((String) find.unwrapped(), find.origin(), str);
        }
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        ConfigOrigin origin = find.origin();
        throw new ConfigException.BadValue(origin, str, "Attempt to construct memory size with negative number: " + bigInteger);
    }

    private List<BigInteger> getBytesListBigInteger(String str) {
        BigInteger bigInteger;
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            if (configValue.valueType() == ConfigValueType.NUMBER) {
                bigInteger = BigInteger.valueOf(((Number) configValue.unwrapped()).longValue());
            } else if (configValue.valueType() == ConfigValueType.STRING) {
                bigInteger = parseBytes((String) configValue.unwrapped(), configValue.origin(), str);
            } else {
                throw new ConfigException.WrongType(configValue.origin(), str, "memory size string or number of bytes", configValue.valueType().name());
            }
            if (bigInteger.signum() >= 0) {
                arrayList.add(bigInteger);
            } else {
                ConfigOrigin origin = configValue.origin();
                throw new ConfigException.BadValue(origin, str, "Attempt to construct ConfigMemorySize with negative number: " + bigInteger);
            }
        }
        return arrayList;
    }

    public ConfigMemorySize getMemorySize(String str) {
        return ConfigMemorySize.ofBytes(getBytesBigInteger(str));
    }

    @Deprecated
    public Long getMilliseconds(String str) {
        return Long.valueOf(getDuration(str, TimeUnit.MILLISECONDS));
    }

    @Deprecated
    public Long getNanoseconds(String str) {
        return Long.valueOf(getDuration(str, TimeUnit.NANOSECONDS));
    }

    public long getDuration(String str, TimeUnit timeUnit) {
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        return timeUnit.convert(parseDuration((String) find.unwrapped(), find.origin(), str), TimeUnit.NANOSECONDS);
    }

    public Duration getDuration(String str) {
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        return Duration.ofNanos(parseDuration((String) find.unwrapped(), find.origin(), str));
    }

    public Period getPeriod(String str) {
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        return parsePeriod((String) find.unwrapped(), find.origin(), str);
    }

    public TemporalAmount getTemporal(String str) {
        try {
            return getDuration(str);
        } catch (ConfigException.BadValue unused) {
            return getPeriod(str);
        }
    }

    private <T> List<T> getHomogeneousUnwrappedList(String str, ConfigValueType configValueType) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            AbstractConfigValue abstractConfigValue = (AbstractConfigValue) configValue;
            if (configValueType != null) {
                abstractConfigValue = DefaultTransformer.transform(abstractConfigValue, configValueType);
            }
            if (abstractConfigValue.valueType() == configValueType) {
                arrayList.add(abstractConfigValue.unwrapped());
            } else {
                throw new ConfigException.WrongType(abstractConfigValue.origin(), str, "list of " + configValueType.name(), "list of " + abstractConfigValue.valueType().name());
            }
        }
        return arrayList;
    }

    public List<Boolean> getBooleanList(String str) {
        return getHomogeneousUnwrappedList(str, ConfigValueType.BOOLEAN);
    }

    public List<Number> getNumberList(String str) {
        return getHomogeneousUnwrappedList(str, ConfigValueType.NUMBER);
    }

    public List<Integer> getIntList(String str) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : getHomogeneousWrappedList(str, ConfigValueType.NUMBER)) {
            arrayList.add(Integer.valueOf(((ConfigNumber) abstractConfigValue).intValueRangeChecked(str)));
        }
        return arrayList;
    }

    public List<Long> getLongList(String str) {
        ArrayList arrayList = new ArrayList();
        for (Number longValue : getNumberList(str)) {
            arrayList.add(Long.valueOf(longValue.longValue()));
        }
        return arrayList;
    }

    public List<Double> getDoubleList(String str) {
        ArrayList arrayList = new ArrayList();
        for (Number doubleValue : getNumberList(str)) {
            arrayList.add(Double.valueOf(doubleValue.doubleValue()));
        }
        return arrayList;
    }

    public List<String> getStringList(String str) {
        return getHomogeneousUnwrappedList(str, ConfigValueType.STRING);
    }

    public <T extends Enum<T>> List<T> getEnumList(Class<T> cls, String str) {
        List<ConfigString> homogeneousWrappedList = getHomogeneousWrappedList(str, ConfigValueType.STRING);
        ArrayList arrayList = new ArrayList();
        for (ConfigString enumValue : homogeneousWrappedList) {
            arrayList.add(getEnumValue(str, cls, enumValue));
        }
        return arrayList;
    }

    private <T extends Enum<T>> T getEnumValue(String str, Class<T> cls, ConfigValue configValue) {
        String str2 = (String) configValue.unwrapped();
        try {
            return Enum.valueOf(cls, str2);
        } catch (IllegalArgumentException unused) {
            ArrayList arrayList = new ArrayList();
            Enum[] enumArr = (Enum[]) cls.getEnumConstants();
            if (enumArr != null) {
                for (Enum name : enumArr) {
                    arrayList.add(name.name());
                }
            }
            throw new ConfigException.BadValue(configValue.origin(), str, String.format("The enum class %s has no constant of the name '%s' (should be one of %s.)", new Object[]{cls.getSimpleName(), str2, arrayList}));
        }
    }

    private <T extends ConfigValue> List<T> getHomogeneousWrappedList(String str, ConfigValueType configValueType) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            AbstractConfigValue abstractConfigValue = (AbstractConfigValue) configValue;
            if (configValueType != null) {
                abstractConfigValue = DefaultTransformer.transform(abstractConfigValue, configValueType);
            }
            if (abstractConfigValue.valueType() == configValueType) {
                arrayList.add(abstractConfigValue);
            } else {
                throw new ConfigException.WrongType(abstractConfigValue.origin(), str, "list of " + configValueType.name(), "list of " + abstractConfigValue.valueType().name());
            }
        }
        return arrayList;
    }

    public List<ConfigObject> getObjectList(String str) {
        return getHomogeneousWrappedList(str, ConfigValueType.OBJECT);
    }

    public List<? extends Config> getConfigList(String str) {
        List<ConfigObject> objectList = getObjectList(str);
        ArrayList arrayList = new ArrayList();
        for (ConfigObject config : objectList) {
            arrayList.add(config.toConfig());
        }
        return arrayList;
    }

    public List<? extends Object> getAnyRefList(String str) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue unwrapped : getList(str)) {
            arrayList.add(unwrapped.unwrapped());
        }
        return arrayList;
    }

    public List<Long> getBytesList(String str) {
        return (List) Collection.EL.stream(getBytesListBigInteger(str)).map(new SimpleConfig$$ExternalSyntheticLambda0(this, find(str, ConfigValueType.LIST), str)).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getBytesList$0$com-typesafe-config-impl-SimpleConfig  reason: not valid java name */
    public /* synthetic */ Long m1442lambda$getBytesList$0$comtypesafeconfigimplSimpleConfig(ConfigValue configValue, String str, BigInteger bigInteger) {
        return toLong(bigInteger, configValue.origin(), str);
    }

    private Long toLong(BigInteger bigInteger, ConfigOrigin configOrigin, String str) {
        if (bigInteger.bitLength() < 64) {
            return Long.valueOf(bigInteger.longValue());
        }
        throw new ConfigException.BadValue(configOrigin, str, "size-in-bytes value is out of range for a 64-bit long: '" + bigInteger + "'");
    }

    public List<ConfigMemorySize> getMemorySizeList(String str) {
        return (List) Collection.EL.stream(getBytesListBigInteger(str)).map(new SimpleConfig$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }

    public List<Long> getDurationList(String str, TimeUnit timeUnit) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            if (configValue.valueType() == ConfigValueType.NUMBER) {
                arrayList.add(Long.valueOf(timeUnit.convert(((Number) configValue.unwrapped()).longValue(), TimeUnit.MILLISECONDS)));
            } else if (configValue.valueType() == ConfigValueType.STRING) {
                arrayList.add(Long.valueOf(timeUnit.convert(parseDuration((String) configValue.unwrapped(), configValue.origin(), str), TimeUnit.NANOSECONDS)));
            } else {
                throw new ConfigException.WrongType(configValue.origin(), str, "duration string or number of milliseconds", configValue.valueType().name());
            }
        }
        return arrayList;
    }

    public List<Duration> getDurationList(String str) {
        List<Long> durationList = getDurationList(str, TimeUnit.NANOSECONDS);
        ArrayList arrayList = new ArrayList(durationList.size());
        for (Long longValue : durationList) {
            arrayList.add(Duration.ofNanos(longValue.longValue()));
        }
        return arrayList;
    }

    @Deprecated
    public List<Long> getMillisecondsList(String str) {
        return getDurationList(str, TimeUnit.MILLISECONDS);
    }

    @Deprecated
    public List<Long> getNanosecondsList(String str) {
        return getDurationList(str, TimeUnit.NANOSECONDS);
    }

    public AbstractConfigObject toFallbackValue() {
        return this.object;
    }

    public SimpleConfig withFallback(ConfigMergeable configMergeable) {
        return this.object.withFallback(configMergeable).toConfig();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof SimpleConfig) {
            return this.object.equals(((SimpleConfig) obj).object);
        }
        return false;
    }

    public final int hashCode() {
        return this.object.hashCode() * 41;
    }

    public String toString() {
        return "Config(" + this.object.toString() + ")";
    }

    private static String getUnits(String str) {
        int length = str.length() - 1;
        while (length >= 0 && Character.isLetter(str.charAt(length))) {
            length--;
        }
        return str.substring(length + 1);
    }

    public static Period parsePeriod(String str, ConfigOrigin configOrigin, String str2) {
        String str3;
        ChronoUnit chronoUnit;
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        String units = getUnits(unicodeTrim);
        String unicodeTrim2 = ConfigImplUtil.unicodeTrim(unicodeTrim.substring(0, unicodeTrim.length() - units.length()));
        if (unicodeTrim2.length() != 0) {
            if (units.length() <= 2 || units.endsWith("s")) {
                str3 = units;
            } else {
                str3 = units + "s";
            }
            if (str3.equals("") || str3.equals("d") || str3.equals("days")) {
                chronoUnit = ChronoUnit.DAYS;
            } else if (str3.equals("w") || str3.equals("weeks")) {
                chronoUnit = ChronoUnit.WEEKS;
            } else if (str3.equals("m") || str3.equals("mo") || str3.equals("months")) {
                chronoUnit = ChronoUnit.MONTHS;
            } else if (str3.equals("y") || str3.equals("years")) {
                chronoUnit = ChronoUnit.YEARS;
            } else {
                throw new ConfigException.BadValue(configOrigin, str2, "Could not parse time unit '" + units + "' (try d, w, mo, y)");
            }
            try {
                return periodOf(Integer.parseInt(unicodeTrim2), chronoUnit);
            } catch (NumberFormatException unused) {
                throw new ConfigException.BadValue(configOrigin, str2, "Could not parse duration number '" + unicodeTrim2 + "'");
            }
        } else {
            throw new ConfigException.BadValue(configOrigin, str2, "No number in period value '" + str + "'");
        }
    }

    private static Period periodOf(int i, ChronoUnit chronoUnit) {
        if (!chronoUnit.isTimeBased()) {
            int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[chronoUnit.ordinal()];
            if (i2 == 1) {
                return Period.ofDays(i);
            }
            if (i2 == 2) {
                return Period.ofWeeks(i);
            }
            if (i2 == 3) {
                return Period.ofMonths(i);
            }
            if (i2 == 4) {
                return Period.ofYears(i);
            }
            throw new DateTimeException(chronoUnit + " cannot be converted to a java.time.Period");
        }
        throw new DateTimeException(chronoUnit + " cannot be converted to a java.time.Period");
    }

    /* renamed from: com.typesafe.config.impl.SimpleConfig$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.SimpleConfig.AnonymousClass1.<clinit>():void");
        }
    }

    public static long parseDuration(String str, ConfigOrigin configOrigin, String str2) {
        String str3;
        TimeUnit timeUnit;
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        String units = getUnits(unicodeTrim);
        String unicodeTrim2 = ConfigImplUtil.unicodeTrim(unicodeTrim.substring(0, unicodeTrim.length() - units.length()));
        if (unicodeTrim2.length() != 0) {
            if (units.length() <= 2 || units.endsWith("s")) {
                str3 = units;
            } else {
                str3 = units + "s";
            }
            if (str3.equals("") || str3.equals("ms") || str3.equals("millis") || str3.equals("milliseconds")) {
                timeUnit = TimeUnit.MILLISECONDS;
            } else if (str3.equals("us") || str3.equals("micros") || str3.equals("microseconds")) {
                timeUnit = TimeUnit.MICROSECONDS;
            } else if (str3.equals("ns") || str3.equals("nanos") || str3.equals("nanoseconds")) {
                timeUnit = TimeUnit.NANOSECONDS;
            } else if (str3.equals("d") || str3.equals("days")) {
                timeUnit = TimeUnit.DAYS;
            } else if (str3.equals("h") || str3.equals("hours")) {
                timeUnit = TimeUnit.HOURS;
            } else if (str3.equals("s") || str3.equals("seconds")) {
                timeUnit = TimeUnit.SECONDS;
            } else if (str3.equals("m") || str3.equals("minutes")) {
                timeUnit = TimeUnit.MINUTES;
            } else {
                throw new ConfigException.BadValue(configOrigin, str2, "Could not parse time unit '" + units + "' (try ns, us, ms, s, m, h, d)");
            }
            try {
                if (unicodeTrim2.matches("[+-]?[0-9]+")) {
                    return timeUnit.toNanos(Long.parseLong(unicodeTrim2));
                }
                long nanos = timeUnit.toNanos(1);
                double parseDouble = Double.parseDouble(unicodeTrim2);
                double d = (double) nanos;
                Double.isNaN(d);
                return (long) (parseDouble * d);
            } catch (NumberFormatException unused) {
                throw new ConfigException.BadValue(configOrigin, str2, "Could not parse duration number '" + unicodeTrim2 + "'");
            }
        } else {
            throw new ConfigException.BadValue(configOrigin, str2, "No number in duration value '" + str + "'");
        }
    }

    private enum MemoryUnit {
        BYTES("", 1024, 0),
        KILOBYTES("kilo", 1000, 1),
        MEGABYTES("mega", 1000, 2),
        GIGABYTES("giga", 1000, 3),
        TERABYTES("tera", 1000, 4),
        PETABYTES("peta", 1000, 5),
        EXABYTES("exa", 1000, 6),
        ZETTABYTES("zetta", 1000, 7),
        YOTTABYTES("yotta", 1000, 8),
        KIBIBYTES("kibi", 1024, 1),
        MEBIBYTES("mebi", 1024, 2),
        GIBIBYTES("gibi", 1024, 3),
        TEBIBYTES("tebi", 1024, 4),
        PEBIBYTES("pebi", 1024, 5),
        EXBIBYTES("exbi", 1024, 6),
        ZEBIBYTES("zebi", 1024, 7),
        YOBIBYTES("yobi", 1024, 8);
        
        private static Map<String, MemoryUnit> unitsMap;
        final BigInteger bytes;
        final int power;
        final int powerOf;
        final String prefix;

        static {
            unitsMap = makeUnitsMap();
        }

        private MemoryUnit(String str, int i, int i2) {
            this.prefix = str;
            this.powerOf = i;
            this.power = i2;
            this.bytes = BigInteger.valueOf((long) i).pow(i2);
        }

        private static Map<String, MemoryUnit> makeUnitsMap() {
            HashMap hashMap = new HashMap();
            for (MemoryUnit memoryUnit : values()) {
                hashMap.put(memoryUnit.prefix + "byte", memoryUnit);
                hashMap.put(memoryUnit.prefix + "bytes", memoryUnit);
                if (memoryUnit.prefix.length() == 0) {
                    hashMap.put("b", memoryUnit);
                    hashMap.put("B", memoryUnit);
                    hashMap.put("", memoryUnit);
                } else {
                    String substring = memoryUnit.prefix.substring(0, 1);
                    String upperCase = substring.toUpperCase();
                    int i = memoryUnit.powerOf;
                    if (i == 1024) {
                        hashMap.put(substring, memoryUnit);
                        hashMap.put(upperCase, memoryUnit);
                        hashMap.put(upperCase + "i", memoryUnit);
                        hashMap.put(upperCase + "iB", memoryUnit);
                    } else if (i != 1000) {
                        throw new RuntimeException("broken MemoryUnit enum");
                    } else if (memoryUnit.power == 1) {
                        hashMap.put(substring + "B", memoryUnit);
                    } else {
                        hashMap.put(upperCase + "B", memoryUnit);
                    }
                }
            }
            return hashMap;
        }

        static MemoryUnit parseUnit(String str) {
            return unitsMap.get(str);
        }
    }

    public static BigInteger parseBytes(String str, ConfigOrigin configOrigin, String str2) {
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        String units = getUnits(unicodeTrim);
        String unicodeTrim2 = ConfigImplUtil.unicodeTrim(unicodeTrim.substring(0, unicodeTrim.length() - units.length()));
        if (unicodeTrim2.length() != 0) {
            MemoryUnit parseUnit = MemoryUnit.parseUnit(units);
            if (parseUnit != null) {
                try {
                    if (unicodeTrim2.matches("[0-9]+")) {
                        return parseUnit.bytes.multiply(new BigInteger(unicodeTrim2));
                    }
                    return new BigDecimal(parseUnit.bytes).multiply(new BigDecimal(unicodeTrim2)).toBigInteger();
                } catch (NumberFormatException unused) {
                    throw new ConfigException.BadValue(configOrigin, str2, "Could not parse size-in-bytes number '" + unicodeTrim2 + "'");
                }
            } else {
                throw new ConfigException.BadValue(configOrigin, str2, "Could not parse size-in-bytes unit '" + units + "' (try k, K, kB, KiB, kilobytes, kibibytes)");
            }
        } else {
            throw new ConfigException.BadValue(configOrigin, str2, "No number in size-in-bytes value '" + str + "'");
        }
    }

    private AbstractConfigValue peekPath(Path path) {
        return root().peekPath(path);
    }

    private static void addProblem(List<ConfigException.ValidationProblem> list, Path path, ConfigOrigin configOrigin, String str) {
        list.add(new ConfigException.ValidationProblem(path.render(), configOrigin, str));
    }

    private static String getDesc(ConfigValueType configValueType) {
        return configValueType.name().toLowerCase();
    }

    private static String getDesc(ConfigValue configValue) {
        if (!(configValue instanceof AbstractConfigObject)) {
            return getDesc(configValue.valueType());
        }
        AbstractConfigObject abstractConfigObject = (AbstractConfigObject) configValue;
        if (abstractConfigObject.isEmpty()) {
            return getDesc(configValue.valueType());
        }
        return "object with keys " + abstractConfigObject.keySet();
    }

    private static void addMissing(List<ConfigException.ValidationProblem> list, String str, Path path, ConfigOrigin configOrigin) {
        addProblem(list, path, configOrigin, "No setting at '" + path.render() + "', expecting: " + str);
    }

    private static void addMissing(List<ConfigException.ValidationProblem> list, ConfigValue configValue, Path path, ConfigOrigin configOrigin) {
        addMissing(list, getDesc(configValue), path, configOrigin);
    }

    static void addMissing(List<ConfigException.ValidationProblem> list, ConfigValueType configValueType, Path path, ConfigOrigin configOrigin) {
        addMissing(list, getDesc(configValueType), path, configOrigin);
    }

    private static void addWrongType(List<ConfigException.ValidationProblem> list, String str, AbstractConfigValue abstractConfigValue, Path path) {
        SimpleConfigOrigin origin = abstractConfigValue.origin();
        addProblem(list, path, origin, "Wrong value type at '" + path.render() + "', expecting: " + str + " but got: " + getDesc((ConfigValue) abstractConfigValue));
    }

    private static void addWrongType(List<ConfigException.ValidationProblem> list, ConfigValue configValue, AbstractConfigValue abstractConfigValue, Path path) {
        addWrongType(list, getDesc(configValue), abstractConfigValue, path);
    }

    private static void addWrongType(List<ConfigException.ValidationProblem> list, ConfigValueType configValueType, AbstractConfigValue abstractConfigValue, Path path) {
        addWrongType(list, getDesc(configValueType), abstractConfigValue, path);
    }

    private static boolean couldBeNull(AbstractConfigValue abstractConfigValue) {
        return DefaultTransformer.transform(abstractConfigValue, ConfigValueType.NULL).valueType() == ConfigValueType.NULL;
    }

    private static boolean haveCompatibleTypes(ConfigValue configValue, AbstractConfigValue abstractConfigValue) {
        if (couldBeNull((AbstractConfigValue) configValue)) {
            return true;
        }
        return haveCompatibleTypes(configValue.valueType(), abstractConfigValue);
    }

    private static boolean haveCompatibleTypes(ConfigValueType configValueType, AbstractConfigValue abstractConfigValue) {
        if (configValueType == ConfigValueType.NULL || couldBeNull(abstractConfigValue)) {
            return true;
        }
        if (configValueType == ConfigValueType.OBJECT) {
            if (abstractConfigValue instanceof AbstractConfigObject) {
                return true;
            }
            return false;
        } else if (configValueType == ConfigValueType.LIST) {
            if ((abstractConfigValue instanceof SimpleConfigList) || (abstractConfigValue instanceof SimpleConfigObject)) {
                return true;
            }
            return false;
        } else if (configValueType == ConfigValueType.STRING || (abstractConfigValue instanceof ConfigString) || configValueType == abstractConfigValue.valueType()) {
            return true;
        } else {
            return false;
        }
    }

    private static void checkValidObject(Path path, AbstractConfigObject abstractConfigObject, AbstractConfigObject abstractConfigObject2, List<ConfigException.ValidationProblem> list) {
        Path path2;
        for (Map.Entry entry : abstractConfigObject.entrySet()) {
            String str = (String) entry.getKey();
            if (path != null) {
                path2 = Path.newKey(str).prepend(path);
            } else {
                path2 = Path.newKey(str);
            }
            AbstractConfigValue abstractConfigValue = abstractConfigObject2.get((Object) str);
            if (abstractConfigValue == null) {
                addMissing(list, (ConfigValue) entry.getValue(), path2, (ConfigOrigin) abstractConfigObject2.origin());
            } else {
                checkValid(path2, (ConfigValue) entry.getValue(), abstractConfigValue, list);
            }
        }
    }

    private static void checkListCompatibility(Path path, SimpleConfigList simpleConfigList, SimpleConfigList simpleConfigList2, List<ConfigException.ValidationProblem> list) {
        if (!simpleConfigList.isEmpty() && !simpleConfigList2.isEmpty()) {
            AbstractConfigValue abstractConfigValue = simpleConfigList.get(0);
            Iterator<ConfigValue> it = simpleConfigList2.iterator();
            while (it.hasNext()) {
                AbstractConfigValue abstractConfigValue2 = (AbstractConfigValue) it.next();
                if (!haveCompatibleTypes((ConfigValue) abstractConfigValue, abstractConfigValue2)) {
                    SimpleConfigOrigin origin = abstractConfigValue2.origin();
                    addProblem(list, path, origin, "List at '" + path.render() + "' contains wrong value type, expecting list of " + getDesc((ConfigValue) abstractConfigValue) + " but got element of type " + getDesc((ConfigValue) abstractConfigValue2));
                    return;
                }
            }
        }
    }

    static void checkValid(Path path, ConfigValueType configValueType, AbstractConfigValue abstractConfigValue, List<ConfigException.ValidationProblem> list) {
        if (!haveCompatibleTypes(configValueType, abstractConfigValue)) {
            addWrongType(list, configValueType, abstractConfigValue, path);
        } else if (configValueType == ConfigValueType.LIST && (abstractConfigValue instanceof SimpleConfigObject) && !(DefaultTransformer.transform(abstractConfigValue, ConfigValueType.LIST) instanceof SimpleConfigList)) {
            addWrongType(list, configValueType, abstractConfigValue, path);
        }
    }

    private static void checkValid(Path path, ConfigValue configValue, AbstractConfigValue abstractConfigValue, List<ConfigException.ValidationProblem> list) {
        if (!haveCompatibleTypes(configValue, abstractConfigValue)) {
            addWrongType(list, configValue, abstractConfigValue, path);
        } else if (!(configValue instanceof AbstractConfigObject) || !(abstractConfigValue instanceof AbstractConfigObject)) {
            boolean z = configValue instanceof SimpleConfigList;
            if (z && (abstractConfigValue instanceof SimpleConfigList)) {
                checkListCompatibility(path, (SimpleConfigList) configValue, (SimpleConfigList) abstractConfigValue, list);
            } else if (z && (abstractConfigValue instanceof SimpleConfigObject)) {
                SimpleConfigList simpleConfigList = (SimpleConfigList) configValue;
                AbstractConfigValue transform = DefaultTransformer.transform(abstractConfigValue, ConfigValueType.LIST);
                if (transform instanceof SimpleConfigList) {
                    checkListCompatibility(path, simpleConfigList, (SimpleConfigList) transform, list);
                } else {
                    addWrongType(list, configValue, abstractConfigValue, path);
                }
            }
        } else {
            checkValidObject(path, (AbstractConfigObject) configValue, (AbstractConfigObject) abstractConfigValue, list);
        }
    }

    public boolean isResolved() {
        return root().resolveStatus() == ResolveStatus.RESOLVED;
    }

    public void checkValid(Config config, String... strArr) {
        SimpleConfig simpleConfig = (SimpleConfig) config;
        if (simpleConfig.root().resolveStatus() != ResolveStatus.RESOLVED) {
            throw new ConfigException.BugOrBroken("do not call checkValid() with an unresolved reference config, call Config#resolve(), see Config#resolve() API docs");
        } else if (root().resolveStatus() == ResolveStatus.RESOLVED) {
            ArrayList arrayList = new ArrayList();
            if (strArr.length == 0) {
                checkValidObject((Path) null, simpleConfig.root(), root(), arrayList);
            } else {
                for (String newPath : strArr) {
                    Path newPath2 = Path.newPath(newPath);
                    AbstractConfigValue peekPath = simpleConfig.peekPath(newPath2);
                    if (peekPath != null) {
                        AbstractConfigValue peekPath2 = peekPath(newPath2);
                        if (peekPath2 != null) {
                            checkValid(newPath2, (ConfigValue) peekPath, peekPath2, (List<ConfigException.ValidationProblem>) arrayList);
                        } else {
                            addMissing((List<ConfigException.ValidationProblem>) arrayList, (ConfigValue) peekPath, newPath2, origin());
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                throw new ConfigException.ValidationFailed(arrayList);
            }
        } else {
            throw new ConfigException.NotResolved("need to Config#resolve() each config before using it, see the API docs for Config#resolve()");
        }
    }

    public SimpleConfig withOnlyPath(String str) {
        return new SimpleConfig(root().withOnlyPath(Path.newPath(str)));
    }

    public SimpleConfig withoutPath(String str) {
        return new SimpleConfig(root().withoutPath(Path.newPath(str)));
    }

    public SimpleConfig withValue(String str, ConfigValue configValue) {
        return new SimpleConfig(root().withValue(Path.newPath(str), configValue));
    }

    /* access modifiers changed from: package-private */
    public SimpleConfig atKey(ConfigOrigin configOrigin, String str) {
        return root().atKey(configOrigin, str);
    }

    public SimpleConfig atKey(String str) {
        return root().atKey(str);
    }

    public Config atPath(String str) {
        return root().atPath(str);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((Config) this);
    }
}
