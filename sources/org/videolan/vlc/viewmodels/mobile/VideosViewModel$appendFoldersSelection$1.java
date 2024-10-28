package org.videolan.vlc.viewmodels.mobile;

import java.util.ArrayList;
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
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.MediaUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.VideosViewModel$appendFoldersSelection$1", f = "VideosViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideosViewModel.kt */
final class VideosViewModel$appendFoldersSelection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Folder> $selection;
    int label;
    final /* synthetic */ VideosViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideosViewModel$appendFoldersSelection$1(List<? extends Folder> list, VideosViewModel videosViewModel, Continuation<? super VideosViewModel$appendFoldersSelection$1> continuation) {
        super(2, continuation);
        this.$selection = list;
        this.this$0 = videosViewModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideosViewModel$appendFoldersSelection$1(this.$selection, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideosViewModel$appendFoldersSelection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Collection arrayList = new ArrayList();
            for (Folder all$default : this.$selection) {
                CollectionsKt.addAll(arrayList, MediaUtilsKt.getAll$default(all$default, 0, 0, false, false, false, 31, (Object) null));
            }
            MediaUtils.INSTANCE.appendMedia(this.this$0.getContext(), (List<? extends MediaWrapper>) (List) arrayList);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
