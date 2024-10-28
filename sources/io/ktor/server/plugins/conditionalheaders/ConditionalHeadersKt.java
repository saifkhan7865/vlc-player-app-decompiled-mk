package io.ktor.server.plugins.conditionalheaders;

import io.ktor.http.DateUtilsKt;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.LastModifiedVersion;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.Version;
import io.ktor.http.content.VersionCheckResult;
import io.ktor.http.content.VersionsKt;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u0007*\u00020\u0016\u001a#\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0007*\u00020\t2\u0006\u0010\u0018\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001aE\u0010\u001a\u001a\u00020\u001b*\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010 \u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0!H@ø\u0001\u0000¢\u0006\u0002\u0010\"\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"K\u0010\u0005\u001a6\u00122\u00120\u0012,\u0012*\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\b0\u00070\u0006X\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0007*\u00020\n8FX\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"ConditionalHeaders", "Lio/ktor/server/application/RouteScopedPlugin;", "Lio/ktor/server/plugins/conditionalheaders/ConditionalHeadersConfig;", "getConditionalHeaders", "()Lio/ktor/server/application/RouteScopedPlugin;", "VersionProvidersKey", "Lio/ktor/util/AttributeKey;", "", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/http/content/OutgoingContent;", "Lkotlin/coroutines/Continuation;", "Lio/ktor/http/content/Version;", "", "getVersionProvidersKey", "()Lio/ktor/util/AttributeKey;", "defaultVersions", "getDefaultVersions$annotations", "(Lio/ktor/http/content/OutgoingContent;)V", "getDefaultVersions", "(Lio/ktor/http/content/OutgoingContent;)Ljava/util/List;", "parseVersions", "Lio/ktor/http/Headers;", "versionsFor", "content", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/content/OutgoingContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withETag", "", "etag", "", "putHeader", "", "block", "Lkotlin/Function1;", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-conditional-headers"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConditionalHeaders.kt */
public final class ConditionalHeadersKt {
    private static final RouteScopedPlugin<ConditionalHeadersConfig> ConditionalHeaders = CreatePluginUtilsKt.createRouteScopedPlugin("ConditionalHeaders", ConditionalHeadersKt$ConditionalHeaders$1.INSTANCE, ConditionalHeadersKt$ConditionalHeaders$2.INSTANCE);
    private static final AttributeKey<List<Function3<ApplicationCall, OutgoingContent, Continuation<? super List<? extends Version>>, Object>>> VersionProvidersKey = new AttributeKey<>("ConditionalHeadersKey");

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ConditionalHeaders.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                io.ktor.http.content.VersionCheckResult[] r0 = io.ktor.http.content.VersionCheckResult.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.http.content.VersionCheckResult r1 = io.ktor.http.content.VersionCheckResult.NOT_MODIFIED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.http.content.VersionCheckResult r1 = io.ktor.http.content.VersionCheckResult.PRECONDITION_FAILED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.http.content.VersionCheckResult r1 = io.ktor.http.content.VersionCheckResult.OK     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.WhenMappings.<clinit>():void");
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use versions or headers.parseVersions()")
    public static /* synthetic */ void getDefaultVersions$annotations(OutgoingContent outgoingContent) {
    }

    public static final AttributeKey<List<Function3<ApplicationCall, OutgoingContent, Continuation<? super List<? extends Version>>, Object>>> getVersionProvidersKey() {
        return VersionProvidersKey;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object versionsFor(io.ktor.server.application.ApplicationCall r6, io.ktor.http.content.OutgoingContent r7, kotlin.coroutines.Continuation<? super java.util.List<? extends io.ktor.http.content.Version>> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$versionsFor$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$versionsFor$1 r0 = (io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$versionsFor$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$versionsFor$1 r0 = new io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$versionsFor$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            java.lang.Object r6 = r0.L$3
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r0.L$2
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r2 = r0.L$1
            io.ktor.http.content.OutgoingContent r2 = (io.ktor.http.content.OutgoingContent) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            kotlin.ResultKt.throwOnFailure(r8)
            r5 = r4
            r4 = r7
            r7 = r5
            goto L_0x008c
        L_0x003d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.server.application.Application r8 = r6.getApplication()
            io.ktor.util.Attributes r8 = r8.getAttributes()
            io.ktor.util.AttributeKey<java.util.List<kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, io.ktor.http.content.OutgoingContent, kotlin.coroutines.Continuation<? super java.util.List<? extends io.ktor.http.content.Version>>, java.lang.Object>>> r2 = VersionProvidersKey
            java.lang.Object r8 = r8.getOrNull(r2)
            java.util.List r8 = (java.util.List) r8
            if (r8 == 0) goto L_0x009a
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r8 = r8.iterator()
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L_0x006b:
            boolean r4 = r6.hasNext()
            if (r4 == 0) goto L_0x0096
            java.lang.Object r4 = r6.next()
            kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r2
            r0.L$3 = r6
            r0.label = r3
            java.lang.Object r4 = r4.invoke(r7, r8, r0)
            if (r4 != r1) goto L_0x0088
            return r1
        L_0x0088:
            r5 = r2
            r2 = r8
            r8 = r4
            r4 = r5
        L_0x008c:
            java.util.List r8 = (java.util.List) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            kotlin.collections.CollectionsKt.addAll(r4, r8)
            r8 = r2
            r2 = r4
            goto L_0x006b
        L_0x0096:
            java.util.List r2 = (java.util.List) r2
            if (r2 != 0) goto L_0x009e
        L_0x009a:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L_0x009e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.versionsFor(io.ktor.server.application.ApplicationCall, io.ktor.http.content.OutgoingContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final RouteScopedPlugin<ConditionalHeadersConfig> getConditionalHeaders() {
        return ConditionalHeaders;
    }

    public static /* synthetic */ Object withETag$default(ApplicationCall applicationCall, String str, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return withETag(applicationCall, str, z, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use configuration for ConditionalHeaders or configure block of call.respond function.")
    public static final Object withETag(ApplicationCall applicationCall, String str, boolean z, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        VersionCheckResult check = VersionsKt.EntityTagVersion(str).check(applicationCall.getRequest().getHeaders());
        if (z) {
            ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), HttpHeaders.INSTANCE.getETag(), str);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[check.ordinal()];
        if (i == 1 || i == 2) {
            HttpStatusCode statusCode = check.getStatusCode();
            if (!(statusCode instanceof OutgoingContent) && !(statusCode instanceof byte[])) {
                ApplicationResponse response = applicationCall.getResponse();
                KType typeOf = Reflection.typeOf(HttpStatusCode.class);
                ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf));
            }
            ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
            Intrinsics.checkNotNull(statusCode, "null cannot be cast to non-null type kotlin.Any");
            Object execute = pipeline.execute(applicationCall, statusCode, continuation);
            if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return execute;
            }
            return Unit.INSTANCE;
        } else if (i != 3) {
            return Unit.INSTANCE;
        } else {
            Object invoke = function1.invoke(continuation);
            return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
        }
    }

    public static final List<Version> getDefaultVersions(OutgoingContent outgoingContent) {
        Intrinsics.checkNotNullParameter(outgoingContent, "<this>");
        List<Version> versions = VersionsKt.getVersions(outgoingContent);
        if (!versions.isEmpty()) {
            return versions;
        }
        return parseVersions(outgoingContent.getHeaders());
    }

    public static final List<Version> parseVersions(Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        List<String> all = headers.getAll(HttpHeaders.INSTANCE.getLastModified());
        if (all == null) {
            all = CollectionsKt.emptyList();
        }
        List<String> all2 = headers.getAll(HttpHeaders.INSTANCE.getETag());
        if (all2 == null) {
            all2 = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(all.size() + all2.size());
        for (String fromHttpToGmtDate : all) {
            arrayList.add(new LastModifiedVersion(DateUtilsKt.fromHttpToGmtDate(fromHttpToGmtDate)));
        }
        Collection collection = arrayList;
        for (String EntityTagVersion : all2) {
            collection.add(VersionsKt.EntityTagVersion(EntityTagVersion));
        }
        return arrayList;
    }
}
