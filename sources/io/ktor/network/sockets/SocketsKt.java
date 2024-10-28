package io.ktor.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\t\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"isClosed", "", "Lio/ktor/network/sockets/ASocket;", "(Lio/ktor/network/sockets/ASocket;)Z", "awaitClosed", "", "(Lio/ktor/network/sockets/ASocket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connection", "Lio/ktor/network/sockets/Connection;", "Lio/ktor/network/sockets/Socket;", "openReadChannel", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/network/sockets/AReadable;", "openWriteChannel", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/network/sockets/AWritable;", "autoFlush", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sockets.kt */
public final class SocketsKt {
    public static final boolean isClosed(ASocket aSocket) {
        Intrinsics.checkNotNullParameter(aSocket, "<this>");
        return aSocket.getSocketContext().isCompleted();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object awaitClosed(io.ktor.network.sockets.ASocket r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.network.sockets.SocketsKt$awaitClosed$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            io.ktor.network.sockets.SocketsKt$awaitClosed$1 r0 = (io.ktor.network.sockets.SocketsKt$awaitClosed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.SocketsKt$awaitClosed$1 r0 = new io.ktor.network.sockets.SocketsKt$awaitClosed$1
            r0.<init>(r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            io.ktor.network.sockets.ASocket r4 = (io.ktor.network.sockets.ASocket) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0048
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.Job r5 = r4.getSocketContext()
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.join(r0)
            if (r5 != r1) goto L_0x0048
            return r1
        L_0x0048:
            kotlinx.coroutines.Job r5 = r4.getSocketContext()
            boolean r5 = r5.isCancelled()
            if (r5 != 0) goto L_0x0055
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x0055:
            kotlinx.coroutines.Job r4 = r4.getSocketContext()
            java.util.concurrent.CancellationException r4 = r4.getCancellationException()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketsKt.awaitClosed(io.ktor.network.sockets.ASocket, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final ByteReadChannel openReadChannel(AReadable aReadable) {
        Intrinsics.checkNotNullParameter(aReadable, "<this>");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(false);
        aReadable.attachForReading(ByteChannel);
        return ByteChannel;
    }

    public static /* synthetic */ ByteWriteChannel openWriteChannel$default(AWritable aWritable, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return openWriteChannel(aWritable, z);
    }

    public static final ByteWriteChannel openWriteChannel(AWritable aWritable, boolean z) {
        Intrinsics.checkNotNullParameter(aWritable, "<this>");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        aWritable.attachForWriting(ByteChannel);
        return ByteChannel;
    }

    public static final Connection connection(Socket socket) {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        return new Connection(socket, openReadChannel(socket), openWriteChannel$default(socket, false, 1, (Object) null));
    }
}
