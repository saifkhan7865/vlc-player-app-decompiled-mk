package org.bouncycastle.crypto.util;

import java.math.BigInteger;
import org.bouncycastle.util.BigIntegers;

public class RadixConverter {
    private static final int DEFAULT_POWERS_TO_CACHE = 10;
    private static final double LOG_LONG_MAX_VALUE = Math.log(9.223372036854776E18d);
    private final int digitsGroupLength;
    private final BigInteger[] digitsGroupSpacePowers;
    private final BigInteger digitsGroupSpaceSize;
    private final int radix;

    public RadixConverter(int i) {
        this(i, 10);
    }

    public RadixConverter(int i, int i2) {
        this.radix = i;
        int floor = (int) Math.floor(LOG_LONG_MAX_VALUE / Math.log((double) i));
        this.digitsGroupLength = floor;
        BigInteger pow = BigInteger.valueOf((long) i).pow(floor);
        this.digitsGroupSpaceSize = pow;
        this.digitsGroupSpacePowers = precomputeDigitsGroupPowers(i2, pow);
    }

    private long fromEncoding(int i, int i2, short[] sArr) {
        long j = 0;
        while (i < i2) {
            j = (j * ((long) this.radix)) + ((long) (sArr[i] & 65535));
            i++;
        }
        return j;
    }

    private BigInteger[] precomputeDigitsGroupPowers(int i, BigInteger bigInteger) {
        BigInteger[] bigIntegerArr = new BigInteger[i];
        BigInteger bigInteger2 = bigInteger;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerArr[i2] = bigInteger2;
            bigInteger2 = bigInteger2.multiply(bigInteger);
        }
        return bigIntegerArr;
    }

    private int toEncoding(long j, int i, short[] sArr) {
        int i2;
        for (int i3 = 0; i3 < this.digitsGroupLength && i >= 0; i3++) {
            if (j == 0) {
                i2 = i - 1;
                sArr[i] = 0;
            } else {
                i2 = i - 1;
                int i4 = this.radix;
                sArr[i] = (short) ((int) (j % ((long) i4)));
                j /= (long) i4;
            }
            i = i2;
        }
        if (j == 0) {
            return i;
        }
        throw new IllegalStateException("Failed to convert decimal number");
    }

    public BigInteger fromEncoding(short[] sArr) {
        BigInteger bigInteger = BigIntegers.ONE;
        int length = sArr.length;
        int i = length - this.digitsGroupLength;
        BigInteger bigInteger2 = null;
        int i2 = 0;
        while (true) {
            int i3 = this.digitsGroupLength;
            if (i <= (-i3)) {
                return bigInteger2;
            }
            if (i < 0) {
                i3 += i;
                i = 0;
            }
            BigInteger valueOf = BigInteger.valueOf(fromEncoding(i, Math.min(i3 + i, length), sArr));
            if (i2 == 0) {
                bigInteger2 = valueOf;
            } else {
                BigInteger[] bigIntegerArr = this.digitsGroupSpacePowers;
                bigInteger = i2 <= bigIntegerArr.length ? bigIntegerArr[i2 - 1] : bigInteger.multiply(this.digitsGroupSpaceSize);
                bigInteger2 = bigInteger2.add(valueOf.multiply(bigInteger));
            }
            i2++;
            i -= this.digitsGroupLength;
        }
    }

    public int getDigitsGroupLength() {
        return this.digitsGroupLength;
    }

    public int getRadix() {
        return this.radix;
    }

    public void toEncoding(BigInteger bigInteger, int i, short[] sArr) {
        if (bigInteger.signum() >= 0) {
            int i2 = i - 1;
            do {
                if (bigInteger.equals(BigInteger.ZERO)) {
                    sArr[i2] = 0;
                    i2--;
                    continue;
                } else {
                    BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(this.digitsGroupSpaceSize);
                    BigInteger bigInteger2 = divideAndRemainder[0];
                    i2 = toEncoding(divideAndRemainder[1].longValue(), i2, sArr);
                    bigInteger = bigInteger2;
                    continue;
                }
            } while (i2 >= 0);
            if (bigInteger.signum() != 0) {
                throw new IllegalArgumentException();
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
