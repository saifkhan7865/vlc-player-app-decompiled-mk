package org.videolan.vlc.gui.helpers.hf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.resources.util.HelpersKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.util.Permissions;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\bH\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/StoragePermissionsDelegate;", "Lorg/videolan/vlc/gui/helpers/hf/BaseHeadlessFragment;", "()V", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "askOnlyRead", "", "askedPermission", "", "firstRun", "timeAsked", "", "upgrade", "withDialog", "write", "askAllAccessPermission", "", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestStorageAccess", "Companion", "CustomActionController", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StoragePermissionsDelegate.kt */
public final class StoragePermissionsDelegate extends BaseHeadlessFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/StorageHF";
    /* access modifiers changed from: private */
    public static final LiveEvent<Boolean> storageAccessGranted = new LiveEvent<>();
    private final ActivityResultLauncher<String> activityResultLauncher;
    private boolean askOnlyRead = true;
    private int askedPermission = -1;
    private boolean firstRun;
    private long timeAsked = -1;
    private boolean upgrade;
    private boolean withDialog = true;
    private boolean write;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/StoragePermissionsDelegate$CustomActionController;", "", "onStorageAccessGranted", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: StoragePermissionsDelegate.kt */
    public interface CustomActionController {
        void onStorageAccessGranted();
    }

    public StoragePermissionsDelegate() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new StoragePermissionsDelegate$$ExternalSyntheticLambda0(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.activityResultLauncher = registerForActivityResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
        if (r6.hasAllAccess(r3) == false) goto L_0x007b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            androidx.fragment.app.FragmentActivity r6 = r5.getActivity()
            if (r6 == 0) goto L_0x000e
            android.content.Intent r6 = r6.getIntent()
            goto L_0x000f
        L_0x000e:
            r6 = 0
        L_0x000f:
            r0 = 1
            r1 = 0
            if (r6 == 0) goto L_0x0025
            java.lang.String r2 = "extra_upgrade"
            boolean r2 = r6.getBooleanExtra(r2, r1)
            if (r2 == 0) goto L_0x0025
            r5.upgrade = r0
            java.lang.String r2 = "extra_first_run"
            boolean r6 = r6.getBooleanExtra(r2, r1)
            r5.firstRun = r6
        L_0x0025:
            android.os.Bundle r6 = r5.getArguments()
            if (r6 == 0) goto L_0x0032
            java.lang.String r2 = "write"
            boolean r6 = r6.getBoolean(r2)
            goto L_0x0033
        L_0x0032:
            r6 = 0
        L_0x0033:
            r5.write = r6
            android.os.Bundle r6 = r5.getArguments()
            if (r6 == 0) goto L_0x0042
            java.lang.String r2 = "with_dialog"
            boolean r6 = r6.getBoolean(r2)
            goto L_0x0043
        L_0x0042:
            r6 = 1
        L_0x0043:
            r5.withDialog = r6
            android.os.Bundle r6 = r5.getArguments()
            if (r6 == 0) goto L_0x0052
            java.lang.String r2 = "only_media"
            boolean r6 = r6.getBoolean(r2)
            goto L_0x0053
        L_0x0052:
            r6 = 0
        L_0x0053:
            r5.askOnlyRead = r6
            boolean r6 = org.videolan.libvlc.util.AndroidUtil.isMarshMallowOrLater
            java.lang.String r2 = "requireActivity(...)"
            if (r6 == 0) goto L_0x00a5
            org.videolan.vlc.util.Permissions r6 = org.videolan.vlc.util.Permissions.INSTANCE
            android.content.Context r3 = r5.requireContext()
            java.lang.String r4 = "requireContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            boolean r6 = r6.canReadStorage(r3)
            if (r6 == 0) goto L_0x007b
            org.videolan.vlc.util.Permissions r6 = org.videolan.vlc.util.Permissions.INSTANCE
            android.content.Context r3 = r5.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            boolean r6 = r6.hasAllAccess(r3)
            if (r6 != 0) goto L_0x00a5
        L_0x007b:
            java.lang.String r6 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r6 = r5.shouldShowRequestPermissionRationale(r6)
            if (r6 == 0) goto L_0x00a1
            org.videolan.vlc.gui.helpers.hf.PermissionViewmodel r6 = r5.getModel()
            boolean r6 = r6.getPermissionRationaleShown()
            if (r6 != 0) goto L_0x00a1
            org.videolan.vlc.util.Permissions r6 = org.videolan.vlc.util.Permissions.INSTANCE
            androidx.fragment.app.FragmentActivity r3 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            r6.showStoragePermissionDialog(r3, r1)
            org.videolan.vlc.gui.helpers.hf.PermissionViewmodel r6 = r5.getModel()
            r6.setPermissionRationaleShown(r0)
            goto L_0x00d2
        L_0x00a1:
            r5.requestStorageAccess(r1)
            goto L_0x00d2
        L_0x00a5:
            boolean r6 = r5.write
            if (r6 == 0) goto L_0x00d2
            java.lang.String r6 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r6 = r5.shouldShowRequestPermissionRationale(r6)
            if (r6 == 0) goto L_0x00cf
            org.videolan.vlc.gui.helpers.hf.PermissionViewmodel r6 = r5.getModel()
            boolean r6 = r6.getPermissionRationaleShown()
            if (r6 != 0) goto L_0x00cf
            org.videolan.vlc.util.Permissions r6 = org.videolan.vlc.util.Permissions.INSTANCE
            androidx.fragment.app.FragmentActivity r3 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            r6.showStoragePermissionDialog(r3, r1)
            org.videolan.vlc.gui.helpers.hf.PermissionViewmodel r6 = r5.getModel()
            r6.setPermissionRationaleShown(r0)
            goto L_0x00d2
        L_0x00cf:
            r5.requestStorageAccess(r0)
        L_0x00d2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public static final void activityResultLauncher$lambda$0(StoragePermissionsDelegate storagePermissionsDelegate, Boolean bool) {
        Intrinsics.checkNotNullParameter(storagePermissionsDelegate, "this$0");
        if (storagePermissionsDelegate.getActivity() != null) {
            if (System.currentTimeMillis() - storagePermissionsDelegate.timeAsked < 300) {
                Permissions permissions = Permissions.INSTANCE;
                FragmentActivity requireActivity = storagePermissionsDelegate.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                permissions.showAppSettingsPage(requireActivity);
                return;
            }
            int i = storagePermissionsDelegate.askedPermission;
            if (i == 253) {
                CompletableDeferred<Boolean> deferredGrant = storagePermissionsDelegate.getModel().getDeferredGrant();
                Intrinsics.checkNotNull(bool);
                deferredGrant.complete(bool);
                storagePermissionsDelegate.exit();
            } else if ((i == 255 || i == 256) && storagePermissionsDelegate.getActivity() != null) {
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue() || HelpersKt.isExternalStorageManager()) {
                    storageAccessGranted.setValue(true);
                    storagePermissionsDelegate.getModel().complete(true);
                    storagePermissionsDelegate.exit();
                    return;
                }
                storageAccessGranted.setValue(false);
                if (storagePermissionsDelegate.getModel().getPermissionPending()) {
                    storagePermissionsDelegate.getModel().getDeferredGrant().complete(false);
                }
                storagePermissionsDelegate.exit();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void requestStorageAccess(boolean z) {
        if (Build.VERSION.SDK_INT >= 30 && !this.askOnlyRead) {
            Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.fromParts(Constants.SCHEME_PACKAGE, requireContext().getPackageName(), (String) null));
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (KotlinExtensionsKt.isCallable(intent, requireActivity)) {
                if (this.withDialog) {
                    Permissions permissions = Permissions.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    permissions.showExternalPermissionDialog(requireActivity2, new StoragePermissionsDelegate$requestStorageAccess$1(this, intent));
                    return;
                }
                askAllAccessPermission(intent);
                return;
            }
        }
        String str = z ? "android.permission.WRITE_EXTERNAL_STORAGE" : "android.permission.READ_EXTERNAL_STORAGE";
        this.askedPermission = z ? Permissions.PERMISSION_WRITE_STORAGE_TAG : 255;
        this.timeAsked = System.currentTimeMillis();
        this.activityResultLauncher.launch(str);
    }

    /* access modifiers changed from: private */
    public final void askAllAccessPermission(Intent intent) {
        this.askedPermission = 256;
        this.timeAsked = System.currentTimeMillis();
        this.activityResultLauncher.launch("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
        startActivity(intent);
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bJ0\u0010\u0014\u001a\u00020\u0007*\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u0007H@¢\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\u0007*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH@¢\u0006\u0002\u0010\u001cJ\u001a\u0010\u0018\u001a\u00020\u0007*\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001e²\u0006\n\u0010\u001f\u001a\u00020 X\u0002²\u0006\n\u0010\u001f\u001a\u00020 X\u0002"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/StoragePermissionsDelegate$Companion;", "", "()V", "TAG", "", "storageAccessGranted", "Lvideolan/org/commontools/LiveEvent;", "", "getStorageAccessGranted", "()Lvideolan/org/commontools/LiveEvent;", "getAction", "Ljava/lang/Runnable;", "activity", "Landroidx/fragment/app/FragmentActivity;", "firstRun", "upgrade", "askStoragePermission", "", "write", "cb", "getStoragePermission", "withDialog", "onlyMedia", "(Landroidx/fragment/app/FragmentActivity;ZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWritePermission", "Landroidx/fragment/app/Fragment;", "uri", "Landroid/net/Uri;", "(Landroidx/fragment/app/Fragment;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/fragment/app/FragmentActivity;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release", "model", "Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: StoragePermissionsDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LiveEvent<Boolean> getStorageAccessGranted() {
            return StoragePermissionsDelegate.storageAccessGranted;
        }

        public final void askStoragePermission(FragmentActivity fragmentActivity, boolean z, Runnable runnable) {
            Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
            Intent intent = fragmentActivity.getIntent();
            boolean booleanExtra = intent != null ? intent.getBooleanExtra(Constants.EXTRA_UPGRADE, false) : false;
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivity), (CoroutineContext) null, (CoroutineStart) null, new StoragePermissionsDelegate$Companion$askStoragePermission$1(fragmentActivity, z, runnable, booleanExtra && intent.getBooleanExtra(Constants.EXTRA_FIRST_RUN, false), booleanExtra, (SharedPreferences) Settings.INSTANCE.getInstance(fragmentActivity), (Continuation<? super StoragePermissionsDelegate$Companion$askStoragePermission$1>) null), 3, (Object) null);
        }

        public static /* synthetic */ Object getStoragePermission$default(Companion companion, FragmentActivity fragmentActivity, boolean z, boolean z2, boolean z3, Continuation continuation, int i, Object obj) {
            return companion.getStoragePermission(fragmentActivity, (i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? false : z3, continuation);
        }

        public final Object getStoragePermission(FragmentActivity fragmentActivity, boolean z, boolean z2, boolean z3, Continuation<? super Boolean> continuation) {
            if (fragmentActivity.isFinishing()) {
                return Boxing.boxBoolean(false);
            }
            SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(fragmentActivity), SettingsKt.INITIAL_PERMISSION_ASKED, Boxing.boxBoolean(true));
            ComponentActivity componentActivity = fragmentActivity;
            Unit unit = null;
            Lazy viewModelLazy = new ViewModelLazy(Reflection.getOrCreateKotlinClass(PermissionViewmodel.class), new StoragePermissionsDelegate$Companion$getStoragePermission$$inlined$viewModels$default$2(componentActivity), new StoragePermissionsDelegate$Companion$getStoragePermission$$inlined$viewModels$default$1(componentActivity), new StoragePermissionsDelegate$Companion$getStoragePermission$$inlined$viewModels$default$3((Function0) null, componentActivity));
            if (getStoragePermission$lambda$0(viewModelLazy).isCompleted() && Intrinsics.areEqual((Object) getStorageAccessGranted().getValue(), (Object) Boxing.boxBoolean(true))) {
                return getStoragePermission$lambda$0(viewModelLazy).getDeferredGrant().getCompleted();
            }
            if (getStoragePermission$lambda$0(viewModelLazy).getPermissionPending()) {
                Fragment findFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag(StoragePermissionsDelegate.TAG);
                StoragePermissionsDelegate storagePermissionsDelegate = findFragmentByTag instanceof StoragePermissionsDelegate ? (StoragePermissionsDelegate) findFragmentByTag : null;
                if (storagePermissionsDelegate != null) {
                    storagePermissionsDelegate.requestStorageAccess(z);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    return Boxing.boxBoolean(false);
                }
            } else {
                getStoragePermission$lambda$0(viewModelLazy).setupDeferred();
                StoragePermissionsDelegate storagePermissionsDelegate2 = new StoragePermissionsDelegate();
                storagePermissionsDelegate2.setArguments(BundleKt.bundleOf(TuplesKt.to("write", Boxing.boxBoolean(z)), TuplesKt.to("with_dialog", Boxing.boxBoolean(z2)), TuplesKt.to("only_media", Boxing.boxBoolean(z3))));
                fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) storagePermissionsDelegate2, StoragePermissionsDelegate.TAG).commitAllowingStateLoss();
            }
            return getStoragePermission$lambda$0(viewModelLazy).getDeferredGrant().await(continuation);
        }

        private static final PermissionViewmodel getStoragePermission$lambda$0(Lazy<PermissionViewmodel> lazy) {
            return lazy.getValue();
        }

        /* access modifiers changed from: private */
        public final Runnable getAction(FragmentActivity fragmentActivity, boolean z, boolean z2) {
            return new StoragePermissionsDelegate$Companion$$ExternalSyntheticLambda0(fragmentActivity, z, z2);
        }

        /* access modifiers changed from: private */
        public static final void getAction$lambda$2(FragmentActivity fragmentActivity, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
            if (fragmentActivity instanceof CustomActionController) {
                ((CustomActionController) fragmentActivity).onStorageAccessGranted();
            } else {
                ExtensionsKt.startMedialibrary$default(fragmentActivity, z, z2, true, false, (CoroutineContextProvider) null, 24, (Object) null);
            }
        }

        public final Object getWritePermission(FragmentActivity fragmentActivity, Uri uri, Continuation<? super Boolean> continuation) {
            String path = uri.getPath();
            if (path == null || !StringsKt.startsWith$default(path, AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null)) {
                Uri uri2 = uri;
                Continuation<? super Boolean> continuation2 = continuation;
                return WriteExternalDelegateKt.getExtWritePermission(fragmentActivity, uri, continuation);
            } else if (AndroidUtil.isOOrLater && !Permissions.canWriteStorage$default(Permissions.INSTANCE, (Context) null, 1, (Object) null)) {
                return getStoragePermission$default(this, fragmentActivity, true, false, false, continuation, 6, (Object) null);
            } else {
                Uri uri3 = uri;
                return BuildersKt.withContext(Dispatchers.getIO(), new StoragePermissionsDelegate$Companion$getWritePermission$2(uri, (Continuation<? super StoragePermissionsDelegate$Companion$getWritePermission$2>) null), continuation);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object getWritePermission(androidx.fragment.app.Fragment r6, android.net.Uri r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
            /*
                r5 = this;
                boolean r0 = r8 instanceof org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$getWritePermission$3
                if (r0 == 0) goto L_0x0014
                r0 = r8
                org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$getWritePermission$3 r0 = (org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$getWritePermission$3) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$getWritePermission$3 r0 = new org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$getWritePermission$3
                r0.<init>(r5, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L_0x0033
                if (r2 != r4) goto L_0x002b
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0045
            L_0x002b:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0033:
                kotlin.ResultKt.throwOnFailure(r8)
                androidx.fragment.app.FragmentActivity r6 = r6.getActivity()
                if (r6 == 0) goto L_0x004e
                r0.label = r4
                java.lang.Object r8 = r5.getWritePermission((androidx.fragment.app.FragmentActivity) r6, (android.net.Uri) r7, (kotlin.coroutines.Continuation<? super java.lang.Boolean>) r0)
                if (r8 != r1) goto L_0x0045
                return r1
            L_0x0045:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r6 = r8.booleanValue()
                if (r6 == 0) goto L_0x004e
                r3 = 1
            L_0x004e:
                java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion.getWritePermission(androidx.fragment.app.Fragment, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
