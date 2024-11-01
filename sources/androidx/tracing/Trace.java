package androidx.tracing;

import android.os.Build;
import android.util.Log;
import androidx.core.os.BundleKt$$ExternalSyntheticApiModelOutline0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Trace {
    static final String TAG = "Trace";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    public static boolean isEnabled() {
        try {
            if (sIsTagEnabledMethod == null) {
                return Trace$$ExternalSyntheticApiModelOutline0.m();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        return isEnabledFallback();
    }

    public static void beginSection(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            TraceApi18Impl.beginSection(str);
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            TraceApi18Impl.endSection();
        }
    }

    public static void beginAsyncSection(String str, int i) {
        try {
            if (sAsyncTraceBeginMethod == null) {
                TraceApi29Impl.beginAsyncSection(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        beginAsyncSectionFallback(str, i);
    }

    public static void endAsyncSection(String str, int i) {
        try {
            if (sAsyncTraceEndMethod == null) {
                TraceApi29Impl.endAsyncSection(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        endAsyncSectionFallback(str, i);
    }

    public static void setCounter(String str, int i) {
        try {
            if (sTraceCounterMethod == null) {
                TraceApi29Impl.setCounter(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        setCounterFallback(str, i);
    }

    private static boolean isEnabledFallback() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sIsTagEnabledMethod == null) {
                    sTraceTagApp = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getField("TRACE_TAG_APP").getLong((Object) null);
                    sIsTagEnabledMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("isTagEnabled", new Class[]{Long.TYPE});
                }
                return ((Boolean) sIsTagEnabledMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp)})).booleanValue();
            } catch (Exception e) {
                handleException("isTagEnabled", e);
            }
        }
        return false;
    }

    private static void beginAsyncSectionFallback(String str, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sAsyncTraceBeginMethod == null) {
                    sAsyncTraceBeginMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("asyncTraceBegin", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                }
                sAsyncTraceBeginMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp), str, Integer.valueOf(i)});
            } catch (Exception e) {
                handleException("asyncTraceBegin", e);
            }
        }
    }

    private static void endAsyncSectionFallback(String str, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sAsyncTraceEndMethod == null) {
                    sAsyncTraceEndMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("asyncTraceEnd", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                }
                sAsyncTraceEndMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp), str, Integer.valueOf(i)});
            } catch (Exception e) {
                handleException("asyncTraceEnd", e);
            }
        }
    }

    private static void setCounterFallback(String str, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sTraceCounterMethod == null) {
                    sTraceCounterMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("traceCounter", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                }
                sTraceCounterMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp), str, Integer.valueOf(i)});
            } catch (Exception e) {
                handleException("traceCounter", e);
            }
        }
    }

    private static void handleException(String str, Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
        Log.v(TAG, "Unable to call " + str + " via reflection", exc);
    }

    private Trace() {
    }
}
