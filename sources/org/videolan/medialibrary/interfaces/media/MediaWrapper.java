package org.videolan.medialibrary.interfaces.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.List;
import java.util.Locale;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.util.Extensions;
import org.videolan.libvlc.util.VLCUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.VlcMigrationHelper;

public abstract class MediaWrapper extends MediaLibraryItem implements Parcelable {
    public static final Parcelable.Creator<MediaWrapper> CREATOR = new Parcelable.Creator<MediaWrapper>() {
        public MediaWrapper createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractMediaWrapper(parcel);
        }

        public MediaWrapper[] newArray(int i) {
            return new MediaWrapper[i];
        }
    };
    public static final int MEDIA_BENCHMARK = 16;
    public static final int MEDIA_FORCE_AUDIO = 8;
    public static final int MEDIA_FROM_START = 32;
    public static final int MEDIA_NO_HWACCEL = 2;
    public static final int MEDIA_PAUSED = 4;
    public static final int MEDIA_VIDEO = 1;
    public static final int META_AB_REPEAT_START = 106;
    public static final int META_AB_REPEAT_STOP = 107;
    public static final int META_APPLICATION_SPECIFIC = 250;
    public static final int META_ASPECT_RATIO = 101;
    public static final int META_AUDIODELAY = 152;
    public static final int META_AUDIOTRACK = 150;
    public static final int META_CHAPTER = 53;
    public static final int META_CROP = 103;
    public static final int META_DEINTERLACE = 104;
    public static final int META_GAIN = 151;
    public static final int META_METADATA_RETRIEVED = 251;
    public static final int META_PROGRAM = 54;
    public static final int META_RATING = 1;
    public static final int META_SPEED = 51;
    public static final int META_SUBTITLE_DELAY = 201;
    public static final int META_SUBTITLE_TRACK = 200;
    public static final int META_TITLE = 52;
    public static final int META_VIDEOFILTER = 105;
    public static final int META_VIDEOTRACK = 100;
    public static final int META_ZOOM = 102;
    protected static final long PODCAST_ABSOLUTE = 3600000;
    protected static final long PODCAST_THRESHOLD = 900000;
    public static final int TYPE_ALL = -1;
    public static final int TYPE_AUDIO = 1;
    public static final int TYPE_DIR = 3;
    public static final int TYPE_GROUP = 2;
    public static final int TYPE_PLAYLIST = 5;
    public static final int TYPE_STREAM = 6;
    public static final int TYPE_SUBTITLE = 4;
    public static final int TYPE_VIDEO = 0;
    protected String mAlbum;
    protected String mAlbumArtist;
    protected String mArtist;
    protected String mArtworkURL;
    protected int mAudioTrack = -2;
    protected String mCopyright;
    protected String mDate;
    protected int mDiscNumber;
    protected long mDisplayTime = 0;
    protected String mDisplayTitle;
    protected String mEncodedBy;
    protected String mFilename;
    protected int mFlags = 0;
    protected String mGenre;
    protected int mHeight = 0;
    protected long mInsertionDate;
    protected boolean mIsPictureParsed;
    private boolean mIsPresent = true;
    protected long mLastModified = 0;
    protected long mLength = 0;
    protected String mNowPlaying;
    protected Bitmap mPicture;
    protected float mPosition = -1.0f;
    protected String mPublisher;
    protected String mRating;
    protected int mReleaseYear;
    protected long mSeen = 0;
    protected String mSettings;
    protected IMedia.Slave[] mSlaves = null;
    protected int mSpuTrack = -2;
    protected boolean mThumbnailGenerated;
    protected long mTime = -1;
    protected String mTrackID;
    protected int mTrackNumber;
    protected int mType;
    protected final Uri mUri;
    protected int mWidth = 0;

    public abstract Bookmark addBookmark(long j);

    public int describeContents() {
        return 0;
    }

    public abstract Bookmark[] getBookmarks();

    public int getItemType() {
        return 32;
    }

    public abstract long getMetaLong(int i);

    public abstract String getMetaString(int i);

    public abstract long getPlayCount();

    public int getTracksCount() {
        return 1;
    }

    public abstract boolean markAsPlayed();

    public abstract boolean removeAllBookmarks();

    public abstract boolean removeBookmark(long j);

    public abstract boolean removeFromHistory();

    public abstract void removeThumbnail();

    public abstract void rename(String str);

    public abstract void requestBanner(int i, float f);

    public abstract void requestThumbnail(int i, float f);

    public abstract boolean setLongMeta(int i, long j);

    public abstract boolean setPlayCount(long j);

    public abstract boolean setStringMeta(int i, String str);

    public abstract void setThumbnail(String str);

    public MediaWrapper(long j, String str, long j2, float f, long j3, int i, String str2, String str3, String str4, String str5, String str6, String str7, int i2, int i3, String str8, int i4, int i5, int i6, int i7, long j4, long j5, boolean z, boolean z2, int i8, boolean z3, long j6) {
        MediaWrapper mediaWrapper;
        int i9 = i;
        if (!TextUtils.isEmpty(str)) {
            this.mUri = Uri.parse(manageVLCMrl(str));
            this.mId = j;
            this.mFilename = str3;
            this.mReleaseYear = i8;
            this.mIsPresent = z3;
            init(j2, f, j3, i, (Bitmap) null, str2, str4, str5, str6, str7, i2, i3, str8 != null ? VLCUtil.UriFromMrl(str8).getPath() : null, i4, i5, i6, i7, j4, j5, z3, (IMedia.Slave[]) null, z2, j6);
            StringBuilder sb = new StringBuilder();
            int i10 = i;
            if (i10 == 1) {
                boolean z4 = !TextUtils.isEmpty(str4);
                boolean isEmpty = true ^ TextUtils.isEmpty(str6);
                if (z4) {
                    sb.append(str4);
                    if (isEmpty) {
                        sb.append(" - ");
                    }
                }
                if (isEmpty) {
                    sb.append(str6);
                }
            } else if (i10 == 0) {
                Tools.setMediaDescription(this);
            }
            if (sb.length() > 0) {
                mediaWrapper = this;
                mediaWrapper.mDescription = sb.toString();
            } else {
                mediaWrapper = this;
            }
            defineType();
            mediaWrapper.mThumbnailGenerated = z;
            mediaWrapper.mFavorite = z2;
            return;
        }
        throw new IllegalArgumentException("uri was empty");
    }

    private String manageVLCMrl(String str) {
        if (!str.isEmpty() && str.charAt(0) == '/') {
            return "file://" + str;
        } else if (!str.toLowerCase().startsWith("vlc://")) {
            return str;
        } else {
            String substring = str.substring(6);
            if (Uri.parse(substring).getScheme() != null) {
                return substring;
            }
            return "http://" + substring;
        }
    }

    public MediaWrapper(Uri uri) {
        if (uri != null) {
            this.mUri = Uri.parse(manageVLCMrl(uri.toString()));
            init((IMedia) null);
            return;
        }
        throw new NullPointerException("uri was null");
    }

    public MediaWrapper(IMedia iMedia) {
        if (iMedia != null) {
            this.mUri = Uri.parse(VLCUtil.encodeVLCUri(iMedia.getUri()));
            init(iMedia);
            return;
        }
        throw new NullPointerException("media was null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLibraryItem) || ((MediaLibraryItem) obj).getItemType() != 32) {
            return false;
        }
        return equals((MediaWrapper) obj);
    }

    public boolean equals(MediaWrapper mediaWrapper) {
        long id = mediaWrapper.getId();
        if (id != 0 && getId() != 0 && id == getId()) {
            return true;
        }
        Uri uri = mediaWrapper.getUri();
        Uri uri2 = this.mUri;
        if (uri2 == null || uri == null || (uri2 != uri && !uri2.equals(uri))) {
            return false;
        }
        return true;
    }

    private void init(IMedia iMedia) {
        this.mType = -1;
        if (iMedia != null) {
            if (iMedia.isParsed()) {
                this.mLength = iMedia.getDuration();
                List<IMedia.Track> mediaTracks = VlcMigrationHelper.getMediaTracks(iMedia);
                for (int i = 0; i < mediaTracks.size(); i++) {
                    IMedia.Track track = mediaTracks.get(i);
                    if (track.type == 1) {
                        IMedia.VideoTrack videoTrack = (IMedia.VideoTrack) track;
                        this.mType = 0;
                        this.mWidth = videoTrack.width;
                        this.mHeight = videoTrack.height;
                    } else if (this.mType == -1 && track.type == 0) {
                        this.mType = 1;
                    }
                }
            }
            updateMeta(iMedia);
            if (this.mType == -1) {
                int type = iMedia.getType();
                if (type == 2) {
                    this.mType = 3;
                } else if (type == 5) {
                    this.mType = 5;
                }
            }
            this.mSlaves = iMedia.getSlaves();
        }
        defineType();
    }

    private void defineType() {
        if (this.mType == -1) {
            String lastPathSegment = this.mUri.getLastPathSegment();
            if (TextUtils.isEmpty(lastPathSegment)) {
                lastPathSegment = this.mTitle;
            }
            if (!TextUtils.isEmpty(lastPathSegment)) {
                int indexOf = lastPathSegment.indexOf(63);
                if (indexOf != -1) {
                    lastPathSegment = lastPathSegment.substring(0, indexOf);
                }
                int lastIndexOf = lastPathSegment.lastIndexOf(".");
                String lowerCase = lastIndexOf != -1 ? lastPathSegment.substring(lastIndexOf).toLowerCase(Locale.ENGLISH) : null;
                if (TextUtils.isEmpty(lowerCase)) {
                    return;
                }
                if (Extensions.VIDEO.contains(lowerCase)) {
                    this.mType = 0;
                } else if (Extensions.AUDIO.contains(lowerCase)) {
                    this.mType = 1;
                } else if (Extensions.SUBTITLES.contains(lowerCase)) {
                    this.mType = 4;
                } else if (Extensions.PLAYLIST.contains(lowerCase)) {
                    this.mType = 5;
                }
            }
        }
    }

    private void init(long j, float f, long j2, int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5, int i2, int i3, String str6, int i4, int i5, int i6, int i7, long j3, long j4, boolean z, IMedia.Slave[] slaveArr, boolean z2, long j5) {
        long j6 = j;
        String str7 = null;
        this.mFilename = null;
        this.mTime = j6;
        this.mPosition = f;
        this.mDisplayTime = j6;
        this.mAudioTrack = i4;
        this.mSpuTrack = i5;
        this.mLength = j2;
        this.mType = i;
        this.mPicture = bitmap;
        this.mWidth = i2;
        this.mHeight = i3;
        this.mInsertionDate = j5;
        this.mTitle = str != null ? str.trim() : null;
        this.mArtist = str2 != null ? str2.trim() : null;
        this.mGenre = str3 != null ? str3.trim() : null;
        this.mAlbum = str4 != null ? str4.trim() : null;
        if (str5 != null) {
            str7 = str5.trim();
        }
        this.mAlbumArtist = str7;
        this.mArtworkURL = str6;
        this.mTrackNumber = i6;
        this.mDiscNumber = i7;
        this.mLastModified = j3;
        this.mSeen = j4;
        this.mSlaves = slaveArr;
        this.mIsPresent = z;
        this.mFavorite = z2;
    }

    public MediaWrapper(Uri uri, long j, float f, long j2, int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5, int i2, int i3, String str6, int i4, int i5, int i6, int i7, long j3, long j4, boolean z, long j5) {
        long j6 = j;
        float f2 = f;
        this.mUri = uri;
        init(j, f, j2, i, bitmap, str, str2, str3, str4, str5, i2, i3, str6, i4, i5, i6, i7, j3, j4, true, (IMedia.Slave[]) null, z, j5);
    }

    public MediaWrapper[] getTracks() {
        return new MediaWrapper[]{this};
    }

    public long getId() {
        return this.mId;
    }

    public String getLocation() {
        return this.mUri.toString();
    }

    public Uri getUri() {
        return this.mUri;
    }

    private static String getMetaId(IMedia iMedia, String str, int i, boolean z) {
        String meta = iMedia.getMeta(i, true);
        if (meta != null) {
            return z ? meta.trim() : meta;
        }
        return str;
    }

    private String getMetaTitle(IMedia iMedia) {
        String metaId = getMetaId(iMedia, this.mTitle, 0, true);
        return (iMedia.getType() == 2 || (!TextUtils.isEmpty(metaId) && !metaId.equals(getFileName()) && (iMedia.getType() != 4 || !metaId.toLowerCase().contains("://")))) ? metaId : getTitle();
    }

    private void updateMeta(IMedia iMedia) {
        this.mTitle = getMetaTitle(iMedia);
        this.mArtist = getMetaId(iMedia, this.mArtist, 1, true);
        this.mAlbum = getMetaId(iMedia, this.mAlbum, 4, true);
        this.mGenre = getMetaId(iMedia, this.mGenre, 2, true);
        this.mAlbumArtist = getMetaId(iMedia, this.mAlbumArtist, 23, true);
        this.mArtworkURL = getMetaId(iMedia, this.mArtworkURL, 15, false);
        this.mNowPlaying = getMetaId(iMedia, this.mNowPlaying, 12, false);
        String metaId = getMetaId(iMedia, (String) null, 5, false);
        if (!TextUtils.isEmpty(metaId)) {
            try {
                this.mTrackNumber = Integer.parseInt(metaId);
            } catch (NumberFormatException unused) {
            }
        }
        String metaId2 = getMetaId(iMedia, (String) null, 24, false);
        if (!TextUtils.isEmpty(metaId2)) {
            try {
                this.mDiscNumber = Integer.parseInt(metaId2);
            } catch (NumberFormatException unused2) {
            }
        }
    }

    public void updateMeta(MediaPlayer mediaPlayer) {
        String str;
        if ((!TextUtils.isEmpty(this.mTitle) && TextUtils.isEmpty(this.mDisplayTitle)) || ((str = this.mDisplayTitle) != null && !str.equals(this.mTitle))) {
            this.mDisplayTitle = this.mTitle;
        }
        IMedia media = mediaPlayer.getMedia();
        if (media != null) {
            updateMeta(media);
            media.release();
        }
    }

    public String getFileName() {
        if (this.mFilename == null) {
            Uri uri = this.mUri;
            if (uri == null) {
                this.mFilename = "";
            } else if (uri.getLastPathSegment() != null) {
                this.mFilename = this.mUri.getLastPathSegment();
            } else {
                this.mFilename = this.mUri.toString();
            }
        }
        return this.mFilename;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public float getPosition() {
        return this.mPosition;
    }

    public void setPosition(float f) {
        this.mPosition = f;
    }

    public long getDisplayTime() {
        return this.mDisplayTime;
    }

    public void setDisplayTime(long j) {
        this.mDisplayTime = j;
    }

    public int getAudioTrack() {
        return this.mAudioTrack;
    }

    public void setAudioTrack(int i) {
        this.mAudioTrack = i;
    }

    public int getSpuTrack() {
        return this.mSpuTrack;
    }

    public void setSpuTrack(int i) {
        this.mSpuTrack = i;
    }

    public long getLength() {
        return this.mLength;
    }

    public void setLength(long j) {
        this.mLength = j;
    }

    public int getType() {
        return this.mType;
    }

    public boolean isPodcast() {
        if (this.mType == 1) {
            if (this.mLength > PODCAST_ABSOLUTE) {
                return true;
            }
            if ((!TextUtils.isEmpty(this.mAlbum) || this.mLength <= PODCAST_THRESHOLD) && !"podcast".equalsIgnoreCase(this.mGenre) && !"audiobooks".equalsIgnoreCase(this.mGenre) && !"audiobook".equalsIgnoreCase(this.mGenre) && !"speech".equalsIgnoreCase(this.mGenre) && !"vocal".equalsIgnoreCase(this.mGenre)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public Bitmap getPicture() {
        return this.mPicture;
    }

    public void setPicture(Bitmap bitmap) {
        this.mPicture = bitmap;
    }

    public boolean isPictureParsed() {
        return this.mIsPictureParsed;
    }

    public void setPictureParsed(boolean z) {
        this.mIsPictureParsed = z;
    }

    public void setDisplayTitle(String str) {
        this.mDisplayTitle = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public String getTitle() {
        String str = this.mDisplayTitle;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = this.mTitle;
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String fileName = getFileName();
        if (fileName == null) {
            return "";
        }
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf <= 0) {
            return fileName;
        }
        return fileName.substring(0, lastIndexOf);
    }

    public long getInsertionDate() {
        return this.mInsertionDate;
    }

    public String getReferenceArtist() {
        String str = this.mAlbumArtist;
        return str == null ? this.mArtist : str;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public Boolean isArtistUnknown() {
        return Boolean.valueOf(this.mArtist == null);
    }

    public String getGenre() {
        String str = this.mGenre;
        if (str == null) {
            return null;
        }
        if (str.length() <= 1) {
            return this.mGenre;
        }
        return Character.toUpperCase(this.mGenre.charAt(0)) + this.mGenre.substring(1).toLowerCase(Locale.getDefault());
    }

    public String getCopyright() {
        return this.mCopyright;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public String getAlbumArtist() {
        return this.mAlbumArtist;
    }

    public Boolean isAlbumUnknown() {
        return Boolean.valueOf(this.mAlbum == null);
    }

    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    public int getDiscNumber() {
        return this.mDiscNumber;
    }

    public String getRating() {
        return this.mRating;
    }

    public String getDate() {
        return this.mDate;
    }

    public int getReleaseYear() {
        return this.mReleaseYear;
    }

    public String getSettings() {
        return this.mSettings;
    }

    public String getNowPlaying() {
        return this.mNowPlaying;
    }

    public String getPublisher() {
        return this.mPublisher;
    }

    public String getEncodedBy() {
        return this.mEncodedBy;
    }

    public String getTrackID() {
        return this.mTrackID;
    }

    public String getArtworkURL() {
        return this.mArtworkURL;
    }

    public boolean isThumbnailGenerated() {
        return this.mThumbnailGenerated;
    }

    public String getArtworkMrl() {
        return this.mArtworkURL;
    }

    public void setArtworkURL(String str) {
        this.mArtworkURL = str;
    }

    public long getLastModified() {
        return this.mLastModified;
    }

    public void setLastModified(long j) {
        this.mLastModified = j;
    }

    public long getSeen() {
        return this.mSeen;
    }

    public void setSeen(long j) {
        this.mSeen = j;
    }

    public void addFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean hasFlag(int i) {
        return (i & this.mFlags) != 0;
    }

    public void removeFlags(int i) {
        this.mFlags = (i ^ -1) & this.mFlags;
    }

    public boolean isPresent() {
        return this.mIsPresent;
    }

    public IMedia.Slave[] getSlaves() {
        return this.mSlaves;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaWrapper(Parcel parcel) {
        super(parcel);
        Parcel parcel2 = parcel;
        this.mUri = (Uri) parcel2.readParcelable(Uri.class.getClassLoader());
        init(parcel.readLong(), parcel.readFloat(), parcel.readLong(), parcel.readInt(), (Bitmap) parcel2.readParcelable(Bitmap.class.getClassLoader()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt() == 1, (IMedia.Slave[]) parcel2.createTypedArray(PSlave.CREATOR), parcel.readInt() == 1, parcel.readLong());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mUri, i);
        parcel.writeLong(getTime());
        parcel.writeFloat(getPosition());
        parcel.writeLong(getLength());
        parcel.writeInt(getType());
        parcel.writeParcelable(getPicture(), i);
        parcel.writeString(getTitle());
        parcel.writeString(getArtist());
        parcel.writeString(getGenre());
        parcel.writeString(getAlbum());
        parcel.writeString(getAlbumArtist());
        parcel.writeInt(getWidth());
        parcel.writeInt(getHeight());
        parcel.writeString(getArtworkURL());
        parcel.writeInt(getAudioTrack());
        parcel.writeInt(getSpuTrack());
        parcel.writeInt(getTrackNumber());
        parcel.writeInt(getDiscNumber());
        parcel.writeLong(getLastModified());
        parcel.writeLong(getSeen());
        parcel.writeInt(isPresent() ? 1 : 0);
        IMedia.Slave[] slaveArr = this.mSlaves;
        if (slaveArr != null) {
            PSlave[] pSlaveArr = new PSlave[slaveArr.length];
            for (int i2 = 0; i2 < this.mSlaves.length; i2++) {
                pSlaveArr[i2] = new PSlave(this.mSlaves[i2]);
            }
            parcel.writeTypedArray(pSlaveArr, i);
        } else {
            parcel.writeTypedArray((Parcelable[]) null, i);
        }
        parcel.writeInt(this.mFavorite ? 1 : 0);
        parcel.writeLong(this.mInsertionDate);
    }

    protected static class PSlave extends IMedia.Slave implements Parcelable {
        public static final Parcelable.Creator<PSlave> CREATOR = new Parcelable.Creator<PSlave>() {
            public PSlave createFromParcel(Parcel parcel) {
                return new PSlave(parcel);
            }

            public PSlave[] newArray(int i) {
                return new PSlave[i];
            }
        };

        public int describeContents() {
            return 0;
        }

        PSlave(IMedia.Slave slave) {
            super(slave.type, slave.priority, slave.uri);
        }

        PSlave(Parcel parcel) {
            super(parcel.readInt(), parcel.readInt(), parcel.readString());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.type);
            parcel.writeInt(this.priority);
            parcel.writeString(this.uri);
        }
    }
}
