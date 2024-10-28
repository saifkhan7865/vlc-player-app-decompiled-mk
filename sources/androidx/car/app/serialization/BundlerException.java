package androidx.car.app.serialization;

public class BundlerException extends Exception {
    public BundlerException(String str, Throwable th) {
        super(str, th);
    }

    public BundlerException(String str) {
        super(str);
    }
}
