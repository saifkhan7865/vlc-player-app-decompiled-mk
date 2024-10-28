package io.ktor.util;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00030\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "", "Value", "", "Lio/ktor/util/CaseInsensitiveString;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CaseInsensitiveMap.kt */
final class CaseInsensitiveMap$entries$1 extends Lambda implements Function1<Map.Entry<CaseInsensitiveString, Value>, Map.Entry<String, Value>> {
    public static final CaseInsensitiveMap$entries$1 INSTANCE = new CaseInsensitiveMap$entries$1();

    CaseInsensitiveMap$entries$1() {
        super(1);
    }

    public final Map.Entry<String, Value> invoke(Map.Entry<CaseInsensitiveString, Value> entry) {
        Intrinsics.checkNotNullParameter(entry, "$this$$receiver");
        return new Entry<>(entry.getKey().getContent(), entry.getValue());
    }
}
