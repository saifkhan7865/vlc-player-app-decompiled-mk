package org.videolan.vlc.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.ExtensionsKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J$\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\tJ\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00192\u0006\u0010\u001a\u001a\u00020\tJ\u001a\u0010\u001b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH@¢\u0006\u0002\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\tR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R'\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/vlc/util/AccessControl;", "", "()V", "callingUidChecked", "", "", "Lorg/videolan/vlc/util/AuthEntry;", "certificateAllowList", "", "", "Lorg/videolan/vlc/util/CertInfo;", "getCertificateAllowList", "()Ljava/util/Map;", "certificateAllowList$delegate", "Lkotlin/Lazy;", "platformSignature", "genSigSha256", "certificate", "", "getCallingPackage", "ctx", "Landroid/content/Context;", "callingUid", "clientPackageName", "getKeysByPackage", "", "packageName", "getSignature", "callingPackage", "loadAuthorizedKeys", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logCaller", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessControl.kt */
public final class AccessControl {
    public static final AccessControl INSTANCE;
    private static final Map<Integer, AuthEntry> callingUidChecked = new LinkedHashMap();
    private static final Lazy certificateAllowList$delegate = LazyKt.lazy(AccessControl$certificateAllowList$2.INSTANCE);
    private static final String platformSignature;

    private AccessControl() {
    }

    static {
        AccessControl accessControl = new AccessControl();
        INSTANCE = accessControl;
        platformSignature = accessControl.getSignature(AppContextProvider.INSTANCE.getAppContext(), "android");
    }

    private final Map<String, CertInfo> getCertificateAllowList() {
        return (Map) certificateAllowList$delegate.getValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r2 = r2.getKeys();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> getKeysByPackage(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.util.Map r0 = r1.getCertificateAllowList()
            java.lang.Object r2 = r0.get(r2)
            org.videolan.vlc.util.CertInfo r2 = (org.videolan.vlc.util.CertInfo) r2
            if (r2 == 0) goto L_0x0017
            java.util.List r2 = r2.getKeys()
            if (r2 != 0) goto L_0x001b
        L_0x0017:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L_0x001b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.AccessControl.getKeysByPackage(java.lang.String):java.util.List");
    }

    public static /* synthetic */ void logCaller$default(AccessControl accessControl, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        accessControl.logCaller(i, str);
    }

    public final void logCaller(int i, String str) {
        List<String> keys;
        Map<Integer, AuthEntry> map = callingUidChecked;
        if (map.get(Integer.valueOf(i)) == null) {
            Context appContext = AppContextProvider.INSTANCE.getAppContext();
            String callingPackage = getCallingPackage(appContext, i, str);
            if (i == Process.myUid()) {
                Log.i("VLC/AccessControl", "Known access from self (" + appContext.getPackageName() + ") to VLC");
                map.put(Integer.valueOf(i), new AuthEntry(true, appContext.getPackageName(), "VLC UID"));
            } else if (i == 1000) {
                Log.i("VLC/AccessControl", "Known access from system to VLC");
                map.put(Integer.valueOf(i), new AuthEntry(true, "system", "System Process"));
            } else {
                if (callingPackage != null) {
                    String signature = getSignature(appContext, callingPackage);
                    if (Intrinsics.areEqual((Object) signature, (Object) platformSignature)) {
                        Log.i("VLC/AccessControl", "Known access from Android platform (" + callingPackage + ") to VLC");
                        map.put(Integer.valueOf(i), new AuthEntry(true, callingPackage, "Known Platform Signature"));
                        return;
                    }
                    CertInfo certInfo = getCertificateAllowList().get(callingPackage);
                    if (!(certInfo == null || (keys = certInfo.getKeys()) == null)) {
                        for (String areEqual : keys) {
                            if (Intrinsics.areEqual((Object) signature, (Object) areEqual)) {
                                Log.i("VLC/AccessControl", "Known access from " + certInfo.getName() + " (" + callingPackage + ") to VLC");
                                callingUidChecked.put(Integer.valueOf(i), new AuthEntry(true, callingPackage, "Known App Signature"));
                                return;
                            }
                        }
                    }
                    Log.i("VLC/AccessControl", "Unknown access from signature " + signature + " (" + callingPackage + ") to VLC");
                    callingUidChecked.put(Integer.valueOf(i), new AuthEntry(false, callingPackage, "Unknown Signature"));
                }
                Log.i("VLC/AccessControl", "Access history: " + callingUidChecked);
            }
        }
    }

    public static /* synthetic */ String getCallingPackage$default(AccessControl accessControl, Context context, int i, String str, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        return accessControl.getCallingPackage(context, i, str);
    }

    public final String getCallingPackage(Context context, int i, String str) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null) {
            return null;
        }
        String str2 = (String) ArraysKt.firstOrNull((T[]) packagesForUid);
        if (str == null || Intrinsics.areEqual((Object) str, (Object) str2)) {
            return str2;
        }
        Log.i("VLC/AccessControl", "Unexpected package name mismatch between " + str + " and " + str2);
        return null;
    }

    /* access modifiers changed from: private */
    public final Object loadAuthorizedKeys(Continuation<? super Map<String, CertInfo>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AccessControl$loadAuthorizedKeys$2((Continuation<? super AccessControl$loadAuthorizedKeys$2>) null), continuation);
    }

    private final String getSignature(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            PackageInfo packageInfoCompat = ExtensionsKt.getPackageInfoCompat(packageManager, str, 64);
            if (packageInfoCompat.signatures == null || packageInfoCompat.signatures.length != 1) {
                return null;
            }
            byte[] byteArray = packageInfoCompat.signatures[0].toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
            return genSigSha256(byteArray);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("VLC/AccessControl", "Calling package name not found: " + str, e);
            return null;
        }
    }

    private final String genSigSha256(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(bArr);
            byte[] digest = instance.digest();
            Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
            return ArraysKt.joinToString$default(digest, (CharSequence) ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) AccessControl$genSigSha256$1.INSTANCE, 30, (Object) null);
        } catch (NoSuchAlgorithmException e) {
            Log.e("VLC/AccessControl", "Message digest algorithm SHA-256 not found", e);
            return null;
        }
    }
}
