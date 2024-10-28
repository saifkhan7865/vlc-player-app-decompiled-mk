package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u000028\u00124\u00122\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\tJH\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r26\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0016¨\u0006\u000f"}, d2 = {"Lio/ktor/server/application/hooks/ReceiveRequestBytes;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function2;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "name", "call", "Lio/ktor/utils/io/ByteReadChannel;", "body", "()V", "install", "", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommonHooks.kt */
public final class ReceiveRequestBytes implements Hook<Function2<? super ApplicationCall, ? super ByteReadChannel, ? extends ByteReadChannel>> {
    public static final ReceiveRequestBytes INSTANCE = new ReceiveRequestBytes();

    private ReceiveRequestBytes() {
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function2<? super ApplicationCall, ? super ByteReadChannel, ? extends ByteReadChannel> function2) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function2, "handler");
        applicationCallPipeline.getReceivePipeline().intercept(ApplicationReceivePipeline.Phases.getBefore(), new ReceiveRequestBytes$install$1(function2, (Continuation<? super ReceiveRequestBytes$install$1>) null));
    }
}
