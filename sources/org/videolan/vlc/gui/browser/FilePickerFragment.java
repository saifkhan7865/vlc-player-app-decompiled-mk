package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Strings;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.providers.PickerType;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.viewmodels.SortableModel;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0006\u0010\u000f\u001a\u00020\fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0006H\u0014J \u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\u0012\u0010\u001b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J \u0010\u001e\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016J\u001a\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\fH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/videolan/vlc/gui/browser/FilePickerFragment;", "Lorg/videolan/vlc/gui/browser/FileBrowserFragment;", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "()V", "isFile", "", "()Z", "isNetwork", "pickerType", "Lorg/videolan/vlc/providers/PickerType;", "backTo", "", "tag", "", "browseUp", "containerActivity", "Landroidx/fragment/app/FragmentActivity;", "createFragment", "Landroidx/fragment/app/Fragment;", "defineIsRoot", "onClick", "v", "Landroid/view/View;", "position", "", "item", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onImageClick", "onStart", "onViewCreated", "view", "pickFile", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setupBrowser", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilePickerFragment.kt */
public final class FilePickerFragment extends FileBrowserFragment implements BrowserContainer<MediaLibraryItem> {
    private final boolean isFile = true;
    private final boolean isNetwork;
    private PickerType pickerType;

    public void onImageClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    /* access modifiers changed from: protected */
    public Fragment createFragment() {
        return new FilePickerFragment();
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        FragmentActivity activity;
        Intent intent = requireActivity().getIntent();
        if (intent != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, BaseBrowserFragmentKt.KEY_MEDIA, MediaWrapper.class);
            } else {
                Parcelable parcelableExtra = intent.getParcelableExtra(BaseBrowserFragmentKt.KEY_MEDIA);
                if (!(parcelableExtra instanceof MediaWrapper)) {
                    parcelableExtra = null;
                }
                parcelable = (MediaWrapper) parcelableExtra;
            }
            MediaWrapper mediaWrapper = (MediaWrapper) parcelable;
            if (mediaWrapper != null && ((mediaWrapper.getUri() == null || Intrinsics.areEqual((Object) mediaWrapper.getUri().getScheme(), (Object) "http") || Intrinsics.areEqual((Object) mediaWrapper.getUri().getScheme(), (Object) "content") || Intrinsics.areEqual((Object) mediaWrapper.getUri().getScheme(), (Object) "fd")) && (activity = getActivity()) != null)) {
                activity.setIntent((Intent) null);
            }
        }
        Intent intent2 = requireActivity().getIntent();
        if (intent2 != null) {
            this.pickerType = PickerType.values()[intent2.getIntExtra(BaseBrowserFragmentKt.KEY_PICKER_TYPE, 0)];
        } else {
            PickerType pickerType2 = PickerType.SUBTITLE;
        }
        super.onCreate(bundle);
        setAdapter(new FilePickerAdapter(this));
    }

    /* access modifiers changed from: protected */
    public void setupBrowser() {
        ViewModelStoreOwner viewModelStoreOwner = this;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        String mrl = getMrl();
        PickerType pickerType2 = this.pickerType;
        if (pickerType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pickerType");
            pickerType2 = null;
        }
        setViewModel((SortableModel) new ViewModelProvider(viewModelStoreOwner, (ViewModelProvider.Factory) new BrowserModel.Factory(requireContext, mrl, 2, false, pickerType2)).get(BrowserModel.class));
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        EmptyLoadingStateView emptyLoadingStateView = getBinding().emptyLoading;
        String string = getString(R.string.no_subs_found);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        emptyLoadingStateView.setEmptyText(string);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
        if (((org.videolan.vlc.gui.ContentActivity) r0).getDisplayTitle() != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStart() {
        /*
            r2 = this;
            super.onStart()
            androidx.fragment.app.FragmentActivity r0 = r2.getActivity()
            boolean r0 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            if (r0 == 0) goto L_0x001c
            androidx.fragment.app.FragmentActivity r0 = r2.getActivity()
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.vlc.gui.ContentActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            org.videolan.vlc.gui.ContentActivity r0 = (org.videolan.vlc.gui.ContentActivity) r0
            boolean r0 = r0.getDisplayTitle()
            if (r0 == 0) goto L_0x002c
        L_0x001c:
            androidx.fragment.app.FragmentActivity r0 = r2.getActivity()
            if (r0 != 0) goto L_0x0023
            goto L_0x002c
        L_0x0023:
            java.lang.String r1 = r2.getTitle()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setTitle(r1)
        L_0x002c:
            org.videolan.vlc.gui.view.SwipeRefreshLayout r0 = r2.getSwipeRefreshLayout()
            r1 = 0
            r0.setEnabled(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.FilePickerFragment.onStart():void");
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        if (mediaWrapper.getType() == 3) {
            browse(mediaWrapper, true);
        } else {
            pickFile(mediaWrapper);
        }
    }

    public void backTo(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        if (Intrinsics.areEqual((Object) str, (Object) "root")) {
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
            int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
            for (int i = 0; i < backStackEntryCount; i++) {
                supportFragmentManager.popBackStack(str, 1);
            }
            ((BrowserModel) getViewModel()).setDestination(MLServiceLocator.getAbstractMediaWrapper(Uri.parse(str)));
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            Fragment fragment = this;
            beginTransaction.detach(fragment).attach(fragment).commit();
            return;
        }
        super.backTo(str);
    }

    private final void pickFile(MediaWrapper mediaWrapper) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.putExtra(FilePickerFragmentKt.EXTRA_MRL, mediaWrapper.getLocation());
        requireActivity().setResult(-1, intent);
        requireActivity().finish();
    }

    public final void browseUp() {
        if (isRootDirectory()) {
            requireActivity().finish();
            return;
        }
        String mrl = getMrl();
        Uri uri = null;
        if (Intrinsics.areEqual((Object) mrl != null ? Strings.removeFileScheme(mrl) : null, (Object) AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY())) {
            setMrl((String) null);
            setRootDirectory(true);
            ((BrowserModel) getViewModel()).refresh();
        } else if (getMrl() != null) {
            String parent = FileUtils.INSTANCE.getParent(getMrl());
            if (parent != null) {
                uri = Uri.parse(parent);
            }
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(uri);
            Intrinsics.checkNotNull(abstractMediaWrapper);
            browse(abstractMediaWrapper, false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean defineIsRoot() {
        String mrl = getMrl();
        if (mrl == null) {
            return true;
        }
        if (StringsKt.startsWith$default(mrl, "file", false, 2, (Object) null)) {
            String removeFileScheme = Strings.removeFileScheme(mrl);
            for (String startsWith$default : (List) BuildersKt.runBlocking(Dispatchers.getIO(), new FilePickerFragment$defineIsRoot$1$rootDirectories$1(this, (Continuation<? super FilePickerFragment$defineIsRoot$1$rootDirectories$1>) null))) {
                if (StringsKt.startsWith$default(removeFileScheme, startsWith$default, false, 2, (Object) null)) {
                    return false;
                }
            }
            return true;
        } else if (mrl.length() < 7) {
            return true;
        } else {
            return false;
        }
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
}
