package org.videolan.vlc.viewmodels;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.SubtitlesModel$merge$2", f = "SubtitlesModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$merge$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SubtitleItem>>, Object> {
    final /* synthetic */ List<SubtitleItem> $downloadedResult;
    final /* synthetic */ List<SubtitleItem> $downloadingResult;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$merge$2(List<SubtitleItem> list, List<SubtitleItem> list2, Continuation<? super SubtitlesModel$merge$2> continuation) {
        super(2, continuation);
        this.$downloadedResult = list;
        this.$downloadingResult = list2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SubtitlesModel$merge$2(this.$downloadedResult, this.$downloadingResult, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<SubtitleItem>> continuation) {
        return ((SubtitlesModel$merge$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<SubtitleItem> list = this.$downloadedResult;
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            Collection collection = list;
            List<SubtitleItem> list2 = this.$downloadingResult;
            List list3 = list2 != null ? CollectionsKt.toList(list2) : null;
            if (list3 == null) {
                list3 = CollectionsKt.emptyList();
            }
            return CollectionsKt.plus(collection, list3);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
