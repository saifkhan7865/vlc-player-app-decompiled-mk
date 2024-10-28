package org.bouncycastle.tsp.ers;

public class ERSException extends Exception {
    private final Throwable cause;

    public ERSException(String str) {
        this(str, (Throwable) null);
    }

    public ERSException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
