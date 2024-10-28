package org.videolan.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0004J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001e\u001a\u00020\u0019J\b\u0010\u001f\u001a\u00020\bH\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/tools/NetworkMonitor;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cm", "Landroid/net/ConnectivityManager;", "connected", "", "getConnected", "()Z", "connectionFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/videolan/tools/Connection;", "getConnectionFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "isLan", "lanAllowed", "getLanAllowed", "receiver", "Landroid/content/BroadcastReceiver;", "getReceiver", "()Landroid/content/BroadcastReceiver;", "registered", "finalize", "", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "stop", "updateVPNStatus", "Companion", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkMonitor.kt */
public final class NetworkMonitor implements DefaultLifecycleObserver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final ConnectivityManager cm;
    private final MutableStateFlow<Connection> connectionFlow = StateFlowKt.MutableStateFlow(new Connection(false, true, false));
    private final Context context;
    private final BroadcastReceiver receiver = new NetworkMonitor$receiver$1(this);
    private boolean registered;

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public NetworkMonitor(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        Object systemService = ContextCompat.getSystemService(context2, ConnectivityManager.class);
        Intrinsics.checkNotNull(systemService);
        this.cm = (ConnectivityManager) systemService;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public final MutableStateFlow<Connection> getConnectionFlow() {
        return this.connectionFlow;
    }

    public final boolean getConnected() {
        return this.connectionFlow.getValue().getConnected();
    }

    public final boolean isLan() {
        Connection value = this.connectionFlow.getValue();
        return value.getConnected() && !value.getMobile();
    }

    public final boolean getLanAllowed() {
        Connection value = this.connectionFlow.getValue();
        return value.getConnected() && (!value.getMobile() || value.getVpn());
    }

    public final BroadcastReceiver getReceiver() {
        return this.receiver;
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        if (!this.registered) {
            this.registered = true;
            this.context.registerReceiver(this.receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        stop();
    }

    public final void stop() {
        if (this.registered) {
            this.registered = false;
            this.context.unregisterReceiver(this.receiver);
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        stop();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038 A[Catch:{ SocketException -> 0x0060 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean updateVPNStatus() {
        /*
            r7 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            r2 = 1
            r3 = 0
            if (r0 < r1) goto L_0x002e
            android.net.ConnectivityManager r0 = r7.cm
            android.net.Network[] r0 = r0.getAllNetworks()
            java.lang.String r1 = "getAllNetworks(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.length
            r4 = 0
        L_0x0015:
            if (r4 >= r1) goto L_0x002d
            r5 = r0[r4]
            android.net.ConnectivityManager r6 = r7.cm
            android.net.NetworkCapabilities r5 = r6.getNetworkCapabilities(r5)
            if (r5 != 0) goto L_0x0022
            return r3
        L_0x0022:
            r6 = 4
            boolean r5 = r5.hasTransport(r6)
            if (r5 == 0) goto L_0x002a
            return r2
        L_0x002a:
            int r4 = r4 + 1
            goto L_0x0015
        L_0x002d:
            return r3
        L_0x002e:
            java.util.Enumeration r0 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ SocketException -> 0x0060 }
        L_0x0032:
            boolean r1 = r0.hasMoreElements()     // Catch:{ SocketException -> 0x0060 }
            if (r1 == 0) goto L_0x0060
            java.lang.Object r1 = r0.nextElement()     // Catch:{ SocketException -> 0x0060 }
            java.net.NetworkInterface r1 = (java.net.NetworkInterface) r1     // Catch:{ SocketException -> 0x0060 }
            java.lang.String r1 = r1.getDisplayName()     // Catch:{ SocketException -> 0x0060 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ SocketException -> 0x0060 }
            java.lang.String r4 = "ppp"
            r5 = 0
            r6 = 2
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r1, r4, r3, r6, r5)     // Catch:{ SocketException -> 0x0060 }
            if (r4 != 0) goto L_0x005f
            java.lang.String r4 = "tun"
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r1, r4, r3, r6, r5)     // Catch:{ SocketException -> 0x0060 }
            if (r4 != 0) goto L_0x005f
            java.lang.String r4 = "tap"
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r1, r4, r3, r6, r5)     // Catch:{ SocketException -> 0x0060 }
            if (r1 == 0) goto L_0x0032
        L_0x005f:
            return r2
        L_0x0060:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.NetworkMonitor.updateVPNStatus():boolean");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/tools/NetworkMonitor$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/tools/NetworkMonitor;", "Landroid/content/Context;", "()V", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NetworkMonitor.kt */
    public static final class Companion extends SingletonHolder<NetworkMonitor, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
