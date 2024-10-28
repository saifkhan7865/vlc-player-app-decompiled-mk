package org.videolan.vlc.viewmodels.mobile;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.VideosViewModel$addItemToPlaylist$1", f = "VideosViewModel.kt", i = {}, l = {117}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideosViewModel.kt */
final class VideosViewModel$addItemToPlaylist$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ int $position;
    int label;
    final /* synthetic */ VideosViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideosViewModel$addItemToPlaylist$1(VideosViewModel videosViewModel, int i, FragmentActivity fragmentActivity, Continuation<? super VideosViewModel$addItemToPlaylist$1> continuation) {
        super(2, continuation);
        this.this$0 = videosViewModel;
        this.$position = i;
        this.$activity = fragmentActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideosViewModel$addItemToPlaylist$1(this.this$0, this.$position, this.$activity, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideosViewModel$addItemToPlaylist$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        r6 = (org.videolan.medialibrary.media.MediaLibraryItem) r6.get(r5.$position);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0051
        L_0x000f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r6)
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r6 = r5.this$0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r6 = r6.getProvider()
            androidx.lifecycle.LiveData r6 = r6.getPagedList()
            java.lang.Object r6 = r6.getValue()
            androidx.paging.PagedList r6 = (androidx.paging.PagedList) r6
            if (r6 == 0) goto L_0x0068
            int r1 = r5.$position
            java.lang.Object r6 = r6.get(r1)
            org.videolan.medialibrary.media.MediaLibraryItem r6 = (org.videolan.medialibrary.media.MediaLibraryItem) r6
            if (r6 != 0) goto L_0x0037
            goto L_0x0068
        L_0x0037:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.viewmodels.mobile.VideosViewModel$addItemToPlaylist$1$1 r3 = new org.videolan.vlc.viewmodels.mobile.VideosViewModel$addItemToPlaylist$1$1
            r4 = 0
            r3.<init>(r6, r4)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r6 = r5
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r5.label = r2
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r6)
            if (r6 != r0) goto L_0x0051
            return r0
        L_0x0051:
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0065
            androidx.fragment.app.FragmentActivity r0 = r5.$activity
            r1 = r0
            androidx.lifecycle.LifecycleOwner r1 = (androidx.lifecycle.LifecycleOwner) r1
            boolean r1 = org.videolan.tools.KotlinExtensionsKt.isStarted(r1)
            if (r1 == 0) goto L_0x0065
            org.videolan.vlc.gui.helpers.UiTools r1 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            r1.addToPlaylist(r0, r6)
        L_0x0065:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0068:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.mobile.VideosViewModel$addItemToPlaylist$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
