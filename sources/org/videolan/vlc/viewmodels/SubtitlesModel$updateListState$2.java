package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.opensubtitles.OpenSubtitle;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.SubtitlesModel$updateListState$2", f = "SubtitlesModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$updateListState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<SubtitleItem>>, Object> {
    final /* synthetic */ List<OpenSubtitle> $apiResultLiveData;
    final /* synthetic */ List<SubtitleItem> $history;
    int label;
    final /* synthetic */ SubtitlesModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$updateListState$2(List<OpenSubtitle> list, List<SubtitleItem> list2, SubtitlesModel subtitlesModel, Continuation<? super SubtitlesModel$updateListState$2> continuation) {
        super(2, continuation);
        this.$apiResultLiveData = list;
        this.$history = list2;
        this.this$0 = subtitlesModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SubtitlesModel$updateListState$2(this.$apiResultLiveData, this.$history, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<SubtitleItem>> continuation) {
        return ((SubtitlesModel$updateListState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: org.videolan.vlc.gui.dialogs.SubtitleItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: org.videolan.vlc.gui.dialogs.SubtitleItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: org.videolan.vlc.gui.dialogs.SubtitleItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: org.videolan.vlc.gui.dialogs.SubtitleItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: org.videolan.vlc.gui.dialogs.SubtitleItem} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r12.label
            if (r0 != 0) goto L_0x007d
            kotlin.ResultKt.throwOnFailure(r13)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.List r13 = (java.util.List) r13
            java.util.List<org.videolan.resources.opensubtitles.OpenSubtitle> r0 = r12.$apiResultLiveData
            if (r0 == 0) goto L_0x007c
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.List<org.videolan.vlc.gui.dialogs.SubtitleItem> r1 = r12.$history
            org.videolan.vlc.viewmodels.SubtitlesModel r2 = r12.this$0
            java.util.Iterator r0 = r0.iterator()
        L_0x001f:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x007c
            java.lang.Object r3 = r0.next()
            org.videolan.resources.opensubtitles.OpenSubtitle r3 = (org.videolan.resources.opensubtitles.OpenSubtitle) r3
            r4 = 0
            if (r1 == 0) goto L_0x0053
            r5 = r1
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0035:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0051
            java.lang.Object r6 = r5.next()
            r7 = r6
            org.videolan.vlc.gui.dialogs.SubtitleItem r7 = (org.videolan.vlc.gui.dialogs.SubtitleItem) r7
            java.lang.String r7 = r7.getIdSubtitle()
            java.lang.String r8 = r3.getIdSubtitle()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x0035
            r4 = r6
        L_0x0051:
            org.videolan.vlc.gui.dialogs.SubtitleItem r4 = (org.videolan.vlc.gui.dialogs.SubtitleItem) r4
        L_0x0053:
            if (r4 == 0) goto L_0x005b
            org.videolan.vlc.gui.dialogs.State r4 = r4.getState()
            if (r4 != 0) goto L_0x005d
        L_0x005b:
            org.videolan.vlc.gui.dialogs.State r4 = org.videolan.vlc.gui.dialogs.State.NotDownloaded
        L_0x005d:
            r10 = r4
            org.videolan.vlc.gui.dialogs.SubtitleItem r4 = new org.videolan.vlc.gui.dialogs.SubtitleItem
            java.lang.String r6 = r3.getIdSubtitle()
            android.net.Uri r7 = r2.mediaUri
            java.lang.String r8 = r3.getSubLanguageID()
            java.lang.String r9 = r3.getMovieReleaseName()
            java.lang.String r11 = r3.getZipDownloadLink()
            r5 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r13.add(r4)
            goto L_0x001f
        L_0x007c:
            return r13
        L_0x007d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            goto L_0x0086
        L_0x0085:
            throw r13
        L_0x0086:
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.SubtitlesModel$updateListState$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
