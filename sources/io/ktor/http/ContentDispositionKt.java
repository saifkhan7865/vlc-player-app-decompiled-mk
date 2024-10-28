package io.ktor.http;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"encodeContentDispositionAttribute", "", "key", "value", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ContentDisposition.kt */
public final class ContentDispositionKt {
    /* access modifiers changed from: private */
    public static final String encodeContentDispositionAttribute(String str, String str2) {
        if (!Intrinsics.areEqual((Object) str, (Object) ContentDisposition.Parameters.FileNameAsterisk) || StringsKt.startsWith(str2, "utf-8''", true)) {
            return str2;
        }
        CharSequence charSequence = str2;
        for (int i = 0; i < charSequence.length(); i++) {
            if (!CodecsKt.getATTRIBUTE_CHARACTERS().contains(Character.valueOf(charSequence.charAt(i)))) {
                String percentEncode = CodecsKt.percentEncode(str2, CodecsKt.getATTRIBUTE_CHARACTERS());
                return "utf-8''" + percentEncode;
            }
        }
        return str2;
    }
}
