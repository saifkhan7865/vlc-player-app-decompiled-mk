package org.videolan.vlc.viewmodels.mobile;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.media.MediaUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.VideosViewModel$markAsUnplayed$2", f = "VideosViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideosViewModel.kt */
final class VideosViewModel$markAsUnplayed$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final /* synthetic */ MediaLibraryItem $media;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideosViewModel$markAsUnplayed$2(MediaLibraryItem mediaLibraryItem, Continuation<? super VideosViewModel$markAsUnplayed$2> continuation) {
        super(2, continuation);
        this.$media = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideosViewModel$markAsUnplayed$2(this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<Object> continuation) {
        return ((VideosViewModel$markAsUnplayed$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaLibraryItem mediaLibraryItem = this.$media;
            if (mediaLibraryItem instanceof VideoGroup) {
                for (MediaWrapper playCount : MediaUtilsKt.getAll$default((VideoGroup) mediaLibraryItem, 0, false, false, false, 15, (Object) null)) {
                    playCount.setPlayCount(0);
                }
                return Unit.INSTANCE;
            } else if (!(mediaLibraryItem instanceof Folder)) {
                return mediaLibraryItem instanceof MediaWrapper ? Boxing.boxBoolean(((MediaWrapper) mediaLibraryItem).setPlayCount(0)) : Unit.INSTANCE;
            } else {
                for (MediaWrapper playCount2 : MediaUtilsKt.getAll$default((Folder) mediaLibraryItem, 0, 0, false, false, false, 31, (Object) null)) {
                    playCount2.setPlayCount(0);
                }
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
