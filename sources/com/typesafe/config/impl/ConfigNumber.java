package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import java.io.ObjectStreamException;
import java.io.Serializable;

abstract class ConfigNumber extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;
    protected final String originalText;

    /* access modifiers changed from: protected */
    public abstract double doubleValue();

    /* access modifiers changed from: protected */
    public abstract long longValue();

    public abstract Number unwrapped();

    protected ConfigNumber(ConfigOrigin configOrigin, String str) {
        super(configOrigin);
        this.originalText = str;
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        return this.originalText;
    }

    /* access modifiers changed from: package-private */
    public int intValueRangeChecked(String str) {
        long longValue = longValue();
        if (longValue >= -2147483648L && longValue <= 2147483647L) {
            return (int) longValue;
        }
        SimpleConfigOrigin origin = origin();
        throw new ConfigException.WrongType(origin, str, "32-bit integer", "out-of-range value " + longValue);
    }

    private boolean isWhole() {
        return ((double) longValue()) == doubleValue();
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigNumber;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigNumber) || !canEqual(obj)) {
            return false;
        }
        ConfigNumber configNumber = (ConfigNumber) obj;
        if (isWhole()) {
            if (!configNumber.isWhole() || longValue() != configNumber.longValue()) {
                return false;
            }
            return true;
        } else if (configNumber.isWhole() || doubleValue() != configNumber.doubleValue()) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        long j;
        if (isWhole()) {
            j = longValue();
        } else {
            j = Double.doubleToLongBits(doubleValue());
        }
        return (int) (j ^ (j >>> 32));
    }

    static ConfigNumber newNumber(ConfigOrigin configOrigin, long j, String str) {
        if (j > 2147483647L || j < -2147483648L) {
            return new ConfigLong(configOrigin, j, str);
        }
        return new ConfigInt(configOrigin, (int) j, str);
    }

    static ConfigNumber newNumber(ConfigOrigin configOrigin, double d, String str) {
        long j = (long) d;
        if (((double) j) == d) {
            return newNumber(configOrigin, j, str);
        }
        return new ConfigDouble(configOrigin, d, str);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
