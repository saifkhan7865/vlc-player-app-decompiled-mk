package org.videolan.medialibrary.interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.videolan.medialibrary.EventTools;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.MlService;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.SearchAggregate;

public abstract class Medialibrary {
    public static final MediaWrapper[] EMPTY_COLLECTION = new MediaWrapper[0];
    public static final int FLAG_MEDIA_ADDED_AUDIO = 16;
    public static final int FLAG_MEDIA_ADDED_AUDIO_EMPTY = 32;
    public static final int FLAG_MEDIA_ADDED_VIDEO = 64;
    public static final int FLAG_MEDIA_ADDED_VIDEO_EMPTY = 128;
    public static final int FLAG_MEDIA_UPDATED_AUDIO = 1;
    public static final int FLAG_MEDIA_UPDATED_AUDIO_EMPTY = 2;
    public static final int FLAG_MEDIA_UPDATED_VIDEO = 4;
    public static final int FLAG_MEDIA_UPDATED_VIDEO_EMPTY = 8;
    public static final int HISTORY_TYPE_GLOBAL = 0;
    public static final int HISTORY_TYPE_LOCAL = 1;
    public static final int HISTORY_TYPE_NETWORK = 2;
    public static final String MEDIALIB_FOLDER_NAME = "/medialib";
    public static final int ML_INIT_ALREADY_INITIALIZED = 1;
    public static final int ML_INIT_DB_CORRUPTED = 4;
    public static final int ML_INIT_DB_RESET = 3;
    public static final int ML_INIT_DB_UNRECOVERABLE = 5;
    public static final int ML_INIT_FAILED = 2;
    public static final int ML_INIT_SUCCESS = 0;
    public static final int ML_SET_TIME_AS_IS = 2;
    public static final int ML_SET_TIME_BEGIN = 1;
    public static final int ML_SET_TIME_END = 3;
    public static final int ML_SET_TIME_ERROR = 0;
    public static final int NbAudio = 14;
    public static final int NbMedia = 15;
    public static final int NbVideo = 13;
    public static final int SORT_ALBUM = 9;
    public static final int SORT_ALPHA = 1;
    public static final int SORT_ARTIST = 7;
    public static final int SORT_DEFAULT = 0;
    public static final int SORT_DURATION = 2;
    public static final int SORT_FILENAME = 10;
    public static final int SORT_FILESIZE = 6;
    public static final int SORT_INSERTIONDATE = 3;
    public static final int SORT_LASTMODIFICATIONDATE = 4;
    public static final int SORT_PLAYCOUNT = 8;
    public static final int SORT_RELEASEDATE = 5;
    public static final String THUMBS_FOLDER_NAME = "/thumbs";
    public static final int TrackId = 12;
    public static final int TrackNumber = 11;
    public static final String VLC_MEDIA_DB_NAME = "/vlc_media.db";
    protected static final Medialibrary instance = MLServiceLocator.getAbstractMedialibrary();
    protected static final MutableLiveData<Boolean> sRunning = new MutableLiveData<>();
    protected final List<DevicesDiscoveryCb> devicesDiscoveryCbList = new ArrayList();
    protected volatile boolean isMedialibraryStarted = false;
    protected final List<AlbumsCb> mAlbumsCbs = new ArrayList();
    protected final List<ArtistsCb> mArtistsCbs = new ArrayList();
    private MedialibraryExceptionHandler mExceptionHandler;
    protected final List<FoldersCb> mFoldersCbs = new ArrayList();
    protected final List<GenresCb> mGenreCbs = new ArrayList();
    protected final List<HistoryCb> mHistoryCbs = new ArrayList();
    protected long mInstanceID;
    protected volatile boolean mIsInitiated = false;
    protected volatile boolean mIsWorking = false;
    protected final List<MediaCb> mMediaCbs = new ArrayList();
    protected final List<MediaGroupCb> mMediaGroupCbs = new ArrayList();
    protected final List<PlaylistsCb> mPlaylistCbs = new ArrayList();
    protected final List<OnDeviceChangeListener> onDeviceChangeListeners = new ArrayList();
    protected final List<OnMedialibraryReadyListener> onMedialibraryReadyListeners = new ArrayList();
    protected final List<RootsEventsCb> rootsEventsCbList = new ArrayList();

    public interface AlbumsCb {
        void onAlbumsAdded();

        void onAlbumsDeleted();

        void onAlbumsModified();
    }

    public interface ArtistsCb {
        void onArtistsAdded();

        void onArtistsDeleted();

        void onArtistsModified();
    }

    public interface FoldersCb {
        void onFoldersAdded();

        void onFoldersDeleted();

        void onFoldersModified();
    }

    public interface GenresCb {
        void onGenresAdded();

        void onGenresDeleted();

        void onGenresModified();
    }

    public interface HistoryCb {
        void onHistoryModified();
    }

    public interface MediaCb {
        void onMediaAdded();

        void onMediaConvertedToExternal(long[] jArr);

        void onMediaDeleted(long[] jArr);

        void onMediaModified();
    }

    public interface MediaGroupCb {
        void onMediaGroupsAdded();

        void onMediaGroupsDeleted();

        void onMediaGroupsModified();
    }

    public interface MedialibraryExceptionHandler {
        void onUnhandledException(String str, String str2, boolean z);
    }

    public interface OnDeviceChangeListener {
        void onDeviceChange();
    }

    public interface OnMedialibraryReadyListener {
        void onMedialibraryIdle();

        void onMedialibraryReady();
    }

    public interface PlaylistsCb {
        void onPlaylistsAdded();

        void onPlaylistsDeleted();

        void onPlaylistsModified();
    }

    public enum ThumbnailSizeType {
        Thumbnail,
        Banner
    }

    public abstract void addDevice(String str, String str2, boolean z);

    public abstract MediaWrapper addMedia(String str, long j);

    public abstract MediaWrapper addStream(String str, String str2);

    public abstract boolean addToHistory(String str, String str2);

    public abstract void banFolder(String str);

    public abstract String[] bannedFolders();

    public abstract void cacheNewSubscriptionMedia();

    public abstract void clearDatabase(boolean z);

    public abstract boolean clearHistory(int i);

    public abstract boolean construct(Context context);

    public abstract Playlist createPlaylist(String str, boolean z, boolean z2);

    public abstract VideoGroup createVideoGroup(String str);

    public abstract VideoGroup createVideoGroup(long[] jArr);

    public abstract boolean deleteRemovableDevices();

    public abstract void discover(String str);

    public abstract boolean fitsInSubscriptionCache(MediaWrapper mediaWrapper);

    public abstract boolean flushUserProvidedThumbnails();

    public abstract void forceParserRetry();

    public abstract void forceRescan();

    public abstract Album getAlbum(long j);

    public abstract Album[] getAlbums(int i, boolean z, boolean z2, boolean z3);

    public abstract Album[] getAlbums(boolean z, boolean z2);

    public abstract int getAlbumsCount();

    public abstract int getAlbumsCount(String str);

    public abstract Artist getArtist(long j);

    public abstract Artist[] getArtists(boolean z, int i, boolean z2, boolean z3, boolean z4);

    public abstract Artist[] getArtists(boolean z, boolean z2, boolean z3);

    public abstract int getArtistsCount(String str);

    public abstract int getArtistsCount(boolean z);

    public abstract MediaWrapper[] getAudio();

    public abstract MediaWrapper[] getAudio(int i, boolean z, boolean z2, boolean z3);

    public abstract int getAudioCount();

    public abstract int getAudioCount(String str);

    public abstract String[] getDevices();

    public abstract Folder getFolder(int i, long j);

    public abstract Folder[] getFolders(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    public abstract int getFoldersCount(int i);

    public abstract int getFoldersCount(String str);

    public abstract String[] getFoldersList();

    public abstract Genre getGenre(long j);

    public abstract Genre[] getGenres(int i, boolean z, boolean z2, boolean z3);

    public abstract Genre[] getGenres(boolean z, boolean z2);

    public abstract int getGenresCount();

    public abstract int getGenresCount(String str);

    public abstract long getMaxCacheSize();

    public abstract MediaWrapper getMedia(long j);

    public abstract MediaWrapper getMedia(Uri uri);

    public abstract MediaWrapper getMedia(String str);

    public abstract int getMediaCount(String str);

    public abstract Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Artist[] getPagedArtists(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3);

    public abstract MediaWrapper[] getPagedAudio(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Genre[] getPagedGenres(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Playlist[] getPagedPlaylists(Playlist.Type type, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract MediaWrapper[] getPagedVideos(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Playlist getPlaylist(long j, boolean z, boolean z2);

    public abstract Playlist[] getPlaylists(Playlist.Type type, int i, boolean z, boolean z2, boolean z3);

    public abstract Playlist[] getPlaylists(Playlist.Type type, boolean z);

    public abstract int getPlaylistsCount();

    public abstract int getPlaylistsCount(String str);

    public abstract MediaWrapper[] getRecentAudio();

    public abstract MediaWrapper[] getRecentVideos();

    public abstract MlService getService(MlService.Type type);

    public abstract long getSubscriptionMaxCacheSize();

    public abstract int getSubscriptionMaxCachedMedia();

    public abstract int getVideoCount();

    public abstract int getVideoCount(String str);

    public abstract VideoGroup getVideoGroup(long j);

    public abstract VideoGroup[] getVideoGroups(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int getVideoGroupsCount(String str);

    public abstract MediaWrapper[] getVideos();

    public abstract MediaWrapper[] getVideos(int i, boolean z, boolean z2, boolean z3);

    public abstract MediaWrapper[] history(int i);

    public abstract int init(Context context);

    public abstract boolean isDeviceKnown(String str, String str2, boolean z);

    public abstract void pauseBackgroundOperations();

    public abstract boolean refreshAllSubscriptions();

    public abstract boolean regroup(long j);

    public abstract boolean regroupAll();

    public abstract void reload();

    public abstract void reload(String str);

    public abstract boolean removeDevice(String str, String str2);

    public abstract boolean removeExternalMedia(long j);

    public abstract void removeFolder(String str);

    public abstract void resumeBackgroundOperations();

    public abstract SearchAggregate search(String str, boolean z, boolean z2);

    public abstract Album[] searchAlbum(String str);

    public abstract Album[] searchAlbum(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Artist[] searchArtist(String str);

    public abstract Artist[] searchArtist(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract MediaWrapper[] searchAudio(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Folder[] searchFolders(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Genre[] searchGenre(String str);

    public abstract Genre[] searchGenre(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract MediaWrapper[] searchMedia(String str);

    public abstract MediaWrapper[] searchMedia(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Playlist[] searchPlaylist(String str, Playlist.Type type, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract Playlist[] searchPlaylist(String str, Playlist.Type type, boolean z, boolean z2);

    public abstract MediaWrapper[] searchVideo(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract VideoGroup[] searchVideoGroups(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract boolean setDiscoverNetworkEnabled(boolean z);

    public abstract boolean setLastPosition(long j, float f);

    public abstract int setLastTime(long j, long j2);

    public abstract void setLibVLCInstance(long j);

    public abstract boolean setMaxCacheSize(long j);

    public abstract boolean setSubscriptionMaxCacheSize(long j);

    public abstract boolean setSubscriptionMaxCachedMedia(int i);

    public abstract void setVideoGroupsPrefixLength(int i);

    public abstract void start();

    public abstract void unbanFolder(String str);

    public static LiveData<Boolean> getState() {
        return sRunning;
    }

    public boolean isStarted() {
        return this.isMedialibraryStarted;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static Medialibrary getInstance() {
        return instance;
    }

    private Object getWeakReference() {
        return new WeakReference(this);
    }

    public long getId() {
        return this.mInstanceID;
    }

    public boolean isWorking() {
        return this.mIsWorking;
    }

    public boolean isInitiated() {
        return this.mIsInitiated;
    }

    public static String[] getBanList() {
        return new String[]{"/Android/data/", "/Android/media/", "/Alarms/", "/Ringtones/", "/Notifications/", "/alarms/", "/ringtones/", "/notifications/", "/audio/Alarms/", "/audio/Ringtones/", "/audio/Notifications/", "/audio/alarms/", "/audio/ringtones/", "/audio/notifications/", "/WhatsApp/Media/WhatsApp%20Animated%20Gifs/"};
    }

    public static File[] getDefaultFolders() {
        return new File[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)};
    }

    /* access modifiers changed from: protected */
    public boolean canReadStorage(Context context) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    public MedialibraryExceptionHandler getExceptionHandler() {
        return this.mExceptionHandler;
    }

    public void setExceptionHandler(MedialibraryExceptionHandler medialibraryExceptionHandler) {
        this.mExceptionHandler = medialibraryExceptionHandler;
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

    public void onMediaAdded(MediaWrapper[] mediaWrapperArr) {
        synchronized (this.mMediaCbs) {
            for (MediaCb onMediaAdded : this.mMediaCbs) {
                onMediaAdded.onMediaAdded();
            }
        }
    }

    public void onMediaUpdated() {
        synchronized (this.mMediaCbs) {
            for (MediaCb onMediaModified : this.mMediaCbs) {
                onMediaModified.onMediaModified();
            }
        }
    }

    public void onMediaDeleted(long[] jArr) {
        synchronized (this.mMediaCbs) {
            for (MediaCb onMediaDeleted : this.mMediaCbs) {
                onMediaDeleted.onMediaDeleted(jArr);
            }
        }
    }

    public void onMediaConvertedToExternal(long[] jArr) {
        synchronized (this.mMediaCbs) {
            for (MediaCb onMediaConvertedToExternal : this.mMediaCbs) {
                onMediaConvertedToExternal.onMediaConvertedToExternal(jArr);
            }
        }
    }

    public void onArtistsAdded() {
        synchronized (this.mArtistsCbs) {
            for (ArtistsCb onArtistsAdded : this.mArtistsCbs) {
                onArtistsAdded.onArtistsAdded();
            }
        }
    }

    public void onArtistsModified() {
        synchronized (this.mArtistsCbs) {
            for (ArtistsCb onArtistsModified : this.mArtistsCbs) {
                onArtistsModified.onArtistsModified();
            }
        }
    }

    public void onArtistsDeleted() {
        synchronized (this.mArtistsCbs) {
            for (ArtistsCb onArtistsDeleted : this.mArtistsCbs) {
                onArtistsDeleted.onArtistsDeleted();
            }
        }
    }

    public void onAlbumsAdded() {
        synchronized (this.mAlbumsCbs) {
            for (AlbumsCb onAlbumsAdded : this.mAlbumsCbs) {
                onAlbumsAdded.onAlbumsAdded();
            }
        }
    }

    public void onAlbumsModified() {
        synchronized (this.mAlbumsCbs) {
            for (AlbumsCb onAlbumsModified : this.mAlbumsCbs) {
                onAlbumsModified.onAlbumsModified();
            }
        }
    }

    public void onAlbumsDeleted() {
        synchronized (this.mAlbumsCbs) {
            for (AlbumsCb onAlbumsDeleted : this.mAlbumsCbs) {
                onAlbumsDeleted.onAlbumsDeleted();
            }
        }
    }

    public void onGenresAdded() {
        synchronized (this.mGenreCbs) {
            for (GenresCb onGenresAdded : this.mGenreCbs) {
                onGenresAdded.onGenresAdded();
            }
        }
    }

    public void onGenresModified() {
        synchronized (this.mGenreCbs) {
            for (GenresCb onGenresModified : this.mGenreCbs) {
                onGenresModified.onGenresModified();
            }
        }
    }

    public void onGenresDeleted() {
        synchronized (this.mGenreCbs) {
            for (GenresCb onGenresDeleted : this.mGenreCbs) {
                onGenresDeleted.onGenresDeleted();
            }
        }
    }

    public void onPlaylistsAdded() {
        synchronized (this.mPlaylistCbs) {
            for (PlaylistsCb onPlaylistsAdded : this.mPlaylistCbs) {
                onPlaylistsAdded.onPlaylistsAdded();
            }
        }
    }

    public void onPlaylistsModified() {
        synchronized (this.mPlaylistCbs) {
            for (PlaylistsCb onPlaylistsModified : this.mPlaylistCbs) {
                onPlaylistsModified.onPlaylistsModified();
            }
        }
    }

    public void onPlaylistsDeleted() {
        synchronized (this.mPlaylistCbs) {
            for (PlaylistsCb onPlaylistsDeleted : this.mPlaylistCbs) {
                onPlaylistsDeleted.onPlaylistsDeleted();
            }
        }
    }

    public void onHistoryChanged(int i) {
        synchronized (this.mHistoryCbs) {
            for (HistoryCb onHistoryModified : this.mHistoryCbs) {
                onHistoryModified.onHistoryModified();
            }
        }
    }

    public void onMediaGroupAdded() {
        synchronized (this.mMediaGroupCbs) {
            for (MediaGroupCb onMediaGroupsAdded : this.mMediaGroupCbs) {
                onMediaGroupsAdded.onMediaGroupsAdded();
            }
        }
    }

    public void onMediaGroupModified() {
        synchronized (this.mMediaGroupCbs) {
            for (MediaGroupCb onMediaGroupsModified : this.mMediaGroupCbs) {
                onMediaGroupsModified.onMediaGroupsModified();
            }
        }
    }

    public void onMediaGroupDeleted() {
        synchronized (this.mMediaGroupCbs) {
            for (MediaGroupCb onMediaGroupsDeleted : this.mMediaGroupCbs) {
                onMediaGroupsDeleted.onMediaGroupsDeleted();
            }
        }
    }

    public void onFoldersAdded() {
        synchronized (this.mFoldersCbs) {
            for (FoldersCb onFoldersAdded : this.mFoldersCbs) {
                onFoldersAdded.onFoldersAdded();
            }
        }
    }

    public void onFoldersModified() {
        synchronized (this.mFoldersCbs) {
            for (FoldersCb onFoldersModified : this.mFoldersCbs) {
                onFoldersModified.onFoldersModified();
            }
        }
    }

    public void onFoldersDeleted() {
        synchronized (this.mFoldersCbs) {
            for (FoldersCb onFoldersDeleted : this.mFoldersCbs) {
                onFoldersDeleted.onFoldersDeleted();
            }
        }
    }

    public void onDiscoveryStarted() {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onDiscoveryStarted : this.devicesDiscoveryCbList) {
                    onDiscoveryStarted.onDiscoveryStarted();
                }
            }
        }
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onDiscoveryStarted2 : this.rootsEventsCbList) {
                    onDiscoveryStarted2.onDiscoveryStarted();
                }
            }
        }
    }

    public void onDiscoveryProgress(String str) {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onDiscoveryProgress : this.devicesDiscoveryCbList) {
                    onDiscoveryProgress.onDiscoveryProgress(str);
                }
            }
        }
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onDiscoveryProgress2 : this.rootsEventsCbList) {
                    onDiscoveryProgress2.onDiscoveryProgress(str);
                }
            }
        }
    }

    public void onDiscoveryCompleted() {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onDiscoveryCompleted : this.devicesDiscoveryCbList) {
                    onDiscoveryCompleted.onDiscoveryCompleted();
                }
            }
        }
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onDiscoveryCompleted2 : this.rootsEventsCbList) {
                    onDiscoveryCompleted2.onDiscoveryCompleted();
                }
            }
        }
    }

    public void onDiscoveryFailed(String str) {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onDiscoveryFailed : this.devicesDiscoveryCbList) {
                    onDiscoveryFailed.onDiscoveryFailed(str);
                }
            }
        }
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onDiscoveryFailed2 : this.rootsEventsCbList) {
                    onDiscoveryFailed2.onDiscoveryFailed(str);
                }
            }
        }
    }

    public void onParsingStatsUpdated(int i, int i2) {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onParsingStatsUpdated : this.devicesDiscoveryCbList) {
                    onParsingStatsUpdated.onParsingStatsUpdated(i, i2);
                }
            }
        }
    }

    public void onBackgroundTasksIdleChanged(boolean z) {
        this.mIsWorking = !z;
        sRunning.postValue(Boolean.valueOf(this.mIsWorking));
        if (z) {
            synchronized (this.onMedialibraryReadyListeners) {
                for (OnMedialibraryReadyListener onMedialibraryIdle : this.onMedialibraryReadyListeners) {
                    onMedialibraryIdle.onMedialibraryIdle();
                }
            }
        }
    }

    public void onUnhandledException(String str, String str2, boolean z) {
        MedialibraryExceptionHandler medialibraryExceptionHandler = this.mExceptionHandler;
        if (medialibraryExceptionHandler != null) {
            medialibraryExceptionHandler.onUnhandledException(str, str2, z);
        }
    }

    public void onReloadStarted(String str) {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onReloadStarted : this.devicesDiscoveryCbList) {
                    onReloadStarted.onReloadStarted(str);
                }
            }
        }
    }

    public void onReloadCompleted(String str) {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.isEmpty()) {
                for (DevicesDiscoveryCb onReloadCompleted : this.devicesDiscoveryCbList) {
                    onReloadCompleted.onReloadCompleted(str);
                }
            }
        }
    }

    public void onRootBanned(String str, boolean z) {
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onRootBanned : this.rootsEventsCbList) {
                    onRootBanned.onRootBanned(str, z);
                }
            }
        }
    }

    public void onRootUnbanned(String str, boolean z) {
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onRootUnbanned : this.rootsEventsCbList) {
                    onRootUnbanned.onRootUnbanned(str, z);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onRootAdded(String str, boolean z) {
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onRootAdded : this.rootsEventsCbList) {
                    onRootAdded.onRootAdded(str, z);
                }
            }
        }
    }

    public void onRootRemoved(String str, boolean z) {
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.isEmpty()) {
                for (RootsEventsCb onRootRemoved : this.rootsEventsCbList) {
                    onRootRemoved.onRootRemoved(str, z);
                }
            }
        }
    }

    public void onMediaThumbnailReady(MediaWrapper mediaWrapper, boolean z) {
        if (z) {
            ((MutableLiveData) EventTools.getInstance().lastThumb).postValue(mediaWrapper);
        }
    }

    public void addMediaCb(MediaCb mediaCb) {
        synchronized (this.mMediaCbs) {
            this.mMediaCbs.add(mediaCb);
        }
    }

    public void removeMediaCb(MediaCb mediaCb) {
        synchronized (this.mMediaCbs) {
            this.mMediaCbs.remove(mediaCb);
        }
    }

    public void addArtistsCb(ArtistsCb artistsCb) {
        synchronized (this.mArtistsCbs) {
            this.mArtistsCbs.add(artistsCb);
        }
    }

    public void removeArtistsCb(ArtistsCb artistsCb) {
        synchronized (this.mArtistsCbs) {
            this.mArtistsCbs.remove(artistsCb);
        }
    }

    public void addAlbumsCb(AlbumsCb albumsCb) {
        synchronized (this.mAlbumsCbs) {
            this.mAlbumsCbs.add(albumsCb);
        }
    }

    public void removeAlbumsCb(AlbumsCb albumsCb) {
        synchronized (this.mAlbumsCbs) {
            this.mAlbumsCbs.remove(albumsCb);
        }
    }

    public void addGenreCb(GenresCb genresCb) {
        synchronized (this.mGenreCbs) {
            this.mGenreCbs.add(genresCb);
        }
    }

    public void removeGenreCb(GenresCb genresCb) {
        synchronized (this.mGenreCbs) {
            this.mGenreCbs.remove(genresCb);
        }
    }

    public void addPlaylistCb(PlaylistsCb playlistsCb) {
        synchronized (this.mPlaylistCbs) {
            this.mPlaylistCbs.add(playlistsCb);
        }
    }

    public void removePlaylistCb(PlaylistsCb playlistsCb) {
        synchronized (this.mPlaylistCbs) {
            this.mPlaylistCbs.remove(playlistsCb);
        }
    }

    public void addHistoryCb(HistoryCb historyCb) {
        synchronized (this.mHistoryCbs) {
            this.mHistoryCbs.add(historyCb);
        }
    }

    public void removeHistoryCb(HistoryCb historyCb) {
        synchronized (this.mHistoryCbs) {
            this.mHistoryCbs.remove(historyCb);
        }
    }

    public void addMediaGroupCb(MediaGroupCb mediaGroupCb) {
        synchronized (this.mMediaGroupCbs) {
            this.mMediaGroupCbs.add(mediaGroupCb);
        }
    }

    public void removeMediaGroupCb(MediaGroupCb mediaGroupCb) {
        synchronized (this.mMediaGroupCbs) {
            this.mMediaGroupCbs.remove(mediaGroupCb);
        }
    }

    public void addFoldersCb(FoldersCb foldersCb) {
        synchronized (this.mFoldersCbs) {
            this.mFoldersCbs.add(foldersCb);
        }
    }

    public void removeFoldersCb(FoldersCb foldersCb) {
        synchronized (this.mFoldersCbs) {
            this.mFoldersCbs.remove(foldersCb);
        }
    }

    public void addDeviceDiscoveryCb(DevicesDiscoveryCb devicesDiscoveryCb) {
        synchronized (this.devicesDiscoveryCbList) {
            if (!this.devicesDiscoveryCbList.contains(devicesDiscoveryCb)) {
                this.devicesDiscoveryCbList.add(devicesDiscoveryCb);
            }
        }
    }

    public void removeDeviceDiscoveryCb(DevicesDiscoveryCb devicesDiscoveryCb) {
        synchronized (this.devicesDiscoveryCbList) {
            this.devicesDiscoveryCbList.remove(devicesDiscoveryCb);
        }
    }

    public void addOnMedialibraryReadyListener(OnMedialibraryReadyListener onMedialibraryReadyListener) {
        synchronized (this.onMedialibraryReadyListeners) {
            if (!this.onMedialibraryReadyListeners.contains(onMedialibraryReadyListener)) {
                this.onMedialibraryReadyListeners.add(onMedialibraryReadyListener);
            }
        }
    }

    public void removeOnMedialibraryReadyListener(OnMedialibraryReadyListener onMedialibraryReadyListener) {
        synchronized (this.onMedialibraryReadyListeners) {
            this.onMedialibraryReadyListeners.remove(onMedialibraryReadyListener);
        }
    }

    public void addRootsEventsCb(RootsEventsCb rootsEventsCb) {
        synchronized (this.rootsEventsCbList) {
            if (!this.rootsEventsCbList.contains(rootsEventsCb)) {
                this.rootsEventsCbList.add(rootsEventsCb);
            }
        }
    }

    public void removeRootsEventsCb(RootsEventsCb rootsEventsCb) {
        synchronized (this.rootsEventsCbList) {
            this.rootsEventsCbList.remove(rootsEventsCb);
        }
    }

    public void addOnDeviceChangeListener(OnDeviceChangeListener onDeviceChangeListener) {
        synchronized (this.onDeviceChangeListeners) {
            if (!this.onDeviceChangeListeners.contains(onDeviceChangeListener)) {
                this.onDeviceChangeListeners.add(onDeviceChangeListener);
            }
        }
    }

    public void removeOnDeviceChangeListener(OnDeviceChangeListener onDeviceChangeListener) {
        synchronized (this.onDeviceChangeListeners) {
            this.onDeviceChangeListeners.remove(onDeviceChangeListener);
        }
    }
}
