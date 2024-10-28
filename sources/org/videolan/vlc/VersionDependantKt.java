package org.videolan.vlc;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\u00020\t¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\u00020\t¢\u0006\u0002\u0010\n\u001a\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u000f\u001a\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\u00020\t¢\u0006\u0002\u0010\n\u001a\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r*\u00020\u000f\u001a\f\u0010\u0013\u001a\u0004\u0018\u00010\u0001*\u00020\t\u001a\f\u0010\u0014\u001a\u0004\u0018\u00010\u0001*\u00020\t\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\t\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0012\u0010\u001a\u001a\u00020\u0017*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0012\u0010\u001b\u001a\u00020\u0017*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0012\u0010\u001c\u001a\u00020\u001d*\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f¨\u0006 "}, d2 = {"getDisableTrack", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "context", "Landroid/content/Context;", "convertToVlcTrack", "", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "([Lorg/videolan/libvlc/MediaPlayer$TrackDescription;)[Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "getAllAudioTracks", "Lorg/videolan/libvlc/MediaPlayer;", "(Lorg/videolan/libvlc/MediaPlayer;)[Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "getAllSpuTracks", "getAllTracks", "", "Lorg/videolan/libvlc/interfaces/IMedia$Track;", "Lorg/videolan/libvlc/interfaces/IMedia;", "getAllVideoTracks", "getAudioTracks", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", "getSelectedAudioTrack", "getSelectedSpuTrack", "getSelectedVideoTrack", "setAudioTrack", "", "index", "", "setSpuTrack", "setVideoTrack", "unselectTrackType", "", "type", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: VersionDependant.kt */
public final class VersionDependantKt {
    public static final List<IMedia.AudioTrack> getAudioTracks(IMedia iMedia) {
        Intrinsics.checkNotNullParameter(iMedia, "<this>");
        ArrayList arrayList = new ArrayList();
        int trackCount = iMedia.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            IMedia.Track track = iMedia.getTrack(i);
            if (track instanceof IMedia.AudioTrack) {
                arrayList.add(track);
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    public static final List<IMedia.Track> getAllTracks(IMedia iMedia) {
        Intrinsics.checkNotNullParameter(iMedia, "<this>");
        ArrayList arrayList = new ArrayList();
        int trackCount = iMedia.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            arrayList.add(iMedia.getTrack(i));
        }
        return arrayList;
    }

    public static final VlcTrack getSelectedVideoTrack(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        IMedia.VideoTrack currentVideoTrack = mediaPlayer.getCurrentVideoTrack();
        return currentVideoTrack != null ? new VlcTrackImpl((IMedia.Track) currentVideoTrack) : null;
    }

    public static final VlcTrack getSelectedAudioTrack(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        int audioTrack = mediaPlayer.getAudioTrack();
        MediaPlayer.TrackDescription[] audioTracks = mediaPlayer.getAudioTracks();
        if (audioTracks == null) {
            return null;
        }
        for (MediaPlayer.TrackDescription trackDescription : audioTracks) {
            if (trackDescription.id == audioTrack) {
                Intrinsics.checkNotNull(trackDescription);
                return new VlcTrackImpl(trackDescription);
            }
        }
        return null;
    }

    public static final VlcTrack getSelectedSpuTrack(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        int spuTrack = mediaPlayer.getSpuTrack();
        MediaPlayer.TrackDescription[] spuTracks = mediaPlayer.getSpuTracks();
        if (spuTracks == null) {
            return null;
        }
        for (MediaPlayer.TrackDescription trackDescription : spuTracks) {
            if (trackDescription.id == spuTrack) {
                Intrinsics.checkNotNull(trackDescription);
                return new VlcTrackImpl(trackDescription);
            }
        }
        return null;
    }

    public static final boolean setVideoTrack(MediaPlayer mediaPlayer, String str) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        Intrinsics.checkNotNullParameter(str, "index");
        return mediaPlayer.setVideoTrack(Integer.parseInt(str));
    }

    public static final boolean setAudioTrack(MediaPlayer mediaPlayer, String str) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        Intrinsics.checkNotNullParameter(str, "index");
        return mediaPlayer.setAudioTrack(Integer.parseInt(str));
    }

    public static final boolean setSpuTrack(MediaPlayer mediaPlayer, String str) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        Intrinsics.checkNotNullParameter(str, "index");
        return mediaPlayer.setSpuTrack(Integer.parseInt(str));
    }

    public static final VlcTrack[] getAllAudioTracks(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        return convertToVlcTrack(mediaPlayer.getAudioTracks());
    }

    public static final VlcTrack[] getAllVideoTracks(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        return convertToVlcTrack(mediaPlayer.getVideoTracks());
    }

    public static final VlcTrack[] getAllSpuTracks(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        return convertToVlcTrack(mediaPlayer.getSpuTracks());
    }

    public static final VlcTrack[] convertToVlcTrack(MediaPlayer.TrackDescription[] trackDescriptionArr) {
        if (trackDescriptionArr == null) {
            return new VlcTrack[0];
        }
        ArrayList arrayList = new ArrayList();
        for (MediaPlayer.TrackDescription vlcTrackImpl : trackDescriptionArr) {
            arrayList.add(new VlcTrackImpl(vlcTrackImpl));
        }
        return (VlcTrack[]) arrayList.toArray(new VlcTrack[0]);
    }

    public static final void unselectTrackType(MediaPlayer mediaPlayer, int i) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "<this>");
        throw new IllegalStateException("This is a VLC 4 only API. It should not be called by VLC 3");
    }

    public static final VlcTrack getDisableTrack(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        throw new IllegalStateException("This is a VLC 4 only API. It should not be called by VLC 3");
    }
}
