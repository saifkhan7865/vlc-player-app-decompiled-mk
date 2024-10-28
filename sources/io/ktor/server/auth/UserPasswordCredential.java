package io.ktor.server.auth;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/ktor/server/auth/UserPasswordCredential;", "Lio/ktor/server/auth/Credential;", "name", "", "password", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPassword", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SimpleAuth.kt */
public final class UserPasswordCredential implements Credential {
    private final String name;
    private final String password;

    public static /* synthetic */ UserPasswordCredential copy$default(UserPasswordCredential userPasswordCredential, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userPasswordCredential.name;
        }
        if ((i & 2) != 0) {
            str2 = userPasswordCredential.password;
        }
        return userPasswordCredential.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.password;
    }

    public final UserPasswordCredential copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "password");
        return new UserPasswordCredential(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserPasswordCredential)) {
            return false;
        }
        UserPasswordCredential userPasswordCredential = (UserPasswordCredential) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) userPasswordCredential.name) && Intrinsics.areEqual((Object) this.password, (Object) userPasswordCredential.password);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.password.hashCode();
    }

    public String toString() {
        return "UserPasswordCredential(name=" + this.name + ", password=" + this.password + ')';
    }

    public UserPasswordCredential(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "password");
        this.name = str;
        this.password = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPassword() {
        return this.password;
    }
}
