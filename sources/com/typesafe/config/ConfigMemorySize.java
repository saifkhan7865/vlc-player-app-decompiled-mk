package com.typesafe.config;

import java.math.BigInteger;

public final class ConfigMemorySize {
    private BigInteger bytes;

    private ConfigMemorySize(BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            this.bytes = bigInteger;
            return;
        }
        throw new IllegalArgumentException("Attempt to construct ConfigMemorySize with negative number: " + bigInteger);
    }

    public static ConfigMemorySize ofBytes(BigInteger bigInteger) {
        return new ConfigMemorySize(bigInteger);
    }

    public static ConfigMemorySize ofBytes(long j) {
        return new ConfigMemorySize(BigInteger.valueOf(j));
    }

    public long toBytes() {
        if (this.bytes.bitLength() < 64) {
            return this.bytes.longValue();
        }
        throw new IllegalArgumentException("size-in-bytes value is out of range for a 64-bit long: '" + this.bytes + "'");
    }

    public BigInteger toBytesBigInteger() {
        return this.bytes;
    }

    public String toString() {
        return "ConfigMemorySize(" + this.bytes + ")";
    }

    public boolean equals(Object obj) {
        if (obj instanceof ConfigMemorySize) {
            return ((ConfigMemorySize) obj).bytes.equals(this.bytes);
        }
        return false;
    }

    public int hashCode() {
        return this.bytes.hashCode();
    }
}
