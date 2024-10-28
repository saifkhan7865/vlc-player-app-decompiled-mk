package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$savePlaycount$2", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$savePlaycount$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<MediaWrapper> $currentMedia;
    final /* synthetic */ MediaWrapper $mw;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$savePlaycount$2(MediaWrapper mediaWrapper, PlaylistManager playlistManager, Ref.ObjectRef<MediaWrapper> objectRef, Continuation<? super PlaylistManager$savePlaycount$2> continuation) {
        super(2, continuation);
        this.$mw = mediaWrapper;
        this.this$0 = playlistManager;
        this.$currentMedia = objectRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$savePlaycount$2(this.$mw, this.this$0, this.$currentMedia, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$savePlaycount$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        T t;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            long id = this.$mw.getId();
            if (id == 0) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.this$0.getMedialibrary().findMedia(this.$mw);
                if (objectRef.element == null || ((MediaWrapper) objectRef.element).getId() == 0) {
                    if (this.$mw.getType() == 6 || BrowserutilsKt.isSchemeStreaming(this.$mw.getUri().getScheme())) {
                        Medialibrary access$getMedialibrary = this.this$0.getMedialibrary();
                        String access$getEntryUrl$p = this.this$0.entryUrl;
                        if (access$getEntryUrl$p == null) {
                            access$getEntryUrl$p = this.$mw.getUri().toString();
                            Intrinsics.checkNotNullExpressionValue(access$getEntryUrl$p, "toString(...)");
                        }
                        t = access$getMedialibrary.addStream(access$getEntryUrl$p, this.$mw.getTitle());
                    } else {
                        t = this.this$0.getMedialibrary().addMedia(this.$mw.getUri().toString(), this.$mw.getLength());
                    }
                    objectRef.element = t;
                    if (objectRef.element != null) {
                        Ref.ObjectRef<MediaWrapper> objectRef2 = this.$currentMedia;
                        T t2 = objectRef.element;
                        Intrinsics.checkNotNullExpressionValue(t2, "element");
                        objectRef2.element = t2;
                        id = ((MediaWrapper) objectRef.element).getId();
                        MediaWrapper currentMedia = this.this$0.getCurrentMedia();
                        if (currentMedia != null && !Intrinsics.areEqual((Object) ((MediaWrapper) objectRef.element).getTitle(), (Object) currentMedia.getTitle())) {
                            ((MediaWrapper) objectRef.element).rename(currentMedia.getTitle());
                        }
                    }
                } else {
                    id = ((MediaWrapper) objectRef.element).getId();
                }
            }
            boolean canSwitchToVideo = this.this$0.getPlayer().canSwitchToVideo();
            if (id != 0 && ((MediaWrapper) this.$currentMedia.element).getType() != 0 && !canSwitchToVideo && !((MediaWrapper) this.$currentMedia.element).isPodcast()) {
                ((MediaWrapper) this.$currentMedia.element).markAsPlayed();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
