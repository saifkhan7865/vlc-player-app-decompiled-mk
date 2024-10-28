package org.videolan.vlc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.DebugLogService;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"org/videolan/vlc/DebugLogService$Client$mServiceConnection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DebugLogService.kt */
public final class DebugLogService$Client$mServiceConnection$1 implements ServiceConnection {
    final /* synthetic */ DebugLogService.Client this$0;

    DebugLogService$Client$mServiceConnection$1(DebugLogService.Client client) {
        this.this$0 = client;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|4|5|6|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r4 = "service"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
            org.videolan.vlc.DebugLogService$Client r4 = r3.this$0
            monitor-enter(r4)
            org.videolan.vlc.IDebugLogService r5 = org.videolan.vlc.IDebugLogService.Stub.asInterface(r5)     // Catch:{ all -> 0x0045 }
            r4.mIDebugLogService = r5     // Catch:{ all -> 0x0045 }
            org.videolan.vlc.IDebugLogService r5 = r4.mIDebugLogService     // Catch:{ RemoteException -> 0x0025 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ RemoteException -> 0x0025 }
            org.videolan.vlc.DebugLogService$Client$mICallback$1 r0 = r4.mICallback     // Catch:{ RemoteException -> 0x0025 }
            org.videolan.vlc.IDebugLogServiceCallback r0 = (org.videolan.vlc.IDebugLogServiceCallback) r0     // Catch:{ RemoteException -> 0x0025 }
            r5.registerCallback(r0)     // Catch:{ RemoteException -> 0x0025 }
            goto L_0x0041
        L_0x0025:
            r4.release()     // Catch:{ all -> 0x0045 }
            android.content.Context r5 = r4.mContext     // Catch:{ all -> 0x0045 }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x0045 }
            android.content.Context r1 = r4.mContext     // Catch:{ all -> 0x0045 }
            java.lang.Class<org.videolan.vlc.DebugLogService> r2 = org.videolan.vlc.DebugLogService.class
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0045 }
            r5.stopService(r0)     // Catch:{ all -> 0x0045 }
            org.videolan.vlc.DebugLogService$Client$Callback r5 = r4.mCallback     // Catch:{ all -> 0x0045 }
            r5.onStopped()     // Catch:{ all -> 0x0045 }
        L_0x0041:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            monitor-exit(r4)
            return
        L_0x0045:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.DebugLogService$Client$mServiceConnection$1.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, ContentDisposition.Parameters.Name);
        this.this$0.release();
        this.this$0.mContext.stopService(new Intent(this.this$0.mContext, DebugLogService.class));
        this.this$0.mCallback.onStopped();
    }
}
