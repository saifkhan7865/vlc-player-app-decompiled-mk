package org.videolan.vlc.gui.dialogs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AllAccessPermissionDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "grantAllAccessButton", "Landroid/widget/Button;", "neverAskAgain", "Landroid/widget/CheckBox;", "titleView", "Landroid/widget/TextView;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AllAccessPermissionDialog.kt */
public final class AllAccessPermissionDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Button grantAllAccessButton;
    private CheckBox neverAskAgain;
    private TextView titleView;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AllAccessPermissionDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/AllAccessPermissionDialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AllAccessPermissionDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AllAccessPermissionDialog newInstance() {
            return new AllAccessPermissionDialog();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_all_access, viewGroup);
        View findViewById = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        ((TextView) inflate.findViewById(R.id.description)).setText(getString(R.string.partial_content_description, getString(R.string.allow_storage_manager_explanation)));
        View findViewById2 = inflate.findViewById(R.id.grant_all_access_button);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.grantAllAccessButton = (Button) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.never_ask_again);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.neverAskAgain = (CheckBox) findViewById3;
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SharedPreferences sharedPreferences = (SharedPreferences) settings.getInstance(requireActivity);
        Button button = this.grantAllAccessButton;
        CheckBox checkBox = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("grantAllAccessButton");
            button = null;
        }
        button.setOnClickListener(new AllAccessPermissionDialog$$ExternalSyntheticLambda0(this));
        CheckBox checkBox2 = this.neverAskAgain;
        if (checkBox2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("neverAskAgain");
        } else {
            checkBox = checkBox2;
        }
        checkBox.setOnCheckedChangeListener(new AllAccessPermissionDialog$$ExternalSyntheticLambda1(sharedPreferences));
        return inflate;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(AllAccessPermissionDialog allAccessPermissionDialog, View view) {
        Intrinsics.checkNotNullParameter(allAccessPermissionDialog, "this$0");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(allAccessPermissionDialog), (CoroutineContext) null, (CoroutineStart) null, new AllAccessPermissionDialog$onCreateView$1$1(allAccessPermissionDialog, (Continuation<? super AllAccessPermissionDialog$onCreateView$1$1>) null), 3, (Object) null);
        allAccessPermissionDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(SharedPreferences sharedPreferences, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "$settings");
        SettingsKt.putSingle(sharedPreferences, SettingsKt.PERMISSION_NEVER_ASK, Boolean.valueOf(z));
    }

    public View initialFocusedView() {
        TextView textView = this.titleView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleView");
            textView = null;
        }
        return textView;
    }
}
