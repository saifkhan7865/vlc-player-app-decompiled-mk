package org.videolan.vlc.repository;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import org.videolan.tools.SingletonHolder;
import org.videolan.vlc.database.WidgetDao;
import org.videolan.vlc.mediadb.models.Widget;
import org.videolan.vlc.widget.utils.WidgetCache;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0014H@¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00182\u0006\u0010\u0011\u001a\u00020\u000eJ \u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/repository/WidgetRepository;", "", "widgetDao", "Lorg/videolan/vlc/database/WidgetDao;", "(Lorg/videolan/vlc/database/WidgetDao;)V", "addWidget", "", "widget", "Lorg/videolan/vlc/mediadb/models/Widget;", "(Lorg/videolan/vlc/mediadb/models/Widget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createNew", "context", "Landroid/content/Context;", "appWidgetId", "", "(Landroid/content/Context;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWidget", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWidgets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWidget", "getWidgetFlow", "Lkotlinx/coroutines/flow/Flow;", "updateWidget", "preventCacheClear", "", "(Lorg/videolan/vlc/mediadb/models/Widget;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetRepository.kt */
public final class WidgetRepository {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final WidgetDao widgetDao;

    public WidgetRepository(WidgetDao widgetDao2) {
        Intrinsics.checkNotNullParameter(widgetDao2, "widgetDao");
        this.widgetDao = widgetDao2;
    }

    public final Object getAllWidgets(Continuation<? super List<Widget>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WidgetRepository$getAllWidgets$2(this, (Continuation<? super WidgetRepository$getAllWidgets$2>) null), continuation);
    }

    public final Object getWidget(int i, Continuation<? super Widget> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WidgetRepository$getWidget$2(this, i, (Continuation<? super WidgetRepository$getWidget$2>) null), continuation);
    }

    public final Flow<Widget> getWidgetFlow(int i) {
        return this.widgetDao.getFlow(i);
    }

    public final Object addWidget(Widget widget, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new WidgetRepository$addWidget$2(this, widget, (Continuation<? super WidgetRepository$addWidget$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public static /* synthetic */ Object updateWidget$default(WidgetRepository widgetRepository, Widget widget, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return widgetRepository.updateWidget(widget, z, continuation);
    }

    public final Object updateWidget(Widget widget, boolean z, Continuation<? super Unit> continuation) {
        if (!z) {
            WidgetCache.INSTANCE.clear(widget);
        }
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new WidgetRepository$updateWidget$2(this, widget, (Continuation<? super WidgetRepository$updateWidget$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final Object deleteWidget(int i, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new WidgetRepository$deleteWidget$2(this, i, (Continuation<? super WidgetRepository$deleteWidget$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createNew(android.content.Context r23, int r24, kotlin.coroutines.Continuation<? super org.videolan.vlc.mediadb.models.Widget> r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r25
            boolean r3 = r2 instanceof org.videolan.vlc.repository.WidgetRepository$createNew$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.repository.WidgetRepository$createNew$1 r3 = (org.videolan.vlc.repository.WidgetRepository$createNew$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.repository.WidgetRepository$createNew$1 r3 = new org.videolan.vlc.repository.WidgetRepository$createNew$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            if (r5 == 0) goto L_0x003c
            if (r5 != r6) goto L_0x0034
            java.lang.Object r1 = r3.L$0
            org.videolan.vlc.mediadb.models.Widget r1 = (org.videolan.vlc.mediadb.models.Widget) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0070
        L_0x0034:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r2)
            org.videolan.vlc.mediadb.models.Widget r2 = new org.videolan.vlc.mediadb.models.Widget
            int r5 = org.videolan.vlc.R.color.black
            int r14 = androidx.core.content.ContextCompat.getColor(r1, r5)
            int r5 = org.videolan.vlc.R.color.white
            int r15 = androidx.core.content.ContextCompat.getColor(r1, r5)
            r20 = 1
            r21 = 1
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 1
            r16 = 10
            r17 = 10
            r18 = 100
            r19 = 1
            r7 = r2
            r8 = r24
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            r3.L$0 = r2
            r3.label = r6
            java.lang.Object r1 = r0.addWidget(r2, r3)
            if (r1 != r4) goto L_0x006f
            return r4
        L_0x006f:
            r1 = r2
        L_0x0070:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.repository.WidgetRepository.createNew(android.content.Context, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/repository/WidgetRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/vlc/repository/WidgetRepository;", "Landroid/content/Context;", "()V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: WidgetRepository.kt */
    public static final class Companion extends SingletonHolder<WidgetRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
