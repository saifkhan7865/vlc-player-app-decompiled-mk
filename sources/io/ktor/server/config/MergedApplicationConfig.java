package io.ktor.server.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0016\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001d"}, d2 = {"Lio/ktor/server/config/MergedApplicationConfig;", "Lio/ktor/server/config/ApplicationConfig;", "first", "second", "(Lio/ktor/server/config/ApplicationConfig;Lio/ktor/server/config/ApplicationConfig;)V", "getFirst", "()Lio/ktor/server/config/ApplicationConfig;", "firstKeys", "", "", "getFirstKeys", "()Ljava/util/Set;", "firstKeys$delegate", "Lkotlin/Lazy;", "getSecond", "secondKeys", "getSecondKeys", "secondKeys$delegate", "config", "path", "configList", "", "keys", "property", "Lio/ktor/server/config/ApplicationConfigValue;", "propertyOrNull", "toMap", "", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MergedApplicationConfig.kt */
public final class MergedApplicationConfig implements ApplicationConfig {
    private final ApplicationConfig first;
    private final Lazy firstKeys$delegate = LazyKt.lazy(new MergedApplicationConfig$firstKeys$2(this));
    private final ApplicationConfig second;
    private final Lazy secondKeys$delegate = LazyKt.lazy(new MergedApplicationConfig$secondKeys$2(this));

    public MergedApplicationConfig(ApplicationConfig applicationConfig, ApplicationConfig applicationConfig2) {
        Intrinsics.checkNotNullParameter(applicationConfig, "first");
        Intrinsics.checkNotNullParameter(applicationConfig2, "second");
        this.first = applicationConfig;
        this.second = applicationConfig2;
    }

    public final ApplicationConfig getFirst() {
        return this.first;
    }

    public final ApplicationConfig getSecond() {
        return this.second;
    }

    private final Set<String> getFirstKeys() {
        return (Set) this.firstKeys$delegate.getValue();
    }

    private final Set<String> getSecondKeys() {
        return (Set) this.secondKeys$delegate.getValue();
    }

    public ApplicationConfigValue property(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (getFirstKeys().contains(str)) {
            return this.first.property(str);
        }
        return this.second.property(str);
    }

    public ApplicationConfigValue propertyOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (getFirstKeys().contains(str)) {
            return this.first.propertyOrNull(str);
        }
        return this.second.propertyOrNull(str);
    }

    public ApplicationConfig config(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Iterable<String> firstKeys = getFirstKeys();
        if (!(firstKeys instanceof Collection) || !((Collection) firstKeys).isEmpty()) {
            for (String startsWith$default : firstKeys) {
                if (StringsKt.startsWith$default(startsWith$default, str + '.', false, 2, (Object) null)) {
                    Iterable<String> secondKeys = getSecondKeys();
                    if (!(secondKeys instanceof Collection) || !((Collection) secondKeys).isEmpty()) {
                        for (String startsWith$default2 : secondKeys) {
                            if (StringsKt.startsWith$default(startsWith$default2, str + '.', false, 2, (Object) null)) {
                                return new MergedApplicationConfig(this.first.config(str), this.second.config(str));
                            }
                        }
                    }
                    return this.first.config(str);
                }
            }
        }
        return this.second.config(str);
    }

    public List<ApplicationConfig> configList(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return CollectionsKt.plus(getFirstKeys().contains(str) ? this.first.configList(str) : CollectionsKt.emptyList(), getSecondKeys().contains(str) ? this.second.configList(str) : CollectionsKt.emptyList());
    }

    public Set<String> keys() {
        return SetsKt.plus(getFirstKeys(), getSecondKeys());
    }

    public Map<String, Object> toMap() {
        return MapsKt.plus(this.second.toMap(), this.first.toMap());
    }
}
