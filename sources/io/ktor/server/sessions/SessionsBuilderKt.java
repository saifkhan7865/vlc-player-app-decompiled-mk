package io.ktor.server.sessions;

import io.ktor.http.ContentDisposition;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a!\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001aB\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001\u001aC\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a)\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\b\u001aK\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a,\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\nH\u0007\u001a:\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0001\u001aO\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a4\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001aW\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a!\u0010\u0011\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001aC\u0010\u0011\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a)\u0010\u0011\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\b\u001aK\u0010\u0011\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a,\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\nH\u0007\u001aO\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001a4\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001aW\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0010H\bø\u0001\u0000\u001aD\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0012H\u0001\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0014"}, d2 = {"cookie", "", "S", "", "Lio/ktor/server/sessions/SessionsConfig;", "name", "", "builder", "Lio/ktor/server/sessions/CookieIdSessionBuilder;", "sessionType", "Lkotlin/reflect/KClass;", "storage", "Lio/ktor/server/sessions/SessionStorage;", "block", "Lkotlin/Function1;", "Lio/ktor/server/sessions/CookieSessionBuilder;", "Lkotlin/ExtensionFunctionType;", "header", "Lio/ktor/server/sessions/HeaderSessionBuilder;", "Lio/ktor/server/sessions/HeaderIdSessionBuilder;", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsBuilder.kt */
public final class SessionsBuilderKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified types instead.")
    public static final <S> void cookie(SessionsConfig sessionsConfig, String str, KClass<S> kClass, SessionStorage sessionStorage) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        cookie(sessionsConfig, str, new CookieIdSessionBuilder(kClass), kClass, sessionStorage);
    }

    public static final /* synthetic */ <S> void cookie(SessionsConfig sessionsConfig, String str, SessionStorage sessionStorage) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        cookie(sessionsConfig, str, new CookieIdSessionBuilder(orCreateKotlinClass, (KType) null), orCreateKotlinClass, sessionStorage);
    }

    public static final <S> void cookie(SessionsConfig sessionsConfig, String str, CookieIdSessionBuilder<S> cookieIdSessionBuilder, KClass<S> kClass, SessionStorage sessionStorage) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(cookieIdSessionBuilder, "builder");
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        sessionsConfig.register(new SessionProvider(str, kClass, new SessionTransportCookie(str, cookieIdSessionBuilder.getCookie(), cookieIdSessionBuilder.getTransformers()), new SessionTrackerById(kClass, cookieIdSessionBuilder.getSerializer(), sessionStorage, cookieIdSessionBuilder.getSessionIdProvider())));
    }

    public static final /* synthetic */ <S> void cookie(SessionsConfig sessionsConfig, String str, SessionStorage sessionStorage, Function1<? super CookieIdSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.checkNotNullParameter(function1, "block");
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        CookieIdSessionBuilder cookieIdSessionBuilder = new CookieIdSessionBuilder(orCreateKotlinClass, (KType) null);
        function1.invoke(cookieIdSessionBuilder);
        CookieIdSessionBuilder cookieIdSessionBuilder2 = cookieIdSessionBuilder;
        cookie(sessionsConfig, str, cookieIdSessionBuilder, orCreateKotlinClass, sessionStorage);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified types instead.")
    public static final <S> void cookie(SessionsConfig sessionsConfig, String str, KClass<S> kClass, SessionStorage sessionStorage, Function1<? super CookieIdSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.checkNotNullParameter(function1, "block");
        CookieIdSessionBuilder cookieIdSessionBuilder = new CookieIdSessionBuilder(kClass);
        function1.invoke(cookieIdSessionBuilder);
        cookie(sessionsConfig, str, cookieIdSessionBuilder, kClass, sessionStorage);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified type instead.")
    public static final <S> void header(SessionsConfig sessionsConfig, String str, KClass<S> kClass, SessionStorage sessionStorage) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        header(sessionsConfig, str, kClass, sessionStorage, new HeaderIdSessionBuilder(kClass));
    }

    public static final /* synthetic */ <S> void header(SessionsConfig sessionsConfig, String str, SessionStorage sessionStorage) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        HeaderIdSessionBuilder headerIdSessionBuilder = new HeaderIdSessionBuilder(orCreateKotlinClass, (KType) null);
        HeaderIdSessionBuilder headerIdSessionBuilder2 = headerIdSessionBuilder;
        header(sessionsConfig, str, orCreateKotlinClass, sessionStorage, headerIdSessionBuilder);
    }

    public static final /* synthetic */ <S> void header(SessionsConfig sessionsConfig, String str, SessionStorage sessionStorage, Function1<? super HeaderIdSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.checkNotNullParameter(function1, "block");
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        HeaderIdSessionBuilder headerIdSessionBuilder = new HeaderIdSessionBuilder(orCreateKotlinClass, (KType) null);
        function1.invoke(headerIdSessionBuilder);
        HeaderIdSessionBuilder headerIdSessionBuilder2 = headerIdSessionBuilder;
        header(sessionsConfig, str, orCreateKotlinClass, sessionStorage, headerIdSessionBuilder);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified types instead.")
    public static final <S> void header(SessionsConfig sessionsConfig, String str, KClass<S> kClass, SessionStorage sessionStorage, Function1<? super HeaderIdSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(sessionStorage, "storage");
        Intrinsics.checkNotNullParameter(function1, "block");
        HeaderIdSessionBuilder headerIdSessionBuilder = new HeaderIdSessionBuilder(kClass);
        function1.invoke(headerIdSessionBuilder);
        header(sessionsConfig, str, kClass, sessionStorage, headerIdSessionBuilder);
    }

    public static final <S> void header(SessionsConfig sessionsConfig, String str, KClass<S> kClass, SessionStorage sessionStorage, HeaderSessionBuilder<S> headerSessionBuilder) {
        SessionTracker sessionTracker;
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(headerSessionBuilder, "builder");
        SessionTransportHeader sessionTransportHeader = new SessionTransportHeader(str, headerSessionBuilder.getTransformers());
        if (sessionStorage == null || !(headerSessionBuilder instanceof HeaderIdSessionBuilder)) {
            sessionTracker = new SessionTrackerByValue(kClass, headerSessionBuilder.getSerializer());
        } else {
            sessionTracker = new SessionTrackerById(kClass, headerSessionBuilder.getSerializer(), sessionStorage, ((HeaderIdSessionBuilder) headerSessionBuilder).getSessionIdProvider());
        }
        sessionsConfig.register(new SessionProvider(str, kClass, sessionTransportHeader, sessionTracker));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified type parameter instead.")
    public static final <S> void cookie(SessionsConfig sessionsConfig, String str, KClass<S> kClass) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        cookie(sessionsConfig, str, kClass, new CookieSessionBuilder(kClass));
    }

    public static final /* synthetic */ <S> void cookie(SessionsConfig sessionsConfig, String str) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        cookie(sessionsConfig, str, orCreateKotlinClass, new CookieSessionBuilder(orCreateKotlinClass, (KType) null));
    }

    public static final /* synthetic */ <S> void cookie(SessionsConfig sessionsConfig, String str, Function1<? super CookieSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "block");
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        CookieSessionBuilder cookieSessionBuilder = new CookieSessionBuilder(orCreateKotlinClass, (KType) null);
        function1.invoke(cookieSessionBuilder);
        CookieSessionBuilder cookieSessionBuilder2 = cookieSessionBuilder;
        cookie(sessionsConfig, str, orCreateKotlinClass, cookieSessionBuilder);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified type instead.")
    public static final <S> void cookie(SessionsConfig sessionsConfig, String str, KClass<S> kClass, Function1<? super CookieSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(function1, "block");
        CookieSessionBuilder cookieSessionBuilder = new CookieSessionBuilder(kClass);
        function1.invoke(cookieSessionBuilder);
        cookie(sessionsConfig, str, kClass, cookieSessionBuilder);
    }

    public static final <S> void cookie(SessionsConfig sessionsConfig, String str, KClass<S> kClass, CookieSessionBuilder<S> cookieSessionBuilder) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(cookieSessionBuilder, "builder");
        sessionsConfig.register(new SessionProvider(str, kClass, new SessionTransportCookie(str, cookieSessionBuilder.getCookie(), cookieSessionBuilder.getTransformers()), new SessionTrackerByValue(kClass, cookieSessionBuilder.getSerializer())));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified type instead.")
    public static final <S> void header(SessionsConfig sessionsConfig, String str, KClass<S> kClass) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        header(sessionsConfig, str, kClass, (SessionStorage) null, new HeaderSessionBuilder(kClass));
    }

    public static final /* synthetic */ <S> void header(SessionsConfig sessionsConfig, String str) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        HeaderSessionBuilder headerSessionBuilder = new HeaderSessionBuilder(orCreateKotlinClass, (KType) null);
        HeaderSessionBuilder headerSessionBuilder2 = headerSessionBuilder;
        header(sessionsConfig, str, orCreateKotlinClass, (SessionStorage) null, headerSessionBuilder);
    }

    public static final /* synthetic */ <S> void header(SessionsConfig sessionsConfig, String str, Function1<? super HeaderSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "block");
        Intrinsics.reifiedOperationMarker(4, "S");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, "S");
        HeaderSessionBuilder headerSessionBuilder = new HeaderSessionBuilder(orCreateKotlinClass, (KType) null);
        function1.invoke(headerSessionBuilder);
        HeaderSessionBuilder headerSessionBuilder2 = headerSessionBuilder;
        header(sessionsConfig, str, orCreateKotlinClass, (SessionStorage) null, headerSessionBuilder);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use reified type instead.")
    public static final <S> void header(SessionsConfig sessionsConfig, String str, KClass<S> kClass, Function1<? super HeaderSessionBuilder<S>, Unit> function1) {
        Intrinsics.checkNotNullParameter(sessionsConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "sessionType");
        Intrinsics.checkNotNullParameter(function1, "block");
        HeaderSessionBuilder headerSessionBuilder = new HeaderSessionBuilder(kClass);
        function1.invoke(headerSessionBuilder);
        sessionsConfig.register(new SessionProvider(str, kClass, new SessionTransportHeader(str, headerSessionBuilder.getTransformers()), new SessionTrackerByValue(kClass, headerSessionBuilder.getSerializer())));
    }
}
