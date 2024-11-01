package org.videolan.vlc.util;

import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/util/CertInfo;", "", "name", "", "keys", "", "(Ljava/lang/String;Ljava/util/List;)V", "getKeys", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessControl.kt */
public final class CertInfo {
    private final List<String> keys;
    private final String name;

    public static /* synthetic */ CertInfo copy$default(CertInfo certInfo, String str, List<String> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = certInfo.name;
        }
        if ((i & 2) != 0) {
            list = certInfo.keys;
        }
        return certInfo.copy(str, list);
    }

    public final String component1() {
        return this.name;
    }

    public final List<String> component2() {
        return this.keys;
    }

    public final CertInfo copy(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(list, "keys");
        return new CertInfo(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CertInfo)) {
            return false;
        }
        CertInfo certInfo = (CertInfo) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) certInfo.name) && Intrinsics.areEqual((Object) this.keys, (Object) certInfo.keys);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.keys.hashCode();
    }

    public String toString() {
        return "CertInfo(name=" + this.name + ", keys=" + this.keys + ')';
    }

    public CertInfo(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(list, "keys");
        this.name = str;
        this.keys = list;
    }

    public final List<String> getKeys() {
        return this.keys;
    }

    public final String getName() {
        return this.name;
    }
}
