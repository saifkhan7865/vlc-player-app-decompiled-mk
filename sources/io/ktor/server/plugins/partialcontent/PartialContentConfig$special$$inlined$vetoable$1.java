package io.ktor.server.plugins.partialcontent;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J)\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\b¨\u0006\t¸\u0006\u0000"}, d2 = {"kotlin/properties/Delegates$vetoable$1", "Lkotlin/properties/ObservableProperty;", "beforeChange", "", "property", "Lkotlin/reflect/KProperty;", "oldValue", "newValue", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)Z", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Delegates.kt */
public final class PartialContentConfig$special$$inlined$vetoable$1 extends ObservableProperty<Integer> {
    public PartialContentConfig$special$$inlined$vetoable$1(Object obj) {
        super(obj);
    }

    /* access modifiers changed from: protected */
    public boolean beforeChange(KProperty<?> kProperty, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        int intValue = num2.intValue();
        num.intValue();
        if (intValue > 0) {
            return true;
        }
        throw new IllegalArgumentException("Bad maxRangeCount value " + intValue);
    }
}
