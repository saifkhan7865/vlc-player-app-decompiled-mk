package io.netty.handler.codec.http.multipart;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.util.Collections;
import java.util.Set;

final class DeleteFileOnExitHook {
    private static final Set<String> FILES = Collections.newSetFromMap(new ConcurrentHashMap());

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                DeleteFileOnExitHook.runHook();
            }
        });
    }

    private DeleteFileOnExitHook() {
    }

    public static void remove(String str) {
        FILES.remove(str);
    }

    public static void add(String str) {
        FILES.add(str);
    }

    public static boolean checkFileExist(String str) {
        return FILES.contains(str);
    }

    static void runHook() {
        for (String file : FILES) {
            new File(file).delete();
        }
    }
}
