package org.videolan.vlc.repository;

import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.MediatorLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/MediatorLiveData;", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$networkFavorites$2 extends Lambda implements Function0<MediatorLiveData<List<? extends MediaWrapper>>> {
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$networkFavorites$2(BrowserFavRepository browserFavRepository) {
        super(0);
        this.this$0 = browserFavRepository;
    }

    public final MediatorLiveData<List<MediaWrapper>> invoke() {
        MediatorLiveData<List<MediaWrapper>> mediatorLiveData = new MediatorLiveData<>();
        BrowserFavRepository browserFavRepository = this.this$0;
        mediatorLiveData.addSource(FlowLiveDataConversions.asLiveData$default((Flow) browserFavRepository.getNetworkFavs(), (CoroutineContext) null, 0, 3, (Object) null), new BrowserFavRepository$sam$androidx_lifecycle_Observer$0(new BrowserFavRepository$networkFavorites$2$1$1(mediatorLiveData, browserFavRepository)));
        mediatorLiveData.addSource(FlowLiveDataConversions.asLiveData$default((Flow) browserFavRepository.networkMonitor.getConnectionFlow(), (CoroutineContext) null, 0, 3, (Object) null), new BrowserFavRepository$sam$androidx_lifecycle_Observer$0(new BrowserFavRepository$networkFavorites$2$1$2(browserFavRepository, mediatorLiveData)));
        return mediatorLiveData;
    }
}
