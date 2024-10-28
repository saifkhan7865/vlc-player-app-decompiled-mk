package org.videolan.vlc.util;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.util.AndroidUtil;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0018\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010*\u001a\u00020\u0003H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0019\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\u001b\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R\u001a\u0010\u001f\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001a\u0010!\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\u001c\u0010#\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\t\"\u0004\b%\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\tR\u001c\u0010'\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\t\"\u0004\b)\u0010\u000b¨\u0006+"}, d2 = {"Lorg/videolan/vlc/util/VoiceSearchParams;", "", "query", "", "extras", "Landroid/os/Bundle;", "(Ljava/lang/String;Landroid/os/Bundle;)V", "album", "getAlbum", "()Ljava/lang/String;", "setAlbum", "(Ljava/lang/String;)V", "artist", "getArtist", "setArtist", "genre", "getGenre", "setGenre", "isAlbumFocus", "", "()Z", "setAlbumFocus", "(Z)V", "isAny", "setAny", "isArtistFocus", "setArtistFocus", "isGenreFocus", "setGenreFocus", "isPlaylistFocus", "setPlaylistFocus", "isSongFocus", "setSongFocus", "isUnstructured", "setUnstructured", "playlist", "getPlaylist", "setPlaylist", "getQuery", "song", "getSong", "setSong", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VoiceSearchParams.kt */
public final class VoiceSearchParams {
    private String album;
    private String artist;
    private String genre;
    private boolean isAlbumFocus;
    private boolean isAny;
    private boolean isArtistFocus;
    private boolean isGenreFocus;
    private boolean isPlaylistFocus;
    private boolean isSongFocus;
    private boolean isUnstructured;
    private String playlist;
    private final String query;
    private String song;

    public VoiceSearchParams(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "query");
        this.query = str;
        if (str.length() == 0) {
            this.isAny = true;
        } else if (bundle == null || !bundle.containsKey("android.intent.extra.focus")) {
            this.isUnstructured = true;
        } else {
            boolean z = AndroidUtil.isLolliPopOrLater;
            String string = bundle.getString("android.intent.extra.focus");
            if (string != null) {
                switch (string.hashCode()) {
                    case -451210025:
                        if (string.equals("vnd.android.cursor.item/playlist")) {
                            this.isPlaylistFocus = true;
                            this.playlist = bundle.getString("android.intent.extra.playlist");
                            return;
                        }
                        return;
                    case 892096906:
                        if (string.equals("vnd.android.cursor.item/album")) {
                            this.isAlbumFocus = true;
                            this.album = bundle.getString("android.intent.extra.album");
                            this.genre = bundle.getString("android.intent.extra.genre");
                            this.artist = bundle.getString("android.intent.extra.artist");
                            return;
                        }
                        return;
                    case 892366577:
                        if (string.equals("vnd.android.cursor.item/audio")) {
                            this.isSongFocus = true;
                            String string2 = bundle.getString("android.intent.extra.title");
                            this.song = string2;
                            Intrinsics.checkNotNull(string2);
                            if (StringsKt.contains$default((CharSequence) string2, (CharSequence) "(", false, 2, (Object) null)) {
                                String str2 = this.song;
                                Intrinsics.checkNotNull(str2);
                                String str3 = this.song;
                                Intrinsics.checkNotNull(str3);
                                String substring = str2.substring(0, StringsKt.indexOf$default((CharSequence) str3, '(', 0, false, 6, (Object) null));
                                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                                this.song = substring;
                            }
                            this.album = bundle.getString("android.intent.extra.album");
                            this.genre = bundle.getString("android.intent.extra.genre");
                            this.artist = bundle.getString("android.intent.extra.artist");
                            return;
                        }
                        return;
                    case 897440926:
                        if (string.equals("vnd.android.cursor.item/genre")) {
                            this.isGenreFocus = true;
                            String string3 = bundle.getString("android.intent.extra.genre");
                            this.genre = string3;
                            CharSequence charSequence = string3;
                            if (charSequence == null || charSequence.length() == 0) {
                                this.genre = str;
                                return;
                            }
                            return;
                        }
                        return;
                    case 1891266444:
                        if (string.equals("vnd.android.cursor.item/artist")) {
                            this.isArtistFocus = true;
                            this.genre = bundle.getString("android.intent.extra.genre");
                            this.artist = bundle.getString("android.intent.extra.artist");
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public final String getQuery() {
        return this.query;
    }

    public final boolean isAny() {
        return this.isAny;
    }

    public final void setAny(boolean z) {
        this.isAny = z;
    }

    public final boolean isUnstructured() {
        return this.isUnstructured;
    }

    public final void setUnstructured(boolean z) {
        this.isUnstructured = z;
    }

    public final boolean isGenreFocus() {
        return this.isGenreFocus;
    }

    public final void setGenreFocus(boolean z) {
        this.isGenreFocus = z;
    }

    public final boolean isArtistFocus() {
        return this.isArtistFocus;
    }

    public final void setArtistFocus(boolean z) {
        this.isArtistFocus = z;
    }

    public final boolean isAlbumFocus() {
        return this.isAlbumFocus;
    }

    public final void setAlbumFocus(boolean z) {
        this.isAlbumFocus = z;
    }

    public final boolean isPlaylistFocus() {
        return this.isPlaylistFocus;
    }

    public final void setPlaylistFocus(boolean z) {
        this.isPlaylistFocus = z;
    }

    public final boolean isSongFocus() {
        return this.isSongFocus;
    }

    public final void setSongFocus(boolean z) {
        this.isSongFocus = z;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final void setGenre(String str) {
        this.genre = str;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final void setArtist(String str) {
        this.artist = str;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final void setAlbum(String str) {
        this.album = str;
    }

    public final String getPlaylist() {
        return this.playlist;
    }

    public final void setPlaylist(String str) {
        this.playlist = str;
    }

    public final String getSong() {
        return this.song;
    }

    public final void setSong(String str) {
        this.song = str;
    }

    public String toString() {
        return StringsKt.trimIndent("\n            query=" + this.query + "\n            isAny=" + this.isAny + "\n            isUnstructured=" + this.isUnstructured + "\n            isGenreFocus=" + this.isGenreFocus + "\n            isArtistFocus=" + this.isArtistFocus + "\n            isAlbumFocus=" + this.isAlbumFocus + "\n            isPlaylistFocus=" + this.isPlaylistFocus + "\n            isSongFocus=" + this.isSongFocus + "\n            genre=" + this.genre + "\n            artist=" + this.artist + "\n            album=" + this.album + "\n            playlist=" + this.playlist + "\n            song=" + this.song);
    }
}
