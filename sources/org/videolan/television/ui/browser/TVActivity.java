package org.videolan.television.ui.browser;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.R;
import org.videolan.television.ui.MainTvActivity;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.network.MRLPanelFragment;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/browser/TVActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "()V", "fragment", "Landroidx/fragment/app/Fragment;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "refresh", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TVActivity.kt */
public final class TVActivity extends BaseTvActivity {
    private Fragment fragment;

    /* access modifiers changed from: protected */
    public void refresh() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tv_vertical_grid);
        if (bundle != null) {
            return;
        }
        if (getIntent().getLongExtra(MainTvActivity.BROWSER_TYPE, -1) == 6) {
            this.fragment = new MRLPanelFragment();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            int i = R.id.tv_fragment_placeholder;
            Fragment fragment2 = this.fragment;
            if (fragment2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
                fragment2 = null;
            }
            beginTransaction.add(i, fragment2).commit();
            return;
        }
        finish();
    }
}
