package org.videolan.vlc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.AppScope;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.gui.helpers.hf.OtgAccess;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010#\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0016J\u000e\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020\"J\u000e\u00102\u001a\u00020$2\u0006\u00101\u001a\u00020\"R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0004R\u0012\u0010\t\u001a\u00020\nX\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0019X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u0004R\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001c8F¢\u0006\f\u0012\u0004\b\u001d\u0010\u0004\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/videolan/vlc/ExternalMonitor;", "Landroid/content/BroadcastReceiver;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "actor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/DeviceAction;", "getActor$annotations", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "ctx", "Landroid/content/Context;", "devices", "Lorg/videolan/tools/livedata/LiveDataset;", "Landroid/hardware/usb/UsbDevice;", "getDevices", "()Lorg/videolan/tools/livedata/LiveDataset;", "setDevices", "(Lorg/videolan/tools/livedata/LiveDataset;)V", "registered", "", "storageChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "getStorageChannel$annotations", "storageEvents", "Lkotlinx/coroutines/flow/Flow;", "getStorageEvents$annotations", "getStorageEvents", "()Lkotlinx/coroutines/flow/Flow;", "storageObserver", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "checkNewStorages", "", "notifyNewStorage", "mediaMounted", "Lorg/videolan/vlc/MediaMounted;", "onReceive", "context", "intent", "Landroid/content/Intent;", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "subscribeStorageCb", "observer", "unsubscribeStorageCb", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalMonitor.kt */
public final class ExternalMonitor extends BroadcastReceiver implements DefaultLifecycleObserver, CoroutineScope {
    public static final ExternalMonitor INSTANCE;
    private static final SendChannel<DeviceAction> actor;
    /* access modifiers changed from: private */
    public static Context ctx;
    private static LiveDataset<UsbDevice> devices = new LiveDataset<>();
    private static boolean registered;
    /* access modifiers changed from: private */
    public static final MutableSharedFlow<DeviceAction> storageChannel = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
    private static WeakReference<Activity> storageObserver;
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();

    private static /* synthetic */ void getActor$annotations() {
    }

    private static /* synthetic */ void getStorageChannel$annotations() {
    }

    public static /* synthetic */ void getStorageEvents$annotations() {
    }

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    private ExternalMonitor() {
    }

    static {
        ExternalMonitor externalMonitor = new ExternalMonitor();
        INSTANCE = externalMonitor;
        actor = ActorKt.actor$default(externalMonitor, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new ExternalMonitor$actor$1((Continuation<? super ExternalMonitor$actor$1>) null), 13, (Object) null);
        Job unused = BuildersKt__Builders_commonKt.launch$default(externalMonitor, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.ExternalMonitor$1", f = "ExternalMonitor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.ExternalMonitor$1  reason: invalid class name */
    /* compiled from: ExternalMonitor.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ProcessLifecycleOwner.Companion.get().getLifecycle().addObserver(ExternalMonitor.INSTANCE);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public void onReceive(Context context, Intent intent) {
        Parcelable parcelable;
        Parcelable parcelable2;
        Uri data;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (ctx == null) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            ctx = applicationContext;
        }
        String action = intent.getAction();
        if (action != null) {
            UsbDevice usbDevice = null;
            switch (action.hashCode()) {
                case -2114103349:
                    if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED") && intent.hasExtra("device")) {
                        if (Build.VERSION.SDK_INT >= 33) {
                            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "device", UsbDevice.class);
                        } else {
                            Parcelable parcelableExtra = intent.getParcelableExtra("device");
                            if (parcelableExtra instanceof UsbDevice) {
                                usbDevice = parcelableExtra;
                            }
                            parcelable = usbDevice;
                        }
                        UsbDevice usbDevice2 = (UsbDevice) parcelable;
                        if (usbDevice2 != null) {
                            devices.add(usbDevice2);
                            return;
                        }
                        return;
                    }
                    return;
                case -1608292967:
                    if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED") && intent.hasExtra("device")) {
                        OtgAccess.Companion.getOtgRoot().setValue(null);
                        if (Build.VERSION.SDK_INT >= 33) {
                            parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "device", UsbDevice.class);
                        } else {
                            Parcelable parcelableExtra2 = intent.getParcelableExtra("device");
                            if (parcelableExtra2 instanceof UsbDevice) {
                                usbDevice = parcelableExtra2;
                            }
                            parcelable2 = (UsbDevice) usbDevice;
                        }
                        UsbDevice usbDevice3 = (UsbDevice) parcelable2;
                        if (usbDevice3 != null) {
                            devices.remove(usbDevice3);
                            return;
                        }
                        return;
                    }
                    return;
                case -1514214344:
                    if (action.equals("android.intent.action.MEDIA_MOUNTED") && (data = intent.getData()) != null) {
                        ChannelResult.m2336boximpl(actor.m1139trySendJP2dKIU(new MediaMounted(data, (String) null, (String) null, 6, (DefaultConstructorMarker) null)));
                        return;
                    }
                    return;
                case -963871873:
                    if (!action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                        return;
                    }
                    break;
                case -625887599:
                    if (!action.equals("android.intent.action.MEDIA_EJECT")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            Uri data2 = intent.getData();
            if (data2 != null) {
                ChannelResult.m2336boximpl(actor.m1139trySendJP2dKIU(new MediaUnmounted(data2, (String) null, (String) null, 6, (DefaultConstructorMarker) null)));
            }
        }
    }

    public final Flow<DeviceAction> getStorageEvents() {
        return FlowKt.asSharedFlow(storageChannel);
    }

    public final LiveDataset<UsbDevice> getDevices() {
        return devices;
    }

    public final void setDevices(LiveDataset<UsbDevice> liveDataset) {
        Intrinsics.checkNotNullParameter(liveDataset, "<set-?>");
        devices = liveDataset;
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        if (!registered) {
            Context appContext = AppContextProvider.INSTANCE.getAppContext();
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
            IntentFilter intentFilter2 = new IntentFilter("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            intentFilter.addDataScheme("file");
            BroadcastReceiver broadcastReceiver = this;
            appContext.registerReceiver(broadcastReceiver, intentFilter);
            appContext.registerReceiver(broadcastReceiver, intentFilter2);
            registered = true;
            checkNewStorages(appContext);
        }
    }

    private final void checkNewStorages(Context context) {
        if (Medialibrary.getInstance().isStarted() && (Settings.INSTANCE.getShowTvUi() || ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getInt(SettingsKt.KEY_MEDIALIBRARY_SCAN, -1) == 0)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new ExternalMonitor$checkNewStorages$1(context, (Continuation<? super ExternalMonitor$checkNewStorages$1>) null), 3, (Object) null);
        }
        UsbManager usbManager = (UsbManager) ContextCompat.getSystemService(context, UsbManager.class);
        if (usbManager != null) {
            devices.add(new ArrayList(usbManager.getDeviceList().values()));
        }
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        Context appContext = AppContextProvider.INSTANCE.getAppContext();
        if (registered) {
            try {
                appContext.unregisterReceiver(this);
            } catch (IllegalArgumentException unused) {
            }
        }
        registered = false;
        devices.clear();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void notifyNewStorage(org.videolan.vlc.MediaMounted r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.ref.WeakReference<android.app.Activity> r0 = storageObserver     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0020 }
            android.app.Activity r0 = (android.app.Activity) r0     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x000e
            goto L_0x001e
        L_0x000e:
            org.videolan.vlc.gui.helpers.UiTools r1 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r4.getPath()     // Catch:{ all -> 0x0020 }
            r1.newStorageDetected(r0, r2)     // Catch:{ all -> 0x0020 }
            kotlinx.coroutines.flow.MutableSharedFlow<org.videolan.vlc.DeviceAction> r0 = storageChannel     // Catch:{ all -> 0x0020 }
            r0.tryEmit(r4)     // Catch:{ all -> 0x0020 }
            monitor-exit(r3)
            return
        L_0x001e:
            monitor-exit(r3)
            return
        L_0x0020:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ExternalMonitor.notifyNewStorage(org.videolan.vlc.MediaMounted):void");
    }

    public final synchronized void subscribeStorageCb(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "observer");
        storageObserver = new WeakReference<>(activity);
    }

    public final synchronized void unsubscribeStorageCb(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "observer");
        WeakReference<Activity> weakReference = storageObserver;
        if ((weakReference != null ? (Activity) weakReference.get() : null) == activity) {
            WeakReference<Activity> weakReference2 = storageObserver;
            if (weakReference2 != null) {
                weakReference2.clear();
            }
            storageObserver = null;
        }
    }
}
