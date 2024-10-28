package io.ktor.network.sockets;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000\u001aF\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¨\u0006\u0011"}, d2 = {"attachForWritingDirectImpl", "Lio/ktor/utils/io/ReaderJob;", "Lkotlinx/coroutines/CoroutineScope;", "channel", "Lio/ktor/utils/io/ByteChannel;", "nioChannel", "Ljava/nio/channels/WritableByteChannel;", "selectable", "Lio/ktor/network/selector/Selectable;", "selector", "Lio/ktor/network/selector/SelectorManager;", "socketOptions", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "attachForWritingImpl", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOWriter.kt */
public final class CIOWriterKt {
    public static /* synthetic */ ReaderJob attachForWritingImpl$default(CoroutineScope coroutineScope, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i, Object obj) {
        if ((i & 32) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForWritingImpl(coroutineScope, byteChannel, writableByteChannel, selectable, selectorManager, objectPool, tCPClientSocketOptions);
    }

    public static final ReaderJob attachForWritingImpl(CoroutineScope coroutineScope, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool<ByteBuffer> objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        CoroutineScope coroutineScope2 = coroutineScope;
        ByteChannel byteChannel2 = byteChannel;
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(writableByteChannel, "nioChannel");
        Selectable selectable2 = selectable;
        Intrinsics.checkNotNullParameter(selectable2, "selectable");
        SelectorManager selectorManager2 = selectorManager;
        Intrinsics.checkNotNullParameter(selectorManager2, "selector");
        ObjectPool<ByteBuffer> objectPool2 = objectPool;
        Intrinsics.checkNotNullParameter(objectPool2, "pool");
        ByteBuffer borrow = objectPool.borrow();
        return CoroutinesKt.reader(coroutineScope, Dispatchers.getUnconfined().plus(new CoroutineName("cio-to-nio-writer")), byteChannel, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) new CIOWriterKt$attachForWritingImpl$1(tCPClientSocketOptions, borrow, byteChannel, selectable2, objectPool2, writableByteChannel, selectorManager2, (Continuation<? super CIOWriterKt$attachForWritingImpl$1>) null));
    }

    public static /* synthetic */ ReaderJob attachForWritingDirectImpl$default(CoroutineScope coroutineScope, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i, Object obj) {
        if ((i & 16) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForWritingDirectImpl(coroutineScope, byteChannel, writableByteChannel, selectable, selectorManager, tCPClientSocketOptions);
    }

    public static final ReaderJob attachForWritingDirectImpl(CoroutineScope coroutineScope, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(writableByteChannel, "nioChannel");
        Intrinsics.checkNotNullParameter(selectable, "selectable");
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        return CoroutinesKt.reader(coroutineScope, Dispatchers.getUnconfined().plus(new CoroutineName("cio-to-nio-writer")), byteChannel, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) new CIOWriterKt$attachForWritingDirectImpl$1(selectable, byteChannel, writableByteChannel, tCPClientSocketOptions, selectorManager, (Continuation<? super CIOWriterKt$attachForWritingDirectImpl$1>) null));
    }
}
