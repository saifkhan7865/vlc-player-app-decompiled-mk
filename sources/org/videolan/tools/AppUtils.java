package org.videolan.tools;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lorg/videolan/tools/AppUtils;", "", "()V", "freeMemory", "", "totalMemory", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AppUtils.kt */
public final class AppUtils {
    public static final AppUtils INSTANCE = new AppUtils();

    private AppUtils() {
    }

    public final long totalMemory() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
        }
        return (long) (statFs.getBlockCount() * statFs.getBlockSize());
    }

    public final long freeMemory() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        return (long) (statFs.getAvailableBlocks() * statFs.getBlockSize());
    }
}
