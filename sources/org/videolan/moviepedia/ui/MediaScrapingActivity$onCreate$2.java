package org.videolan.moviepedia.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.models.resolver.ResolverResult;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingActivity.kt */
final class MediaScrapingActivity$onCreate$2 extends Lambda implements Function1<ResolverResult, Unit> {
    final /* synthetic */ MediaScrapingActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingActivity$onCreate$2(MediaScrapingActivity mediaScrapingActivity) {
        super(1);
        this.this$0 = mediaScrapingActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ResolverResult) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ResolverResult resolverResult) {
        MediaScrapingResultAdapter access$getMediaScrapingResultAdapter$p = this.this$0.mediaScrapingResultAdapter;
        if (access$getMediaScrapingResultAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaScrapingResultAdapter");
            access$getMediaScrapingResultAdapter$p = null;
        }
        access$getMediaScrapingResultAdapter$p.setItems(resolverResult.getAllResults());
    }
}
