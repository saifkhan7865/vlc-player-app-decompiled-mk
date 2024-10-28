package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.collection.SimpleArrayMap;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.BrowserItemBinding;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.helpers.ThreeStatesCheckbox;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.viewmodels.browser.BrowserModelKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005JA\u0010\u001e\u001a\u00020\u001f26\u0010 \u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u001f0!H\u0001J\t\u0010&\u001a\u00020\u001fH\u0001J\u0019\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\tH\u0001J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0014J\n\u0010/\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u00100\u001a\u00020\rH\u0014J \u00101\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0003H\u0016J\u0012\u00105\u001a\u00020\u001f2\b\u00106\u001a\u0004\u0018\u000107H\u0016J \u00108\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0003H\u0016J \u00109\u001a\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0003H\u0016J \u0010:\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0003H\u0016J\u0010\u0010;\u001a\u00020\r2\u0006\u00104\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020\u001f2\u0006\u0010A\u001a\u000207H\u0016J\b\u0010B\u001a\u00020\u001fH\u0016J\b\u0010C\u001a\u00020\u001fH\u0016J\u001a\u0010D\u001a\u00020\u001f2\u0006\u0010E\u001a\u00020)2\b\u00106\u001a\u0004\u0018\u000107H\u0017J\t\u0010F\u001a\u00020\u001fH\u0001J\b\u0010G\u001a\u00020\u001fH\u0014J\b\u0010H\u001a\u00020\u001fH\u0002J\u001c\u0010I\u001a\u00020\u001f2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020L0KH\u0001¢\u0006\u0002\u0010MJ\u0011\u0010N\u001a\u00020\u001f2\u0006\u0010O\u001a\u00020PH\u0001R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8TX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\rXD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\rXD¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001e\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lorg/videolan/vlc/gui/browser/StorageBrowserFragment;", "Lorg/videolan/vlc/gui/browser/FileBrowserFragment;", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "()V", "alertDialog", "Landroidx/appcompat/app/AlertDialog;", "categoryTitle", "", "getCategoryTitle", "()Ljava/lang/String;", "inCards", "", "getInCards", "()Z", "setInCards", "(Z)V", "isFile", "isNetwork", "processingFolders", "Landroidx/collection/SimpleArrayMap;", "Landroid/widget/CheckBox;", "getProcessingFolders", "()Landroidx/collection/SimpleArrayMap;", "scannedDirectory", "getScannedDirectory", "setScannedDirectory", "snack", "Lcom/google/android/material/snackbar/Snackbar;", "addBannedFoldersCallback", "", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "folder", "banned", "addRootsCallback", "checkBoxAction", "v", "Landroid/view/View;", "mrl", "containerActivity", "Landroidx/fragment/app/FragmentActivity;", "createFragment", "Landroidx/fragment/app/Fragment;", "getStorageDelegate", "hasFAB", "onClick", "position", "", "item", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCtxClick", "onLongClick", "onMainActionClick", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onSaveInstanceState", "outState", "onStart", "onStop", "onViewCreated", "view", "removeRootsCallback", "setupBrowser", "showAddDirectoryDialog", "withAdapters", "adapters", "", "Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;", "([Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;)V", "withContext", "context", "Landroid/content/Context;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageBrowserFragment.kt */
public final class StorageBrowserFragment extends FileBrowserFragment implements BrowserContainer<MediaLibraryItem>, IStorageFragmentDelegate {
    private final /* synthetic */ StorageFragmentDelegate $$delegate_0 = new StorageFragmentDelegate();
    private AlertDialog alertDialog;
    private boolean inCards;
    private final boolean isFile = true;
    private final boolean isNetwork;
    private boolean scannedDirectory;
    private Snackbar snack;

    /* access modifiers changed from: private */
    public static final void showAddDirectoryDialog$lambda$3(DialogInterface dialogInterface, int i) {
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

    public void onCtxClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
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

    public boolean getScannedDirectory() {
        return this.scannedDirectory;
    }

    public void setScannedDirectory(boolean z) {
        this.scannedDirectory = z;
    }

    public boolean getInCards() {
        return this.inCards;
    }

    public void setInCards(boolean z) {
        this.inCards = z;
    }

    /* access modifiers changed from: protected */
    public String getCategoryTitle() {
        String string = getString(R.string.directories_summary);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* access modifiers changed from: protected */
    public Fragment createFragment() {
        return new StorageBrowserFragment();
    }

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        if (requireActivity() instanceof SecondaryActivity) {
            return false;
        }
        return super.hasFAB();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: org.videolan.vlc.gui.browser.StorageBrowserAdapter[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r5) {
        /*
            r4 = this;
            super.onCreate(r5)
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = new org.videolan.vlc.gui.browser.StorageBrowserAdapter
            r1 = r4
            org.videolan.vlc.gui.browser.BrowserContainer r1 = (org.videolan.vlc.gui.browser.BrowserContainer) r1
            r0.<init>(r1)
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r0 = (org.videolan.vlc.gui.browser.BaseBrowserAdapter) r0
            r4.setAdapter(r0)
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r0 = r4.getAdapter()
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.vlc.gui.browser.StorageBrowserAdapter"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = (org.videolan.vlc.gui.browser.StorageBrowserAdapter) r0
            org.videolan.medialibrary.interfaces.Medialibrary r2 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String[] r2 = r2.bannedFolders()
            java.lang.String r3 = "bannedFolders(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            java.util.List r2 = kotlin.collections.ArraysKt.toList((T[]) r2)
            r0.setBannedFolders(r2)
            if (r5 != 0) goto L_0x0037
            android.os.Bundle r5 = r4.getArguments()
        L_0x0037:
            if (r5 == 0) goto L_0x0042
            java.lang.String r0 = "key_in_medialib"
            boolean r5 = r5.getBoolean(r0)
            r4.setScannedDirectory(r5)
        L_0x0042:
            androidx.fragment.app.FragmentActivity r5 = r4.requireActivity()
            java.lang.String r0 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            android.content.Context r5 = (android.content.Context) r5
            r4.withContext(r5)
            r5 = 1
            org.videolan.vlc.gui.browser.StorageBrowserAdapter[] r5 = new org.videolan.vlc.gui.browser.StorageBrowserAdapter[r5]
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r0 = r4.getAdapter()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            r1 = 0
            r5[r1] = r0
            r4.withAdapters(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.StorageBrowserFragment.onCreate(android.os.Bundle):void");
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (isRootDirectory() && Settings.INSTANCE.getShowTvUi()) {
            this.snack = Snackbar.make(view, R.string.tv_settings_hint, -2);
            if (AndroidUtil.isLolliPopOrLater) {
                Snackbar snackbar = this.snack;
                View view2 = snackbar != null ? snackbar.getView() : null;
                if (view2 != null) {
                    view2.setElevation((float) view.getResources().getDimensionPixelSize(R.dimen.audio_player_elevation));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupBrowser() {
        setViewModel(BrowserModelKt.getBrowserModel$default(this, 3, getMrl(), false, 4, (Object) null));
    }

    public void onStart() {
        super.onStart();
        addRootsCallback();
        Snackbar snackbar = this.snack;
        if (snackbar != null) {
            snackbar.show();
        }
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new StorageBrowserFragment$onStart$1(this, (Continuation<? super StorageBrowserFragment$onStart$1>) null));
        addBannedFoldersCallback(new StorageBrowserFragment$onStart$2(this));
    }

    public void onStop() {
        super.onStop();
        removeRootsCallback();
        Snackbar snackbar = this.snack;
        if (snackbar != null) {
            snackbar.dismiss();
        }
        AlertDialog alertDialog2 = this.alertDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            alertDialog2.dismiss();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(StorageBrowserFragmentKt.KEY_IN_MEDIALIB, getScannedDirectory());
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.ml_menu_custom_dir);
        if (findItem != null) {
            findItem.setVisible(true);
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
            return super.onOptionsItemSelected(menuItem);
        }
        showAddDirectoryDialog();
        return true;
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        String str;
        BrowserItemBinding browserItemBinding;
        ThreeStatesCheckbox threeStatesCheckbox;
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Uri uri = null;
        Storage storage = mediaLibraryItem instanceof Storage ? (Storage) mediaLibraryItem : null;
        MediaWrapper abstractMediaWrapper = storage != null ? MLServiceLocator.getAbstractMediaWrapper(storage.getUri()) : null;
        if (abstractMediaWrapper != null) {
            abstractMediaWrapper.setType(3);
            boolean z = true;
            if (!getScannedDirectory() && ((browserItemBinding = (BrowserItemBinding) DataBindingUtil.findBinding(view)) == null || (threeStatesCheckbox = browserItemBinding.browserCheckbox) == null || threeStatesCheckbox.getState() != 1)) {
                z = false;
            }
            Fragment createFragment = createFragment();
            if (isRootDirectory()) {
                str = "root";
            } else if (getCurrentMedia() != null) {
                MediaWrapper currentMedia = getCurrentMedia();
                if (currentMedia != null) {
                    uri = currentMedia.getUri();
                }
                str = String.valueOf(uri);
            } else {
                str = getMrl();
                Intrinsics.checkNotNull(str);
            }
            browse(abstractMediaWrapper, z, createFragment, str);
        }
    }

    public boolean onLongClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        String path = ((Storage) mediaLibraryItem).getUri().getPath();
        if (path == null) {
            return true;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new StorageBrowserFragment$onLongClick$1$1(this, path, (Continuation<? super StorageBrowserFragment$onLongClick$1$1>) null), 2, (Object) null);
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
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) new StorageBrowserFragment$$ExternalSyntheticLambda0());
        builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new StorageBrowserFragment$$ExternalSyntheticLambda1(appCompatEditText, this));
        this.alertDialog = builder.show();
    }

    /* access modifiers changed from: private */
    public static final void showAddDirectoryDialog$lambda$6(AppCompatEditText appCompatEditText, StorageBrowserFragment storageBrowserFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(appCompatEditText, "$input");
        Intrinsics.checkNotNullParameter(storageBrowserFragment, "this$0");
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
            FragmentActivity requireActivity = storageBrowserFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            String string = storageBrowserFragment.getString(R.string.directorynotfound, obj);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            uiTools.snacker(requireActivity, string);
            return;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(storageBrowserFragment), new StorageBrowserFragment$showAddDirectoryDialog$lambda$6$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key), (CoroutineStart) null, new StorageBrowserFragment$showAddDirectoryDialog$2$2(storageBrowserFragment, file, (Continuation<? super StorageBrowserFragment$showAddDirectoryDialog$2$2>) null), 2, (Object) null);
    }

    public FragmentActivity containerActivity() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return requireActivity;
    }

    public IStorageFragmentDelegate getStorageDelegate() {
        return this;
    }

    public boolean isNetwork() {
        return this.isNetwork;
    }

    public boolean isFile() {
        return this.isFile;
    }
}
