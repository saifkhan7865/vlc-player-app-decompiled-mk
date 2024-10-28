package io.ktor.util.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/util/pipeline/InvalidPhaseException;", "", "message", "", "(Ljava/lang/String;)V", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PipelinePhase.kt */
public final class InvalidPhaseException extends Throwable {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidPhaseException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "message");
    }
}
