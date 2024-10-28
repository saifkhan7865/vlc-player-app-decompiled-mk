package org.videolan.vlc.gui.browser;

import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\"\u0010\u0006\u001a\u00020\u00072\u0010\u0010\b\u001a\f\u0012\u0004\u0012\u00020\n0\tR\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/browser/FilePickerAdapter;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "browserContainer", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Lorg/videolan/vlc/gui/browser/BrowserContainer;)V", "onBindViewHolder", "", "holder", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "Landroidx/databinding/ViewDataBinding;", "position", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilePickerAdapter.kt */
public final class FilePickerAdapter extends BaseBrowserAdapter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FilePickerAdapter(BrowserContainer<MediaLibraryItem> browserContainer) {
        super(browserContainer, 0, false, false, 14, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(browserContainer, "browserContainer");
    }

    public void onBindViewHolder(BaseBrowserAdapter.ViewHolder<ViewDataBinding> viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        BaseBrowserAdapter.MediaViewHolder mediaViewHolder = (BaseBrowserAdapter.MediaViewHolder) viewHolder;
        MediaLibraryItem item = getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
        MediaWrapper mediaWrapper = (MediaWrapper) item;
        mediaViewHolder.getBindingContainer().setItem(mediaWrapper);
        mediaViewHolder.getBindingContainer().setHasContextMenu(false);
        mediaViewHolder.getBindingContainer().setProtocol((String) null);
        mediaViewHolder.getBindingContainer().setCover(getIcon(mediaWrapper, false));
    }
}
