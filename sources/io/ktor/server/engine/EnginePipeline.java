package io.ktor.server.engine;

import androidx.core.app.NotificationCompat;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0011B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lio/ktor/server/engine/EnginePipeline;", "Lio/ktor/util/pipeline/Pipeline;", "", "Lio/ktor/server/application/ApplicationCall;", "developmentMode", "", "(Z)V", "getDevelopmentMode", "()Z", "receivePipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "getReceivePipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "sendPipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getSendPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "Companion", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EnginePipeline.kt */
public final class EnginePipeline extends Pipeline<Unit, ApplicationCall> {
    /* access modifiers changed from: private */
    public static final PipelinePhase Before = new PipelinePhase("before");
    /* access modifiers changed from: private */
    public static final PipelinePhase Call = new PipelinePhase(NotificationCompat.CATEGORY_CALL);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean developmentMode;
    private final ApplicationReceivePipeline receivePipeline;
    private final ApplicationSendPipeline sendPipeline;

    public EnginePipeline() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EnginePipeline(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public EnginePipeline(boolean z) {
        super(Before, Call);
        this.developmentMode = z;
        this.receivePipeline = new ApplicationReceivePipeline(getDevelopmentMode());
        this.sendPipeline = new ApplicationSendPipeline(getDevelopmentMode());
    }

    public final ApplicationReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final ApplicationSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/EnginePipeline$Companion;", "", "()V", "Before", "Lio/ktor/util/pipeline/PipelinePhase;", "getBefore", "()Lio/ktor/util/pipeline/PipelinePhase;", "Call", "getCall", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: EnginePipeline.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PipelinePhase getBefore() {
            return EnginePipeline.Before;
        }

        public final PipelinePhase getCall() {
            return EnginePipeline.Call;
        }
    }
}
