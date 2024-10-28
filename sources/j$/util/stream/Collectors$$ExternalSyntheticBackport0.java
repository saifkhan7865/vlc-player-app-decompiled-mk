package j$.util.stream;

public abstract /* synthetic */ class Collectors$$ExternalSyntheticBackport0 {
    public static /* synthetic */ void m(Throwable th, Throwable th2) {
        Class<Throwable> cls = Throwable.class;
        try {
            cls.getDeclaredMethod("addSuppressed", new Class[]{cls}).invoke(th, new Object[]{th2});
        } catch (Exception unused) {
        }
    }
}
