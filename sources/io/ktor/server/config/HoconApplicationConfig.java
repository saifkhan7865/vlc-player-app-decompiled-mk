package io.ktor.server.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lio/ktor/server/config/HoconApplicationConfig;", "Lio/ktor/server/config/ApplicationConfig;", "config", "Lcom/typesafe/config/Config;", "(Lcom/typesafe/config/Config;)V", "path", "", "configList", "", "keys", "", "property", "Lio/ktor/server/config/ApplicationConfigValue;", "propertyOrNull", "toMap", "", "", "HoconApplicationConfigValue", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HoconApplicationConfig.kt */
public class HoconApplicationConfig implements ApplicationConfig {
    private final Config config;

    public HoconApplicationConfig(Config config2) {
        Intrinsics.checkNotNullParameter(config2, "config");
        this.config = config2;
    }

    public ApplicationConfigValue property(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (this.config.hasPath(str)) {
            return new HoconApplicationConfigValue(this.config, str);
        }
        throw new ApplicationConfigurationException("Property " + str + " not found.");
    }

    public ApplicationConfigValue propertyOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (!this.config.hasPath(str)) {
            return null;
        }
        return new HoconApplicationConfigValue(this.config, str);
    }

    public List<ApplicationConfig> configList(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        List<? extends Config> configList = this.config.getConfigList(str);
        Intrinsics.checkNotNullExpressionValue(configList, "config.getConfigList(path)");
        Iterable<Config> iterable = configList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Config config2 : iterable) {
            Intrinsics.checkNotNullExpressionValue(config2, "it");
            arrayList.add(new HoconApplicationConfig(config2));
        }
        return (List) arrayList;
    }

    public ApplicationConfig config(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Config config2 = this.config.getConfig(str);
        Intrinsics.checkNotNullExpressionValue(config2, "config.getConfig(path)");
        return new HoconApplicationConfig(config2);
    }

    public Set<String> keys() {
        Set<Map.Entry<String, ConfigValue>> entrySet = this.config.entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "config.entrySet()");
        Iterable<Map.Entry> iterable = entrySet;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Map.Entry key : iterable) {
            arrayList.add((String) key.getKey());
        }
        return CollectionsKt.toSet((List) arrayList);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> unwrapped = this.config.root().unwrapped();
        Intrinsics.checkNotNullExpressionValue(unwrapped, "config.root().unwrapped()");
        return unwrapped;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lio/ktor/server/config/HoconApplicationConfig$HoconApplicationConfigValue;", "Lio/ktor/server/config/ApplicationConfigValue;", "config", "Lcom/typesafe/config/Config;", "path", "", "(Lcom/typesafe/config/Config;Ljava/lang/String;)V", "getConfig", "()Lcom/typesafe/config/Config;", "getPath", "()Ljava/lang/String;", "getList", "", "getString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HoconApplicationConfig.kt */
    private static final class HoconApplicationConfigValue implements ApplicationConfigValue {
        private final Config config;
        private final String path;

        public HoconApplicationConfigValue(Config config2, String str) {
            Intrinsics.checkNotNullParameter(config2, "config");
            Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
            this.config = config2;
            this.path = str;
        }

        public final Config getConfig() {
            return this.config;
        }

        public final String getPath() {
            return this.path;
        }

        public String getString() {
            String string = this.config.getString(this.path);
            Intrinsics.checkNotNullExpressionValue(string, "config.getString(path)");
            return string;
        }

        public List<String> getList() {
            List<String> stringList = this.config.getStringList(this.path);
            Intrinsics.checkNotNullExpressionValue(stringList, "config.getStringList(path)");
            return stringList;
        }
    }
}
