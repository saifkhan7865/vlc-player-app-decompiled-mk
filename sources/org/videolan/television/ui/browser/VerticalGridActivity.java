package org.videolan.television.ui.browser;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.television.R;
import org.videolan.television.databinding.TvVerticalGridBinding;
import org.videolan.television.ui.MainTvActivity;
import org.videolan.television.ui.browser.FileBrowserTvFragment;
import org.videolan.television.ui.browser.interfaces.BrowserActivityInterface;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.interfaces.BrowserFragmentInterface;
import org.videolan.vlc.interfaces.Sortable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0014J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\rH\u0016J\u000e\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/television/ui/browser/VerticalGridActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "Lorg/videolan/television/ui/browser/interfaces/BrowserActivityInterface;", "()V", "binding", "Lorg/videolan/television/databinding/TvVerticalGridBinding;", "fragment", "Lorg/videolan/vlc/interfaces/BrowserFragmentInterface;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", "", "event", "Landroid/view/KeyEvent;", "refresh", "showProgress", "show", "sort", "v", "Landroid/view/View;", "updateEmptyView", "empty", "OnKeyPressedListener", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VerticalGridActivity.kt */
public final class VerticalGridActivity extends BaseTvActivity implements BrowserActivityInterface {
    /* access modifiers changed from: private */
    public TvVerticalGridBinding binding;
    private BrowserFragmentInterface fragment;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/videolan/television/ui/browser/VerticalGridActivity$OnKeyPressedListener;", "", "onKeyPressed", "", "keyCode", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VerticalGridActivity.kt */
    public interface OnKeyPressedListener {
        boolean onKeyPressed(int i);
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        Parcelable parcelable2;
        super.onCreate(bundle);
        TvVerticalGridBinding inflate = TvVerticalGridBinding.inflate(LayoutInflater.from(this));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        BrowserFragmentInterface browserFragmentInterface = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView((View) inflate.getRoot());
        if (bundle == null) {
            long longExtra = getIntent().getLongExtra(MainTvActivity.BROWSER_TYPE, -1);
            if (longExtra == 0) {
                this.fragment = MediaBrowserTvFragment.Companion.newInstance(25, (MediaLibraryItem) null);
            } else if (longExtra == 1) {
                long longExtra2 = getIntent().getLongExtra(Constants.CATEGORY, 24);
                Intent intent = getIntent();
                Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
                if (Build.VERSION.SDK_INT >= 33) {
                    parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "item", MediaLibraryItem.class);
                } else {
                    Parcelable parcelableExtra = intent.getParcelableExtra("item");
                    if (!(parcelableExtra instanceof MediaLibraryItem)) {
                        parcelableExtra = null;
                    }
                    parcelable2 = (MediaLibraryItem) parcelableExtra;
                }
                MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) parcelable2;
                if (longExtra2 == 24) {
                    this.fragment = MediaBrowserTvFragment.Companion.newInstance(24, mediaLibraryItem);
                } else if (longExtra2 == 22) {
                    this.fragment = MediaBrowserTvFragment.Companion.newInstance(22, mediaLibraryItem);
                } else if (longExtra2 == 21) {
                    this.fragment = MediaBrowserTvFragment.Companion.newInstance(21, mediaLibraryItem);
                } else if (longExtra2 == 23) {
                    this.fragment = MediaBrowserTvFragment.Companion.newInstance(23, mediaLibraryItem);
                }
            } else {
                boolean z = true;
                if (longExtra == 3) {
                    Uri data = getIntent().getData();
                    if (data == null) {
                        Intent intent2 = getIntent();
                        Intrinsics.checkNotNullExpressionValue(intent2, "getIntent(...)");
                        if (Build.VERSION.SDK_INT >= 33) {
                            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent2, Constants.KEY_URI, Uri.class);
                        } else {
                            Parcelable parcelableExtra2 = intent2.getParcelableExtra(Constants.KEY_URI);
                            if (!(parcelableExtra2 instanceof Uri)) {
                                parcelableExtra2 = null;
                            }
                            parcelable = (Uri) parcelableExtra2;
                        }
                        data = (Uri) parcelable;
                    }
                    MediaWrapper abstractMediaWrapper = data == null ? null : MLServiceLocator.getAbstractMediaWrapper(data);
                    if (abstractMediaWrapper != null && getIntent().hasExtra(Constants.FAVORITE_TITLE)) {
                        abstractMediaWrapper.setTitle(getIntent().getStringExtra(Constants.FAVORITE_TITLE));
                    }
                    FileBrowserTvFragment.Companion companion = FileBrowserTvFragment.Companion;
                    MediaLibraryItem mediaLibraryItem2 = abstractMediaWrapper;
                    if (abstractMediaWrapper != null) {
                        z = false;
                    }
                    this.fragment = companion.newInstance(1, mediaLibraryItem2, z);
                } else if (longExtra == 30 || longExtra == 31) {
                    this.fragment = MediaScrapingBrowserTvFragment.Companion.newInstance(longExtra);
                } else if (longExtra == 4) {
                    FileBrowserTvFragment.Companion companion2 = FileBrowserTvFragment.Companion;
                    Uri data2 = getIntent().getData();
                    this.fragment = companion2.newInstance(0, data2 != null ? MLServiceLocator.getAbstractMediaWrapper(data2) : null, true);
                } else if (longExtra == 8) {
                    this.fragment = MediaBrowserTvFragment.Companion.newInstance(27, (MediaLibraryItem) null);
                } else {
                    finish();
                    return;
                }
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            int i = R.id.tv_fragment_placeholder;
            BrowserFragmentInterface browserFragmentInterface2 = this.fragment;
            if (browserFragmentInterface2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            } else {
                browserFragmentInterface = browserFragmentInterface2;
            }
            beginTransaction.add(i, (Fragment) browserFragmentInterface).commit();
        }
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        BrowserFragmentInterface browserFragmentInterface = this.fragment;
        if (browserFragmentInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            browserFragmentInterface = null;
        }
        browserFragmentInterface.refresh();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: org.videolan.television.ui.browser.VerticalGridActivity$OnKeyPressedListener} */
    /* JADX WARNING: type inference failed for: r5v2, types: [org.videolan.vlc.interfaces.BrowserFragmentInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            org.videolan.vlc.interfaces.BrowserFragmentInterface r0 = r4.fragment
            if (r0 == 0) goto L_0x004f
            java.lang.String r1 = "fragment"
            r2 = 0
            if (r0 != 0) goto L_0x0012
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x0012:
            boolean r0 = r0 instanceof org.videolan.television.ui.browser.interfaces.DetailsFragment
            r3 = 1
            if (r0 == 0) goto L_0x0032
            r0 = 85
            if (r5 == r0) goto L_0x0023
            r0 = 100
            if (r5 == r0) goto L_0x0023
            r0 = 53
            if (r5 != r0) goto L_0x0032
        L_0x0023:
            org.videolan.vlc.interfaces.BrowserFragmentInterface r5 = r4.fragment
            if (r5 != 0) goto L_0x002b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x002c
        L_0x002b:
            r2 = r5
        L_0x002c:
            org.videolan.television.ui.browser.interfaces.DetailsFragment r2 = (org.videolan.television.ui.browser.interfaces.DetailsFragment) r2
            r2.showDetails()
            return r3
        L_0x0032:
            androidx.fragment.app.FragmentManager r0 = r4.getSupportFragmentManager()     // Catch:{ IndexOutOfBoundsException -> 0x004f }
            java.util.List r0 = r0.getFragments()     // Catch:{ IndexOutOfBoundsException -> 0x004f }
            r1 = 0
            java.lang.Object r0 = r0.get(r1)     // Catch:{ IndexOutOfBoundsException -> 0x004f }
            boolean r1 = r0 instanceof org.videolan.television.ui.browser.VerticalGridActivity.OnKeyPressedListener     // Catch:{ IndexOutOfBoundsException -> 0x004f }
            if (r1 == 0) goto L_0x0046
            r2 = r0
            org.videolan.television.ui.browser.VerticalGridActivity$OnKeyPressedListener r2 = (org.videolan.television.ui.browser.VerticalGridActivity.OnKeyPressedListener) r2     // Catch:{ IndexOutOfBoundsException -> 0x004f }
        L_0x0046:
            if (r2 == 0) goto L_0x004f
            boolean r0 = r2.onKeyPressed(r5)     // Catch:{ IndexOutOfBoundsException -> 0x004f }
            if (r0 != r3) goto L_0x004f
            return r3
        L_0x004f:
            boolean r5 = super.onKeyDown(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.VerticalGridActivity.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    public void showProgress(boolean z) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VerticalGridActivity$showProgress$1(this, z, (Continuation<? super VerticalGridActivity$showProgress$1>) null), 3, (Object) null);
    }

    public void updateEmptyView(boolean z) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VerticalGridActivity$updateEmptyView$1(this, z, (Continuation<? super VerticalGridActivity$updateEmptyView$1>) null), 3, (Object) null);
    }

    public final void sort(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        BrowserFragmentInterface browserFragmentInterface = this.fragment;
        if (browserFragmentInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            browserFragmentInterface = null;
        }
        ((Sortable) browserFragmentInterface).sort(view);
    }
}
