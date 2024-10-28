package com.typesafe.config.impl;

import com.typesafe.config.ConfigMemorySize;
import j$.util.function.Function$CC;
import java.math.BigInteger;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleConfig$$ExternalSyntheticLambda1 implements Function {
    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return ConfigMemorySize.ofBytes((BigInteger) obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
