package androidx.car.app;

import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CarAppPermission {
    public static final String ACCESS_SURFACE = "androidx.car.app.ACCESS_SURFACE";
    public static final String MAP_TEMPLATES = "androidx.car.app.MAP_TEMPLATES";
    public static final String NAVIGATION_TEMPLATES = "androidx.car.app.NAVIGATION_TEMPLATES";

    @Retention(RetentionPolicy.SOURCE)
    public @interface LibraryPermission {
    }

    public static void checkHasPermission(Context context, String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            throw new SecurityException("The car app does not have the required permission: " + str);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:6|7|(3:9|(2:11|(2:20|13)(1:14))|19)|17|18) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        if (r3 < r2) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003c, code lost:
        if (r0[r3].equals(r6) != false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        android.util.Log.e(androidx.car.app.utils.LogTags.TAG, "Package name not found on the system: " + r5.getPackageName(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        throw new java.lang.SecurityException("The car app does not have a required permission: " + r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r0 = r5.getPackageManager().getPackageInfo(r5.getPackageName(), 4096);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002e, code lost:
        if (r0.requestedPermissions != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r0 = r0.requestedPermissions;
        r2 = r0.length;
        r3 = 0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void checkHasLibraryPermission(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 3
            java.lang.String r1 = "CarApp"
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L_0x001a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Checking to see if the car app requested the required library permission: "
            r0.<init>(r2)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L_0x001a:
            checkHasPermission(r5, r6)     // Catch:{ SecurityException -> 0x001e }
            return
        L_0x001e:
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r2 = r5.getPackageName()     // Catch:{ NameNotFoundException -> 0x0042 }
            r3 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String[] r2 = r0.requestedPermissions     // Catch:{ NameNotFoundException -> 0x0042 }
            if (r2 == 0) goto L_0x0058
            java.lang.String[] r0 = r0.requestedPermissions     // Catch:{ NameNotFoundException -> 0x0042 }
            int r2 = r0.length     // Catch:{ NameNotFoundException -> 0x0042 }
            r3 = 0
        L_0x0034:
            if (r3 >= r2) goto L_0x0058
            r4 = r0[r3]     // Catch:{ NameNotFoundException -> 0x0042 }
            boolean r4 = r4.equals(r6)     // Catch:{ NameNotFoundException -> 0x0042 }
            if (r4 == 0) goto L_0x003f
            return
        L_0x003f:
            int r3 = r3 + 1
            goto L_0x0034
        L_0x0042:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Package name not found on the system: "
            r2.<init>(r3)
            java.lang.String r5 = r5.getPackageName()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            android.util.Log.e(r1, r5, r0)
        L_0x0058:
            java.lang.SecurityException r5 = new java.lang.SecurityException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The car app does not have a required permission: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            goto L_0x006d
        L_0x006c:
            throw r5
        L_0x006d:
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.CarAppPermission.checkHasLibraryPermission(android.content.Context, java.lang.String):void");
    }

    private CarAppPermission() {
    }
}
