package io.ktor.network.tls;

import io.ktor.network.sockets.Socket;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001aO\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"tls", "Lio/ktor/network/sockets/Socket;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "config", "Lio/ktor/network/tls/TLSConfig;", "(Lio/ktor/network/sockets/Socket;Lkotlin/coroutines/CoroutineContext;Lio/ktor/network/tls/TLSConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "block", "Lkotlin/Function1;", "Lio/ktor/network/tls/TLSConfigBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/network/sockets/Socket;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "randomAlgorithm", "", "cipherSuites", "", "Lio/ktor/network/tls/CipherSuite;", "serverName", "(Lio/ktor/network/sockets/Socket;Lkotlin/coroutines/CoroutineContext;Ljavax/net/ssl/X509TrustManager;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLS.kt */
public final class TLSKt {
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object tls(io.ktor.network.sockets.Socket r9, kotlin.coroutines.CoroutineContext r10, io.ktor.network.tls.TLSConfig r11, kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.network.tls.TLSKt$tls$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.network.tls.TLSKt$tls$1 r0 = (io.ktor.network.tls.TLSKt$tls$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSKt$tls$1 r0 = new io.ktor.network.tls.TLSKt$tls$1
            r0.<init>(r12)
        L_0x0019:
            r6 = r0
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0043
            if (r1 != r2) goto L_0x003b
            java.lang.Object r9 = r6.L$2
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            java.lang.Object r10 = r6.L$1
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            java.lang.Object r11 = r6.L$0
            io.ktor.network.sockets.Socket r11 = (io.ktor.network.sockets.Socket) r11
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0039 }
            r7 = r9
            r9 = r11
            goto L_0x006d
        L_0x0039:
            r12 = move-exception
            goto L_0x007a
        L_0x003b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r9
            io.ktor.network.sockets.AReadable r12 = (io.ktor.network.sockets.AReadable) r12
            io.ktor.utils.io.ByteReadChannel r12 = io.ktor.network.sockets.SocketsKt.openReadChannel(r12)
            r1 = r9
            io.ktor.network.sockets.AWritable r1 = (io.ktor.network.sockets.AWritable) r1
            r3 = 0
            r4 = 0
            io.ktor.utils.io.ByteWriteChannel r7 = io.ktor.network.sockets.SocketsKt.openWriteChannel$default(r1, r3, r2, r4)
            r6.L$0 = r9     // Catch:{ all -> 0x0074 }
            r6.L$1 = r12     // Catch:{ all -> 0x0074 }
            r6.L$2 = r7     // Catch:{ all -> 0x0074 }
            r6.label = r2     // Catch:{ all -> 0x0074 }
            r1 = r9
            r2 = r12
            r3 = r7
            r4 = r11
            r5 = r10
            java.lang.Object r10 = io.ktor.network.tls.TLSClientSessionJvmKt.openTLSSession(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0074 }
            if (r10 != r0) goto L_0x006a
            return r0
        L_0x006a:
            r8 = r12
            r12 = r10
            r10 = r8
        L_0x006d:
            io.ktor.network.sockets.Socket r12 = (io.ktor.network.sockets.Socket) r12     // Catch:{ all -> 0x0070 }
            return r12
        L_0x0070:
            r12 = move-exception
            r11 = r9
            r9 = r7
            goto L_0x007a
        L_0x0074:
            r10 = move-exception
            r11 = r9
            r9 = r7
            r8 = r12
            r12 = r10
            r10 = r8
        L_0x007a:
            r10.cancel(r12)
            r9.close(r12)
            r11.close()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSKt.tls(io.ktor.network.sockets.Socket, kotlin.coroutines.CoroutineContext, io.ktor.network.tls.TLSConfig, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object tls$default(Socket socket, CoroutineContext coroutineContext, X509TrustManager x509TrustManager, String str, List list, String str2, Continuation continuation, int i, Object obj) {
        return tls(socket, coroutineContext, (i & 2) != 0 ? null : x509TrustManager, (i & 4) != 0 ? "NativePRNGNonBlocking" : str, (i & 8) != 0 ? CIOCipherSuites.INSTANCE.getSupportedSuites() : list, (i & 16) != 0 ? null : str2, continuation);
    }

    public static final Object tls(Socket socket, CoroutineContext coroutineContext, X509TrustManager x509TrustManager, String str, List<CipherSuite> list, String str2, Continuation<? super Socket> continuation) {
        return tls(socket, coroutineContext, (Function1<? super TLSConfigBuilder, Unit>) new TLSKt$tls$3(x509TrustManager, str, list, str2), continuation);
    }

    public static final Object tls(Socket socket, CoroutineContext coroutineContext, Function1<? super TLSConfigBuilder, Unit> function1, Continuation<? super Socket> continuation) {
        TLSConfigBuilder tLSConfigBuilder = new TLSConfigBuilder();
        function1.invoke(tLSConfigBuilder);
        return tls(socket, coroutineContext, tLSConfigBuilder.build(), continuation);
    }
}
