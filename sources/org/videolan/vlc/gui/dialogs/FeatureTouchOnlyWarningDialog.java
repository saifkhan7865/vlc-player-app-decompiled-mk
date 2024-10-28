package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.SwipeToUnlockView;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/FeatureTouchOnlyWarningDialog;", "Lorg/videolan/vlc/gui/dialogs/FeatureFlagWarningDialog;", "()V", "swipeToEnable", "Lorg/videolan/vlc/gui/view/SwipeToUnlockView;", "title", "Landroid/widget/TextView;", "titleString", "", "warning", "warningString", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FeatureTouchOnlyWarningDialog.kt */
public final class FeatureTouchOnlyWarningDialog extends FeatureFlagWarningDialog {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private SwipeToUnlockView swipeToEnable;
    private TextView title;
    private String titleString;
    private TextView warning;
    private String warningString;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/FeatureTouchOnlyWarningDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/FeatureTouchOnlyWarningDialog;", "listener", "Lkotlin/Function0;", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FeatureTouchOnlyWarningDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FeatureTouchOnlyWarningDialog newInstance(Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(function0, "listener");
            FeatureTouchOnlyWarningDialog featureTouchOnlyWarningDialog = new FeatureTouchOnlyWarningDialog();
            featureTouchOnlyWarningDialog.setListener(function0);
            return featureTouchOnlyWarningDialog;
        }
    }

    public void onCreate(Bundle bundle) {
        this.titleString = getString(R.string.touch_only);
        this.warningString = getString(R.string.touch_only_description);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_feature_flag_warning, viewGroup);
        View findViewById = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.title = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.generic_warning);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.warning = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.swipe_to_enable);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        SwipeToUnlockView swipeToUnlockView = (SwipeToUnlockView) findViewById3;
        this.swipeToEnable = swipeToUnlockView;
        TextView textView = null;
        if (swipeToUnlockView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView = null;
        }
        swipeToUnlockView.setDPADAllowed(false);
        SwipeToUnlockView swipeToUnlockView2 = this.swipeToEnable;
        if (swipeToUnlockView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView2 = null;
        }
        swipeToUnlockView2.setOnStartTouchingListener(new FeatureTouchOnlyWarningDialog$onCreateView$1(this));
        SwipeToUnlockView swipeToUnlockView3 = this.swipeToEnable;
        if (swipeToUnlockView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView3 = null;
        }
        swipeToUnlockView3.setOnStopTouchingListener(new FeatureTouchOnlyWarningDialog$onCreateView$2(this));
        SwipeToUnlockView swipeToUnlockView4 = this.swipeToEnable;
        if (swipeToUnlockView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView4 = null;
        }
        swipeToUnlockView4.setOnUnlockListener(new FeatureTouchOnlyWarningDialog$onCreateView$3(this));
        TextView textView2 = this.title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView2 = null;
        }
        textView2.setText(this.titleString);
        TextView textView3 = this.warning;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warning");
        } else {
            textView = textView3;
        }
        textView.setText(this.warningString);
        return inflate;
    }

    public View initialFocusedView() {
        SwipeToUnlockView swipeToUnlockView = this.swipeToEnable;
        if (swipeToUnlockView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView = null;
        }
        return swipeToUnlockView;
    }
}
