package io.ktor.server.config;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: MergedApplicationConfig.kt */
final class MergedApplicationConfig$firstKeys$2 extends Lambda implements Function0<Set<? extends String>> {
    final /* synthetic */ MergedApplicationConfig this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MergedApplicationConfig$firstKeys$2(MergedApplicationConfig mergedApplicationConfig) {
        super(0);
        this.this$0 = mergedApplicationConfig;
    }

    public final Set<String> invoke() {
        return this.this$0.getFirst().keys();
    }
}
