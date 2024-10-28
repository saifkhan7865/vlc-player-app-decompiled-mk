package org.videolan.vlc.webserver.websockets;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/webserver/websockets/WSAuthTicket;", "", "id", "", "expiration", "", "(Ljava/lang/String;J)V", "getExpiration", "()J", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WSAuthTicket.kt */
public final class WSAuthTicket {
    private final long expiration;
    private final String id;

    public static /* synthetic */ WSAuthTicket copy$default(WSAuthTicket wSAuthTicket, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wSAuthTicket.id;
        }
        if ((i & 2) != 0) {
            j = wSAuthTicket.expiration;
        }
        return wSAuthTicket.copy(str, j);
    }

    public final String component1() {
        return this.id;
    }

    public final long component2() {
        return this.expiration;
    }

    public final WSAuthTicket copy(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "id");
        return new WSAuthTicket(str, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WSAuthTicket)) {
            return false;
        }
        WSAuthTicket wSAuthTicket = (WSAuthTicket) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) wSAuthTicket.id) && this.expiration == wSAuthTicket.expiration;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + UInt$$ExternalSyntheticBackport0.m(this.expiration);
    }

    public String toString() {
        return "WSAuthTicket(id=" + this.id + ", expiration=" + this.expiration + ')';
    }

    public WSAuthTicket(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "id");
        this.id = str;
        this.expiration = j;
    }

    public final String getId() {
        return this.id;
    }

    public final long getExpiration() {
        return this.expiration;
    }
}
