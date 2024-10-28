package io.ktor.http.cio.internals;

import io.ktor.http.HttpMethod;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "m", "Lio/ktor/http/HttpMethod;", "idx", "", "invoke", "(Lio/ktor/http/HttpMethod;I)Ljava/lang/Character;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Chars.kt */
final class CharsKt$DefaultHttpMethods$2 extends Lambda implements Function2<HttpMethod, Integer, Character> {
    public static final CharsKt$DefaultHttpMethods$2 INSTANCE = new CharsKt$DefaultHttpMethods$2();

    CharsKt$DefaultHttpMethods$2() {
        super(2);
    }

    public final Character invoke(HttpMethod httpMethod, int i) {
        Intrinsics.checkNotNullParameter(httpMethod, "m");
        return Character.valueOf(httpMethod.getValue().charAt(i));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((HttpMethod) obj, ((Number) obj2).intValue());
    }
}
