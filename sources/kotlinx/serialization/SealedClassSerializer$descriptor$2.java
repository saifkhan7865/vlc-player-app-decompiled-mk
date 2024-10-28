package kotlinx.serialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Typography;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "T", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SealedSerializer.kt */
final class SealedClassSerializer$descriptor$2 extends Lambda implements Function0<SerialDescriptor> {
    final /* synthetic */ String $serialName;
    final /* synthetic */ SealedClassSerializer<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SealedClassSerializer$descriptor$2(String str, SealedClassSerializer<T> sealedClassSerializer) {
        super(0);
        this.$serialName = str;
        this.this$0 = sealedClassSerializer;
    }

    public final SerialDescriptor invoke() {
        final SealedClassSerializer<T> sealedClassSerializer = this.this$0;
        return SerialDescriptorsKt.buildSerialDescriptor(this.$serialName, PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], new Function1<ClassSerialDescriptorBuilder, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ClassSerialDescriptorBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
                Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "$this$buildSerialDescriptor");
                ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder, "type", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), (List) null, false, 12, (Object) null);
                ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder, "value", SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.Sealed<" + sealedClassSerializer.getBaseClass().getSimpleName() + Typography.greater, SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], new SealedClassSerializer$descriptor$2$1$elementDescriptor$1(sealedClassSerializer)), (List) null, false, 12, (Object) null);
                classSerialDescriptorBuilder.setAnnotations(sealedClassSerializer._annotations);
            }
        });
    }
}
