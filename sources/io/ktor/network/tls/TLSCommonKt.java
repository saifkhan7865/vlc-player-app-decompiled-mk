package io.ktor.network.tls;

import io.ktor.network.sockets.Connection;
import io.ktor.network.sockets.Socket;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"tls", "Lio/ktor/network/sockets/Socket;", "Lio/ktor/network/sockets/Connection;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lio/ktor/network/sockets/Connection;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "config", "Lio/ktor/network/tls/TLSConfig;", "(Lio/ktor/network/sockets/Connection;Lkotlin/coroutines/CoroutineContext;Lio/ktor/network/tls/TLSConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "block", "Lkotlin/Function1;", "Lio/ktor/network/tls/TLSConfigBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/network/sockets/Connection;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/network/sockets/Socket;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSCommon.kt */
public final class TLSCommonKt {
    public static final Object tls(Socket socket, CoroutineContext coroutineContext, Continuation<? super Socket> continuation) {
        return TLSKt.tls(socket, coroutineContext, (Function1<? super TLSConfigBuilder, Unit>) TLSCommonKt$tls$2.INSTANCE, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object tls(io.ktor.network.sockets.Connection r7, kotlin.coroutines.CoroutineContext r8, io.ktor.network.tls.TLSConfig r9, kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.network.tls.TLSCommonKt$tls$3
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.network.tls.TLSCommonKt$tls$3 r0 = (io.ktor.network.tls.TLSCommonKt$tls$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSCommonKt$tls$3 r0 = new io.ktor.network.tls.TLSCommonKt$tls$3
            r0.<init>(r10)
        L_0x0019:
            r6 = r0
            java.lang.Object r10 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0037
            if (r1 != r2) goto L_0x002f
            java.lang.Object r7 = r6.L$0
            io.ktor.network.sockets.Connection r7 = (io.ktor.network.sockets.Connection) r7
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0057 }
            goto L_0x0054
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.network.sockets.Socket r1 = r7.getSocket()     // Catch:{ all -> 0x0057 }
            io.ktor.utils.io.ByteReadChannel r10 = r7.getInput()     // Catch:{ all -> 0x0057 }
            io.ktor.utils.io.ByteWriteChannel r3 = r7.getOutput()     // Catch:{ all -> 0x0057 }
            r6.L$0 = r7     // Catch:{ all -> 0x0057 }
            r6.label = r2     // Catch:{ all -> 0x0057 }
            r2 = r10
            r4 = r9
            r5 = r8
            java.lang.Object r10 = io.ktor.network.tls.TLSClientSessionJvmKt.openTLSSession(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0057 }
            if (r10 != r0) goto L_0x0054
            return r0
        L_0x0054:
            io.ktor.network.sockets.Socket r10 = (io.ktor.network.sockets.Socket) r10     // Catch:{ all -> 0x0057 }
            return r10
        L_0x0057:
            r8 = move-exception
            io.ktor.utils.io.ByteReadChannel r9 = r7.getInput()
            r9.cancel(r8)
            io.ktor.utils.io.ByteWriteChannel r9 = r7.getOutput()
            r9.close(r8)
            io.ktor.network.sockets.Socket r7 = r7.getSocket()
            r7.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSCommonKt.tls(io.ktor.network.sockets.Connection, kotlin.coroutines.CoroutineContext, io.ktor.network.tls.TLSConfig, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object tls(Connection connection, CoroutineContext coroutineContext, Continuation<? super Socket> continuation) {
        return tls(connection, coroutineContext, (Function1<? super TLSConfigBuilder, Unit>) TLSCommonKt$tls$5.INSTANCE, continuation);
    }

    public static final Object tls(Connection connection, CoroutineContext coroutineContext, Function1<? super TLSConfigBuilder, Unit> function1, Continuation<? super Socket> continuation) {
        TLSConfigBuilder tLSConfigBuilder = new TLSConfigBuilder();
        function1.invoke(tLSConfigBuilder);
        return tls(connection, coroutineContext, tLSConfigBuilder.build(), continuation);
    }
}
