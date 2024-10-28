package org.videolan.television.ui;

import android.os.Bundle;
import kotlin.Metadata;
import org.videolan.television.R;
import org.videolan.television.ui.browser.BaseTvActivity;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0014¨\u0006\b"}, d2 = {"Lorg/videolan/television/ui/DetailsActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "refresh", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DetailsActivity.kt */
public final class DetailsActivity extends BaseTvActivity {
    /* access modifiers changed from: protected */
    public void refresh() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tv_details);
    }
}
