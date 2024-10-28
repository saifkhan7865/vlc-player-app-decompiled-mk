package io.netty.util.internal;

final class NativeLibraryUtil {
    public static void loadLibrary(String str, boolean z) {
        if (z) {
            System.load(str);
        } else {
            System.loadLibrary(str);
        }
    }

    private NativeLibraryUtil() {
    }
}