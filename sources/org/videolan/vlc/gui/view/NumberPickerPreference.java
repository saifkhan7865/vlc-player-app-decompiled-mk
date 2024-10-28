package org.videolan.vlc.gui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.DialogPreference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0014J\u001a\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000fH\u0014J\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/gui/view/NumberPickerPreference;", "Landroidx/preference/DialogPreference;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "doPersistInt", "", "value", "", "getPersistedInt", "getSummary", "", "onGetDefaultValue", "", "a", "Landroid/content/res/TypedArray;", "index", "onSetInitialValue", "restore", "", "defaultValue", "setValue", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NumberPickerPreference.kt */
public final class NumberPickerPreference extends DialogPreference {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int FALLBACK_DEFAULT_VALUE = -1;
    public static final int MAX_VALUE = 100;
    public static final int MIN_VALUE = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NumberPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public CharSequence getSummary() {
        String string = getContext().getString(R.string.jump_delay_summary, new Object[]{String.valueOf(getPersistedInt())});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final int getPersistedInt() {
        return super.getPersistedInt(-1);
    }

    public final void doPersistInt(int i) {
        super.persistInt(i);
        notifyChanged();
    }

    public final void setValue(int i) {
        boolean shouldDisableDependents = shouldDisableDependents();
        persistInt(i);
        boolean shouldDisableDependents2 = shouldDisableDependents();
        if (shouldDisableDependents2 != shouldDisableDependents) {
            notifyDependencyChange(shouldDisableDependents2);
        }
        notifyChanged();
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        int i;
        onSetInitialValue(obj);
        if (z) {
            i = getPersistedInt(-1);
        } else {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            i = ((Integer) obj).intValue();
        }
        setValue(i);
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        Intrinsics.checkNotNullParameter(typedArray, "a");
        return Integer.valueOf(typedArray.getInteger(i, -1));
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/view/NumberPickerPreference$Companion;", "", "()V", "FALLBACK_DEFAULT_VALUE", "", "MAX_VALUE", "MIN_VALUE", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NumberPickerPreference.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
