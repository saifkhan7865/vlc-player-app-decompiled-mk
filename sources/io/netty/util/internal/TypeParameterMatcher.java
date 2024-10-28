package io.netty.util.internal;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public abstract class TypeParameterMatcher {
    private static final TypeParameterMatcher NOOP = new TypeParameterMatcher() {
        public boolean match(Object obj) {
            return true;
        }
    };

    public abstract boolean match(Object obj);

    public static TypeParameterMatcher get(Class<?> cls) {
        Map<Class<?>, TypeParameterMatcher> typeParameterMatcherGetCache = InternalThreadLocalMap.get().typeParameterMatcherGetCache();
        TypeParameterMatcher typeParameterMatcher = typeParameterMatcherGetCache.get(cls);
        if (typeParameterMatcher == null) {
            if (cls == Object.class) {
                typeParameterMatcher = NOOP;
            } else {
                typeParameterMatcher = new ReflectiveMatcher(cls);
            }
            typeParameterMatcherGetCache.put(cls, typeParameterMatcher);
        }
        return typeParameterMatcher;
    }

    public static TypeParameterMatcher find(Object obj, Class<?> cls, String str) {
        Map<Class<?>, Map<String, TypeParameterMatcher>> typeParameterMatcherFindCache = InternalThreadLocalMap.get().typeParameterMatcherFindCache();
        Class<?> cls2 = obj.getClass();
        Map map = typeParameterMatcherFindCache.get(cls2);
        if (map == null) {
            map = new HashMap();
            typeParameterMatcherFindCache.put(cls2, map);
        }
        TypeParameterMatcher typeParameterMatcher = (TypeParameterMatcher) map.get(str);
        if (typeParameterMatcher != null) {
            return typeParameterMatcher;
        }
        TypeParameterMatcher typeParameterMatcher2 = get(find0(obj, cls, str));
        map.put(str, typeParameterMatcher2);
        return typeParameterMatcher2;
    }

    private static Class<?> find0(Object obj, Class<?> cls, String str) {
        Class<?> cls2 = obj.getClass();
        Class<?> cls3 = cls2;
        while (true) {
            if (cls3.getSuperclass() == cls) {
                TypeVariable[] typeParameters = cls3.getSuperclass().getTypeParameters();
                int i = 0;
                while (true) {
                    if (i >= typeParameters.length) {
                        i = -1;
                        break;
                    } else if (str.equals(typeParameters[i].getName())) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i >= 0) {
                    Type genericSuperclass = cls3.getGenericSuperclass();
                    if (!(genericSuperclass instanceof ParameterizedType)) {
                        return Object.class;
                    }
                    Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[i];
                    if (type instanceof ParameterizedType) {
                        type = ((ParameterizedType) type).getRawType();
                    }
                    if (type instanceof Class) {
                        return (Class) type;
                    }
                    if (type instanceof GenericArrayType) {
                        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                        if (genericComponentType instanceof ParameterizedType) {
                            genericComponentType = ((ParameterizedType) genericComponentType).getRawType();
                        }
                        if (genericComponentType instanceof Class) {
                            return Array.newInstance((Class) genericComponentType, 0).getClass();
                        }
                    }
                    if (!(type instanceof TypeVariable)) {
                        return fail(cls2, str);
                    }
                    TypeVariable typeVariable = (TypeVariable) type;
                    if (!(typeVariable.getGenericDeclaration() instanceof Class)) {
                        return Object.class;
                    }
                    Class<?> cls4 = (Class) typeVariable.getGenericDeclaration();
                    String name = typeVariable.getName();
                    if (!cls4.isAssignableFrom(cls2)) {
                        return Object.class;
                    }
                    cls3 = cls2;
                    Class<?> cls5 = cls4;
                    str = name;
                    cls = cls5;
                } else {
                    throw new IllegalStateException("unknown type parameter '" + str + "': " + cls);
                }
            } else {
                cls3 = cls3.getSuperclass();
                if (cls3 == null) {
                    return fail(cls2, str);
                }
            }
        }
    }

    private static Class<?> fail(Class<?> cls, String str) {
        throw new IllegalStateException("cannot determine the type of the type parameter '" + str + "': " + cls);
    }

    private static final class ReflectiveMatcher extends TypeParameterMatcher {
        private final Class<?> type;

        ReflectiveMatcher(Class<?> cls) {
            this.type = cls;
        }

        public boolean match(Object obj) {
            return this.type.isInstance(obj);
        }
    }

    TypeParameterMatcher() {
    }
}
