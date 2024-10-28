package org.videolan.vlc.gui;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.ActivityBetaWelcomeBinding;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/BetaWelcomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lorg/videolan/vlc/databinding/ActivityBetaWelcomeBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BetaWelcomeActivity.kt */
public final class BetaWelcomeActivity extends AppCompatActivity {
    private ActivityBetaWelcomeBinding binding;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_beta_welcome);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        ActivityBetaWelcomeBinding activityBetaWelcomeBinding = (ActivityBetaWelcomeBinding) contentView;
        this.binding = activityBetaWelcomeBinding;
        if (activityBetaWelcomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBetaWelcomeBinding = null;
        }
        activityBetaWelcomeBinding.betaOkButton.setOnClickListener(new BetaWelcomeActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(BetaWelcomeActivity betaWelcomeActivity, View view) {
        Intrinsics.checkNotNullParameter(betaWelcomeActivity, "this$0");
        betaWelcomeActivity.finish();
    }
}
