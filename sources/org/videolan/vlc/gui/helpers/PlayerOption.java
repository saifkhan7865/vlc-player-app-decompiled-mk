package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerOption;", "", "id", "", "icon", "", "title", "", "(JILjava/lang/String;)V", "getIcon", "()I", "getId", "()J", "getTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerOptionsDelegate.kt */
public final class PlayerOption {
    private final int icon;
    private final long id;
    private final String title;

    public static /* synthetic */ PlayerOption copy$default(PlayerOption playerOption, long j, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = playerOption.id;
        }
        if ((i2 & 2) != 0) {
            i = playerOption.icon;
        }
        if ((i2 & 4) != 0) {
            str = playerOption.title;
        }
        return playerOption.copy(j, i, str);
    }

    public final long component1() {
        return this.id;
    }

    public final int component2() {
        return this.icon;
    }

    public final String component3() {
        return this.title;
    }

    public final PlayerOption copy(long j, int i, String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        return new PlayerOption(j, i, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerOption)) {
            return false;
        }
        PlayerOption playerOption = (PlayerOption) obj;
        return this.id == playerOption.id && this.icon == playerOption.icon && Intrinsics.areEqual((Object) this.title, (Object) playerOption.title);
    }

    public int hashCode() {
        return (((UInt$$ExternalSyntheticBackport0.m(this.id) * 31) + this.icon) * 31) + this.title.hashCode();
    }

    public String toString() {
        return "PlayerOption(id=" + this.id + ", icon=" + this.icon + ", title=" + this.title + ')';
    }

    public PlayerOption(long j, int i, String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        this.id = j;
        this.icon = i;
        this.title = str;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }
}
