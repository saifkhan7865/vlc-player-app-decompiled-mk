package io.ktor.server.http.content;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "name", "", "<anonymous parameter 1>", "invoke", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedResponse$headers$2$1$1 extends Lambda implements Function2<String, String, Boolean> {
    public static final PreCompressedResponse$headers$2$1$1 INSTANCE = new PreCompressedResponse$headers$2$1$1();

    PreCompressedResponse$headers$2$1$1() {
        super(2);
    }

    public final Boolean invoke(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "<anonymous parameter 1>");
        return Boolean.valueOf(!StringsKt.equals(str, HttpHeaders.INSTANCE.getContentLength(), true));
    }
}
