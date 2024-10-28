package org.videolan.tools;

import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/tools/CloseableUtils;", "", "()V", "close", "", "closeable", "Ljava/io/Closeable;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CloseableUtils.kt */
public final class CloseableUtils {
    public static final CloseableUtils INSTANCE = new CloseableUtils();

    private CloseableUtils() {
    }

    public final boolean close(Closeable closeable) {
        if (closeable == null) {
            return false;
        }
        try {
            closeable.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
