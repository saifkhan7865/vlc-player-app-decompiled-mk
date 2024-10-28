package org.videolan.vlc.gui.helpers;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda12 implements View.OnClickListener {
    public final /* synthetic */ CoroutineScope f$0;
    public final /* synthetic */ Function1 f$1;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda12(CoroutineScope coroutineScope, Function1 function1) {
        this.f$0 = coroutineScope;
        this.f$1 = function1;
    }

    public final void onClick(View view) {
        UiTools.snackerConfirm$lambda$1(this.f$0, this.f$1, view);
    }
}
