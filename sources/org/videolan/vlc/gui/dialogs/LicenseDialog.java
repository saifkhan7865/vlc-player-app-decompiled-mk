package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.databinding.DialogLicenseBinding;
import org.videolan.vlc.gui.LibraryWithLicense;
import org.videolan.vlc.util.UrlUtilsKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J$\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/LicenseDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogLicenseBinding;", "licenseItem", "Lorg/videolan/vlc/gui/LibraryWithLicense;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LicenseDialog.kt */
public final class LicenseDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private DialogLicenseBinding binding;
    private LibraryWithLicense licenseItem;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/LicenseDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/LicenseDialog;", "libraryWithLicense", "Lorg/videolan/vlc/gui/LibraryWithLicense;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: LicenseDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LicenseDialog newInstance(LibraryWithLicense libraryWithLicense) {
            Intrinsics.checkNotNullParameter(libraryWithLicense, "libraryWithLicense");
            LicenseDialog licenseDialog = new LicenseDialog();
            licenseDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(LicenseDialogKt.LICENSE_ITEM, libraryWithLicense)));
            return licenseDialog;
        }
    }

    public View initialFocusedView() {
        DialogLicenseBinding dialogLicenseBinding = this.binding;
        if (dialogLicenseBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogLicenseBinding = null;
        }
        TextView textView = dialogLicenseBinding.title;
        Intrinsics.checkNotNullExpressionValue(textView, "title");
        return textView;
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(arguments, LicenseDialogKt.LICENSE_ITEM, LibraryWithLicense.class);
            } else {
                Parcelable parcelable2 = arguments.getParcelable(LicenseDialogKt.LICENSE_ITEM);
                if (!(parcelable2 instanceof LibraryWithLicense)) {
                    parcelable2 = null;
                }
                parcelable = (LibraryWithLicense) parcelable2;
            }
            LibraryWithLicense libraryWithLicense = (LibraryWithLicense) parcelable;
            if (libraryWithLicense != null) {
                this.licenseItem = libraryWithLicense;
                super.onCreate(bundle);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogLicenseBinding inflate = DialogLicenseBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogLicenseBinding dialogLicenseBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        LibraryWithLicense libraryWithLicense = this.licenseItem;
        if (libraryWithLicense == null) {
            Intrinsics.throwUninitializedPropertyAccessException("licenseItem");
            libraryWithLicense = null;
        }
        inflate.setLibrary(libraryWithLicense);
        DialogLicenseBinding dialogLicenseBinding2 = this.binding;
        if (dialogLicenseBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogLicenseBinding2 = null;
        }
        dialogLicenseBinding2.licenseButton.setOnClickListener(new LicenseDialog$$ExternalSyntheticLambda0(this));
        DialogLicenseBinding dialogLicenseBinding3 = this.binding;
        if (dialogLicenseBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogLicenseBinding = dialogLicenseBinding3;
        }
        View root = dialogLicenseBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(LicenseDialog licenseDialog, View view) {
        Intrinsics.checkNotNullParameter(licenseDialog, "this$0");
        LibraryWithLicense libraryWithLicense = licenseDialog.licenseItem;
        if (libraryWithLicense == null) {
            Intrinsics.throwUninitializedPropertyAccessException("licenseItem");
            libraryWithLicense = null;
        }
        if (libraryWithLicense.getLicenseLink().length() > 0) {
            FragmentActivity requireActivity = licenseDialog.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            Context context = requireActivity;
            LibraryWithLicense libraryWithLicense2 = licenseDialog.licenseItem;
            if (libraryWithLicense2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("licenseItem");
                libraryWithLicense2 = null;
            }
            UrlUtilsKt.openLinkIfPossible$default(context, libraryWithLicense2.getLicenseLink(), 0, 2, (Object) null);
        }
    }
}
