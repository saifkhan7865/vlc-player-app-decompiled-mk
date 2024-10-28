package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/cio/MultipartInput;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$withoutTempFile$lazyInput$1 extends Lambda implements Function0<MultipartInput> {
    final /* synthetic */ ByteBuffer $buffer;
    final /* synthetic */ Ref.BooleanRef $closed;
    final /* synthetic */ MultipartEvent.MultipartPart $part;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$withoutTempFile$lazyInput$1(Ref.BooleanRef booleanRef, ByteBuffer byteBuffer, MultipartEvent.MultipartPart multipartPart) {
        super(0);
        this.$closed = booleanRef;
        this.$buffer = byteBuffer;
        this.$part = multipartPart;
    }

    public final MultipartInput invoke() {
        if (!this.$closed.element) {
            return new MultipartInput(this.$buffer, this.$part.getBody());
        }
        throw new IllegalStateException("Already disposed");
    }
}
