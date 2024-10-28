package org.videolan.vlc.gui.dialogs;

import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.security.MessageDigest;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;
import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogAboutVersionBinding;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J&\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AboutVersionDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogAboutVersionBinding;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AboutVersionDialog.kt */
public final class AboutVersionDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private DialogAboutVersionBinding binding;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AboutVersionDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/AboutVersionDialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AboutVersionDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AboutVersionDialog newInstance() {
            return new AboutVersionDialog();
        }
    }

    public View initialFocusedView() {
        DialogAboutVersionBinding dialogAboutVersionBinding = this.binding;
        if (dialogAboutVersionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding = null;
        }
        TextView textView = dialogAboutVersionBinding.medias2;
        Intrinsics.checkNotNullExpressionValue(textView, "medias2");
        return textView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogAboutVersionBinding inflate = DialogAboutVersionBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Signature[] signatureArr;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        DialogAboutVersionBinding dialogAboutVersionBinding = this.binding;
        DialogAboutVersionBinding dialogAboutVersionBinding2 = null;
        if (dialogAboutVersionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding = null;
        }
        dialogAboutVersionBinding.version.setText(BuildConfig.VLC_VERSION_NAME);
        DialogAboutVersionBinding dialogAboutVersionBinding3 = this.binding;
        if (dialogAboutVersionBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding3 = null;
        }
        dialogAboutVersionBinding3.medias2.setText(getString(R.string.build_time));
        DialogAboutVersionBinding dialogAboutVersionBinding4 = this.binding;
        if (dialogAboutVersionBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding4 = null;
        }
        TextView textView = dialogAboutVersionBinding4.changelog;
        String string = getString(R.string.changelog);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        textView.setText(StringsKt.replace$default(string, "*", "•", false, 4, (Object) null));
        DialogAboutVersionBinding dialogAboutVersionBinding5 = this.binding;
        if (dialogAboutVersionBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding5 = null;
        }
        dialogAboutVersionBinding5.revision.setText(getString(R.string.build_revision));
        DialogAboutVersionBinding dialogAboutVersionBinding6 = this.binding;
        if (dialogAboutVersionBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding6 = null;
        }
        dialogAboutVersionBinding6.vlcRevision.setText(getString(R.string.build_vlc_revision));
        DialogAboutVersionBinding dialogAboutVersionBinding7 = this.binding;
        if (dialogAboutVersionBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding7 = null;
        }
        dialogAboutVersionBinding7.libvlcRevision.setText(getString(R.string.build_libvlc_revision));
        DialogAboutVersionBinding dialogAboutVersionBinding8 = this.binding;
        if (dialogAboutVersionBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding8 = null;
        }
        dialogAboutVersionBinding8.libvlcVersion.setText(BuildConfig.LIBVLC_VERSION);
        DialogAboutVersionBinding dialogAboutVersionBinding9 = this.binding;
        if (dialogAboutVersionBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding9 = null;
        }
        dialogAboutVersionBinding9.remoteAccessVersion.setText(getString(R.string.remote_access_version));
        DialogAboutVersionBinding dialogAboutVersionBinding10 = this.binding;
        if (dialogAboutVersionBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding10 = null;
        }
        dialogAboutVersionBinding10.remoteAccessRevision.setText(getString(R.string.build_remote_access_revision));
        DialogAboutVersionBinding dialogAboutVersionBinding11 = this.binding;
        if (dialogAboutVersionBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding11 = null;
        }
        dialogAboutVersionBinding11.compiledBy.setText(getString(R.string.build_host));
        DialogAboutVersionBinding dialogAboutVersionBinding12 = this.binding;
        if (dialogAboutVersionBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAboutVersionBinding12 = null;
        }
        dialogAboutVersionBinding12.moreButton.setOnClickListener(new AboutVersionDialog$$ExternalSyntheticLambda1(this));
        if (Build.VERSION.SDK_INT < 28) {
            signatureArr = requireActivity().getPackageManager().getPackageInfo(requireActivity().getPackageName(), 64).signatures;
        } else {
            signatureArr = requireActivity().getPackageManager().getPackageInfo(requireActivity().getPackageName(), 134217728).signingInfo.getApkContentsSigners();
        }
        String string2 = requireActivity().getString(R.string.unknown);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        Intrinsics.checkNotNull(signatureArr);
        for (Signature signature : signatureArr) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA1");
                instance.update(signature.toByteArray());
                byte[] digest = instance.digest();
                StringBuilder sb = new StringBuilder();
                int length = digest.length;
                for (int i = 0; i < length; i++) {
                    if (i != 0) {
                        sb.append(":");
                    }
                    String hexString = Integer.toHexString(digest[i] & 255);
                    if (hexString.length() == 1) {
                        sb.append(Constants.GROUP_VIDEOS_FOLDER);
                    }
                    sb.append(hexString);
                }
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                String upperCase = sb2.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                switch (upperCase.hashCode()) {
                    case 55479920:
                        if (upperCase.equals("EE:FB:C9:81:42:83:43:BB:DD:FF:F6:B2:3B:6B:D8:71:73:51:41:0C")) {
                            string2 = "VideoLAN";
                            break;
                        } else {
                            break;
                        }
                    case 318067470:
                        if (upperCase.equals("4D:D5:44:A7:51:D3:D5:4C:17:D8:7E:1D:D3:60:F0:C6:40:A5:C1:50")) {
                            string2 = "Google";
                            break;
                        } else {
                            break;
                        }
                    case 1126076319:
                        if (upperCase.equals("AC:5A:BC:F1:99:AC:86:61:6A:79:65:CB:84:59:94:89:A5:A7:3F:86")) {
                            string2 = "VideoLAN nightly";
                            break;
                        } else {
                            break;
                        }
                    case 1379641574:
                        if (upperCase.equals("40:80:86:F9:AE:A6:52:A8:61:44:70:4F:11:79:9A:CA:BA:31:C7:A0")) {
                            string2 = "F-Droid";
                            break;
                        } else {
                            break;
                        }
                }
                Log.i(getClass().getSimpleName(), "Found signature. Fingerprint: " + sb);
            } catch (Exception e) {
                Log.e("Signature", e.getMessage(), e);
            }
        }
        DialogAboutVersionBinding dialogAboutVersionBinding13 = this.binding;
        if (dialogAboutVersionBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogAboutVersionBinding2 = dialogAboutVersionBinding13;
        }
        dialogAboutVersionBinding2.signedBy.setText(string2);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(AboutVersionDialog aboutVersionDialog, View view) {
        Intrinsics.checkNotNullParameter(aboutVersionDialog, "this$0");
        new WhatsNewDialog().show(aboutVersionDialog.requireActivity().getSupportFragmentManager(), "fragment_whats_new");
        aboutVersionDialog.dismiss();
    }
}
