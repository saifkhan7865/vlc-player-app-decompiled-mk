package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(I)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpHeadersMap.kt */
final class HttpHeadersMap$getAll$3 extends Lambda implements Function1<Integer, Boolean> {
    final /* synthetic */ int $nameHash;
    final /* synthetic */ HttpHeadersMap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpHeadersMap$getAll$3(HttpHeadersMap httpHeadersMap, int i) {
        super(1);
        this.this$0 = httpHeadersMap;
        this.$nameHash = i;
    }

    public final Boolean invoke(int i) {
        return Boolean.valueOf(this.this$0.indexes[i] == this.$nameHash);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
