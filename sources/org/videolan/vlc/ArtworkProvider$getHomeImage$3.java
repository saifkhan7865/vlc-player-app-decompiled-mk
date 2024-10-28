package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getHomeImage$3 extends Lambda implements Function0<String> {
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getHomeImage$3(ArtworkProvider artworkProvider) {
        super(0);
        this.this$0 = artworkProvider;
    }

    public final String invoke() {
        return this.this$0.getTimestamp();
    }
}
