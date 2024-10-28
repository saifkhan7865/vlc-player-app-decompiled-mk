package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/gui/dialogs/DeviceDialog$clickHandler$1", "Lorg/videolan/vlc/gui/dialogs/ExtDeviceHandler;", "browse", "", "v", "Landroid/view/View;", "cancel", "scan", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DeviceDialog.kt */
public final class DeviceDialog$clickHandler$1 implements ExtDeviceHandler {
    final /* synthetic */ DeviceDialog this$0;

    DeviceDialog$clickHandler$1(DeviceDialog deviceDialog) {
        this.this$0 = deviceDialog;
    }

    public void browse(View view) {
        Context applicationContext;
        Intrinsics.checkNotNullParameter(view, "v");
        Context context = this.this$0.getContext();
        if (!(context == null || (applicationContext = context.getApplicationContext()) == null)) {
            DeviceDialog deviceDialog = this.this$0;
            Intent intent = new Intent(applicationContext, StartActivity.class);
            String access$getPath$p = deviceDialog.path;
            if (access$getPath$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ArtworkProvider.PATH);
                access$getPath$p = null;
            }
            applicationContext.startActivity(intent.putExtra("extra_path", access$getPath$p).addFlags(268435456));
        }
        this.this$0.dismiss();
    }

    public void scan(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        Context context = this.this$0.getContext();
        if (context != null) {
            DeviceDialog deviceDialog = this.this$0;
            MedialibraryUtils medialibraryUtils = MedialibraryUtils.INSTANCE;
            String access$getPath$p = deviceDialog.path;
            if (access$getPath$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ArtworkProvider.PATH);
                access$getPath$p = null;
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            medialibraryUtils.addDevice(access$getPath$p, applicationContext);
            context.startActivity(new Intent(context, StartActivity.class).addFlags(268435456));
        }
        this.this$0.dismiss();
    }

    public void cancel(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        this.this$0.dismiss();
    }
}
