package io.ktor.server.auth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u0001J&\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\b¢\u0006\u0002\u0010\u000fJ/\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0011¢\u0006\u0002\u0010\u0012R%\u0010\u0003\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lio/ktor/server/auth/CombinedPrincipal;", "Lio/ktor/server/auth/Principal;", "()V", "principals", "", "Lkotlin/Pair;", "", "getPrincipals", "()Ljava/util/List;", "add", "", "provider", "principal", "get", "T", "(Ljava/lang/String;)Lio/ktor/server/auth/Principal;", "klass", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Lio/ktor/server/auth/Principal;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Principal.kt */
public final class CombinedPrincipal implements Principal {
    private final List<Pair<String, Principal>> principals = new ArrayList();

    public final List<Pair<String, Principal>> getPrincipals() {
        return this.principals;
    }

    public final /* synthetic */ <T extends Principal> T get(String str) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return get(str, Reflection.getOrCreateKotlinClass(Principal.class));
    }

    public final <T extends Principal> T get(String str, KClass<T> kClass) {
        Object obj;
        Intrinsics.checkNotNullParameter(kClass, "klass");
        Iterator it = this.principals.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Pair pair = (Pair) obj;
            String str2 = (String) pair.component1();
            Principal principal = (Principal) pair.component2();
            if (str != null) {
                if (Intrinsics.areEqual((Object) str2, (Object) str) && kClass.isInstance(principal)) {
                    break;
                }
            } else if (kClass.isInstance(principal)) {
                break;
            }
        }
        Pair pair2 = (Pair) obj;
        T t = pair2 != null ? (Principal) pair2.getSecond() : null;
        if (t instanceof Principal) {
            return t;
        }
        return null;
    }

    public final void add(String str, Principal principal) {
        Intrinsics.checkNotNullParameter(principal, "principal");
        this.principals.add(new Pair(str, principal));
    }
}
