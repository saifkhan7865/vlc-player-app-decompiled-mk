package io.ktor.network.sockets;

import java.io.Closeable;
import kotlin.Metadata;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lio/ktor/network/sockets/ASocket;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "Lkotlinx/coroutines/DisposableHandle;", "socketContext", "Lkotlinx/coroutines/Job;", "getSocketContext", "()Lkotlinx/coroutines/Job;", "dispose", "", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sockets.kt */
public interface ASocket extends Closeable, DisposableHandle {
    void dispose();

    Job getSocketContext();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Sockets.kt */
    public static final class DefaultImpls {
        public static void dispose(ASocket aSocket) {
            try {
                aSocket.close();
            } catch (Throwable unused) {
            }
        }
    }
}
