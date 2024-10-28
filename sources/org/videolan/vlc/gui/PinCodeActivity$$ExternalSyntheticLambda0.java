package org.videolan.vlc.gui;

import android.view.KeyEvent;
import android.view.View;
import com.google.android.material.textfield.TextInputEditText;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PinCodeActivity$$ExternalSyntheticLambda0 implements View.OnKeyListener {
    public final /* synthetic */ TextInputEditText f$0;
    public final /* synthetic */ PinCodeActivity f$1;

    public /* synthetic */ PinCodeActivity$$ExternalSyntheticLambda0(TextInputEditText textInputEditText, PinCodeActivity pinCodeActivity) {
        this.f$0 = textInputEditText;
        this.f$1 = pinCodeActivity;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        return PinCodeActivity.onCreate$lambda$8$lambda$5(this.f$0, this.f$1, view, i, keyEvent);
    }
}
