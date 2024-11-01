package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* compiled from: ProtoBasedClassDataFinder.kt */
public final class ProtoBasedClassDataFinder implements ClassDataFinder {
    private final Map<ClassId, ProtoBuf.Class> classIdToProto;
    private final Function1<ClassId, SourceElement> classSource;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;

    public ProtoBasedClassDataFinder(ProtoBuf.PackageFragment packageFragment, NameResolver nameResolver2, BinaryVersion binaryVersion, Function1<? super ClassId, ? extends SourceElement> function1) {
        Intrinsics.checkNotNullParameter(packageFragment, "proto");
        Intrinsics.checkNotNullParameter(nameResolver2, "nameResolver");
        Intrinsics.checkNotNullParameter(binaryVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(function1, "classSource");
        this.nameResolver = nameResolver2;
        this.metadataVersion = binaryVersion;
        this.classSource = function1;
        List<ProtoBuf.Class> class_List = packageFragment.getClass_List();
        Intrinsics.checkNotNullExpressionValue(class_List, "getClass_List(...)");
        Iterable iterable = class_List;
        Map<ClassId, ProtoBuf.Class> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (Object next : iterable) {
            linkedHashMap.put(NameResolverUtilKt.getClassId(this.nameResolver, ((ProtoBuf.Class) next).getFqName()), next);
        }
        this.classIdToProto = linkedHashMap;
    }

    public final Collection<ClassId> getAllClassIds() {
        return this.classIdToProto.keySet();
    }

    public ClassData findClassData(ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        ProtoBuf.Class classR = this.classIdToProto.get(classId);
        if (classR == null) {
            return null;
        }
        return new ClassData(this.nameResolver, classR, this.metadataVersion, this.classSource.invoke(classId));
    }
}
