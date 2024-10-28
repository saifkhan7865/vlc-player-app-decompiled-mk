package io.ktor.server.config;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001 B#\b\u0012\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t0\b¢\u0006\u0002\u0010\nB3\b\u0016\u0012*\u0010\u0007\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t0\u000b\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0002\u0010\fB\u0007\b\u0016¢\u0006\u0002\u0010\rJ\u0010\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u001c\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u001cJ\u0016\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001eH\u0016R \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lio/ktor/server/config/MapApplicationConfig;", "Lio/ktor/server/config/ApplicationConfig;", "map", "", "", "path", "(Ljava/util/Map;Ljava/lang/String;)V", "values", "", "Lkotlin/Pair;", "(Ljava/util/List;)V", "", "([Lkotlin/Pair;)V", "()V", "getMap", "()Ljava/util/Map;", "getPath", "()Ljava/lang/String;", "config", "configList", "keys", "", "property", "Lio/ktor/server/config/ApplicationConfigValue;", "propertyOrNull", "put", "", "value", "", "toMap", "", "", "MapApplicationConfigValue", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MapApplicationConfig.kt */
public class MapApplicationConfig implements ApplicationConfig {
    private final Map<String, String> map;
    private final String path;

    /* access modifiers changed from: protected */
    public final Map<String, String> getMap() {
        return this.map;
    }

    /* access modifiers changed from: protected */
    public final String getPath() {
        return this.path;
    }

    private MapApplicationConfig(Map<String, String> map2, String str) {
        this.map = map2;
        this.path = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MapApplicationConfig(java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> r5) {
        /*
            r4 = this;
            java.lang.String r0 = "values"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Map r0 = kotlin.collections.MapsKt.toMap(r5)
            java.util.Map r0 = kotlin.collections.MapsKt.toMutableMap(r0)
            java.lang.String r1 = ""
            r4.<init>(r0, r1)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Iterator r5 = r5.iterator()
        L_0x001f:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0035
            java.lang.Object r1 = r5.next()
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r1.getFirst()
            java.lang.String r1 = (java.lang.String) r1
            io.ktor.server.config.MapApplicationConfigKt.findListElements(r1, r0)
            goto L_0x001f
        L_0x0035:
            java.util.Set r5 = r0.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x003d:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0074
            java.lang.Object r0 = r5.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.map
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = ".size"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.put(r1, r0)
            goto L_0x003d
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.config.MapApplicationConfig.<init>(java.util.List):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MapApplicationConfig(Pair<String, String>... pairArr) {
        this(MapsKt.mutableMapOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length)), "");
        Intrinsics.checkNotNullParameter(pairArr, "values");
    }

    public MapApplicationConfig() {
        this(new LinkedHashMap(), "");
    }

    public final void put(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(str2, "value");
        this.map.put(MapApplicationConfigKt.combine(this.path, str), str2);
    }

    public ApplicationConfigValue property(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        ApplicationConfigValue propertyOrNull = propertyOrNull(str);
        if (propertyOrNull != null) {
            return propertyOrNull;
        }
        throw new ApplicationConfigurationException("Property " + MapApplicationConfigKt.combine(this.path, str) + " not found.");
    }

    public List<ApplicationConfig> configList(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        String access$combine = MapApplicationConfigKt.combine(this.path, str);
        String str2 = this.map.get(MapApplicationConfigKt.combine(access$combine, ContentDisposition.Parameters.Size));
        if (str2 != null) {
            Iterable until = RangesKt.until(0, Integer.parseInt(str2));
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                arrayList.add(new MapApplicationConfig(this.map, MapApplicationConfigKt.combine(access$combine, String.valueOf(((IntIterator) it).nextInt()))));
            }
            return (List) arrayList;
        }
        throw new ApplicationConfigurationException("Property " + access$combine + ".size not found.");
    }

    public ApplicationConfigValue propertyOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        String access$combine = MapApplicationConfigKt.combine(this.path, str);
        if (this.map.containsKey(access$combine) || this.map.containsKey(MapApplicationConfigKt.combine(access$combine, ContentDisposition.Parameters.Size))) {
            return new MapApplicationConfigValue(this.map, access$combine);
        }
        return null;
    }

    public ApplicationConfig config(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return new MapApplicationConfig(this.map, MapApplicationConfigKt.combine(this.path, str));
    }

    public Set<String> keys() {
        Collection collection;
        Object obj;
        boolean z = this.path.length() == 0;
        Set<String> keySet = this.map.keySet();
        if (z) {
            collection = keySet;
        } else {
            Collection arrayList = new ArrayList();
            for (Object next : keySet) {
                if (StringsKt.startsWith$default((String) next, this.path + '.', false, 2, (Object) null)) {
                    arrayList.add(next);
                }
            }
            collection = (List) arrayList;
        }
        Iterable<String> iterable = collection;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable) {
            if (StringsKt.contains$default((CharSequence) (String) next2, (CharSequence) ".size", false, 2, (Object) null)) {
                arrayList2.add(next2);
            }
        }
        Iterable<String> iterable2 = (List) arrayList2;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (String substringBefore$default : iterable2) {
            arrayList3.add(StringsKt.substringBefore$default(substringBefore$default, ".size", (String) null, 2, (Object) null));
        }
        List list = (List) arrayList3;
        Set linkedHashSet = new LinkedHashSet();
        Collection arrayList4 = new ArrayList();
        for (String str : iterable) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (StringsKt.startsWith$default(str, (String) obj, false, 2, (Object) null)) {
                    break;
                }
            }
            String str2 = (String) obj;
            if (str2 != null && !linkedHashSet.contains(str2)) {
                linkedHashSet.add(str2);
                str = str2;
            } else if (str2 != null) {
                str = null;
            }
            if (!z) {
                if (str != null) {
                    str = StringsKt.substringAfter$default(str, this.path + '.', (String) null, 2, (Object) null);
                } else {
                    str = null;
                }
            }
            if (str != null) {
                arrayList4.add(str);
            }
        }
        return CollectionsKt.toSet((List) arrayList4);
    }

    public Map<String, Object> toMap() {
        Pair<A, B> pair;
        Collection arrayList = new ArrayList();
        for (Object next : this.map.keySet()) {
            if (StringsKt.startsWith$default((String) next, this.path, false, 2, (Object) null)) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String drop : iterable) {
            arrayList2.add((String) CollectionsKt.first(StringsKt.split$default((CharSequence) StringsKt.drop(drop, this.path.length() == 0 ? 0 : this.path.length() + 1), new char[]{'.'}, false, 0, 6, (Object) null)));
        }
        Iterable<String> distinct = CollectionsKt.distinct((List) arrayList2);
        Map<String, Object> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(distinct, 10)), 16));
        for (String str : distinct) {
            String access$combine = MapApplicationConfigKt.combine(this.path, str);
            if (this.map.containsKey(access$combine)) {
                pair = TuplesKt.to(str, this.map.get(access$combine));
            } else if (!this.map.containsKey(MapApplicationConfigKt.combine(access$combine, ContentDisposition.Parameters.Size))) {
                pair = TuplesKt.to(str, config(str).toMap());
            } else if (this.map.containsKey(MapApplicationConfigKt.combine(access$combine, Constants.GROUP_VIDEOS_FOLDER))) {
                pair = TuplesKt.to(str, property(access$combine).getList());
            } else {
                Iterable<ApplicationConfig> configList = configList(access$combine);
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(configList, 10));
                for (ApplicationConfig map2 : configList) {
                    arrayList3.add(map2.toMap());
                }
                pair = TuplesKt.to(str, (List) arrayList3);
            }
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lio/ktor/server/config/MapApplicationConfig$MapApplicationConfigValue;", "Lio/ktor/server/config/ApplicationConfigValue;", "map", "", "", "path", "(Ljava/util/Map;Ljava/lang/String;)V", "getMap", "()Ljava/util/Map;", "getPath", "()Ljava/lang/String;", "getList", "", "getString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: MapApplicationConfig.kt */
    protected static final class MapApplicationConfigValue implements ApplicationConfigValue {
        private final Map<String, String> map;
        private final String path;

        public MapApplicationConfigValue(Map<String, String> map2, String str) {
            Intrinsics.checkNotNullParameter(map2, "map");
            Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
            this.map = map2;
            this.path = str;
        }

        public final Map<String, String> getMap() {
            return this.map;
        }

        public final String getPath() {
            return this.path;
        }

        public String getString() {
            String str = this.map.get(this.path);
            Intrinsics.checkNotNull(str);
            return str;
        }

        public List<String> getList() {
            String str = this.map.get(MapApplicationConfigKt.combine(this.path, ContentDisposition.Parameters.Size));
            if (str != null) {
                Iterable until = RangesKt.until(0, Integer.parseInt(str));
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                Iterator it = until.iterator();
                while (it.hasNext()) {
                    String str2 = this.map.get(MapApplicationConfigKt.combine(this.path, String.valueOf(((IntIterator) it).nextInt())));
                    Intrinsics.checkNotNull(str2);
                    arrayList.add(str2);
                }
                return (List) arrayList;
            }
            throw new ApplicationConfigurationException("Property " + this.path + ".size not found.");
        }
    }

    public final void put(String str, Iterable<String> iterable) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(iterable, "values");
        int i = 0;
        int i2 = 0;
        for (String next : iterable) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            put(MapApplicationConfigKt.combine(str, String.valueOf(i2)), next);
            i++;
            i2 = i3;
        }
        put(MapApplicationConfigKt.combine(str, ContentDisposition.Parameters.Size), String.valueOf(i));
    }
}
