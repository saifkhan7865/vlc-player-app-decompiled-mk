package org.videolan.vlc.viewmodels;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/viewmodels/PlayerState;", "", "playing", "", "title", "", "artist", "(ZLjava/lang/String;Ljava/lang/String;)V", "getArtist", "()Ljava/lang/String;", "getPlaying", "()Z", "getTitle", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistModel.kt */
public final class PlayerState {
    private final String artist;
    private final boolean playing;
    private final String title;

    public PlayerState(boolean z, String str, String str2) {
        this.playing = z;
        this.title = str;
        this.artist = str2;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final boolean getPlaying() {
        return this.playing;
    }

    public final String getTitle() {
        return this.title;
    }
}
