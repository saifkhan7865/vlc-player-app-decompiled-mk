package org.videolan.resources.util;

import java.io.IOException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/resources/util/NoConnectivityException;", "Ljava/io/IOException;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConnectivityInterceptor.kt */
public final class NoConnectivityException extends IOException {
    public String getMessage() {
        return "No connectivity exception";
    }
}
