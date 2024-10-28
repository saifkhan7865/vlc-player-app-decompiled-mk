package androidx.car.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.car.app.versioning.CarAppApiLevels;
import j$.util.Objects;

public final class AppInfo {
    public static final String MIN_API_LEVEL_METADATA_KEY = "androidx.car.app.minCarApiLevel";
    private final int mLatestCarAppApiLevel;
    private final String mLibraryVersion;
    private final int mMinCarAppApiLevel;

    public static AppInfo create(Context context) {
        int retrieveMinCarAppApiLevel = retrieveMinCarAppApiLevel(context);
        if (retrieveMinCarAppApiLevel >= CarAppApiLevels.getOldest() && retrieveMinCarAppApiLevel <= CarAppApiLevels.getLatest()) {
            return new AppInfo(retrieveMinCarAppApiLevel, CarAppApiLevels.getLatest(), context.getResources().getString(R.string.car_app_library_version));
        }
        throw new IllegalArgumentException("Min API level (androidx.car.app.minCarApiLevel=" + retrieveMinCarAppApiLevel + ") is out of range (" + CarAppApiLevels.getOldest() + "-" + CarAppApiLevels.getLatest() + ")");
    }

    public AppInfo(int i, int i2, String str) {
        this.mMinCarAppApiLevel = i;
        this.mLibraryVersion = str;
        this.mLatestCarAppApiLevel = i2;
    }

    private AppInfo() {
        this.mMinCarAppApiLevel = 0;
        this.mLibraryVersion = null;
        this.mLatestCarAppApiLevel = 0;
    }

    public static int retrieveMinCarAppApiLevel(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            int i = 0;
            if (applicationInfo.metaData != null) {
                i = applicationInfo.metaData.getInt(MIN_API_LEVEL_METADATA_KEY, 0);
            }
            if (i != 0) {
                return i;
            }
            throw new IllegalArgumentException("Min API level not declared in manifest (androidx.car.app.minCarApiLevel)");
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalArgumentException("Unable to read min API level from manifest");
        }
    }

    public String getLibraryDisplayVersion() {
        return (String) Objects.requireNonNull(this.mLibraryVersion);
    }

    public int getMinCarAppApiLevel() {
        return this.mMinCarAppApiLevel;
    }

    public int getLatestCarAppApiLevel() {
        return this.mLatestCarAppApiLevel;
    }

    public String toString() {
        return "Library version: [" + getLibraryDisplayVersion() + "] Min Car Api Level: [" + getMinCarAppApiLevel() + "] Latest Car App Api Level: [" + getLatestCarAppApiLevel() + "]";
    }
}
