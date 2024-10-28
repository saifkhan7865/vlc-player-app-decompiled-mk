package org.videolan.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0019\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\b¨\u0006\u001d"}, d2 = {"Lorg/videolan/tools/DeviceInfo;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hasPiP", "", "getHasPiP", "()Z", "hasTsp", "getHasTsp", "isAmazon", "isAndroidTv", "isChromeBook", "isPhone", "isTv", "pipAllowed", "getPipAllowed", "pm", "Landroid/content/pm/PackageManager;", "kotlin.jvm.PlatformType", "getPm", "()Landroid/content/pm/PackageManager;", "tm", "Landroid/telephony/TelephonyManager;", "getTm", "()Landroid/telephony/TelephonyManager;", "watchDevices", "getWatchDevices", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Settings.kt */
public final class DeviceInfo {
    private final boolean hasPiP;
    private final boolean hasTsp;
    private final boolean isAmazon;
    private final boolean isAndroidTv;
    private final boolean isChromeBook;
    private final boolean isPhone;
    private final boolean isTv;
    private final boolean pipAllowed;
    private final PackageManager pm;
    private final TelephonyManager tm;
    private final boolean watchDevices;

    public DeviceInfo(Context context) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        this.pm = packageManager;
        Object systemService = ContextCompat.getSystemService(context, TelephonyManager.class);
        Intrinsics.checkNotNull(systemService);
        TelephonyManager telephonyManager = (TelephonyManager) systemService;
        this.tm = telephonyManager;
        boolean z2 = true;
        this.isPhone = telephonyManager.getPhoneType() != 0;
        boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.touchscreen");
        this.hasTsp = hasSystemFeature;
        boolean hasSystemFeature2 = packageManager.hasSystemFeature("android.software.leanback");
        this.isAndroidTv = hasSystemFeature2;
        if (hasSystemFeature2) {
            String str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
            if (StringsKt.startsWith$default(str, "Bouygtel", false, 2, (Object) null)) {
                z = true;
                this.watchDevices = z;
                boolean hasSystemFeature3 = packageManager.hasSystemFeature("org.chromium.arc.device_management");
                this.isChromeBook = hasSystemFeature3;
                this.isTv = !hasSystemFeature2 || (!hasSystemFeature3 && !hasSystemFeature);
                this.isAmazon = Intrinsics.areEqual((Object) "Amazon", (Object) Build.MANUFACTURER);
                boolean z3 = (Build.VERSION.SDK_INT >= 26 && packageManager.hasSystemFeature("android.software.picture_in_picture")) || (Build.VERSION.SDK_INT >= 24 && hasSystemFeature2);
                this.hasPiP = z3;
                if (!z3 && (!hasSystemFeature || Build.VERSION.SDK_INT >= 26)) {
                    z2 = false;
                }
                this.pipAllowed = z2;
            }
        }
        z = false;
        this.watchDevices = z;
        boolean hasSystemFeature32 = packageManager.hasSystemFeature("org.chromium.arc.device_management");
        this.isChromeBook = hasSystemFeature32;
        this.isTv = !hasSystemFeature2 || (!hasSystemFeature32 && !hasSystemFeature);
        this.isAmazon = Intrinsics.areEqual((Object) "Amazon", (Object) Build.MANUFACTURER);
        if (Build.VERSION.SDK_INT >= 26 || packageManager.hasSystemFeature("android.software.picture_in_picture")) {
            this.hasPiP = z3;
            z2 = false;
            this.pipAllowed = z2;
        }
        this.hasPiP = z3;
        z2 = false;
        this.pipAllowed = z2;
    }

    public final PackageManager getPm() {
        return this.pm;
    }

    public final TelephonyManager getTm() {
        return this.tm;
    }

    public final boolean isPhone() {
        return this.isPhone;
    }

    public final boolean getHasTsp() {
        return this.hasTsp;
    }

    public final boolean isAndroidTv() {
        return this.isAndroidTv;
    }

    public final boolean getWatchDevices() {
        return this.watchDevices;
    }

    public final boolean isChromeBook() {
        return this.isChromeBook;
    }

    public final boolean isTv() {
        return this.isTv;
    }

    public final boolean isAmazon() {
        return this.isAmazon;
    }

    public final boolean getHasPiP() {
        return this.hasPiP;
    }

    public final boolean getPipAllowed() {
        return this.pipAllowed;
    }
}
