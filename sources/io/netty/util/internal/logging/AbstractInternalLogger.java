package io.netty.util.internal.logging;

import io.ktor.http.ContentDisposition;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.io.ObjectStreamException;
import java.io.Serializable;

public abstract class AbstractInternalLogger implements InternalLogger, Serializable {
    static final String EXCEPTION_MESSAGE = "Unexpected exception:";
    private static final long serialVersionUID = -6382972526573193470L;
    private final String name;

    protected AbstractInternalLogger(String str) {
        this.name = (String) ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
    }

    public String name() {
        return this.name;
    }

    /* renamed from: io.netty.util.internal.logging.AbstractInternalLogger$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$internal$logging$InternalLogLevel;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.util.internal.logging.InternalLogLevel[] r0 = io.netty.util.internal.logging.InternalLogLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel = r0
                io.netty.util.internal.logging.InternalLogLevel r1 = io.netty.util.internal.logging.InternalLogLevel.TRACE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$util$internal$logging$InternalLogLevel     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.util.internal.logging.InternalLogLevel r1 = io.netty.util.internal.logging.InternalLogLevel.DEBUG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$util$internal$logging$InternalLogLevel     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.util.internal.logging.InternalLogLevel r1 = io.netty.util.internal.logging.InternalLogLevel.INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$util$internal$logging$InternalLogLevel     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.util.internal.logging.InternalLogLevel r1 = io.netty.util.internal.logging.InternalLogLevel.WARN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$util$internal$logging$InternalLogLevel     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.util.internal.logging.InternalLogLevel r1 = io.netty.util.internal.logging.InternalLogLevel.ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.util.internal.logging.AbstractInternalLogger.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean isEnabled(InternalLogLevel internalLogLevel) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            return isTraceEnabled();
        }
        if (i == 2) {
            return isDebugEnabled();
        }
        if (i == 3) {
            return isInfoEnabled();
        }
        if (i == 4) {
            return isWarnEnabled();
        }
        if (i == 5) {
            return isErrorEnabled();
        }
        throw new Error();
    }

    public void trace(Throwable th) {
        trace(EXCEPTION_MESSAGE, th);
    }

    public void debug(Throwable th) {
        debug(EXCEPTION_MESSAGE, th);
    }

    public void info(Throwable th) {
        info(EXCEPTION_MESSAGE, th);
    }

    public void warn(Throwable th) {
        warn(EXCEPTION_MESSAGE, th);
    }

    public void error(Throwable th) {
        error(EXCEPTION_MESSAGE, th);
    }

    public void log(InternalLogLevel internalLogLevel, String str, Throwable th) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, th);
        } else if (i == 2) {
            debug(str, th);
        } else if (i == 3) {
            info(str, th);
        } else if (i == 4) {
            warn(str, th);
        } else if (i == 5) {
            error(str, th);
        } else {
            throw new Error();
        }
    }

    public void log(InternalLogLevel internalLogLevel, Throwable th) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(th);
        } else if (i == 2) {
            debug(th);
        } else if (i == 3) {
            info(th);
        } else if (i == 4) {
            warn(th);
        } else if (i == 5) {
            error(th);
        } else {
            throw new Error();
        }
    }

    public void log(InternalLogLevel internalLogLevel, String str) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str);
        } else if (i == 2) {
            debug(str);
        } else if (i == 3) {
            info(str);
        } else if (i == 4) {
            warn(str);
        } else if (i == 5) {
            error(str);
        } else {
            throw new Error();
        }
    }

    public void log(InternalLogLevel internalLogLevel, String str, Object obj) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, obj);
        } else if (i == 2) {
            debug(str, obj);
        } else if (i == 3) {
            info(str, obj);
        } else if (i == 4) {
            warn(str, obj);
        } else if (i == 5) {
            error(str, obj);
        } else {
            throw new Error();
        }
    }

    public void log(InternalLogLevel internalLogLevel, String str, Object obj, Object obj2) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, obj, obj2);
        } else if (i == 2) {
            debug(str, obj, obj2);
        } else if (i == 3) {
            info(str, obj, obj2);
        } else if (i == 4) {
            warn(str, obj, obj2);
        } else if (i == 5) {
            error(str, obj, obj2);
        } else {
            throw new Error();
        }
    }

    public void log(InternalLogLevel internalLogLevel, String str, Object... objArr) {
        int i = AnonymousClass1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, objArr);
        } else if (i == 2) {
            debug(str, objArr);
        } else if (i == 3) {
            info(str, objArr);
        } else if (i == 4) {
            warn(str, objArr);
        } else if (i == 5) {
            error(str, objArr);
        } else {
            throw new Error();
        }
    }

    /* access modifiers changed from: protected */
    public Object readResolve() throws ObjectStreamException {
        return InternalLoggerFactory.getInstance(name());
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + '(' + name() + ')';
    }
}
