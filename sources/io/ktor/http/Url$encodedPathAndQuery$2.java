package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Url.kt */
final class Url$encodedPathAndQuery$2 extends Lambda implements Function0<String> {
    final /* synthetic */ Url this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Url$encodedPathAndQuery$2(Url url) {
        super(0);
        this.this$0 = url;
    }

    public final String invoke() {
        int indexOf$default = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '/', this.this$0.getProtocol().getName().length() + 3, false, 4, (Object) null);
        if (indexOf$default == -1) {
            return "";
        }
        int indexOf$default2 = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '#', indexOf$default, false, 4, (Object) null);
        if (indexOf$default2 == -1) {
            String substring = this.this$0.urlString.substring(indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            return substring;
        }
        String substring2 = this.this$0.urlString.substring(indexOf$default, indexOf$default2);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring2;
    }
}
