package com.google.zxing.oned;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import java.util.ArrayList;
import java.util.List;

final class EANManufacturerOrgSupport {
    private final List<String> countryIdentifiers = new ArrayList();
    private final List<int[]> ranges = new ArrayList();

    EANManufacturerOrgSupport() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        r4 = r7.ranges.get(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String lookupCountryIdentifier(java.lang.String r8) {
        /*
            r7 = this;
            r7.initIfNeeded()
            r0 = 3
            r1 = 0
            java.lang.String r8 = r8.substring(r1, r0)
            int r8 = java.lang.Integer.parseInt(r8)
            java.util.List<int[]> r0 = r7.ranges
            int r0 = r0.size()
            r2 = 0
        L_0x0014:
            r3 = 0
            if (r2 >= r0) goto L_0x0039
            java.util.List<int[]> r4 = r7.ranges
            java.lang.Object r4 = r4.get(r2)
            int[] r4 = (int[]) r4
            r5 = r4[r1]
            if (r8 >= r5) goto L_0x0024
            return r3
        L_0x0024:
            int r3 = r4.length
            r6 = 1
            if (r3 != r6) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r5 = r4[r6]
        L_0x002b:
            if (r8 > r5) goto L_0x0036
            java.util.List<java.lang.String> r8 = r7.countryIdentifiers
            java.lang.Object r8 = r8.get(r2)
            java.lang.String r8 = (java.lang.String) r8
            return r8
        L_0x0036:
            int r2 = r2 + 1
            goto L_0x0014
        L_0x0039:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.EANManufacturerOrgSupport.lookupCountryIdentifier(java.lang.String):java.lang.String");
    }

    private void add(int[] iArr, String str) {
        this.ranges.add(iArr);
        this.countryIdentifiers.add(str);
    }

    private synchronized void initIfNeeded() {
        if (this.ranges.isEmpty()) {
            add(new int[]{0, 19}, "US/CA");
            add(new int[]{30, 39}, "US");
            add(new int[]{60, 139}, "US/CA");
            add(new int[]{MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, 379}, "FR");
            add(new int[]{380}, "BG");
            add(new int[]{383}, "SI");
            add(new int[]{385}, "HR");
            add(new int[]{387}, "BA");
            add(new int[]{400, 440}, "DE");
            add(new int[]{450, 459}, "JP");
            add(new int[]{460, 469}, "RU");
            add(new int[]{471}, "TW");
            add(new int[]{474}, "EE");
            add(new int[]{475}, "LV");
            add(new int[]{476}, "AZ");
            add(new int[]{477}, "LT");
            add(new int[]{478}, "UZ");
            add(new int[]{479}, "LK");
            add(new int[]{480}, "PH");
            add(new int[]{481}, "BY");
            add(new int[]{482}, "UA");
            add(new int[]{484}, "MD");
            add(new int[]{485}, "AM");
            add(new int[]{486}, "GE");
            add(new int[]{487}, "KZ");
            add(new int[]{489}, "HK");
            add(new int[]{490, 499}, "JP");
            add(new int[]{500, 509}, "GB");
            add(new int[]{520}, "GR");
            add(new int[]{528}, ExpandedProductParsedResult.POUND);
            add(new int[]{529}, "CY");
            add(new int[]{531}, "MK");
            add(new int[]{535}, "MT");
            add(new int[]{539}, "IE");
            add(new int[]{540, 549}, "BE/LU");
            add(new int[]{560}, "PT");
            add(new int[]{569}, "IS");
            add(new int[]{570, 579}, "DK");
            add(new int[]{590}, "PL");
            add(new int[]{594}, "RO");
            add(new int[]{599}, "HU");
            add(new int[]{600, 601}, "ZA");
            add(new int[]{TypedValues.MotionType.TYPE_EASING}, "GH");
            add(new int[]{TypedValues.MotionType.TYPE_DRAW_PATH}, "BH");
            add(new int[]{TypedValues.MotionType.TYPE_POLAR_RELATIVETO}, "MU");
            add(new int[]{TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE}, "MA");
            add(new int[]{613}, "DZ");
            add(new int[]{616}, "KE");
            add(new int[]{618}, "CI");
            add(new int[]{619}, "TN");
            add(new int[]{621}, "SY");
            add(new int[]{622}, "EG");
            add(new int[]{624}, "LY");
            add(new int[]{625}, "JO");
            add(new int[]{626}, "IR");
            add(new int[]{627}, "KW");
            add(new int[]{628}, "SA");
            add(new int[]{629}, "AE");
            add(new int[]{640, 649}, "FI");
            add(new int[]{690, 695}, "CN");
            add(new int[]{700, 709}, "NO");
            add(new int[]{729}, "IL");
            add(new int[]{730, 739}, "SE");
            add(new int[]{740}, "GT");
            add(new int[]{741}, "SV");
            add(new int[]{742}, "HN");
            add(new int[]{743}, "NI");
            add(new int[]{744}, "CR");
            add(new int[]{745}, "PA");
            add(new int[]{746}, "DO");
            add(new int[]{750}, "MX");
            add(new int[]{754, 755}, "CA");
            add(new int[]{759}, "VE");
            add(new int[]{760, 769}, "CH");
            add(new int[]{770}, "CO");
            add(new int[]{773}, "UY");
            add(new int[]{775}, "PE");
            add(new int[]{777}, "BO");
            add(new int[]{779}, "AR");
            add(new int[]{780}, "CL");
            add(new int[]{784}, "PY");
            add(new int[]{785}, "PE");
            add(new int[]{786}, "EC");
            add(new int[]{789, 790}, "BR");
            add(new int[]{800, 839}, "IT");
            add(new int[]{840, 849}, "ES");
            add(new int[]{850}, "CU");
            add(new int[]{858}, "SK");
            add(new int[]{859}, "CZ");
            add(new int[]{860}, "YU");
            add(new int[]{865}, "MN");
            add(new int[]{867}, "KP");
            add(new int[]{868, 869}, "TR");
            add(new int[]{870, 879}, "NL");
            add(new int[]{880}, "KR");
            add(new int[]{885}, "TH");
            add(new int[]{888}, "SG");
            add(new int[]{890}, "IN");
            add(new int[]{893}, "VN");
            add(new int[]{896}, "PK");
            add(new int[]{899}, "ID");
            add(new int[]{900, 919}, "AT");
            add(new int[]{930, 939}, "AU");
            add(new int[]{940, 949}, "AZ");
            add(new int[]{955}, "MY");
            add(new int[]{958}, "MO");
        }
    }
}
