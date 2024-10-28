package org.videolan.vlc.gui.browser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.Strings;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.browser.BrowserModelKt;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0012\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J \u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010(\u001a\u00020\rH\u0016J\b\u0010)\u001a\u00020\rH\u0016J\b\u0010*\u001a\u00020\rH\u0016J\u001a\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020$2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010-\u001a\u00020\rH\u0016J\b\u0010.\u001a\u00020\rH\u0014R\u0014\u0010\u0003\u001a\u00020\u00048TX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\tR\u0014\u0010\n\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u000e\u0010\u000b\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lorg/videolan/vlc/gui/browser/FileBrowserFragment;", "Lorg/videolan/vlc/gui/browser/BaseBrowserFragment;", "()V", "categoryTitle", "", "getCategoryTitle", "()Ljava/lang/String;", "isFile", "", "()Z", "isNetwork", "needsRefresh", "browseRoot", "", "containerActivity", "Landroidx/fragment/app/FragmentActivity;", "createFragment", "Landroidx/fragment/app/Fragment;", "getStorageDelegate", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "getTitle", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCtxAction", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "onMainActionClick", "v", "Landroid/view/View;", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onPrepareOptionsMenu", "onResume", "onStart", "onStop", "onViewCreated", "view", "registerSwiperRefreshlayout", "setupBrowser", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserFragment.kt */
public class FileBrowserFragment extends BaseBrowserFragment {
    private final boolean isFile = true;
    private final boolean isNetwork;
    private boolean needsRefresh;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileBrowserFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContextOption.values().length];
            try {
                iArr[ContextOption.CTX_FAV_ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public IStorageFragmentDelegate getStorageDelegate() {
        return null;
    }

    public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    /* access modifiers changed from: protected */
    public String getCategoryTitle() {
        String string = getString(R.string.directories);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* access modifiers changed from: protected */
    public Fragment createFragment() {
        return new FileBrowserFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupBrowser();
    }

    public void onViewCreated(View view, Bundle bundle) {
        ActionBar supportActionBar;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        SecondaryActivity secondaryActivity = requireActivity instanceof SecondaryActivity ? (SecondaryActivity) requireActivity : null;
        if (secondaryActivity != null && (supportActionBar = secondaryActivity.getSupportActionBar()) != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_close_up);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.needsRefresh) {
            ((BrowserModel) getViewModel()).browseRoot();
        }
    }

    public void onStop() {
        super.onStop();
        if (isRootDirectory() && getAdapter().isEmpty()) {
            this.needsRefresh = true;
        }
    }

    public void registerSwiperRefreshlayout() {
        if (!isRootDirectory()) {
            super.registerSwiperRefreshlayout();
        } else {
            getSwipeRefreshLayout().setEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public void setupBrowser() {
        setViewModel(BrowserModelKt.getBrowserModel$default(this, 0, !isRootDirectory() ? getMrl() : null, false, 4, (Object) null));
    }

    public String getTitle() {
        String str;
        if (isRootDirectory()) {
            return getCategoryTitle();
        }
        if (getCurrentMedia() != null) {
            String external_public_directory = AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY();
            String mrl = getMrl();
            if (Intrinsics.areEqual((Object) external_public_directory, (Object) mrl != null ? Strings.removeFileScheme(mrl) : null)) {
                str = getString(R.string.internal_memory);
            } else if (this instanceof FilePickerFragment) {
                MediaWrapper currentMedia = getCurrentMedia();
                Intrinsics.checkNotNull(currentMedia);
                str = currentMedia.getUri().toString();
            } else {
                MediaWrapper currentMedia2 = getCurrentMedia();
                Intrinsics.checkNotNull(currentMedia2);
                str = currentMedia2.getTitle();
            }
        } else if (this instanceof FilePickerFragment) {
            str = getMrl();
            if (str == null) {
                str = "";
            }
        } else {
            str = FileUtils.INSTANCE.getFileNameFromPath(getMrl());
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    public void browseRoot() {
        ((BrowserModel) getViewModel()).browseRoot();
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        MediaWrapper mediaWrapper = (MediaWrapper) getAdapter().getItem(i);
        if (WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()] == 1) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new FileBrowserFragment$onCtxAction$1(this, mediaWrapper, (Continuation<? super FileBrowserFragment$onCtxAction$1>) null), 3, (Object) null);
        } else {
            super.onCtxAction(i, contextOption);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        if (!(this instanceof FilePickerFragment) && !(this instanceof StorageBrowserFragment)) {
            menuInflater.inflate(R.menu.fragment_option_network, menu);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public FragmentActivity containerActivity() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return requireActivity;
    }

    public boolean isNetwork() {
        return this.isNetwork;
    }

    public boolean isFile() {
        return this.isFile;
    }

    public void onResume() {
        super.onResume();
        ((BrowserModel) getViewModel()).resetSort();
        if (!((BrowserModel) getViewModel()).getDataset().getValue().isEmpty()) {
            ((BrowserModel) getViewModel()).reSort();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.ml_menu_save);
        if (findItem != null) {
            boolean z = false;
            if (!isRootDirectory()) {
                String mrl = getMrl();
                Intrinsics.checkNotNull(mrl);
                if (StringsKt.startsWith$default(mrl, "file", false, 2, (Object) null)) {
                    z = true;
                }
            }
            findItem.setVisible(z);
            LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new FileBrowserFragment$onPrepareOptionsMenu$1(this, findItem, menu, (Continuation<? super FileBrowserFragment$onPrepareOptionsMenu$1>) null));
        }
    }
}
