package org.videolan.vlc.viewmodels.browser;

import android.net.Uri;
import kotlin.Metadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\n\u0010\t\u001a\u0004\u0018\u00010\nH&J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H&J\u0012\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\nH&J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\fH&¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "", "appendPathToUri", "", "path", "", "uri", "Landroid/net/Uri$Builder;", "consumeSource", "getAndRemoveDestination", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getSource", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "makePathSafe", "replaceStoragePath", "retrieveSafePath", "encoded", "setDestination", "media", "setSource", "currentItem", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PathOperationDelegate.kt */
public interface IPathOperationDelegate {
    void appendPathToUri(String str, Uri.Builder builder);

    void consumeSource();

    MediaWrapper getAndRemoveDestination();

    MediaLibraryItem getSource();

    String makePathSafe(String str);

    String replaceStoragePath(String str);

    String retrieveSafePath(String str);

    void setDestination(MediaWrapper mediaWrapper);

    void setSource(MediaLibraryItem mediaLibraryItem);
}
