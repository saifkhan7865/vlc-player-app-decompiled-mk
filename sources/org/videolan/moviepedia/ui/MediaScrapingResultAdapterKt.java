package org.videolan.moviepedia.ui;

import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverMedia;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"showYear", "", "view", "Landroid/widget/TextView;", "item", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingResultAdapter.kt */
public final class MediaScrapingResultAdapterKt {
    @BindingAdapter({"year"})
    public static final void showYear(TextView textView, ResolverMedia resolverMedia) {
        Intrinsics.checkNotNullParameter(textView, "view");
        Intrinsics.checkNotNullParameter(resolverMedia, "item");
        textView.setText(resolverMedia.year());
    }
}
