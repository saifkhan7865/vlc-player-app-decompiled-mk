package org.videolan.vlc.gui.helpers;

import android.view.MenuItem;
import android.widget.PopupMenu;
import org.videolan.medialibrary.interfaces.media.Bookmark;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BookmarkListDelegate$$ExternalSyntheticLambda0 implements PopupMenu.OnMenuItemClickListener {
    public final /* synthetic */ Bookmark f$0;
    public final /* synthetic */ BookmarkListDelegate f$1;

    public /* synthetic */ BookmarkListDelegate$$ExternalSyntheticLambda0(Bookmark bookmark, BookmarkListDelegate bookmarkListDelegate) {
        this.f$0 = bookmark;
        this.f$1 = bookmarkListDelegate;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return BookmarkListDelegate.onPopupMenu$lambda$4(this.f$0, this.f$1, menuItem);
    }
}
