package org.videolan.vlc.media;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\"\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001j\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002`\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1$playList$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$loadLastPlaylist$1$playList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<MediaWrapper>>, Object> {
    final /* synthetic */ String[] $locations;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$loadLastPlaylist$1$playList$1(String[] strArr, Continuation<? super PlaylistManager$loadLastPlaylist$1$playList$1> continuation) {
        super(2, continuation);
        this.$locations = strArr;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$loadLastPlaylist$1$playList$1(this.$locations, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<MediaWrapper>> continuation) {
        return ((PlaylistManager$loadLastPlaylist$1$playList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Sequence<String> asSequence = ArraysKt.asSequence((T[]) this.$locations);
            Collection arrayList = new ArrayList(this.$locations.length);
            for (String parse : asSequence) {
                arrayList.add(MLServiceLocator.getAbstractMediaWrapper(Uri.parse(parse)));
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
