package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.IntentFilter;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.LocaleUtilsKt;

@Metadata(d1 = {"\u0000=\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0017J\b\u0010\u0017\u001a\u00020\u0011H\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/webserver/RemoteAccessService;", "Landroidx/lifecycle/LifecycleService;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "receiver", "org/videolan/vlc/webserver/RemoteAccessService$receiver$1", "Lorg/videolan/vlc/webserver/RemoteAccessService$receiver$1;", "server", "Lorg/videolan/vlc/webserver/RemoteAccessServer;", "startServerActor", "Lkotlinx/coroutines/channels/SendChannel;", "", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "forceForeground", "getApplicationContext", "onCreate", "onDestroy", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessService.kt */
public final class RemoteAccessService extends LifecycleService implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private final RemoteAccessService$receiver$1 receiver = new RemoteAccessService$receiver$1(this);
    /* access modifiers changed from: private */
    public RemoteAccessServer server;
    /* access modifiers changed from: private */
    public final SendChannel<String> startServerActor = ActorKt.actor$default(this, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new RemoteAccessService$startServerActor$1(this, (Continuation<? super RemoteAccessService$startServerActor$1>) null), 13, (Object) null);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessService.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.webserver.ServerStatus[] r0 = org.videolan.vlc.webserver.ServerStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.NOT_INIT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.STARTED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.STOPPED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.CONNECTING     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.ERROR     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.STOPPING     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessService.WhenMappings.<clinit>():void");
        }
    }

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
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
        if (AndroidUtil.isOOrLater) {
            forceForeground();
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new RemoteAccessService$onCreate$1(this, (Continuation<? super RemoteAccessService$onCreate$1>) null), 2, (Object) null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_STOP_SERVER);
        intentFilter.addAction(Constants.ACTION_START_SERVER);
        intentFilter.addAction(Constants.ACTION_DISABLE_SERVER);
        intentFilter.addAction(Constants.ACTION_RESTART_SERVER);
        ExtensionsKt.registerReceiverCompat(this, this.receiver, intentFilter, false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0098, code lost:
        if (r3.getServerStatus().getValue() == org.videolan.vlc.webserver.ServerStatus.STARTED) goto L_0x009c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void forceForeground() {
        /*
            r15 = this;
            org.videolan.vlc.webserver.RemoteAccessServer r0 = r15.server
            r1 = 1
            r2 = 0
            r3 = 0
            java.lang.String r4 = "server"
            if (r0 != 0) goto L_0x0011
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification_not_init
            java.lang.String r0 = r15.getString(r0)
            goto L_0x0080
        L_0x0011:
            if (r0 != 0) goto L_0x0017
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r0 = r3
        L_0x0017:
            androidx.lifecycle.LiveData r0 = r0.getServerStatus()
            java.lang.Object r0 = r0.getValue()
            org.videolan.vlc.webserver.ServerStatus r0 = (org.videolan.vlc.webserver.ServerStatus) r0
            if (r0 != 0) goto L_0x0025
            r0 = -1
            goto L_0x002d
        L_0x0025:
            int[] r5 = org.videolan.vlc.webserver.RemoteAccessService.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r5[r0]
        L_0x002d:
            switch(r0) {
                case 1: goto L_0x007a;
                case 2: goto L_0x004f;
                case 3: goto L_0x0048;
                case 4: goto L_0x0041;
                case 5: goto L_0x003a;
                case 6: goto L_0x0033;
                default: goto L_0x0030;
            }
        L_0x0030:
            java.lang.String r0 = ""
            goto L_0x0080
        L_0x0033:
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification_stopping
            java.lang.String r0 = r15.getString(r0)
            goto L_0x0080
        L_0x003a:
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification_error
            java.lang.String r0 = r15.getString(r0)
            goto L_0x0080
        L_0x0041:
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification_connecting
            java.lang.String r0 = r15.getString(r0)
            goto L_0x0080
        L_0x0048:
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification_stopped
            java.lang.String r0 = r15.getString(r0)
            goto L_0x0080
        L_0x004f:
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification
            org.videolan.vlc.webserver.RemoteAccessServer r5 = r15.server
            if (r5 != 0) goto L_0x0059
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = r3
        L_0x0059:
            java.util.List r5 = r5.getServerAddresses()
            r6 = r5
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.lang.String r5 = "\n"
            r7 = r5
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r13 = 62
            r14 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r5 = kotlin.collections.CollectionsKt.joinToString$default(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            java.lang.Object[] r6 = new java.lang.Object[r1]
            r6[r2] = r5
            java.lang.String r0 = r15.getString(r0, r6)
            goto L_0x0080
        L_0x007a:
            int r0 = org.videolan.vlc.webserver.R.string.remote_access_notification_not_init
            java.lang.String r0 = r15.getString(r0)
        L_0x0080:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            org.videolan.vlc.webserver.RemoteAccessServer r5 = r15.server
            if (r5 == 0) goto L_0x009b
            if (r5 != 0) goto L_0x008d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x008e
        L_0x008d:
            r3 = r5
        L_0x008e:
            androidx.lifecycle.LiveData r3 = r3.getServerStatus()
            java.lang.Object r3 = r3.getValue()
            org.videolan.vlc.webserver.ServerStatus r4 = org.videolan.vlc.webserver.ServerStatus.STARTED
            if (r3 != r4) goto L_0x009b
            goto L_0x009c
        L_0x009b:
            r1 = 0
        L_0x009c:
            org.videolan.vlc.gui.helpers.NotificationHelper r2 = org.videolan.vlc.gui.helpers.NotificationHelper.INSTANCE
            android.content.Context r3 = r15.getApplicationContext()
            android.app.Notification r0 = r2.createRemoteAccessNotification(r3, r0, r1)
            r1 = 44
            r15.startForeground(r1, r0)     // Catch:{ Exception -> 0x00ac }
            goto L_0x00c0
        L_0x00ac:
            r0 = move-exception
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r1 < r2) goto L_0x00c0
            boolean r0 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m$1((java.lang.Object) r0)
            if (r0 == 0) goto L_0x00c0
            java.lang.String r0 = "RemoteAccessService"
            java.lang.String r1 = "ForegroundServiceStartNotAllowedException caught!"
            android.util.Log.w(r0, r1)
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessService.forceForeground():void");
    }

    public void onDestroy() {
        if (this.server != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new RemoteAccessService$onDestroy$1(this, (Continuation<? super RemoteAccessService$onDestroy$1>) null), 3, (Object) null);
        }
        unregisterReceiver(this.receiver);
        super.onDestroy();
    }
}
