package io.ktor.server.engine;

import androidx.lifecycle.CoroutineLiveDataKt;
import io.ktor.server.application.Application;
import io.ktor.server.engine.internal.ApplicationUtilsJvmKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0016J\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u0010H&J\u001c\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "", "application", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "getEnvironment", "()Lio/ktor/server/engine/ApplicationEngineEnvironment;", "resolvedConnectors", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "wait", "", "stop", "", "gracePeriodMillis", "", "timeoutMillis", "Configuration", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngine.kt */
public interface ApplicationEngine {
    Application getApplication();

    ApplicationEngineEnvironment getEnvironment();

    Object resolvedConnectors(Continuation<? super List<? extends EngineConnectorConfig>> continuation);

    ApplicationEngine start(boolean z);

    void stop(long j, long j2);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\b¨\u0006\u001a"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine$Configuration;", "", "()V", "callGroupSize", "", "getCallGroupSize", "()I", "setCallGroupSize", "(I)V", "connectionGroupSize", "getConnectionGroupSize", "setConnectionGroupSize", "parallelism", "getParallelism", "shutdownGracePeriod", "", "getShutdownGracePeriod", "()J", "setShutdownGracePeriod", "(J)V", "shutdownTimeout", "getShutdownTimeout", "setShutdownTimeout", "workerGroupSize", "getWorkerGroupSize", "setWorkerGroupSize", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ApplicationEngine.kt */
    public static class Configuration {
        private int callGroupSize;
        private int connectionGroupSize;
        private final int parallelism;
        private long shutdownGracePeriod = 1000;
        private long shutdownTimeout = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
        private int workerGroupSize;

        public Configuration() {
            int availableProcessorsBridge = ApplicationUtilsJvmKt.availableProcessorsBridge();
            this.parallelism = availableProcessorsBridge;
            this.connectionGroupSize = (availableProcessorsBridge / 2) + 1;
            this.workerGroupSize = (availableProcessorsBridge / 2) + 1;
            this.callGroupSize = availableProcessorsBridge;
        }

        public final int getParallelism() {
            return this.parallelism;
        }

        public final int getConnectionGroupSize() {
            return this.connectionGroupSize;
        }

        public final void setConnectionGroupSize(int i) {
            this.connectionGroupSize = i;
        }

        public final int getWorkerGroupSize() {
            return this.workerGroupSize;
        }

        public final void setWorkerGroupSize(int i) {
            this.workerGroupSize = i;
        }

        public final int getCallGroupSize() {
            return this.callGroupSize;
        }

        public final void setCallGroupSize(int i) {
            this.callGroupSize = i;
        }

        public final long getShutdownGracePeriod() {
            return this.shutdownGracePeriod;
        }

        public final void setShutdownGracePeriod(long j) {
            this.shutdownGracePeriod = j;
        }

        public final long getShutdownTimeout() {
            return this.shutdownTimeout;
        }

        public final void setShutdownTimeout(long j) {
            this.shutdownTimeout = j;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ApplicationEngine.kt */
    public static final class DefaultImpls {
        public static Application getApplication(ApplicationEngine applicationEngine) {
            return applicationEngine.getEnvironment().getApplication();
        }

        public static /* synthetic */ ApplicationEngine start$default(ApplicationEngine applicationEngine, boolean z, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    z = false;
                }
                return applicationEngine.start(z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: start");
        }

        public static /* synthetic */ void stop$default(ApplicationEngine applicationEngine, long j, long j2, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    j = 500;
                }
                if ((i & 2) != 0) {
                    j2 = 500;
                }
                applicationEngine.stop(j, j2);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stop");
        }
    }
}
