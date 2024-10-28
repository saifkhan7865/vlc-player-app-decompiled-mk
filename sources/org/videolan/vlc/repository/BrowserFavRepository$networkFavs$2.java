package org.videolan.vlc.repository;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/flow/Flow;", "", "Lorg/videolan/vlc/mediadb/models/BrowserFav;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$networkFavs$2 extends Lambda implements Function0<Flow<? extends List<? extends BrowserFav>>> {
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$networkFavs$2(BrowserFavRepository browserFavRepository) {
        super(0);
        this.this$0 = browserFavRepository;
    }

    public final Flow<List<BrowserFav>> invoke() {
        return this.this$0.browserFavDao.getAllNetworkFavs();
    }
}
