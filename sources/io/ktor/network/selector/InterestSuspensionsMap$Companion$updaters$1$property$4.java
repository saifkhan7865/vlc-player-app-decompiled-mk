package io.ktor.network.selector;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: InterestSuspensionsMap.kt */
/* synthetic */ class InterestSuspensionsMap$Companion$updaters$1$property$4 extends MutablePropertyReference1Impl {
    public static final InterestSuspensionsMap$Companion$updaters$1$property$4 INSTANCE = new InterestSuspensionsMap$Companion$updaters$1$property$4();

    InterestSuspensionsMap$Companion$updaters$1$property$4() {
        super(InterestSuspensionsMap.class, "connectHandlerReference", "getConnectHandlerReference()Lkotlinx/coroutines/CancellableContinuation;", 0);
    }

    public Object get(Object obj) {
        return ((InterestSuspensionsMap) obj).connectHandlerReference;
    }

    public void set(Object obj, Object obj2) {
        ((InterestSuspensionsMap) obj).connectHandlerReference = (CancellableContinuation) obj2;
    }
}
