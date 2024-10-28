package io.ktor.server.plugins.conditionalheaders;

import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.Version;
import io.ktor.http.content.VersionCheckResult;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.RouteScopedPluginBuilder;
import io.ktor.server.application.hooks.ResponseBodyReadyForSend;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "Lio/ktor/server/plugins/conditionalheaders/ConditionalHeadersConfig;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConditionalHeaders.kt */
final class ConditionalHeadersKt$ConditionalHeaders$2 extends Lambda implements Function1<RouteScopedPluginBuilder<ConditionalHeadersConfig>, Unit> {
    public static final ConditionalHeadersKt$ConditionalHeaders$2 INSTANCE = new ConditionalHeadersKt$ConditionalHeaders$2();

    ConditionalHeadersKt$ConditionalHeaders$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RouteScopedPluginBuilder<ConditionalHeadersConfig>) (RouteScopedPluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(RouteScopedPluginBuilder<ConditionalHeadersConfig> routeScopedPluginBuilder) {
        Intrinsics.checkNotNullParameter(routeScopedPluginBuilder, "$this$createRouteScopedPlugin");
        routeScopedPluginBuilder.getApplication().getAttributes().put(ConditionalHeadersKt.getVersionProvidersKey(), routeScopedPluginBuilder.getPluginConfig().getVersionProviders$ktor_server_conditional_headers());
        routeScopedPluginBuilder.on(ResponseBodyReadyForSend.INSTANCE, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }

    /* access modifiers changed from: private */
    public static final VersionCheckResult invoke$checkVersions(ApplicationCall applicationCall, List<? extends Version> list) {
        for (Version check : list) {
            VersionCheckResult check2 = check.check(applicationCall.getRequest().getHeaders());
            if (check2 != VersionCheckResult.OK) {
                return check2;
            }
        }
        return VersionCheckResult.OK;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "call", "Lio/ktor/server/application/ApplicationCall;", "content", "Lio/ktor/http/content/OutgoingContent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$ConditionalHeaders$2$1", f = "ConditionalHeaders.kt", i = {0, 0}, l = {94}, m = "invokeSuspend", n = {"$this$on", "call"}, s = {"L$0", "L$1"})
    /* renamed from: io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$ConditionalHeaders$2$1  reason: invalid class name */
    /* compiled from: ConditionalHeaders.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function4<ResponseBodyReadyForSend.Context, ApplicationCall, OutgoingContent, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        public final Object invoke(ResponseBodyReadyForSend.Context context, ApplicationCall applicationCall, OutgoingContent outgoingContent, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = context;
            r0.L$1 = applicationCall;
            r0.L$2 = outgoingContent;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: io.ktor.server.application.hooks.ResponseBodyReadyForSend$Context} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L_0x001f
                if (r1 != r2) goto L_0x0017
                java.lang.Object r0 = r6.L$1
                io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
                java.lang.Object r1 = r6.L$0
                io.ktor.server.application.hooks.ResponseBodyReadyForSend$Context r1 = (io.ktor.server.application.hooks.ResponseBodyReadyForSend.Context) r1
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x0041
            L_0x0017:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L_0x001f:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                r1 = r7
                io.ktor.server.application.hooks.ResponseBodyReadyForSend$Context r1 = (io.ktor.server.application.hooks.ResponseBodyReadyForSend.Context) r1
                java.lang.Object r7 = r6.L$1
                io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
                java.lang.Object r3 = r6.L$2
                io.ktor.http.content.OutgoingContent r3 = (io.ktor.http.content.OutgoingContent) r3
                r4 = r6
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r6.L$0 = r1
                r6.L$1 = r7
                r6.label = r2
                java.lang.Object r3 = io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.versionsFor(r7, r3, r4)
                if (r3 != r0) goto L_0x003f
                return r0
            L_0x003f:
                r0 = r7
                r7 = r3
            L_0x0041:
                java.util.List r7 = (java.util.List) r7
                r3 = r7
                java.util.Collection r3 = (java.util.Collection) r3
                boolean r3 = r3.isEmpty()
                r3 = r3 ^ r2
                if (r3 == 0) goto L_0x0083
                io.ktor.http.Headers$Companion r3 = io.ktor.http.Headers.Companion
                io.ktor.http.HeadersBuilder r3 = new io.ktor.http.HeadersBuilder
                r4 = 0
                r5 = 0
                r3.<init>(r4, r2, r5)
                r2 = r7
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.Iterator r2 = r2.iterator()
            L_0x005d:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x006d
                java.lang.Object r4 = r2.next()
                io.ktor.http.content.Version r4 = (io.ktor.http.content.Version) r4
                r4.appendHeadersTo(r3)
                goto L_0x005d
            L_0x006d:
                io.ktor.http.Headers r2 = r3.build()
                io.ktor.server.response.ApplicationResponse r3 = r0.getResponse()
                io.ktor.server.response.ResponseHeaders r3 = r3.getHeaders()
                io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$ConditionalHeaders$2$1$1 r4 = new io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$ConditionalHeaders$2$1$1
                r4.<init>(r3)
                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                r2.forEach(r4)
            L_0x0083:
                io.ktor.http.content.VersionCheckResult r7 = io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$ConditionalHeaders$2.invoke$checkVersions(r0, r7)
                io.ktor.http.content.VersionCheckResult r0 = io.ktor.http.content.VersionCheckResult.OK
                if (r7 == r0) goto L_0x0099
                io.ktor.server.http.content.HttpStatusCodeContent r0 = new io.ktor.server.http.content.HttpStatusCodeContent
                io.ktor.http.HttpStatusCode r7 = r7.getStatusCode()
                r0.<init>(r7)
                io.ktor.http.content.OutgoingContent r0 = (io.ktor.http.content.OutgoingContent) r0
                r1.transformBodyTo(r0)
            L_0x0099:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt$ConditionalHeaders$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
