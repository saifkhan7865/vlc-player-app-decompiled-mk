package io.ktor.server.netty;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Method;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
final class EventLoopGroupProxy$Companion$prohibitParkingFunction$2 extends Lambda implements Function0<Method> {
    public static final EventLoopGroupProxy$Companion$prohibitParkingFunction$2 INSTANCE = new EventLoopGroupProxy$Companion$prohibitParkingFunction$2();

    EventLoopGroupProxy$Companion$prohibitParkingFunction$2() {
        super(0);
    }

    public final Method invoke() {
        try {
            return Class.forName("io.ktor.utils.io.jvm.javaio.PollersKt").getMethod("prohibitParking", (Class[]) null);
        } catch (Throwable unused) {
            return null;
        }
    }
}
