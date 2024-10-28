package org.videolan.vlc.gui.audio;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.AppContextProvider;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"org/videolan/vlc/gui/audio/EqualizerFragment$createSaveCustomSetDialog$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerFragment.kt */
public final class EqualizerFragment$createSaveCustomSetDialog$1 implements TextWatcher {
    final /* synthetic */ EditText $input;
    final /* synthetic */ String $oldName;
    final /* synthetic */ EqualizerFragment this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    EqualizerFragment$createSaveCustomSetDialog$1(EqualizerFragment equalizerFragment, EditText editText, String str) {
        this.this$0 = equalizerFragment;
        this.$input = editText;
        this.$oldName = str;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.this$0.getContext() != null) {
            String obj = this.$input.getText().toString();
            Editable text = this.$input.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "_", false, 2, (Object) null) || Intrinsics.areEqual((Object) obj, (Object) this.this$0.newPresetName)) {
                this.$input.setError(this.this$0.getString(R.string.custom_set_wrong_input));
                Toast.makeText(this.this$0.requireActivity(), AppContextProvider.INSTANCE.getAppContext().getResources().getString(R.string.custom_set_wrong_input), 0).show();
            } else if (!this.this$0.allSets.contains(obj) || Intrinsics.areEqual((Object) obj, (Object) this.$oldName)) {
                this.$input.setError((CharSequence) null);
            } else {
                this.$input.setError(this.this$0.getString(R.string.custom_set_already_exist));
            }
        }
    }
}
