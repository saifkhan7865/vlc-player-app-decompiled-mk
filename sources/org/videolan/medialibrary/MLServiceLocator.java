package org.videolan.medialibrary;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.AlbumImpl;
import org.videolan.medialibrary.media.ArtistImpl;
import org.videolan.medialibrary.media.BookmarkImpl;
import org.videolan.medialibrary.media.FolderImpl;
import org.videolan.medialibrary.media.GenreImpl;
import org.videolan.medialibrary.media.MediaWrapperImpl;
import org.videolan.medialibrary.media.PlaylistImpl;
import org.videolan.medialibrary.media.VideoGroupImpl;
import org.videolan.medialibrary.stubs.StubAlbum;
import org.videolan.medialibrary.stubs.StubArtist;
import org.videolan.medialibrary.stubs.StubBookmark;
import org.videolan.medialibrary.stubs.StubFolder;
import org.videolan.medialibrary.stubs.StubGenre;
import org.videolan.medialibrary.stubs.StubMediaWrapper;
import org.videolan.medialibrary.stubs.StubMedialibrary;
import org.videolan.medialibrary.stubs.StubPlaylist;
import org.videolan.medialibrary.stubs.StubVideoGroup;

public class MLServiceLocator {
    public static String EXTRA_TEST_STUBS = "extra_test_stubs";
    private static volatile Medialibrary instance;
    private static LocatorMode sMode = LocatorMode.VLC_ANDROID;

    public enum LocatorMode {
        VLC_ANDROID,
        TESTS
    }

    public static void setLocatorMode(LocatorMode locatorMode) {
        if (instance == null || locatorMode == sMode) {
            sMode = locatorMode;
            return;
        }
        throw new IllegalStateException("LocatorMode must be set before Medialibrary initialization");
    }

    public static LocatorMode getLocatorMode() {
        return sMode;
    }

    public static synchronized Medialibrary getAbstractMedialibrary() {
        Medialibrary medialibrary;
        synchronized (MLServiceLocator.class) {
            if (instance == null) {
                instance = sMode == LocatorMode.VLC_ANDROID ? new MedialibraryImpl() : new StubMedialibrary();
            }
            medialibrary = instance;
        }
        return medialibrary;
    }

    public static MediaWrapper getAbstractMediaWrapper(long j, String str, long j2, float f, long j3, int i, String str2, String str3, String str4, String str5, String str6, String str7, int i2, int i3, String str8, int i4, int i5, int i6, int i7, long j4, long j5, boolean z, boolean z2, int i8, boolean z3, long j6) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new MediaWrapperImpl(j, str, j2, f, j3, i, str2, str3, str4, str5, str6, str7, i2, i3, str8, i4, i5, i6, i7, j4, j5, z, z2, i8, z3, j6);
        }
        return new StubMediaWrapper(j, str, j2, f, j3, i, str2, str3, str4, str5, str6, str7, i2, i3, str8, i4, i5, i6, i7, j4, j5, z, z2, i8, z3, j6);
    }

    public static MediaWrapper getAbstractMediaWrapper(Uri uri, long j, float f, long j2, int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5, int i2, int i3, String str6, int i4, int i5, int i6, int i7, long j3, long j4, long j5) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new MediaWrapperImpl(uri, j, f, j2, i, bitmap, str, str2, str3, str4, str5, i2, i3, str6, i4, i5, i6, i7, j3, j4, false, j5);
        }
        return new StubMediaWrapper(uri, j, f, j2, i, bitmap, str, str2, str3, str4, str5, i2, i3, str6, i4, i5, i6, i7, j3, j4, false, j5);
    }

    public static MediaWrapper getAbstractMediaWrapper(Uri uri) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new MediaWrapperImpl(uri);
        }
        return new StubMediaWrapper(uri);
    }

    public static MediaWrapper getAbstractMediaWrapper(IMedia iMedia) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new MediaWrapperImpl(iMedia);
        }
        return new StubMediaWrapper(iMedia);
    }

    public static MediaWrapper getAbstractMediaWrapper(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new MediaWrapperImpl(parcel);
        }
        return new StubMediaWrapper(parcel);
    }

    public static Artist getAbstractArtist(long j, String str, String str2, String str3, String str4, int i, int i2, int i3, boolean z) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new ArtistImpl(j, str, str2, str3, str4, i, i2, i3, z);
        }
        return new StubArtist(j, str, str2, str3, str4, i, i2, i3, z);
    }

    public static Artist getAbstractArtist(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new ArtistImpl(parcel);
        }
        return new StubArtist(parcel);
    }

    public static Genre getAbstractGenre(long j, String str, boolean z) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new GenreImpl(j, str, 0, 0, z);
        }
        return new StubGenre(j, str, 0, 0, z);
    }

    public static Genre getAbstractGenre(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new GenreImpl(parcel);
        }
        return new StubGenre(parcel);
    }

    public static Album getAbstractAlbum(long j, String str, int i, String str2, String str3, long j2, int i2, int i3, long j3, boolean z) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new AlbumImpl(j, str, i, str2, str3, j2, i2, i3, j3, z);
        }
        return new StubAlbum(j, str, i, str2, str3, j2, i2, i3, j3, z);
    }

    public static Album getAbstractAlbum(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new AlbumImpl(parcel);
        }
        return new StubAlbum(parcel);
    }

    public static Folder getAbstractFolder(long j, String str, String str2, int i, boolean z) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new FolderImpl(j, str, str2, i, z);
        }
        return new StubFolder(j, str, str2, i, z);
    }

    public static Folder getAbstractFolder(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new FolderImpl(parcel);
        }
        return new StubFolder(parcel);
    }

    public Bookmark getAbstractBookmark(long j, String str, String str2, long j2, long j3) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new BookmarkImpl(j, str, str2, j2, j3);
        }
        return new StubBookmark(j, str, str2, j2, j3);
    }

    public static Bookmark getAbstractBookmark(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new BookmarkImpl(parcel);
        }
        return new StubBookmark(parcel);
    }

    public static VideoGroup getAbstractVideoGroup(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new VideoGroupImpl(parcel);
        }
        return new StubVideoGroup(parcel);
    }

    public static Playlist getAbstractPlaylist(long j, String str, int i, long j2, int i2, int i3, int i4, int i5, boolean z) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new PlaylistImpl(j, str, i, j2, i2, i3, i4, i5, z);
        }
        return new StubPlaylist(j, str, i, j2, i2, i3, i4, i5, z);
    }

    public static Playlist getAbstractPlaylist(Parcel parcel) {
        if (sMode == LocatorMode.VLC_ANDROID) {
            return new PlaylistImpl(parcel);
        }
        return new StubPlaylist(parcel);
    }
}
