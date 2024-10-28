package androidx.car.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.car.app.IOnRequestPermissionsListener;
import androidx.car.app.utils.LogTags;
import java.util.ArrayList;
import java.util.Map;

public class CarAppPermissionActivity extends ComponentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        maybeSetCustomBackground();
        processInternal(getIntent());
    }

    private void maybeSetCustomBackground() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            int i = applicationInfo.metaData != null ? applicationInfo.metaData.getInt("androidx.car.app.theme") : 0;
            Context createConfigurationContext = createConfigurationContext(getResources().getConfiguration());
            if (i != 0) {
                createConfigurationContext.setTheme(i);
            }
            int identifier = createConfigurationContext.getResources().getIdentifier("carPermissionActivityLayout", "attr", getPackageName());
            if (identifier != 0) {
                int resourceId = createConfigurationContext.getTheme().obtainStyledAttributes(new int[]{identifier}).getResourceId(0, 0);
                if (resourceId != 0) {
                    setContentView(resourceId);
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private void processInternal(Intent intent) {
        if (intent == null || !"androidx.car.app.action.REQUEST_PERMISSIONS".equals(intent.getAction())) {
            StringBuilder sb = new StringBuilder("Unexpected intent action for CarAppPermissionActivity: ");
            sb.append(intent == null ? "null Intent" : intent.getAction());
            Log.e(LogTags.TAG, sb.toString());
            finish();
            return;
        }
        requestPermissions(intent);
    }

    private void requestPermissions(Intent intent) {
        Bundle extras = intent.getExtras();
        IOnRequestPermissionsListener asInterface = IOnRequestPermissionsListener.Stub.asInterface(extras.getBinder("androidx.car.app.action.EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY"));
        String[] stringArray = extras.getStringArray("androidx.car.app.action.EXTRA_PERMISSIONS_KEY");
        if (asInterface == null || stringArray == null) {
            Log.e(LogTags.TAG, "Intent to request permissions is missing the callback binder");
            finish();
            return;
        }
        registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new CarAppPermissionActivity$$ExternalSyntheticLambda1(this, asInterface)).launch(stringArray);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$requestPermissions$0$androidx-car-app-CarAppPermissionActivity  reason: not valid java name */
    public /* synthetic */ void m17lambda$requestPermissions$0$androidxcarappCarAppPermissionActivity(IOnRequestPermissionsListener iOnRequestPermissionsListener, Map map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            Boolean bool = (Boolean) entry.getValue();
            if (bool == null || !bool.booleanValue()) {
                arrayList2.add((String) entry.getKey());
            } else {
                arrayList.add((String) entry.getKey());
            }
        }
        try {
            iOnRequestPermissionsListener.onRequestPermissionsResult((String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]));
        } catch (RemoteException e) {
            Log.e(LogTags.TAG, "CarAppService dead when accepting/rejecting permissions", e);
        }
        finish();
    }
}
