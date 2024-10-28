package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "t", "Lkotlin/Pair;", "", "Lio/ktor/http/cio/ConnectionOptions;", "idx", "", "invoke", "(Lkotlin/Pair;I)Ljava/lang/Character;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConnectionOptions.kt */
final class ConnectionOptions$Companion$knownTypes$2 extends Lambda implements Function2<Pair<? extends String, ? extends ConnectionOptions>, Integer, Character> {
    public static final ConnectionOptions$Companion$knownTypes$2 INSTANCE = new ConnectionOptions$Companion$knownTypes$2();

    ConnectionOptions$Companion$knownTypes$2() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((Pair<String, ConnectionOptions>) (Pair) obj, ((Number) obj2).intValue());
    }

    public final Character invoke(Pair<String, ConnectionOptions> pair, int i) {
        Intrinsics.checkNotNullParameter(pair, "t");
        return Character.valueOf(pair.getFirst().charAt(i));
    }
}
