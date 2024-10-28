package org.videolan.vlc.webserver;

import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/webserver/OTPCode;", "", "code", "", "challenge", "expiration", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getChallenge", "()Ljava/lang/String;", "getCode", "getExpiration", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOTP.kt */
public final class OTPCode {
    private final String challenge;
    private final String code;
    private final long expiration;

    public static /* synthetic */ OTPCode copy$default(OTPCode oTPCode, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = oTPCode.code;
        }
        if ((i & 2) != 0) {
            str2 = oTPCode.challenge;
        }
        if ((i & 4) != 0) {
            j = oTPCode.expiration;
        }
        return oTPCode.copy(str, str2, j);
    }

    public final String component1() {
        return this.code;
    }

    public final String component2() {
        return this.challenge;
    }

    public final long component3() {
        return this.expiration;
    }

    public final OTPCode copy(String str, String str2, long j) {
        Intrinsics.checkNotNullParameter(str, OAuth2RequestParameters.Code);
        Intrinsics.checkNotNullParameter(str2, "challenge");
        return new OTPCode(str, str2, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OTPCode)) {
            return false;
        }
        OTPCode oTPCode = (OTPCode) obj;
        return Intrinsics.areEqual((Object) this.code, (Object) oTPCode.code) && Intrinsics.areEqual((Object) this.challenge, (Object) oTPCode.challenge) && this.expiration == oTPCode.expiration;
    }

    public int hashCode() {
        return (((this.code.hashCode() * 31) + this.challenge.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.expiration);
    }

    public String toString() {
        return "OTPCode(code=" + this.code + ", challenge=" + this.challenge + ", expiration=" + this.expiration + ')';
    }

    public OTPCode(String str, String str2, long j) {
        Intrinsics.checkNotNullParameter(str, OAuth2RequestParameters.Code);
        Intrinsics.checkNotNullParameter(str2, "challenge");
        this.code = str;
        this.challenge = str2;
        this.expiration = j;
    }

    public final String getChallenge() {
        return this.challenge;
    }

    public final String getCode() {
        return this.code;
    }

    public final long getExpiration() {
        return this.expiration;
    }
}
