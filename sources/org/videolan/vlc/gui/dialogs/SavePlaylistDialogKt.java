package org.videolan.vlc.gui.dialogs;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Playlist;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"getPlaylistByName", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "name", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: SavePlaylistDialog.kt */
public final class SavePlaylistDialogKt {
    public static final Playlist getPlaylistByName(Medialibrary medialibrary, String str) {
        Playlist playlist;
        Intrinsics.checkNotNullParameter(medialibrary, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        int i = 0;
        Playlist[] playlists = medialibrary.getPlaylists(Playlist.Type.All, false);
        Intrinsics.checkNotNullExpressionValue(playlists, "getPlaylists(...)");
        Object[] objArr = (Object[]) playlists;
        int length = objArr.length;
        while (true) {
            if (i >= length) {
                playlist = null;
                break;
            }
            playlist = objArr[i];
            if (Intrinsics.areEqual((Object) ((Playlist) playlist).getTitle(), (Object) str)) {
                break;
            }
            i++;
        }
        return playlist;
    }
}
