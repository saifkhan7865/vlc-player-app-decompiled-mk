package io.ktor.server.auth;

import io.ktor.http.CodecsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth1a.kt */
final class OAuth1aKt$signatureBaseStringInternal$1 extends Lambda implements Function1<String, CharSequence> {
    public static final OAuth1aKt$signatureBaseStringInternal$1 INSTANCE = new OAuth1aKt$signatureBaseStringInternal$1();

    OAuth1aKt$signatureBaseStringInternal$1() {
        super(1);
    }

    public final CharSequence invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return CodecsKt.encodeURLParameter$default(str, false, 1, (Object) null);
    }
}