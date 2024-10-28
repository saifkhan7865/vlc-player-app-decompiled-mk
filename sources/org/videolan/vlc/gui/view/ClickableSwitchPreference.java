package org.videolan.vlc.gui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R;
import androidx.preference.TwoStatePreference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0014J\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/view/ClickableSwitchPreference;", "Landroidx/preference/TwoStatePreference;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "switchClickListener", "Landroid/view/View$OnClickListener;", "switchView", "Landroid/view/View;", "onBindViewHolder", "", "holder", "Landroidx/preference/PreferenceViewHolder;", "onClick", "setOnSwitchClickListener", "listener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClickableSwitchPreference.kt */
public final class ClickableSwitchPreference extends TwoStatePreference {
    private View.OnClickListener switchClickListener;
    private View switchView;

    /* access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(CompoundButton compoundButton, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onClick() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClickableSwitchPreference(Context context) {
        super(context, (AttributeSet) null, R.attr.switchPreferenceCompatStyle, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        Intrinsics.checkNotNullParameter(preferenceViewHolder, "holder");
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R.id.switchWidget);
        this.switchView = findViewById;
        Intrinsics.checkNotNull(findViewById);
        findViewById.setOnClickListener(this.switchClickListener);
        View view = this.switchView;
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.appcompat.widget.SwitchCompat");
        ((SwitchCompat) view).setChecked(isChecked());
        View view2 = this.switchView;
        Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type androidx.appcompat.widget.SwitchCompat");
        ((SwitchCompat) view2).setOnCheckedChangeListener(new ClickableSwitchPreference$$ExternalSyntheticLambda0());
    }

    public final void setOnSwitchClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "listener");
        this.switchClickListener = onClickListener;
    }
}
