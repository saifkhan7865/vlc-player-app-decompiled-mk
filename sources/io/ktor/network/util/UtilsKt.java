package io.ktor.network.util;

import io.ktor.http.ContentDisposition;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u001aT\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u001c\u0010\n\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a+\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0011*\u0004\u0018\u00010\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\tH\bø\u0001\u0001¢\u0006\u0002\u0010\u0013\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b\u0019\n\u0005\b20\u0001¨\u0006\u0014"}, d2 = {"INFINITE_TIMEOUT_MS", "", "createTimeout", "Lio/ktor/network/util/Timeout;", "Lkotlinx/coroutines/CoroutineScope;", "name", "", "timeoutMs", "clock", "Lkotlin/Function0;", "onTimeout", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/String;JLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Lio/ktor/network/util/Timeout;", "withTimeout", "T", "block", "(Lio/ktor/network/util/Timeout;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class UtilsKt {
    public static final long INFINITE_TIMEOUT_MS = Long.MAX_VALUE;

    public static /* synthetic */ Timeout createTimeout$default(CoroutineScope coroutineScope, String str, long j, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        String str2 = str;
        if ((i & 4) != 0) {
            function0 = UtilsKt$createTimeout$1.INSTANCE;
        }
        return createTimeout(coroutineScope, str2, j, function0, function1);
    }

    public static final Timeout createTimeout(CoroutineScope coroutineScope, String str, long j, Function0<Long> function0, Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, RtspHeaders.Values.CLOCK);
        Intrinsics.checkNotNullParameter(function1, "onTimeout");
        return new Timeout(str, j, function0, coroutineScope, function1);
    }

    public static final <T> T withTimeout(Timeout timeout, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        if (timeout == null) {
            return function0.invoke();
        }
        timeout.start();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            timeout.stop();
            InlineMarker.finallyEnd(1);
        }
    }
}
