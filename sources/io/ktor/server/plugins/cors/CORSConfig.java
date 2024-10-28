package io.ktor.server.plugins.cors;

import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.util.CaseInsensitiveSet;
import io.ktor.util.CollectionsJvmKt;
import io.ktor.util.KtorDsl;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CertificatePinner;

@KtorDsl
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u000b\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0011H\u0002J\u000e\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u0011J\u001a\u00101\u001a\u00020-2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0017J\u000e\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020\u0011J.\u00105\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00112\u000e\b\u0002\u00106\u001a\b\u0012\u0004\u0012\u00020\u0011072\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001107J\u000e\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020'J\u001a\u0010;\u001a\u00020-2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0017J\u0006\u0010<\u001a\u00020-J\u0006\u0010=\u001a\u00020-J\u000e\u0010>\u001a\u00020-2\u0006\u00100\u001a\u00020\u0011J\u0010\u0010?\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0011H\u0002J\u0010\u0010@\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0011H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0014R#\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0002\u001a\u0004\b\u001c\u0010\u0014R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R$\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 @FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0014R&\u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u00170\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u000e\u0010+\u001a\u00020\u0011XD¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lio/ktor/server/plugins/cors/CORSConfig;", "", "()V", "allowCredentials", "", "getAllowCredentials", "()Z", "setAllowCredentials", "(Z)V", "allowNonSimpleContentTypes", "getAllowNonSimpleContentTypes", "setAllowNonSimpleContentTypes", "allowSameOrigin", "getAllowSameOrigin", "setAllowSameOrigin", "exposedHeaders", "", "", "getExposedHeaders$annotations", "getExposedHeaders", "()Ljava/util/Set;", "headerPredicates", "", "Lkotlin/Function1;", "getHeaderPredicates", "()Ljava/util/List;", "headers", "getHeaders$annotations", "getHeaders", "hosts", "getHosts", "newMaxAge", "", "maxAgeInSeconds", "getMaxAgeInSeconds", "()J", "setMaxAgeInSeconds", "(J)V", "methods", "Lio/ktor/http/HttpMethod;", "getMethods", "originPredicates", "getOriginPredicates$ktor_server_cors", "wildcardWithDot", "addHost", "", "host", "allowHeader", "header", "allowHeaders", "predicate", "allowHeadersPrefixed", "headerPrefix", "allowHost", "schemes", "", "subDomains", "allowMethod", "method", "allowOrigins", "allowXHttpMethodOverride", "anyHost", "exposeHeader", "validateWildcardRequirements", "wildcardInFrontOfDomain", "Companion", "ktor-server-cors"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORSConfig.kt */
public final class CORSConfig {
    public static final long CORS_DEFAULT_MAX_AGE = 86400;
    public static final Companion Companion;
    /* access modifiers changed from: private */
    public static final Set<HttpMethod> CorsDefaultMethods = SetsKt.setOf(HttpMethod.Companion.getGet(), HttpMethod.Companion.getPost(), HttpMethod.Companion.getHead());
    /* access modifiers changed from: private */
    public static final Set<ContentType> CorsSimpleContentTypes = CollectionsJvmKt.unmodifiable(SetsKt.setOf(ContentType.Application.INSTANCE.getFormUrlEncoded(), ContentType.MultiPart.INSTANCE.getFormData(), ContentType.Text.INSTANCE.getPlain()));
    /* access modifiers changed from: private */
    public static final Set<String> CorsSimpleRequestHeaders;
    /* access modifiers changed from: private */
    public static final Set<String> CorsSimpleResponseHeaders;
    private boolean allowCredentials;
    private boolean allowNonSimpleContentTypes;
    private boolean allowSameOrigin = true;
    private final Set<String> exposedHeaders = new CaseInsensitiveSet();
    private final List<Function1<String, Boolean>> headerPredicates = new ArrayList();
    private final Set<String> headers = new CaseInsensitiveSet();
    private final Set<String> hosts = new HashSet();
    private long maxAgeInSeconds = CORS_DEFAULT_MAX_AGE;
    private final Set<HttpMethod> methods = new HashSet();
    private final List<Function1<String, Boolean>> originPredicates = new ArrayList();
    private final String wildcardWithDot = CertificatePinner.WILDCARD;

    public static /* synthetic */ void getExposedHeaders$annotations() {
    }

    public static /* synthetic */ void getHeaders$annotations() {
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u0015\"\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\tR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\t¨\u0006\u0017"}, d2 = {"Lio/ktor/server/plugins/cors/CORSConfig$Companion;", "", "()V", "CORS_DEFAULT_MAX_AGE", "", "CorsDefaultMethods", "", "Lio/ktor/http/HttpMethod;", "getCorsDefaultMethods", "()Ljava/util/Set;", "CorsSimpleContentTypes", "Lio/ktor/http/ContentType;", "getCorsSimpleContentTypes$annotations", "getCorsSimpleContentTypes", "CorsSimpleRequestHeaders", "", "getCorsSimpleRequestHeaders", "CorsSimpleResponseHeaders", "getCorsSimpleResponseHeaders", "caseInsensitiveSet", "elements", "", "([Ljava/lang/String;)Ljava/util/Set;", "ktor-server-cors"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CORSConfig.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getCorsSimpleContentTypes$annotations() {
        }

        private Companion() {
        }

        public final Set<HttpMethod> getCorsDefaultMethods() {
            return CORSConfig.CorsDefaultMethods;
        }

        public final Set<String> getCorsSimpleRequestHeaders() {
            return CORSConfig.CorsSimpleRequestHeaders;
        }

        public final Set<String> getCorsSimpleResponseHeaders() {
            return CORSConfig.CorsSimpleResponseHeaders;
        }

        public final Set<ContentType> getCorsSimpleContentTypes() {
            return CORSConfig.CorsSimpleContentTypes;
        }

        /* access modifiers changed from: private */
        public final Set<String> caseInsensitiveSet(String... strArr) {
            return new CaseInsensitiveSet(ArraysKt.asList((T[]) strArr));
        }
    }

    static {
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        CorsSimpleRequestHeaders = companion.caseInsensitiveSet(HttpHeaders.INSTANCE.getAccept(), HttpHeaders.INSTANCE.getAcceptLanguage(), HttpHeaders.INSTANCE.getContentLanguage(), HttpHeaders.INSTANCE.getContentType());
        CorsSimpleResponseHeaders = companion.caseInsensitiveSet(HttpHeaders.INSTANCE.getCacheControl(), HttpHeaders.INSTANCE.getContentLanguage(), HttpHeaders.INSTANCE.getContentType(), HttpHeaders.INSTANCE.getExpires(), HttpHeaders.INSTANCE.getLastModified(), HttpHeaders.INSTANCE.getPragma());
    }

    public final Set<String> getHosts() {
        return this.hosts;
    }

    public final Set<String> getHeaders() {
        return this.headers;
    }

    public final Set<HttpMethod> getMethods() {
        return this.methods;
    }

    public final Set<String> getExposedHeaders() {
        return this.exposedHeaders;
    }

    public final boolean getAllowCredentials() {
        return this.allowCredentials;
    }

    public final void setAllowCredentials(boolean z) {
        this.allowCredentials = z;
    }

    public final List<Function1<String, Boolean>> getOriginPredicates$ktor_server_cors() {
        return this.originPredicates;
    }

    public final List<Function1<String, Boolean>> getHeaderPredicates() {
        return this.headerPredicates;
    }

    public final long getMaxAgeInSeconds() {
        return this.maxAgeInSeconds;
    }

    public final void setMaxAgeInSeconds(long j) {
        if (j >= 0) {
            this.maxAgeInSeconds = j;
            return;
        }
        throw new IllegalStateException(("maxAgeInSeconds shouldn't be negative: " + j).toString());
    }

    public final boolean getAllowSameOrigin() {
        return this.allowSameOrigin;
    }

    public final void setAllowSameOrigin(boolean z) {
        this.allowSameOrigin = z;
    }

    public final boolean getAllowNonSimpleContentTypes() {
        return this.allowNonSimpleContentTypes;
    }

    public final void setAllowNonSimpleContentTypes(boolean z) {
        this.allowNonSimpleContentTypes = z;
    }

    public final void anyHost() {
        this.hosts.add("*");
    }

    public static /* synthetic */ void allowHost$default(CORSConfig cORSConfig, String str, List list, List list2, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.listOf("http");
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        cORSConfig.allowHost(str, list, list2);
    }

    public final void allowHost(String str, List<String> list, List<String> list2) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(list, "schemes");
        Intrinsics.checkNotNullParameter(list2, "subDomains");
        if (Intrinsics.areEqual((Object) str, (Object) "*")) {
            anyHost();
        } else if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "://", false, 2, (Object) null)) {
            for (String next : list) {
                addHost(next + "://" + str);
                for (String next2 : list2) {
                    validateWildcardRequirements(next2);
                    addHost(next + "://" + next2 + '.' + str);
                }
            }
        } else {
            throw new IllegalArgumentException("scheme should be specified as a separate parameter schemes".toString());
        }
    }

    private final void addHost(String str) {
        validateWildcardRequirements(str);
        this.hosts.add(str);
    }

    private final void validateWildcardRequirements(String str) {
        if (StringsKt.contains$default((CharSequence) str, (char) GMTDateParser.ANY, false, 2, (Object) null)) {
            if (!wildcardInFrontOfDomain(str)) {
                throw new IllegalArgumentException("wildcard must appear in front of the domain, e.g. *.domain.com".toString());
            } else if (validateWildcardRequirements$countMatches(str, this.wildcardWithDot) != 1) {
                throw new IllegalArgumentException("wildcard cannot appear more than once".toString());
            }
        }
    }

    private static final int validateWildcardRequirements$countMatches(String str, String str2) {
        return CollectionsKt.sumOfInt(StringsKt.windowed$default(str, str2.length(), 0, false, new CORSConfig$validateWildcardRequirements$countMatches$1(str2), 6, (Object) null));
    }

    private final boolean wildcardInFrontOfDomain(String str) {
        CharSequence charSequence = str;
        int indexOf$default = StringsKt.indexOf$default(charSequence, this.wildcardWithDot, 0, false, 6, (Object) null);
        if (!StringsKt.contains$default(charSequence, (CharSequence) this.wildcardWithDot, false, 2, (Object) null) || StringsKt.endsWith$default(str, this.wildcardWithDot, false, 2, (Object) null)) {
            return false;
        }
        if (indexOf$default <= 0 || StringsKt.endsWith$default(StringsKt.substringBefore$default(str, this.wildcardWithDot, (String) null, 2, (Object) null), "://", false, 2, (Object) null)) {
            return true;
        }
        return false;
    }

    public final void exposeHeader(String str) {
        Intrinsics.checkNotNullParameter(str, "header");
        if (!CorsSimpleResponseHeaders.contains(str)) {
            this.exposedHeaders.add(str);
        }
    }

    public final void allowXHttpMethodOverride() {
        allowHeader(HttpHeaders.INSTANCE.getXHttpMethodOverride());
    }

    public final void allowOrigins(Function1<? super String, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "predicate");
        this.originPredicates.add(function1);
    }

    public final void allowHeadersPrefixed(String str) {
        Intrinsics.checkNotNullParameter(str, "headerPrefix");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        this.headerPredicates.add(new CORSConfig$allowHeadersPrefixed$1(lowerCase));
    }

    public final void allowHeaders(Function1<? super String, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "predicate");
        this.headerPredicates.add(function1);
    }

    public final void allowHeader(String str) {
        Intrinsics.checkNotNullParameter(str, "header");
        if (StringsKt.equals(str, HttpHeaders.INSTANCE.getContentType(), true)) {
            this.allowNonSimpleContentTypes = true;
        } else if (!CorsSimpleRequestHeaders.contains(str)) {
            this.headers.add(str);
        }
    }

    public final void allowMethod(HttpMethod httpMethod) {
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        if (!CorsDefaultMethods.contains(httpMethod)) {
            this.methods.add(httpMethod);
        }
    }
}
