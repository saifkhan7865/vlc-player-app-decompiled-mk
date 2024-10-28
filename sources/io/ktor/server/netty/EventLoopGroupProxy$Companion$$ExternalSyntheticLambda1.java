package io.ktor.server.netty;

import io.ktor.server.netty.EventLoopGroupProxy;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.concurrent.ThreadFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EventLoopGroupProxy$Companion$$ExternalSyntheticLambda1 implements ThreadFactory {
    public final /* synthetic */ DefaultThreadFactory f$0;

    public /* synthetic */ EventLoopGroupProxy$Companion$$ExternalSyntheticLambda1(DefaultThreadFactory defaultThreadFactory) {
        this.f$0 = defaultThreadFactory;
    }

    public final Thread newThread(Runnable runnable) {
        return EventLoopGroupProxy.Companion.create$lambda$1(this.f$0, runnable);
    }
}
