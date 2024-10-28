package org.bouncycastle.pqc.crypto.gemss;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.gemss.SecretKeyHFE;
import org.bouncycastle.util.Pack;

class GeMSSEngine {
    final int ACCESS_last_equations8;
    Pointer Buffer_NB_WORD_GFqn;
    Pointer Buffer_NB_WORD_MUL;
    final boolean ENABLED_REMOVE_ODD_DEGREE;
    final int HFEDELTA;
    final int HFEDeg;
    final int HFEDegI;
    final int HFEDegJ;
    final int HFENr8;
    final int HFENr8c;
    int HFE_odd_degree;
    final int HFEm;
    final int HFEmq;
    final int HFEmq8;
    final int HFEmr;
    final int HFEmr8;
    final int HFEn;
    int HFEn1h_rightmost;
    int HFEn_1rightmost;
    final int HFEnq;
    final int HFEnr;
    final int HFEnv;
    final int HFEnvq;
    final int HFEnvr;
    final int HFEnvr8;
    final int HFEv;
    final int HFEvq;
    final int HFEvr;
    int II;
    int KP;
    int KX;
    final int LEN_UNROLLED_64 = 4;
    final int LOST_BITS;
    int LTRIANGULAR_NV_SIZE;
    final int LTRIANGULAR_N_SIZE;
    final long MASK_GF2m;
    final long MASK_GF2n;
    final int MATRIXn_SIZE;
    final int MATRIXnv_SIZE;
    final int MLv_GFqn_SIZE;
    int MQv_GFqn_SIZE;
    final int NB_BITS_UINT = 64;
    final int NB_BYTES_EQUATION;
    final int NB_BYTES_GFqm;
    final int NB_BYTES_GFqn;
    final int NB_BYTES_GFqnv;
    int NB_COEFS_HFEPOLY;
    final int NB_ITE;
    int NB_MONOMIAL_PK;
    int NB_MONOMIAL_VINEGAR;
    int NB_UINT_HFEVPOLY;
    int NB_WORD_GF2m;
    int NB_WORD_GF2nv;
    final int NB_WORD_GF2nvm;
    int NB_WORD_GFqn;
    final int NB_WORD_GFqv;
    int NB_WORD_MMUL;
    final int NB_WORD_MUL;
    final int NB_WORD_UNCOMP_EQ;
    int POW_II;
    final int SIZE_DIGEST;
    final int SIZE_DIGEST_UINT;
    final int SIZE_ROW;
    final int SIZE_SEED_SK;
    final int SIZE_SIGN_UNCOMPRESSED;
    final int Sha3BitStrength;
    final int ShakeBitStrength;
    final int VAL_BITS_M;
    private int buffer;
    Mul_GF2x mul;
    private SecureRandom random;
    Rem_GF2n rem;
    SHA3Digest sha3Digest;

    /* renamed from: org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams[] r0 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams = r0
                org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams r1 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams.N     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams r1 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams.NV     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams r1 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams.V     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams r1 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams.M     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.AnonymousClass1.<clinit>():void");
        }
    }

    enum FunctionParams {
        NV,
        V,
        N,
        M
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v11, resolved type: int} */
    /* JADX WARNING: type inference failed for: r1v15, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n] */
    /* JADX WARNING: type inference failed for: r3v9, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n] */
    /* JADX WARNING: type inference failed for: r16v9, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM192_SPECIALIZED_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v10, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM288_SPECIALIZED_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v11, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v12, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM402_SPECIALIZED_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v13, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_SPECIALIZED358_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v14, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_SPECIALIZED_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v15, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM288_SPECIALIZED_TRINOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v16, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM544_PENTANOMIAL_GF2X] */
    /* JADX WARNING: type inference failed for: r16v17, types: [org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM544_PENTANOMIAL_K3_IS_128_GF2X] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0268  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02f0  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0302  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0311  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x034a  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0409 A[LOOP:0: B:165:0x0403->B:167:0x0409, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0419 A[LOOP:1: B:169:0x0411->B:171:0x0419, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x040e A[EDGE_INSN: B:174:0x040e->B:168:0x040e ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x041c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0215  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0231  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GeMSSEngine(int r35, int r36, int r37, int r38, int r39, int r40, int r41, int r42) {
        /*
            r34 = this;
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r37
            r4 = r38
            r5 = r39
            r6 = r40
            r7 = r41
            r8 = r42
            r34.<init>()
            r9 = 64
            r0.NB_BITS_UINT = r9
            r10 = 4
            r0.LEN_UNROLLED_64 = r10
            r0.HFEn = r2
            r0.HFEv = r3
            r0.HFEDELTA = r4
            r0.NB_ITE = r5
            r0.HFEDeg = r6
            r0.HFEDegI = r7
            r0.HFEDegJ = r8
            int r11 = r2 >>> 3
            r12 = r2 & 7
            if (r12 == 0) goto L_0x0032
            r12 = 1
            goto L_0x0033
        L_0x0032:
            r12 = 0
        L_0x0033:
            int r11 = r11 + r12
            r0.NB_BYTES_GFqn = r11
            int r11 = r7 + 1
            r0.SIZE_ROW = r11
            int r12 = r2 + r3
            r0.HFEnv = r12
            int r15 = r2 >>> 6
            r0.HFEnq = r15
            r10 = r2 & 63
            r0.HFEnr = r10
            int r13 = r12 >>> 6
            r0.HFEnvq = r13
            r9 = r12 & 63
            r0.HFEnvr = r9
            int r14 = r1 >>> 3
            r0.SIZE_SEED_SK = r14
            int r14 = r2 + -1
            int r19 = r14 << 1
            r28 = r14
            r14 = 6
            int r19 = r19 >>> 6
            r20 = 1
            int r14 = r19 + 1
            r0.NB_WORD_MUL = r14
            r1 = 9
            r5 = 6
            if (r14 == r5) goto L_0x008d
            if (r14 == r1) goto L_0x0087
            r5 = 17
            if (r14 == r5) goto L_0x0081
            r5 = 12
            if (r14 == r5) goto L_0x007b
            r5 = 13
            if (r14 == r5) goto L_0x0075
            goto L_0x0094
        L_0x0075:
            org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul13 r5 = new org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul13
            r5.<init>()
            goto L_0x0092
        L_0x007b:
            org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul12 r5 = new org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul12
            r5.<init>()
            goto L_0x0092
        L_0x0081:
            org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul17 r5 = new org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul17
            r5.<init>()
            goto L_0x0092
        L_0x0087:
            org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul9 r5 = new org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul9
            r5.<init>()
            goto L_0x0092
        L_0x008d:
            org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul6 r5 = new org.bouncycastle.pqc.crypto.gemss.Mul_GF2x$Mul6
            r5.<init>()
        L_0x0092:
            r0.mul = r5
        L_0x0094:
            int r5 = 64 - r10
            int r1 = r2 - r4
            r0.HFEm = r1
            r24 = r5
            int r5 = r1 >>> 6
            r0.HFEmq = r5
            r25 = r5
            r5 = r1 & 63
            r0.HFEmr = r5
            r26 = r11
            int r11 = r3 >>> 6
            r0.HFEvq = r11
            r8 = r3 & 63
            r0.HFEvr = r8
            if (r8 == 0) goto L_0x00b4
            int r11 = r11 + 1
        L_0x00b4:
            r0.NB_WORD_GFqv = r11
            int r8 = r1 >>> 3
            r0.HFEmq8 = r8
            r1 = r1 & 7
            r0.HFEmr8 = r1
            if (r1 == 0) goto L_0x00c2
            r11 = 1
            goto L_0x00c3
        L_0x00c2:
            r11 = 0
        L_0x00c3:
            int r11 = r11 + r8
            r0.NB_BYTES_GFqm = r11
            int r11 = r13 + 1
            int r11 = r11 * r13
            r27 = 1
            int r11 = r11 >>> 1
            r18 = 64
            int r11 = r11 * 64
            int r29 = r13 + 1
            int r29 = r29 * r9
            int r11 = r11 + r29
            r0.NB_WORD_UNCOMP_EQ = r11
            r11 = r12 & 7
            r0.HFEnvr8 = r11
            int r29 = r12 >>> 3
            if (r11 == 0) goto L_0x00e4
            r11 = 1
            goto L_0x00e5
        L_0x00e4:
            r11 = 0
        L_0x00e5:
            int r11 = r29 + r11
            r0.NB_BYTES_GFqnv = r11
            int r4 = r4 + r3
            int r11 = 8 - r1
            int r4 = java.lang.Math.min(r4, r11)
            r0.VAL_BITS_M = r4
            long r6 = org.bouncycastle.pqc.crypto.gemss.GeMSSUtils.maskUINT(r5)
            r0.MASK_GF2m = r6
            long r6 = org.bouncycastle.pqc.crypto.gemss.GeMSSUtils.maskUINT(r10)
            r0.MASK_GF2n = r6
            if (r10 == 0) goto L_0x0102
            r4 = 1
            goto L_0x0103
        L_0x0102:
            r4 = 0
        L_0x0103:
            int r4 = r4 + r15
            r0.NB_WORD_GFqn = r4
            int r11 = r15 + 1
            int r15 = r15 * r11
            r11 = 1
            int r15 = r15 >>> r11
            r11 = 64
            int r15 = r15 * 64
            int r11 = r4 * r10
            int r15 = r15 + r11
            r0.LTRIANGULAR_N_SIZE = r15
            int r11 = r2 * r4
            r0.MATRIXn_SIZE = r11
            if (r9 == 0) goto L_0x011d
            r11 = 1
            goto L_0x011e
        L_0x011d:
            r11 = 0
        L_0x011e:
            int r11 = r11 + r13
            r0.NB_WORD_GF2nv = r11
            int r15 = r12 * r11
            r0.MATRIXnv_SIZE = r15
            int r15 = r13 + 1
            int r13 = r13 * r15
            r15 = 1
            int r13 = r13 >>> r15
            r18 = 64
            int r13 = r13 * 64
            int r9 = r9 * r11
            int r13 = r13 + r9
            r0.LTRIANGULAR_NV_SIZE = r13
            int r9 = r3 + 1
            int r13 = r3 * r9
            int r13 = r13 >>> r15
            int r13 = r13 + r15
            r0.NB_MONOMIAL_VINEGAR = r13
            int r27 = r12 + 1
            int r12 = r12 * r27
            int r12 = r12 >>> r15
            int r15 = r12 + 1
            r0.NB_MONOMIAL_PK = r15
            int r13 = r13 * r4
            r0.MQv_GFqn_SIZE = r13
            int r8 = r8 * r15
            r0.ACCESS_last_equations8 = r8
            r4 = 8
            int r12 = r12 + r4
            r8 = 3
            int r12 = r12 >>> r8
            r0.NB_BYTES_EQUATION = r12
            r12 = r15 & 7
            r0.HFENr8 = r12
            int r12 = 8 - r12
            r12 = r12 & 7
            r0.HFENr8c = r12
            r13 = 1
            int r1 = r1 - r13
            int r1 = r1 * r12
            r0.LOST_BITS = r1
            r0.NB_WORD_MMUL = r14
            r1 = 174(0xae, float:2.44E-43)
            r12 = 544(0x220, float:7.62E-43)
            r13 = 402(0x192, float:5.63E-43)
            r15 = 358(0x166, float:5.02E-43)
            r8 = 354(0x162, float:4.96E-43)
            r29 = 2
            r4 = 128(0x80, float:1.794E-43)
            if (r2 == r1) goto L_0x0215
            r1 = 175(0xaf, float:2.45E-43)
            if (r2 == r1) goto L_0x020f
            r1 = 177(0xb1, float:2.48E-43)
            if (r2 == r1) goto L_0x020b
            r1 = 178(0xb2, float:2.5E-43)
            if (r2 == r1) goto L_0x0207
            r1 = 265(0x109, float:3.71E-43)
            if (r2 == r1) goto L_0x0201
            r1 = 266(0x10a, float:3.73E-43)
            if (r2 == r1) goto L_0x01fb
            r1 = 268(0x10c, float:3.76E-43)
            if (r2 == r1) goto L_0x01f5
            if (r2 == r8) goto L_0x01ef
            if (r2 == r15) goto L_0x01e9
            r1 = 364(0x16c, float:5.1E-43)
            if (r2 == r1) goto L_0x01e5
            r1 = 366(0x16e, float:5.13E-43)
            if (r2 == r1) goto L_0x01df
            if (r2 == r13) goto L_0x01d9
            r1 = 537(0x219, float:7.52E-43)
            if (r2 == r1) goto L_0x01d1
            if (r2 == r12) goto L_0x01cb
            r1 = 270(0x10e, float:3.78E-43)
            if (r2 == r1) goto L_0x01c5
            r1 = 271(0x10f, float:3.8E-43)
            if (r2 != r1) goto L_0x01b1
            r1 = 58
            r1 = 0
            r13 = 58
            goto L_0x0218
        L_0x01b1:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "error: need to add support for HFEn="
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x01c5:
            r1 = 53
            r1 = 0
            r13 = 53
            goto L_0x0218
        L_0x01cb:
            r1 = 1
            r13 = 128(0x80, float:1.794E-43)
            r19 = 3
            goto L_0x021a
        L_0x01d1:
            r1 = 10
            r1 = 1
            r13 = 10
            r19 = 2
            goto L_0x021a
        L_0x01d9:
            r1 = 171(0xab, float:2.4E-43)
            r1 = 0
            r13 = 171(0xab, float:2.4E-43)
            goto L_0x0218
        L_0x01df:
            r1 = 29
            r1 = 0
            r13 = 29
            goto L_0x0218
        L_0x01e5:
            r1 = 0
            r13 = 9
            goto L_0x0218
        L_0x01e9:
            r1 = 57
            r1 = 0
            r13 = 57
            goto L_0x0218
        L_0x01ef:
            r1 = 99
            r1 = 0
            r13 = 99
            goto L_0x0218
        L_0x01f5:
            r1 = 25
            r1 = 0
            r13 = 25
            goto L_0x0218
        L_0x01fb:
            r1 = 47
            r1 = 0
            r13 = 47
            goto L_0x0218
        L_0x0201:
            r1 = 42
            r1 = 0
            r13 = 42
            goto L_0x0218
        L_0x0207:
            r1 = 0
            r13 = 31
            goto L_0x0218
        L_0x020b:
            r1 = 0
            r13 = 8
            goto L_0x0218
        L_0x020f:
            r1 = 16
            r1 = 0
            r13 = 16
            goto L_0x0218
        L_0x0215:
            r1 = 0
            r13 = 13
        L_0x0218:
            r19 = 0
        L_0x021a:
            if (r19 == 0) goto L_0x0221
            int r30 = 64 - r1
            int r31 = 64 - r19
            goto L_0x0225
        L_0x0221:
            r30 = 0
            r31 = 0
        L_0x0225:
            r32 = r13 & 63
            r18 = 64
            int r32 = 64 - r32
            r15 = r40
            r33 = r15 & 1
            if (r33 != 0) goto L_0x0268
            r8 = 1
            r0.ENABLED_REMOVE_ODD_DEGREE = r8
            int r27 = r8 << r41
            int r12 = r27 + 1
            r0.HFE_odd_degree = r12
            if (r33 != 0) goto L_0x0260
            if (r12 > r15) goto L_0x0258
            if (r12 <= r8) goto L_0x0250
            int r12 = r42 + 2
            int r27 = r41 + -1
            int r27 = r27 * r41
            int r29 = r27 >>> 1
            int r12 = r12 + r29
            int r12 = r12 + r41
            r0.NB_COEFS_HFEPOLY = r12
            r8 = 0
            goto L_0x0277
        L_0x0250:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "The case where the term X^3 is removing is not implemented."
            r1.<init>(r2)
            throw r1
        L_0x0258:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "It is useless to remove 0 term."
            r1.<init>(r2)
            throw r1
        L_0x0260:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "HFEDeg is odd, so to remove the leading term would decrease the degree."
            r1.<init>(r2)
            throw r1
        L_0x0268:
            r8 = 0
            r0.ENABLED_REMOVE_ODD_DEGREE = r8
            int r12 = r42 + 2
            int r17 = r41 * r26
            r27 = 1
            int r17 = r17 >>> 1
            int r12 = r12 + r17
            r0.NB_COEFS_HFEPOLY = r12
        L_0x0277:
            if (r5 == 0) goto L_0x027b
            r12 = 1
            goto L_0x027c
        L_0x027b:
            r12 = 0
        L_0x027c:
            int r12 = r25 + r12
            r0.NB_WORD_GF2m = r12
            int r12 = r11 - r12
            if (r5 == 0) goto L_0x0286
            r5 = 1
            goto L_0x0287
        L_0x0286:
            r5 = 0
        L_0x0287:
            int r12 = r12 + r5
            r0.NB_WORD_GF2nvm = r12
            r5 = 1
            int r17 = r39 + -1
            int r17 = r17 * r12
            int r11 = r11 + r17
            r0.SIZE_SIGN_UNCOMPRESSED = r11
            r5 = 32
            r11 = 256(0x100, float:3.59E-43)
            r12 = r35
            if (r12 > r4) goto L_0x02a5
            r0.SIZE_DIGEST = r5
            r12 = 4
            r0.SIZE_DIGEST_UINT = r12
            r0.ShakeBitStrength = r4
            r0.Sha3BitStrength = r11
            goto L_0x02c3
        L_0x02a5:
            r8 = 192(0xc0, float:2.69E-43)
            if (r12 > r8) goto L_0x02b5
            r8 = 48
            r0.SIZE_DIGEST = r8
            r8 = 6
            r0.SIZE_DIGEST_UINT = r8
            r0.ShakeBitStrength = r11
            r8 = 384(0x180, float:5.38E-43)
            goto L_0x02c1
        L_0x02b5:
            r8 = 64
            r0.SIZE_DIGEST = r8
            r8 = 8
            r0.SIZE_DIGEST_UINT = r8
            r0.ShakeBitStrength = r11
            r8 = 512(0x200, float:7.175E-43)
        L_0x02c1:
            r0.Sha3BitStrength = r8
        L_0x02c3:
            org.bouncycastle.crypto.digests.SHA3Digest r8 = new org.bouncycastle.crypto.digests.SHA3Digest
            int r12 = r0.Sha3BitStrength
            r8.<init>((int) r12)
            r0.sha3Digest = r8
            int r8 = r0.NB_COEFS_HFEPOLY
            int r12 = r0.NB_MONOMIAL_VINEGAR
            r25 = 1
            int r12 = r12 + -1
            int r8 = r8 + r12
            int r3 = r3 * r26
            int r8 = r8 + r3
            int r3 = r0.NB_WORD_GFqn
            int r8 = r8 * r3
            r0.NB_UINT_HFEVPOLY = r8
            int r9 = r9 * r3
            r0.MLv_GFqn_SIZE = r9
            r3 = 34
            if (r15 <= r3) goto L_0x02ec
            r3 = 196(0xc4, float:2.75E-43)
            if (r2 <= r3) goto L_0x030f
            if (r15 >= r11) goto L_0x030f
        L_0x02ec:
            r3 = 17
            if (r15 != r3) goto L_0x02f2
            r3 = 4
            goto L_0x02f3
        L_0x02f2:
            r3 = 6
        L_0x02f3:
            r0.II = r3
            int r3 = r0.II
            r8 = 1
            int r9 = r8 << r3
            r0.POW_II = r9
            int r3 = r15 >>> r3
            int r8 = r15 % r9
            if (r8 == 0) goto L_0x0305
            r17 = 1
            goto L_0x0307
        L_0x0305:
            r17 = 0
        L_0x0307:
            int r3 = r3 + r17
            r0.KP = r3
            int r3 = r15 - r3
            r0.KX = r3
        L_0x030f:
            if (r19 == 0) goto L_0x034a
            r3 = 544(0x220, float:7.62E-43)
            if (r2 != r3) goto L_0x032d
            if (r13 != r4) goto L_0x032d
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM544_PENTANOMIAL_K3_IS_128_GF2X r3 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM544_PENTANOMIAL_K3_IS_128_GF2X
            r16 = r3
            r17 = r1
            r18 = r19
            r19 = r10
            r20 = r24
            r21 = r30
            r22 = r31
            r23 = r6
            r16.<init>(r17, r18, r19, r20, r21, r22, r23)
            goto L_0x0346
        L_0x032d:
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM544_PENTANOMIAL_GF2X r3 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM544_PENTANOMIAL_GF2X
            r16 = r3
            r17 = r1
            r18 = r19
            r19 = r13
            r20 = r10
            r21 = r24
            r22 = r30
            r23 = r31
            r24 = r32
            r25 = r6
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25)
        L_0x0346:
            r0.rem = r3
            goto L_0x03ef
        L_0x034a:
            if (r2 <= r11) goto L_0x0368
            r1 = 289(0x121, float:4.05E-43)
            if (r2 >= r1) goto L_0x0368
            if (r13 <= r5) goto L_0x0368
            r1 = 64
            if (r13 >= r1) goto L_0x0368
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM288_SPECIALIZED_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM288_SPECIALIZED_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            goto L_0x037d
        L_0x0368:
            r1 = 354(0x162, float:4.96E-43)
            if (r2 != r1) goto L_0x0381
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_SPECIALIZED_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_SPECIALIZED_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
        L_0x037d:
            r0.rem = r1
            goto L_0x03ef
        L_0x0381:
            r1 = 358(0x166, float:5.02E-43)
            if (r2 != r1) goto L_0x0397
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_SPECIALIZED358_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_SPECIALIZED358_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            goto L_0x037d
        L_0x0397:
            r1 = 402(0x192, float:5.63E-43)
            if (r2 != r1) goto L_0x03ad
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM402_SPECIALIZED_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM402_SPECIALIZED_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            goto L_0x037d
        L_0x03ad:
            r1 = 6
            if (r14 == r1) goto L_0x03dd
            r1 = 9
            if (r14 == r1) goto L_0x03cb
            r1 = 12
            if (r14 == r1) goto L_0x03b9
            goto L_0x03ef
        L_0x03b9:
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM384_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            goto L_0x037d
        L_0x03cb:
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM288_SPECIALIZED_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM288_SPECIALIZED_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            goto L_0x037d
        L_0x03dd:
            org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM192_SPECIALIZED_TRINOMIAL_GF2X r1 = new org.bouncycastle.pqc.crypto.gemss.Rem_GF2n$REM192_SPECIALIZED_TRINOMIAL_GF2X
            r16 = r1
            r17 = r13
            r18 = r10
            r19 = r24
            r20 = r32
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            goto L_0x037d
        L_0x03ef:
            org.bouncycastle.pqc.crypto.gemss.Pointer r1 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r1.<init>((int) r14)
            r0.Buffer_NB_WORD_MUL = r1
            org.bouncycastle.pqc.crypto.gemss.Pointer r1 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            int r3 = r0.NB_WORD_GFqn
            r1.<init>((int) r3)
            r0.Buffer_NB_WORD_GFqn = r1
            r1 = 31
            r0.HFEn_1rightmost = r1
        L_0x0403:
            int r3 = r0.HFEn_1rightmost
            int r4 = r28 >>> r3
            if (r4 != 0) goto L_0x040e
            int r3 = r3 + -1
            r0.HFEn_1rightmost = r3
            goto L_0x0403
        L_0x040e:
            r3 = 1
            int r2 = r2 + r3
            int r2 = r2 >>> r3
        L_0x0411:
            r0.HFEn1h_rightmost = r1
            int r1 = r0.HFEn1h_rightmost
            int r4 = r2 >>> r1
            if (r4 != 0) goto L_0x041c
            int r1 = r1 + -1
            goto L_0x0411
        L_0x041c:
            int r1 = r1 - r3
            r0.HFEn1h_rightmost = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.<init>(int, int, int, int, int, int, int, int):void");
    }

    private void CMP_AND_SWAP_CST_TIME(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = pointer;
        Pointer pointer5 = pointer2;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i = this.NB_WORD_GFqn - 1; i > 0; i--) {
            j2 |= GeMSSUtils.ORBITS_UINT(pointer5.get(i) ^ pointer.get(i));
            j3 += j2;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.NB_WORD_GFqn;
            if (i2 < i3) {
                j |= (-GeMSSUtils.NORBITS_UINT(((long) i2) ^ j3)) & GeMSSUtils.CMP_LT_UINT(pointer5.get(i2), pointer.get(i2));
                i2++;
            } else {
                pointer3.setRangeFromXorAndMask_xor(pointer, pointer2, -j, i3);
                return;
            }
        }
    }

    private void LOOPIR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3, int i4, boolean z) {
        int i5 = i;
        for (int i6 = 0; i6 < i5; i6++) {
            Pointer pointer4 = new Pointer(pointer3);
            int i7 = i2;
            int i8 = 1;
            while (i8 <= i7) {
                LOOPJR(pointer, pointer2, pointer4, 64, i4, i8);
                i8++;
            }
            if (z) {
                LOOPJR(pointer, pointer2, pointer4, i3, i4, i8);
            }
            Pointer pointer5 = pointer2;
            pointer2.move(i4);
        }
    }

    private void LOOPIR_INIT(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2) {
        while (i < i2) {
            pointer.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.changeIndex(pointer3);
            LOOPK_COMPLETE(pointer, pointer4, pointer2, 0, this.HFEnvq);
            pointer4.move(this.NB_WORD_GF2nv);
            i++;
        }
    }

    private void LOOPIR_LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        while (i < i2) {
            LOOPK_COMPLETE(pointer, pointer2, pointer3, 0, this.HFEnvq);
            i++;
        }
    }

    private void LOOPJR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3) {
        int min = Math.min(i2, i3);
        pointer.set(0);
        for (int i4 = 0; i4 < i; i4++) {
            pointer.setXor(GeMSSUtils.XORBITS_UINT(pointer2.getDotProduct(0, pointer3, 0, min)) << i4);
            pointer3.move(i3);
        }
        pointer.moveIncremental();
    }

    private long LOOPJR_NOCST_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        while (i < i2) {
            if ((1 & j) != 0) {
                pointer.setXorRange(0, pointerUnion, 0, i4);
            }
            pointerUnion.moveNextBytes(i3);
            j >>>= 1;
            i++;
        }
        return j;
    }

    private void LOOPJR_UNROLLED_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        int i5 = i;
        long j2 = j;
        while (i5 < i2 - 3) {
            j2 = LOOPJR_NOCST_64(pointer, pointerUnion, 0, 4, j2, i3, i4);
            i5 += 4;
        }
        LOOPJR_NOCST_64(pointer, pointerUnion, i5, i2, j2, i3, i4);
    }

    private void LOOPKR(Pointer pointer, Pointer pointer2, long j, int i, int i2) {
        while (i < i2) {
            pointer2.setXorRangeAndMaskMove(pointer, this.NB_WORD_GFqn, -(1 & j));
            j >>>= 1;
            i++;
        }
    }

    private void LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        while (i < i2) {
            LOOPKR(pointer3, pointer, pointer2.get(i), 0, 64);
            i++;
        }
        if (this.HFEnvr != 0) {
            LOOPKR(pointer3, pointer, pointer2.get(i2), 0, this.HFEnvr);
        }
        pointer.move(this.NB_WORD_GFqn);
    }

    private int chooseRootHFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx, Pointer pointer2) {
        Pointer pointer3 = pointer;
        SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx2 = complete_sparse_monic_gf2nx;
        Pointer pointer4 = pointer2;
        Pointer pointer5 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer6 = new Pointer(((this.HFEDeg << 1) - 1) * this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer((this.HFEDeg + 1) * this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(this.NB_WORD_GFqn);
        pointer8.setRangeFromXor(complete_sparse_monic_gf2nx2.poly, pointer4, this.NB_WORD_GFqn);
        int i = this.HFEDeg;
        if (i <= 34 || (this.HFEn > 196 && i < 256)) {
            frobeniusMap_multisqr_HFE_gf2nx(pointer6, complete_sparse_monic_gf2nx2, pointer8);
        } else {
            int i2 = 2 << this.HFEDegI;
            pointer6.set(this.NB_WORD_GFqn * i2, 1);
            Pointer pointer9 = pointer6;
            divsqr_r_HFE_cstdeg_gf2nx(pointer9, i2, i2, this.HFEDeg, complete_sparse_monic_gf2nx, pointer8);
            for_sqr_divsqr(pointer9, this.HFEDegI + 1, this.HFEn, complete_sparse_monic_gf2nx, pointer8);
        }
        pointer6.setXor(this.NB_WORD_GFqn, 1);
        int index = pointer7.getIndex();
        pointer7.copyFrom(complete_sparse_monic_gf2nx2.poly, this.NB_WORD_GFqn);
        for_copy_move(pointer7, complete_sparse_monic_gf2nx2);
        pointer7.changeIndex(index);
        pointer7.set(this.HFEDeg * this.NB_WORD_GFqn, 1);
        pointer7.setXorRange(pointer4, this.NB_WORD_GFqn);
        int gcd_gf2nx = gcd_gf2nx(pointer7, this.HFEDeg, pointer6, pointer6.getD_for_not0_or_plus(this.NB_WORD_GFqn, this.HFEDeg - 1));
        if (this.buffer != 0) {
            pointer6.swap(pointer7);
        }
        if (pointer6.is0_gf2n(0, this.NB_WORD_GFqn) == 0) {
            return 0;
        }
        convMonic_gf2nx(pointer7, gcd_gf2nx);
        Pointer pointer10 = new Pointer(this.NB_WORD_GFqn * gcd_gf2nx);
        findRootsSplit_gf2nx(pointer10, pointer7, gcd_gf2nx);
        if (gcd_gf2nx == 1) {
            pointer3.copyFrom(pointer10, this.NB_WORD_GFqn);
        } else {
            fast_sort_gf2n(pointer10, gcd_gf2nx);
            getSHA3Hash(pointer5, 0, this.Sha3BitStrength >>> 3, pointer4.toBytes(this.NB_BYTES_GFqn), 0, this.NB_BYTES_GFqn, new byte[(this.Sha3BitStrength >>> 3)]);
            int remainderUnsigned = (int) remainderUnsigned(pointer5.get(), (long) gcd_gf2nx);
            int i3 = this.NB_WORD_GFqn;
            pointer3.copyFrom(0, pointer10, remainderUnsigned * i3, i3);
        }
        return gcd_gf2nx;
    }

    private void choose_LOOPJR(Pointer pointer, PointerUnion pointerUnion, int i, long j, int i2, int i3) {
        int i4 = this.HFEnvr;
        if (i4 < 8) {
            LOOPJR_NOCST_64(pointer, pointerUnion, i, i4, j, i2, i3);
        } else {
            LOOPJR_UNROLLED_64(pointer, pointerUnion, i, i4, j, i2, i3);
        }
    }

    private long convMQ_last_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i = this.HFEnv - 1;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int for_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, i2);
        if (i3 != 0) {
            for_setpk2_end_move_plus = setPk2Value(pointer, pointerUnion2, for_setpk2_end_move_plus, i2, i3 + 1);
        }
        int i4 = this.HFEnv;
        int i5 = this.LOST_BITS;
        int i6 = i4 - i5;
        int i7 = i6 >>> 6;
        int i8 = i6 & 63;
        if (i8 != 0) {
            int i9 = for_setpk2_end_move_plus & 63;
            if (i9 != 0) {
                int i10 = this.NB_MONOMIAL_PK;
                if (((((i10 - i5) + 7) >>> 3) & 7) != 0) {
                    int i11 = (i4 - ((64 - (((i10 - i5) - this.HFEnvr) & 63)) & 63)) >>> 6;
                    pointer.setRangePointerUnion_Check(pointerUnion2, i11, for_setpk2_end_move_plus);
                    pointer.set(i11, pointerUnion2.getWithCheck(i11) >>> i9);
                    if (i11 < i7) {
                        int i12 = i11 + 1;
                        long withCheck = pointerUnion2.getWithCheck(i12);
                        pointer.setXor(i11, withCheck << (64 - i9));
                        pointer.set(i12, withCheck >>> i9);
                    } else if (i8 + i9 > 64) {
                        pointer.setXor(i11, pointerUnion2.getWithCheck(i11 + 1) << (64 - i9));
                    }
                } else {
                    pointer.setRangePointerUnion(pointerUnion2, i7, i9);
                    pointer.set(i7, pointerUnion2.get(i7) >>> i9);
                    if (i8 + i9 > 64) {
                        pointer.setXor(i7, pointerUnion2.get(i7 + 1) << (64 - i9));
                    }
                }
            } else if (((((this.NB_MONOMIAL_PK - i5) + 7) >>> 3) & 7) != 0) {
                pointer.setRangePointerUnion(pointerUnion2, i7);
                pointer.set(i7, pointerUnion2.getWithCheck(i7));
            } else {
                i7++;
            }
            return pointerUnion.get() & 1;
        }
        if (i7 != 0) {
            int i13 = for_setpk2_end_move_plus & 63;
            if (i13 != 0) {
                if (((((this.NB_MONOMIAL_PK - i5) + 7) >>> 3) & 7) != 0) {
                    int i14 = i7 - 1;
                    pointer.setRangePointerUnion(pointerUnion2, i14, i13);
                    pointer.set(i14, pointerUnion2.get(i14) >>> i13);
                    pointer.setXor(i14, pointerUnion2.getWithCheck(i7) << (64 - i13));
                } else {
                    pointer.setRangePointerUnion(pointerUnion2, i7, i13);
                }
            }
        }
        return pointerUnion.get() & 1;
        pointer.setRangePointerUnion(pointerUnion2, i7);
        return pointerUnion.get() & 1;
    }

    private long convMQ_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int for_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, this.HFEnvq);
        int i = this.HFEnvr;
        if (i != 0) {
            int i2 = i + 1;
            setPk2Value(pointer, pointerUnion2, for_setpk2_end_move_plus, this.HFEnvq, i2);
        }
        return pointerUnion.get() & 1;
    }

    private void convMonic_gf2nx(Pointer pointer, int i) {
        Pointer pointer2 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        inv_gf2n(pointer2, pointer, 0);
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        while (true) {
            i--;
            if (i != -1) {
                pointer.move(-this.NB_WORD_GFqn);
                mul_gf2n(pointer, pointer, pointer2);
            } else {
                pointer.changeIndex(index);
                return;
            }
        }
    }

    private void copy_for_casct(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, Pointer pointer5, int i, int i2) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        while (i > 1) {
            pointer4.changeIndex(pointer3, (i2 + i) * this.NB_WORD_GFqn);
            CMP_AND_SWAP_CST_TIME(pointer, pointer4, pointer5);
            i >>>= 1;
        }
    }

    private void copy_move_matrix_move(Pointer pointer, Pointer pointer2, int i) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer2, this.NB_WORD_GFqn, i);
        pointer2.move(this.NB_WORD_GFqn * (this.HFEv + 1));
    }

    private void div_q_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            int searchDegree = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (searchDegree >= i2) {
                pointer3.changeIndex(pointer, this.NB_WORD_GFqn * searchDegree);
                int max = Math.max(0, (i2 << 1) - searchDegree);
                pointer4.changeIndex(pointer, ((searchDegree - i2) + max) * this.NB_WORD_GFqn);
                for_mul_rem_xor_move(pointer4, pointer3, pointer2, max, i2);
                i = searchDegree - 1;
            } else {
                return;
            }
        }
    }

    private void div_r_monic_cst_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        while (i >= i2) {
            pointer3.changeIndex(pointer, (-i2) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer3, pointer, pointer2, 0, i2);
            pointer.move(-this.NB_WORD_GFqn);
            i--;
        }
        pointer.changeIndex(index);
    }

    private int div_r_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * i);
            pointer4.changeIndex(pointer3, (-i2) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, 0, i2);
            i--;
        }
        if (i == -1) {
            i++;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    private void divsqr_r_HFE_cstdeg_gf2nx(Pointer pointer, int i, int i2, int i3, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx, Pointer pointer2) {
        Pointer pointer3 = new Pointer(pointer, i * this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer();
        while (i2 >= i3) {
            pointer4.changeIndex(pointer3, (-this.HFEDeg) * this.NB_WORD_GFqn);
            mul_rem_xorrange(pointer4, pointer3, pointer2);
            for (int i4 = 1; i4 < this.NB_COEFS_HFEPOLY; i4++) {
                pointer4.move(complete_sparse_monic_gf2nx.L[i4]);
                mul_rem_xorrange(pointer4, pointer3, complete_sparse_monic_gf2nx.poly, this.NB_WORD_GFqn * i4);
            }
            pointer3.move(-this.NB_WORD_GFqn);
            i2--;
        }
    }

    private void dotProduct_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        int index = pointer2.getIndex();
        int index2 = pointer3.getIndex();
        mul_move(pointer4, pointer2, pointer3);
        for_mul_xorrange_move(pointer4, pointer2, pointer3, i - 1);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
        pointer3.changeIndex(index2);
    }

    private void dotproduct_move_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        dotProduct_gf2n(pointer, pointer3, pointer2, i);
        pointer.move(this.NB_WORD_GFqn);
        pointer2.move((i + this.HFEv + 1) * this.NB_WORD_GFqn);
    }

    private void evalMQShybrid8_uncomp_nocst_gf2_m(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion2);
        evalMQSnocst8_quo_gf2(pointer, pointer2, pointerUnion);
        if (this.HFEmr < 8) {
            pointer.set(this.HFEmq, 0);
        }
        for (int i = this.HFEmr - this.HFEmr8; i < this.HFEmr; i++) {
            pointer.setXor(this.HFEmq, evalMQnocst_unrolled_no_simd_gf2(pointer2, pointerUnion3) << i);
            pointerUnion3.move(this.NB_WORD_UNCOMP_EQ);
        }
    }

    private void evalMQSnocst8_quo_gf2(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        PointerUnion pointerUnion2;
        int i7;
        Pointer pointer3 = pointer;
        Pointer pointer4 = pointer2;
        int i8 = this.HFEnv;
        int i9 = this.HFEm;
        if ((i9 >>> 3) != 0) {
            i9 = (i9 >>> 3) << 3;
        }
        int i10 = i9;
        int i11 = (i10 & 7) != 0 ? (i10 >>> 3) + 1 : i10 >>> 3;
        int i12 = (i11 >>> 3) + ((i11 & 7) != 0 ? 1 : 0);
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion);
        System.arraycopy(pointerUnion3.getArray(), 0, pointer.getArray(), pointer.getIndex(), i12);
        pointerUnion3.moveNextBytes(i11);
        int i13 = 0;
        while (true) {
            i = this.HFEnvq;
            if (i13 >= i) {
                break;
            }
            int i14 = i8;
            long j = pointer4.get(i13);
            int i15 = 0;
            while (i15 < 64) {
                if ((j & 1) != 0) {
                    pointer3.setXorRange(0, pointerUnion3, 0, i12);
                    pointerUnion3.moveNextBytes(i11);
                    i5 = i13;
                    i4 = i15;
                    PointerUnion pointerUnion4 = pointerUnion3;
                    i6 = i12;
                    LOOPJR_UNROLLED_64(pointer, pointerUnion3, i15 + 1, 64, j >>> 1, i11, i12);
                    int i16 = i5 + 1;
                    while (true) {
                        i7 = this.HFEnvq;
                        if (i16 >= i7) {
                            break;
                        }
                        LOOPJR_UNROLLED_64(pointer, pointerUnion4, 0, 64, pointer4.get(i16), i11, i6);
                        i16++;
                    }
                    if (this.HFEnvr != 0) {
                        choose_LOOPJR(pointer, pointerUnion4, 0, pointer4.get(i7), i11, i6);
                    }
                    pointerUnion2 = pointerUnion4;
                } else {
                    i5 = i13;
                    i4 = i15;
                    i6 = i12;
                    pointerUnion2 = pointerUnion3;
                    pointerUnion2.moveNextBytes(i14 * i11);
                }
                j >>>= 1;
                i15 = i4 + 1;
                i14--;
                pointerUnion3 = pointerUnion2;
                i12 = i6;
                i13 = i5;
            }
            int i17 = i12;
            PointerUnion pointerUnion5 = pointerUnion3;
            i13++;
            i8 = i14;
            i12 = i17;
        }
        int i18 = i12;
        PointerUnion pointerUnion6 = pointerUnion3;
        if (this.HFEnvr != 0) {
            long j2 = pointer4.get(i);
            int i19 = i8;
            long j3 = j2;
            int i20 = 0;
            while (i20 < this.HFEnvr) {
                if ((j3 & 1) != 0) {
                    int i21 = i18;
                    pointer3.setXorRange(0, pointerUnion6, 0, i21);
                    pointerUnion6.moveNextBytes(i11);
                    i3 = i21;
                    i2 = i20;
                    choose_LOOPJR(pointer, pointerUnion6, i20 + 1, j3 >>> 1, i11, i3);
                } else {
                    i3 = i18;
                    i2 = i20;
                    pointerUnion6.moveNextBytes(i19 * i11);
                }
                j3 >>>= 1;
                i20 = i2 + 1;
                i19--;
                i18 = i3;
            }
        }
        int i22 = i18;
        int i23 = i10 & 63;
        if (i23 != 0) {
            pointer3.setAnd(i22 - 1, (1 << i23) - 1);
        }
    }

    private long evalMQnocst_unrolled_no_simd_gf2(Pointer pointer, PointerUnion pointerUnion) {
        int i;
        Pointer pointer2 = pointer;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        long j = pointer.get();
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2++) {
            if ((1 & (j >>> i2)) != 0) {
                j2 ^= pointerUnion2.get(i2) & j;
            }
        }
        pointerUnion2.move(64);
        int i3 = 1;
        while (true) {
            int i4 = this.NB_WORD_GF2nv;
            if (i3 >= i4) {
                return GeMSSUtils.XORBITS_UINT(j2);
            }
            int i5 = i3 + 1;
            if (i4 != i5 || (i = this.HFEnvr) == 0) {
                i = 64;
            }
            long j3 = pointer2.get(i3);
            for (int i6 = 0; i6 < i; i6++) {
                if (((j3 >>> i6) & 1) != 0) {
                    j2 ^= pointerUnion2.getDotProduct(0, pointer2, 0, i5);
                }
                pointerUnion2.move(i5);
            }
            i3 = i5;
        }
    }

    private void findRootsSplit_gf2nx(Pointer pointer, Pointer pointer2, int i) {
        int i2;
        int gcd_gf2nx;
        int i3;
        if (i == 1) {
            pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        } else if ((this.HFEn & 1) == 0 || i != 2) {
            int i4 = (i << 1) - 1;
            Pointer pointer3 = new Pointer(this.NB_WORD_GFqn * i4);
            Pointer pointer4 = new Pointer(this.NB_WORD_GFqn * i);
            int i5 = i + 1;
            Pointer pointer5 = new Pointer(this.NB_WORD_GFqn * i5);
            Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
            while (true) {
                pointer3.setRangeClear(0, this.NB_WORD_GFqn * i4);
                pointer4.setRangeClear(0, this.NB_WORD_GFqn * i);
                do {
                    pointer4.fillRandom(this.NB_WORD_GFqn, this.random, this.NB_BYTES_GFqn);
                    pointer4.setAnd((this.NB_WORD_GFqn << 1) - 1, this.MASK_GF2n);
                    i2 = this.NB_WORD_GFqn;
                } while (pointer4.is0_gf2n(i2, i2) != 0);
                pointer5.copyFrom(pointer2, this.NB_WORD_GFqn * i5);
                traceMap_gf2nx(pointer4, pointer3, pointer5, i);
                gcd_gf2nx = gcd_gf2nx(pointer5, i, pointer4, pointer4.searchDegree(i - 1, 1, this.NB_WORD_GFqn));
                i3 = this.buffer;
                if (gcd_gf2nx != 0 && gcd_gf2nx != i) {
                    break;
                }
            }
            if (i3 != 0) {
                pointer4.swap(pointer5);
            }
            inv_gf2n(pointer6, pointer5, this.NB_WORD_GFqn * gcd_gf2nx);
            int i6 = this.NB_WORD_GFqn;
            pointer5.set1_gf2n(gcd_gf2nx * i6, i6);
            for_mul(pointer5, pointer6, gcd_gf2nx - 1);
            div_q_monic_gf2nx(pointer2, i, pointer5, gcd_gf2nx);
            findRootsSplit_gf2nx(pointer, pointer5, gcd_gf2nx);
            findRootsSplit_gf2nx(new Pointer(pointer, this.NB_WORD_GFqn * gcd_gf2nx), new Pointer(pointer2, this.NB_WORD_GFqn * gcd_gf2nx), i - gcd_gf2nx);
        } else {
            findRootsSplit2_HT_gf2nx(pointer, pointer2);
        }
    }

    private void for_and_xor_shift_incre_move(Pointer pointer, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            pointer.setAnd(j);
            pointer.setXor(1 << i3);
            j = (j << 1) + 1;
            pointer.move(i);
        }
    }

    private void for_casct_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i2;
        int i4 = 0;
        while (i4 < i) {
            CMP_AND_SWAP_CST_TIME(pointer, pointer2, pointer3);
            pointer.move(i3);
            pointer2.move(i3);
            i4 += i2;
        }
    }

    private void for_copy_move(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx) {
        for (int i = 1; i < this.NB_COEFS_HFEPOLY; i++) {
            pointer.move(complete_sparse_monic_gf2nx.L[i]);
            Pointer pointer2 = complete_sparse_monic_gf2nx.poly;
            int i2 = this.NB_WORD_GFqn;
            pointer.copyFrom(0, pointer2, i * i2, i2);
        }
    }

    private void for_mul(Pointer pointer, Pointer pointer2, int i) {
        Pointer pointer3 = new Pointer(pointer, this.NB_WORD_GFqn * i);
        while (i != -1) {
            mul_gf2n(pointer3, pointer3, pointer2);
            pointer3.move(-this.NB_WORD_GFqn);
            i--;
        }
    }

    private void for_mul_rem_xor_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i;
        while (i < i2) {
            mul_rem_xorrange(pointer, pointer2, pointer3, i3);
            pointer.move(this.NB_WORD_GFqn);
            i++;
            i3 += this.NB_WORD_GFqn;
        }
    }

    private int for_setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        bArr[i] = (byte) (bArr2[i2] & 3);
        int i4 = 2;
        for (int i5 = 2; i5 < i3; i5++) {
            int i6 = this.HFEnv;
            i4 = setPK(bArr, bArr2, i5, i, i2, i4, i6 - 1, i6 - i5);
        }
        return i4;
    }

    private int for_setpk2_end_move_plus(Pointer pointer, PointerUnion pointerUnion, int i) {
        int i2 = 0;
        int i3 = 1;
        while (i2 < i) {
            int pk2Value = setPk2Value(pointer, pointerUnion, i3, i2, 64);
            setPk2_endValue(pointer, pointerUnion, pk2Value, i2);
            i2++;
            pointerUnion.move(i2);
            pointer.move(i2);
            i3 = pk2Value + (i2 << 6);
        }
        return i3;
    }

    private void for_sqr_divsqr(Pointer pointer, int i, int i2, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx, Pointer pointer2) {
        while (i < i2) {
            sqr_gf2nx(pointer, this.HFEDeg - 1);
            int i3 = this.HFEDeg;
            divsqr_r_HFE_cstdeg_gf2nx(pointer, (i3 - 1) << 1, (i3 - 1) << 1, i3, complete_sparse_monic_gf2nx, pointer2);
            i++;
        }
    }

    private void frobeniusMap_multisqr_HFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx, Pointer pointer2) {
        Pointer pointer3 = pointer;
        Pointer pointer4 = new Pointer();
        Pointer pointer5 = new Pointer(this.HFEDeg * this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer();
        Pointer pointer7 = new Pointer(((this.KX * this.HFEDeg) + this.POW_II) * this.NB_WORD_GFqn);
        int i = (this.POW_II * this.KP) - this.HFEDeg;
        Pointer pointer8 = new Pointer(pointer7, this.NB_WORD_GFqn * i);
        pointer8.copyFrom(pointer2, this.NB_WORD_GFqn);
        for_copy_move(pointer8, complete_sparse_monic_gf2nx);
        int i2 = i - 1;
        divsqr_r_HFE_cstdeg_gf2nx(pointer7, i2 + this.HFEDeg, i2, 0, complete_sparse_monic_gf2nx, pointer2);
        int i3 = this.KP + 1;
        while (true) {
            int i4 = this.HFEDeg;
            if (i3 >= i4) {
                break;
            }
            pointer8.changeIndex(pointer7, i4 * this.NB_WORD_GFqn);
            pointer8.setRangeClear(0, this.POW_II * this.NB_WORD_GFqn);
            int i5 = this.POW_II;
            int i6 = this.NB_WORD_GFqn;
            pointer8.copyFrom(i5 * i6, pointer7, 0, this.HFEDeg * i6);
            pointer7.changeIndex(pointer8);
            int i7 = this.POW_II;
            divsqr_r_HFE_cstdeg_gf2nx(pointer7, this.HFEDeg + (i7 - 1), i7 - 1, 0, complete_sparse_monic_gf2nx, pointer2);
            i3++;
        }
        pointer7.indexReset();
        int i8 = (1 << this.HFEDegI) - this.KP;
        int i9 = this.HFEDeg;
        int i10 = this.NB_WORD_GFqn;
        pointer3.copyFrom(0, pointer7, i8 * i9 * i10, i9 * i10);
        int i11 = 0;
        while (true) {
            int i12 = this.HFEn;
            int i13 = this.HFEDegI;
            int i14 = this.II;
            if (i11 < ((i12 - i13) - i14) / i14) {
                loop_sqr(pointer5, pointer3);
                for (int i15 = 1; i15 < this.II; i15++) {
                    loop_sqr(pointer5, pointer5);
                }
                pointer6.changeIndex(pointer5, this.KP * this.NB_WORD_GFqn);
                pointer8.changeIndex(pointer7);
                pointer4.changeIndex(pointer3);
                for (int i16 = 0; i16 < this.HFEDeg; i16++) {
                    mul_gf2n(pointer4, pointer8, pointer6);
                    pointer4.move(this.NB_WORD_GFqn);
                    pointer8.move(this.NB_WORD_GFqn);
                }
                int i17 = this.KP;
                while (true) {
                    i17++;
                    if (i17 >= this.HFEDeg) {
                        break;
                    }
                    pointer6.move(this.NB_WORD_GFqn);
                    pointer4.changeIndex(pointer3);
                    for (int i18 = 0; i18 < this.HFEDeg; i18++) {
                        mul_rem_xorrange(pointer4, pointer8, pointer6);
                        pointer4.move(this.NB_WORD_GFqn);
                        pointer8.move(this.NB_WORD_GFqn);
                    }
                }
                for (int i19 = 0; i19 < this.KP; i19++) {
                    int i20 = this.NB_WORD_GFqn;
                    pointer3.setXorRange(this.POW_II * i19 * i20, pointer5, i19 * i20, i20);
                }
                i11++;
            } else {
                for_sqr_divsqr(pointer, 0, (i12 - i13) % i14, complete_sparse_monic_gf2nx, pointer2);
                return;
            }
        }
    }

    private int gcd_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        int i3;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        this.buffer = 0;
        int i4 = i;
        Pointer pointer4 = pointer;
        Pointer pointer5 = pointer2;
        int i5 = i4;
        while (i2 != 0) {
            if ((i2 << 1) > i5) {
                i3 = div_r_gf2nx(pointer4, i5, pointer5, i2);
            } else {
                inv_gf2n(pointer3, pointer5, this.NB_WORD_GFqn * i2);
                int i6 = this.NB_WORD_GFqn;
                pointer5.set1_gf2n(i2 * i6, i6);
                for_mul(pointer5, pointer3, i2 - 1);
                i3 = div_r_monic_gf2nx(pointer4, i5, pointer5, i2);
            }
            this.buffer = 1 - this.buffer;
            Pointer pointer6 = pointer4;
            pointer4 = pointer5;
            pointer5 = pointer6;
            int i7 = i2;
            i2 = i3;
            i5 = i7;
        }
        return i5;
    }

    private void getSHA3Hash(Pointer pointer, int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2) {
        this.sha3Digest.update(bArr, i3, i4);
        this.sha3Digest.doFinal(bArr2, 0);
        pointer.fill(i, bArr2, 0, i2);
    }

    private void initListDifferences_gf2nx(int[] iArr) {
        iArr[1] = this.NB_WORD_GFqn;
        int i = 2;
        int i2 = 0;
        while (i2 < this.HFEDegI) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i2) + 1 <= this.HFE_odd_degree) {
                iArr[i] = this.NB_WORD_GFqn;
                i = setArrayL(iArr, i + 1, 0, i2);
            } else {
                if (i2 != 0) {
                    iArr[i] = this.NB_WORD_GFqn << 1;
                    i++;
                }
                i = setArrayL(iArr, i, 1, i2);
            }
            i2++;
        }
        int i3 = this.HFEDegJ;
        if (i3 == 0) {
            return;
        }
        if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i2) + 1 <= this.HFE_odd_degree) {
            iArr[i] = this.NB_WORD_GFqn;
            setArrayL(iArr, i + 1, 0, i3 - 1);
            return;
        }
        iArr[i] = this.NB_WORD_GFqn << 1;
        setArrayL(iArr, i + 1, 1, i3 - 1);
    }

    private void inv_gf2n(Pointer pointer, Pointer pointer2, int i) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        for (int i2 = this.HFEn_1rightmost - 1; i2 != -1; i2--) {
            int i3 = (this.HFEn - 1) >>> (i2 + 1);
            sqr_gf2n(pointer3, pointer);
            for (int i4 = 1; i4 < i3; i4++) {
                sqr_gf2n(pointer3, pointer3);
            }
            mul_gf2n(pointer, pointer, pointer3);
            if ((((this.HFEn - 1) >>> i2) & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                mul_gf2n(pointer, pointer2, pointer3);
            }
        }
        sqr_gf2n(pointer, pointer);
        pointer2.changeIndex(index);
    }

    private void loop_sqr(Pointer pointer, Pointer pointer2) {
        for (int i = 0; i < this.HFEDeg; i++) {
            int i2 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i * i2, pointer2, i2 * i);
        }
    }

    private int loop_xor_loop_move_xorandmask_move(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2, int i3, int i4, int i5) {
        Pointer pointer5 = pointer;
        Pointer pointer6 = pointer2;
        int i6 = i2;
        int i7 = i5;
        int i8 = i;
        int i9 = i3;
        int i10 = 0;
        while (i10 < i9) {
            pointer5.setXor(i6, 1 << i10);
            pointer6.changeIndex(pointer5);
            pointer3.changeIndex(pointer4);
            int i11 = i4;
            for (int i12 = i8; i12 < i11; i12++) {
                pointer6.move(i7);
                pointer3.move((i12 >>> 6) + 1);
                pointer6.setXorRangeAndMask(pointer5, i6 + 1, -((pointer3.get() >>> i10) & 1));
            }
            Pointer pointer7 = pointer3;
            pointer5.move(i7);
            pointer4.move(i6 + 1);
            i10++;
            i8++;
        }
        return i8;
    }

    private void mulMatricesLU_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        boolean z;
        int i;
        int i2;
        int index = pointer.getIndex();
        int i3 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        if (i3 == 1) {
            i2 = this.HFEnq;
            i = this.HFEnr;
            z = true;
        } else if (i3 == 2) {
            int i4 = this.HFEnvq;
            int i5 = this.HFEnvr;
            i2 = i4;
            i = i5;
            z = i5 != 0;
        } else {
            throw new IllegalArgumentException("Invalid parameter for MULMATRICESLU_GF2");
        }
        Pointer pointer4 = new Pointer(pointer2);
        int i6 = 1;
        while (i6 <= i2) {
            LOOPIR(pointer, pointer4, pointer3, 64, i2, i, i6, z);
            i6++;
        }
        LOOPIR(pointer, pointer4, pointer3, i, i2, i, i6, z);
        pointer.changeIndex(index);
    }

    private void precSignHFE(SecretKeyHFE secretKeyHFE, Pointer[] pointerArr, byte[] bArr) {
        precSignHFESeed(secretKeyHFE, bArr);
        initListDifferences_gf2nx(secretKeyHFE.F_struct.L);
        Pointer pointer = new Pointer(secretKeyHFE.F_HFEv);
        Pointer pointer2 = new Pointer(this.NB_COEFS_HFEPOLY * this.NB_WORD_GFqn);
        Pointer pointer3 = new Pointer(pointer, this.MQv_GFqn_SIZE);
        int i = 0;
        pointerArr[0] = pointer3;
        pointer.changeIndex(pointer3, this.MLv_GFqn_SIZE);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * 2);
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 >= this.HFEDegI) {
                break;
            }
            if ((1 << i2) + 1 <= this.HFE_odd_degree || !this.ENABLED_REMOVE_ODD_DEGREE) {
                i3 = 0;
            }
            int i4 = i2 - i3;
            pointer4.copyFrom(pointer, this.NB_WORD_GFqn * i4);
            pointer.move(this.NB_WORD_GFqn * i4);
            pointer4.move(i4 * this.NB_WORD_GFqn);
            i2++;
            pointerArr[i2] = new Pointer(pointer);
            pointer.move(this.MLv_GFqn_SIZE);
            pointer4.move(this.NB_WORD_GFqn);
        }
        int i5 = this.HFEDegJ;
        if (i5 != 0) {
            if ((1 << i2) + 1 > this.HFE_odd_degree) {
                i = 1;
            }
            pointer4.copyFrom(pointer, (i5 - i) * this.NB_WORD_GFqn);
        }
        secretKeyHFE.F_struct.poly = new Pointer(pointer2);
    }

    private void precSignHFESeed(SecretKeyHFE secretKeyHFE, byte[] bArr) {
        int i = this.NB_UINT_HFEVPOLY + ((this.LTRIANGULAR_NV_SIZE + this.LTRIANGULAR_N_SIZE) << 1);
        secretKeyHFE.sk_uncomp = new Pointer(this.MATRIXnv_SIZE + i + this.MATRIXn_SIZE);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(this.ShakeBitStrength);
        sHAKEDigest.update(bArr, 0, this.SIZE_SEED_SK);
        int i2 = i << 3;
        byte[] bArr2 = new byte[i2];
        sHAKEDigest.doFinal(bArr2, 0, i2);
        secretKeyHFE.sk_uncomp.fill(0, bArr2, 0, i2);
        secretKeyHFE.S = new Pointer(secretKeyHFE.sk_uncomp, i);
        secretKeyHFE.T = new Pointer(secretKeyHFE.S, this.MATRIXnv_SIZE);
        secretKeyHFE.F_HFEv = new Pointer(secretKeyHFE.sk_uncomp);
        cleanMonicHFEv_gf2nx(secretKeyHFE.F_HFEv);
        Pointer pointer = new Pointer(secretKeyHFE.sk_uncomp, this.NB_UINT_HFEVPOLY);
        Pointer pointer2 = new Pointer(pointer, this.LTRIANGULAR_NV_SIZE);
        cleanLowerMatrix(pointer, FunctionParams.NV);
        cleanLowerMatrix(pointer2, FunctionParams.NV);
        mulMatricesLU_gf2(secretKeyHFE.S, pointer, pointer2, FunctionParams.NV);
        pointer.move(this.LTRIANGULAR_NV_SIZE << 1);
        pointer2.changeIndex(pointer, this.LTRIANGULAR_N_SIZE);
        cleanLowerMatrix(pointer, FunctionParams.N);
        cleanLowerMatrix(pointer2, FunctionParams.N);
        mulMatricesLU_gf2(secretKeyHFE.T, pointer, pointer2, FunctionParams.N);
    }

    private void rem_gf2n(Pointer pointer, int i, Pointer pointer2) {
        this.rem.rem_gf2n(pointer.array, i + pointer.getIndex(), pointer2.array);
    }

    private static long remainderUnsigned(long j, long j2) {
        return (j <= 0 || j2 <= 0) ? new BigInteger(1, Pack.longToBigEndian(j)).mod(new BigInteger(1, Pack.longToBigEndian(j2))).longValue() : j % j2;
    }

    private int setArrayL(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            iArr[i] = this.NB_WORD_GFqn << i2;
            i2++;
            i++;
        }
        return i;
    }

    private int setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        while (i5 >= i6) {
            int i7 = (i4 >>> 3) + i2;
            bArr[i7] = (byte) (bArr[i7] ^ (((bArr2[(i >>> 3) + i3] >>> (i & 7)) & 1) << (i4 & 7)));
            i += i5;
            i5--;
            i4++;
        }
        this.buffer = i;
        return i4;
    }

    private int setPk2Value(Pointer pointer, PointerUnion pointerUnion, int i, int i2, int i3) {
        for (int i4 = 1; i4 < i3; i4++) {
            int i5 = i & 63;
            if (i5 != 0) {
                pointer.setRangePointerUnion(pointerUnion, i2, i5);
                pointer.set(i2, pointerUnion.get(i2) >>> i5);
                int i6 = i5 + i4;
                if (i6 > 64) {
                    pointer.setXor(i2, pointerUnion.get(i2 + 1) << (64 - i5));
                }
                if (i6 >= 64) {
                    pointerUnion.moveIncremental();
                }
            } else {
                pointer.setRangePointerUnion(pointerUnion, i2 + 1);
            }
            pointerUnion.move(i2);
            pointer.setAnd(i2, (1 << i4) - 1);
            pointer.move(i2 + 1);
            i += (i2 << 6) + i4;
        }
        return i;
    }

    private void setPk2_endValue(Pointer pointer, PointerUnion pointerUnion, int i, int i2) {
        int i3 = i & 63;
        int i4 = i2 + 1;
        if (i3 != 0) {
            pointer.setRangePointerUnion(pointerUnion, i4, i3);
        } else {
            pointer.setRangePointerUnion(pointerUnion, i4);
        }
    }

    private void special_buffer(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        int i;
        int index = pointer2.getIndex();
        pointer2.move((this.NB_WORD_GFqn * (this.HFEv + 1)) << 1);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
        int i2 = 2;
        while (i2 < this.SIZE_ROW - 1) {
            copy_move_matrix_move(pointer, pointer4, i2 - 1);
            i2++;
        }
        if (this.ENABLED_REMOVE_ODD_DEGREE) {
            while (i2 < this.SIZE_ROW - 1) {
                copy_move_matrix_move(pointer, pointer4, i2 - 2);
                i2++;
            }
        }
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer4, this.NB_WORD_GFqn, this.HFEDegJ);
        for (int i3 = 0; i3 < this.HFEn - 1; i3++) {
            mul_gf2n(pointer, pointer3, pointer2);
            pointer.move(this.NB_WORD_GFqn);
            pointer4.changeIndex(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
            int i4 = 2;
            while (i4 < this.HFEDegI) {
                dotproduct_move_move(pointer, pointer4, pointer3, i4);
                i4++;
            }
            if (this.ENABLED_REMOVE_ODD_DEGREE) {
                pointer3.move(this.NB_WORD_GFqn);
                while (i4 < this.SIZE_ROW - 1) {
                    dotproduct_move_move(pointer, pointer4, pointer3, i4 - 1);
                    i4++;
                }
                pointer3.move(-this.NB_WORD_GFqn);
            }
            int i5 = this.HFEDegJ;
            if (i5 == 0) {
                pointer.copyFrom(pointer3, this.NB_WORD_GFqn);
                pointer.move(this.NB_WORD_GFqn);
                i = this.SIZE_ROW;
            } else {
                dotProduct_gf2n(pointer, pointer3, pointer4, i5);
                pointer3.move(this.HFEDegJ * this.NB_WORD_GFqn);
                pointer.setXorRange_SelfMove(pointer3, this.NB_WORD_GFqn);
                i = this.SIZE_ROW - this.HFEDegJ;
            }
            pointer3.move(i * this.NB_WORD_GFqn);
        }
        pointer.indexReset();
        pointer2.changeIndex(index);
        pointer3.indexReset();
    }

    private void sqr_gf2n(Pointer pointer, int i, Pointer pointer2, int i2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, i2 + pointer2.cp);
        rem_gf2n(pointer, i, this.Buffer_NB_WORD_MUL);
    }

    private void sqr_gf2n(Pointer pointer, Pointer pointer2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, pointer2.cp);
        this.rem.rem_gf2n(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    private void sqr_gf2nx(Pointer pointer, int i) {
        int i2 = this.NB_WORD_GFqn * i;
        int index = pointer.getIndex();
        pointer.move(i2);
        Pointer pointer2 = new Pointer(pointer, i2);
        for (int i3 = 0; i3 < i; i3++) {
            sqr_gf2n(pointer2, pointer);
            pointer.move(-this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
            pointer2.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
        }
        sqr_gf2n(pointer, pointer);
        pointer.changeIndex(index);
    }

    private void traceMap_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int i2;
        int i3 = 1;
        while (true) {
            i2 = 1 << i3;
            if (i2 >= i) {
                break;
            }
            int i4 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i4 << i3, pointer, i4 << (i3 - 1));
            i3++;
        }
        if (i3 < this.HFEn) {
            int i5 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer2, i5 << i3, pointer, i5 << (i3 - 1));
            div_r_monic_cst_gf2nx(pointer2, i2, pointer3, i);
            pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            for (int i6 = i3 + 1; i6 < this.HFEn; i6++) {
                int i7 = i - 1;
                sqr_gf2nx(pointer2, i7);
                div_r_monic_cst_gf2nx(pointer2, i7 << 1, pointer3, i);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            }
        }
    }

    private void uncompress_signHFE(Pointer pointer, byte[] bArr) {
        PointerUnion pointerUnion = new PointerUnion(pointer);
        int i = (1 << this.HFEnvr8) - 1;
        pointerUnion.fillBytes(0, bArr, 0, this.NB_BYTES_GFqnv);
        if (this.HFEnvr8 != 0) {
            pointerUnion.setAndByte(this.NB_BYTES_GFqnv - 1, (long) i);
        }
        int i2 = this.HFEnv;
        pointerUnion.moveNextBytes((this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7));
        for (int i3 = 1; i3 < this.NB_ITE; i3++) {
            byte b = i2 & 7;
            int min = Math.min(this.HFEDELTA + this.HFEv, (8 - b) & 7);
            if (b != 0) {
                pointerUnion.setXorByte(((bArr[i2 >>> 3] & 255) >>> b) << this.HFEmr8);
                int i4 = min - this.VAL_BITS_M;
                if (i4 >= 0) {
                    pointerUnion.moveNextByte();
                }
                if (i4 > 0) {
                    int i5 = i2 + this.VAL_BITS_M;
                    pointerUnion.setXorByte((bArr[i5 >>> 3] & 255) >>> (i5 & 7));
                    i2 = i5 + i4;
                } else {
                    i2 += min;
                }
            }
            int i6 = (this.HFEDELTA + this.HFEv) - min;
            byte b2 = (this.HFEm + min) & 7;
            if (b2 != 0) {
                for (int i7 = 0; i7 < ((i6 - 1) >>> 3); i7++) {
                    int i8 = i2 >>> 3;
                    pointerUnion.setXorByte((bArr[i8] & 255) << b2);
                    pointerUnion.moveNextByte();
                    pointerUnion.setXorByte((bArr[i8] & 255) >>> (8 - b2));
                    i2 += 8;
                }
                int i9 = i2 >>> 3;
                pointerUnion.setXorByte((bArr[i9] & 255) << b2);
                pointerUnion.moveNextByte();
                int i10 = ((i6 + 7) & 7) + 1;
                int i11 = 8 - b2;
                if (i10 > i11) {
                    pointerUnion.setByte((bArr[i9] & 255) >>> i11);
                    pointerUnion.moveNextByte();
                }
                i2 += i10;
            } else {
                for (int i12 = 0; i12 < ((i6 + 7) >>> 3); i12++) {
                    pointerUnion.setByte(bArr[i2 >>> 3]);
                    i2 += 8;
                    pointerUnion.moveNextByte();
                }
                i2 -= (8 - (i6 & 7)) & 7;
            }
            if (this.HFEnvr8 != 0) {
                pointerUnion.setAndByte(-1, (long) i);
            }
            pointerUnion.moveNextBytes(((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7));
        }
    }

    private void vmpv_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        vecMatProduct(pointer, pointer2, new Pointer(pointer3, this.NB_WORD_GFqn), FunctionParams.V);
        pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
        pointer3.move(this.MLv_GFqn_SIZE);
    }

    /* access modifiers changed from: package-private */
    public void changeVariablesMQS64_gf2(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = pointer;
        Pointer pointer4 = pointer2;
        Pointer pointer5 = new Pointer();
        int i = this.HFEnv;
        Pointer pointer6 = new Pointer(i * i * this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer(pointer3, this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(pointer6);
        Pointer pointer9 = new Pointer(pointer4);
        int i2 = 0;
        while (true) {
            int i3 = 64;
            if (i2 >= this.HFEnv) {
                break;
            }
            pointer5.changeIndex(pointer7);
            int i4 = 0;
            while (i4 < this.HFEnvq) {
                int i5 = 0;
                while (i5 < i3) {
                    int i6 = i4;
                    int i7 = i5;
                    LOOPKR(pointer5, pointer8, pointer9.get() >>> i5, i7, 64);
                    LOOPK_COMPLETE(pointer8, pointer9, pointer5, 1, this.HFEnvq - i6);
                    i5 = i7 + 1;
                    i2 = i2;
                    i4 = i6;
                    i3 = 64;
                }
                int i8 = i2;
                pointer9.moveIncremental();
                i4++;
                i3 = 64;
            }
            int i9 = i2;
            if (this.HFEnvr != 0) {
                for (int i10 = 0; i10 < this.HFEnvr; i10++) {
                    LOOPKR(pointer5, pointer8, pointer9.get() >>> i10, i10, this.HFEnvr);
                    pointer8.move(this.NB_WORD_GFqn);
                }
                pointer9.moveIncremental();
            }
            i2 = i9 + 1;
        }
        int i11 = 64;
        pointer7.changeIndex(pointer6);
        pointer8.changeIndex(pointer3, this.NB_WORD_GFqn);
        Pointer pointer10 = new Pointer(pointer4);
        int i12 = 0;
        while (i12 < this.HFEnvq) {
            int i13 = 0;
            while (i13 < i11) {
                pointer9.changeIndex(pointer10);
                int i14 = i13;
                int i15 = i12;
                Pointer pointer11 = pointer10;
                LOOPIR_INIT(pointer8, pointer5, pointer7, pointer9, i14, 64);
                for (int i16 = i15 + 1; i16 < this.HFEnvq; i16++) {
                    LOOPIR_INIT(pointer8, pointer5, pointer7, pointer9, 0, 64);
                }
                int i17 = this.HFEnvr;
                if (i17 != 0) {
                    LOOPIR_INIT(pointer8, pointer5, pointer7, pointer9, 0, i17);
                }
                pointer7.changeIndex(pointer5);
                pointer11.move(this.NB_WORD_GF2nv);
                i13 = i14 + 1;
                pointer10 = pointer11;
                i12 = i15;
                i11 = 64;
            }
            Pointer pointer12 = pointer10;
            i12++;
            i11 = 64;
        }
        Pointer pointer13 = pointer10;
        if (this.HFEnvr != 0) {
            for (int i18 = 0; i18 < this.HFEnvr; i18++) {
                pointer9.changeIndex(pointer13);
                pointer5.changeIndex(pointer7);
                LOOPIR_INIT(pointer8, pointer5, pointer7, pointer9, i18, this.HFEnvr);
                pointer7.changeIndex(pointer5);
                pointer13.move(this.NB_WORD_GF2nv);
            }
        }
        pointer7.changeIndex(pointer6);
        pointer8.changeIndex(pointer3, this.NB_WORD_GFqn);
        pointer9.changeIndex(pointer4);
        for (int i19 = 0; i19 < this.HFEnvq; i19++) {
            int i20 = 0;
            while (i20 < 64) {
                pointer8.move(this.NB_WORD_GFqn);
                pointer7.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer5.changeIndex(pointer7);
                int i21 = i20 + 1;
                LOOPIR_LOOPK_COMPLETE(pointer8, pointer9, pointer5, i21, 64);
                for (int i22 = i19 + 1; i22 < this.HFEnvq; i22++) {
                    LOOPIR_LOOPK_COMPLETE(pointer8, pointer9, pointer5, 0, 64);
                }
                int i23 = this.HFEnvr;
                if (i23 != 0) {
                    LOOPIR_LOOPK_COMPLETE(pointer8, pointer9, pointer5, 0, i23);
                }
                pointer9.move(this.NB_WORD_GF2nv);
                i20 = i21;
            }
        }
        if (this.HFEnvr != 0) {
            int i24 = 0;
            while (i24 < this.HFEnvr - 1) {
                pointer8.move(this.NB_WORD_GFqn);
                pointer7.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer5.changeIndex(pointer7);
                i24++;
                LOOPIR_LOOPK_COMPLETE(pointer8, pointer9, pointer5, i24, this.HFEnvr);
                pointer9.move(this.NB_WORD_GF2nv);
            }
        }
        pointer.indexReset();
        pointer2.indexReset();
    }

    /* access modifiers changed from: package-private */
    public void cleanLowerMatrix(Pointer pointer, FunctionParams functionParams) {
        int i;
        int i2;
        int i3 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        int i4 = 1;
        if (i3 == 1) {
            i = this.HFEnq;
            i2 = this.HFEnr;
        } else if (i3 == 2) {
            i = this.HFEnvq;
            i2 = this.HFEnvr;
        } else {
            throw new IllegalArgumentException("");
        }
        Pointer pointer2 = new Pointer(pointer);
        while (i4 <= i) {
            for_and_xor_shift_incre_move(pointer2, i4, 64);
            pointer2.moveIncremental();
            i4++;
        }
        for_and_xor_shift_incre_move(pointer2, i4, i2);
    }

    /* access modifiers changed from: package-private */
    public void cleanMonicHFEv_gf2nx(Pointer pointer) {
        int i = this.NB_WORD_GFqn - 1;
        while (i < this.NB_UINT_HFEVPOLY) {
            pointer.setAnd(i, this.MASK_GF2n);
            i += this.NB_WORD_GFqn;
        }
    }

    public void compress_signHFE(byte[] bArr, Pointer pointer) {
        int i;
        byte[] bytes = pointer.toBytes(pointer.getLength() << 3);
        System.arraycopy(bytes, 0, bArr, 0, this.NB_BYTES_GFqnv);
        int i2 = this.HFEnv;
        int i3 = (this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7);
        for (int i4 = 1; i4 < this.NB_ITE; i4++) {
            byte b = i2 & 7;
            int min = Math.min(this.HFEDELTA + this.HFEv, (8 - b) & 7);
            if (b != 0) {
                int i5 = this.HFEmr8;
                if (i5 != 0) {
                    int i6 = i2 >>> 3;
                    bArr[i6] = (byte) ((((bytes[i3] & 255) >>> i5) << b) ^ bArr[i6]);
                    int i7 = this.VAL_BITS_M;
                    int i8 = min - i7;
                    if (i8 >= 0) {
                        i3++;
                    }
                    if (i8 > 0) {
                        int i9 = i2 + i7;
                        int i10 = i9 >>> 3;
                        bArr[i10] = (byte) (bArr[i10] ^ ((bytes[i3] & 255) << (i9 & 7)));
                        i2 = i9 + i8;
                    }
                } else {
                    int i11 = i2 >>> 3;
                    bArr[i11] = (byte) (((bytes[i3] & 255) << b) ^ bArr[i11]);
                }
                i2 += min;
            }
            int i12 = (this.HFEDELTA + this.HFEv) - min;
            byte b2 = (this.HFEm + min) & 7;
            if (b2 != 0) {
                for (int i13 = 0; i13 < ((i12 - 1) >>> 3); i13++) {
                    i3++;
                    bArr[i2 >>> 3] = (byte) (((bytes[i3] & 255) >>> b2) ^ ((bytes[i3] & 255) << (8 - b2)));
                    i2 += 8;
                }
                int i14 = i2 >>> 3;
                i = i3 + 1;
                byte b3 = (byte) ((bytes[i3] & 255) >>> b2);
                bArr[i14] = b3;
                int i15 = ((i12 + 7) & 7) + 1;
                int i16 = 8 - b2;
                if (i15 > i16) {
                    bArr[i14] = (byte) (((byte) ((bytes[i] & 255) << i16)) ^ b3);
                    i = i3 + 2;
                }
                i2 += i15;
            } else {
                int i17 = 0;
                while (i17 < ((i12 + 7) >>> 3)) {
                    bArr[i2 >>> 3] = bytes[i3];
                    i2 += 8;
                    i17++;
                    i3++;
                }
                i2 -= (8 - (i12 & 7)) & 7;
                i = i3;
            }
            i3 = ((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7) + i;
        }
    }

    /* access modifiers changed from: package-private */
    public void convMQS_one_eq_to_hybrid_rep8_comp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        convMQ_UL_gf2(bArr, bArr2, this.HFEmr8);
        int i = 0;
        for (int i2 = 0; i2 < this.NB_MONOMIAL_PK; i2++) {
            i = pointerUnion.toBytesMove(bArr, i, this.HFEmq8);
            if (this.HFEmr8 != 0) {
                pointerUnion.moveNextByte();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void convMQS_one_eq_to_hybrid_rep8_uncomp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i = this.HFEmr8 - 1;
        convMQ_UL_gf2(bArr, bArr4, i);
        int i2 = this.ACCESS_last_equations8;
        int i3 = this.NB_BYTES_EQUATION;
        int i4 = i2 + (i * i3);
        int i5 = i * i3;
        byte[] bArr5 = bArr;
        byte[] bArr6 = bArr2;
        int for_setPK = for_setPK(bArr5, bArr6, i4, i5, this.HFEnv);
        int i6 = this.HFEnv;
        setPK(bArr5, bArr6, i6, i4, i5, for_setPK, i6 - 1, this.LOST_BITS);
        int i7 = this.buffer;
        long j = 0;
        for (int i8 = this.LOST_BITS - 1; i8 >= 0; i8--) {
            j ^= ((long) ((bArr4[(i7 >>> 3) + i5] >>> (i7 & 7)) & 1)) << ((this.LOST_BITS - 1) - i8);
            i7 += i8;
        }
        int i9 = this.ACCESS_last_equations8 - 1;
        for (int i10 = 0; i10 < this.HFEmr8 - 1; i10++) {
            i9 += this.NB_BYTES_EQUATION;
            bArr3[i9] = (byte) (bArr3[i9] ^ (((byte) ((int) (j >>> (this.HFENr8c * i10)))) << this.HFENr8));
        }
        pointerUnion.indexReset();
        int i11 = 0;
        for (int i12 = 0; i12 < this.NB_MONOMIAL_PK; i12++) {
            i11 = pointerUnion.toBytesMove(bArr, i11, this.HFEmq8);
            pointerUnion.moveNextByte();
        }
    }

    /* access modifiers changed from: package-private */
    public void convMQS_one_to_last_mr8_equations_gf2(byte[] bArr, PointerUnion pointerUnion) {
        int i;
        pointerUnion.moveNextBytes(this.HFEmq8);
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i2 = this.NB_MONOMIAL_PK >>> 3;
        int i3 = 0;
        for (int i4 = 0; i4 < this.HFEmr8; i4++) {
            pointerUnion2.changeIndex(pointerUnion);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                int i6 = (pointerUnion2.getByte() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (int i7 = 1; i7 < 8; i7++) {
                    i6 ^= ((pointerUnion2.getByte() >>> i4) & 1) << i7;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) i6;
                i5++;
                i3++;
            }
            if (this.HFENr8 != 0) {
                long withCheck = (pointerUnion2.getWithCheck() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (i = 1; i < this.HFENr8; i++) {
                    withCheck ^= ((pointerUnion2.getWithCheck() >>> i4) & 1) << i;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) ((int) withCheck);
                i3++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void convMQ_UL_gf2(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.ACCESS_last_equations8;
            int i4 = this.NB_BYTES_EQUATION;
            for_setPK(bArr, bArr2, i3 + (i2 * i4), i2 * i4, this.HFEnv + 1);
        }
    }

    public int crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long j;
        int i;
        long j2;
        byte[] bArr4 = bArr2;
        PointerUnion pointerUnion = new PointerUnion(bArr);
        int i2 = 0;
        long j3 = 0;
        if (this.HFENr8 == 0 || this.HFEmr8 <= 1) {
            j = 0;
        } else {
            PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
            pointerUnion2.moveNextBytes(this.ACCESS_last_equations8 - 1);
            j = 0;
            for (int i3 = 0; i3 < this.HFEmr8 - 1; i3++) {
                pointerUnion2.moveNextBytes(this.NB_BYTES_EQUATION);
                j ^= ((((long) pointerUnion2.getByte()) & 255) >>> this.HFENr8) << (this.HFENr8c * i3);
            }
        }
        int i4 = this.HFEmr8;
        if (i4 != 0) {
            Pointer pointer = new Pointer((this.NB_WORD_UNCOMP_EQ * i4) + 1);
            PointerUnion pointerUnion3 = new PointerUnion(pointerUnion);
            while (i2 < this.HFEmr8 - 1) {
                pointerUnion3.setByteIndex(this.ACCESS_last_equations8 + (this.NB_BYTES_EQUATION * i2));
                j3 ^= convMQ_uncompressL_gf2(new Pointer(pointer, (this.NB_WORD_UNCOMP_EQ * i2) + 1), pointerUnion3) << i2;
                i2++;
            }
            pointerUnion3.setByteIndex(this.ACCESS_last_equations8 + (this.NB_BYTES_EQUATION * i2));
            long convMQ_last_uncompressL_gf2 = j3 ^ (convMQ_last_uncompressL_gf2(new Pointer(pointer, (this.NB_WORD_UNCOMP_EQ * i2) + 1), pointerUnion3) << i2);
            if (this.HFENr8 != 0) {
                int i5 = this.HFEnvr;
                if (i5 == 0) {
                    i = (i2 + 1) * this.NB_WORD_UNCOMP_EQ;
                    j2 = j << (64 - this.LOST_BITS);
                } else {
                    int i6 = this.LOST_BITS;
                    int i7 = i2 + 1;
                    if (i5 > i6) {
                        i = i7 * this.NB_WORD_UNCOMP_EQ;
                        j2 = j << (i5 - i6);
                    } else if (i5 == i6) {
                        pointer.set(i7 * this.NB_WORD_UNCOMP_EQ, j);
                    } else {
                        pointer.setXor((this.NB_WORD_UNCOMP_EQ * i7) - 1, j << (64 - (i6 - i5)));
                        pointer.set(i7 * this.NB_WORD_UNCOMP_EQ, j >>> (this.LOST_BITS - this.HFEnvr));
                    }
                }
                pointer.setXor(i, j2);
            }
            pointer.set(convMQ_last_uncompressL_gf2 << (this.HFEmr - this.HFEmr8));
            return sign_openHFE_huncomp_pk(bArr2, bArr4.length, bArr3, pointerUnion, new PointerUnion(pointer));
        }
        Pointer pointer2 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer3 = new Pointer(new Pointer(this.NB_WORD_GF2nv));
        Pointer pointer4 = new Pointer(this.SIZE_DIGEST_UINT);
        pointer2.fill(0, bArr3, 0, this.NB_BYTES_GFqnv);
        getSHA3Hash(pointer4, 0, 64, bArr2, 0, bArr4.length, new byte[64]);
        evalMQSnocst8_quo_gf2(pointer3, pointer2, pointerUnion);
        return pointer3.isEqual_nocst_gf2(pointer4, this.NB_WORD_GF2m);
    }

    /* access modifiers changed from: package-private */
    public int div_r_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer);
        inv_gf2n(pointer4, pointer2, this.NB_WORD_GFqn * i2);
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer5.changeIndex((i - i2) * this.NB_WORD_GFqn);
            mul_gf2n(pointer3, pointer, this.NB_WORD_GFqn * i, pointer4);
            for_mul_rem_xor_move(pointer5, pointer3, pointer2, 0, i2);
            i--;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    /* access modifiers changed from: package-private */
    public void evalHFEv_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = pointer2;
        Pointer pointer5 = pointer3;
        Pointer pointer6 = new Pointer(this.NB_WORD_MUL);
        Pointer pointer7 = new Pointer(this.NB_WORD_MUL);
        Pointer pointer8 = new Pointer((this.HFEDegI + 1) * this.NB_WORD_GFqn);
        Pointer pointer9 = new Pointer();
        int index = pointer2.getIndex();
        Pointer pointer10 = new Pointer(this.NB_WORD_GFqv);
        Pointer pointer11 = new Pointer(pointer8, this.NB_WORD_GFqn);
        pointer8.copyFrom(pointer5, this.NB_WORD_GFqn);
        pointer8.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
        for (int i = 1; i <= this.HFEDegI; i++) {
            sqr_gf2n(pointer11, 0, pointer11, -this.NB_WORD_GFqn);
            pointer11.move(this.NB_WORD_GFqn);
        }
        int i2 = this.NB_WORD_GFqn;
        int i3 = this.NB_WORD_GFqv;
        if (i2 + i3 != this.NB_WORD_GF2nv) {
            i3--;
        }
        int i4 = i3;
        Pointer pointer12 = pointer11;
        pointer10.setRangeRotate(0, pointer3, i2 - 1, i4, 64 - this.HFEnr);
        int i5 = this.NB_WORD_GFqn;
        if (this.NB_WORD_GFqv + i5 != this.NB_WORD_GF2nv) {
            int i6 = i4;
            pointer10.set(i6, pointer5.get((i5 - 1) + i6) >>> this.HFEnr);
        }
        evalMQSv_unrolled_gf2(pointer6, pointer10, pointer4);
        pointer4.move(this.MQv_GFqn_SIZE);
        vmpv_xorrange_move(pointer7, pointer10, pointer4);
        Pointer pointer13 = pointer12;
        pointer13.changeIndex(pointer8);
        mul_xorrange(pointer6, pointer13, pointer7);
        for (int i7 = 1; i7 < this.HFEDegI; i7++) {
            vmpv_xorrange_move(pointer7, pointer10, pointer4);
            int i8 = this.NB_WORD_GFqn;
            pointer7.setRangeClear(i8, this.NB_WORD_MMUL - i8);
            pointer9.changeIndex(pointer13);
            for_mul_xorrange_move(pointer7, pointer4, pointer9, i7);
            rem_gf2n(pointer7, 0, pointer7);
            mul_xorrange(pointer6, pointer9, pointer7);
        }
        vmpv_xorrange_move(pointer7, pointer10, pointer4);
        pointer9.changeIndex(pointer13);
        if (this.HFEDegJ != 0) {
            int i9 = this.NB_WORD_GFqn;
            pointer7.setRangeClear(i9, this.NB_WORD_MMUL - i9);
            for_mul_xorrange_move(pointer7, pointer4, pointer9, this.HFEDegJ);
            pointer7.setXorRange(pointer9, this.NB_WORD_GFqn);
            rem_gf2n(pointer7, 0, pointer7);
        } else {
            pointer7.setRangeFromXor(pointer7, pointer9, this.NB_WORD_GFqn);
        }
        pointer13.move(this.HFEDegI * this.NB_WORD_GFqn);
        mul_xorrange(pointer6, pointer13, pointer7);
        rem_gf2n(pointer, 0, pointer6);
        pointer4.changeIndex(index);
    }

    /* access modifiers changed from: package-private */
    public void evalMQSv_unrolled_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.HFEv);
        int i = this.HFEv;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int i4 = this.HFEn;
        int i5 = 0;
        int i6 = (i4 >>> 6) + ((i4 & 63) != 0 ? 1 : 0);
        int index = pointer3.getIndex();
        Pointer pointer5 = new Pointer(i6);
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            i8 = pointer4.setRange_xi(pointer2.get(i7), i8, 64);
            i7++;
        }
        if (i3 != 0) {
            pointer4.setRange_xi(pointer2.get(i7), i8, i3);
        }
        pointer.copyFrom(pointer3, i6);
        pointer3.move(i6);
        while (i5 < this.HFEv) {
            pointer5.copyFrom(pointer3, i6);
            pointer3.move(i6);
            int i9 = i5 + 1;
            int i10 = i9;
            while (i10 < this.HFEv - 3) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10 + 1));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10 + 2));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10 + 3));
                i10 += 4;
            }
            while (i10 < this.HFEv) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10));
                i10++;
            }
            pointer.setXorRangeAndMask(pointer5, i6, pointer4.get(i5));
            i5 = i9;
        }
        pointer3.changeIndex(index);
    }

    /* access modifiers changed from: package-private */
    public void fast_sort_gf2n(Pointer pointer, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Pointer pointer2 = pointer;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer();
        int i6 = i - 1;
        int Highest_One = GeMSSUtils.Highest_One(i6);
        int i7 = Highest_One;
        while (true) {
            i2 = 0;
            if (i7 <= 1) {
                break;
            }
            int i8 = i7 << 1;
            int i9 = i / i8;
            int max = Math.max(0, (i - (i8 * i9)) - i7);
            pointer5.changeIndex(pointer2);
            pointer6.changeIndex(pointer2, this.NB_WORD_GFqn * i7);
            int i10 = 0;
            while (i10 < i9) {
                for_casct_move(pointer5, pointer6, pointer4, i7, 1);
                pointer5.move(this.NB_WORD_GFqn * i7);
                pointer6.move(this.NB_WORD_GFqn * i7);
                i10++;
                i9 = i9;
            }
            for_casct_move(pointer5, pointer6, pointer4, max, 1);
            int i11 = Highest_One;
            while (i11 > i7) {
                while (i2 < i - i11) {
                    if ((i2 & i7) == 0) {
                        pointer6.changeIndex(pointer2, (i2 + i7) * this.NB_WORD_GFqn);
                        i5 = i11;
                        i4 = i2;
                        i3 = i7;
                        copy_for_casct(pointer3, pointer6, pointer, pointer5, pointer4, i5, i4);
                        pointer6.copyFrom(pointer3, this.NB_WORD_GFqn);
                    } else {
                        i5 = i11;
                        i4 = i2;
                        i3 = i7;
                    }
                    i2 = i4 + 1;
                    i11 = i5;
                    i7 = i3;
                }
                int i12 = i2;
                int i13 = i7;
                i11 >>>= 1;
            }
            i7 >>>= 1;
        }
        pointer5.changeIndex(pointer2);
        pointer6.changeIndex(pointer2, this.NB_WORD_GFqn);
        for_casct_move(pointer5, pointer6, pointer4, i6, 2);
        pointer6.changeIndex(pointer2, this.NB_WORD_GFqn);
        while (Highest_One > 1) {
            int i14 = i2;
            while (i14 < i - Highest_One) {
                copy_for_casct(pointer3, pointer6, pointer, pointer5, pointer4, Highest_One, i14);
                pointer6.copyFrom(pointer3, this.NB_WORD_GFqn);
                pointer6.move(this.NB_WORD_GFqn << 1);
                i14 += 2;
            }
            Highest_One >>>= 1;
            i2 = i14;
        }
    }

    /* access modifiers changed from: package-private */
    public void findRootsSplit2_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer2.getIndex();
        sqr_gf2n(pointer3, 0, pointer2, this.NB_WORD_GFqn);
        inv_gf2n(pointer, pointer3, 0);
        mul_gf2n(pointer3, pointer2, pointer);
        findRootsSplit_x2_x_c_HT_gf2nx(pointer4, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        mul_gf2n(pointer, pointer4, pointer2);
        int i = this.NB_WORD_GFqn;
        pointer.setRangeFromXor(i, pointer, 0, pointer2, 0, i);
        pointer2.changeIndex(index);
    }

    /* access modifiers changed from: package-private */
    public void findRootsSplit_x2_x_c_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        int i = (this.HFEn + 1) >>> 1;
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        int i2 = 1;
        for (int i3 = this.HFEn1h_rightmost; i3 != -1; i3--) {
            int i4 = i2 << 1;
            sqr_gf2n(pointer3, pointer);
            for (int i5 = 1; i5 < i4; i5++) {
                sqr_gf2n(pointer3, pointer3);
            }
            pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
            i2 = i >>> i3;
            if ((i2 & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                sqr_gf2n(pointer, pointer3);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void for_mul_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
            pointer2.move(this.NB_WORD_GFqn);
            pointer3.move(this.NB_WORD_GFqn);
        }
    }

    /* access modifiers changed from: package-private */
    public void genSecretMQS_gf2_opt(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = pointer;
        Pointer pointer4 = pointer2;
        Pointer pointer5 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer((this.HFEDegI + 1) * (this.HFEv + 1) * this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer(pointer4, this.MQv_GFqn_SIZE);
        for (int i = 0; i <= this.HFEDegI; i++) {
            for (int i2 = 0; i2 <= this.HFEv; i2++) {
                int i3 = this.NB_WORD_GFqn;
                pointer6.copyFrom((((this.HFEDegI + 1) * i2) + i) * i3, pointer7, 0, i3);
                pointer7.move(this.NB_WORD_GFqn);
            }
            pointer7.move(this.NB_WORD_GFqn * i);
        }
        Pointer pointer8 = new Pointer(this.SIZE_ROW * (this.HFEn - 1) * this.NB_WORD_GFqn);
        for (int i4 = 1; i4 < this.HFEn; i4++) {
            pointer8.set(i4 >>> 6, 1 << (i4 & 63));
            for (int i5 = 0; i5 < this.HFEDegI; i5++) {
                sqr_gf2n(pointer8, this.NB_WORD_GFqn, pointer8, 0);
                pointer8.move(this.NB_WORD_GFqn);
            }
            pointer8.move(this.NB_WORD_GFqn);
        }
        pointer8.indexReset();
        pointer3.copyFrom(pointer4, this.NB_WORD_GFqn);
        pointer4.move(this.MQv_GFqn_SIZE);
        pointer3.move(this.NB_WORD_GFqn);
        Pointer pointer9 = new Pointer(this.HFEDegI * this.HFEn * this.NB_WORD_GFqn);
        special_buffer(pointer9, pointer4, pointer8);
        Pointer pointer10 = new Pointer(pointer9);
        Pointer pointer11 = new Pointer(pointer9);
        pointer3.copyFrom(pointer11, this.NB_WORD_GFqn);
        pointer11.move(this.NB_WORD_GFqn);
        pointer3.setXorMatrix_NoMove(pointer11, this.NB_WORD_GFqn, this.HFEDegI - 1);
        pointer7.changeIndex(pointer6);
        pointer3.setXorMatrix(pointer7, this.NB_WORD_GFqn, this.HFEDegI + 1);
        Pointer pointer12 = new Pointer(pointer8, this.NB_WORD_GFqn);
        int i6 = 1;
        while (i6 < this.HFEn) {
            dotProduct_gf2n(pointer3, pointer12, pointer10, this.HFEDegI);
            pointer12.move(this.SIZE_ROW * this.NB_WORD_GFqn);
            pointer3.setXorMatrix(pointer11, this.NB_WORD_GFqn, this.HFEDegI);
            i6++;
        }
        while (i6 < this.HFEnv) {
            pointer3.copyFrom(pointer7, this.NB_WORD_GFqn);
            pointer7.move(this.NB_WORD_GFqn);
            pointer3.setXorMatrix(pointer7, this.NB_WORD_GFqn, this.HFEDegI);
            i6++;
        }
        Pointer pointer13 = new Pointer(pointer8, this.NB_WORD_GFqn);
        Pointer pointer14 = new Pointer(this.NB_WORD_MUL);
        int i7 = 1;
        while (i7 < this.HFEn) {
            pointer10.move(this.HFEDegI * this.NB_WORD_GFqn);
            pointer12.changeIndex(pointer13);
            pointer11.changeIndex(pointer10);
            this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer6, new Pointer(pointer12, -this.NB_WORD_GFqn));
            int i8 = 1;
            while (i8 <= this.HFEDegI) {
                int i9 = this.NB_WORD_GFqn;
                Pointer pointer15 = pointer11;
                pointer5.setRangeFromXor(0, pointer11, 0, pointer6, i8 * i9, i9);
                Pointer pointer16 = pointer12;
                mul_xorrange(this.Buffer_NB_WORD_MUL, pointer5, pointer16);
                pointer15.move(this.NB_WORD_GFqn);
                pointer16.move(this.NB_WORD_GFqn);
                i8++;
                pointer11 = pointer15;
                pointer12 = pointer16;
                pointer13 = pointer13;
                i7 = i7;
                pointer14 = pointer14;
                Pointer pointer17 = pointer2;
            }
            Pointer pointer18 = pointer14;
            Pointer pointer19 = pointer13;
            Pointer pointer20 = pointer12;
            Pointer pointer21 = pointer11;
            pointer20.move(this.NB_WORD_GFqn);
            rem_gf2n(pointer3, 0, this.Buffer_NB_WORD_MUL);
            pointer3.move(this.NB_WORD_GFqn);
            int i10 = i7 + 1;
            int i11 = i10;
            while (i11 < this.HFEn) {
                int index = pointer20.getIndex();
                int index2 = pointer10.getIndex();
                int index3 = pointer19.getIndex();
                int index4 = pointer21.getIndex();
                Pointer pointer22 = pointer18;
                mul_move(pointer22, pointer20, pointer10);
                for_mul_xorrange_move(pointer22, pointer20, pointer10, this.HFEDegI - 1);
                int i12 = i10;
                Pointer pointer23 = pointer19;
                for_mul_xorrange_move(pointer22, pointer23, pointer21, this.HFEDegI);
                rem_gf2n(pointer3, 0, pointer22);
                pointer20.changeIndex(index + (this.SIZE_ROW * this.NB_WORD_GFqn));
                pointer10.changeIndex(index2);
                pointer23.changeIndex(index3);
                pointer21.changeIndex(index4 + (this.HFEDegI * this.NB_WORD_GFqn));
                pointer3.move(this.NB_WORD_GFqn);
                i11++;
                pointer5 = pointer5;
                pointer19 = pointer23;
                i10 = i12;
            }
            int i13 = i10;
            Pointer pointer24 = pointer19;
            Pointer pointer25 = pointer18;
            Pointer pointer26 = pointer5;
            pointer7.changeIndex(pointer6);
            pointer24.move(-this.NB_WORD_GFqn);
            while (i11 < this.HFEnv) {
                pointer7.move((this.HFEDegI + 1) * this.NB_WORD_GFqn);
                dotProduct_gf2n(pointer3, pointer24, pointer7, this.HFEDegI + 1);
                pointer3.move(this.NB_WORD_GFqn);
                i11++;
            }
            int i14 = this.NB_WORD_GFqn;
            pointer24.move(i14 + (this.SIZE_ROW * i14));
            pointer11 = pointer21;
            pointer12 = pointer20;
            pointer13 = pointer24;
            pointer14 = pointer25;
            i7 = i13;
            pointer5 = pointer26;
            Pointer pointer27 = pointer2;
        }
        Pointer pointer28 = pointer2;
        pointer28.move(this.NB_WORD_GFqn - this.MQv_GFqn_SIZE);
        pointer3.copyFrom(pointer28, this.NB_WORD_GFqn * (this.NB_MONOMIAL_VINEGAR - 1));
        pointer.indexReset();
        pointer2.indexReset();
    }

    /* access modifiers changed from: package-private */
    public int interpolateHFE_FS_ref(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = pointer;
        Pointer pointer5 = pointer2;
        Pointer pointer6 = pointer3;
        Pointer pointer7 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer8 = new Pointer();
        Pointer pointer9 = new Pointer();
        Pointer pointer10 = new Pointer(this.HFEnv * this.NB_WORD_GFqn);
        pointer4.copyFrom(pointer5, this.NB_WORD_GFqn);
        Pointer pointer11 = new Pointer(pointer6);
        Pointer pointer12 = new Pointer(pointer10);
        for (int i = 0; i < this.HFEnv; i++) {
            evalHFEv_gf2nx(pointer12, pointer5, pointer11);
            pointer12.move(this.NB_WORD_GFqn);
            pointer11.move(this.NB_WORD_GF2nv);
        }
        pointer11.changeIndex(pointer6);
        pointer12.changeIndex(pointer10);
        int i2 = 0;
        while (i2 < this.HFEnv) {
            pointer4.move(this.NB_WORD_GFqn);
            pointer12.setXorRange(pointer5, this.NB_WORD_GFqn);
            pointer4.copyFrom(pointer12, this.NB_WORD_GFqn);
            pointer8.changeIndex(pointer12);
            pointer9.changeIndex(pointer11);
            int i3 = i2 + 1;
            for (int i4 = i3; i4 < this.HFEnv; i4++) {
                pointer4.move(this.NB_WORD_GFqn);
                pointer8.move(this.NB_WORD_GFqn);
                pointer9.move(this.NB_WORD_GF2nv);
                pointer7.setRangeFromXor(pointer11, pointer9, this.NB_WORD_GF2nv);
                evalHFEv_gf2nx(pointer4, pointer5, pointer7);
                pointer.setXorRangeXor(0, pointer12, 0, pointer8, 0, this.NB_WORD_GFqn);
            }
            pointer12.move(this.NB_WORD_GFqn);
            pointer11.move(this.NB_WORD_GF2nv);
            i2 = i3;
        }
        pointer.indexReset();
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void invMatrixLU_gf2(org.bouncycastle.pqc.crypto.gemss.Pointer r21, org.bouncycastle.pqc.crypto.gemss.Pointer r22, org.bouncycastle.pqc.crypto.gemss.Pointer r23, org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams r24) {
        /*
            r20 = this;
            r10 = r20
            r11 = r21
            r0 = r22
            org.bouncycastle.pqc.crypto.gemss.Pointer r12 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r12.<init>((org.bouncycastle.pqc.crypto.gemss.Pointer) r0)
            org.bouncycastle.pqc.crypto.gemss.Pointer r13 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r13.<init>((org.bouncycastle.pqc.crypto.gemss.Pointer) r0)
            org.bouncycastle.pqc.crypto.gemss.Pointer r14 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r0 = r23
            r14.<init>((org.bouncycastle.pqc.crypto.gemss.Pointer) r0)
            int[] r0 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams
            int r1 = r24.ordinal()
            r0 = r0[r1]
            r15 = 0
            r9 = 1
            if (r0 == r9) goto L_0x003a
            r1 = 2
            if (r0 != r1) goto L_0x0032
            int r0 = r10.HFEnvq
            int r1 = r10.HFEnv
            int r1 = r1 - r9
            int r2 = r10.NB_WORD_GF2nv
            int r3 = r10.HFEnvr
            int r4 = r10.LTRIANGULAR_NV_SIZE
            goto L_0x004a
        L_0x0032:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Invalid Input"
            r0.<init>(r1)
            throw r0
        L_0x003a:
            int r0 = r10.MATRIXn_SIZE
            r11.setRangeClear(r15, r0)
            int r0 = r10.HFEnq
            int r1 = r10.HFEn
            int r1 = r1 - r9
            int r2 = r10.NB_WORD_GFqn
            int r3 = r10.HFEnr
            int r4 = r10.LTRIANGULAR_N_SIZE
        L_0x004a:
            r8 = r0
            r16 = r1
            r7 = r2
            r6 = r3
            r5 = r4
            org.bouncycastle.pqc.crypto.gemss.Pointer r4 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r4.<init>((org.bouncycastle.pqc.crypto.gemss.Pointer) r11)
            org.bouncycastle.pqc.crypto.gemss.Pointer r3 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r3.<init>((org.bouncycastle.pqc.crypto.gemss.Pointer) r11)
            r2 = 0
            r17 = 0
        L_0x005d:
            if (r2 >= r8) goto L_0x009a
            r18 = 64
            r0 = r20
            r1 = r4
            r22 = r2
            r2 = r3
            r23 = r3
            r3 = r12
            r24 = r4
            r4 = r13
            r15 = r5
            r5 = r17
            r10 = r6
            r6 = r22
            r19 = r7
            r7 = r18
            r18 = r8
            r8 = r16
            r11 = 1
            r9 = r19
            int r17 = r0.loop_xor_loop_move_xorandmask_move(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r13.moveIncremental()
            r9 = r22
            int r2 = r9 + 1
            r11 = r21
            r3 = r23
            r4 = r24
            r6 = r10
            r5 = r15
            r8 = r18
            r7 = r19
            r9 = 1
            r15 = 0
            r10 = r20
            goto L_0x005d
        L_0x009a:
            r9 = r2
            r23 = r3
            r24 = r4
            r15 = r5
            r10 = r6
            r19 = r7
            r11 = 1
            r7 = 1
            if (r10 <= r11) goto L_0x00cb
            int r10 = r10 - r11
            r0 = r20
            r1 = r24
            r2 = r23
            r3 = r12
            r4 = r13
            r5 = r17
            r6 = r9
            r12 = r7
            r7 = r10
            r8 = r16
            r11 = r9
            r9 = r19
            r0.loop_xor_loop_move_xorandmask_move(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            long r0 = r12 << r10
            r2 = r24
            r2.setXor(r11, r0)
            r0 = r19
        L_0x00c7:
            r2.move(r0)
            goto L_0x00d7
        L_0x00cb:
            r2 = r24
            r12 = r7
            r1 = r9
            r0 = r19
            if (r10 != r11) goto L_0x00d7
            r2.set(r1, r12)
            goto L_0x00c7
        L_0x00d7:
            r14.move(r15)
            r1 = r16
        L_0x00dc:
            if (r1 <= 0) goto L_0x010c
            int r3 = r1 >>> 6
            int r3 = -1 - r3
            r14.move(r3)
            int r3 = -r0
            r2.move(r3)
            r3 = r21
            r4 = r23
            r4.changeIndex((org.bouncycastle.pqc.crypto.gemss.Pointer) r3)
            r5 = 0
        L_0x00f1:
            if (r5 >= r1) goto L_0x0107
            int r6 = r5 >>> 6
            long r6 = r14.get(r6)
            r8 = r5 & 63
            long r6 = r6 >>> r8
            long r6 = r6 & r12
            long r6 = -r6
            r4.setXorRangeAndMask(r2, r0, r6)
            r4.move(r0)
            int r5 = r5 + 1
            goto L_0x00f1
        L_0x0107:
            int r1 = r1 + -1
            r23 = r4
            goto L_0x00dc
        L_0x010c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.invMatrixLU_gf2(org.bouncycastle.pqc.crypto.gemss.Pointer, org.bouncycastle.pqc.crypto.gemss.Pointer, org.bouncycastle.pqc.crypto.gemss.Pointer, org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams):void");
    }

    /* access modifiers changed from: package-private */
    public void mul_gf2n(Pointer pointer, Pointer pointer2, int i, Pointer pointer3) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        pointer2.changeIndex(index);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    /* access modifiers changed from: package-private */
    public void mul_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    /* access modifiers changed from: package-private */
    public void mul_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(pointer, pointer2, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        pointer3.move(this.NB_WORD_GFqn);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int index = pointer3.getIndex();
        pointer3.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
        pointer3.changeIndex(index);
    }

    public void mul_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
    }

    public void signHFE_FeistelPatarin(SecureRandom secureRandom, byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3) {
        int i3;
        Pointer pointer;
        int i4;
        int i5;
        SecretKeyHFE secretKeyHFE;
        Pointer pointer2;
        long j;
        PointerUnion pointerUnion;
        long j2;
        Pointer pointer3;
        Pointer pointer4;
        PointerUnion pointerUnion2;
        SecretKeyHFE secretKeyHFE2;
        Pointer pointer5;
        Pointer pointer6;
        SecureRandom secureRandom2 = secureRandom;
        byte[] bArr4 = bArr;
        this.random = secureRandom2;
        Pointer pointer7 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer9 = new Pointer(new Pointer(this.SIZE_DIGEST_UINT));
        int i6 = this.HFEv;
        int i7 = i6 & 7;
        int i8 = (i6 >>> 3) + (i7 != 0 ? 1 : 0);
        long maskUINT = GeMSSUtils.maskUINT(this.HFEvr);
        SecretKeyHFE secretKeyHFE3 = new SecretKeyHFE(this);
        Pointer pointer10 = new Pointer(this.NB_WORD_GFqv);
        Pointer[] pointerArr = new Pointer[(this.HFEDegI + 1)];
        precSignHFE(secretKeyHFE3, pointerArr, bArr3);
        Pointer pointer11 = new Pointer(secretKeyHFE3.F_struct.poly);
        Pointer pointer12 = new Pointer(pointer8);
        int i9 = this.Sha3BitStrength >>> 3;
        Pointer pointer13 = pointer11;
        Pointer[] pointerArr2 = pointerArr;
        Pointer pointer14 = pointer10;
        SecretKeyHFE secretKeyHFE4 = secretKeyHFE3;
        long j3 = maskUINT;
        Pointer pointer15 = pointer9;
        int i10 = i8;
        getSHA3Hash(pointer12, 0, i9, bArr2, i, i2, new byte[i9]);
        Pointer pointer16 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer17 = new Pointer(this.NB_WORD_GF2nv);
        PointerUnion pointerUnion3 = new PointerUnion(pointer17);
        long j4 = 0;
        int i11 = 1;
        while (true) {
            i3 = this.NB_ITE;
            if (i11 > i3) {
                break;
            }
            pointer17.setRangeFromXor(pointer16, pointer12, this.NB_WORD_GF2m);
            if (this.HFEmr8 != 0) {
                pointer17.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
                j4 = (long) pointerUnion3.getByte(this.HFEmq8);
            }
            long j5 = j4;
            while (true) {
                if (this.HFEmr8 != 0) {
                    pointerUnion3.fillRandomBytes(this.HFEmq8, secureRandom2, (this.NB_BYTES_GFqn - this.NB_BYTES_GFqm) + 1);
                    pointer = pointer12;
                    i4 = i11;
                    pointerUnion3.setAndThenXorByte(this.HFEmq8, -(1 << this.HFEmr8), j5);
                } else {
                    pointer = pointer12;
                    i4 = i11;
                    int i12 = this.NB_BYTES_GFqm;
                    pointerUnion3.fillRandomBytes(i12, secureRandom2, this.NB_BYTES_GFqn - i12);
                }
                if ((this.HFEn & 7) != 0) {
                    i5 = 1;
                    pointer17.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
                } else {
                    i5 = 1;
                }
                secretKeyHFE = secretKeyHFE4;
                vecMatProduct(pointer7, pointer17, secretKeyHFE.T, FunctionParams.N);
                pointer2 = pointer14;
                pointer2.fillRandom(0, secureRandom2, i10);
                if (i7 != 0) {
                    j = j3;
                    pointer2.setAnd(this.NB_WORD_GFqv - i5, j);
                } else {
                    j = j3;
                }
                evalMQSv_unrolled_gf2(pointer13, pointer2, secretKeyHFE.F_HFEv);
                int i13 = 0;
                while (i13 <= this.HFEDegI) {
                    PointerUnion pointerUnion4 = pointerUnion3;
                    vecMatProduct(this.Buffer_NB_WORD_GFqn, pointer2, new Pointer(pointerArr2[i13], this.NB_WORD_GFqn), FunctionParams.V);
                    int i14 = this.NB_WORD_GFqn;
                    int i15 = i13 + 1;
                    pointer13.setRangeFromXor(i14 * (((i13 * i15) >>> 1) + 1), pointerArr2[i13], 0, this.Buffer_NB_WORD_GFqn, 0, i14);
                    pointerUnion3 = pointerUnion4;
                    i13 = i15;
                    j = j;
                }
                pointerUnion = pointerUnion3;
                j3 = j;
                if (chooseRootHFE_gf2nx(pointer17, secretKeyHFE.F_struct, pointer7) != 0) {
                    break;
                }
                pointerUnion3 = pointerUnion;
                pointer14 = pointer2;
                secretKeyHFE4 = secretKeyHFE;
                i11 = i4;
                pointer12 = pointer;
            }
            pointer17.setXor(this.NB_WORD_GFqn - 1, pointer2.get() << this.HFEnr);
            pointer17.setRangeRotate(this.NB_WORD_GFqn, pointer2, 0, this.NB_WORD_GFqv - 1, 64 - this.HFEnr);
            int i16 = this.NB_WORD_GFqn;
            int i17 = this.NB_WORD_GFqv;
            if (i16 + i17 == this.NB_WORD_GF2nv) {
                pointer17.set((i16 + i17) - 1, pointer2.get(i17 - 1) >>> (64 - this.HFEnr));
            }
            vecMatProduct(pointer16, pointer17, secretKeyHFE.S, FunctionParams.NV);
            int i18 = this.NB_ITE;
            if (i4 != i18) {
                int i19 = this.NB_WORD_GF2nv;
                int i20 = this.NB_WORD_GF2nvm;
                int i21 = (((i18 - 1) - i4) * i20) + i19;
                pointer16.copyFrom(i21, pointer16, i19 - i20, i20);
                if (this.HFEmr != 0) {
                    pointer16.setAnd(i21, this.MASK_GF2m ^ -1);
                }
                Pointer pointer18 = pointer;
                byte[] bytes = pointer18.toBytes(this.SIZE_DIGEST);
                pointerUnion2 = pointerUnion;
                byte[] bArr5 = bytes;
                j2 = j3;
                pointer3 = pointer2;
                secretKeyHFE2 = secretKeyHFE;
                pointer4 = pointer17;
                getSHA3Hash(pointer15, 0, this.SIZE_DIGEST, bArr5, 0, bytes.length, bArr5);
                pointer6 = pointer15;
                pointer5 = pointer18;
                pointer6.swap(pointer5);
            } else {
                pointerUnion2 = pointerUnion;
                pointer5 = pointer;
                pointer3 = pointer2;
                secretKeyHFE2 = secretKeyHFE;
                pointer4 = pointer17;
                j2 = j3;
                pointer6 = pointer15;
            }
            i11 = i4 + 1;
            pointer15 = pointer6;
            pointer12 = pointer5;
            secretKeyHFE4 = secretKeyHFE2;
            pointerUnion3 = pointerUnion2;
            pointer17 = pointer4;
            pointer14 = pointer3;
            j3 = j2;
            j4 = j5;
        }
        if (i3 == 1) {
            System.arraycopy(pointer16.toBytes(pointer16.getLength() << 3), 0, bArr, 0, this.NB_BYTES_GFqnv);
        } else {
            compress_signHFE(bArr, pointer16);
        }
    }

    public int sign_openHFE_huncomp_pk(byte[] bArr, int i, byte[] bArr2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        PointerUnion pointerUnion3 = pointerUnion;
        PointerUnion pointerUnion4 = pointerUnion2;
        Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer2 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer3 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer4 = new Pointer(pointer2);
        Pointer pointer5 = new Pointer(pointer3);
        byte[] bArr3 = new byte[64];
        Pointer pointer6 = new Pointer(this.NB_ITE * this.SIZE_DIGEST_UINT);
        long j = pointerUnion2.get();
        pointerUnion4.move(1);
        uncompress_signHFE(pointer, bArr2);
        long j2 = j;
        getSHA3Hash(pointer6, 0, 64, bArr, 0, i, bArr3);
        int i2 = 1;
        while (i2 < this.NB_ITE) {
            int i3 = i2;
            getSHA3Hash(pointer6, i2 * this.SIZE_DIGEST_UINT, 64, bArr3, 0, this.SIZE_DIGEST, bArr3);
            pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i3 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
            i2 = i3 + 1;
        }
        pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i2 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
        evalMQShybrid8_uncomp_nocst_gf2_m(pointer4, pointer, pointerUnion3, pointerUnion4);
        long j3 = j2;
        pointer4.setXor(this.HFEmq, j3);
        for (int i4 = this.NB_ITE - 1; i4 > 0; i4--) {
            pointer4.setXorRange(pointer6, this.SIZE_DIGEST_UINT * i4, this.NB_WORD_GF2m);
            int i5 = this.NB_WORD_GF2nv + (((this.NB_ITE - 1) - i4) * this.NB_WORD_GF2nvm);
            pointer4.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
            pointer4.setXor(this.NB_WORD_GF2m - 1, pointer.get(i5));
            int i6 = this.NB_WORD_GF2nvm;
            if (i6 != 1) {
                pointer4.copyFrom(this.NB_WORD_GF2m, pointer, i5 + 1, i6 - 1);
            }
            evalMQShybrid8_uncomp_nocst_gf2_m(pointer5, pointer4, pointerUnion3, pointerUnion4);
            pointer5.setXor(this.HFEmq, j3);
            pointer5.swap(pointer4);
        }
        return pointer4.isEqual_nocst_gf2(pointer6, this.NB_WORD_GF2m);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b9 A[LOOP:2: B:40:0x00b7->B:41:0x00b9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void vecMatProduct(org.bouncycastle.pqc.crypto.gemss.Pointer r19, org.bouncycastle.pqc.crypto.gemss.Pointer r20, org.bouncycastle.pqc.crypto.gemss.Pointer r21, org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            org.bouncycastle.pqc.crypto.gemss.Pointer r3 = new org.bouncycastle.pqc.crypto.gemss.Pointer
            r4 = r21
            r3.<init>((org.bouncycastle.pqc.crypto.gemss.Pointer) r4)
            int[] r4 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams
            int r5 = r22.ordinal()
            r4 = r4[r5]
            java.lang.String r5 = "Invalid input for vecMatProduct"
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            r10 = 0
            if (r4 == r9) goto L_0x004a
            if (r4 == r8) goto L_0x0040
            if (r4 == r7) goto L_0x0036
            if (r4 != r6) goto L_0x0030
            int r4 = r0.NB_WORD_GF2m
            r1.setRangeClear(r10, r4)
            int r4 = r0.HFEnq
            int r11 = r0.NB_WORD_GF2m
            int r12 = r0.NB_WORD_GFqn
            goto L_0x0054
        L_0x0030:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r5)
            throw r1
        L_0x0036:
            int r4 = r0.NB_WORD_GFqn
            r1.setRangeClear(r10, r4)
            int r11 = r0.NB_WORD_GFqn
            int r4 = r0.HFEvq
            goto L_0x0053
        L_0x0040:
            int r4 = r0.NB_WORD_GF2nv
            r1.setRangeClear(r10, r4)
            int r4 = r0.HFEnvq
            int r11 = r0.NB_WORD_GF2nv
            goto L_0x0053
        L_0x004a:
            int r4 = r0.NB_WORD_GFqn
            r1.setRangeClear(r10, r4)
            int r11 = r0.NB_WORD_GFqn
            int r4 = r0.HFEnq
        L_0x0053:
            r12 = r11
        L_0x0054:
            r13 = 0
        L_0x0055:
            r14 = 1
            if (r13 >= r4) goto L_0x0078
            long r16 = r2.get(r13)
        L_0x005d:
            r6 = 64
            if (r10 >= r6) goto L_0x0071
            long r7 = r16 & r14
            long r7 = -r7
            r1.setXorRangeAndMask(r3, r11, r7)
            r3.move(r12)
            long r16 = r16 >>> r9
            int r10 = r10 + 1
            r7 = 3
            r8 = 2
            goto L_0x005d
        L_0x0071:
            int r13 = r13 + 1
            r6 = 4
            r7 = 3
            r8 = 2
            r10 = 0
            goto L_0x0055
        L_0x0078:
            int[] r4 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams
            int r7 = r22.ordinal()
            r4 = r4[r7]
            if (r4 == r9) goto L_0x00ae
            r7 = 2
            if (r4 == r7) goto L_0x00a0
            r6 = 3
            if (r4 == r6) goto L_0x0092
            r6 = 4
            if (r4 != r6) goto L_0x008c
            goto L_0x00ae
        L_0x008c:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r5)
            throw r1
        L_0x0092:
            int r4 = r0.HFEvr
            if (r4 != 0) goto L_0x0097
            return
        L_0x0097:
            int r4 = r0.HFEvq
            long r4 = r2.get(r4)
            int r2 = r0.HFEvr
            goto L_0x00b6
        L_0x00a0:
            int r4 = r0.HFEnvr
            if (r4 != 0) goto L_0x00a5
            return
        L_0x00a5:
            int r4 = r0.HFEnvq
            long r4 = r2.get(r4)
            int r2 = r0.HFEnvr
            goto L_0x00b6
        L_0x00ae:
            int r4 = r0.HFEnq
            long r4 = r2.get(r4)
            int r2 = r0.HFEnr
        L_0x00b6:
            r10 = 0
        L_0x00b7:
            if (r10 >= r2) goto L_0x00c6
            long r6 = r4 & r14
            long r6 = -r6
            r1.setXorRangeAndMask(r3, r11, r6)
            r3.move(r12)
            long r4 = r4 >>> r9
            int r10 = r10 + 1
            goto L_0x00b7
        L_0x00c6:
            org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams r2 = org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.FunctionParams.M
            r3 = r22
            if (r3 != r2) goto L_0x00d8
            int r2 = r0.HFEmr
            if (r2 == 0) goto L_0x00d8
            int r2 = r0.NB_WORD_GF2m
            int r2 = r2 - r9
            long r3 = r0.MASK_GF2m
            r1.setAnd(r2, r3)
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gemss.GeMSSEngine.vecMatProduct(org.bouncycastle.pqc.crypto.gemss.Pointer, org.bouncycastle.pqc.crypto.gemss.Pointer, org.bouncycastle.pqc.crypto.gemss.Pointer, org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$FunctionParams):void");
    }
}
