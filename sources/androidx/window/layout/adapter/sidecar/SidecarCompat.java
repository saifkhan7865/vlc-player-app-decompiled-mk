package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.util.Consumer;
import androidx.window.core.VerificationMode;
import androidx.window.core.Version;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarProvider;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 #2\u00020\u0001:\u0004#$%&B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0007\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u0016\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\fJ\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\fH\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\fH\u0002J\b\u0010!\u001a\u00020\"H\u0017R \u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarCompat;", "Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sidecar", "Landroidx/window/sidecar/SidecarInterface;", "sidecarAdapter", "Landroidx/window/layout/adapter/sidecar/SidecarAdapter;", "(Landroidx/window/sidecar/SidecarInterface;Landroidx/window/layout/adapter/sidecar/SidecarAdapter;)V", "componentCallbackMap", "", "Landroid/app/Activity;", "Landroidx/core/util/Consumer;", "Landroid/content/res/Configuration;", "extensionCallback", "Landroidx/window/layout/adapter/sidecar/SidecarCompat$DistinctElementCallback;", "getSidecar", "()Landroidx/window/sidecar/SidecarInterface;", "windowListenerRegisteredContexts", "Landroid/os/IBinder;", "getWindowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "activity", "onWindowLayoutChangeListenerAdded", "", "onWindowLayoutChangeListenerRemoved", "register", "windowToken", "registerConfigurationChangeListener", "setExtensionCallback", "Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "unregisterComponentCallback", "validateExtensionInterface", "", "Companion", "DistinctElementCallback", "FirstAttachAdapter", "TranslatingCallback", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SidecarCompat.kt */
public final class SidecarCompat implements ExtensionInterfaceCompat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "SidecarCompat";
    private final Map<Activity, Consumer<Configuration>> componentCallbackMap;
    /* access modifiers changed from: private */
    public DistinctElementCallback extensionCallback;
    private final SidecarInterface sidecar;
    /* access modifiers changed from: private */
    public final SidecarAdapter sidecarAdapter;
    /* access modifiers changed from: private */
    public final Map<IBinder, Activity> windowListenerRegisteredContexts;

    public SidecarCompat(SidecarInterface sidecarInterface, SidecarAdapter sidecarAdapter2) {
        Intrinsics.checkNotNullParameter(sidecarAdapter2, "sidecarAdapter");
        this.sidecar = sidecarInterface;
        this.sidecarAdapter = sidecarAdapter2;
        this.windowListenerRegisteredContexts = new LinkedHashMap();
        this.componentCallbackMap = new LinkedHashMap();
    }

    public final SidecarInterface getSidecar() {
        return this.sidecar;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SidecarCompat(Context context) {
        this(Companion.getSidecarCompat$window_release(context), new SidecarAdapter((VerificationMode) null, 1, (DefaultConstructorMarker) null));
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void setExtensionCallback(ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallbackInterface) {
        Intrinsics.checkNotNullParameter(extensionCallbackInterface, "extensionCallback");
        this.extensionCallback = new DistinctElementCallback(extensionCallbackInterface);
        SidecarInterface sidecarInterface = this.sidecar;
        if (sidecarInterface != null) {
            sidecarInterface.setSidecarCallback(new DistinctElementSidecarCallback(this.sidecarAdapter, new TranslatingCallback()));
        }
    }

    public final WindowLayoutInfo getWindowLayoutInfo(Activity activity) {
        SidecarDeviceState sidecarDeviceState;
        Intrinsics.checkNotNullParameter(activity, "activity");
        IBinder activityWindowToken$window_release = Companion.getActivityWindowToken$window_release(activity);
        if (activityWindowToken$window_release == null) {
            return new WindowLayoutInfo(CollectionsKt.emptyList());
        }
        SidecarInterface sidecarInterface = this.sidecar;
        SidecarWindowLayoutInfo windowLayoutInfo = sidecarInterface != null ? sidecarInterface.getWindowLayoutInfo(activityWindowToken$window_release) : null;
        SidecarAdapter sidecarAdapter2 = this.sidecarAdapter;
        SidecarInterface sidecarInterface2 = this.sidecar;
        if (sidecarInterface2 == null || (sidecarDeviceState = sidecarInterface2.getDeviceState()) == null) {
            sidecarDeviceState = new SidecarDeviceState();
        }
        return sidecarAdapter2.translate(windowLayoutInfo, sidecarDeviceState);
    }

    public void onWindowLayoutChangeListenerAdded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        IBinder activityWindowToken$window_release = Companion.getActivityWindowToken$window_release(activity);
        if (activityWindowToken$window_release != null) {
            register(activityWindowToken$window_release, activity);
            return;
        }
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new FirstAttachAdapter(this, activity));
    }

    public final void register(IBinder iBinder, Activity activity) {
        SidecarInterface sidecarInterface;
        Intrinsics.checkNotNullParameter(iBinder, "windowToken");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.windowListenerRegisteredContexts.put(iBinder, activity);
        SidecarInterface sidecarInterface2 = this.sidecar;
        if (sidecarInterface2 != null) {
            sidecarInterface2.onWindowLayoutChangeListenerAdded(iBinder);
        }
        if (this.windowListenerRegisteredContexts.size() == 1 && (sidecarInterface = this.sidecar) != null) {
            sidecarInterface.onDeviceStateListenersChanged(false);
        }
        DistinctElementCallback distinctElementCallback = this.extensionCallback;
        if (distinctElementCallback != null) {
            distinctElementCallback.onWindowLayoutChanged(activity, getWindowLayoutInfo(activity));
        }
        registerConfigurationChangeListener(activity);
    }

    private final void registerConfigurationChangeListener(Activity activity) {
        if (this.componentCallbackMap.get(activity) == null && (activity instanceof OnConfigurationChangedProvider)) {
            SidecarCompat$$ExternalSyntheticLambda0 sidecarCompat$$ExternalSyntheticLambda0 = new SidecarCompat$$ExternalSyntheticLambda0(this, activity);
            this.componentCallbackMap.put(activity, sidecarCompat$$ExternalSyntheticLambda0);
            ((OnConfigurationChangedProvider) activity).addOnConfigurationChangedListener(sidecarCompat$$ExternalSyntheticLambda0);
        }
    }

    /* access modifiers changed from: private */
    public static final void registerConfigurationChangeListener$lambda$0(SidecarCompat sidecarCompat, Activity activity, Configuration configuration) {
        Intrinsics.checkNotNullParameter(sidecarCompat, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        DistinctElementCallback distinctElementCallback = sidecarCompat.extensionCallback;
        if (distinctElementCallback != null) {
            distinctElementCallback.onWindowLayoutChanged(activity, sidecarCompat.getWindowLayoutInfo(activity));
        }
    }

    public void onWindowLayoutChangeListenerRemoved(Activity activity) {
        SidecarInterface sidecarInterface;
        Intrinsics.checkNotNullParameter(activity, "activity");
        IBinder activityWindowToken$window_release = Companion.getActivityWindowToken$window_release(activity);
        if (activityWindowToken$window_release != null) {
            SidecarInterface sidecarInterface2 = this.sidecar;
            if (sidecarInterface2 != null) {
                sidecarInterface2.onWindowLayoutChangeListenerRemoved(activityWindowToken$window_release);
            }
            unregisterComponentCallback(activity);
            DistinctElementCallback distinctElementCallback = this.extensionCallback;
            if (distinctElementCallback != null) {
                distinctElementCallback.clearWindowLayoutInfo(activity);
            }
            boolean z = this.windowListenerRegisteredContexts.size() == 1;
            this.windowListenerRegisteredContexts.remove(activityWindowToken$window_release);
            if (z && (sidecarInterface = this.sidecar) != null) {
                sidecarInterface.onDeviceStateListenersChanged(true);
            }
        }
    }

    private final void unregisterComponentCallback(Activity activity) {
        Consumer consumer = this.componentCallbackMap.get(activity);
        if (consumer != null) {
            if (activity instanceof OnConfigurationChangedProvider) {
                ((OnConfigurationChangedProvider) activity).removeOnConfigurationChangedListener(consumer);
            }
            this.componentCallbackMap.remove(activity);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:52|53|54|55|61|62|63|64|65|(2:68|69)|67|82) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:64:0x0113 */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x014d A[Catch:{ NoSuchFieldError -> 0x00c2, all -> 0x01a5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean validateExtensionInterface() {
        /*
            r11 = this;
            java.lang.String r0 = "Illegal return type for 'onWindowLayoutChangeListenerRemoved': "
            java.lang.String r1 = "Illegal return type for 'onWindowLayoutChangeListenerAdded': "
            java.lang.String r2 = "Illegal return type for 'getWindowLayoutInfo': "
            java.lang.String r3 = "Illegal return type for 'setSidecarCallback': "
            r4 = 0
            androidx.window.sidecar.SidecarInterface r5 = r11.sidecar     // Catch:{ all -> 0x01a5 }
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0022
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x01a5 }
            if (r5 == 0) goto L_0x0022
            java.lang.String r8 = "setSidecarCallback"
            java.lang.Class[] r9 = new java.lang.Class[r6]     // Catch:{ all -> 0x01a5 }
            java.lang.Class<androidx.window.sidecar.SidecarInterface$SidecarCallback> r10 = androidx.window.sidecar.SidecarInterface.SidecarCallback.class
            r9[r4] = r10     // Catch:{ all -> 0x01a5 }
            java.lang.reflect.Method r5 = r5.getMethod(r8, r9)     // Catch:{ all -> 0x01a5 }
            goto L_0x0023
        L_0x0022:
            r5 = r7
        L_0x0023:
            if (r5 == 0) goto L_0x002a
            java.lang.Class r5 = r5.getReturnType()     // Catch:{ all -> 0x01a5 }
            goto L_0x002b
        L_0x002a:
            r5 = r7
        L_0x002b:
            java.lang.Class r8 = java.lang.Void.TYPE     // Catch:{ all -> 0x01a5 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)     // Catch:{ all -> 0x01a5 }
            if (r8 == 0) goto L_0x0193
            androidx.window.sidecar.SidecarInterface r3 = r11.sidecar     // Catch:{ all -> 0x01a5 }
            if (r3 == 0) goto L_0x003a
            r3.getDeviceState()     // Catch:{ all -> 0x01a5 }
        L_0x003a:
            androidx.window.sidecar.SidecarInterface r3 = r11.sidecar     // Catch:{ all -> 0x01a5 }
            if (r3 == 0) goto L_0x0041
            r3.onDeviceStateListenersChanged(r6)     // Catch:{ all -> 0x01a5 }
        L_0x0041:
            androidx.window.sidecar.SidecarInterface r3 = r11.sidecar     // Catch:{ all -> 0x01a5 }
            if (r3 == 0) goto L_0x0058
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x01a5 }
            if (r3 == 0) goto L_0x0058
            java.lang.String r5 = "getWindowLayoutInfo"
            java.lang.Class[] r8 = new java.lang.Class[r6]     // Catch:{ all -> 0x01a5 }
            java.lang.Class<android.os.IBinder> r9 = android.os.IBinder.class
            r8[r4] = r9     // Catch:{ all -> 0x01a5 }
            java.lang.reflect.Method r3 = r3.getMethod(r5, r8)     // Catch:{ all -> 0x01a5 }
            goto L_0x0059
        L_0x0058:
            r3 = r7
        L_0x0059:
            if (r3 == 0) goto L_0x0060
            java.lang.Class r3 = r3.getReturnType()     // Catch:{ all -> 0x01a5 }
            goto L_0x0061
        L_0x0060:
            r3 = r7
        L_0x0061:
            java.lang.Class<androidx.window.sidecar.SidecarWindowLayoutInfo> r5 = androidx.window.sidecar.SidecarWindowLayoutInfo.class
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ all -> 0x01a5 }
            if (r5 == 0) goto L_0x0181
            androidx.window.sidecar.SidecarInterface r2 = r11.sidecar     // Catch:{ all -> 0x01a5 }
            if (r2 == 0) goto L_0x0080
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x01a5 }
            if (r2 == 0) goto L_0x0080
            java.lang.String r3 = "onWindowLayoutChangeListenerAdded"
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ all -> 0x01a5 }
            java.lang.Class<android.os.IBinder> r8 = android.os.IBinder.class
            r5[r4] = r8     // Catch:{ all -> 0x01a5 }
            java.lang.reflect.Method r2 = r2.getMethod(r3, r5)     // Catch:{ all -> 0x01a5 }
            goto L_0x0081
        L_0x0080:
            r2 = r7
        L_0x0081:
            if (r2 == 0) goto L_0x0088
            java.lang.Class r2 = r2.getReturnType()     // Catch:{ all -> 0x01a5 }
            goto L_0x0089
        L_0x0088:
            r2 = r7
        L_0x0089:
            java.lang.Class r3 = java.lang.Void.TYPE     // Catch:{ all -> 0x01a5 }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x01a5 }
            if (r3 == 0) goto L_0x016f
            androidx.window.sidecar.SidecarInterface r1 = r11.sidecar     // Catch:{ all -> 0x01a5 }
            if (r1 == 0) goto L_0x00a8
            java.lang.Class r1 = r1.getClass()     // Catch:{ all -> 0x01a5 }
            if (r1 == 0) goto L_0x00a8
            java.lang.String r2 = "onWindowLayoutChangeListenerRemoved"
            java.lang.Class[] r3 = new java.lang.Class[r6]     // Catch:{ all -> 0x01a5 }
            java.lang.Class<android.os.IBinder> r5 = android.os.IBinder.class
            r3[r4] = r5     // Catch:{ all -> 0x01a5 }
            java.lang.reflect.Method r1 = r1.getMethod(r2, r3)     // Catch:{ all -> 0x01a5 }
            goto L_0x00a9
        L_0x00a8:
            r1 = r7
        L_0x00a9:
            if (r1 == 0) goto L_0x00b0
            java.lang.Class r1 = r1.getReturnType()     // Catch:{ all -> 0x01a5 }
            goto L_0x00b1
        L_0x00b0:
            r1 = r7
        L_0x00b1:
            java.lang.Class r2 = java.lang.Void.TYPE     // Catch:{ all -> 0x01a5 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x01a5 }
            if (r2 == 0) goto L_0x015d
            androidx.window.sidecar.SidecarDeviceState r0 = new androidx.window.sidecar.SidecarDeviceState     // Catch:{ all -> 0x01a5 }
            r0.<init>()     // Catch:{ all -> 0x01a5 }
            r1 = 3
            r0.posture = r1     // Catch:{ NoSuchFieldError -> 0x00c2 }
            goto L_0x00f4
        L_0x00c2:
            java.lang.Class<androidx.window.sidecar.SidecarDeviceState> r2 = androidx.window.sidecar.SidecarDeviceState.class
            java.lang.String r3 = "setPosture"
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ all -> 0x01a5 }
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01a5 }
            r5[r4] = r8     // Catch:{ all -> 0x01a5 }
            java.lang.reflect.Method r2 = r2.getMethod(r3, r5)     // Catch:{ all -> 0x01a5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x01a5 }
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x01a5 }
            r5[r4] = r3     // Catch:{ all -> 0x01a5 }
            r2.invoke(r0, r5)     // Catch:{ all -> 0x01a5 }
            java.lang.Class<androidx.window.sidecar.SidecarDeviceState> r2 = androidx.window.sidecar.SidecarDeviceState.class
            java.lang.String r3 = "getPosture"
            java.lang.reflect.Method r2 = r2.getMethod(r3, r7)     // Catch:{ all -> 0x01a5 }
            java.lang.Object r0 = r2.invoke(r0, r7)     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Int"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r2)     // Catch:{ all -> 0x01a5 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x01a5 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x01a5 }
            if (r0 != r1) goto L_0x0155
        L_0x00f4:
            androidx.window.sidecar.SidecarDisplayFeature r0 = new androidx.window.sidecar.SidecarDisplayFeature     // Catch:{ all -> 0x01a5 }
            r0.<init>()     // Catch:{ all -> 0x01a5 }
            android.graphics.Rect r1 = r0.getRect()     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "displayFeature.rect"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ all -> 0x01a5 }
            r0.setRect(r1)     // Catch:{ all -> 0x01a5 }
            r0.getType()     // Catch:{ all -> 0x01a5 }
            r0.setType(r6)     // Catch:{ all -> 0x01a5 }
            androidx.window.sidecar.SidecarWindowLayoutInfo r1 = new androidx.window.sidecar.SidecarWindowLayoutInfo     // Catch:{ all -> 0x01a5 }
            r1.<init>()     // Catch:{ all -> 0x01a5 }
            java.util.List r0 = r1.displayFeatures     // Catch:{ NoSuchFieldError -> 0x0113 }
            goto L_0x014b
        L_0x0113:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x01a5 }
            r2.<init>()     // Catch:{ all -> 0x01a5 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x01a5 }
            r2.add(r0)     // Catch:{ all -> 0x01a5 }
            java.lang.Class<androidx.window.sidecar.SidecarWindowLayoutInfo> r0 = androidx.window.sidecar.SidecarWindowLayoutInfo.class
            java.lang.String r3 = "setDisplayFeatures"
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ all -> 0x01a5 }
            java.lang.Class<java.util.List> r8 = java.util.List.class
            r5[r4] = r8     // Catch:{ all -> 0x01a5 }
            java.lang.reflect.Method r0 = r0.getMethod(r3, r5)     // Catch:{ all -> 0x01a5 }
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x01a5 }
            r3[r4] = r2     // Catch:{ all -> 0x01a5 }
            r0.invoke(r1, r3)     // Catch:{ all -> 0x01a5 }
            java.lang.Class<androidx.window.sidecar.SidecarWindowLayoutInfo> r0 = androidx.window.sidecar.SidecarWindowLayoutInfo.class
            java.lang.String r3 = "getDisplayFeatures"
            java.lang.reflect.Method r0 = r0.getMethod(r3, r7)     // Catch:{ all -> 0x01a5 }
            java.lang.Object r0 = r0.invoke(r1, r7)     // Catch:{ all -> 0x01a5 }
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)     // Catch:{ all -> 0x01a5 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x01a5 }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r0)     // Catch:{ all -> 0x01a5 }
            if (r0 == 0) goto L_0x014d
        L_0x014b:
            r4 = 1
            goto L_0x01a5
        L_0x014d:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ all -> 0x01a5 }
            java.lang.String r1 = "Invalid display feature getter/setter"
            r0.<init>(r1)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x0155:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ all -> 0x01a5 }
            java.lang.String r1 = "Invalid device posture getter/setter"
            r0.<init>(r1)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x015d:
            java.lang.NoSuchMethodException r2 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r3.<init>(r0)     // Catch:{ all -> 0x01a5 }
            r3.append(r1)     // Catch:{ all -> 0x01a5 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x01a5 }
            r2.<init>(r0)     // Catch:{ all -> 0x01a5 }
            throw r2     // Catch:{ all -> 0x01a5 }
        L_0x016f:
            java.lang.NoSuchMethodException r0 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r3.<init>(r1)     // Catch:{ all -> 0x01a5 }
            r3.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x01a5 }
            r0.<init>(r1)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x0181:
            java.lang.NoSuchMethodException r0 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r1.<init>(r2)     // Catch:{ all -> 0x01a5 }
            r1.append(r3)     // Catch:{ all -> 0x01a5 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01a5 }
            r0.<init>(r1)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x0193:
            java.lang.NoSuchMethodException r0 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r1.<init>(r3)     // Catch:{ all -> 0x01a5 }
            r1.append(r5)     // Catch:{ all -> 0x01a5 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01a5 }
            r0.<init>(r1)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x01a5:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarCompat.validateExtensionInterface():boolean");
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00050\u00050\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarCompat$FirstAttachAdapter;", "Landroid/view/View$OnAttachStateChangeListener;", "sidecarCompat", "Landroidx/window/layout/adapter/sidecar/SidecarCompat;", "activity", "Landroid/app/Activity;", "(Landroidx/window/layout/adapter/sidecar/SidecarCompat;Landroid/app/Activity;)V", "activityWeakReference", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "onViewAttachedToWindow", "", "view", "Landroid/view/View;", "onViewDetachedFromWindow", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarCompat.kt */
    private static final class FirstAttachAdapter implements View.OnAttachStateChangeListener {
        private final WeakReference<Activity> activityWeakReference;
        private final SidecarCompat sidecarCompat;

        public void onViewDetachedFromWindow(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
        }

        public FirstAttachAdapter(SidecarCompat sidecarCompat2, Activity activity) {
            Intrinsics.checkNotNullParameter(sidecarCompat2, "sidecarCompat");
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.sidecarCompat = sidecarCompat2;
            this.activityWeakReference = new WeakReference<>(activity);
        }

        public void onViewAttachedToWindow(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            view.removeOnAttachStateChangeListener(this);
            Activity activity = (Activity) this.activityWeakReference.get();
            IBinder activityWindowToken$window_release = SidecarCompat.Companion.getActivityWindowToken$window_release(activity);
            if (activity != null && activityWindowToken$window_release != null) {
                this.sidecarCompat.register(activityWindowToken$window_release, activity);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0017¨\u0006\f"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarCompat$TranslatingCallback;", "Landroidx/window/sidecar/SidecarInterface$SidecarCallback;", "(Landroidx/window/layout/adapter/sidecar/SidecarCompat;)V", "onDeviceStateChanged", "", "newDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "onWindowLayoutChanged", "windowToken", "Landroid/os/IBinder;", "newLayout", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarCompat.kt */
    public final class TranslatingCallback implements SidecarInterface.SidecarCallback {
        public TranslatingCallback() {
        }

        public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState) {
            SidecarInterface sidecar;
            Intrinsics.checkNotNullParameter(sidecarDeviceState, "newDeviceState");
            SidecarCompat sidecarCompat = SidecarCompat.this;
            for (Activity activity : SidecarCompat.this.windowListenerRegisteredContexts.values()) {
                IBinder activityWindowToken$window_release = SidecarCompat.Companion.getActivityWindowToken$window_release(activity);
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo = null;
                if (!(activityWindowToken$window_release == null || (sidecar = sidecarCompat.getSidecar()) == null)) {
                    sidecarWindowLayoutInfo = sidecar.getWindowLayoutInfo(activityWindowToken$window_release);
                }
                DistinctElementCallback access$getExtensionCallback$p = sidecarCompat.extensionCallback;
                if (access$getExtensionCallback$p != null) {
                    access$getExtensionCallback$p.onWindowLayoutChanged(activity, sidecarCompat.sidecarAdapter.translate(sidecarWindowLayoutInfo, sidecarDeviceState));
                }
            }
        }

        public void onWindowLayoutChanged(IBinder iBinder, SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
            SidecarDeviceState sidecarDeviceState;
            Intrinsics.checkNotNullParameter(iBinder, "windowToken");
            Intrinsics.checkNotNullParameter(sidecarWindowLayoutInfo, "newLayout");
            Activity activity = (Activity) SidecarCompat.this.windowListenerRegisteredContexts.get(iBinder);
            if (activity == null) {
                Log.w(SidecarCompat.TAG, "Unable to resolve activity from window token. Missing a call to #onWindowLayoutChangeListenerAdded()?");
                return;
            }
            SidecarAdapter access$getSidecarAdapter$p = SidecarCompat.this.sidecarAdapter;
            SidecarInterface sidecar = SidecarCompat.this.getSidecar();
            if (sidecar == null || (sidecarDeviceState = sidecar.getDeviceState()) == null) {
                sidecarDeviceState = new SidecarDeviceState();
            }
            WindowLayoutInfo translate = access$getSidecarAdapter$p.translate(sidecarWindowLayoutInfo, sidecarDeviceState);
            DistinctElementCallback access$getExtensionCallback$p = SidecarCompat.this.extensionCallback;
            if (access$getExtensionCallback$p != null) {
                access$getExtensionCallback$p.onWindowLayoutChanged(activity, translate);
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u0018\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0007H\u0016R\u001c\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarCompat$DistinctElementCallback;", "Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "callbackInterface", "(Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat$ExtensionCallbackInterface;)V", "activityWindowLayoutInfo", "Ljava/util/WeakHashMap;", "Landroid/app/Activity;", "Landroidx/window/layout/WindowLayoutInfo;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "clearWindowLayoutInfo", "", "activity", "onWindowLayoutChanged", "newLayout", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarCompat.kt */
    private static final class DistinctElementCallback implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        private final WeakHashMap<Activity, WindowLayoutInfo> activityWindowLayoutInfo = new WeakHashMap<>();
        private final ExtensionInterfaceCompat.ExtensionCallbackInterface callbackInterface;
        private final ReentrantLock lock = new ReentrantLock();

        public DistinctElementCallback(ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallbackInterface) {
            Intrinsics.checkNotNullParameter(extensionCallbackInterface, "callbackInterface");
            this.callbackInterface = extensionCallbackInterface;
        }

        public void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(windowLayoutInfo, "newLayout");
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                if (!Intrinsics.areEqual((Object) windowLayoutInfo, (Object) this.activityWindowLayoutInfo.get(activity))) {
                    WindowLayoutInfo put = this.activityWindowLayoutInfo.put(activity, windowLayoutInfo);
                    lock2.unlock();
                    this.callbackInterface.onWindowLayoutChanged(activity, windowLayoutInfo);
                }
            } finally {
                lock2.unlock();
            }
        }

        public final void clearWindowLayoutInfo(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                this.activityWindowLayoutInfo.put(activity, (Object) null);
                Unit unit = Unit.INSTANCE;
            } finally {
                lock2.unlock();
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarCompat$Companion;", "", "()V", "TAG", "", "sidecarVersion", "Landroidx/window/core/Version;", "getSidecarVersion", "()Landroidx/window/core/Version;", "getActivityWindowToken", "Landroid/os/IBinder;", "activity", "Landroid/app/Activity;", "getActivityWindowToken$window_release", "getSidecarCompat", "Landroidx/window/sidecar/SidecarInterface;", "context", "Landroid/content/Context;", "getSidecarCompat$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarCompat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Version getSidecarVersion() {
            try {
                String apiVersion = SidecarProvider.getApiVersion();
                if (!TextUtils.isEmpty(apiVersion)) {
                    return Version.Companion.parse(apiVersion);
                }
                return null;
            } catch (NoClassDefFoundError | UnsupportedOperationException unused) {
                return null;
            }
        }

        public final SidecarInterface getSidecarCompat$window_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return SidecarProvider.getSidecarImpl(context.getApplicationContext());
        }

        public final IBinder getActivityWindowToken$window_release(Activity activity) {
            Window window;
            WindowManager.LayoutParams attributes;
            if (activity == null || (window = activity.getWindow()) == null || (attributes = window.getAttributes()) == null) {
                return null;
            }
            return attributes.token;
        }
    }
}
