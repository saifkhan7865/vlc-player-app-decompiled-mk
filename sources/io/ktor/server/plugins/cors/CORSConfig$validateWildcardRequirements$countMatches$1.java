package io.ktor.server.plugins.cors;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/CharSequence;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORSConfig.kt */
final class CORSConfig$validateWildcardRequirements$countMatches$1 extends Lambda implements Function1<CharSequence, Integer> {
    final /* synthetic */ String $subString;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CORSConfig$validateWildcardRequirements$countMatches$1(String str) {
        super(1);
        this.$subString = str;
    }

    public final Integer invoke(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "it");
        return Integer.valueOf(Intrinsics.areEqual((Object) charSequence, (Object) this.$subString) ? 1 : 0);
    }
}
