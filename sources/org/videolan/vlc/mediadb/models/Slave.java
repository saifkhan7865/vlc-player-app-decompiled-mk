package org.videolan.vlc.mediadb.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/mediadb/models/Slave;", "", "mediaPath", "", "type", "", "priority", "uri", "(Ljava/lang/String;IILjava/lang/String;)V", "getMediaPath", "()Ljava/lang/String;", "getPriority", "()I", "getType", "getUri", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Slave.kt */
public final class Slave {
    private final String mediaPath;
    private final int priority;
    private final int type;
    private final String uri;

    public static /* synthetic */ Slave copy$default(Slave slave, String str, int i, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = slave.mediaPath;
        }
        if ((i3 & 2) != 0) {
            i = slave.type;
        }
        if ((i3 & 4) != 0) {
            i2 = slave.priority;
        }
        if ((i3 & 8) != 0) {
            str2 = slave.uri;
        }
        return slave.copy(str, i, i2, str2);
    }

    public final String component1() {
        return this.mediaPath;
    }

    public final int component2() {
        return this.type;
    }

    public final int component3() {
        return this.priority;
    }

    public final String component4() {
        return this.uri;
    }

    public final Slave copy(String str, int i, int i2, String str2) {
        Intrinsics.checkNotNullParameter(str, "mediaPath");
        Intrinsics.checkNotNullParameter(str2, Constants.KEY_URI);
        return new Slave(str, i, i2, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Slave)) {
            return false;
        }
        Slave slave = (Slave) obj;
        return Intrinsics.areEqual((Object) this.mediaPath, (Object) slave.mediaPath) && this.type == slave.type && this.priority == slave.priority && Intrinsics.areEqual((Object) this.uri, (Object) slave.uri);
    }

    public int hashCode() {
        return (((((this.mediaPath.hashCode() * 31) + this.type) * 31) + this.priority) * 31) + this.uri.hashCode();
    }

    public String toString() {
        return "Slave(mediaPath=" + this.mediaPath + ", type=" + this.type + ", priority=" + this.priority + ", uri=" + this.uri + ')';
    }

    public Slave(String str, int i, int i2, String str2) {
        Intrinsics.checkNotNullParameter(str, "mediaPath");
        Intrinsics.checkNotNullParameter(str2, Constants.KEY_URI);
        this.mediaPath = str;
        this.type = i;
        this.priority = i2;
        this.uri = str2;
    }

    public final String getMediaPath() {
        return this.mediaPath;
    }

    public final int getType() {
        return this.type;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final String getUri() {
        return this.uri;
    }
}
