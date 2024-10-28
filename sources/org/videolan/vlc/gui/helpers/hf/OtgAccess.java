package org.videolan.vlc.gui.helpers.hf;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/OtgAccess;", "Lorg/videolan/vlc/gui/helpers/hf/BaseHeadlessFragment;", "()V", "onActivityResult", "", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OtgAccess.kt */
public final class OtgAccess extends BaseHeadlessFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MutableStateFlow<Uri> otgRoot = StateFlowKt.MutableStateFlow(null);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            new AlertDialog.Builder(requireActivity()).setTitle((CharSequence) getResources().getString(R.string.allow_otg)).setMessage((CharSequence) getResources().getString(R.string.allow_otg_description)).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new OtgAccess$$ExternalSyntheticLambda0(this)).setOnCancelListener(new OtgAccess$$ExternalSyntheticLambda1(this)).show();
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(OtgAccess otgAccess, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(otgAccess, "this$0");
        try {
            otgAccess.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 85);
        } catch (ActivityNotFoundException unused) {
            otgAccess.exit();
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(OtgAccess otgAccess, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(otgAccess, "this$0");
        otgAccess.exit();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent == null || i != 85) {
            super.onActivityResult(i, i2, intent);
        } else {
            otgRoot.setValue(intent.getData());
        }
        exit();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/OtgAccess$Companion;", "", "()V", "otgRoot", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroid/net/Uri;", "getOtgRoot", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OtgAccess.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableStateFlow<Uri> getOtgRoot() {
            return OtgAccess.otgRoot;
        }
    }
}
