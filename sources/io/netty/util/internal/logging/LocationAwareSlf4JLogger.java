package io.netty.util.internal.logging;

import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

final class LocationAwareSlf4JLogger extends AbstractInternalLogger {
    static final String FQCN = "io.netty.util.internal.logging.LocationAwareSlf4JLogger";
    private static final long serialVersionUID = -8292030083201538180L;
    private final transient LocationAwareLogger logger;

    LocationAwareSlf4JLogger(LocationAwareLogger locationAwareLogger) {
        super(locationAwareLogger.getName());
        this.logger = locationAwareLogger;
    }

    private void log(int i, String str) {
        this.logger.log((Marker) null, FQCN, i, str, (Object[]) null, (Throwable) null);
    }

    private void log(int i, String str, Throwable th) {
        this.logger.log((Marker) null, FQCN, i, str, (Object[]) null, th);
    }

    private void log(int i, FormattingTuple formattingTuple) {
        this.logger.log((Marker) null, FQCN, i, formattingTuple.getMessage(), formattingTuple.getArgArray(), formattingTuple.getThrowable());
    }

    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    public void trace(String str) {
        if (isTraceEnabled()) {
            log(0, str);
        }
    }

    public void trace(String str, Object obj) {
        if (isTraceEnabled()) {
            log(0, MessageFormatter.format(str, obj));
        }
    }

    public void trace(String str, Object obj, Object obj2) {
        if (isTraceEnabled()) {
            log(0, MessageFormatter.format(str, obj, obj2));
        }
    }

    public void trace(String str, Object... objArr) {
        if (isTraceEnabled()) {
            log(0, MessageFormatter.arrayFormat(str, objArr));
        }
    }

    public void trace(String str, Throwable th) {
        if (isTraceEnabled()) {
            log(0, str, th);
        }
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public void debug(String str) {
        if (isDebugEnabled()) {
            log(10, str);
        }
    }

    public void debug(String str, Object obj) {
        if (isDebugEnabled()) {
            log(10, MessageFormatter.format(str, obj));
        }
    }

    public void debug(String str, Object obj, Object obj2) {
        if (isDebugEnabled()) {
            log(10, MessageFormatter.format(str, obj, obj2));
        }
    }

    public void debug(String str, Object... objArr) {
        if (isDebugEnabled()) {
            log(10, MessageFormatter.arrayFormat(str, objArr));
        }
    }

    public void debug(String str, Throwable th) {
        if (isDebugEnabled()) {
            log(10, str, th);
        }
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public void info(String str) {
        if (isInfoEnabled()) {
            log(20, str);
        }
    }

    public void info(String str, Object obj) {
        if (isInfoEnabled()) {
            log(20, MessageFormatter.format(str, obj));
        }
    }

    public void info(String str, Object obj, Object obj2) {
        if (isInfoEnabled()) {
            log(20, MessageFormatter.format(str, obj, obj2));
        }
    }

    public void info(String str, Object... objArr) {
        if (isInfoEnabled()) {
            log(20, MessageFormatter.arrayFormat(str, objArr));
        }
    }

    public void info(String str, Throwable th) {
        if (isInfoEnabled()) {
            log(20, str, th);
        }
    }

    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    public void warn(String str) {
        if (isWarnEnabled()) {
            log(30, str);
        }
    }

    public void warn(String str, Object obj) {
        if (isWarnEnabled()) {
            log(30, MessageFormatter.format(str, obj));
        }
    }

    public void warn(String str, Object... objArr) {
        if (isWarnEnabled()) {
            log(30, MessageFormatter.arrayFormat(str, objArr));
        }
    }

    public void warn(String str, Object obj, Object obj2) {
        if (isWarnEnabled()) {
            log(30, MessageFormatter.format(str, obj, obj2));
        }
    }

    public void warn(String str, Throwable th) {
        if (isWarnEnabled()) {
            log(30, str, th);
        }
    }

    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    public void error(String str) {
        if (isErrorEnabled()) {
            log(40, str);
        }
    }

    public void error(String str, Object obj) {
        if (isErrorEnabled()) {
            log(40, MessageFormatter.format(str, obj));
        }
    }

    public void error(String str, Object obj, Object obj2) {
        if (isErrorEnabled()) {
            log(40, MessageFormatter.format(str, obj, obj2));
        }
    }

    public void error(String str, Object... objArr) {
        if (isErrorEnabled()) {
            log(40, MessageFormatter.arrayFormat(str, objArr));
        }
    }

    public void error(String str, Throwable th) {
        if (isErrorEnabled()) {
            log(40, str, th);
        }
    }
}
