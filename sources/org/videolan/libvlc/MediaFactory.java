package org.videolan.libvlc;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.FileDescriptor;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaFactory;

public class MediaFactory implements IMediaFactory {
    public IMedia getFromLocalPath(ILibVLC iLibVLC, String str) {
        return new Media(iLibVLC, str);
    }

    public IMedia getFromUri(ILibVLC iLibVLC, Uri uri) {
        return new Media(iLibVLC, uri);
    }

    public IMedia getFromFileDescriptor(ILibVLC iLibVLC, FileDescriptor fileDescriptor) {
        return new Media(iLibVLC, fileDescriptor);
    }

    public IMedia getFromAssetFileDescriptor(ILibVLC iLibVLC, AssetFileDescriptor assetFileDescriptor) {
        return new Media(iLibVLC, assetFileDescriptor);
    }
}
