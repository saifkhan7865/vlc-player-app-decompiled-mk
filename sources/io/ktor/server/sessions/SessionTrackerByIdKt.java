package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\u0002H\b\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0001\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\b"}, d2 = {"sessionId", "", "Lio/ktor/server/application/ApplicationCall;", "getSessionId", "(Lio/ktor/server/application/ApplicationCall;)Ljava/lang/String;", "SessionType", "", "name", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTrackerById.kt */
public final class SessionTrackerByIdKt {
    public static final /* synthetic */ <SessionType> String sessionId(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        CurrentSession sessions = SessionDataKt.getSessions(applicationCall);
        Intrinsics.reifiedOperationMarker(4, "SessionType");
        return sessionId(applicationCall, sessions.findName(Reflection.getOrCreateKotlinClass(Object.class)));
    }

    public static final String getSessionId(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Collection arrayList = new ArrayList();
        for (Object next : (Iterable) applicationCall.getApplication().getAttributes().get(SessionsKt.getSessionProvidersKey())) {
            if (((SessionProvider) next).getTracker() instanceof SessionTrackerById) {
                arrayList.add(next);
            }
        }
        List list = (List) arrayList;
        int size = list.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return sessionId(applicationCall, ((SessionProvider) list.get(0)).getName());
        }
        throw new IllegalStateException("Multiple session providers installed. Please use sessionId<S>() function instead.".toString());
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [io.ktor.server.sessions.SessionTracker] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String sessionId(io.ktor.server.application.ApplicationCall r4, java.lang.String r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            io.ktor.server.application.Application r0 = r4.getApplication()
            io.ktor.util.Attributes r0 = r0.getAttributes()
            io.ktor.util.AttributeKey r1 = io.ktor.server.sessions.SessionsKt.getSessionProvidersKey()
            java.lang.Object r0 = r0.get(r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0020:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0039
            java.lang.Object r1 = r0.next()
            r3 = r1
            io.ktor.server.sessions.SessionProvider r3 = (io.ktor.server.sessions.SessionProvider) r3
            java.lang.String r3 = r3.getName()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)
            if (r3 == 0) goto L_0x0020
            goto L_0x003a
        L_0x0039:
            r1 = r2
        L_0x003a:
            io.ktor.server.sessions.SessionProvider r1 = (io.ktor.server.sessions.SessionProvider) r1
            if (r1 == 0) goto L_0x0077
            io.ktor.server.sessions.SessionTracker r0 = r1.getTracker()
            boolean r1 = r0 instanceof io.ktor.server.sessions.SessionTrackerById
            if (r1 == 0) goto L_0x0049
            r2 = r0
            io.ktor.server.sessions.SessionTrackerById r2 = (io.ktor.server.sessions.SessionTrackerById) r2
        L_0x0049:
            if (r2 == 0) goto L_0x005a
            io.ktor.util.Attributes r4 = r4.getAttributes()
            io.ktor.util.AttributeKey r5 = r2.getSessionIdKey$ktor_server_sessions()
            java.lang.Object r4 = r4.getOrNull(r5)
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L_0x005a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Provider "
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = " doesn't use session IDs"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0077:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No session provider "
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = " found."
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            goto L_0x0095
        L_0x0094:
            throw r4
        L_0x0095:
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionTrackerByIdKt.sessionId(io.ktor.server.application.ApplicationCall, java.lang.String):java.lang.String");
    }
}
