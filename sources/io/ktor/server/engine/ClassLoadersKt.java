package io.ktor.server.engine;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u0006H\u0002\u001a\u0014\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b*\u00020\u0003H\u0002\u001a\u0014\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b*\u00020\u0003H\u0002¨\u0006\n"}, d2 = {"allURLs", "", "Ljava/net/URL;", "Ljava/lang/ClassLoader;", "findURLClassPathField", "Ljava/lang/reflect/Field;", "Ljava/lang/Class;", "urlClassPath", "", "urlClassPathByPackagesList", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ClassLoaders.kt */
public final class ClassLoadersKt {
    public static final Set<URL> allURLs(ClassLoader classLoader) {
        Set<URL> set;
        Intrinsics.checkNotNullParameter(classLoader, "<this>");
        ClassLoader parent = classLoader.getParent();
        if (parent == null || (set = allURLs(parent)) == null) {
            set = SetsKt.emptySet();
        }
        if (classLoader instanceof URLClassLoader) {
            URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
            Intrinsics.checkNotNullExpressionValue(uRLs, "urLs");
            return SetsKt.plus(CollectionsKt.toSet(ArraysKt.filterNotNull((Object[]) uRLs)), set);
        }
        List<URL> urlClassPath = urlClassPath(classLoader);
        if (urlClassPath == null) {
            return set;
        }
        return SetsKt.plus(set, urlClassPath);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return urlClassPathByPackagesList(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.net.URL> urlClassPath(java.lang.ClassLoader r5) {
        /*
            r0 = 0
            java.lang.Class r1 = r5.getClass()     // Catch:{ all -> 0x0034 }
            java.lang.reflect.Field r1 = findURLClassPathField(r1)     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x000c
            return r0
        L_0x000c:
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ all -> 0x0034 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x0017
            return r0
        L_0x0017:
            java.lang.Class r3 = r1.getClass()     // Catch:{ all -> 0x0034 }
            java.lang.String r4 = "getURLs"
            java.lang.reflect.Method r3 = r3.getMethod(r4, r0)     // Catch:{ all -> 0x0034 }
            if (r3 != 0) goto L_0x0024
            return r0
        L_0x0024:
            r3.setAccessible(r2)     // Catch:{ all -> 0x0034 }
            java.lang.Object r1 = r3.invoke(r1, r0)     // Catch:{ all -> 0x0034 }
            java.net.URL[] r1 = (java.net.URL[]) r1     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0033
            java.util.List r0 = kotlin.collections.ArraysKt.toList((T[]) r1)     // Catch:{ all -> 0x0034 }
        L_0x0033:
            return r0
        L_0x0034:
            java.util.List r0 = urlClassPathByPackagesList(r5)     // Catch:{ all -> 0x0038 }
        L_0x0038:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ClassLoadersKt.urlClassPath(java.lang.ClassLoader):java.util.List");
    }

    private static final List<URL> urlClassPathByPackagesList(ClassLoader classLoader) {
        List list;
        ClassLoader classLoader2 = classLoader;
        Iterable<String> packagesList$ktor_server_host_common = new ClassLoaderDelegate(classLoader2).packagesList$ktor_server_host_common();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(packagesList$ktor_server_host_common, 10));
        for (String replace$default : packagesList$ktor_server_host_common) {
            arrayList.add(StringsKt.replace$default(replace$default, '.', '/', false, 4, (Object) null));
        }
        Collection hashSet = new HashSet();
        for (String str : (List) arrayList) {
            List split$default = StringsKt.split$default((CharSequence) str, new char[]{'/'}, false, 0, 6, (Object) null);
            Iterable intRange = new IntRange(1, split$default.size());
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                arrayList2.add(CollectionsKt.joinToString$default(split$default.subList(0, ((IntIterator) it).nextInt()), "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            }
            CollectionsKt.addAll(hashSet, CollectionsKt.plus((List) arrayList2, str));
        }
        Collection arrayList3 = new ArrayList();
        for (String resources : CollectionsKt.plus(CollectionsKt.sortedWith(hashSet, new ClassLoadersKt$urlClassPathByPackagesList$$inlined$sortedBy$1()), "")) {
            Enumeration<URL> resources2 = classLoader2.getResources(resources);
            if (resources2 != null) {
                Intrinsics.checkNotNullExpressionValue(resources2, "getResources(path)");
                ArrayList<T> list2 = Collections.list(resources2);
                Intrinsics.checkNotNullExpressionValue(list2, "list(this)");
                list = list2;
                if (list != null) {
                    CollectionsKt.addAll(arrayList3, list);
                }
            }
            list = CollectionsKt.emptyList();
            CollectionsKt.addAll(arrayList3, list);
        }
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object next : (List) arrayList3) {
            String path = ((URL) next).getPath();
            Intrinsics.checkNotNullExpressionValue(path, "it.path");
            if (hashSet2.add(StringsKt.substringBefore$default(path, '!', (String) null, 2, (Object) null))) {
                arrayList4.add(next);
            }
        }
        return arrayList4;
    }

    private static final Field findURLClassPathField(Class<?> cls) {
        Field field;
        Field findURLClassPathField;
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "declaredFields");
        Object[] objArr = (Object[]) declaredFields;
        int length = objArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                field = null;
                break;
            }
            field = objArr[i];
            Field field2 = (Field) field;
            if (Intrinsics.areEqual((Object) field2.getName(), (Object) "ucp") && Intrinsics.areEqual((Object) field2.getType().getSimpleName(), (Object) "URLClassPath")) {
                break;
            }
            i++;
        }
        Field field3 = field;
        if (field3 != null) {
            return field3;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || (findURLClassPathField = findURLClassPathField(superclass)) == null) {
            return null;
        }
        return findURLClassPathField;
    }
}
