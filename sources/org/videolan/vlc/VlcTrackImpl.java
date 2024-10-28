package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\u000e0\u000eH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/VlcTrackImpl;", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "track", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "(Lorg/videolan/libvlc/MediaPlayer$TrackDescription;)V", "Lorg/videolan/libvlc/interfaces/IMedia$Track;", "(Lorg/videolan/libvlc/interfaces/IMedia$Track;)V", "mediaTrack", "mediaplayerTrack", "getFrameRateDen", "", "getFrameRateNum", "getHeight", "getId", "", "getName", "kotlin.jvm.PlatformType", "getProjection", "getWidth", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VlcTrackImpl.kt */
public final class VlcTrackImpl implements VlcTrack {
    private IMedia.Track mediaTrack;
    private MediaPlayer.TrackDescription mediaplayerTrack;

    public VlcTrackImpl(MediaPlayer.TrackDescription trackDescription) {
        Intrinsics.checkNotNullParameter(trackDescription, "track");
        this.mediaplayerTrack = trackDescription;
    }

    public VlcTrackImpl(IMedia.Track track) {
        Intrinsics.checkNotNullParameter(track, "track");
        this.mediaTrack = track;
    }

    public String getId() {
        String num;
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        if (trackDescription != null && (num = Integer.valueOf(trackDescription.id).toString()) != null) {
            return num;
        }
        IMedia.Track track = this.mediaTrack;
        Intrinsics.checkNotNull(track);
        return String.valueOf(track.id);
    }

    public String getName() {
        String str;
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        if (trackDescription != null && (str = trackDescription.name) != null) {
            return str;
        }
        IMedia.Track track = this.mediaTrack;
        Intrinsics.checkNotNull(track);
        return track.description;
    }

    public int getWidth() {
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        IMedia.VideoTrack videoTrack = trackDescription instanceof IMedia.VideoTrack ? (IMedia.VideoTrack) trackDescription : null;
        if (videoTrack != null) {
            return videoTrack.width;
        }
        return 0;
    }

    public int getHeight() {
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        IMedia.VideoTrack videoTrack = trackDescription instanceof IMedia.VideoTrack ? (IMedia.VideoTrack) trackDescription : null;
        if (videoTrack != null) {
            return videoTrack.height;
        }
        return 0;
    }

    public int getProjection() {
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        IMedia.VideoTrack videoTrack = trackDescription instanceof IMedia.VideoTrack ? (IMedia.VideoTrack) trackDescription : null;
        if (videoTrack != null) {
            return videoTrack.projection;
        }
        return 0;
    }

    public int getFrameRateDen() {
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        IMedia.VideoTrack videoTrack = trackDescription instanceof IMedia.VideoTrack ? (IMedia.VideoTrack) trackDescription : null;
        if (videoTrack != null) {
            return videoTrack.frameRateDen;
        }
        return 0;
    }

    public int getFrameRateNum() {
        MediaPlayer.TrackDescription trackDescription = this.mediaplayerTrack;
        IMedia.VideoTrack videoTrack = trackDescription instanceof IMedia.VideoTrack ? (IMedia.VideoTrack) trackDescription : null;
        if (videoTrack != null) {
            return videoTrack.frameRateNum;
        }
        return 0;
    }
}
