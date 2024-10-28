package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.MedialibraryViewModel$changeFavorite$2", f = "MedialibraryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MedialibraryViewModel.kt */
final class MedialibraryViewModel$changeFavorite$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $favorite;
    final /* synthetic */ List<MediaLibraryItem> $tracks;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MedialibraryViewModel$changeFavorite$2(List<? extends MediaLibraryItem> list, boolean z, Continuation<? super MedialibraryViewModel$changeFavorite$2> continuation) {
        super(2, continuation);
        this.$tracks = list;
        this.$favorite = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MedialibraryViewModel$changeFavorite$2(this.$tracks, this.$favorite, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MedialibraryViewModel$changeFavorite$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = this.$favorite;
            for (MediaLibraryItem favorite : this.$tracks) {
                favorite.setFavorite(z);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
