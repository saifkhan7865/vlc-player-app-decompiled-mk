package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth1a.kt */
final class OAuth1aKt$parametersString$4 extends Lambda implements Function1<Pair<? extends String, ? extends String>, CharSequence> {
    public static final OAuth1aKt$parametersString$4 INSTANCE = new OAuth1aKt$parametersString$4();

    OAuth1aKt$parametersString$4() {
        super(1);
    }

    public final CharSequence invoke(Pair<String, String> pair) {
        Intrinsics.checkNotNullParameter(pair, "it");
        return pair.getFirst() + '=' + pair.getSecond();
    }
}
