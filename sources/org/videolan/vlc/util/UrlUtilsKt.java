package org.videolan.vlc.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"openLinkIfPossible", "", "Landroid/content/Context;", "url", "", "size", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: UrlUtils.kt */
public final class UrlUtilsKt {
    /* access modifiers changed from: private */
    public static final void openLinkIfPossible$lambda$0(DialogInterface dialogInterface, int i) {
    }

    public static /* synthetic */ void openLinkIfPossible$default(Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 512;
        }
        openLinkIfPossible(context, str, i);
    }

    public static final void openLinkIfPossible(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(...)");
            if (queryIntentActivities.size() == 1) {
                ComponentName resolveActivity = intent.resolveActivity(context.getPackageManager());
                if (resolveActivity != null) {
                    String packageName = resolveActivity.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
                    if (!StringsKt.startsWith$default(packageName, "com.google.android.tv.frameworkpackagestubs", false, 2, (Object) null)) {
                    }
                }
                throw new IllegalStateException("No web browser found");
            }
            context.startActivity(intent);
        } catch (Exception unused) {
            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(UrlUtils.INSTANCE.generateQRCode(str, i));
            new AlertDialog.Builder(context).setTitle((CharSequence) context.getString(R.string.no_web_browser)).setMessage((CharSequence) context.getString(R.string.no_web_browser_message, new Object[]{str})).setView((View) imageView).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new UrlUtilsKt$$ExternalSyntheticLambda0()).show();
        }
    }
}
