package com.typesafe.config.impl;

import com.typesafe.config.ConfigValue;
import j$.util.function.Function$CC;
import java.math.BigInteger;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleConfig$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ SimpleConfig f$0;
    public final /* synthetic */ ConfigValue f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ SimpleConfig$$ExternalSyntheticLambda0(SimpleConfig simpleConfig, ConfigValue configValue, String str) {
        this.f$0 = simpleConfig;
        this.f$1 = configValue;
        this.f$2 = str;
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return this.f$0.m1442lambda$getBytesList$0$comtypesafeconfigimplSimpleConfig(this.f$1, this.f$2, (BigInteger) obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
