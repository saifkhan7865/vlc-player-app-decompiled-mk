package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.Dialog;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.NetworkMonitor;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.DialogDelegatesKt;
import org.videolan.vlc.util.IDialogManager;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.browser.BrowserModelKt;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0000H\u0014J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0011H\u0016J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010*\u001a\u00020\u0011H\u0016J\u001a\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010.\u001a\u00020\u0011H\u0016J\b\u0010/\u001a\u00020\u0011H\u0014R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\fR\u0014\u0010\r\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/videolan/vlc/gui/browser/NetworkBrowserFragment;", "Lorg/videolan/vlc/gui/browser/BaseBrowserFragment;", "Lorg/videolan/vlc/util/IDialogManager;", "()V", "categoryTitle", "", "getCategoryTitle", "()Ljava/lang/String;", "dialogsDelegate", "Lorg/videolan/vlc/util/DialogDelegate;", "isFile", "", "()Z", "isNetwork", "networkMonitor", "Lorg/videolan/tools/NetworkMonitor;", "browseRoot", "", "containerActivity", "Landroidx/fragment/app/FragmentActivity;", "createFragment", "dialogCanceled", "dialog", "Lorg/videolan/libvlc/Dialog;", "fireDialog", "getStorageDelegate", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCtxAction", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroyView", "onPrepareOptionsMenu", "onStart", "onViewCreated", "view", "Landroid/view/View;", "refresh", "updateEmptyView", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkBrowserFragment.kt */
public final class NetworkBrowserFragment extends BaseBrowserFragment implements IDialogManager {
    private final DialogDelegate dialogsDelegate = new DialogDelegate();
    private final boolean isFile;
    private final boolean isNetwork = true;
    private NetworkMonitor networkMonitor;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NetworkBrowserFragment.kt */
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

    /* access modifiers changed from: protected */
    public void browseRoot() {
    }

    public IStorageFragmentDelegate getStorageDelegate() {
        return null;
    }

    /* access modifiers changed from: protected */
    public NetworkBrowserFragment createFragment() {
        return new NetworkBrowserFragment();
    }

    /* access modifiers changed from: protected */
    public String getCategoryTitle() {
        String string = getString(R.string.network_browsing);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public void onCreate(Bundle bundle) {
        ActionBar supportActionBar;
        super.onCreate(bundle);
        this.dialogsDelegate.observeDialogs(this, this);
        NetworkMonitor.Companion companion = NetworkMonitor.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.networkMonitor = (NetworkMonitor) companion.getInstance(requireContext);
        FragmentActivity requireActivity = requireActivity();
        SecondaryActivity secondaryActivity = requireActivity instanceof SecondaryActivity ? (SecondaryActivity) requireActivity : null;
        if (!(secondaryActivity == null || (supportActionBar = secondaryActivity.getSupportActionBar()) == null)) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_close_up);
        }
        setViewModel(BrowserModelKt.getBrowserModel$default(this, 1, getMrl(), false, 4, (Object) null));
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (isRootDirectory()) {
            getSwipeRefreshLayout().setEnabled(false);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        menuInflater.inflate(R.menu.fragment_option_network, menu);
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

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.ml_menu_save);
        findItem.setVisible(!isRootDirectory());
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new NetworkBrowserFragment$onPrepareOptionsMenu$1(this, findItem, menu, (Continuation<? super NetworkBrowserFragment$onPrepareOptionsMenu$1>) null));
    }

    public void onStart() {
        super.onStart();
        FloatingActionButton fabPlay = getFabPlay();
        if (fabPlay != null) {
            fabPlay.setImageResource(isRootDirectory() ? R.drawable.ic_fab_add : R.drawable.ic_fab_play);
        }
        FloatingActionButton fabPlay2 = getFabPlay();
        if (fabPlay2 != null) {
            fabPlay2.setContentDescription(getString(isRootDirectory() ? R.string.add : R.string.play));
        }
        FloatingActionButton fabPlay3 = getFabPlay();
        if (fabPlay3 != null) {
            fabPlay3.setOnClickListener(new NetworkBrowserFragment$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void onStart$lambda$0(NetworkBrowserFragment networkBrowserFragment, View view) {
        Intrinsics.checkNotNullParameter(networkBrowserFragment, "this$0");
        Intrinsics.checkNotNull(view);
        networkBrowserFragment.onFabPlayClick(view);
    }

    public void onDestroyView() {
        FragmentActivity requireActivity = requireActivity();
        SecondaryActivity secondaryActivity = requireActivity instanceof SecondaryActivity ? (SecondaryActivity) requireActivity : null;
        if (secondaryActivity != null) {
            secondaryActivity.setSupportActionBar((Toolbar) null);
        }
        super.onDestroyView();
    }

    public void refresh() {
        NetworkMonitor networkMonitor2 = this.networkMonitor;
        if (networkMonitor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
            networkMonitor2 = null;
        }
        if (networkMonitor2.getConnected()) {
            super.refresh();
            return;
        }
        updateEmptyView();
        getAdapter().clear();
    }

    public void fireDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogDelegatesKt.showVlcDialog((Fragment) this, dialog);
    }

    public void dialogCanceled(Dialog dialog) {
        if (dialog instanceof Dialog.LoginDialog) {
            goBack();
        } else if (dialog instanceof Dialog.ErrorMessage) {
            View view = getView();
            if (view != null) {
                StringBuilder sb = new StringBuilder();
                Dialog.ErrorMessage errorMessage = (Dialog.ErrorMessage) dialog;
                sb.append(errorMessage.getTitle());
                sb.append(": ");
                sb.append(errorMessage.getText());
                Snackbar.make(view, (CharSequence) sb.toString(), 0).show();
            }
            goBack();
        }
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        MediaLibraryItem item = getAdapter().getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
        MediaWrapper mediaWrapper = (MediaWrapper) item;
        if (WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()] == 1) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new NetworkBrowserFragment$onCtxAction$1(this, mediaWrapper, (Continuation<? super NetworkBrowserFragment$onCtxAction$1>) null), 3, (Object) null);
        } else {
            super.onCtxAction(i, contextOption);
        }
    }

    /* access modifiers changed from: protected */
    public void updateEmptyView() {
        String str;
        NetworkMonitor networkMonitor2 = this.networkMonitor;
        if (networkMonitor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
            networkMonitor2 = null;
        }
        if (!networkMonitor2.getConnected()) {
            getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY);
            EmptyLoadingStateView emptyLoadingStateView = getBinding().emptyLoading;
            String string = getString(R.string.network_connection_needed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            emptyLoadingStateView.setEmptyText(string);
            getBinding().networkList.setVisibility(8);
            getBinding().setShowFavorites(false);
        } else if (!((BrowserModel) getViewModel()).isEmpty()) {
            getBinding().emptyLoading.setState(EmptyLoadingState.NONE);
            getBinding().networkList.setVisibility(0);
        } else if (getSwipeRefreshLayout().isRefreshing()) {
            getBinding().emptyLoading.setState(EmptyLoadingState.LOADING);
            getBinding().networkList.setVisibility(8);
        } else {
            EmptyLoadingStateView emptyLoadingStateView2 = getBinding().emptyLoading;
            String filterQuery = ((BrowserModel) getViewModel()).getFilterQuery();
            if (filterQuery != null) {
                str = getString(R.string.empty_search, filterQuery);
            } else {
                str = null;
            }
            if (str == null) {
                str = getString(R.string.nomedia);
                Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
            }
            emptyLoadingStateView2.setEmptyText(str);
            if (((BrowserModel) getViewModel()).getFilterQuery() != null) {
                getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY_SEARCH);
                return;
            }
            if (isRootDirectory()) {
                NetworkMonitor networkMonitor3 = this.networkMonitor;
                if (networkMonitor3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
                    networkMonitor3 = null;
                }
                if (networkMonitor3.getLanAllowed()) {
                    getBinding().emptyLoading.setState(EmptyLoadingState.LOADING);
                    EmptyLoadingStateView emptyLoadingStateView3 = getBinding().emptyLoading;
                    String string2 = getString(R.string.network_shares_discovery);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    emptyLoadingStateView3.setLoadingText(string2);
                } else {
                    getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY);
                    EmptyLoadingStateView emptyLoadingStateView4 = getBinding().emptyLoading;
                    String string3 = getString(R.string.network_connection_needed);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    emptyLoadingStateView4.setEmptyText(string3);
                }
            } else {
                getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY);
                EmptyLoadingStateView emptyLoadingStateView5 = getBinding().emptyLoading;
                String string4 = getString(R.string.network_empty);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                emptyLoadingStateView5.setEmptyText(string4);
            }
            getBinding().networkList.setVisibility(8);
            LifecycleAwareScheduler.startAction$default(getScheduler(), BaseBrowserFragmentKt.MSG_HIDE_LOADING, (Bundle) null, 2, (Object) null);
        }
    }
}
