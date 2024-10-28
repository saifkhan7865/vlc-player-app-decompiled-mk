package org.videolan.vlc.viewmodels;

import android.view.Menu;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"prepareOptionsMenu", "", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "menu", "Landroid/view/Menu;", "sortMenuTitles", "index", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MedialibraryViewModel.kt */
public final class MedialibraryViewModelKt {
    public static final void prepareOptionsMenu(MedialibraryViewModel medialibraryViewModel, Menu menu) {
        Intrinsics.checkNotNullParameter(medialibraryViewModel, "<this>");
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.ml_menu_sortby).setVisible(medialibraryViewModel.canSortByName());
        menu.findItem(R.id.ml_menu_sortby_filename).setVisible(medialibraryViewModel.canSortByFileNameName());
        menu.findItem(R.id.ml_menu_sortby_artist_name).setVisible(medialibraryViewModel.canSortByArtist());
        menu.findItem(R.id.ml_menu_sortby_album_name).setVisible(medialibraryViewModel.canSortByAlbum());
        menu.findItem(R.id.ml_menu_sortby_length).setVisible(medialibraryViewModel.canSortByDuration());
        menu.findItem(R.id.ml_menu_sortby_date).setVisible(medialibraryViewModel.canSortByReleaseDate());
        menu.findItem(R.id.ml_menu_sortby_last_modified).setVisible(medialibraryViewModel.canSortByLastModified());
        menu.findItem(R.id.ml_menu_sortby_media_number).setVisible(medialibraryViewModel.canSortByMediaNumber());
        menu.findItem(R.id.ml_menu_sortby_number).setVisible(false);
    }

    public static final void sortMenuTitles(MedialibraryViewModel medialibraryViewModel, Menu menu, int i) {
        Intrinsics.checkNotNullParameter(medialibraryViewModel, "<this>");
        Intrinsics.checkNotNullParameter(menu, "menu");
        UiTools.INSTANCE.updateSortTitles(menu, medialibraryViewModel.getProviders()[i]);
    }
}
