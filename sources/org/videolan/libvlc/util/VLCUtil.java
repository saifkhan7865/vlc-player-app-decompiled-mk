package org.videolan.libvlc.util;

import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

public class VLCUtil {
    private static final String[] CPU_archs = {"*Pre-v4", "*v4", "*v4T", "v5T", "v5TE", "v5TEJ", "v6", "v6KZ", "v6T2", "v6K", "v7", "*v6-M", "*v6S-M", "*v7E-M", "*v8"};
    private static final int ELF_HEADER_SIZE = 52;
    private static final int EM_386 = 3;
    private static final int EM_AARCH64 = 183;
    private static final int EM_ARM = 40;
    private static final int EM_MIPS = 8;
    private static final int EM_X86_64 = 62;
    private static final int SECTION_HEADER_SIZE = 40;
    private static final int SHT_ARM_ATTRIBUTES = 1879048195;
    public static final String TAG = "VLC/LibVLC/Util";
    private static final String URI_AUTHORIZED_CHARS = "'()*";
    private static String errorMsg = null;
    private static boolean isCompatible = false;
    private static MachineSpecs machineSpecs;

    public static class MachineSpecs {
        public float bogoMIPS;
        public float frequency;
        public boolean hasArmV6;
        public boolean hasArmV7;
        public boolean hasFpu;
        public boolean hasMips;
        public boolean hasNeon;
        public boolean hasX86;
        public boolean is64bits;
        public int processors;
    }

    public static String getErrorMsg() {
        return errorMsg;
    }

    public static String[] getABIList21() {
        String[] m = AppUtils$$ExternalSyntheticApiModelOutline0.m();
        if (m == null || m.length == 0) {
            return getABIList();
        }
        return m;
    }

    public static String[] getABIList() {
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x019e A[Catch:{ IOException -> 0x01ea, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x0298 A[Catch:{ IOException -> 0x02d0, NumberFormatException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x01ea A[EDGE_INSN: B:221:0x01ea->B:133:0x01ea ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0112 A[Catch:{ IOException -> 0x01ea, all -> 0x01c8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean hasCompatibleCPU(android.content.Context r22) {
        /*
            java.lang.String r0 = errorMsg
            if (r0 != 0) goto L_0x0341
            boolean r0 = isCompatible
            if (r0 == 0) goto L_0x000a
            goto L_0x0341
        L_0x000a:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0015
            java.lang.String[] r0 = getABIList21()
            goto L_0x0019
        L_0x0015:
            java.lang.String[] r0 = getABIList()
        L_0x0019:
            int r1 = r0.length
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0020:
            java.lang.String r9 = "x86"
            if (r3 >= r1) goto L_0x005d
            r11 = r0[r3]
            boolean r9 = r11.equals(r9)
            if (r9 == 0) goto L_0x002e
            r7 = 1
            goto L_0x005a
        L_0x002e:
            java.lang.String r9 = "x86_64"
            boolean r9 = r11.equals(r9)
            if (r9 == 0) goto L_0x0039
            r7 = 1
        L_0x0037:
            r8 = 1
            goto L_0x005a
        L_0x0039:
            java.lang.String r9 = "armeabi-v7a"
            boolean r9 = r11.equals(r9)
            if (r9 == 0) goto L_0x0044
            r5 = 1
            r6 = 1
            goto L_0x005a
        L_0x0044:
            java.lang.String r9 = "armeabi"
            boolean r9 = r11.equals(r9)
            if (r9 == 0) goto L_0x004e
            r5 = 1
            goto L_0x005a
        L_0x004e:
            java.lang.String r9 = "arm64-v8a"
            boolean r9 = r11.equals(r9)
            if (r9 == 0) goto L_0x005a
            r4 = 1
            r5 = 1
            r6 = 1
            goto L_0x0037
        L_0x005a:
            int r3 = r3 + 1
            goto L_0x0020
        L_0x005d:
            android.content.pm.ApplicationInfo r0 = r22.getApplicationInfo()
            java.io.File r0 = searchLibrary(r0)
            r1 = 40
            java.lang.String r11 = "VLC/LibVLC/Util"
            if (r0 == 0) goto L_0x00ea
            org.videolan.libvlc.util.VLCUtil$ElfData r0 = readLib(r0)
            if (r0 == 0) goto L_0x00eb
            int r12 = r0.e_machine
            r13 = 3
            if (r12 == r13) goto L_0x007f
            int r12 = r0.e_machine
            r13 = 62
            if (r12 != r13) goto L_0x007d
            goto L_0x007f
        L_0x007d:
            r12 = 0
            goto L_0x0080
        L_0x007f:
            r12 = 1
        L_0x0080:
            int r13 = r0.e_machine
            if (r13 == r1) goto L_0x008d
            int r13 = r0.e_machine
            r14 = 183(0xb7, float:2.56E-43)
            if (r13 != r14) goto L_0x008b
            goto L_0x008d
        L_0x008b:
            r13 = 0
            goto L_0x008e
        L_0x008d:
            r13 = 1
        L_0x008e:
            int r14 = r0.e_machine
            r15 = 8
            if (r14 != r15) goto L_0x0096
            r14 = 1
            goto L_0x0097
        L_0x0096:
            r14 = 0
        L_0x0097:
            boolean r15 = r0.is64bits
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r1 = "ELF ABI = "
            r3.<init>(r1)
            if (r13 == 0) goto L_0x00a5
            java.lang.String r9 = "arm"
            goto L_0x00aa
        L_0x00a5:
            if (r12 == 0) goto L_0x00a8
            goto L_0x00aa
        L_0x00a8:
            java.lang.String r9 = "mips"
        L_0x00aa:
            r3.append(r9)
            java.lang.String r1 = ", "
            r3.append(r1)
            if (r15 == 0) goto L_0x00b7
            java.lang.String r1 = "64bits"
            goto L_0x00b9
        L_0x00b7:
            java.lang.String r1 = "32bits"
        L_0x00b9:
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            android.util.Log.i(r11, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "ELF arch = "
            r1.<init>(r3)
            java.lang.String r3 = r0.att_arch
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r11, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "ELF fpu = "
            r1.<init>(r3)
            boolean r3 = r0.att_fpu
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r11, r1)
            goto L_0x00f4
        L_0x00ea:
            r0 = 0
        L_0x00eb:
            java.lang.String r1 = "WARNING: Unable to read libvlcjni.so; cannot check device ABI!"
            android.util.Log.w(r11, r1)
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x00f4:
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x01dc, all -> 0x01d1 }
            java.lang.String r9 = "/proc/cpuinfo"
            r3.<init>(r9)     // Catch:{ IOException -> 0x01dc, all -> 0x01d1 }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x01dd, all -> 0x01cd }
            r9.<init>(r3)     // Catch:{ IOException -> 0x01dd, all -> 0x01cd }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
        L_0x010a:
            r21 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x010c:
            java.lang.String r1 = r9.readLine()     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r1 == 0) goto L_0x01ea
            java.lang.String r2 = "AArch64"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x011d
        L_0x011a:
            r5 = 1
            r6 = 1
            goto L_0x016b
        L_0x011d:
            java.lang.String r2 = "ARMv7"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0126
            goto L_0x011a
        L_0x0126:
            java.lang.String r2 = "ARMv6"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0130
            r5 = 1
            goto L_0x016b
        L_0x0130:
            java.lang.String r2 = "clflush size"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x013a
        L_0x0138:
            r7 = 1
            goto L_0x016b
        L_0x013a:
            java.lang.String r2 = "GenuineIntel"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0143
            goto L_0x0138
        L_0x0143:
            java.lang.String r2 = "placeholder"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x014e
            r16 = 1
            goto L_0x016b
        L_0x014e:
            java.lang.String r2 = "CPU implementer"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0161
            java.lang.String r2 = "0x69"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0161
            r17 = 1
            goto L_0x016b
        L_0x0161:
            java.lang.String r2 = "microsecond timers"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x016b
            r19 = 1
        L_0x016b:
            java.lang.String r2 = "neon"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 != 0) goto L_0x017b
            java.lang.String r2 = "asimd"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x017c
        L_0x017b:
            r4 = 1
        L_0x017c:
            java.lang.String r2 = "vfp"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 != 0) goto L_0x0194
            java.lang.String r2 = "Features"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0196
            java.lang.String r2 = "fp"
            boolean r2 = r1.contains(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x0196
        L_0x0194:
            r20 = 1
        L_0x0196:
            java.lang.String r2 = "processor"
            boolean r2 = r1.startsWith(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x01a0
            int r18 = r18 + 1
        L_0x01a0:
            r2 = 0
            int r2 = (r21 > r2 ? 1 : (r21 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x010c
            java.util.Locale r2 = java.util.Locale.ENGLISH     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            java.lang.String r2 = r1.toLowerCase(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            java.lang.String r10 = "bogomips"
            boolean r2 = r2.contains(r10)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            if (r2 == 0) goto L_0x010c
            java.lang.String r2 = ":"
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ IOException -> 0x01ea, all -> 0x01c8 }
            r2 = 1
            r1 = r1[r2]     // Catch:{ NumberFormatException -> 0x010a }
            java.lang.String r1 = r1.trim()     // Catch:{ NumberFormatException -> 0x010a }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x010a }
            r21 = r1
            goto L_0x010c
        L_0x01c8:
            r0 = move-exception
            r1 = r0
            r0 = r3
            r3 = r9
            goto L_0x01d5
        L_0x01cd:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L_0x01d4
        L_0x01d1:
            r0 = move-exception
            r1 = r0
            r0 = 0
        L_0x01d4:
            r3 = 0
        L_0x01d5:
            close(r3)
            close(r0)
            throw r1
        L_0x01dc:
            r3 = 0
        L_0x01dd:
            r9 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x01ea:
            close(r9)
            close(r3)
            r1 = r19
            r2 = r20
            r3 = r21
            if (r18 != 0) goto L_0x01fa
            r9 = 1
            goto L_0x01fc
        L_0x01fa:
            r9 = r18
        L_0x01fc:
            r10 = 1
            isCompatible = r10
            if (r0 == 0) goto L_0x0284
            if (r12 == 0) goto L_0x0218
            if (r7 != 0) goto L_0x0218
            if (r16 == 0) goto L_0x0210
            if (r17 == 0) goto L_0x0210
            java.lang.String r10 = "Emulated armv7 detected, trying to launch x86 libraries"
            android.util.Log.d(r11, r10)
            r10 = 0
            goto L_0x0223
        L_0x0210:
            java.lang.String r10 = "x86 build on non-x86 device"
            errorMsg = r10
            r10 = 0
            isCompatible = r10
            goto L_0x0223
        L_0x0218:
            r10 = 0
            if (r13 == 0) goto L_0x0223
            if (r5 != 0) goto L_0x0223
            java.lang.String r12 = "ARM build on non ARM device"
            errorMsg = r12
            isCompatible = r10
        L_0x0223:
            if (r14 == 0) goto L_0x022e
            if (r1 != 0) goto L_0x022e
            java.lang.String r12 = "MIPS build on non-MIPS device"
            errorMsg = r12
            isCompatible = r10
            goto L_0x0238
        L_0x022e:
            if (r13 == 0) goto L_0x0238
            if (r1 == 0) goto L_0x0238
            java.lang.String r12 = "ARM build on MIPS device"
            errorMsg = r12
            isCompatible = r10
        L_0x0238:
            int r10 = r0.e_machine
            r12 = 40
            if (r10 != r12) goto L_0x0251
            java.lang.String r10 = r0.att_arch
            java.lang.String r12 = "v7"
            boolean r10 = r10.startsWith(r12)
            if (r10 == 0) goto L_0x0251
            if (r6 != 0) goto L_0x0251
            java.lang.String r10 = "ARMv7 build on non-ARMv7 device"
            errorMsg = r10
            r10 = 0
            isCompatible = r10
        L_0x0251:
            int r10 = r0.e_machine
            r12 = 40
            if (r10 != r12) goto L_0x0279
            java.lang.String r10 = r0.att_arch
            java.lang.String r12 = "v6"
            boolean r10 = r10.startsWith(r12)
            if (r10 == 0) goto L_0x026b
            if (r5 != 0) goto L_0x026b
            java.lang.String r0 = "ARMv6 build on non-ARMv6 device"
            errorMsg = r0
            r10 = 0
            isCompatible = r10
            goto L_0x027a
        L_0x026b:
            r10 = 0
            boolean r0 = r0.att_fpu
            if (r0 == 0) goto L_0x027a
            if (r2 != 0) goto L_0x027a
            java.lang.String r0 = "FPU-enabled build on non-FPU device"
            errorMsg = r0
            isCompatible = r10
            goto L_0x027a
        L_0x0279:
            r10 = 0
        L_0x027a:
            if (r15 == 0) goto L_0x0284
            if (r8 != 0) goto L_0x0284
            java.lang.String r0 = "64bits build on 32bits device"
            errorMsg = r0
            isCompatible = r10
        L_0x0284:
            java.lang.String r0 = ""
            java.io.FileReader r10 = new java.io.FileReader     // Catch:{ IOException -> 0x02ce, NumberFormatException -> 0x02b2, all -> 0x02ad }
            java.lang.String r12 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r10.<init>(r12)     // Catch:{ IOException -> 0x02ce, NumberFormatException -> 0x02b2, all -> 0x02ad }
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch:{ IOException -> 0x02cf, NumberFormatException -> 0x02b3, all -> 0x02a9 }
            r12.<init>(r10)     // Catch:{ IOException -> 0x02cf, NumberFormatException -> 0x02b3, all -> 0x02a9 }
            java.lang.String r0 = r12.readLine()     // Catch:{ IOException -> 0x02d0, NumberFormatException -> 0x02b4 }
            if (r0 == 0) goto L_0x02a0
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ IOException -> 0x02d0, NumberFormatException -> 0x02b4 }
            r13 = 1148846080(0x447a0000, float:1000.0)
            float r0 = r0 / r13
            goto L_0x02a2
        L_0x02a0:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x02a2:
            close(r12)
            close(r10)
            goto L_0x02dd
        L_0x02a9:
            r0 = move-exception
            r3 = 0
            goto L_0x033a
        L_0x02ad:
            r0 = move-exception
            r3 = 0
            r10 = 0
            goto L_0x033a
        L_0x02b2:
            r10 = 0
        L_0x02b3:
            r12 = 0
        L_0x02b4:
            java.lang.String r13 = "Could not parse maximum CPU frequency!"
            android.util.Log.w(r11, r13)     // Catch:{ all -> 0x0338 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0338 }
            r13.<init>()     // Catch:{ all -> 0x0338 }
            java.lang.String r14 = "Failed to parse: "
            r13.append(r14)     // Catch:{ all -> 0x0338 }
            r13.append(r0)     // Catch:{ all -> 0x0338 }
            java.lang.String r0 = r13.toString()     // Catch:{ all -> 0x0338 }
            android.util.Log.w(r11, r0)     // Catch:{ all -> 0x0338 }
            goto L_0x02d5
        L_0x02ce:
            r10 = 0
        L_0x02cf:
            r12 = 0
        L_0x02d0:
            java.lang.String r0 = "Could not find maximum CPU frequency!"
            android.util.Log.w(r11, r0)     // Catch:{ all -> 0x0338 }
        L_0x02d5:
            close(r12)
            close(r10)
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x02dd:
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r10 = new org.videolan.libvlc.util.VLCUtil$MachineSpecs
            r10.<init>()
            machineSpecs = r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = "machineSpecs: hasArmV6: "
            r10.<init>(r12)
            r10.append(r5)
            java.lang.String r12 = ", hasArmV7: "
            r10.append(r12)
            r10.append(r6)
            java.lang.String r12 = ", hasX86: "
            r10.append(r12)
            r10.append(r7)
            java.lang.String r12 = ", is64bits: "
            r10.append(r12)
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r11, r10)
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r10 = machineSpecs
            r10.hasArmV6 = r5
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r5 = machineSpecs
            r5.hasArmV7 = r6
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r5 = machineSpecs
            r5.hasFpu = r2
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r2 = machineSpecs
            r2.hasMips = r1
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r1 = machineSpecs
            r1.hasNeon = r4
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r1 = machineSpecs
            r1.hasX86 = r7
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r1 = machineSpecs
            r1.is64bits = r8
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r1 = machineSpecs
            r1.bogoMIPS = r3
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r1 = machineSpecs
            r1.processors = r9
            org.videolan.libvlc.util.VLCUtil$MachineSpecs r1 = machineSpecs
            r1.frequency = r0
            boolean r0 = isCompatible
            return r0
        L_0x0338:
            r0 = move-exception
            r3 = r12
        L_0x033a:
            close(r3)
            close(r10)
            throw r0
        L_0x0341:
            boolean r0 = isCompatible
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.libvlc.util.VLCUtil.hasCompatibleCPU(android.content.Context):boolean");
    }

    public static MachineSpecs getMachineSpecs() {
        return machineSpecs;
    }

    private static class ElfData {
        String att_arch;
        boolean att_fpu;
        int e_machine;
        int e_shnum;
        int e_shoff;
        boolean is64bits;
        ByteOrder order;
        int sh_offset;
        int sh_size;

        private ElfData() {
        }
    }

    private static File searchLibrary(ApplicationInfo applicationInfo) {
        String[] strArr;
        if ((applicationInfo.flags & 1) != 0) {
            strArr = System.getProperty("java.library.path").split(":");
        } else {
            strArr = new String[]{applicationInfo.nativeLibraryDir};
        }
        if (strArr[0] == null) {
            Log.e(TAG, "can't find library path");
            return null;
        }
        for (String file : strArr) {
            File file2 = new File(file, "libvlcjni.so");
            if (file2.exists() && file2.canRead()) {
                return file2;
            }
        }
        Log.e(TAG, "WARNING: Can't find shared library");
        return null;
    }

    private static ElfData readLib(File file) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                ElfData elfData = new ElfData();
                if (!readHeader(randomAccessFile, elfData)) {
                    close(randomAccessFile);
                    return null;
                }
                int i = elfData.e_machine;
                if (!(i == 3 || i == 8)) {
                    if (i == 40) {
                        randomAccessFile.close();
                        RandomAccessFile randomAccessFile3 = new RandomAccessFile(file, "r");
                        try {
                            if (!readSection(randomAccessFile3, elfData)) {
                                close(randomAccessFile3);
                                return null;
                            }
                            randomAccessFile3.close();
                            randomAccessFile = new RandomAccessFile(file, "r");
                            if (!readArmAttributes(randomAccessFile, elfData)) {
                                close(randomAccessFile);
                                return null;
                            }
                            close(randomAccessFile);
                            return elfData;
                        } catch (IOException e) {
                            e = e;
                            randomAccessFile = randomAccessFile3;
                            try {
                                e.printStackTrace();
                                close(randomAccessFile);
                                return null;
                            } catch (Throwable th) {
                                th = th;
                                randomAccessFile2 = randomAccessFile;
                                close(randomAccessFile2);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile2 = randomAccessFile3;
                            close(randomAccessFile2);
                            throw th;
                        }
                    } else if (!(i == 62 || i == EM_AARCH64)) {
                        close(randomAccessFile);
                        return null;
                    }
                }
                close(randomAccessFile);
                return elfData;
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                close(randomAccessFile);
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            randomAccessFile = null;
            e.printStackTrace();
            close(randomAccessFile);
            return null;
        } catch (Throwable th3) {
            th = th3;
            close(randomAccessFile2);
            throw th;
        }
    }

    private static boolean readHeader(RandomAccessFile randomAccessFile, ElfData elfData) throws IOException {
        byte b;
        ByteOrder byteOrder;
        byte[] bArr = new byte[52];
        randomAccessFile.readFully(bArr);
        boolean z = false;
        if (bArr[0] == Byte.MAX_VALUE && bArr[1] == 69 && bArr[2] == 76 && bArr[3] == 70 && ((b = bArr[4]) == 1 || b == 2)) {
            if (b == 2) {
                z = true;
            }
            elfData.is64bits = z;
            if (bArr[5] == 1) {
                byteOrder = ByteOrder.LITTLE_ENDIAN;
            } else {
                byteOrder = ByteOrder.BIG_ENDIAN;
            }
            elfData.order = byteOrder;
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(elfData.order);
            elfData.e_machine = wrap.getShort(18);
            elfData.e_shoff = wrap.getInt(32);
            elfData.e_shnum = wrap.getShort(48);
            return true;
        }
        Log.e(TAG, "ELF header invalid");
        return false;
    }

    private static boolean readSection(RandomAccessFile randomAccessFile, ElfData elfData) throws IOException {
        byte[] bArr = new byte[40];
        randomAccessFile.seek((long) elfData.e_shoff);
        int i = 0;
        while (i < elfData.e_shnum) {
            randomAccessFile.readFully(bArr);
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(elfData.order);
            if (wrap.getInt(4) != SHT_ARM_ATTRIBUTES) {
                i++;
            } else {
                elfData.sh_offset = wrap.getInt(16);
                elfData.sh_size = wrap.getInt(20);
                return true;
            }
        }
        return false;
    }

    private static boolean readArmAttributes(RandomAccessFile randomAccessFile, ElfData elfData) throws IOException {
        byte[] bArr = new byte[elfData.sh_size];
        randomAccessFile.seek((long) elfData.sh_offset);
        randomAccessFile.readFully(bArr);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(elfData.order);
        if (wrap.get() != 65) {
            return false;
        }
        while (true) {
            if (wrap.remaining() <= 0) {
                break;
            }
            int position = wrap.position();
            int i = wrap.getInt();
            if (getString(wrap).equals("aeabi")) {
                while (wrap.position() < position + i) {
                    int position2 = wrap.position();
                    byte b = wrap.get();
                    int i2 = wrap.getInt();
                    if (b != 1) {
                        wrap.position(position2 + i2);
                    } else {
                        while (wrap.position() < position2 + i2) {
                            int uleb128 = getUleb128(wrap);
                            if (uleb128 == 6) {
                                elfData.att_arch = CPU_archs[getUleb128(wrap)];
                            } else if (uleb128 == 27) {
                                getUleb128(wrap);
                                elfData.att_fpu = true;
                            } else {
                                int i3 = uleb128 % 128;
                                if (i3 == 4 || i3 == 5 || i3 == 32 || (i3 > 32 && (i3 & 1) != 0)) {
                                    getString(wrap);
                                } else {
                                    getUleb128(wrap);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static String getString(ByteBuffer byteBuffer) {
        char c;
        StringBuilder sb = new StringBuilder(byteBuffer.limit());
        while (byteBuffer.remaining() > 0 && (c = (char) byteBuffer.get()) != 0) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static int getUleb128(ByteBuffer byteBuffer) {
        byte b;
        byte b2 = 0;
        do {
            b = byteBuffer.get();
            b2 = (b2 << 7) | (b & Byte.MAX_VALUE);
        } while ((b & 128) > 0);
        return b2;
    }

    public static Uri UriFromMrl(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length * 2);
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            if (c == '%' && charArray.length - i >= 3) {
                try {
                    int parseInt = Integer.parseInt(new String(charArray, i + 1, 2), 16);
                    if (URI_AUTHORIZED_CHARS.indexOf(parseInt) != -1) {
                        sb.append((char) parseInt);
                        i += 2;
                        i++;
                    }
                } catch (NumberFormatException unused) {
                }
            }
            sb.append(c);
            i++;
        }
        return Uri.parse(sb.toString());
    }

    public static String encodeVLCUri(Uri uri) {
        return encodeVLCString(uri.toString());
    }

    public static String encodeVLCString(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length * 2);
        for (char c : charArray) {
            if (URI_AUTHORIZED_CHARS.indexOf(c) != -1) {
                sb.append("%");
                sb.append(Integer.toHexString(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
