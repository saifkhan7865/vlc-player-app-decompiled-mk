package org.videolan.resources.util;

import android.app.Application;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.CoroutineContextProvider;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001aB\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00052\u0014\b\u0004\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\b0\u0007HH¢\u0006\u0002\u0010\t\u001a:\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001a\b\u0004\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000b\u0012\u0004\u0012\u00020\f0\u0007HH¢\u0006\u0002\u0010\r\u001a\u000e\u0010\u000e\u001a\u00020\fHH¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0010\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0005H@¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\b*\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0002\u0010\u0016\u001a5\u0010\u0017\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00132\u0019\b\u0004\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u00010\u0007¢\u0006\u0002\b\u0019HH¢\u0006\u0002\u0010\u001a\u001a\u001e\u0010\u001b\u001a\u00020\u001c*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\n\u0010 \u001a\u00020!\"\u00020\"\u001a\"\u0010#\u001a\u00020\f*\u00020\u00132\u0006\u0010$\u001a\u00020%2\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\f0&\u001a(\u0010'\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020(*\u00020%2\u0006\u0010)\u001a\u00020\u001fH\b¢\u0006\u0002\u0010*\u001a(\u0010'\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020(*\u00020+2\u0006\u0010)\u001a\u00020\u001fH\b¢\u0006\u0002\u0010,\u001a.\u0010-\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010.\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020(*\u00020%2\u0006\u0010)\u001a\u00020\u001fH\b¢\u0006\u0002\u0010/\u001a.\u0010-\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010.\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020(*\u00020+2\u0006\u0010)\u001a\u00020\u001fH\b¢\u0006\u0002\u00100\u001a5\u00101\u001a\u0016\u0012\u0004\u0012\u0002H\u0001\u0018\u000102j\n\u0012\u0004\u0012\u0002H\u0001\u0018\u0001`3\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020(*\u00020%2\u0006\u0010)\u001a\u00020\u001fH\b\u001a5\u00101\u001a\u0016\u0012\u0004\u0012\u0002H\u0001\u0018\u000102j\n\u0012\u0004\u0012\u0002H\u0001\u0018\u0001`3\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020(*\u00020+2\u0006\u0010)\u001a\u00020\u001fH\b\u001a$\u00104\u001a\u00020\f*\u00020\u00132\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\bH\u0007\u001a\n\u0010:\u001a\u00020\f*\u00020\u0013\u001a\n\u0010;\u001a\u00020<*\u00020\u0013\u001a\"\u0010=\u001a\u00020\f*\u00020>2\u0006\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\"\u001a<\u0010C\u001a\u00020D*\u00020\u00132\b\b\u0002\u0010E\u001a\u00020\b2\b\b\u0002\u0010F\u001a\u00020\b2\b\b\u0002\u0010G\u001a\u00020\b2\b\b\u0002\u0010H\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u001a\n\u0010I\u001a\u00020\f*\u00020\u0013\u001a\u0014\u0010J\u001a\u00020\f*\u00020>2\b\b\u0002\u0010K\u001a\u00020\b\u001a\n\u0010L\u001a\u00020\f*\u00020\u0013¨\u0006M"}, d2 = {"observeLiveDataUntil", "T", "timeout", "", "data", "Landroidx/lifecycle/LiveData;", "block", "Lkotlin/Function1;", "", "(JLandroidx/lifecycle/LiveData;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suspendCoroutineWithTimeout", "Lkotlinx/coroutines/CancellableContinuation;", "", "(JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForML", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "await", "(Landroidx/lifecycle/LiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dbExists", "Landroid/content/Context;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Landroid/content/Context;Lorg/videolan/tools/CoroutineContextProvider;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromMl", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "Lkotlin/ExtensionFunctionType;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPackageInfoCompat", "Landroid/content/pm/PackageInfo;", "Landroid/content/pm/PackageManager;", "packageName", "", "flagArgs", "", "", "launchForeground", "intent", "Landroid/content/Intent;", "Lkotlin/Function0;", "parcelable", "Landroid/os/Parcelable;", "key", "(Landroid/content/Intent;Ljava/lang/String;)Landroid/os/Parcelable;", "Landroid/os/Bundle;", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/Parcelable;", "parcelableArray", "", "(Landroid/content/Intent;Ljava/lang/String;)[Landroid/os/Parcelable;", "(Landroid/os/Bundle;Ljava/lang/String;)[Landroid/os/Parcelable;", "parcelableList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "registerReceiverCompat", "receiver", "Landroid/content/BroadcastReceiver;", "filter", "Landroid/content/IntentFilter;", "exported", "restartRemoteAccess", "retrieveApplication", "Landroid/app/Application;", "startForegroundCompat", "Landroid/app/Service;", "id", "notification", "Landroid/app/Notification;", "foregroundServiceType", "startMedialibrary", "Lkotlinx/coroutines/Job;", "firstRun", "upgrade", "parse", "removeDevices", "startRemoteAccess", "stopForegroundCompat", "removeNotification", "stopRemoteAccess", "resources_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Extensions.kt */
public final class ExtensionsKt {
    public static final /* synthetic */ <T> Object getFromMl(Context context, Function1<? super Medialibrary, ? extends T> function1, Continuation<? super T> continuation) {
        Intrinsics.needClassReification();
        InlineMarker.mark(0);
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new ExtensionsKt$getFromMl$2(function1, context, (Continuation<? super ExtensionsKt$getFromMl$2>) null), continuation);
        InlineMarker.mark(1);
        return withContext;
    }

    public static final Object waitForML(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new ExtensionsKt$waitForML$2((Continuation<? super ExtensionsKt$waitForML$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private static final Object waitForML$$forInline(Continuation<? super Unit> continuation) {
        InlineMarker.mark(0);
        BuildersKt.withContext(Dispatchers.getIO(), new ExtensionsKt$waitForML$2((Continuation<? super ExtensionsKt$waitForML$2>) null), continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static final Job startMedialibrary(Context context, boolean z, boolean z2, boolean z3, boolean z4, CoroutineContextProvider coroutineContextProvider) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContextProvider, "coroutineContextProvider");
        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new ExtensionsKt$startMedialibrary$1(coroutineContextProvider, z3, context, z, z2, z4, (Continuation<? super ExtensionsKt$startMedialibrary$1>) null), 3, (Object) null);
    }

    public static /* synthetic */ Job startMedialibrary$default(Context context, boolean z, boolean z2, boolean z3, boolean z4, CoroutineContextProvider coroutineContextProvider, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = true;
        }
        if ((i & 8) != 0) {
            z4 = false;
        }
        if ((i & 16) != 0) {
            coroutineContextProvider = new CoroutineContextProvider();
        }
        return startMedialibrary(context, z, z2, z3, z4, coroutineContextProvider);
    }

    public static final Object dbExists(Context context, CoroutineContextProvider coroutineContextProvider, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(coroutineContextProvider.getIO(), new ExtensionsKt$dbExists$2(context, (Continuation<? super ExtensionsKt$dbExists$2>) null), continuation);
    }

    public static /* synthetic */ Object dbExists$default(Context context, CoroutineContextProvider coroutineContextProvider, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContextProvider = new CoroutineContextProvider();
        }
        return dbExists(context, coroutineContextProvider, continuation);
    }

    public static /* synthetic */ void launchForeground$default(Context context, Intent intent, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = ExtensionsKt$launchForeground$1.INSTANCE;
        }
        launchForeground(context, intent, function0);
    }

    public static final void launchForeground(Context context, Intent intent, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(function0, "block");
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new ExtensionsKt$launchForeground$2(intent, context, function0, (Continuation<? super ExtensionsKt$launchForeground$2>) null), 2, (Object) null);
    }

    public static final /* synthetic */ <T extends Parcelable> T parcelable(Intent intent, String str) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (Build.VERSION.SDK_INT >= 33) {
            Intrinsics.reifiedOperationMarker(4, "T");
            Class<Parcelable> cls = Parcelable.class;
            Class cls2 = cls;
            return (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, str, (Class) cls);
        }
        T parcelableExtra = intent.getParcelableExtra(str);
        Intrinsics.reifiedOperationMarker(2, "T");
        Parcelable parcelable = (Parcelable) parcelableExtra;
        Parcelable parcelable2 = (Parcelable) parcelableExtra;
        return parcelableExtra;
    }

    public static final /* synthetic */ <T extends Parcelable> T parcelable(Bundle bundle, String str) {
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (Build.VERSION.SDK_INT >= 33) {
            Intrinsics.reifiedOperationMarker(4, "T");
            Class<Parcelable> cls = Parcelable.class;
            Class cls2 = cls;
            return (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, str, (Class) cls);
        }
        T parcelable = bundle.getParcelable(str);
        Intrinsics.reifiedOperationMarker(2, "T");
        Parcelable parcelable2 = (Parcelable) parcelable;
        Parcelable parcelable3 = (Parcelable) parcelable;
        return parcelable;
    }

    public static final /* synthetic */ <T extends Parcelable> ArrayList<T> parcelableList(Intent intent, String str) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (Build.VERSION.SDK_INT < 33) {
            return intent.getParcelableArrayListExtra(str);
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<Parcelable> cls = Parcelable.class;
        Class cls2 = cls;
        return AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, str, (Class) cls);
    }

    public static final /* synthetic */ <T extends Parcelable> ArrayList<T> parcelableList(Bundle bundle, String str) {
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (Build.VERSION.SDK_INT < 33) {
            return bundle.getParcelableArrayList(str);
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<Parcelable> cls = Parcelable.class;
        Class cls2 = cls;
        return AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, str, (Class) cls);
    }

    public static final /* synthetic */ <T extends Parcelable> T[] parcelableArray(Intent intent, String str) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (Build.VERSION.SDK_INT < 33) {
            return intent.getParcelableArrayExtra(str);
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<Parcelable> cls = Parcelable.class;
        Class cls2 = cls;
        return (Parcelable[]) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, str, (Class) cls);
    }

    public static final /* synthetic */ <T extends Parcelable> T[] parcelableArray(Bundle bundle, String str) {
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (Build.VERSION.SDK_INT < 33) {
            return bundle.getParcelableArray(str);
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<Parcelable> cls = Parcelable.class;
        Class cls2 = cls;
        return (Parcelable[]) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, str, (Class) cls);
    }

    public static /* synthetic */ void stopForegroundCompat$default(Service service, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        stopForegroundCompat(service, z);
    }

    public static final void stopForegroundCompat(Service service, boolean z) {
        Intrinsics.checkNotNullParameter(service, "<this>");
        if (Build.VERSION.SDK_INT >= 24) {
            service.stopForeground(z ? 1 : 2);
        } else {
            service.stopForeground(z);
        }
    }

    public static final void startForegroundCompat(Service service, int i, Notification notification, int i2) {
        Intrinsics.checkNotNullParameter(service, "<this>");
        Intrinsics.checkNotNullParameter(notification, "notification");
        if (Build.VERSION.SDK_INT >= 29) {
            service.startForeground(i, notification, i2);
        } else {
            service.startForeground(i, notification);
        }
    }

    public static final void registerReceiverCompat(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(broadcastReceiver, "receiver");
        Intrinsics.checkNotNullParameter(intentFilter, "filter");
        if (Build.VERSION.SDK_INT >= 33) {
            Intent unused = context.registerReceiver(broadcastReceiver, intentFilter, z ? 2 : 4);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static final void startRemoteAccess(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intent className = new Intent(Constants.ACTION_INIT).setClassName(context.getApplicationContext(), Constants.REMOTE_ACCESS_SERVICE);
        Intrinsics.checkNotNullExpressionValue(className, "setClassName(...)");
        launchForeground$default(context, className, (Function0) null, 2, (Object) null);
    }

    public static final void stopRemoteAccess(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intent intent = new Intent(Constants.ACTION_STOP_SERVER);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
        Intent className = new Intent(Constants.ACTION_INIT).setClassName(context.getApplicationContext(), Constants.REMOTE_ACCESS_SERVICE);
        Intrinsics.checkNotNullExpressionValue(className, "setClassName(...)");
        context.stopService(className);
    }

    public static final void restartRemoteAccess(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intent intent = new Intent(Constants.ACTION_RESTART_SERVER);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static final <T> Object await(LiveData<T> liveData, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new ExtensionsKt$await$2(liveData, (Continuation<? super ExtensionsKt$await$2>) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object suspendCoroutineWithTimeout(long r5, kotlin.jvm.functions.Function1<? super kotlinx.coroutines.CancellableContinuation<? super T>, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super T> r8) {
        /*
            boolean r0 = r8 instanceof org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$1 r0 = (org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$1 r0 = new org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0052
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$ObjectRef r8 = new kotlin.jvm.internal.Ref$ObjectRef
            r8.<init>()
            org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$2 r2 = new org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$2
            r4 = 0
            r2.<init>(r8, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r5, r2, r0)
            if (r5 != r1) goto L_0x0051
            return r1
        L_0x0051:
            r5 = r8
        L_0x0052:
            T r5 = r5.element
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.util.ExtensionsKt.suspendCoroutineWithTimeout(long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final <T> Object suspendCoroutineWithTimeout$$forInline(long j, Function1<? super CancellableContinuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        InlineMarker.mark(0);
        TimeoutKt.withTimeoutOrNull(j, new ExtensionsKt$suspendCoroutineWithTimeout$2(objectRef, function1, (Continuation<? super ExtensionsKt$suspendCoroutineWithTimeout$2>) null), continuation);
        InlineMarker.mark(1);
        return objectRef.element;
    }

    public static final <T> Object observeLiveDataUntil(long j, LiveData<T> liveData, Function1<? super T, Boolean> function1, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new ExtensionsKt$observeLiveDataUntil$2(j, liveData, function1, (Continuation<? super ExtensionsKt$observeLiveDataUntil$2>) null), continuation);
    }

    private static final <T> Object observeLiveDataUntil$$forInline(long j, LiveData<T> liveData, Function1<? super T, Boolean> function1, Continuation<? super T> continuation) {
        InlineMarker.mark(0);
        Object withContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new ExtensionsKt$observeLiveDataUntil$2(j, liveData, function1, (Continuation<? super ExtensionsKt$observeLiveDataUntil$2>) null), continuation);
        InlineMarker.mark(1);
        return withContext;
    }

    public static final Application retrieveApplication(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
            return retrieveApplication(baseContext);
        }
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        return (Application) applicationContext;
    }

    public static final PackageInfo getPackageInfoCompat(PackageManager packageManager, String str, int... iArr) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(iArr, "flagArgs");
        int i = 0;
        for (int i2 : iArr) {
            i |= i2;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            PackageInfo m = packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of((long) i));
            Intrinsics.checkNotNull(m);
            return m;
        }
        PackageInfo packageInfo = packageManager.getPackageInfo(str, i);
        Intrinsics.checkNotNull(packageInfo);
        return packageInfo;
    }
}
