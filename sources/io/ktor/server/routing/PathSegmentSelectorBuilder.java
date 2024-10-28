package io.ktor.server.routing;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u000b"}, d2 = {"Lio/ktor/server/routing/PathSegmentSelectorBuilder;", "", "()V", "parseConstant", "Lio/ktor/server/routing/RouteSelector;", "value", "", "hasTrailingSlash", "", "parseName", "parseParameter", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingBuilder.kt */
public final class PathSegmentSelectorBuilder {
    public static final PathSegmentSelectorBuilder INSTANCE = new PathSegmentSelectorBuilder();

    private PathSegmentSelectorBuilder() {
    }

    public final RouteSelector parseParameter(String str) {
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(str, "value");
        CharSequence charSequence = str;
        int indexOf$default = StringsKt.indexOf$default(charSequence, (char) AbstractJsonLexerKt.BEGIN_OBJ, 0, false, 6, (Object) null);
        int lastIndexOf$default = StringsKt.lastIndexOf$default(charSequence, (char) AbstractJsonLexerKt.END_OBJ, 0, false, 6, (Object) null);
        if (indexOf$default == 0) {
            str2 = null;
        } else {
            str2 = str.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        if (lastIndexOf$default == str.length() - 1) {
            str3 = null;
        } else {
            str3 = str.substring(lastIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).substring(startIndex)");
        }
        String substring = str.substring(indexOf$default + 1, lastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        if (StringsKt.endsWith$default(substring, "?", false, 2, (Object) null)) {
            return new PathSegmentOptionalParameterRouteSelector(StringsKt.dropLast(substring, 1), str2, str3);
        }
        if (!StringsKt.endsWith$default(substring, "...", false, 2, (Object) null)) {
            return new PathSegmentParameterRouteSelector(substring, str2, str3);
        }
        if (str3 == null || str3.length() <= 0) {
            String dropLast = StringsKt.dropLast(substring, 3);
            if (str2 == null) {
                str2 = "";
            }
            return new PathSegmentTailcardRouteSelector(dropLast, str2);
        }
        throw new IllegalArgumentException("Suffix after tailcard is not supported");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "hasTrailingSlash is not used anymore. This is going to be removed", replaceWith = @ReplaceWith(expression = "parseParameter(value)", imports = {}))
    public final RouteSelector parseParameter(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "value");
        return parseParameter(str);
    }

    public final RouteSelector parseConstant(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        if (Intrinsics.areEqual((Object) str, (Object) "*")) {
            return PathSegmentWildcardRouteSelector.INSTANCE;
        }
        return new PathSegmentConstantRouteSelector(str);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "hasTrailingSlash is not used anymore. This is going to be removed", replaceWith = @ReplaceWith(expression = "parseConstant(value)", imports = {}))
    public final RouteSelector parseConstant(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "value");
        return parseConstant(str);
    }

    public final String parseName(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        String substring = str.substring(StringsKt.substringBefore(str, (char) AbstractJsonLexerKt.BEGIN_OBJ, "").length() + 1, (str.length() - StringsKt.substringAfterLast(str, (char) AbstractJsonLexerKt.END_OBJ, "").length()) - 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        if (StringsKt.endsWith$default(substring, "?", false, 2, (Object) null)) {
            return StringsKt.dropLast(substring, 1);
        }
        return StringsKt.endsWith$default(substring, "...", false, 2, (Object) null) ? StringsKt.dropLast(substring, 3) : substring;
    }
}
