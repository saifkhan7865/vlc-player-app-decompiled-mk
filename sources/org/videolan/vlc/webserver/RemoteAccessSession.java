package org.videolan.vlc.webserver;

import android.content.SharedPreferences;
import androidx.core.app.NotificationCompat;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.sessions.SessionDataKt;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.webserver.ssl.SecretGenerator;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J&\u0010\u0012\u001a\u00020\r*\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0\u00132\u0006\u0010\n\u001a\u00020\u000bH@¢\u0006\u0002\u0010\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/webserver/RemoteAccessSession;", "", "()V", "maxAge", "", "getMaxAge", "()J", "getSessions", "", "Lorg/videolan/vlc/webserver/UserSession;", "settings", "Landroid/content/SharedPreferences;", "injectCookie", "", "call", "Lio/ktor/server/application/ApplicationCall;", "saveSessions", "newList", "verifyLogin", "Lio/ktor/util/pipeline/PipelineContext;", "(Lio/ktor/util/pipeline/PipelineContext;Landroid/content/SharedPreferences;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessSession.kt */
public final class RemoteAccessSession {
    public static final RemoteAccessSession INSTANCE = new RemoteAccessSession();
    private static final long maxAge = 31536000;

    private RemoteAccessSession() {
    }

    public final long getMaxAge() {
        return maxAge;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: org.videolan.vlc.webserver.UserSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: org.videolan.vlc.webserver.UserSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: org.videolan.vlc.webserver.UserSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: org.videolan.vlc.webserver.UserSession} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object verifyLogin(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r11, android.content.SharedPreferences r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            org.videolan.vlc.webserver.RemoteAccessServer$Companion r0 = org.videolan.vlc.webserver.RemoteAccessServer.Companion
            boolean r0 = r0.getByPassAuth()
            if (r0 == 0) goto L_0x000b
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x000b:
            java.util.List r0 = r10.getSessions(r12)
            java.lang.Object r1 = r11.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            io.ktor.server.sessions.CurrentSession r1 = io.ktor.server.sessions.SessionDataKt.getSessions(r1)
            java.lang.String r2 = "user_session"
            java.lang.Object r1 = r1.get(r2)
            boolean r3 = r1 instanceof org.videolan.vlc.webserver.UserSession
            r4 = 0
            if (r3 == 0) goto L_0x0027
            org.videolan.vlc.webserver.UserSession r1 = (org.videolan.vlc.webserver.UserSession) r1
            goto L_0x0028
        L_0x0027:
            r1 = r4
        L_0x0028:
            if (r1 == 0) goto L_0x0052
            r3 = r0
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x0031:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x004d
            java.lang.Object r5 = r3.next()
            r6 = r5
            org.videolan.vlc.webserver.UserSession r6 = (org.videolan.vlc.webserver.UserSession) r6
            java.lang.String r6 = r6.getId()
            java.lang.String r7 = r1.getId()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r6 == 0) goto L_0x0031
            goto L_0x004e
        L_0x004d:
            r5 = r4
        L_0x004e:
            if (r5 == 0) goto L_0x0052
            r3 = 1
            goto L_0x0053
        L_0x0052:
            r3 = 0
        L_0x0053:
            if (r1 == 0) goto L_0x008b
            r5 = r0
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x005c:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0078
            java.lang.Object r6 = r5.next()
            r7 = r6
            org.videolan.vlc.webserver.UserSession r7 = (org.videolan.vlc.webserver.UserSession) r7
            java.lang.String r7 = r7.getId()
            java.lang.String r8 = r1.getId()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x005c
            r4 = r6
        L_0x0078:
            org.videolan.vlc.webserver.UserSession r4 = (org.videolan.vlc.webserver.UserSession) r4
            if (r4 == 0) goto L_0x008b
            long r5 = java.lang.System.currentTimeMillis()
            org.videolan.vlc.webserver.RemoteAccessSession r7 = INSTANCE
            long r8 = maxAge
            long r5 = r5 + r8
            r4.setMaxAge(r5)
            r7.saveSessions(r12, r0)
        L_0x008b:
            if (r3 != 0) goto L_0x00d9
            java.lang.Object r11 = r11.getContext()
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
            io.ktor.http.HttpStatusCode$Companion r12 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r12 = r12.getUnauthorized()
            boolean r0 = r12 instanceof io.ktor.http.content.OutgoingContent
            if (r0 != 0) goto L_0x00bc
            boolean r0 = r12 instanceof byte[]
            if (r0 != 0) goto L_0x00bc
            io.ktor.server.response.ApplicationResponse r0 = r11.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r1 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r1 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r1)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r1)
            java.lang.Class<io.ktor.http.HttpStatusCode> r3 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            io.ktor.util.reflect.TypeInfo r1 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r3, r1)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r0, r1)
        L_0x00bc:
            io.ktor.server.response.ApplicationResponse r0 = r11.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r0 = r0.getPipeline()
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12, r1)
            java.lang.Object r12 = (java.lang.Object) r12
            java.lang.Object r11 = r0.execute(r11, r12, r13)
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r11 != r12) goto L_0x00d6
            return r11
        L_0x00d6:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00d9:
            java.lang.Object r11 = r11.getContext()
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
            io.ktor.server.sessions.CurrentSession r11 = io.ktor.server.sessions.SessionDataKt.getSessions(r11)
            org.videolan.vlc.webserver.UserSession r12 = new org.videolan.vlc.webserver.UserSession
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r13 = r1.getId()
            long r0 = r1.getMaxAge()
            r12.<init>(r13, r0)
            r11.set(r2, r12)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessSession.verifyLogin(io.ktor.util.pipeline.PipelineContext, android.content.SharedPreferences, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final List<UserSession> getSessions(SharedPreferences sharedPreferences) {
        String str = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        String string = sharedPreferences.getString("valid_sessions", str);
        if (string != null) {
            str = string;
        }
        Moshi build = new Moshi.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        JsonAdapter adapter = build.adapter((Type) Types.newParameterizedType(List.class, UserSession.class));
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        List<UserSession> list = (List) adapter.fromJson(str);
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        RemoteAccessSession remoteAccessSession = INSTANCE;
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (((UserSession) next).getMaxAge() > System.currentTimeMillis() + ((long) 3600)) {
                arrayList.add(next);
            }
        }
        remoteAccessSession.saveSessions(sharedPreferences, (List) arrayList);
        return list;
    }

    private final void saveSessions(SharedPreferences sharedPreferences, List<UserSession> list) {
        Moshi build = new Moshi.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        JsonAdapter adapter = build.adapter((Type) Types.newParameterizedType(List.class, UserSession.class));
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        String json = adapter.toJson(list);
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        SettingsKt.putSingle(sharedPreferences, "valid_sessions", json);
    }

    public final void injectCookie(ApplicationCall applicationCall, SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(sharedPreferences, "settings");
        UserSession userSession = new UserSession(SecretGenerator.INSTANCE.generateRandomString(), System.currentTimeMillis() + maxAge);
        List mutableList = CollectionsKt.toMutableList(getSessions(sharedPreferences));
        mutableList.add(userSession);
        saveSessions(sharedPreferences, CollectionsKt.toList(mutableList));
        SessionDataKt.getSessions(applicationCall).set("user_session", userSession);
    }
}
