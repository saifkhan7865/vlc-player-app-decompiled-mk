package org.videolan.vlc.gui.helpers;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0004J\u0016\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0012J\u0016\u0010!\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u0004J\u0016\u0010&\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0012J\u0016\u0010(\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0012J\u0016\u0010*\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0012J\u0016\u0010+\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0012J\u0016\u0010,\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010 \u001a\u00020-J\u0016\u0010.\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019¨\u0006/"}, d2 = {"Lorg/videolan/vlc/gui/helpers/TalkbackUtil;", "", "()V", "getAlbum", "", "context", "Landroid/content/Context;", "album", "Lorg/videolan/medialibrary/interfaces/media/Album;", "getAlbumTitle", "getAll", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getArtist", "artist", "Lorg/videolan/medialibrary/interfaces/media/Artist;", "getAudioTrack", "audio", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getDir", "folder", "favorite", "", "getDuration", "duration", "", "getFolder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "getGenre", "genre", "Lorg/videolan/medialibrary/interfaces/media/Genre;", "getPlayed", "video", "getPlaylist", "playlist", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "getReleaseDate", "date", "getStream", "stream", "getTimeAndArtist", "item", "getTrackNumber", "getVideo", "getVideoGroup", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "millisToString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TalkbackUtil.kt */
public final class TalkbackUtil {
    public static final TalkbackUtil INSTANCE = new TalkbackUtil();

    private TalkbackUtil() {
    }

    public final String getDuration(Context context, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.talkback_duration, new Object[]{millisToString(context, j)});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getDuration(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, TypedValues.TransitionType.S_DURATION);
        String string = context.getString(R.string.talkback_duration, new Object[]{str});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getPlayed(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, "video");
        if (mediaWrapper.getPlayCount() > 0) {
            return context.getString(R.string.talkback_already_played);
        }
        return null;
    }

    public final String getAlbumTitle(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.ALBUM);
        String string = context.getString(R.string.talkback_album, new Object[]{str});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getReleaseDate(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (str == null) {
            return "";
        }
        String string = context.getString(R.string.talkback_release_date, new Object[]{str});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getVideo(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, "video");
        String string = context.getString(R.string.talkback_video, new Object[]{mediaWrapper.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(TalkbackUtilKt.talkbackAppend$default(string, getPlayed(context, mediaWrapper), false, 2, (Object) null), getDuration(context, millisToString(context, mediaWrapper.getLength())), false, 2, (Object) null);
    }

    public final String getStream(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, "stream");
        String string = context.getString(R.string.talkback_stream, new Object[]{mediaWrapper.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getAudioTrack(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, Constants.ID_AUDIO);
        String string = context.getString(R.string.talkback_audio_track, new Object[]{mediaWrapper.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(TalkbackUtilKt.talkbackAppend$default(TalkbackUtilKt.talkbackAppend$default(string, getDuration(context, millisToString(context, mediaWrapper.getLength())), false, 2, (Object) null), context.getString(R.string.talkback_album, new Object[]{mediaWrapper.getAlbum()}), false, 2, (Object) null), context.getString(R.string.talkback_artist, new Object[]{mediaWrapper.getArtist()}), false, 2, (Object) null);
    }

    public final String getVideoGroup(Context context, VideoGroup videoGroup) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(videoGroup, "video");
        String string = context.getString(R.string.talkback_video_group, new Object[]{videoGroup.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(string, context.getResources().getQuantityString(R.plurals.videos_quantity, videoGroup.mediaCount(), new Object[]{Integer.valueOf(videoGroup.mediaCount())}), false, 2, (Object) null);
    }

    public final String getGenre(Context context, Genre genre) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(genre, "genre");
        String string = context.getString(R.string.talkback_genre, new Object[]{genre.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(string, context.getResources().getQuantityString(R.plurals.track_quantity, genre.getTracksCount(), new Object[]{Integer.valueOf(genre.getTracksCount())}), false, 2, (Object) null);
    }

    public final String getArtist(Context context, Artist artist) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (artist == null) {
            return null;
        }
        String string = context.getString(R.string.talkback_artist, new Object[]{artist.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(string, context.getResources().getQuantityString(R.plurals.albums_quantity, artist.getAlbumsCount(), new Object[]{Integer.valueOf(artist.getAlbumsCount())}), false, 2, (Object) null);
    }

    public final String getAlbum(Context context, Album album) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(album, ArtworkProvider.ALBUM);
        String string = context.getString(R.string.talkback_album, new Object[]{album.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(TalkbackUtilKt.talkbackAppend$default(string, context.getString(R.string.talkback_artist, new Object[]{album.getAlbumArtist()}), false, 2, (Object) null), context.getResources().getQuantityString(R.plurals.track_quantity, album.getTracksCount(), new Object[]{Integer.valueOf(album.getTracksCount())}), false, 2, (Object) null);
    }

    public final String getPlaylist(Context context, Playlist playlist) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlist, ArtworkProvider.PLAYLIST);
        String string = context.getString(R.string.talkback_playlist, new Object[]{playlist.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(string, context.getResources().getQuantityString(R.plurals.track_quantity, playlist.getTracksCount(), new Object[]{Integer.valueOf(playlist.getTracksCount())}), false, 2, (Object) null);
    }

    public final String getArtist(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (str == null) {
            return "";
        }
        String string = context.getString(R.string.talkback_artist, new Object[]{str});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getTrackNumber(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        String string = context.getString(R.string.talkback_track_number, new Object[]{String.valueOf(mediaWrapper.getTrackNumber())});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getTimeAndArtist(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        return TalkbackUtilKt.talkbackAppend$default(millisToString(context, mediaWrapper.getLength()), getArtist(context, mediaWrapper.getArtist()), false, 2, (Object) null);
    }

    public final String getFolder(Context context, Folder folder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(folder, "folder");
        int mediaCount = folder.mediaCount(Folder.TYPE_FOLDER_VIDEO);
        String string = context.getString(R.string.talkback_folder, new Object[]{folder.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return TalkbackUtilKt.talkbackAppend$default(string, context.getResources().getQuantityString(R.plurals.videos_quantity, mediaCount, new Object[]{Integer.valueOf(mediaCount)}), false, 2, (Object) null);
    }

    public final String getDir(Context context, MediaLibraryItem mediaLibraryItem, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "folder");
        if (!(mediaLibraryItem instanceof MediaWrapper)) {
            String string = context.getString(R.string.talkback_folder, new Object[]{mediaLibraryItem.getTitle()});
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        if (mediaWrapper.getType() == 3) {
            String description = mediaWrapper.getDescription();
            int folderNumber = description != null ? KextensionsKt.getFolderNumber(description) : 0;
            String description2 = mediaWrapper.getDescription();
            int filesNumber = description2 != null ? KextensionsKt.getFilesNumber(description2) : 0;
            String string2 = context.getString(z ? R.string.talkback_favorite : R.string.talkback_folder, new Object[]{mediaWrapper.getTitle()});
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            if (folderNumber > 0) {
                string2 = TalkbackUtilKt.talkbackAppend$default(string2, context.getResources().getQuantityString(R.plurals.subfolders_quantity, folderNumber, new Object[]{Integer.valueOf(folderNumber)}), false, 2, (Object) null);
            }
            if (filesNumber > 0) {
                string2 = TalkbackUtilKt.talkbackAppend$default(string2, context.getResources().getQuantityString(R.plurals.mediafiles_quantity, filesNumber, new Object[]{Integer.valueOf(filesNumber)}), false, 2, (Object) null);
            }
            if (filesNumber >= 1 || folderNumber >= 1) {
                return string2;
            }
            return TalkbackUtilKt.talkbackAppend$default(string2, context.getString(R.string.empty_directory), false, 2, (Object) null);
        }
        String string3 = context.getString(R.string.talkback_file, new Object[]{mediaWrapper.getTitle()});
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CharSequence description3 = mediaWrapper.getDescription();
        if (description3 == null || description3.length() == 0) {
            return string3;
        }
        return TalkbackUtilKt.talkbackAppend$default(string3, context.getString(R.string.talkback_file_size, new Object[]{mediaWrapper.getDescription()}), false, 2, (Object) null);
    }

    public final String getAll(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "media");
        String title = mediaLibraryItem.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        return title;
    }

    public final String millisToString(Context context, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        if (j < 0) {
            j = -j;
            sb.append("-");
        }
        long j2 = j / ((long) 1000);
        long j3 = (long) 60;
        int i = (int) (j2 % j3);
        long j4 = j2 / j3;
        int i2 = (int) (j4 % j3);
        int i3 = (int) (j4 / j3);
        if (i3 > 0) {
            sb.append(i3);
            sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
            sb.append(context.getString(R.string.talkback_hours));
            sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
        }
        if (i2 > 0) {
            sb.append(i2);
            sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
            sb.append(context.getString(R.string.talkback_minutes));
            sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
        }
        if (i > 0) {
            sb.append(i);
            sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
            sb.append(context.getString(R.string.talkback_seconds));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
