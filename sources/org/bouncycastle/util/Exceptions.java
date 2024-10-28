package org.bouncycastle.util;

import java.io.IOException;

public class Exceptions {
    public static IllegalArgumentException illegalArgumentException(String str, Throwable th) {
        return new IllegalArgumentException(str, th);
    }

    public static IllegalStateException illegalStateException(String str, Throwable th) {
        return new IllegalStateException(str, th);
    }

    public static IOException ioException(String str, Throwable th) {
        return new IOException(str, th);
    }
}
