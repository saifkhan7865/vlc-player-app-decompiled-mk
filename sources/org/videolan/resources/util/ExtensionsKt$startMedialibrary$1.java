package org.videolan.resources.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.CoroutineContextProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$startMedialibrary$1", f = "Extensions.kt", i = {1, 1}, l = {87, 90}, m = "invokeSuspend", n = {"prefs", "scanOpt"}, s = {"L$0", "I$0"})
/* compiled from: Extensions.kt */
final class ExtensionsKt$startMedialibrary$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineContextProvider $coroutineContextProvider;
    final /* synthetic */ boolean $firstRun;
    final /* synthetic */ boolean $parse;
    final /* synthetic */ boolean $removeDevices;
    final /* synthetic */ Context $this_startMedialibrary;
    final /* synthetic */ boolean $upgrade;
    int I$0;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtensionsKt$startMedialibrary$1(CoroutineContextProvider coroutineContextProvider, boolean z, Context context, boolean z2, boolean z3, boolean z4, Continuation<? super ExtensionsKt$startMedialibrary$1> continuation) {
        super(2, continuation);
        this.$coroutineContextProvider = coroutineContextProvider;
        this.$parse = z;
        this.$this_startMedialibrary = context;
        this.$firstRun = z2;
        this.$upgrade = z3;
        this.$removeDevices = z4;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$startMedialibrary$1(this.$coroutineContextProvider, this.$parse, this.$this_startMedialibrary, this.$firstRun, this.$upgrade, this.$removeDevices, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$startMedialibrary$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            java.lang.String r2 = "ml_scan"
            r3 = 0
            r4 = 0
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0028
            if (r1 == r6) goto L_0x0024
            if (r1 != r5) goto L_0x001c
            int r0 = r10.I$0
            java.lang.Object r1 = r10.L$0
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0083
        L_0x001c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0055
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.medialibrary.interfaces.Medialibrary r11 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            boolean r11 = r11.isStarted()
            if (r11 == 0) goto L_0x0038
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0038:
            org.videolan.tools.CoroutineContextProvider r11 = r10.$coroutineContextProvider
            kotlinx.coroutines.CoroutineDispatcher r11 = r11.getIO()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.resources.util.ExtensionsKt$startMedialibrary$1$prefs$1 r1 = new org.videolan.resources.util.ExtensionsKt$startMedialibrary$1$prefs$1
            android.content.Context r7 = r10.$this_startMedialibrary
            r1.<init>(r7, r4)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r7 = r10
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r10.label = r6
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r1, r7)
            if (r11 != r0) goto L_0x0055
            return r0
        L_0x0055:
            r1 = r11
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            org.videolan.tools.Settings r11 = org.videolan.tools.Settings.INSTANCE
            boolean r11 = r11.getShowTvUi()
            r7 = -1
            if (r11 == 0) goto L_0x0063
            r11 = 0
            goto L_0x0067
        L_0x0063:
            int r11 = r1.getInt(r2, r7)
        L_0x0067:
            boolean r8 = r10.$parse
            if (r8 == 0) goto L_0x0093
            if (r11 != r7) goto L_0x0093
            android.content.Context r7 = r10.$this_startMedialibrary
            org.videolan.tools.CoroutineContextProvider r8 = r10.$coroutineContextProvider
            r9 = r10
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r10.L$0 = r1
            r10.I$0 = r11
            r10.label = r5
            java.lang.Object r7 = org.videolan.resources.util.ExtensionsKt.dbExists(r7, r8, r9)
            if (r7 != r0) goto L_0x0081
            return r0
        L_0x0081:
            r0 = r11
            r11 = r7
        L_0x0083:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0092
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            org.videolan.tools.SettingsKt.putSingle(r1, r2, r11)
        L_0x0092:
            r11 = r0
        L_0x0093:
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "medialibrary_init"
            r0.<init>(r1)
            android.content.Context r1 = r10.$this_startMedialibrary
            android.content.Context r1 = r1.getApplicationContext()
            java.lang.String r2 = "org.videolan.vlc.MediaParsingService"
            android.content.Intent r0 = r0.setClassName(r1, r2)
            java.lang.String r1 = "setClassName(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            android.content.Context r1 = r10.$this_startMedialibrary
            java.lang.String r2 = "extra_first_run"
            boolean r7 = r10.$firstRun
            android.content.Intent r0 = r0.putExtra(r2, r7)
            java.lang.String r2 = "extra_upgrade"
            boolean r7 = r10.$upgrade
            android.content.Intent r0 = r0.putExtra(r2, r7)
            java.lang.String r2 = "extra_remove_device"
            boolean r7 = r10.$removeDevices
            android.content.Intent r0 = r0.putExtra(r2, r7)
            boolean r2 = r10.$parse
            if (r2 == 0) goto L_0x00d4
            if (r11 == r6) goto L_0x00d4
            android.content.Context r11 = r10.$this_startMedialibrary
            boolean r11 = org.videolan.resources.util.HelpersKt.canReadStorage(r11)
            if (r11 == 0) goto L_0x00d4
            r3 = 1
        L_0x00d4:
            java.lang.String r11 = "extra_parse"
            android.content.Intent r11 = r0.putExtra(r11, r3)
            java.lang.String r0 = "putExtra(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r0)
            org.videolan.resources.util.ExtensionsKt.launchForeground$default(r1, r11, r4, r5, r4)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.util.ExtensionsKt$startMedialibrary$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
