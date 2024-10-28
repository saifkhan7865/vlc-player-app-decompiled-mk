package org.videolan.vlc.mediadb.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/mediadb/models/CustomDirectory;", "", "path", "", "(Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CustomDirectory.kt */
public final class CustomDirectory {
    private final String path;

    public static /* synthetic */ CustomDirectory copy$default(CustomDirectory customDirectory, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customDirectory.path;
        }
        return customDirectory.copy(str);
    }

    public final String component1() {
        return this.path;
    }

    public final CustomDirectory copy(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return new CustomDirectory(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CustomDirectory) && Intrinsics.areEqual((Object) this.path, (Object) ((CustomDirectory) obj).path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return "CustomDirectory(path=" + this.path + ')';
    }

    public CustomDirectory(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        this.path = str;
    }

    public final String getPath() {
        return this.path;
    }
}
