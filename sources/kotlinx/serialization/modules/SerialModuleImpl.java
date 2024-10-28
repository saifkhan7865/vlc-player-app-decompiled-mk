package kotlinx.serialization.modules;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.modules.ContextualProvider;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001Bä\u0001\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012*\u0010\u0006\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u0003\u0012.\u0010\b\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u001c\u0012\u001a\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\n0\tj\u0006\u0012\u0002\b\u0003`\u000b0\u0003\u0012&\u0010\f\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u0003\u0012A\u0010\u000e\u001a=\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012/\u0012-\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00120\tj\u0006\u0012\u0002\b\u0003`\u00130\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J:\u0010\u0019\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u0007\"\b\b\u0000\u0010\u001a*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u00042\u0010\u0010\u001d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u001eH\u0016J7\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\n\"\b\b\u0000\u0010\u001a*\u00020\u001b2\u000e\u0010 \u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u001a0\u00042\u0006\u0010!\u001a\u0002H\u001aH\u0016¢\u0006\u0002\u0010\"J4\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u0012\"\b\b\u0000\u0010\u001a*\u00020\u001b2\u000e\u0010 \u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u001a0\u00042\b\u0010#\u001a\u0004\u0018\u00010\rH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\u0002\n\u0000RI\u0010\u000e\u001a=\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012/\u0012-\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00120\tj\u0006\u0012\u0002\b\u0003`\u00130\u0003X\u0004¢\u0006\u0002\n\u0000R6\u0010\b\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u001c\u0012\u001a\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\n0\tj\u0006\u0012\u0002\b\u0003`\u000b0\u0003X\u0004¢\u0006\u0002\n\u0000R.\u0010\f\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u0003X\u0004¢\u0006\u0002\n\u0000R4\u0010\u0006\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlinx/serialization/modules/SerialModuleImpl;", "Lkotlinx/serialization/modules/SerializersModule;", "class2ContextualFactory", "", "Lkotlin/reflect/KClass;", "Lkotlinx/serialization/modules/ContextualProvider;", "polyBase2Serializers", "Lkotlinx/serialization/KSerializer;", "polyBase2DefaultSerializerProvider", "Lkotlin/Function1;", "Lkotlinx/serialization/SerializationStrategy;", "Lkotlinx/serialization/modules/PolymorphicSerializerProvider;", "polyBase2NamedSerializers", "", "polyBase2DefaultDeserializerProvider", "Lkotlin/ParameterName;", "name", "className", "Lkotlinx/serialization/DeserializationStrategy;", "Lkotlinx/serialization/modules/PolymorphicDeserializerProvider;", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "dumpTo", "", "collector", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "getContextual", "T", "", "kClass", "typeArgumentsSerializers", "", "getPolymorphic", "baseClass", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lkotlinx/serialization/SerializationStrategy;", "serializedClassName", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SerializersModule.kt */
public final class SerialModuleImpl extends SerializersModule {
    private final Map<KClass<?>, ContextualProvider> class2ContextualFactory;
    private final Map<KClass<?>, Function1<String, DeserializationStrategy<?>>> polyBase2DefaultDeserializerProvider;
    private final Map<KClass<?>, Function1<?, SerializationStrategy<?>>> polyBase2DefaultSerializerProvider;
    private final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers;
    public final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SerialModuleImpl(Map<KClass<?>, ? extends ContextualProvider> map, Map<KClass<?>, ? extends Map<KClass<?>, ? extends KSerializer<?>>> map2, Map<KClass<?>, ? extends Function1<?, ? extends SerializationStrategy<?>>> map3, Map<KClass<?>, ? extends Map<String, ? extends KSerializer<?>>> map4, Map<KClass<?>, ? extends Function1<? super String, ? extends DeserializationStrategy<?>>> map5) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(map, "class2ContextualFactory");
        Intrinsics.checkNotNullParameter(map2, "polyBase2Serializers");
        Intrinsics.checkNotNullParameter(map3, "polyBase2DefaultSerializerProvider");
        Intrinsics.checkNotNullParameter(map4, "polyBase2NamedSerializers");
        Intrinsics.checkNotNullParameter(map5, "polyBase2DefaultDeserializerProvider");
        this.class2ContextualFactory = map;
        this.polyBase2Serializers = map2;
        this.polyBase2DefaultSerializerProvider = map3;
        this.polyBase2NamedSerializers = map4;
        this.polyBase2DefaultDeserializerProvider = map5;
    }

    public <T> SerializationStrategy<T> getPolymorphic(KClass<? super T> kClass, T t) {
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Intrinsics.checkNotNullParameter(t, "value");
        if (!kClass.isInstance(t)) {
            return null;
        }
        Map map = this.polyBase2Serializers.get(kClass);
        KSerializer kSerializer = map != null ? (KSerializer) map.get(Reflection.getOrCreateKotlinClass(t.getClass())) : null;
        SerializationStrategy<T> serializationStrategy = kSerializer instanceof SerializationStrategy ? kSerializer : null;
        if (serializationStrategy != null) {
            return serializationStrategy;
        }
        Function1<?, SerializationStrategy<?>> function1 = this.polyBase2DefaultSerializerProvider.get(kClass);
        Function1 function12 = TypeIntrinsics.isFunctionOfArity(function1, 1) ? function1 : null;
        if (function12 != null) {
            return (SerializationStrategy) function12.invoke(t);
        }
        return null;
    }

    public <T> DeserializationStrategy<T> getPolymorphic(KClass<? super T> kClass, String str) {
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Map map = this.polyBase2NamedSerializers.get(kClass);
        KSerializer kSerializer = map != null ? (KSerializer) map.get(str) : null;
        if (!(kSerializer instanceof KSerializer)) {
            kSerializer = null;
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        Function1<String, DeserializationStrategy<?>> function1 = this.polyBase2DefaultDeserializerProvider.get(kClass);
        Function1 function12 = TypeIntrinsics.isFunctionOfArity(function1, 1) ? function1 : null;
        if (function12 != null) {
            return (DeserializationStrategy) function12.invoke(str);
        }
        return null;
    }

    public <T> KSerializer<T> getContextual(KClass<T> kClass, List<? extends KSerializer<?>> list) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(list, "typeArgumentsSerializers");
        ContextualProvider contextualProvider = this.class2ContextualFactory.get(kClass);
        KSerializer<?> invoke = contextualProvider != null ? contextualProvider.invoke(list) : null;
        if (invoke instanceof KSerializer) {
            return invoke;
        }
        return null;
    }

    public void dumpTo(SerializersModuleCollector serializersModuleCollector) {
        Intrinsics.checkNotNullParameter(serializersModuleCollector, "collector");
        for (Map.Entry next : this.class2ContextualFactory.entrySet()) {
            KClass kClass = (KClass) next.getKey();
            ContextualProvider contextualProvider = (ContextualProvider) next.getValue();
            if (contextualProvider instanceof ContextualProvider.Argless) {
                Intrinsics.checkNotNull(kClass, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                KSerializer<?> serializer = ((ContextualProvider.Argless) contextualProvider).getSerializer();
                Intrinsics.checkNotNull(serializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                serializersModuleCollector.contextual(kClass, serializer);
            } else if (contextualProvider instanceof ContextualProvider.WithTypeArguments) {
                serializersModuleCollector.contextual(kClass, (Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>>) ((ContextualProvider.WithTypeArguments) contextualProvider).getProvider());
            }
        }
        for (Map.Entry next2 : this.polyBase2Serializers.entrySet()) {
            KClass kClass2 = (KClass) next2.getKey();
            for (Map.Entry entry : ((Map) next2.getValue()).entrySet()) {
                KClass kClass3 = (KClass) entry.getKey();
                KSerializer kSerializer = (KSerializer) entry.getValue();
                Intrinsics.checkNotNull(kClass2, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                Intrinsics.checkNotNull(kClass3, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                serializersModuleCollector.polymorphic(kClass2, kClass3, kSerializer);
            }
        }
        for (Map.Entry next3 : this.polyBase2DefaultSerializerProvider.entrySet()) {
            KClass kClass4 = (KClass) next3.getKey();
            Function1 function1 = (Function1) next3.getValue();
            Intrinsics.checkNotNull(kClass4, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            Intrinsics.checkNotNull(function1, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'value')] kotlin.Any, kotlinx.serialization.SerializationStrategy<kotlin.Any>?>{ kotlinx.serialization.modules.SerializersModuleKt.PolymorphicSerializerProvider<kotlin.Any> }");
            serializersModuleCollector.polymorphicDefaultSerializer(kClass4, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1));
        }
        for (Map.Entry next4 : this.polyBase2DefaultDeserializerProvider.entrySet()) {
            KClass kClass5 = (KClass) next4.getKey();
            Function1 function12 = (Function1) next4.getValue();
            Intrinsics.checkNotNull(kClass5, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            Intrinsics.checkNotNull(function12, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'className')] kotlin.String?, kotlinx.serialization.DeserializationStrategy<out kotlin.Any>?>{ kotlinx.serialization.modules.SerializersModuleKt.PolymorphicDeserializerProvider<out kotlin.Any> }");
            serializersModuleCollector.polymorphicDefaultDeserializer(kClass5, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function12, 1));
        }
    }
}
