package org.videolan.television.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.R;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.vlc.gui.SecondaryActivity;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0014J\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/videolan/television/ui/SearchActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "()V", "emptyView", "Landroid/widget/TextView;", "fragment", "Lorg/videolan/television/ui/SearchFragment;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSearchRequested", "", "refresh", "updateEmptyView", "empty", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SearchActivity.kt */
public final class SearchActivity extends BaseTvActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "VLC/SearchActivity";
    private TextView emptyView;
    private SearchFragment fragment;

    /* access modifiers changed from: protected */
    public void refresh() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tv_search);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.search_fragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type org.videolan.television.ui.SearchFragment");
        this.fragment = (SearchFragment) findFragmentById;
        this.emptyView = (TextView) findViewById(R.id.empty);
    }

    public final void updateEmptyView(boolean z) {
        TextView textView = this.emptyView;
        Intrinsics.checkNotNull(textView);
        textView.setVisibility(z ? 0 : 8);
    }

    public boolean onSearchRequested() {
        SearchFragment searchFragment = this.fragment;
        if (searchFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            searchFragment = null;
        }
        searchFragment.startRecognition();
        return true;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/SearchActivity$Companion;", "", "()V", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SearchActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
