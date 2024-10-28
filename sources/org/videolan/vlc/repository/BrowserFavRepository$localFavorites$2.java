package org.videolan.vlc.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "", "Lorg/videolan/vlc/mediadb/models/BrowserFav;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$localFavorites$2 extends Lambda implements Function0<LiveData<List<? extends BrowserFav>>> {
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$localFavorites$2(BrowserFavRepository browserFavRepository) {
        super(0);
        this.this$0 = browserFavRepository;
    }

    public final LiveData<List<BrowserFav>> invoke() {
        return this.this$0.browserFavDao.getAllLocalFavs();
    }
}
