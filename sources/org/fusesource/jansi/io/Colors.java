package org.fusesource.jansi.io;

import androidx.core.view.MotionEventCompat;

public class Colors {
    public static final int[] DEFAULT_COLORS_256 = {0, 8388608, 32768, 8421376, 128, 8388736, 32896, 12632256, 8421504, 16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16776960, 255, 16711935, 65535, 16777215, 0, 95, 135, 175, 215, 255, 24320, 24415, 24455, 24495, 24535, 24575, 34560, 34655, 34695, 34735, 34775, 34815, 44800, 44895, 44935, 44975, 45015, 45055, 55040, 55135, 55175, 55215, 55255, 55295, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 65375, 65415, 65455, 65495, 65535, 6225920, 6226015, 6226055, 6226095, 6226135, 6226175, 6250240, 6250335, 6250375, 6250415, 6250455, 6250495, 6260480, 6260575, 6260615, 6260655, 6260695, 6260735, 6270720, 6270815, 6270855, 6270895, 6270935, 6270975, 6280960, 6281055, 6281095, 6281135, 6281175, 6281215, 6291200, 6291295, 6291335, 6291375, 6291415, 6291455, 8847360, 8847455, 8847495, 8847535, 8847575, 8847615, 8871680, 8871775, 8871815, 8871855, 8871895, 8871935, 8881920, 8882015, 8882055, 8882095, 8882135, 8882175, 8892160, 8892255, 8892295, 8892335, 8892375, 8892415, 8902400, 8902495, 8902535, 8902575, 8902615, 8902655, 8912640, 8912735, 8912775, 8912815, 8912855, 8912895, 11468800, 11468895, 11468935, 11468975, 11469015, 11469055, 11493120, 11493215, 11493255, 11493295, 11493335, 11493375, 11503360, 11503455, 11503495, 11503535, 11503575, 11503615, 11513600, 11513695, 11513735, 11513775, 11513815, 11513855, 11523840, 11523935, 11523975, 11524015, 11524055, 11524095, 11534080, 11534175, 11534215, 11534255, 11534295, 11534335, 14090240, 14090335, 14090375, 14090415, 14090455, 14090495, 14114560, 14114655, 14114695, 14114735, 14114775, 14114815, 14124800, 14124895, 14124935, 14124975, 14125015, 14125055, 14135040, 14135135, 14135175, 14135215, 14135255, 14135295, 14145280, 14145375, 14145415, 14145455, 14145495, 14145535, 14155520, 14155615, 14155655, 14155695, 14155735, 14155775, 16711680, 16711775, 16711815, 16711855, 16711895, 16711935, 16736000, 16736095, 16736135, 16736175, 16736215, 16736255, 16746240, 16746335, 16746375, 16746415, 16746455, 16746495, 16756480, 16756575, 16756615, 16756655, 16756695, 16756735, 16766720, 16766815, 16766855, 16766895, 16766935, 16766975, 16776960, 16777055, 16777095, 16777135, 16777175, 16777215, 526344, 1184274, 1842204, 2500134, 3158064, 3815994, 4473924, 5131854, 5789784, 6447714, 7105644, 7763574, 8421504, 9079434, 9737364, 10395294, 11053224, 11711154, 12369084, 13027014, 13684944, 14342874, 15000804, 15658734};
    private static final double epsilon = 0.008856451679035631d;
    private static final double kappa = 903.2962962962963d;

    private static double sqr(double d) {
        return d * d;
    }

    public static int roundColor(int i, int i2) {
        if (i < i2) {
            return i;
        }
        int[] iArr = DEFAULT_COLORS_256;
        return roundColor(iArr[i], iArr, i2);
    }

    public static int roundRgbColor(int i, int i2, int i3, int i4) {
        return roundColor((i << 16) + (i2 << 8) + i3, DEFAULT_COLORS_256, i4);
    }

    private static int roundColor(int i, int[] iArr, int i2) {
        double d = 2.147483647E9d;
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < i2; i4++) {
            double cie76 = cie76(i, iArr[i4]);
            if (cie76 <= d) {
                i3 = i4;
                d = cie76;
            }
        }
        return i3;
    }

    private static double cie76(int i, int i2) {
        return scalar(rgb2cielab(i), rgb2cielab(i2));
    }

    private static double scalar(double[] dArr, double[] dArr2) {
        return sqr(dArr[0] - dArr2[0]) + sqr(dArr[1] - dArr2[1]) + sqr(dArr[2] - dArr2[2]);
    }

    private static double[] rgb(int i) {
        double d = (double) ((i >> 16) & 255);
        Double.isNaN(d);
        double d2 = (double) ((i >> 8) & 255);
        Double.isNaN(d2);
        double d3 = (double) (i & 255);
        Double.isNaN(d3);
        return new double[]{d / 255.0d, d2 / 255.0d, d3 / 255.0d};
    }

    private static double[] rgb2cielab(int i) {
        return rgb2cielab(rgb(i));
    }

    private static double[] rgb2cielab(double[] dArr) {
        return xyz2lab(rgb2xyz(dArr));
    }

    private static double[] rgb2xyz(double[] dArr) {
        double pivotRgb = pivotRgb(dArr[0]);
        double pivotRgb2 = pivotRgb(dArr[1]);
        double pivotRgb3 = pivotRgb(dArr[2]);
        return new double[]{(0.4124564d * pivotRgb) + (0.3575761d * pivotRgb2) + (0.1804375d * pivotRgb3), (0.2126729d * pivotRgb) + (0.7151522d * pivotRgb2) + (0.072175d * pivotRgb3), (pivotRgb * 0.0193339d) + (pivotRgb2 * 0.119192d) + (pivotRgb3 * 0.9503041d)};
    }

    private static double pivotRgb(double d) {
        return d > 0.04045d ? Math.pow((d + 0.055d) / 1.055d, 2.4d) : d / 12.92d;
    }

    private static double[] xyz2lab(double[] dArr) {
        double pivotXyz = pivotXyz(dArr[0]);
        double pivotXyz2 = pivotXyz(dArr[1]);
        return new double[]{(116.0d * pivotXyz2) - 16.0d, (pivotXyz - pivotXyz2) * 500.0d, (pivotXyz2 - pivotXyz(dArr[2])) * 200.0d};
    }

    private static double pivotXyz(double d) {
        return d > epsilon ? Math.cbrt(d) : ((d * kappa) + 16.0d) / 116.0d;
    }
}
