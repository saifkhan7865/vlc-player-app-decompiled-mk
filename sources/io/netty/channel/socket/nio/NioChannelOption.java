package io.netty.channel.socket.nio;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.Channel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Set;

public final class NioChannelOption<T> extends ChannelOption<T> {
    private final SocketOption<T> option;

    private NioChannelOption(SocketOption<T> socketOption) {
        super(socketOption.name());
        this.option = socketOption;
    }

    public static <T> ChannelOption<T> of(SocketOption<T> socketOption) {
        return new NioChannelOption(socketOption);
    }

    static <T> boolean setOption(Channel channel, NioChannelOption<T> nioChannelOption, T t) {
        NetworkChannel m = NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) channel);
        if (!m.supportedOptions().contains(nioChannelOption.option)) {
            return false;
        }
        if ((m instanceof ServerSocketChannel) && nioChannelOption.option == NioPathKt$$ExternalSyntheticApiModelOutline0.m()) {
            return false;
        }
        try {
            NetworkChannel unused = m.setOption(nioChannelOption.option, t);
            return true;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    static <T> T getOption(Channel channel, NioChannelOption<T> nioChannelOption) {
        NetworkChannel m = NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) channel);
        if (!m.supportedOptions().contains(nioChannelOption.option)) {
            return null;
        }
        if ((m instanceof ServerSocketChannel) && nioChannelOption.option == NioPathKt$$ExternalSyntheticApiModelOutline0.m()) {
            return null;
        }
        try {
            return m.getOption(nioChannelOption.option);
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    static ChannelOption[] getOptions(Channel channel) {
        NetworkChannel m = NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) channel);
        Set<Object> m2 = m.supportedOptions();
        int i = 0;
        if (m instanceof ServerSocketChannel) {
            ArrayList arrayList = new ArrayList(m2.size());
            for (Object m3 : m2) {
                SocketOption m4 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(m3);
                if (m4 != NioPathKt$$ExternalSyntheticApiModelOutline0.m()) {
                    arrayList.add(new NioChannelOption(m4));
                }
            }
            return (ChannelOption[]) arrayList.toArray(new ChannelOption[0]);
        }
        ChannelOption[] channelOptionArr = new ChannelOption[m2.size()];
        for (Object m5 : m2) {
            channelOptionArr[i] = new NioChannelOption(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m5));
            i++;
        }
        return channelOptionArr;
    }
}
