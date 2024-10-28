package org.bouncycastle.crypto.generators;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;
import kotlin.text.Typography;
import okio.Utf8;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.videolan.resources.Constants;

public class OpenBSDBCrypt {
    private static final Set<String> allowedVersions;
    private static final byte[] decodingTable = new byte[128];
    private static final String defaultVersion = "2y";
    private static final byte[] encodingTable = {46, 47, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57};

    static {
        HashSet hashSet = new HashSet();
        allowedVersions = hashSet;
        hashSet.add("2");
        hashSet.add("2x");
        hashSet.add("2a");
        hashSet.add(defaultVersion);
        hashSet.add("2b");
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = encodingTable;
            if (i < bArr2.length) {
                decodingTable[bArr2[i]] = (byte) i;
                i++;
            } else {
                return;
            }
        }
    }

    private OpenBSDBCrypt() {
    }

    public static boolean checkPassword(String str, byte[] bArr) {
        if (bArr != null) {
            return doCheckPassword(str, Arrays.clone(bArr));
        }
        throw new IllegalArgumentException("Missing password.");
    }

    public static boolean checkPassword(String str, char[] cArr) {
        if (cArr != null) {
            return doCheckPassword(str, Strings.toUTF8ByteArray(cArr));
        }
        throw new IllegalArgumentException("Missing password.");
    }

    private static String createBcryptString(String str, byte[] bArr, byte[] bArr2, int i) {
        String str2;
        if (allowedVersions.contains(str)) {
            StringBuilder sb = new StringBuilder(60);
            sb.append(Typography.dollar);
            sb.append(str);
            sb.append(Typography.dollar);
            if (i < 10) {
                str2 = Constants.GROUP_VIDEOS_FOLDER + i;
            } else {
                str2 = Integer.toString(i);
            }
            sb.append(str2);
            sb.append(Typography.dollar);
            encodeData(sb, bArr2);
            encodeData(sb, BCrypt.generate(bArr, bArr2, i));
            return sb.toString();
        }
        throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
    }

    private static byte[] decodeSaltString(String str) {
        char[] charArray = str.toCharArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
        if (charArray.length == 22) {
            for (char c : charArray) {
                if (c > 'z' || c < '.' || (c > '9' && c < 'A')) {
                    throw new IllegalArgumentException("Salt string contains invalid character: " + c);
                }
            }
            char[] cArr = new char[24];
            System.arraycopy(charArray, 0, cArr, 0, charArray.length);
            for (int i = 0; i < 24; i += 4) {
                byte[] bArr = decodingTable;
                byte b = bArr[cArr[i]];
                byte b2 = bArr[cArr[i + 1]];
                byte b3 = bArr[cArr[i + 2]];
                byte b4 = bArr[cArr[i + 3]];
                byteArrayOutputStream.write((b << 2) | (b2 >> 4));
                byteArrayOutputStream.write((b2 << 4) | (b3 >> 2));
                byteArrayOutputStream.write(b4 | (b3 << 6));
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byte[] bArr2 = new byte[16];
            System.arraycopy(byteArray, 0, bArr2, 0, 16);
            return bArr2;
        }
        throw new DataLengthException("Invalid base64 salt length: " + charArray.length + " , 22 required.");
    }

    private static boolean doCheckPassword(String str, byte[] bArr) {
        String str2;
        if (str == null) {
            throw new IllegalArgumentException("Missing bcryptString.");
        } else if (str.charAt(1) == '2') {
            int length = str.length();
            if (length == 60 || (length == 59 && str.charAt(2) == '$')) {
                int i = 3;
                if (str.charAt(2) == '$') {
                    if (!(str.charAt(0) == '$' && str.charAt(5) == '$')) {
                        throw new IllegalArgumentException("Invalid Bcrypt String format.");
                    }
                } else if (!(str.charAt(0) == '$' && str.charAt(3) == '$' && str.charAt(6) == '$')) {
                    throw new IllegalArgumentException("Invalid Bcrypt String format.");
                }
                if (str.charAt(2) == '$') {
                    str2 = str.substring(1, 2);
                } else {
                    str2 = str.substring(1, 3);
                    i = 4;
                }
                if (allowedVersions.contains(str2)) {
                    String substring = str.substring(i, i + 2);
                    try {
                        int parseInt = Integer.parseInt(substring);
                        if (parseInt >= 4 && parseInt <= 31) {
                            return Strings.constantTimeAreEqual(str, doGenerate(str2, bArr, decodeSaltString(str.substring(str.lastIndexOf(36) + 1, length - 31)), parseInt));
                        }
                        throw new IllegalArgumentException("Invalid cost factor: " + parseInt + ", 4 < cost < 31 expected.");
                    } catch (NumberFormatException unused) {
                        throw new IllegalArgumentException("Invalid cost factor: " + substring);
                    }
                } else {
                    throw new IllegalArgumentException("Bcrypt version '" + str2 + "' is not supported by this implementation");
                }
            } else {
                throw new DataLengthException("Bcrypt String length: " + length + ", 60 required.");
            }
        } else {
            throw new IllegalArgumentException("not a Bcrypt string");
        }
    }

    private static String doGenerate(String str, byte[] bArr, byte[] bArr2, int i) {
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        } else if (bArr2 == null) {
            throw new IllegalArgumentException("Salt required.");
        } else if (bArr2.length != 16) {
            throw new DataLengthException("16 byte salt required: " + bArr2.length);
        } else if (i < 4 || i > 31) {
            throw new IllegalArgumentException("Invalid cost factor.");
        } else {
            int i2 = 72;
            if (bArr.length < 72) {
                i2 = bArr.length + 1;
            }
            byte[] bArr3 = new byte[i2];
            if (i2 > bArr.length) {
                i2 = bArr.length;
            }
            System.arraycopy(bArr, 0, bArr3, 0, i2);
            Arrays.fill(bArr, (byte) 0);
            String createBcryptString = createBcryptString(str, bArr3, bArr2, i);
            Arrays.fill(bArr3, (byte) 0);
            return createBcryptString;
        }
    }

    private static void encodeData(StringBuilder sb, byte[] bArr) {
        boolean z;
        if (bArr.length == 24 || bArr.length == 16) {
            if (bArr.length == 16) {
                byte[] bArr2 = new byte[18];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                bArr = bArr2;
                z = true;
            } else {
                bArr[bArr.length - 1] = 0;
                z = false;
            }
            int length = bArr.length;
            for (int i = 0; i < length; i += 3) {
                byte b = bArr[i] & 255;
                byte b2 = bArr[i + 1] & 255;
                byte b3 = bArr[i + 2];
                byte[] bArr3 = encodingTable;
                sb.append((char) bArr3[(b >>> 2) & 63]);
                sb.append((char) bArr3[((b << 4) | (b2 >>> 4)) & 63]);
                sb.append((char) bArr3[((b2 << 2) | ((b3 & 255) >>> 6)) & 63]);
                sb.append((char) bArr3[b3 & Utf8.REPLACEMENT_BYTE]);
            }
            int length2 = sb.length();
            sb.setLength(z ? length2 - 2 : length2 - 1);
            return;
        }
        throw new DataLengthException("Invalid length: " + bArr.length + ", 24 for key or 16 for salt expected");
    }

    public static String generate(String str, byte[] bArr, byte[] bArr2, int i) {
        if (bArr != null) {
            return doGenerate(str, Arrays.clone(bArr), bArr2, i);
        }
        throw new IllegalArgumentException("Password required.");
    }

    public static String generate(String str, char[] cArr, byte[] bArr, int i) {
        if (cArr != null) {
            return doGenerate(str, Strings.toUTF8ByteArray(cArr), bArr, i);
        }
        throw new IllegalArgumentException("Password required.");
    }

    public static String generate(byte[] bArr, byte[] bArr2, int i) {
        return generate(defaultVersion, bArr, bArr2, i);
    }

    public static String generate(char[] cArr, byte[] bArr, int i) {
        return generate(defaultVersion, cArr, bArr, i);
    }
}
