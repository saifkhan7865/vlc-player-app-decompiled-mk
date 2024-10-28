package io.ktor.client.network.sockets;

import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.ThrowableKt;
import java.net.SocketTimeoutException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "cause", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TimeoutExceptions.kt */
final class TimeoutExceptionsKt$ByteChannelWithMappedExceptions$1 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ HttpRequestData $request;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TimeoutExceptionsKt$ByteChannelWithMappedExceptions$1(HttpRequestData httpRequestData) {
        super(1);
        this.$request = httpRequestData;
    }

    public final Throwable invoke(Throwable th) {
        return (th != null ? ThrowableKt.getRootCause(th) : null) instanceof SocketTimeoutException ? HttpTimeoutKt.SocketTimeoutException(this.$request, th) : th;
    }
}
