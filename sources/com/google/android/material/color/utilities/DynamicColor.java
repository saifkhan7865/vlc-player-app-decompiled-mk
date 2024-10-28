package com.google.android.material.color.utilities;

import java.util.HashMap;
import java.util.function.Function;

public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final ContrastCurve contrastCurve;
    private final HashMap<DynamicScheme, Hct> hctCache = new HashMap<>();
    public final boolean isBackground;
    public final String name;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, TonalPalette> palette;
    public final Function<DynamicScheme, DynamicColor> secondBackground;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaPair> toneDeltaPair;

    static /* synthetic */ TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette, DynamicScheme dynamicScheme) {
        return tonalPalette;
    }

    public DynamicColor(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, DynamicColor> function4, ContrastCurve contrastCurve2, Function<DynamicScheme, ToneDeltaPair> function5) {
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve2;
        this.toneDeltaPair = function5;
        this.opacity = null;
    }

    public DynamicColor(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, DynamicColor> function4, ContrastCurve contrastCurve2, Function<DynamicScheme, ToneDeltaPair> function5, Function<DynamicScheme, Double> function6) {
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve2;
        this.toneDeltaPair = function5;
        this.opacity = function6;
    }

    public static DynamicColor fromPalette(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2) {
        return new DynamicColor(str, function, function2, false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public static DynamicColor fromPalette(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z) {
        return new DynamicColor(str, function, function2, z, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public static DynamicColor fromArgb(String str, int i) {
        return fromPalette(str, new DynamicColor$$ExternalSyntheticLambda0(TonalPalette.fromInt(i)), new DynamicColor$$ExternalSyntheticLambda1(Hct.fromInt(i)));
    }

    public int getArgb(DynamicScheme dynamicScheme) {
        int i = getHct(dynamicScheme).toInt();
        Function<DynamicScheme, Double> function = this.opacity;
        if (function == null) {
            return i;
        }
        return (MathUtils.clampInt(0, 255, (int) Math.round(function.apply(dynamicScheme).doubleValue() * 255.0d)) << 24) | (i & 16777215);
    }

    public Hct getHct(DynamicScheme dynamicScheme) {
        Hct hct = this.hctCache.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct hct2 = this.palette.apply(dynamicScheme).getHct(getTone(dynamicScheme));
        if (this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme, hct2);
        return hct2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double getTone(com.google.android.material.color.utilities.DynamicScheme r31) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            double r2 = r1.contrastLevel
            r6 = 0
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x000e
            r2 = 1
            goto L_0x000f
        L_0x000e:
            r2 = 0
        L_0x000f:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.ToneDeltaPair> r3 = r0.toneDeltaPair
            if (r3 == 0) goto L_0x0151
            java.lang.Object r3 = r3.apply(r1)
            com.google.android.material.color.utilities.ToneDeltaPair r3 = (com.google.android.material.color.utilities.ToneDeltaPair) r3
            com.google.android.material.color.utilities.DynamicColor r16 = r3.getRoleA()
            com.google.android.material.color.utilities.DynamicColor r17 = r3.getRoleB()
            double r18 = r3.getDelta()
            com.google.android.material.color.utilities.TonePolarity r5 = r3.getPolarity()
            boolean r3 = r3.getStayTogether()
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r4 = r0.background
            java.lang.Object r4 = r4.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r4 = (com.google.android.material.color.utilities.DynamicColor) r4
            double r12 = r4.getTone(r1)
            com.google.android.material.color.utilities.TonePolarity r4 = com.google.android.material.color.utilities.TonePolarity.NEARER
            if (r5 == r4) goto L_0x0050
            com.google.android.material.color.utilities.TonePolarity r4 = com.google.android.material.color.utilities.TonePolarity.LIGHTER
            if (r5 != r4) goto L_0x0045
            boolean r4 = r1.isDark
            if (r4 == 0) goto L_0x0050
        L_0x0045:
            com.google.android.material.color.utilities.TonePolarity r4 = com.google.android.material.color.utilities.TonePolarity.DARKER
            if (r5 != r4) goto L_0x004e
            boolean r4 = r1.isDark
            if (r4 == 0) goto L_0x004e
            goto L_0x0050
        L_0x004e:
            r4 = 0
            goto L_0x0051
        L_0x0050:
            r4 = 1
        L_0x0051:
            if (r4 == 0) goto L_0x0056
            r5 = r16
            goto L_0x0058
        L_0x0056:
            r5 = r17
        L_0x0058:
            if (r4 == 0) goto L_0x005d
            r4 = r17
            goto L_0x005f
        L_0x005d:
            r4 = r16
        L_0x005f:
            java.lang.String r10 = r0.name
            java.lang.String r11 = r5.name
            boolean r10 = r10.equals(r11)
            boolean r11 = r1.isDark
            if (r11 == 0) goto L_0x006e
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0070
        L_0x006e:
            r20 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0070:
            com.google.android.material.color.utilities.ContrastCurve r11 = r5.contrastCurve
            double r6 = r1.contrastLevel
            double r6 = r11.getContrast(r6)
            com.google.android.material.color.utilities.ContrastCurve r11 = r4.contrastCurve
            double r14 = r1.contrastLevel
            double r14 = r11.getContrast(r14)
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r5 = r5.tone
            java.lang.Object r5 = r5.apply(r1)
            java.lang.Double r5 = (java.lang.Double) r5
            double r8 = r5.doubleValue()
            double r22 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r12, r8)
            int r5 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x0095
            goto L_0x0099
        L_0x0095:
            double r8 = foregroundTone(r12, r6)
        L_0x0099:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r4 = r4.tone
            java.lang.Object r1 = r4.apply(r1)
            java.lang.Double r1 = (java.lang.Double) r1
            double r4 = r1.doubleValue()
            double r22 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r12, r4)
            int r1 = (r22 > r14 ? 1 : (r22 == r14 ? 0 : -1))
            if (r1 < 0) goto L_0x00ae
            goto L_0x00b2
        L_0x00ae:
            double r4 = foregroundTone(r12, r14)
        L_0x00b2:
            if (r2 == 0) goto L_0x00bc
            double r8 = foregroundTone(r12, r6)
            double r4 = foregroundTone(r12, r14)
        L_0x00bc:
            double r1 = r4 - r8
            double r1 = r1 * r20
            int r6 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1))
            if (r6 >= 0) goto L_0x00e2
            double r1 = r18 * r20
            double r28 = r8 + r1
            r24 = 0
            r26 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r4 = com.google.android.material.color.utilities.MathUtils.clampDouble(r24, r26, r28)
            double r6 = r4 - r8
            double r6 = r6 * r20
            int r11 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1))
            if (r11 >= 0) goto L_0x00e2
            r26 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r28 = r4 - r1
            r24 = 0
            double r8 = com.google.android.material.color.utilities.MathUtils.clampDouble(r24, r26, r28)
        L_0x00e2:
            r1 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r6 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            r1 = 4633641066610819072(0x404e000000000000, double:60.0)
            if (r6 > 0) goto L_0x0112
            int r6 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x0112
            r6 = 0
            int r3 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x00ff
            double r18 = r18 * r20
            double r6 = r18 + r1
            double r3 = java.lang.Math.max(r4, r6)
            r8 = r1
            r1 = r3
            goto L_0x014d
        L_0x00ff:
            double r18 = r18 * r20
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r6 = r18 + r1
            double r1 = java.lang.Math.min(r4, r6)
        L_0x010c:
            r8 = 4632092954238910464(0x4048800000000000, double:49.0)
            goto L_0x014d
        L_0x0112:
            r6 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r11 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r11 > 0) goto L_0x014c
            int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x014c
            if (r3 == 0) goto L_0x013d
            r6 = 0
            int r3 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x012f
            double r18 = r18 * r20
            double r6 = r18 + r1
            double r1 = java.lang.Math.max(r4, r6)
            r8 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x014d
        L_0x012f:
            double r18 = r18 * r20
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r6 = r18 + r1
            double r1 = java.lang.Math.min(r4, r6)
            goto L_0x010c
        L_0x013d:
            r6 = 0
            int r1 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x0146
            r1 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x014d
        L_0x0146:
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            goto L_0x014d
        L_0x014c:
            r1 = r4
        L_0x014d:
            if (r10 == 0) goto L_0x0150
            r1 = r8
        L_0x0150:
            return r1
        L_0x0151:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r3 = r0.tone
            java.lang.Object r3 = r3.apply(r1)
            java.lang.Double r3 = (java.lang.Double) r3
            double r3 = r3.doubleValue()
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r5 = r0.background
            if (r5 != 0) goto L_0x0162
            return r3
        L_0x0162:
            java.lang.Object r5 = r5.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r5 = (com.google.android.material.color.utilities.DynamicColor) r5
            double r8 = r5.getTone(r1)
            com.google.android.material.color.utilities.ContrastCurve r5 = r0.contrastCurve
            double r10 = r1.contrastLevel
            double r10 = r5.getContrast(r10)
            double r12 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r8, r3)
            int r5 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x017d
            goto L_0x0181
        L_0x017d:
            double r3 = foregroundTone(r8, r10)
        L_0x0181:
            if (r2 == 0) goto L_0x0187
            double r3 = foregroundTone(r8, r10)
        L_0x0187:
            boolean r2 = r0.isBackground
            if (r2 == 0) goto L_0x01a7
            r12 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r2 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x01a7
            r12 = 4633641066610819072(0x404e000000000000, double:60.0)
            int r2 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r2 >= 0) goto L_0x01a7
            r14 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r2 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r14, r8)
            int r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r4 < 0) goto L_0x01a5
            goto L_0x01a8
        L_0x01a5:
            r14 = r12
            goto L_0x01a8
        L_0x01a7:
            r14 = r3
        L_0x01a8:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.secondBackground
            if (r2 == 0) goto L_0x0234
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.background
            java.lang.Object r2 = r2.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r2 = (com.google.android.material.color.utilities.DynamicColor) r2
            double r2 = r2.getTone(r1)
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r4 = r0.secondBackground
            java.lang.Object r4 = r4.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r4 = (com.google.android.material.color.utilities.DynamicColor) r4
            double r4 = r4.getTone(r1)
            double r8 = java.lang.Math.max(r2, r4)
            double r12 = java.lang.Math.min(r2, r4)
            double r16 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r8, r14)
            int r1 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r1 < 0) goto L_0x01dd
            double r16 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r12, r14)
            int r1 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r1 < 0) goto L_0x01dd
            return r14
        L_0x01dd:
            double r8 = com.google.android.material.color.utilities.Contrast.lighter(r8, r10)
            double r10 = com.google.android.material.color.utilities.Contrast.darker(r12, r10)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x01f7
            java.lang.Double r14 = java.lang.Double.valueOf(r8)
            r1.add(r14)
        L_0x01f7:
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x0202
            java.lang.Double r12 = java.lang.Double.valueOf(r10)
            r1.add(r12)
        L_0x0202:
            boolean r2 = tonePrefersLightForeground(r2)
            if (r2 != 0) goto L_0x022b
            boolean r2 = tonePrefersLightForeground(r4)
            if (r2 == 0) goto L_0x020f
            goto L_0x022b
        L_0x020f:
            int r2 = r1.size()
            r3 = 1
            if (r2 != r3) goto L_0x0222
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            java.lang.Double r1 = (java.lang.Double) r1
            double r1 = r1.doubleValue()
            return r1
        L_0x0222:
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r3 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0229
            goto L_0x022a
        L_0x0229:
            r6 = r10
        L_0x022a:
            return r6
        L_0x022b:
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0233
            r8 = 4636737291354636288(0x4059000000000000, double:100.0)
        L_0x0233:
            return r8
        L_0x0234:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.DynamicColor.getTone(com.google.android.material.color.utilities.DynamicScheme):double");
    }

    public static double foregroundTone(double d, double d2) {
        double lighterUnsafe = Contrast.lighterUnsafe(d, d2);
        double darkerUnsafe = Contrast.darkerUnsafe(d, d2);
        double ratioOfTones = Contrast.ratioOfTones(lighterUnsafe, d);
        double ratioOfTones2 = Contrast.ratioOfTones(darkerUnsafe, d);
        if (!tonePrefersLightForeground(d)) {
            return (ratioOfTones2 >= d2 || ratioOfTones2 >= ratioOfTones) ? darkerUnsafe : lighterUnsafe;
        }
        return (ratioOfTones >= d2 || ratioOfTones >= ratioOfTones2 || ((Math.abs(ratioOfTones - ratioOfTones2) > 0.1d ? 1 : (Math.abs(ratioOfTones - ratioOfTones2) == 0.1d ? 0 : -1)) < 0 && (ratioOfTones > d2 ? 1 : (ratioOfTones == d2 ? 0 : -1)) < 0 && (ratioOfTones2 > d2 ? 1 : (ratioOfTones2 == d2 ? 0 : -1)) < 0)) ? lighterUnsafe : darkerUnsafe;
    }

    public static double enableLightForeground(double d) {
        if (!tonePrefersLightForeground(d) || toneAllowsLightForeground(d)) {
            return d;
        }
        return 49.0d;
    }

    public static boolean tonePrefersLightForeground(double d) {
        return Math.round(d) < 60;
    }

    public static boolean toneAllowsLightForeground(double d) {
        return Math.round(d) <= 49;
    }
}
