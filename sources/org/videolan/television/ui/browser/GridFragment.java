package org.videolan.television.ui.browser;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.VerticalGridSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.VerticalGridPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.ui.CardPresenter;
import org.videolan.vlc.interfaces.BrowserFragmentInterface;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lorg/videolan/television/ui/browser/GridFragment;", "Landroidx/leanback/app/VerticalGridSupportFragment;", "Lorg/videolan/vlc/interfaces/BrowserFragmentInterface;", "()V", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "context", "Landroidx/fragment/app/FragmentActivity;", "getContext$television_release", "()Landroidx/fragment/app/FragmentActivity;", "setContext$television_release", "(Landroidx/fragment/app/FragmentActivity;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "refresh", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GridFragment.kt */
public class GridFragment extends VerticalGridSupportFragment implements BrowserFragmentInterface {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int NUM_COLUMNS = 4;
    protected static final String TAG = "VLC/GridFragment";
    protected ArrayObjectAdapter adapter;
    private FragmentActivity context;

    public void refresh() {
    }

    /* access modifiers changed from: protected */
    public final ArrayObjectAdapter getAdapter() {
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            return arrayObjectAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setAdapter(ArrayObjectAdapter arrayObjectAdapter) {
        Intrinsics.checkNotNullParameter(arrayObjectAdapter, "<set-?>");
        this.adapter = arrayObjectAdapter;
    }

    public final FragmentActivity getContext$television_release() {
        return this.context;
    }

    public final void setContext$television_release(FragmentActivity fragmentActivity) {
        this.context = fragmentActivity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = getActivity();
        VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter();
        verticalGridPresenter.setNumberOfColumns(4);
        setGridPresenter(verticalGridPresenter);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        setAdapter(new ArrayObjectAdapter((Presenter) new CardPresenter(requireActivity, false, false, 6, (DefaultConstructorMarker) null)));
        getAdapter().clear();
        setAdapter(getAdapter());
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/television/ui/browser/GridFragment$Companion;", "", "()V", "NUM_COLUMNS", "", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: GridFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
