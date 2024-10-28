package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public abstract class InetNameResolver extends SimpleNameResolver<InetAddress> {
    private volatile AddressResolver<InetSocketAddress> addressResolver;

    protected InetNameResolver(EventExecutor eventExecutor) {
        super(eventExecutor);
    }

    public AddressResolver<InetSocketAddress> asAddressResolver() {
        AddressResolver<InetSocketAddress> addressResolver2 = this.addressResolver;
        if (addressResolver2 == null) {
            synchronized (this) {
                addressResolver2 = this.addressResolver;
                if (addressResolver2 == null) {
                    addressResolver2 = new InetSocketAddressResolver(executor(), this);
                    this.addressResolver = addressResolver2;
                }
            }
        }
        return addressResolver2;
    }
}
