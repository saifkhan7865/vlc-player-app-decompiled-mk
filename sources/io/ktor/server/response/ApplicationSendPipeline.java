package io.ktor.server.response;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u0000 \t2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\tB\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lio/ktor/server/response/ApplicationSendPipeline;", "Lio/ktor/util/pipeline/Pipeline;", "", "Lio/ktor/server/application/ApplicationCall;", "developmentMode", "", "(Z)V", "getDevelopmentMode", "()Z", "Phases", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationSendPipeline.kt */
public class ApplicationSendPipeline extends Pipeline<Object, ApplicationCall> {
    /* access modifiers changed from: private */
    public static final PipelinePhase After = new PipelinePhase("After");
    /* access modifiers changed from: private */
    public static final PipelinePhase Before = new PipelinePhase("Before");
    /* access modifiers changed from: private */
    public static final PipelinePhase ContentEncoding = new PipelinePhase("ContentEncoding");
    /* access modifiers changed from: private */
    public static final PipelinePhase Engine = new PipelinePhase("Engine");
    public static final Phases Phases = new Phases((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final PipelinePhase Render = new PipelinePhase("Render");
    /* access modifiers changed from: private */
    public static final PipelinePhase TransferEncoding = new PipelinePhase("TransferEncoding");
    /* access modifiers changed from: private */
    public static final PipelinePhase Transform = new PipelinePhase("Transform");
    private final boolean developmentMode;

    public ApplicationSendPipeline() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ApplicationSendPipeline(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public ApplicationSendPipeline(boolean z) {
        super(Before, Transform, Render, ContentEncoding, TransferEncoding, After, Engine);
        this.developmentMode = z;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, d2 = {"Lio/ktor/server/response/ApplicationSendPipeline$Phases;", "", "()V", "After", "Lio/ktor/util/pipeline/PipelinePhase;", "getAfter", "()Lio/ktor/util/pipeline/PipelinePhase;", "Before", "getBefore", "ContentEncoding", "getContentEncoding", "Engine", "getEngine", "Render", "getRender", "TransferEncoding", "getTransferEncoding", "Transform", "getTransform", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ApplicationSendPipeline.kt */
    public static final class Phases {
        public /* synthetic */ Phases(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Phases() {
        }

        public final PipelinePhase getBefore() {
            return ApplicationSendPipeline.Before;
        }

        public final PipelinePhase getTransform() {
            return ApplicationSendPipeline.Transform;
        }

        public final PipelinePhase getRender() {
            return ApplicationSendPipeline.Render;
        }

        public final PipelinePhase getContentEncoding() {
            return ApplicationSendPipeline.ContentEncoding;
        }

        public final PipelinePhase getTransferEncoding() {
            return ApplicationSendPipeline.TransferEncoding;
        }

        public final PipelinePhase getAfter() {
            return ApplicationSendPipeline.After;
        }

        public final PipelinePhase getEngine() {
            return ApplicationSendPipeline.Engine;
        }
    }
}
