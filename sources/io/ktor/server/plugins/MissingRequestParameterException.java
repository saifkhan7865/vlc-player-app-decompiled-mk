package io.ktor.server.plugins;

import io.ktor.util.internal.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0000H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lio/ktor/server/plugins/MissingRequestParameterException;", "Lio/ktor/server/plugins/BadRequestException;", "Lkotlinx/coroutines/CopyableThrowable;", "parameterName", "", "(Ljava/lang/String;)V", "getParameterName", "()Ljava/lang/String;", "createCopy", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Errors.kt */
public final class MissingRequestParameterException extends BadRequestException implements CopyableThrowable<MissingRequestParameterException> {
    private final String parameterName;

    public final String getParameterName() {
        return this.parameterName;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MissingRequestParameterException(String str) {
        super("Request parameter " + str + " is missing", (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "parameterName");
        this.parameterName = str;
    }

    public MissingRequestParameterException createCopy() {
        MissingRequestParameterException missingRequestParameterException = new MissingRequestParameterException(this.parameterName);
        ExceptionUtilsJvmKt.initCauseBridge(missingRequestParameterException, this);
        return missingRequestParameterException;
    }
}
