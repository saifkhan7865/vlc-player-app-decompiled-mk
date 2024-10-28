package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.internal.EngineUtilsJvmKt;
import io.ktor.util.date.DateJvmKt;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/server/engine/BaseApplicationEngine;", "Lio/ktor/server/engine/ApplicationEngine;", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "pipeline", "Lio/ktor/server/engine/EnginePipeline;", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lio/ktor/server/engine/EnginePipeline;)V", "getEnvironment", "()Lio/ktor/server/engine/ApplicationEngineEnvironment;", "getPipeline", "()Lio/ktor/server/engine/EnginePipeline;", "resolvedConnectors", "Lkotlinx/coroutines/CompletableDeferred;", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "getResolvedConnectors", "()Lkotlinx/coroutines/CompletableDeferred;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Configuration", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationEngine.kt */
public abstract class BaseApplicationEngine implements ApplicationEngine {
    private final ApplicationEngineEnvironment environment;
    private final EnginePipeline pipeline;
    private final CompletableDeferred<List<EngineConnectorConfig>> resolvedConnectors;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/engine/BaseApplicationEngine$Configuration;", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "()V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BaseApplicationEngine.kt */
    public static class Configuration extends ApplicationEngine.Configuration {
    }

    public Object resolvedConnectors(Continuation<? super List<? extends EngineConnectorConfig>> continuation) {
        return this.resolvedConnectors.await(continuation);
    }

    public BaseApplicationEngine(final ApplicationEngineEnvironment applicationEngineEnvironment, final EnginePipeline enginePipeline) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironment, "environment");
        Intrinsics.checkNotNullParameter(enginePipeline, "pipeline");
        this.environment = applicationEngineEnvironment;
        this.pipeline = enginePipeline;
        final CompletableDeferred<List<EngineConnectorConfig>> CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        this.resolvedConnectors = CompletableDeferred$default;
        final StartupInfo startupInfo = new StartupInfo();
        BaseApplicationResponse.Companion.setupSendPipeline(enginePipeline.getSendPipeline());
        applicationEngineEnvironment.getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStarting(), new Function1<Application, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Application) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Application application) {
                Intrinsics.checkNotNullParameter(application, "it");
                if (!startupInfo.isFirstLoading()) {
                    startupInfo.setInitializedStartAt(DateJvmKt.getTimeMillis());
                }
                application.getReceivePipeline().merge(enginePipeline.getReceivePipeline());
                application.getSendPipeline().merge(enginePipeline.getSendPipeline());
                DefaultTransformKt.installDefaultTransformations(application.getReceivePipeline());
                DefaultTransformKt.installDefaultTransformations(application.getSendPipeline());
                BaseApplicationEngineKt.installDefaultInterceptors(application);
                BaseApplicationEngineKt.installDefaultTransformationChecker(application);
            }
        });
        applicationEngineEnvironment.getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStarted(), new Function1<Application, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Application) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Application application) {
                Intrinsics.checkNotNullParameter(application, "it");
                double timeMillis = (double) (DateJvmKt.getTimeMillis() - startupInfo.getInitializedStartAt());
                Double.isNaN(timeMillis);
                double d = timeMillis / 1000.0d;
                if (startupInfo.isFirstLoading()) {
                    Logger log = applicationEngineEnvironment.getLog();
                    log.info("Application started in " + d + " seconds.");
                    startupInfo.setFirstLoading(false);
                    return;
                }
                Logger log2 = applicationEngineEnvironment.getLog();
                log2.info("Application auto-reloaded in " + d + " seconds.");
            }
        });
        final Logger log = applicationEngineEnvironment.getLog();
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(applicationEngineEnvironment.getApplication().getCoroutineContext()), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3((Continuation<? super AnonymousClass3>) null), 3, (Object) null);
    }

    public Application getApplication() {
        return ApplicationEngine.DefaultImpls.getApplication(this);
    }

    public final ApplicationEngineEnvironment getEnvironment() {
        return this.environment;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseApplicationEngine(ApplicationEngineEnvironment applicationEngineEnvironment, EnginePipeline enginePipeline, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicationEngineEnvironment, (i & 2) != 0 ? DefaultEnginePipelineKt.defaultEnginePipeline(applicationEngineEnvironment) : enginePipeline);
    }

    public final EnginePipeline getPipeline() {
        return this.pipeline;
    }

    /* access modifiers changed from: protected */
    public final CompletableDeferred<List<EngineConnectorConfig>> getResolvedConnectors() {
        return this.resolvedConnectors;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.engine.BaseApplicationEngine$3", f = "BaseApplicationEngine.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.engine.BaseApplicationEngine$3  reason: invalid class name */
    /* compiled from: BaseApplicationEngine.kt */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(CompletableDeferred$default, log, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = CompletableDeferred$default.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Logger logger = log;
            for (EngineConnectorConfig engineConnectorConfig : (Iterable) obj) {
                String escapeHostname = EngineUtilsJvmKt.escapeHostname(engineConnectorConfig.getHost());
                StringBuilder sb = new StringBuilder("Responding at ");
                String lowerCase = engineConnectorConfig.getType().getName().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                sb.append(lowerCase);
                sb.append("://");
                sb.append(escapeHostname);
                sb.append(AbstractJsonLexerKt.COLON);
                sb.append(engineConnectorConfig.getPort());
                logger.info(sb.toString());
            }
            return Unit.INSTANCE;
        }
    }
}
