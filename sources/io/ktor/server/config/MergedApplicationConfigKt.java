package io.ktor.server.config;

import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"merge", "Lio/ktor/server/config/ApplicationConfig;", "", "mergeWith", "other", "withFallback", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MergedApplicationConfig.kt */
public final class MergedApplicationConfigKt {
    @Deprecated(message = "Use mergeWith/withFallback instead.")
    public static final ApplicationConfig merge(List<? extends ApplicationConfig> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (!list.isEmpty()) {
            Object last = CollectionsKt.last(list);
            if (!list.isEmpty()) {
                ListIterator<? extends ApplicationConfig> listIterator = list.listIterator(list.size());
                while (listIterator.hasPrevious()) {
                    last = withFallback((ApplicationConfig) listIterator.previous(), (ApplicationConfig) last);
                }
            }
            return (ApplicationConfig) last;
        }
        throw new IllegalArgumentException("List of configs can not be empty".toString());
    }

    public static final ApplicationConfig mergeWith(ApplicationConfig applicationConfig, ApplicationConfig applicationConfig2) {
        Intrinsics.checkNotNullParameter(applicationConfig, "<this>");
        Intrinsics.checkNotNullParameter(applicationConfig2, "other");
        return new MergedApplicationConfig(applicationConfig2, applicationConfig);
    }

    public static final ApplicationConfig withFallback(ApplicationConfig applicationConfig, ApplicationConfig applicationConfig2) {
        Intrinsics.checkNotNullParameter(applicationConfig, "<this>");
        Intrinsics.checkNotNullParameter(applicationConfig2, "other");
        return new MergedApplicationConfig(applicationConfig, applicationConfig2);
    }
}
