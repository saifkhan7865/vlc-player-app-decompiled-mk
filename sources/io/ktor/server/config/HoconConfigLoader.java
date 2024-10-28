package io.ktor.server.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.File;
import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lio/ktor/server/config/HoconConfigLoader;", "Lio/ktor/server/config/ConfigLoader;", "()V", "load", "Lio/ktor/server/config/ApplicationConfig;", "path", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HoconApplicationConfig.kt */
public final class HoconConfigLoader implements ConfigLoader {
    public ApplicationConfig load(String str) {
        Config config;
        if (str == null) {
            str = "application.conf";
        } else if (!StringsKt.endsWith$default(str, ".conf", false, 2, (Object) null) && !StringsKt.endsWith$default(str, ".json", false, 2, (Object) null) && !StringsKt.endsWith$default(str, ".properties", false, 2, (Object) null)) {
            return null;
        }
        if (Thread.currentThread().getContextClassLoader().getResource(str) != null) {
            config = ConfigFactory.load(str);
        } else {
            File file = new File(str);
            config = file.exists() ? ConfigFactory.parseFile(file) : null;
        }
        Config resolve = config != null ? config.resolve() : null;
        if (resolve == null) {
            return null;
        }
        return new HoconApplicationConfig(resolve);
    }
}
