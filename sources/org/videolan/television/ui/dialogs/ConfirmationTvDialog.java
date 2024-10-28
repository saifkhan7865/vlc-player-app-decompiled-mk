package org.videolan.television.ui.dialogs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.leanback.app.GuidedStepSupportFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\u000f"}, d2 = {"Lorg/videolan/television/ui/dialogs/ConfirmationTvDialog;", "Landroidx/leanback/app/GuidedStepSupportFragment;", "()V", "onCreateActions", "", "actions", "", "Landroidx/leanback/widget/GuidedAction;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateGuidance", "Landroidx/leanback/widget/GuidanceStylist$Guidance;", "onGuidedActionClicked", "action", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConfirmationTvDialog.kt */
public final class ConfirmationTvDialog extends GuidedStepSupportFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        String string = arguments.getString(ConfirmationTvActivity.CONFIRMATION_DIALOG_TITLE);
        Bundle arguments2 = getArguments();
        Intrinsics.checkNotNull(arguments2);
        return new GuidanceStylist.Guidance(string, arguments2.getString(ConfirmationTvActivity.CONFIRMATION_DIALOG_TEXT), "", (Drawable) null);
    }

    public void onCreateActions(List<GuidedAction> list, Bundle bundle) {
        Intrinsics.checkNotNullParameter(list, "actions");
        GuidedAction build = ((GuidedAction.Builder) ((GuidedAction.Builder) new GuidedAction.Builder(requireActivity()).id(1)).title((CharSequence) getString(R.string.yes))).build();
        Intrinsics.checkNotNull(build);
        list.add(build);
        GuidedAction build2 = ((GuidedAction.Builder) ((GuidedAction.Builder) new GuidedAction.Builder(requireActivity()).id(2)).title((CharSequence) getString(R.string.no))).build();
        Intrinsics.checkNotNull(build2);
        list.add(build2);
    }

    public void onGuidedActionClicked(GuidedAction guidedAction) {
        Intrinsics.checkNotNull(guidedAction);
        if (1 == guidedAction.getId()) {
            requireActivity().setResult(1);
        } else {
            requireActivity().setResult(2);
        }
        requireActivity().finish();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lorg/videolan/television/ui/dialogs/ConfirmationTvDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/television/ui/dialogs/ConfirmationTvDialog;", "title", "", "text", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ConfirmationTvDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConfirmationTvDialog newInstance(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "title");
            Intrinsics.checkNotNullParameter(str2, "text");
            ConfirmationTvDialog confirmationTvDialog = new ConfirmationTvDialog();
            confirmationTvDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(ConfirmationTvActivity.CONFIRMATION_DIALOG_TITLE, str), TuplesKt.to(ConfirmationTvActivity.CONFIRMATION_DIALOG_TEXT, str2)));
            return confirmationTvDialog;
        }
    }
}
