package org.bouncycastle.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.WeakHashMap;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;

public final class BigIntegers {
    private static final int MAX_ITERATIONS = 1000;
    private static final int MAX_SMALL = BigInteger.valueOf(743).bitLength();
    public static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger SMALL_PRIMES_PRODUCT = new BigInteger("8138e8a0fcf3a4e84a771d40fd305d7f4aa59306d7251de54d98af8fe95729a1f73d893fa424cd2edc8636a6c3285e022b0e3866a565ae8108eed8591cd4fe8d2ce86165a978d719ebf647f362d33fca29cd179fb42401cbaf3df0c614056f9c8f3cfd51e474afb6bc6974f78db8aba8e9e517fded658591ab7502bd41849462f", 16);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger ZERO = BigInteger.valueOf(0);

    public static class Cache {
        private final BigInteger[] preserve = new BigInteger[8];
        private int preserveCounter = 0;
        private final Map<BigInteger, Boolean> values = new WeakHashMap();

        public synchronized void add(BigInteger bigInteger) {
            this.values.put(bigInteger, Boolean.TRUE);
            BigInteger[] bigIntegerArr = this.preserve;
            int i = this.preserveCounter;
            bigIntegerArr[i] = bigInteger;
            this.preserveCounter = (i + 1) % bigIntegerArr.length;
        }

        public synchronized void clear() {
            this.values.clear();
            int i = 0;
            while (true) {
                BigInteger[] bigIntegerArr = this.preserve;
                if (i != bigIntegerArr.length) {
                    bigIntegerArr[i] = null;
                    i++;
                }
            }
        }

        public synchronized boolean contains(BigInteger bigInteger) {
            return this.values.containsKey(bigInteger);
        }

        public synchronized int size() {
            return this.values.size();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r3.length != 1) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void asUnsignedByteArray(java.math.BigInteger r3, byte[] r4, int r5, int r6) {
        /*
            byte[] r3 = r3.toByteArray()
            int r0 = r3.length
            r1 = 0
            if (r0 != r6) goto L_0x000c
            java.lang.System.arraycopy(r3, r1, r4, r5, r6)
            return
        L_0x000c:
            byte r0 = r3[r1]
            if (r0 != 0) goto L_0x0015
            int r0 = r3.length
            r2 = 1
            if (r0 == r2) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            r2 = 0
        L_0x0016:
            int r0 = r3.length
            int r0 = r0 - r2
            if (r0 > r6) goto L_0x0023
            int r6 = r6 - r0
            int r6 = r6 + r5
            org.bouncycastle.util.Arrays.fill((byte[]) r4, (int) r5, (int) r6, (byte) r1)
            java.lang.System.arraycopy(r3, r2, r4, r6, r0)
            return
        L_0x0023:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "standard length exceeded for value"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.util.BigIntegers.asUnsignedByteArray(java.math.BigInteger, byte[], int, int):void");
    }

    public static byte[] asUnsignedByteArray(int i, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i) {
            return byteArray;
        }
        int i2 = 0;
        if (byteArray[0] == 0 && byteArray.length != 1) {
            i2 = 1;
        }
        int length = byteArray.length - i2;
        if (length <= i) {
            byte[] bArr = new byte[i];
            System.arraycopy(byteArray, i2, bArr, i - length, length);
            return bArr;
        }
        throw new IllegalArgumentException("standard length exceeded for value");
    }

    public static byte[] asUnsignedByteArray(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] != 0 || byteArray.length == 1) {
            return byteArray;
        }
        int length = byteArray.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 1, bArr, 0, length);
        return bArr;
    }

    public static byte byteValueExact(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 7) {
            return bigInteger.byteValue();
        }
        throw new ArithmeticException("BigInteger out of int range");
    }

    private static byte[] createRandom(int i, SecureRandom secureRandom) throws IllegalArgumentException {
        if (i >= 1) {
            int i2 = (i + 7) / 8;
            byte[] bArr = new byte[i2];
            secureRandom.nextBytes(bArr);
            bArr[0] = (byte) (bArr[0] & ((byte) (255 >>> ((i2 * 8) - i))));
            return bArr;
        }
        throw new IllegalArgumentException("bitLength must be at least 1");
    }

    public static BigInteger createRandomBigInteger(int i, SecureRandom secureRandom) {
        return new BigInteger(1, createRandom(i, secureRandom));
    }

    public static BigInteger createRandomInRange(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger createRandomBigInteger;
        int compareTo = bigInteger.compareTo(bigInteger2);
        if (compareTo < 0) {
            if (bigInteger.bitLength() > bigInteger2.bitLength() / 2) {
                createRandomBigInteger = createRandomInRange(ZERO, bigInteger2.subtract(bigInteger), secureRandom);
            } else {
                for (int i = 0; i < 1000; i++) {
                    BigInteger createRandomBigInteger2 = createRandomBigInteger(bigInteger2.bitLength(), secureRandom);
                    if (createRandomBigInteger2.compareTo(bigInteger) >= 0 && createRandomBigInteger2.compareTo(bigInteger2) <= 0) {
                        return createRandomBigInteger2;
                    }
                }
                createRandomBigInteger = createRandomBigInteger(bigInteger2.subtract(bigInteger).bitLength() - 1, secureRandom);
            }
            return createRandomBigInteger.add(bigInteger);
        } else if (compareTo <= 0) {
            return bigInteger;
        } else {
            throw new IllegalArgumentException("'min' may not be greater than 'max'");
        }
    }

    public static BigInteger createRandomPrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger;
        if (i < 2) {
            throw new IllegalArgumentException("bitLength < 2");
        } else if (i == 2) {
            return secureRandom.nextInt() < 0 ? TWO : THREE;
        } else {
            do {
                byte[] createRandom = createRandom(i, secureRandom);
                createRandom[0] = (byte) (((byte) (1 << (7 - ((createRandom.length * 8) - i)))) | createRandom[0]);
                int length = createRandom.length - 1;
                createRandom[length] = (byte) (createRandom[length] | 1);
                bigInteger = new BigInteger(1, createRandom);
                if (i > MAX_SMALL) {
                    while (!bigInteger.gcd(SMALL_PRIMES_PRODUCT).equals(ONE)) {
                        bigInteger = bigInteger.add(TWO);
                    }
                }
            } while (!bigInteger.isProbablePrime(i2));
            return bigInteger;
        }
    }

    public static BigInteger fromUnsignedByteArray(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static BigInteger fromUnsignedByteArray(byte[] bArr, int i, int i2) {
        if (!(i == 0 && i2 == bArr.length)) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            bArr = bArr2;
        }
        return new BigInteger(1, bArr);
    }

    public static int getUnsignedByteLength(BigInteger bigInteger) {
        if (bigInteger.equals(ZERO)) {
            return 1;
        }
        return (bigInteger.bitLength() + 7) / 8;
    }

    public static int intValueExact(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 31) {
            return bigInteger.intValue();
        }
        throw new ArithmeticException("BigInteger out of int range");
    }

    public static long longValueExact(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 63) {
            return bigInteger.longValue();
        }
        throw new ArithmeticException("BigInteger out of long range");
    }

    public static BigInteger modOddInverse(BigInteger bigInteger, BigInteger bigInteger2) {
        if (!bigInteger.testBit(0)) {
            throw new IllegalArgumentException("'M' must be odd");
        } else if (bigInteger.signum() == 1) {
            if (bigInteger2.signum() < 0 || bigInteger2.compareTo(bigInteger) >= 0) {
                bigInteger2 = bigInteger2.mod(bigInteger);
            }
            int bitLength = bigInteger.bitLength();
            int[] fromBigInteger = Nat.fromBigInteger(bitLength, bigInteger);
            int[] fromBigInteger2 = Nat.fromBigInteger(bitLength, bigInteger2);
            int length = fromBigInteger.length;
            int[] create = Nat.create(length);
            if (Mod.modOddInverse(fromBigInteger, fromBigInteger2, create) != 0) {
                return Nat.toBigInteger(length, create);
            }
            throw new ArithmeticException("BigInteger not invertible.");
        } else {
            throw new ArithmeticException("BigInteger: modulus not positive");
        }
    }

    public static BigInteger modOddInverseVar(BigInteger bigInteger, BigInteger bigInteger2) {
        if (!bigInteger.testBit(0)) {
            throw new IllegalArgumentException("'M' must be odd");
        } else if (bigInteger.signum() == 1) {
            BigInteger bigInteger3 = ONE;
            if (bigInteger.equals(bigInteger3)) {
                return ZERO;
            }
            if (bigInteger2.signum() < 0 || bigInteger2.compareTo(bigInteger) >= 0) {
                bigInteger2 = bigInteger2.mod(bigInteger);
            }
            if (bigInteger2.equals(bigInteger3)) {
                return bigInteger3;
            }
            int bitLength = bigInteger.bitLength();
            int[] fromBigInteger = Nat.fromBigInteger(bitLength, bigInteger);
            int[] fromBigInteger2 = Nat.fromBigInteger(bitLength, bigInteger2);
            int length = fromBigInteger.length;
            int[] create = Nat.create(length);
            if (Mod.modOddInverseVar(fromBigInteger, fromBigInteger2, create)) {
                return Nat.toBigInteger(length, create);
            }
            throw new ArithmeticException("BigInteger not invertible.");
        } else {
            throw new ArithmeticException("BigInteger: modulus not positive");
        }
    }

    public static short shortValueExact(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 15) {
            return bigInteger.shortValue();
        }
        throw new ArithmeticException("BigInteger out of int range");
    }
}
