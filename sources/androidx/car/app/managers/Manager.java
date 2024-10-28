package androidx.car.app.managers;

public interface Manager {

    /* renamed from: androidx.car.app.managers.Manager$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static <U extends Manager> U create(Class<U> cls, String str, Object... objArr) {
            try {
                Class<?> cls2 = Class.forName(str);
                Class[] clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
                return (Manager) cls.cast(cls2.getConstructor(clsArr).newInstance(objArr));
            } catch (ClassNotFoundException unused) {
                return null;
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException("Mismatch with artifact", e);
            }
        }
    }
}
