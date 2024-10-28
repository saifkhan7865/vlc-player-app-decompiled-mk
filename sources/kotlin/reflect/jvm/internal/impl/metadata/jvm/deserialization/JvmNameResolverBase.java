package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* compiled from: JvmNameResolverBase.kt */
public class JvmNameResolverBase implements NameResolver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final List<String> PREDEFINED_STRINGS;
    private static final Map<String, Integer> PREDEFINED_STRINGS_MAP;

    /* renamed from: kotlin  reason: collision with root package name */
    private static final String f7kotlin;
    private final Set<Integer> localNameIndices;
    private final List<JvmProtoBuf.StringTableTypes.Record> records;
    private final String[] strings;

    /* compiled from: JvmNameResolverBase.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record$Operation[] r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record$Operation r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record$Operation r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation.INTERNAL_TO_CLASS_ID     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record$Operation r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation.DESC_TO_CLASS_ID     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolverBase.WhenMappings.<clinit>():void");
        }
    }

    public JvmNameResolverBase(String[] strArr, Set<Integer> set, List<JvmProtoBuf.StringTableTypes.Record> list) {
        Intrinsics.checkNotNullParameter(strArr, "strings");
        Intrinsics.checkNotNullParameter(set, "localNameIndices");
        Intrinsics.checkNotNullParameter(list, "records");
        this.strings = strArr;
        this.localNameIndices = set;
        this.records = list;
    }

    public String getString(int i) {
        String str;
        JvmProtoBuf.StringTableTypes.Record record = this.records.get(i);
        if (record.hasString()) {
            str = record.getString();
        } else {
            if (record.hasPredefinedIndex()) {
                List<String> list = PREDEFINED_STRINGS;
                int size = list.size();
                int predefinedIndex = record.getPredefinedIndex();
                if (predefinedIndex >= 0 && predefinedIndex < size) {
                    str = list.get(record.getPredefinedIndex());
                }
            }
            str = this.strings[i];
        }
        if (record.getSubstringIndexCount() >= 2) {
            List<Integer> substringIndexList = record.getSubstringIndexList();
            Intrinsics.checkNotNull(substringIndexList);
            Integer num = substringIndexList.get(0);
            Integer num2 = substringIndexList.get(1);
            Intrinsics.checkNotNull(num);
            if (num.intValue() >= 0) {
                int intValue = num.intValue();
                Intrinsics.checkNotNull(num2);
                if (intValue <= num2.intValue() && num2.intValue() <= str.length()) {
                    Intrinsics.checkNotNull(str);
                    str = str.substring(num.intValue(), num2.intValue());
                    Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
                }
            }
        }
        String str2 = str;
        if (record.getReplaceCharCount() >= 2) {
            List<Integer> replaceCharList = record.getReplaceCharList();
            Intrinsics.checkNotNull(replaceCharList);
            Intrinsics.checkNotNull(str2);
            str2 = StringsKt.replace$default(str2, (char) replaceCharList.get(0).intValue(), (char) replaceCharList.get(1).intValue(), false, 4, (Object) null);
        }
        String str3 = str2;
        JvmProtoBuf.StringTableTypes.Record.Operation operation = record.getOperation();
        if (operation == null) {
            operation = JvmProtoBuf.StringTableTypes.Record.Operation.NONE;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i2 == 2) {
            Intrinsics.checkNotNull(str3);
            str3 = StringsKt.replace$default(str3, (char) Typography.dollar, '.', false, 4, (Object) null);
        } else if (i2 == 3) {
            if (str3.length() >= 2) {
                Intrinsics.checkNotNull(str3);
                str3 = str3.substring(1, str3.length() - 1);
                Intrinsics.checkNotNullExpressionValue(str3, "substring(...)");
            }
            String str4 = str3;
            Intrinsics.checkNotNull(str4);
            str3 = StringsKt.replace$default(str4, (char) Typography.dollar, '.', false, 4, (Object) null);
        }
        Intrinsics.checkNotNull(str3);
        return str3;
    }

    public String getQualifiedClassName(int i) {
        return getString(i);
    }

    public boolean isLocalClassName(int i) {
        return this.localNameIndices.contains(Integer.valueOf(i));
    }

    /* compiled from: JvmNameResolverBase.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.listOf('k', 'o', 't', 'l', 'i', 'n'), "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        f7kotlin = joinToString$default;
        StringBuilder sb = new StringBuilder();
        sb.append(joinToString$default);
        String str = joinToString$default + "/CharSequence";
        sb.append("/String");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(joinToString$default);
        String str2 = sb2;
        sb3.append("/Comparable");
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(joinToString$default);
        String str3 = sb4;
        sb5.append("/Enum");
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(joinToString$default);
        String str4 = sb6;
        sb7.append("/Array");
        String sb8 = sb7.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(joinToString$default);
        String str5 = sb8;
        sb9.append("/ByteArray");
        String sb10 = sb9.toString();
        StringBuilder sb11 = new StringBuilder();
        sb11.append(joinToString$default);
        String str6 = sb10;
        sb11.append("/DoubleArray");
        String sb12 = sb11.toString();
        StringBuilder sb13 = new StringBuilder();
        sb13.append(joinToString$default);
        String str7 = sb12;
        sb13.append("/FloatArray");
        String sb14 = sb13.toString();
        StringBuilder sb15 = new StringBuilder();
        sb15.append(joinToString$default);
        String str8 = sb14;
        sb15.append("/IntArray");
        String sb16 = sb15.toString();
        StringBuilder sb17 = new StringBuilder();
        sb17.append(joinToString$default);
        String str9 = sb16;
        sb17.append("/LongArray");
        String sb18 = sb17.toString();
        StringBuilder sb19 = new StringBuilder();
        sb19.append(joinToString$default);
        String str10 = sb18;
        sb19.append("/ShortArray");
        String sb20 = sb19.toString();
        StringBuilder sb21 = new StringBuilder();
        sb21.append(joinToString$default);
        String str11 = sb20;
        sb21.append("/BooleanArray");
        String sb22 = sb21.toString();
        StringBuilder sb23 = new StringBuilder();
        sb23.append(joinToString$default);
        String str12 = sb22;
        sb23.append("/CharArray");
        String sb24 = sb23.toString();
        StringBuilder sb25 = new StringBuilder();
        sb25.append(joinToString$default);
        String str13 = sb24;
        sb25.append("/Cloneable");
        String sb26 = sb25.toString();
        StringBuilder sb27 = new StringBuilder();
        sb27.append(joinToString$default);
        String str14 = sb26;
        sb27.append("/Annotation");
        String sb28 = sb27.toString();
        StringBuilder sb29 = new StringBuilder();
        sb29.append(joinToString$default);
        String str15 = sb28;
        sb29.append("/collections/Iterable");
        String sb30 = sb29.toString();
        StringBuilder sb31 = new StringBuilder();
        sb31.append(joinToString$default);
        String str16 = sb30;
        sb31.append("/collections/MutableIterable");
        String sb32 = sb31.toString();
        StringBuilder sb33 = new StringBuilder();
        sb33.append(joinToString$default);
        String str17 = sb32;
        sb33.append("/collections/Collection");
        String sb34 = sb33.toString();
        StringBuilder sb35 = new StringBuilder();
        sb35.append(joinToString$default);
        String str18 = sb34;
        sb35.append("/collections/MutableCollection");
        String sb36 = sb35.toString();
        StringBuilder sb37 = new StringBuilder();
        sb37.append(joinToString$default);
        String str19 = sb36;
        sb37.append("/collections/List");
        String sb38 = sb37.toString();
        StringBuilder sb39 = new StringBuilder();
        sb39.append(joinToString$default);
        String str20 = sb38;
        sb39.append("/collections/MutableList");
        String sb40 = sb39.toString();
        StringBuilder sb41 = new StringBuilder();
        sb41.append(joinToString$default);
        String str21 = sb40;
        sb41.append("/collections/Set");
        String sb42 = sb41.toString();
        StringBuilder sb43 = new StringBuilder();
        sb43.append(joinToString$default);
        String str22 = sb42;
        sb43.append("/collections/MutableSet");
        String sb44 = sb43.toString();
        StringBuilder sb45 = new StringBuilder();
        sb45.append(joinToString$default);
        String str23 = sb44;
        sb45.append("/collections/Map");
        String sb46 = sb45.toString();
        StringBuilder sb47 = new StringBuilder();
        sb47.append(joinToString$default);
        String str24 = sb46;
        sb47.append("/collections/MutableMap");
        String sb48 = sb47.toString();
        StringBuilder sb49 = new StringBuilder();
        sb49.append(joinToString$default);
        String str25 = sb48;
        sb49.append("/collections/Map.Entry");
        String sb50 = sb49.toString();
        StringBuilder sb51 = new StringBuilder();
        sb51.append(joinToString$default);
        String str26 = sb50;
        sb51.append("/collections/MutableMap.MutableEntry");
        String sb52 = sb51.toString();
        StringBuilder sb53 = new StringBuilder();
        sb53.append(joinToString$default);
        String str27 = sb52;
        sb53.append("/collections/Iterator");
        String sb54 = sb53.toString();
        StringBuilder sb55 = new StringBuilder();
        sb55.append(joinToString$default);
        String str28 = sb54;
        sb55.append("/collections/MutableIterator");
        String sb56 = sb55.toString();
        StringBuilder sb57 = new StringBuilder();
        sb57.append(joinToString$default);
        sb57.append("/collections/ListIterator");
        List<String> listOf = CollectionsKt.listOf(joinToString$default + "/Any", joinToString$default + "/Nothing", joinToString$default + "/Unit", joinToString$default + "/Throwable", joinToString$default + "/Number", joinToString$default + "/Byte", joinToString$default + "/Double", joinToString$default + "/Float", joinToString$default + "/Int", joinToString$default + "/Long", joinToString$default + "/Short", joinToString$default + "/Boolean", joinToString$default + "/Char", str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, str26, str27, str28, sb56, sb57.toString(), joinToString$default + "/collections/MutableListIterator");
        PREDEFINED_STRINGS = listOf;
        Iterable<IndexedValue> withIndex = CollectionsKt.withIndex(listOf);
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(withIndex, 10)), 16));
        for (IndexedValue indexedValue : withIndex) {
            linkedHashMap.put((String) indexedValue.getValue(), Integer.valueOf(indexedValue.getIndex()));
        }
        PREDEFINED_STRINGS_MAP = linkedHashMap;
    }
}
