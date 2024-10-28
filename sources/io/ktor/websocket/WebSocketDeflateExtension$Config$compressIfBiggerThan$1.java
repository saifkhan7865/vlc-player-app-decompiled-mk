package io.ktor.websocket;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "frame", "Lio/ktor/websocket/Frame;", "invoke", "(Lio/ktor/websocket/Frame;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketDeflateExtension.kt */
final class WebSocketDeflateExtension$Config$compressIfBiggerThan$1 extends Lambda implements Function1<Frame, Boolean> {
    final /* synthetic */ int $bytes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSocketDeflateExtension$Config$compressIfBiggerThan$1(int i) {
        super(1);
        this.$bytes = i;
    }

    public final Boolean invoke(Frame frame) {
        Intrinsics.checkNotNullParameter(frame, TypedValues.AttributesType.S_FRAME);
        return Boolean.valueOf(frame.getData().length > this.$bytes);
    }
}
