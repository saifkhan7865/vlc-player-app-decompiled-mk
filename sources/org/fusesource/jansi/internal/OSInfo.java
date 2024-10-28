package org.fusesource.jansi.internal;

import androidx.core.os.EnvironmentCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;

public class OSInfo {
    public static final String ARM64 = "arm64";
    public static final String IA64 = "ia64";
    public static final String IA64_32 = "ia64_32";
    public static final String PPC = "ppc";
    public static final String PPC64 = "ppc64";
    public static final String X86 = "x86";
    public static final String X86_64 = "x86_64";
    private static final HashMap<String, String> archMapping;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        archMapping = hashMap;
        hashMap.put(X86, X86);
        hashMap.put("i386", X86);
        hashMap.put("i486", X86);
        hashMap.put("i586", X86);
        hashMap.put("i686", X86);
        hashMap.put("pentium", X86);
        hashMap.put(X86_64, X86_64);
        hashMap.put("amd64", X86_64);
        hashMap.put("em64t", X86_64);
        hashMap.put("universal", X86_64);
        hashMap.put(IA64, IA64);
        hashMap.put("ia64w", IA64);
        hashMap.put(IA64_32, IA64_32);
        hashMap.put("ia64n", IA64_32);
        hashMap.put(PPC, PPC);
        hashMap.put("power", PPC);
        hashMap.put("powerpc", PPC);
        hashMap.put("power_pc", PPC);
        hashMap.put("power_rs", PPC);
        hashMap.put(PPC64, PPC64);
        hashMap.put("power64", PPC64);
        hashMap.put("powerpc64", PPC64);
        hashMap.put("power_pc64", PPC64);
        hashMap.put("power_rs64", PPC64);
        hashMap.put("aarch64", ARM64);
    }

    public static void main(String[] strArr) {
        if (strArr.length >= 1) {
            if ("--os".equals(strArr[0])) {
                System.out.print(getOSName());
                return;
            } else if ("--arch".equals(strArr[0])) {
                System.out.print(getArchName());
                return;
            }
        }
        System.out.print(getNativeLibFolderPathForCurrentOS());
    }

    public static String getNativeLibFolderPathForCurrentOS() {
        return getOSName() + "/" + getArchName();
    }

    public static String getOSName() {
        return translateOSNameToFolderName(System.getProperty("os.name"));
    }

    public static boolean isAndroid() {
        return System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
    }

    public static boolean isAlpine() {
        InputStream inputStream;
        try {
            Process exec = Runtime.getRuntime().exec("cat /etc/os-release | grep ^ID");
            exec.waitFor();
            inputStream = exec.getInputStream();
            boolean contains = readFully(inputStream).toLowerCase().contains("alpine");
            inputStream.close();
            return contains;
        } catch (Throwable unused) {
            return false;
        }
    }

    static String getHardwareName() {
        InputStream inputStream;
        try {
            Process exec = Runtime.getRuntime().exec("uname -m");
            exec.waitFor();
            inputStream = exec.getInputStream();
            String readFully = readFully(inputStream);
            inputStream.close();
            return readFully;
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            printStream.println("Error while running uname -m: " + th.getMessage());
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    private static String readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[32];
        while (true) {
            int read = inputStream.read(bArr, 0, 32);
            if (read < 0) {
                return byteArrayOutputStream.toString();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    static String resolveArmArchType() {
        if (System.getProperty("os.name").contains("Linux")) {
            String hardwareName = getHardwareName();
            if (hardwareName.startsWith("armv6")) {
                return "armv6";
            }
            if (hardwareName.startsWith("armv7")) {
                return "armv7";
            }
            if (hardwareName.startsWith("armv5")) {
                return "arm";
            }
            if (hardwareName.equals("aarch64")) {
                return ARM64;
            }
            String property = System.getProperty("sun.arch.abi");
            if (property == null || !property.startsWith("gnueabihf")) {
                return "arm";
            }
            return "armv7";
        }
        return "arm";
    }

    public static String getArchName() {
        String property = System.getProperty("os.arch");
        if (isAndroid()) {
            return "android-arm";
        }
        if (property.startsWith("arm")) {
            property = resolveArmArchType();
        } else {
            String lowerCase = property.toLowerCase(Locale.US);
            HashMap<String, String> hashMap = archMapping;
            if (hashMap.containsKey(lowerCase)) {
                return hashMap.get(lowerCase);
            }
        }
        return translateArchNameToFolderName(property);
    }

    static String translateOSNameToFolderName(String str) {
        if (str.contains("Windows")) {
            return "Windows";
        }
        if (str.contains("Mac") || str.contains("Darwin")) {
            return "Mac";
        }
        if (str.contains("Linux")) {
            return "Linux";
        }
        if (str.contains("AIX")) {
            return "AIX";
        }
        return str.replaceAll("\\W", "");
    }

    static String translateArchNameToFolderName(String str) {
        return str.replaceAll("\\W", "");
    }
}
