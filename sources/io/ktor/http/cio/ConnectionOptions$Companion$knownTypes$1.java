package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "Lio/ktor/http/cio/ConnectionOptions;", "invoke", "(Lkotlin/Pair;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConnectionOptions.kt */
final class ConnectionOptions$Companion$knownTypes$1 extends Lambda implements Function1<Pair<? extends String, ? extends ConnectionOptions>, Integer> {
    public static final ConnectionOptions$Companion$knownTypes$1 INSTANCE = new ConnectionOptions$Companion$knownTypes$1();

    ConnectionOptions$Companion$knownTypes$1() {
        super(1);
    }

    public final Integer invoke(Pair<String, ConnectionOptions> pair) {
        Intrinsics.checkNotNullParameter(pair, "it");
        return Integer.valueOf(pair.getFirst().length());
    }
}
