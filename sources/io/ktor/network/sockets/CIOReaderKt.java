package io.ktor.network.sockets;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a8\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000\u001aF\u0010\u0010\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000\u001a\u001d\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\f\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"selectForRead", "", "selectable", "Lio/ktor/network/selector/Selectable;", "selector", "Lio/ktor/network/selector/SelectorManager;", "(Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectorManager;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "attachForReadingDirectImpl", "Lio/ktor/utils/io/WriterJob;", "Lkotlinx/coroutines/CoroutineScope;", "channel", "Lio/ktor/utils/io/ByteChannel;", "nioChannel", "Ljava/nio/channels/ReadableByteChannel;", "socketOptions", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "attachForReadingImpl", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "readFrom", "", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/channels/ReadableByteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOReader.kt */
public final class CIOReaderKt {
    public static /* synthetic */ WriterJob attachForReadingImpl$default(CoroutineScope coroutineScope, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i, Object obj) {
        if ((i & 32) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForReadingImpl(coroutineScope, byteChannel, readableByteChannel, selectable, selectorManager, objectPool, tCPClientSocketOptions);
    }

    public static final WriterJob attachForReadingImpl(CoroutineScope coroutineScope, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool<ByteBuffer> objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        CoroutineScope coroutineScope2 = coroutineScope;
        ByteChannel byteChannel2 = byteChannel;
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(readableByteChannel, "nioChannel");
        Selectable selectable2 = selectable;
        Intrinsics.checkNotNullParameter(selectable2, "selectable");
        SelectorManager selectorManager2 = selectorManager;
        Intrinsics.checkNotNullParameter(selectorManager2, "selector");
        ObjectPool<ByteBuffer> objectPool2 = objectPool;
        Intrinsics.checkNotNullParameter(objectPool2, "pool");
        ByteBuffer borrow = objectPool.borrow();
        return CoroutinesKt.writer(coroutineScope, Dispatchers.getUnconfined().plus(new CoroutineName("cio-from-nio-reader")), byteChannel, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new CIOReaderKt$attachForReadingImpl$1(tCPClientSocketOptions, byteChannel, selectable2, borrow, objectPool2, readableByteChannel, selectorManager2, (Continuation<? super CIOReaderKt$attachForReadingImpl$1>) null));
    }

    public static /* synthetic */ WriterJob attachForReadingDirectImpl$default(CoroutineScope coroutineScope, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i, Object obj) {
        if ((i & 16) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForReadingDirectImpl(coroutineScope, byteChannel, readableByteChannel, selectable, selectorManager, tCPClientSocketOptions);
    }

    public static final WriterJob attachForReadingDirectImpl(CoroutineScope coroutineScope, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(readableByteChannel, "nioChannel");
        Intrinsics.checkNotNullParameter(selectable, "selectable");
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        return CoroutinesKt.writer(coroutineScope, Dispatchers.getUnconfined().plus(new CoroutineName("cio-from-nio-reader")), byteChannel, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new CIOReaderKt$attachForReadingDirectImpl$1(selectable, tCPClientSocketOptions, byteChannel, readableByteChannel, selectorManager, (Continuation<? super CIOReaderKt$attachForReadingDirectImpl$1>) null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readFrom(io.ktor.utils.io.ByteWriteChannel r7, java.nio.channels.ReadableByteChannel r8, kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.network.sockets.CIOReaderKt$readFrom$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.network.sockets.CIOReaderKt$readFrom$1 r0 = (io.ktor.network.sockets.CIOReaderKt$readFrom$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.CIOReaderKt$readFrom$1 r0 = new io.ktor.network.sockets.CIOReaderKt$readFrom$1
            r0.<init>(r9)
        L_0x0019:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x0037
            if (r1 != r2) goto L_0x002f
            java.lang.Object r7 = r4.L$0
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0057
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$IntRef r9 = new kotlin.jvm.internal.Ref$IntRef
            r9.<init>()
            io.ktor.network.sockets.CIOReaderKt$readFrom$2 r1 = new io.ktor.network.sockets.CIOReaderKt$readFrom$2
            r1.<init>(r9, r8)
            r3 = r1
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            r4.L$0 = r9
            r4.label = r2
            r2 = 0
            r5 = 1
            r6 = 0
            r1 = r7
            java.lang.Object r7 = io.ktor.utils.io.ByteWriteChannel.DefaultImpls.write$default(r1, r2, r3, r4, r5, r6)
            if (r7 != r0) goto L_0x0056
            return r0
        L_0x0056:
            r7 = r9
        L_0x0057:
            int r7 = r7.element
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt.readFrom(io.ktor.utils.io.ByteWriteChannel, java.nio.channels.ReadableByteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object selectForRead(Selectable selectable, SelectorManager selectorManager, Continuation<? super Unit> continuation) {
        selectable.interestOp(SelectInterest.READ, true);
        Object select = selectorManager.select(selectable, SelectInterest.READ, continuation);
        return select == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? select : Unit.INSTANCE;
    }
}
