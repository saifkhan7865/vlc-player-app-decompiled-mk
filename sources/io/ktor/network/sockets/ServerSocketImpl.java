package io.ktor.network.sockets;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.selector.InterestSuspensionsMap;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectableBase;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.ServerSocket;
import io.ktor.network.sockets.SocketOptions;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u001f\u001a\u00020 H@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0011\u0010\"\u001a\u00020 H@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0010\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020'H\u0016J\u0019\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000fH\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u001cX\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lio/ktor/network/sockets/ServerSocketImpl;", "Lio/ktor/network/sockets/ServerSocket;", "Lio/ktor/network/selector/Selectable;", "channel", "Ljava/nio/channels/ServerSocketChannel;", "selector", "Lio/ktor/network/selector/SelectorManager;", "(Ljava/nio/channels/ServerSocketChannel;Lio/ktor/network/selector/SelectorManager;)V", "getChannel", "()Ljava/nio/channels/ServerSocketChannel;", "interestedOps", "", "getInterestedOps", "()I", "isClosed", "", "()Z", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "getSelector", "()Lio/ktor/network/selector/SelectorManager;", "socketContext", "Lkotlinx/coroutines/CompletableJob;", "getSocketContext", "()Lkotlinx/coroutines/CompletableJob;", "suspensions", "Lio/ktor/network/selector/InterestSuspensionsMap;", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "accept", "Lio/ktor/network/sockets/Socket;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acceptSuspend", "accepted", "nioChannel", "Ljava/nio/channels/SocketChannel;", "close", "", "dispose", "interestOp", "interest", "Lio/ktor/network/selector/SelectInterest;", "state", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ServerSocketImpl.kt */
public final class ServerSocketImpl implements ServerSocket, Selectable {
    private final /* synthetic */ SelectableBase $$delegate_0;
    private final ServerSocketChannel channel;
    private final SelectorManager selector;
    private final CompletableJob socketContext;

    public int getInterestedOps() {
        return this.$$delegate_0.getInterestedOps();
    }

    public InterestSuspensionsMap getSuspensions() {
        return this.$$delegate_0.getSuspensions();
    }

    public void interestOp(SelectInterest selectInterest, boolean z) {
        Intrinsics.checkNotNullParameter(selectInterest, "interest");
        this.$$delegate_0.interestOp(selectInterest, z);
    }

    public boolean isClosed() {
        return this.$$delegate_0.isClosed();
    }

    public ServerSocketImpl(ServerSocketChannel serverSocketChannel, SelectorManager selectorManager) {
        Intrinsics.checkNotNullParameter(serverSocketChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        this.channel = serverSocketChannel;
        this.selector = selectorManager;
        this.$$delegate_0 = new SelectableBase(serverSocketChannel);
        if (!getChannel().isBlocking()) {
            this.socketContext = JobKt.Job$default((Job) null, 1, (Object) null);
            return;
        }
        throw new IllegalArgumentException("Channel need to be configured as non-blocking.".toString());
    }

    public ServerSocketChannel getChannel() {
        return this.channel;
    }

    public final SelectorManager getSelector() {
        return this.selector;
    }

    public CompletableJob getSocketContext() {
        return this.socketContext;
    }

    public SocketAddress getLocalAddress() {
        SocketAddress socketAddress;
        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            socketAddress = getChannel().getLocalAddress();
        } else {
            socketAddress = getChannel().socket().getLocalSocketAddress();
        }
        Intrinsics.checkNotNullExpressionValue(socketAddress, "localAddress");
        return JavaSocketAddressUtilsKt.toSocketAddress(socketAddress);
    }

    public Object accept(Continuation<? super Socket> continuation) {
        SocketChannel accept = getChannel().accept();
        if (accept != null) {
            return accepted(accept);
        }
        return acceptSuspend(continuation);
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005b  */
    public final java.lang.Object acceptSuspend(kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1 r0 = (io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1 r0 = new io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.ServerSocketImpl r2 = (io.ktor.network.sockets.ServerSocketImpl) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0051
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
        L_0x003a:
            io.ktor.network.selector.SelectInterest r7 = io.ktor.network.selector.SelectInterest.ACCEPT
            r2.interestOp(r7, r3)
            io.ktor.network.selector.SelectorManager r7 = r2.selector
            r4 = r2
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.ACCEPT
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r7.select(r4, r5, r0)
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            java.nio.channels.ServerSocketChannel r7 = r2.getChannel()
            java.nio.channels.SocketChannel r7 = r7.accept()
            if (r7 == 0) goto L_0x003a
            io.ktor.network.sockets.Socket r7 = r2.accepted(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.ServerSocketImpl.acceptSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Socket accepted(SocketChannel socketChannel) {
        interestOp(SelectInterest.ACCEPT, false);
        socketChannel.configureBlocking(false);
        if (getLocalAddress() instanceof InetSocketAddress) {
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                SocketChannel unused = socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
            } else {
                socketChannel.socket().setTcpNoDelay(true);
            }
        }
        return new SocketImpl(socketChannel, this.selector, (SocketOptions.TCPClientSocketOptions) null, 4, (DefaultConstructorMarker) null);
    }

    public void close() {
        try {
            getChannel().close();
            this.selector.notifyClosed(this);
            getSocketContext().complete();
        } catch (Throwable th) {
            getSocketContext().completeExceptionally(th);
        }
    }

    public void dispose() {
        ServerSocket.DefaultImpls.dispose(this);
    }
}
