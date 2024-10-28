package org.videolan.libvlc.stubs;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.FileDescriptor;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaList;

public class StubMedia extends StubVLCObject<IMedia.Event> implements IMedia {
    private ILibVLC mILibVLC;
    private int mType;
    private Uri mUri;

    public void addOption(String str) {
    }

    public void addSlave(IMedia.Slave slave) {
    }

    public void clearSlaves() {
    }

    public long getDuration() {
        return 0;
    }

    public int getState() {
        return 0;
    }

    public IMedia.Stats getStats() {
        return null;
    }

    public IMedia.Track getTrack(int i) {
        return null;
    }

    public int getTrackCount() {
        return 0;
    }

    public boolean isParsed() {
        return false;
    }

    public boolean isReleased() {
        return false;
    }

    public boolean parse() {
        return false;
    }

    public boolean parse(int i) {
        return false;
    }

    public boolean parseAsync() {
        return false;
    }

    public boolean parseAsync(int i) {
        return false;
    }

    public boolean parseAsync(int i, int i2) {
        return false;
    }

    public void release() {
    }

    public boolean retain() {
        return false;
    }

    public void setDefaultMediaPlayerOptions() {
    }

    public void setEventListener(IMedia.EventListener eventListener) {
    }

    public void setHWDecoderEnabled(boolean z, boolean z2) {
    }

    public StubMedia(ILibVLC iLibVLC, String str) {
        this(iLibVLC, Uri.parse(str));
    }

    public StubMedia(ILibVLC iLibVLC, Uri uri) {
        this.mType = 0;
        this.mUri = uri;
        this.mILibVLC = iLibVLC;
    }

    public StubMedia(ILibVLC iLibVLC, FileDescriptor fileDescriptor) {
        this.mType = 0;
        this.mILibVLC = iLibVLC;
    }

    public StubMedia(ILibVLC iLibVLC, AssetFileDescriptor assetFileDescriptor) {
        this.mType = 0;
        this.mILibVLC = iLibVLC;
    }

    public IMediaList subItems() {
        return new StubMediaList();
    }

    public int getType() {
        return this.mType;
    }

    public String getMeta(int i) {
        Uri uri = this.mUri;
        if (uri == null) {
            return null;
        }
        if (i == 0) {
            return getTitle();
        }
        if (i != 10) {
            return null;
        }
        return uri.getPath();
    }

    public String getMeta(int i, boolean z) {
        return getMeta(i);
    }

    private String getTitle() {
        if ("file".equals(this.mUri.getScheme())) {
            return this.mUri.getLastPathSegment();
        }
        return this.mUri.getPath();
    }

    public IMedia.Slave[] getSlaves() {
        return new IMedia.Slave[0];
    }

    public Uri getUri() {
        return this.mUri;
    }

    public ILibVLC getLibVLC() {
        return this.mILibVLC;
    }

    public void setType(int i) {
        this.mType = i;
    }
}
