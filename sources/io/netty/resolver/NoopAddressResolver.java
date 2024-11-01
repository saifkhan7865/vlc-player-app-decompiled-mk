package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;

public class NoopAddressResolver extends AbstractAddressResolver<SocketAddress> {
    /* access modifiers changed from: protected */
    public boolean doIsResolved(SocketAddress socketAddress) {
        return true;
    }

    public NoopAddressResolver(EventExecutor eventExecutor) {
        super(eventExecutor);
    }

    /* access modifiers changed from: protected */
    public void doResolve(SocketAddress socketAddress, Promise<SocketAddress> promise) throws Exception {
        promise.setSuccess(socketAddress);
    }

    /* access modifiers changed from: protected */
    public void doResolveAll(SocketAddress socketAddress, Promise<List<SocketAddress>> promise) throws Exception {
        promise.setSuccess(Collections.singletonList(socketAddress));
    }
}
