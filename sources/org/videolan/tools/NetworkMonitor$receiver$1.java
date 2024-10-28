package org.videolan.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0017¨\u0006\b"}, d2 = {"org/videolan/tools/NetworkMonitor$receiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkMonitor.kt */
public final class NetworkMonitor$receiver$1 extends BroadcastReceiver {
    final /* synthetic */ NetworkMonitor this$0;

    NetworkMonitor$receiver$1(NetworkMonitor networkMonitor) {
        this.this$0 = networkMonitor;
    }

    public void onReceive(Context context, Intent intent) {
        boolean z;
        String action = intent != null ? intent.getAction() : null;
        if (action != null && action.hashCode() == -1172645946 && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            NetworkInfo activeNetworkInfo = this.this$0.cm.getActiveNetworkInfo();
            boolean z2 = true;
            boolean z3 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            if (z3) {
                Intrinsics.checkNotNull(activeNetworkInfo);
                if (activeNetworkInfo.getType() == 0) {
                    z = true;
                    if (!z3 || !this.this$0.updateVPNStatus()) {
                        z2 = false;
                    }
                    this.this$0.getConnectionFlow().setValue(new Connection(z3, z, z2));
                }
            }
            z = false;
            z2 = false;
            this.this$0.getConnectionFlow().setValue(new Connection(z3, z, z2));
        }
    }
}
