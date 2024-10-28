package androidx.car.app;

public final class HostException extends RuntimeException {
    public HostException(String str) {
        super(str);
    }

    public HostException(String str, Throwable th) {
        super(str, th);
    }

    public HostException(Throwable th) {
        super(th);
    }
}
