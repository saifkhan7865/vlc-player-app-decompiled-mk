package com.google.android.material.color.utilities;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    private QuantizerWsmeans() {
    }

    private static final class Distance implements Comparable<Distance> {
        double distance = -1.0d;
        int index = -1;

        Distance() {
        }

        public int compareTo(Distance distance2) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(distance2.distance));
        }
    }

    public static Map<Integer, Integer> quantize(int[] iArr, int[] iArr2, int i) {
        int[] iArr3;
        int i2;
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        Random random = new Random(272008);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        double[][] dArr = new double[iArr4.length][];
        int[] iArr6 = new int[iArr4.length];
        PointProviderLab pointProviderLab = new PointProviderLab();
        int i3 = 0;
        for (int i4 : iArr4) {
            Integer num = (Integer) linkedHashMap.get(Integer.valueOf(i4));
            if (num == null) {
                dArr[i3] = pointProviderLab.fromInt(i4);
                iArr6[i3] = i4;
                i3++;
                linkedHashMap.put(Integer.valueOf(i4), 1);
            } else {
                linkedHashMap.put(Integer.valueOf(i4), Integer.valueOf(num.intValue() + 1));
            }
        }
        int[] iArr7 = new int[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            iArr7[i5] = ((Integer) linkedHashMap.get(Integer.valueOf(iArr6[i5]))).intValue();
        }
        int min = Math.min(i, i3);
        if (iArr5.length != 0) {
            min = Math.min(min, iArr5.length);
        }
        double[][] dArr2 = new double[min][];
        int i6 = 0;
        for (int i7 = 0; i7 < iArr5.length; i7++) {
            dArr2[i7] = pointProviderLab.fromInt(iArr5[i7]);
            i6++;
        }
        int i8 = min - i6;
        if (i8 > 0) {
            for (int i9 = 0; i9 < i8; i9++) {
            }
        }
        int[] iArr8 = new int[i3];
        for (int i10 = 0; i10 < i3; i10++) {
            iArr8[i10] = random.nextInt(min);
        }
        int[][] iArr9 = new int[min][];
        for (int i11 = 0; i11 < min; i11++) {
            iArr9[i11] = new int[min];
        }
        Distance[][] distanceArr = new Distance[min][];
        for (int i12 = 0; i12 < min; i12++) {
            distanceArr[i12] = new Distance[min];
            for (int i13 = 0; i13 < min; i13++) {
                distanceArr[i12][i13] = new Distance();
            }
        }
        int[] iArr10 = new int[min];
        int i14 = 0;
        while (true) {
            if (i14 >= 10) {
                iArr3 = iArr10;
                break;
            }
            int i15 = 0;
            while (i15 < min) {
                int i16 = i15 + 1;
                int i17 = i16;
                while (i17 < min) {
                    int[] iArr11 = iArr10;
                    double distance = pointProviderLab.distance(dArr2[i15], dArr2[i17]);
                    distanceArr[i17][i15].distance = distance;
                    distanceArr[i17][i15].index = i15;
                    distanceArr[i15][i17].distance = distance;
                    distanceArr[i15][i17].index = i17;
                    i17++;
                    iArr10 = iArr11;
                }
                int[] iArr12 = iArr10;
                Arrays.sort(distanceArr[i15]);
                for (int i18 = 0; i18 < min; i18++) {
                    iArr9[i15][i18] = distanceArr[i15][i18].index;
                }
                iArr10 = iArr12;
                i15 = i16;
            }
            int[] iArr13 = iArr10;
            int i19 = 0;
            int i20 = 0;
            while (i19 < i3) {
                double[] dArr3 = dArr[i19];
                int i21 = iArr8[i19];
                double distance2 = pointProviderLab.distance(dArr3, dArr2[i21]);
                int[][] iArr14 = iArr9;
                int[] iArr15 = iArr7;
                double d = distance2;
                int i22 = -1;
                int i23 = 0;
                while (i23 < min) {
                    Distance[][] distanceArr2 = distanceArr;
                    int i24 = i3;
                    if (distanceArr[i21][i23].distance < 4.0d * distance2) {
                        double distance3 = pointProviderLab.distance(dArr3, dArr2[i23]);
                        if (distance3 < d) {
                            i22 = i23;
                            d = distance3;
                        }
                    }
                    i23++;
                    i3 = i24;
                    distanceArr = distanceArr2;
                }
                Distance[][] distanceArr3 = distanceArr;
                int i25 = i3;
                if (i22 != -1 && Math.abs(Math.sqrt(d) - Math.sqrt(distance2)) > 3.0d) {
                    i20++;
                    iArr8[i19] = i22;
                }
                i19++;
                iArr9 = iArr14;
                iArr7 = iArr15;
                i3 = i25;
                distanceArr = distanceArr3;
            }
            int[] iArr16 = iArr7;
            int[][] iArr17 = iArr9;
            Distance[][] distanceArr4 = distanceArr;
            int i26 = i3;
            if (i20 == 0 && i14 != 0) {
                iArr3 = iArr13;
                break;
            }
            double[] dArr4 = new double[min];
            double[] dArr5 = new double[min];
            double[] dArr6 = new double[min];
            int[] iArr18 = iArr13;
            char c = 0;
            Arrays.fill(iArr18, 0);
            int i27 = 0;
            while (true) {
                i2 = i26;
                if (i27 >= i2) {
                    break;
                }
                int i28 = iArr8[i27];
                double[] dArr7 = dArr[i27];
                int i29 = iArr16[i27];
                iArr18[i28] = iArr18[i28] + i29;
                double d2 = dArr4[i28];
                double d3 = dArr7[c];
                double d4 = (double) i29;
                Double.isNaN(d4);
                dArr4[i28] = d2 + (d3 * d4);
                double d5 = dArr5[i28];
                double d6 = dArr7[1];
                Double.isNaN(d4);
                dArr5[i28] = d5 + (d6 * d4);
                double d7 = dArr6[i28];
                double d8 = dArr7[2];
                Double.isNaN(d4);
                dArr6[i28] = d7 + (d8 * d4);
                i27++;
                i14 = i14;
                i26 = i2;
                c = 0;
            }
            int i30 = i14;
            for (int i31 = 0; i31 < min; i31++) {
                int i32 = iArr18[i31];
                if (i32 == 0) {
                    dArr2[i31] = new double[]{0.0d, 0.0d, 0.0d};
                } else {
                    double d9 = dArr4[i31];
                    double d10 = (double) i32;
                    Double.isNaN(d10);
                    double d11 = d9 / d10;
                    double d12 = dArr5[i31];
                    Double.isNaN(d10);
                    double d13 = d12 / d10;
                    double d14 = dArr6[i31];
                    Double.isNaN(d10);
                    double d15 = d14 / d10;
                    double[] dArr8 = dArr2[i31];
                    dArr8[0] = d11;
                    dArr8[1] = d13;
                    dArr8[2] = d15;
                }
            }
            iArr9 = iArr17;
            i14 = i30 + 1;
            iArr10 = iArr18;
            i3 = i2;
            iArr7 = iArr16;
            distanceArr = distanceArr4;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (int i33 = 0; i33 < min; i33++) {
            int i34 = iArr3[i33];
            if (i34 != 0) {
                int i35 = pointProviderLab.toInt(dArr2[i33]);
                if (!linkedHashMap2.containsKey(Integer.valueOf(i35))) {
                    linkedHashMap2.put(Integer.valueOf(i35), Integer.valueOf(i34));
                }
            }
        }
        return linkedHashMap2;
    }
}
