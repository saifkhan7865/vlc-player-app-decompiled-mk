package com.typesafe.config;

import j$.time.Duration;
import j$.time.Period;
import j$.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Config extends ConfigMergeable {

    /* renamed from: com.typesafe.config.Config$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    Config atKey(String str);

    Config atPath(String str);

    void checkValid(Config config, String... strArr);

    Set<Map.Entry<String, ConfigValue>> entrySet();

    Object getAnyRef(String str);

    List<? extends Object> getAnyRefList(String str);

    boolean getBoolean(String str);

    List<Boolean> getBooleanList(String str);

    Long getBytes(String str);

    List<Long> getBytesList(String str);

    Config getConfig(String str);

    List<? extends Config> getConfigList(String str);

    double getDouble(String str);

    List<Double> getDoubleList(String str);

    long getDuration(String str, TimeUnit timeUnit);

    Duration getDuration(String str);

    List<Duration> getDurationList(String str);

    List<Long> getDurationList(String str, TimeUnit timeUnit);

    <T extends Enum<T>> T getEnum(Class<T> cls, String str);

    <T extends Enum<T>> List<T> getEnumList(Class<T> cls, String str);

    int getInt(String str);

    List<Integer> getIntList(String str);

    boolean getIsNull(String str);

    ConfigList getList(String str);

    long getLong(String str);

    List<Long> getLongList(String str);

    ConfigMemorySize getMemorySize(String str);

    List<ConfigMemorySize> getMemorySizeList(String str);

    @Deprecated
    Long getMilliseconds(String str);

    @Deprecated
    List<Long> getMillisecondsList(String str);

    @Deprecated
    Long getNanoseconds(String str);

    @Deprecated
    List<Long> getNanosecondsList(String str);

    Number getNumber(String str);

    List<Number> getNumberList(String str);

    ConfigObject getObject(String str);

    List<? extends ConfigObject> getObjectList(String str);

    Period getPeriod(String str);

    String getString(String str);

    List<String> getStringList(String str);

    TemporalAmount getTemporal(String str);

    ConfigValue getValue(String str);

    boolean hasPath(String str);

    boolean hasPathOrNull(String str);

    boolean isEmpty();

    boolean isResolved();

    ConfigOrigin origin();

    Config resolve();

    Config resolve(ConfigResolveOptions configResolveOptions);

    Config resolveWith(Config config);

    Config resolveWith(Config config, ConfigResolveOptions configResolveOptions);

    ConfigObject root();

    Config withFallback(ConfigMergeable configMergeable);

    Config withOnlyPath(String str);

    Config withValue(String str, ConfigValue configValue);

    Config withoutPath(String str);
}
