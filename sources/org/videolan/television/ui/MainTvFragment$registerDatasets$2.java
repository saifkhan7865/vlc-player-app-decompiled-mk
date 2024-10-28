package org.videolan.television.ui;

import androidx.leanback.widget.ArrayObjectAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainTvFragment.kt */
final class MainTvFragment$registerDatasets$2 extends Lambda implements Function1<List<? extends MediaLibraryItem>, Unit> {
    final /* synthetic */ MainTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvFragment$registerDatasets$2(MainTvFragment mainTvFragment) {
        super(1);
        this.this$0 = mainTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<? extends MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<? extends MediaLibraryItem> list) {
        MainTvFragment mainTvFragment = this.this$0;
        Intrinsics.checkNotNull(list);
        mainTvFragment.displayFavorites = !list.isEmpty();
        ArrayObjectAdapter access$getFavoritesAdapter$p = this.this$0.favoritesAdapter;
        if (access$getFavoritesAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesAdapter");
            access$getFavoritesAdapter$p = null;
        }
        access$getFavoritesAdapter$p.setItems(list, TvUtil.INSTANCE.getDiffCallback());
    }
}
