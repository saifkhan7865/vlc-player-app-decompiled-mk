package org.videolan.vlc.gui.helpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.media.app.NotificationCompat;
import androidx.media.session.MediaButtonReceiver;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.DrawableCache;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.util.PlaybackAction;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0002J2\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bH\u0007J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bH\u0007J\u0001\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00132\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010\tJ\u001e\u0010*\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0013J\u0016\u0010-\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u0004J.\u0010/\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000203R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lorg/videolan/vlc/gui/helpers/NotificationHelper;", "", "()V", "TAG", "", "VLC_DEBUG_CHANNEL", "notificationIntent", "Landroid/content/Intent;", "buildCustomButtonPendingIntent", "Landroid/app/PendingIntent;", "ctx", "Landroid/content/Context;", "actionId", "buildMediaButtonPendingIntent", "enabledActions", "Lorg/videolan/vlc/util/FlagSet;", "Lorg/videolan/vlc/util/PlaybackAction;", "action", "allowIntent", "", "createDebugServcieChannel", "", "appCtx", "createNotificationChannels", "createPlaybackNotification", "Landroid/app/Notification;", "video", "title", "artist", "album", "cover", "Landroid/graphics/Bitmap;", "playing", "pausable", "seekable", "speed", "", "podcastMode", "seekInCompactView", "sessionToken", "Landroid/support/v4/media/session/MediaSessionCompat$Token;", "spi", "createRemoteAccessNotification", "connectionTip", "started", "createRemoteAccessOtpNotification", "code", "createScanNotification", "progressText", "paused", "max", "", "progress", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationHelper.kt */
public final class NotificationHelper {
    public static final NotificationHelper INSTANCE = new NotificationHelper();
    public static final String TAG = "VLC/NotificationHelper";
    public static final String VLC_DEBUG_CHANNEL = "vlc_debug";
    private static final Intent notificationIntent = new Intent();

    private NotificationHelper() {
    }

    public final Notification createPlaybackNotification(Context context, boolean z, String str, String str2, String str3, Bitmap bitmap, boolean z2, boolean z3, boolean z4, float f, boolean z5, boolean z6, FlagSet<PlaybackAction> flagSet, MediaSessionCompat.Token token, PendingIntent pendingIntent) {
        boolean z7;
        int[] iArr;
        Object obj;
        Context context2 = context;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        boolean z8 = z2;
        MediaSessionCompat.Token token2 = token;
        PendingIntent pendingIntent2 = pendingIntent;
        Intrinsics.checkNotNullParameter(context2, "ctx");
        Intrinsics.checkNotNullParameter(str4, "title");
        Intrinsics.checkNotNullParameter(str5, ArtworkProvider.ARTIST);
        Intrinsics.checkNotNullParameter(str6, ArtworkProvider.ALBUM);
        Intrinsics.checkNotNullParameter(flagSet, "enabledActions");
        PendingIntent buildMediaButtonPendingIntent = MediaButtonReceiver.buildMediaButtonPendingIntent(context2, 1);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context2, "vlc_playback");
        builder.setSmallIcon(z ? R.drawable.ic_notif_video : R.drawable.ic_notif_audio).setVisibility(1).setContentTitle(str4).setContentText(TextUtils.INSTANCE.separatedStringArgs('-', str5, str6)).setLargeIcon(bitmap).setTicker(TextUtils.INSTANCE.separatedStringArgs('-', str4, str5)).setAutoCancel(!z8).setOngoing(z8).setCategory(NotificationCompat.CATEGORY_TRANSPORT).setDeleteIntent(buildMediaButtonPendingIntent).setColor(ViewCompat.MEASURED_STATE_MASK);
        if (pendingIntent2 != null) {
            builder.setContentIntent(pendingIntent2);
        }
        if (z5) {
            HashMap hashMapOf = MapsKt.hashMapOf(TuplesKt.to(Float.valueOf(0.5f), Integer.valueOf(R.drawable.ic_notif_speed_0_50)), TuplesKt.to(Float.valueOf(0.8f), Integer.valueOf(R.drawable.ic_notif_speed_0_80)), TuplesKt.to(Float.valueOf(1.0f), Integer.valueOf(R.drawable.ic_notif_speed_1_00)), TuplesKt.to(Float.valueOf(1.1f), Integer.valueOf(R.drawable.ic_notif_speed_1_10)), TuplesKt.to(Float.valueOf(1.2f), Integer.valueOf(R.drawable.ic_notif_speed_1_20)), TuplesKt.to(Float.valueOf(1.5f), Integer.valueOf(R.drawable.ic_notif_speed_1_50)), TuplesKt.to(Float.valueOf(2.0f), Integer.valueOf(R.drawable.ic_notif_speed_2_00)));
            Map map = hashMapOf;
            Set keySet = hashMapOf.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
            Iterator it = keySet.iterator();
            if (!it.hasNext()) {
                obj = null;
            } else {
                Object next = it.next();
                if (!it.hasNext()) {
                    obj = next;
                } else {
                    Float f2 = (Float) next;
                    Intrinsics.checkNotNull(f2);
                    float abs = Math.abs(f - f2.floatValue());
                    do {
                        Object next2 = it.next();
                        Float f3 = (Float) next2;
                        Intrinsics.checkNotNull(f3);
                        float abs2 = Math.abs(f - f3.floatValue());
                        if (Float.compare(abs, abs2) > 0) {
                            next = next2;
                            abs = abs2;
                        }
                    } while (it.hasNext());
                }
                obj = next;
            }
            Integer num = (Integer) map.get(obj);
            builder.addAction(new NotificationCompat.Action(num != null ? num.intValue() : R.drawable.ic_notif_speed_1_00, (CharSequence) context2.getString(R.string.playback_speed), buildCustomButtonPendingIntent(context2, Constants.CUSTOM_ACTION_SPEED)));
        } else {
            builder.addAction(new NotificationCompat.Action(R.drawable.ic_notif_previous, (CharSequence) context2.getString(R.string.previous), buildMediaButtonPendingIntent$default(this, context, flagSet, PlaybackAction.ACTION_SKIP_TO_PREVIOUS, false, 8, (Object) null)));
        }
        DrawableCache drawableCache = DrawableCache.INSTANCE;
        builder.addAction(new NotificationCompat.Action(drawableCache.getDrawableFromMemCache(context2, "ic_notif_rewind_" + Settings.INSTANCE.getAudioJumpDelay(), R.drawable.ic_notif_rewind), (CharSequence) context2.getString(R.string.playback_rewind), buildMediaButtonPendingIntent$default(this, context, flagSet, PlaybackAction.ACTION_REWIND, false, 8, (Object) null)));
        if (!z3) {
            builder.addAction(new NotificationCompat.Action(R.drawable.ic_widget_close_w, (CharSequence) context2.getString(R.string.stop), buildMediaButtonPendingIntent));
        } else if (z8) {
            builder.addAction(new NotificationCompat.Action(R.drawable.ic_widget_pause_w, (CharSequence) context2.getString(R.string.pause), MediaButtonReceiver.buildMediaButtonPendingIntent(context2, 512)));
        } else {
            builder.addAction(new NotificationCompat.Action(R.drawable.ic_widget_play_w, (CharSequence) context2.getString(R.string.play), MediaButtonReceiver.buildMediaButtonPendingIntent(context2, 512)));
        }
        DrawableCache drawableCache2 = DrawableCache.INSTANCE;
        builder.addAction(new NotificationCompat.Action(drawableCache2.getDrawableFromMemCache(context2, "ic_notif_forward_" + Settings.INSTANCE.getAudioJumpDelay(), R.drawable.ic_notif_forward), (CharSequence) context2.getString(R.string.playback_forward), buildMediaButtonPendingIntent$default(this, context, flagSet, PlaybackAction.ACTION_FAST_FORWARD, false, 8, (Object) null)));
        if (z5) {
            builder.addAction(new NotificationCompat.Action(R.drawable.ic_notif_bookmark_add, (CharSequence) context2.getString(R.string.add_bookmark), buildCustomButtonPendingIntent(context2, Constants.CUSTOM_ACTION_BOOKMARK)));
        } else {
            builder.addAction(new NotificationCompat.Action(R.drawable.ic_notif_next, (CharSequence) context2.getString(R.string.next), buildMediaButtonPendingIntent$default(this, context, flagSet, PlaybackAction.ACTION_SKIP_TO_NEXT, false, 8, (Object) null)));
        }
        if (AndroidDevices.INSTANCE.getShowMediaStyle()) {
            if (z5 || (z4 && z6)) {
                z7 = true;
                iArr = new int[]{1, 2, 3};
            } else {
                z7 = true;
                iArr = new int[]{0, 2, 4};
            }
            NotificationCompat.MediaStyle cancelButtonIntent = new NotificationCompat.MediaStyle().setShowActionsInCompactView(Arrays.copyOf(iArr, iArr.length)).setShowCancelButton(z7).setCancelButtonIntent(buildMediaButtonPendingIntent);
            if (token2 != null) {
                cancelButtonIntent.setMediaSession(token2);
            }
            builder.setStyle(cancelButtonIntent);
        }
        Notification build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    static /* synthetic */ PendingIntent buildMediaButtonPendingIntent$default(NotificationHelper notificationHelper, Context context, FlagSet flagSet, PlaybackAction playbackAction, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return notificationHelper.buildMediaButtonPendingIntent(context, flagSet, playbackAction, z);
    }

    private final PendingIntent buildMediaButtonPendingIntent(Context context, FlagSet<PlaybackAction> flagSet, PlaybackAction playbackAction, boolean z) {
        if (!z || !flagSet.contains(playbackAction)) {
            return null;
        }
        return MediaButtonReceiver.buildMediaButtonPendingIntent(context, playbackAction.toLong());
    }

    private final PendingIntent buildCustomButtonPendingIntent(Context context, String str) {
        Intent intent = new Intent(Constants.CUSTOM_ACTION);
        intent.putExtra(Constants.EXTRA_CUSTOM_ACTION_ID, str);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, str.hashCode(), intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    public final Notification createScanNotification(Context context, String str, boolean z, int i, int i2) {
        NotificationCompat.Action action;
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(str, "progressText");
        Intent className = new Intent("android.intent.action.VIEW").setClassName(context, Constants.START_ACTIVITY);
        Intrinsics.checkNotNullExpressionValue(className, "setClassName(...)");
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(context, "vlc_medialibrary").setContentIntent(PendingIntent.getActivity(context, 0, className, 201326592)).setSmallIcon(R.drawable.ic_notif_scan).setVisibility(1).setContentTitle(context.getString(R.string.ml_scanning)).setProgress(i, i2, i < 1 || i2 < 1).setAutoCancel(false).setCategory("progress").setOngoing(true);
        Intrinsics.checkNotNullExpressionValue(ongoing, "setOngoing(...)");
        ongoing.setContentText(str);
        Intent intent = notificationIntent;
        intent.setAction(z ? Constants.ACTION_RESUME_SCAN : Constants.ACTION_PAUSE_SCAN);
        intent.setPackage(context.getPackageName());
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        PendingIntent broadcast = PendingIntent.getBroadcast(LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale()), 0, intent, 201326592);
        if (z) {
            action = new NotificationCompat.Action(R.drawable.ic_play_notif, (CharSequence) context.getString(R.string.resume), broadcast);
        } else {
            action = new NotificationCompat.Action(R.drawable.ic_pause_notif, (CharSequence) context.getString(R.string.pause), broadcast);
        }
        ongoing.addAction(action);
        Notification build = ongoing.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final Notification createRemoteAccessNotification(Context context, String str, boolean z) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(str, "connectionTip");
        Intent intent = new Intent(context, StartActivity.class);
        intent.setAction("vlc.remoteaccess.share");
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(context, "vlc_remote_access").setContentIntent(PendingIntent.getActivity(context, 0, intent, 201326592)).setSmallIcon(R.drawable.ic_notif_remote_access).setVisibility(1).setContentTitle(context.getString(R.string.ra_server_running)).setAutoCancel(false).setCategory(androidx.core.app.NotificationCompat.CATEGORY_SERVICE).setOngoing(true);
        Intrinsics.checkNotNullExpressionValue(ongoing, "setOngoing(...)");
        ongoing.setContentText(str);
        Intent intent2 = new Intent();
        intent2.setAction(Constants.ACTION_DISABLE_SERVER);
        intent2.setPackage(context.getPackageName());
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        ongoing.addAction(new NotificationCompat.Action(R.drawable.ic_popup_close_w, (CharSequence) context.getString(R.string.ra_disable), PendingIntent.getBroadcast(LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale()), 0, intent2, 201326592)));
        Intent intent3 = new Intent();
        intent3.setAction(z ? Constants.ACTION_STOP_SERVER : Constants.ACTION_START_SERVER);
        intent3.setPackage(context.getPackageName());
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        ongoing.addAction(new NotificationCompat.Action(z ? R.drawable.ic_pause_notif : R.drawable.ic_play_notif, (CharSequence) context.getString(z ? R.string.stop : R.string.start), PendingIntent.getBroadcast(LocaleUtilsKt.getContextWithLocale(applicationContext2, AppContextProvider.INSTANCE.getLocale()), 0, intent3, 201326592)));
        Notification build = ongoing.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final Notification createRemoteAccessOtpNotification(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(str, OAuth2RequestParameters.Code);
        NotificationCompat.Builder category = new NotificationCompat.Builder(context, "vlc_remote_access_otp").setSmallIcon(R.drawable.ic_notif_remote_access).setVisibility(1).setContentTitle(context.getString(R.string.ra_otp_title)).setAutoCancel(true).setCategory(androidx.core.app.NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkNotNullExpressionValue(category, "setCategory(...)");
        category.setContentText(context.getString(R.string.ra_code, new Object[]{str}));
        Notification build = category.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final void createNotificationChannels(Context context) {
        Intrinsics.checkNotNullParameter(context, "appCtx");
        if (AndroidUtil.isOOrLater) {
            Object systemService = ContextCompat.getSystemService(context, NotificationManager.class);
            Intrinsics.checkNotNull(systemService);
            NotificationManager notificationManager = (NotificationManager) systemService;
            List arrayList = new ArrayList();
            if (notificationManager.getNotificationChannel("vlc_playback") == null) {
                String string = context.getString(R.string.playback);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = context.getString(R.string.playback_controls);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                NotificationChannel notificationChannel = new NotificationChannel("vlc_playback", string, 2);
                notificationChannel.setDescription(string2);
                notificationChannel.setLockscreenVisibility(1);
                arrayList.add(notificationChannel);
            }
            if (notificationManager.getNotificationChannel("vlc_medialibrary") == null) {
                String string3 = context.getString(R.string.medialibrary_scan);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = context.getString(R.string.Medialibrary_progress);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                NotificationChannel notificationChannel2 = new NotificationChannel("vlc_medialibrary", string3, 2);
                notificationChannel2.setDescription(string4);
                notificationChannel2.setLockscreenVisibility(1);
                arrayList.add(notificationChannel2);
            }
            if (notificationManager.getNotificationChannel("vlc_remote_access") == null) {
                String string5 = context.getString(R.string.ra_remote_access);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String string6 = context.getString(R.string.ra_remote_access_description);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                NotificationChannel notificationChannel3 = new NotificationChannel("vlc_remote_access", string5, 1);
                notificationChannel3.setDescription(string6);
                notificationChannel3.setLockscreenVisibility(1);
                arrayList.add(notificationChannel3);
            }
            if (notificationManager.getNotificationChannel("vlc_remote_access_otp") == null) {
                String string7 = context.getString(R.string.ra_otp);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                String string8 = context.getString(R.string.ra_otp_description);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                NotificationChannel notificationChannel4 = new NotificationChannel("vlc_remote_access_otp", string7, 4);
                notificationChannel4.setDescription(string8);
                notificationChannel4.setLockscreenVisibility(1);
                if (Build.VERSION.SDK_INT >= 29) {
                    notificationChannel4.setAllowBubbles(true);
                }
                arrayList.add(notificationChannel4);
            }
            if (notificationManager.getNotificationChannel(NotificationHelperKt.MISC_CHANNEL_ID) == null) {
                String string9 = context.getString(R.string.misc);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                NotificationChannel notificationChannel5 = new NotificationChannel(NotificationHelperKt.MISC_CHANNEL_ID, string9, 2);
                notificationChannel5.setLockscreenVisibility(1);
                arrayList.add(notificationChannel5);
            }
            if (AndroidDevices.INSTANCE.isAndroidTv() && notificationManager.getNotificationChannel("vlc_recommendations") == null) {
                String string10 = context.getString(R.string.recommendations);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                String string11 = context.getString(R.string.recommendations_desc);
                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                NotificationChannel notificationChannel6 = new NotificationChannel("vlc_recommendations", string10, 2);
                notificationChannel6.setDescription(string11);
                notificationChannel6.setLockscreenVisibility(1);
                arrayList.add(notificationChannel6);
            }
            if (!arrayList.isEmpty()) {
                notificationManager.createNotificationChannels(arrayList);
            }
        }
    }

    public final void createDebugServcieChannel(Context context) {
        Intrinsics.checkNotNullParameter(context, "appCtx");
        Object systemService = ContextCompat.getSystemService(context, NotificationManager.class);
        Intrinsics.checkNotNull(systemService);
        String string = context.getString(R.string.debug_logs);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        NotificationChannel notificationChannel = new NotificationChannel(VLC_DEBUG_CHANNEL, string, 2);
        notificationChannel.setLockscreenVisibility(1);
        ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
    }
}
