package androidx.car.app.managers;

import java.util.HashMap;
import java.util.Map;

public class ManagerCache {
    private final Map<String, Class<?>> mClassByName = new HashMap();
    private final Map<Class<?>, RuntimeException> mExceptions = new HashMap();
    private final Map<Class<?>, ManagerFactory<? extends Manager>> mFactories = new HashMap();
    private final Map<Class<?>, String> mNameByClass = new HashMap();
    private final Map<Class<?>, Manager> mValues = new HashMap();

    public <T extends Manager> void addFactory(Class<T> cls, String str, ManagerFactory<T> managerFactory) {
        this.mFactories.put(cls, managerFactory);
        if (str != null) {
            this.mClassByName.put(str, cls);
            this.mNameByClass.put(cls, str);
        }
    }

    public String getName(Class<?> cls) {
        String str = this.mNameByClass.get(cls);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("The class does not correspond to a car service");
    }

    public Object getOrCreate(String str) throws IllegalArgumentException {
        Class cls = this.mClassByName.get(str);
        if (cls != null) {
            return getOrCreate(cls);
        }
        throw new IllegalArgumentException("The name '" + str + "' does not correspond to a car service");
    }

    public <T> T getOrCreate(Class<T> cls) {
        RuntimeException runtimeException = this.mExceptions.get(cls);
        if (runtimeException == null) {
            T t = (Manager) this.mValues.get(cls);
            if (t != null) {
                return t;
            }
            ManagerFactory managerFactory = this.mFactories.get(cls);
            if (managerFactory != null) {
                try {
                    T create = managerFactory.create();
                    this.mValues.put(cls, create);
                    return create;
                } catch (RuntimeException e) {
                    this.mExceptions.put(cls, e);
                    throw e;
                }
            } else {
                throw new IllegalArgumentException("The class '" + cls + "' does not correspond to a car service");
            }
        } else {
            throw runtimeException;
        }
    }
}
