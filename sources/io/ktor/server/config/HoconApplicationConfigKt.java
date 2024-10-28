package io.ktor.server.config;

import com.typesafe.config.Config;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u001a\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003\u001a\u001a\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003¨\u0006\t"}, d2 = {"ApplicationConfig", "Lio/ktor/server/config/ApplicationConfig;", "configPath", "", "tryGetString", "Lcom/typesafe/config/Config;", "path", "tryGetStringList", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HoconApplicationConfig.kt */
public final class HoconApplicationConfigKt {
    public static final String tryGetString(Config config, String str) {
        Intrinsics.checkNotNullParameter(config, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (config.hasPath(str)) {
            return config.getString(str);
        }
        return null;
    }

    public static final List<String> tryGetStringList(Config config, String str) {
        Intrinsics.checkNotNullParameter(config, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (config.hasPath(str)) {
            return config.getStringList(str);
        }
        return null;
    }

    public static final ApplicationConfig ApplicationConfig(String str) {
        return ConfigLoader.Companion.load(ConfigLoader.Companion, str);
    }
}
