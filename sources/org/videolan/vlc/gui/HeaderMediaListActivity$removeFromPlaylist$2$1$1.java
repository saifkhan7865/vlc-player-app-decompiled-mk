package org.videolan.vlc.gui;

import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$1$1", f = "HeaderMediaListActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$removeFromPlaylist$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Integer> $indexes;
    final /* synthetic */ HashMap<Integer, Long> $itemsRemoved;
    final /* synthetic */ List<MediaWrapper> $list;
    final /* synthetic */ MediaLibraryItem $playlist;
    final /* synthetic */ MediaWrapper[] $tracks;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$removeFromPlaylist$2$1$1(List<Integer> list, MediaLibraryItem mediaLibraryItem, List<? extends MediaWrapper> list2, HashMap<Integer, Long> hashMap, MediaWrapper[] mediaWrapperArr, Continuation<? super HeaderMediaListActivity$removeFromPlaylist$2$1$1> continuation) {
        super(2, continuation);
        this.$indexes = list;
        this.$playlist = mediaLibraryItem;
        this.$list = list2;
        this.$itemsRemoved = hashMap;
        this.$tracks = mediaWrapperArr;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$removeFromPlaylist$2$1$1(this.$indexes, this.$playlist, this.$list, this.$itemsRemoved, this.$tracks, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeaderMediaListActivity$removeFromPlaylist$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int i = 0;
            for (Number intValue : CollectionsKt.sortedWith(this.$indexes, new HeaderMediaListActivity$removeFromPlaylist$2$1$1$invokeSuspend$$inlined$sortedBy$1())) {
                int i2 = i + 1;
                int intValue2 = intValue.intValue();
                MediaWrapper[] tracks = this.$playlist.getTracks();
                Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
                int indexOf = ArraysKt.indexOf((T[]) (Object[]) tracks, this.$list.get(i));
                this.$itemsRemoved.put(Boxing.boxInt(indexOf), Boxing.boxLong(this.$tracks[intValue2].getId()));
                MediaLibraryItem mediaLibraryItem = this.$playlist;
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
                ((Playlist) mediaLibraryItem).remove(indexOf);
                i = i2;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
