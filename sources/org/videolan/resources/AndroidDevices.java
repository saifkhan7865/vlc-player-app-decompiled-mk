package org.videolan.resources;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.InputDevice;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.Strings;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001?B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010,\u001a\u00020\u0010J\u0010\u0010-\u001a\u00020\u00102\b\u0010.\u001a\u0004\u0018\u00010/J \u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0007J\u0006\u00108\u001a\u00020\u0010J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u00109\u001a\u00020:H\u0002J\u000e\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020=J\u0006\u0010>\u001a\u00020\u0010R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\u0019\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u001a\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\u001b\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0014\u0010\u001c\u001a\u00020\u00108BX\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u001d\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0011\u0010\u001e\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u0019\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\t¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b \u0010!R\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0011\u0010$\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0012R\u0011\u0010&\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0012R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0012¨\u0006@"}, d2 = {"Lorg/videolan/resources/AndroidDevices;", "", "()V", "EXTERNAL_PUBLIC_DIRECTORY", "", "getEXTERNAL_PUBLIC_DIRECTORY", "()Ljava/lang/String;", "TAG", "deviceWL", "", "[Ljava/lang/String;", "externalStorageDirectories", "", "getExternalStorageDirectories", "()Ljava/util/List;", "hasNavBar", "", "getHasNavBar", "()Z", "hasPiP", "getHasPiP", "hasPlayServices", "getHasPlayServices", "hasTsp", "getHasTsp", "isAmazon", "isAndroidTv", "isChromeBook", "isManufacturerBannedForMediastyleNotifications", "isPhone", "isTv", "mountBL", "getMountBL", "()[Ljava/lang/String;", "mountWL", "noMediaStyleManufacturers", "pipAllowed", "getPipAllowed", "showMediaStyle", "getShowMediaStyle", "typeBL", "typeWL", "watchDevices", "getWatchDevices", "canUseSystemNightMode", "close", "closeable", "Ljava/io/Closeable;", "getCenteredAxis", "", "event", "Landroid/view/MotionEvent;", "device", "Landroid/view/InputDevice;", "axis", "", "hasExternalStorage", "pm", "Landroid/content/pm/PackageManager;", "isDex", "ctx", "Landroid/content/Context;", "showInternalStorage", "MediaFolders", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AndroidDevices.kt */
public final class AndroidDevices {
    private static final String EXTERNAL_PUBLIC_DIRECTORY;
    public static final AndroidDevices INSTANCE;
    public static final String TAG = "VLC/UiTools/AndroidDevices";
    private static final String[] deviceWL = {"/dev/block/vold", "/dev/fuse", "/mnt/media_rw", "passthrough", "//"};
    private static final boolean hasNavBar;
    private static final boolean hasPiP;
    private static final boolean hasPlayServices;
    private static final boolean hasTsp;
    private static final boolean isAmazon = Intrinsics.areEqual((Object) Build.MANUFACTURER, (Object) "Amazon");
    private static final boolean isAndroidTv;
    private static final boolean isChromeBook;
    private static final boolean isPhone;
    private static final boolean isTv;
    private static final String[] mountBL;
    private static final String[] mountWL = {"/mnt", "/Removable", "/storage"};
    private static final String[] noMediaStyleManufacturers = {"huawei", "symphony teleca"};
    private static final boolean pipAllowed;
    private static final boolean showMediaStyle;
    private static final List<String> typeBL = CollectionsKt.listOf("tmpfs");
    private static final List<String> typeWL = CollectionsKt.listOf("vfat", "exfat", "sdcardfs", "fuse", "ntfs", "fat32", "ext3", "ext4", "esdfs");
    private static final boolean watchDevices;

    private AndroidDevices() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0155  */
    static {
        /*
            org.videolan.resources.AndroidDevices r0 = new org.videolan.resources.AndroidDevices
            r0.<init>()
            INSTANCE = r0
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r1 = r1.getPath()
            java.lang.String r2 = "getPath(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            EXTERNAL_PUBLIC_DIRECTORY = r1
            java.lang.String r2 = android.os.Build.MANUFACTURER
            java.lang.String r3 = "Amazon"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            isAmazon = r2
            r2 = 2
            java.lang.String[] r3 = new java.lang.String[r2]
            r4 = 0
            java.lang.String r5 = "huawei"
            r3[r4] = r5
            r5 = 1
            java.lang.String r6 = "symphony teleca"
            r3[r5] = r6
            noMediaStyleManufacturers = r3
            boolean r3 = r0.isManufacturerBannedForMediastyleNotifications()
            r3 = r3 ^ r5
            showMediaStyle = r3
            r3 = 9
            java.lang.String[] r6 = new java.lang.String[r3]
            java.lang.String r7 = "vfat"
            r6[r4] = r7
            java.lang.String r7 = "exfat"
            r6[r5] = r7
            java.lang.String r7 = "sdcardfs"
            r6[r2] = r7
            java.lang.String r7 = "fuse"
            r8 = 3
            r6[r8] = r7
            java.lang.String r7 = "ntfs"
            r9 = 4
            r6[r9] = r7
            java.lang.String r7 = "fat32"
            r10 = 5
            r6[r10] = r7
            java.lang.String r7 = "ext3"
            r11 = 6
            r6[r11] = r7
            java.lang.String r7 = "ext4"
            r12 = 7
            r6[r12] = r7
            java.lang.String r7 = "esdfs"
            r13 = 8
            r6[r13] = r7
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)
            typeWL = r6
            java.lang.String r6 = "tmpfs"
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)
            typeBL = r6
            java.lang.String[] r6 = new java.lang.String[r8]
            java.lang.String r7 = "/mnt"
            r6[r4] = r7
            java.lang.String r7 = "/Removable"
            r6[r5] = r7
            java.lang.String r7 = "/storage"
            r6[r2] = r7
            mountWL = r6
            r6 = 11
            java.lang.String[] r6 = new java.lang.String[r6]
            r6[r4] = r1
            java.lang.String r1 = "/mnt/secure"
            r6[r5] = r1
            java.lang.String r1 = "/mnt/shell"
            r6[r2] = r1
            java.lang.String r1 = "/mnt/asec"
            r6[r8] = r1
            java.lang.String r1 = "/mnt/nand"
            r6[r9] = r1
            java.lang.String r1 = "/mnt/runtime"
            r6[r10] = r1
            java.lang.String r1 = "/mnt/obb"
            r6[r11] = r1
            java.lang.String r1 = "/mnt/media_rw/extSdCard"
            r6[r12] = r1
            java.lang.String r1 = "/mnt/media_rw/sdcard"
            r6[r13] = r1
            java.lang.String r1 = "/storage/emulated"
            r6[r3] = r1
            java.lang.String r1 = "/var/run/arc"
            r3 = 10
            r6[r3] = r1
            mountBL = r6
            java.lang.String[] r1 = new java.lang.String[r10]
            java.lang.String r3 = "/dev/block/vold"
            r1[r4] = r3
            java.lang.String r3 = "/dev/fuse"
            r1[r5] = r3
            java.lang.String r3 = "/mnt/media_rw"
            r1[r2] = r3
            java.lang.String r3 = "passthrough"
            r1[r8] = r3
            java.lang.String r3 = "//"
            r1[r9] = r3
            deviceWL = r1
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.lang.String r3 = "HTC One V"
            r1.add(r3)
            java.lang.String r3 = "HTC One S"
            r1.add(r3)
            java.lang.String r3 = "HTC One X"
            r1.add(r3)
            java.lang.String r3 = "HTC One XL"
            r1.add(r3)
            java.lang.String r3 = android.os.Build.MODEL
            boolean r1 = r1.contains(r3)
            r1 = r1 ^ r5
            hasNavBar = r1
            org.videolan.resources.AppContextProvider r1 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r1 = r1.getAppContext()
            android.content.pm.PackageManager r3 = r1.getPackageManager()
            if (r3 == 0) goto L_0x0106
            java.lang.String r6 = "android.hardware.touchscreen"
            boolean r6 = r3.hasSystemFeature(r6)
            if (r6 == 0) goto L_0x0104
            goto L_0x0106
        L_0x0104:
            r6 = 0
            goto L_0x0107
        L_0x0106:
            r6 = 1
        L_0x0107:
            hasTsp = r6
            if (r3 == 0) goto L_0x0115
            java.lang.String r7 = "android.software.leanback"
            boolean r7 = r3.hasSystemFeature(r7)
            if (r7 == 0) goto L_0x0115
            r7 = 1
            goto L_0x0116
        L_0x0115:
            r7 = 0
        L_0x0116:
            isAndroidTv = r7
            if (r7 == 0) goto L_0x012c
            java.lang.String r8 = android.os.Build.MODEL
            java.lang.String r9 = "MODEL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            java.lang.String r9 = "Bouygtel"
            r10 = 0
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r8, r9, r4, r2, r10)
            if (r2 == 0) goto L_0x012c
            r2 = 1
            goto L_0x012d
        L_0x012c:
            r2 = 0
        L_0x012d:
            watchDevices = r2
            if (r3 == 0) goto L_0x013b
            java.lang.String r2 = "org.chromium.arc.device_management"
            boolean r2 = r3.hasSystemFeature(r2)
            if (r2 == 0) goto L_0x013b
            r2 = 1
            goto L_0x013c
        L_0x013b:
            r2 = 0
        L_0x013c:
            isChromeBook = r2
            if (r7 != 0) goto L_0x0147
            if (r2 != 0) goto L_0x0145
            if (r6 != 0) goto L_0x0145
            goto L_0x0147
        L_0x0145:
            r2 = 0
            goto L_0x0148
        L_0x0147:
            r2 = 1
        L_0x0148:
            isTv = r2
            if (r3 == 0) goto L_0x0155
            boolean r0 = r0.hasPlayServices(r3)
            if (r0 == 0) goto L_0x0153
            goto L_0x0155
        L_0x0153:
            r0 = 0
            goto L_0x0156
        L_0x0155:
            r0 = 1
        L_0x0156:
            hasPlayServices = r0
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r0 == 0) goto L_0x0166
            if (r3 == 0) goto L_0x0166
            java.lang.String r0 = "android.software.picture_in_picture"
            boolean r0 = r3.hasSystemFeature(r0)
            if (r0 != 0) goto L_0x016c
        L_0x0166:
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isNougatOrLater
            if (r0 == 0) goto L_0x016e
            if (r7 == 0) goto L_0x016e
        L_0x016c:
            r0 = 1
            goto L_0x016f
        L_0x016e:
            r0 = 0
        L_0x016f:
            hasPiP = r0
            if (r0 != 0) goto L_0x017c
            if (r6 == 0) goto L_0x017a
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r0 != 0) goto L_0x017a
            goto L_0x017c
        L_0x017a:
            r0 = 0
            goto L_0x017d
        L_0x017c:
            r0 = 1
        L_0x017d:
            pipAllowed = r0
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = androidx.core.content.ContextCompat.getSystemService(r1, r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L_0x018f
            int r0 = r0.getPhoneType()
            if (r0 == 0) goto L_0x0190
        L_0x018f:
            r4 = 1
        L_0x0190:
            isPhone = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.AndroidDevices.<clinit>():void");
    }

    public final String getEXTERNAL_PUBLIC_DIRECTORY() {
        return EXTERNAL_PUBLIC_DIRECTORY;
    }

    public final boolean isPhone() {
        return isPhone;
    }

    public final boolean getHasNavBar() {
        return hasNavBar;
    }

    public final boolean getHasTsp() {
        return hasTsp;
    }

    public final boolean isAndroidTv() {
        return isAndroidTv;
    }

    public final boolean getWatchDevices() {
        return watchDevices;
    }

    public final boolean isTv() {
        return isTv;
    }

    public final boolean isAmazon() {
        return isAmazon;
    }

    public final boolean isChromeBook() {
        return isChromeBook;
    }

    public final boolean getHasPiP() {
        return hasPiP;
    }

    public final boolean getPipAllowed() {
        return pipAllowed;
    }

    public final boolean getShowMediaStyle() {
        return showMediaStyle;
    }

    public final boolean getHasPlayServices() {
        return hasPlayServices;
    }

    public final String[] getMountBL() {
        return mountBL;
    }

    public final List<String> getExternalStorageDirectories() {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/mounts"));
            try {
                String readLine = bufferedReader2.readLine();
                while (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, AnsiRenderer.CODE_TEXT_SEPARATOR);
                    String nextToken = stringTokenizer.nextToken();
                    String nextToken2 = stringTokenizer.nextToken();
                    Intrinsics.checkNotNullExpressionValue(nextToken2, "nextToken(...)");
                    String replace = new Regex("\\\\040").replace((CharSequence) nextToken2, AnsiRenderer.CODE_TEXT_SEPARATOR);
                    String nextToken3 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null;
                    if (!arrayList.contains(replace) && !CollectionsKt.contains(typeBL, nextToken3)) {
                        if (!Strings.startsWith(mountBL, replace)) {
                            String[] strArr = deviceWL;
                            Intrinsics.checkNotNull(nextToken);
                            if (Strings.startsWith(strArr, nextToken) && (CollectionsKt.contains(typeWL, nextToken3) || Strings.startsWith(mountWL, replace))) {
                                int containsName = Strings.containsName(arrayList, Strings.getFileNameFromPath(replace));
                                if (containsName > -1) {
                                    arrayList.remove(containsName);
                                }
                                arrayList.add(replace);
                            }
                            readLine = bufferedReader2.readLine();
                        }
                    }
                    readLine = bufferedReader2.readLine();
                }
                close(bufferedReader2);
            } catch (IOException unused) {
                bufferedReader = bufferedReader2;
                close(bufferedReader);
                arrayList.remove(EXTERNAL_PUBLIC_DIRECTORY);
                return arrayList;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                close(bufferedReader);
                throw th;
            }
        } catch (IOException unused2) {
            close(bufferedReader);
            arrayList.remove(EXTERNAL_PUBLIC_DIRECTORY);
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            close(bufferedReader);
            throw th;
        }
        arrayList.remove(EXTERNAL_PUBLIC_DIRECTORY);
        return arrayList;
    }

    private final boolean isManufacturerBannedForMediastyleNotifications() {
        if (!AndroidUtil.isMarshMallowOrLater) {
            for (String str : noMediaStyleManufacturers) {
                String str2 = Build.MANUFACTURER;
                Intrinsics.checkNotNullExpressionValue(str2, "MANUFACTURER");
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String lowerCase = str2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) str, false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean hasExternalStorage() {
        return Intrinsics.areEqual((Object) Environment.getExternalStorageState(), (Object) "mounted");
    }

    public final boolean showInternalStorage() {
        return !ArraysKt.contains((T[]) new String[]{"Swisscom", "BouyguesTelecom"}, Build.BRAND) && !Intrinsics.areEqual((Object) Build.BOARD, (Object) "sprint");
    }

    public final float getCenteredAxis(MotionEvent motionEvent, InputDevice inputDevice, int i) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(inputDevice, "device");
        InputDevice.MotionRange motionRange = inputDevice.getMotionRange(i, motionEvent.getSource());
        if (motionRange == null) {
            return 0.0f;
        }
        float flat = motionRange.getFlat();
        float axisValue = motionEvent.getAxisValue(i);
        if (Math.abs(axisValue) > flat) {
            return axisValue;
        }
        return 0.0f;
    }

    public final boolean canUseSystemNightMode() {
        if (Build.VERSION.SDK_INT <= 28) {
            if (Build.VERSION.SDK_INT == 28) {
                String str = Build.MANUFACTURER;
                Intrinsics.checkNotNullExpressionValue(str, "MANUFACTURER");
                Locale locale = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale, "US");
                String lowerCase = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (Intrinsics.areEqual((Object) "samsung", (Object) lowerCase)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private final boolean hasPlayServices(PackageManager packageManager) {
        try {
            ExtensionsKt.getPackageInfoCompat(packageManager, "com.google.android.gsf", 4);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final boolean isDex(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        if (!AndroidUtil.isNougatOrLater) {
            return false;
        }
        try {
            Configuration configuration = context.getResources().getConfiguration();
            Class<?> cls = configuration.getClass();
            if (cls.getField("SEM_DESKTOP_MODE_ENABLED").getInt(cls) == cls.getField("semDesktopModeEnabled").getInt(configuration)) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u000e\u0010\f\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u000e\u0010\u000f\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u000e\u0010\u0012\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u000e\u0010\u0015\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u000e\u0010\u0018\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\bR\u0011\u0010\u001c\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\b¨\u0006#"}, d2 = {"Lorg/videolan/resources/AndroidDevices$MediaFolders;", "", "()V", "EXTERNAL_PUBLIC_DCIM_DIRECTORY_FILE", "Ljava/io/File;", "EXTERNAL_PUBLIC_DCIM_DIRECTORY_URI", "Landroid/net/Uri;", "getEXTERNAL_PUBLIC_DCIM_DIRECTORY_URI", "()Landroid/net/Uri;", "EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_FILE", "EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI", "getEXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI", "EXTERNAL_PUBLIC_MOVIES_DIRECTORY_FILE", "EXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI", "getEXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI", "EXTERNAL_PUBLIC_MUSIC_DIRECTORY_FILE", "EXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI", "getEXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI", "EXTERNAL_PUBLIC_PODCAST_DIRECTORY_FILE", "EXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI", "getEXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI", "EXTERNAL_PUBLIC_SCREENSHOTS_DIRECTORY", "EXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY", "getEXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY", "WHATSAPP_VIDEOS_FILE", "WHATSAPP_VIDEOS_FILE_A11", "WHATSAPP_VIDEOS_FILE_URI", "getWHATSAPP_VIDEOS_FILE_URI", "WHATSAPP_VIDEOS_FILE_URI_A11", "getWHATSAPP_VIDEOS_FILE_URI_A11", "getFolderUri", "file", "isOneOfMediaFolders", "", "uri", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AndroidDevices.kt */
    public static final class MediaFolders {
        private static final File EXTERNAL_PUBLIC_DCIM_DIRECTORY_FILE;
        private static final Uri EXTERNAL_PUBLIC_DCIM_DIRECTORY_URI;
        private static final File EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_FILE;
        private static final Uri EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI;
        private static final File EXTERNAL_PUBLIC_MOVIES_DIRECTORY_FILE;
        private static final Uri EXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI;
        private static final File EXTERNAL_PUBLIC_MUSIC_DIRECTORY_FILE;
        private static final Uri EXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI;
        private static final File EXTERNAL_PUBLIC_PODCAST_DIRECTORY_FILE;
        private static final Uri EXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI;
        private static final File EXTERNAL_PUBLIC_SCREENSHOTS_DIRECTORY;
        private static final Uri EXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY;
        public static final MediaFolders INSTANCE;
        private static final File WHATSAPP_VIDEOS_FILE;
        private static final File WHATSAPP_VIDEOS_FILE_A11;
        private static final Uri WHATSAPP_VIDEOS_FILE_URI;
        private static final Uri WHATSAPP_VIDEOS_FILE_URI_A11;

        private MediaFolders() {
        }

        static {
            MediaFolders mediaFolders = new MediaFolders();
            INSTANCE = mediaFolders;
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory, "getExternalStoragePublicDirectory(...)");
            EXTERNAL_PUBLIC_MOVIES_DIRECTORY_FILE = externalStoragePublicDirectory;
            File externalStoragePublicDirectory2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory2, "getExternalStoragePublicDirectory(...)");
            EXTERNAL_PUBLIC_MUSIC_DIRECTORY_FILE = externalStoragePublicDirectory2;
            File externalStoragePublicDirectory3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS);
            Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory3, "getExternalStoragePublicDirectory(...)");
            EXTERNAL_PUBLIC_PODCAST_DIRECTORY_FILE = externalStoragePublicDirectory3;
            File externalStoragePublicDirectory4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory4, "getExternalStoragePublicDirectory(...)");
            EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_FILE = externalStoragePublicDirectory4;
            File externalStoragePublicDirectory5 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory5, "getExternalStoragePublicDirectory(...)");
            EXTERNAL_PUBLIC_DCIM_DIRECTORY_FILE = externalStoragePublicDirectory5;
            File file = new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + "/WhatsApp/Media/WhatsApp Video/");
            WHATSAPP_VIDEOS_FILE = file;
            File file2 = new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + "/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Video/");
            WHATSAPP_VIDEOS_FILE_A11 = file2;
            File externalStoragePublicDirectory6 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Screenshots");
            Intrinsics.checkNotNullExpressionValue(externalStoragePublicDirectory6, "getExternalStoragePublicDirectory(...)");
            EXTERNAL_PUBLIC_SCREENSHOTS_DIRECTORY = externalStoragePublicDirectory6;
            EXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI = mediaFolders.getFolderUri(externalStoragePublicDirectory);
            EXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI = mediaFolders.getFolderUri(externalStoragePublicDirectory2);
            EXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI = mediaFolders.getFolderUri(externalStoragePublicDirectory3);
            EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI = mediaFolders.getFolderUri(externalStoragePublicDirectory4);
            EXTERNAL_PUBLIC_DCIM_DIRECTORY_URI = mediaFolders.getFolderUri(externalStoragePublicDirectory5);
            WHATSAPP_VIDEOS_FILE_URI = mediaFolders.getFolderUri(file);
            WHATSAPP_VIDEOS_FILE_URI_A11 = mediaFolders.getFolderUri(file2);
            EXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY = mediaFolders.getFolderUri(externalStoragePublicDirectory6);
        }

        public final Uri getEXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI() {
            return EXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI;
        }

        public final Uri getEXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI() {
            return EXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI;
        }

        public final Uri getEXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI() {
            return EXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI;
        }

        public final Uri getEXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI() {
            return EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI;
        }

        public final Uri getEXTERNAL_PUBLIC_DCIM_DIRECTORY_URI() {
            return EXTERNAL_PUBLIC_DCIM_DIRECTORY_URI;
        }

        public final Uri getWHATSAPP_VIDEOS_FILE_URI() {
            return WHATSAPP_VIDEOS_FILE_URI;
        }

        public final Uri getWHATSAPP_VIDEOS_FILE_URI_A11() {
            return WHATSAPP_VIDEOS_FILE_URI_A11;
        }

        public final Uri getEXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY() {
            return EXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY;
        }

        private final Uri getFolderUri(File file) {
            try {
                return Uri.parse("file://" + file.getCanonicalPath());
            } catch (IOException unused) {
                return Uri.parse("file://" + file.getPath());
            }
        }

        public final boolean isOneOfMediaFolders(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            return Intrinsics.areEqual((Object) EXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI, (Object) uri) || Intrinsics.areEqual((Object) EXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI, (Object) uri) || Intrinsics.areEqual((Object) EXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI, (Object) uri) || Intrinsics.areEqual((Object) EXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI, (Object) uri) || Intrinsics.areEqual((Object) EXTERNAL_PUBLIC_DCIM_DIRECTORY_URI, (Object) uri) || Intrinsics.areEqual((Object) WHATSAPP_VIDEOS_FILE, (Object) uri);
        }
    }

    public final boolean close(Closeable closeable) {
        if (closeable == null) {
            return false;
        }
        try {
            closeable.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
