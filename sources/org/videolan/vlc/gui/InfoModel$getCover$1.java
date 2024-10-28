package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoModel$getCover$1", f = "InfoActivity.kt", i = {0}, l = {255, 257}, m = "invokeSuspend", n = {"item"}, s = {"L$0"})
/* compiled from: InfoActivity.kt */
final class InfoModel$getCover$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ int $width;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ InfoModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoModel$getCover$1(MediaLibraryItem mediaLibraryItem, InfoModel infoModel, int i, Continuation<? super InfoModel$getCover$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.this$0 = infoModel;
        this.$width = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new InfoModel$getCover$1(this.$item, this.this$0, this.$width, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InfoModel$getCover$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x002e
            if (r1 == r3) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            java.lang.Object r0 = r10.L$0
            androidx.lifecycle.MutableLiveData r0 = (androidx.lifecycle.MutableLiveData) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0099
        L_0x0018:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0020:
            int r1 = r10.I$0
            java.lang.Object r3 = r10.L$1
            androidx.lifecycle.MutableLiveData r3 = (androidx.lifecycle.MutableLiveData) r3
            java.lang.Object r5 = r10.L$0
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0062
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.medialibrary.media.MediaLibraryItem r5 = r10.$item
            if (r5 == 0) goto L_0x00a2
            org.videolan.vlc.gui.InfoModel r11 = r10.this$0
            int r1 = r10.$width
            androidx.lifecycle.MutableLiveData r11 = r11.getCover()
            java.lang.String r6 = r5.getArtworkMrl()
            if (r6 == 0) goto L_0x006a
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            org.videolan.vlc.gui.InfoModel$getCover$1$1$1$1 r8 = new org.videolan.vlc.gui.InfoModel$getCover$1$1$1$1
            r8.<init>(r6, r1, r4)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r10.L$0 = r5
            r10.L$1 = r11
            r10.I$0 = r1
            r10.label = r3
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r10)
            if (r3 != r0) goto L_0x005f
            return r0
        L_0x005f:
            r9 = r3
            r3 = r11
            r11 = r9
        L_0x0062:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            if (r11 != 0) goto L_0x0068
            r11 = r3
            goto L_0x006a
        L_0x0068:
            r4 = r11
            goto L_0x009f
        L_0x006a:
            boolean r3 = r5 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r3 == 0) goto L_0x0072
            r3 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            goto L_0x0073
        L_0x0072:
            r3 = r4
        L_0x0073:
            if (r3 == 0) goto L_0x009e
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            int r5 = r5.getType()
            if (r5 != 0) goto L_0x009e
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            org.videolan.vlc.gui.InfoModel$getCover$1$1$2$1 r6 = new org.videolan.vlc.gui.InfoModel$getCover$1$1$2$1
            r6.<init>(r3, r1, r4)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r10.L$0 = r11
            r10.L$1 = r4
            r10.label = r2
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r10)
            if (r1 != r0) goto L_0x0097
            return r0
        L_0x0097:
            r0 = r11
            r11 = r1
        L_0x0099:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            r4 = r11
            r3 = r0
            goto L_0x009f
        L_0x009e:
            r3 = r11
        L_0x009f:
            r3.setValue(r4)
        L_0x00a2:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.InfoModel$getCover$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
