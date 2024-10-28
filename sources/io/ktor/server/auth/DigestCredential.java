package io.ktor.server.auth;

import io.ktor.http.auth.HttpAuthHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jw\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006+"}, d2 = {"Lio/ktor/server/auth/DigestCredential;", "Lio/ktor/server/auth/Credential;", "realm", "", "userName", "digestUri", "nonce", "opaque", "nonceCount", "algorithm", "response", "cnonce", "qop", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAlgorithm", "()Ljava/lang/String;", "getCnonce", "getDigestUri", "getNonce", "getNonceCount", "getOpaque", "getQop", "getRealm", "getResponse", "getUserName", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DigestAuth.kt */
public final class DigestCredential implements Credential {
    private final String algorithm;
    private final String cnonce;
    private final String digestUri;
    private final String nonce;
    private final String nonceCount;
    private final String opaque;
    private final String qop;
    private final String realm;
    private final String response;
    private final String userName;

    public static /* synthetic */ DigestCredential copy$default(DigestCredential digestCredential, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, Object obj) {
        DigestCredential digestCredential2 = digestCredential;
        int i2 = i;
        return digestCredential.copy((i2 & 1) != 0 ? digestCredential2.realm : str, (i2 & 2) != 0 ? digestCredential2.userName : str2, (i2 & 4) != 0 ? digestCredential2.digestUri : str3, (i2 & 8) != 0 ? digestCredential2.nonce : str4, (i2 & 16) != 0 ? digestCredential2.opaque : str5, (i2 & 32) != 0 ? digestCredential2.nonceCount : str6, (i2 & 64) != 0 ? digestCredential2.algorithm : str7, (i2 & 128) != 0 ? digestCredential2.response : str8, (i2 & 256) != 0 ? digestCredential2.cnonce : str9, (i2 & 512) != 0 ? digestCredential2.qop : str10);
    }

    public final String component1() {
        return this.realm;
    }

    public final String component10() {
        return this.qop;
    }

    public final String component2() {
        return this.userName;
    }

    public final String component3() {
        return this.digestUri;
    }

    public final String component4() {
        return this.nonce;
    }

    public final String component5() {
        return this.opaque;
    }

    public final String component6() {
        return this.nonceCount;
    }

    public final String component7() {
        return this.algorithm;
    }

    public final String component8() {
        return this.response;
    }

    public final String component9() {
        return this.cnonce;
    }

    public final DigestCredential copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        Intrinsics.checkNotNullParameter(str, HttpAuthHeader.Parameters.Realm);
        Intrinsics.checkNotNullParameter(str2, "userName");
        Intrinsics.checkNotNullParameter(str3, "digestUri");
        String str11 = str4;
        Intrinsics.checkNotNullParameter(str11, "nonce");
        String str12 = str8;
        Intrinsics.checkNotNullParameter(str12, "response");
        return new DigestCredential(str, str2, str3, str11, str5, str6, str7, str12, str9, str10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DigestCredential)) {
            return false;
        }
        DigestCredential digestCredential = (DigestCredential) obj;
        return Intrinsics.areEqual((Object) this.realm, (Object) digestCredential.realm) && Intrinsics.areEqual((Object) this.userName, (Object) digestCredential.userName) && Intrinsics.areEqual((Object) this.digestUri, (Object) digestCredential.digestUri) && Intrinsics.areEqual((Object) this.nonce, (Object) digestCredential.nonce) && Intrinsics.areEqual((Object) this.opaque, (Object) digestCredential.opaque) && Intrinsics.areEqual((Object) this.nonceCount, (Object) digestCredential.nonceCount) && Intrinsics.areEqual((Object) this.algorithm, (Object) digestCredential.algorithm) && Intrinsics.areEqual((Object) this.response, (Object) digestCredential.response) && Intrinsics.areEqual((Object) this.cnonce, (Object) digestCredential.cnonce) && Intrinsics.areEqual((Object) this.qop, (Object) digestCredential.qop);
    }

    public int hashCode() {
        int hashCode = ((((((this.realm.hashCode() * 31) + this.userName.hashCode()) * 31) + this.digestUri.hashCode()) * 31) + this.nonce.hashCode()) * 31;
        String str = this.opaque;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.nonceCount;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.algorithm;
        int hashCode4 = (((hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.response.hashCode()) * 31;
        String str4 = this.cnonce;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.qop;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode5 + i;
    }

    public String toString() {
        return "DigestCredential(realm=" + this.realm + ", userName=" + this.userName + ", digestUri=" + this.digestUri + ", nonce=" + this.nonce + ", opaque=" + this.opaque + ", nonceCount=" + this.nonceCount + ", algorithm=" + this.algorithm + ", response=" + this.response + ", cnonce=" + this.cnonce + ", qop=" + this.qop + ')';
    }

    public DigestCredential(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        Intrinsics.checkNotNullParameter(str, HttpAuthHeader.Parameters.Realm);
        Intrinsics.checkNotNullParameter(str2, "userName");
        Intrinsics.checkNotNullParameter(str3, "digestUri");
        Intrinsics.checkNotNullParameter(str4, "nonce");
        Intrinsics.checkNotNullParameter(str8, "response");
        this.realm = str;
        this.userName = str2;
        this.digestUri = str3;
        this.nonce = str4;
        this.opaque = str5;
        this.nonceCount = str6;
        this.algorithm = str7;
        this.response = str8;
        this.cnonce = str9;
        this.qop = str10;
    }

    public final String getRealm() {
        return this.realm;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getDigestUri() {
        return this.digestUri;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public final String getOpaque() {
        return this.opaque;
    }

    public final String getNonceCount() {
        return this.nonceCount;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public final String getResponse() {
        return this.response;
    }

    public final String getCnonce() {
        return this.cnonce;
    }

    public final String getQop() {
        return this.qop;
    }
}
