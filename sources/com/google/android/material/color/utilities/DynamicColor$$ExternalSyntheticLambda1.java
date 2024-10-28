package com.google.android.material.color.utilities;

import j$.util.function.Function$CC;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DynamicColor$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ Hct f$0;

    public /* synthetic */ DynamicColor$$ExternalSyntheticLambda1(Hct hct) {
        this.f$0 = hct;
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return Double.valueOf(this.f$0.getTone());
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
