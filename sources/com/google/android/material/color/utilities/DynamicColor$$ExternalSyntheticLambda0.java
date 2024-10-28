package com.google.android.material.color.utilities;

import j$.util.function.Function$CC;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DynamicColor$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ TonalPalette f$0;

    public /* synthetic */ DynamicColor$$ExternalSyntheticLambda0(TonalPalette tonalPalette) {
        this.f$0 = tonalPalette;
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return DynamicColor.lambda$fromArgb$0(this.f$0, (DynamicScheme) obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
