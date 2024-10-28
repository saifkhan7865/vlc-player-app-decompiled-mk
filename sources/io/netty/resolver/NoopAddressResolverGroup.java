package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import java.net.SocketAddress;

public final class NoopAddressResolverGroup extends AddressResolverGroup<SocketAddress> {
    public static final NoopAddressResolverGroup INSTANCE = new NoopAddressResolverGroup();

    private NoopAddressResolverGroup() {
    }

    /* access modifiers changed from: protected */
    public AddressResolver<SocketAddress> newResolver(EventExecutor eventExecutor) throws Exception {
        return new NoopAddressResolver(eventExecutor);
    }
}
