package androidx.core.content.pm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import j$.util.Objects;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortcutManagerCompat {
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final int DEFAULT_MAX_ICON_DIMENSION_DP = 96;
    private static final int DEFAULT_MAX_ICON_DIMENSION_LOWRAM_DP = 48;
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    public static final int FLAG_MATCH_CACHED = 8;
    public static final int FLAG_MATCH_DYNAMIC = 2;
    public static final int FLAG_MATCH_MANIFEST = 1;
    public static final int FLAG_MATCH_PINNED = 4;
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    private static final String SHORTCUT_LISTENER_INTENT_FILTER_ACTION = "androidx.core.content.pm.SHORTCUT_LISTENER";
    private static final String SHORTCUT_LISTENER_META_DATA_KEY = "androidx.core.content.pm.shortcut_listener_impl";
    private static volatile List<ShortcutInfoChangeListener> sShortcutInfoChangeListeners;
    private static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShortcutMatchFlags {
    }

    private ShortcutManagerCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRequestPinShortcutSupported(android.content.Context r4) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L_0x0017
            java.lang.Class r0 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m()
            java.lang.Object r4 = r4.getSystemService(r0)
            android.content.pm.ShortcutManager r4 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r4)
            boolean r4 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m$1((android.content.pm.ShortcutManager) r4)
            return r4
        L_0x0017:
            java.lang.String r0 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r1 = androidx.core.content.ContextCompat.checkSelfPermission(r4, r0)
            r2 = 0
            if (r1 == 0) goto L_0x0021
            return r2
        L_0x0021:
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r3 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r1.<init>(r3)
            java.util.List r4 = r4.queryBroadcastReceivers(r1, r2)
            java.util.Iterator r4 = r4.iterator()
        L_0x0034:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0052
            java.lang.Object r1 = r4.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r1 = r1.activityInfo
            java.lang.String r1 = r1.permission
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x0050
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0034
        L_0x0050:
            r4 = 1
            return r4
        L_0x0052:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.ShortcutManagerCompat.isRequestPinShortcutSupported(android.content.Context):boolean");
    }

    public static boolean requestPinShortcut(Context context, ShortcutInfoCompat shortcutInfoCompat, final IntentSender intentSender) {
        if (Build.VERSION.SDK_INT <= 32 && shortcutInfoCompat.isExcludedFromSurfaces(1)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
        }
        if (!isRequestPinShortcutSupported(context)) {
            return false;
        }
        Intent addToIntent = shortcutInfoCompat.addToIntent(new Intent(ACTION_INSTALL_SHORTCUT));
        if (intentSender == null) {
            context.sendBroadcast(addToIntent);
            return true;
        }
        context.sendOrderedBroadcast(addToIntent, (String) null, new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                try {
                    intentSender.sendIntent(context, 0, (Intent) null, (IntentSender.OnFinished) null, (Handler) null);
                } catch (IntentSender.SendIntentException unused) {
                }
            }
        }, (Handler) null, -1, (String) null, (Bundle) null);
        return true;
    }

    public static Intent createShortcutResultIntent(Context context, ShortcutInfoCompat shortcutInfoCompat) {
        Intent m = Build.VERSION.SDK_INT >= 26 ? PathKt$$ExternalSyntheticApiModelOutline0.m(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), shortcutInfoCompat.toShortcutInfo()) : null;
        if (m == null) {
            m = new Intent();
        }
        return shortcutInfoCompat.addToIntent(m);
    }

    public static List<ShortcutInfoCompat> getShortcuts(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return ShortcutInfoCompat.fromShortcuts(context, PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())).getShortcuts(i));
        }
        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManager m = PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m()));
            ArrayList arrayList = new ArrayList();
            if ((i & 1) != 0) {
                arrayList.addAll(PathKt$$ExternalSyntheticApiModelOutline0.m$2(m));
            }
            if ((i & 2) != 0) {
                arrayList.addAll(PathKt$$ExternalSyntheticApiModelOutline0.m$1(m));
            }
            if ((i & 4) != 0) {
                arrayList.addAll(PathKt$$ExternalSyntheticApiModelOutline0.m(m));
            }
            return ShortcutInfoCompat.fromShortcuts(context, arrayList);
        }
        if ((i & 2) != 0) {
            try {
                return getShortcutInfoSaverInstance(context).getShortcuts();
            } catch (Exception unused) {
            }
        }
        return Collections.emptyList();
    }

    public static boolean addDynamicShortcuts(Context context, List<ShortcutInfoCompat> list) {
        List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconsToBitmapIcons(context, removeShortcutsExcludedFromSurface);
        }
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList();
            for (ShortcutInfoCompat shortcutInfo : removeShortcutsExcludedFromSurface) {
                arrayList.add(shortcutInfo.toShortcutInfo());
            }
            if (!PathKt$$ExternalSyntheticApiModelOutline0.m$1(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), (List) arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).addShortcuts(removeShortcutsExcludedFromSurface);
        for (ShortcutInfoChangeListener onShortcutAdded : getShortcutInfoListeners(context)) {
            onShortcutAdded.onShortcutAdded(list);
        }
        return true;
    }

    public static int getMaxShortcutCountPerActivity(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return PathKt$$ExternalSyntheticApiModelOutline0.m(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())));
        }
        return 5;
    }

    public static boolean isRateLimitingActive(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return PathKt$$ExternalSyntheticApiModelOutline0.m(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())));
        }
        return getShortcuts(context, 3).size() == getMaxShortcutCountPerActivity(context);
    }

    public static int getIconMaxWidth(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return PathKt$$ExternalSyntheticApiModelOutline0.m$2(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())));
        }
        return getIconDimensionInternal(context, true);
    }

    public static int getIconMaxHeight(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return PathKt$$ExternalSyntheticApiModelOutline0.m$1(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())));
        }
        return getIconDimensionInternal(context, false);
    }

    public static void reportShortcutUsed(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        if (Build.VERSION.SDK_INT >= 25) {
            PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())).reportShortcutUsed(str);
        }
        for (ShortcutInfoChangeListener onShortcutUsageReported : getShortcutInfoListeners(context)) {
            onShortcutUsageReported.onShortcutUsageReported(Collections.singletonList(str));
        }
    }

    public static boolean setDynamicShortcuts(Context context, List<ShortcutInfoCompat> list) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(list);
        List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList(removeShortcutsExcludedFromSurface.size());
            for (ShortcutInfoCompat shortcutInfo : removeShortcutsExcludedFromSurface) {
                arrayList.add(shortcutInfo.toShortcutInfo());
            }
            if (!PathKt$$ExternalSyntheticApiModelOutline0.m(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), (List) arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        getShortcutInfoSaverInstance(context).addShortcuts(removeShortcutsExcludedFromSurface);
        for (ShortcutInfoChangeListener next : getShortcutInfoListeners(context)) {
            next.onAllShortcutsRemoved();
            next.onShortcutAdded(list);
        }
        return true;
    }

    public static List<ShortcutInfoCompat> getDynamicShortcuts(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            List<Object> m$1 = PathKt$$ExternalSyntheticApiModelOutline0.m$1(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())));
            ArrayList arrayList = new ArrayList(m$1.size());
            for (Object m : m$1) {
                arrayList.add(new ShortcutInfoCompat.Builder(context, PathKt$$ExternalSyntheticApiModelOutline0.m(m)).build());
            }
            return arrayList;
        }
        try {
            return getShortcutInfoSaverInstance(context).getShortcuts();
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static boolean updateShortcuts(Context context, List<ShortcutInfoCompat> list) {
        List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconsToBitmapIcons(context, removeShortcutsExcludedFromSurface);
        }
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList();
            for (ShortcutInfoCompat shortcutInfo : removeShortcutsExcludedFromSurface) {
                arrayList.add(shortcutInfo.toShortcutInfo());
            }
            if (!PathKt$$ExternalSyntheticApiModelOutline0.m$2(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), (List) arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).addShortcuts(removeShortcutsExcludedFromSurface);
        for (ShortcutInfoChangeListener onShortcutUpdated : getShortcutInfoListeners(context)) {
            onShortcutUpdated.onShortcutUpdated(list);
        }
        return true;
    }

    static boolean convertUriIconToBitmapIcon(Context context, ShortcutInfoCompat shortcutInfoCompat) {
        Bitmap decodeStream;
        IconCompat iconCompat;
        if (shortcutInfoCompat.mIcon == null) {
            return false;
        }
        int i = shortcutInfoCompat.mIcon.mType;
        if (i != 6 && i != 4) {
            return true;
        }
        InputStream uriInputStream = shortcutInfoCompat.mIcon.getUriInputStream(context);
        if (uriInputStream == null || (decodeStream = BitmapFactory.decodeStream(uriInputStream)) == null) {
            return false;
        }
        if (i == 6) {
            iconCompat = IconCompat.createWithAdaptiveBitmap(decodeStream);
        } else {
            iconCompat = IconCompat.createWithBitmap(decodeStream);
        }
        shortcutInfoCompat.mIcon = iconCompat;
        return true;
    }

    static void convertUriIconsToBitmapIcons(Context context, List<ShortcutInfoCompat> list) {
        for (ShortcutInfoCompat shortcutInfoCompat : new ArrayList(list)) {
            if (!convertUriIconToBitmapIcon(context, shortcutInfoCompat)) {
                list.remove(shortcutInfoCompat);
            }
        }
    }

    public static void disableShortcuts(Context context, List<String> list, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 25) {
            PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())).disableShortcuts(list, charSequence);
        }
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutRemoved : getShortcutInfoListeners(context)) {
            onShortcutRemoved.onShortcutRemoved(list);
        }
    }

    public static void enableShortcuts(Context context, List<ShortcutInfoCompat> list) {
        List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList(list.size());
            for (ShortcutInfoCompat shortcutInfoCompat : removeShortcutsExcludedFromSurface) {
                arrayList.add(shortcutInfoCompat.mId);
            }
            PathKt$$ExternalSyntheticApiModelOutline0.m(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), (List) arrayList);
        }
        getShortcutInfoSaverInstance(context).addShortcuts(removeShortcutsExcludedFromSurface);
        for (ShortcutInfoChangeListener onShortcutAdded : getShortcutInfoListeners(context)) {
            onShortcutAdded.onShortcutAdded(list);
        }
    }

    public static void removeDynamicShortcuts(Context context, List<String> list) {
        if (Build.VERSION.SDK_INT >= 25) {
            PathKt$$ExternalSyntheticApiModelOutline0.m$1(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), (List) list);
        }
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutRemoved : getShortcutInfoListeners(context)) {
            onShortcutRemoved.onShortcutRemoved(list);
        }
    }

    public static void removeAllDynamicShortcuts(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            PathKt$$ExternalSyntheticApiModelOutline0.m(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())));
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        for (ShortcutInfoChangeListener onAllShortcutsRemoved : getShortcutInfoListeners(context)) {
            onAllShortcutsRemoved.onAllShortcutsRemoved();
        }
    }

    public static void removeLongLivedShortcuts(Context context, List<String> list) {
        if (Build.VERSION.SDK_INT < 30) {
            removeDynamicShortcuts(context, list);
            return;
        }
        PathKt$$ExternalSyntheticApiModelOutline0.m$2(PathKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(PathKt$$ExternalSyntheticApiModelOutline0.m())), (List) list);
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutRemoved : getShortcutInfoListeners(context)) {
            onShortcutRemoved.onShortcutRemoved(list);
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static boolean pushDynamicShortcut(android.content.Context r6, androidx.core.content.pm.ShortcutInfoCompat r7) {
        /*
            androidx.core.util.Preconditions.checkNotNull(r6)
            androidx.core.util.Preconditions.checkNotNull(r7)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 32
            r2 = 1
            if (r0 > r1) goto L_0x0030
            boolean r0 = r7.isExcludedFromSurfaces(r2)
            if (r0 == 0) goto L_0x0030
            java.util.List r6 = getShortcutInfoListeners(r6)
            java.util.Iterator r6 = r6.iterator()
        L_0x001b:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x002f
            java.lang.Object r0 = r6.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r0 = (androidx.core.content.pm.ShortcutInfoChangeListener) r0
            java.util.List r1 = java.util.Collections.singletonList(r7)
            r0.onShortcutAdded(r1)
            goto L_0x001b
        L_0x002f:
            return r2
        L_0x0030:
            int r0 = getMaxShortcutCountPerActivity(r6)
            r1 = 0
            if (r0 != 0) goto L_0x0038
            return r1
        L_0x0038:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 29
            if (r3 > r4) goto L_0x0041
            convertUriIconToBitmapIcon(r6, r7)
        L_0x0041:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 30
            if (r3 < r4) goto L_0x005b
            java.lang.Class r3 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m()
            java.lang.Object r3 = r6.getSystemService(r3)
            android.content.pm.ShortcutManager r3 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r3)
            android.content.pm.ShortcutInfo r4 = r7.toShortcutInfo()
            androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r3, (android.content.pm.ShortcutInfo) r4)
            goto L_0x009c
        L_0x005b:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 25
            if (r3 < r4) goto L_0x009c
            java.lang.Class r3 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m()
            java.lang.Object r3 = r6.getSystemService(r3)
            android.content.pm.ShortcutManager r3 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r3)
            boolean r4 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r3)
            if (r4 == 0) goto L_0x0074
            return r1
        L_0x0074:
            java.util.List r4 = androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m$1((android.content.pm.ShortcutManager) r3)
            int r5 = r4.size()
            if (r5 < r0) goto L_0x008d
            java.lang.String r4 = androidx.core.content.pm.ShortcutManagerCompat.Api25Impl.getShortcutInfoWithLowestRank(r4)
            java.lang.String[] r5 = new java.lang.String[r2]
            r5[r1] = r4
            java.util.List r4 = java.util.Arrays.asList(r5)
            androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m$1((android.content.pm.ShortcutManager) r3, (java.util.List) r4)
        L_0x008d:
            android.content.pm.ShortcutInfo[] r4 = new android.content.pm.ShortcutInfo[r2]
            android.content.pm.ShortcutInfo r5 = r7.toShortcutInfo()
            r4[r1] = r5
            java.util.List r4 = java.util.Arrays.asList(r4)
            androidx.core.graphics.PathKt$$ExternalSyntheticApiModelOutline0.m$1((android.content.pm.ShortcutManager) r3, (java.util.List) r4)
        L_0x009c:
            androidx.core.content.pm.ShortcutInfoCompatSaver r3 = getShortcutInfoSaverInstance(r6)
            java.util.List r4 = r3.getShortcuts()     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            int r5 = r4.size()     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            if (r5 < r0) goto L_0x00b9
            java.lang.String r0 = getShortcutInfoCompatWithLowestRank(r4)     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            r4[r1] = r0     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            java.util.List r0 = java.util.Arrays.asList(r4)     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            r3.removeShortcuts(r0)     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
        L_0x00b9:
            androidx.core.content.pm.ShortcutInfoCompat[] r0 = new androidx.core.content.pm.ShortcutInfoCompat[r2]     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            r0[r1] = r7     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            r3.addShortcuts(r0)     // Catch:{ Exception -> 0x010d, all -> 0x00e8 }
            java.util.List r0 = getShortcutInfoListeners(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x00cc:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00e0
            java.lang.Object r1 = r0.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r1 = (androidx.core.content.pm.ShortcutInfoChangeListener) r1
            java.util.List r3 = java.util.Collections.singletonList(r7)
            r1.onShortcutAdded(r3)
            goto L_0x00cc
        L_0x00e0:
            java.lang.String r7 = r7.getId()
            reportShortcutUsed(r6, r7)
            return r2
        L_0x00e8:
            r0 = move-exception
            java.util.List r1 = getShortcutInfoListeners(r6)
            java.util.Iterator r1 = r1.iterator()
        L_0x00f1:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0105
            java.lang.Object r2 = r1.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r2 = (androidx.core.content.pm.ShortcutInfoChangeListener) r2
            java.util.List r3 = java.util.Collections.singletonList(r7)
            r2.onShortcutAdded(r3)
            goto L_0x00f1
        L_0x0105:
            java.lang.String r7 = r7.getId()
            reportShortcutUsed(r6, r7)
            throw r0
        L_0x010d:
            java.util.List r0 = getShortcutInfoListeners(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x0115:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0129
            java.lang.Object r2 = r0.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r2 = (androidx.core.content.pm.ShortcutInfoChangeListener) r2
            java.util.List r3 = java.util.Collections.singletonList(r7)
            r2.onShortcutAdded(r3)
            goto L_0x0115
        L_0x0129:
            java.lang.String r7 = r7.getId()
            reportShortcutUsed(r6, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.ShortcutManagerCompat.pushDynamicShortcut(android.content.Context, androidx.core.content.pm.ShortcutInfoCompat):boolean");
    }

    private static String getShortcutInfoCompatWithLowestRank(List<ShortcutInfoCompat> list) {
        int i = -1;
        String str = null;
        for (ShortcutInfoCompat next : list) {
            if (next.getRank() > i) {
                String id = next.getId();
                str = id;
                i = next.getRank();
            }
        }
        return str;
    }

    static void setShortcutInfoCompatSaver(ShortcutInfoCompatSaver<Void> shortcutInfoCompatSaver) {
        sShortcutInfoCompatSaver = shortcutInfoCompatSaver;
    }

    static void setShortcutInfoChangeListeners(List<ShortcutInfoChangeListener> list) {
        sShortcutInfoChangeListeners = list;
    }

    static List<ShortcutInfoChangeListener> getShortcutInfoChangeListeners() {
        return sShortcutInfoChangeListeners;
    }

    private static int getIconDimensionInternal(Context context, boolean z) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int max = Math.max(1, Build.VERSION.SDK_INT < 19 || activityManager == null || activityManager.isLowRamDevice() ? 48 : 96);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) max) * ((z ? displayMetrics.xdpi : displayMetrics.ydpi) / 160.0f));
    }

    private static ShortcutInfoCompatSaver<?> getShortcutInfoSaverInstance(Context context) {
        if (sShortcutInfoCompatSaver == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                } catch (Exception unused) {
                }
            }
            if (sShortcutInfoCompatSaver == null) {
                sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
            }
        }
        return sShortcutInfoCompatSaver;
    }

    private static List<ShortcutInfoChangeListener> getShortcutInfoListeners(Context context) {
        Bundle bundle;
        String string;
        if (sShortcutInfoChangeListeners == null) {
            ArrayList arrayList = new ArrayList();
            if (Build.VERSION.SDK_INT >= 21) {
                PackageManager packageManager = context.getPackageManager();
                Intent intent = new Intent(SHORTCUT_LISTENER_INTENT_FILTER_ACTION);
                intent.setPackage(context.getPackageName());
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 128)) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    if (!(activityInfo == null || (bundle = activityInfo.metaData) == null || (string = bundle.getString(SHORTCUT_LISTENER_META_DATA_KEY)) == null)) {
                        try {
                            arrayList.add((ShortcutInfoChangeListener) Class.forName(string, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context}));
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            if (sShortcutInfoChangeListeners == null) {
                sShortcutInfoChangeListeners = arrayList;
            }
        }
        return sShortcutInfoChangeListeners;
    }

    private static List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface(List<ShortcutInfoCompat> list, int i) {
        Objects.requireNonNull(list);
        if (Build.VERSION.SDK_INT > 32) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        for (ShortcutInfoCompat next : list) {
            if (next.isExcludedFromSurfaces(i)) {
                arrayList.remove(next);
            }
        }
        return arrayList;
    }

    private static class Api25Impl {
        private Api25Impl() {
        }

        static String getShortcutInfoWithLowestRank(List<ShortcutInfo> list) {
            int i = -1;
            String str = null;
            for (ShortcutInfo next : list) {
                if (next.getRank() > i) {
                    String id = next.getId();
                    str = id;
                    i = next.getRank();
                }
            }
            return str;
        }
    }
}
