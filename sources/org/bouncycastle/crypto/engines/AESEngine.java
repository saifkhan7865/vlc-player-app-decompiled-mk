package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import io.netty.handler.codec.http.HttpConstants;
import java.lang.reflect.Array;
import okio.Utf8;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DefaultMultiBlockCipher;
import org.bouncycastle.crypto.MultiBlockCipher;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class AESEngine extends DefaultMultiBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, HttpConstants.COMMA, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, HttpConstants.SEMICOLON, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, Ascii.DLE, -1, -13, -46, -51, Ascii.FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115, 96, -127, 79, -36, HttpConstants.DOUBLE_QUOTE, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37, -32, 50, HttpConstants.COLON, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, Ascii.SI, -80, 84, -69, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, Ascii.VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, HttpConstants.COMMA, Ascii.RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, HttpConstants.COLON, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, HttpConstants.DOUBLE_QUOTE, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.GS, 41, -59, -119, 111, -73, 98, Ascii.SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, Ascii.DLE, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, HttpConstants.SEMICOLON, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.FF, 125};
    private static final int[] T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    private static final int[] Tinv0 = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, MediaWrapper.META_GAIN, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private byte[] s;

    public AESEngine() {
        int[][] iArr = null;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = i & m4;
        int i3 = i2 ^ (i2 >>> 1);
        return (i3 >>> 5) ^ (((m5 & i) << 2) ^ (i3 >>> 2));
    }

    private int bitsOfSecurity() {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            return 256;
        }
        return (iArr.length - 7) << 5;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i3 = i2;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr3, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr3, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr3, i + 12);
        int i4 = this.ROUNDS;
        int[] iArr2 = iArr[i4];
        char c = 0;
        int i5 = littleEndianToInt ^ iArr2[0];
        int i6 = littleEndianToInt2 ^ iArr2[1];
        int i7 = littleEndianToInt3 ^ iArr2[2];
        int i8 = i4 - 1;
        int i9 = littleEndianToInt4 ^ iArr2[3];
        for (int i10 = 1; i8 > i10; i10 = 1) {
            int[] iArr3 = Tinv0;
            int shift = (((shift(iArr3[(i9 >> 8) & 255], 24) ^ iArr3[i5 & 255]) ^ shift(iArr3[(i7 >> 16) & 255], 16)) ^ shift(iArr3[(i6 >> 24) & 255], 8)) ^ iArr[i8][c];
            int shift2 = (((shift(iArr3[(i5 >> 8) & 255], 24) ^ iArr3[i6 & 255]) ^ shift(iArr3[(i9 >> 16) & 255], 16)) ^ shift(iArr3[(i7 >> 24) & 255], 8)) ^ iArr[i8][i10];
            int shift3 = (((shift(iArr3[(i6 >> 8) & 255], 24) ^ iArr3[i7 & 255]) ^ shift(iArr3[(i5 >> 16) & 255], 16)) ^ shift(iArr3[(i9 >> 24) & 255], 8)) ^ iArr[i8][2];
            int shift4 = ((iArr3[i9 & 255] ^ shift(iArr3[(i7 >> 8) & 255], 24)) ^ shift(iArr3[(i6 >> 16) & 255], 16)) ^ shift(iArr3[(i5 >> 24) & 255], 8);
            int i11 = i8 - 1;
            int i12 = shift4 ^ iArr[i8][3];
            int shift5 = (((iArr3[shift & 255] ^ shift(iArr3[(i12 >> 8) & 255], 24)) ^ shift(iArr3[(shift3 >> 16) & 255], 16)) ^ shift(iArr3[(shift2 >> 24) & 255], 8)) ^ iArr[i11][0];
            int shift6 = (((iArr3[shift2 & 255] ^ shift(iArr3[(shift >> 8) & 255], 24)) ^ shift(iArr3[(i12 >> 16) & 255], 16)) ^ shift(iArr3[(shift3 >> 24) & 255], 8)) ^ iArr[i11][1];
            i8 -= 2;
            i9 = (((iArr3[i12 & 255] ^ shift(iArr3[(shift3 >> 8) & 255], 24)) ^ shift(iArr3[(shift2 >> 16) & 255], 16)) ^ shift(iArr3[(shift >> 24) & 255], 8)) ^ iArr[i11][3];
            i5 = shift5;
            i6 = shift6;
            i7 = (((iArr3[shift3 & 255] ^ shift(iArr3[(shift2 >> 8) & 255], 24)) ^ shift(iArr3[(shift >> 16) & 255], 16)) ^ shift(iArr3[(i12 >> 24) & 255], 8)) ^ iArr[i11][2];
            c = 0;
        }
        int[] iArr4 = Tinv0;
        int shift7 = (((iArr4[i5 & 255] ^ shift(iArr4[(i9 >> 8) & 255], 24)) ^ shift(iArr4[(i7 >> 16) & 255], 16)) ^ shift(iArr4[(i6 >> 24) & 255], 8)) ^ iArr[i8][0];
        int shift8 = (((iArr4[i6 & 255] ^ shift(iArr4[(i5 >> 8) & 255], 24)) ^ shift(iArr4[(i9 >> 16) & 255], 16)) ^ shift(iArr4[(i7 >> 24) & 255], 8)) ^ iArr[i8][1];
        int shift9 = (((iArr4[i7 & 255] ^ shift(iArr4[(i6 >> 8) & 255], 24)) ^ shift(iArr4[(i5 >> 16) & 255], 16)) ^ shift(iArr4[(i9 >> 24) & 255], 8)) ^ iArr[i8][2];
        int shift10 = (((iArr4[i9 & 255] ^ shift(iArr4[(i7 >> 8) & 255], 24)) ^ shift(iArr4[(i6 >> 16) & 255], 16)) ^ shift(iArr4[(i5 >> 24) & 255], 8)) ^ iArr[i8][3];
        byte[] bArr5 = Si;
        byte[] bArr6 = this.s;
        int[] iArr5 = iArr[0];
        byte b = ((((bArr5[shift7 & 255] & 255) ^ ((bArr6[(shift10 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(shift9 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(shift8 >> 24) & 255] << Ascii.CAN)) ^ iArr5[0];
        byte b2 = ((((bArr6[shift8 & 255] & 255) ^ ((bArr6[(shift7 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(shift10 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(shift9 >> 24) & 255] << Ascii.CAN)) ^ iArr5[1];
        byte b3 = ((((bArr5[shift10 & 255] & 255) ^ ((bArr6[(shift9 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(shift8 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(shift7 >> 24) & 255] << Ascii.CAN)) ^ iArr5[3];
        Pack.intToLittleEndian((int) b, bArr4, i3);
        Pack.intToLittleEndian((int) b2, bArr4, i3 + 4);
        Pack.intToLittleEndian((int) ((((bArr6[shift9 & 255] & 255) ^ ((bArr5[(shift8 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(shift7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(shift10 >> 24) & 255] << Ascii.CAN)) ^ iArr5[2], bArr4, i3 + 8);
        Pack.intToLittleEndian((int) b3, bArr4, i3 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i3 = i2;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr3, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr3, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr3, i + 12);
        char c = 0;
        int[] iArr2 = iArr[0];
        int i4 = littleEndianToInt ^ iArr2[0];
        int i5 = littleEndianToInt2 ^ iArr2[1];
        int i6 = littleEndianToInt3 ^ iArr2[2];
        int i7 = littleEndianToInt4 ^ iArr2[3];
        int i8 = 1;
        for (int i9 = 1; i8 < this.ROUNDS - i9; i9 = 1) {
            int[] iArr3 = T0;
            int shift = (((shift(iArr3[(i5 >> 8) & 255], 24) ^ iArr3[i4 & 255]) ^ shift(iArr3[(i6 >> 16) & 255], 16)) ^ shift(iArr3[(i7 >> 24) & 255], 8)) ^ iArr[i8][c];
            int shift2 = (((shift(iArr3[(i6 >> 8) & 255], 24) ^ iArr3[i5 & 255]) ^ shift(iArr3[(i7 >> 16) & 255], 16)) ^ shift(iArr3[(i4 >> 24) & 255], 8)) ^ iArr[i8][i9];
            int shift3 = (((shift(iArr3[(i7 >> 8) & 255], 24) ^ iArr3[i6 & 255]) ^ shift(iArr3[(i4 >> 16) & 255], 16)) ^ shift(iArr3[(i5 >> 24) & 255], 8)) ^ iArr[i8][2];
            int shift4 = ((iArr3[i7 & 255] ^ shift(iArr3[(i4 >> 8) & 255], 24)) ^ shift(iArr3[(i5 >> 16) & 255], 16)) ^ shift(iArr3[(i6 >> 24) & 255], 8);
            int i10 = i8 + 1;
            int i11 = shift4 ^ iArr[i8][3];
            int shift5 = (((iArr3[shift & 255] ^ shift(iArr3[(shift2 >> 8) & 255], 24)) ^ shift(iArr3[(shift3 >> 16) & 255], 16)) ^ shift(iArr3[(i11 >> 24) & 255], 8)) ^ iArr[i10][0];
            int shift6 = (((iArr3[shift2 & 255] ^ shift(iArr3[(shift3 >> 8) & 255], 24)) ^ shift(iArr3[(i11 >> 16) & 255], 16)) ^ shift(iArr3[(shift >> 24) & 255], 8)) ^ iArr[i10][1];
            i8 += 2;
            i7 = (((iArr3[i11 & 255] ^ shift(iArr3[(shift >> 8) & 255], 24)) ^ shift(iArr3[(shift2 >> 16) & 255], 16)) ^ shift(iArr3[(shift3 >> 24) & 255], 8)) ^ iArr[i10][3];
            i4 = shift5;
            i5 = shift6;
            i6 = (((iArr3[shift3 & 255] ^ shift(iArr3[(i11 >> 8) & 255], 24)) ^ shift(iArr3[(shift >> 16) & 255], 16)) ^ shift(iArr3[(shift2 >> 24) & 255], 8)) ^ iArr[i10][2];
            c = 0;
        }
        int[] iArr4 = T0;
        int shift7 = (((iArr4[i4 & 255] ^ shift(iArr4[(i5 >> 8) & 255], 24)) ^ shift(iArr4[(i6 >> 16) & 255], 16)) ^ shift(iArr4[(i7 >> 24) & 255], 8)) ^ iArr[i8][0];
        int shift8 = (((iArr4[i5 & 255] ^ shift(iArr4[(i6 >> 8) & 255], 24)) ^ shift(iArr4[(i7 >> 16) & 255], 16)) ^ shift(iArr4[(i4 >> 24) & 255], 8)) ^ iArr[i8][1];
        int shift9 = (((iArr4[i6 & 255] ^ shift(iArr4[(i7 >> 8) & 255], 24)) ^ shift(iArr4[(i4 >> 16) & 255], 16)) ^ shift(iArr4[(i5 >> 24) & 255], 8)) ^ iArr[i8][2];
        int shift10 = ((iArr4[i7 & 255] ^ shift(iArr4[(i4 >> 8) & 255], 24)) ^ shift(iArr4[(i5 >> 16) & 255], 16)) ^ shift(iArr4[(i6 >> 24) & 255], 8);
        int i12 = shift10 ^ iArr[i8][3];
        byte[] bArr5 = S;
        byte b = (bArr5[shift7 & 255] & 255) ^ ((bArr5[(shift8 >> 8) & 255] & 255) << 8);
        byte[] bArr6 = this.s;
        int[] iArr5 = iArr[i8 + 1];
        byte b2 = ((b ^ ((bArr6[(shift9 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(i12 >> 24) & 255] << Ascii.CAN)) ^ iArr5[0];
        byte b3 = ((((bArr6[shift8 & 255] & 255) ^ ((bArr5[(shift9 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i12 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(shift7 >> 24) & 255] << Ascii.CAN)) ^ iArr5[1];
        byte b4 = ((((bArr6[i12 & 255] & 255) ^ ((bArr6[(shift7 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(shift8 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(shift9 >> 24) & 255] << Ascii.CAN)) ^ iArr5[3];
        Pack.intToLittleEndian((int) b2, bArr4, i3);
        Pack.intToLittleEndian((int) b3, bArr4, i3 + 4);
        Pack.intToLittleEndian((int) ((((bArr6[shift9 & 255] & 255) ^ ((bArr5[(i12 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(shift7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(shift8 >> 24) & 255] << Ascii.CAN)) ^ iArr5[2], bArr4, i3 + 8);
        Pack.intToLittleEndian((int) b4, bArr4, i3 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        this.ROUNDS = i + 6;
        int[] iArr = new int[2];
        iArr[1] = 4;
        iArr[0] = i + 7;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int i2 = 8;
        char c = 3;
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i3 - 1];
                int[] iArr3 = iArr2[i3];
                iArr3[0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr3[1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr3[2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr3[3] = littleEndianToInt4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr2, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr2, 20);
            int i4 = 1;
            int i5 = 1;
            while (true) {
                int[] iArr4 = iArr2[i4];
                iArr4[0] = littleEndianToInt9;
                iArr4[1] = littleEndianToInt10;
                int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ i5);
                int[] iArr5 = iArr2[i4];
                iArr5[2] = subWord;
                int i6 = littleEndianToInt6 ^ subWord;
                iArr5[3] = i6;
                int i7 = littleEndianToInt7 ^ i6;
                int[] iArr6 = iArr2[i4 + 1];
                iArr6[0] = i7;
                int i8 = littleEndianToInt8 ^ i7;
                iArr6[1] = i8;
                int i9 = littleEndianToInt9 ^ i8;
                iArr6[2] = i9;
                int i10 = littleEndianToInt10 ^ i9;
                iArr6[3] = i10;
                i5 <<= 2;
                littleEndianToInt5 = subWord ^ (subWord(shift(i10, 8)) ^ (i5 << 1));
                int[] iArr7 = iArr2[i4 + 2];
                iArr7[0] = littleEndianToInt5;
                littleEndianToInt6 = i6 ^ littleEndianToInt5;
                iArr7[1] = littleEndianToInt6;
                littleEndianToInt7 = i7 ^ littleEndianToInt6;
                iArr7[2] = littleEndianToInt7;
                littleEndianToInt8 = i8 ^ littleEndianToInt7;
                iArr7[3] = littleEndianToInt8;
                i4 += 3;
                if (i4 >= 13) {
                    break;
                }
                littleEndianToInt9 = i9 ^ littleEndianToInt8;
                littleEndianToInt10 = i10 ^ littleEndianToInt9;
            }
        } else if (i == 8) {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr2, 16);
            iArr2[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr2, 20);
            iArr2[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr2, 24);
            iArr2[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr2, 28);
            iArr2[1][3] = littleEndianToInt18;
            int i11 = 1;
            int i12 = 2;
            while (true) {
                int subWord2 = subWord(shift(littleEndianToInt18, i2)) ^ i11;
                i11 <<= 1;
                littleEndianToInt11 ^= subWord2;
                int[] iArr8 = iArr2[i12];
                iArr8[0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr8[1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr8[2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr8[c] = littleEndianToInt14;
                int i13 = i12 + 1;
                if (i13 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int[] iArr9 = iArr2[i13];
                iArr9[0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr9[1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr9[2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr9[3] = littleEndianToInt18;
                i12 += 2;
                i2 = 8;
                c = 3;
            }
        } else {
            throw new IllegalStateException("Should never get here");
        }
        if (!z) {
            for (int i14 = 1; i14 < this.ROUNDS; i14++) {
                for (int i15 = 0; i15 < 4; i15++) {
                    int[] iArr10 = iArr2[i14];
                    iArr10[i15] = inv_mcol(iArr10[i15]);
                }
            }
        }
        return iArr2;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    public static MultiBlockCipher newInstance() {
        return new AESEngine();
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << Ascii.CAN) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << Ascii.DLE);
    }

    public String getAlgorithmName() {
        return "AES";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            if (z) {
                this.s = Arrays.clone(S);
            } else {
                this.s = Arrays.clone(Si);
            }
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 <= bArr2.length - 16) {
            if (this.forEncryption) {
                encryptBlock(bArr, i, bArr2, i2, iArr);
            } else {
                decryptBlock(bArr, i, bArr2, i2, iArr);
            }
            return 16;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
