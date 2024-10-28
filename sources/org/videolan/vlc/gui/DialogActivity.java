package org.videolan.vlc.gui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.DeviceDialog;
import org.videolan.vlc.gui.dialogs.NetworkServerDialog;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.DialogDelegatesKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0006\u0010\u0003\u001a\u00020\u0006J\b\u0010\r\u001a\u00020\u0006H\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0002J\b\u0010\u000f\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/gui/DialogActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "preventFinish", "", "finish", "", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupDeviceDialog", "setupServerDialog", "setupSubsDialog", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DialogActivity.kt */
public final class DialogActivity extends BaseActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRA_MEDIA = "extra_media";
    public static final String EXTRA_MEDIALIST = "extra_media_list";
    public static final String EXTRA_PATH = "extra_path";
    public static final String EXTRA_SCAN = "extra_scan";
    public static final String EXTRA_UUID = "extra_uuid";
    public static final String KEY_DEVICE = "deviceDialog";
    public static final String KEY_DIALOG = "vlcDialog";
    public static final String KEY_SERVER = "serverDialog";
    public static final String KEY_SUBS_DL = "subsdlDialog";
    /* access modifiers changed from: private */
    public static Dialog dialog;
    /* access modifiers changed from: private */
    public static MutableLiveData<Boolean> loginDialogShown = new MutableLiveData<>(false);
    private boolean preventFinish;

    public View getSnackAnchorView(boolean z) {
        return findViewById(16908290);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.transparent);
        String action = getIntent().getAction();
        CharSequence charSequence = action;
        if (charSequence == null || charSequence.length() == 0) {
            finish();
            return;
        }
        switch (action.hashCode()) {
            case -1368166594:
                if (action.equals(KEY_DEVICE)) {
                    setupDeviceDialog();
                    return;
                }
                break;
            case -12347613:
                if (action.equals(KEY_SUBS_DL)) {
                    setupSubsDialog();
                    return;
                }
                break;
            case 428993557:
                if (action.equals(KEY_DIALOG)) {
                    Dialog dialog2 = dialog;
                    Unit unit = null;
                    if (dialog2 != null) {
                        DialogDelegatesKt.showVlcDialog((FragmentActivity) this, dialog2);
                        loginDialogShown.postValue(true);
                        dialog = null;
                        unit = Unit.INSTANCE;
                    }
                    if (unit == null) {
                        finish();
                        return;
                    }
                    return;
                }
                break;
            case 1470323307:
                if (action.equals(KEY_SERVER)) {
                    setupServerDialog();
                    return;
                }
                break;
        }
        finish();
    }

    private final void setupDeviceDialog() {
        getWindow().getDecorView().setAlpha(0.0f);
        DeviceDialog deviceDialog = new DeviceDialog();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("extra_path");
        Intrinsics.checkNotNull(stringExtra);
        String stringExtra2 = intent.getStringExtra("extra_uuid");
        Intrinsics.checkNotNull(stringExtra2);
        deviceDialog.setDevice(stringExtra, stringExtra2, intent.getBooleanExtra(EXTRA_SCAN, false));
        deviceDialog.show(getSupportFragmentManager(), "device_dialog");
    }

    private final void setupServerDialog() {
        Parcelable parcelable;
        NetworkServerDialog networkServerDialog = new NetworkServerDialog();
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, EXTRA_MEDIA, MediaWrapper.class);
        } else {
            Parcelable parcelableExtra = intent.getParcelableExtra(EXTRA_MEDIA);
            if (!(parcelableExtra instanceof MediaWrapper)) {
                parcelableExtra = null;
            }
            parcelable = (MediaWrapper) parcelableExtra;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) parcelable;
        if (mediaWrapper != null) {
            networkServerDialog.setServer(mediaWrapper);
        }
        networkServerDialog.show(getSupportFragmentManager(), "fragment_edit_network");
    }

    private final void setupSubsDialog() {
        ArrayList arrayList;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            arrayList = AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, EXTRA_MEDIALIST, MediaWrapper.class);
        } else {
            arrayList = intent.getParcelableArrayListExtra(EXTRA_MEDIALIST);
        }
        if (arrayList != null) {
            MediaUtils.INSTANCE.getSubs((FragmentActivity) this, (List<? extends MediaWrapper>) arrayList);
        } else {
            finish();
        }
    }

    public void finish() {
        loginDialogShown.postValue(false);
        if (this.preventFinish) {
            this.preventFinish = false;
        } else {
            super.finish();
        }
    }

    public final void preventFinish() {
        this.preventFinish = true;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R(\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/DialogActivity$Companion;", "", "()V", "EXTRA_MEDIA", "", "EXTRA_MEDIALIST", "EXTRA_PATH", "EXTRA_SCAN", "EXTRA_UUID", "KEY_DEVICE", "KEY_DIALOG", "KEY_SERVER", "KEY_SUBS_DL", "dialog", "Lorg/videolan/libvlc/Dialog;", "getDialog", "()Lorg/videolan/libvlc/Dialog;", "setDialog", "(Lorg/videolan/libvlc/Dialog;)V", "loginDialogShown", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getLoginDialogShown", "()Landroidx/lifecycle/MutableLiveData;", "setLoginDialogShown", "(Landroidx/lifecycle/MutableLiveData;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DialogActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Dialog getDialog() {
            return DialogActivity.dialog;
        }

        public final void setDialog(Dialog dialog) {
            DialogActivity.dialog = dialog;
        }

        public final MutableLiveData<Boolean> getLoginDialogShown() {
            return DialogActivity.loginDialogShown;
        }

        public final void setLoginDialogShown(MutableLiveData<Boolean> mutableLiveData) {
            Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
            DialogActivity.loginDialogShown = mutableLiveData;
        }
    }
}
