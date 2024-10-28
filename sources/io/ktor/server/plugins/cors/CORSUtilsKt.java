package io.ktor.server.plugins.cors;

import com.google.common.net.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.vlc.gui.dialogs.NetworkServerDialog;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u001ab\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b0\u00062\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a>\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000b0\nH\u0000\u001a*\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00032\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000b0\nH\u0000\u001a \u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0010\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0014\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0001H\u0000\u001a$\u0010\u001d\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u0000\u001a\u001a\u0010 \u001a\u00020\u0001*\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0006H\u0000\u001a\u001a\u0010#\u001a\u00020\u0001*\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0006H\u0000\u001a\f\u0010$\u001a\u00020\u001a*\u00020\u001bH\u0000\u001a\u0015\u0010%\u001a\u00020\u001a*\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010&\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"corsCheckOrigins", "", "origin", "", "allowsAnyHost", "hostsNormalized", "", "hostsWithWildcard", "Lkotlin/Pair;", "originPredicates", "", "Lkotlin/Function1;", "numberRegex", "Lkotlin/text/Regex;", "corsCheckRequestHeaders", "requestHeaders", "allHeadersSet", "headerPredicates", "headerMatchesAPredicate", "header", "isSameOrigin", "point", "Lio/ktor/http/RequestConnectionPoint;", "isValidOrigin", "normalizeOrigin", "accessControlAllowCredentials", "", "Lio/ktor/server/application/ApplicationCall;", "allowCredentials", "accessControlAllowOrigin", "accessControlMaxAge", "maxAgeHeaderValue", "corsCheckCurrentMethod", "methods", "Lio/ktor/http/HttpMethod;", "corsCheckRequestMethod", "corsVary", "respondCorsFailed", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-cors"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORSUtils.kt */
public final class CORSUtilsKt {
    public static final void accessControlAllowOrigin(ApplicationCall applicationCall, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(str, HttpHeaders.ReferrerPolicyValues.ORIGIN);
        if (z && !z2) {
            str = "*";
        }
        ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), io.ktor.http.HttpHeaders.INSTANCE.getAccessControlAllowOrigin(), str);
    }

    public static final void corsVary(ApplicationCall applicationCall) {
        String str;
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        String str2 = applicationCall.getResponse().getHeaders().get(io.ktor.http.HttpHeaders.INSTANCE.getVary());
        if (str2 == null) {
            str = io.ktor.http.HttpHeaders.INSTANCE.getOrigin();
        } else {
            str = str2 + ", " + io.ktor.http.HttpHeaders.INSTANCE.getOrigin();
        }
        ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), io.ktor.http.HttpHeaders.INSTANCE.getVary(), str);
    }

    public static final void accessControlAllowCredentials(ApplicationCall applicationCall, boolean z) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        if (z) {
            ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), io.ktor.http.HttpHeaders.INSTANCE.getAccessControlAllowCredentials(), "true");
        }
    }

    public static final void accessControlMaxAge(ApplicationCall applicationCall, String str) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        if (str != null) {
            ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), io.ktor.http.HttpHeaders.INSTANCE.getAccessControlMaxAge(), str);
        }
    }

    public static final boolean isSameOrigin(String str, RequestConnectionPoint requestConnectionPoint, Regex regex) {
        Intrinsics.checkNotNullParameter(str, HttpHeaders.ReferrerPolicyValues.ORIGIN);
        Intrinsics.checkNotNullParameter(requestConnectionPoint, "point");
        Intrinsics.checkNotNullParameter(regex, "numberRegex");
        return Intrinsics.areEqual((Object) normalizeOrigin(requestConnectionPoint.getScheme() + "://" + requestConnectionPoint.getServerHost() + AbstractJsonLexerKt.COLON + requestConnectionPoint.getServerPort(), regex), (Object) normalizeOrigin(str, regex));
    }

    public static final boolean corsCheckOrigins(String str, boolean z, Set<String> set, Set<Pair<String, String>> set2, List<? extends Function1<? super String, Boolean>> list, Regex regex) {
        Intrinsics.checkNotNullParameter(str, HttpHeaders.ReferrerPolicyValues.ORIGIN);
        Intrinsics.checkNotNullParameter(set, "hostsNormalized");
        Intrinsics.checkNotNullParameter(set2, "hostsWithWildcard");
        Intrinsics.checkNotNullParameter(list, "originPredicates");
        Intrinsics.checkNotNullParameter(regex, "numberRegex");
        String normalizeOrigin = normalizeOrigin(str, regex);
        if (!z && !set.contains(normalizeOrigin)) {
            Iterable iterable = set2;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Pair pair = (Pair) it.next();
                    String str2 = (String) pair.component2();
                    if (StringsKt.startsWith$default(normalizeOrigin, (String) pair.component1(), false, 2, (Object) null) && StringsKt.endsWith$default(normalizeOrigin, str2, false, 2, (Object) null)) {
                        break;
                    }
                }
            }
            Iterable<Function1> iterable2 = list;
            if ((iterable2 instanceof Collection) && ((Collection) iterable2).isEmpty()) {
                return false;
            }
            for (Function1 invoke : iterable2) {
                if (((Boolean) invoke.invoke(str)).booleanValue()) {
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean corsCheckRequestHeaders(List<String> list, Set<String> set, List<? extends Function1<? super String, Boolean>> list2) {
        Intrinsics.checkNotNullParameter(list, "requestHeaders");
        Intrinsics.checkNotNullParameter(set, "allHeadersSet");
        Intrinsics.checkNotNullParameter(list2, "headerPredicates");
        Iterable<String> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (String str : iterable) {
            if (!set.contains(str) && !headerMatchesAPredicate(str, list2)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean headerMatchesAPredicate(String str, List<? extends Function1<? super String, Boolean>> list) {
        Intrinsics.checkNotNullParameter(str, "header");
        Intrinsics.checkNotNullParameter(list, "headerPredicates");
        Iterable<Function1> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (Function1 invoke : iterable) {
            if (((Boolean) invoke.invoke(str)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean corsCheckCurrentMethod(ApplicationCall applicationCall, Set<HttpMethod> set) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(set, "methods");
        return set.contains(ApplicationRequestPropertiesKt.getHttpMethod(applicationCall.getRequest()));
    }

    public static final boolean corsCheckRequestMethod(ApplicationCall applicationCall, Set<HttpMethod> set) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(set, "methods");
        String header = ApplicationRequestPropertiesKt.header(applicationCall.getRequest(), io.ktor.http.HttpHeaders.INSTANCE.getAccessControlRequestMethod());
        HttpMethod httpMethod = header != null ? new HttpMethod(header) : null;
        return httpMethod != null && set.contains(httpMethod);
    }

    public static final Object respondCorsFailed(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        HttpStatusCode forbidden = HttpStatusCode.Companion.getForbidden();
        if (!(forbidden instanceof OutgoingContent) && !(forbidden instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(HttpStatusCode.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        Intrinsics.checkNotNull(forbidden, "null cannot be cast to non-null type kotlin.Any");
        Object execute = pipeline.execute(applicationCall, forbidden, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static final boolean isValidOrigin(String str) {
        int indexOf$default;
        Intrinsics.checkNotNullParameter(str, HttpHeaders.ReferrerPolicyValues.ORIGIN);
        CharSequence charSequence = str;
        if (charSequence.length() == 0) {
            return false;
        }
        if (Intrinsics.areEqual((Object) str, (Object) AbstractJsonLexerKt.NULL)) {
            return true;
        }
        if (!StringsKt.contains$default(charSequence, (CharSequence) "%", false, 2, (Object) null) && (indexOf$default = StringsKt.indexOf$default(charSequence, "://", 0, false, 6, (Object) null)) > 0 && Character.isLetter(str.charAt(0))) {
            CharSequence subSequence = str.subSequence(0, indexOf$default);
            int i = 0;
            while (i < subSequence.length()) {
                char charAt = subSequence.charAt(i);
                if (Character.isLetter(charAt) || Character.isDigit(charAt) || charAt == '-' || charAt == '+' || charAt == '.') {
                    i++;
                }
            }
            int length = str.length();
            int i2 = indexOf$default + 3;
            int length2 = str.length();
            while (true) {
                if (i2 >= length2) {
                    break;
                }
                char charAt2 = str.charAt(i2);
                if (charAt2 == ':' || charAt2 == '/') {
                    length = i2 + 1;
                } else if (charAt2 == '?') {
                    return false;
                } else {
                    i2++;
                }
            }
            length = i2 + 1;
            int length3 = str.length();
            while (length < length3) {
                if (!Character.isDigit(str.charAt(length))) {
                    return false;
                }
                length++;
            }
            return true;
        }
        return false;
    }

    public static final String normalizeOrigin(String str, Regex regex) {
        Intrinsics.checkNotNullParameter(str, HttpHeaders.ReferrerPolicyValues.ORIGIN);
        Intrinsics.checkNotNullParameter(regex, "numberRegex");
        if (Intrinsics.areEqual((Object) str, (Object) AbstractJsonLexerKt.NULL) || Intrinsics.areEqual((Object) str, (Object) "*")) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(str);
        if (!regex.matches(StringsKt.substringAfterLast(str, ":", ""))) {
            String str2 = null;
            String substringBefore$default = StringsKt.substringBefore$default(str, (char) AbstractJsonLexerKt.COLON, (String) null, 2, (Object) null);
            if (Intrinsics.areEqual((Object) substringBefore$default, (Object) "http")) {
                str2 = NetworkServerDialog.HTTP_DEFAULT_PORT;
            } else if (Intrinsics.areEqual((Object) substringBefore$default, (Object) "https")) {
                str2 = NetworkServerDialog.HTTPS_DEFAULT_PORT;
            }
            if (str2 != null) {
                sb.append(":" + str2);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }
}
