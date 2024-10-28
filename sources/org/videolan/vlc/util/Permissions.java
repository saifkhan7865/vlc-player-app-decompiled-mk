package org.videolan.vlc.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.animation.AnimatorKt$$ExternalSyntheticApiModelOutline0;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HelpersKt;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;
import org.videolan.vlc.gui.helpers.hf.WriteExternalDelegate;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u000e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0012\u0010 \u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007J\u000e\u0010!\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010#\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u001e\u0010$\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010'\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u0004J\u0018\u0010)\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010*\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J3\u0010+\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152!\u0010,\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00130-H\u0002J\u0018\u00101\u001a\u00020\r2\u0006\u0010\u0014\u001a\u0002022\u0006\u0010(\u001a\u00020\u0004H\u0002J\u000e\u00103\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u00104\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u00105\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J1\u00106\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152!\u0010,\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00130-J\u0018\u00107\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u0004H\u0002J\u0016\u00108\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\"\u00109\u001a\u00020\u0013*\u00020\u00152\b\b\u0002\u0010:\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006;"}, d2 = {"Lorg/videolan/vlc/util/Permissions;", "", "()V", "MANAGE_EXTERNAL_STORAGE", "", "PERMISSION_PIP", "PERMISSION_SETTINGS_TAG", "PERMISSION_STORAGE_TAG", "PERMISSION_SYSTEM_BRIGHTNESS", "PERMISSION_SYSTEM_DRAW_OVRLAYS", "PERMISSION_SYSTEM_RINGTONE", "PERMISSION_WRITE_STORAGE_TAG", "sAlertDialog", "Landroid/app/Dialog;", "getSAlertDialog", "()Landroid/app/Dialog;", "setSAlertDialog", "(Landroid/app/Dialog;)V", "askWriteStoragePermission", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "exit", "", "callback", "Ljava/lang/Runnable;", "canDrawOverlays", "context", "Landroid/content/Context;", "canReadStorage", "canSendNotifications", "canWriteSettings", "canWriteStorage", "checkDrawOverlaysPermission", "checkPiPPermission", "checkReadStoragePermission", "checkWritePermission", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "checkWriteSettingsPermission", "mode", "createDialog", "createDialogCompat", "createExternalManagerDialog", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "boolean", "createSettingsDialogCompat", "Landroid/app/Activity;", "hasAllAccess", "isPiPAllowed", "showAppSettingsPage", "showExternalPermissionDialog", "showSettingsPermissionDialog", "showStoragePermissionDialog", "requestStoragePermission", "write", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Permissions.kt */
public final class Permissions {
    public static final Permissions INSTANCE = new Permissions();
    public static final int MANAGE_EXTERNAL_STORAGE = 256;
    private static final int PERMISSION_PIP = 45;
    public static final int PERMISSION_SETTINGS_TAG = 254;
    public static final int PERMISSION_STORAGE_TAG = 255;
    private static final int PERMISSION_SYSTEM_BRIGHTNESS = 43;
    private static final int PERMISSION_SYSTEM_DRAW_OVRLAYS = 44;
    public static final int PERMISSION_SYSTEM_RINGTONE = 42;
    public static final int PERMISSION_WRITE_STORAGE_TAG = 253;
    private static Dialog sAlertDialog;

    public final boolean canWriteStorage() {
        return canWriteStorage$default(this, (Context) null, 1, (Object) null);
    }

    private Permissions() {
    }

    public final Dialog getSAlertDialog() {
        return sAlertDialog;
    }

    public final void setSAlertDialog(Dialog dialog) {
        sAlertDialog = dialog;
    }

    public final boolean canDrawOverlays(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !AndroidUtil.isMarshMallowOrLater || Settings.canDrawOverlays(context);
    }

    public final boolean isPiPAllowed(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AppOpsManager m = AnimatorKt$$ExternalSyntheticApiModelOutline0.m(context.getSystemService("appops"));
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (m == null || m.unsafeCheckOpNoThrow("android:picture_in_picture", Process.myUid(), "org.videolan.vlc") != 0) {
                return false;
            }
        } else if (m == null || m.checkOpNoThrow("android:picture_in_picture", Process.myUid(), "org.videolan.vlc") != 0) {
            return false;
        }
        return true;
    }

    public final boolean canWriteSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !AndroidUtil.isMarshMallowOrLater || Settings.System.canWrite(context);
    }

    public final boolean canReadStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !AndroidUtil.isMarshMallowOrLater || ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0 || HelpersKt.isExternalStorageManager();
    }

    public final boolean canSendNotifications(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Build.VERSION.SDK_INT <= 32 || ContextCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") == 0;
    }

    public final boolean hasAllAccess(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !KotlinExtensionsKt.isCallable(new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.fromParts(Constants.SCHEME_PACKAGE, context.getPackageName(), (String) null)), context) || HelpersKt.isExternalStorageManager();
    }

    public static /* synthetic */ boolean canWriteStorage$default(Permissions permissions, Context context, int i, Object obj) {
        if ((i & 1) != 0) {
            context = AppContextProvider.INSTANCE.getAppContext();
        }
        return permissions.canWriteStorage(context);
    }

    public final boolean canWriteStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (AndroidUtil.isROrLater) {
            return hasAllAccess(context);
        }
        return ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static /* synthetic */ boolean checkReadStoragePermission$default(Permissions permissions, FragmentActivity fragmentActivity, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return permissions.checkReadStoragePermission(fragmentActivity, z);
    }

    public final boolean checkReadStoragePermission(FragmentActivity fragmentActivity, boolean z) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (!AndroidUtil.isMarshMallowOrLater || canReadStorage(fragmentActivity)) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(fragmentActivity, "android.permission.READ_EXTERNAL_STORAGE")) {
            showStoragePermissionDialog(fragmentActivity, z);
        } else {
            requestStoragePermission(fragmentActivity, false, (Runnable) null);
        }
        return false;
    }

    public final void askWriteStoragePermission(FragmentActivity fragmentActivity, boolean z, Runnable runnable) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(runnable, "callback");
        if (ActivityCompat.shouldShowRequestPermissionRationale(fragmentActivity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            showStoragePermissionDialog(fragmentActivity, z);
        } else {
            requestStoragePermission(fragmentActivity, true, runnable);
        }
    }

    public final void checkDrawOverlaysPermission(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (AndroidUtil.isMarshMallowOrLater && !canDrawOverlays(fragmentActivity)) {
            showSettingsPermissionDialog(fragmentActivity, 44);
        }
    }

    public final void checkPiPPermission(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (!isPiPAllowed(fragmentActivity)) {
            showSettingsPermissionDialog(fragmentActivity, 45);
        }
    }

    public final void checkWriteSettingsPermission(FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (!canWriteSettings(fragmentActivity)) {
            showSettingsPermissionDialog(fragmentActivity, i);
        }
    }

    private final void showSettingsPermissionDialog(FragmentActivity fragmentActivity, int i) {
        if (!fragmentActivity.isFinishing()) {
            Dialog dialog = sAlertDialog;
            if (dialog != null) {
                Intrinsics.checkNotNull(dialog);
                if (dialog.isShowing()) {
                    return;
                }
            }
            sAlertDialog = createSettingsDialogCompat(fragmentActivity, i);
        }
    }

    public final void showStoragePermissionDialog(FragmentActivity fragmentActivity, boolean z) {
        Dialog dialog;
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (!fragmentActivity.isFinishing()) {
            Dialog dialog2 = sAlertDialog;
            if (dialog2 != null) {
                Intrinsics.checkNotNull(dialog2);
                if (dialog2.isShowing()) {
                    return;
                }
            }
            if (fragmentActivity instanceof AppCompatActivity) {
                dialog = createDialogCompat(fragmentActivity, z);
            } else {
                dialog = createDialog(fragmentActivity, z);
            }
            sAlertDialog = dialog;
        }
    }

    public final void showExternalPermissionDialog(FragmentActivity fragmentActivity, Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(function1, "listener");
        if (!fragmentActivity.isFinishing()) {
            Dialog dialog = sAlertDialog;
            if (dialog != null) {
                Intrinsics.checkNotNull(dialog);
                if (dialog.isShowing()) {
                    return;
                }
            }
            sAlertDialog = createExternalManagerDialog(fragmentActivity, function1);
        }
    }

    private final Dialog createDialog(FragmentActivity fragmentActivity, boolean z) {
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(fragmentActivity).setTitle(fragmentActivity.getString(R.string.allow_storage_access_title)).setMessage(fragmentActivity.getString(R.string.allow_storage_access_description)).setIcon(R.drawable.ic_warning).setPositiveButton(fragmentActivity.getString(R.string.permission_ask_again), new Permissions$$ExternalSyntheticLambda6(fragmentActivity));
        if (z) {
            positiveButton.setNegativeButton(fragmentActivity.getString(R.string.exit_app), new Permissions$$ExternalSyntheticLambda7(fragmentActivity)).setCancelable(false);
        }
        AlertDialog show = positiveButton.show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }

    /* access modifiers changed from: private */
    public static final void createDialog$lambda$0(FragmentActivity fragmentActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        requestStoragePermission$default(INSTANCE, fragmentActivity, false, (Runnable) null, 3, (Object) null);
        SettingsKt.putSingle((SharedPreferences) org.videolan.tools.Settings.INSTANCE.getInstance(fragmentActivity), "user_declined_storage_access", true);
    }

    /* access modifiers changed from: private */
    public static final void createDialog$lambda$1(FragmentActivity fragmentActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        fragmentActivity.finish();
    }

    private final Dialog createExternalManagerDialog(FragmentActivity fragmentActivity, Function1<? super Boolean, Unit> function1) {
        AlertDialog show = new AlertDialog.Builder(fragmentActivity).setTitle(fragmentActivity.getString(R.string.allow_storage_manager_title)).setMessage(fragmentActivity.getString(R.string.allow_storage_manager_description, new Object[]{fragmentActivity.getString(R.string.allow_storage_manager_explanation)})).setIcon(R.drawable.ic_warning).setPositiveButton(fragmentActivity.getString(R.string.ok), new Permissions$$ExternalSyntheticLambda8(function1)).setNegativeButton(fragmentActivity.getString(R.string.cancel), new Permissions$$ExternalSyntheticLambda9(fragmentActivity, function1)).setCancelable(false).show();
        if (fragmentActivity instanceof AppCompatActivity) {
            fragmentActivity.getLifecycle().addObserver(new Permissions$createExternalManagerDialog$1$1(show));
        }
        Intrinsics.checkNotNullExpressionValue(show, "apply(...)");
        return show;
    }

    /* access modifiers changed from: private */
    public static final void createExternalManagerDialog$lambda$2(Function1 function1, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(function1, "$listener");
        function1.invoke(true);
    }

    /* access modifiers changed from: private */
    public static final void createExternalManagerDialog$lambda$3(FragmentActivity fragmentActivity, Function1 function1, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        Intrinsics.checkNotNullParameter(function1, "$listener");
        fragmentActivity.finish();
        function1.invoke(false);
    }

    private final Dialog createDialogCompat(FragmentActivity fragmentActivity, boolean z) {
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(fragmentActivity).setTitle((CharSequence) fragmentActivity.getString(R.string.allow_storage_access_title)).setMessage((CharSequence) fragmentActivity.getString(R.string.allow_storage_access_description)).setIcon(R.drawable.ic_warning).setPositiveButton((CharSequence) fragmentActivity.getString(R.string.permission_ask_again), (DialogInterface.OnClickListener) new Permissions$$ExternalSyntheticLambda4(fragmentActivity));
        if (z) {
            positiveButton.setNegativeButton((CharSequence) fragmentActivity.getString(R.string.exit_app), (DialogInterface.OnClickListener) new Permissions$$ExternalSyntheticLambda5(fragmentActivity)).setCancelable(false);
        }
        androidx.appcompat.app.AlertDialog show = positiveButton.show();
        fragmentActivity.getLifecycle().addObserver(new Permissions$createDialogCompat$2$1(show));
        Intrinsics.checkNotNullExpressionValue(show, "apply(...)");
        return show;
    }

    /* access modifiers changed from: private */
    public static final void createDialogCompat$lambda$5(FragmentActivity fragmentActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        requestStoragePermission$default(INSTANCE, fragmentActivity, false, (Runnable) null, 3, (Object) null);
        SettingsKt.putSingle((SharedPreferences) org.videolan.tools.Settings.INSTANCE.getInstance(fragmentActivity), "user_declined_storage_access", true);
    }

    /* access modifiers changed from: private */
    public static final void createDialogCompat$lambda$6(FragmentActivity fragmentActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        fragmentActivity.finish();
    }

    public final void showAppSettingsPage(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.fromParts(Constants.SCHEME_PACKAGE, AppContextProvider.INSTANCE.getAppContext().getPackageName(), (String) null));
        intent.addFlags(268435456);
        try {
            fragmentActivity.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    private final Dialog createSettingsDialogCompat(Activity activity, int i) {
        int i2;
        int i3;
        String str;
        int i4;
        String str2 = "android.settings.action.MANAGE_WRITE_SETTINGS";
        switch (i) {
            case 42:
                i2 = R.string.allow_settings_access_ringtone_title;
                i3 = R.string.allow_settings_access_ringtone_description;
                break;
            case 43:
                i2 = R.string.allow_settings_access_brightness_title;
                i3 = R.string.allow_settings_access_brightness_description;
                break;
            case 44:
                i2 = R.string.allow_draw_overlays_title;
                i4 = R.string.allow_sdraw_overlays_description;
                str = "android.settings.action.MANAGE_OVERLAY_PERMISSION";
                break;
            case 45:
                i2 = R.string.allow_pip;
                i4 = R.string.allow_pip_description;
                str = "android.settings.PICTURE_IN_PICTURE_SETTINGS";
                break;
            default:
                i2 = 0;
                i3 = 0;
                break;
        }
        String str3 = str;
        i3 = i4;
        str2 = str3;
        androidx.appcompat.app.AlertDialog show = new AlertDialog.Builder(activity).setTitle((CharSequence) activity.getString(i2)).setMessage((CharSequence) activity.getString(i3)).setIcon(R.drawable.ic_warning).setPositiveButton((CharSequence) activity.getString(R.string.permission_ask_again), (DialogInterface.OnClickListener) new Permissions$$ExternalSyntheticLambda10(activity, str2)).show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }

    /* access modifiers changed from: private */
    public static final void createSettingsDialogCompat$lambda$9(Activity activity, String str, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(str, "$finalAction");
        SharedPreferences sharedPreferences = (SharedPreferences) org.videolan.tools.Settings.INSTANCE.getInstance(activity);
        Intent intent = new Intent(str);
        intent.setData(Uri.fromParts(Constants.SCHEME_PACKAGE, activity.getPackageName(), (String) null));
        try {
            activity.startActivity(intent);
        } catch (Exception unused) {
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("user_declined_settings_access", true);
        edit.apply();
    }

    static /* synthetic */ void requestStoragePermission$default(Permissions permissions, FragmentActivity fragmentActivity, boolean z, Runnable runnable, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            runnable = null;
        }
        permissions.requestStoragePermission(fragmentActivity, z, runnable);
    }

    private final void requestStoragePermission(FragmentActivity fragmentActivity, boolean z, Runnable runnable) {
        StoragePermissionsDelegate.Companion.askStoragePermission(fragmentActivity, z, runnable);
    }

    public final boolean checkWritePermission(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper, Runnable runnable) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        Intrinsics.checkNotNullParameter(runnable, "callback");
        Uri uri = mediaWrapper.getUri();
        if (HelpersKt.isExternalStorageManager()) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) "file", (Object) uri.getScheme())) {
            return false;
        }
        String path = uri.getPath();
        Intrinsics.checkNotNull(path);
        if (StringsKt.startsWith$default(path, AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null)) {
            if (AndroidUtil.isOOrLater && !canWriteStorage$default(INSTANCE, (Context) null, 1, (Object) null)) {
                askWriteStoragePermission(fragmentActivity, false, runnable);
                return false;
            }
        } else if (AndroidUtil.isLolliPopOrLater) {
            WriteExternalDelegate.Companion companion = WriteExternalDelegate.Companion;
            Intrinsics.checkNotNull(uri);
            if (companion.needsWritePermission(uri)) {
                WriteExternalDelegate.Companion.askForExtWrite(fragmentActivity, uri, runnable);
                return false;
            }
        }
        return true;
    }
}
