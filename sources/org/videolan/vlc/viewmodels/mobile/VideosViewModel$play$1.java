package org.videolan.vlc.viewmodels.mobile;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.VideosViewModel$play$1", f = "VideosViewModel.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideosViewModel.kt */
final class VideosViewModel$play$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $position;
    int label;
    final /* synthetic */ VideosViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideosViewModel$play$1(VideosViewModel videosViewModel, int i, Continuation<? super VideosViewModel$play$1> continuation) {
        super(2, continuation);
        this.this$0 = videosViewModel;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideosViewModel$play$1(this.this$0, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideosViewModel$play$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        r8 = (org.videolan.medialibrary.media.MediaLibraryItem) r8.get(r7.$position);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0051
        L_0x000f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r8 = r7.this$0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r8 = r8.getProvider()
            androidx.lifecycle.LiveData r8 = r8.getPagedList()
            java.lang.Object r8 = r8.getValue()
            androidx.paging.PagedList r8 = (androidx.paging.PagedList) r8
            if (r8 == 0) goto L_0x0069
            int r1 = r7.$position
            java.lang.Object r8 = r8.get(r1)
            org.videolan.medialibrary.media.MediaLibraryItem r8 = (org.videolan.medialibrary.media.MediaLibraryItem) r8
            if (r8 != 0) goto L_0x0037
            goto L_0x0069
        L_0x0037:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.viewmodels.mobile.VideosViewModel$play$1$1 r3 = new org.videolan.vlc.viewmodels.mobile.VideosViewModel$play$1$1
            r4 = 0
            r3.<init>(r8, r4)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r2
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r8)
            if (r8 != r0) goto L_0x0051
            return r0
        L_0x0051:
            r2 = r8
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0066
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r8 = r7.this$0
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            android.content.Context r1 = r8.getContext()
            r5 = 8
            r6 = 0
            r3 = 0
            r4 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r0, r1, r2, r3, r4, r5, r6)
        L_0x0066:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0069:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.mobile.VideosViewModel$play$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
