package org.videolan.television.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "it", "Lorg/videolan/vlc/PlaybackService;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvUtil.kt */
final class TvUtil$openMediaFromPaged$2 extends Lambda implements Function1<PlaybackService, List<? extends MediaWrapper>> {
    final /* synthetic */ MedialibraryProvider<? extends MediaLibraryItem> $provider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtil$openMediaFromPaged$2(MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider) {
        super(1);
        this.$provider = medialibraryProvider;
    }

    public final List<MediaWrapper> invoke(PlaybackService playbackService) {
        Intrinsics.checkNotNullParameter(playbackService, "it");
        Collection arrayList = new ArrayList();
        for (Object next : ArraysKt.toList((T[]) this.$provider.getAll())) {
            if (((MediaLibraryItem) next).getItemType() != 3) {
                arrayList.add(next);
            }
        }
        return (ArrayList) ((List) arrayList);
    }
}
