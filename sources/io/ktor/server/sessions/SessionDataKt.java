package io.ktor.server.sessions;

import io.ktor.http.ContentDisposition;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.util.AttributeKey;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0006\u001a\u0019\u0010\n\u001a\u00020\u000b\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\r*\u00020\u0006H\b\u001a \u0010\u000e\u001a\u0004\u0018\u0001H\f\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\r*\u00020\u0006H\b¢\u0006\u0002\u0010\u000f\u001a)\u0010\u000e\u001a\u0004\u0018\u0001H\f\"\b\b\u0000\u0010\f*\u00020\r*\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u0011¢\u0006\u0002\u0010\u0012\u001a9\u0010\u0013\u001a\u0002H\f\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\r*\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\f0\u0017H\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a3\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001a\"\b\b\u0000\u0010\u001b*\u00020\r*\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H@ø\u0001\u0001¢\u0006\u0002\u0010\u001e\u001a\f\u0010\u001f\u001a\u00020 *\u00020\u0007H\u0002\u001a-\u0010!\u001a\u00020\u000b\"\b\b\u0000\u0010\u001b*\u00020\r*\b\u0012\u0004\u0012\u0002H\u001b0\u001a2\u0006\u0010\u001d\u001a\u00020\u0007H@ø\u0001\u0001¢\u0006\u0002\u0010\"\u001a(\u0010#\u001a\u00020\u000b\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\r*\u00020\u00062\b\u0010$\u001a\u0004\u0018\u0001H\fH\b¢\u0006\u0002\u0010%\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0002\u000b\n\u0005\b20\u0001\n\u0002\b\u0019¨\u0006&"}, d2 = {"SessionDataKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/sessions/SessionData;", "getSessionDataKey", "()Lio/ktor/util/AttributeKey;", "sessions", "Lio/ktor/server/sessions/CurrentSession;", "Lio/ktor/server/application/ApplicationCall;", "getSessions", "(Lio/ktor/server/application/ApplicationCall;)Lio/ktor/server/sessions/CurrentSession;", "clear", "", "T", "", "get", "(Lio/ktor/server/sessions/CurrentSession;)Ljava/lang/Object;", "klass", "Lkotlin/reflect/KClass;", "(Lio/ktor/server/sessions/CurrentSession;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "getOrSet", "name", "", "generator", "Lkotlin/Function0;", "(Lio/ktor/server/sessions/CurrentSession;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "receiveSessionData", "Lio/ktor/server/sessions/SessionProviderData;", "S", "Lio/ktor/server/sessions/SessionProvider;", "call", "(Lio/ktor/server/sessions/SessionProvider;Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reportMissingSession", "", "sendSessionData", "(Lio/ktor/server/sessions/SessionProviderData;Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "set", "value", "(Lio/ktor/server/sessions/CurrentSession;Ljava/lang/Object;)V", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionData.kt */
public final class SessionDataKt {
    private static final AttributeKey<SessionData> SessionDataKey = new AttributeKey<>("SessionKey");

    public static final CurrentSession getSessions(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        SessionData sessionData = (SessionData) applicationCall.getAttributes().getOrNull(SessionDataKey);
        if (sessionData != null) {
            return sessionData;
        }
        reportMissingSession(applicationCall);
        throw new KotlinNothingValueException();
    }

    public static final /* synthetic */ <T> void set(CurrentSession currentSession, T t) {
        Intrinsics.checkNotNullParameter(currentSession, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        currentSession.set(currentSession.findName(Reflection.getOrCreateKotlinClass(Object.class)), t);
    }

    public static final /* synthetic */ <T> T get(CurrentSession currentSession) {
        Intrinsics.checkNotNullParameter(currentSession, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        return get(currentSession, Reflection.getOrCreateKotlinClass(Object.class));
    }

    public static final <T> T get(CurrentSession currentSession, KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(currentSession, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "klass");
        return currentSession.get(currentSession.findName(kClass));
    }

    public static final /* synthetic */ <T> void clear(CurrentSession currentSession) {
        Intrinsics.checkNotNullParameter(currentSession, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        currentSession.clear(currentSession.findName(Reflection.getOrCreateKotlinClass(Object.class)));
    }

    public static /* synthetic */ Object getOrSet$default(CurrentSession currentSession, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "T");
            str = currentSession.findName(Reflection.getOrCreateKotlinClass(Object.class));
        }
        Intrinsics.checkNotNullParameter(currentSession, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, "generator");
        Intrinsics.reifiedOperationMarker(4, "T");
        Object obj2 = get(currentSession, Reflection.getOrCreateKotlinClass(Object.class));
        if (obj2 != null) {
            return obj2;
        }
        Object invoke = function0.invoke();
        currentSession.set(str, invoke);
        return invoke;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <S> java.lang.Object receiveSessionData(io.ktor.server.sessions.SessionProvider<S> r5, io.ktor.server.application.ApplicationCall r6, kotlin.coroutines.Continuation<? super io.ktor.server.sessions.SessionProviderData<S>> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.sessions.SessionDataKt$receiveSessionData$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.sessions.SessionDataKt$receiveSessionData$1 r0 = (io.ktor.server.sessions.SessionDataKt$receiveSessionData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.sessions.SessionDataKt$receiveSessionData$1 r0 = new io.ktor.server.sessions.SessionDataKt$receiveSessionData$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.server.sessions.SessionProvider r6 = (io.ktor.server.sessions.SessionProvider) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005a
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            io.ktor.server.sessions.SessionTransport r7 = r5.getTransport()
            java.lang.String r7 = r7.receive(r6)
            io.ktor.server.sessions.SessionTracker r2 = r5.getTracker()
            r0.L$0 = r5
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r6 = r2.load(r6, r7, r0)
            if (r6 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
        L_0x005a:
            if (r5 == 0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r3 = 0
        L_0x005e:
            io.ktor.server.sessions.SessionProviderData r5 = new io.ktor.server.sessions.SessionProviderData
            r0 = 0
            r5.<init>(r7, r0, r3, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionDataKt.receiveSessionData(io.ktor.server.sessions.SessionProvider, io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: io.ktor.server.application.ApplicationCall} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <S> java.lang.Object sendSessionData(io.ktor.server.sessions.SessionProviderData<S> r5, io.ktor.server.application.ApplicationCall r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.sessions.SessionDataKt$sendSessionData$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.sessions.SessionDataKt$sendSessionData$1 r0 = (io.ktor.server.sessions.SessionDataKt$sendSessionData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.sessions.SessionDataKt$sendSessionData$1 r0 = new io.ktor.server.sessions.SessionDataKt$sendSessionData$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0096
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r5 = r0.L$1
            r6 = r5
            io.ktor.server.application.ApplicationCall r6 = (io.ktor.server.application.ApplicationCall) r6
            java.lang.Object r5 = r0.L$0
            io.ktor.server.sessions.SessionProviderData r5 = (io.ktor.server.sessions.SessionProviderData) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0064
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r5.getOldValue()
            java.lang.Object r2 = r5.getNewValue()
            if (r2 == 0) goto L_0x0072
            io.ktor.server.sessions.SessionProvider r7 = r5.getProvider()
            io.ktor.server.sessions.SessionTracker r7 = r7.getTracker()
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r7.store(r6, r2, r0)
            if (r7 != r1) goto L_0x0064
            return r1
        L_0x0064:
            java.lang.String r7 = (java.lang.String) r7
            io.ktor.server.sessions.SessionProvider r5 = r5.getProvider()
            io.ktor.server.sessions.SessionTransport r5 = r5.getTransport()
            r5.send(r6, r7)
            goto L_0x0099
        L_0x0072:
            boolean r2 = r5.getIncoming()
            if (r2 == 0) goto L_0x0099
            if (r7 != 0) goto L_0x0099
            io.ktor.server.sessions.SessionProvider r7 = r5.getProvider()
            io.ktor.server.sessions.SessionTransport r7 = r7.getTransport()
            r7.clear(r6)
            io.ktor.server.sessions.SessionProvider r5 = r5.getProvider()
            io.ktor.server.sessions.SessionTracker r5 = r5.getTracker()
            r0.label = r3
            java.lang.Object r5 = r5.clear(r6, r0)
            if (r5 != r1) goto L_0x0096
            return r1
        L_0x0096:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0099:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionDataKt.sendSessionData(io.ktor.server.sessions.SessionProviderData, io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final AttributeKey<SessionData> getSessionDataKey() {
        return SessionDataKey;
    }

    private static final Void reportMissingSession(ApplicationCall applicationCall) {
        ApplicationPluginKt.plugin(applicationCall.getApplication(), SessionsKt.getSessions());
        throw new SessionNotYetConfiguredException();
    }

    public static final /* synthetic */ <T> T getOrSet(CurrentSession currentSession, String str, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(currentSession, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, "generator");
        Intrinsics.reifiedOperationMarker(4, "T");
        T t = get(currentSession, Reflection.getOrCreateKotlinClass(Object.class));
        if (t != null) {
            return t;
        }
        T invoke = function0.invoke();
        currentSession.set(str, invoke);
        return invoke;
    }
}
