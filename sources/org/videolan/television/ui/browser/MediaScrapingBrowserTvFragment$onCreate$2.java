package org.videolan.television.ui.browser;

import androidx.collection.SparseArrayCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012$\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00050\u0003j\u0002`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Landroidx/collection/SparseArrayCompat;", "", "Lorg/videolan/resources/util/HeadersIndex;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingBrowserTvFragment.kt */
final class MediaScrapingBrowserTvFragment$onCreate$2 extends Lambda implements Function1<SparseArrayCompat<String>, Unit> {
    final /* synthetic */ MediaScrapingBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingBrowserTvFragment$onCreate$2(MediaScrapingBrowserTvFragment mediaScrapingBrowserTvFragment) {
        super(1);
        this.this$0 = mediaScrapingBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SparseArrayCompat<String>) (SparseArrayCompat) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(SparseArrayCompat<String> sparseArrayCompat) {
        MediaScrapingBrowserTvFragment mediaScrapingBrowserTvFragment = this.this$0;
        Intrinsics.checkNotNull(sparseArrayCompat);
        mediaScrapingBrowserTvFragment.updateHeaders(sparseArrayCompat);
        this.this$0.getBinding().list.invalidateItemDecorations();
    }
}
