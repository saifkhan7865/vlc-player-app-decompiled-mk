package org.videolan.vlc;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ServiceLifecycleDispatcher;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.DevicesDiscoveryCb;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.stubs.StubMedialibrary;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.gui.SendCrashActivity;
import org.videolan.vlc.gui.helpers.NotificationHelper;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.Util;

@Metadata(d1 = {"\u0000¥\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001&\u0018\u0000 p2\u00020\u00012\u00020\u0002:\u0002pqB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\u001e\u00108\u001a\u0002062\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\fH@¢\u0006\u0002\u0010<J\u0012\u0010=\u001a\u0002062\b\u0010>\u001a\u0004\u0018\u00010:H\u0014J&\u0010?\u001a\u0002062\u0006\u00109\u001a\u00020:2\u0006\u0010@\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH@¢\u0006\u0002\u0010AJ\u0010\u0010B\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\u0010\u0010C\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\b\u0010D\u001a\u000206H\u0002J\b\u0010E\u001a\u000206H\u0003J\b\u0010F\u001a\u00020:H\u0016J\b\u0010G\u001a\u000206H\u0002J6\u0010H\u001a\u0002062\u0006\u0010I\u001a\u00020\f2\u0006\u00109\u001a\u00020:2\u0006\u0010J\u001a\u00020\f2\u0006\u0010K\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH@¢\u0006\u0002\u0010LJ\u0010\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0016J\b\u0010Q\u001a\u000206H\u0017J\b\u0010R\u001a\u000206H\u0016J\b\u0010S\u001a\u000206H\u0016J\u0010\u0010T\u001a\u0002062\u0006\u0010U\u001a\u00020\nH\u0016J\u0010\u0010V\u001a\u0002062\u0006\u0010U\u001a\u00020\nH\u0016J\b\u0010W\u001a\u000206H\u0016J\u0018\u0010X\u001a\u0002062\u0006\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020\u0013H\u0016J\u0010\u0010[\u001a\u0002062\u0006\u0010U\u001a\u00020\nH\u0016J\u0010\u0010\\\u001a\u0002062\u0006\u0010U\u001a\u00020\nH\u0016J\"\u0010]\u001a\u00020\u00132\b\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010^\u001a\u00020\u00132\u0006\u0010_\u001a\u00020\u0013H\u0016J\u0012\u0010(\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\nH\u0002J \u0010`\u001a\u0002062\u0006\u0010K\u001a\u00020\f2\u0006\u0010I\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0002J\b\u0010a\u001a\u000206H\u0002J\u001e\u0010b\u001a\u0002062\u0006\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020\u0013H@¢\u0006\u0002\u0010cJ\u0018\u0010d\u001a\u0002062\u0006\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020\nH\u0002J\u0010\u0010h\u001a\u0002062\u0006\u0010i\u001a\u00020\nH\u0002J\u0018\u0010j\u001a\u0002062\u0006\u0010J\u001a\u00020\f2\u0006\u0010K\u001a\u00020\fH\u0002J\u000e\u0010k\u001a\u000206H@¢\u0006\u0002\u0010lJ\u0018\u0010m\u001a\u000206*\b\u0012\u0004\u0012\u00020\u00060nH@¢\u0006\u0002\u0010oR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017R\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X.¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0005X.¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&X\u0004¢\u0006\u0004\n\u0002\u0010'R\u000e\u0010(\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020-8BX\u0002¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b.\u0010/R\u0012\u00102\u001a\u000603R\u000204X.¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Lorg/videolan/vlc/MediaParsingService;", "Landroidx/lifecycle/LifecycleService;", "Lorg/videolan/medialibrary/interfaces/DevicesDiscoveryCb;", "()V", "actions", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/MLAction;", "binder", "Lorg/videolan/vlc/MediaParsingService$LocalBinder;", "currentDiscovery", "", "discoverTriggered", "", "dispatcher", "Landroidx/lifecycle/ServiceLifecycleDispatcher;", "exceptionHandler", "Lorg/videolan/medialibrary/interfaces/Medialibrary$MedialibraryExceptionHandler;", "inDiscovery", "lastDone", "", "getLastDone", "()I", "setLastDone", "(I)V", "lastNotificationTime", "", "lastScheduled", "getLastScheduled", "setLastScheduled", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "notificationActor", "Lorg/videolan/vlc/Notification;", "receiver", "org/videolan/vlc/MediaParsingService$receiver$1", "Lorg/videolan/vlc/MediaParsingService$receiver$1;", "reload", "scanActivated", "scanPaused", "serviceLock", "settings", "Landroid/content/SharedPreferences;", "getSettings", "()Landroid/content/SharedPreferences;", "settings$delegate", "Lkotlin/Lazy;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "addDeviceIfNeeded", "", "path", "addDevices", "context", "Landroid/content/Context;", "removeDevices", "(Landroid/content/Context;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "attachBaseContext", "newBase", "checkNewDevicesForDialog", "addExternal", "(Landroid/content/Context;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discover", "discoverStorage", "exitCommand", "forceForeground", "getApplicationContext", "hideNotification", "initMedialib", "parse", "shouldInit", "upgrade", "(ZLandroid/content/Context;ZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onDiscoveryCompleted", "onDiscoveryFailed", "entryPoint", "onDiscoveryProgress", "onDiscoveryStarted", "onParsingStatsUpdated", "done", "scheduled", "onReloadCompleted", "onReloadStarted", "onStartCommand", "flags", "startId", "setupMedialibrary", "setupScope", "showNotification", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showProgress", "parsing", "", "progressText", "showStorageNotification", "device", "startScan", "updateStorages", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processAction", "Lkotlinx/coroutines/channels/ActorScope;", "(Lkotlinx/coroutines/channels/ActorScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "LocalBinder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
public final class MediaParsingService extends LifecycleService implements DevicesDiscoveryCb {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MutableLiveData<DiscoveryError> discoveryError = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public static final MutableLiveData<List<String>> newStorages = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public static final List<String> preselectedStorages = new ArrayList();
    /* access modifiers changed from: private */
    public static final MutableLiveData<ScanProgress> progress = new MutableLiveData<>();
    private SendChannel<? super MLAction> actions;
    private final LocalBinder binder = new LocalBinder();
    /* access modifiers changed from: private */
    public String currentDiscovery;
    private volatile boolean discoverTriggered;
    private final ServiceLifecycleDispatcher dispatcher = new ServiceLifecycleDispatcher(this);
    private final Medialibrary.MedialibraryExceptionHandler exceptionHandler = new MediaParsingService$$ExternalSyntheticLambda1(this);
    /* access modifiers changed from: private */
    public boolean inDiscovery;
    private int lastDone = -1;
    /* access modifiers changed from: private */
    public volatile long lastNotificationTime;
    private int lastScheduled = -1;
    /* access modifiers changed from: private */
    public Medialibrary medialibrary;
    /* access modifiers changed from: private */
    public SendChannel<? super Notification> notificationActor;
    private final MediaParsingService$receiver$1 receiver = new MediaParsingService$receiver$1(this);
    private int reload;
    private volatile boolean scanActivated;
    /* access modifiers changed from: private */
    public boolean scanPaused;
    private volatile boolean serviceLock;
    private final Lazy settings$delegate = LazyKt.lazy(new MediaParsingService$settings$2(this));
    /* access modifiers changed from: private */
    public PowerManager.WakeLock wakeLock;

    private final SharedPreferences getSettings() {
        return (SharedPreferences) this.settings$delegate.getValue();
    }

    public final int getLastDone() {
        return this.lastDone;
    }

    public final void setLastDone(int i) {
        this.lastDone = i;
    }

    public final int getLastScheduled() {
        return this.lastScheduled;
    }

    public final void setLastScheduled(int i) {
        this.lastScheduled = i;
    }

    /* access modifiers changed from: private */
    public static final void exceptionHandler$lambda$1(MediaParsingService mediaParsingService, String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(mediaParsingService, "this$0");
        Intent intent = new Intent(mediaParsingService.getApplicationContext(), SendCrashActivity.class);
        intent.putExtra(Constants.CRASH_ML_CTX, str);
        intent.putExtra(Constants.CRASH_ML_MSG, str2);
        intent.addFlags(268435456);
        Log.wtf("VLC/MediaParsingService", "medialibrary reported unhandled exception: -----------------");
        Object unused = BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new MediaParsingService$exceptionHandler$1$1(mediaParsingService, intent, (Continuation<? super MediaParsingService$exceptionHandler$1$1>) null), 1, (Object) null);
    }

    private static final void exceptionHandler$lambda$2(String str, String str2, boolean z) {
        throw new IllegalStateException(str + ":\n" + str2);
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
        this.dispatcher.onServicePreSuperOnCreate();
        super.onCreate();
        NotificationHelper.INSTANCE.createNotificationChannels(getApplicationContext());
        if (AndroidUtil.isOOrLater) {
            forceForeground();
        }
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
        Medialibrary medialibrary2 = null;
        if (instance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            instance = null;
        }
        instance.addDeviceDiscoveryCb(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_PAUSE_SCAN);
        intentFilter.addAction(Constants.ACTION_RESUME_SCAN);
        ExtensionsKt.registerReceiverCompat(this, this.receiver, intentFilter, false);
        Object systemService = ContextCompat.getSystemService(getApplicationContext(), PowerManager.class);
        Intrinsics.checkNotNull(systemService);
        PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(1, "VLC:MediaParsingService");
        Intrinsics.checkNotNullExpressionValue(newWakeLock, "newWakeLock(...)");
        this.wakeLock = newWakeLock;
        if (this.lastNotificationTime == 5) {
            stopService(new Intent(getApplicationContext(), MediaParsingService.class));
        }
        Medialibrary.getState().observe(this, new MediaParsingService$$ExternalSyntheticLambda0(this));
        Medialibrary medialibrary3 = this.medialibrary;
        if (medialibrary3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        } else {
            medialibrary2 = medialibrary3;
        }
        medialibrary2.setExceptionHandler(this.exceptionHandler);
        setupScope();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$3(MediaParsingService mediaParsingService, Boolean bool) {
        Intrinsics.checkNotNullParameter(mediaParsingService, "this$0");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(mediaParsingService), (CoroutineContext) null, (CoroutineStart) null, new MediaParsingService$onCreate$1$1(bool, mediaParsingService, (Continuation<? super MediaParsingService$onCreate$1$1>) null), 3, (Object) null);
    }

    private final void setupScope() {
        LifecycleOwner lifecycleOwner = this;
        this.actions = ActorKt.actor$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), Dispatchers.getIO(), Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new MediaParsingService$setupScope$1(this, (Continuation<? super MediaParsingService$setupScope$1>) null), 12, (Object) null);
        this.notificationActor = ActorKt.actor$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new MediaParsingService$setupScope$2(this, (Continuation<? super MediaParsingService$setupScope$2>) null), 13, (Object) null);
    }

    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onBind(intent);
        this.dispatcher.onServicePreSuperOnBind();
        return this.binder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e6, code lost:
        r6 = r5.wakeLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ea, code lost:
        if (r6 != null) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ec, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f4, code lost:
        if (r6.isHeld() != false) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f6, code lost:
        r6 = r5.wakeLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f8, code lost:
        if (r6 != null) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fa, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fe, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ff, code lost:
        r4.acquire();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0102, code lost:
        return 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r6, int r7, int r8) {
        /*
            r5 = this;
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r0 == 0) goto L_0x0007
            r5.forceForeground()
        L_0x0007:
            long r0 = r5.lastNotificationTime
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x001a
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r0 == 0) goto L_0x0014
            goto L_0x0018
        L_0x0014:
            long r2 = java.lang.System.currentTimeMillis()
        L_0x0018:
            r5.lastNotificationTime = r2
        L_0x001a:
            super.onStartCommand(r6, r7, r8)
            androidx.lifecycle.ServiceLifecycleDispatcher r7 = r5.dispatcher
            r7.onServicePreSuperOnStart()
            r7 = 2
            if (r6 != 0) goto L_0x0029
            r5.exitCommand()
            return r7
        L_0x0029:
            java.lang.String r8 = r6.getAction()
            if (r8 == 0) goto L_0x0103
            int r0 = r8.hashCode()
            r1 = 1
            java.lang.String r2 = "actions"
            java.lang.String r3 = "extra_path"
            r4 = 0
            switch(r0) {
                case -1458998655: goto L_0x00c9;
                case -1123103023: goto L_0x00b6;
                case -954300331: goto L_0x009f;
                case 239562744: goto L_0x007e;
                case 717884311: goto L_0x0053;
                case 1778265988: goto L_0x003e;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x0103
        L_0x003e:
            java.lang.String r0 = "medialibrary_discover_device"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L_0x0048
            goto L_0x0103
        L_0x0048:
            java.lang.String r6 = r6.getStringExtra(r3)
            if (r6 == 0) goto L_0x00e6
            r5.discoverStorage(r6)
            goto L_0x00e6
        L_0x0053:
            java.lang.String r6 = "medialibrary_check_storages"
            boolean r6 = r8.equals(r6)
            if (r6 != 0) goto L_0x005d
            goto L_0x0103
        L_0x005d:
            android.content.SharedPreferences r6 = r5.getSettings()
            java.lang.String r8 = "ml_scan"
            r0 = -1
            int r6 = r6.getInt(r8, r0)
            if (r6 == r1) goto L_0x0079
            kotlinx.coroutines.channels.SendChannel<? super org.videolan.vlc.MLAction> r6 = r5.actions
            if (r6 != 0) goto L_0x0072
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r6 = r4
        L_0x0072:
            org.videolan.vlc.UpdateStorages r8 = org.videolan.vlc.UpdateStorages.INSTANCE
            r6.m1139trySendJP2dKIU(r8)
            goto L_0x00e6
        L_0x0079:
            r5.exitCommand()
            goto L_0x00e6
        L_0x007e:
            java.lang.String r0 = "medialibrary_init"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L_0x0088
            goto L_0x0103
        L_0x0088:
            java.lang.String r8 = "extra_upgrade"
            r0 = 0
            boolean r8 = r6.getBooleanExtra(r8, r0)
            java.lang.String r2 = "extra_parse"
            boolean r1 = r6.getBooleanExtra(r2, r1)
            java.lang.String r2 = "extra_remove_device"
            boolean r6 = r6.getBooleanExtra(r2, r0)
            r5.setupMedialibrary(r8, r1, r6)
            goto L_0x00e6
        L_0x009f:
            java.lang.String r6 = "medialibrary_force_reload"
            boolean r6 = r8.equals(r6)
            if (r6 != 0) goto L_0x00a8
            goto L_0x0103
        L_0x00a8:
            kotlinx.coroutines.channels.SendChannel<? super org.videolan.vlc.MLAction> r6 = r5.actions
            if (r6 != 0) goto L_0x00b0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r6 = r4
        L_0x00b0:
            org.videolan.vlc.ForceReload r8 = org.videolan.vlc.ForceReload.INSTANCE
            r6.m1139trySendJP2dKIU(r8)
            goto L_0x00e6
        L_0x00b6:
            java.lang.String r0 = "medialibrary_discover"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L_0x00bf
            goto L_0x0103
        L_0x00bf:
            java.lang.String r6 = r6.getStringExtra(r3)
            if (r6 == 0) goto L_0x00e6
            r5.discover(r6)
            goto L_0x00e6
        L_0x00c9:
            java.lang.String r0 = "medialibrary_reload"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L_0x00d2
            goto L_0x0103
        L_0x00d2:
            kotlinx.coroutines.channels.SendChannel<? super org.videolan.vlc.MLAction> r8 = r5.actions
            if (r8 != 0) goto L_0x00da
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r4
        L_0x00da:
            org.videolan.vlc.Reload r0 = new org.videolan.vlc.Reload
            java.lang.String r6 = r6.getStringExtra(r3)
            r0.<init>(r6)
            r8.m1139trySendJP2dKIU(r0)
        L_0x00e6:
            android.os.PowerManager$WakeLock r6 = r5.wakeLock
            java.lang.String r8 = "wakeLock"
            if (r6 != 0) goto L_0x00f0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r6 = r4
        L_0x00f0:
            boolean r6 = r6.isHeld()
            if (r6 != 0) goto L_0x0102
            android.os.PowerManager$WakeLock r6 = r5.wakeLock
            if (r6 != 0) goto L_0x00fe
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            goto L_0x00ff
        L_0x00fe:
            r4 = r6
        L_0x00ff:
            r4.acquire()
        L_0x0102:
            return r7
        L_0x0103:
            r5.exitCommand()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.onStartCommand(android.content.Intent, int, int):int");
    }

    private final void forceForeground() {
        NotificationHelper notificationHelper = NotificationHelper.INSTANCE;
        Context applicationContext = getApplicationContext();
        String string = getString(R.string.loading_medialibrary);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        try {
            ExtensionsKt.startForegroundCompat(this, 43, notificationHelper.createScanNotification(applicationContext, string, this.scanPaused, -1, -1), 1);
        } catch (Exception e) {
            if (Build.VERSION.SDK_INT >= 31 && AppUtils$$ExternalSyntheticApiModelOutline0.m$1((Object) e)) {
                Log.w("MediaParsingService", "ForegroundServiceStartNotAllowedException caught!");
            }
        }
    }

    private final void discoverStorage(String str) {
        if (str.length() == 0) {
            exitCommand();
            return;
        }
        this.discoverTriggered = true;
        SendChannel<? super MLAction> sendChannel = this.actions;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actions");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(new DiscoverStorage(str));
    }

    private final void discover(String str) {
        if (str.length() == 0) {
            exitCommand();
            return;
        }
        SendChannel<? super MLAction> sendChannel = this.actions;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actions");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(new DiscoverFolder(str));
    }

    private final void addDeviceIfNeeded(String str) {
        Medialibrary medialibrary2 = this.medialibrary;
        Medialibrary medialibrary3 = null;
        if (medialibrary2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            medialibrary2 = null;
        }
        String[] devices = medialibrary2.getDevices();
        Intrinsics.checkNotNullExpressionValue(devices, "getDevices(...)");
        int length = devices.length;
        int i = 0;
        while (i < length) {
            String str2 = devices[i];
            Intrinsics.checkNotNull(str2);
            if (!StringsKt.startsWith$default(str, Strings.removeFileScheme(str2), false, 2, (Object) null)) {
                i++;
            } else {
                return;
            }
        }
        if (StringsKt.startsWith$default(Strings.removeFileScheme(str), AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null)) {
            Medialibrary medialibrary4 = this.medialibrary;
            if (medialibrary4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            } else {
                medialibrary3 = medialibrary4;
            }
            medialibrary3.addDevice("main-storage", str, false);
        } else if (!AndroidDevices.INSTANCE.getExternalStorageDirectories().isEmpty()) {
            for (String startsWith$default : AndroidDevices.INSTANCE.getExternalStorageDirectories()) {
                if (StringsKt.startsWith$default(str, startsWith$default, false, 2, (Object) null)) {
                    String fileNameFromPath = FileUtils.INSTANCE.getFileNameFromPath(str);
                    if (fileNameFromPath.length() == 0) {
                        exitCommand();
                        return;
                    }
                    Medialibrary medialibrary5 = this.medialibrary;
                    if (medialibrary5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                        medialibrary5 = null;
                    }
                    medialibrary5.addDevice(fileNameFromPath, str, true);
                    String[] banList = Medialibrary.getBanList();
                    Intrinsics.checkNotNullExpressionValue(banList, "getBanList(...)");
                    for (String str3 : banList) {
                        Medialibrary medialibrary6 = this.medialibrary;
                        if (medialibrary6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                            medialibrary6 = null;
                        }
                        medialibrary6.banFolder(str + str3);
                    }
                }
            }
        } else {
            String fileNameFromPath2 = FileUtils.INSTANCE.getFileNameFromPath(str);
            Medialibrary medialibrary7 = this.medialibrary;
            if (medialibrary7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            } else {
                medialibrary3 = medialibrary7;
            }
            medialibrary3.addDevice(fileNameFromPath2, str, false);
        }
    }

    private final void reload(String str) {
        if (this.reload <= 0) {
            CharSequence charSequence = str;
            if (charSequence == null || charSequence.length() == 0) {
                Medialibrary medialibrary2 = this.medialibrary;
                if (medialibrary2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    medialibrary2 = null;
                }
                medialibrary2.reload();
            } else {
                Medialibrary medialibrary3 = this.medialibrary;
                if (medialibrary3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    medialibrary3 = null;
                }
                medialibrary3.reload(str);
            }
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new MediaParsingService$reload$1(this, (Continuation<? super MediaParsingService$reload$1>) null), 2, (Object) null);
        }
    }

    private final void setupMedialibrary(boolean z, boolean z2, boolean z3) {
        Medialibrary medialibrary2 = this.medialibrary;
        SendChannel<? super MLAction> sendChannel = null;
        if (medialibrary2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            medialibrary2 = null;
        }
        if (medialibrary2.isInitiated()) {
            Medialibrary medialibrary3 = this.medialibrary;
            if (medialibrary3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                medialibrary3 = null;
            }
            medialibrary3.resumeBackgroundOperations();
            if (z2 && !this.scanActivated) {
                SendChannel<? super MLAction> sendChannel2 = this.actions;
                if (sendChannel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actions");
                } else {
                    sendChannel = sendChannel2;
                }
                sendChannel.m1139trySendJP2dKIU(new StartScan(z));
                return;
            }
            return;
        }
        SendChannel<? super MLAction> sendChannel3 = this.actions;
        if (sendChannel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actions");
        } else {
            sendChannel = sendChannel3;
        }
        sendChannel.m1139trySendJP2dKIU(new Init(z, z2, z3));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object initMedialib(boolean r5, android.content.Context r6, boolean r7, boolean r8, boolean r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r4 = this;
            boolean r0 = r10 instanceof org.videolan.vlc.MediaParsingService$initMedialib$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            org.videolan.vlc.MediaParsingService$initMedialib$1 r0 = (org.videolan.vlc.MediaParsingService$initMedialib$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.MediaParsingService$initMedialib$1 r0 = new org.videolan.vlc.MediaParsingService$initMedialib$1
            r0.<init>(r4, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            boolean r8 = r0.Z$2
            boolean r7 = r0.Z$1
            boolean r5 = r0.Z$0
            java.lang.Object r6 = r0.L$0
            org.videolan.vlc.MediaParsingService r6 = (org.videolan.vlc.MediaParsingService) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0051
        L_0x0034:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r10)
            r0.L$0 = r4
            r0.Z$0 = r5
            r0.Z$1 = r7
            r0.Z$2 = r8
            r0.label = r3
            java.lang.Object r6 = r4.checkNewDevicesForDialog(r6, r5, r9, r0)
            if (r6 != r1) goto L_0x0050
            return r1
        L_0x0050:
            r6 = r4
        L_0x0051:
            r9 = 0
            java.lang.String r10 = "medialibrary"
            if (r8 == 0) goto L_0x0061
            org.videolan.medialibrary.interfaces.Medialibrary r0 = r6.medialibrary
            if (r0 != 0) goto L_0x005e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r0 = r9
        L_0x005e:
            r0.forceParserRetry()
        L_0x0061:
            org.videolan.medialibrary.interfaces.Medialibrary r0 = r6.medialibrary
            if (r0 != 0) goto L_0x0069
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            goto L_0x006a
        L_0x0069:
            r9 = r0
        L_0x006a:
            r9.start()
            if (r5 == 0) goto L_0x0073
            r6.startScan(r7, r8)
            goto L_0x0076
        L_0x0073:
            r6.exitCommand()
        L_0x0076:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.initMedialib(boolean, android.content.Context, boolean, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00de A[SYNTHETIC] */
    public final java.lang.Object addDevices(android.content.Context r9, boolean r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof org.videolan.vlc.MediaParsingService$addDevices$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.MediaParsingService$addDevices$1 r0 = (org.videolan.vlc.MediaParsingService$addDevices$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.MediaParsingService$addDevices$1 r0 = new org.videolan.vlc.MediaParsingService$addDevices$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            java.lang.String r4 = "medialibrary"
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r6) goto L_0x004b
            if (r2 != r5) goto L_0x0043
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$3
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r2 = r0.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r6 = r0.L$1
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r0.L$0
            org.videolan.vlc.MediaParsingService r7 = (org.videolan.vlc.MediaParsingService) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c3
        L_0x0043:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x004b:
            java.lang.Object r9 = r0.L$0
            org.videolan.vlc.MediaParsingService r9 = (org.videolan.vlc.MediaParsingService) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0077
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r10 == 0) goto L_0x0063
            org.videolan.medialibrary.interfaces.Medialibrary r10 = r8.medialibrary
            if (r10 != 0) goto L_0x0060
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r10 = r3
        L_0x0060:
            r10.deleteRemovableDevices()
        L_0x0063:
            org.videolan.vlc.repository.DirectoryRepository$Companion r10 = org.videolan.vlc.repository.DirectoryRepository.Companion
            java.lang.Object r9 = r10.getInstance(r9)
            org.videolan.vlc.repository.DirectoryRepository r9 = (org.videolan.vlc.repository.DirectoryRepository) r9
            r0.L$0 = r8
            r0.label = r6
            java.lang.Object r11 = r9.getMediaDirectories(r0)
            if (r11 != r1) goto L_0x0076
            return r1
        L_0x0076:
            r9 = r8
        L_0x0077:
            java.util.List r11 = (java.util.List) r11
            java.util.Iterator r10 = r11.iterator()
            r7 = r9
            r6 = r10
        L_0x007f:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x00de
            java.lang.Object r9 = r6.next()
            r2 = r9
            java.lang.String r2 = (java.lang.String) r2
            org.videolan.resources.AndroidDevices r9 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r9 = r9.getEXTERNAL_PUBLIC_DIRECTORY()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r9)
            org.videolan.vlc.util.FileUtils r10 = org.videolan.vlc.util.FileUtils.INSTANCE
            java.lang.String r10 = r10.getFileNameFromPath(r2)
            r11 = r2
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            int r11 = r11.length()
            if (r11 != 0) goto L_0x00a6
            goto L_0x007f
        L_0x00a6:
            r11 = r10
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            int r11 = r11.length()
            if (r11 != 0) goto L_0x00b0
            goto L_0x007f
        L_0x00b0:
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r2
            r0.L$3 = r10
            r0.Z$0 = r9
            r0.label = r5
            java.lang.Object r11 = org.videolan.vlc.util.KextensionsKt.scanAllowed(r2, r0)
            if (r11 != r1) goto L_0x00c3
            return r1
        L_0x00c3:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L_0x00cc
            goto L_0x007f
        L_0x00cc:
            org.videolan.medialibrary.interfaces.Medialibrary r11 = r7.medialibrary
            if (r11 != 0) goto L_0x00d4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r11 = r3
        L_0x00d4:
            if (r9 == 0) goto L_0x00d8
            java.lang.String r10 = "main-storage"
        L_0x00d8:
            r9 = r9 ^ 1
            r11.addDevice(r10, r2, r9)
            goto L_0x007f
        L_0x00de:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.addDevices(android.content.Context, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0115 A[SYNTHETIC] */
    public final java.lang.Object checkNewDevicesForDialog(android.content.Context r10, boolean r11, boolean r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof org.videolan.vlc.MediaParsingService$checkNewDevicesForDialog$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            org.videolan.vlc.MediaParsingService$checkNewDevicesForDialog$1 r0 = (org.videolan.vlc.MediaParsingService$checkNewDevicesForDialog$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.MediaParsingService$checkNewDevicesForDialog$1 r0 = new org.videolan.vlc.MediaParsingService$checkNewDevicesForDialog$1
            r0.<init>(r9, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "medialibrary"
            r5 = 0
            r6 = 1
            if (r2 == 0) goto L_0x0059
            if (r2 == r6) goto L_0x004f
            if (r2 != r3) goto L_0x0047
            boolean r10 = r0.Z$0
            java.lang.Object r11 = r0.L$4
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r0.L$3
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r2 = r0.L$2
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r7 = r0.L$1
            java.lang.String[] r7 = (java.lang.String[]) r7
            java.lang.Object r8 = r0.L$0
            org.videolan.vlc.MediaParsingService r8 = (org.videolan.vlc.MediaParsingService) r8
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00e7
        L_0x0047:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004f:
            boolean r11 = r0.Z$0
            java.lang.Object r10 = r0.L$0
            org.videolan.vlc.MediaParsingService r10 = (org.videolan.vlc.MediaParsingService) r10
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x007f
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r13)
            if (r12 == 0) goto L_0x0069
            org.videolan.medialibrary.interfaces.Medialibrary r12 = r9.medialibrary
            if (r12 != 0) goto L_0x0066
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r12 = r5
        L_0x0066:
            r12.deleteRemovableDevices()
        L_0x0069:
            org.videolan.vlc.repository.DirectoryRepository$Companion r12 = org.videolan.vlc.repository.DirectoryRepository.Companion
            java.lang.Object r10 = r12.getInstance(r10)
            org.videolan.vlc.repository.DirectoryRepository r10 = (org.videolan.vlc.repository.DirectoryRepository) r10
            r0.L$0 = r9
            r0.Z$0 = r11
            r0.label = r6
            java.lang.Object r13 = r10.getMediaDirectories(r0)
            if (r13 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r10 = r9
        L_0x007f:
            java.util.List r13 = (java.util.List) r13
            org.videolan.resources.AndroidDevices r12 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r12 = r12.getWatchDevices()
            if (r12 == 0) goto L_0x0096
            org.videolan.medialibrary.interfaces.Medialibrary r12 = r10.medialibrary
            if (r12 != 0) goto L_0x0091
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r12 = r5
        L_0x0091:
            java.lang.String[] r12 = r12.getDevices()
            goto L_0x0097
        L_0x0096:
            r12 = r5
        L_0x0097:
            java.util.Iterator r13 = r13.iterator()
            r8 = r10
            r10 = r11
            r7 = r12
            r2 = r13
        L_0x009f:
            boolean r11 = r2.hasNext()
            if (r11 == 0) goto L_0x0115
            java.lang.Object r11 = r2.next()
            r12 = r11
            java.lang.String r12 = (java.lang.String) r12
            org.videolan.resources.AndroidDevices r11 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r11 = r11.getEXTERNAL_PUBLIC_DIRECTORY()
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r11)
            if (r11 != 0) goto L_0x009f
            org.videolan.vlc.util.FileUtils r11 = org.videolan.vlc.util.FileUtils.INSTANCE
            java.lang.String r11 = r11.getFileNameFromPath(r12)
            r13 = r12
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            int r13 = r13.length()
            if (r13 != 0) goto L_0x00c8
            goto L_0x009f
        L_0x00c8:
            r13 = r11
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            int r13 = r13.length()
            if (r13 != 0) goto L_0x00d2
            goto L_0x009f
        L_0x00d2:
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r12
            r0.L$4 = r11
            r0.Z$0 = r10
            r0.label = r3
            java.lang.Object r13 = org.videolan.vlc.util.KextensionsKt.scanAllowed(r12, r0)
            if (r13 != r1) goto L_0x00e7
            return r1
        L_0x00e7:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L_0x00f0
            goto L_0x009f
        L_0x00f0:
            if (r10 == 0) goto L_0x009f
            if (r7 == 0) goto L_0x00fb
            boolean r13 = kotlin.collections.ArraysKt.contains((T[]) r7, r12)
            if (r13 != r6) goto L_0x00fb
            goto L_0x009f
        L_0x00fb:
            org.videolan.medialibrary.interfaces.Medialibrary r13 = r8.medialibrary
            if (r13 != 0) goto L_0x0103
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r13 = r5
        L_0x0103:
            boolean r11 = r13.isDeviceKnown(r11, r12, r6)
            if (r11 != 0) goto L_0x009f
            java.util.List<java.lang.String> r11 = preselectedStorages
            boolean r11 = r11.isEmpty()
            if (r11 == 0) goto L_0x009f
            r8.showStorageNotification(r12)
            goto L_0x009f
        L_0x0115:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.checkNewDevicesForDialog(android.content.Context, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void startScan(boolean z, boolean z2) {
        this.scanActivated = true;
        Medialibrary medialibrary2 = null;
        if (MLServiceLocator.getLocatorMode() == MLServiceLocator.LocatorMode.TESTS) {
            Medialibrary medialibrary3 = this.medialibrary;
            if (medialibrary3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                medialibrary3 = null;
            }
            ((StubMedialibrary) medialibrary3).loadJsonData(Util.INSTANCE.readAsset("basic_stub.json", ""));
        }
        if (z) {
            String[] banList = Medialibrary.getBanList();
            Intrinsics.checkNotNullExpressionValue(banList, "getBanList(...)");
            for (String str : banList) {
                Medialibrary medialibrary4 = this.medialibrary;
                if (medialibrary4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    medialibrary4 = null;
                }
                medialibrary4.banFolder(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + str);
            }
            List<String> list = preselectedStorages;
            if (list.isEmpty()) {
                Medialibrary medialibrary5 = this.medialibrary;
                if (medialibrary5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                } else {
                    medialibrary2 = medialibrary5;
                }
                medialibrary2.discover(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY());
                return;
            }
            for (String next : list) {
                Medialibrary medialibrary6 = this.medialibrary;
                if (medialibrary6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    medialibrary6 = null;
                }
                medialibrary6.discover(next);
            }
            preselectedStorages.clear();
        } else if (z2) {
            exitCommand();
        } else if (getSettings().getBoolean(SettingsKt.KEY_MEDIALIBRARY_AUTO_RESCAN, true)) {
            reload((String) null);
        } else {
            exitCommand();
        }
    }

    private final void showStorageNotification(String str) {
        MutableLiveData<List<String>> mutableLiveData = newStorages;
        List value = mutableLiveData.getValue();
        if (value == null) {
            value = CollectionsKt.mutableListOf(str);
        } else {
            value.add(str);
        }
        mutableLiveData.postValue(value);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x010b, code lost:
        r4.L$0 = r2;
        r4.L$1 = r1;
        r4.L$2 = r11;
        r4.L$3 = r9;
        r4.L$4 = r12;
        r4.L$5 = r13;
        r4.label = 2;
        r14 = org.videolan.vlc.util.KextensionsKt.scanAllowed(r12, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x011d, code lost:
        if (r14 != r3) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x011f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0120, code lost:
        r16 = r13;
        r13 = r1;
        r1 = r14;
        r14 = r11;
        r11 = r4;
        r4 = r12;
        r12 = r9;
        r9 = r16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01a4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0183 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateStorages(kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            boolean r2 = r1 instanceof org.videolan.vlc.MediaParsingService$updateStorages$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            org.videolan.vlc.MediaParsingService$updateStorages$1 r2 = (org.videolan.vlc.MediaParsingService$updateStorages$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            org.videolan.vlc.MediaParsingService$updateStorages$1 r2 = new org.videolan.vlc.MediaParsingService$updateStorages$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            java.lang.String r5 = "file://"
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            r10 = 0
            if (r4 == 0) goto L_0x0094
            if (r4 == r9) goto L_0x008c
            if (r4 == r8) goto L_0x0063
            if (r4 == r7) goto L_0x0047
            if (r4 != r6) goto L_0x003f
            java.lang.Object r2 = r2.L$0
            org.videolan.vlc.MediaParsingService r2 = (org.videolan.vlc.MediaParsingService) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x01a5
        L_0x003f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0047:
            java.lang.Object r4 = r2.L$4
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r9 = r2.L$3
            java.util.Iterator r9 = (java.util.Iterator) r9
            java.lang.Object r11 = r2.L$2
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r12 = r2.L$1
            java.lang.String[] r12 = (java.lang.String[]) r12
            java.lang.Object r13 = r2.L$0
            org.videolan.vlc.MediaParsingService r13 = (org.videolan.vlc.MediaParsingService) r13
            kotlin.ResultKt.throwOnFailure(r1)
            r14 = r11
            r11 = r2
            r2 = r13
            goto L_0x0173
        L_0x0063:
            java.lang.Object r4 = r2.L$5
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r9 = r2.L$4
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r11 = r2.L$3
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r12 = r2.L$2
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r2.L$1
            java.lang.String[] r13 = (java.lang.String[]) r13
            java.lang.Object r14 = r2.L$0
            org.videolan.vlc.MediaParsingService r14 = (org.videolan.vlc.MediaParsingService) r14
            kotlin.ResultKt.throwOnFailure(r1)
            r16 = r11
            r11 = r2
            r2 = r14
            r14 = r12
            r12 = r16
            r17 = r9
            r9 = r4
            r4 = r17
            goto L_0x012a
        L_0x008c:
            java.lang.Object r4 = r2.L$0
            org.videolan.vlc.MediaParsingService r4 = (org.videolan.vlc.MediaParsingService) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00b2
        L_0x0094:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.serviceLock = r9
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.MediaParsingService$updateStorages$2 r4 = new org.videolan.vlc.MediaParsingService$updateStorages$2
            r4.<init>(r0, r10)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r2.L$0 = r0
            r2.label = r9
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r4, r2)
            if (r1 != r3) goto L_0x00b1
            return r3
        L_0x00b1:
            r4 = r0
        L_0x00b2:
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r9 = r1.component1()
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r1 = r1.component2()
            java.lang.String[] r1 = (java.lang.String[]) r1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.util.List r11 = kotlin.collections.ArraysKt.toMutableList((T[]) r1)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r5)
            org.videolan.resources.AndroidDevices r13 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r13 = r13.getEXTERNAL_PUBLIC_DIRECTORY()
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.remove(r12)
            java.util.Iterator r9 = r9.iterator()
            r16 = r4
            r4 = r2
            r2 = r16
        L_0x00e5:
            boolean r12 = r9.hasNext()
            if (r12 == 0) goto L_0x0183
            java.lang.Object r12 = r9.next()
            java.lang.String r12 = (java.lang.String) r12
            org.videolan.vlc.util.FileUtils r13 = org.videolan.vlc.util.FileUtils.INSTANCE
            java.lang.String r13 = r13.getFileNameFromPath(r12)
            r14 = r12
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            int r14 = r14.length()
            if (r14 != 0) goto L_0x0101
            goto L_0x00e5
        L_0x0101:
            r14 = r13
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            int r14 = r14.length()
            if (r14 != 0) goto L_0x010b
            goto L_0x00e5
        L_0x010b:
            r4.L$0 = r2
            r4.L$1 = r1
            r4.L$2 = r11
            r4.L$3 = r9
            r4.L$4 = r12
            r4.L$5 = r13
            r4.label = r8
            java.lang.Object r14 = org.videolan.vlc.util.KextensionsKt.scanAllowed(r12, r4)
            if (r14 != r3) goto L_0x0120
            return r3
        L_0x0120:
            r16 = r13
            r13 = r1
            r1 = r14
            r14 = r11
            r11 = r4
            r4 = r12
            r12 = r9
            r9 = r16
        L_0x012a:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0133
            goto L_0x014b
        L_0x0133:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            boolean r1 = org.videolan.vlc.ExternalMonitorKt.containsDevice(r13, r4)
            if (r1 == 0) goto L_0x014f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r14.remove(r1)
        L_0x014b:
            r4 = r11
            r9 = r12
            r1 = r13
            goto L_0x0180
        L_0x014f:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.MediaParsingService$updateStorages$isNew$1 r15 = new org.videolan.vlc.MediaParsingService$updateStorages$isNew$1
            r15.<init>(r2, r9, r4, r10)
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r11.L$0 = r2
            r11.L$1 = r13
            r11.L$2 = r14
            r11.L$3 = r12
            r11.L$4 = r4
            r11.L$5 = r10
            r11.label = r7
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r15, r11)
            if (r1 != r3) goto L_0x0171
            return r3
        L_0x0171:
            r9 = r12
            r12 = r13
        L_0x0173:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x017e
            r2.showStorageNotification(r4)
        L_0x017e:
            r4 = r11
            r1 = r12
        L_0x0180:
            r11 = r14
            goto L_0x00e5
        L_0x0183:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.MediaParsingService$updateStorages$3 r5 = new org.videolan.vlc.MediaParsingService$updateStorages$3
            r5.<init>(r11, r2, r10)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r4.L$0 = r2
            r4.L$1 = r10
            r4.L$2 = r10
            r4.L$3 = r10
            r4.L$4 = r10
            r4.L$5 = r10
            r4.label = r6
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r5, r4)
            if (r1 != r3) goto L_0x01a5
            return r3
        L_0x01a5:
            r1 = 0
            r2.serviceLock = r1
            r2.exitCommand()
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.updateStorages(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object showNotification(int r13, int r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof org.videolan.vlc.MediaParsingService$showNotification$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            org.videolan.vlc.MediaParsingService$showNotification$1 r0 = (org.videolan.vlc.MediaParsingService$showNotification$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.MediaParsingService$showNotification$1 r0 = new org.videolan.vlc.MediaParsingService$showNotification$1
            r0.<init>(r12, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            float r13 = r0.F$0
            java.lang.Object r14 = r0.L$0
            org.videolan.vlc.MediaParsingService r14 = (org.videolan.vlc.MediaParsingService) r14
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x006c
        L_0x0030:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r15)
            long r4 = java.lang.System.currentTimeMillis()
            r12.lastNotificationTime = r4
            float r15 = (float) r13
            float r2 = (float) r14
            float r15 = r15 / r2
            r2 = 1120403456(0x42c80000, float:100.0)
            float r15 = r15 * r2
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.MediaParsingService$showNotification$discovery$1 r10 = new org.videolan.vlc.MediaParsingService$showNotification$discovery$1
            r9 = 0
            r4 = r10
            r5 = r12
            r6 = r15
            r7 = r13
            r8 = r14
            r4.<init>(r5, r6, r7, r8, r9)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r0.L$0 = r12
            r0.F$0 = r15
            r0.label = r3
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r2, r10, r0)
            if (r13 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r14 = r12
            r11 = r15
            r15 = r13
            r13 = r11
        L_0x006c:
            java.lang.String r15 = (java.lang.String) r15
            r14.showProgress(r13, r15)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.showNotification(int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void hideNotification() {
        this.lastNotificationTime = -1;
        ExtensionsKt.stopForegroundCompat$default(this, false, 1, (Object) null);
        showProgress(-1.0f, "");
    }

    public void onDiscoveryStarted() {
        this.discoverTriggered = false;
        this.inDiscovery = true;
    }

    public void onDiscoveryProgress(String str) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
        this.currentDiscovery = str;
        SendChannel<? super Notification> sendChannel = this.notificationActor;
        if (sendChannel != null) {
            if (sendChannel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationActor");
                sendChannel = null;
            }
            sendChannel.m1139trySendJP2dKIU(new Show(-1, -1));
        }
    }

    public void onDiscoveryCompleted() {
        this.inDiscovery = false;
    }

    public void onDiscoveryFailed(String str) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
        Log.e("VLC/MediaParsingService", "onDiscoveryFailed");
        SendChannel<? super Notification> sendChannel = this.notificationActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(new Error(str));
    }

    public void onParsingStatsUpdated(int i, int i2) {
        SendChannel<? super Notification> sendChannel;
        SendChannel<? super Notification> sendChannel2;
        this.lastDone = i;
        this.lastScheduled = i2;
        boolean z = i == i2;
        SendChannel<? super Notification> sendChannel3 = null;
        if (!z && (sendChannel2 = this.notificationActor) != null) {
            if (sendChannel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationActor");
            } else {
                sendChannel3 = sendChannel2;
            }
            sendChannel3.m1139trySendJP2dKIU(new Show(i, i2));
        } else if (z && (sendChannel = this.notificationActor) != null) {
            if (sendChannel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationActor");
            } else {
                sendChannel3 = sendChannel;
            }
            sendChannel3.m1139trySendJP2dKIU(Hide.INSTANCE);
        }
    }

    public void onReloadStarted(String str) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
        if (str.length() == 0) {
            this.reload++;
        }
    }

    public void onReloadCompleted(String str) {
        Intrinsics.checkNotNullParameter(str, "entryPoint");
        if (str.length() == 0) {
            this.reload--;
        }
        if (this.reload <= 0) {
            exitCommand();
        }
    }

    /* access modifiers changed from: private */
    public final void exitCommand() {
        Medialibrary medialibrary2 = this.medialibrary;
        if (medialibrary2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            medialibrary2 = null;
        }
        if (!medialibrary2.isWorking() && !this.serviceLock && !this.discoverTriggered) {
            this.lastNotificationTime = 0;
            PowerManager.WakeLock wakeLock2 = this.wakeLock;
            if (wakeLock2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                wakeLock2 = null;
            }
            if (wakeLock2.isHeld()) {
                try {
                    PowerManager.WakeLock wakeLock3 = this.wakeLock;
                    if (wakeLock3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                        wakeLock3 = null;
                    }
                    wakeLock3.release();
                } catch (Throwable unused) {
                }
            }
            KotlinExtensionsKt.getLocalBroadcastManager(this).sendBroadcast(new Intent(Constants.ACTION_CONTENT_INDEXING));
            SendChannel<? super Notification> sendChannel = this.notificationActor;
            if (sendChannel != null) {
                if (sendChannel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("notificationActor");
                    sendChannel = null;
                }
                sendChannel.m1139trySendJP2dKIU(Hide.INSTANCE);
            }
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new MediaParsingService$exitCommand$1(this, (Continuation<? super MediaParsingService$exitCommand$1>) null), 3, (Object) null);
        }
    }

    public void onDestroy() {
        this.dispatcher.onServicePreSuperOnDestroy();
        Medialibrary medialibrary2 = this.medialibrary;
        if (medialibrary2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            medialibrary2 = null;
        }
        medialibrary2.removeDeviceDiscoveryCb(this);
        unregisterReceiver(this.receiver);
        Medialibrary medialibrary3 = this.medialibrary;
        if (medialibrary3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            medialibrary3 = null;
        }
        medialibrary3.setExceptionHandler((Medialibrary.MedialibraryExceptionHandler) null);
        super.onDestroy();
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/videolan/vlc/MediaParsingService$LocalBinder;", "Landroid/os/Binder;", "(Lorg/videolan/vlc/MediaParsingService;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaParsingService.kt */
    private final class LocalBinder extends Binder {
        public LocalBinder() {
        }
    }

    private final void showProgress(float f, String str) {
        if (f == -1.0f) {
            progress.setValue(null);
            return;
        }
        MutableLiveData<ScanProgress> mutableLiveData = progress;
        ScanProgress value = mutableLiveData.getValue();
        mutableLiveData.setValue(value == null ? new ScanProgress(f, str, this.inDiscovery) : value.copy(f, str, this.inDiscovery));
    }

    /* JADX WARNING: type inference failed for: r4v17, types: [int] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x024b, code lost:
        if ((r0 instanceof org.videolan.vlc.StartScan) == false) goto L_0x0271;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x024d, code lost:
        r12.scanActivated = true;
        r1.L$0 = r12;
        r1.L$1 = r4;
        r1.L$2 = r0;
        r1.label = 5;
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0261, code lost:
        if (r12.addDevices(r12, false, r1) != r3) goto L_0x0264;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0263, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0264, code lost:
        r7 = r0;
        r0 = r4;
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0267, code lost:
        r4.startScan(r13, ((org.videolan.vlc.StartScan) r7).getUpgrade());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x027a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (java.lang.Object) org.videolan.vlc.UpdateStorages.INSTANCE) == false) goto L_0x028a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x027c, code lost:
        r1.L$0 = r12;
        r1.L$1 = r4;
        r1.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0287, code lost:
        if (r12.updateStorages(r1) != r3) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0289, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x028c, code lost:
        if ((r0 instanceof org.videolan.vlc.Reload) == false) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x028e, code lost:
        r12.reload(((org.videolan.vlc.Reload) r0).getPath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x029e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (java.lang.Object) org.videolan.vlc.ForceReload.INSTANCE) == false) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02a0, code lost:
        r0 = r12.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02a2, code lost:
        if (r0 != null) goto L_0x02a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02a4, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02a8, code lost:
        r0.forceRescan();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02ab, code lost:
        r0 = r4;
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02b0, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c0, code lost:
        r1.L$0 = r4;
        r1.L$1 = r0;
        r1.L$2 = null;
        r1.L$3 = null;
        r1.label = r10;
        r12 = r0.hasNext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ce, code lost:
        if (r12 != r3) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00d0, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d1, code lost:
        r24 = r4;
        r4 = r0;
        r0 = r12;
        r12 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00dd, code lost:
        if (((java.lang.Boolean) r0).booleanValue() == false) goto L_0x02ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00df, code lost:
        r0 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e7, code lost:
        if ((r0 instanceof org.videolan.vlc.DiscoverStorage) == false) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e9, code lost:
        r13 = org.videolan.medialibrary.interfaces.Medialibrary.getBanList();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, "getBanList(...)");
        r14 = r13.length;
        r15 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f4, code lost:
        if (r15 >= r14) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00f6, code lost:
        r8 = r13[r15];
        r7 = r12.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00fa, code lost:
        if (r7 != null) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00fc, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0100, code lost:
        r7.banFolder(((org.videolan.vlc.DiscoverStorage) r0).getPath() + r8);
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x011e, code lost:
        r6 = r12.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0120, code lost:
        if (r6 != null) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0122, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0126, code lost:
        r6.discover(((org.videolan.vlc.DiscoverStorage) r0).getPath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0132, code lost:
        if ((r0 instanceof org.videolan.vlc.DiscoverFolder) == false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0134, code lost:
        r0 = (org.videolan.vlc.DiscoverFolder) r0;
        r12.addDeviceIfNeeded(r0.getPath());
        r6 = r12.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x013f, code lost:
        if (r6 != null) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0141, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0145, code lost:
        r6.discover(r0.getPath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0154, code lost:
        if ((r0 instanceof org.videolan.vlc.Init) == false) goto L_0x0248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0156, code lost:
        r6 = r12.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0158, code lost:
        if (r6 != null) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x015a, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0162, code lost:
        if (r6.isInitiated() == false) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0164, code lost:
        r12.exitCommand();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0168, code lost:
        r1.L$0 = r12;
        r1.L$1 = r4;
        r1.L$2 = r0;
        r1.L$3 = r12;
        r1.label = 2;
        r7 = true;
        r6 = org.videolan.resources.util.ExtensionsKt.dbExists$default(r12, (org.videolan.tools.CoroutineContextProvider) null, r1, 1, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x017a, code lost:
        if (r6 != r3) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x017c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x017d, code lost:
        r13 = r0;
        r0 = r6;
        r14 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0180, code lost:
        r0 = ((java.lang.Boolean) r0).booleanValue() ^ r7;
        r6 = r14.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0189, code lost:
        if (r6 != null) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x018b, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x018f, code lost:
        r7 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0196, code lost:
        if (r6.construct(r7) != false) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0198, code lost:
        r14.exitCommand();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x019d, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x019e, code lost:
        r6 = ((org.videolan.vlc.Init) r13).getParse();
        r1.L$0 = r14;
        r1.L$1 = r4;
        r1.L$2 = r13;
        r1.L$3 = r12;
        r1.I$0 = r0;
        r1.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01b6, code lost:
        if (r14.addDevices(r7, r6, r1) != r3) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01b8, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01b9, code lost:
        r6 = r0;
        r0 = r4;
        r4 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01bc, code lost:
        r7 = r4.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01be, code lost:
        if (r7 != null) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01c0, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01c4, code lost:
        r8 = r12;
        r7 = r7.init(r8);
        r10 = r4.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01cd, code lost:
        if (r10 != null) goto L_0x01d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01cf, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01d3, code lost:
        r12 = org.videolan.resources.VLCInstance.INSTANCE.getInstance(r12);
        kotlin.jvm.internal.Intrinsics.checkNotNull(r12, "null cannot be cast to non-null type org.videolan.libvlc.LibVLC");
        r10.setLibVLCInstance(((org.videolan.libvlc.LibVLC) r12).getInstance());
        r10 = r4.medialibrary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01e9, code lost:
        if (r10 != null) goto L_0x01ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01eb, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01ef, code lost:
        r10.setDiscoverNetworkEnabled(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01f4, code lost:
        if (r7 == 5) goto L_0x0240;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01f6, code lost:
        if (r7 == 1) goto L_0x023b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01f9, code lost:
        if (r7 != 3) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01fb, code lost:
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01fd, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01fe, code lost:
        r6 = r6 | r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0200, code lost:
        if (r7 != 4) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0202, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0204, code lost:
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0205, code lost:
        r6 = r6 | r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0206, code lost:
        if (r7 == 2) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0208, code lost:
        r13 = (org.videolan.vlc.Init) r13;
        r18 = r13.getParse();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x020e, code lost:
        if (r6 == false) goto L_0x0213;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0210, code lost:
        r20 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0213, code lost:
        r20 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0215, code lost:
        r21 = r13.getUpgrade();
        r22 = r13.getRemoveDevices();
        r1.L$0 = r4;
        r1.L$1 = r0;
        r1.L$2 = null;
        r1.L$3 = null;
        r1.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0231, code lost:
        if (r4.initMedialib(r18, r8, r20, r21, r22, r1) != r3) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0233, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0234, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0237, code lost:
        r4.exitCommand();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x023b, code lost:
        r4.exitCommand();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0247, code lost:
        throw new java.lang.IllegalStateException("Medialibrary DB file is corrupted and unrecoverable");
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object processAction(kotlinx.coroutines.channels.ActorScope<org.videolan.vlc.MLAction> r26, kotlin.coroutines.Continuation<? super kotlin.Unit> r27) {
        /*
            r25 = this;
            r0 = r27
            boolean r1 = r0 instanceof org.videolan.vlc.MediaParsingService$processAction$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            org.videolan.vlc.MediaParsingService$processAction$1 r1 = (org.videolan.vlc.MediaParsingService$processAction$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r25
            goto L_0x001f
        L_0x0018:
            org.videolan.vlc.MediaParsingService$processAction$1 r1 = new org.videolan.vlc.MediaParsingService$processAction$1
            r2 = r25
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 2
            java.lang.String r9 = "medialibrary"
            r10 = 1
            r11 = 0
            switch(r4) {
                case 0: goto L_0x00b4;
                case 1: goto L_0x00a8;
                case 2: goto L_0x008c;
                case 3: goto L_0x0072;
                case 4: goto L_0x0062;
                case 5: goto L_0x004a;
                case 6: goto L_0x0037;
                default: goto L_0x002f;
            }
        L_0x002f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0037:
            java.lang.Object r4 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r12 = r1.L$0
            org.videolan.vlc.MediaParsingService r12 = (org.videolan.vlc.MediaParsingService) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r4
            r4 = r12
            r6 = 1
            r8 = 5
            r10 = 3
            r13 = 0
            goto L_0x0234
        L_0x004a:
            java.lang.Object r4 = r1.L$2
            org.videolan.vlc.MLAction r4 = (org.videolan.vlc.MLAction) r4
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r1.L$0
            org.videolan.vlc.MediaParsingService r13 = (org.videolan.vlc.MediaParsingService) r13
            kotlin.ResultKt.throwOnFailure(r0)
            r7 = r4
            r0 = r12
            r4 = r13
            r6 = 1
            r8 = 5
            r10 = 3
            r13 = 0
            goto L_0x0267
        L_0x0062:
            java.lang.Object r4 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r12 = r1.L$0
            org.videolan.vlc.MediaParsingService r12 = (org.videolan.vlc.MediaParsingService) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r4
            r4 = r12
            r10 = 3
            goto L_0x0234
        L_0x0072:
            int r4 = r1.I$0
            java.lang.Object r12 = r1.L$3
            org.videolan.vlc.MediaParsingService r12 = (org.videolan.vlc.MediaParsingService) r12
            java.lang.Object r13 = r1.L$2
            org.videolan.vlc.MLAction r13 = (org.videolan.vlc.MLAction) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r14 = (kotlinx.coroutines.channels.ChannelIterator) r14
            java.lang.Object r15 = r1.L$0
            org.videolan.vlc.MediaParsingService r15 = (org.videolan.vlc.MediaParsingService) r15
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r4
            r0 = r14
            r4 = r15
            goto L_0x01bc
        L_0x008c:
            java.lang.Object r4 = r1.L$3
            org.videolan.vlc.MediaParsingService r4 = (org.videolan.vlc.MediaParsingService) r4
            java.lang.Object r12 = r1.L$2
            org.videolan.vlc.MLAction r12 = (org.videolan.vlc.MLAction) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r1.L$0
            org.videolan.vlc.MediaParsingService r14 = (org.videolan.vlc.MediaParsingService) r14
            kotlin.ResultKt.throwOnFailure(r0)
            r7 = 1
            r24 = r12
            r12 = r4
            r4 = r13
            r13 = r24
            goto L_0x0180
        L_0x00a8:
            java.lang.Object r4 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r12 = r1.L$0
            org.videolan.vlc.MediaParsingService r12 = (org.videolan.vlc.MediaParsingService) r12
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00d7
        L_0x00b4:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.channels.Channel r0 = r26.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r0 = r0.iterator()
            r4 = r2
        L_0x00c0:
            r1.L$0 = r4
            r1.L$1 = r0
            r1.L$2 = r11
            r1.L$3 = r11
            r1.label = r10
            java.lang.Object r12 = r0.hasNext(r1)
            if (r12 != r3) goto L_0x00d1
            return r3
        L_0x00d1:
            r24 = r4
            r4 = r0
            r0 = r12
            r12 = r24
        L_0x00d7:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x02ae
            java.lang.Object r0 = r4.next()
            org.videolan.vlc.MLAction r0 = (org.videolan.vlc.MLAction) r0
            boolean r13 = r0 instanceof org.videolan.vlc.DiscoverStorage
            if (r13 == 0) goto L_0x0130
            java.lang.String[] r13 = org.videolan.medialibrary.interfaces.Medialibrary.getBanList()
            java.lang.String r14 = "getBanList(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r14)
            int r14 = r13.length
            r15 = 0
        L_0x00f4:
            if (r15 >= r14) goto L_0x011e
            r8 = r13[r15]
            org.videolan.medialibrary.interfaces.Medialibrary r7 = r12.medialibrary
            if (r7 != 0) goto L_0x0100
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r7 = r11
        L_0x0100:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r16 = r0
            org.videolan.vlc.DiscoverStorage r16 = (org.videolan.vlc.DiscoverStorage) r16
            java.lang.String r10 = r16.getPath()
            r6.append(r10)
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r7.banFolder(r6)
            int r15 = r15 + 1
            r10 = 1
            goto L_0x00f4
        L_0x011e:
            org.videolan.medialibrary.interfaces.Medialibrary r6 = r12.medialibrary
            if (r6 != 0) goto L_0x0126
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r6 = r11
        L_0x0126:
            org.videolan.vlc.DiscoverStorage r0 = (org.videolan.vlc.DiscoverStorage) r0
            java.lang.String r0 = r0.getPath()
            r6.discover(r0)
            goto L_0x014c
        L_0x0130:
            boolean r6 = r0 instanceof org.videolan.vlc.DiscoverFolder
            if (r6 == 0) goto L_0x0152
            org.videolan.vlc.DiscoverFolder r0 = (org.videolan.vlc.DiscoverFolder) r0
            java.lang.String r6 = r0.getPath()
            r12.addDeviceIfNeeded(r6)
            org.videolan.medialibrary.interfaces.Medialibrary r6 = r12.medialibrary
            if (r6 != 0) goto L_0x0145
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r6 = r11
        L_0x0145:
            java.lang.String r0 = r0.getPath()
            r6.discover(r0)
        L_0x014c:
            r6 = 1
            r8 = 5
            r10 = 3
            r13 = 0
            goto L_0x02ab
        L_0x0152:
            boolean r6 = r0 instanceof org.videolan.vlc.Init
            if (r6 == 0) goto L_0x0248
            org.videolan.medialibrary.interfaces.Medialibrary r6 = r12.medialibrary
            if (r6 != 0) goto L_0x015e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r6 = r11
        L_0x015e:
            boolean r6 = r6.isInitiated()
            if (r6 == 0) goto L_0x0168
            r12.exitCommand()
            goto L_0x014c
        L_0x0168:
            r6 = r12
            android.content.Context r6 = (android.content.Context) r6
            r1.L$0 = r12
            r1.L$1 = r4
            r1.L$2 = r0
            r1.L$3 = r12
            r1.label = r5
            r7 = 1
            java.lang.Object r6 = org.videolan.resources.util.ExtensionsKt.dbExists$default(r6, r11, r1, r7, r11)
            if (r6 != r3) goto L_0x017d
            return r3
        L_0x017d:
            r13 = r0
            r0 = r6
            r14 = r12
        L_0x0180:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r0 = r0 ^ r7
            org.videolan.medialibrary.interfaces.Medialibrary r6 = r14.medialibrary
            if (r6 != 0) goto L_0x018f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r6 = r11
        L_0x018f:
            r7 = r12
            android.content.Context r7 = (android.content.Context) r7
            boolean r6 = r6.construct(r7)
            if (r6 != 0) goto L_0x019e
            r14.exitCommand()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x019e:
            r6 = r13
            org.videolan.vlc.Init r6 = (org.videolan.vlc.Init) r6
            boolean r6 = r6.getParse()
            r1.L$0 = r14
            r1.L$1 = r4
            r1.L$2 = r13
            r1.L$3 = r12
            r1.I$0 = r0
            r8 = 3
            r1.label = r8
            java.lang.Object r6 = r14.addDevices(r7, r6, r1)
            if (r6 != r3) goto L_0x01b9
            return r3
        L_0x01b9:
            r6 = r0
            r0 = r4
            r4 = r14
        L_0x01bc:
            org.videolan.medialibrary.interfaces.Medialibrary r7 = r4.medialibrary
            if (r7 != 0) goto L_0x01c4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r7 = r11
        L_0x01c4:
            r8 = r12
            android.content.Context r8 = (android.content.Context) r8
            int r7 = r7.init(r8)
            org.videolan.medialibrary.interfaces.Medialibrary r10 = r4.medialibrary
            if (r10 != 0) goto L_0x01d3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r10 = r11
        L_0x01d3:
            org.videolan.resources.VLCInstance r14 = org.videolan.resources.VLCInstance.INSTANCE
            java.lang.Object r12 = r14.getInstance(r12)
            java.lang.String r14 = "null cannot be cast to non-null type org.videolan.libvlc.LibVLC"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12, r14)
            org.videolan.libvlc.LibVLC r12 = (org.videolan.libvlc.LibVLC) r12
            long r14 = r12.getInstance()
            r10.setLibVLCInstance(r14)
            org.videolan.medialibrary.interfaces.Medialibrary r10 = r4.medialibrary
            if (r10 != 0) goto L_0x01ef
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r10 = r11
        L_0x01ef:
            r12 = 1
            r10.setDiscoverNetworkEnabled(r12)
            r10 = 5
            if (r7 == r10) goto L_0x0240
            if (r7 == r12) goto L_0x023b
            r10 = 3
            if (r7 != r10) goto L_0x01fd
            r12 = 1
            goto L_0x01fe
        L_0x01fd:
            r12 = 0
        L_0x01fe:
            r6 = r6 | r12
            r12 = 4
            if (r7 != r12) goto L_0x0204
            r14 = 1
            goto L_0x0205
        L_0x0204:
            r14 = 0
        L_0x0205:
            r6 = r6 | r14
            if (r7 == r5) goto L_0x0237
            org.videolan.vlc.Init r13 = (org.videolan.vlc.Init) r13
            boolean r18 = r13.getParse()
            if (r6 == 0) goto L_0x0213
            r20 = 1
            goto L_0x0215
        L_0x0213:
            r20 = 0
        L_0x0215:
            boolean r21 = r13.getUpgrade()
            boolean r22 = r13.getRemoveDevices()
            r1.L$0 = r4
            r1.L$1 = r0
            r1.L$2 = r11
            r1.L$3 = r11
            r1.label = r12
            r17 = r4
            r19 = r8
            r23 = r1
            java.lang.Object r6 = r17.initMedialib(r18, r19, r20, r21, r22, r23)
            if (r6 != r3) goto L_0x0234
            return r3
        L_0x0234:
            r10 = 1
            goto L_0x00c0
        L_0x0237:
            r4.exitCommand()
            goto L_0x0234
        L_0x023b:
            r10 = 3
            r4.exitCommand()
            goto L_0x0234
        L_0x0240:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Medialibrary DB file is corrupted and unrecoverable"
            r0.<init>(r1)
            throw r0
        L_0x0248:
            r10 = 3
            boolean r6 = r0 instanceof org.videolan.vlc.StartScan
            if (r6 == 0) goto L_0x0271
            r6 = 1
            r12.scanActivated = r6
            r7 = r12
            android.content.Context r7 = (android.content.Context) r7
            r1.L$0 = r12
            r1.L$1 = r4
            r1.L$2 = r0
            r8 = 5
            r1.label = r8
            r13 = 0
            java.lang.Object r7 = r12.addDevices(r7, r13, r1)
            if (r7 != r3) goto L_0x0264
            return r3
        L_0x0264:
            r7 = r0
            r0 = r4
            r4 = r12
        L_0x0267:
            org.videolan.vlc.StartScan r7 = (org.videolan.vlc.StartScan) r7
            boolean r7 = r7.getUpgrade()
            r4.startScan(r13, r7)
            goto L_0x0234
        L_0x0271:
            r6 = 1
            r8 = 5
            r13 = 0
            org.videolan.vlc.UpdateStorages r7 = org.videolan.vlc.UpdateStorages.INSTANCE
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x028a
            r1.L$0 = r12
            r1.L$1 = r4
            r0 = 6
            r1.label = r0
            java.lang.Object r0 = r12.updateStorages(r1)
            if (r0 != r3) goto L_0x02ab
            return r3
        L_0x028a:
            boolean r7 = r0 instanceof org.videolan.vlc.Reload
            if (r7 == 0) goto L_0x0298
            org.videolan.vlc.Reload r0 = (org.videolan.vlc.Reload) r0
            java.lang.String r0 = r0.getPath()
            r12.reload(r0)
            goto L_0x02ab
        L_0x0298:
            org.videolan.vlc.ForceReload r7 = org.videolan.vlc.ForceReload.INSTANCE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r7)
            if (r0 == 0) goto L_0x02ab
            org.videolan.medialibrary.interfaces.Medialibrary r0 = r12.medialibrary
            if (r0 != 0) goto L_0x02a8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r0 = r11
        L_0x02a8:
            r0.forceRescan()
        L_0x02ab:
            r0 = r4
            r4 = r12
            goto L_0x0234
        L_0x02ae:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService.processAction(kotlinx.coroutines.channels.ActorScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Lifecycle getLifecycle() {
        return this.dispatcher.getLifecycle();
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/MediaParsingService$Companion;", "", "()V", "discoveryError", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/vlc/DiscoveryError;", "getDiscoveryError", "()Landroidx/lifecycle/MutableLiveData;", "newStorages", "", "", "getNewStorages", "preselectedStorages", "getPreselectedStorages", "()Ljava/util/List;", "progress", "Lorg/videolan/vlc/ScanProgress;", "getProgress", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaParsingService.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<ScanProgress> getProgress() {
            return MediaParsingService.progress;
        }

        public final MutableLiveData<DiscoveryError> getDiscoveryError() {
            return MediaParsingService.discoveryError;
        }

        public final MutableLiveData<List<String>> getNewStorages() {
            return MediaParsingService.newStorages;
        }

        public final List<String> getPreselectedStorages() {
            return MediaParsingService.preselectedStorages;
        }
    }
}
