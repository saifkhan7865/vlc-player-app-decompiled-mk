package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.Dialog;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.VlcLoginDialogBinding;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u001a\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0006\u0010\u001a\u001a\u00020\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VlcLoginDialog;", "Lorg/videolan/vlc/gui/dialogs/VlcDialog;", "Lorg/videolan/libvlc/Dialog$LoginDialog;", "Lorg/videolan/vlc/databinding/VlcLoginDialogBinding;", "Landroid/view/View$OnFocusChangeListener;", "()V", "layout", "", "getLayout", "()I", "settings", "Landroid/content/SharedPreferences;", "onAttach", "", "context", "Landroid/content/Context;", "onFocusChange", "v", "Landroid/view/View;", "hasFocus", "", "onLogin", "onViewCreated", "view", "savedInstanceState", "Landroid/os/Bundle;", "store", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VlcLoginDialog.kt */
public final class VlcLoginDialog extends VlcDialog<Dialog.LoginDialog, VlcLoginDialogBinding> implements View.OnFocusChangeListener {
    private final int layout = R.layout.vlc_login_dialog;
    private SharedPreferences settings;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return this.layout;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (Settings.INSTANCE.getShowTvUi() && !AndroidDevices.INSTANCE.getHasPlayServices()) {
            View.OnFocusChangeListener onFocusChangeListener = this;
            ((VlcLoginDialogBinding) getBinding()).login.setOnFocusChangeListener(onFocusChangeListener);
            ((VlcLoginDialogBinding) getBinding()).password.setOnFocusChangeListener(onFocusChangeListener);
        }
        ((VlcLoginDialogBinding) getBinding()).store.setOnFocusChangeListener(this);
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(requireActivity);
    }

    public final void onLogin(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        ((Dialog.LoginDialog) getVlcDialog()).postLogin(StringsKt.trim((CharSequence) ((VlcLoginDialogBinding) getBinding()).login.getText().toString()).toString(), StringsKt.trim((CharSequence) ((VlcLoginDialogBinding) getBinding()).password.getText().toString()).toString(), ((VlcLoginDialogBinding) getBinding()).store.isChecked());
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        SettingsKt.putSingle(sharedPreferences, SettingsKt.LOGIN_STORE, Boolean.valueOf(((VlcLoginDialogBinding) getBinding()).store.isChecked()));
        dismiss();
    }

    public final boolean store() {
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        return sharedPreferences.getBoolean(SettingsKt.LOGIN_STORE, true);
    }

    public void onFocusChange(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (z) {
            UiTools.INSTANCE.setKeyboardVisibility(view, view instanceof EditText);
        }
    }
}
