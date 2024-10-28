package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoModel$checkFile$1", f = "InfoActivity.kt", i = {0, 1, 1, 2}, l = {277, 279, 283}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "itemFile", "itemFile"}, s = {"L$0", "L$0", "L$1", "L$0"})
/* compiled from: InfoActivity.kt */
final class InfoModel$checkFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ InfoModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoModel$checkFile$1(InfoModel infoModel, MediaWrapper mediaWrapper, Continuation<? super InfoModel$checkFile$1> continuation) {
        super(2, continuation);
        this.this$0 = infoModel;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InfoModel$checkFile$1 infoModel$checkFile$1 = new InfoModel$checkFile$1(this.this$0, this.$mw, continuation);
        infoModel$checkFile$1.L$0 = obj;
        return infoModel$checkFile$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InfoModel$checkFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0037
            if (r1 == r4) goto L_0x002f
            if (r1 == r3) goto L_0x0023
            if (r1 != r2) goto L_0x001b
            java.lang.Object r0 = r9.L$0
            java.io.File r0 = (java.io.File) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ab
        L_0x001b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0023:
            java.lang.Object r1 = r9.L$1
            java.io.File r1 = (java.io.File) r1
            java.lang.Object r3 = r9.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0081
        L_0x002f:
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x005e
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.gui.InfoModel$checkFile$1$itemFile$1 r6 = new org.videolan.vlc.gui.InfoModel$checkFile$1$itemFile$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r9.$mw
            r6.<init>(r7, r5)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r9.L$0 = r10
            r9.label = r4
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r6, r7)
            if (r1 != r0) goto L_0x005b
            return r0
        L_0x005b:
            r8 = r1
            r1 = r10
            r10 = r8
        L_0x005e:
            java.io.File r10 = (java.io.File) r10
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            org.videolan.vlc.gui.InfoModel$checkFile$1$1 r6 = new org.videolan.vlc.gui.InfoModel$checkFile$1$1
            r6.<init>(r10, r5)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r9.L$0 = r1
            r9.L$1 = r10
            r9.label = r3
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r4, r6, r7)
            if (r3 != r0) goto L_0x007d
            return r0
        L_0x007d:
            r8 = r1
            r1 = r10
            r10 = r3
            r3 = r8
        L_0x0081:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00c0
            boolean r10 = kotlinx.coroutines.CoroutineScopeKt.isActive(r3)
            if (r10 != 0) goto L_0x0090
            goto L_0x00c0
        L_0x0090:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = r9.$mw
            int r10 = r10.getType()
            if (r10 != 0) goto L_0x00ac
            org.videolan.vlc.gui.InfoModel r10 = r9.this$0
            r3 = r9
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r9.L$0 = r1
            r9.L$1 = r5
            r9.label = r2
            java.lang.Object r10 = r10.checkSubtitles(r1, r3)
            if (r10 != r0) goto L_0x00aa
            return r0
        L_0x00aa:
            r0 = r1
        L_0x00ab:
            r1 = r0
        L_0x00ac:
            org.videolan.vlc.gui.InfoModel r10 = r9.this$0
            androidx.lifecycle.MutableLiveData r10 = r10.getSizeText()
            long r0 = r1.length()
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)
            r10.setValue(r0)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00c0:
            org.videolan.vlc.gui.InfoModel r10 = r9.this$0
            androidx.lifecycle.MutableLiveData r10 = r10.getSizeText()
            r0 = -1
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)
            r10.setValue(r0)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.InfoModel$checkFile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
