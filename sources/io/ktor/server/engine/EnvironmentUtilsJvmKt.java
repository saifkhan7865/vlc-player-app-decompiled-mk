package io.ktor.server.engine;

import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.MapApplicationConfig;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\t\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\u001f\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0002\u0010\b\u001aB\u0010\t\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0000¨\u0006\u0010"}, d2 = {"getConfigFromEnvironment", "Lio/ktor/server/config/ApplicationConfig;", "configurePlatformProperties", "", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "args", "", "", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;[Ljava/lang/String;)V", "configureSSLConnectors", "host", "sslPort", "sslKeyStorePath", "sslKeyStorePassword", "sslPrivateKeyPassword", "sslKeyAlias", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: EnvironmentUtilsJvm.kt */
public final class EnvironmentUtilsJvmKt {
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00af, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b0, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b3, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void configureSSLConnectors(io.ktor.server.engine.ApplicationEngineEnvironmentBuilder r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "host"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "sslPort"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "sslKeyAlias"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            if (r8 == 0) goto L_0x00c4
            if (r9 == 0) goto L_0x00bc
            if (r10 == 0) goto L_0x00b4
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0037
            boolean r1 = r0.isAbsolute()
            if (r1 == 0) goto L_0x002c
            goto L_0x0037
        L_0x002c:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "."
            r0.<init>(r1, r8)
            java.io.File r0 = r0.getAbsoluteFile()
        L_0x0037:
            java.lang.String r1 = "JKS"
            java.security.KeyStore r1 = java.security.KeyStore.getInstance(r1)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r0)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = r2
            java.io.FileInputStream r3 = (java.io.FileInputStream) r3     // Catch:{ all -> 0x00ad }
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ all -> 0x00ad }
            char[] r4 = io.ktor.util.CharsetKt.toCharArray(r9)     // Catch:{ all -> 0x00ad }
            r1.load(r3, r4)     // Catch:{ all -> 0x00ad }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ad }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            char[] r2 = io.ktor.util.CharsetKt.toCharArray(r10)
            java.security.Key r2 = r1.getKey(r11, r2)
            if (r2 == 0) goto L_0x008d
            java.lang.String r8 = "keyStore"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            io.ktor.server.engine.EnvironmentUtilsJvmKt$configureSSLConnectors$1 r8 = new io.ktor.server.engine.EnvironmentUtilsJvmKt$configureSSLConnectors$1
            r8.<init>(r9)
            kotlin.jvm.functions.Function0 r8 = (kotlin.jvm.functions.Function0) r8
            io.ktor.server.engine.EnvironmentUtilsJvmKt$configureSSLConnectors$2 r9 = new io.ktor.server.engine.EnvironmentUtilsJvmKt$configureSSLConnectors$2
            r9.<init>(r10)
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            java.util.List r5 = r5.getConnectors()
            io.ktor.server.engine.EngineSSLConnectorBuilder r10 = new io.ktor.server.engine.EngineSSLConnectorBuilder
            r10.<init>(r1, r11, r8, r9)
            r10.setHost(r6)
            int r6 = java.lang.Integer.parseInt(r7)
            r10.setPort(r6)
            r10.setKeyStorePath(r0)
            r5.add(r10)
            return
        L_0x008d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "The specified key "
            r5.<init>(r6)
            r5.append(r11)
            java.lang.String r6 = " doesn't exist in the key store "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L_0x00ad:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00af }
        L_0x00af:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r5)
            throw r6
        L_0x00b4:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "SSL requires certificate password: use ktor.security.ssl.privateKeyPassword config"
            r5.<init>(r6)
            throw r5
        L_0x00bc:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "SSL requires keystore password: use ktor.security.ssl.keyStorePassword config"
            r5.<init>(r6)
            throw r5
        L_0x00c4:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "SSL requires keystore: use -sslKeyStore=path or ktor.security.ssl.keyStore config"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.EnvironmentUtilsJvmKt.configureSSLConnectors(io.ktor.server.engine.ApplicationEngineEnvironmentBuilder, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static final ApplicationConfig getConfigFromEnvironment() {
        Properties properties = System.getProperties();
        Intrinsics.checkNotNullExpressionValue(properties, "getProperties()");
        Map map = MapsKt.toMap(properties);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type kotlin.String");
            if (StringsKt.startsWith$default((String) key, "ktor.", false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Map map2 = linkedHashMap;
        Collection arrayList = new ArrayList(map2.size());
        for (Map.Entry entry2 : map2.entrySet()) {
            Object key2 = entry2.getKey();
            Intrinsics.checkNotNull(key2, "null cannot be cast to non-null type kotlin.String");
            Object value = entry2.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
            arrayList.add(TuplesKt.to((String) key2, (String) value));
        }
        return new MapApplicationConfig((List<Pair<String, String>>) (List) arrayList);
    }

    public static final void configurePlatformProperties(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder, String[] strArr) {
        ClassLoader classLoader;
        URL url;
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "args");
        Collection arrayList = new ArrayList();
        for (String splitPair : strArr) {
            Pair<String, String> splitPair2 = CommandLineKt.splitPair(splitPair, '=');
            if (splitPair2 != null) {
                arrayList.add(splitPair2);
            }
        }
        String str = (String) MapsKt.toMap((List) arrayList).get("-jar");
        URL url2 = null;
        if (str != null) {
            if (StringsKt.startsWith$default(str, "file:", false, 2, (Object) null) || StringsKt.startsWith$default(str, "jrt:", false, 2, (Object) null) || StringsKt.startsWith$default(str, "jar:", false, 2, (Object) null)) {
                url = new URI(str).toURL();
            } else {
                url = new File(str).toURI().toURL();
            }
            url2 = url;
        }
        if (url2 != null) {
            classLoader = new URLClassLoader(new URL[]{url2}, ApplicationEnvironment.class.getClassLoader());
        } else {
            classLoader = ApplicationEnvironment.class.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader, "ApplicationEnvironment::class.java.classLoader");
        }
        applicationEngineEnvironmentBuilder.setClassLoader(classLoader);
    }
}
