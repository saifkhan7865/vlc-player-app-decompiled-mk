package io.ktor.server.engine;

import io.ktor.http.ContentDisposition;
import java.io.Closeable;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0012B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/server/engine/OverridingClassLoader;", "Ljava/lang/ClassLoader;", "Ljava/io/Closeable;", "classpath", "", "Ljava/net/URL;", "parentClassLoader", "(Ljava/util/List;Ljava/lang/ClassLoader;)V", "childClassLoader", "Lio/ktor/server/engine/OverridingClassLoader$ChildURLClassLoader;", "close", "", "loadClass", "Ljava/lang/Class;", "name", "", "resolve", "", "ChildURLClassLoader", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OverridingClassLoader.kt */
public final class OverridingClassLoader extends ClassLoader implements Closeable {
    private final ChildURLClassLoader childClassLoader;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OverridingClassLoader(List<URL> list, ClassLoader classLoader) {
        super(classLoader);
        Intrinsics.checkNotNullParameter(list, "classpath");
        ClassLoader parent = getParent();
        Intrinsics.checkNotNullExpressionValue(parent, "parent");
        this.childClassLoader = new ChildURLClassLoader((URL[]) list.toArray(new URL[0]), parent);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r2 = super.loadClass(r2, r3);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "{\n        // didn't find…lass(name, resolve)\n    }");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Class<?> loadClass(java.lang.String r2, boolean r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)     // Catch:{ all -> 0x0018 }
            io.ktor.server.engine.OverridingClassLoader$ChildURLClassLoader r0 = r1.childClassLoader     // Catch:{ ClassNotFoundException -> 0x000d }
            java.lang.Class r2 = r0.findClass(r2)     // Catch:{ ClassNotFoundException -> 0x000d }
            goto L_0x0016
        L_0x000d:
            java.lang.Class r2 = super.loadClass(r2, r3)     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = "{\n        // didn't find…lass(name, resolve)\n    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0018 }
        L_0x0016:
            monitor-exit(r1)
            return r2
        L_0x0018:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.OverridingClassLoader.loadClass(java.lang.String, boolean):java.lang.Class");
    }

    public void close() {
        this.childClassLoader.close();
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/server/engine/OverridingClassLoader$ChildURLClassLoader;", "Ljava/net/URLClassLoader;", "urls", "", "Ljava/net/URL;", "realParent", "Ljava/lang/ClassLoader;", "([Ljava/net/URL;Ljava/lang/ClassLoader;)V", "findClass", "Ljava/lang/Class;", "name", "", "findResource", "findResources", "Ljava/util/Enumeration;", "getResource", "getResourceAsStream", "Ljava/io/InputStream;", "getResources", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OverridingClassLoader.kt */
    private static final class ChildURLClassLoader extends URLClassLoader {
        private final ClassLoader realParent;

        public URL findResource(String str) {
            return null;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ChildURLClassLoader(URL[] urlArr, ClassLoader classLoader) {
            super(urlArr, (ClassLoader) null);
            Intrinsics.checkNotNullParameter(urlArr, "urls");
            Intrinsics.checkNotNullParameter(classLoader, "realParent");
            this.realParent = classLoader;
        }

        public Class<?> findClass(String str) {
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Class<?> findLoadedClass = super.findLoadedClass(str);
            if (findLoadedClass != null) {
                return findLoadedClass;
            }
            try {
                Class<?> findClass = super.findClass(str);
                Intrinsics.checkNotNullExpressionValue(findClass, "super.findClass(name)");
                return findClass;
            } catch (ClassNotFoundException unused) {
                Class<?> loadClass = this.realParent.loadClass(str);
                Intrinsics.checkNotNullExpressionValue(loadClass, "realParent.loadClass(name)");
                return loadClass;
            }
        }

        public Enumeration<URL> getResources(String str) {
            Enumeration<URL> resources = this.realParent.getResources(str);
            Intrinsics.checkNotNullExpressionValue(resources, "realParent.getResources(name)");
            return resources;
        }

        public URL getResource(String str) {
            return this.realParent.getResource(str);
        }

        public InputStream getResourceAsStream(String str) {
            return this.realParent.getResourceAsStream(str);
        }

        public Enumeration<URL> findResources(String str) {
            Enumeration<URL> m = Collections.enumeration(Collections.emptyList());
            Intrinsics.checkNotNullExpressionValue(m, "emptyEnumeration()");
            return m;
        }
    }
}
