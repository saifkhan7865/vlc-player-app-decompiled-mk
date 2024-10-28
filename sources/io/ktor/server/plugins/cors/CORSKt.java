package io.ktor.server.plugins.cors;

import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPlugin;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.PluginBuilder;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDateParser;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001ar\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00160\u00142\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00110\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u0012\u0010\u001c\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0000\u001a\u0001\u0010\u001f\u001a\u00020\u001d*\u00020 2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\u00182\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\r2\u0018\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00110\u00190\u00182\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\r0\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010)\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0012\u0010\u0007\u001a\u00060\bj\u0002`\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"CORS", "Lio/ktor/server/application/ApplicationPlugin;", "Lio/ktor/server/plugins/cors/CORSConfig;", "getCORS$annotations", "()V", "getCORS", "()Lio/ktor/server/application/ApplicationPlugin;", "LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "checkOrigin", "Lio/ktor/server/plugins/cors/OriginCheckResult;", "origin", "", "point", "Lio/ktor/http/RequestConnectionPoint;", "allowSameOrigin", "", "allowsAnyHost", "hostsNormalized", "", "hostsWithWildcard", "Lkotlin/Pair;", "originPredicates", "", "Lkotlin/Function1;", "numberRegex", "Lkotlin/text/Regex;", "buildPlugin", "", "Lio/ktor/server/application/PluginBuilder;", "respondPreflight", "Lio/ktor/server/application/ApplicationCall;", "methodsListHeaderValue", "headersList", "methods", "Lio/ktor/http/HttpMethod;", "allowCredentials", "maxAgeHeaderValue", "headerPredicates", "allHeadersSet", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Set;ZZLjava/lang/String;Ljava/util/List;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-cors"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORS.kt */
public final class CORSKt {
    private static final ApplicationPlugin<CORSConfig> CORS = CreatePluginUtilsKt.createApplicationPlugin("CORS", CORSKt$CORS$1.INSTANCE, CORSKt$CORS$2.INSTANCE);
    /* access modifiers changed from: private */
    public static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.server.plugins.cors.CORS");

    @Deprecated(level = DeprecationLevel.WARNING, message = "This plugin was moved to io.ktor.server.plugins.cors.routing", replaceWith = @ReplaceWith(expression = "CORS", imports = {"io.ktor.server.plugins.cors.routing.CORS"}))
    public static /* synthetic */ void getCORS$annotations() {
    }

    public static final ApplicationPlugin<CORSConfig> getCORS() {
        return CORS;
    }

    public static final void buildPlugin(PluginBuilder<CORSConfig> pluginBuilder) {
        Intrinsics.checkNotNullParameter(pluginBuilder, "<this>");
        Regex regex = new Regex("[0-9]+");
        boolean allowSameOrigin = pluginBuilder.getPluginConfig().getAllowSameOrigin();
        boolean contains = pluginBuilder.getPluginConfig().getHosts().contains("*");
        boolean allowCredentials = pluginBuilder.getPluginConfig().getAllowCredentials();
        Set<T> plus = SetsKt.plus(pluginBuilder.getPluginConfig().getHeaders(), CORSConfig.Companion.getCorsSimpleRequestHeaders());
        if (!pluginBuilder.getPluginConfig().getAllowNonSimpleContentTypes()) {
            plus = SetsKt.minus(plus, HttpHeaders.INSTANCE.getContentType());
        }
        List<Function1<String, Boolean>> originPredicates$ktor_server_cors = pluginBuilder.getPluginConfig().getOriginPredicates$ktor_server_cors();
        List<Function1<String, Boolean>> headerPredicates = pluginBuilder.getPluginConfig().getHeaderPredicates();
        Set hashSet = new HashSet(SetsKt.plus(pluginBuilder.getPluginConfig().getMethods(), CORSConfig.Companion.getCorsDefaultMethods()));
        Iterable<String> iterable = plus;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String lowerCasePreservingASCIIRules : iterable) {
            arrayList.add(TextKt.toLowerCasePreservingASCIIRules(lowerCasePreservingASCIIRules));
        }
        Set set = CollectionsKt.toSet((List) arrayList);
        boolean allowNonSimpleContentTypes = pluginBuilder.getPluginConfig().getAllowNonSimpleContentTypes();
        Collection arrayList2 = new ArrayList();
        for (Object next : pluginBuilder.getPluginConfig().getHeaders()) {
            if (!CORSConfig.Companion.getCorsSimpleRequestHeaders().contains((String) next)) {
                arrayList2.add(next);
            }
        }
        List list = (List) arrayList2;
        List plus2 = allowNonSimpleContentTypes ? CollectionsKt.plus(list, HttpHeaders.INSTANCE.getContentType()) : list;
        Collection arrayList3 = new ArrayList();
        for (Object next2 : hashSet) {
            if (!CORSConfig.Companion.getCorsDefaultMethods().contains((HttpMethod) next2)) {
                arrayList3.add(next2);
            }
        }
        Iterable<HttpMethod> iterable2 = (List) arrayList3;
        Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (HttpMethod value : iterable2) {
            arrayList4.add(value.getValue());
        }
        String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.sorted((List) arrayList4), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        long maxAgeInSeconds = pluginBuilder.getPluginConfig().getMaxAgeInSeconds();
        String valueOf = maxAgeInSeconds > 0 ? String.valueOf(maxAgeInSeconds) : null;
        String joinToString$default2 = pluginBuilder.getPluginConfig().getExposedHeaders().isEmpty() ^ true ? CollectionsKt.joinToString$default(CollectionsKt.sorted(pluginBuilder.getPluginConfig().getExposedHeaders()), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) : null;
        Collection arrayList5 = new ArrayList();
        Iterator it = pluginBuilder.getPluginConfig().getHosts().iterator();
        while (it.hasNext()) {
            Object next3 = it.next();
            Iterator it2 = it;
            String str = joinToString$default2;
            String str2 = valueOf;
            List<Function1<String, Boolean>> list2 = headerPredicates;
            if (!StringsKt.contains$default((CharSequence) (String) next3, (char) GMTDateParser.ANY, false, 2, (Object) null)) {
                arrayList5.add(next3);
            }
            PluginBuilder<CORSConfig> pluginBuilder2 = pluginBuilder;
            joinToString$default2 = str;
            it = it2;
            headerPredicates = list2;
            valueOf = str2;
        }
        String str3 = joinToString$default2;
        String str4 = valueOf;
        List<Function1<String, Boolean>> list3 = headerPredicates;
        Iterable<String> iterable3 = (List) arrayList5;
        Collection arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        for (String normalizeOrigin : iterable3) {
            arrayList6.add(CORSUtilsKt.normalizeOrigin(normalizeOrigin, regex));
        }
        HashSet hashSet2 = new HashSet((List) arrayList6);
        Collection arrayList7 = new ArrayList();
        Iterator it3 = pluginBuilder.getPluginConfig().getHosts().iterator();
        while (it3.hasNext()) {
            Object next4 = it3.next();
            Iterator it4 = it3;
            Set set2 = hashSet;
            if (StringsKt.contains$default((CharSequence) (String) next4, (char) GMTDateParser.ANY, false, 2, (Object) null)) {
                arrayList7.add(next4);
            }
            it3 = it4;
            hashSet = set2;
        }
        Set set3 = hashSet;
        Iterable<String> iterable4 = (List) arrayList7;
        Collection arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
        for (String normalizeOrigin2 : iterable4) {
            List split$default = StringsKt.split$default((CharSequence) CORSUtilsKt.normalizeOrigin(normalizeOrigin2, regex), new char[]{GMTDateParser.ANY}, false, 0, 6, (Object) null);
            arrayList8.add(TuplesKt.to((String) split$default.get(0), (String) split$default.get(1)));
        }
        HashSet hashSet3 = r1;
        HashSet hashSet4 = new HashSet((List) arrayList8);
        pluginBuilder.onCall(new CORSKt$buildPlugin$1(contains, allowCredentials, allowSameOrigin, hashSet2, hashSet3, originPredicates$ktor_server_cors, regex, allowNonSimpleContentTypes, joinToString$default, plus2, set3, str4, list3, set, str3, (Continuation<? super CORSKt$buildPlugin$1>) null));
    }

    /* access modifiers changed from: private */
    public static final OriginCheckResult checkOrigin(String str, RequestConnectionPoint requestConnectionPoint, boolean z, boolean z2, Set<String> set, Set<Pair<String, String>> set2, List<? extends Function1<? super String, Boolean>> list, Regex regex) {
        if (!CORSUtilsKt.isValidOrigin(str)) {
            return OriginCheckResult.SkipCORS;
        }
        if (z && CORSUtilsKt.isSameOrigin(str, requestConnectionPoint, regex)) {
            return OriginCheckResult.SkipCORS;
        }
        if (!CORSUtilsKt.corsCheckOrigins(str, z2, set, set2, list, regex)) {
            return OriginCheckResult.Failed;
        }
        return OriginCheckResult.OK;
    }

    /* access modifiers changed from: private */
    public static final Object respondPreflight(ApplicationCall applicationCall, String str, String str2, List<String> list, Set<HttpMethod> set, boolean z, boolean z2, String str3, List<? extends Function1<? super String, Boolean>> list2, Set<String> set2, Continuation<? super Unit> continuation) {
        List list3;
        ApplicationCall applicationCall2 = applicationCall;
        String str4 = str2;
        boolean z3 = z2;
        List<? extends Function1<? super String, Boolean>> list4 = list2;
        Continuation<? super Unit> continuation2 = continuation;
        List<String> all = applicationCall.getRequest().getHeaders().getAll(HttpHeaders.INSTANCE.getAccessControlRequestHeaders());
        if (all != null) {
            Collection arrayList = new ArrayList();
            for (String split$default : all) {
                CollectionsKt.addAll(arrayList, StringsKt.split$default((CharSequence) split$default, new String[]{AnsiRenderer.CODE_LIST_SEPARATOR}, false, 0, 6, (Object) null));
            }
            Collection arrayList2 = new ArrayList();
            for (Object next : (List) arrayList) {
                if (!StringsKt.isBlank((String) next)) {
                    arrayList2.add(next);
                }
            }
            Iterable<String> iterable = (List) arrayList2;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (String trim : iterable) {
                arrayList3.add(TextKt.toLowerCasePreservingASCIIRules(StringsKt.trim((CharSequence) trim).toString()));
            }
            list3 = (List) arrayList3;
        } else {
            list3 = CollectionsKt.emptyList();
        }
        if (!CORSUtilsKt.corsCheckRequestMethod(applicationCall2, set)) {
            Logger logger = LOGGER;
            logger.trace("Return Forbidden for " + ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()) + ": CORS method doesn't match " + ApplicationRequestPropertiesKt.getHttpMethod(applicationCall.getRequest()));
            HttpStatusCode forbidden = HttpStatusCode.Companion.getForbidden();
            if (!(forbidden instanceof OutgoingContent) && !(forbidden instanceof byte[])) {
                ApplicationResponse response = applicationCall.getResponse();
                KType typeOf = Reflection.typeOf(HttpStatusCode.class);
                ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf));
            }
            ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
            Intrinsics.checkNotNull(forbidden, "null cannot be cast to non-null type kotlin.Any");
            Object execute = pipeline.execute(applicationCall2, forbidden, continuation2);
            return execute == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? execute : Unit.INSTANCE;
        } else if (!CORSUtilsKt.corsCheckRequestHeaders(list3, set2, list4)) {
            Logger logger2 = LOGGER;
            logger2.trace("Return Forbidden for " + ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()) + ": request has not allowed headers.");
            HttpStatusCode forbidden2 = HttpStatusCode.Companion.getForbidden();
            if (!(forbidden2 instanceof OutgoingContent) && !(forbidden2 instanceof byte[])) {
                ApplicationResponse response2 = applicationCall.getResponse();
                KType typeOf2 = Reflection.typeOf(HttpStatusCode.class);
                ResponseTypeKt.setResponseType(response2, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf2));
            }
            ApplicationSendPipeline pipeline2 = applicationCall.getResponse().getPipeline();
            Intrinsics.checkNotNull(forbidden2, "null cannot be cast to non-null type kotlin.Any");
            Object execute2 = pipeline2.execute(applicationCall2, forbidden2, continuation2);
            return execute2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? execute2 : Unit.INSTANCE;
        } else {
            CORSUtilsKt.accessControlAllowOrigin(applicationCall2, str, z, z3);
            CORSUtilsKt.accessControlAllowCredentials(applicationCall2, z3);
            if (str4.length() > 0) {
                ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), HttpHeaders.INSTANCE.getAccessControlAllowMethods(), str4);
            }
            Collection arrayList4 = new ArrayList();
            for (Object next2 : list3) {
                if (CORSUtilsKt.headerMatchesAPredicate((String) next2, list4)) {
                    arrayList4.add(next2);
                }
            }
            ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), HttpHeaders.INSTANCE.getAccessControlAllowHeaders(), CollectionsKt.joinToString$default(CollectionsKt.sorted(CollectionsKt.plus(list, (List) arrayList4)), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            CORSUtilsKt.accessControlMaxAge(applicationCall2, str3);
            HttpStatusCode ok = HttpStatusCode.Companion.getOK();
            if (!(ok instanceof OutgoingContent) && !(ok instanceof byte[])) {
                ApplicationResponse response3 = applicationCall.getResponse();
                KType typeOf3 = Reflection.typeOf(HttpStatusCode.class);
                ResponseTypeKt.setResponseType(response3, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf3), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf3));
            }
            ApplicationSendPipeline pipeline3 = applicationCall.getResponse().getPipeline();
            Intrinsics.checkNotNull(ok, "null cannot be cast to non-null type kotlin.Any");
            Object execute3 = pipeline3.execute(applicationCall2, ok, continuation2);
            if (execute3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return execute3;
            }
            return Unit.INSTANCE;
        }
    }
}
