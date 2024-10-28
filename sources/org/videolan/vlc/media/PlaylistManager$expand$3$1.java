package org.videolan.vlc.media;

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
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$expand$3$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$expand$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mrl;
    final /* synthetic */ boolean $stream;
    final /* synthetic */ MediaWrapper $this_apply;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$expand$3$1(boolean z, MediaWrapper mediaWrapper, PlaylistManager playlistManager, String str, Continuation<? super PlaylistManager$expand$3$1> continuation) {
        super(2, continuation);
        this.$stream = z;
        this.$this_apply = mediaWrapper;
        this.this$0 = playlistManager;
        this.$mrl = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$expand$3$1(this.$stream, this.$this_apply, this.this$0, this.$mrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$expand$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$stream) {
                this.$this_apply.setType(6);
                this.this$0.entryUrl = this.$mrl;
                MediaWrapper media = this.this$0.getMedialibrary().getMedia(this.$mrl);
                if (media != null) {
                    PlaylistManager playlistManager = this.this$0;
                    if (media.getId() > 0) {
                        playlistManager.getMedialibrary().removeExternalMedia(media.getId());
                    }
                }
            } else if (!BrowserutilsKt.isSchemeFD(this.$this_apply.getUri().getScheme())) {
                this.this$0.getMedialibrary().addToHistory(this.$mrl, this.$this_apply.getTitle());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
