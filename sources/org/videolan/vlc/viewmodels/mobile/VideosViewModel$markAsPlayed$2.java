package org.videolan.vlc.viewmodels.mobile;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.media.MediaUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.VideosViewModel$markAsPlayed$2", f = "VideosViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideosViewModel.kt */
final class VideosViewModel$markAsPlayed$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $media;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideosViewModel$markAsPlayed$2(MediaLibraryItem mediaLibraryItem, Continuation<? super VideosViewModel$markAsPlayed$2> continuation) {
        super(2, continuation);
        this.$media = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideosViewModel$markAsPlayed$2(this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideosViewModel$markAsPlayed$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaLibraryItem mediaLibraryItem = this.$media;
            if (mediaLibraryItem instanceof VideoGroup) {
                for (MediaWrapper mediaWrapper : MediaUtilsKt.getAll$default((VideoGroup) mediaLibraryItem, 0, false, false, false, 15, (Object) null)) {
                    if (mediaWrapper.getSeen() == 0) {
                        mediaWrapper.setPlayCount(1);
                    }
                }
            } else if (mediaLibraryItem instanceof Folder) {
                for (MediaWrapper mediaWrapper2 : MediaUtilsKt.getAll$default((Folder) mediaLibraryItem, 0, 0, false, false, false, 31, (Object) null)) {
                    if (mediaWrapper2.getSeen() == 0) {
                        mediaWrapper2.setPlayCount(1);
                    }
                }
            } else if ((mediaLibraryItem instanceof MediaWrapper) && ((MediaWrapper) mediaLibraryItem).getSeen() == 0) {
                ((MediaWrapper) this.$media).setPlayCount(1);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
