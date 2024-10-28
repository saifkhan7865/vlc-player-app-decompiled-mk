package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpHeadersMap.kt */
final class HttpHeadersMap$getAll$1 extends Lambda implements Function1<Integer, Integer> {
    final /* synthetic */ HttpHeadersMap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpHeadersMap$getAll$1(HttpHeadersMap httpHeadersMap) {
        super(1);
        this.this$0 = httpHeadersMap;
    }

    public final Integer invoke(int i) {
        int i2 = i + 1;
        if (i2 >= this.this$0.getSize()) {
            return null;
        }
        return Integer.valueOf(i2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
