package androidx.car.app;

import android.location.Location;
import android.os.Bundle;
import androidx.core.location.LocationListenerCompat;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$$ExternalSyntheticLambda5 implements LocationListenerCompat {
    public final /* synthetic */ AppManager f$0;

    public /* synthetic */ AppManager$$ExternalSyntheticLambda5(AppManager appManager) {
        this.f$0 = appManager;
    }

    public /* synthetic */ void onFlushComplete(int i) {
        LocationListenerCompat.CC.$default$onFlushComplete(this, i);
    }

    public final void onLocationChanged(Location location) {
        this.f$0.m7lambda$new$7$androidxcarappAppManager(location);
    }

    public /* synthetic */ void onLocationChanged(List list) {
        LocationListenerCompat.CC.$default$onLocationChanged(this, list);
    }

    public /* synthetic */ void onProviderDisabled(String str) {
        LocationListenerCompat.CC.$default$onProviderDisabled(this, str);
    }

    public /* synthetic */ void onProviderEnabled(String str) {
        LocationListenerCompat.CC.$default$onProviderEnabled(this, str);
    }

    public /* synthetic */ void onStatusChanged(String str, int i, Bundle bundle) {
        LocationListenerCompat.CC.$default$onStatusChanged(this, str, i, bundle);
    }
}
