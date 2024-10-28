package org.videolan.vlc.gui.audio;

import android.content.Context;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.BaseAudioBrowser$onCtxAction$1", f = "BaseAudioBrowser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseAudioBrowser.kt */
final class BaseAudioBrowser$onCtxAction$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $media;
    int label;
    final /* synthetic */ BaseAudioBrowser<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseAudioBrowser$onCtxAction$1(MediaLibraryItem mediaLibraryItem, BaseAudioBrowser<T> baseAudioBrowser, Continuation<? super BaseAudioBrowser$onCtxAction$1> continuation) {
        super(2, continuation);
        this.$media = mediaLibraryItem;
        this.this$0 = baseAudioBrowser;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseAudioBrowser$onCtxAction$1(this.$media, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseAudioBrowser$onCtxAction$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaWrapper[] tracks;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaLibraryItem mediaLibraryItem = this.$media;
            Playlist playlist = mediaLibraryItem instanceof Playlist ? (Playlist) mediaLibraryItem : null;
            if (!(playlist == null || (tracks = playlist.getTracks()) == null)) {
                BaseAudioBrowser<T> baseAudioBrowser = this.this$0;
                MediaUtils mediaUtils = MediaUtils.INSTANCE;
                Context requireActivity = baseAudioBrowser.requireActivity();
                Collection arrayList = new ArrayList(tracks.length);
                for (MediaWrapper mediaWrapper : tracks) {
                    mediaWrapper.addFlags(8);
                    arrayList.add(mediaWrapper);
                }
                MediaUtils.openList$default(mediaUtils, requireActivity, CollectionsKt.toList((List) arrayList), 0, false, 8, (Object) null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
