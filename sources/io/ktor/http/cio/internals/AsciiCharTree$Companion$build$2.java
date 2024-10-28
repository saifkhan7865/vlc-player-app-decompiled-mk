package io.ktor.http.cio.internals;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\r\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "T", "", "", "s", "idx", "", "invoke", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AsciiCharTree.kt */
final class AsciiCharTree$Companion$build$2 extends Lambda implements Function2<T, Integer, Character> {
    public static final AsciiCharTree$Companion$build$2 INSTANCE = new AsciiCharTree$Companion$build$2();

    AsciiCharTree$Companion$build$2() {
        super(2);
    }

    public final Character invoke(T t, int i) {
        Intrinsics.checkNotNullParameter(t, "s");
        return Character.valueOf(t.charAt(i));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }
}
