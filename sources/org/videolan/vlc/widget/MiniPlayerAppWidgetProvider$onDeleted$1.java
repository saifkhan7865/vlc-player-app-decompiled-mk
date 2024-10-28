package org.videolan.vlc.widget;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$onDeleted$1", f = "MiniPlayerAppWidgetProvider.kt", i = {0}, l = {99}, m = "invokeSuspend", n = {"$this$forEach$iv"}, s = {"L$0"})
/* compiled from: MiniPlayerAppWidgetProvider.kt */
final class MiniPlayerAppWidgetProvider$onDeleted$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int[] $appWidgetIds;
    final /* synthetic */ Context $context;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MiniPlayerAppWidgetProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MiniPlayerAppWidgetProvider$onDeleted$1(int[] iArr, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, Continuation<? super MiniPlayerAppWidgetProvider$onDeleted$1> continuation) {
        super(2, continuation);
        this.$appWidgetIds = iArr;
        this.this$0 = miniPlayerAppWidgetProvider;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MiniPlayerAppWidgetProvider$onDeleted$1(this.$appWidgetIds, this.this$0, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MiniPlayerAppWidgetProvider$onDeleted$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            int r1 = r8.I$1
            int r3 = r8.I$0
            java.lang.Object r4 = r8.L$2
            android.content.Context r4 = (android.content.Context) r4
            java.lang.Object r5 = r8.L$1
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r5 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider) r5
            java.lang.Object r6 = r8.L$0
            int[] r6 = (int[]) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0057
        L_0x001f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r9)
            int[] r9 = r8.$appWidgetIds
            if (r9 == 0) goto L_0x0059
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r1 = r8.this$0
            android.content.Context r3 = r8.$context
            int r4 = r9.length
            r5 = 0
            r6 = r9
            r5 = r1
            r1 = r4
            r4 = r3
            r3 = 0
        L_0x0039:
            if (r3 >= r1) goto L_0x0059
            r9 = r6[r3]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            org.videolan.vlc.repository.WidgetRepository r7 = r5.getWidgetRepository(r4)
            r8.L$0 = r6
            r8.L$1 = r5
            r8.L$2 = r4
            r8.I$0 = r3
            r8.I$1 = r1
            r8.label = r2
            java.lang.Object r9 = r7.deleteWidget(r9, r8)
            if (r9 != r0) goto L_0x0057
            return r0
        L_0x0057:
            int r3 = r3 + r2
            goto L_0x0039
        L_0x0059:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$onDeleted$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
