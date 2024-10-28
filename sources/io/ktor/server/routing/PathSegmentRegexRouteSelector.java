package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/server/routing/PathSegmentRegexRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "regex", "Lkotlin/text/Regex;", "(Lkotlin/text/Regex;)V", "countSegments", "", "result", "Lkotlin/text/MatchResult;", "lastSlashPosition", "prefix", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "toString", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RegexRouting.kt */
public final class PathSegmentRegexRouteSelector extends RouteSelector {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Regex GROUP_NAME_MATCHER = new Regex("(^|[^\\\\])\\(\\?<(\\p{Alpha}\\p{Alnum}*)>(.*?[^\\\\])?\\)");
    private final Regex regex;

    public PathSegmentRegexRouteSelector(Regex regex2) {
        Intrinsics.checkNotNullParameter(regex2, "regex");
        this.regex = regex2;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        int i2;
        String str;
        int i3 = i;
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        String str2 = (StringsKt.startsWith$default((CharSequence) this.regex.getPattern(), '/', false, 2, (Object) null) || StringsKt.startsWith$default(this.regex.getPattern(), "\\/", false, 2, (Object) null)) ? "/" : "";
        String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.drop(routingResolveContext.getSegments(), i3), "/", str2, (!StringsKt.endsWith$default((CharSequence) this.regex.getPattern(), '/', false, 2, (Object) null) || !IgnoreTrailingSlashKt.getIgnoreTrailingSlash(routingResolveContext.getCall())) ? "" : "/", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        MatchResult find$default = Regex.find$default(this.regex, joinToString$default, 0, 2, (Object) null);
        if (find$default == null) {
            return RouteSelectorEvaluation.Companion.getFailed();
        }
        int length = find$default.getValue().length();
        if (joinToString$default.length() == length) {
            i2 = routingResolveContext.getSegments().size() - i3;
        } else if (joinToString$default.charAt(length) == '/') {
            i2 = countSegments(find$default, length, str2);
        } else {
            if (length >= 1) {
                int i4 = length - 1;
                if (joinToString$default.charAt(i4) == '/') {
                    i2 = countSegments(find$default, i4, str2);
                }
            }
            return RouteSelectorEvaluation.Companion.getFailed();
        }
        MatchGroupCollection groups = find$default.getGroups();
        Intrinsics.checkNotNull(groups, "null cannot be cast to non-null type kotlin.text.MatchNamedGroupCollection");
        MatchNamedGroupCollection matchNamedGroupCollection = (MatchNamedGroupCollection) groups;
        Parameters.Companion companion = Parameters.Companion;
        ParametersBuilder ParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
        for (MatchResult destructured : Regex.findAll$default(GROUP_NAME_MATCHER, this.regex.getPattern(), 0, 2, (Object) null)) {
            String str3 = destructured.getDestructured().getMatch().getGroupValues().get(2);
            MatchGroup matchGroup = matchNamedGroupCollection.get(str3);
            if (matchGroup == null || (str = matchGroup.getValue()) == null) {
                str = "";
            }
            ParametersBuilder$default.append(str3, str);
        }
        return new RouteSelectorEvaluation.Success(1.0d, ParametersBuilder$default.build(), i2);
    }

    private final int countSegments(MatchResult matchResult, int i, String str) {
        String substring = matchResult.getValue().substring(0, i);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        CharSequence charSequence = substring;
        int i2 = 0;
        for (int i3 = 0; i3 < charSequence.length(); i3++) {
            if (charSequence.charAt(i3) == '/') {
                i2++;
            }
        }
        return Intrinsics.areEqual((Object) str, (Object) "/") ? i2 : i2 + 1;
    }

    public String toString() {
        return "Regex(" + this.regex.getPattern() + ')';
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/server/routing/PathSegmentRegexRouteSelector$Companion;", "", "()V", "GROUP_NAME_MATCHER", "Lkotlin/text/Regex;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RegexRouting.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
