package org.videolan.vlc.gui.browser;

import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.vlc.databinding.BrowserItemBinding;
import org.videolan.vlc.gui.helpers.ThreeStatesCheckbox;

@Metadata(d1 = {"\u0000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J \u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J \u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J\u0018\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J \u0010 \u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J \u0010!\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J\u0014\u0010\"\u001a\u00020\u00172\n\u0010#\u001a\u0006\u0012\u0002\b\u00030$H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0006R\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006¨\u0006%"}, d2 = {"org/videolan/vlc/gui/browser/MLStorageBrowserFragment$getBrowserContainer$1", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "inCards", "", "getInCards", "()Z", "setInCards", "(Z)V", "isFile", "isNetwork", "isRootDirectory", "mrl", "", "getMrl", "()Ljava/lang/String;", "scannedDirectory", "getScannedDirectory", "containerActivity", "Landroidx/fragment/app/FragmentActivity;", "getStorageDelegate", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "onClick", "", "v", "Landroid/view/View;", "position", "", "item", "onCtxClick", "onImageClick", "onItemFocused", "onLongClick", "onMainActionClick", "onUpdateFinished", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MLStorageBrowserFragment.kt */
public final class MLStorageBrowserFragment$getBrowserContainer$1 implements BrowserContainer<MediaLibraryItem> {
    private boolean inCards;
    private final boolean isFile;
    private final boolean isNetwork;
    private final boolean isRootDirectory = true;
    private final String mrl;
    private final boolean scannedDirectory;
    final /* synthetic */ MLStorageBrowserFragment this$0;

    public void onImageClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public void onItemFocused(View view, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public boolean onLongClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        return false;
    }

    public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
    }

    MLStorageBrowserFragment$getBrowserContainer$1(boolean z, MLStorageBrowserFragment mLStorageBrowserFragment) {
        this.this$0 = mLStorageBrowserFragment;
        this.isNetwork = z;
        this.isFile = !z;
    }

    public FragmentActivity containerActivity() {
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return requireActivity;
    }

    public IStorageFragmentDelegate getStorageDelegate() {
        return this.this$0;
    }

    public boolean getScannedDirectory() {
        return this.scannedDirectory;
    }

    public String getMrl() {
        return this.mrl;
    }

    public boolean isRootDirectory() {
        return this.isRootDirectory;
    }

    public boolean isNetwork() {
        return this.isNetwork;
    }

    public boolean isFile() {
        return this.isFile;
    }

    public boolean getInCards() {
        return this.inCards;
    }

    public void setInCards(boolean z) {
        this.inCards = z;
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        MediaWrapper mediaWrapper;
        ThreeStatesCheckbox threeStatesCheckbox;
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        MediaWrapper mediaWrapper2 = null;
        MediaWrapper mediaWrapper3 = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
        if (mediaWrapper3 == null || (mediaWrapper = MLServiceLocator.getAbstractMediaWrapper(mediaWrapper3.getUri())) == null) {
            Storage storage = mediaLibraryItem instanceof Storage ? (Storage) mediaLibraryItem : null;
            if (storage != null) {
                mediaWrapper2 = MLServiceLocator.getAbstractMediaWrapper(storage.getUri());
            }
            if (mediaWrapper2 != null) {
                mediaWrapper = mediaWrapper2;
            } else {
                return;
            }
        }
        mediaWrapper.setType(3);
        MLStorageBrowserFragment mLStorageBrowserFragment = this.this$0;
        BrowserItemBinding browserItemBinding = (BrowserItemBinding) DataBindingUtil.findBinding(view);
        boolean z = false;
        if (!(browserItemBinding == null || (threeStatesCheckbox = browserItemBinding.browserCheckbox) == null || threeStatesCheckbox.getState() != 1)) {
            z = true;
        }
        mLStorageBrowserFragment.browse(mediaWrapper, z, new StorageBrowserFragment(), "root");
    }

    public void onCtxClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (isRootDirectory()) {
            StorageBrowserAdapter access$getStorageBrowserAdapter$p = this.this$0.storageBrowserAdapter;
            if (access$getStorageBrowserAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storageBrowserAdapter");
                access$getStorageBrowserAdapter$p = null;
            }
            MediaLibraryItem item = access$getStorageBrowserAdapter$p.getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
            String path = ((Storage) item).getUri().getPath();
            if (path != null) {
                LifecycleOwnerKt.getLifecycleScope(this.this$0).launchWhenStarted(new MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1(this.this$0, path, i, mediaLibraryItem, (Continuation<? super MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1>) null));
            }
        }
    }
}
