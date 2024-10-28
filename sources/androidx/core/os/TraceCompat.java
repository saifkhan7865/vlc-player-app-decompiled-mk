package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

@Deprecated
public final class TraceCompat {
    private static final String TAG = "TraceCompat";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    static {
        if (Build.VERSION.SDK_INT >= 18 && Build.VERSION.SDK_INT < 29) {
            try {
                sTraceTagApp = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getField("TRACE_TAG_APP").getLong((Object) null);
                sIsTagEnabledMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("isTagEnabled", new Class[]{Long.TYPE});
                sAsyncTraceBeginMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("asyncTraceBegin", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                sAsyncTraceEndMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("asyncTraceEnd", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                sTraceCounterMethod = BundleKt$$ExternalSyntheticApiModelOutline0.m$1().getMethod("traceCounter", new Class[]{Long.TYPE, String.class, Integer.TYPE});
            } catch (Exception e) {
                Log.i(TAG, "Unable to initialize via reflection.", e);
            }
        }
    }

    public static boolean isEnabled() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.isEnabled();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                return ((Boolean) sIsTagEnabledMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp)})).booleanValue();
            } catch (Exception unused) {
                Log.v(TAG, "Unable to invoke isTagEnabled() via reflection.");
            }
        }
        return false;
    }

    public static void beginSection(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Api18Impl.beginSection(str);
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            Api18Impl.endSection();
        }
    }

    public static void beginAsyncSection(String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.beginAsyncSection(str, i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            try {
                sAsyncTraceBeginMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp), str, Integer.valueOf(i)});
            } catch (Exception unused) {
                Log.v(TAG, "Unable to invoke asyncTraceBegin() via reflection.");
            }
        }
    }

    public static void endAsyncSection(String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.endAsyncSection(str, i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            try {
                sAsyncTraceEndMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp), str, Integer.valueOf(i)});
            } catch (Exception unused) {
                Log.v(TAG, "Unable to invoke endAsyncSection() via reflection.");
            }
        }
    }

    public static void setCounter(String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setCounter(str, (long) i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            try {
                sTraceCounterMethod.invoke((Object) null, new Object[]{Long.valueOf(sTraceTagApp), str, Integer.valueOf(i)});
            } catch (Exception unused) {
                Log.v(TAG, "Unable to invoke traceCounter() via reflection.");
            }
        }
    }

    private TraceCompat() {
    }

    static class Api29Impl {
        private Api29Impl() {
        }

        static boolean isEnabled() {
            return Trace.isEnabled();
        }

        static void endAsyncSection(String str, int i) {
            Trace.endAsyncSection(str, i);
        }

        static void beginAsyncSection(String str, int i) {
            Trace.beginAsyncSection(str, i);
        }

        static void setCounter(String str, long j) {
            Trace.setCounter(str, j);
        }
    }

    static class Api18Impl {
        private Api18Impl() {
        }

        static void beginSection(String str) {
            Trace.beginSection(str);
        }

        static void endSection() {
            Trace.endSection();
        }
    }
}
