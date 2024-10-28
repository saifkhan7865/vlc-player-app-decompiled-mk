package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a%\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\bø\u0001\u0000\u001a,\u0010\b\u001a\u00020\u0001\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000e\u001a#\u0010\b\u001a\u00020\u0001\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000eH\b\u001a'\u0010\u000f\u001a\u00020\u0006\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n*\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000eH\b\u001a[\u0010\u0010\u001a\u00020\u0006\"\b\b\u0000\u0010\u0011*\u00020\n*\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\f2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0011\u0018\u00010\u000e2\u001f\b\u0002\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0015"}, d2 = {"EmptySerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "SerializersModule", "builderAction", "Lkotlin/Function1;", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "", "Lkotlin/ExtensionFunctionType;", "serializersModuleOf", "T", "", "kClass", "Lkotlin/reflect/KClass;", "serializer", "Lkotlinx/serialization/KSerializer;", "contextual", "polymorphic", "Base", "baseClass", "baseSerializer", "Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SerializersModuleBuilders.kt */
public final class SerializersModuleBuildersKt {
    public static final /* synthetic */ <T> SerializersModule serializersModuleOf(KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, "T");
        return serializersModuleOf(Reflection.getOrCreateKotlinClass(Object.class), kSerializer);
    }

    public static final SerializersModule SerializersModule(Function1<? super SerializersModuleBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        function1.invoke(serializersModuleBuilder);
        return serializersModuleBuilder.build();
    }

    public static final SerializersModule EmptySerializersModule() {
        return SerializersModuleKt.getEmptySerializersModule();
    }

    public static final /* synthetic */ <T> void contextual(SerializersModuleBuilder serializersModuleBuilder, KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(serializersModuleBuilder, "<this>");
        Intrinsics.checkNotNullParameter(kSerializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, "T");
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(Object.class), kSerializer);
    }

    public static /* synthetic */ void polymorphic$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, KSerializer kSerializer, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            kSerializer = null;
        }
        if ((i & 4) != 0) {
            function1 = SerializersModuleBuildersKt$polymorphic$1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(serializersModuleBuilder, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        PolymorphicModuleBuilder polymorphicModuleBuilder = new PolymorphicModuleBuilder(kClass, kSerializer);
        function1.invoke(polymorphicModuleBuilder);
        polymorphicModuleBuilder.buildTo(serializersModuleBuilder);
    }

    public static final <Base> void polymorphic(SerializersModuleBuilder serializersModuleBuilder, KClass<Base> kClass, KSerializer<Base> kSerializer, Function1<? super PolymorphicModuleBuilder<? super Base>, Unit> function1) {
        Intrinsics.checkNotNullParameter(serializersModuleBuilder, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        PolymorphicModuleBuilder polymorphicModuleBuilder = new PolymorphicModuleBuilder(kClass, kSerializer);
        function1.invoke(polymorphicModuleBuilder);
        polymorphicModuleBuilder.buildTo(serializersModuleBuilder);
    }

    public static final <T> SerializersModule serializersModuleOf(KClass<T> kClass, KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(kSerializer, "serializer");
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModuleBuilder.contextual(kClass, kSerializer);
        return serializersModuleBuilder.build();
    }
}
