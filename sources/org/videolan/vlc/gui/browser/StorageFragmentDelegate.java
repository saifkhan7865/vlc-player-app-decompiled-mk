package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.os.Handler;
import android.widget.CheckBox;
import androidx.collection.SimpleArrayMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.RootsEventsCb;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J@\u0010\u001a\u001a\u00020\u001026\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\tH\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0016J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\nH\u0016J\b\u0010!\u001a\u00020\u0010H\u0016J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\nH\u0016J\u0010\u0010$\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\nH\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\u0018\u0010&\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u000eH\u0016J\u0018\u0010(\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u000eH\u0016J\u0018\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u000eH\u0016J\u0018\u0010+\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u000eH\u0016J\u0018\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\nH\u0002J\b\u0010.\u001a\u00020\u0010H\u0016J\u001b\u0010/\u001a\u00020\u00102\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¢\u0006\u0002\u00100J\u0010\u00101\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X.¢\u0006\u0004\n\u0002\u0010\u0007R@\u0010\b\u001a4\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u00062"}, d2 = {"Lorg/videolan/vlc/gui/browser/StorageFragmentDelegate;", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "Lorg/videolan/medialibrary/interfaces/RootsEventsCb;", "()V", "adapters", "", "Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;", "[Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;", "bannedFolderCallback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "folder", "", "banned", "", "context", "Landroid/content/Context;", "handler", "Landroid/os/Handler;", "processingFolders", "Landroidx/collection/SimpleArrayMap;", "Landroid/widget/CheckBox;", "getProcessingFolders", "()Landroidx/collection/SimpleArrayMap;", "addBannedFoldersCallback", "callback", "addRootsCallback", "checkBoxAction", "v", "Landroid/view/View;", "mrl", "onDiscoveryCompleted", "onDiscoveryFailed", "entryPoint", "onDiscoveryProgress", "onDiscoveryStarted", "onRootAdded", "success", "onRootBanned", "onRootRemoved", "entrypoint", "onRootUnbanned", "processEvent", "cbp", "removeRootsCallback", "withAdapters", "([Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;)V", "withContext", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageFragmentDelegate.kt */
public final class StorageFragmentDelegate implements IStorageFragmentDelegate, RootsEventsCb {
    private StorageBrowserAdapter[] adapters;
    private Function2<? super String, ? super Boolean, Unit> bannedFolderCallback;
    private Context context;
    private final Handler handler = new Handler();
    private final SimpleArrayMap<String, CheckBox> processingFolders = new SimpleArrayMap<>();

    public void onDiscoveryFailed(String str) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
    }

    public void onDiscoveryProgress(String str) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
    }

    public void onDiscoveryStarted() {
    }

    public void onRootAdded(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
    }

    public SimpleArrayMap<String, CheckBox> getProcessingFolders() {
        return this.processingFolders;
    }

    public void withContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public void withAdapters(StorageBrowserAdapter[] storageBrowserAdapterArr) {
        Intrinsics.checkNotNullParameter(storageBrowserAdapterArr, "adapters");
        this.adapters = storageBrowserAdapterArr;
    }

    public void addBannedFoldersCallback(Function2<? super String, ? super Boolean, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "callback");
        this.bannedFolderCallback = function2;
    }

    public void addRootsCallback() {
        Medialibrary.getInstance().addRootsEventsCb(this);
    }

    public void removeRootsCallback() {
        Medialibrary.getInstance().removeRootsEventsCb(this);
    }

    /* JADX WARNING: type inference failed for: r11v7, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkBoxAction(android.view.View r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "mrl"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = r10
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r0 = (org.videolan.vlc.gui.helpers.ThreeStatesCheckbox) r0
            int r1 = r0.getState()
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L_0x0017
            r1 = 1
            goto L_0x0018
        L_0x0017:
            r1 = 0
        L_0x0018:
            java.lang.String r4 = "context"
            r5 = 0
            if (r1 == 0) goto L_0x004d
            r6 = r11
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r7 = "file://"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r8 = 2
            boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, (boolean) r2, (int) r8, (java.lang.Object) r5)
            if (r6 == 0) goto L_0x004d
            android.content.Context r6 = r9.context
            if (r6 != 0) goto L_0x0033
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = r5
        L_0x0033:
            boolean r6 = org.videolan.resources.util.HelpersKt.canReadStorage(r6)
            if (r6 != 0) goto L_0x004d
            org.videolan.vlc.util.Permissions r10 = org.videolan.vlc.util.Permissions.INSTANCE
            android.content.Context r11 = r9.context
            if (r11 != 0) goto L_0x0043
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x0044
        L_0x0043:
            r5 = r11
        L_0x0044:
            androidx.fragment.app.FragmentActivity r5 = (androidx.fragment.app.FragmentActivity) r5
            r10.showStoragePermissionDialog(r5, r2)
            r0.setState(r2)
            return
        L_0x004d:
            android.content.Context r6 = r9.context
            if (r6 != 0) goto L_0x0055
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = r5
        L_0x0055:
            boolean r4 = r6 instanceof org.videolan.vlc.gui.SecondaryActivity
            if (r4 == 0) goto L_0x005c
            r5 = r6
            org.videolan.vlc.gui.SecondaryActivity r5 = (org.videolan.vlc.gui.SecondaryActivity) r5
        L_0x005c:
            if (r5 == 0) goto L_0x0095
            boolean r4 = r5.isOnboarding()
            if (r4 != r3) goto L_0x0095
            java.lang.String r10 = org.videolan.tools.PathUtilsKt.sanitizePath(r11)
            if (r1 == 0) goto L_0x0084
            org.videolan.vlc.MediaParsingService$Companion r11 = org.videolan.vlc.MediaParsingService.Companion
            java.util.List r11 = r11.getPreselectedStorages()
            org.videolan.vlc.gui.browser.StorageFragmentDelegate$checkBoxAction$1 r0 = new org.videolan.vlc.gui.browser.StorageFragmentDelegate$checkBoxAction$1
            r0.<init>(r10)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            kotlin.collections.CollectionsKt.removeAll(r11, r0)
            org.videolan.vlc.MediaParsingService$Companion r11 = org.videolan.vlc.MediaParsingService.Companion
            java.util.List r11 = r11.getPreselectedStorages()
            r11.add(r10)
            goto L_0x00d5
        L_0x0084:
            org.videolan.vlc.MediaParsingService$Companion r11 = org.videolan.vlc.MediaParsingService.Companion
            java.util.List r11 = r11.getPreselectedStorages()
            org.videolan.vlc.gui.browser.StorageFragmentDelegate$checkBoxAction$2 r0 = new org.videolan.vlc.gui.browser.StorageFragmentDelegate$checkBoxAction$2
            r0.<init>(r10)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            kotlin.collections.CollectionsKt.removeAll(r11, r0)
            goto L_0x00d5
        L_0x0095:
            if (r1 == 0) goto L_0x00cb
            org.videolan.vlc.gui.helpers.MedialibraryUtils r1 = org.videolan.vlc.gui.helpers.MedialibraryUtils.INSTANCE
            android.content.Context r0 = r0.getContext()
            android.content.Context r0 = r0.getApplicationContext()
            java.lang.String r3 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.addDir(r11, r0)
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            android.content.Context r1 = r10.getContext()
            java.lang.String r3 = "getContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            java.lang.Object r0 = r0.getInstance(r1)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            r1 = -1
            java.lang.String r3 = "ml_scan"
            int r1 = r0.getInt(r3, r1)
            if (r1 == 0) goto L_0x00d0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            org.videolan.tools.SettingsKt.putSingle(r0, r3, r1)
            goto L_0x00d0
        L_0x00cb:
            org.videolan.vlc.gui.helpers.MedialibraryUtils r0 = org.videolan.vlc.gui.helpers.MedialibraryUtils.INSTANCE
            r0.removeDir(r11)
        L_0x00d0:
            android.widget.CheckBox r10 = (android.widget.CheckBox) r10
            r9.processEvent(r10, r11)
        L_0x00d5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.StorageFragmentDelegate.checkBoxAction(android.view.View, java.lang.String):void");
    }

    private final void processEvent(CheckBox checkBox, String str) {
        checkBox.setEnabled(false);
        getProcessingFolders().put(str, checkBox);
    }

    /* access modifiers changed from: private */
    public static final void onRootBanned$lambda$0(StorageFragmentDelegate storageFragmentDelegate, String str) {
        Intrinsics.checkNotNullParameter(storageFragmentDelegate, "this$0");
        Intrinsics.checkNotNullParameter(str, "$entryPoint");
        Function2<? super String, ? super Boolean, Unit> function2 = storageFragmentDelegate.bannedFolderCallback;
        if (function2 != null) {
            function2.invoke(str, true);
        }
    }

    public void onRootBanned(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
        this.handler.post(new StorageFragmentDelegate$$ExternalSyntheticLambda1(this, str));
    }

    /* access modifiers changed from: private */
    public static final void onRootUnbanned$lambda$1(StorageFragmentDelegate storageFragmentDelegate, String str) {
        Intrinsics.checkNotNullParameter(storageFragmentDelegate, "this$0");
        Intrinsics.checkNotNullParameter(str, "$entryPoint");
        Function2<? super String, ? super Boolean, Unit> function2 = storageFragmentDelegate.bannedFolderCallback;
        if (function2 != null) {
            function2.invoke(str, false);
        }
    }

    public void onRootUnbanned(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
        this.handler.post(new StorageFragmentDelegate$$ExternalSyntheticLambda2(this, str));
    }

    public void onRootRemoved(String str, boolean z) {
        CheckBox remove;
        Intrinsics.checkNotNullParameter(str, "entrypoint");
        if (StringsKt.endsWith$default(str, "/", false, 2, (Object) null)) {
            str = str.substring(0, str.length() - 1);
            Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
        }
        if (getProcessingFolders().containsKey(str) && (remove = getProcessingFolders().remove(str)) != null) {
            this.handler.post(new StorageFragmentDelegate$$ExternalSyntheticLambda3(remove, z, this));
        }
    }

    /* access modifiers changed from: private */
    public static final void onRootRemoved$lambda$4$lambda$3(CheckBox checkBox, boolean z, StorageFragmentDelegate storageFragmentDelegate) {
        Intrinsics.checkNotNullParameter(checkBox, "$it");
        Intrinsics.checkNotNullParameter(storageFragmentDelegate, "this$0");
        checkBox.setEnabled(true);
        if (z) {
            StorageBrowserAdapter[] storageBrowserAdapterArr = storageFragmentDelegate.adapters;
            if (storageBrowserAdapterArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapters");
                storageBrowserAdapterArr = null;
            }
            for (StorageBrowserAdapter storageBrowserAdapter : storageBrowserAdapterArr) {
                Context context2 = storageFragmentDelegate.context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                storageBrowserAdapter.updateMediaDirs(context2);
                storageBrowserAdapter.notifyDataSetChanged();
            }
            return;
        }
        checkBox.setChecked(true);
    }

    /* access modifiers changed from: private */
    public static final void onDiscoveryCompleted$lambda$5(StorageFragmentDelegate storageFragmentDelegate) {
        Intrinsics.checkNotNullParameter(storageFragmentDelegate, "this$0");
        int size = storageFragmentDelegate.getProcessingFolders().size();
        for (int i = 0; i < size; i++) {
            CheckBox checkBox = storageFragmentDelegate.getProcessingFolders().get(storageFragmentDelegate.getProcessingFolders().keyAt(i));
            if (checkBox != null) {
                checkBox.setEnabled(true);
            }
        }
    }

    public void onDiscoveryCompleted() {
        this.handler.post(new StorageFragmentDelegate$$ExternalSyntheticLambda0(this));
        StorageBrowserAdapter[] storageBrowserAdapterArr = this.adapters;
        if (storageBrowserAdapterArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapters");
            storageBrowserAdapterArr = null;
        }
        for (StorageBrowserAdapter storageBrowserAdapter : storageBrowserAdapterArr) {
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context2 = null;
            }
            storageBrowserAdapter.updateMediaDirs(context2);
        }
    }
}
