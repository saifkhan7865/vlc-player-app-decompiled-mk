package io.ktor.network.sockets;

import io.ktor.utils.io.core.ByteBuffersKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0005\u001a\u00020\u00032%\u0010\u0006\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001H\u0002\u001a\u0014\u0010\n\u001a\u00020\u0003*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\"\u001c\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"CLOSED", "Lkotlin/Function1;", "", "", "CLOSED_INVOKED", "failInvokeOnClose", "handler", "Lkotlin/ParameterName;", "name", "cause", "writeMessageTo", "Lio/ktor/network/sockets/Datagram;", "buffer", "Ljava/nio/ByteBuffer;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DatagramSendChannel.kt */
public final class DatagramSendChannelKt {
    /* access modifiers changed from: private */
    public static final Function1<Throwable, Unit> CLOSED = DatagramSendChannelKt$CLOSED$1.INSTANCE;
    /* access modifiers changed from: private */
    public static final Function1<Throwable, Unit> CLOSED_INVOKED = DatagramSendChannelKt$CLOSED_INVOKED$1.INSTANCE;

    /* access modifiers changed from: private */
    public static final void failInvokeOnClose(Function1<? super Throwable, Unit> function1) {
        String str;
        if (function1 == CLOSED_INVOKED) {
            str = "Another handler was already registered and successfully invoked";
        } else {
            str = "Another handler was already registered: " + function1;
        }
        throw new IllegalStateException(str);
    }

    /* access modifiers changed from: private */
    public static final void writeMessageTo(Datagram datagram, ByteBuffer byteBuffer) {
        ByteBuffersKt.readAvailable(datagram.getPacket(), byteBuffer);
        byteBuffer.flip();
    }
}
