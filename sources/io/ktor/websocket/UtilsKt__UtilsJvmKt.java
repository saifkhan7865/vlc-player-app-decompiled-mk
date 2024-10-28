package io.ktor.websocket;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\"\u0014\u0010\u0000\u001a\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"OUTGOING_CHANNEL_CAPACITY", "", "getOUTGOING_CHANNEL_CAPACITY", "()I", "xor", "", "Ljava/nio/ByteBuffer;", "other", "ktor-websockets"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/websocket/UtilsKt")
/* compiled from: UtilsJvm.kt */
final /* synthetic */ class UtilsKt__UtilsJvmKt {
    public static final void xor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(byteBuffer2, "other");
        ByteBuffer slice = byteBuffer.slice();
        ByteBuffer slice2 = byteBuffer2.slice();
        int remaining = slice2.remaining();
        int remaining2 = slice.remaining();
        for (int i = 0; i < remaining2; i++) {
            slice.put(i, (byte) (slice.get(i) ^ slice2.get(i % remaining)));
        }
    }

    public static final int getOUTGOING_CHANNEL_CAPACITY() {
        String property = System.getProperty("io.ktor.websocket.outgoingChannelCapacity");
        if (property != null) {
            return Integer.parseInt(property);
        }
        return 8;
    }
}
