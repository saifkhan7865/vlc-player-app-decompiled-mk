package org.videolan.libvlc;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.FileDescriptor;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaList;
import org.videolan.libvlc.interfaces.IVLCObject;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.HWDecoderUtil;
import org.videolan.libvlc.util.VLCUtil;

public class Media extends VLCObject<IMedia.Event> implements IMedia {
    private static final int PARSE_STATUS_INIT = 0;
    private static final int PARSE_STATUS_PARSED = 2;
    private static final int PARSE_STATUS_PARSING = 1;
    private static final String TAG = "LibVLC/Media";
    private boolean mCodecOptionSet = false;
    private long mDuration = -1;
    private boolean mFileCachingSet = false;
    private final String[] mNativeMetas = new String[25];
    private IMedia.Track[] mNativeTracks = null;
    private boolean mNetworkCachingSet = false;
    private int mParseStatus = 0;
    private int mState = -1;
    private MediaList mSubItems = null;
    private int mType = -1;
    private Uri mUri = null;

    private native void nativeAddOption(String str);

    private native void nativeAddSlave(int i, int i2, String str);

    private native void nativeClearSlaves();

    private native long nativeGetDuration();

    private native String nativeGetMeta(int i);

    private native String nativeGetMrl();

    private native IMedia.Slave[] nativeGetSlaves();

    private native int nativeGetState();

    private native IMedia.Stats nativeGetStats();

    private native IMedia.Track[] nativeGetTracks();

    private native int nativeGetType();

    private native void nativeNewFromFd(ILibVLC iLibVLC, FileDescriptor fileDescriptor);

    private native void nativeNewFromFdWithOffsetLength(ILibVLC iLibVLC, FileDescriptor fileDescriptor, long j, long j2);

    private native void nativeNewFromLocation(ILibVLC iLibVLC, String str);

    private native void nativeNewFromMediaList(IMediaList iMediaList, int i);

    private native void nativeNewFromPath(ILibVLC iLibVLC, String str);

    private native boolean nativeParse(int i);

    private native boolean nativeParseAsync(int i, int i2);

    private native void nativeRelease();

    public /* bridge */ /* synthetic */ long getInstance() {
        return super.getInstance();
    }

    public /* bridge */ /* synthetic */ ILibVLC getLibVLC() {
        return super.getLibVLC();
    }

    public /* bridge */ /* synthetic */ boolean isReleased() {
        return super.isReleased();
    }

    private static IMedia.Track createAudioTrackFromNative(String str, String str2, int i, int i2, int i3, int i4, int i5, String str3, String str4, int i6, int i7) {
        return new IMedia.AudioTrack(str, str2, i, i2, i3, i4, i5, str3, str4, i6, i7);
    }

    private static IMedia.Track createVideoTrackFromNative(String str, String str2, int i, int i2, int i3, int i4, int i5, String str3, String str4, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
        return new IMedia.VideoTrack(str, str2, i, i2, i3, i4, i5, str3, str4, i6, i7, i8, i9, i10, i11, i12, i13);
    }

    private static IMedia.Track createSubtitleTrackFromNative(String str, String str2, int i, int i2, int i3, int i4, int i5, String str3, String str4, String str5) {
        return new IMedia.SubtitleTrack(str, str2, i, i2, i3, i4, i5, str3, str4, str5);
    }

    private static IMedia.Track createUnknownTrackFromNative(String str, String str2, int i, int i2, int i3, int i4, int i5, String str3, String str4) {
        return new IMedia.UnknownTrack(str, str2, i, i2, i3, i4, i5, str3, str4);
    }

    private static IMedia.Slave createSlaveFromNative(int i, int i2, String str) {
        return new IMedia.Slave(i, i2, str);
    }

    private static IMedia.Stats createStatsFromNative(int i, float f, int i2, float f2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, float f3) {
        return new IMedia.Stats(i, f, i2, f2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, f3);
    }

    public Media(ILibVLC iLibVLC, String str) {
        super(iLibVLC);
        nativeNewFromPath(iLibVLC, str);
        this.mUri = VLCUtil.UriFromMrl(nativeGetMrl());
    }

    public Media(ILibVLC iLibVLC, Uri uri) {
        super(iLibVLC);
        nativeNewFromLocation(iLibVLC, VLCUtil.encodeVLCUri(uri));
        this.mUri = uri;
    }

    public Media(ILibVLC iLibVLC, FileDescriptor fileDescriptor) {
        super(iLibVLC);
        nativeNewFromFd(iLibVLC, fileDescriptor);
        this.mUri = VLCUtil.UriFromMrl(nativeGetMrl());
    }

    public Media(ILibVLC iLibVLC, AssetFileDescriptor assetFileDescriptor) {
        super(iLibVLC);
        ILibVLC iLibVLC2 = iLibVLC;
        nativeNewFromFdWithOffsetLength(iLibVLC2, assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        this.mUri = VLCUtil.UriFromMrl(nativeGetMrl());
    }

    protected Media(IMediaList iMediaList, int i) {
        super((IVLCObject) iMediaList);
        if (iMediaList == null || iMediaList.isReleased()) {
            throw new IllegalArgumentException("MediaList is null or released");
        } else if (iMediaList.isLocked()) {
            nativeNewFromMediaList(iMediaList, i);
            this.mUri = VLCUtil.UriFromMrl(nativeGetMrl());
        } else {
            throw new IllegalStateException("MediaList should be locked");
        }
    }

    public void setEventListener(IMedia.EventListener eventListener) {
        super.setEventListener(eventListener);
    }

    /* access modifiers changed from: protected */
    public synchronized IMedia.Event onEventNative(int i, long j, long j2, float f, String str) {
        if (i != 0) {
            if (i == 5) {
                this.mState = -1;
            } else if (i == 2) {
                this.mDuration = -1;
            } else if (i == 3) {
                postParse();
                return new IMedia.Event(i, j);
            }
            return new IMedia.Event(i);
        }
        int i2 = (int) j;
        if (i2 >= 0 && i2 < 25) {
            this.mNativeMetas[i2] = null;
        }
        return new IMedia.Event(i, j);
    }

    public synchronized Uri getUri() {
        return this.mUri;
    }

    public long getDuration() {
        synchronized (this) {
            long j = this.mDuration;
            if (j != -1) {
                return j;
            }
            if (isReleased()) {
                return 0;
            }
            long nativeGetDuration = nativeGetDuration();
            synchronized (this) {
                this.mDuration = nativeGetDuration;
            }
            return nativeGetDuration;
        }
    }

    public int getState() {
        synchronized (this) {
            int i = this.mState;
            if (i != -1) {
                return i;
            }
            if (isReleased()) {
                return 7;
            }
            int nativeGetState = nativeGetState();
            synchronized (this) {
                this.mState = nativeGetState;
            }
            return nativeGetState;
        }
    }

    public MediaList subItems() {
        MediaList mediaList;
        synchronized (this) {
            MediaList mediaList2 = this.mSubItems;
            if (mediaList2 != null) {
                mediaList2.retain();
                MediaList mediaList3 = this.mSubItems;
                return mediaList3;
            }
            MediaList mediaList4 = new MediaList((IMedia) this);
            synchronized (this) {
                this.mSubItems = mediaList4;
                mediaList4.retain();
                mediaList = this.mSubItems;
            }
            return mediaList;
        }
    }

    private synchronized void postParse() {
        int i = this.mParseStatus;
        if ((i & 2) == 0) {
            this.mParseStatus = (i & -2) | 2;
            this.mNativeTracks = null;
            this.mDuration = -1;
            this.mState = -1;
            this.mType = -1;
        }
    }

    public boolean parse(int i) {
        boolean z;
        synchronized (this) {
            int i2 = this.mParseStatus;
            if ((i2 & 3) == 0) {
                this.mParseStatus = i2 | 1;
                z = true;
            } else {
                z = false;
            }
        }
        if (!z || !nativeParse(i)) {
            return false;
        }
        postParse();
        return true;
    }

    public boolean parse() {
        return parse(2);
    }

    public boolean parseAsync(int i, int i2) {
        boolean z;
        synchronized (this) {
            int i3 = this.mParseStatus;
            if ((i3 & 3) == 0) {
                this.mParseStatus = i3 | 1;
                z = true;
            } else {
                z = false;
            }
        }
        if (!z || !nativeParseAsync(i, i2)) {
            return false;
        }
        return true;
    }

    public boolean parseAsync(int i) {
        return parseAsync(i, -1);
    }

    public boolean parseAsync() {
        return parseAsync(2);
    }

    public synchronized boolean isParsed() {
        return (this.mParseStatus & 2) != 0;
    }

    public int getType() {
        synchronized (this) {
            int i = this.mType;
            if (i != -1) {
                return i;
            }
            if (isReleased()) {
                return 0;
            }
            int nativeGetType = nativeGetType();
            synchronized (this) {
                this.mType = nativeGetType;
            }
            return nativeGetType;
        }
    }

    private IMedia.Track[] getTracks() {
        synchronized (this) {
            IMedia.Track[] trackArr = this.mNativeTracks;
            if (trackArr != null) {
                return trackArr;
            }
            if (isReleased()) {
                return null;
            }
            IMedia.Track[] nativeGetTracks = nativeGetTracks();
            synchronized (this) {
                this.mNativeTracks = nativeGetTracks;
            }
            return nativeGetTracks;
        }
    }

    public int getTrackCount() {
        IMedia.Track[] tracks = getTracks();
        if (tracks != null) {
            return tracks.length;
        }
        return 0;
    }

    public IMedia.Track getTrack(int i) {
        IMedia.Track[] tracks = getTracks();
        if (tracks == null || i < 0 || i >= tracks.length) {
            return null;
        }
        return tracks[i];
    }

    public String getMeta(int i) {
        return getMeta(i, false);
    }

    public String getMeta(int i, boolean z) {
        if (i < 0 || i >= 25) {
            return null;
        }
        if (!z) {
            synchronized (this) {
                String str = this.mNativeMetas[i];
                if (str != null) {
                    return str;
                }
                if (isReleased()) {
                    return null;
                }
            }
        }
        String nativeGetMeta = nativeGetMeta(i);
        synchronized (this) {
            this.mNativeMetas[i] = nativeGetMeta;
        }
        return nativeGetMeta;
    }

    private static String getMediaCodecModule() {
        return AndroidUtil.isLolliPopOrLater ? "mediacodec_ndk" : "mediacodec_jni";
    }

    public void setHWDecoderEnabled(boolean z, boolean z2) {
        HWDecoderUtil.Decoder decoder;
        if (LibVLC.majorVersion() == 3) {
            if (z) {
                decoder = HWDecoderUtil.getDecoderFromDevice();
            } else {
                decoder = HWDecoderUtil.Decoder.NONE;
            }
            if (decoder == HWDecoderUtil.Decoder.UNKNOWN && z2) {
                decoder = HWDecoderUtil.Decoder.ALL;
            }
            if (decoder == HWDecoderUtil.Decoder.NONE || decoder == HWDecoderUtil.Decoder.UNKNOWN) {
                addOption(":codec=all");
                return;
            }
            if (!this.mFileCachingSet) {
                addOption(":file-caching=1500");
            }
            if (!this.mNetworkCachingSet) {
                addOption(":network-caching=1500");
            }
            StringBuilder sb = new StringBuilder(":codec=");
            if (decoder == HWDecoderUtil.Decoder.MEDIACODEC || decoder == HWDecoderUtil.Decoder.ALL) {
                sb.append(getMediaCodecModule());
                sb.append(AnsiRenderer.CODE_LIST_SEPARATOR);
            }
            if (z2 && (decoder == HWDecoderUtil.Decoder.OMX || decoder == HWDecoderUtil.Decoder.ALL)) {
                sb.append("iomx,");
            }
            sb.append("all");
            addOption(sb.toString());
        } else if (!z) {
            addOption(":no-hw-dec");
        }
    }

    public void setDefaultMediaPlayerOptions() {
        boolean z;
        if (LibVLC.majorVersion() == 3) {
            synchronized (this) {
                z = this.mCodecOptionSet;
                this.mCodecOptionSet = true;
            }
            if (!z) {
                setHWDecoderEnabled(true, false);
            }
        }
        Uri uri = this.mUri;
        if (uri != null && uri.getScheme() != null && !this.mUri.getScheme().equalsIgnoreCase("file") && this.mUri.getLastPathSegment() != null && this.mUri.getLastPathSegment().toLowerCase().endsWith(".iso")) {
            addOption(":demux=dvdnav,any");
        }
    }

    public void addOption(String str) {
        synchronized (this) {
            if (!this.mCodecOptionSet && str.startsWith(":codec=")) {
                this.mCodecOptionSet = true;
            }
            if (!this.mNetworkCachingSet && str.startsWith(":network-caching=")) {
                this.mNetworkCachingSet = true;
            }
            if (!this.mFileCachingSet && str.startsWith(":file-caching=")) {
                this.mFileCachingSet = true;
            }
        }
        nativeAddOption(str);
    }

    public void addSlave(IMedia.Slave slave) {
        nativeAddSlave(slave.type, slave.priority, slave.uri);
    }

    public void clearSlaves() {
        nativeClearSlaves();
    }

    public IMedia.Slave[] getSlaves() {
        return nativeGetSlaves();
    }

    public IMedia.Stats getStats() {
        return nativeGetStats();
    }

    /* access modifiers changed from: protected */
    public void onReleaseNative() {
        MediaList mediaList = this.mSubItems;
        if (mediaList != null) {
            mediaList.release();
        }
        nativeRelease();
    }
}
