package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.ValueAnimator;
import kotlin.jvm.internal.Ref;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteAccessOnboardingSslFragment$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Ref.LongRef f$0;
    public final /* synthetic */ RemoteAccessOnboardingSslFragment f$1;

    public /* synthetic */ RemoteAccessOnboardingSslFragment$$ExternalSyntheticLambda0(Ref.LongRef longRef, RemoteAccessOnboardingSslFragment remoteAccessOnboardingSslFragment) {
        this.f$0 = longRef;
        this.f$1 = remoteAccessOnboardingSslFragment;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        RemoteAccessOnboardingSslFragment.onViewCreated$lambda$0(this.f$0, this.f$1, valueAnimator);
    }
}
