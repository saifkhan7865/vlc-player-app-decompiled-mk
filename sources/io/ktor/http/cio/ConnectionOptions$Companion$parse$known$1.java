package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "<anonymous parameter 1>", "", "invoke", "(CI)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConnectionOptions.kt */
final class ConnectionOptions$Companion$parse$known$1 extends Lambda implements Function2<Character, Integer, Boolean> {
    public static final ConnectionOptions$Companion$parse$known$1 INSTANCE = new ConnectionOptions$Companion$parse$known$1();

    ConnectionOptions$Companion$parse$known$1() {
        super(2);
    }

    public final Boolean invoke(char c, int i) {
        return false;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Character) obj).charValue(), ((Number) obj2).intValue());
    }
}
