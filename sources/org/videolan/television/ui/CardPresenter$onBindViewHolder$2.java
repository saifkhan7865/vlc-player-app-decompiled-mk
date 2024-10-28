package org.videolan.television.ui;

import android.graphics.drawable.Drawable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.ui.CardPresenter;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/television/ui/CardPresenter$onBindViewHolder$2", "Landroidx/vectordrawable/graphics/drawable/Animatable2Compat$AnimationCallback;", "onAnimationEnd", "", "drawable", "Landroid/graphics/drawable/Drawable;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CardPresenter.kt */
public final class CardPresenter$onBindViewHolder$2 extends Animatable2Compat.AnimationCallback {
    final /* synthetic */ AnimatedVectorDrawableCompat $badge;
    final /* synthetic */ CardPresenter.ViewHolder $holder;

    CardPresenter$onBindViewHolder$2(CardPresenter.ViewHolder viewHolder, AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        this.$holder = viewHolder;
        this.$badge = animatedVectorDrawableCompat;
    }

    /* access modifiers changed from: private */
    public static final void onAnimationEnd$lambda$0(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        Intrinsics.checkNotNullParameter(animatedVectorDrawableCompat, "$badge");
        animatedVectorDrawableCompat.start();
    }

    public void onAnimationEnd(Drawable drawable) {
        this.$holder.getCardView().post(new CardPresenter$onBindViewHolder$2$$ExternalSyntheticLambda0(this.$badge));
        super.onAnimationEnd(drawable);
    }
}
