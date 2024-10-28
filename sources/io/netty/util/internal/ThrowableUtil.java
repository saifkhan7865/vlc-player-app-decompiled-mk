package io.netty.util.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import kotlin.UInt$$ExternalSyntheticBackport0;

public final class ThrowableUtil {
    private ThrowableUtil() {
    }

    public static <T extends Throwable> T unknownStackTrace(T t, Class<?> cls, String str) {
        t.setStackTrace(new StackTraceElement[]{new StackTraceElement(cls.getName(), str, (String) null, -1)});
        return t;
    }

    public static String stackTraceToString(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        try {
            return new String(byteArrayOutputStream.toByteArray());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean haveSuppressed() {
        return PlatformDependent.javaVersion() >= 7;
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        if (haveSuppressed()) {
            UInt$$ExternalSyntheticBackport0.m(th, th2);
        }
    }

    public static void addSuppressedAndClear(Throwable th, List<Throwable> list) {
        addSuppressed(th, list);
        list.clear();
    }

    public static void addSuppressed(Throwable th, List<Throwable> list) {
        for (Throwable addSuppressed : list) {
            addSuppressed(th, addSuppressed);
        }
    }

    public static Throwable[] getSuppressed(Throwable th) {
        if (!haveSuppressed()) {
            return EmptyArrays.EMPTY_THROWABLES;
        }
        return ThrowableUtil$$ExternalSyntheticBackport0.m(th);
    }
}
