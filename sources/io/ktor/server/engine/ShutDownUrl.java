package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.BaseApplicationPlugin;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.PluginInstance;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00122\u00020\u0001:\u0003\u0012\u0013\u0014B&\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¢\u0006\u0002\u0010\tJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\"\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl;", "", "url", "", "exitCode", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getExitCode", "()Lkotlin/jvm/functions/Function1;", "getUrl", "()Ljava/lang/String;", "doShutdown", "", "call", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Config", "EnginePlugin", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutDownUrl.kt */
public final class ShutDownUrl {
    /* access modifiers changed from: private */
    public static final BaseApplicationPlugin<Application, Config, PluginInstance> ApplicationCallPlugin = CreatePluginUtilsKt.createApplicationPlugin("shutdown.url", ShutDownUrl$Companion$ApplicationCallPlugin$1.INSTANCE, ShutDownUrl$Companion$ApplicationCallPlugin$2.INSTANCE);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Function1<ApplicationCall, Integer> exitCode;
    private final String url;

    public ShutDownUrl(String str, Function1<? super ApplicationCall, Integer> function1) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        Intrinsics.checkNotNullParameter(function1, "exitCode");
        this.url = str;
        this.exitCode = function1;
    }

    public final Function1<ApplicationCall, Integer> getExitCode() {
        return this.exitCode;
    }

    public final String getUrl() {
        return this.url;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: kotlinx.coroutines.CompletableDeferred} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object doShutdown(io.ktor.server.application.ApplicationCall r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r20
            r2 = r21
            boolean r3 = r2 instanceof io.ktor.server.engine.ShutDownUrl$doShutdown$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            io.ktor.server.engine.ShutDownUrl$doShutdown$1 r3 = (io.ktor.server.engine.ShutDownUrl$doShutdown$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            io.ktor.server.engine.ShutDownUrl$doShutdown$1 r3 = new io.ktor.server.engine.ShutDownUrl$doShutdown$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0042
            if (r5 != r6) goto L_0x003a
            java.lang.Object r0 = r3.L$0
            r3 = r0
            kotlinx.coroutines.CompletableDeferred r3 = (kotlinx.coroutines.CompletableDeferred) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0037 }
            goto L_0x00ca
        L_0x0037:
            r0 = move-exception
            goto L_0x00d4
        L_0x003a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r2)
            io.ktor.server.application.Application r2 = r20.getApplication()
            org.slf4j.Logger r2 = io.ktor.server.application.ApplicationKt.getLog(r2)
            java.lang.String r5 = "Shutdown URL was called: server is going down"
            r2.warn(r5)
            io.ktor.server.application.Application r11 = r20.getApplication()
            io.ktor.server.application.ApplicationEnvironment r10 = r11.getEnvironment()
            kotlin.jvm.functions.Function1<io.ktor.server.application.ApplicationCall, java.lang.Integer> r2 = r1.exitCode
            java.lang.Object r2 = r2.invoke(r0)
            java.lang.Number r2 = (java.lang.Number) r2
            int r12 = r2.intValue()
            kotlinx.coroutines.CompletableDeferred r2 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r7, r6, r7)
            io.ktor.server.application.Application r5 = r20.getApplication()
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            io.ktor.server.engine.ShutDownUrl$doShutdown$2 r14 = new io.ktor.server.engine.ShutDownUrl$doShutdown$2
            r13 = 0
            r8 = r14
            r9 = r2
            r8.<init>(r9, r10, r11, r12, r13)
            r16 = r14
            kotlin.jvm.functions.Function2 r16 = (kotlin.jvm.functions.Function2) r16
            r17 = 3
            r18 = 0
            r14 = 0
            r15 = 0
            r13 = r5
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r13, r14, r15, r16, r17, r18)
            io.ktor.http.HttpStatusCode$Companion r5 = io.ktor.http.HttpStatusCode.Companion     // Catch:{ all -> 0x00d2 }
            io.ktor.http.HttpStatusCode r5 = r5.getGone()     // Catch:{ all -> 0x00d2 }
            boolean r8 = r5 instanceof io.ktor.http.content.OutgoingContent     // Catch:{ all -> 0x00d2 }
            if (r8 != 0) goto L_0x00af
            boolean r8 = r5 instanceof byte[]     // Catch:{ all -> 0x00d2 }
            if (r8 != 0) goto L_0x00af
            io.ktor.server.response.ApplicationResponse r8 = r20.getResponse()     // Catch:{ all -> 0x00d2 }
            java.lang.Class<io.ktor.http.HttpStatusCode> r9 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r9 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r9)     // Catch:{ all -> 0x00d2 }
            java.lang.reflect.Type r10 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r9)     // Catch:{ all -> 0x00d2 }
            java.lang.Class<io.ktor.http.HttpStatusCode> r11 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ all -> 0x00d2 }
            io.ktor.util.reflect.TypeInfo r9 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r10, r11, r9)     // Catch:{ all -> 0x00d2 }
            io.ktor.server.response.ResponseTypeKt.setResponseType(r8, r9)     // Catch:{ all -> 0x00d2 }
        L_0x00af:
            io.ktor.server.response.ApplicationResponse r8 = r20.getResponse()     // Catch:{ all -> 0x00d2 }
            io.ktor.server.response.ApplicationSendPipeline r8 = r8.getPipeline()     // Catch:{ all -> 0x00d2 }
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r9)     // Catch:{ all -> 0x00d2 }
            java.lang.Object r5 = (java.lang.Object) r5     // Catch:{ all -> 0x00d2 }
            r3.L$0 = r2     // Catch:{ all -> 0x00d2 }
            r3.label = r6     // Catch:{ all -> 0x00d2 }
            java.lang.Object r0 = r8.execute(r0, r5, r3)     // Catch:{ all -> 0x00d2 }
            if (r0 != r4) goto L_0x00c9
            return r4
        L_0x00c9:
            r3 = r2
        L_0x00ca:
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3
            kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r3, (java.util.concurrent.CancellationException) r7, (int) r6, (java.lang.Object) r7)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00d2:
            r0 = move-exception
            r3 = r2
        L_0x00d4:
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3
            kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r3, (java.util.concurrent.CancellationException) r7, (int) r6, (java.lang.Object) r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ShutDownUrl.doShutdown(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J)\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00022\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl$EnginePlugin;", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/engine/EnginePipeline;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "Lio/ktor/server/engine/ShutDownUrl;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "pipeline", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ShutDownUrl.kt */
    public static final class EnginePlugin implements BaseApplicationPlugin<EnginePipeline, Config, ShutDownUrl> {
        public static final EnginePlugin INSTANCE = new EnginePlugin();
        private static final AttributeKey<ShutDownUrl> key = new AttributeKey<>("shutdown.url");

        private EnginePlugin() {
        }

        public AttributeKey<ShutDownUrl> getKey() {
            return key;
        }

        public ShutDownUrl install(EnginePipeline enginePipeline, Function1<? super Config, Unit> function1) {
            Intrinsics.checkNotNullParameter(enginePipeline, "pipeline");
            Intrinsics.checkNotNullParameter(function1, "configure");
            Config config = new Config();
            function1.invoke(config);
            ShutDownUrl shutDownUrl = new ShutDownUrl(config.getShutDownUrl(), config.getExitCodeSupplier());
            enginePipeline.intercept(EnginePipeline.Companion.getBefore(), new ShutDownUrl$EnginePlugin$install$1(shutDownUrl, (Continuation<? super ShutDownUrl$EnginePlugin$install$1>) null));
            return shutDownUrl;
        }
    }

    @KtorDsl
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R+\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl$Config;", "", "()V", "exitCodeSupplier", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "Lkotlin/ExtensionFunctionType;", "getExitCodeSupplier", "()Lkotlin/jvm/functions/Function1;", "setExitCodeSupplier", "(Lkotlin/jvm/functions/Function1;)V", "shutDownUrl", "", "getShutDownUrl", "()Ljava/lang/String;", "setShutDownUrl", "(Ljava/lang/String;)V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ShutDownUrl.kt */
    public static final class Config {
        private Function1<? super ApplicationCall, Integer> exitCodeSupplier = ShutDownUrl$Config$exitCodeSupplier$1.INSTANCE;
        private String shutDownUrl = "/ktor/application/shutdown";

        public final String getShutDownUrl() {
            return this.shutDownUrl;
        }

        public final void setShutDownUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.shutDownUrl = str;
        }

        public final Function1<ApplicationCall, Integer> getExitCodeSupplier() {
            return this.exitCodeSupplier;
        }

        public final void setExitCodeSupplier(Function1<? super ApplicationCall, Integer> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.exitCodeSupplier = function1;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl$Companion;", "", "()V", "ApplicationCallPlugin", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/application/Application;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "Lio/ktor/server/application/PluginInstance;", "getApplicationCallPlugin", "()Lio/ktor/server/application/BaseApplicationPlugin;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ShutDownUrl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BaseApplicationPlugin<Application, Config, PluginInstance> getApplicationCallPlugin() {
            return ShutDownUrl.ApplicationCallPlugin;
        }
    }
}
