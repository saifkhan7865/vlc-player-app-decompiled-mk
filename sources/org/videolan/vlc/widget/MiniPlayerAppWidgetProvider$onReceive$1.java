package org.videolan.vlc.widget;

import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.repository.WidgetRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$onReceive$1", f = "MiniPlayerAppWidgetProvider.kt", i = {1, 3}, l = {115, 116, 118, 119}, m = "invokeSuspend", n = {"it", "it"}, s = {"L$4", "L$0"})
/* compiled from: MiniPlayerAppWidgetProvider.kt */
final class MiniPlayerAppWidgetProvider$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Intent $intent;
    final /* synthetic */ boolean $partial;
    final /* synthetic */ WidgetRepository $widgetRepository;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;
    final /* synthetic */ MiniPlayerAppWidgetProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MiniPlayerAppWidgetProvider$onReceive$1(Intent intent, WidgetRepository widgetRepository, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, boolean z, Continuation<? super MiniPlayerAppWidgetProvider$onReceive$1> continuation) {
        super(2, continuation);
        this.$intent = intent;
        this.$widgetRepository = widgetRepository;
        this.this$0 = miniPlayerAppWidgetProvider;
        this.$context = context;
        this.$partial = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MiniPlayerAppWidgetProvider$onReceive$1(this.$intent, this.$widgetRepository, this.this$0, this.$context, this.$partial, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MiniPlayerAppWidgetProvider$onReceive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00aa, code lost:
        r2 = r7.getWidgetId();
        r11.L$0 = r15;
        r11.L$1 = r10;
        r11.L$2 = r8;
        r11.L$3 = r9;
        r11.L$4 = r7;
        r11.L$5 = r10;
        r11.L$6 = r15;
        r11.Z$0 = r14;
        r11.label = 2;
        r19 = r7;
        r16 = r8;
        r20 = r9;
        r17 = r10;
        r0 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.layoutWidget$default(r15, r10, r2, r8, false, (android.graphics.Bitmap) null, (androidx.palette.graphics.Palette) null, false, r21, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e0, code lost:
        if (r0 != r12) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e2, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e3, code lost:
        r1 = r15;
        r7 = r1;
        r5 = r16;
        r2 = r17;
        r6 = r2;
        r3 = r19;
        r4 = r20;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
        /*
            r21 = this;
            r11 = r21
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r11.label
            r1 = 4
            r2 = 3
            r13 = 2
            r3 = 1
            if (r0 == 0) goto L_0x0068
            if (r0 == r3) goto L_0x0062
            if (r0 == r13) goto L_0x003c
            if (r0 == r2) goto L_0x0035
            if (r0 != r1) goto L_0x002d
            boolean r0 = r11.Z$0
            java.lang.Object r1 = r11.L$2
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r1 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider) r1
            java.lang.Object r2 = r11.L$1
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Object r3 = r11.L$0
            org.videolan.vlc.mediadb.models.Widget r3 = (org.videolan.vlc.mediadb.models.Widget) r3
            kotlin.ResultKt.throwOnFailure(r22)
            r14 = r1
            r1 = r0
            r0 = r22
            goto L_0x014d
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r22)
            r0 = r22
            goto L_0x0111
        L_0x003c:
            boolean r0 = r11.Z$0
            java.lang.Object r1 = r11.L$6
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r1 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider) r1
            java.lang.Object r2 = r11.L$5
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Object r3 = r11.L$4
            org.videolan.vlc.mediadb.models.Widget r3 = (org.videolan.vlc.mediadb.models.Widget) r3
            java.lang.Object r4 = r11.L$3
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r11.L$2
            android.content.Intent r5 = (android.content.Intent) r5
            java.lang.Object r6 = r11.L$1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.Object r7 = r11.L$0
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r7 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider) r7
            kotlin.ResultKt.throwOnFailure(r22)
            r14 = r0
            r0 = r22
            goto L_0x00ee
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r22)
            r0 = r22
            goto L_0x0084
        L_0x0068:
            kotlin.ResultKt.throwOnFailure(r22)
            android.content.Intent r0 = r11.$intent
            java.lang.String r4 = "ID"
            r5 = -1
            int r0 = r0.getIntExtra(r4, r5)
            if (r0 != r5) goto L_0x0103
            org.videolan.vlc.repository.WidgetRepository r0 = r11.$widgetRepository
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.label = r3
            java.lang.Object r0 = r0.getAllWidgets(r1)
            if (r0 != r12) goto L_0x0084
            return r12
        L_0x0084:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r1 = r11.this$0
            android.content.Context r2 = r11.$context
            android.content.Intent r3 = r11.$intent
            boolean r4 = r11.$partial
            java.util.Iterator r0 = r0.iterator()
            r9 = r0
            r15 = r1
            r10 = r2
            r8 = r3
            r14 = r4
        L_0x0097:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x0156
            java.lang.Object r0 = r9.next()
            r7 = r0
            org.videolan.vlc.mediadb.models.Widget r7 = (org.videolan.vlc.mediadb.models.Widget) r7
            int r0 = r7.getWidgetId()
            if (r0 == 0) goto L_0x00fc
            int r2 = r7.getWidgetId()
            r11.L$0 = r15
            r11.L$1 = r10
            r11.L$2 = r8
            r11.L$3 = r9
            r11.L$4 = r7
            r11.L$5 = r10
            r11.L$6 = r15
            r11.Z$0 = r14
            r11.label = r13
            r4 = 0
            r5 = 0
            r6 = 0
            r16 = 0
            r17 = 120(0x78, float:1.68E-43)
            r18 = 0
            r0 = r15
            r1 = r10
            r3 = r8
            r19 = r7
            r7 = r16
            r16 = r8
            r8 = r21
            r20 = r9
            r9 = r17
            r17 = r10
            r10 = r18
            java.lang.Object r0 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.layoutWidget$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r0 != r12) goto L_0x00e3
            return r12
        L_0x00e3:
            r1 = r15
            r7 = r1
            r5 = r16
            r2 = r17
            r6 = r2
            r3 = r19
            r4 = r20
        L_0x00ee:
            android.widget.RemoteViews r0 = (android.widget.RemoteViews) r0
            int r3 = r3.getWidgetId()
            r1.applyUpdate(r2, r0, r14, r3)
            r9 = r4
            r8 = r5
            r10 = r6
            r15 = r7
            goto L_0x0097
        L_0x00fc:
            r16 = r8
            r20 = r9
            r17 = r10
            goto L_0x0097
        L_0x0103:
            org.videolan.vlc.repository.WidgetRepository r3 = r11.$widgetRepository
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r11.label = r2
            java.lang.Object r0 = r3.getWidget(r0, r4)
            if (r0 != r12) goto L_0x0111
            return r12
        L_0x0111:
            r13 = r0
            org.videolan.vlc.mediadb.models.Widget r13 = (org.videolan.vlc.mediadb.models.Widget) r13
            if (r13 == 0) goto L_0x0156
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r14 = r11.this$0
            android.content.Context r15 = r11.$context
            android.content.Intent r3 = r11.$intent
            boolean r10 = r11.$partial
            int r0 = r13.getWidgetId()
            if (r0 == 0) goto L_0x0156
            int r2 = r13.getWidgetId()
            r11.L$0 = r13
            r11.L$1 = r15
            r11.L$2 = r14
            r11.Z$0 = r10
            r11.label = r1
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r9 = 120(0x78, float:1.68E-43)
            r16 = 0
            r0 = r14
            r1 = r15
            r8 = r21
            r17 = r10
            r10 = r16
            java.lang.Object r0 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.layoutWidget$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r0 != r12) goto L_0x0149
            return r12
        L_0x0149:
            r3 = r13
            r2 = r15
            r1 = r17
        L_0x014d:
            android.widget.RemoteViews r0 = (android.widget.RemoteViews) r0
            int r3 = r3.getWidgetId()
            r14.applyUpdate(r2, r0, r1, r3)
        L_0x0156:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$onReceive$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
