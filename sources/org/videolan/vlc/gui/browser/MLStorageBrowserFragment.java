package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.collection.SimpleArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.NetworkMonitor;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.TitleListView;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.browser.BrowserModelKt;

@Metadata(d1 = {"\u0000Á\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001&\u0018\u0000 S2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001SB\u0005¢\u0006\u0002\u0010\u0004JA\u0010\u0017\u001a\u00020\u001826\u0010\u0019\u001a2\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00180\u001aH\u0001J\t\u0010 \u001a\u00020\u0018H\u0001J\u0019\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0011H\u0001J\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u0011H\u0016J\b\u0010*\u001a\u00020\u001eH\u0014J\u001c\u0010+\u001a\u00020\u001e2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0012\u00100\u001a\u00020\u00182\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u001c\u00103\u001a\u00020\u001e2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u00104\u001a\u0004\u0018\u000105H\u0016J&\u00106\u001a\u0004\u0018\u00010#2\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0018\u0010;\u001a\u00020\u00182\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0016J\u0012\u0010@\u001a\u00020\u00182\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010A\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u0010B\u001a\u00020\u00182\u0006\u00104\u001a\u000205H\u0016J\b\u0010C\u001a\u00020\u0018H\u0016J\b\u0010D\u001a\u00020\u0018H\u0016J\u001a\u0010E\u001a\u00020\u00182\u0006\u0010F\u001a\u00020#2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\t\u0010G\u001a\u00020\u0018H\u0001J\b\u0010H\u001a\u00020\u0018H\u0002J\u0010\u0010I\u001a\u00020\u00182\u0006\u0010J\u001a\u00020KH\u0002J\u001c\u0010L\u001a\u00020\u00182\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00160NH\u0001¢\u0006\u0002\u0010OJ\u0011\u0010P\u001a\u00020\u00182\u0006\u0010Q\u001a\u00020RH\u0001R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lorg/videolan/vlc/gui/browser/MLStorageBrowserFragment;", "Lorg/videolan/vlc/gui/BaseFragment;", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "()V", "alertDialog", "Landroidx/appcompat/app/AlertDialog;", "localEntry", "Lorg/videolan/vlc/gui/view/TitleListView;", "localViewModel", "Lorg/videolan/vlc/viewmodels/browser/BrowserModel;", "networkEntry", "networkMonitor", "Lorg/videolan/tools/NetworkMonitor;", "networkViewModel", "processingFolders", "Landroidx/collection/SimpleArrayMap;", "", "Landroid/widget/CheckBox;", "getProcessingFolders", "()Landroidx/collection/SimpleArrayMap;", "storageBrowserAdapter", "Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;", "addBannedFoldersCallback", "", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "folder", "", "banned", "addRootsCallback", "checkBoxAction", "v", "Landroid/view/View;", "mrl", "getBrowserContainer", "org/videolan/vlc/gui/browser/MLStorageBrowserFragment$getBrowserContainer$1", "isNetwork", "(Z)Lorg/videolan/vlc/gui/browser/MLStorageBrowserFragment$getBrowserContainer$1;", "getTitle", "hasFAB", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroyActionMode", "onOptionsItemSelected", "onPrepareOptionsMenu", "onStart", "onStop", "onViewCreated", "view", "removeRootsCallback", "showAddDirectoryDialog", "updateNetworkEmptyView", "emptyLoading", "Lorg/videolan/vlc/gui/view/EmptyLoadingStateView;", "withAdapters", "adapters", "", "([Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;)V", "withContext", "context", "Landroid/content/Context;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MLStorageBrowserFragment.kt */
public final class MLStorageBrowserFragment extends BaseFragment implements IStorageFragmentDelegate, CtxActionReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final /* synthetic */ StorageFragmentDelegate $$delegate_0 = new StorageFragmentDelegate();
    private AlertDialog alertDialog;
    /* access modifiers changed from: private */
    public TitleListView localEntry;
    /* access modifiers changed from: private */
    public BrowserModel localViewModel;
    /* access modifiers changed from: private */
    public TitleListView networkEntry;
    private NetworkMonitor networkMonitor;
    /* access modifiers changed from: private */
    public BrowserModel networkViewModel;
    /* access modifiers changed from: private */
    public StorageBrowserAdapter storageBrowserAdapter;

    /* access modifiers changed from: private */
    public static final void showAddDirectoryDialog$lambda$0(DialogInterface dialogInterface, int i) {
    }

    public void addBannedFoldersCallback(Function2<? super String, ? super Boolean, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "callback");
        this.$$delegate_0.addBannedFoldersCallback(function2);
    }

    public void addRootsCallback() {
        this.$$delegate_0.addRootsCallback();
    }

    public void checkBoxAction(View view, String str) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(str, "mrl");
        this.$$delegate_0.checkBoxAction(view, str);
    }

    public SimpleArrayMap<String, CheckBox> getProcessingFolders() {
        return this.$$delegate_0.getProcessingFolders();
    }

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        return false;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    public void removeRootsCallback() {
        this.$$delegate_0.removeRootsCallback();
    }

    public void withAdapters(StorageBrowserAdapter[] storageBrowserAdapterArr) {
        Intrinsics.checkNotNullParameter(storageBrowserAdapterArr, "adapters");
        this.$$delegate_0.withAdapters(storageBrowserAdapterArr);
    }

    public void withContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.$$delegate_0.withContext(context);
    }

    public String getTitle() {
        Bundle arguments = getArguments();
        String string = getString((arguments == null || !arguments.getBoolean("from_onboarding", false)) ? R.string.directories_summary : R.string.medialibrary_directories);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public void onCreate(Bundle bundle) {
        NetworkMonitor.Companion companion = NetworkMonitor.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.networkMonitor = (NetworkMonitor) companion.getInstance(requireContext);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        withContext(requireActivity);
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        addRootsCallback();
    }

    public void onStop() {
        super.onStop();
        removeRootsCallback();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.main_browser_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        KotlinExtensionsKt.setGone(view.findViewById(R.id.fav_browser_entry));
        View findViewById = view.findViewById(R.id.local_browser_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.localEntry = (TitleListView) findViewById;
        this.storageBrowserAdapter = new StorageBrowserAdapter(getBrowserContainer(false));
        TitleListView titleListView = this.localEntry;
        StorageBrowserAdapter storageBrowserAdapter2 = null;
        if (titleListView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localEntry");
            titleListView = null;
        }
        RecyclerView list = titleListView.getList();
        StorageBrowserAdapter storageBrowserAdapter3 = this.storageBrowserAdapter;
        if (storageBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storageBrowserAdapter");
            storageBrowserAdapter3 = null;
        }
        list.setAdapter(storageBrowserAdapter3);
        Fragment fragment = this;
        BrowserModel browserModel$default = BrowserModelKt.getBrowserModel$default(fragment, 3, (String) null, false, 4, (Object) null);
        this.localViewModel = browserModel$default;
        if (browserModel$default == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel$default = null;
        }
        browserModel$default.getDataset().observe(getViewLifecycleOwner(), new MLStorageBrowserFragmentKt$sam$androidx_lifecycle_Observer$0(new MLStorageBrowserFragment$onViewCreated$1(this)));
        BrowserModel browserModel = this.localViewModel;
        if (browserModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel = null;
        }
        browserModel.getLoading().observe(getViewLifecycleOwner(), new MLStorageBrowserFragmentKt$sam$androidx_lifecycle_Observer$0(new MLStorageBrowserFragment$onViewCreated$2(this)));
        BrowserModel browserModel2 = this.localViewModel;
        if (browserModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel2 = null;
        }
        browserModel2.browseRoot();
        BrowserModel browserModel3 = this.localViewModel;
        if (browserModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel3 = null;
        }
        browserModel3.getDescriptionUpdate().observe(getViewLifecycleOwner(), new MLStorageBrowserFragmentKt$sam$androidx_lifecycle_Observer$0(new MLStorageBrowserFragment$onViewCreated$3(this)));
        View findViewById2 = view.findViewById(R.id.network_browser_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        TitleListView titleListView2 = (TitleListView) findViewById2;
        this.networkEntry = titleListView2;
        if (titleListView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView2 = null;
        }
        titleListView2.getLoading().setShowNoMedia(false);
        TitleListView titleListView3 = this.networkEntry;
        if (titleListView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView3 = null;
        }
        EmptyLoadingStateView loading = titleListView3.getLoading();
        String string = getString(R.string.nomedia);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        loading.setEmptyText(string);
        StorageBrowserAdapter storageBrowserAdapter4 = new StorageBrowserAdapter(getBrowserContainer(true));
        TitleListView titleListView4 = this.networkEntry;
        if (titleListView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView4 = null;
        }
        titleListView4.getList().setAdapter(storageBrowserAdapter4);
        BrowserModel browserModel$default2 = BrowserModelKt.getBrowserModel$default(fragment, 1, (String) null, false, 4, (Object) null);
        this.networkViewModel = browserModel$default2;
        if (browserModel$default2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel$default2 = null;
        }
        browserModel$default2.getDataset().observe(getViewLifecycleOwner(), new MLStorageBrowserFragmentKt$sam$androidx_lifecycle_Observer$0(new MLStorageBrowserFragment$onViewCreated$4(storageBrowserAdapter4, this)));
        BrowserModel browserModel4 = this.networkViewModel;
        if (browserModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel4 = null;
        }
        browserModel4.getLoading().observe(getViewLifecycleOwner(), new MLStorageBrowserFragmentKt$sam$androidx_lifecycle_Observer$0(new MLStorageBrowserFragment$onViewCreated$5(this)));
        BrowserModel browserModel5 = this.networkViewModel;
        if (browserModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel5 = null;
        }
        browserModel5.browseRoot();
        TitleListView titleListView5 = this.localEntry;
        if (titleListView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localEntry");
            titleListView5 = null;
        }
        titleListView5.setDisplayInCards(false);
        TitleListView titleListView6 = this.networkEntry;
        if (titleListView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView6 = null;
        }
        titleListView6.setDisplayInCards(false);
        StorageBrowserAdapter[] storageBrowserAdapterArr = new StorageBrowserAdapter[2];
        StorageBrowserAdapter storageBrowserAdapter5 = this.storageBrowserAdapter;
        if (storageBrowserAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storageBrowserAdapter");
        } else {
            storageBrowserAdapter2 = storageBrowserAdapter5;
        }
        storageBrowserAdapterArr[0] = storageBrowserAdapter2;
        storageBrowserAdapterArr[1] = storageBrowserAdapter4;
        withAdapters(storageBrowserAdapterArr);
    }

    /* access modifiers changed from: private */
    public final void updateNetworkEmptyView(EmptyLoadingStateView emptyLoadingStateView) {
        NetworkMonitor networkMonitor2 = this.networkMonitor;
        TitleListView titleListView = null;
        if (networkMonitor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
            networkMonitor2 = null;
        }
        if (networkMonitor2.getConnected()) {
            BrowserModel browserModel = this.networkViewModel;
            if (browserModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
                browserModel = null;
            }
            if (browserModel.isEmpty()) {
                BrowserModel browserModel2 = this.networkViewModel;
                if (browserModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
                    browserModel2 = null;
                }
                if (Intrinsics.areEqual((Object) browserModel2.getLoading().getValue(), (Object) true)) {
                    emptyLoadingStateView.setState(EmptyLoadingState.LOADING);
                    return;
                }
                NetworkMonitor networkMonitor3 = this.networkMonitor;
                if (networkMonitor3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
                    networkMonitor3 = null;
                }
                if (networkMonitor3.getLanAllowed()) {
                    emptyLoadingStateView.setState(EmptyLoadingState.LOADING);
                    String string = getString(R.string.network_shares_discovery);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    emptyLoadingStateView.setLoadingText(string);
                } else {
                    emptyLoadingStateView.setState(EmptyLoadingState.EMPTY);
                    String string2 = getString(R.string.network_connection_needed);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    emptyLoadingStateView.setEmptyText(string2);
                }
                TitleListView titleListView2 = this.networkEntry;
                if (titleListView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
                } else {
                    titleListView = titleListView2;
                }
                titleListView.getList().setVisibility(8);
                return;
            }
            emptyLoadingStateView.setState(EmptyLoadingState.NONE);
            TitleListView titleListView3 = this.networkEntry;
            if (titleListView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            } else {
                titleListView = titleListView3;
            }
            titleListView.getList().setVisibility(0);
            return;
        }
        emptyLoadingStateView.setState(EmptyLoadingState.EMPTY);
        String string3 = getString(R.string.network_connection_needed);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        emptyLoadingStateView.setEmptyText(string3);
        TitleListView titleListView4 = this.networkEntry;
        if (titleListView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
        } else {
            titleListView = titleListView4;
        }
        titleListView.getList().setVisibility(8);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        Bundle arguments = getArguments();
        boolean z = arguments != null && arguments.getBoolean("from_onboarding", false);
        MenuItem findItem = menu.findItem(R.id.ml_menu_custom_dir);
        if (findItem != null) {
            findItem.setVisible(!z);
        }
        MenuItem findItem2 = menu.findItem(R.id.ml_menu_refresh);
        if (findItem2 != null) {
            findItem2.setVisible(false);
        }
        MenuItem findItem3 = menu.findItem(R.id.ml_menu_add_playlist);
        if (findItem3 != null) {
            findItem3.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.ml_menu_custom_dir) {
            return false;
        }
        showAddDirectoryDialog();
        return true;
    }

    private final void showAddDirectoryDialog() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Context context = activity;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AppCompatEditText appCompatEditText = new AppCompatEditText(context);
        appCompatEditText.setInputType(524288);
        builder.setTitle(R.string.add_custom_path);
        builder.setMessage(R.string.add_custom_path_description);
        builder.setView((View) appCompatEditText);
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) new MLStorageBrowserFragment$$ExternalSyntheticLambda0());
        builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new MLStorageBrowserFragment$$ExternalSyntheticLambda1(appCompatEditText, this));
        this.alertDialog = builder.show();
    }

    /* access modifiers changed from: private */
    public static final void showAddDirectoryDialog$lambda$3(AppCompatEditText appCompatEditText, MLStorageBrowserFragment mLStorageBrowserFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(appCompatEditText, "$input");
        Intrinsics.checkNotNullParameter(mLStorageBrowserFragment, "this$0");
        CharSequence valueOf = String.valueOf(appCompatEditText.getText());
        int length = valueOf.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean z2 = Intrinsics.compare((int) valueOf.charAt(!z ? i2 : length), 32) <= 0;
            if (!z) {
                if (!z2) {
                    z = true;
                } else {
                    i2++;
                }
            } else if (!z2) {
                break;
            } else {
                length--;
            }
        }
        String obj = valueOf.subSequence(i2, length + 1).toString();
        File file = new File(obj);
        if (!file.exists() || !file.isDirectory()) {
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = mLStorageBrowserFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            String string = mLStorageBrowserFragment.getString(R.string.directorynotfound, obj);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            uiTools.snacker(requireActivity, string);
            return;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(mLStorageBrowserFragment), new MLStorageBrowserFragment$showAddDirectoryDialog$lambda$3$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key), (CoroutineStart) null, new MLStorageBrowserFragment$showAddDirectoryDialog$2$2(mLStorageBrowserFragment, file, (Continuation<? super MLStorageBrowserFragment$showAddDirectoryDialog$2$2>) null), 2, (Object) null);
    }

    private final MLStorageBrowserFragment$getBrowserContainer$1 getBrowserContainer(boolean z) {
        return new MLStorageBrowserFragment$getBrowserContainer$1(z, this);
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        StorageBrowserAdapter storageBrowserAdapter2 = this.storageBrowserAdapter;
        BrowserModel browserModel = null;
        if (storageBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storageBrowserAdapter");
            storageBrowserAdapter2 = null;
        }
        MediaLibraryItem item = storageBrowserAdapter2.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
        Storage storage = (Storage) item;
        String path = storage.getUri().getPath();
        if (path != null) {
            BrowserModel browserModel2 = this.localViewModel;
            if (browserModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
                browserModel2 = null;
            }
            browserModel2.deleteCustomDirectory(path);
            BrowserModel browserModel3 = this.localViewModel;
            if (browserModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            } else {
                browserModel = browserModel3;
            }
            browserModel.remove(storage);
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
            ((AudioPlayerContainerActivity) activity).updateLib();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/browser/MLStorageBrowserFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/browser/MLStorageBrowserFragment;", "onboarding", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MLStorageBrowserFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MLStorageBrowserFragment newInstance(boolean z) {
            MLStorageBrowserFragment mLStorageBrowserFragment = new MLStorageBrowserFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("from_onboarding", z);
            mLStorageBrowserFragment.setArguments(bundle);
            return mLStorageBrowserFragment;
        }
    }
}
