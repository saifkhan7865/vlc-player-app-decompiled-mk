package io.ktor.server.plugins;

import io.ktor.util.internal.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\u0000H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\r"}, d2 = {"Lio/ktor/server/plugins/ParameterConversionException;", "Lio/ktor/server/plugins/BadRequestException;", "Lkotlinx/coroutines/CopyableThrowable;", "parameterName", "", "type", "cause", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "getParameterName", "()Ljava/lang/String;", "getType", "createCopy", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Errors.kt */
public final class ParameterConversionException extends BadRequestException implements CopyableThrowable<ParameterConversionException> {
    private final String parameterName;
    private final String type;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ParameterConversionException(String str, String str2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : th);
    }

    public final String getParameterName() {
        return this.parameterName;
    }

    public final String getType() {
        return this.type;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ParameterConversionException(String str, String str2, Throwable th) {
        super("Request parameter " + str + " couldn't be parsed/converted to " + str2, th);
        Intrinsics.checkNotNullParameter(str, "parameterName");
        Intrinsics.checkNotNullParameter(str2, "type");
        this.parameterName = str;
        this.type = str2;
    }

    public ParameterConversionException createCopy() {
        Throwable th = this;
        ParameterConversionException parameterConversionException = new ParameterConversionException(this.parameterName, this.type, th);
        ExceptionUtilsJvmKt.initCauseBridge(parameterConversionException, th);
        return parameterConversionException;
    }
}
