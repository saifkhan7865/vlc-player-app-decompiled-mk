package io.ktor.http.cio;

import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Ljava/util/LinkedHashSet;", "", "Lkotlin/collections/LinkedHashSet;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOHeaders.kt */
final class CIOHeaders$names$2 extends Lambda implements Function0<LinkedHashSet<String>> {
    final /* synthetic */ CIOHeaders this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOHeaders$names$2(CIOHeaders cIOHeaders) {
        super(0);
        this.this$0 = cIOHeaders;
    }

    public final LinkedHashSet<String> invoke() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(this.this$0.headers.getSize());
        CIOHeaders cIOHeaders = this.this$0;
        int size = cIOHeaders.headers.getSize();
        for (int i = 0; i < size; i++) {
            linkedHashSet.add(cIOHeaders.headers.nameAt(i).toString());
        }
        return linkedHashSet;
    }
}
