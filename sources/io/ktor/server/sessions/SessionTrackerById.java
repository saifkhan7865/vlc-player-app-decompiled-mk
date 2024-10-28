package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B7\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010\fH@ø\u0001\u0000¢\u0006\u0002\u0010!J!\u0010\"\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010#\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\fH\u0016J\u0015\u0010&\u001a\u00020\u001b2\u0006\u0010#\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010'R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lio/ktor/server/sessions/SessionTrackerById;", "S", "", "Lio/ktor/server/sessions/SessionTracker;", "type", "Lkotlin/reflect/KClass;", "serializer", "Lio/ktor/server/sessions/SessionSerializer;", "storage", "Lio/ktor/server/sessions/SessionStorage;", "sessionIdProvider", "Lkotlin/Function0;", "", "(Lkotlin/reflect/KClass;Lio/ktor/server/sessions/SessionSerializer;Lio/ktor/server/sessions/SessionStorage;Lkotlin/jvm/functions/Function0;)V", "getSerializer", "()Lio/ktor/server/sessions/SessionSerializer;", "sessionIdKey", "Lio/ktor/util/AttributeKey;", "getSessionIdKey$ktor_server_sessions", "()Lio/ktor/util/AttributeKey;", "getSessionIdProvider", "()Lkotlin/jvm/functions/Function0;", "getStorage", "()Lio/ktor/server/sessions/SessionStorage;", "getType", "()Lkotlin/reflect/KClass;", "clear", "", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "transport", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "store", "value", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "validate", "(Ljava/lang/Object;)V", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTrackerById.kt */
public final class SessionTrackerById<S> implements SessionTracker<S> {
    private final SessionSerializer<S> serializer;
    private final AttributeKey<String> sessionIdKey = new AttributeKey<>("SessionId");
    private final Function0<String> sessionIdProvider;
    private final SessionStorage storage;
    private final KClass<S> type;

    public SessionTrackerById(KClass<S> kClass, SessionSerializer<S> sessionSerializer, SessionStorage sessionStorage, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        Intrinsics.checkNotNullParameter(sessionSerializer, "serializer");
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.checkNotNullParameter(function0, "sessionIdProvider");
        this.type = kClass;
        this.serializer = sessionSerializer;
        this.storage = sessionStorage;
        this.sessionIdProvider = function0;
    }

    public final KClass<S> getType() {
        return this.type;
    }

    public final SessionSerializer<S> getSerializer() {
        return this.serializer;
    }

    public final SessionStorage getStorage() {
        return this.storage;
    }

    public final Function0<String> getSessionIdProvider() {
        return this.sessionIdProvider;
    }

    public final AttributeKey<String> getSessionIdKey$ktor_server_sessions() {
        return this.sessionIdKey;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object load(io.ktor.server.application.ApplicationCall r6, java.lang.String r7, kotlin.coroutines.Continuation<? super S> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.server.sessions.SessionTrackerById$load$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.sessions.SessionTrackerById$load$1 r0 = (io.ktor.server.sessions.SessionTrackerById$load$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.sessions.SessionTrackerById$load$1 r0 = new io.ktor.server.sessions.SessionTrackerById$load$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 != r4) goto L_0x0035
            java.lang.Object r6 = r0.L$1
            io.ktor.server.application.ApplicationCall r6 = (io.ktor.server.application.ApplicationCall) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.server.sessions.SessionTrackerById r7 = (io.ktor.server.sessions.SessionTrackerById) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ NoSuchElementException -> 0x0033 }
            goto L_0x005c
        L_0x0033:
            r8 = move-exception
            goto L_0x0067
        L_0x0035:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r7 != 0) goto L_0x0043
            return r3
        L_0x0043:
            io.ktor.util.Attributes r8 = r6.getAttributes()
            io.ktor.util.AttributeKey<java.lang.String> r2 = r5.sessionIdKey
            r8.put(r2, r7)
            io.ktor.server.sessions.SessionStorage r8 = r5.storage     // Catch:{ NoSuchElementException -> 0x0065 }
            r0.L$0 = r5     // Catch:{ NoSuchElementException -> 0x0065 }
            r0.L$1 = r6     // Catch:{ NoSuchElementException -> 0x0065 }
            r0.label = r4     // Catch:{ NoSuchElementException -> 0x0065 }
            java.lang.Object r8 = r8.read(r7, r0)     // Catch:{ NoSuchElementException -> 0x0065 }
            if (r8 != r1) goto L_0x005b
            return r1
        L_0x005b:
            r7 = r5
        L_0x005c:
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NoSuchElementException -> 0x0033 }
            io.ktor.server.sessions.SessionSerializer<S> r0 = r7.serializer     // Catch:{ NoSuchElementException -> 0x0033 }
            java.lang.Object r6 = r0.deserialize(r8)     // Catch:{ NoSuchElementException -> 0x0033 }
            return r6
        L_0x0065:
            r8 = move-exception
            r7 = r5
        L_0x0067:
            io.ktor.server.application.Application r0 = r6.getApplication()
            org.slf4j.Logger r0 = io.ktor.server.application.ApplicationKt.getLog(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to lookup session: "
            r1.<init>(r2)
            java.lang.String r2 = r8.getMessage()
            if (r2 != 0) goto L_0x0080
            java.lang.String r2 = r8.toString()
        L_0x0080:
            r1.append(r2)
            java.lang.String r8 = ". The session id is wrong or outdated."
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.debug(r8)
            io.ktor.util.Attributes r6 = r6.getAttributes()
            io.ktor.util.AttributeKey<java.lang.String> r7 = r7.sessionIdKey
            r6.remove(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionTrackerById.load(io.ktor.server.application.ApplicationCall, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object store(io.ktor.server.application.ApplicationCall r5, S r6, kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.server.sessions.SessionTrackerById$store$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.sessions.SessionTrackerById$store$1 r0 = (io.ktor.server.sessions.SessionTrackerById$store$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.sessions.SessionTrackerById$store$1 r0 = new io.ktor.server.sessions.SessionTrackerById$store$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005a
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            io.ktor.util.Attributes r5 = r5.getAttributes()
            io.ktor.util.AttributeKey<java.lang.String> r7 = r4.sessionIdKey
            kotlin.jvm.functions.Function0<java.lang.String> r2 = r4.sessionIdProvider
            java.lang.Object r5 = r5.computeIfAbsent(r7, r2)
            java.lang.String r5 = (java.lang.String) r5
            io.ktor.server.sessions.SessionSerializer<S> r7 = r4.serializer
            java.lang.String r6 = r7.serialize(r6)
            io.ktor.server.sessions.SessionStorage r7 = r4.storage
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r7.write(r5, r6, r0)
            if (r6 != r1) goto L_0x005a
            return r1
        L_0x005a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionTrackerById.store(io.ktor.server.application.ApplicationCall, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object clear(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        String str = (String) applicationCall.getAttributes().takeOrNull(this.sessionIdKey);
        if (str == null) {
            return Unit.INSTANCE;
        }
        Object invalidate = this.storage.invalidate(str, continuation);
        return invalidate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invalidate : Unit.INSTANCE;
    }

    public void validate(S s) {
        Intrinsics.checkNotNullParameter(s, "value");
        if (!this.type.isInstance(s)) {
            throw new IllegalArgumentException("Value for this session tracker expected to be of type " + this.type + " but was " + s);
        }
    }

    public String toString() {
        return "SessionTrackerById: " + this.storage;
    }
}
