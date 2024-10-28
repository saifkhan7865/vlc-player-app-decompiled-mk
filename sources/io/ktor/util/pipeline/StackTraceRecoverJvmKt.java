package io.ktor.util.pipeline;

import io.ktor.utils.io.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0003"}, d2 = {"withCause", "", "cause", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: StackTraceRecoverJvm.kt */
public final class StackTraceRecoverJvmKt {
    public static final Throwable withCause(Throwable th, Throwable th2) {
        Throwable tryCopyException;
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th2 == null || Intrinsics.areEqual((Object) th.getCause(), (Object) th2) || (tryCopyException = ExceptionUtilsJvmKt.tryCopyException(th, th2)) == null) {
            return th;
        }
        tryCopyException.setStackTrace(th.getStackTrace());
        return tryCopyException;
    }
}
