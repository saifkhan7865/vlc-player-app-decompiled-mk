package org.videolan.vlc.gui;

import com.google.android.material.textfield.TextInputEditText;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lcom/google/android/material/textfield/TextInputEditText;", "invoke", "()[Lcom/google/android/material/textfield/TextInputEditText;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
final class PinCodeActivity$pinTexts$2 extends Lambda implements Function0<TextInputEditText[]> {
    final /* synthetic */ PinCodeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PinCodeActivity$pinTexts$2(PinCodeActivity pinCodeActivity) {
        super(0);
        this.this$0 = pinCodeActivity;
    }

    public final TextInputEditText[] invoke() {
        return new TextInputEditText[]{this.this$0.getBinding$vlc_android_release().pinCode1, this.this$0.getBinding$vlc_android_release().pinCode2, this.this$0.getBinding$vlc_android_release().pinCode3, this.this$0.getBinding$vlc_android_release().pinCode4};
    }
}
