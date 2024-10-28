package androidx.car.app.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import androidx.car.app.model.CarColor;
import androidx.car.app.serialization.Bundler;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.CollectionUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

public final class CarAppExtender implements NotificationCompat.Extender {
    private static final String EXTRA_ACTIONS = "actions";
    private static final String EXTRA_CAR_EXTENDER = "androidx.car.app.EXTENSIONS";
    private static final String EXTRA_CHANNEL_ID = "channel_id";
    private static final String EXTRA_COLOR = "color";
    private static final String EXTRA_CONTENT_INTENT = "content_intent";
    private static final String EXTRA_CONTENT_TEXT = "content_text";
    private static final String EXTRA_CONTENT_TITLE = "content_title";
    private static final String EXTRA_DELETE_INTENT = "delete_intent";
    private static final String EXTRA_IMPORTANCE = "importance";
    private static final String EXTRA_LARGE_BITMAP = "large_bitmap";
    private static final String EXTRA_SMALL_RES_ID = "small_res_id";
    private static final String TAG = "CarAppExtender";
    private ArrayList<Notification.Action> mActions;
    private String mChannelId;
    private CarColor mColor;
    private PendingIntent mContentIntent;
    private CharSequence mContentText;
    private CharSequence mContentTitle;
    private PendingIntent mDeleteIntent;
    private int mImportance;
    private Bitmap mLargeIconBitmap;
    private int mSmallIconResId;

    public CarAppExtender(Notification notification) {
        Bundle bundle;
        Bundle extras = NotificationCompat.getExtras(notification);
        if (extras != null && (bundle = extras.getBundle(EXTRA_CAR_EXTENDER)) != null) {
            this.mContentTitle = bundle.getCharSequence(EXTRA_CONTENT_TITLE);
            this.mContentText = bundle.getCharSequence(EXTRA_CONTENT_TEXT);
            this.mSmallIconResId = bundle.getInt(EXTRA_SMALL_RES_ID);
            this.mLargeIconBitmap = (Bitmap) bundle.getParcelable(EXTRA_LARGE_BITMAP);
            this.mContentIntent = (PendingIntent) bundle.getParcelable(EXTRA_CONTENT_INTENT);
            this.mDeleteIntent = (PendingIntent) bundle.getParcelable(EXTRA_DELETE_INTENT);
            ArrayList<Notification.Action> parcelableArrayList = bundle.getParcelableArrayList(EXTRA_ACTIONS);
            this.mActions = parcelableArrayList == null ? new ArrayList<>() : parcelableArrayList;
            this.mImportance = bundle.getInt(EXTRA_IMPORTANCE, NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
            Bundle bundle2 = bundle.getBundle("color");
            if (bundle2 != null) {
                try {
                    this.mColor = (CarColor) Bundler.fromBundle(bundle2);
                } catch (BundlerException e) {
                    Log.e(TAG, "Failed to deserialize the notification color", e);
                }
            }
            this.mChannelId = bundle.getString("channel_id");
        }
    }

    CarAppExtender(Builder builder) {
        this.mContentTitle = builder.mContentTitle;
        this.mContentText = builder.mContentText;
        this.mSmallIconResId = builder.mSmallIconResId;
        this.mLargeIconBitmap = builder.mLargeIconBitmap;
        this.mContentIntent = builder.mContentIntent;
        this.mDeleteIntent = builder.mDeleteIntent;
        this.mActions = builder.mActions;
        this.mImportance = builder.mImportance;
        this.mColor = builder.mColor;
        this.mChannelId = builder.mChannelId;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
        Objects.requireNonNull(builder);
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.mContentTitle;
        if (charSequence != null) {
            bundle.putCharSequence(EXTRA_CONTENT_TITLE, charSequence);
        }
        CharSequence charSequence2 = this.mContentText;
        if (charSequence2 != null) {
            bundle.putCharSequence(EXTRA_CONTENT_TEXT, charSequence2);
        }
        int i = this.mSmallIconResId;
        if (i != 0) {
            bundle.putInt(EXTRA_SMALL_RES_ID, i);
        }
        Bitmap bitmap = this.mLargeIconBitmap;
        if (bitmap != null) {
            bundle.putParcelable(EXTRA_LARGE_BITMAP, bitmap);
        }
        PendingIntent pendingIntent = this.mContentIntent;
        if (pendingIntent != null) {
            bundle.putParcelable(EXTRA_CONTENT_INTENT, pendingIntent);
        }
        PendingIntent pendingIntent2 = this.mDeleteIntent;
        if (pendingIntent2 != null) {
            bundle.putParcelable(EXTRA_DELETE_INTENT, pendingIntent2);
        }
        ArrayList<Notification.Action> arrayList = this.mActions;
        if (arrayList != null && !arrayList.isEmpty()) {
            bundle.putParcelableArrayList(EXTRA_ACTIONS, this.mActions);
        }
        bundle.putInt(EXTRA_IMPORTANCE, this.mImportance);
        CarColor carColor = this.mColor;
        if (carColor != null) {
            try {
                bundle.putBundle("color", Bundler.toBundle(carColor));
            } catch (BundlerException e) {
                Log.e(TAG, "Failed to serialize the notification color", e);
            }
        }
        String str = this.mChannelId;
        if (str != null) {
            bundle.putString("channel_id", str);
        }
        builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
        return builder;
    }

    public static boolean isExtended(Notification notification) {
        Bundle extras = NotificationCompat.getExtras((Notification) Objects.requireNonNull(notification));
        if (extras == null || extras.getBundle(EXTRA_CAR_EXTENDER) == null) {
            return false;
        }
        return true;
    }

    public CharSequence getContentTitle() {
        return this.mContentTitle;
    }

    public CharSequence getContentText() {
        return this.mContentText;
    }

    public int getSmallIcon() {
        return this.mSmallIconResId;
    }

    public Bitmap getLargeIcon() {
        return this.mLargeIconBitmap;
    }

    public PendingIntent getContentIntent() {
        return this.mContentIntent;
    }

    public PendingIntent getDeleteIntent() {
        return this.mDeleteIntent;
    }

    public List<Notification.Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActions);
    }

    public int getImportance() {
        return this.mImportance;
    }

    public CarColor getColor() {
        return this.mColor;
    }

    public String getChannelId() {
        return this.mChannelId;
    }

    public static final class Builder {
        final ArrayList<Notification.Action> mActions = new ArrayList<>();
        String mChannelId;
        CarColor mColor;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        PendingIntent mDeleteIntent;
        int mImportance = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        Bitmap mLargeIconBitmap;
        int mSmallIconResId;

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = (CharSequence) Objects.requireNonNull(charSequence);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = (CharSequence) Objects.requireNonNull(charSequence);
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mSmallIconResId = i;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.mLargeIconBitmap = (Bitmap) Objects.requireNonNull(bitmap);
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = (PendingIntent) Objects.requireNonNull(pendingIntent);
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.mDeleteIntent = (PendingIntent) Objects.requireNonNull(pendingIntent);
            return this;
        }

        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Notification.Action(i, (CharSequence) Objects.requireNonNull(charSequence), (PendingIntent) Objects.requireNonNull(pendingIntent)));
            return this;
        }

        public Builder setImportance(int i) {
            this.mImportance = i;
            return this;
        }

        public Builder setColor(CarColor carColor) {
            this.mColor = (CarColor) Objects.requireNonNull(carColor);
            return this;
        }

        public Builder setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public CarAppExtender build() {
            return new CarAppExtender(this);
        }
    }
}
