package io.ktor.serialization;

import io.ktor.util.reflect.TypeInfo;
import io.ktor.websocket.Frame;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J/\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH¦@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH&J-\u0010\u000e\u001a\u00020\t2\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J/\u0010\u0011\u001a\u00020\t2\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/serialization/WebsocketContentConverter;", "", "deserialize", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "content", "Lio/ktor/websocket/Frame;", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/websocket/Frame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isApplicable", "", "frame", "serialize", "value", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serializeNullable", "ktor-serialization"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebsocketContentConverter.kt */
public interface WebsocketContentConverter {
    Object deserialize(Charset charset, TypeInfo typeInfo, Frame frame, Continuation<Object> continuation);

    boolean isApplicable(Frame frame);

    Object serialize(Charset charset, TypeInfo typeInfo, Object obj, Continuation<? super Frame> continuation);

    Object serializeNullable(Charset charset, TypeInfo typeInfo, Object obj, Continuation<? super Frame> continuation);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebsocketContentConverter.kt */
    public static final class DefaultImpls {
        public static Object serialize(WebsocketContentConverter websocketContentConverter, Charset charset, TypeInfo typeInfo, Object obj, Continuation<? super Frame> continuation) {
            return websocketContentConverter.serializeNullable(charset, typeInfo, obj, continuation);
        }

        public static Object serializeNullable(WebsocketContentConverter websocketContentConverter, Charset charset, TypeInfo typeInfo, Object obj, Continuation<? super Frame> continuation) {
            Intrinsics.checkNotNull(obj);
            return websocketContentConverter.serialize(charset, typeInfo, obj, continuation);
        }
    }
}
