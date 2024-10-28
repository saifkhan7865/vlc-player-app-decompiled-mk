package io.ktor.server.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/server/config/ApplicationConfigurationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationConfig.kt */
public final class ApplicationConfigurationException extends Exception {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApplicationConfigurationException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "message");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ApplicationConfigurationException(String str) {
        this(str, (Throwable) null);
        Intrinsics.checkNotNullParameter(str, "message");
    }
}
