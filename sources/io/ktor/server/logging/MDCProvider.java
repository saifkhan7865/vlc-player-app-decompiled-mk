package io.ktor.server.logging;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J7\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/server/logging/MDCProvider;", "", "withMDCBlock", "", "call", "Lio/ktor/server/application/ApplicationCall;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Logging.kt */
public interface MDCProvider {
    Object withMDCBlock(ApplicationCall applicationCall, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation);
}
