package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpHeadersMap.kt */
final class HttpHeadersMap$getAll$4 extends Lambda implements Function1<Integer, CharSequence> {
    final /* synthetic */ HttpHeadersMap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpHeadersMap$getAll$4(HttpHeadersMap httpHeadersMap) {
        super(1);
        this.this$0 = httpHeadersMap;
    }

    public final CharSequence invoke(int i) {
        return this.this$0.builder.subSequence(this.this$0.indexes[i + 4], this.this$0.indexes[i + 5]);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
