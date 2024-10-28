package org.videolan.television.ui;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NowPlayingDelegate$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ NowPlayingDelegate f$0;

    public /* synthetic */ NowPlayingDelegate$$ExternalSyntheticLambda0(NowPlayingDelegate nowPlayingDelegate) {
        this.f$0 = nowPlayingDelegate;
    }

    public final void onChanged(Object obj) {
        NowPlayingDelegate.nowPlayingObserver$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
    }
}
