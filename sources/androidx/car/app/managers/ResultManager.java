package androidx.car.app.managers;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import androidx.car.app.CarAppMetadataHolderService;
import androidx.car.app.CarContext;

public interface ResultManager extends Manager {
    ComponentName getCallingComponent();

    void setCarAppResult(int i, Intent intent);

    /* renamed from: androidx.car.app.managers.ResultManager$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static ResultManager create(CarContext carContext) throws IllegalStateException {
            try {
                ServiceInfo serviceInfo = CarAppMetadataHolderService.getServiceInfo(carContext);
                String string = serviceInfo.metaData != null ? serviceInfo.metaData.getString("androidx.car.app.CarAppMetadataHolderService.RESULT_MANAGER") : null;
                if (string != null) {
                    return (ResultManager) Class.forName(string).getConstructor((Class[]) null).newInstance((Object[]) null);
                }
                throw new ClassNotFoundException("ResultManager metadata could not be found");
            } catch (PackageManager.NameNotFoundException | ReflectiveOperationException unused) {
                throw new IllegalStateException("ResultManager not configured. Did you forget to add a dependency on the app-automotive artifact?");
            }
        }
    }
}
