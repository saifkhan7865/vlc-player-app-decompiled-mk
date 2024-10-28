package org.videolan.vlc.gui.browser;

import android.content.Context;
import kotlin.Metadata;
import org.videolan.vlc.viewmodels.browser.IPathOperationDelegate;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/browser/PathAdapterListener;", "", "backTo", "", "tag", "", "currentContext", "Landroid/content/Context;", "getPathOperationDelegate", "Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "showRoot", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PathAdapter.kt */
public interface PathAdapterListener {
    void backTo(String str);

    Context currentContext();

    IPathOperationDelegate getPathOperationDelegate();

    boolean showRoot();
}
