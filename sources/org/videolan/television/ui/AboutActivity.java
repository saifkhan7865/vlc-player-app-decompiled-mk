package org.videolan.television.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.util.HelpersKt;
import org.videolan.television.R;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lorg/videolan/television/ui/AboutActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AboutActivity.kt */
public final class AboutActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.about);
        View rootView = getWindow().getDecorView().getRootView();
        Intrinsics.checkNotNullExpressionValue(rootView, "getRootView(...)");
        UiTools.INSTANCE.fillAboutView(this, rootView);
        HelpersKt.applyOverscanMargin((Activity) this);
    }
}
