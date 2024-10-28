package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/webserver/LogFile;", "", "path", "", "type", "(Ljava/lang/String;Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessRouting.kt */
public final class LogFile {
    private final String path;
    private final String type;

    public static /* synthetic */ LogFile copy$default(LogFile logFile, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = logFile.path;
        }
        if ((i & 2) != 0) {
            str2 = logFile.type;
        }
        return logFile.copy(str, str2);
    }

    public final String component1() {
        return this.path;
    }

    public final String component2() {
        return this.type;
    }

    public final LogFile copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(str2, "type");
        return new LogFile(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogFile)) {
            return false;
        }
        LogFile logFile = (LogFile) obj;
        return Intrinsics.areEqual((Object) this.path, (Object) logFile.path) && Intrinsics.areEqual((Object) this.type, (Object) logFile.type);
    }

    public int hashCode() {
        return (this.path.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "LogFile(path=" + this.path + ", type=" + this.type + ')';
    }

    public LogFile(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(str2, "type");
        this.path = str;
        this.type = str2;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getType() {
        return this.type;
    }
}
