package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOHeaders.kt */
final class CIOHeaders$getAll$1 extends Lambda implements Function1<CharSequence, String> {
    public static final CIOHeaders$getAll$1 INSTANCE = new CIOHeaders$getAll$1();

    CIOHeaders$getAll$1() {
        super(1);
    }

    public final String invoke(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "it");
        return charSequence.toString();
    }
}