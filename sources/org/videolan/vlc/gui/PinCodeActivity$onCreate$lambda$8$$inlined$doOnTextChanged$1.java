package org.videolan.vlc.gui;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J*\u0010\r\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016¨\u0006\u000f¸\u0006\u0010"}, d2 = {"androidx/core/widget/TextViewKt$addTextChangedListener$textWatcher$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "text", "", "start", "", "count", "after", "onTextChanged", "before", "core-ktx_release", "androidx/core/widget/TextViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TextView.kt */
public final class PinCodeActivity$onCreate$lambda$8$$inlined$doOnTextChanged$1 implements TextWatcher {
    final /* synthetic */ TextInputEditText $editText$inlined;
    final /* synthetic */ PinCodeActivity this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public PinCodeActivity$onCreate$lambda$8$$inlined$doOnTextChanged$1(PinCodeActivity pinCodeActivity, TextInputEditText textInputEditText) {
        this.this$0 = pinCodeActivity;
        this.$editText$inlined = textInputEditText;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        boolean z;
        if (charSequence != null) {
            TextInputEditText[] access$getPinTexts = this.this$0.getPinTexts();
            int length = access$getPinTexts.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    z = true;
                    break;
                }
                CharSequence text = access$getPinTexts[i4].getText();
                if (text == null || StringsKt.isBlank(text)) {
                    z = false;
                } else {
                    i4++;
                }
            }
            z = false;
            this.this$0.getBinding$vlc_android_release().nextButton.setEnabled(z);
            this.this$0.updateFocus();
            if (Settings.INSTANCE.getTvUI() && z) {
                this.this$0.getBinding$vlc_android_release().nextButton.requestFocus();
            }
            if (Intrinsics.areEqual((Object) this.$editText$inlined, (Object) this.this$0.getBinding$vlc_android_release().pinCode4)) {
                TextInputEditText[] access$getPinTexts2 = this.this$0.getPinTexts();
                Collection arrayList = new ArrayList();
                for (TextInputEditText textInputEditText : access$getPinTexts2) {
                    Editable text2 = textInputEditText.getText();
                    if (text2 != null) {
                        Intrinsics.checkNotNull(text2);
                        if (text2.length() > 0) {
                            arrayList.add(textInputEditText);
                        }
                    }
                }
                if (((List) arrayList).size() == 4) {
                    this.this$0.next();
                }
            }
        }
    }
}
