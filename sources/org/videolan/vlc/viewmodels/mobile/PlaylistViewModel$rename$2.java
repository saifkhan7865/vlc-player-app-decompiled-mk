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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$2", f = "PlaylistViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistViewModel.kt */
final class PlaylistViewModel$rename$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $media;
    final /* synthetic */ String $name;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistViewModel$rename$2(MediaWrapper mediaWrapper, String str, Continuation<? super PlaylistViewModel$rename$2> continuation) {
        super(2, continuation);
        this.$media = mediaWrapper;
        this.$name = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistViewModel$rename$2(this.$media, this.$name, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistViewModel$rename$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper mediaWrapper = this.$media;
            if (!(mediaWrapper instanceof MediaWrapper)) {
                mediaWrapper = null;
            }
            if (mediaWrapper == null) {
                return null;
            }
            mediaWrapper.rename(this.$name);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
