package org.videolan.television.util;

import android.app.Activity;
import android.content.Intent;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.util.NoConnectivityException;
import org.videolan.television.ui.dialogs.ConfirmationTvActivity;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¨\u0006\u0006"}, d2 = {"manageHttpException", "", "Landroid/app/Activity;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Helpers.kt */
public final class HelpersKt {
    public static final void manageHttpException(Activity activity, Exception exc) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(exc, "e");
        if (!(exc instanceof NoConnectivityException)) {
            return;
        }
        if (Settings.INSTANCE.getShowTvUi()) {
            Intent intent = new Intent(activity, ConfirmationTvActivity.class);
            intent.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TITLE, activity.getString(R.string.no_internet_connection));
            intent.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TEXT, activity.getString(R.string.open_network_settings));
            activity.startActivityForResult(intent, 100);
            return;
        }
        Snackbar.make(activity.findViewById(16908290), R.string.no_internet_connection, 0).show();
    }
}
