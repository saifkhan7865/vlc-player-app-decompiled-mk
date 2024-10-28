package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"kotlinx/serialization/json/internal/AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1", "Lkotlinx/serialization/encoding/AbstractEncoder;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "encodeString", "", "value", "", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TreeJsonEncoder.kt */
public final class AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1 extends AbstractEncoder {
    final /* synthetic */ SerialDescriptor $inlineDescriptor;
    final /* synthetic */ String $tag;
    final /* synthetic */ AbstractJsonTreeEncoder this$0;

    AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1(AbstractJsonTreeEncoder abstractJsonTreeEncoder, String str, SerialDescriptor serialDescriptor) {
        this.this$0 = abstractJsonTreeEncoder;
        this.$tag = str;
        this.$inlineDescriptor = serialDescriptor;
    }

    public SerializersModule getSerializersModule() {
        return this.this$0.getJson().getSerializersModule();
    }

    public void encodeString(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.this$0.putElement(this.$tag, new JsonLiteral(str, false, this.$inlineDescriptor));
    }
}
