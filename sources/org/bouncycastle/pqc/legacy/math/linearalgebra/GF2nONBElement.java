package org.bouncycastle.pqc.legacy.math.linearalgebra;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.primitives.Longs;
import io.ktor.util.date.GMTDateParser;
import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.time.DurationKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.util.Arrays;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.resources.Constants;

public class GF2nONBElement extends GF2nElement {
    private static final int MAXLONG = 64;
    private static final long[] mBitmask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, 8192, 16384, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, 65536, PlaybackStateCompat.ACTION_PREPARE_FROM_URI, PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, LockFreeTaskQueueCore.FROZEN_MASK, LockFreeTaskQueueCore.CLOSED_MASK, Longs.MAX_POWER_OF_TWO, Long.MIN_VALUE};
    private static final int[] mIBY64;
    private static final long[] mMaxmask = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, WebSocketProtocol.PAYLOAD_SHORT_MAX, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, LockFreeTaskQueueCore.HEAD_MASK, 2147483647L, 4294967295L, 8589934591L, 17179869183L, 34359738367L, 68719476735L, 137438953471L, 274877906943L, 549755813887L, 1099511627775L, 2199023255551L, 4398046511103L, 8796093022207L, 17592186044415L, 35184372088831L, 70368744177663L, 140737488355327L, 281474976710655L, 562949953421311L, 1125899906842623L, 2251799813685247L, 4503599627370495L, 9007199254740991L, 18014398509481983L, 36028797018963967L, 72057594037927935L, 144115188075855871L, 288230376151711743L, 576460752303423487L, 1152921504606846975L, 2305843009213693951L, DurationKt.MAX_MILLIS, Long.MAX_VALUE, -1};
    private int mBit;
    private int mLength;
    private long[] mPol;

    static {
        int[] iArr = new int[KyberEngine.KyberPolyBytes];
        // fill-array-data instruction
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        iArr[8] = 0;
        iArr[9] = 0;
        iArr[10] = 0;
        iArr[11] = 0;
        iArr[12] = 0;
        iArr[13] = 0;
        iArr[14] = 0;
        iArr[15] = 0;
        iArr[16] = 0;
        iArr[17] = 0;
        iArr[18] = 0;
        iArr[19] = 0;
        iArr[20] = 0;
        iArr[21] = 0;
        iArr[22] = 0;
        iArr[23] = 0;
        iArr[24] = 0;
        iArr[25] = 0;
        iArr[26] = 0;
        iArr[27] = 0;
        iArr[28] = 0;
        iArr[29] = 0;
        iArr[30] = 0;
        iArr[31] = 0;
        iArr[32] = 0;
        iArr[33] = 0;
        iArr[34] = 0;
        iArr[35] = 0;
        iArr[36] = 0;
        iArr[37] = 0;
        iArr[38] = 0;
        iArr[39] = 0;
        iArr[40] = 0;
        iArr[41] = 0;
        iArr[42] = 0;
        iArr[43] = 0;
        iArr[44] = 0;
        iArr[45] = 0;
        iArr[46] = 0;
        iArr[47] = 0;
        iArr[48] = 0;
        iArr[49] = 0;
        iArr[50] = 0;
        iArr[51] = 0;
        iArr[52] = 0;
        iArr[53] = 0;
        iArr[54] = 0;
        iArr[55] = 0;
        iArr[56] = 0;
        iArr[57] = 0;
        iArr[58] = 0;
        iArr[59] = 0;
        iArr[60] = 0;
        iArr[61] = 0;
        iArr[62] = 0;
        iArr[63] = 0;
        iArr[64] = 1;
        iArr[65] = 1;
        iArr[66] = 1;
        iArr[67] = 1;
        iArr[68] = 1;
        iArr[69] = 1;
        iArr[70] = 1;
        iArr[71] = 1;
        iArr[72] = 1;
        iArr[73] = 1;
        iArr[74] = 1;
        iArr[75] = 1;
        iArr[76] = 1;
        iArr[77] = 1;
        iArr[78] = 1;
        iArr[79] = 1;
        iArr[80] = 1;
        iArr[81] = 1;
        iArr[82] = 1;
        iArr[83] = 1;
        iArr[84] = 1;
        iArr[85] = 1;
        iArr[86] = 1;
        iArr[87] = 1;
        iArr[88] = 1;
        iArr[89] = 1;
        iArr[90] = 1;
        iArr[91] = 1;
        iArr[92] = 1;
        iArr[93] = 1;
        iArr[94] = 1;
        iArr[95] = 1;
        iArr[96] = 1;
        iArr[97] = 1;
        iArr[98] = 1;
        iArr[99] = 1;
        iArr[100] = 1;
        iArr[101] = 1;
        iArr[102] = 1;
        iArr[103] = 1;
        iArr[104] = 1;
        iArr[105] = 1;
        iArr[106] = 1;
        iArr[107] = 1;
        iArr[108] = 1;
        iArr[109] = 1;
        iArr[110] = 1;
        iArr[111] = 1;
        iArr[112] = 1;
        iArr[113] = 1;
        iArr[114] = 1;
        iArr[115] = 1;
        iArr[116] = 1;
        iArr[117] = 1;
        iArr[118] = 1;
        iArr[119] = 1;
        iArr[120] = 1;
        iArr[121] = 1;
        iArr[122] = 1;
        iArr[123] = 1;
        iArr[124] = 1;
        iArr[125] = 1;
        iArr[126] = 1;
        iArr[127] = 1;
        iArr[128] = 2;
        iArr[129] = 2;
        iArr[130] = 2;
        iArr[131] = 2;
        iArr[132] = 2;
        iArr[133] = 2;
        iArr[134] = 2;
        iArr[135] = 2;
        iArr[136] = 2;
        iArr[137] = 2;
        iArr[138] = 2;
        iArr[139] = 2;
        iArr[140] = 2;
        iArr[141] = 2;
        iArr[142] = 2;
        iArr[143] = 2;
        iArr[144] = 2;
        iArr[145] = 2;
        iArr[146] = 2;
        iArr[147] = 2;
        iArr[148] = 2;
        iArr[149] = 2;
        iArr[150] = 2;
        iArr[151] = 2;
        iArr[152] = 2;
        iArr[153] = 2;
        iArr[154] = 2;
        iArr[155] = 2;
        iArr[156] = 2;
        iArr[157] = 2;
        iArr[158] = 2;
        iArr[159] = 2;
        iArr[160] = 2;
        iArr[161] = 2;
        iArr[162] = 2;
        iArr[163] = 2;
        iArr[164] = 2;
        iArr[165] = 2;
        iArr[166] = 2;
        iArr[167] = 2;
        iArr[168] = 2;
        iArr[169] = 2;
        iArr[170] = 2;
        iArr[171] = 2;
        iArr[172] = 2;
        iArr[173] = 2;
        iArr[174] = 2;
        iArr[175] = 2;
        iArr[176] = 2;
        iArr[177] = 2;
        iArr[178] = 2;
        iArr[179] = 2;
        iArr[180] = 2;
        iArr[181] = 2;
        iArr[182] = 2;
        iArr[183] = 2;
        iArr[184] = 2;
        iArr[185] = 2;
        iArr[186] = 2;
        iArr[187] = 2;
        iArr[188] = 2;
        iArr[189] = 2;
        iArr[190] = 2;
        iArr[191] = 2;
        iArr[192] = 3;
        iArr[193] = 3;
        iArr[194] = 3;
        iArr[195] = 3;
        iArr[196] = 3;
        iArr[197] = 3;
        iArr[198] = 3;
        iArr[199] = 3;
        iArr[200] = 3;
        iArr[201] = 3;
        iArr[202] = 3;
        iArr[203] = 3;
        iArr[204] = 3;
        iArr[205] = 3;
        iArr[206] = 3;
        iArr[207] = 3;
        iArr[208] = 3;
        iArr[209] = 3;
        iArr[210] = 3;
        iArr[211] = 3;
        iArr[212] = 3;
        iArr[213] = 3;
        iArr[214] = 3;
        iArr[215] = 3;
        iArr[216] = 3;
        iArr[217] = 3;
        iArr[218] = 3;
        iArr[219] = 3;
        iArr[220] = 3;
        iArr[221] = 3;
        iArr[222] = 3;
        iArr[223] = 3;
        iArr[224] = 3;
        iArr[225] = 3;
        iArr[226] = 3;
        iArr[227] = 3;
        iArr[228] = 3;
        iArr[229] = 3;
        iArr[230] = 3;
        iArr[231] = 3;
        iArr[232] = 3;
        iArr[233] = 3;
        iArr[234] = 3;
        iArr[235] = 3;
        iArr[236] = 3;
        iArr[237] = 3;
        iArr[238] = 3;
        iArr[239] = 3;
        iArr[240] = 3;
        iArr[241] = 3;
        iArr[242] = 3;
        iArr[243] = 3;
        iArr[244] = 3;
        iArr[245] = 3;
        iArr[246] = 3;
        iArr[247] = 3;
        iArr[248] = 3;
        iArr[249] = 3;
        iArr[250] = 3;
        iArr[251] = 3;
        iArr[252] = 3;
        iArr[253] = 3;
        iArr[254] = 3;
        iArr[255] = 3;
        iArr[256] = 4;
        iArr[257] = 4;
        iArr[258] = 4;
        iArr[259] = 4;
        iArr[260] = 4;
        iArr[261] = 4;
        iArr[262] = 4;
        iArr[263] = 4;
        iArr[264] = 4;
        iArr[265] = 4;
        iArr[266] = 4;
        iArr[267] = 4;
        iArr[268] = 4;
        iArr[269] = 4;
        iArr[270] = 4;
        iArr[271] = 4;
        iArr[272] = 4;
        iArr[273] = 4;
        iArr[274] = 4;
        iArr[275] = 4;
        iArr[276] = 4;
        iArr[277] = 4;
        iArr[278] = 4;
        iArr[279] = 4;
        iArr[280] = 4;
        iArr[281] = 4;
        iArr[282] = 4;
        iArr[283] = 4;
        iArr[284] = 4;
        iArr[285] = 4;
        iArr[286] = 4;
        iArr[287] = 4;
        iArr[288] = 4;
        iArr[289] = 4;
        iArr[290] = 4;
        iArr[291] = 4;
        iArr[292] = 4;
        iArr[293] = 4;
        iArr[294] = 4;
        iArr[295] = 4;
        iArr[296] = 4;
        iArr[297] = 4;
        iArr[298] = 4;
        iArr[299] = 4;
        iArr[300] = 4;
        iArr[301] = 4;
        iArr[302] = 4;
        iArr[303] = 4;
        iArr[304] = 4;
        iArr[305] = 4;
        iArr[306] = 4;
        iArr[307] = 4;
        iArr[308] = 4;
        iArr[309] = 4;
        iArr[310] = 4;
        iArr[311] = 4;
        iArr[312] = 4;
        iArr[313] = 4;
        iArr[314] = 4;
        iArr[315] = 4;
        iArr[316] = 4;
        iArr[317] = 4;
        iArr[318] = 4;
        iArr[319] = 4;
        iArr[320] = 5;
        iArr[321] = 5;
        iArr[322] = 5;
        iArr[323] = 5;
        iArr[324] = 5;
        iArr[325] = 5;
        iArr[326] = 5;
        iArr[327] = 5;
        iArr[328] = 5;
        iArr[329] = 5;
        iArr[330] = 5;
        iArr[331] = 5;
        iArr[332] = 5;
        iArr[333] = 5;
        iArr[334] = 5;
        iArr[335] = 5;
        iArr[336] = 5;
        iArr[337] = 5;
        iArr[338] = 5;
        iArr[339] = 5;
        iArr[340] = 5;
        iArr[341] = 5;
        iArr[342] = 5;
        iArr[343] = 5;
        iArr[344] = 5;
        iArr[345] = 5;
        iArr[346] = 5;
        iArr[347] = 5;
        iArr[348] = 5;
        iArr[349] = 5;
        iArr[350] = 5;
        iArr[351] = 5;
        iArr[352] = 5;
        iArr[353] = 5;
        iArr[354] = 5;
        iArr[355] = 5;
        iArr[356] = 5;
        iArr[357] = 5;
        iArr[358] = 5;
        iArr[359] = 5;
        iArr[360] = 5;
        iArr[361] = 5;
        iArr[362] = 5;
        iArr[363] = 5;
        iArr[364] = 5;
        iArr[365] = 5;
        iArr[366] = 5;
        iArr[367] = 5;
        iArr[368] = 5;
        iArr[369] = 5;
        iArr[370] = 5;
        iArr[371] = 5;
        iArr[372] = 5;
        iArr[373] = 5;
        iArr[374] = 5;
        iArr[375] = 5;
        iArr[376] = 5;
        iArr[377] = 5;
        iArr[378] = 5;
        iArr[379] = 5;
        iArr[380] = 5;
        iArr[381] = 5;
        iArr[382] = 5;
        iArr[383] = 5;
        mIBY64 = iArr;
    }

    public GF2nONBElement(GF2nONBElement gF2nONBElement) {
        this.mField = gF2nONBElement.mField;
        this.mDegree = this.mField.getDegree();
        this.mLength = ((GF2nONBField) this.mField).getONBLength();
        this.mBit = ((GF2nONBField) this.mField).getONBBit();
        this.mPol = new long[this.mLength];
        assign(gF2nONBElement.getElement());
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, BigInteger bigInteger) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bigInteger);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, SecureRandom secureRandom) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        int i = this.mLength;
        long[] jArr = new long[i];
        this.mPol = jArr;
        if (i > 1) {
            for (int i2 = 0; i2 < this.mLength - 1; i2++) {
                this.mPol[i2] = secureRandom.nextLong();
            }
            this.mPol[this.mLength - 1] = secureRandom.nextLong() >>> (64 - this.mBit);
            return;
        }
        jArr[0] = secureRandom.nextLong();
        long[] jArr2 = this.mPol;
        jArr2[0] = jArr2[0] >>> (64 - this.mBit);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, byte[] bArr) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bArr);
    }

    private GF2nONBElement(GF2nONBField gF2nONBField, long[] jArr) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = jArr;
    }

    public static GF2nONBElement ONE(GF2nONBField gF2nONBField) {
        int oNBLength = gF2nONBField.getONBLength();
        long[] jArr = new long[oNBLength];
        int i = 0;
        while (true) {
            int i2 = oNBLength - 1;
            if (i < i2) {
                jArr[i] = -1;
                i++;
            } else {
                jArr[i2] = mMaxmask[gF2nONBField.getONBBit() - 1];
                return new GF2nONBElement(gF2nONBField, jArr);
            }
        }
    }

    public static GF2nONBElement ZERO(GF2nONBField gF2nONBField) {
        return new GF2nONBElement(gF2nONBField, new long[gF2nONBField.getONBLength()]);
    }

    private void assign(BigInteger bigInteger) {
        assign(bigInteger.toByteArray());
    }

    private void assign(byte[] bArr) {
        this.mPol = new long[this.mLength];
        for (int i = 0; i < bArr.length; i++) {
            long[] jArr = this.mPol;
            int i2 = i >>> 3;
            jArr[i2] = jArr[i2] | ((((long) bArr[(bArr.length - 1) - i]) & 255) << ((i & 7) << 3));
        }
    }

    private void assign(long[] jArr) {
        System.arraycopy(jArr, 0, this.mPol, 0, this.mLength);
    }

    private long[] getElement() {
        long[] jArr = this.mPol;
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    private long[] getElementReverseOrder() {
        long[] jArr = new long[this.mPol.length];
        for (int i = 0; i < this.mDegree; i++) {
            if (testBit((this.mDegree - i) - 1)) {
                int i2 = i >>> 6;
                jArr[i2] = jArr[i2] | mBitmask[i & 63];
            }
        }
        return jArr;
    }

    public GFElement add(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.addToThis(gFElement);
        return gF2nONBElement;
    }

    public void addToThis(GFElement gFElement) throws RuntimeException {
        if (gFElement instanceof GF2nONBElement) {
            GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
            if (this.mField.equals(gF2nONBElement.mField)) {
                for (int i = 0; i < this.mLength; i++) {
                    long[] jArr = this.mPol;
                    jArr[i] = jArr[i] ^ gF2nONBElement.mPol[i];
                }
                return;
            }
            throw new RuntimeException();
        }
        throw new RuntimeException();
    }

    /* access modifiers changed from: package-private */
    public void assignOne() {
        int i = 0;
        while (true) {
            int i2 = this.mLength;
            if (i < i2 - 1) {
                this.mPol[i] = -1;
                i++;
            } else {
                this.mPol[i2 - 1] = mMaxmask[this.mBit - 1];
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void assignZero() {
        this.mPol = new long[this.mLength];
    }

    public Object clone() {
        return new GF2nONBElement(this);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nONBElement)) {
            return false;
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) obj;
        for (int i = 0; i < this.mLength; i++) {
            if (this.mPol[i] != gF2nONBElement.mPol[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mPol);
    }

    public GF2nElement increase() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.increaseThis();
        return gF2nONBElement;
    }

    public void increaseThis() {
        addToThis(ONE((GF2nONBField) this.mField));
    }

    public GFElement invert() throws ArithmeticException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.invertThis();
        return gF2nONBElement;
    }

    public void invertThis() throws ArithmeticException {
        if (!isZero()) {
            int i = 31;
            boolean z = false;
            while (!z && i >= 0) {
                if ((((long) (this.mDegree - 1)) & mBitmask[i]) != 0) {
                    z = true;
                }
                i--;
            }
            ZERO((GF2nONBField) this.mField);
            GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
            int i2 = 1;
            while (i >= 0) {
                GF2nElement gF2nElement = (GF2nElement) gF2nONBElement.clone();
                for (int i3 = 1; i3 <= i2; i3++) {
                    gF2nElement.squareThis();
                }
                gF2nONBElement.multiplyThisBy(gF2nElement);
                i2 <<= 1;
                if ((((long) (this.mDegree - 1)) & mBitmask[i]) != 0) {
                    gF2nONBElement.squareThis();
                    gF2nONBElement.multiplyThisBy(this);
                    i2++;
                }
                i--;
            }
            gF2nONBElement.squareThis();
            return;
        }
        throw new ArithmeticException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if ((r3 & r2[r5 - 1]) == r2[r5 - 1]) goto L_0x0039;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isOne() {
        /*
            r8 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            r3 = 1
        L_0x0004:
            int r4 = r8.mLength
            int r5 = r4 + -1
            if (r2 >= r5) goto L_0x001e
            if (r3 == 0) goto L_0x001e
            if (r3 == 0) goto L_0x001a
            long[] r3 = r8.mPol
            r4 = r3[r2]
            r6 = -1
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x001a
            r3 = 1
            goto L_0x001b
        L_0x001a:
            r3 = 0
        L_0x001b:
            int r2 = r2 + 1
            goto L_0x0004
        L_0x001e:
            if (r3 == 0) goto L_0x003a
            if (r3 == 0) goto L_0x0038
            long[] r2 = r8.mPol
            int r4 = r4 - r0
            r3 = r2[r4]
            long[] r2 = mMaxmask
            int r5 = r8.mBit
            int r6 = r5 + -1
            r6 = r2[r6]
            long r3 = r3 & r6
            int r5 = r5 - r0
            r5 = r2[r5]
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0038
            goto L_0x0039
        L_0x0038:
            r0 = 0
        L_0x0039:
            r3 = r0
        L_0x003a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.legacy.math.linearalgebra.GF2nONBElement.isOne():boolean");
    }

    public boolean isZero() {
        boolean z = true;
        for (int i = 0; i < this.mLength && z; i++) {
            z = z && this.mPol[i] == 0;
        }
        return z;
    }

    public GFElement multiply(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.multiplyThisBy(gFElement);
        return gF2nONBElement;
    }

    public void multiplyThisBy(GFElement gFElement) throws RuntimeException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        GFElement gFElement2 = gFElement;
        if (gFElement2 instanceof GF2nONBElement) {
            GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement2;
            if (!this.mField.equals(gF2nONBElement.mField)) {
                throw new RuntimeException();
            } else if (equals(gFElement)) {
                squareThis();
            } else {
                long[] jArr = this.mPol;
                long[] jArr2 = gF2nONBElement.mPol;
                long[] jArr3 = new long[this.mLength];
                int[][] iArr = ((GF2nONBField) this.mField).mMult;
                int i = this.mLength;
                int i2 = i - 1;
                long[] jArr4 = mBitmask;
                long j = jArr4[63];
                long j2 = jArr4[this.mBit - 1];
                char c = 0;
                for (int i3 = 0; i3 < this.mDegree; i3++) {
                    int i4 = 0;
                    boolean z5 = false;
                    while (i4 < this.mDegree) {
                        int[] iArr2 = mIBY64;
                        int i5 = iArr2[i4];
                        int[] iArr3 = iArr[i4];
                        int i6 = iArr3[c];
                        int i7 = iArr2[i6];
                        int i8 = i6 & 63;
                        long j3 = jArr[i5];
                        long[] jArr5 = mBitmask;
                        if ((j3 & jArr5[i4 & 63]) != 0) {
                            if ((jArr2[i7] & jArr5[i8]) != 0) {
                                z5 = !z5;
                            }
                            int i9 = iArr3[1];
                            if (i9 != -1) {
                                if ((jArr2[iArr2[i9]] & jArr5[i9 & 63]) != 0) {
                                    z5 = !z5;
                                }
                            }
                        }
                        i4++;
                        c = 0;
                    }
                    int i10 = mIBY64[i3];
                    int i11 = i3 & 63;
                    if (z5) {
                        jArr3[i10] = jArr3[i10] ^ mBitmask[i11];
                    }
                    if (this.mLength > 1) {
                        boolean z6 = (jArr[i2] & 1) == 1;
                        int i12 = i - 2;
                        int i13 = i12;
                        while (i13 >= 0) {
                            long j4 = jArr[i13];
                            boolean z7 = (j4 & 1) != 0;
                            long j5 = j4 >>> 1;
                            jArr[i13] = j5;
                            if (z6) {
                                jArr[i13] = j5 ^ j;
                            }
                            i13--;
                            z6 = z7;
                        }
                        long j6 = jArr[i2] >>> 1;
                        jArr[i2] = j6;
                        if (z6) {
                            jArr[i2] = j6 ^ j2;
                        }
                        boolean z8 = (jArr2[i2] & 1) == 1;
                        while (i12 >= 0) {
                            long j7 = jArr2[i12];
                            boolean z9 = (j7 & 1) != 0;
                            long j8 = j7 >>> 1;
                            jArr2[i12] = j8;
                            if (z8) {
                                jArr2[i12] = j8 ^ j;
                            }
                            i12--;
                            z8 = z9;
                        }
                        long j9 = jArr2[i2] >>> 1;
                        jArr2[i2] = j9;
                        if (z8) {
                            jArr2[i2] = j9 ^ j2;
                        }
                        c = 0;
                    } else {
                        c = 0;
                        long j10 = jArr[0];
                        if ((j10 & 1) == 1) {
                            z2 = true;
                            z = true;
                        } else {
                            z2 = true;
                            z = false;
                        }
                        long j11 = j10 >>> (z2 ? 1 : 0);
                        jArr[0] = j11;
                        if (z) {
                            jArr[0] = j11 ^ j2;
                        }
                        long j12 = jArr2[0];
                        if ((j12 & 1) == 1) {
                            z4 = true;
                            z3 = true;
                        } else {
                            z4 = true;
                            z3 = false;
                        }
                        long j13 = j12 >>> (z4 ? 1 : 0);
                        jArr2[0] = j13;
                        if (z3) {
                            jArr2[0] = j13 ^ j2;
                        }
                    }
                }
                assign(jArr3);
            }
        } else {
            throw new RuntimeException("The elements have different representation: not yet implemented");
        }
    }

    /* access modifiers changed from: package-private */
    public void reverseOrder() {
        this.mPol = getElementReverseOrder();
    }

    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        if (trace() != 1) {
            long j = mBitmask[63];
            long[] jArr = new long[this.mLength];
            long j2 = 0;
            for (int i = 0; i < this.mLength - 1; i++) {
                for (int i2 = 1; i2 < 64; i2++) {
                    long[] jArr2 = mBitmask;
                    long j3 = jArr2[i2];
                    long j4 = this.mPol[i];
                    if (((j3 & j4) == 0 || (j2 & jArr2[i2 - 1]) == 0) && !((j4 & j3) == 0 && (jArr2[i2 - 1] & j2) == 0)) {
                        j2 ^= j3;
                    }
                }
                jArr[i] = j2;
                long j5 = j2 & j;
                j2 = ((j5 == 0 || (this.mPol[i + 1] & 1) != 1) && !(j5 == 0 && (1 & this.mPol[i + 1]) == 0)) ? 1 : 0;
            }
            int i3 = 63 & this.mDegree;
            long j6 = this.mPol[this.mLength - 1];
            for (int i4 = 1; i4 < i3; i4++) {
                long[] jArr3 = mBitmask;
                long j7 = jArr3[i4];
                if (((j7 & j6) == 0 || (jArr3[i4 - 1] & j2) == 0) && !((j7 & j6) == 0 && (jArr3[i4 - 1] & j2) == 0)) {
                    j2 ^= j7;
                }
            }
            jArr[this.mLength - 1] = j2;
            return new GF2nONBElement((GF2nONBField) this.mField, jArr);
        }
        throw new RuntimeException();
    }

    public GF2nElement square() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareThis();
        return gF2nONBElement;
    }

    public GF2nElement squareRoot() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareRootThis();
        return gF2nONBElement;
    }

    public void squareRootThis() {
        long[] element = getElement();
        int i = this.mLength - 1;
        int i2 = this.mBit - 1;
        long j = mBitmask[63];
        boolean z = (element[0] & 1) != 0;
        int i3 = i;
        while (i3 >= 0) {
            long j2 = element[i3];
            boolean z2 = (j2 & 1) != 0;
            long j3 = j2 >>> 1;
            element[i3] = j3;
            if (z) {
                if (i3 == i) {
                    element[i3] = j3 ^ mBitmask[i2];
                } else {
                    element[i3] = j3 ^ j;
                }
            }
            i3--;
            z = z2;
        }
        assign(element);
    }

    public void squareThis() {
        long[] element = getElement();
        int i = this.mLength - 1;
        int i2 = this.mBit;
        int i3 = i2 - 1;
        long[] jArr = mBitmask;
        long j = jArr[63];
        boolean z = false;
        boolean z2 = (element[i] & jArr[i3]) != 0;
        int i4 = 0;
        while (i4 < i) {
            long j2 = element[i4];
            boolean z3 = (j2 & j) != 0;
            long j3 = j2 << 1;
            element[i4] = j3;
            if (z2) {
                element[i4] = 1 ^ j3;
            }
            i4++;
            z2 = z3;
        }
        long j4 = element[i];
        long[] jArr2 = mBitmask;
        if ((jArr2[i3] & j4) != 0) {
            z = true;
        }
        long j5 = j4 << 1;
        element[i] = j5;
        if (z2) {
            element[i] = j5 ^ 1;
        }
        if (z) {
            element[i] = jArr2[i2] ^ element[i];
        }
        assign(element);
    }

    /* access modifiers changed from: package-private */
    public boolean testBit(int i) {
        return i >= 0 && i <= this.mDegree && (this.mPol[i >>> 6] & mBitmask[i & 63]) != 0;
    }

    public boolean testRightmostBit() {
        return (this.mPol[this.mLength - 1] & mBitmask[this.mBit - 1]) != 0;
    }

    public byte[] toByteArray() {
        int i = ((this.mDegree - 1) >> 3) + 1;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (i2 & 7) << 3;
            bArr[(i - i2) - 1] = (byte) ((int) ((this.mPol[i2 >>> 3] & (255 << i3)) >>> i3));
        }
        return bArr;
    }

    public BigInteger toFlexiBigInt() {
        return new BigInteger(1, toByteArray());
    }

    public String toString() {
        return toString(16);
    }

    public String toString(int i) {
        StringBuilder sb;
        String str;
        long[] element = getElement();
        int i2 = this.mBit;
        String str2 = "";
        if (i == 2) {
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                if ((element[element.length - 1] & (1 << i2)) == 0) {
                    str = str2 + Constants.GROUP_VIDEOS_FOLDER;
                } else {
                    str = str2 + DiskLruCache.VERSION_1;
                }
                str2 = str;
            }
            for (int length = element.length - 2; length >= 0; length--) {
                for (int i3 = 63; i3 >= 0; i3--) {
                    if ((element[length] & mBitmask[i3]) == 0) {
                        sb.append(str2);
                        sb.append(Constants.GROUP_VIDEOS_FOLDER);
                    } else {
                        sb = new StringBuilder();
                        sb.append(str2);
                        sb.append(DiskLruCache.VERSION_1);
                    }
                    str2 = sb.toString();
                }
            }
        } else if (i == 16) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f'};
            for (int length2 = element.length - 1; length2 >= 0; length2--) {
                str2 = ((((((((((((((((str2 + cArr[((int) (element[length2] >>> 60)) & 15]) + cArr[((int) (element[length2] >>> 56)) & 15]) + cArr[((int) (element[length2] >>> 52)) & 15]) + cArr[((int) (element[length2] >>> 48)) & 15]) + cArr[((int) (element[length2] >>> 44)) & 15]) + cArr[((int) (element[length2] >>> 40)) & 15]) + cArr[((int) (element[length2] >>> 36)) & 15]) + cArr[((int) (element[length2] >>> 32)) & 15]) + cArr[((int) (element[length2] >>> 28)) & 15]) + cArr[((int) (element[length2] >>> 24)) & 15]) + cArr[((int) (element[length2] >>> 20)) & 15]) + cArr[((int) (element[length2] >>> 16)) & 15]) + cArr[((int) (element[length2] >>> 12)) & 15]) + cArr[((int) (element[length2] >>> 8)) & 15]) + cArr[((int) (element[length2] >>> 4)) & 15]) + cArr[((int) element[length2]) & 15]) + AnsiRenderer.CODE_TEXT_SEPARATOR;
            }
        }
        return str2;
    }

    public int trace() {
        int i = this.mLength - 1;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < 64; i4++) {
                if ((this.mPol[i3] & mBitmask[i4]) != 0) {
                    i2 ^= 1;
                }
            }
        }
        int i5 = this.mBit;
        for (int i6 = 0; i6 < i5; i6++) {
            if ((this.mPol[i] & mBitmask[i6]) != 0) {
                i2 ^= 1;
            }
        }
        return i2;
    }
}
