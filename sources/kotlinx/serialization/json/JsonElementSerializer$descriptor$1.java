package kotlinx.serialization.json;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/descriptors/ClassSerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonElementSerializers.kt */
final class JsonElementSerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {
    public static final JsonElementSerializer$descriptor$1 INSTANCE = new JsonElementSerializer$descriptor$1();

    JsonElementSerializer$descriptor$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ClassSerialDescriptorBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "$this$buildSerialDescriptor");
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder2 = classSerialDescriptorBuilder;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonPrimitive", JsonElementSerializersKt.defer(AnonymousClass1.INSTANCE), (List) null, false, 12, (Object) null);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonNull", JsonElementSerializersKt.defer(AnonymousClass2.INSTANCE), (List) null, false, 12, (Object) null);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonLiteral", JsonElementSerializersKt.defer(AnonymousClass3.INSTANCE), (List) null, false, 12, (Object) null);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonObject", JsonElementSerializersKt.defer(AnonymousClass4.INSTANCE), (List) null, false, 12, (Object) null);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonArray", JsonElementSerializersKt.defer(AnonymousClass5.INSTANCE), (List) null, false, 12, (Object) null);
    }
}
