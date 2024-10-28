package org.videolan.vlc.gui.dialogs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.fusesource.jansi.internal.OSInfo;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogUpdateBinding;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J$\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/UpdateDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogUpdateBinding;", "newInstall", "", "updateDate", "Ljava/util/Date;", "updateURL", "", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onSaveInstanceState", "outState", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UpdateDialog.kt */
public final class UpdateDialog extends VLCBottomSheetDialogFragment {
    /* access modifiers changed from: private */
    public DialogUpdateBinding binding;
    private boolean newInstall;
    private Date updateDate;
    /* access modifiers changed from: private */
    public String updateURL;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public View initialFocusedView() {
        DialogUpdateBinding dialogUpdateBinding = this.binding;
        if (dialogUpdateBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogUpdateBinding = null;
        }
        TextView textView = dialogUpdateBinding.title;
        Intrinsics.checkNotNullExpressionValue(textView, "title");
        return textView;
    }

    public void onCreate(Bundle bundle) {
        String str;
        long j;
        boolean z;
        super.onCreate(bundle);
        if (bundle == null || (str = bundle.getString(UpdateDialogKt.UPDATE_URL)) == null) {
            Bundle arguments = getArguments();
            str = arguments != null ? arguments.getString(UpdateDialogKt.UPDATE_URL) : null;
        }
        if (str != null) {
            this.updateURL = str;
            if (bundle != null) {
                j = bundle.getLong(UpdateDialogKt.UPDATE_DATE);
            } else {
                Bundle arguments2 = getArguments();
                if (arguments2 != null) {
                    j = arguments2.getLong(UpdateDialogKt.UPDATE_DATE);
                } else {
                    throw new IllegalStateException("Update date not provided");
                }
            }
            this.updateDate = new Date(j);
            if (bundle != null) {
                z = bundle.getBoolean(UpdateDialogKt.NEW_INSTALL);
            } else {
                Bundle arguments3 = getArguments();
                z = arguments3 != null ? arguments3.getBoolean(UpdateDialogKt.NEW_INSTALL) : false;
            }
            this.newInstall = z;
            return;
        }
        throw new IllegalStateException("Update URL not provided");
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        String str = this.updateURL;
        Date date = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateURL");
            str = null;
        }
        bundle.putString(UpdateDialogKt.UPDATE_URL, str);
        Date date2 = this.updateDate;
        if (date2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateDate");
        } else {
            date = date2;
        }
        bundle.putLong(UpdateDialogKt.UPDATE_DATE, date.getTime());
        bundle.putBoolean(UpdateDialogKt.NEW_INSTALL, this.newInstall);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogUpdateBinding inflate = DialogUpdateBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogUpdateBinding dialogUpdateBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        KotlinExtensionsKt.setGone(inflate.download);
        DialogUpdateBinding dialogUpdateBinding2 = this.binding;
        if (dialogUpdateBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogUpdateBinding2 = null;
        }
        dialogUpdateBinding2.download.setOnClickListener(new UpdateDialog$$ExternalSyntheticLambda1(this));
        DialogUpdateBinding dialogUpdateBinding3 = this.binding;
        if (dialogUpdateBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogUpdateBinding3 = null;
        }
        dialogUpdateBinding3.openInBrowser.setOnClickListener(new UpdateDialog$$ExternalSyntheticLambda2(this));
        DialogUpdateBinding dialogUpdateBinding4 = this.binding;
        if (dialogUpdateBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogUpdateBinding4 = null;
        }
        CheckBox checkBox = dialogUpdateBinding4.neverAgain;
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        checkBox.setChecked(!((SharedPreferences) settings.getInstance(requireActivity)).getBoolean(SettingsKt.KEY_SHOW_UPDATE, true));
        DialogUpdateBinding dialogUpdateBinding5 = this.binding;
        if (dialogUpdateBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogUpdateBinding5 = null;
        }
        dialogUpdateBinding5.neverAgain.setOnCheckedChangeListener(new UpdateDialog$$ExternalSyntheticLambda3(this));
        if (Build.VERSION.SDK_INT >= 21) {
            str = AppUtils$$ExternalSyntheticApiModelOutline0.m()[0];
        } else {
            str = Build.CPU_ABI;
        }
        try {
            Map mapOf = MapsKt.mapOf(new Pair("armeabi-v7a", "armv7"), new Pair("arm64-v8a", OSInfo.ARM64), new Pair(OSInfo.X86, OSInfo.X86), new Pair(OSInfo.X86_64, OSInfo.X86_64));
            if (mapOf.containsKey(str)) {
                str2 = (String) mapOf.get(str);
                DialogUpdateBinding dialogUpdateBinding6 = this.binding;
                if (dialogUpdateBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    dialogUpdateBinding6 = null;
                }
                TextView textView = dialogUpdateBinding6.nightlyDate;
                int i = R.string.nightly_version;
                DateFormat dateInstance = DateFormat.getDateInstance(3);
                Date date = this.updateDate;
                if (date == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateDate");
                    date = null;
                }
                textView.setText(getString(i, dateInstance.format(date), str2));
                if (this.newInstall) {
                    DialogUpdateBinding dialogUpdateBinding7 = this.binding;
                    if (dialogUpdateBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        dialogUpdateBinding7 = null;
                    }
                    dialogUpdateBinding7.title.setText(getString(R.string.install_nightly_title));
                }
                DialogUpdateBinding dialogUpdateBinding8 = this.binding;
                if (dialogUpdateBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    dialogUpdateBinding8 = null;
                }
                TextView textView2 = dialogUpdateBinding8.descriptionText;
                if (this.newInstall) {
                    str3 = getString(R.string.install_text);
                } else {
                    str3 = getString(R.string.update_text) + "\n\n" + getString(R.string.install_text);
                }
                textView2.setText(str3);
                DialogUpdateBinding dialogUpdateBinding9 = this.binding;
                if (dialogUpdateBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogUpdateBinding = dialogUpdateBinding9;
                }
                View root = dialogUpdateBinding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                return root;
            }
            throw new Exception("Unsupported architecture");
        } catch (Exception unused) {
            str2 = "";
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(UpdateDialog updateDialog, View view) {
        Intrinsics.checkNotNullParameter(updateDialog, "this$0");
        if (Build.VERSION.SDK_INT < 26 || updateDialog.requireActivity().getPackageManager().canRequestPackageInstalls()) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(updateDialog), (CoroutineContext) null, (CoroutineStart) null, new UpdateDialog$onCreateView$1$1(updateDialog, (Continuation<? super UpdateDialog$onCreateView$1$1>) null), 3, (Object) null);
            return;
        }
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("package:%s", Arrays.copyOf(new Object[]{updateDialog.requireActivity().getPackageName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        updateDialog.startActivityForResult(intent.setData(Uri.parse(format)), 1);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(UpdateDialog updateDialog, View view) {
        Intrinsics.checkNotNullParameter(updateDialog, "this$0");
        String str = updateDialog.updateURL;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateURL");
            str = null;
        }
        updateDialog.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$2(UpdateDialog updateDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(updateDialog, "this$0");
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = updateDialog.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), SettingsKt.KEY_SHOW_UPDATE, Boolean.valueOf(!z));
    }
}
