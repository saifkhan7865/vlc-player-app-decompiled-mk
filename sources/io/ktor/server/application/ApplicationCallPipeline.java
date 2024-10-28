package io.ktor.server.application;

import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00152\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0015B\u001b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lio/ktor/server/application/ApplicationCallPipeline;", "Lio/ktor/util/pipeline/Pipeline;", "", "Lio/ktor/server/application/ApplicationCall;", "developmentMode", "", "environment", "Lio/ktor/server/application/ApplicationEnvironment;", "(ZLio/ktor/server/application/ApplicationEnvironment;)V", "getDevelopmentMode", "()Z", "getEnvironment", "()Lio/ktor/server/application/ApplicationEnvironment;", "receivePipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "getReceivePipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "sendPipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getSendPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "ApplicationPhase", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationCallPipeline.kt */
public class ApplicationCallPipeline extends Pipeline<Unit, ApplicationCall> {
    public static final ApplicationPhase ApplicationPhase = new ApplicationPhase((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final PipelinePhase Call = new PipelinePhase("Call");
    /* access modifiers changed from: private */
    public static final PipelinePhase Fallback = new PipelinePhase("Fallback");
    /* access modifiers changed from: private */
    public static final PipelinePhase Features;
    /* access modifiers changed from: private */
    public static final PipelinePhase Monitoring = new PipelinePhase("Monitoring");
    /* access modifiers changed from: private */
    public static final PipelinePhase Plugins;
    /* access modifiers changed from: private */
    public static final PipelinePhase Setup = new PipelinePhase("Setup");
    private final boolean developmentMode;
    private final ApplicationEnvironment environment;
    private final ApplicationReceivePipeline receivePipeline;
    private final ApplicationSendPipeline sendPipeline;

    public ApplicationCallPipeline() {
        this(false, (ApplicationEnvironment) null, 3, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ApplicationCallPipeline(boolean z, ApplicationEnvironment applicationEnvironment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : applicationEnvironment);
    }

    public final boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public ApplicationEnvironment getEnvironment() {
        return this.environment;
    }

    public ApplicationCallPipeline(boolean z, ApplicationEnvironment applicationEnvironment) {
        super(Setup, Monitoring, Plugins, Call, Fallback);
        this.developmentMode = z;
        this.environment = applicationEnvironment;
        this.receivePipeline = new ApplicationReceivePipeline(z);
        this.sendPipeline = new ApplicationSendPipeline(z);
    }

    public final ApplicationReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final ApplicationSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0006R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0006R\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006¨\u0006\u0012"}, d2 = {"Lio/ktor/server/application/ApplicationCallPipeline$ApplicationPhase;", "", "()V", "Call", "Lio/ktor/util/pipeline/PipelinePhase;", "getCall", "()Lio/ktor/util/pipeline/PipelinePhase;", "Fallback", "getFallback", "Features", "getFeatures$annotations", "getFeatures", "Monitoring", "getMonitoring", "Plugins", "getPlugins", "Setup", "getSetup", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ApplicationCallPipeline.kt */
    public static final class ApplicationPhase {
        public /* synthetic */ ApplicationPhase(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "Renamed to Plugins", replaceWith = @ReplaceWith(expression = "Plugins", imports = {}))
        public static /* synthetic */ void getFeatures$annotations() {
        }

        private ApplicationPhase() {
        }

        public final PipelinePhase getSetup() {
            return ApplicationCallPipeline.Setup;
        }

        public final PipelinePhase getMonitoring() {
            return ApplicationCallPipeline.Monitoring;
        }

        public final PipelinePhase getPlugins() {
            return ApplicationCallPipeline.Plugins;
        }

        public final PipelinePhase getCall() {
            return ApplicationCallPipeline.Call;
        }

        public final PipelinePhase getFallback() {
            return ApplicationCallPipeline.Fallback;
        }

        public final PipelinePhase getFeatures() {
            return ApplicationCallPipeline.Features;
        }
    }

    static {
        PipelinePhase pipelinePhase = new PipelinePhase("Plugins");
        Plugins = pipelinePhase;
        Features = pipelinePhase;
    }
}
