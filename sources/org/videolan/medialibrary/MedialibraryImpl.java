package org.videolan.medialibrary;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.util.VLCUtil;
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

public class MedialibraryImpl extends Medialibrary {
    private static final String TAG = "VLC/JMedialibrary";

    private native void nativeAddDevice(String str, String str2, boolean z);

    private native MediaWrapper nativeAddMedia(String str, long j);

    private native MediaWrapper nativeAddStream(String str, String str2);

    private native boolean nativeAddToHistory(String str, String str2);

    private native void nativeBanFolder(String str);

    private native String[] nativeBannedFolders();

    private native void nativeCacheNewSubscriptionMedia(Medialibrary medialibrary);

    private native boolean nativeClearDatabase(boolean z);

    private native boolean nativeClearHistory(int i);

    private native void nativeConstruct(String str, String str2);

    private native VideoGroup nativeCreateGroup(long[] jArr);

    private native VideoGroup nativeCreateGroupByName(String str);

    private native boolean nativeDeleteRemovableDevices();

    private native String[] nativeDevices();

    private native void nativeDiscover(String str);

    private native boolean nativeFitsInSubscriptionCache(Medialibrary medialibrary, long j);

    private native boolean nativeFlushUserProvidedThumbnails();

    private native void nativeForceParserRetry();

    private native void nativeForceRescan();

    private native Album nativeGetAlbum(long j);

    private native int nativeGetAlbumSearchCount(String str);

    private native Album[] nativeGetAlbums(int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetAlbumsCount();

    private native Artist nativeGetArtist(long j);

    private native Artist[] nativeGetArtists(boolean z, int i, boolean z2, boolean z3, boolean z4);

    private native int nativeGetArtistsCount(boolean z);

    private native int nativeGetArtistsSearchCount(String str);

    private native MediaWrapper[] nativeGetAudio();

    private native int nativeGetAudioCount();

    private native Folder nativeGetFolder(int i, long j);

    private native Folder[] nativeGetFolders(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    private native int nativeGetFoldersCount(int i);

    private native Genre nativeGetGenre(long j);

    private native int nativeGetGenreSearchCount(String str);

    private native Genre[] nativeGetGenres(int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetGenresCount();

    private native VideoGroup nativeGetGroup(long j);

    private native long nativeGetMaxCacheSize(Medialibrary medialibrary);

    private native MediaWrapper nativeGetMedia(long j);

    private native MediaWrapper nativeGetMediaFromMrl(String str);

    private native long nativeGetMlSubscriptionMaxCacheSize(Medialibrary medialibrary);

    private native Album[] nativeGetPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Artist[] nativeGetPagedArtists(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3);

    private native Genre[] nativeGetPagedGenres(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Playlist[] nativeGetPagedPlaylists(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    private native Playlist nativeGetPlaylist(long j, boolean z, boolean z2);

    private native int nativeGetPlaylistSearchCount(String str);

    private native Playlist[] nativeGetPlaylists(int i, int i2, boolean z, boolean z2, boolean z3);

    private native int nativeGetPlaylistsCount();

    private native MediaWrapper[] nativeGetRecentAudio();

    private native MediaWrapper[] nativeGetRecentVideos();

    private native int nativeGetSearchAudioCount(String str);

    private native int nativeGetSearchFoldersCount(String str);

    private native int nativeGetSearchMediaCount(String str);

    private native int nativeGetSearchVideoCount(String str);

    private native MlService nativeGetService(int i);

    private native MediaWrapper[] nativeGetSortedAudio(int i, boolean z, boolean z2, boolean z3);

    private native MediaWrapper[] nativeGetSortedPagedAudio(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeGetSortedPagedVideos(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeGetSortedVideos(int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetSubscriptionMaxCachedMedia(Medialibrary medialibrary);

    private native int nativeGetVideoCount();

    private native VideoGroup[] nativeGetVideoGroups(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native int nativeGetVideoGroupsCount(String str);

    private native MediaWrapper[] nativeGetVideos();

    private native MediaWrapper[] nativeHistory(int i);

    private native int nativeInit(String str);

    private native boolean nativeIsDeviceKnown(String str, String str2, boolean z);

    private native boolean nativeIsServiceSupported(int i);

    private native void nativePauseBackgroundOperations();

    private native Playlist nativePlaylistCreate(String str, boolean z, boolean z2);

    private native boolean nativeRefreshAllSubscriptions(Medialibrary medialibrary);

    private native boolean nativeRegroup(long j);

    private native boolean nativeRegroupAll();

    private native void nativeRelease();

    private native void nativeReload();

    private native void nativeReload(String str);

    private native boolean nativeRemoveDevice(String str, String str2);

    private native boolean nativeRemoveExternalMedia(long j);

    private native void nativeRemoveRoot(String str);

    private native void nativeRequestThumbnail(long j);

    private native void nativeResumeBackgroundOperations();

    private native String[] nativeRoots();

    private native SearchAggregate nativeSearch(String str, boolean z, boolean z2);

    private native Album[] nativeSearchAlbum(String str);

    private native Artist[] nativeSearchArtist(String str);

    private native Genre[] nativeSearchGenre(String str);

    private native MediaWrapper[] nativeSearchMedia(String str);

    private native Album[] nativeSearchPagedAlbum(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Artist[] nativeSearchPagedArtist(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeSearchPagedAudio(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Folder[] nativeSearchPagedFolders(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Genre[] nativeSearchPagedGenre(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native VideoGroup[] nativeSearchPagedGroups(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeSearchPagedMedia(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Playlist[] nativeSearchPagedPlaylist(String str, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    private native MediaWrapper[] nativeSearchPagedVideo(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Playlist[] nativeSearchPlaylist(String str, int i, boolean z, boolean z2);

    private native boolean nativeSetDiscoverNetworkEnabled(boolean z);

    private native boolean nativeSetLastPosition(long j, float f);

    private native int nativeSetLastTime(long j, long j2);

    private native void nativeSetLibVLCInstance(long j);

    private native boolean nativeSetMaxCacheSize(Medialibrary medialibrary, long j);

    private native void nativeSetMediaAddedCbFlag(int i);

    private native void nativeSetMediaUpdatedCbFlag(int i);

    private native boolean nativeSetMlSubscriptionMaxCacheSize(Medialibrary medialibrary, long j);

    private native boolean nativeSetSubscriptionMaxCachedMedia(Medialibrary medialibrary, int i);

    private native void nativeSetVideoGroupsPrefixLength(int i);

    private native void nativeUnbanFolder(String str);

    public boolean construct(Context context) {
        if (context == null) {
            throw new IllegalStateException("context cannot be null");
        } else if (this.mIsInitiated) {
            return false;
        } else {
            MLContextTools.getInstance().setContext(context);
            File externalFilesDir = context.getExternalFilesDir((String) null);
            File dir = context.getDir("db", 0);
            if (externalFilesDir != null && externalFilesDir.exists() && dir != null && dir.canWrite()) {
                LibVLC.loadLibraries();
                try {
                    System.loadLibrary("c++_shared");
                    System.loadLibrary("mla");
                    File file = new File(externalFilesDir + Medialibrary.THUMBS_FOLDER_NAME);
                    if (file.isDirectory()) {
                        new Thread(new MedialibraryImpl$$ExternalSyntheticLambda0(file)).start();
                    }
                    nativeConstruct(dir + Medialibrary.VLC_MEDIA_DB_NAME, externalFilesDir + Medialibrary.MEDIALIB_FOLDER_NAME);
                    return true;
                } catch (UnsatisfiedLinkError e) {
                    Log.e(TAG, "Can't load mla: " + e);
                }
            }
            return false;
        }
    }

    static /* synthetic */ void lambda$construct$0(File file) {
        String[] list = file.list();
        if (list != null) {
            for (String file2 : list) {
                new File(file, file2).delete();
            }
        }
        file.delete();
    }

    public int init(Context context) {
        if (context == null) {
            return 2;
        }
        boolean z = true;
        if (this.mIsInitiated) {
            return 1;
        }
        if (MLContextTools.getInstance().getContext() != null) {
            File dir = context.getDir("db", 0);
            int nativeInit = nativeInit(dir + Medialibrary.VLC_MEDIA_DB_NAME);
            if (nativeInit == 4) {
                Log.e(TAG, "Medialib database is corrupted. Clearing it and try to restore playlists");
                if (!nativeClearDatabase(true)) {
                    return 5;
                }
            }
            if (nativeInit == 2) {
                z = false;
            }
            this.mIsInitiated = z;
            return nativeInit;
        }
        throw new IllegalStateException("Medialibrary construct has to be called before init");
    }

    public void start() {
        if (!isStarted()) {
            this.isMedialibraryStarted = true;
            synchronized (this.onMedialibraryReadyListeners) {
                for (Medialibrary.OnMedialibraryReadyListener onMedialibraryReady : this.onMedialibraryReadyListeners) {
                    onMedialibraryReady.onMedialibraryReady();
                }
            }
            nativeSetMediaAddedCbFlag(160);
            nativeSetMediaUpdatedCbFlag(10);
        }
    }

    public void banFolder(String str) {
        if (this.mIsInitiated && new File(str).exists()) {
            nativeBanFolder(Tools.mlEncodeMrl(str));
        }
    }

    public void unbanFolder(String str) {
        if (this.mIsInitiated && new File(str).exists()) {
            nativeUnbanFolder(Tools.mlEncodeMrl(str));
        }
    }

    public String[] bannedFolders() {
        return this.mIsInitiated ? nativeBannedFolders() : new String[0];
    }

    public String[] getDevices() {
        return this.mIsInitiated ? nativeDevices() : new String[0];
    }

    public boolean isDeviceKnown(String str, String str2, boolean z) {
        return this.mIsInitiated && nativeIsDeviceKnown(VLCUtil.encodeVLCString(str), Tools.encodeVLCMrl(str2), z);
    }

    public boolean deleteRemovableDevices() {
        return this.mIsInitiated && nativeDeleteRemovableDevices();
    }

    public void addDevice(String str, String str2, boolean z) {
        nativeAddDevice(VLCUtil.encodeVLCString(str), Tools.encodeVLCMrl(str2), z);
        synchronized (this.onDeviceChangeListeners) {
            for (Medialibrary.OnDeviceChangeListener onDeviceChange : this.onDeviceChangeListeners) {
                onDeviceChange.onDeviceChange();
            }
        }
    }

    public void discover(String str) {
        if (this.mIsInitiated) {
            nativeDiscover(Tools.encodeVLCMrl(str));
        }
    }

    public void setLibVLCInstance(long j) {
        if (this.mIsInitiated) {
            nativeSetLibVLCInstance(j);
        }
    }

    public boolean setDiscoverNetworkEnabled(boolean z) {
        if (this.mIsInitiated) {
            return nativeSetDiscoverNetworkEnabled(z);
        }
        return false;
    }

    public void removeFolder(String str) {
        if (this.mIsInitiated) {
            for (String str2 : getFoldersList()) {
                if (!str2.equals(str)) {
                    if (!str2.equals(str + "/") && str2.contains(str)) {
                        removeFolder(str2);
                    }
                }
            }
            nativeRemoveRoot(Tools.encodeVLCMrl(str));
        }
    }

    public String[] getFoldersList() {
        if (!this.mIsInitiated) {
            return new String[0];
        }
        return nativeRoots();
    }

    public boolean removeDevice(String str, String str2) {
        boolean z = false;
        if (!this.mIsInitiated) {
            return false;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && nativeRemoveDevice(VLCUtil.encodeVLCString(str), Tools.encodeVLCMrl(str2))) {
            z = true;
        }
        synchronized (this.onDeviceChangeListeners) {
            for (Medialibrary.OnDeviceChangeListener onDeviceChange : this.onDeviceChangeListeners) {
                onDeviceChange.onDeviceChange();
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (this.mIsInitiated) {
            nativeRelease();
        }
        super.finalize();
    }

    public MediaWrapper[] getVideos() {
        return this.mIsInitiated ? nativeGetVideos() : new MediaWrapper[0];
    }

    public MediaWrapper[] getPagedVideos(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return this.mIsInitiated ? nativeGetSortedPagedVideos(i, z, z2, z3, i2, i3) : new MediaWrapper[0];
    }

    public MediaWrapper[] getVideos(int i, boolean z, boolean z2, boolean z3) {
        return this.mIsInitiated ? nativeGetSortedVideos(i, z, z2, z3) : new MediaWrapper[0];
    }

    public MediaWrapper[] getRecentVideos() {
        return this.mIsInitiated ? nativeGetRecentVideos() : new MediaWrapper[0];
    }

    public MediaWrapper[] getAudio() {
        return this.mIsInitiated ? nativeGetAudio() : new MediaWrapper[0];
    }

    public MediaWrapper[] getAudio(int i, boolean z, boolean z2, boolean z3) {
        return this.mIsInitiated ? nativeGetSortedAudio(i, z, z2, z3) : new MediaWrapper[0];
    }

    public MediaWrapper[] getPagedAudio(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return this.mIsInitiated ? nativeGetSortedPagedAudio(i, z, z2, z3, i2, i3) : new MediaWrapper[0];
    }

    public MediaWrapper[] getRecentAudio() {
        return this.mIsInitiated ? nativeGetRecentAudio() : new MediaWrapper[0];
    }

    public int getVideoCount() {
        if (this.mIsInitiated) {
            return nativeGetVideoCount();
        }
        return 0;
    }

    public int getAudioCount() {
        if (this.mIsInitiated) {
            return nativeGetAudioCount();
        }
        return 0;
    }

    public VideoGroup[] getVideoGroups(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return this.mIsInitiated ? nativeGetVideoGroups(i, z, z2, z3, i2, i3) : new VideoGroup[0];
    }

    public int getVideoGroupsCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetVideoGroupsCount(str);
        }
        return 0;
    }

    public void setVideoGroupsPrefixLength(int i) {
        if (this.mIsInitiated) {
            nativeSetVideoGroupsPrefixLength(i);
        }
    }

    public VideoGroup createVideoGroup(String str) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeCreateGroupByName(str);
    }

    public VideoGroup createVideoGroup(long[] jArr) {
        if (!this.mIsInitiated || jArr.length == 0) {
            return null;
        }
        return nativeCreateGroup(jArr);
    }

    public VideoGroup getVideoGroup(long j) {
        if (this.mIsInitiated) {
            return nativeGetGroup(j);
        }
        return null;
    }

    public boolean regroupAll() {
        return this.mIsInitiated && nativeRegroupAll();
    }

    public boolean regroup(long j) {
        return this.mIsInitiated && j > 0 && nativeRegroup(j);
    }

    public Album[] getAlbums(boolean z, boolean z2) {
        return getAlbums(0, false, z, z2);
    }

    public Album[] getAlbums(int i, boolean z, boolean z2, boolean z3) {
        return this.mIsInitiated ? nativeGetAlbums(i, z, z2, z3) : new Album[0];
    }

    public Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return this.mIsInitiated ? nativeGetPagedAlbums(i, z, z2, z3, i2, i3) : new Album[0];
    }

    public int getAlbumsCount() {
        if (this.mIsInitiated) {
            return nativeGetAlbumsCount();
        }
        return 0;
    }

    public int getAlbumsCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetAlbumSearchCount(str);
        }
        return 0;
    }

    public Album getAlbum(long j) {
        if (this.mIsInitiated) {
            return nativeGetAlbum(j);
        }
        return null;
    }

    public Artist[] getArtists(boolean z, boolean z2, boolean z3) {
        return getArtists(z, 0, false, z2, z3);
    }

    public Artist[] getArtists(boolean z, int i, boolean z2, boolean z3, boolean z4) {
        return this.mIsInitiated ? nativeGetArtists(z, i, z2, z3, z4) : new Artist[0];
    }

    public Artist[] getPagedArtists(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3) {
        return this.mIsInitiated ? nativeGetPagedArtists(z, i, z2, z3, z4, i2, i3) : new Artist[0];
    }

    public int getArtistsCount(boolean z) {
        if (this.mIsInitiated) {
            return nativeGetArtistsCount(z);
        }
        return 0;
    }

    public int getArtistsCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetArtistsSearchCount(str);
        }
        return 0;
    }

    public Artist getArtist(long j) {
        if (this.mIsInitiated) {
            return nativeGetArtist(j);
        }
        return null;
    }

    public Genre[] getGenres(boolean z, boolean z2) {
        return getGenres(0, false, z, z2);
    }

    public Genre[] getGenres(int i, boolean z, boolean z2, boolean z3) {
        return this.mIsInitiated ? nativeGetGenres(i, z, z2, z3) : new Genre[0];
    }

    public Genre[] getPagedGenres(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return this.mIsInitiated ? nativeGetPagedGenres(i, z, z2, z3, i2, i3) : new Genre[0];
    }

    public int getGenresCount() {
        if (this.mIsInitiated) {
            return nativeGetGenresCount();
        }
        return 0;
    }

    public int getGenresCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetGenreSearchCount(str);
        }
        return 0;
    }

    public Genre getGenre(long j) {
        if (this.mIsInitiated) {
            return nativeGetGenre(j);
        }
        return null;
    }

    public Playlist[] getPlaylists(Playlist.Type type, boolean z) {
        return getPlaylists(type, 0, false, true, z);
    }

    public Playlist[] getPlaylists(Playlist.Type type, int i, boolean z, boolean z2, boolean z3) {
        if (!this.mIsInitiated) {
            return new Playlist[0];
        }
        return nativeGetPlaylists(type.ordinal(), i, z, z2, z3);
    }

    public Playlist[] getPagedPlaylists(Playlist.Type type, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated) {
            return new Playlist[0];
        }
        return nativeGetPagedPlaylists(type.ordinal(), i, z, z2, z3, i2, i3);
    }

    public int getPlaylistsCount() {
        if (this.mIsInitiated) {
            return nativeGetPlaylistsCount();
        }
        return 0;
    }

    public int getPlaylistsCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetPlaylistSearchCount(str);
        }
        return 0;
    }

    public Playlist getPlaylist(long j, boolean z, boolean z2) {
        if (this.mIsInitiated) {
            return nativeGetPlaylist(j, z, z2);
        }
        return null;
    }

    public Playlist createPlaylist(String str, boolean z, boolean z2) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativePlaylistCreate(str, z, z2);
    }

    public void pauseBackgroundOperations() {
        if (this.mIsInitiated) {
            nativePauseBackgroundOperations();
        }
    }

    public void resumeBackgroundOperations() {
        if (this.mIsInitiated) {
            nativeResumeBackgroundOperations();
        }
    }

    public void reload() {
        if (this.mIsInitiated) {
            nativeReload();
        }
    }

    public void reload(String str) {
        if (this.mIsInitiated && !TextUtils.isEmpty(str)) {
            nativeReload(Tools.encodeVLCMrl(str));
        }
    }

    public void forceParserRetry() {
        if (this.mIsInitiated) {
            nativeForceParserRetry();
        }
    }

    public void forceRescan() {
        if (this.mIsInitiated) {
            nativeForceRescan();
        }
    }

    public MediaWrapper[] history(int i) {
        return this.mIsInitiated ? nativeHistory(i) : EMPTY_COLLECTION;
    }

    public boolean clearHistory(int i) {
        return this.mIsInitiated && nativeClearHistory(i);
    }

    public void clearDatabase(boolean z) {
        if (this.mIsInitiated) {
            nativeClearDatabase(z);
        }
    }

    public boolean addToHistory(String str, String str2) {
        return this.mIsInitiated && nativeAddToHistory(Tools.encodeVLCMrl(str), Tools.encodeVLCMrl(str2));
    }

    public MediaWrapper getMedia(long j) {
        if (this.mIsInitiated) {
            return nativeGetMedia(j);
        }
        return null;
    }

    public MediaWrapper getMedia(Uri uri) {
        if ("content".equals(uri.getScheme())) {
            return null;
        }
        String encodeVLCMrl = Tools.encodeVLCMrl(uri.toString());
        if (!this.mIsInitiated || TextUtils.isEmpty(encodeVLCMrl)) {
            return null;
        }
        return nativeGetMediaFromMrl(encodeVLCMrl);
    }

    public MediaWrapper getMedia(String str) {
        if (str != null && str.startsWith("content:")) {
            return null;
        }
        String encodeVLCMrl = Tools.encodeVLCMrl(str);
        if (!this.mIsInitiated || TextUtils.isEmpty(encodeVLCMrl)) {
            return null;
        }
        return nativeGetMediaFromMrl(encodeVLCMrl);
    }

    public MediaWrapper addMedia(String str, long j) {
        String encodeVLCMrl = Tools.encodeVLCMrl(str);
        if (!this.mIsInitiated || TextUtils.isEmpty(encodeVLCMrl)) {
            return null;
        }
        return nativeAddMedia(encodeVLCMrl, j);
    }

    public boolean removeExternalMedia(long j) {
        return this.mIsInitiated && nativeRemoveExternalMedia(j);
    }

    public boolean flushUserProvidedThumbnails() {
        return this.mIsInitiated && nativeFlushUserProvidedThumbnails();
    }

    public MediaWrapper addStream(String str, String str2) {
        String encodeVLCMrl = Tools.encodeVLCMrl(str);
        String encodeVLCMrl2 = Tools.encodeVLCMrl(str2);
        if (!this.mIsInitiated || TextUtils.isEmpty(encodeVLCMrl)) {
            return null;
        }
        return nativeAddStream(encodeVLCMrl, encodeVLCMrl2);
    }

    public Folder[] getFolders(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        return this.mIsInitiated ? nativeGetFolders(i, i2, z, z2, z3, i3, i4) : new Folder[0];
    }

    public Folder getFolder(int i, long j) {
        if (this.mIsInitiated) {
            return nativeGetFolder(i, j);
        }
        return null;
    }

    public int getFoldersCount(int i) {
        if (this.mIsInitiated) {
            return nativeGetFoldersCount(i);
        }
        return 0;
    }

    public int setLastTime(long j, long j2) {
        if (!this.mIsInitiated || j < 1) {
            return 0;
        }
        return nativeSetLastTime(j, j2);
    }

    public boolean setLastPosition(long j, float f) {
        return this.mIsInitiated && j > 0 && nativeSetLastPosition(j, f);
    }

    public MediaWrapper findMedia(MediaWrapper mediaWrapper) {
        MediaWrapper media;
        if (this.mIsInitiated && mediaWrapper != null && mediaWrapper.getId() == 0) {
            Uri uri = mediaWrapper.getUri();
            MediaWrapper media2 = getMedia(uri);
            if (media2 != null) {
                media2.addFlags(mediaWrapper.getFlags());
                return media2;
            } else if (TextUtils.equals("file", uri.getScheme()) && uri.getPath() != null && uri.getPath().startsWith("/sdcard") && (media = getMedia(Tools.convertLocalUri(uri))) != null) {
                media.addFlags(mediaWrapper.getFlags());
                return media;
            }
        }
        return mediaWrapper;
    }

    public SearchAggregate search(String str, boolean z, boolean z2) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearch(str, z, z2);
    }

    public MediaWrapper[] searchMedia(String str) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchMedia(str);
    }

    public MediaWrapper[] searchMedia(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPagedMedia(str, i, z, z2, z3, i2, i3);
    }

    public int getMediaCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetSearchMediaCount(str);
        }
        return 0;
    }

    public MediaWrapper[] searchAudio(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPagedAudio(str, i, z, z2, z3, i2, i3);
    }

    public int getAudioCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetSearchAudioCount(str);
        }
        return 0;
    }

    public MediaWrapper[] searchVideo(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPagedVideo(str, i, z, z2, z3, i2, i3);
    }

    public int getVideoCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetSearchVideoCount(str);
        }
        return 0;
    }

    public Artist[] searchArtist(String str) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchArtist(str);
    }

    public Artist[] searchArtist(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return (!this.mIsInitiated || TextUtils.isEmpty(str)) ? new Artist[0] : nativeSearchPagedArtist(str, i, z, z2, z3, i2, i3);
    }

    public Album[] searchAlbum(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPagedAlbum(str, i, z, z2, z3, i2, i3);
    }

    public Album[] searchAlbum(String str) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchAlbum(str);
    }

    public Genre[] searchGenre(String str) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchGenre(str);
    }

    public Genre[] searchGenre(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPagedGenre(str, i, z, z2, z3, i2, i3);
    }

    public Playlist[] searchPlaylist(String str, Playlist.Type type, boolean z, boolean z2) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPlaylist(str, type.ordinal(), z, z2);
    }

    public Playlist[] searchPlaylist(String str, Playlist.Type type, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        if (!this.mIsInitiated || TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeSearchPagedPlaylist(str, type.ordinal(), i, z, z2, z3, i2, i3);
    }

    public Folder[] searchFolders(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return (!this.mIsInitiated || TextUtils.isEmpty(str)) ? new Folder[0] : nativeSearchPagedFolders(str, i, z, z2, z3, i2, i3);
    }

    public int getFoldersCount(String str) {
        if (this.mIsInitiated) {
            return nativeGetSearchFoldersCount(str);
        }
        return 0;
    }

    public VideoGroup[] searchVideoGroups(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return (!this.mIsInitiated || TextUtils.isEmpty(str)) ? new VideoGroup[0] : nativeSearchPagedGroups(str, i, z, z2, z3, i2, i3);
    }

    public MlService getService(MlService.Type type) {
        if (this.mIsInitiated) {
            return nativeGetService(type.value);
        }
        return null;
    }

    public boolean fitsInSubscriptionCache(MediaWrapper mediaWrapper) {
        return this.mIsInitiated && nativeFitsInSubscriptionCache(this, mediaWrapper.getId());
    }

    public void cacheNewSubscriptionMedia() {
        if (this.mIsInitiated) {
            nativeCacheNewSubscriptionMedia(this);
        }
    }

    public boolean setSubscriptionMaxCachedMedia(int i) {
        return this.mIsInitiated && nativeSetSubscriptionMaxCachedMedia(this, i);
    }

    public boolean setSubscriptionMaxCacheSize(long j) {
        return this.mIsInitiated && nativeSetMlSubscriptionMaxCacheSize(this, j);
    }

    public boolean setMaxCacheSize(long j) {
        return this.mIsInitiated && nativeSetMaxCacheSize(this, j);
    }

    public int getSubscriptionMaxCachedMedia() {
        if (this.mIsInitiated) {
            return nativeGetSubscriptionMaxCachedMedia(this);
        }
        return -1;
    }

    public long getSubscriptionMaxCacheSize() {
        if (this.mIsInitiated) {
            return nativeGetMlSubscriptionMaxCacheSize(this);
        }
        return -1;
    }

    public long getMaxCacheSize() {
        if (this.mIsInitiated) {
            return nativeGetMaxCacheSize(this);
        }
        return -1;
    }

    public boolean refreshAllSubscriptions() {
        return this.mIsInitiated && nativeRefreshAllSubscriptions(this);
    }
}
