package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Url.kt */
final class Url$encodedUser$2 extends Lambda implements Function0<String> {
    final /* synthetic */ Url this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Url$encodedUser$2(Url url) {
        super(0);
        this.this$0 = url;
    }

    public final String invoke() {
        if (this.this$0.getUser() == null) {
            return null;
        }
        if (this.this$0.getUser().length() == 0) {
            return "";
        }
        int length = this.this$0.getProtocol().getName().length() + 3;
        String substring = this.this$0.urlString.substring(length, StringsKt.indexOfAny$default((CharSequence) this.this$0.urlString, new char[]{AbstractJsonLexerKt.COLON, '@'}, length, false, 4, (Object) null));
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }
}
