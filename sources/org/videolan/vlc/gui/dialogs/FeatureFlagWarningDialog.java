package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.SwipeToUnlockView;
import org.videolan.vlc.util.FeatureFlag;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/FeatureFlagWarningDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "listener", "Lkotlin/Function0;", "", "getListener", "()Lkotlin/jvm/functions/Function0;", "setListener", "(Lkotlin/jvm/functions/Function0;)V", "swipeToEnable", "Lorg/videolan/vlc/gui/view/SwipeToUnlockView;", "title", "Landroid/widget/TextView;", "titleString", "", "warning", "warningString", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FeatureFlagWarningDialog.kt */
public class FeatureFlagWarningDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    protected Function0<Unit> listener;
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

    /* access modifiers changed from: protected */
    public final Function0<Unit> getListener() {
        Function0<Unit> function0 = this.listener;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("listener");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.listener = function0;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/FeatureFlagWarningDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/FeatureFlagWarningDialog;", "featureFlag", "Lorg/videolan/vlc/util/FeatureFlag;", "listener", "Lkotlin/Function0;", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FeatureFlagWarningDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FeatureFlagWarningDialog newInstance(FeatureFlag featureFlag, Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(featureFlag, "featureFlag");
            Intrinsics.checkNotNullParameter(function0, "listener");
            FeatureFlagWarningDialog featureFlagWarningDialog = new FeatureFlagWarningDialog();
            featureFlagWarningDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(FeatureFlagWarningDialogKt.FEATURE_FLAG_WARNING_TEXT, featureFlag.getWarning()), TuplesKt.to(FeatureFlagWarningDialogKt.FEATURE_FLAG_WARNING_TITLE, Integer.valueOf(featureFlag.getTitle()))));
            featureFlagWarningDialog.setListener(function0);
            return featureFlagWarningDialog;
        }
    }

    public void onCreate(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.titleString = getString(arguments.getInt(FeatureFlagWarningDialogKt.FEATURE_FLAG_WARNING_TITLE));
        }
        Bundle arguments2 = getArguments();
        if (arguments2 != null) {
            this.warningString = getString(arguments2.getInt(FeatureFlagWarningDialogKt.FEATURE_FLAG_WARNING_TEXT));
        }
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_feature_flag_warning, viewGroup);
        View findViewById = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.title = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.warning);
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
        swipeToUnlockView.setOnStartTouchingListener(new FeatureFlagWarningDialog$onCreateView$1(this));
        SwipeToUnlockView swipeToUnlockView2 = this.swipeToEnable;
        if (swipeToUnlockView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView2 = null;
        }
        swipeToUnlockView2.setOnStopTouchingListener(new FeatureFlagWarningDialog$onCreateView$2(this));
        SwipeToUnlockView swipeToUnlockView3 = this.swipeToEnable;
        if (swipeToUnlockView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeToEnable");
            swipeToUnlockView3 = null;
        }
        swipeToUnlockView3.setOnUnlockListener(new FeatureFlagWarningDialog$onCreateView$3(this));
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
