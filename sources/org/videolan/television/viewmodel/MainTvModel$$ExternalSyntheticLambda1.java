package org.videolan.television.viewmodel;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MainTvModel$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ MainTvModel f$0;

    public /* synthetic */ MainTvModel$$ExternalSyntheticLambda1(MainTvModel mainTvModel) {
        this.f$0 = mainTvModel;
    }

    public final void onChanged(Object obj) {
        MainTvModel.playerObserver$lambda$1(this.f$0, ((Boolean) obj).booleanValue());
    }
}
