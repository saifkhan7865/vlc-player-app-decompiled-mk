package org.videolan.vlc.interfaces;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.viewmodels.BaseModel;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/interfaces/Sortable;", "Landroid/widget/PopupMenu$OnMenuItemClickListener;", "getVM", "Lorg/videolan/vlc/viewmodels/BaseModel;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onMenuItemClick", "", "item", "Landroid/view/MenuItem;", "sort", "", "v", "Landroid/view/View;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Sortable.kt */
public interface Sortable extends PopupMenu.OnMenuItemClickListener {
    BaseModel<? extends MediaLibraryItem> getVM();

    boolean onMenuItemClick(MenuItem menuItem);

    void sort(View view);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Sortable.kt */
    public static final class DefaultImpls {
        public static void sort(Sortable sortable, View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            BaseModel<? extends MediaLibraryItem> vm = sortable.getVM();
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.sort_options);
            popupMenu.getMenu().findItem(R.id.ml_menu_sortby_filename).setVisible(vm.canSortByFileNameName());
            popupMenu.getMenu().findItem(R.id.ml_menu_sortby_length).setVisible(vm.canSortByDuration());
            popupMenu.getMenu().findItem(R.id.ml_menu_sortby_date).setVisible(vm.canSortByReleaseDate());
            popupMenu.getMenu().findItem(R.id.ml_menu_sortby_last_modified).setVisible(vm.canSortByLastModified());
            popupMenu.getMenu().findItem(R.id.ml_menu_sortby_number).setVisible(false);
            popupMenu.setOnMenuItemClickListener(sortable);
            popupMenu.show();
        }

        public static boolean onMenuItemClick(Sortable sortable, MenuItem menuItem) {
            int i;
            Intrinsics.checkNotNullParameter(menuItem, "item");
            BaseModel<? extends MediaLibraryItem> vm = sortable.getVM();
            int itemId = menuItem.getItemId();
            if (itemId == R.id.ml_menu_sortby_name) {
                i = 1;
            } else if (itemId == R.id.ml_menu_sortby_filename) {
                i = 10;
            } else if (itemId == R.id.ml_menu_sortby_length) {
                i = 2;
            } else if (itemId == R.id.ml_menu_sortby_last_modified) {
                i = 4;
            } else if (itemId != R.id.ml_menu_sortby_date) {
                return false;
            } else {
                i = 5;
            }
            vm.sort(i);
            return true;
        }
    }
}
