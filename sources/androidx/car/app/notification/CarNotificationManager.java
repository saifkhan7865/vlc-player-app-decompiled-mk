package androidx.car.app.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.car.app.R;
import androidx.car.app.model.CarColor;
import androidx.car.app.notification.CarAppExtender;
import androidx.car.app.utils.CommonUtils;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationChannelGroupCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import j$.util.Objects;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class CarNotificationManager {
    private final Context mContext;
    private final NotificationManagerCompat mNotificationManagerCompat;
    private final Integer mPrimaryColor;
    private final Integer mPrimaryColorDark;
    private final Integer mSecondaryColor;
    private final Integer mSecondaryColorDark;

    public static CarNotificationManager from(Context context) {
        return new CarNotificationManager((Context) Objects.requireNonNull(context));
    }

    public void cancel(int i) {
        this.mNotificationManagerCompat.cancel(i);
    }

    public void cancel(String str, int i) {
        this.mNotificationManagerCompat.cancel(str, i);
    }

    public void cancelAll() {
        this.mNotificationManagerCompat.cancelAll();
    }

    public void notify(int i, NotificationCompat.Builder builder) {
        this.mNotificationManagerCompat.notify(i, updateForCar((NotificationCompat.Builder) Objects.requireNonNull(builder)));
    }

    public void notify(String str, int i, NotificationCompat.Builder builder) {
        this.mNotificationManagerCompat.notify(str, i, updateForCar((NotificationCompat.Builder) Objects.requireNonNull(builder)));
    }

    public boolean areNotificationsEnabled() {
        return this.mNotificationManagerCompat.areNotificationsEnabled();
    }

    public int getImportance() {
        return this.mNotificationManagerCompat.getImportance();
    }

    public void createNotificationChannel(NotificationChannelCompat notificationChannelCompat) {
        this.mNotificationManagerCompat.createNotificationChannel((NotificationChannelCompat) Objects.requireNonNull(notificationChannelCompat));
    }

    public void createNotificationChannelGroup(NotificationChannelGroupCompat notificationChannelGroupCompat) {
        this.mNotificationManagerCompat.createNotificationChannelGroup((NotificationChannelGroupCompat) Objects.requireNonNull(notificationChannelGroupCompat));
    }

    public void createNotificationChannels(List<NotificationChannelCompat> list) {
        this.mNotificationManagerCompat.createNotificationChannelsCompat((List) Objects.requireNonNull(list));
    }

    public void createNotificationChannelGroups(List<NotificationChannelGroupCompat> list) {
        this.mNotificationManagerCompat.createNotificationChannelGroupsCompat((List) Objects.requireNonNull(list));
    }

    public void deleteNotificationChannel(String str) {
        this.mNotificationManagerCompat.deleteNotificationChannel((String) Objects.requireNonNull(str));
    }

    public void deleteNotificationChannelGroup(String str) {
        this.mNotificationManagerCompat.deleteNotificationChannelGroup((String) Objects.requireNonNull(str));
    }

    public void deleteUnlistedNotificationChannels(Collection<String> collection) {
        this.mNotificationManagerCompat.deleteUnlistedNotificationChannels((Collection) Objects.requireNonNull(collection));
    }

    public NotificationChannelCompat getNotificationChannel(String str) {
        return this.mNotificationManagerCompat.getNotificationChannelCompat((String) Objects.requireNonNull(str));
    }

    public NotificationChannelCompat getNotificationChannel(String str, String str2) {
        return this.mNotificationManagerCompat.getNotificationChannelCompat((String) Objects.requireNonNull(str), (String) Objects.requireNonNull(str2));
    }

    public NotificationChannelGroupCompat getNotificationChannelGroup(String str) {
        return this.mNotificationManagerCompat.getNotificationChannelGroupCompat((String) Objects.requireNonNull(str));
    }

    public List<NotificationChannelCompat> getNotificationChannels() {
        return this.mNotificationManagerCompat.getNotificationChannelsCompat();
    }

    public List<NotificationChannelGroupCompat> getNotificationChannelGroups() {
        return this.mNotificationManagerCompat.getNotificationChannelGroupsCompat();
    }

    public static Set<String> getEnabledListenerPackages(Context context) {
        return NotificationManagerCompat.getEnabledListenerPackages((Context) Objects.requireNonNull(context));
    }

    /* access modifiers changed from: package-private */
    public Notification updateForCar(NotificationCompat.Builder builder) {
        if (CommonUtils.isAutomotiveOS(this.mContext)) {
            return updateForAutomotive(builder);
        }
        if (!CarAppExtender.isExtended(builder.build())) {
            builder.extend(new CarAppExtender.Builder().build());
        }
        return builder.build();
    }

    private Notification updateForAutomotive(NotificationCompat.Builder builder) {
        Integer colorInt;
        if (Build.VERSION.SDK_INT >= 29) {
            CarAppExtender carAppExtender = new CarAppExtender(builder.build());
            Api29Impl.convertActionsToCompatActions(builder, carAppExtender.getActions());
            CarColor color = carAppExtender.getColor();
            if (!(color == null || (colorInt = getColorInt(color)) == null)) {
                builder.setColorized(true);
                builder.setColor(colorInt.intValue());
            }
            PendingIntent contentIntent = carAppExtender.getContentIntent();
            if (contentIntent != null) {
                builder.setContentIntent(contentIntent);
            }
            CharSequence contentTitle = carAppExtender.getContentTitle();
            if (contentTitle != null) {
                builder.setContentTitle(contentTitle);
            }
            CharSequence contentText = carAppExtender.getContentText();
            if (contentText != null) {
                builder.setContentText(contentText);
            }
            PendingIntent deleteIntent = carAppExtender.getDeleteIntent();
            if (deleteIntent != null) {
                builder.setDeleteIntent(deleteIntent);
            }
            String channelId = carAppExtender.getChannelId();
            if (channelId != null) {
                builder.setChannelId(channelId);
            }
            Bitmap largeIcon = carAppExtender.getLargeIcon();
            if (largeIcon != null) {
                builder.setLargeIcon(largeIcon);
            }
            int smallIcon = carAppExtender.getSmallIcon();
            if (smallIcon != 0) {
                builder.setSmallIcon(smallIcon);
            }
            return builder.build();
        }
        throw new UnsupportedOperationException("Not supported for Automotive OS before API 29.");
    }

    /* access modifiers changed from: package-private */
    public Integer getColorInt(CarColor carColor) {
        boolean z = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
        int type = carColor.getType();
        if (type != 0) {
            switch (type) {
                case 2:
                    return z ? this.mPrimaryColorDark : this.mPrimaryColor;
                case 3:
                    return z ? this.mSecondaryColorDark : this.mSecondaryColor;
                case 4:
                    return Integer.valueOf(this.mContext.getResources().getColor(R.color.carColorRed));
                case 5:
                    return Integer.valueOf(this.mContext.getResources().getColor(R.color.carColorGreen));
                case 6:
                    return Integer.valueOf(this.mContext.getResources().getColor(R.color.carColorBlue));
                case 7:
                    return Integer.valueOf(this.mContext.getResources().getColor(R.color.carColorYellow));
                default:
                    return null;
            }
        } else {
            return Integer.valueOf(z ? carColor.getColorDark() : carColor.getColor());
        }
    }

    private static int loadThemeId(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getInt("androidx.car.app.theme");
            }
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    private static Integer getColor(int i, Resources.Theme theme) {
        if (i == 0) {
            return null;
        }
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{i});
        Integer valueOf = Integer.valueOf(obtainStyledAttributes.getColor(0, 0));
        obtainStyledAttributes.recycle();
        return valueOf;
    }

    private CarNotificationManager(Context context) {
        Context context2 = (Context) Objects.requireNonNull(context);
        this.mContext = context2;
        this.mNotificationManagerCompat = NotificationManagerCompat.from(context);
        Context createConfigurationContext = context2.createConfigurationContext(context.getResources().getConfiguration());
        int loadThemeId = loadThemeId(context);
        if (loadThemeId != 0) {
            createConfigurationContext.setTheme(loadThemeId);
        }
        Resources.Theme theme = createConfigurationContext.getTheme();
        Resources m = theme.getResources();
        this.mPrimaryColor = getColor(m.getIdentifier("carColorPrimary", "attr", context.getPackageName()), theme);
        this.mPrimaryColorDark = getColor(m.getIdentifier("carColorPrimaryDark", "attr", context.getPackageName()), theme);
        this.mSecondaryColor = getColor(m.getIdentifier("carColorSecondary", "attr", context.getPackageName()), theme);
        this.mSecondaryColorDark = getColor(m.getIdentifier("carColorSecondaryDark", "attr", context.getPackageName()), theme);
    }

    private static final class Api29Impl {
        static void convertActionsToCompatActions(NotificationCompat.Builder builder, List<Notification.Action> list) {
            if (!list.isEmpty()) {
                builder.clearActions();
                for (Notification.Action fromAndroidAction : list) {
                    builder.addAction(fromAndroidAction(fromAndroidAction));
                }
            }
        }

        private static NotificationCompat.Action fromAndroidAction(Notification.Action action) {
            int i;
            if (action.getIcon() == null) {
                i = 0;
            } else {
                i = action.getIcon().getResId();
            }
            return new NotificationCompat.Action(i, action.title, action.actionIntent);
        }

        private Api29Impl() {
        }
    }
}
