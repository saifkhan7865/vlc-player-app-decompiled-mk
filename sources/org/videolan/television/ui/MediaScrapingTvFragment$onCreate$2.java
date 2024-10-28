package org.videolan.television.ui;

import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.models.resolver.ResolverResult;
import org.videolan.television.R;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvFragment.kt */
final class MediaScrapingTvFragment$onCreate$2 extends Lambda implements Function1<ResolverResult, Unit> {
    final /* synthetic */ ArrayObjectAdapter $videoAdapter;
    final /* synthetic */ MediaScrapingTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvFragment$onCreate$2(ArrayObjectAdapter arrayObjectAdapter, MediaScrapingTvFragment mediaScrapingTvFragment) {
        super(1);
        this.$videoAdapter = arrayObjectAdapter;
        this.this$0 = mediaScrapingTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ResolverResult) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ResolverResult resolverResult) {
        List<ResolverMedia> allResults = resolverResult.getAllResults();
        this.$videoAdapter.clear();
        this.$videoAdapter.addAll(0, allResults);
        this.this$0.rowsAdapter.add(new ListRow(new HeaderItem(0, this.this$0.getResources().getString(R.string.moviepedia_result)), this.$videoAdapter));
        this.this$0.updateEmptyView(allResults.isEmpty());
    }
}
