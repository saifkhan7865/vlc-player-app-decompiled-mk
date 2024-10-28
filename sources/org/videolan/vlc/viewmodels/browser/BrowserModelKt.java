package org.videolan.vlc.viewmodels.browser;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.providers.PickerType;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.browser.NetworkModel;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a&\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"TYPE_FILE", "", "TYPE_NETWORK", "TYPE_PICKER", "TYPE_STORAGE", "getBrowserModel", "Lorg/videolan/vlc/viewmodels/browser/BrowserModel;", "Landroidx/fragment/app/Fragment;", "category", "url", "", "showDummyCategory", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserModel.kt */
public final class BrowserModelKt {
    public static final long TYPE_FILE = 0;
    public static final long TYPE_NETWORK = 1;
    public static final long TYPE_PICKER = 2;
    public static final long TYPE_STORAGE = 3;

    public static final BrowserModel getBrowserModel(Fragment fragment, long j, String str, boolean z) {
        Fragment fragment2 = fragment;
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        if (j == 1) {
            Context requireContext = fragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            return (BrowserModel) new ViewModelProvider((ViewModelStoreOwner) fragment2, (ViewModelProvider.Factory) new NetworkModel.Factory(requireContext, str)).get(NetworkModel.class);
        }
        Context requireContext2 = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
        return (BrowserModel) new ViewModelProvider((ViewModelStoreOwner) fragment2, (ViewModelProvider.Factory) new BrowserModel.Factory(requireContext2, str, j, z, (PickerType) null, 16, (DefaultConstructorMarker) null)).get(BrowserModel.class);
    }

    public static /* synthetic */ BrowserModel getBrowserModel$default(Fragment fragment, long j, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return getBrowserModel(fragment, j, str, z);
    }
}
