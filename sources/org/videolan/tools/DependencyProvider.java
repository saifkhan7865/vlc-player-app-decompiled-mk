package org.videolan.tools;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J>\u0010\u0012\u001a\u0002H\u0013\"\b\b\u0001\u0010\u0014*\u00020\u0002\"\n\b\u0002\u0010\u0013\u0018\u0001*\u0002H\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u0002H\u0014\u0018\u00010\u0017H\b¢\u0006\u0002\u0010\u0018JJ\u0010\u0019\u001a\u00020\u001a\"\b\b\u0001\u0010\u0014*\u00020\u0002\"\n\b\u0002\u0010\u0013\u0018\u0001*\u0002H\u00142\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u0002H\u0014\u0018\u00010\u00172\u0014\b\b\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00130\u0007H\bø\u0001\u0000R)\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001c"}, d2 = {"Lorg/videolan/tools/DependencyProvider;", "A", "", "()V", "creatorMap", "Ljava/util/concurrent/ConcurrentMap;", "", "Lkotlin/Function1;", "getCreatorMap", "()Ljava/util/concurrent/ConcurrentMap;", "objectMap", "getObjectMap", "overrideCreator", "", "getOverrideCreator", "()Z", "setOverrideCreator", "(Z)V", "get", "T", "X", "arg", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "registerCreator", "", "creator", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DependencyProvider.kt */
public class DependencyProvider<A> {
    private final ConcurrentMap<String, Function1<A, Object>> creatorMap = new ConcurrentHashMap();
    private final ConcurrentMap<String, Object> objectMap = new ConcurrentHashMap();
    private boolean overrideCreator = true;

    public final ConcurrentMap<String, Object> getObjectMap() {
        return this.objectMap;
    }

    public final ConcurrentMap<String, Function1<A, Object>> getCreatorMap() {
        return this.creatorMap;
    }

    public final boolean getOverrideCreator() {
        return this.overrideCreator;
    }

    public final void setOverrideCreator(boolean z) {
        this.overrideCreator = z;
    }

    public static /* synthetic */ void registerCreator$default(DependencyProvider dependencyProvider, Class<Object> cls, Function1 function1, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                cls = null;
            }
            Intrinsics.checkNotNullParameter(function1, "creator");
            if (cls == null) {
                Intrinsics.reifiedOperationMarker(4, "T");
                cls = Object.class;
                Class cls2 = cls;
            }
            String name = cls.getName();
            if (dependencyProvider.getOverrideCreator() || !dependencyProvider.getCreatorMap().containsKey(name)) {
                dependencyProvider.getCreatorMap().put(name, function1);
            }
            if (dependencyProvider.getObjectMap().containsKey(name) && dependencyProvider.getOverrideCreator()) {
                dependencyProvider.getObjectMap().remove(name);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: registerCreator");
    }

    public final /* synthetic */ <X, T extends X> void registerCreator(Class<X> cls, Function1<? super A, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(function1, "creator");
        Class cls2 = cls;
        if (cls == null) {
            Intrinsics.reifiedOperationMarker(4, "T");
            cls2 = Object.class;
            Class cls3 = cls2;
        }
        String name = cls2.getName();
        if (getOverrideCreator() || !getCreatorMap().containsKey(name)) {
            getCreatorMap().put(name, function1);
        }
        if (getObjectMap().containsKey(name) && getOverrideCreator()) {
            getObjectMap().remove(name);
        }
    }

    public static /* synthetic */ Object get$default(DependencyProvider dependencyProvider, Object obj, Class<Object> cls, int i, Object obj2) {
        if (obj2 == null) {
            Object obj3 = null;
            if ((i & 2) != 0) {
                cls = null;
            }
            if (cls == null) {
                Intrinsics.reifiedOperationMarker(4, "T");
                cls = Object.class;
                Class cls2 = cls;
            }
            String name = cls.getName();
            if (!dependencyProvider.getObjectMap().containsKey(name)) {
                Map objectMap2 = dependencyProvider.getObjectMap();
                Function1 function1 = (Function1) dependencyProvider.getCreatorMap().get(name);
                if (function1 != null) {
                    obj3 = function1.invoke(obj);
                }
                objectMap2.put(name, obj3);
            }
            Object obj4 = dependencyProvider.getObjectMap().get(name);
            Intrinsics.reifiedOperationMarker(1, "T");
            Object obj5 = obj4;
            return obj4;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
    }

    public final /* synthetic */ <X, T extends X> T get(A a, Class<X> cls) {
        Class cls2 = cls;
        if (cls == null) {
            Intrinsics.reifiedOperationMarker(4, "T");
            cls2 = Object.class;
            Class cls3 = cls2;
        }
        String name = cls2.getName();
        if (!getObjectMap().containsKey(name)) {
            Map objectMap2 = getObjectMap();
            Function1 function1 = (Function1) getCreatorMap().get(name);
            objectMap2.put(name, function1 != null ? function1.invoke(a) : null);
        }
        T t = getObjectMap().get(name);
        Intrinsics.reifiedOperationMarker(1, "T");
        Object obj = (Object) t;
        return t;
    }
}
