package io.ktor.server.engine;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineName;
import org.slf4j.Logger;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005B\u0017\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lio/ktor/server/engine/DefaultUncaughtExceptionHandler;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "logger", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "(Lorg/slf4j/Logger;)V", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultUncaughtExceptionHandler.kt */
public final class DefaultUncaughtExceptionHandler implements CoroutineExceptionHandler {
    private final Function0<Logger> logger;

    public DefaultUncaughtExceptionHandler(Function0<? extends Logger> function0) {
        Intrinsics.checkNotNullParameter(function0, "logger");
        this.logger = function0;
    }

    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return CoroutineExceptionHandler.DefaultImpls.fold(this, r, function2);
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return CoroutineExceptionHandler.DefaultImpls.get(this, key);
    }

    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return CoroutineExceptionHandler.DefaultImpls.minusKey(this, key);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineExceptionHandler.DefaultImpls.plus(this, coroutineContext);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultUncaughtExceptionHandler(final Logger logger2) {
        this((Function0<? extends Logger>) new Function0<Logger>() {
            public final Logger invoke() {
                return logger2;
            }
        });
        Intrinsics.checkNotNullParameter(logger2, "logger");
    }

    public CoroutineContext.Key<?> getKey() {
        return CoroutineExceptionHandler.Key;
    }

    public void handleException(CoroutineContext coroutineContext, Throwable th) {
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        Intrinsics.checkNotNullParameter(th, "exception");
        if (!(th instanceof CancellationException) && !(th instanceof IOException)) {
            Object obj = (CoroutineName) coroutineContext.get(CoroutineName.Key);
            if (obj == null) {
                obj = coroutineContext.toString();
            }
            this.logger.invoke().error("Unhandled exception caught for " + obj, th);
        }
    }
}
