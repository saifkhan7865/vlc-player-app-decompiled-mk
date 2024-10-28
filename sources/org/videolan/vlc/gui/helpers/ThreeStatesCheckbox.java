package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\b\u0010\u0014\u001a\u00020\u0012H\u0002R\u000e\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t8F@FX\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/helpers/ThreeStatesCheckbox;", "Landroidx/appcompat/widget/AppCompatCheckBox;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "currentState", "state", "getState", "()I", "setState", "(I)V", "init", "", "init$vlc_android_release", "updateBtn", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ThreeStatesCheckbox.kt */
public final class ThreeStatesCheckbox extends AppCompatCheckBox {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int STATE_CHECKED = 1;
    public static final int STATE_PARTIAL = 2;
    public static final int STATE_UNCHECKED = 0;
    private int currentState;

    public final int getState() {
        return this.currentState;
    }

    public final void setState(int i) {
        this.currentState = i;
        updateBtn();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThreeStatesCheckbox(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init$vlc_android_release();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThreeStatesCheckbox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init$vlc_android_release();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThreeStatesCheckbox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init$vlc_android_release();
    }

    public final void init$vlc_android_release() {
        updateBtn();
        setOnCheckedChangeListener(new ThreeStatesCheckbox$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        if (r2 != 2) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void init$lambda$0(org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r1, android.widget.CompoundButton r2, boolean r3) {
        /*
            java.lang.String r2 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            int r2 = r1.currentState
            r3 = 1
            if (r2 == 0) goto L_0x0014
            if (r2 == r3) goto L_0x0010
            r0 = 2
            if (r2 == r0) goto L_0x0014
            goto L_0x0016
        L_0x0010:
            r2 = 0
            r1.currentState = r2
            goto L_0x0016
        L_0x0014:
            r1.currentState = r3
        L_0x0016:
            r1.updateBtn()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.ThreeStatesCheckbox.init$lambda$0(org.videolan.vlc.gui.helpers.ThreeStatesCheckbox, android.widget.CompoundButton, boolean):void");
    }

    private final void updateBtn() {
        int i;
        int i2 = this.currentState;
        if (i2 == 1) {
            i = R.drawable.ic_checkbox_true;
        } else if (i2 != 2) {
            i = R.drawable.ic_checkbox_not_checked;
        } else {
            i = R.drawable.ic_checkbox_partialy;
        }
        setButtonDrawable(i);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/helpers/ThreeStatesCheckbox$Companion;", "", "()V", "STATE_CHECKED", "", "STATE_PARTIAL", "STATE_UNCHECKED", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ThreeStatesCheckbox.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
