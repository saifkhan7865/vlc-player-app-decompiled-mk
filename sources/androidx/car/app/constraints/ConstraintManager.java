package androidx.car.app.constraints;

import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.R;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.managers.Manager;
import androidx.car.app.utils.LogTags;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresCarApi(2)
public class ConstraintManager implements Manager {
    public static final int CONTENT_LIMIT_TYPE_GRID = 1;
    public static final int CONTENT_LIMIT_TYPE_LIST = 0;
    public static final int CONTENT_LIMIT_TYPE_PANE = 4;
    public static final int CONTENT_LIMIT_TYPE_PLACE_LIST = 2;
    public static final int CONTENT_LIMIT_TYPE_ROUTE_LIST = 3;
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentLimitType {
    }

    public int getContentLimit(int i) {
        Integer num;
        try {
            num = (Integer) this.mHostDispatcher.dispatchForResult("constraints", "getContentLimit", new ConstraintManager$$ExternalSyntheticLambda1(i));
        } catch (RemoteException e) {
            Log.w(LogTags.TAG, "Failed to retrieve list limit from the host, using defaults", e);
            num = null;
        }
        if (num != null) {
            return num.intValue();
        }
        return this.mCarContext.getResources().getInteger(getResourceIdForContentType(i));
    }

    @RequiresCarApi(6)
    public boolean isAppDrivenRefreshEnabled() {
        try {
            return Boolean.TRUE.equals((Boolean) this.mHostDispatcher.dispatchForResult("constraints", "isAppDrivenRefreshEnabled", new ConstraintManager$$ExternalSyntheticLambda0()));
        } catch (RemoteException e) {
            Log.w(LogTags.TAG, "Failed to retrieve if the host supports appDriven Refresh, using defaults", e);
            return false;
        }
    }

    private int getResourceIdForContentType(int i) {
        if (i == 1) {
            return R.integer.content_limit_grid;
        }
        if (i == 2) {
            return R.integer.content_limit_place_list;
        }
        if (i == 3) {
            return R.integer.content_limit_route_list;
        }
        if (i != 4) {
            return R.integer.content_limit_list;
        }
        return R.integer.content_limit_pane;
    }

    public static ConstraintManager create(CarContext carContext, HostDispatcher hostDispatcher) {
        return new ConstraintManager((CarContext) Objects.requireNonNull(carContext), (HostDispatcher) Objects.requireNonNull(hostDispatcher));
    }

    private ConstraintManager(CarContext carContext, HostDispatcher hostDispatcher) {
        this.mCarContext = carContext;
        this.mHostDispatcher = hostDispatcher;
    }
}
