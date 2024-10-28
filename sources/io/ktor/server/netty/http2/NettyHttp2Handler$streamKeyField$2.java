package io.ktor.server.netty.http2;

import io.netty.handler.codec.http2.Http2FrameCodec;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Field;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp2Handler.kt */
final class NettyHttp2Handler$streamKeyField$2 extends Lambda implements Function0<Field> {
    public static final NettyHttp2Handler$streamKeyField$2 INSTANCE = new NettyHttp2Handler$streamKeyField$2();

    NettyHttp2Handler$streamKeyField$2() {
        super(0);
    }

    public final Field invoke() {
        try {
            Field declaredField = Http2FrameCodec.class.getDeclaredField("streamKey");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }
}
