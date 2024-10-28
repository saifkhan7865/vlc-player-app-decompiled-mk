package org.videolan.vlc;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\"\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\b\u0010\u001a\u001a\u00020\u000bH\u0016J\u0012\u0010\u001b\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u0012\u0010\u0004\u001a\u00020\u0005X\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/RecommendationsService;", "Landroid/app/IntentService;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "mNotificationManager", "Landroid/app/NotificationManager;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "buildPendingIntent", "Landroid/app/PendingIntent;", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "id", "", "buildRecommendation", "priority", "doRecommendations", "Lkotlinx/coroutines/Job;", "getApplicationContext", "onCreate", "onDestroy", "onHandleIntent", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecommendationsService.kt */
public final class RecommendationsService extends IntentService implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    /* access modifiers changed from: private */
    public NotificationManager mNotificationManager;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public RecommendationsService() {
        super("RecommendationService");
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context != null ? LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale()) : null);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    public void onCreate() {
        super.onCreate();
        Object appSystemService = KextensionsKt.getAppSystemService(this, "notification");
        Intrinsics.checkNotNull(appSystemService, "null cannot be cast to non-null type android.app.NotificationManager");
        this.mNotificationManager = (NotificationManager) appSystemService;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        doRecommendations();
    }

    /* access modifiers changed from: private */
    public final void buildRecommendation(MediaWrapper mediaWrapper, int i, int i2) {
        if (mediaWrapper != null) {
            Context context = this;
            Notification build = new NotificationCompat.BigPictureStyle(new NotificationCompat.Builder(context, "vlc_recommendations").setContentTitle(mediaWrapper.getTitle()).setContentText(mediaWrapper.getDescription()).setContentInfo(getString(R.string.app_name)).setPriority(i2).setLocalOnly(true).setOngoing(true).setColor(ContextCompat.getColor(context, R.color.orange800)).setCategory(NotificationCompat.CATEGORY_RECOMMENDATION).setLargeIcon(BitmapUtil.INSTANCE.getPicture(mediaWrapper)).setSmallIcon(R.drawable.icon).setContentIntent(buildPendingIntent(mediaWrapper, i))).build();
            NotificationManager notificationManager = this.mNotificationManager;
            if (notificationManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mNotificationManager");
                notificationManager = null;
            }
            notificationManager.notify(i, build);
        }
    }

    private final PendingIntent buildPendingIntent(MediaWrapper mediaWrapper, int i) {
        Context context = this;
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.setAction(Constants.PLAY_FROM_VIDEOGRID);
        intent.putExtra(Constants.PLAY_EXTRA_ITEM_LOCATION, mediaWrapper.getUri());
        intent.putExtra("title", mediaWrapper.getTitle());
        intent.putExtra(Constants.PLAY_EXTRA_FROM_START, false);
        intent.putExtra(VideoPlayerActivity.FROM_EXTERNAL, true);
        PendingIntent activity = PendingIntent.getActivity(context, i, intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        return activity;
    }

    private final Job doRecommendations() {
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new RecommendationsService$doRecommendations$1(this, (Continuation<? super RecommendationsService$doRecommendations$1>) null), 3, (Object) null);
    }

    public void onDestroy() {
        CoroutineScopeKt.cancel$default(this, (CancellationException) null, 1, (Object) null);
        super.onDestroy();
    }
}
