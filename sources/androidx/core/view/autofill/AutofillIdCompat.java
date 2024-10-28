package androidx.core.view.autofill;

import android.view.autofill.AutofillId;
import androidx.core.widget.TextViewCompat$$ExternalSyntheticApiModelOutline0;

public class AutofillIdCompat {
    private final Object mWrappedObj;

    private AutofillIdCompat(AutofillId autofillId) {
        this.mWrappedObj = autofillId;
    }

    public static AutofillIdCompat toAutofillIdCompat(AutofillId autofillId) {
        return new AutofillIdCompat(autofillId);
    }

    public AutofillId toAutofillId() {
        return TextViewCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
    }
}
