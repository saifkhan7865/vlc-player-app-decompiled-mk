package org.videolan.vlc;

import android.os.RemoteException;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.DebugLogService;
import org.videolan.vlc.IDebugLogServiceCallback;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016¨\u0006\u000e"}, d2 = {"org/videolan/vlc/DebugLogService$Client$mICallback$1", "Lorg/videolan/vlc/IDebugLogServiceCallback$Stub;", "onLog", "", "msg", "", "onSaved", "success", "", "path", "onStarted", "logList", "", "onStopped", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DebugLogService.kt */
public final class DebugLogService$Client$mICallback$1 extends IDebugLogServiceCallback.Stub {
    final /* synthetic */ DebugLogService.Client this$0;

    DebugLogService$Client$mICallback$1(DebugLogService.Client client) {
        this.this$0 = client;
    }

    /* access modifiers changed from: private */
    public static final void onStopped$lambda$0(DebugLogService.Client client) {
        Intrinsics.checkNotNullParameter(client, "this$0");
        client.mCallback.onStopped();
    }

    public void onStopped() throws RemoteException {
        this.this$0.mHandler.post(new DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda3(this.this$0));
    }

    /* access modifiers changed from: private */
    public static final void onStarted$lambda$1(DebugLogService.Client client, List list) {
        Intrinsics.checkNotNullParameter(client, "this$0");
        Intrinsics.checkNotNullParameter(list, "$logList");
        client.mCallback.onStarted(list);
    }

    public void onStarted(List<String> list) throws RemoteException {
        Intrinsics.checkNotNullParameter(list, "logList");
        this.this$0.mHandler.post(new DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda1(this.this$0, list));
    }

    /* access modifiers changed from: private */
    public static final void onLog$lambda$2(DebugLogService.Client client, String str) {
        Intrinsics.checkNotNullParameter(client, "this$0");
        Intrinsics.checkNotNullParameter(str, "$msg");
        client.mCallback.onLog(str);
    }

    public void onLog(String str) throws RemoteException {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        this.this$0.mHandler.post(new DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda0(this.this$0, str));
    }

    /* access modifiers changed from: private */
    public static final void onSaved$lambda$3(DebugLogService.Client client, boolean z, String str) {
        Intrinsics.checkNotNullParameter(client, "this$0");
        Intrinsics.checkNotNullParameter(str, "$path");
        client.mCallback.onSaved(z, str);
    }

    public void onSaved(boolean z, String str) throws RemoteException {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        this.this$0.mHandler.post(new DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda2(this.this$0, z, str));
    }
}
