package org.videolan.vlc.gui;

import android.os.Bundle;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0006\"\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/gui/OTPCodeActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "displayTitle", "", "getDisplayTitle", "()Z", "isOTPActivity", "setOTPActivity", "(Z)V", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OTPCodeActivity.kt */
public final class OTPCodeActivity extends BaseActivity {
    private final boolean displayTitle = true;
    private boolean isOTPActivity = true;

    public View getSnackAnchorView(boolean z) {
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        return decorView;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    public boolean isOTPActivity() {
        return this.isOTPActivity;
    }

    public void setOTPActivity(boolean z) {
        this.isOTPActivity = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.otp_code_activity);
        getOnBackPressedDispatcher().addCallback(this, new OTPCodeActivity$onCreate$1(this));
    }
}
