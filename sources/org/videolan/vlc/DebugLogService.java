package org.videolan.vlc;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Logcat;
import org.videolan.vlc.IDebugLogService;
import org.videolan.vlc.gui.DebugLogActivity;
import org.videolan.vlc.gui.helpers.NotificationHelper;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u0000 -2\u00020\u00012\u00020\u00022\u00020\u0003:\u0003,-.B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0006\u0010\u0015\u001a\u00020\u0012J\b\u0010\u0016\u001a\u00020\u0012H\u0003J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\fH\u0016J\"\u0010\u001e\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0016J\u0012\u0010\"\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010$\u001a\u00020\u0012H\u0016J\u0006\u0010%\u001a\u00020\u0012J\u001a\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u001f2\b\u0010(\u001a\u0004\u0018\u00010\fH\u0002J\u0006\u0010)\u001a\u00020\u0012J\u0006\u0010*\u001a\u00020\u0012J\u0012\u0010+\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lorg/videolan/vlc/DebugLogService;", "Landroid/app/Service;", "Lorg/videolan/tools/Logcat$Callback;", "Ljava/lang/Runnable;", "()V", "binder", "Lorg/videolan/vlc/DebugLogService$DebugLogServiceStub;", "callbacks", "Landroid/os/RemoteCallbackList;", "Lorg/videolan/vlc/IDebugLogServiceCallback;", "logList", "Ljava/util/LinkedList;", "", "logcat", "Lorg/videolan/tools/Logcat;", "saveThread", "Ljava/lang/Thread;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "clear", "forceForeground", "getApplicationContext", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onLog", "log", "onStartCommand", "", "flags", "startId", "registerCallback", "cb", "run", "save", "sendMessage", "what", "str", "start", "stop", "unregisterCallback", "Client", "Companion", "DebugLogServiceStub", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DebugLogService.kt */
public final class DebugLogService extends Service implements Logcat.Callback, Runnable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_LINES = 20000;
    private static final int MSG_ONLOG = 2;
    private static final int MSG_SAVED = 3;
    private static final int MSG_STARTED = 0;
    private static final int MSG_STOPPED = 1;
    private final DebugLogServiceStub binder = new DebugLogServiceStub(this);
    private final RemoteCallbackList<IDebugLogServiceCallback> callbacks = new RemoteCallbackList<>();
    private final LinkedList<String> logList = new LinkedList<>();
    private Logcat logcat;
    private Thread saveThread;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context != null ? LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale()) : null);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/DebugLogService$DebugLogServiceStub;", "Lorg/videolan/vlc/IDebugLogService$Stub;", "service", "Lorg/videolan/vlc/DebugLogService;", "(Lorg/videolan/vlc/DebugLogService;)V", "clear", "", "registerCallback", "cb", "Lorg/videolan/vlc/IDebugLogServiceCallback;", "save", "start", "stop", "unregisterCallback", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DebugLogService.kt */
    public static final class DebugLogServiceStub extends IDebugLogService.Stub {
        private final DebugLogService service;

        public DebugLogServiceStub(DebugLogService debugLogService) {
            Intrinsics.checkNotNullParameter(debugLogService, NotificationCompat.CATEGORY_SERVICE);
            this.service = debugLogService;
        }

        public void start() {
            this.service.start();
        }

        public void stop() {
            this.service.stop();
        }

        public void clear() {
            this.service.clear();
        }

        public void save() {
            this.service.save();
        }

        public void registerCallback(IDebugLogServiceCallback iDebugLogServiceCallback) {
            Intrinsics.checkNotNullParameter(iDebugLogServiceCallback, "cb");
            this.service.registerCallback(iDebugLogServiceCallback);
        }

        public void unregisterCallback(IDebugLogServiceCallback iDebugLogServiceCallback) {
            Intrinsics.checkNotNullParameter(iDebugLogServiceCallback, "cb");
            this.service.unregisterCallback(iDebugLogServiceCallback);
        }
    }

    private final synchronized void sendMessage(int i, String str) {
        int beginBroadcast = this.callbacks.beginBroadcast();
        while (beginBroadcast > 0) {
            beginBroadcast--;
            IDebugLogServiceCallback broadcastItem = this.callbacks.getBroadcastItem(beginBroadcast);
            if (i != 0) {
                boolean z = true;
                if (i == 1) {
                    broadcastItem.onStopped();
                } else if (i == 2) {
                    broadcastItem.onLog(str);
                } else if (i == 3) {
                    if (str == null) {
                        z = false;
                    }
                    try {
                        broadcastItem.onSaved(z, str);
                    } catch (RemoteException unused) {
                    }
                }
            } else {
                broadcastItem.onStarted(this.logList);
            }
        }
        this.callbacks.finishBroadcast();
    }

    public synchronized void onLog(String str) {
        Intrinsics.checkNotNullParameter(str, "log");
        if (this.logList.size() > 20000) {
            this.logList.remove(0);
        }
        this.logList.add(str);
        sendMessage(2, str);
    }

    private final void forceForeground() {
        if (AndroidUtil.isOOrLater) {
            NotificationHelper.INSTANCE.createDebugServcieChannel(getApplicationContext());
        }
        Context context = this;
        Intent intent = new Intent(context, DebugLogActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.setFlags(603979776);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHelper.VLC_DEBUG_CHANNEL);
        builder.setContentTitle(getResources().getString(R.string.log_service_title));
        builder.setContentText(getResources().getString(R.string.log_service_text));
        builder.setSmallIcon(R.drawable.ic_stat_vlc);
        builder.setContentIntent(activity);
        Notification build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        ExtensionsKt.startForegroundCompat(this, 3, build, 1);
    }

    public final synchronized void start() {
        if (this.logcat == null) {
            clear();
            Logcat logcat2 = new Logcat();
            this.logcat = logcat2;
            Intrinsics.checkNotNull(logcat2);
            logcat2.start(this);
            ExtensionsKt.launchForeground$default(this, new Intent(this, DebugLogService.class), (Function0) null, 2, (Object) null);
            sendMessage(0, (String) null);
        }
    }

    public final synchronized void stop() {
        Logcat logcat2 = this.logcat;
        Intrinsics.checkNotNull(logcat2);
        logcat2.stop();
        this.logcat = null;
        sendMessage(1, (String) null);
        ExtensionsKt.stopForegroundCompat$default(this, false, 1, (Object) null);
        stopSelf();
    }

    public final synchronized void clear() {
        this.logList.clear();
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public void run() {
        /*
            r17 = this;
            r1 = r17
            java.lang.String r2 = "vlc options: "
            java.lang.String r0 = "Changed settings:\r\n"
            java.lang.String r3 = "PiP Allowed: "
            java.lang.String r4 = "Notifications: "
            java.lang.String r5 = "Storage ALL access: "
            java.lang.String r6 = "Can write: "
            java.lang.String r7 = "Can read: "
            java.lang.String r8 = "Device Model: "
            java.lang.String r9 = "Android version: "
            java.lang.String r10 = "vlc revision: "
            java.lang.String r11 = "libvlc revision: "
            java.lang.String r12 = "yyyyMMdd_kkmmss"
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            long r13 = java.lang.System.currentTimeMillis()
            java.lang.CharSequence r12 = android.text.format.DateFormat.format(r12, r13)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            org.videolan.resources.AndroidDevices r14 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r14 = r14.getEXTERNAL_PUBLIC_DIRECTORY()
            r13.append(r14)
            java.lang.String r14 = "/vlc_logcat_"
            r13.append(r14)
            r13.append(r12)
            java.lang.String r12 = ".log"
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            java.io.FileOutputStream r15 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException | IOException -> 0x0261, all -> 0x0247 }
            r15.<init>(r12)     // Catch:{ FileNotFoundException | IOException -> 0x0261, all -> 0x0247 }
            java.io.OutputStreamWriter r13 = new java.io.OutputStreamWriter     // Catch:{ FileNotFoundException -> 0x0242, IOException -> 0x023e, all -> 0x023a }
            r14 = r15
            java.io.OutputStream r14 = (java.io.OutputStream) r14     // Catch:{ FileNotFoundException -> 0x0242, IOException -> 0x023e, all -> 0x023a }
            r13.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0242, IOException -> 0x023e, all -> 0x023a }
            java.io.BufferedWriter r14 = new java.io.BufferedWriter     // Catch:{ FileNotFoundException -> 0x0237, IOException -> 0x0234, all -> 0x0232 }
            r16 = r12
            r12 = r13
            java.io.Writer r12 = (java.io.Writer) r12     // Catch:{ FileNotFoundException | IOException -> 0x0245, all -> 0x0232 }
            r14.<init>(r12)     // Catch:{ FileNotFoundException | IOException -> 0x0245, all -> 0x0232 }
            monitor-enter(r17)     // Catch:{ FileNotFoundException | IOException -> 0x0266, all -> 0x0230 }
            java.lang.String r12 = "____________________________\r\n"
            r14.write(r12)     // Catch:{ all -> 0x022d }
            java.lang.String r12 = "Useful info\r\n"
            r14.write(r12)     // Catch:{ all -> 0x022d }
            java.lang.String r12 = "____________________________\r\n"
            r14.write(r12)     // Catch:{ all -> 0x022d }
            java.lang.String r12 = "App version: 3050720 / 3.6.0 Beta 2\r\n"
            r14.write(r12)     // Catch:{ all -> 0x022d }
            java.lang.String r12 = "libvlc: 3.6.0-eap12\r\n"
            r14.write(r12)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r12.<init>(r11)     // Catch:{ all -> 0x022d }
            int r11 = org.videolan.vlc.R.string.build_libvlc_revision     // Catch:{ all -> 0x022d }
            java.lang.String r11 = r1.getString(r11)     // Catch:{ all -> 0x022d }
            r12.append(r11)     // Catch:{ all -> 0x022d }
            java.lang.String r11 = "\r\n"
            r12.append(r11)     // Catch:{ all -> 0x022d }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x022d }
            r14.write(r11)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r11.<init>(r10)     // Catch:{ all -> 0x022d }
            int r10 = org.videolan.vlc.R.string.build_vlc_revision     // Catch:{ all -> 0x022d }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ all -> 0x022d }
            r11.append(r10)     // Catch:{ all -> 0x022d }
            java.lang.String r10 = "\r\n"
            r11.append(r10)     // Catch:{ all -> 0x022d }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x022d }
            r14.write(r10)     // Catch:{ all -> 0x022d }
            java.lang.String r10 = "medialibrary: 0.13.13-rc12\r\n"
            r14.write(r10)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r10.<init>(r9)     // Catch:{ all -> 0x022d }
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x022d }
            r10.append(r9)     // Catch:{ all -> 0x022d }
            java.lang.String r9 = "\r\n"
            r10.append(r9)     // Catch:{ all -> 0x022d }
            java.lang.String r9 = r10.toString()     // Catch:{ all -> 0x022d }
            r14.write(r9)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r9.<init>(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x022d }
            r9.append(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = " - "
            r9.append(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = android.os.Build.MODEL     // Catch:{ all -> 0x022d }
            r9.append(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = "\r\n"
            r9.append(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x022d }
            r14.write(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = "____________________________\r\n"
            r14.write(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = "Permissions\r\n"
            r14.write(r8)     // Catch:{ all -> 0x022d }
            java.lang.String r8 = "____________________________\r\n"
            r14.write(r8)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r8.<init>(r7)     // Catch:{ all -> 0x022d }
            org.videolan.vlc.util.Permissions r7 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x022d }
            r9 = r1
            android.content.Context r9 = (android.content.Context) r9     // Catch:{ all -> 0x022d }
            boolean r7 = r7.canReadStorage(r9)     // Catch:{ all -> 0x022d }
            r8.append(r7)     // Catch:{ all -> 0x022d }
            java.lang.String r7 = "\r\n"
            r8.append(r7)     // Catch:{ all -> 0x022d }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x022d }
            r14.write(r7)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r7.<init>(r6)     // Catch:{ all -> 0x022d }
            org.videolan.vlc.util.Permissions r6 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x022d }
            r8 = r1
            android.content.Context r8 = (android.content.Context) r8     // Catch:{ all -> 0x022d }
            boolean r6 = r6.canWriteStorage(r8)     // Catch:{ all -> 0x022d }
            r7.append(r6)     // Catch:{ all -> 0x022d }
            java.lang.String r6 = "\r\n"
            r7.append(r6)     // Catch:{ all -> 0x022d }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x022d }
            r14.write(r6)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r6.<init>(r5)     // Catch:{ all -> 0x022d }
            org.videolan.vlc.util.Permissions r5 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x022d }
            r7 = r1
            android.content.Context r7 = (android.content.Context) r7     // Catch:{ all -> 0x022d }
            boolean r5 = r5.hasAllAccess(r7)     // Catch:{ all -> 0x022d }
            r6.append(r5)     // Catch:{ all -> 0x022d }
            java.lang.String r5 = "\r\n"
            r6.append(r5)     // Catch:{ all -> 0x022d }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x022d }
            r14.write(r5)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r5.<init>(r4)     // Catch:{ all -> 0x022d }
            org.videolan.vlc.util.Permissions r4 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x022d }
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6     // Catch:{ all -> 0x022d }
            boolean r4 = r4.canSendNotifications(r6)     // Catch:{ all -> 0x022d }
            r5.append(r4)     // Catch:{ all -> 0x022d }
            java.lang.String r4 = "\r\n"
            r5.append(r4)     // Catch:{ all -> 0x022d }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x022d }
            r14.write(r4)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r4.<init>(r3)     // Catch:{ all -> 0x022d }
            org.videolan.vlc.util.Permissions r3 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x022d }
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5     // Catch:{ all -> 0x022d }
            boolean r3 = r3.isPiPAllowed(r5)     // Catch:{ all -> 0x022d }
            r4.append(r3)     // Catch:{ all -> 0x022d }
            java.lang.String r3 = "\r\n"
            r4.append(r3)     // Catch:{ all -> 0x022d }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x022d }
            r14.write(r3)     // Catch:{ all -> 0x022d }
            java.lang.String r3 = "____________________________\r\n"
            r14.write(r3)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01a6 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x01a6 }
            org.videolan.vlc.gui.preferences.search.PreferenceParser r0 = org.videolan.vlc.gui.preferences.search.PreferenceParser.INSTANCE     // Catch:{ Exception -> 0x01a6 }
            r4 = r1
            android.content.Context r4 = (android.content.Context) r4     // Catch:{ Exception -> 0x01a6 }
            java.lang.String r0 = r0.getChangedPrefsString(r4)     // Catch:{ Exception -> 0x01a6 }
            r3.append(r0)     // Catch:{ Exception -> 0x01a6 }
            java.lang.String r0 = "\r\n"
            r3.append(r0)     // Catch:{ Exception -> 0x01a6 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x01a6 }
            r14.write(r0)     // Catch:{ Exception -> 0x01a6 }
            goto L_0x01bb
        L_0x01a6:
            r0 = move-exception
            r3 = r14
            java.io.BufferedWriter r3 = (java.io.BufferedWriter) r3     // Catch:{ all -> 0x022d }
            java.lang.String r3 = "Cannot retrieve changed settings\r\n"
            r14.write(r3)     // Catch:{ all -> 0x022d }
            r3 = r14
            java.io.BufferedWriter r3 = (java.io.BufferedWriter) r3     // Catch:{ all -> 0x022d }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x022d }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x022d }
            r14.write(r0)     // Catch:{ all -> 0x022d }
        L_0x01bb:
            java.lang.String r0 = "____________________________\r\n"
            r14.write(r0)     // Catch:{ all -> 0x022d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x022d }
            r0.<init>(r2)     // Catch:{ all -> 0x022d }
            org.videolan.resources.VLCOptions r2 = org.videolan.resources.VLCOptions.INSTANCE     // Catch:{ all -> 0x022d }
            java.util.ArrayList r2 = r2.getLibOptions()     // Catch:{ all -> 0x022d }
            r3 = r2
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x022d }
            java.lang.String r2 = " "
            r4 = r2
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ all -> 0x022d }
            r10 = 62
            r11 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r2 = kotlin.collections.CollectionsKt.joinToString$default(r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x022d }
            r0.append(r2)     // Catch:{ all -> 0x022d }
            java.lang.String r2 = "\r\n"
            r0.append(r2)     // Catch:{ all -> 0x022d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x022d }
            r14.write(r0)     // Catch:{ all -> 0x022d }
            java.lang.String r0 = "____________________________\r\n"
            r14.write(r0)     // Catch:{ all -> 0x022d }
            java.util.LinkedList<java.lang.String> r0 = r1.logList     // Catch:{ all -> 0x022d }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x022d }
        L_0x01f9:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x022d }
            if (r2 == 0) goto L_0x020c
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x022d }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x022d }
            r14.write(r2)     // Catch:{ all -> 0x022d }
            r14.newLine()     // Catch:{ all -> 0x022d }
            goto L_0x01f9
        L_0x020c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x022d }
            monitor-exit(r17)     // Catch:{ FileNotFoundException | IOException -> 0x0266, all -> 0x0230 }
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            boolean r0 = r0.close(r14)
            r2 = 1
            r0 = r0 & r2
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r13 = (java.io.Closeable) r13
            boolean r2 = r2.close(r13)
            r0 = r0 & r2
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r15 = (java.io.Closeable) r15
            boolean r2 = r2.close(r15)
            r13 = r0 & r2
            goto L_0x027c
        L_0x022d:
            r0 = move-exception
            monitor-exit(r17)     // Catch:{ FileNotFoundException | IOException -> 0x0266, all -> 0x0230 }
            throw r0     // Catch:{ FileNotFoundException | IOException -> 0x0266, all -> 0x0230 }
        L_0x0230:
            r0 = move-exception
            goto L_0x024b
        L_0x0232:
            r0 = move-exception
            goto L_0x023c
        L_0x0234:
            r16 = r12
            goto L_0x0245
        L_0x0237:
            r16 = r12
            goto L_0x0245
        L_0x023a:
            r0 = move-exception
            r13 = 0
        L_0x023c:
            r14 = 0
            goto L_0x024b
        L_0x023e:
            r16 = r12
            r13 = 0
            goto L_0x0245
        L_0x0242:
            r16 = r12
            r13 = 0
        L_0x0245:
            r14 = 0
            goto L_0x0266
        L_0x0247:
            r0 = move-exception
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x024b:
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            r2.close(r14)
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r13 = (java.io.Closeable) r13
            r2.close(r13)
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r15 = (java.io.Closeable) r15
            r2.close(r15)
            throw r0
        L_0x0261:
            r16 = r12
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0266:
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            r0.close(r14)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r13 = (java.io.Closeable) r13
            r0.close(r13)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r15 = (java.io.Closeable) r15
            r0.close(r15)
            r13 = 0
        L_0x027c:
            monitor-enter(r17)
            r2 = 0
            r1.saveThread = r2     // Catch:{ all -> 0x028e }
            if (r13 == 0) goto L_0x0285
            r12 = r16
            goto L_0x0286
        L_0x0285:
            r12 = r2
        L_0x0286:
            r0 = 3
            r1.sendMessage(r0, r12)     // Catch:{ all -> 0x028e }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x028e }
            monitor-exit(r17)
            return
        L_0x028e:
            r0 = move-exception
            monitor-exit(r17)
            goto L_0x0292
        L_0x0291:
            throw r0
        L_0x0292:
            goto L_0x0291
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.DebugLogService.run():void");
    }

    public final synchronized void save() {
        Thread thread = this.saveThread;
        if (thread != null) {
            try {
                Intrinsics.checkNotNull(thread);
                thread.join();
            } catch (InterruptedException unused) {
            }
            this.saveThread = null;
        }
        Thread thread2 = new Thread(this);
        this.saveThread = thread2;
        Intrinsics.checkNotNull(thread2);
        thread2.start();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (!AndroidUtil.isOOrLater) {
            return 1;
        }
        forceForeground();
        return 1;
    }

    /* access modifiers changed from: private */
    public final void registerCallback(IDebugLogServiceCallback iDebugLogServiceCallback) {
        if (iDebugLogServiceCallback != null) {
            this.callbacks.register(iDebugLogServiceCallback);
            sendMessage(this.logcat != null ? 0 : 1, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public final void unregisterCallback(IDebugLogServiceCallback iDebugLogServiceCallback) {
        if (iDebugLogServiceCallback != null) {
            this.callbacks.unregister(iDebugLogServiceCallback);
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0002\f\u0011\u0018\u00002\u00020\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\bJ\u0006\u0010\u0017\u001a\u00020\bJ\u0006\u0010\u0018\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/DebugLogService$Client;", "", "mContext", "Landroid/content/Context;", "mCallback", "Lorg/videolan/vlc/DebugLogService$Client$Callback;", "(Landroid/content/Context;Lorg/videolan/vlc/DebugLogService$Client$Callback;)V", "mBound", "", "mHandler", "Landroid/os/Handler;", "mICallback", "org/videolan/vlc/DebugLogService$Client$mICallback$1", "Lorg/videolan/vlc/DebugLogService$Client$mICallback$1;", "mIDebugLogService", "Lorg/videolan/vlc/IDebugLogService;", "mServiceConnection", "org/videolan/vlc/DebugLogService$Client$mServiceConnection$1", "Lorg/videolan/vlc/DebugLogService$Client$mServiceConnection$1;", "clear", "release", "", "save", "start", "stop", "Callback", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DebugLogService.kt */
    public static final class Client {
        private boolean mBound;
        /* access modifiers changed from: private */
        public final Callback mCallback;
        /* access modifiers changed from: private */
        public final Context mContext;
        /* access modifiers changed from: private */
        public final Handler mHandler = new Handler(Looper.getMainLooper());
        /* access modifiers changed from: private */
        public final DebugLogService$Client$mICallback$1 mICallback = new DebugLogService$Client$mICallback$1(this);
        /* access modifiers changed from: private */
        public IDebugLogService mIDebugLogService;
        private final DebugLogService$Client$mServiceConnection$1 mServiceConnection;

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/DebugLogService$Client$Callback;", "", "onLog", "", "msg", "", "onSaved", "success", "", "path", "onStarted", "logList", "", "onStopped", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: DebugLogService.kt */
        public interface Callback {
            void onLog(String str);

            void onSaved(boolean z, String str);

            void onStarted(List<String> list);

            void onStopped();
        }

        public Client(Context context, Callback callback) throws IllegalArgumentException {
            Intrinsics.checkNotNullParameter(context, "mContext");
            Intrinsics.checkNotNullParameter(callback, "mCallback");
            this.mContext = context;
            this.mCallback = callback;
            DebugLogService$Client$mServiceConnection$1 debugLogService$Client$mServiceConnection$1 = new DebugLogService$Client$mServiceConnection$1(this);
            this.mServiceConnection = debugLogService$Client$mServiceConnection$1;
            this.mBound = context.bindService(new Intent(context, DebugLogService.class), debugLogService$Client$mServiceConnection$1, 1);
        }

        public final boolean start() {
            synchronized (this) {
                IDebugLogService iDebugLogService = this.mIDebugLogService;
                if (iDebugLogService != null) {
                    try {
                        Intrinsics.checkNotNull(iDebugLogService);
                        iDebugLogService.start();
                        return true;
                    } catch (RemoteException unused) {
                        return false;
                    }
                }
            }
        }

        public final boolean stop() {
            synchronized (this) {
                IDebugLogService iDebugLogService = this.mIDebugLogService;
                if (iDebugLogService != null) {
                    try {
                        Intrinsics.checkNotNull(iDebugLogService);
                        iDebugLogService.stop();
                        return true;
                    } catch (RemoteException unused) {
                        return false;
                    }
                }
            }
        }

        public final boolean clear() {
            synchronized (this) {
                IDebugLogService iDebugLogService = this.mIDebugLogService;
                if (iDebugLogService != null) {
                    try {
                        Intrinsics.checkNotNull(iDebugLogService);
                        iDebugLogService.clear();
                        return true;
                    } catch (RemoteException unused) {
                        return false;
                    }
                }
            }
        }

        public final boolean save() {
            synchronized (this) {
                IDebugLogService iDebugLogService = this.mIDebugLogService;
                if (iDebugLogService != null) {
                    try {
                        Intrinsics.checkNotNull(iDebugLogService);
                        iDebugLogService.save();
                        return true;
                    } catch (RemoteException unused) {
                        return false;
                    }
                }
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0014 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void release() {
            /*
                r3 = this;
                boolean r0 = r3.mBound
                r1 = 0
                if (r0 == 0) goto L_0x0029
                monitor-enter(r3)
                org.videolan.vlc.IDebugLogService r0 = r3.mIDebugLogService     // Catch:{ all -> 0x0026 }
                if (r0 == 0) goto L_0x0016
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ RemoteException -> 0x0014 }
                org.videolan.vlc.DebugLogService$Client$mICallback$1 r2 = r3.mICallback     // Catch:{ RemoteException -> 0x0014 }
                org.videolan.vlc.IDebugLogServiceCallback r2 = (org.videolan.vlc.IDebugLogServiceCallback) r2     // Catch:{ RemoteException -> 0x0014 }
                r0.unregisterCallback(r2)     // Catch:{ RemoteException -> 0x0014 }
            L_0x0014:
                r3.mIDebugLogService = r1     // Catch:{ all -> 0x0026 }
            L_0x0016:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0026 }
                monitor-exit(r3)
                r0 = 0
                r3.mBound = r0
                android.content.Context r0 = r3.mContext
                org.videolan.vlc.DebugLogService$Client$mServiceConnection$1 r2 = r3.mServiceConnection
                android.content.ServiceConnection r2 = (android.content.ServiceConnection) r2
                r0.unbindService(r2)
                goto L_0x0029
            L_0x0026:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            L_0x0029:
                android.os.Handler r0 = r3.mHandler
                r0.removeCallbacksAndMessages(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.DebugLogService.Client.release():void");
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/DebugLogService$Companion;", "", "()V", "MAX_LINES", "", "MSG_ONLOG", "MSG_SAVED", "MSG_STARTED", "MSG_STOPPED", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DebugLogService.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
