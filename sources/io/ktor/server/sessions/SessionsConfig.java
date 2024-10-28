package io.ktor.server.sessions;

import io.ktor.util.KtorDsl;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0005R\u001b\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R&\u0010\b\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\tj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/server/sessions/SessionsConfig;", "", "()V", "providers", "", "Lio/ktor/server/sessions/SessionProvider;", "getProviders", "()Ljava/util/List;", "registered", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "register", "", "provider", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: SessionsConfig.kt */
public final class SessionsConfig {
    private final ArrayList<SessionProvider<?>> registered = new ArrayList<>();

    public final List<SessionProvider<?>> getProviders() {
        return CollectionsKt.toList(this.registered);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: io.ktor.server.sessions.SessionProvider} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: io.ktor.server.sessions.SessionProvider} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: io.ktor.server.sessions.SessionProvider} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: io.ktor.server.sessions.SessionProvider} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void register(io.ktor.server.sessions.SessionProvider<?> r7) {
        /*
            r6 = this;
            java.lang.String r0 = "provider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.ArrayList<io.ktor.server.sessions.SessionProvider<?>> r0 = r6.registered
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x000d:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x002a
            java.lang.Object r1 = r0.next()
            r3 = r1
            io.ktor.server.sessions.SessionProvider r3 = (io.ktor.server.sessions.SessionProvider) r3
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = r7.getName()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x000d
            goto L_0x002b
        L_0x002a:
            r1 = r2
        L_0x002b:
            io.ktor.server.sessions.SessionProvider r1 = (io.ktor.server.sessions.SessionProvider) r1
            java.lang.String r0 = ": "
            if (r1 != 0) goto L_0x007d
            java.util.ArrayList<io.ktor.server.sessions.SessionProvider<?>> r1 = r6.registered
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0039:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0055
            java.lang.Object r3 = r1.next()
            r4 = r3
            io.ktor.server.sessions.SessionProvider r4 = (io.ktor.server.sessions.SessionProvider) r4
            kotlin.reflect.KClass r4 = r4.getType()
            kotlin.reflect.KClass r5 = r7.getType()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0039
            r2 = r3
        L_0x0055:
            io.ktor.server.sessions.SessionProvider r2 = (io.ktor.server.sessions.SessionProvider) r2
            if (r2 != 0) goto L_0x005f
            java.util.ArrayList<io.ktor.server.sessions.SessionProvider<?>> r0 = r6.registered
            r0.add(r7)
            return
        L_0x005f:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "There is already registered session provider for type "
            r3.<init>(r4)
            kotlin.reflect.KClass r7 = r7.getType()
            r3.append(r7)
            r3.append(r0)
            r3.append(r2)
            java.lang.String r7 = r3.toString()
            r1.<init>(r7)
            throw r1
        L_0x007d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "There is already registered session provider with name "
            r3.<init>(r4)
            java.lang.String r7 = r7.getName()
            r3.append(r7)
            r3.append(r0)
            r3.append(r1)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7)
            goto L_0x009c
        L_0x009b:
            throw r2
        L_0x009c:
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionsConfig.register(io.ktor.server.sessions.SessionProvider):void");
    }
}
