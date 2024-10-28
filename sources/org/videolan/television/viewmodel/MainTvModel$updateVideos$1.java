package org.videolan.television.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$updateVideos$1", f = "MainTvModel.kt", i = {1, 2, 2, 3, 3, 3}, l = {158, 159, 340, 342}, m = "invokeSuspend", n = {"allMovies", "allMovies", "allTvshows", "allMovies", "allTvshows", "videoNb"}, s = {"I$0", "I$0", "I$1", "I$0", "I$1", "I$2"})
/* compiled from: MainTvModel.kt */
final class MainTvModel$updateVideos$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$updateVideos$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$updateVideos$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$updateVideos$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$updateVideos$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00f5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0125 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0194  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<kotlin.collections.List<org.videolan.medialibrary.media.MediaLibraryItem>>"
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 0
            r8 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r8) goto L_0x0044
            if (r2 == r6) goto L_0x003b
            if (r2 == r5) goto L_0x002f
            if (r2 != r4) goto L_0x0027
            int r1 = r0.I$2
            int r2 = r0.I$1
            int r4 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r18)
            r6 = r4
            r4 = r18
            goto L_0x0127
        L_0x0027:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x002f:
            int r2 = r0.I$1
            int r5 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r18)
            r6 = r5
            r5 = r18
            goto L_0x00fb
        L_0x003b:
            int r2 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r18)
            r6 = r18
            goto L_0x00cd
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r18)
            r2 = r18
            goto L_0x00aa
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r18)
            org.videolan.vlc.util.Permissions r2 = org.videolan.vlc.util.Permissions.INSTANCE
            org.videolan.television.viewmodel.MainTvModel r9 = r0.this$0
            android.content.Context r9 = r9.getContext()
            boolean r2 = r2.canReadStorage(r9)
            if (r2 != 0) goto L_0x008f
            org.videolan.television.viewmodel.MainTvModel r1 = r0.this$0
            androidx.lifecycle.LiveData r1 = r1.getVideos()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r3)
            androidx.lifecycle.MutableLiveData r1 = (androidx.lifecycle.MutableLiveData) r1
            org.videolan.medialibrary.media.DummyItem r2 = new org.videolan.medialibrary.media.DummyItem
            org.videolan.television.viewmodel.MainTvModel r3 = r0.this$0
            android.content.Context r3 = r3.getContext()
            int r4 = org.videolan.vlc.R.string.permission_media
            java.lang.String r3 = r3.getString(r4)
            org.videolan.television.viewmodel.MainTvModel r4 = r0.this$0
            android.content.Context r4 = r4.getContext()
            int r5 = org.videolan.vlc.R.string.permission_ask_again
            java.lang.String r4 = r4.getString(r5)
            r5 = 35
            r2.<init>(r5, r3, r4)
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r1.setValue(r2)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x008f:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.television.viewmodel.MainTvModel$updateVideos$1$allMovies$1 r9 = new org.videolan.television.viewmodel.MainTvModel$updateVideos$1$allMovies$1
            org.videolan.television.viewmodel.MainTvModel r10 = r0.this$0
            r9.<init>(r10, r7)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r0.label = r8
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r2, r9, r10)
            if (r2 != r1) goto L_0x00aa
            return r1
        L_0x00aa:
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.television.viewmodel.MainTvModel$updateVideos$1$allTvshows$1 r10 = new org.videolan.television.viewmodel.MainTvModel$updateVideos$1$allTvshows$1
            org.videolan.television.viewmodel.MainTvModel r11 = r0.this$0
            r10.<init>(r11, r7)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r0.I$0 = r2
            r0.label = r6
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r11)
            if (r6 != r1) goto L_0x00cd
            return r1
        L_0x00cd:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            org.videolan.television.viewmodel.MainTvModel r9 = r0.this$0
            android.content.Context r9 = r9.getContext()
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.television.viewmodel.MainTvModel$updateVideos$1$invokeSuspend$$inlined$getFromMl$1 r11 = new org.videolan.television.viewmodel.MainTvModel$updateVideos$1$invokeSuspend$$inlined$getFromMl$1
            r11.<init>(r9, r7)
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r0.I$0 = r2
            r0.I$1 = r6
            r0.label = r5
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r10, r11, r9)
            if (r5 != r1) goto L_0x00f6
            return r1
        L_0x00f6:
            r16 = r6
            r6 = r2
            r2 = r16
        L_0x00fb:
            java.lang.Number r5 = (java.lang.Number) r5
            int r5 = r5.intValue()
            org.videolan.television.viewmodel.MainTvModel r9 = r0.this$0
            android.content.Context r9 = r9.getContext()
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.television.viewmodel.MainTvModel$updateVideos$1$invokeSuspend$$inlined$getFromMl$2 r11 = new org.videolan.television.viewmodel.MainTvModel$updateVideos$1$invokeSuspend$$inlined$getFromMl$2
            r11.<init>(r9, r7)
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r7 = r0
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r0.I$0 = r6
            r0.I$1 = r2
            r0.I$2 = r5
            r0.label = r4
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt.withContext(r10, r11, r7)
            if (r4 != r1) goto L_0x0126
            return r1
        L_0x0126:
            r1 = r5
        L_0x0127:
            org.videolan.television.viewmodel.MainTvModel r5 = r0.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r4
            androidx.lifecycle.LiveData r7 = r5.getVideos()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r3)
            androidx.lifecycle.MutableLiveData r7 = (androidx.lifecycle.MutableLiveData) r7
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r3 = (java.util.List) r3
            org.videolan.medialibrary.media.DummyItem r9 = new org.videolan.medialibrary.media.DummyItem
            android.content.Context r10 = r5.getContext()
            int r11 = org.videolan.vlc.R.string.videos_all
            java.lang.String r10 = r10.getString(r11)
            android.content.Context r11 = r5.getContext()
            android.content.res.Resources r11 = r11.getResources()
            int r12 = org.videolan.vlc.R.plurals.videos_quantity
            java.lang.Integer r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            java.lang.Object[] r14 = new java.lang.Object[r8]
            r15 = 0
            r14[r15] = r13
            java.lang.String r1 = r11.getQuantityString(r12, r1, r14)
            r11 = 0
            r9.<init>(r11, r10, r1)
            r3.add(r9)
            if (r6 <= 0) goto L_0x0192
            org.videolan.medialibrary.media.DummyItem r1 = new org.videolan.medialibrary.media.DummyItem
            android.content.Context r9 = r5.getContext()
            int r10 = org.videolan.vlc.R.string.header_movies
            java.lang.String r9 = r9.getString(r10)
            android.content.Context r10 = r5.getContext()
            android.content.res.Resources r10 = r10.getResources()
            int r11 = org.videolan.vlc.R.plurals.movies_quantity
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            java.lang.Object[] r13 = new java.lang.Object[r8]
            r13[r15] = r12
            java.lang.String r6 = r10.getQuantityString(r11, r6, r13)
            r10 = 30
            r1.<init>(r10, r9, r6)
            r3.add(r1)
        L_0x0192:
            if (r2 <= 0) goto L_0x01be
            org.videolan.medialibrary.media.DummyItem r1 = new org.videolan.medialibrary.media.DummyItem
            android.content.Context r6 = r5.getContext()
            int r9 = org.videolan.vlc.R.string.header_tvshows
            java.lang.String r6 = r6.getString(r9)
            android.content.Context r5 = r5.getContext()
            android.content.res.Resources r5 = r5.getResources()
            int r9 = org.videolan.vlc.R.plurals.tvshow_quantity
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r8[r15] = r10
            java.lang.String r2 = r5.getQuantityString(r9, r2, r8)
            r8 = 31
            r1.<init>(r8, r6, r2)
            r3.add(r1)
        L_0x01be:
            r1 = r3
            java.util.Collection r1 = (java.util.Collection) r1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            kotlin.collections.CollectionsKt.addAll(r1, (T[]) r4)
            r7.setValue(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.viewmodel.MainTvModel$updateVideos$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
