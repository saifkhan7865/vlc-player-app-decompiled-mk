package org.videolan.vlc.repository;

import androidx.lifecycle.MediatorLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.mediadb.models.BrowserFav;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/vlc/mediadb/models/BrowserFav;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$networkFavorites$2$1$1 extends Lambda implements Function1<List<? extends BrowserFav>, Unit> {
    final /* synthetic */ MediatorLiveData<List<MediaWrapper>> $this_apply;
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$networkFavorites$2$1$1(MediatorLiveData<List<MediaWrapper>> mediatorLiveData, BrowserFavRepository browserFavRepository) {
        super(1);
        this.$this_apply = mediatorLiveData;
        this.this$0 = browserFavRepository;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<BrowserFav>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<BrowserFav> list) {
        this.$this_apply.setValue(this.this$0.filterNetworkFavs(BrowserutilsKt.convertFavorites(list)));
    }
}
