package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nl.dionsegijn.konfetti.core.Spread;

public final class Score {
    private static final int BLUE_500 = -12417548;
    private static final double CUTOFF_CHROMA = 5.0d;
    private static final double CUTOFF_EXCITED_PROPORTION = 0.01d;
    private static final int MAX_COLOR_COUNT = 4;
    private static final double TARGET_CHROMA = 48.0d;
    private static final double WEIGHT_CHROMA_ABOVE = 0.3d;
    private static final double WEIGHT_CHROMA_BELOW = 0.1d;
    private static final double WEIGHT_PROPORTION = 0.7d;

    private Score() {
    }

    public static List<Integer> score(Map<Integer, Integer> map) {
        return score(map, 4, BLUE_500, true);
    }

    public static List<Integer> score(Map<Integer, Integer> map, int i) {
        return score(map, i, BLUE_500, true);
    }

    public static List<Integer> score(Map<Integer, Integer> map, int i, int i2) {
        return score(map, i, i2, true);
    }

    public static List<Integer> score(Map<Integer, Integer> map, int i, int i2, boolean z) {
        ArrayList<Hct> arrayList = new ArrayList<>();
        int[] iArr = new int[Spread.ROUND];
        double d = 0.0d;
        for (Map.Entry next : map.entrySet()) {
            Hct fromInt = Hct.fromInt(((Integer) next.getKey()).intValue());
            arrayList.add(fromInt);
            int floor = (int) Math.floor(fromInt.getHue());
            int intValue = ((Integer) next.getValue()).intValue();
            iArr[floor] = iArr[floor] + intValue;
            double d2 = (double) intValue;
            Double.isNaN(d2);
            d += d2;
        }
        double[] dArr = new double[Spread.ROUND];
        for (int i3 = 0; i3 < 360; i3++) {
            double d3 = (double) iArr[i3];
            Double.isNaN(d3);
            double d4 = d3 / d;
            for (int i4 = i3 - 14; i4 < i3 + 16; i4++) {
                int sanitizeDegreesInt = MathUtils.sanitizeDegreesInt(i4);
                dArr[sanitizeDegreesInt] = dArr[sanitizeDegreesInt] + d4;
            }
        }
        ArrayList<ScoredHCT> arrayList2 = new ArrayList<>();
        for (Hct hct : arrayList) {
            double d5 = dArr[MathUtils.sanitizeDegreesInt((int) Math.round(hct.getHue()))];
            if (!z || (hct.getChroma() >= CUTOFF_CHROMA && d5 > 0.01d)) {
                arrayList2.add(new ScoredHCT(hct, (d5 * 100.0d * WEIGHT_PROPORTION) + ((hct.getChroma() - TARGET_CHROMA) * (hct.getChroma() < TARGET_CHROMA ? 0.1d : WEIGHT_CHROMA_ABOVE))));
            }
        }
        Collections.sort(arrayList2, new ScoredComparator());
        ArrayList<Hct> arrayList3 = new ArrayList<>();
        for (int i5 = 90; i5 >= 15; i5--) {
            arrayList3.clear();
            for (ScoredHCT scoredHCT : arrayList2) {
                Hct hct2 = scoredHCT.hct;
                Iterator it = arrayList3.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (MathUtils.differenceDegrees(hct2.getHue(), ((Hct) it.next()).getHue()) < ((double) i5)) {
                            break;
                        }
                    } else {
                        arrayList3.add(hct2);
                        break;
                    }
                }
                if (arrayList3.size() >= i) {
                    break;
                }
            }
            if (arrayList3.size() >= i) {
                break;
            }
        }
        ArrayList arrayList4 = new ArrayList();
        if (arrayList3.isEmpty()) {
            arrayList4.add(Integer.valueOf(i2));
            return arrayList4;
        }
        for (Hct hct3 : arrayList3) {
            arrayList4.add(Integer.valueOf(hct3.toInt()));
        }
        return arrayList4;
    }

    private static class ScoredHCT {
        public final Hct hct;
        public final double score;

        public ScoredHCT(Hct hct2, double d) {
            this.hct = hct2;
            this.score = d;
        }
    }

    private static class ScoredComparator implements Comparator<ScoredHCT> {
        public int compare(ScoredHCT scoredHCT, ScoredHCT scoredHCT2) {
            return Double.compare(scoredHCT2.score, scoredHCT.score);
        }
    }
}
