package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

public class CamColor {
    private static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    private static final float DE_MAX = 1.0f;
    private static final float DL_MAX = 0.2f;
    private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;
    private final float mJ;
    private final float mJstar;
    private final float mM;
    private final float mQ;
    private final float mS;

    /* access modifiers changed from: package-private */
    public float getHue() {
        return this.mHue;
    }

    /* access modifiers changed from: package-private */
    public float getChroma() {
        return this.mChroma;
    }

    /* access modifiers changed from: package-private */
    public float getJ() {
        return this.mJ;
    }

    /* access modifiers changed from: package-private */
    public float getQ() {
        return this.mQ;
    }

    /* access modifiers changed from: package-private */
    public float getM() {
        return this.mM;
    }

    /* access modifiers changed from: package-private */
    public float getS() {
        return this.mS;
    }

    /* access modifiers changed from: package-private */
    public float getJStar() {
        return this.mJstar;
    }

    /* access modifiers changed from: package-private */
    public float getAStar() {
        return this.mAstar;
    }

    /* access modifiers changed from: package-private */
    public float getBStar() {
        return this.mBstar;
    }

    CamColor(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.mHue = f;
        this.mChroma = f2;
        this.mJ = f3;
        this.mQ = f4;
        this.mM = f5;
        this.mS = f6;
        this.mJstar = f7;
        this.mAstar = f8;
        this.mBstar = f9;
    }

    public static int toColor(float f, float f2, float f3) {
        return toColor(f, f2, f3, ViewingConditions.DEFAULT);
    }

    static CamColor fromColor(int i) {
        float[] fArr = new float[7];
        float[] fArr2 = new float[3];
        fromColorInViewingConditions(i, ViewingConditions.DEFAULT, fArr, fArr2);
        return new CamColor(fArr2[0], fArr2[1], fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6]);
    }

    public static void getM3HCTfromColor(int i, float[] fArr) {
        fromColorInViewingConditions(i, ViewingConditions.DEFAULT, (float[]) null, fArr);
        fArr[2] = CamUtils.lStarFromInt(i);
    }

    static void fromColorInViewingConditions(int i, ViewingConditions viewingConditions, float[] fArr, float[] fArr2) {
        float[] fArr3 = fArr2;
        CamUtils.xyzFromInt(i, fArr3);
        float[][] fArr4 = CamUtils.XYZ_TO_CAM16RGB;
        float f = fArr3[0];
        float[] fArr5 = fArr4[0];
        float f2 = fArr3[1];
        float f3 = fArr3[2];
        float f4 = (fArr5[0] * f) + (fArr5[1] * f2) + (fArr5[2] * f3);
        float[] fArr6 = fArr4[1];
        float f5 = (fArr6[0] * f) + (fArr6[1] * f2) + (fArr6[2] * f3);
        float[] fArr7 = fArr4[2];
        float f6 = viewingConditions.getRgbD()[0] * f4;
        float f7 = viewingConditions.getRgbD()[1] * f5;
        float f8 = viewingConditions.getRgbD()[2] * ((f * fArr7[0]) + (f2 * fArr7[1]) + (f3 * fArr7[2]));
        double fl = (double) (viewingConditions.getFl() * Math.abs(f6));
        Double.isNaN(fl);
        float pow = (float) Math.pow(fl / 100.0d, 0.42d);
        double fl2 = (double) (viewingConditions.getFl() * Math.abs(f7));
        Double.isNaN(fl2);
        float pow2 = (float) Math.pow(fl2 / 100.0d, 0.42d);
        double fl3 = (double) (viewingConditions.getFl() * Math.abs(f8));
        Double.isNaN(fl3);
        float pow3 = (float) Math.pow(fl3 / 100.0d, 0.42d);
        float signum = ((Math.signum(f6) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f7) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f8) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d = (double) signum;
        Double.isNaN(d);
        double d2 = (double) signum2;
        Double.isNaN(d2);
        double d3 = (double) signum3;
        Double.isNaN(d3);
        float f9 = ((float) (((d * 11.0d) + (d2 * -12.0d)) + d3)) / 11.0f;
        double d4 = (double) (signum + signum2);
        Double.isNaN(d3);
        Double.isNaN(d4);
        float f10 = ((float) (d4 - (d3 * 2.0d))) / 9.0f;
        float f11 = signum2 * 20.0f;
        float f12 = (((signum * 20.0f) + f11) + (21.0f * signum3)) / 20.0f;
        float f13 = (((signum * 40.0f) + f11) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2((double) f10, (double) f9)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f14 = (3.1415927f * atan2) / 180.0f;
        float f15 = f12;
        float f16 = f10;
        float pow4 = ((float) Math.pow((double) ((f13 * viewingConditions.getNbb()) / viewingConditions.getAw()), (double) (viewingConditions.getC() * viewingConditions.getZ()))) * 100.0f;
        float c = (4.0f / viewingConditions.getC()) * ((float) Math.sqrt((double) (pow4 / 100.0f))) * (viewingConditions.getAw() + 4.0f) * viewingConditions.getFlRoot();
        double d5 = (double) (((double) atan2) < 20.14d ? 360.0f + atan2 : atan2);
        Double.isNaN(d5);
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d)) * ((float) Math.pow((double) ((((((((float) (Math.cos(((d5 * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.getNc()) * viewingConditions.getNcb()) * ((float) Math.sqrt((double) ((f9 * f9) + (f16 * f16))))) / (f15 + 0.305f)), 0.9d));
        double d6 = (double) pow4;
        Double.isNaN(d6);
        float sqrt = ((float) Math.sqrt(d6 / 100.0d)) * pow5;
        float flRoot = viewingConditions.getFlRoot() * sqrt;
        float sqrt2 = ((float) Math.sqrt((double) ((pow5 * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float f17 = (1.7f * pow4) / ((0.007f * pow4) + 1.0f);
        float log = ((float) Math.log((double) ((0.0228f * flRoot) + 1.0f))) * 43.85965f;
        double d7 = (double) f14;
        float cos = ((float) Math.cos(d7)) * log;
        float sin = log * ((float) Math.sin(d7));
        fArr3[0] = atan2;
        fArr3[1] = sqrt;
        if (fArr != null) {
            fArr[0] = pow4;
            fArr[1] = c;
            fArr[2] = flRoot;
            fArr[3] = sqrt2;
            fArr[4] = f17;
            fArr[5] = cos;
            fArr[6] = sin;
        }
    }

    private static CamColor fromJch(float f, float f2, float f3) {
        return fromJchInFrame(f, f2, f3, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float f, float f2, float f3, ViewingConditions viewingConditions) {
        float f4 = f;
        double d = (double) f4;
        Double.isNaN(d);
        double d2 = d / 100.0d;
        float c = (4.0f / viewingConditions.getC()) * ((float) Math.sqrt(d2)) * (viewingConditions.getAw() + 4.0f) * viewingConditions.getFlRoot();
        float flRoot = f2 * viewingConditions.getFlRoot();
        float sqrt = ((float) Math.sqrt((double) (((f2 / ((float) Math.sqrt(d2))) * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float f5 = (1.7f * f4) / ((0.007f * f4) + 1.0f);
        double d3 = (double) flRoot;
        Double.isNaN(d3);
        float log = ((float) Math.log((d3 * 0.0228d) + 1.0d)) * 43.85965f;
        double d4 = (double) ((3.1415927f * f3) / 180.0f);
        return new CamColor(f3, f2, f4, c, flRoot, sqrt, f5, log * ((float) Math.cos(d4)), log * ((float) Math.sin(d4)));
    }

    /* access modifiers changed from: package-private */
    public float distance(CamColor camColor) {
        float jStar = getJStar() - camColor.getJStar();
        float aStar = getAStar() - camColor.getAStar();
        float bStar = getBStar() - camColor.getBStar();
        return (float) (Math.pow(Math.sqrt((double) ((jStar * jStar) + (aStar * aStar) + (bStar * bStar))), 0.63d) * 1.41d);
    }

    /* access modifiers changed from: package-private */
    public int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    /* access modifiers changed from: package-private */
    public int viewed(ViewingConditions viewingConditions) {
        float f;
        if (((double) getChroma()) == 0.0d || ((double) getJ()) == 0.0d) {
            f = 0.0f;
        } else {
            float chroma = getChroma();
            double j = (double) getJ();
            Double.isNaN(j);
            f = chroma / ((float) Math.sqrt(j / 100.0d));
        }
        double d = (double) f;
        double pow = Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d);
        Double.isNaN(d);
        float pow2 = (float) Math.pow(d / pow, 1.1111111111111112d);
        double hue = (double) ((getHue() * 3.1415927f) / 180.0f);
        Double.isNaN(hue);
        float aw = viewingConditions.getAw();
        double j2 = (double) getJ();
        Double.isNaN(j2);
        double c = (double) viewingConditions.getC();
        Double.isNaN(c);
        double d2 = 1.0d / c;
        double z = (double) viewingConditions.getZ();
        Double.isNaN(z);
        float pow3 = aw * ((float) Math.pow(j2 / 100.0d, d2 / z));
        float cos = ((float) (Math.cos(2.0d + hue) + 3.8d)) * 0.25f * 3846.1538f * viewingConditions.getNc() * viewingConditions.getNcb();
        float nbb = pow3 / viewingConditions.getNbb();
        float sin = (float) Math.sin(hue);
        float cos2 = (float) Math.cos(hue);
        float f2 = (((0.305f + nbb) * 23.0f) * pow2) / (((cos * 23.0f) + ((11.0f * pow2) * cos2)) + ((pow2 * 108.0f) * sin));
        float f3 = cos2 * f2;
        float f4 = f2 * sin;
        float f5 = nbb * 460.0f;
        float f6 = (((451.0f * f3) + f5) + (288.0f * f4)) / 1403.0f;
        float f7 = ((f5 - (891.0f * f3)) - (261.0f * f4)) / 1403.0f;
        float f8 = ((f5 - (f3 * 220.0f)) - (f4 * 6300.0f)) / 1403.0f;
        double abs = (double) Math.abs(f6);
        Double.isNaN(abs);
        double abs2 = (double) Math.abs(f6);
        Double.isNaN(abs2);
        float signum = Math.signum(f6) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) ((float) Math.max(0.0d, (abs * 27.13d) / (400.0d - abs2))), 2.380952380952381d));
        double abs3 = (double) Math.abs(f7);
        Double.isNaN(abs3);
        double abs4 = (double) Math.abs(f7);
        Double.isNaN(abs4);
        float signum2 = Math.signum(f7) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) ((float) Math.max(0.0d, (abs3 * 27.13d) / (400.0d - abs4))), 2.380952380952381d));
        double abs5 = (double) Math.abs(f8);
        Double.isNaN(abs5);
        double abs6 = (double) Math.abs(f8);
        Double.isNaN(abs6);
        float signum3 = Math.signum(f8) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) ((float) Math.max(0.0d, (abs5 * 27.13d) / (400.0d - abs6))), 2.380952380952381d));
        float f9 = signum / viewingConditions.getRgbD()[0];
        float f10 = signum2 / viewingConditions.getRgbD()[1];
        float f11 = signum3 / viewingConditions.getRgbD()[2];
        float[][] fArr = CamUtils.CAM16RGB_TO_XYZ;
        float[] fArr2 = fArr[0];
        float f12 = (fArr2[0] * f9) + (fArr2[1] * f10) + (fArr2[2] * f11);
        float[] fArr3 = fArr[1];
        float[] fArr4 = fArr[2];
        return ColorUtils.XYZToColor((double) f12, (double) ((fArr3[0] * f9) + (fArr3[1] * f10) + (fArr3[2] * f11)), (double) ((f9 * fArr4[0]) + (f10 * fArr4[1]) + (f11 * fArr4[2])));
    }

    static int toColor(float f, float f2, float f3, ViewingConditions viewingConditions) {
        float f4;
        if (((double) f2) < 1.0d || ((double) Math.round(f3)) <= 0.0d || ((double) Math.round(f3)) >= 100.0d) {
            return CamUtils.intFromLStar(f3);
        }
        if (f < 0.0f) {
            f4 = 0.0f;
        } else {
            f4 = Math.min(360.0f, f);
        }
        float f5 = f2;
        CamColor camColor = null;
        float f6 = 0.0f;
        boolean z = true;
        while (Math.abs(f6 - f2) >= CHROMA_SEARCH_ENDPOINT) {
            CamColor findCamByJ = findCamByJ(f4, f5, f3);
            if (!z) {
                if (findCamByJ == null) {
                    f2 = f5;
                } else {
                    f6 = f5;
                    camColor = findCamByJ;
                }
                f5 = ((f2 - f6) / 2.0f) + f6;
            } else if (findCamByJ != null) {
                return findCamByJ.viewed(viewingConditions);
            } else {
                f5 = ((f2 - f6) / 2.0f) + f6;
                z = false;
            }
        }
        if (camColor == null) {
            return CamUtils.intFromLStar(f3);
        }
        return camColor.viewed(viewingConditions);
    }

    private static CamColor findCamByJ(float f, float f2, float f3) {
        float f4 = 100.0f;
        float f5 = 1000.0f;
        CamColor camColor = null;
        float f6 = 1000.0f;
        float f7 = 0.0f;
        while (Math.abs(f7 - f4) > LIGHTNESS_SEARCH_ENDPOINT) {
            float f8 = ((f4 - f7) / 2.0f) + f7;
            int viewedInSrgb = fromJch(f8, f2, f).viewedInSrgb();
            float lStarFromInt = CamUtils.lStarFromInt(viewedInSrgb);
            float abs = Math.abs(f3 - lStarFromInt);
            if (abs < 0.2f) {
                CamColor fromColor = fromColor(viewedInSrgb);
                float distance = fromColor.distance(fromJch(fromColor.getJ(), fromColor.getChroma(), f));
                if (distance <= 1.0f) {
                    camColor = fromColor;
                    f5 = abs;
                    f6 = distance;
                }
            }
            if (f5 == 0.0f && f6 == 0.0f) {
                break;
            } else if (lStarFromInt < f3) {
                f7 = f8;
            } else {
                f4 = f8;
            }
        }
        return camColor;
    }
}