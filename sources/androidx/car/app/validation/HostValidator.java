package androidx.car.app.validation;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import androidx.car.app.HostInfo;
import androidx.car.app.utils.LogTags;
import j$.util.Objects;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.fusesource.jansi.AnsiRenderer;

public final class HostValidator {
    public static final HostValidator ALLOW_ALL_HOSTS_VALIDATOR = new HostValidator((PackageManager) null, new HashMap(), true);
    public static final String TEMPLATE_RENDERER_PERMISSION = "android.car.permission.TEMPLATE_RENDERER";
    private final boolean mAllowAllHosts;
    private final Map<String, List<String>> mAllowedHosts;
    private final Map<String, Pair<Integer, Boolean>> mCallerChecked = new HashMap();
    private final PackageManager mPackageManager;

    HostValidator(PackageManager packageManager, Map<String, List<String>> map, boolean z) {
        this.mPackageManager = packageManager;
        this.mAllowedHosts = map;
        this.mAllowAllHosts = z;
    }

    public boolean isValidHost(HostInfo hostInfo) {
        Objects.requireNonNull(hostInfo);
        if (Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
            Log.d(LogTags.TAG_HOST_VALIDATION, "Evaluating " + hostInfo);
        }
        if (!this.mAllowAllHosts) {
            Boolean checkCache = checkCache(hostInfo);
            if (checkCache != null) {
                return checkCache.booleanValue();
            }
            boolean validateHost = validateHost(hostInfo);
            updateCache(hostInfo, validateHost);
            return validateHost;
        } else if (!Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
            return true;
        } else {
            Log.d(LogTags.TAG_HOST_VALIDATION, "Accepted - Validator disabled, all hosts allowed");
            return true;
        }
    }

    public Map<String, List<String>> getAllowedHosts() {
        return Collections.unmodifiableMap(this.mAllowedHosts);
    }

    private PackageInfo getPackageInfo(String str) {
        try {
            if (this.mPackageManager == null) {
                Log.d(LogTags.TAG_HOST_VALIDATION, "PackageManager is null. Package info cannot be found for package " + str);
                return null;
            } else if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getPackageInfo(this.mPackageManager, str);
            } else {
                return this.mPackageManager.getPackageInfo(str, 4160);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(LogTags.TAG_HOST_VALIDATION, "Package " + str + " not found", e);
            return null;
        }
    }

    private boolean validateHost(HostInfo hostInfo) {
        String packageName = hostInfo.getPackageName();
        PackageInfo packageInfo = getPackageInfo(packageName);
        if (packageInfo == null) {
            Log.w(LogTags.TAG_HOST_VALIDATION, "Rejected - package name " + packageName + " not found");
            return false;
        }
        Signature[] signatures = getSignatures(packageInfo);
        if (signatures == null || signatures.length == 0) {
            Log.w(LogTags.TAG_HOST_VALIDATION, "Package " + packageName + " is not signed or it has more than one signature");
            return false;
        }
        int i = packageInfo.applicationInfo.uid;
        if (i == hostInfo.getUid()) {
            boolean hasPermissionGranted = hasPermissionGranted(packageInfo, TEMPLATE_RENDERER_PERMISSION);
            boolean isAllowListed = isAllowListed(packageName, signatures);
            if (i == Process.myUid()) {
                if (Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
                    Log.d(LogTags.TAG_HOST_VALIDATION, "Accepted - Local service call");
                }
                return true;
            } else if (isAllowListed) {
                if (Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
                    Log.d(LogTags.TAG_HOST_VALIDATION, "Accepted - Host in allow-list");
                }
                return true;
            } else if (i == 1000) {
                if (Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
                    Log.d(LogTags.TAG_HOST_VALIDATION, "Accepted - System binding");
                }
                return true;
            } else if (hasPermissionGranted) {
                if (Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
                    Log.d(LogTags.TAG_HOST_VALIDATION, "Accepted - Host has android.car.permission.TEMPLATE_RENDERER");
                }
                return true;
            } else {
                Log.e(LogTags.TAG_HOST_VALIDATION, String.format("Unrecognized host.\nIf this is a valid caller, please add the following to your CarAppService#createHostValidator() implementation:\nreturn new HostValidator.Builder(context)\n\t.addAllowedHost(\"%s\", \"%s\");\n\t.build()", new Object[]{packageName, getDigest(signatures[0])}));
                return false;
            }
        } else {
            throw new IllegalStateException("Host " + hostInfo + " doesn't match caller's actual UID " + i);
        }
    }

    private boolean isAllowListed(String str, Signature[] signatureArr) {
        List list = this.mAllowedHosts.get(str);
        if (list == null) {
            return false;
        }
        for (Signature digest : signatureArr) {
            if (list.contains(getDigest(digest))) {
                return true;
            }
        }
        return false;
    }

    private Boolean checkCache(HostInfo hostInfo) {
        Pair pair = this.mCallerChecked.get(hostInfo.getPackageName());
        if (pair != null && ((Integer) pair.first).intValue() == hostInfo.getUid()) {
            return (Boolean) pair.second;
        }
        return null;
    }

    private void updateCache(HostInfo hostInfo, boolean z) {
        this.mCallerChecked.put(hostInfo.getPackageName(), Pair.create(Integer.valueOf(hostInfo.getUid()), Boolean.valueOf(z)));
    }

    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("SHA256");
        } catch (NoSuchAlgorithmException e) {
            Log.e(LogTags.TAG_HOST_VALIDATION, "Could not find SHA256 hash algorithm", e);
            return null;
        }
    }

    private Signature[] getSignatures(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getSignatures(packageInfo);
        }
        if (packageInfo.signatures == null || packageInfo.signatures.length != 1) {
            return null;
        }
        return packageInfo.signatures;
    }

    private String getDigest(Signature signature) {
        byte[] byteArray = signature.toByteArray();
        MessageDigest messageDigest = getMessageDigest();
        if (messageDigest == null) {
            return null;
        }
        messageDigest.update(byteArray);
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder((digest.length * 3) - 1);
        int length = digest.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
        }
        return sb.toString();
    }

    private static boolean hasPermissionGranted(PackageInfo packageInfo, String str) {
        if (!(packageInfo.requestedPermissionsFlags == null || packageInfo.requestedPermissions == null)) {
            for (int i = 0; i < packageInfo.requestedPermissionsFlags.length; i++) {
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0 && i < packageInfo.requestedPermissions.length && str.equals(packageInfo.requestedPermissions[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final class Api28Impl {
        private Api28Impl() {
        }

        static Signature[] getSignatures(PackageInfo packageInfo) {
            if (packageInfo.signingInfo == null) {
                return null;
            }
            return packageInfo.signingInfo.getSigningCertificateHistory();
        }

        static PackageInfo getPackageInfo(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
            return packageManager.getPackageInfo(str, 134221824);
        }
    }

    public static final class Builder {
        private final Map<String, List<String>> mAllowedHosts = new HashMap();
        private final Context mContext;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder addAllowedHost(String str, String str2) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(str2);
            List list = this.mAllowedHosts.get(str);
            if (list == null) {
                list = new ArrayList();
                this.mAllowedHosts.put(str, list);
            }
            list.add(str2);
            return this;
        }

        public Builder addAllowedHosts(int i) {
            String[] stringArray = this.mContext.getResources().getStringArray(i);
            if (stringArray != null) {
                int length = stringArray.length;
                int i2 = 0;
                while (i2 < length) {
                    String str = stringArray[i2];
                    String[] split = str.split(AnsiRenderer.CODE_LIST_SEPARATOR, -1);
                    if (split.length == 2) {
                        addAllowedHost(cleanUp(split[1]), cleanUp(split[0]));
                        i2++;
                    } else {
                        throw new IllegalArgumentException("Invalid allowed host entry: '" + str + "'");
                    }
                }
                return this;
            }
            throw new IllegalArgumentException("Invalid allowlist res id: " + i);
        }

        public HostValidator build() {
            return new HostValidator(this.mContext.getPackageManager(), this.mAllowedHosts, false);
        }

        private String cleanUp(String str) {
            return str.toLowerCase(Locale.US).replace(AnsiRenderer.CODE_TEXT_SEPARATOR, "");
        }
    }
}
