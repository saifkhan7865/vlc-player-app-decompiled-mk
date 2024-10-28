package org.videolan.television.ui.dialogs;

import android.os.Bundle;
import androidx.leanback.app.GuidedStepSupportFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.television.ui.dialogs.ConfirmationTvDialog;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0014¨\u0006\t"}, d2 = {"Lorg/videolan/television/ui/dialogs/ConfirmationTvActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "refresh", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConfirmationTvActivity.kt */
public final class ConfirmationTvActivity extends BaseTvActivity {
    public static final int ACTION_ID_NEGATIVE = 2;
    public static final int ACTION_ID_POSITIVE = 1;
    public static final String CONFIRMATION_DIALOG_TEXT = "confirmation_dialog_text";
    public static final String CONFIRMATION_DIALOG_TITLE = "confirmation_dialog_title";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* access modifiers changed from: protected */
    public void refresh() {
    }

    public void onCreate(Bundle bundle) {
        String stringExtra;
        String stringExtra2;
        super.onCreate(bundle);
        if (bundle == null && (stringExtra = getIntent().getStringExtra(CONFIRMATION_DIALOG_TITLE)) != null && (stringExtra2 = getIntent().getStringExtra(CONFIRMATION_DIALOG_TEXT)) != null) {
            ConfirmationTvDialog.Companion companion = ConfirmationTvDialog.Companion;
            Intrinsics.checkNotNull(stringExtra2);
            GuidedStepSupportFragment.addAsRoot(this, companion.newInstance(stringExtra, stringExtra2), 16908290);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/television/ui/dialogs/ConfirmationTvActivity$Companion;", "", "()V", "ACTION_ID_NEGATIVE", "", "ACTION_ID_POSITIVE", "CONFIRMATION_DIALOG_TEXT", "", "CONFIRMATION_DIALOG_TITLE", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ConfirmationTvActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
