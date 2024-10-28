package org.videolan.vlc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Platform$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0010\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/util/NetworkConnectionManager;", "", "()V", "hasConnection", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getHasConnection", "()Landroidx/lifecycle/MutableLiveData;", "isMetered", "networkCallback", "org/videolan/vlc/util/NetworkConnectionManager$networkCallback$1", "Lorg/videolan/vlc/util/NetworkConnectionManager$networkCallback$1;", "start", "", "context", "Landroid/content/Context;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkConnectionManager.kt */
public final class NetworkConnectionManager {
    public static final NetworkConnectionManager INSTANCE = new NetworkConnectionManager();
    private static final MutableLiveData<Boolean> hasConnection = new MutableLiveData<>(false);
    private static final MutableLiveData<Boolean> isMetered = new MutableLiveData<>(true);
    private static final NetworkConnectionManager$networkCallback$1 networkCallback = new NetworkConnectionManager$networkCallback$1();

    private NetworkConnectionManager() {
    }

    public final MutableLiveData<Boolean> isMetered() {
        return isMetered;
    }

    public final MutableLiveData<Boolean> getHasConnection() {
        return hasConnection;
    }

    public final void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NetworkRequest m = new NetworkRequest.Builder().addCapability(12).build();
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ((ConnectivityManager) systemService).requestNetwork(m, Platform$$ExternalSyntheticApiModelOutline0.m((Object) networkCallback));
    }
}
