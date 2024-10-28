package io.ktor.websocket;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010,\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*H\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0010@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R$\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f@BX\u000e¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0006R\u001e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0006R\u001e\u0010 \u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0006R\u001c\u0010\"\u001a\u0010\u0012\f\u0012\n %*\u0004\u0018\u00010$0$0#X\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lio/ktor/websocket/FrameParser;", "", "()V", "bodyReady", "", "getBodyReady", "()Z", "<set-?>", "fin", "getFin", "frameType", "Lio/ktor/websocket/FrameType;", "getFrameType", "()Lio/ktor/websocket/FrameType;", "lastOpcode", "", "", "length", "getLength", "()J", "lengthLength", "mask", "getMask", "maskKey", "getMaskKey", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "opcode", "rsv1", "getRsv1", "rsv2", "getRsv2", "rsv3", "getRsv3", "state", "Ljava/util/concurrent/atomic/AtomicReference;", "Lio/ktor/websocket/FrameParser$State;", "kotlin.jvm.PlatformType", "bodyComplete", "", "frame", "bb", "Ljava/nio/ByteBuffer;", "handleStep", "parseHeader1", "parseLength", "parseMaskKey", "State", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FrameParser.kt */
public final class FrameParser {
    private boolean fin;
    private int lastOpcode;
    private long length;
    private int lengthLength;
    private boolean mask;
    private Integer maskKey;
    private int opcode;
    private boolean rsv1;
    private boolean rsv2;
    private boolean rsv3;
    private final AtomicReference<State> state = new AtomicReference<>(State.HEADER0);

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/websocket/FrameParser$State;", "", "(Ljava/lang/String;I)V", "HEADER0", "LENGTH", "MASK_KEY", "BODY", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FrameParser.kt */
    public enum State {
        HEADER0,
        LENGTH,
        MASK_KEY,
        BODY
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FrameParser.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                io.ktor.websocket.FrameParser$State[] r0 = io.ktor.websocket.FrameParser.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.websocket.FrameParser$State r1 = io.ktor.websocket.FrameParser.State.HEADER0     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.websocket.FrameParser$State r1 = io.ktor.websocket.FrameParser.State.LENGTH     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.websocket.FrameParser$State r1 = io.ktor.websocket.FrameParser.State.MASK_KEY     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                io.ktor.websocket.FrameParser$State r1 = io.ktor.websocket.FrameParser.State.BODY     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.FrameParser.WhenMappings.<clinit>():void");
        }
    }

    public final boolean getFin() {
        return this.fin;
    }

    public final boolean getRsv1() {
        return this.rsv1;
    }

    public final boolean getRsv2() {
        return this.rsv2;
    }

    public final boolean getRsv3() {
        return this.rsv3;
    }

    public final boolean getMask() {
        return this.mask;
    }

    public final long getLength() {
        return this.length;
    }

    public final Integer getMaskKey() {
        return this.maskKey;
    }

    public final FrameType getFrameType() {
        FrameType frameType = FrameType.Companion.get(this.opcode);
        if (frameType != null) {
            return frameType;
        }
        throw new IllegalStateException("Unsupported opcode " + Integer.toHexString(this.opcode));
    }

    public final boolean getBodyReady() {
        return this.state.get() == State.BODY;
    }

    public final void bodyComplete() {
        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, State.BODY, State.HEADER0)) {
            this.opcode = 0;
            this.length = 0;
            this.lengthLength = 0;
            this.maskKey = null;
            return;
        }
        throw new IllegalStateException("It should be state BODY but it is " + this.state.get());
    }

    public final void frame(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "bb");
        if (Intrinsics.areEqual((Object) byteBuffer.order(), (Object) ByteOrder.BIG_ENDIAN)) {
            do {
            } while (handleStep(byteBuffer));
            return;
        }
        throw new IllegalArgumentException(("Buffer order should be BIG_ENDIAN but it is " + byteBuffer.order()).toString());
    }

    private final boolean handleStep(ByteBuffer byteBuffer) {
        State state2 = this.state.get();
        Intrinsics.checkNotNull(state2);
        int i = WhenMappings.$EnumSwitchMapping$0[state2.ordinal()];
        if (i == 1) {
            return parseHeader1(byteBuffer);
        }
        if (i == 2) {
            return parseLength(byteBuffer);
        }
        if (i == 3) {
            return parseMaskKey(byteBuffer);
        }
        if (i == 4) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final boolean parseHeader1(ByteBuffer byteBuffer) {
        int i = 0;
        if (byteBuffer.remaining() < 2) {
            return false;
        }
        byte b = byteBuffer.get();
        byte b2 = byteBuffer.get();
        this.fin = (b & 128) != 0;
        this.rsv1 = (b & SignedBytes.MAX_POWER_OF_TWO) != 0;
        this.rsv2 = (b & 32) != 0;
        this.rsv3 = (b & Ascii.DLE) != 0;
        byte b3 = b & Ascii.SI;
        this.opcode = b3;
        if (b3 == 0 && this.lastOpcode == 0) {
            throw new ProtocolViolationException("Can't continue finished frames");
        }
        if (b3 == 0) {
            this.opcode = this.lastOpcode;
        } else if (this.lastOpcode != 0 && !getFrameType().getControlFrame()) {
            throw new ProtocolViolationException("Can't start new data frame before finishing previous one");
        }
        if (!getFrameType().getControlFrame()) {
            this.lastOpcode = this.fin ? 0 : this.opcode;
        } else if (!this.fin) {
            throw new ProtocolViolationException("control frames can't be fragmented");
        }
        this.mask = (b2 & 128) != 0;
        byte b4 = b2 & Byte.MAX_VALUE;
        if (!getFrameType().getControlFrame() || b4 <= 125) {
            if (b4 == 126) {
                i = 2;
            } else if (b4 == Byte.MAX_VALUE) {
                i = 8;
            }
            this.lengthLength = i;
            this.length = i == 0 ? (long) b4 : 0;
            if (i > 0) {
                this.state.set(State.LENGTH);
            } else if (this.mask) {
                this.state.set(State.MASK_KEY);
            } else {
                this.state.set(State.BODY);
            }
            return true;
        }
        throw new ProtocolViolationException("control frames can't be larger than 125 bytes");
    }

    private final boolean parseLength(ByteBuffer byteBuffer) {
        long j;
        int remaining = byteBuffer.remaining();
        int i = this.lengthLength;
        if (remaining < i) {
            return false;
        }
        if (i == 2) {
            j = ((long) byteBuffer.getShort()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        } else if (i == 8) {
            j = byteBuffer.getLong();
        } else {
            throw new IllegalStateException();
        }
        this.length = j;
        this.state.set(this.mask ? State.MASK_KEY : State.BODY);
        return true;
    }

    private final boolean parseMaskKey(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 4) {
            return false;
        }
        this.maskKey = Integer.valueOf(byteBuffer.getInt());
        this.state.set(State.BODY);
        return true;
    }
}
