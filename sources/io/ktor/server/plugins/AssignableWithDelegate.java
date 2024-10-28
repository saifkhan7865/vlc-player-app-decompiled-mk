package io.ktor.server.plugins;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\"\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\fH\u0002¢\u0006\u0002\u0010\rJ*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0011R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lio/ktor/server/plugins/AssignableWithDelegate;", "T", "", "property", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "assigned", "Ljava/lang/Object;", "getProperty", "()Lkotlin/jvm/functions/Function0;", "getValue", "thisRef", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OriginConnectionPoint.kt */
final class AssignableWithDelegate<T> {
    private T assigned;
    private final Function0<T> property;

    public AssignableWithDelegate(Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "property");
        this.property = function0;
    }

    public final Function0<T> getProperty() {
        return this.property;
    }

    public final T getValue(Object obj, KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(obj, "thisRef");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        T t = this.assigned;
        return t == null ? this.property.invoke() : t;
    }

    public final void setValue(Object obj, KProperty<?> kProperty, T t) {
        Intrinsics.checkNotNullParameter(obj, "thisRef");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        Intrinsics.checkNotNullParameter(t, "value");
        this.assigned = t;
    }
}
