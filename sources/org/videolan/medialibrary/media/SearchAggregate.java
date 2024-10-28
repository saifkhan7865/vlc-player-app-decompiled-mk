package org.videolan.medialibrary.media;

import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;

public class SearchAggregate {
    private static final String TAG = "VLC/SearchAggregate";
    private final Album[] albums;
    private final Artist[] artists;
    private final Genre[] genres;
    private final Playlist[] playlists;
    private final MediaWrapper[] tracks;
    private final MediaWrapper[] videos;

    public SearchAggregate() {
        this.albums = null;
        this.artists = null;
        this.genres = null;
        this.videos = null;
        this.tracks = null;
        this.playlists = null;
    }

    public SearchAggregate(Album[] albumArr, Artist[] artistArr, Genre[] genreArr, MediaWrapper[] mediaWrapperArr, MediaWrapper[] mediaWrapperArr2, Playlist[] playlistArr) {
        this.albums = albumArr;
        this.artists = artistArr;
        this.genres = genreArr;
        this.videos = mediaWrapperArr;
        this.tracks = mediaWrapperArr2;
        this.playlists = playlistArr;
    }

    public Album[] getAlbums() {
        return this.albums;
    }

    public Artist[] getArtists() {
        return this.artists;
    }

    public Genre[] getGenres() {
        return this.genres;
    }

    public MediaWrapper[] getVideos() {
        return this.videos;
    }

    public MediaWrapper[] getTracks() {
        return this.tracks;
    }

    public Playlist[] getPlaylists() {
        return this.playlists;
    }

    public boolean isEmpty() {
        return Tools.isArrayEmpty(this.videos) && Tools.isArrayEmpty(this.tracks) && Tools.isArrayEmpty(this.albums) && Tools.isArrayEmpty(this.artists) && Tools.isArrayEmpty(this.genres) && Tools.isArrayEmpty(this.playlists);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!Tools.isArrayEmpty(this.albums)) {
            sb.append("Albums:\n");
            for (Album title : this.albums) {
                sb.append(title.getTitle());
                sb.append("\n");
            }
        }
        if (!Tools.isArrayEmpty(this.artists)) {
            sb.append("Artists:\n");
            for (Artist title2 : this.artists) {
                sb.append(title2.getTitle());
                sb.append("\n");
            }
        }
        if (!Tools.isArrayEmpty(this.genres)) {
            sb.append("Genres:\n");
            for (Genre title3 : this.genres) {
                sb.append(title3.getTitle());
                sb.append("\n");
            }
        }
        if (!Tools.isArrayEmpty(this.tracks)) {
            sb.append("Tracks:\n");
            for (MediaWrapper title4 : this.tracks) {
                sb.append(title4.getTitle());
                sb.append("\n");
            }
        }
        if (!Tools.isArrayEmpty(this.videos)) {
            sb.append("Videos:\n");
            for (MediaWrapper title5 : this.videos) {
                sb.append(title5.getTitle());
                sb.append("\n");
            }
        }
        if (!Tools.isArrayEmpty(this.playlists)) {
            sb.append("Playlists:\n");
            for (Playlist title6 : this.playlists) {
                sb.append(title6.getTitle());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
