package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ServerChannel;

public abstract class ChannelInitializerExtension {
    public static final String EXTENSIONS_SYSTEM_PROPERTY = "io.netty.bootstrap.extensions";

    public void postInitializeClientChannel(Channel channel) {
    }

    public void postInitializeServerChildChannel(Channel channel) {
    }

    public void postInitializeServerListenerChannel(ServerChannel serverChannel) {
    }

    public double priority() {
        return 0.0d;
    }
}
