package io.ktor.server.plugins.cors;

import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.OnCallContext;
import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/OnCallContext;", "Lio/ktor/server/plugins/cors/CORSConfig;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.cors.CORSKt$buildPlugin$1", f = "CORS.kt", i = {}, l = {108, 118, 126, 142}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CORS.kt */
final class CORSKt$buildPlugin$1 extends SuspendLambda implements Function3<OnCallContext<CORSConfig>, ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ Set<String> $allHeadersSet;
    final /* synthetic */ boolean $allowCredentials;
    final /* synthetic */ boolean $allowNonSimpleContentTypes;
    final /* synthetic */ boolean $allowSameOrigin;
    final /* synthetic */ boolean $allowsAnyHost;
    final /* synthetic */ String $exposedHeaders;
    final /* synthetic */ List<Function1<String, Boolean>> $headerPredicates;
    final /* synthetic */ List<String> $headersList;
    final /* synthetic */ HashSet<String> $hostsNormalized;
    final /* synthetic */ HashSet<Pair<String, String>> $hostsWithWildcard;
    final /* synthetic */ String $maxAgeHeaderValue;
    final /* synthetic */ Set<HttpMethod> $methods;
    final /* synthetic */ String $methodsListHeaderValue;
    final /* synthetic */ Regex $numberRegex;
    final /* synthetic */ List<Function1<String, Boolean>> $originPredicates;
    /* synthetic */ Object L$0;
    int label;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CORS.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                io.ktor.server.plugins.cors.OriginCheckResult[] r0 = io.ktor.server.plugins.cors.OriginCheckResult.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.server.plugins.cors.OriginCheckResult r1 = io.ktor.server.plugins.cors.OriginCheckResult.OK     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.server.plugins.cors.OriginCheckResult r1 = io.ktor.server.plugins.cors.OriginCheckResult.SkipCORS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.server.plugins.cors.OriginCheckResult r1 = io.ktor.server.plugins.cors.OriginCheckResult.Failed     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.cors.CORSKt$buildPlugin$1.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CORSKt$buildPlugin$1(boolean z, boolean z2, boolean z3, HashSet<String> hashSet, HashSet<Pair<String, String>> hashSet2, List<? extends Function1<? super String, Boolean>> list, Regex regex, boolean z4, String str, List<String> list2, Set<HttpMethod> set, String str2, List<? extends Function1<? super String, Boolean>> list3, Set<String> set2, String str3, Continuation<? super CORSKt$buildPlugin$1> continuation) {
        super(3, continuation);
        this.$allowsAnyHost = z;
        this.$allowCredentials = z2;
        this.$allowSameOrigin = z3;
        this.$hostsNormalized = hashSet;
        this.$hostsWithWildcard = hashSet2;
        this.$originPredicates = list;
        this.$numberRegex = regex;
        this.$allowNonSimpleContentTypes = z4;
        this.$methodsListHeaderValue = str;
        this.$headersList = list2;
        this.$methods = set;
        this.$maxAgeHeaderValue = str2;
        this.$headerPredicates = list3;
        this.$allHeadersSet = set2;
        this.$exposedHeaders = str3;
    }

    public final Object invoke(OnCallContext<CORSConfig> onCallContext, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        CORSKt$buildPlugin$1 cORSKt$buildPlugin$1 = new CORSKt$buildPlugin$1(this.$allowsAnyHost, this.$allowCredentials, this.$allowSameOrigin, this.$hostsNormalized, this.$hostsWithWildcard, this.$originPredicates, this.$numberRegex, this.$allowNonSimpleContentTypes, this.$methodsListHeaderValue, this.$headersList, this.$methods, this.$maxAgeHeaderValue, this.$headerPredicates, this.$allHeadersSet, this.$exposedHeaders, continuation);
        cORSKt$buildPlugin$1.L$0 = applicationCall;
        return cORSKt$buildPlugin$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ApplicationCall applicationCall = (ApplicationCall) this.L$0;
            if (!this.$allowsAnyHost || this.$allowCredentials) {
                CORSUtilsKt.corsVary(applicationCall);
            }
            List<String> all = applicationCall.getRequest().getHeaders().getAll(HttpHeaders.INSTANCE.getOrigin());
            if (all == null || (str = (String) CollectionsKt.singleOrNull(all)) == null) {
                return Unit.INSTANCE;
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[CORSKt.checkOrigin(str, OriginConnectionPointKt.getOrigin(applicationCall.getRequest()), this.$allowSameOrigin, this.$allowsAnyHost, this.$hostsNormalized, this.$hostsWithWildcard, this.$originPredicates, this.$numberRegex).ordinal()];
            if (i2 == 2) {
                return Unit.INSTANCE;
            }
            if (i2 != 3) {
                if (!this.$allowNonSimpleContentTypes) {
                    String header = ApplicationRequestPropertiesKt.header(applicationCall.getRequest(), HttpHeaders.INSTANCE.getContentType());
                    ContentType parse = header != null ? ContentType.Companion.parse(header) : null;
                    if (parse != null && !CORSConfig.Companion.getCorsSimpleContentTypes().contains(parse.withoutParameters())) {
                        Logger access$getLOGGER$p = CORSKt.LOGGER;
                        access$getLOGGER$p.trace("Respond forbidden " + ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()) + ": Content-Type isn't allowed " + parse);
                        this.label = 2;
                        if (CORSUtilsKt.respondCorsFailed(applicationCall, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                if (Intrinsics.areEqual((Object) ApplicationRequestPropertiesKt.getHttpMethod(applicationCall.getRequest()), (Object) HttpMethod.Companion.getOptions())) {
                    Logger access$getLOGGER$p2 = CORSKt.LOGGER;
                    access$getLOGGER$p2.trace("Respond preflight on OPTIONS for " + ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()));
                    String str2 = this.$methodsListHeaderValue;
                    List<String> list = this.$headersList;
                    Set<HttpMethod> set = this.$methods;
                    boolean z = this.$allowsAnyHost;
                    boolean z2 = this.$allowCredentials;
                    String str3 = this.$maxAgeHeaderValue;
                    List<Function1<String, Boolean>> list2 = this.$headerPredicates;
                    Set<String> set2 = this.$allHeadersSet;
                    this.label = 3;
                    if (CORSKt.respondPreflight(applicationCall, str, str2, list, set, z, z2, str3, list2, set2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                } else if (!CORSUtilsKt.corsCheckCurrentMethod(applicationCall, this.$methods)) {
                    Logger access$getLOGGER$p3 = CORSKt.LOGGER;
                    access$getLOGGER$p3.trace("Respond forbidden " + ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()) + ": method doesn't match " + ApplicationRequestPropertiesKt.getHttpMethod(applicationCall.getRequest()));
                    this.label = 4;
                    if (CORSUtilsKt.respondCorsFailed(applicationCall, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                } else {
                    CORSUtilsKt.accessControlAllowOrigin(applicationCall, str, this.$allowsAnyHost, this.$allowCredentials);
                    CORSUtilsKt.accessControlAllowCredentials(applicationCall, this.$allowCredentials);
                    if (this.$exposedHeaders != null) {
                        ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), HttpHeaders.INSTANCE.getAccessControlExposeHeaders(), this.$exposedHeaders);
                    }
                    return Unit.INSTANCE;
                }
            } else {
                Logger access$getLOGGER$p4 = CORSKt.LOGGER;
                access$getLOGGER$p4.trace("Respond forbidden " + ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()) + ": origin doesn't match " + OriginConnectionPointKt.getOrigin(applicationCall.getRequest()));
                this.label = 1;
                if (CORSUtilsKt.respondCorsFailed(applicationCall, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 3) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else if (i == 4) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
