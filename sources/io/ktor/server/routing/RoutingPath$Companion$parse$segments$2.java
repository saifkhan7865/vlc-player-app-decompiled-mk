package io.ktor.server.routing;

import io.ktor.http.CodecsKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/server/routing/RoutingPathSegment;", "segment", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingPath.kt */
final class RoutingPath$Companion$parse$segments$2 extends Lambda implements Function1<String, RoutingPathSegment> {
    public static final RoutingPath$Companion$parse$segments$2 INSTANCE = new RoutingPath$Companion$parse$segments$2();

    RoutingPath$Companion$parse$segments$2() {
        super(1);
    }

    public final RoutingPathSegment invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "segment");
        CharSequence charSequence = str;
        if (!StringsKt.contains$default(charSequence, (char) AbstractJsonLexerKt.BEGIN_OBJ, false, 2, (Object) null) || !StringsKt.contains$default(charSequence, (char) AbstractJsonLexerKt.END_OBJ, false, 2, (Object) null)) {
            return new RoutingPathSegment(CodecsKt.decodeURLPart$default(str, 0, 0, (Charset) null, 7, (Object) null), RoutingPathSegmentKind.Constant);
        }
        return new RoutingPathSegment(str, RoutingPathSegmentKind.Parameter);
    }
}
