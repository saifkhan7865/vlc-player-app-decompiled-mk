package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.SubtitlesModel$search$1", f = "SubtitlesModel.kt", i = {0, 1}, l = {149, 164}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$search$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $byFile;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SubtitlesModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$search$1(boolean z, SubtitlesModel subtitlesModel, Continuation<? super SubtitlesModel$search$1> continuation) {
        super(2, continuation);
        this.$byFile = z;
        this.this$0 = subtitlesModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SubtitlesModel$search$1 subtitlesModel$search$1 = new SubtitlesModel$search$1(this.$byFile, this.this$0, continuation);
        subtitlesModel$search$1.L$0 = obj;
        return subtitlesModel$search$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SubtitlesModel$search$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b7 A[Catch:{ Exception -> 0x0112, all -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c5 A[Catch:{ Exception -> 0x0112, all -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d4 A[Catch:{ Exception -> 0x0112, all -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ea A[Catch:{ Exception -> 0x0112, all -> 0x0110 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0028
            if (r1 == r3) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            java.lang.Object r0 = r13.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Exception -> 0x0112 }
            goto L_0x00b3
        L_0x0018:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0020:
            java.lang.Object r0 = r13.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Exception -> 0x0112 }
            goto L_0x0059
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            boolean r1 = r13.$byFile     // Catch:{ Exception -> 0x0112 }
            r5 = 0
            if (r1 == 0) goto L_0x005d
            org.videolan.vlc.viewmodels.SubtitlesModel r1 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            org.videolan.tools.CoroutineContextProvider r1 = r1.getCoroutineContextProvider()     // Catch:{ Exception -> 0x0112 }
            kotlinx.coroutines.CoroutineDispatcher r1 = r1.getIO()     // Catch:{ Exception -> 0x0112 }
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1     // Catch:{ Exception -> 0x0112 }
            org.videolan.vlc.viewmodels.SubtitlesModel$search$1$subs$1 r2 = new org.videolan.vlc.viewmodels.SubtitlesModel$search$1$subs$1     // Catch:{ Exception -> 0x0112 }
            org.videolan.vlc.viewmodels.SubtitlesModel r6 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            r2.<init>(r6, r5)     // Catch:{ Exception -> 0x0112 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ Exception -> 0x0112 }
            r5 = r13
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch:{ Exception -> 0x0112 }
            r13.L$0 = r14     // Catch:{ Exception -> 0x0112 }
            r13.label = r3     // Catch:{ Exception -> 0x0112 }
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r2, r5)     // Catch:{ Exception -> 0x0112 }
            if (r1 != r0) goto L_0x0057
            return r0
        L_0x0057:
            r0 = r14
            r14 = r1
        L_0x0059:
            java.util.List r14 = (java.util.List) r14     // Catch:{ Exception -> 0x0112 }
            goto L_0x00bf
        L_0x005d:
            org.videolan.vlc.viewmodels.SubtitlesModel r1 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            androidx.databinding.ObservableField r1 = r1.getObservableSearchName()     // Catch:{ Exception -> 0x0112 }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x0112 }
            r7 = r1
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x0112 }
            if (r7 == 0) goto L_0x00b8
            org.videolan.vlc.viewmodels.SubtitlesModel r6 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            androidx.databinding.ObservableField r1 = r6.getObservableSearchEpisode()     // Catch:{ Exception -> 0x0112 }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x0112 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0112 }
            if (r1 == 0) goto L_0x0084
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0112 }
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)     // Catch:{ Exception -> 0x0112 }
            r8 = r1
            goto L_0x0085
        L_0x0084:
            r8 = r5
        L_0x0085:
            androidx.databinding.ObservableField r1 = r6.getObservableSearchSeason()     // Catch:{ Exception -> 0x0112 }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x0112 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0112 }
            if (r1 == 0) goto L_0x0099
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0112 }
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)     // Catch:{ Exception -> 0x0112 }
        L_0x0099:
            r9 = r5
            androidx.databinding.ObservableField r1 = r6.getObservableSearchLanguage()     // Catch:{ Exception -> 0x0112 }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x0112 }
            r10 = r1
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0112 }
            r13.L$0 = r14     // Catch:{ Exception -> 0x0112 }
            r13.label = r2     // Catch:{ Exception -> 0x0112 }
            r11 = r13
            java.lang.Object r1 = r6.getSubtitleByName(r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x0112 }
            if (r1 != r0) goto L_0x00b1
            return r0
        L_0x00b1:
            r0 = r14
            r14 = r1
        L_0x00b3:
            java.util.List r14 = (java.util.List) r14     // Catch:{ Exception -> 0x0112 }
            if (r14 != 0) goto L_0x00bf
            r14 = r0
        L_0x00b8:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ Exception -> 0x0112 }
            r12 = r0
            r0 = r14
            r14 = r12
        L_0x00bf:
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)     // Catch:{ Exception -> 0x0112 }
            if (r0 == 0) goto L_0x00ce
            org.videolan.vlc.viewmodels.SubtitlesModel r0 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            androidx.lifecycle.MutableLiveData r0 = r0.apiResultLiveData     // Catch:{ Exception -> 0x0112 }
            r0.postValue(r14)     // Catch:{ Exception -> 0x0112 }
        L_0x00ce:
            boolean r14 = r14.isEmpty()     // Catch:{ Exception -> 0x0112 }
            if (r14 == 0) goto L_0x00ea
            org.videolan.vlc.viewmodels.SubtitlesModel r14 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            androidx.databinding.ObservableField r14 = r14.getObservableMessage()     // Catch:{ Exception -> 0x0112 }
            org.videolan.vlc.viewmodels.SubtitlesModel r0 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            android.content.Context r0 = r0.context     // Catch:{ Exception -> 0x0112 }
            int r1 = org.videolan.vlc.R.string.no_result     // Catch:{ Exception -> 0x0112 }
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x0112 }
            r14.set(r0)     // Catch:{ Exception -> 0x0112 }
            goto L_0x00f5
        L_0x00ea:
            org.videolan.vlc.viewmodels.SubtitlesModel r14 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            androidx.databinding.ObservableField r14 = r14.getObservableMessage()     // Catch:{ Exception -> 0x0112 }
            java.lang.String r0 = ""
            r14.set(r0)     // Catch:{ Exception -> 0x0112 }
        L_0x00f5:
            org.videolan.vlc.viewmodels.SubtitlesModel r14 = r13.this$0     // Catch:{ Exception -> 0x0112 }
            androidx.databinding.ObservableField r14 = r14.getObservableError()     // Catch:{ Exception -> 0x0112 }
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ Exception -> 0x0112 }
            r14.set(r0)     // Catch:{ Exception -> 0x0112 }
        L_0x0102:
            org.videolan.vlc.viewmodels.SubtitlesModel r14 = r13.this$0
            androidx.lifecycle.MediatorLiveData r14 = r14.isApiLoading()
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            r14.postValue(r0)
            goto L_0x015c
        L_0x0110:
            r14 = move-exception
            goto L_0x015f
        L_0x0112:
            r14 = move-exception
            java.lang.String r0 = "SubtitlesModel"
            java.lang.String r1 = r14.getMessage()     // Catch:{ all -> 0x0110 }
            r2 = r14
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x0110 }
            android.util.Log.e(r0, r1, r2)     // Catch:{ all -> 0x0110 }
            org.videolan.vlc.viewmodels.SubtitlesModel r0 = r13.this$0     // Catch:{ all -> 0x0110 }
            androidx.databinding.ObservableField r0 = r0.getObservableError()     // Catch:{ all -> 0x0110 }
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ all -> 0x0110 }
            r0.set(r1)     // Catch:{ all -> 0x0110 }
            boolean r14 = r14 instanceof org.videolan.resources.util.NoConnectivityException     // Catch:{ all -> 0x0110 }
            if (r14 == 0) goto L_0x0146
            org.videolan.vlc.viewmodels.SubtitlesModel r14 = r13.this$0     // Catch:{ all -> 0x0110 }
            androidx.databinding.ObservableField r14 = r14.getObservableMessage()     // Catch:{ all -> 0x0110 }
            org.videolan.vlc.viewmodels.SubtitlesModel r0 = r13.this$0     // Catch:{ all -> 0x0110 }
            android.content.Context r0 = r0.context     // Catch:{ all -> 0x0110 }
            int r1 = org.videolan.vlc.R.string.no_internet_connection     // Catch:{ all -> 0x0110 }
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x0110 }
            r14.set(r0)     // Catch:{ all -> 0x0110 }
            goto L_0x0102
        L_0x0146:
            org.videolan.vlc.viewmodels.SubtitlesModel r14 = r13.this$0     // Catch:{ all -> 0x0110 }
            androidx.databinding.ObservableField r14 = r14.getObservableMessage()     // Catch:{ all -> 0x0110 }
            org.videolan.vlc.viewmodels.SubtitlesModel r0 = r13.this$0     // Catch:{ all -> 0x0110 }
            android.content.Context r0 = r0.context     // Catch:{ all -> 0x0110 }
            int r1 = org.videolan.vlc.R.string.subs_download_error     // Catch:{ all -> 0x0110 }
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x0110 }
            r14.set(r0)     // Catch:{ all -> 0x0110 }
            goto L_0x0102
        L_0x015c:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x015f:
            org.videolan.vlc.viewmodels.SubtitlesModel r0 = r13.this$0
            androidx.lifecycle.MediatorLiveData r0 = r0.isApiLoading()
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            r0.postValue(r1)
            goto L_0x016e
        L_0x016d:
            throw r14
        L_0x016e:
            goto L_0x016d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.SubtitlesModel$search$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
