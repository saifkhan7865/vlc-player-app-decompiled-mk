package org.videolan.medialibrary.stubs;

import android.content.Context;
import android.net.Uri;
import android.webkit.URLUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.videolan.medialibrary.MLContextTools;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.MlService;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.SearchAggregate;

public class StubMedialibrary extends Medialibrary {
    private StubDataSource dt = StubDataSource.getInstance();

    public void addDevice(String str, String str2, boolean z) {
    }

    public MediaWrapper addMedia(String str, long j) {
        return null;
    }

    public void cacheNewSubscriptionMedia() {
    }

    public void clearDatabase(boolean z) {
    }

    public boolean construct(Context context) {
        return true;
    }

    public VideoGroup createVideoGroup(String str) {
        return null;
    }

    public VideoGroup createVideoGroup(long[] jArr) {
        return null;
    }

    public boolean deleteRemovableDevices() {
        return false;
    }

    public boolean fitsInSubscriptionCache(MediaWrapper mediaWrapper) {
        return false;
    }

    public boolean flushUserProvidedThumbnails() {
        return true;
    }

    public void forceParserRetry() {
    }

    public void forceRescan() {
    }

    public Folder getFolder(int i, long j) {
        return null;
    }

    public int getFoldersCount(String str) {
        return 0;
    }

    public long getMaxCacheSize() {
        return -1;
    }

    public MediaWrapper getMedia(String str) {
        return null;
    }

    public MlService getService(MlService.Type type) {
        return null;
    }

    public long getSubscriptionMaxCacheSize() {
        return -1;
    }

    public int getSubscriptionMaxCachedMedia() {
        return -1;
    }

    public VideoGroup getVideoGroup(long j) {
        return null;
    }

    public int getVideoGroupsCount(String str) {
        return 0;
    }

    public boolean isDeviceKnown(String str, String str2, boolean z) {
        return false;
    }

    public boolean isStarted() {
        return true;
    }

    public void pauseBackgroundOperations() {
    }

    public boolean refreshAllSubscriptions() {
        return false;
    }

    public boolean regroup(long j) {
        return false;
    }

    public boolean regroupAll() {
        return false;
    }

    public boolean removeDevice(String str, String str2) {
        return true;
    }

    public boolean removeExternalMedia(long j) {
        return true;
    }

    public void removeFolder(String str) {
    }

    public void requestThumbnail(long j) {
    }

    public void resumeBackgroundOperations() {
    }

    public boolean setDiscoverNetworkEnabled(boolean z) {
        return false;
    }

    public boolean setLastPosition(long j, float f) {
        return true;
    }

    public void setLibVLCInstance(long j) {
    }

    public boolean setMaxCacheSize(long j) {
        return false;
    }

    public boolean setSubscriptionMaxCacheSize(long j) {
        return false;
    }

    public boolean setSubscriptionMaxCachedMedia(int i) {
        return false;
    }

    public void setVideoGroupsPrefixLength(int i) {
    }

    public int init(Context context) {
        if (context == null) {
            return 2;
        }
        MLContextTools.getInstance().setContext(context);
        return 0;
    }

    public void start() {
        this.isMedialibraryStarted = true;
        synchronized (this.onMedialibraryReadyListeners) {
            for (Medialibrary.OnMedialibraryReadyListener onMedialibraryReady : this.onMedialibraryReadyListeners) {
                onMedialibraryReady.onMedialibraryReady();
            }
        }
    }

    public void banFolder(String str) {
        if (!this.dt.mBannedFolders.contains(str)) {
            this.dt.mBannedFolders.add(str);
        }
    }

    public void unbanFolder(String str) {
        this.dt.mBannedFolders.remove(str);
    }

    public String[] bannedFolders() {
        return new String[0];
    }

    public String[] getDevices() {
        return new String[0];
    }

    public void loadJsonData(String str) {
        this.dt.loadJsonData(str);
        reload();
    }

    public void discover(String str) {
        onDiscoveryStarted();
        onDiscoveryCompleted();
        onBackgroundTasksIdleChanged(true);
    }

    public String[] getFoldersList() {
        ArrayList arrayList = new ArrayList();
        Iterator<Folder> it = this.dt.mFolders.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getTitle());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public MediaWrapper[] getVideos() {
        return getVideos(0, false, true, false);
    }

    public MediaWrapper[] getPagedVideos(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(stubDataSource.mVideoMediaWrappers, i3, i2 + i3), i, z);
    }

    public MediaWrapper[] getVideos(int i, boolean z, boolean z2, boolean z3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.mVideoMediaWrappers, i, z);
    }

    public MediaWrapper[] getRecentVideos() {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getItemType() == 0) {
                arrayList.add(next);
            }
        }
        return (MediaWrapper[]) arrayList.toArray(new MediaWrapper[0]);
    }

    public MediaWrapper[] getAudio() {
        return getAudio(0, false, true, false);
    }

    public MediaWrapper[] getAudio(int i, boolean z, boolean z2, boolean z3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.mAudioMediaWrappers, i, z);
    }

    public MediaWrapper[] getPagedAudio(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(stubDataSource.mAudioMediaWrappers, i3, i2 + i3), i, z);
    }

    public MediaWrapper[] getRecentAudio() {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getItemType() == 1) {
                arrayList.add(next);
            }
        }
        return (MediaWrapper[]) arrayList.toArray(new MediaWrapper[0]);
    }

    public int getVideoCount() {
        return this.dt.mVideoMediaWrappers.size();
    }

    public int getAudioCount() {
        return this.dt.mAudioMediaWrappers.size();
    }

    public VideoGroup[] getVideoGroups(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return new VideoGroup[0];
    }

    public Album[] getAlbums(boolean z, boolean z2) {
        return getAlbums(0, false, z, z2);
    }

    public Album[] getAlbums(int i, boolean z, boolean z2, boolean z3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortAlbum(stubDataSource.mAlbums, i, z);
    }

    public Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortAlbum(stubDataSource.secureSublist(stubDataSource.mAlbums, i3, i2 + i3), i, z);
    }

    public int getAlbumsCount() {
        return this.dt.mAlbums.size();
    }

    public int getAlbumsCount(String str) {
        Iterator<Album> it = this.dt.mAlbums.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public Album getAlbum(long j) {
        Iterator<Album> it = this.dt.mAlbums.iterator();
        while (it.hasNext()) {
            Album next = it.next();
            if (next.getId() == j) {
                return next;
            }
        }
        return null;
    }

    public Artist[] getArtists(boolean z, boolean z2, boolean z3) {
        return getArtists(z, 0, false, true, false);
    }

    private boolean checkForArtist(ArrayList<Artist> arrayList, Artist artist) {
        Iterator<Artist> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getTitle().equals(artist.getTitle())) {
                return true;
            }
        }
        return false;
    }

    private Artist[] getAlbumArtists() {
        ArrayList arrayList = new ArrayList();
        Iterator<Album> it = this.dt.mAlbums.iterator();
        while (it.hasNext()) {
            Artist retrieveAlbumArtist = it.next().retrieveAlbumArtist();
            if (!checkForArtist(arrayList, retrieveAlbumArtist)) {
                arrayList.add(retrieveAlbumArtist);
            }
        }
        return (Artist[]) arrayList.toArray(new Artist[0]);
    }

    public Artist[] getArtists(boolean z, int i, boolean z2, boolean z3, boolean z4) {
        ArrayList<Artist> arrayList;
        if (z) {
            arrayList = this.dt.mArtists;
        } else {
            arrayList = new ArrayList<>(Arrays.asList(getAlbumArtists()));
        }
        return this.dt.sortArtist(arrayList, i, z2);
    }

    public Artist[] getPagedArtists(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3) {
        ArrayList<Artist> arrayList;
        if (z) {
            arrayList = this.dt.mArtists;
        } else {
            arrayList = new ArrayList<>(Arrays.asList(getAlbumArtists()));
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortArtist(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z2);
    }

    public int getArtistsCount(boolean z) {
        if (z) {
            return this.dt.mArtists.size();
        }
        return getAlbumArtists().length;
    }

    public int getArtistsCount(String str) {
        Iterator<Artist> it = this.dt.mArtists.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public Artist getArtist(long j) {
        Iterator<Artist> it = this.dt.mArtists.iterator();
        while (it.hasNext()) {
            Artist next = it.next();
            if (next.getId() == j) {
                return next;
            }
        }
        return null;
    }

    public Genre[] getGenres(boolean z, boolean z2) {
        return (Genre[]) this.dt.mGenres.toArray(new Genre[0]);
    }

    public Genre[] getGenres(int i, boolean z, boolean z2, boolean z3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortGenre(stubDataSource.mGenres, i, z);
    }

    public Genre[] getPagedGenres(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortGenre(stubDataSource.secureSublist(stubDataSource.mGenres, i3, i2 + i3), i, z);
    }

    public int getGenresCount() {
        return this.dt.mGenres.size();
    }

    public int getGenresCount(String str) {
        Iterator<Genre> it = this.dt.mGenres.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public Genre getGenre(long j) {
        Iterator<Genre> it = this.dt.mGenres.iterator();
        while (it.hasNext()) {
            Genre next = it.next();
            if (next.getId() == j) {
                return next;
            }
        }
        return null;
    }

    public Playlist[] getPlaylists(Playlist.Type type, boolean z) {
        return (Playlist[]) this.dt.mPlaylists.toArray(new Playlist[0]);
    }

    public Playlist[] getPlaylists(Playlist.Type type, int i, boolean z, boolean z2, boolean z3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortPlaylist(stubDataSource.mPlaylists, i, z);
    }

    public Playlist[] getPagedPlaylists(Playlist.Type type, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortPlaylist(stubDataSource.secureSublist(stubDataSource.mPlaylists, i3, i2 + i3), i, z);
    }

    public int getPlaylistsCount() {
        return this.dt.mPlaylists.size();
    }

    public int getPlaylistsCount(String str) {
        Iterator<Playlist> it = this.dt.mPlaylists.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public Playlist getPlaylist(long j, boolean z, boolean z2) {
        Iterator<Playlist> it = this.dt.mPlaylists.iterator();
        while (it.hasNext()) {
            Playlist next = it.next();
            if (next.getId() == j) {
                return next;
            }
        }
        return null;
    }

    public Playlist createPlaylist(String str, boolean z, boolean z2) {
        Playlist abstractPlaylist = MLServiceLocator.getAbstractPlaylist(this.dt.getUUID(), str, 0, 0, 0, 0, 0, 0, false);
        this.dt.mPlaylists.add(abstractPlaylist);
        onPlaylistsAdded();
        return abstractPlaylist;
    }

    public void reload() {
        reload("");
    }

    public void reload(String str) {
        onReloadStarted(str);
        onReloadCompleted(str);
        onBackgroundTasksIdleChanged(true);
    }

    public MediaWrapper[] history(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mHistory.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getType() == 0 || next.getType() == 1) {
                arrayList.add(next);
            }
            if (arrayList.size() >= 100) {
                break;
            }
        }
        return (MediaWrapper[]) arrayList.toArray(new MediaWrapper[0]);
    }

    public boolean clearHistory(int i) {
        this.dt.mHistory.clear();
        return true;
    }

    public boolean addToHistory(String str, String str2) {
        MediaWrapper media = getMedia(str, str2);
        if (media == null) {
            media = addStream(str, str2);
        }
        this.dt.mHistory.add(media);
        setLastTime(media.getId(), media.getTime());
        return true;
    }

    private MediaWrapper getMedia(String str, String str2) {
        String encodeVLCMrl = Tools.encodeVLCMrl(str);
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getLocation().equals(encodeVLCMrl)) {
                return next;
            }
        }
        Iterator<MediaWrapper> it2 = this.dt.mAudioMediaWrappers.iterator();
        while (it2.hasNext()) {
            MediaWrapper next2 = it2.next();
            if (next2.getLocation().equals(encodeVLCMrl)) {
                return next2;
            }
        }
        Iterator<MediaWrapper> it3 = this.dt.mStreamMediaWrappers.iterator();
        while (it3.hasNext()) {
            MediaWrapper next3 = it3.next();
            if (next3.getLocation().equals(encodeVLCMrl)) {
                return next3;
            }
        }
        if (!URLUtil.isNetworkUrl(encodeVLCMrl)) {
            return this.dt.addMediaWrapper(encodeVLCMrl, str2, -1);
        }
        return null;
    }

    public MediaWrapper getMedia(long j) {
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getId() == j) {
                return next;
            }
        }
        Iterator<MediaWrapper> it2 = this.dt.mAudioMediaWrappers.iterator();
        while (it2.hasNext()) {
            MediaWrapper next2 = it2.next();
            if (next2.getId() == j) {
                return next2;
            }
        }
        Iterator<MediaWrapper> it3 = this.dt.mStreamMediaWrappers.iterator();
        while (it3.hasNext()) {
            MediaWrapper next3 = it3.next();
            if (next3.getId() == j) {
                return next3;
            }
        }
        return null;
    }

    public MediaWrapper getMedia(Uri uri) {
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getUri().equals(uri)) {
                return next;
            }
        }
        Iterator<MediaWrapper> it2 = this.dt.mAudioMediaWrappers.iterator();
        while (it2.hasNext()) {
            MediaWrapper next2 = it2.next();
            if (next2.getUri().equals(uri)) {
                return next2;
            }
        }
        Iterator<MediaWrapper> it3 = this.dt.mStreamMediaWrappers.iterator();
        while (it3.hasNext()) {
            MediaWrapper next3 = it3.next();
            if (next3.getUri().equals(uri)) {
                return next3;
            }
        }
        return null;
    }

    public MediaWrapper addStream(String str, String str2) {
        return this.dt.addMediaWrapper(str, str2, 6);
    }

    public Folder[] getFolders(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        ArrayList arrayList = new ArrayList();
        if (i == Folder.TYPE_FOLDER_VIDEO) {
            Iterator<Folder> it = this.dt.mFolders.iterator();
            while (it.hasNext()) {
                Folder next = it.next();
                if (!arrayList.contains(next)) {
                    String str = next.mMrl;
                    if (!str.isEmpty()) {
                        Iterator<MediaWrapper> it2 = this.dt.mVideoMediaWrappers.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            String path = it2.next().getUri().getPath();
                            if (path != null && isParentFolder(str, path)) {
                                arrayList.add(next);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return (Folder[]) arrayList.toArray(new Folder[0]);
    }

    public int getFoldersCount(int i) {
        return getFolders(i, 0, false, true, false, 0, 0).length;
    }

    public int setLastTime(long j, long j2) {
        for (int i = 0; i < this.dt.mVideoMediaWrappers.size(); i++) {
            MediaWrapper mediaWrapper = this.dt.mVideoMediaWrappers.get(i);
            if (mediaWrapper.getId() == j) {
                mediaWrapper.setSeen(mediaWrapper.getSeen() + 1);
                this.dt.mVideoMediaWrappers.set(i, mediaWrapper);
            }
        }
        return 1;
    }

    public SearchAggregate search(String str, boolean z, boolean z2) {
        return new SearchAggregate(searchAlbum(str), searchArtist(str), searchGenre(str), searchVideo(str), searchAudio(str), searchPlaylist(str, Playlist.Type.All, true, false));
    }

    public MediaWrapper[] searchMedia(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        Iterator<MediaWrapper> it2 = this.dt.mAudioMediaWrappers.iterator();
        while (it2.hasNext()) {
            MediaWrapper next2 = it2.next();
            if (Tools.hasSubString(next2.getTitle(), str).booleanValue()) {
                arrayList.add(next2);
            }
        }
        Iterator<MediaWrapper> it3 = this.dt.mStreamMediaWrappers.iterator();
        while (it3.hasNext()) {
            MediaWrapper next3 = it3.next();
            if (Tools.hasSubString(next3.getTitle(), str).booleanValue()) {
                arrayList.add(next3);
            }
        }
        return (MediaWrapper[]) arrayList.toArray(new MediaWrapper[0]);
    }

    public MediaWrapper[] searchMedia(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList(Arrays.asList(searchMedia(str)));
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public int getMediaCount(String str) {
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        Iterator<MediaWrapper> it2 = this.dt.mAudioMediaWrappers.iterator();
        while (it2.hasNext()) {
            if (Tools.hasSubString(it2.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        Iterator<MediaWrapper> it3 = this.dt.mStreamMediaWrappers.iterator();
        while (it3.hasNext()) {
            if (Tools.hasSubString(it3.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    private MediaWrapper[] searchAudio(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return this.dt.sortMedia(arrayList, 0, false);
    }

    public MediaWrapper[] searchAudio(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public int getAudioCount(String str) {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    private MediaWrapper[] searchVideo(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return this.dt.sortMedia(arrayList, 0, false);
    }

    public MediaWrapper[] searchVideo(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public int getVideoCount(String str) {
        Iterator<MediaWrapper> it = this.dt.mVideoMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Tools.hasSubString(it.next().getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public Artist[] searchArtist(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<Artist> it = this.dt.mArtists.iterator();
        while (it.hasNext()) {
            Artist next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return (Artist[]) arrayList.toArray(new Artist[0]);
    }

    public Artist[] searchArtist(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList(Arrays.asList(searchArtist(str)));
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortArtist(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public Album[] searchAlbum(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<Album> it = this.dt.mAlbums.iterator();
        while (it.hasNext()) {
            Album next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return (Album[]) arrayList.toArray(new Album[0]);
    }

    public Album[] searchAlbum(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList(Arrays.asList(searchAlbum(str)));
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortAlbum(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public Genre[] searchGenre(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<Genre> it = this.dt.mGenres.iterator();
        while (it.hasNext()) {
            Genre next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return (Genre[]) arrayList.toArray(new Genre[0]);
    }

    public Genre[] searchGenre(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList(Arrays.asList(searchGenre(str)));
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortGenre(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public Playlist[] searchPlaylist(String str, Playlist.Type type, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        Iterator<Playlist> it = this.dt.mPlaylists.iterator();
        while (it.hasNext()) {
            Playlist next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return (Playlist[]) arrayList.toArray(new Playlist[0]);
    }

    public Playlist[] searchPlaylist(String str, Playlist.Type type, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList(Arrays.asList(searchPlaylist(str, type, z2, z3)));
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortPlaylist(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public Folder[] searchFolders(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return new Folder[0];
    }

    public VideoGroup[] searchVideoGroups(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return new VideoGroup[0];
    }

    private boolean isParentFolder(String str, String str2) {
        if (!str2.contains(str)) {
            return false;
        }
        return new File(str2).getParent().equals(str);
    }
}
