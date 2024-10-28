package io.ktor.server.netty;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.CommandLineKt;
import io.ktor.server.netty.NettyApplicationEngine;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\u0004*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/netty/EngineMain;", "", "()V", "main", "", "args", "", "", "([Ljava/lang/String;)V", "loadConfiguration", "Lio/ktor/server/netty/NettyApplicationEngine$Configuration;", "config", "Lio/ktor/server/config/ApplicationConfig;", "loadConfiguration$ktor_server_netty", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineMain.kt */
public final class EngineMain {
    public static final EngineMain INSTANCE = new EngineMain();

    private EngineMain() {
    }

    @JvmStatic
    public static final void main(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "args");
        ApplicationEngineEnvironment commandLineEnvironment = CommandLineKt.commandLineEnvironment(strArr);
        new NettyApplicationEngine(commandLineEnvironment, new EngineMain$main$engine$1(commandLineEnvironment)).start(true);
    }

    public final void loadConfiguration$ktor_server_netty(NettyApplicationEngine.Configuration configuration, ApplicationConfig applicationConfig) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String string9;
        Intrinsics.checkNotNullParameter(configuration, "<this>");
        Intrinsics.checkNotNullParameter(applicationConfig, "config");
        ApplicationConfig config = applicationConfig.config("ktor.deployment");
        CommandLineKt.loadCommonConfiguration(configuration, config);
        ApplicationConfigValue propertyOrNull = config.propertyOrNull("requestQueueLimit");
        if (!(propertyOrNull == null || (string9 = propertyOrNull.getString()) == null)) {
            configuration.setRequestQueueLimit(Integer.parseInt(string9));
        }
        ApplicationConfigValue propertyOrNull2 = config.propertyOrNull("runningLimit");
        if (!(propertyOrNull2 == null || (string8 = propertyOrNull2.getString()) == null)) {
            configuration.setRunningLimit(Integer.parseInt(string8));
        }
        ApplicationConfigValue propertyOrNull3 = config.propertyOrNull("shareWorkGroup");
        if (!(propertyOrNull3 == null || (string7 = propertyOrNull3.getString()) == null)) {
            configuration.setShareWorkGroup(Boolean.parseBoolean(string7));
        }
        ApplicationConfigValue propertyOrNull4 = config.propertyOrNull("responseWriteTimeoutSeconds");
        if (!(propertyOrNull4 == null || (string6 = propertyOrNull4.getString()) == null)) {
            configuration.setResponseWriteTimeoutSeconds(Integer.parseInt(string6));
        }
        ApplicationConfigValue propertyOrNull5 = config.propertyOrNull("requestReadTimeoutSeconds");
        if (!(propertyOrNull5 == null || (string5 = propertyOrNull5.getString()) == null)) {
            configuration.setRequestReadTimeoutSeconds(Integer.parseInt(string5));
        }
        ApplicationConfigValue propertyOrNull6 = config.propertyOrNull("tcpKeepAlive");
        if (!(propertyOrNull6 == null || (string4 = propertyOrNull6.getString()) == null)) {
            configuration.setTcpKeepAlive(Boolean.parseBoolean(string4));
        }
        ApplicationConfigValue propertyOrNull7 = config.propertyOrNull("maxInitialLineLength");
        if (!(propertyOrNull7 == null || (string3 = propertyOrNull7.getString()) == null)) {
            configuration.setMaxInitialLineLength(Integer.parseInt(string3));
        }
        ApplicationConfigValue propertyOrNull8 = config.propertyOrNull("maxHeaderSize");
        if (!(propertyOrNull8 == null || (string2 = propertyOrNull8.getString()) == null)) {
            configuration.setMaxHeaderSize(Integer.parseInt(string2));
        }
        ApplicationConfigValue propertyOrNull9 = config.propertyOrNull("maxChunkSize");
        if (propertyOrNull9 != null && (string = propertyOrNull9.getString()) != null) {
            configuration.setMaxChunkSize(Integer.parseInt(string));
        }
    }
}
