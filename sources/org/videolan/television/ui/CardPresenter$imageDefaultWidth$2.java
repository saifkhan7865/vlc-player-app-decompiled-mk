package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CardPresenter.kt */
final class CardPresenter$imageDefaultWidth$2 extends Lambda implements Function0<Float> {
    final /* synthetic */ CardPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardPresenter$imageDefaultWidth$2(CardPresenter cardPresenter) {
        super(0);
        this.this$0 = cardPresenter;
    }

    public final Float invoke() {
        return Float.valueOf(this.this$0.context.getResources().getDimension(R.dimen.tv_grid_card_thumb_width));
    }
}