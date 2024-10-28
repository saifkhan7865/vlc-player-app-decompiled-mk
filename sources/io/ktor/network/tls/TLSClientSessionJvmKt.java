package io.ktor.network.tls;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"openTLSSession", "Lio/ktor/network/sockets/Socket;", "socket", "input", "Lio/ktor/utils/io/ByteReadChannel;", "output", "Lio/ktor/utils/io/ByteWriteChannel;", "config", "Lio/ktor/network/tls/TLSConfig;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lio/ktor/network/sockets/Socket;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/network/tls/TLSConfig;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientSessionJvm.kt */
public final class TLSClientSessionJvmKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: kotlin.coroutines.CoroutineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object openTLSSession(io.ktor.network.sockets.Socket r4, io.ktor.utils.io.ByteReadChannel r5, io.ktor.utils.io.ByteWriteChannel r6, io.ktor.network.tls.TLSConfig r7, kotlin.coroutines.CoroutineContext r8, kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.network.tls.TLSClientSessionJvmKt$openTLSSession$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.network.tls.TLSClientSessionJvmKt$openTLSSession$1 r0 = (io.ktor.network.tls.TLSClientSessionJvmKt$openTLSSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSClientSessionJvmKt$openTLSSession$1 r0 = new io.ktor.network.tls.TLSClientSessionJvmKt$openTLSSession$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r4 = r0.L$2
            io.ktor.network.tls.TLSClientHandshake r4 = (io.ktor.network.tls.TLSClientHandshake) r4
            java.lang.Object r5 = r0.L$1
            r8 = r5
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            java.lang.Object r5 = r0.L$0
            io.ktor.network.sockets.Socket r5 = (io.ktor.network.sockets.Socket) r5
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ ClosedSendChannelException -> 0x0066 }
            goto L_0x0058
        L_0x0037:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.network.tls.TLSClientHandshake r9 = new io.ktor.network.tls.TLSClientHandshake
            r9.<init>(r5, r6, r7, r8)
            r0.L$0 = r4     // Catch:{ ClosedSendChannelException -> 0x0066 }
            r0.L$1 = r8     // Catch:{ ClosedSendChannelException -> 0x0066 }
            r0.L$2 = r9     // Catch:{ ClosedSendChannelException -> 0x0066 }
            r0.label = r3     // Catch:{ ClosedSendChannelException -> 0x0066 }
            java.lang.Object r5 = r9.negotiate(r0)     // Catch:{ ClosedSendChannelException -> 0x0066 }
            if (r5 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r5 = r4
            r4 = r9
        L_0x0058:
            io.ktor.network.tls.TLSSocket r6 = new io.ktor.network.tls.TLSSocket
            kotlinx.coroutines.channels.ReceiveChannel r7 = r4.getInput()
            kotlinx.coroutines.channels.SendChannel r4 = r4.getOutput()
            r6.<init>(r7, r4, r5, r8)
            return r6
        L_0x0066:
            r4 = move-exception
            io.ktor.network.tls.TLSException r5 = new io.ktor.network.tls.TLSException
            java.lang.String r6 = "Negotiation failed due to EOS"
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r5.<init>(r6, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientSessionJvmKt.openTLSSession(io.ktor.network.sockets.Socket, io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, io.ktor.network.tls.TLSConfig, kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
