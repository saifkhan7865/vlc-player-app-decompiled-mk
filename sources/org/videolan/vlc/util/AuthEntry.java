package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/util/AuthEntry;", "", "approved", "", "callingPackage", "", "desc", "(ZLjava/lang/String;Ljava/lang/String;)V", "getApproved", "()Z", "getCallingPackage", "()Ljava/lang/String;", "getDesc", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessControl.kt */
public final class AuthEntry {
    private final boolean approved;
    private final String callingPackage;
    private final String desc;

    public static /* synthetic */ AuthEntry copy$default(AuthEntry authEntry, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = authEntry.approved;
        }
        if ((i & 2) != 0) {
            str = authEntry.callingPackage;
        }
        if ((i & 4) != 0) {
            str2 = authEntry.desc;
        }
        return authEntry.copy(z, str, str2);
    }

    public final boolean component1() {
        return this.approved;
    }

    public final String component2() {
        return this.callingPackage;
    }

    public final String component3() {
        return this.desc;
    }

    public final AuthEntry copy(boolean z, String str, String str2) {
        Intrinsics.checkNotNullParameter(str2, "desc");
        return new AuthEntry(z, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthEntry)) {
            return false;
        }
        AuthEntry authEntry = (AuthEntry) obj;
        return this.approved == authEntry.approved && Intrinsics.areEqual((Object) this.callingPackage, (Object) authEntry.callingPackage) && Intrinsics.areEqual((Object) this.desc, (Object) authEntry.desc);
    }

    public int hashCode() {
        int m = UInt$$ExternalSyntheticBackport0.m(this.approved) * 31;
        String str = this.callingPackage;
        return ((m + (str == null ? 0 : str.hashCode())) * 31) + this.desc.hashCode();
    }

    public String toString() {
        return "AuthEntry(approved=" + this.approved + ", callingPackage=" + this.callingPackage + ", desc=" + this.desc + ')';
    }

    public AuthEntry(boolean z, String str, String str2) {
        Intrinsics.checkNotNullParameter(str2, "desc");
        this.approved = z;
        this.callingPackage = str;
        this.desc = str2;
    }

    public final boolean getApproved() {
        return this.approved;
    }

    public final String getCallingPackage() {
        return this.callingPackage;
    }

    public final String getDesc() {
        return this.desc;
    }
}
