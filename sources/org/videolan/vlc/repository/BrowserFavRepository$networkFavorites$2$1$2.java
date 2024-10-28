package org.videolan.vlc.repository;

import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.MediatorLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.Connection;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/tools/Connection;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$networkFavorites$2$1$2 extends Lambda implements Function1<Connection, Unit> {
    final /* synthetic */ MediatorLiveData<List<MediaWrapper>> $this_apply;
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$networkFavorites$2$1$2(BrowserFavRepository browserFavRepository, MediatorLiveData<List<MediaWrapper>> mediatorLiveData) {
        super(1);
        this.this$0 = browserFavRepository;
        this.$this_apply = mediatorLiveData;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Connection) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Connection connection) {
        List<MediaWrapper> convertFavorites = BrowserutilsKt.convertFavorites((List) FlowLiveDataConversions.asLiveData$default((Flow) this.this$0.getNetworkFavs(), (CoroutineContext) null, 0, 3, (Object) null).getValue());
        if (!convertFavorites.isEmpty()) {
            this.$this_apply.setValue(connection.getConnected() ? this.this$0.filterNetworkFavs(convertFavorites) : CollectionsKt.emptyList());
        }
    }
}
