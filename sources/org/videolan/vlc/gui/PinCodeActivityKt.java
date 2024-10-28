package org.videolan.vlc.gui;

import android.text.Editable;
import com.google.android.material.textfield.TextInputEditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"PIN_CODE_REASON", "", "clearText", "", "Lcom/google/android/material/textfield/TextInputEditText;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
public final class PinCodeActivityKt {
    private static final String PIN_CODE_REASON = "pin_code_reason";

    public static final void clearText(TextInputEditText textInputEditText) {
        Intrinsics.checkNotNullParameter(textInputEditText, "<this>");
        Editable text = textInputEditText.getText();
        if (text != null) {
            text.clear();
        }
        textInputEditText.requestLayout();
    }
}
