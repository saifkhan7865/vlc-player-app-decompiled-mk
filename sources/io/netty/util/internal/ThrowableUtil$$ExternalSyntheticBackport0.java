package io.netty.util.internal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ThrowableUtil$$ExternalSyntheticBackport0 {
    public static /* synthetic */ Throwable[] m(Throwable th) {
        try {
            return (Throwable[]) Throwable.class.getDeclaredMethod("getSuppressed", (Class[]) null).invoke(th, (Object[]) null);
        } catch (Exception unused) {
            return new Throwable[0];
        }
    }
}
