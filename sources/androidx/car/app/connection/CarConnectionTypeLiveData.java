package androidx.car.app.connection;

import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.car.app.utils.LogTags;
import androidx.lifecycle.LiveData;

final class CarConnectionTypeLiveData extends LiveData<Integer> {
    static final String CAR_CONNECTION_AUTHORITY = "androidx.car.app.connection";
    private static final Uri PROJECTION_HOST_URI = new Uri.Builder().scheme("content").authority(CAR_CONNECTION_AUTHORITY).build();
    private static final int QUERY_TOKEN = 42;
    private final CarConnectionBroadcastReceiver mBroadcastReceiver = new CarConnectionBroadcastReceiver();
    private final Context mContext;
    private final AsyncQueryHandler mQueryHandler;

    CarConnectionTypeLiveData(Context context) {
        this.mContext = context;
        this.mQueryHandler = new CarConnectionQueryHandler(context.getContentResolver());
    }

    public void onActive() {
        IntentFilter intentFilter = new IntentFilter(CarConnection.ACTION_CAR_CONNECTION_UPDATED);
        if (Build.VERSION.SDK_INT >= 33) {
            Api33Impl.registerExportedReceiver(this.mContext, this.mBroadcastReceiver, intentFilter);
        } else {
            this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
        }
        queryForState();
    }

    public void onInactive() {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mQueryHandler.cancelOperation(42);
    }

    /* access modifiers changed from: package-private */
    public void queryForState() {
        this.mQueryHandler.startQuery(42, (Object) null, PROJECTION_HOST_URI, new String[]{CarConnection.CAR_CONNECTION_STATE}, (String) null, (String[]) null, (String) null);
    }

    class CarConnectionQueryHandler extends AsyncQueryHandler {
        CarConnectionQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(int i, Object obj, Cursor cursor) {
            if (cursor == null) {
                Log.w(LogTags.TAG_CONNECTION_TO_CAR, "Null response from content provider when checking connection to the car, treating as disconnected");
                CarConnectionTypeLiveData.this.postValue(0);
                return;
            }
            int columnIndex = cursor.getColumnIndex(CarConnection.CAR_CONNECTION_STATE);
            if (columnIndex < 0) {
                Log.e(LogTags.TAG_CONNECTION_TO_CAR, "Connection to car response is missing the connection type, treating as disconnected");
                CarConnectionTypeLiveData.this.postValue(0);
            } else if (!cursor.moveToNext()) {
                Log.e(LogTags.TAG_CONNECTION_TO_CAR, "Connection to car response is empty, treating as disconnected");
                CarConnectionTypeLiveData.this.postValue(0);
            } else {
                CarConnectionTypeLiveData.this.postValue(Integer.valueOf(cursor.getInt(columnIndex)));
            }
        }
    }

    class CarConnectionBroadcastReceiver extends BroadcastReceiver {
        CarConnectionBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            CarConnectionTypeLiveData.this.queryForState();
        }
    }

    static class Api33Impl {
        private Api33Impl() {
        }

        static void registerExportedReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            context.registerReceiver(broadcastReceiver, intentFilter, 2);
        }
    }
}
