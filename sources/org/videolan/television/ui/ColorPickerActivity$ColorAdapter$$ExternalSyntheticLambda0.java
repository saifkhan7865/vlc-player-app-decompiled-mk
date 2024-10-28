package org.videolan.television.ui;

import android.view.View;
import org.videolan.television.ui.ColorPickerActivity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ColorPickerActivity$ColorAdapter$$ExternalSyntheticLambda0 implements View.OnFocusChangeListener {
    public final /* synthetic */ ColorPickerActivity.ColorAdapter f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ColorPickerActivity$ColorAdapter$$ExternalSyntheticLambda0(ColorPickerActivity.ColorAdapter colorAdapter, int i) {
        this.f$0 = colorAdapter;
        this.f$1 = i;
    }

    public final void onFocusChange(View view, boolean z) {
        ColorPickerActivity.ColorAdapter.onBindViewHolder$lambda$0(this.f$0, this.f$1, view, z);
    }
}
