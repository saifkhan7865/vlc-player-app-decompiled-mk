package io.ktor.network.selector;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: InterestSuspensionsMap.kt */
/* synthetic */ class InterestSuspensionsMap$Companion$updaters$1$property$1 extends MutablePropertyReference1Impl {
    public static final InterestSuspensionsMap$Companion$updaters$1$property$1 INSTANCE = new InterestSuspensionsMap$Companion$updaters$1$property$1();

    InterestSuspensionsMap$Companion$updaters$1$property$1() {
        super(InterestSuspensionsMap.class, "readHandlerReference", "getReadHandlerReference()Lkotlinx/coroutines/CancellableContinuation;", 0);
    }

    public Object get(Object obj) {
        return ((InterestSuspensionsMap) obj).readHandlerReference;
    }

    public void set(Object obj, Object obj2) {
        ((InterestSuspensionsMap) obj).readHandlerReference = (CancellableContinuation) obj2;
    }
}
