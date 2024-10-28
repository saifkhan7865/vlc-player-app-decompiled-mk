package io.ktor.server.auth;

import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lio/ktor/server/auth/UserHashedTableAuth;", "", "digester", "Lkotlin/Function1;", "", "", "table", "", "(Lkotlin/jvm/functions/Function1;Ljava/util/Map;)V", "getDigester", "()Lkotlin/jvm/functions/Function1;", "getTable", "()Ljava/util/Map;", "authenticate", "Lio/ktor/server/auth/UserIdPrincipal;", "credential", "Lio/ktor/server/auth/UserPasswordCredential;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SimpleAuth.kt */
public final class UserHashedTableAuth {
    private final Function1<String, byte[]> digester;
    private final Map<String, byte[]> table;

    public UserHashedTableAuth(Function1<? super String, byte[]> function1, Map<String, byte[]> map) {
        Intrinsics.checkNotNullParameter(function1, "digester");
        Intrinsics.checkNotNullParameter(map, "table");
        this.digester = function1;
        this.table = map;
        map.isEmpty();
    }

    public final Function1<String, byte[]> getDigester() {
        return this.digester;
    }

    public final Map<String, byte[]> getTable() {
        return this.table;
    }

    public final UserIdPrincipal authenticate(UserPasswordCredential userPasswordCredential) {
        Intrinsics.checkNotNullParameter(userPasswordCredential, "credential");
        byte[] bArr = this.table.get(userPasswordCredential.getName());
        if (bArr == null || !Arrays.equals(this.digester.invoke(userPasswordCredential.getPassword()), bArr)) {
            return null;
        }
        return new UserIdPrincipal(userPasswordCredential.getName());
    }
}
