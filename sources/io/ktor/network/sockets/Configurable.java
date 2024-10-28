package io.ktor.network.sockets;

import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0016\b\u0000\u0010\u0001 \u0001*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0000*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004J&\u0010\n\u001a\u00028\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u0018\u0010\u0005\u001a\u00028\u0001X¦\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lio/ktor/network/sockets/Configurable;", "T", "Options", "Lio/ktor/network/sockets/SocketOptions;", "", "options", "getOptions", "()Lio/ktor/network/sockets/SocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions;)V", "configure", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Lio/ktor/network/sockets/Configurable;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Builders.kt */
public interface Configurable<T extends Configurable<? extends T, Options>, Options extends SocketOptions> {
    T configure(Function1<? super Options, Unit> function1);

    Options getOptions();

    void setOptions(Options options);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Builders.kt */
    public static final class DefaultImpls {
        public static <T extends Configurable<? extends T, Options>, Options extends SocketOptions> T configure(Configurable<? extends T, Options> configurable, Function1<? super Options, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            SocketOptions copy$ktor_network = configurable.getOptions().copy$ktor_network();
            Intrinsics.checkNotNull(copy$ktor_network, "null cannot be cast to non-null type Options of io.ktor.network.sockets.Configurable");
            function1.invoke(copy$ktor_network);
            configurable.setOptions(copy$ktor_network);
            Intrinsics.checkNotNull(configurable, "null cannot be cast to non-null type T of io.ktor.network.sockets.Configurable");
            return configurable;
        }
    }
}
