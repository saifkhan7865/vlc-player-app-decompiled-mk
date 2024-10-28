package io.ktor.server.plugins.partialcontent;

import io.ktor.util.KtorDsl;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lio/ktor/server/plugins/partialcontent/PartialContentConfig;", "", "()V", "<set-?>", "", "maxRangeCount", "getMaxRangeCount", "()I", "setMaxRangeCount", "(I)V", "maxRangeCount$delegate", "Lkotlin/properties/ReadWriteProperty;", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: PartialContent.kt */
public final class PartialContentConfig {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PartialContentConfig.class, "maxRangeCount", "getMaxRangeCount()I", 0))};
    private final ReadWriteProperty maxRangeCount$delegate = new PartialContentConfig$special$$inlined$vetoable$1(10);

    public PartialContentConfig() {
        Delegates delegates = Delegates.INSTANCE;
    }

    public final int getMaxRangeCount() {
        return ((Number) this.maxRangeCount$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    public final void setMaxRangeCount(int i) {
        this.maxRangeCount$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }
}
