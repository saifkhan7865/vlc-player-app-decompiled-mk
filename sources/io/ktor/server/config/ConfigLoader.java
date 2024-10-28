package io.ktor.server.config;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007"}, d2 = {"Lio/ktor/server/config/ConfigLoader;", "", "load", "Lio/ktor/server/config/ApplicationConfig;", "path", "", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConfigLoaders.kt */
public interface ConfigLoader {
    public static final Companion Companion = Companion.$$INSTANCE;

    ApplicationConfig load(String str);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u0016\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/server/config/ConfigLoader$Companion;", "", "()V", "loadDefault", "Lio/ktor/server/config/ApplicationConfig;", "load", "path", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ConfigLoaders.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public static /* synthetic */ ApplicationConfig load$default(Companion companion, Companion companion2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return companion.load(companion2, str);
        }

        public final ApplicationConfig load(Companion companion, String str) {
            ApplicationConfig loadDefault;
            Intrinsics.checkNotNullParameter(companion, "<this>");
            if (str == null && (loadDefault = companion.loadDefault()) != null) {
                return loadDefault;
            }
            for (ConfigLoader load : ConfigLoadersJvmKt.getConfigLoaders()) {
                ApplicationConfig load2 = load.load(str);
                if (load2 != null) {
                    return load2;
                }
            }
            return new MapApplicationConfig();
        }

        private final ApplicationConfig loadDefault() {
            for (String next : ConfigLoadersJvmKt.getCONFIG_PATH()) {
                Iterator<ConfigLoader> it = ConfigLoadersJvmKt.getConfigLoaders().iterator();
                while (true) {
                    if (it.hasNext()) {
                        ApplicationConfig load = it.next().load(next);
                        if (load != null) {
                            return load;
                        }
                    }
                }
            }
            return null;
        }
    }
}
