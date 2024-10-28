package io.ktor.server.engine;

import io.ktor.util.date.DateJvmKt;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/engine/StartupInfo;", "", "()V", "initializedStartAt", "", "getInitializedStartAt", "()J", "setInitializedStartAt", "(J)V", "isFirstLoading", "", "()Z", "setFirstLoading", "(Z)V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationEngine.kt */
final class StartupInfo {
    private long initializedStartAt = DateJvmKt.getTimeMillis();
    private boolean isFirstLoading = true;

    public final boolean isFirstLoading() {
        return this.isFirstLoading;
    }

    public final void setFirstLoading(boolean z) {
        this.isFirstLoading = z;
    }

    public final long getInitializedStartAt() {
        return this.initializedStartAt;
    }

    public final void setInitializedStartAt(long j) {
        this.initializedStartAt = j;
    }
}
