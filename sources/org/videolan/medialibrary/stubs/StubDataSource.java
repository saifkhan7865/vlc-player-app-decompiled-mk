package org.videolan.medialibrary.stubs;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;

public class StubDataSource {
    public static final String STUBBED_AUDIO_EXTENSION = ".mp3";
    public static final String STUBBED_AUDIO_TITLE = "Show Me The Way";
    public static final String STUBBED_VIDEO_EXTENSION = ".mp4";
    public static final String STUBBED_VIDEO_TITLE = "Invincible";
    private static String baseMrl = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/");
    private static StubDataSource mInstance = null;
    public static AtomicLong uuid = new AtomicLong(2);
    private String TAG = getClass().getName();
    ArrayList<Album> mAlbums = new ArrayList<>();
    ArrayList<Artist> mArtists = new ArrayList<>();
    ArrayList<MediaWrapper> mAudioMediaWrappers = new ArrayList<>();
    ArrayList<String> mBannedFolders = new ArrayList<>();
    ArrayList<String> mDevices = new ArrayList<>();
    ArrayList<Folder> mFolders = new ArrayList<>();
    ArrayList<Genre> mGenres = new ArrayList<>();
    ArrayList<MediaWrapper> mHistory = new ArrayList<>();
    ArrayList<Playlist> mPlaylists = new ArrayList<>();
    ArrayList<MediaWrapper> mStreamMediaWrappers = new ArrayList<>();
    ArrayList<MediaWrapper> mVideoMediaWrappers = new ArrayList<>();

    public static StubDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new StubDataSource();
        }
        return mInstance;
    }

    private StubDataSource() {
    }

    public void resetData() {
        this.mFolders.clear();
        this.mVideoMediaWrappers.clear();
        this.mAudioMediaWrappers.clear();
        this.mStreamMediaWrappers.clear();
        this.mHistory.clear();
        this.mPlaylists.clear();
        this.mAlbums.clear();
        this.mArtists.clear();
        this.mGenres.clear();
        this.mBannedFolders.clear();
        this.mDevices.clear();
    }

    public void setVideoByCount(int i, String str) {
        String str2;
        String str3 = str;
        int i2 = i;
        for (int i3 = 0; i3 < i2; i3++) {
            String str4 = i3 + " - Invincible.mp3";
            StringBuilder sb = new StringBuilder();
            sb.append(baseMrl);
            if (str3 != null) {
                str2 = str3 + "/";
            } else {
                str2 = "";
            }
            sb.append(str2);
            sb.append(str4);
            addVideo(MLServiceLocator.getAbstractMediaWrapper(getUUID(), sb.toString(), -1, -1.0f, 18820, 0, str4, str4, "", "", "", "", 416, 304, "", 0, -2, 0, 0, 1509466228, 0, true, false, 1970, true, 1683711438317L));
        }
    }

    public void setAudioByCount(int i, String str) {
        String str2;
        String str3 = str;
        this.mAudioMediaWrappers.clear();
        int i2 = i;
        for (int i3 = 0; i3 < i2; i3++) {
            String str4 = i3 + " - Show Me The Way.mp3";
            StringBuilder sb = new StringBuilder();
            sb.append(baseMrl);
            if (str3 != null) {
                str2 = str3 + "/";
            } else {
                str2 = "";
            }
            sb.append(str2);
            sb.append(str4);
            String sb2 = sb.toString();
            addAudio(MLServiceLocator.getAbstractMediaWrapper(getUUID(), sb2, -1, -1.0f, 280244, 1, i3 + "-Show Me The Way", str4, "Peter Frampton", "Rock", "Shine On CD2", "Peter Frampton", 0, 0, baseMrl + str3 + ".jpg", 0, -2, 1, 0, 1547452796, 0, true, false, 1965, true, 1683711438317L), "", 1965, 400, sb2);
        }
    }

    public Folder createFolder(String str) {
        long uuid2 = getUUID();
        Folder abstractFolder = MLServiceLocator.getAbstractFolder(uuid2, str, baseMrl + str, 1, false);
        this.mFolders.add(abstractFolder);
        return abstractFolder;
    }

    public void init() {
        addArtistSecure(MLServiceLocator.getAbstractArtist(1, "", "", "", "", 0, 0, 0, false));
        addArtistSecure(MLServiceLocator.getAbstractArtist(2, "", "", "", "", 0, 0, 0, false));
    }

    /* access modifiers changed from: package-private */
    public <T> List<T> secureSublist(List<T> list, int i, int i2) {
        int size = list.size() + -1 < 0 ? 0 : list.size();
        int i3 = (i < list.size() || i <= 0) ? i : size;
        int i4 = i + i2;
        if (i4 < list.size() || i4 <= 0) {
            size = i4;
        }
        return list.subList(i3, size);
    }

    /* access modifiers changed from: package-private */
    public int compareArtistStr(String str, String str2) {
        if ((str.equals(Artist.SpecialRes.UNKNOWN_ARTIST) || str.equals(Artist.SpecialRes.VARIOUS_ARTISTS)) && (str2.equals(Artist.SpecialRes.UNKNOWN_ARTIST) || str2.equals(Artist.SpecialRes.VARIOUS_ARTISTS))) {
            return 0;
        }
        if (str.equals(Artist.SpecialRes.UNKNOWN_ARTIST) || str.equals(Artist.SpecialRes.VARIOUS_ARTISTS)) {
            return -1;
        }
        if (str2.equals(Artist.SpecialRes.UNKNOWN_ARTIST) || str2.equals(Artist.SpecialRes.VARIOUS_ARTISTS)) {
            return 1;
        }
        return str.compareTo(str2);
    }

    /* access modifiers changed from: package-private */
    public int compareArtist(Artist artist, Artist artist2) {
        return compareArtistStr(artist.getTitle(), artist2.getTitle());
    }

    /* access modifiers changed from: package-private */
    public int compareAlbumStr(String str, String str2) {
        if (str.equals(Album.SpecialRes.UNKNOWN_ALBUM) && str2.equals(Album.SpecialRes.UNKNOWN_ALBUM)) {
            return 0;
        }
        if (str.equals(Album.SpecialRes.UNKNOWN_ALBUM)) {
            return -1;
        }
        if (str2.equals(Album.SpecialRes.UNKNOWN_ALBUM)) {
            return 1;
        }
        return str.compareTo(str2);
    }

    /* access modifiers changed from: package-private */
    public int compareAlbum(Album album, Album album2) {
        if (album.getTitle().equals(album2.getTitle())) {
            return compareArtist(album.retrieveAlbumArtist(), album2.retrieveAlbumArtist());
        }
        if (album.getTitle().equals(Album.SpecialRes.UNKNOWN_ALBUM)) {
            return -1;
        }
        if (album2.getTitle().equals(Album.SpecialRes.UNKNOWN_ALBUM)) {
            return 1;
        }
        return album.getTitle().compareTo(album2.getTitle());
    }

    class MediaComparator implements Comparator<MediaWrapper> {
        private int sort;

        MediaComparator(int i) {
            this.sort = i;
        }

        public int compare(MediaWrapper mediaWrapper, MediaWrapper mediaWrapper2) {
            int i = this.sort;
            if (i == 0 || i == 1) {
                return mediaWrapper.getTitle().compareTo(mediaWrapper2.getTitle());
            }
            if (i == 2) {
                return Long.valueOf(mediaWrapper.getLength()).compareTo(Long.valueOf(mediaWrapper2.getLength()));
            }
            if (i == 3) {
                return Long.valueOf(mediaWrapper.getTime()).compareTo(Long.valueOf(mediaWrapper2.getTime()));
            }
            if (i == 4) {
                return Long.valueOf(mediaWrapper.getLastModified()).compareTo(Long.valueOf(mediaWrapper2.getLastModified()));
            }
            if (i == 7) {
                return StubDataSource.this.compareArtistStr(mediaWrapper.getArtist(), mediaWrapper2.getArtist());
            }
            if (i == 9) {
                return StubDataSource.this.compareAlbumStr(mediaWrapper.getAlbum(), mediaWrapper2.getAlbum());
            }
            if (i != 10) {
                return 0;
            }
            return mediaWrapper.getFileName().compareTo(mediaWrapper2.getFileName());
        }
    }

    class ArtistComparator implements Comparator<Artist> {
        private int sort;

        ArtistComparator(int i) {
            this.sort = i;
        }

        public int compare(Artist artist, Artist artist2) {
            int i = this.sort;
            if (i == 0 || i == 7) {
                return StubDataSource.this.compareArtist(artist, artist2);
            }
            return 0;
        }
    }

    class AlbumComparator implements Comparator<Album> {
        private int sort;

        AlbumComparator(int i) {
            this.sort = i;
        }

        public int compare(Album album, Album album2) {
            int i = this.sort;
            if (!(i == 0 || i == 1)) {
                if (i == 2) {
                    return Long.valueOf(album.getDuration()).compareTo(Long.valueOf(album2.getDuration()));
                }
                if (i == 5) {
                    return Integer.valueOf(album.getReleaseYear()).compareTo(Integer.valueOf(album2.getReleaseYear()));
                }
                if (i != 9) {
                    return 0;
                }
            }
            return StubDataSource.this.compareAlbum(album, album2);
        }
    }

    class GenreComparator implements Comparator<Genre> {
        private int sort;

        GenreComparator(int i) {
            this.sort = i;
        }

        public int compare(Genre genre, Genre genre2) {
            int i = this.sort;
            if (i == 0 || i == 1) {
                return genre.getTitle().compareTo(genre2.getTitle());
            }
            return 0;
        }
    }

    class PlaylistComparator implements Comparator<Playlist> {
        private int sort;

        PlaylistComparator(int i) {
            this.sort = i;
        }

        public int compare(Playlist playlist, Playlist playlist2) {
            int i = this.sort;
            if (i == 0 || i == 1) {
                return playlist.getTitle().compareTo(playlist2.getTitle());
            }
            return 0;
        }
    }

    class FolderComparator implements Comparator<Folder> {
        private int sort;

        FolderComparator(int i) {
            this.sort = i;
        }

        public int compare(Folder folder, Folder folder2) {
            int i = this.sort;
            if (i == 0 || i == 1) {
                return folder.getTitle().compareTo(folder2.getTitle());
            }
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public MediaWrapper[] sortMedia(List<MediaWrapper> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new MediaComparator(i));
        if (z) {
            Collections.reverse(arrayList);
        }
        return (MediaWrapper[]) arrayList.toArray(new MediaWrapper[0]);
    }

    /* access modifiers changed from: package-private */
    public Album[] sortAlbum(List<Album> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new AlbumComparator(i));
        if (z) {
            Collections.reverse(arrayList);
        }
        return (Album[]) arrayList.toArray(new Album[0]);
    }

    /* access modifiers changed from: package-private */
    public Artist[] sortArtist(List<Artist> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new ArtistComparator(i));
        if (z) {
            Collections.reverse(arrayList);
        }
        return (Artist[]) arrayList.toArray(new Artist[0]);
    }

    /* access modifiers changed from: package-private */
    public Genre[] sortGenre(List<Genre> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new GenreComparator(i));
        if (z) {
            Collections.reverse(arrayList);
        }
        return (Genre[]) arrayList.toArray(new Genre[0]);
    }

    /* access modifiers changed from: package-private */
    public Playlist[] sortPlaylist(List<Playlist> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new PlaylistComparator(i));
        if (z) {
            Collections.reverse(arrayList);
        }
        return (Playlist[]) arrayList.toArray(new Playlist[0]);
    }

    /* access modifiers changed from: package-private */
    public Folder[] sortFolder(List<Folder> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new FolderComparator(i));
        if (z) {
            Collections.reverse(arrayList);
        }
        return (Folder[]) arrayList.toArray(new Folder[0]);
    }

    public long getUUID() {
        return uuid.incrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public void loadJsonData(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                String str2 = this.TAG;
                Log.d(str2, "discover: " + jSONArray.getJSONObject(i).getString("title"));
                addMediaFromJson(jSONArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            String str3 = this.TAG;
            Log.e(str3, "discover: " + e.toString());
        }
    }

    private void addMediaFromJson(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        try {
            int i = jSONObject2.getInt("type");
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(getUUID(), jSONObject2.getString("mrl"), -1, -1.0f, jSONObject2.getLong("length"), i, jSONObject2.getString("title"), jSONObject2.getString(ContentDisposition.Parameters.FileName), jSONObject2.getString(ArtworkProvider.ARTIST), jSONObject2.getString("genre"), jSONObject2.getString(ArtworkProvider.ALBUM), jSONObject2.getString("album_artist"), jSONObject2.getInt("width"), jSONObject2.getInt("height"), jSONObject2.getString("artwork_url"), jSONObject2.getInt(Constants.ID_AUDIO), jSONObject2.getInt("spu"), jSONObject2.getInt("track_number"), 0, jSONObject2.getLong("last_modified"), 0, true, false, jSONObject2.getInt(TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE), true, 1683711438317L);
            if (i == 0) {
                addVideo(abstractMediaWrapper);
                return;
            }
            addAudio(abstractMediaWrapper, "", jSONObject2.getInt("year"), jSONObject2.getInt("track_total"), jSONObject2.getString("mrl"));
        } catch (JSONException e) {
            String str = this.TAG;
            Log.e(str, "addMediaFromJson: failed to load json: " + e.toString());
        }
    }

    private void addArtistSecure(Artist artist) {
        if (!artist.getTitle().isEmpty()) {
            Iterator<Artist> it = this.mArtists.iterator();
            while (it.hasNext()) {
                if (it.next().getTitle().equals(artist.getTitle())) {
                    return;
                }
            }
            this.mArtists.add(artist);
        }
    }

    private void addGenreSecure(Genre genre) {
        if (!genre.getTitle().isEmpty()) {
            Iterator<Genre> it = this.mGenres.iterator();
            while (it.hasNext()) {
                if (it.next().getTitle().equals(genre.getTitle())) {
                    return;
                }
            }
            this.mGenres.add(genre);
        }
    }

    private void addAlbumSecure(Album album) {
        if (!album.getTitle().isEmpty()) {
            Iterator<Album> it = this.mAlbums.iterator();
            while (it.hasNext()) {
                Album next = it.next();
                if (next.getTitle().equals(album.getTitle()) && next.retrieveAlbumArtist().getTitle().equals(album.retrieveAlbumArtist().getTitle())) {
                    return;
                }
            }
            this.mAlbums.add(album);
        }
    }

    private Artist getArtistFromName(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Iterator<Artist> it = this.mArtists.iterator();
        while (it.hasNext()) {
            Artist next = it.next();
            if (next.getTitle().equals(str)) {
                return next;
            }
        }
        return null;
    }

    private Album getAlbumFromName(String str, long j) {
        if (str.equals("")) {
            str = Album.SpecialRes.UNKNOWN_ALBUM;
        }
        Iterator<Album> it = this.mAlbums.iterator();
        while (it.hasNext()) {
            Album next = it.next();
            if (next.getTitle().equals(str) && next.retrieveAlbumArtist().getId() == j) {
                return next;
            }
        }
        return null;
    }

    private void raiseAlbumDuration(Album album, long j) {
        int i = 0;
        while (i < this.mAlbums.size()) {
            Album album2 = this.mAlbums.get(i);
            Artist retrieveAlbumArtist = album2.retrieveAlbumArtist();
            if (!album2.getTitle().equals(album.getTitle()) || !album2.retrieveAlbumArtist().getTitle().equals(retrieveAlbumArtist.getTitle())) {
                i++;
            } else {
                this.mAlbums.set(i, MLServiceLocator.getAbstractAlbum(album.getId(), album.getTitle(), album.getReleaseYear(), album.getArtworkMrl(), retrieveAlbumArtist.getTitle(), retrieveAlbumArtist.getId(), album.getTracksCount(), album.getPresentTracksCount(), album.getDuration() + j, album.isFavorite()));
                return;
            }
        }
    }

    private String getArtistName(String str, String str2) {
        if (str == null || str2 == null || (str.isEmpty() && str2.isEmpty())) {
            return Artist.SpecialRes.UNKNOWN_ARTIST;
        }
        return !str.isEmpty() ? str : str2;
    }

    private String getAlbumName(String str) {
        if (str == null || str.isEmpty()) {
            return Album.SpecialRes.UNKNOWN_ALBUM;
        }
        return str;
    }

    private void addAudio(MediaWrapper mediaWrapper, String str, int i, int i2, String str2) {
        addFolders(mediaWrapper);
        String artistName = getArtistName(mediaWrapper.getAlbumArtist(), mediaWrapper.getArtist());
        Artist artistFromName = getArtistFromName(artistName);
        if (artistFromName == null) {
            artistFromName = MLServiceLocator.getAbstractArtist(getUUID(), artistName, "", mediaWrapper.getArtworkMrl(), "", 0, i2, i2, false);
            addArtistSecure(artistFromName);
        }
        if (mediaWrapper.getArtist().isEmpty()) {
            mediaWrapper.setArtist(artistName);
        } else {
            MediaWrapper mediaWrapper2 = mediaWrapper;
            if (!mediaWrapper.getArtist().equals(artistName) && getArtistFromName(mediaWrapper.getArtist()) == null) {
                addArtistSecure(MLServiceLocator.getAbstractArtist(getUUID(), mediaWrapper.getArtist(), "", mediaWrapper.getArtworkMrl(), "", 1, i2, i2, false));
            }
        }
        String albumName = getAlbumName(mediaWrapper.getAlbum());
        Album albumFromName = getAlbumFromName(albumName, artistFromName.getId());
        if (albumFromName == null) {
            albumFromName = MLServiceLocator.getAbstractAlbum(getUUID(), albumName, i, mediaWrapper.getArtworkMrl(), artistFromName.getTitle(), artistFromName.getId(), i2, i2, 0, false);
            addAlbumSecure(albumFromName);
        }
        raiseAlbumDuration(albumFromName, (long) ((int) mediaWrapper.getLength()));
        Genre abstractGenre = MLServiceLocator.getAbstractGenre(getUUID(), mediaWrapper.getGenre(), false);
        addGenreSecure(abstractGenre);
        this.mAudioMediaWrappers.add(MLServiceLocator.getAbstractMediaWrapper(mediaWrapper.getId(), str2, -1, -1.0f, mediaWrapper.getLength(), 1, mediaWrapper.getTitle(), mediaWrapper.getFileName(), mediaWrapper.getArtist(), abstractGenre.getTitle(), albumFromName.getTitle(), artistFromName.getTitle(), mediaWrapper.getWidth(), mediaWrapper.getHeight(), mediaWrapper.getArtworkURL(), mediaWrapper.getAudioTrack(), mediaWrapper.getSpuTrack(), mediaWrapper.getTrackNumber(), 0, mediaWrapper.getLastModified(), 0, true, false, i, true, 1683711438317L));
    }

    private void addVideo(MediaWrapper mediaWrapper) {
        addFolders(mediaWrapper);
        this.mVideoMediaWrappers.add(mediaWrapper);
    }

    public MediaWrapper addMediaWrapper(String str, String str2, int i) {
        long uuid2 = getUUID();
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(uuid2, str, -1, -1.0f, 280224, i, str2, str2, "Artisto", "Jazz", "XYZ CD1", "", 0, 0, baseMrl + str2, -2, 1, 1, 0, 1547452796, 0, true, false, 0, true, 1683711438317L);
        int i2 = i;
        int type = i2 == -1 ? abstractMediaWrapper.getType() : i2;
        if (type == 0) {
            addVideo(abstractMediaWrapper);
        } else {
            if (type == 1) {
                addAudio(abstractMediaWrapper, "", 2018, 12313, str);
            }
        }
        return abstractMediaWrapper;
    }

    public MediaWrapper addMediaWrapper(String str, int i) {
        return addMediaWrapper(baseMrl + str, str, i);
    }

    private String[] getFoldersString() {
        ArrayList arrayList = new ArrayList();
        Iterator<Folder> it = this.mFolders.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getTitle());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private void addFolders(MediaWrapper mediaWrapper) {
        String path = mediaWrapper.getUri().getPath();
        if (path != null) {
            String[] split = path.split("/");
            ArrayList arrayList = new ArrayList(Arrays.asList(split));
            for (int i = 0; i < arrayList.size(); i++) {
                String join = TextUtils.join("/", secureSublist(arrayList, 0, i));
                if (!new ArrayList(Arrays.asList(getFoldersString())).contains(join)) {
                    this.mFolders.add(MLServiceLocator.getAbstractFolder(getUUID(), split[split.length - 1], join, 1, false));
                }
            }
        }
    }
}
