package org.videolan.vlc.webserver;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.application.Plugin;
import io.ktor.server.auth.Authentication;
import io.ktor.server.engine.ApplicationEngineEnvironmentBuilder;
import io.ktor.server.engine.ConnectorType;
import io.ktor.server.engine.EngineConnectorBuilder;
import io.ktor.server.engine.EngineConnectorConfig;
import io.ktor.server.engine.EngineSSLConnectorBuilder;
import io.ktor.server.plugins.cors.routing.CORSKt;
import io.ktor.server.plugins.partialcontent.PartialContentKt;
import io.ktor.server.routing.Routing;
import io.ktor.server.routing.RoutingKt;
import io.ktor.server.sessions.CookieIdSessionBuilder;
import io.ktor.server.sessions.DirectoryStorageKt;
import io.ktor.server.sessions.SessionStorage;
import io.ktor.server.sessions.SessionTransportTransformerEncrypt;
import io.ktor.server.sessions.SessionsBuilderKt;
import io.ktor.server.sessions.SessionsConfig;
import io.ktor.server.sessions.SessionsKt;
import io.ktor.server.websocket.WebSockets;
import io.ktor.util.CryptoKt;
import io.ktor.util.pipeline.Pipeline;
import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.webserver.ssl.SecretGenerator;
import org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateServer$environment$1 extends Lambda implements Function1<ApplicationEngineEnvironmentBuilder, Unit> {
    final /* synthetic */ char[] $password;
    final /* synthetic */ KeyStore $store;
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$generateServer$environment$1(KeyStore keyStore, RemoteAccessServer remoteAccessServer, char[] cArr) {
        super(1);
        this.$store = keyStore;
        this.this$0 = remoteAccessServer;
        this.$password = cArr;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngineEnvironmentBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "$this$applicationEngineEnvironment");
        Logger logger = LoggerFactory.getLogger("ktor.application");
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(...)");
        applicationEngineEnvironmentBuilder.setLog(logger);
        RemoteAccessServer remoteAccessServer = this.this$0;
        List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
        EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder((ConnectorType) null, 1, (DefaultConstructorMarker) null);
        engineConnectorBuilder.setPort(remoteAccessServer.getFreePort(8080));
        connectors.add(engineConnectorBuilder);
        KeyStore keyStore = this.$store;
        final char[] cArr = this.$password;
        final char[] cArr2 = this.$password;
        RemoteAccessServer remoteAccessServer2 = this.this$0;
        List<EngineConnectorConfig> connectors2 = applicationEngineEnvironmentBuilder.getConnectors();
        EngineSSLConnectorBuilder engineSSLConnectorBuilder = new EngineSSLConnectorBuilder(keyStore, "vlc-android", new Function0<char[]>() {
            public final char[] invoke() {
                return cArr;
            }
        }, new Function0<char[]>() {
            public final char[] invoke() {
                return cArr2;
            }
        });
        engineSSLConnectorBuilder.setPort(remoteAccessServer2.getFreePort(8443));
        connectors2.add(engineSSLConnectorBuilder);
        final RemoteAccessServer remoteAccessServer3 = this.this$0;
        applicationEngineEnvironmentBuilder.module(new Function1<Application, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Application) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Application application) {
                Intrinsics.checkNotNullParameter(application, "$this$module");
                Pipeline pipeline = application;
                final RemoteAccessServer remoteAccessServer = remoteAccessServer3;
                ApplicationPluginKt.install(pipeline, SessionsKt.getSessions(), new Function1<SessionsConfig, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((SessionsConfig) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SessionsConfig sessionsConfig) {
                        SessionsConfig sessionsConfig2 = sessionsConfig;
                        Intrinsics.checkNotNullParameter(sessionsConfig2, "$this$install");
                        String str = "";
                        String string = remoteAccessServer.settings.getString("cookie_encrypt_key", str);
                        if (string == null) {
                            string = str;
                        }
                        if (StringsKt.isBlank(string)) {
                            string = SecretGenerator.INSTANCE.generateRandomAlphanumericString(32);
                            SettingsKt.putSingle(remoteAccessServer.settings, "cookie_encrypt_key", string);
                        }
                        String string2 = remoteAccessServer.settings.getString("cookie_sign_key", str);
                        if (string2 != null) {
                            str = string2;
                        }
                        if (StringsKt.isBlank(str)) {
                            str = SecretGenerator.INSTANCE.generateRandomAlphanumericString(32);
                            SettingsKt.putSingle(remoteAccessServer.settings, "cookie_sign_key", str);
                        }
                        SessionStorage directorySessionStorage = DirectoryStorageKt.directorySessionStorage(new File(remoteAccessServer.context.getFilesDir().getPath() + "/server/cache"), true);
                        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(UserSession.class);
                        CookieIdSessionBuilder cookieIdSessionBuilder = new CookieIdSessionBuilder(orCreateKotlinClass, Reflection.typeOf(UserSession.class));
                        cookieIdSessionBuilder.getCookie().setMaxAgeInSeconds(RemoteAccessSession.INSTANCE.getMaxAge());
                        cookieIdSessionBuilder.transform(new SessionTransportTransformerEncrypt(CryptoKt.hex(string), CryptoKt.hex(str), (Function1) null, (String) null, (String) null, 28, (DefaultConstructorMarker) null));
                        SessionsBuilderKt.cookie(sessionsConfig2, "user_session", cookieIdSessionBuilder, orCreateKotlinClass, directorySessionStorage);
                    }
                });
                ApplicationPluginKt.install(pipeline, Authentication.Companion, AnonymousClass2.INSTANCE);
                ApplicationPluginKt.install$default(pipeline, (Plugin) remoteAccessServer3.InterceptorPlugin, (Function1) null, 2, (Object) null);
                ApplicationPluginKt.install(pipeline, WebSockets.Plugin, AnonymousClass3.INSTANCE);
                ApplicationPluginKt.install(pipeline, CORSKt.getCORS(), AnonymousClass4.INSTANCE);
                ApplicationPluginKt.install$default(pipeline, (Plugin) PartialContentKt.getPartialContent(), (Function1) null, 2, (Object) null);
                final RemoteAccessServer remoteAccessServer2 = remoteAccessServer3;
                RoutingKt.routing(application, new Function1<Routing, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Routing) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Routing routing) {
                        Intrinsics.checkNotNullParameter(routing, "$this$routing");
                        RemoteAccessRoutingKt.setupRouting(routing, remoteAccessServer2.context, remoteAccessServer2.scope);
                        RemoteAccessWebSockets.INSTANCE.setupWebSockets(routing, remoteAccessServer2.context, remoteAccessServer2.settings);
                    }
                });
            }
        });
    }
}
