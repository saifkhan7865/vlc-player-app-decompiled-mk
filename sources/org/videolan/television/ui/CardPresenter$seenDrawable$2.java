package org.videolan.television.ui;

import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/vectordrawable/graphics/drawable/VectorDrawableCompat;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CardPresenter.kt */
final class CardPresenter$seenDrawable$2 extends Lambda implements Function0<VectorDrawableCompat> {
    final /* synthetic */ CardPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardPresenter$seenDrawable$2(CardPresenter cardPresenter) {
        super(0);
        this.this$0 = cardPresenter;
    }

    public final VectorDrawableCompat invoke() {
        return VectorDrawableCompat.create(this.this$0.context.getResources(), R.drawable.ic_seen_tv_normal, this.this$0.context.getTheme());
    }
}
