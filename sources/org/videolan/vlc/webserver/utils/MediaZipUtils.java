package org.videolan.vlc.webserver.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006J\u0016\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0006J\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0006J\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0006J\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u0006J\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0006J$\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J&\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002¨\u0006 "}, d2 = {"Lorg/videolan/vlc/webserver/utils/MediaZipUtils;", "", "()V", "generateAlbumFiles", "", "Lkotlin/Pair;", "", "album", "Lorg/videolan/medialibrary/interfaces/media/Album;", "generateAlbumZip", "folder", "generateArtistZip", "artist", "Lorg/videolan/medialibrary/interfaces/media/Artist;", "generateGenreZip", "genre", "Lorg/videolan/medialibrary/interfaces/media/Genre;", "generatePlaylistZip", "playlist", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "generateVideoGroupZip", "videoFolder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "videoGroup", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "getFileNamePair", "track", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "number", "prepareTrackForZip", "index", "", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaZipUtils.kt */
public final class MediaZipUtils {
    public static final MediaZipUtils INSTANCE = new MediaZipUtils();

    private MediaZipUtils() {
    }

    public final String generateGenreZip(Genre genre, String str) {
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(str, "folder");
        MediaWrapper[] tracks = genre.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        Collection arrayList = new ArrayList();
        for (Object obj : (Object[]) tracks) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            MediaZipUtils mediaZipUtils = INSTANCE;
            Intrinsics.checkNotNull(mediaWrapper);
            Pair<String, String> prepareTrackForZip = mediaZipUtils.prepareTrackForZip(mediaWrapper, -1);
            if (prepareTrackForZip != null) {
                arrayList.add(prepareTrackForZip);
            }
        }
        StringBuilder sb = new StringBuilder();
        String title = genre.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        sb.append(KextensionsKt.slugify(title, "_"));
        sb.append(".zip");
        String sb2 = sb.toString();
        String path = new File(str + '/' + sb2).getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        FileUtils.INSTANCE.zipWithName((Pair[]) ((List) arrayList).toArray(new Pair[0]), path);
        return sb2;
    }

    public final String generatePlaylistZip(Playlist playlist, String str) {
        Intrinsics.checkNotNullParameter(playlist, ArtworkProvider.PLAYLIST);
        Intrinsics.checkNotNullParameter(str, "folder");
        MediaWrapper[] tracks = playlist.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        Collection arrayList = new ArrayList();
        for (Object obj : (Object[]) tracks) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            MediaZipUtils mediaZipUtils = INSTANCE;
            Intrinsics.checkNotNull(mediaWrapper);
            Pair<String, String> prepareTrackForZip = mediaZipUtils.prepareTrackForZip(mediaWrapper, -1);
            if (prepareTrackForZip != null) {
                arrayList.add(prepareTrackForZip);
            }
        }
        StringBuilder sb = new StringBuilder();
        String title = playlist.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        sb.append(KextensionsKt.slugify(title, "_"));
        sb.append(".zip");
        String sb2 = sb.toString();
        String path = new File(str + '/' + sb2).getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        FileUtils.INSTANCE.zipWithName((Pair[]) ((List) arrayList).toArray(new Pair[0]), path);
        return sb2;
    }

    public final String generateVideoGroupZip(VideoGroup videoGroup, String str) {
        Intrinsics.checkNotNullParameter(videoGroup, "videoGroup");
        Intrinsics.checkNotNullParameter(str, "folder");
        MediaWrapper[] media = videoGroup.media(0, false, false, false, videoGroup.mediaCount(), 0);
        Intrinsics.checkNotNullExpressionValue(media, "media(...)");
        Collection arrayList = new ArrayList();
        for (Object obj : (Object[]) media) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            MediaZipUtils mediaZipUtils = INSTANCE;
            Intrinsics.checkNotNull(mediaWrapper);
            Pair<String, String> prepareTrackForZip = mediaZipUtils.prepareTrackForZip(mediaWrapper, -1);
            if (prepareTrackForZip != null) {
                arrayList.add(prepareTrackForZip);
            }
        }
        StringBuilder sb = new StringBuilder();
        String title = videoGroup.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        sb.append(KextensionsKt.slugify(title, "_"));
        sb.append(".zip");
        String sb2 = sb.toString();
        String path = new File(str + '/' + sb2).getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        FileUtils.INSTANCE.zipWithName((Pair[]) ((List) arrayList).toArray(new Pair[0]), path);
        return sb2;
    }

    public final String generateVideoGroupZip(Folder folder, String str) {
        Intrinsics.checkNotNullParameter(folder, "videoFolder");
        Intrinsics.checkNotNullParameter(str, "folder");
        MediaWrapper[] media = folder.media(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, folder.mediaCount(Folder.TYPE_FOLDER_VIDEO), 0);
        Intrinsics.checkNotNullExpressionValue(media, "media(...)");
        Collection arrayList = new ArrayList();
        for (Object obj : (Object[]) media) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            MediaZipUtils mediaZipUtils = INSTANCE;
            Intrinsics.checkNotNull(mediaWrapper);
            Pair<String, String> prepareTrackForZip = mediaZipUtils.prepareTrackForZip(mediaWrapper, -1);
            if (prepareTrackForZip != null) {
                arrayList.add(prepareTrackForZip);
            }
        }
        StringBuilder sb = new StringBuilder();
        String title = folder.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        sb.append(KextensionsKt.slugify(title, "_"));
        sb.append(".zip");
        String sb2 = sb.toString();
        String path = new File(str + '/' + sb2).getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        FileUtils.INSTANCE.zipWithName((Pair[]) ((List) arrayList).toArray(new Pair[0]), path);
        return sb2;
    }

    public final String generateArtistZip(Artist artist, String str) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(artist, ArtworkProvider.ARTIST);
        Intrinsics.checkNotNullParameter(str2, "folder");
        ArrayList arrayList = new ArrayList();
        Album[] albums = artist.getAlbums();
        Intrinsics.checkNotNullExpressionValue(albums, "getAlbums(...)");
        for (Object obj : (Object[]) albums) {
            Album album = (Album) obj;
            String title = album.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            String slugify = KextensionsKt.slugify(title, "_");
            MediaZipUtils mediaZipUtils = INSTANCE;
            Intrinsics.checkNotNull(album);
            for (Pair pair : mediaZipUtils.generateAlbumFiles(album)) {
                String valueOf = album.getReleaseYear() <= 0 ? "" : String.valueOf(album.getReleaseYear());
                arrayList.add(new Pair(pair.getFirst(), valueOf + '_' + slugify + '/' + ((String) pair.getSecond())));
            }
        }
        StringBuilder sb = new StringBuilder();
        String title2 = artist.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
        sb.append(KextensionsKt.slugify(title2, "_"));
        sb.append(".zip");
        String sb2 = sb.toString();
        String path = new File(str2 + '/' + sb2).getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        FileUtils.INSTANCE.zipWithName((Pair[]) arrayList.toArray(new Pair[0]), path);
        return sb2;
    }

    public final String generateAlbumZip(Album album, String str) {
        Intrinsics.checkNotNullParameter(album, ArtworkProvider.ALBUM);
        Intrinsics.checkNotNullParameter(str, "folder");
        StringBuilder sb = new StringBuilder();
        String title = album.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        sb.append(KextensionsKt.slugify(title, "_"));
        sb.append(".zip");
        String sb2 = sb.toString();
        File file = new File(str + '/' + sb2);
        List<Pair<String, String>> generateAlbumFiles = generateAlbumFiles(album);
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        FileUtils.INSTANCE.zipWithName((Pair[]) generateAlbumFiles.toArray(new Pair[0]), path);
        return sb2;
    }

    private final List<Pair<String, String>> generateAlbumFiles(Album album) {
        MediaWrapper[] tracks = album.getTracks();
        Intrinsics.checkNotNull(tracks);
        Collection arrayList = new ArrayList();
        for (MediaWrapper mediaWrapper : tracks) {
            MediaZipUtils mediaZipUtils = INSTANCE;
            Intrinsics.checkNotNull(mediaWrapper);
            Pair<String, String> prepareTrackForZip = mediaZipUtils.prepareTrackForZip(mediaWrapper, (tracks != null ? ArraysKt.indexOf((T[]) tracks, mediaWrapper) : -2) + 1);
            if (prepareTrackForZip != null) {
                arrayList.add(prepareTrackForZip);
            }
        }
        List<Pair<String, String>> mutableList = CollectionsKt.toMutableList((List) arrayList);
        String artworkMrl = album.getArtworkMrl();
        if (artworkMrl != null) {
            Intrinsics.checkNotNull(artworkMrl);
            mutableList.add(new Pair(artworkMrl, "cover.jpg"));
        }
        return mutableList;
    }

    private final Pair<String, String> prepareTrackForZip(MediaWrapper mediaWrapper, int i) {
        String str;
        if (!Intrinsics.areEqual((Object) mediaWrapper.getUri().getScheme(), (Object) "file") || mediaWrapper.getUri().getPath() == null) {
            return null;
        }
        MediaZipUtils mediaZipUtils = INSTANCE;
        if (i < 1) {
            str = "";
        } else {
            StringBuilder sb = i < 10 ? new StringBuilder(Constants.GROUP_VIDEOS_FOLDER) : new StringBuilder();
            sb.append(i);
            sb.append(' ');
            str = sb.toString();
        }
        return mediaZipUtils.getFileNamePair(mediaWrapper, str);
    }

    private final Pair<String, String> getFileNamePair(MediaWrapper mediaWrapper, String str) {
        String path = mediaWrapper.getUri().getPath();
        Intrinsics.checkNotNull(path);
        StringBuilder sb = new StringBuilder();
        sb.append(KextensionsKt.slugify(str + mediaWrapper.getTitle(), "_"));
        String uri = mediaWrapper.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        String uri2 = mediaWrapper.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        String substring = uri.substring(StringsKt.lastIndexOf$default((CharSequence) uri2, ".", 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        sb.append(substring);
        return new Pair<>(path, sb.toString());
    }
}
