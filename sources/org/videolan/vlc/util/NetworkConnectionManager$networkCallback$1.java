package org.videolan.vlc.util;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"org/videolan/vlc/util/NetworkConnectionManager$networkCallback$1", "Landroid/net/ConnectivityManager$NetworkCallback;", "onAvailable", "", "network", "Landroid/net/Network;", "onCapabilitiesChanged", "networkCapabilities", "Landroid/net/NetworkCapabilities;", "onLost", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkConnectionManager.kt */
public final class NetworkConnectionManager$networkCallback$1 extends ConnectivityManager.NetworkCallback {
    NetworkConnectionManager$networkCallback$1() {
    }

    public void onAvailable(Network network) {
        Intrinsics.checkNotNullParameter(network, Constants.ID_NETWORK);
        super.onAvailable(network);
        if (Intrinsics.areEqual((Object) NetworkConnectionManager.INSTANCE.getHasConnection().getValue(), (Object) false)) {
            NetworkConnectionManager.INSTANCE.getHasConnection().postValue(true);
        }
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Intrinsics.checkNotNullParameter(network, Constants.ID_NETWORK);
        Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
        super.onCapabilitiesChanged(network, networkCapabilities);
        boolean z = !networkCapabilities.hasCapability(11);
        if (!Intrinsics.areEqual((Object) Boolean.valueOf(z), (Object) NetworkConnectionManager.INSTANCE.isMetered().getValue())) {
            NetworkConnectionManager.INSTANCE.isMetered().postValue(Boolean.valueOf(z));
        }
    }

    public void onLost(Network network) {
        Intrinsics.checkNotNullParameter(network, Constants.ID_NETWORK);
        super.onLost(network);
        if (Intrinsics.areEqual((Object) NetworkConnectionManager.INSTANCE.getHasConnection().getValue(), (Object) true)) {
            NetworkConnectionManager.INSTANCE.getHasConnection().postValue(false);
        }
    }
}
