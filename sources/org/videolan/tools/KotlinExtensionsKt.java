package org.videolan.tools;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0006\u0010\u000b\u001a\u00020\f\u001a@\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012H@¢\u0006\u0002\u0010\u0015\u001a\u0012\u0010\u0016\u001a\u00020\f*\u00020\u0006H@¢\u0006\u0002\u0010\u0017\u001a\u0010\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\u001b\u001a=\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001d*\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00102\u001c\u0010 \u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012¢\u0006\u0002\u0010!\u001a\u001a\u0010\"\u001a\u00020\u001a*\u00020\u00062\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$\u001a(\u0010&\u001a\u00020\u0001*\u00020\u00062\b\b\u0001\u0010'\u001a\u00020\u00012\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\f\u001a$\u0010+\u001a\u00020\u0001*\u00020,2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\b\b\u0001\u0010/\u001a\u00020\u0001\u001a\u0014\u00100\u001a\u000201*\u00020,2\b\b\u0001\u00102\u001a\u00020\u0001\u001a#\u00103\u001a\u00020\u0001\"\u0004\b\u0000\u00104*\b\u0012\u0004\u0012\u0002H4052\u0006\u00106\u001a\u0002H4¢\u0006\u0002\u00107\u001a\f\u00108\u001a\u00020\f*\u000209H\u0002\u001a\u0012\u0010:\u001a\u00020\f*\u00020;2\u0006\u0010<\u001a\u00020\u0006\u001a\f\u0010=\u001a\u00020\f*\u00020\u0006H\u0007\u001a\n\u0010>\u001a\u00020\f*\u00020\u001b\u001a\n\u0010?\u001a\u00020\f*\u00020\u001b\u001a\n\u0010@\u001a\u00020\f*\u00020A\u001a\u001d\u0010B\u001a\u00020\f*\u0004\u0018\u00010$\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\b\u0003\u0010\u0000\u001a\n\u0010C\u001a\u00020\f*\u00020\u001b\u001a\u000e\u0010D\u001a\u0004\u0018\u000101*\u0004\u0018\u000101\u001a\u001c\u0010E\u001a\u00020\u0001*\u00020\u00062\b\u0010F\u001a\u0004\u0018\u00010$2\u0006\u0010G\u001a\u00020$\u001a\u000e\u0010H\u001a\u0004\u0018\u000101*\u0004\u0018\u000101\u001a\f\u0010I\u001a\u00020\u001a*\u0004\u0018\u00010\u001b\u001a\f\u0010J\u001a\u00020\u001a*\u0004\u0018\u00010\u001b\u001a\u0014\u0010K\u001a\u00020\u001a*\u0004\u0018\u00010\u001b2\u0006\u0010L\u001a\u00020\u0001\u001a\f\u0010M\u001a\u00020\u001a*\u0004\u0018\u00010\u001b\u001a\n\u0010N\u001a\u00020\u0001*\u00020\f\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0003¨\u0006O"}, d2 = {"dp", "", "getDp", "(I)I", "localBroadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "Landroid/content/Context;", "getLocalBroadcastManager", "(Landroid/content/Context;)Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "px", "getPx", "isAppStarted", "", "retry", "times", "delayTime", "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(IJLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAppIsForegroung", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clicks", "Lkotlinx/coroutines/flow/Flow;", "", "Landroid/view/View;", "conflatedActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlinx/coroutines/CoroutineScope;", "time", "action", "(Lkotlinx/coroutines/CoroutineScope;JLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/channels/SendChannel;", "copy", "label", "", "text", "getColorFromAttr", "attrColor", "typedValue", "Landroid/util/TypedValue;", "resolveRefs", "getDrawableOrDefault", "Landroid/content/res/Resources;", "name", "defPackage", "defaultDrawable", "getResourceUri", "Landroid/net/Uri;", "id", "getposition", "T", "", "target", "(Ljava/util/List;Ljava/lang/Object;)I", "isAppForeground", "Landroid/app/ActivityManager;", "isCallable", "Landroid/content/Intent;", "context", "isConnected", "isGone", "isInvisible", "isStarted", "Landroidx/lifecycle/LifecycleOwner;", "isValidUrl", "isVisible", "removeQuery", "resIdByName", "resIdName", "resType", "retrieveParent", "setGone", "setInvisible", "setVisibility", "visibility", "setVisible", "toInt", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: KotlinExtensions.kt */
public final class KotlinExtensionsKt {
    public static final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    public static final <T> int getposition(List<? extends T> list, T t) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int i = 0;
        for (Object areEqual : list) {
            int i2 = i + 1;
            if (Intrinsics.areEqual((Object) areEqual, (Object) t)) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    public static final boolean isAppStarted() {
        LifecycleOwner lifecycleOwner = ProcessLifecycleOwner.get();
        Intrinsics.checkNotNullExpressionValue(lifecycleOwner, "get(...)");
        return isStarted(lifecycleOwner);
    }

    public static final boolean isStarted(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<this>");
        return lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    public static final boolean isVisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 0;
    }

    public static final boolean isInvisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 4;
    }

    public static final boolean isGone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 8;
    }

    public static final void setVisibility(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    public static final void setVisible(View view) {
        setVisibility(view, 0);
    }

    public static final void setInvisible(View view) {
        setVisibility(view, 4);
    }

    public static final void setGone(View view) {
        setVisibility(view, 8);
    }

    public static final int getDp(int i) {
        return (int) (((float) i) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static final int getPx(int i) {
        return (int) (((float) i) / Resources.getSystem().getDisplayMetrics().density);
    }

    public static final SendChannel<Unit> conflatedActor(CoroutineScope coroutineScope, long j, Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        return ActorKt.actor$default(coroutineScope, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new KotlinExtensionsKt$conflatedActor$1(function1, j, (Continuation<? super KotlinExtensionsKt$conflatedActor$1>) null), 13, (Object) null);
    }

    public static /* synthetic */ SendChannel conflatedActor$default(CoroutineScope coroutineScope, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 2000;
        }
        return conflatedActor(coroutineScope, j, function1);
    }

    public static /* synthetic */ int getColorFromAttr$default(Context context, int i, TypedValue typedValue, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            typedValue = new TypedValue();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        return getColorFromAttr(context, i, typedValue, z);
    }

    public static final int getColorFromAttr(Context context, int i, TypedValue typedValue, boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(typedValue, "typedValue");
        context.getTheme().resolveAttribute(i, typedValue, z);
        return typedValue.data;
    }

    public static final void copy(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, AnnotatedPrivateKey.LABEL);
        Intrinsics.checkNotNullParameter(str2, "text");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        ClipboardManager clipboardManager = (ClipboardManager) ContextCompat.getSystemService(applicationContext, ClipboardManager.class);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(str, str2));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9 A[PHI: r14 
      PHI: (r14v2 ? extends java.lang.Object) = (r14v10 ? extends java.lang.Object), (r14v1 ? extends java.lang.Object) binds: [B:33:0x00a6, B:11:0x002c] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object retry(int r10, long r11, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r13, kotlin.coroutines.Continuation<? super java.lang.Boolean> r14) {
        /*
            boolean r0 = r14 instanceof org.videolan.tools.KotlinExtensionsKt$retry$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            org.videolan.tools.KotlinExtensionsKt$retry$1 r0 = (org.videolan.tools.KotlinExtensionsKt$retry$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            org.videolan.tools.KotlinExtensionsKt$retry$1 r0 = new org.videolan.tools.KotlinExtensionsKt$retry$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0055
            if (r2 == r5) goto L_0x0047
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00a9
        L_0x0031:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0039:
            int r10 = r0.I$1
            int r11 = r0.I$0
            long r12 = r0.J$0
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0097
        L_0x0047:
            int r10 = r0.I$1
            int r11 = r0.I$0
            long r12 = r0.J$0
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0073
        L_0x0055:
            kotlin.ResultKt.throwOnFailure(r14)
            int r10 = r10 - r5
            r14 = 0
        L_0x005a:
            if (r14 >= r10) goto L_0x009d
            r0.L$0 = r13
            r0.J$0 = r11
            r0.I$0 = r10
            r0.I$1 = r14
            r0.label = r5
            java.lang.Object r2 = r13.invoke(r0)
            if (r2 != r1) goto L_0x006d
            return r1
        L_0x006d:
            r8 = r11
            r11 = r10
            r10 = r14
            r14 = r2
            r2 = r13
            r12 = r8
        L_0x0073:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x0080
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r10
        L_0x0080:
            r6 = 0
            int r14 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r14 <= 0) goto L_0x0097
            r0.L$0 = r2
            r0.J$0 = r12
            r0.I$0 = r11
            r0.I$1 = r10
            r0.label = r4
            java.lang.Object r14 = kotlinx.coroutines.DelayKt.delay(r12, r0)
            if (r14 != r1) goto L_0x0097
            return r1
        L_0x0097:
            int r14 = r10 + 1
            r10 = r11
            r11 = r12
            r13 = r2
            goto L_0x005a
        L_0x009d:
            r10 = 0
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r14 = r13.invoke(r0)
            if (r14 != r1) goto L_0x00a9
            return r1
        L_0x00a9:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.KotlinExtensionsKt.retry(int, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object retry$default(int i, long j, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 3;
        }
        if ((i2 & 2) != 0) {
            j = 500;
        }
        return retry(i, j, function1, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object awaitAppIsForegroung(android.content.Context r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            boolean r0 = r6 instanceof org.videolan.tools.KotlinExtensionsKt$awaitAppIsForegroung$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.tools.KotlinExtensionsKt$awaitAppIsForegroung$1 r0 = (org.videolan.tools.KotlinExtensionsKt$awaitAppIsForegroung$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.tools.KotlinExtensionsKt$awaitAppIsForegroung$1 r0 = new org.videolan.tools.KotlinExtensionsKt$awaitAppIsForegroung$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            int r5 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            android.app.ActivityManager r4 = (android.app.ActivityManager) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0075
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            android.content.Context r5 = r5.getApplicationContext()
            java.lang.String r6 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.lang.Class<android.app.ActivityManager> r6 = android.app.ActivityManager.class
            java.lang.Object r5 = androidx.core.content.ContextCompat.getSystemService(r5, r6)
            android.app.ActivityManager r5 = (android.app.ActivityManager) r5
            r6 = 0
            if (r5 != 0) goto L_0x0056
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            return r5
        L_0x0056:
            r2 = 2
            r4 = r5
            r5 = 0
        L_0x0059:
            if (r5 >= r2) goto L_0x0077
            boolean r6 = isAppForeground(r4)
            if (r6 == 0) goto L_0x0066
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        L_0x0066:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.I$1 = r5
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.YieldKt.yield(r0)
            if (r6 != r1) goto L_0x0075
            return r1
        L_0x0075:
            int r5 = r5 + r3
            goto L_0x0059
        L_0x0077:
            boolean r5 = isAppForeground(r4)
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.KotlinExtensionsKt.awaitAppIsForegroung(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean isAppForeground(ActivityManager activityManager) {
        try {
            return activityManager.getRunningAppProcesses().get(0).importance <= 100;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final boolean isValidUrl(String str) {
        CharSequence charSequence = str;
        return (charSequence == null || charSequence.length() == 0 || !Patterns.WEB_URL.matcher(charSequence).matches()) ? false : true;
    }

    public static final Flow<Unit> clicks(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return FlowKt.callbackFlow(new KotlinExtensionsKt$clicks$1(view, (Continuation<? super KotlinExtensionsKt$clicks$1>) null));
    }

    public static final LocalBroadcastManager getLocalBroadcastManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        return instance;
    }

    public static final Uri getResourceUri(Resources resources, int i) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        Uri build = new Uri.Builder().scheme("android.resource").authority(resources.getResourcePackageName(i)).appendPath(resources.getResourceTypeName(i)).appendPath(resources.getResourceEntryName(i)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final Uri retrieveParent(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            Uri.Builder authority = new Uri.Builder().scheme(uri.getScheme()).authority(uri.getAuthority());
            List<String> pathSegments = uri.getPathSegments();
            Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
            for (T t : CollectionsKt.dropLast(pathSegments, 1)) {
                if (!Intrinsics.areEqual((Object) t, (Object) uri.getLastPathSegment())) {
                    authority.appendPath(t);
                }
            }
            return authority.build();
        } catch (Exception unused) {
            return null;
        }
    }

    public static final Uri removeQuery(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            Uri.Builder authority = new Uri.Builder().scheme(uri.getScheme()).authority(uri.getAuthority());
            List<String> pathSegments = uri.getPathSegments();
            Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
            for (String appendPath : pathSegments) {
                authority.appendPath(appendPath);
            }
            return authority.build();
        } catch (Exception unused) {
            return null;
        }
    }

    public static final boolean isCallable(Intent intent, Context context) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(...)");
        return !queryIntentActivities.isEmpty();
    }

    public static final int getDrawableOrDefault(Resources resources, String str, String str2, int i) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "defPackage");
        int identifier = resources.getIdentifier(str, Constants.DRAWABLE, str2);
        return identifier == 0 ? i : identifier;
    }

    public static final int resIdByName(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str2, "resType");
        if (str != null) {
            return context.getResources().getIdentifier(str, str2, context.getPackageName());
        }
        throw new Resources.NotFoundException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
        r2 = r2.getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isConnected(android.content.Context r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.Class<android.net.ConnectivityManager> r0 = android.net.ConnectivityManager.class
            java.lang.Object r2 = androidx.core.content.ContextCompat.getSystemService(r2, r0)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            r0 = 0
            if (r2 == 0) goto L_0x001e
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()
            if (r2 == 0) goto L_0x001e
            boolean r2 = r2.isConnected()
            r1 = 1
            if (r2 != r1) goto L_0x001e
            r0 = 1
        L_0x001e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.KotlinExtensionsKt.isConnected(android.content.Context):boolean");
    }
}
