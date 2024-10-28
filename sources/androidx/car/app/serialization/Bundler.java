package androidx.car.app.serialization;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import androidx.car.app.utils.LogTags;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.os.EnvironmentCompat;
import io.ktor.http.ContentDisposition;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlinx.serialization.json.internal.TreeJsonEncoderKt;
import org.fusesource.jansi.AnsiRenderer;

public final class Bundler {
    private static final Map<Integer, String> BUNDLED_TYPE_NAMES = initBundledTypeNames();
    private static final int CLASS = 8;
    private static final int ENUM = 7;
    private static final int IBINDER = 9;
    static final String ICON_COMPAT_ANDROIDX = "androidx.core.graphics.drawable.IconCompat";
    static final String ICON_COMPAT_SUPPORT = "android.support.v4.graphics.drawable.IconCompat";
    private static final int IINTERFACE = 1;
    private static final int IMAGE = 6;
    private static final int LIST = 4;
    private static final int MAP = 2;
    private static final int MAX_VALUE_LOG_LENGTH = 32;
    private static final int OBJECT = 5;
    private static final int PRIMITIVE = 0;
    private static final boolean REDACT_LOG_VALUES = true;
    private static final int SET = 3;
    private static final String TAG_1 = "tag_1";
    private static final String TAG_2 = "tag_2";
    private static final String TAG_CLASS_NAME = "tag_class_name";
    private static final String TAG_CLASS_TYPE = "tag_class_type";
    private static final String TAG_VALUE = "tag_value";
    private static final Map<Class<?>, String> UNOBFUSCATED_TYPE_NAMES = initUnobfuscatedTypeNames();

    public static Bundle toBundle(Object obj) throws BundlerException {
        String unobfuscatedClassName = getUnobfuscatedClassName(obj.getClass());
        if (Log.isLoggable(LogTags.TAG_BUNDLER, 3)) {
            Log.d(LogTags.TAG_BUNDLER, "Bundling " + unobfuscatedClassName);
        }
        return toBundle(obj, unobfuscatedClassName, Trace.create());
    }

    private static Bundle toBundle(Object obj, String str, Trace trace) throws BundlerException {
        if (obj == null || !trace.find(obj)) {
            Trace fromParent = Trace.fromParent(obj, str, trace);
            if (obj != null) {
                try {
                    if (obj instanceof IconCompat) {
                        Bundle serializeImage = serializeImage((IconCompat) obj);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeImage;
                    }
                    if (!isPrimitiveType(obj)) {
                        if (!(obj instanceof Parcelable)) {
                            if (obj instanceof IInterface) {
                                Bundle serializeIInterface = serializeIInterface((IInterface) obj);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeIInterface;
                            } else if (obj instanceof IBinder) {
                                Bundle serializeIBinder = serializeIBinder((IBinder) obj);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeIBinder;
                            } else if (obj instanceof Map) {
                                Bundle serializeMap = serializeMap((Map) obj, fromParent);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeMap;
                            } else if (obj instanceof List) {
                                Bundle serializeList = serializeList((List) obj, fromParent);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeList;
                            } else if (obj instanceof Set) {
                                Bundle serializeSet = serializeSet((Set) obj, fromParent);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeSet;
                            } else if (obj.getClass().isEnum()) {
                                Bundle serializeEnum = serializeEnum(obj, fromParent);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeEnum;
                            } else if (obj instanceof Class) {
                                Bundle serializeClass = serializeClass((Class) obj);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeClass;
                            } else if (!obj.getClass().isArray()) {
                                Bundle serializeObject = serializeObject(obj, fromParent);
                                if (fromParent != null) {
                                    fromParent.close();
                                }
                                return serializeObject;
                            } else {
                                throw new TracedBundlerException("Object serializing contains an array, use a list or a set instead", fromParent);
                            }
                        }
                    }
                    Bundle serializePrimitive = serializePrimitive(obj, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return serializePrimitive;
                } catch (Throwable th) {
                    UInt$$ExternalSyntheticBackport0.m(th, th);
                }
            } else {
                throw new TracedBundlerException("Bundling of null object is not supported", fromParent);
            }
        } else {
            throw new CycleDetectedBundlerException("Found cycle while bundling type " + obj.getClass().getSimpleName(), trace);
        }
        throw th;
    }

    public static Object fromBundle(Bundle bundle) throws BundlerException {
        if (Log.isLoggable(LogTags.TAG_BUNDLER, 3)) {
            Log.d(LogTags.TAG_BUNDLER, "Unbundling " + getBundledTypeName(bundle.getInt(TAG_CLASS_TYPE)));
        }
        return fromBundle(bundle, Trace.create());
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x009d A[SYNTHETIC, Splitter:B:58:0x009d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object fromBundle(android.os.Bundle r3, androidx.car.app.serialization.Bundler.Trace r4) throws androidx.car.app.serialization.BundlerException {
        /*
            java.lang.String r0 = "Unsupported class type in bundle: "
            java.lang.Class<androidx.car.app.serialization.Bundler> r1 = androidx.car.app.serialization.Bundler.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
            java.lang.ClassLoader r1 = (java.lang.ClassLoader) r1
            r3.setClassLoader(r1)
            java.lang.String r1 = "tag_class_type"
            int r1 = r3.getInt(r1)
            java.lang.String r2 = androidx.car.app.serialization.Bundler.Trace.bundleToString(r3)
            androidx.car.app.serialization.Bundler$Trace r4 = androidx.car.app.serialization.Bundler.Trace.fromParent(r3, r2, r4)
            switch(r1) {
                case 0: goto L_0x007f;
                case 1: goto L_0x0075;
                case 2: goto L_0x006b;
                case 3: goto L_0x0061;
                case 4: goto L_0x0057;
                case 5: goto L_0x004d;
                case 6: goto L_0x0043;
                case 7: goto L_0x0039;
                case 8: goto L_0x002f;
                case 9: goto L_0x0025;
                default: goto L_0x0022;
            }
        L_0x0022:
            androidx.car.app.serialization.Bundler$TracedBundlerException r3 = new androidx.car.app.serialization.Bundler$TracedBundlerException     // Catch:{ all -> 0x0089 }
            goto L_0x008b
        L_0x0025:
            java.lang.Object r3 = deserializeIBinder(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x002e
            r4.close()
        L_0x002e:
            return r3
        L_0x002f:
            java.lang.Object r3 = deserializeClass(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0038
            r4.close()
        L_0x0038:
            return r3
        L_0x0039:
            java.lang.Object r3 = deserializeEnum(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0042
            r4.close()
        L_0x0042:
            return r3
        L_0x0043:
            java.lang.Object r3 = deserializeImage(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x004c
            r4.close()
        L_0x004c:
            return r3
        L_0x004d:
            java.lang.Object r3 = deserializeObject(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0056
            r4.close()
        L_0x0056:
            return r3
        L_0x0057:
            java.lang.Object r3 = deserializeList(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0060
            r4.close()
        L_0x0060:
            return r3
        L_0x0061:
            java.lang.Object r3 = deserializeSet(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x006a
            r4.close()
        L_0x006a:
            return r3
        L_0x006b:
            java.lang.Object r3 = deserializeMap(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0074
            r4.close()
        L_0x0074:
            return r3
        L_0x0075:
            java.lang.Object r3 = deserializeIInterface(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x007e
            r4.close()
        L_0x007e:
            return r3
        L_0x007f:
            java.lang.Object r3 = deserializePrimitive(r3, r4)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0088
            r4.close()
        L_0x0088:
            return r3
        L_0x0089:
            r3 = move-exception
            goto L_0x009b
        L_0x008b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r2.<init>(r0)     // Catch:{ all -> 0x0089 }
            r2.append(r1)     // Catch:{ all -> 0x0089 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0089 }
            r3.<init>(r0, r4)     // Catch:{ all -> 0x0089 }
            throw r3     // Catch:{ all -> 0x0089 }
        L_0x009b:
            if (r4 == 0) goto L_0x00a5
            r4.close()     // Catch:{ all -> 0x00a1 }
            goto L_0x00a5
        L_0x00a1:
            r4 = move-exception
            kotlin.UInt$$ExternalSyntheticBackport0.m((java.lang.Throwable) r3, (java.lang.Throwable) r4)
        L_0x00a5:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.serialization.Bundler.fromBundle(android.os.Bundle, androidx.car.app.serialization.Bundler$Trace):java.lang.Object");
    }

    private static Bundle serializePrimitive(Object obj, Trace trace) throws BundlerException {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 0);
        if (obj instanceof Boolean) {
            bundle.putBoolean(TAG_VALUE, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Byte) {
            bundle.putByte(TAG_VALUE, ((Byte) obj).byteValue());
        } else if (obj instanceof Character) {
            bundle.putChar(TAG_VALUE, ((Character) obj).charValue());
        } else if (obj instanceof Short) {
            bundle.putShort(TAG_VALUE, ((Short) obj).shortValue());
        } else if (obj instanceof Integer) {
            bundle.putInt(TAG_VALUE, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            bundle.putLong(TAG_VALUE, ((Long) obj).longValue());
        } else if (obj instanceof Double) {
            bundle.putDouble(TAG_VALUE, ((Double) obj).doubleValue());
        } else if (obj instanceof Float) {
            bundle.putFloat(TAG_VALUE, ((Float) obj).floatValue());
        } else if (obj instanceof String) {
            bundle.putString(TAG_VALUE, (String) obj);
        } else if (obj instanceof Parcelable) {
            bundle.putParcelable(TAG_VALUE, (Parcelable) obj);
        } else {
            throw new TracedBundlerException("Unsupported primitive type: " + obj.getClass().getName(), trace);
        }
        return bundle;
    }

    private static Bundle serializeIInterface(IInterface iInterface) {
        Bundle bundle = new Bundle(3);
        String name = iInterface.getClass().getName();
        bundle.putInt(TAG_CLASS_TYPE, 1);
        bundle.putBinder(TAG_VALUE, iInterface.asBinder());
        bundle.putString(TAG_CLASS_NAME, name);
        return bundle;
    }

    private static Bundle serializeIBinder(IBinder iBinder) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 9);
        bundle.putBinder(TAG_VALUE, iBinder);
        return bundle;
    }

    private static Bundle serializeMap(Map<Object, Object> map, Trace trace) throws BundlerException {
        Bundle bundle = new Bundle(2);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            Bundle bundle2 = new Bundle(2);
            Object key = next.getKey();
            bundle2.putBundle(TAG_1, toBundle(key, "<key " + i + ">", trace));
            if (next.getValue() != null) {
                Object value = next.getValue();
                bundle2.putBundle(TAG_2, toBundle(value, "<value " + i + ">", trace));
            }
            i++;
            arrayList.add(bundle2);
        }
        bundle.putInt(TAG_CLASS_TYPE, 2);
        bundle.putParcelableArrayList(TAG_VALUE, arrayList);
        return bundle;
    }

    private static Bundle serializeList(List<Object> list, Trace trace) throws BundlerException {
        Bundle serializeCollection = serializeCollection(list, trace);
        serializeCollection.putInt(TAG_CLASS_TYPE, 4);
        return serializeCollection;
    }

    private static Bundle serializeSet(Set<Object> set, Trace trace) throws BundlerException {
        Bundle serializeCollection = serializeCollection(set, trace);
        serializeCollection.putInt(TAG_CLASS_TYPE, 3);
        return serializeCollection;
    }

    private static Bundle serializeCollection(Collection<Object> collection, Trace trace) throws BundlerException {
        Bundle bundle = new Bundle(2);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object next : collection) {
            arrayList.add(toBundle(next, "<item " + i + ">", trace));
            i++;
        }
        bundle.putParcelableArrayList(TAG_VALUE, arrayList);
        return bundle;
    }

    private static Bundle serializeEnum(Object obj, Trace trace) throws BundlerException {
        Bundle bundle = new Bundle(3);
        bundle.putInt(TAG_CLASS_TYPE, 7);
        try {
            bundle.putString(TAG_VALUE, (String) getClassOrSuperclassMethod(obj.getClass(), ContentDisposition.Parameters.Name, trace).invoke(obj, (Object[]) null));
            bundle.putString(TAG_CLASS_NAME, obj.getClass().getName());
            return bundle;
        } catch (ReflectiveOperationException e) {
            throw new TracedBundlerException("Enum missing name method", trace, e);
        }
    }

    private static Bundle serializeClass(Class<?> cls) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 8);
        bundle.putString(TAG_VALUE, cls.getName());
        return bundle;
    }

    private static Bundle serializeImage(IconCompat iconCompat) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 6);
        bundle.putBundle(TAG_VALUE, iconCompat.toBundle());
        return bundle;
    }

    private static Bundle serializeObject(Object obj, Trace trace) throws BundlerException {
        String name = obj.getClass().getName();
        try {
            obj.getClass().getDeclaredConstructor((Class[]) null);
            List<Field> fields = getFields(obj.getClass());
            Bundle bundle = new Bundle(fields.size() + 2);
            bundle.putInt(TAG_CLASS_TYPE, 5);
            bundle.putString(TAG_CLASS_NAME, name);
            for (Field next : fields) {
                next.setAccessible(true);
                String fieldName = getFieldName(next);
                try {
                    Object obj2 = next.get(obj);
                    if (obj2 != null) {
                        bundle.putParcelable(fieldName, toBundle(obj2, next.getName(), trace));
                    }
                } catch (IllegalAccessException e) {
                    throw new TracedBundlerException("Field is not accessible: " + fieldName, trace, e);
                }
            }
            return bundle;
        } catch (NoSuchMethodException e2) {
            throw new TracedBundlerException("Class to deserialize is missing a no args constructor: " + name, trace, e2);
        }
    }

    private static Object deserializePrimitive(Bundle bundle, Trace trace) throws BundlerException {
        Object obj = bundle.get(TAG_VALUE);
        if (obj != null) {
            return obj;
        }
        throw new TracedBundlerException("Bundle is missing the primitive value", trace);
    }

    private static Object deserializeIInterface(Bundle bundle, Trace trace) throws BundlerException {
        IBinder m = bundle.getBinder(TAG_VALUE);
        if (m != null) {
            String string = bundle.getString(TAG_CLASS_NAME);
            if (string != null) {
                try {
                    Object invoke = getClassOrSuperclassMethod(Class.forName(string), "asInterface", trace).invoke((Object) null, new Object[]{m});
                    if (invoke != null) {
                        return invoke;
                    }
                    throw new TracedBundlerException("Failed to get interface from binder", trace);
                } catch (ClassNotFoundException e) {
                    throw new TracedBundlerException("Binder for unknown IInterface: " + string, trace, e);
                } catch (ReflectiveOperationException e2) {
                    throw new TracedBundlerException("Method to create IInterface from a Binder is not accessible for interface: " + string, trace, e2);
                }
            } else {
                throw new TracedBundlerException("Bundle is missing IInterface class name", trace);
            }
        } else {
            throw new TracedBundlerException("Bundle is missing the binder", trace);
        }
    }

    private static Object deserializeIBinder(Bundle bundle, Trace trace) throws BundlerException {
        IBinder m = bundle.getBinder(TAG_VALUE);
        if (m != null) {
            return m;
        }
        throw new TracedBundlerException("Bundle is missing the binder", trace);
    }

    private static Object deserializeMap(Bundle bundle, Trace trace) throws BundlerException {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(TAG_VALUE);
        if (parcelableArrayList != null) {
            HashMap hashMap = new HashMap();
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                Bundle bundle2 = (Bundle) ((Parcelable) it.next());
                Bundle bundle3 = bundle2.getBundle(TAG_1);
                Bundle bundle4 = bundle2.getBundle(TAG_2);
                if (bundle3 != null) {
                    hashMap.put(fromBundle(bundle3, trace), bundle4 == null ? null : fromBundle(bundle4, trace));
                } else {
                    throw new TracedBundlerException("Bundle is missing key", trace);
                }
            }
            return hashMap;
        }
        throw new TracedBundlerException("Bundle is missing the map", trace);
    }

    private static Object deserializeSet(Bundle bundle, Trace trace) throws BundlerException {
        return deserializeCollection(bundle, new HashSet(), trace);
    }

    private static Object deserializeList(Bundle bundle, Trace trace) throws BundlerException {
        return deserializeCollection(bundle, new ArrayList(), trace);
    }

    private static Object deserializeCollection(Bundle bundle, Collection<Object> collection, Trace trace) throws BundlerException {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(TAG_VALUE);
        if (parcelableArrayList != null) {
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                collection.add(fromBundle((Bundle) ((Parcelable) it.next()), trace));
            }
            return collection;
        }
        throw new TracedBundlerException("Bundle is missing the collection", trace);
    }

    private static Object deserializeEnum(Bundle bundle, Trace trace) throws BundlerException {
        String string = bundle.getString(TAG_VALUE);
        if (string != null) {
            String string2 = bundle.getString(TAG_CLASS_NAME);
            if (string2 != null) {
                try {
                    return getClassOrSuperclassMethod(Class.forName(string2), "valueOf", trace).invoke((Object) null, new Object[]{string});
                } catch (IllegalArgumentException e) {
                    throw new TracedBundlerException("Enum value [" + string + "] does not exist in enum class [" + string2 + "]", trace, e);
                } catch (ClassNotFoundException e2) {
                    throw new TracedBundlerException("Enum class [" + string2 + "] not found", trace, e2);
                } catch (ReflectiveOperationException e3) {
                    throw new TracedBundlerException("Enum of class [" + string2 + "] missing valueOf method", trace, e3);
                }
            } else {
                throw new TracedBundlerException("Missing enum className [" + string2 + "]", trace);
            }
        } else {
            throw new TracedBundlerException("Missing enum name [" + string + "]", trace);
        }
    }

    private static Object deserializeClass(Bundle bundle, Trace trace) throws BundlerException {
        String string = bundle.getString(TAG_VALUE);
        if (string != null) {
            try {
                return Class.forName(string);
            } catch (ClassNotFoundException e) {
                throw new TracedBundlerException("Class name is unknown: " + string, trace, e);
            }
        } else {
            throw new TracedBundlerException("Class is missing the class name", trace);
        }
    }

    private static Object deserializeImage(Bundle bundle, Trace trace) throws BundlerException {
        Bundle bundle2 = bundle.getBundle(TAG_VALUE);
        if (bundle2 != null) {
            IconCompat createFromBundle = IconCompat.createFromBundle(bundle2);
            if (createFromBundle != null) {
                return createFromBundle;
            }
            throw new TracedBundlerException("Failed to create IconCompat from bundle", trace);
        }
        throw new TracedBundlerException("IconCompat bundle is null", trace);
    }

    private static Object deserializeObject(Bundle bundle, Trace trace) throws BundlerException {
        String string = bundle.getString(TAG_CLASS_NAME);
        if (string != null) {
            try {
                Class<?> cls = Class.forName(string);
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
                declaredConstructor.setAccessible(true);
                Object newInstance = declaredConstructor.newInstance((Object[]) null);
                for (Field next : getFields(cls)) {
                    next.setAccessible(true);
                    String fieldName = getFieldName(next);
                    Object obj = bundle.get(fieldName);
                    if (obj == null) {
                        obj = bundle.get(fieldName.replaceAll(ICON_COMPAT_ANDROIDX, ICON_COMPAT_SUPPORT));
                    }
                    if (obj instanceof Bundle) {
                        next.set(newInstance, fromBundle((Bundle) obj, trace));
                    } else if (obj == null && Log.isLoggable(LogTags.TAG_BUNDLER, 3)) {
                        Log.d(LogTags.TAG_BUNDLER, "Value is null for field: " + next);
                    }
                }
                return newInstance;
            } catch (ClassNotFoundException e) {
                throw new TracedBundlerException("Object for unknown class: " + string, trace, e);
            } catch (NoSuchMethodException e2) {
                throw new TracedBundlerException("Object missing no args constructor: " + string, trace, e2);
            } catch (ReflectiveOperationException e3) {
                throw new TracedBundlerException("Constructor or field is not accessible: " + string, trace, e3);
            } catch (IllegalArgumentException e4) {
                throw new TracedBundlerException("Failed to deserialize class: " + string, trace, e4);
            }
        } else {
            throw new TracedBundlerException("Bundle is missing the class name", trace);
        }
    }

    static String getFieldName(Field field) {
        return getFieldName(field.getDeclaringClass().getName(), field.getName());
    }

    static String getFieldName(String str, String str2) {
        return str + str2;
    }

    private static List<Field> getFields(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        if (!(cls == null || cls == Object.class)) {
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    arrayList.add(field);
                }
            }
            arrayList.addAll(getFields(cls.getSuperclass()));
        }
        return arrayList;
    }

    private static Method getClassOrSuperclassMethod(Class<?> cls, String str, Trace trace) throws TracedBundlerException {
        if (cls == null || cls == Object.class) {
            throw new TracedBundlerException("No method " + str + " in class " + cls, trace);
        }
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getName().equals(str)) {
                method.setAccessible(true);
                return method;
            }
        }
        return getClassOrSuperclassMethod(cls.getSuperclass(), str, trace);
    }

    static String getUnobfuscatedClassName(Class<?> cls) {
        String str = UNOBFUSCATED_TYPE_NAMES.get(cls);
        if (str == null) {
            if (List.class.isAssignableFrom(cls)) {
                return "<List>";
            }
            if (Map.class.isAssignableFrom(cls)) {
                return "<Map>";
            }
            if (Set.class.isAssignableFrom(cls)) {
                return "<Set>";
            }
        }
        return str == null ? cls.getSimpleName() : str;
    }

    static String getBundledTypeName(int i) {
        String str = BUNDLED_TYPE_NAMES.get(Integer.valueOf(i));
        return str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
    }

    private static Map<Integer, String> initBundledTypeNames() {
        ArrayMap arrayMap = new ArrayMap();
        Object unused = arrayMap.put(0, TreeJsonEncoderKt.PRIMITIVE_TAG);
        Object unused2 = arrayMap.put(1, "iInterface");
        Object unused3 = arrayMap.put(9, "iBinder");
        Object unused4 = arrayMap.put(2, "map");
        Object unused5 = arrayMap.put(3, "set");
        Object unused6 = arrayMap.put(4, "list");
        Object unused7 = arrayMap.put(5, "object");
        Object unused8 = arrayMap.put(6, "image");
        return arrayMap;
    }

    private static Map<Class<?>, String> initUnobfuscatedTypeNames() {
        ArrayMap arrayMap = new ArrayMap();
        Object unused = arrayMap.put(Boolean.class, "bool");
        Object unused2 = arrayMap.put(Byte.class, "byte");
        Object unused3 = arrayMap.put(Short.class, "short");
        Object unused4 = arrayMap.put(Integer.class, "int");
        Object unused5 = arrayMap.put(Long.class, "long");
        Object unused6 = arrayMap.put(Double.class, "double");
        Object unused7 = arrayMap.put(Float.class, TypedValues.Custom.S_FLOAT);
        Object unused8 = arrayMap.put(String.class, TypedValues.Custom.S_STRING);
        Object unused9 = arrayMap.put(Parcelable.class, "parcelable");
        Object unused10 = arrayMap.put(Map.class, "map");
        Object unused11 = arrayMap.put(List.class, "list");
        Object unused12 = arrayMap.put(IconCompat.class, "image");
        return arrayMap;
    }

    static String ellipsize(String str) {
        if (str.length() < 32) {
            return str;
        }
        return str.substring(0, 31) + "...";
    }

    static boolean isPrimitiveType(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof String);
    }

    private static class Frame {
        private final String mDisplay;
        private final Object mObj;

        Frame(Object obj, String str) {
            this.mObj = obj;
            this.mDisplay = str;
        }

        public Object getObj() {
            return this.mObj;
        }

        public String toString() {
            return toFlatString();
        }

        /* access modifiers changed from: package-private */
        public String toFlatString() {
            return "[" + this.mDisplay + ", " + Bundler.getUnobfuscatedClassName(this.mObj.getClass()) + "]";
        }

        /* access modifiers changed from: package-private */
        public String toTraceString() {
            return Bundler.getUnobfuscatedClassName(this.mObj.getClass()) + AnsiRenderer.CODE_TEXT_SEPARATOR + this.mDisplay;
        }
    }

    private static class Trace implements AutoCloseable {
        private static final int MAX_FLAT_FRAMES = 8;
        private static final int MAX_LOG_INDENT = 12;
        private final ArrayDeque<Frame> mFrames;
        private String[] mIndents;

        static Trace create() {
            return new Trace((Object) null, "", new ArrayDeque());
        }

        static Trace fromParent(Object obj, String str, Trace trace) {
            return new Trace(obj, str, trace.mFrames);
        }

        static String bundleToString(Bundle bundle) {
            return Bundler.getBundledTypeName(bundle.getInt(Bundler.TAG_CLASS_TYPE));
        }

        public void close() {
            this.mFrames.removeFirst();
        }

        /* access modifiers changed from: package-private */
        public boolean find(Object obj) {
            Iterator<Frame> it = this.mFrames.iterator();
            while (it.hasNext()) {
                if (it.next().getObj() == obj) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public String toFlatString() {
            StringBuilder sb = new StringBuilder();
            int min = Math.min(this.mFrames.size(), 8);
            Iterator<Frame> descendingIterator = this.mFrames.descendingIterator();
            while (descendingIterator.hasNext()) {
                int i = min - 1;
                if (min <= 0) {
                    break;
                }
                sb.append(descendingIterator.next().toFlatString());
                min = i;
            }
            if (descendingIterator.hasNext()) {
                sb.append("[...]");
            }
            return sb.toString();
        }

        private String getIndent(int i) {
            int min = Math.min(i, 11);
            if (this.mIndents == null) {
                this.mIndents = new String[12];
            }
            String str = this.mIndents[min];
            if (str == null) {
                str = repeatChar(' ', min);
                if (min == 11) {
                    str = str + "...";
                }
                this.mIndents[min] = str;
            }
            return str;
        }

        private static String repeatChar(char c, int i) {
            char[] cArr = new char[i];
            Arrays.fill(cArr, c);
            return new String(cArr);
        }

        private Trace(Object obj, String str, ArrayDeque<Frame> arrayDeque) {
            this.mFrames = arrayDeque;
            if (obj != null) {
                Frame frame = new Frame(obj, str);
                arrayDeque.addFirst(frame);
                if (Log.isLoggable(LogTags.TAG_BUNDLER, 2)) {
                    Log.v(LogTags.TAG_BUNDLER, getIndent(arrayDeque.size()) + frame.toTraceString());
                }
            }
        }
    }

    static class TracedBundlerException extends BundlerException {
        TracedBundlerException(String str, Trace trace) {
            super(str + ", frames: " + trace.toFlatString());
        }

        TracedBundlerException(String str, Trace trace, Throwable th) {
            super(str + ", frames: " + trace.toFlatString(), th);
        }
    }

    static class CycleDetectedBundlerException extends TracedBundlerException {
        CycleDetectedBundlerException(String str, Trace trace) {
            super(str, trace);
        }
    }

    private Bundler() {
    }
}
