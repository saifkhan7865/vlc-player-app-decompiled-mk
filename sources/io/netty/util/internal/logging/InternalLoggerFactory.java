package io.netty.util.internal.logging;

import io.netty.util.internal.ObjectUtil;

public abstract class InternalLoggerFactory {
    private static volatile InternalLoggerFactory defaultFactory;

    /* access modifiers changed from: protected */
    public abstract InternalLogger newInstance(String str);

    private static InternalLoggerFactory newDefaultFactory(String str) {
        InternalLoggerFactory useSlf4JLoggerFactory = useSlf4JLoggerFactory(str);
        if (useSlf4JLoggerFactory != null) {
            return useSlf4JLoggerFactory;
        }
        InternalLoggerFactory useLog4J2LoggerFactory = useLog4J2LoggerFactory(str);
        if (useLog4J2LoggerFactory != null) {
            return useLog4J2LoggerFactory;
        }
        InternalLoggerFactory useLog4JLoggerFactory = useLog4JLoggerFactory(str);
        if (useLog4JLoggerFactory != null) {
            return useLog4JLoggerFactory;
        }
        return useJdkLoggerFactory(str);
    }

    private static InternalLoggerFactory useSlf4JLoggerFactory(String str) {
        try {
            InternalLoggerFactory instanceWithNopCheck = Slf4JLoggerFactory.getInstanceWithNopCheck();
            instanceWithNopCheck.newInstance(str).debug("Using SLF4J as the default logging framework");
            return instanceWithNopCheck;
        } catch (Exception | LinkageError unused) {
            return null;
        }
    }

    private static InternalLoggerFactory useLog4J2LoggerFactory(String str) {
        try {
            InternalLoggerFactory internalLoggerFactory = Log4J2LoggerFactory.INSTANCE;
            internalLoggerFactory.newInstance(str).debug("Using Log4J2 as the default logging framework");
            return internalLoggerFactory;
        } catch (Exception | LinkageError unused) {
            return null;
        }
    }

    private static InternalLoggerFactory useLog4JLoggerFactory(String str) {
        try {
            InternalLoggerFactory internalLoggerFactory = Log4JLoggerFactory.INSTANCE;
            internalLoggerFactory.newInstance(str).debug("Using Log4J as the default logging framework");
            return internalLoggerFactory;
        } catch (Exception | LinkageError unused) {
            return null;
        }
    }

    private static InternalLoggerFactory useJdkLoggerFactory(String str) {
        InternalLoggerFactory internalLoggerFactory = JdkLoggerFactory.INSTANCE;
        internalLoggerFactory.newInstance(str).debug("Using java.util.logging as the default logging framework");
        return internalLoggerFactory;
    }

    public static InternalLoggerFactory getDefaultFactory() {
        if (defaultFactory == null) {
            defaultFactory = newDefaultFactory(InternalLoggerFactory.class.getName());
        }
        return defaultFactory;
    }

    public static void setDefaultFactory(InternalLoggerFactory internalLoggerFactory) {
        defaultFactory = (InternalLoggerFactory) ObjectUtil.checkNotNull(internalLoggerFactory, "defaultFactory");
    }

    public static InternalLogger getInstance(Class<?> cls) {
        return getInstance(cls.getName());
    }

    public static InternalLogger getInstance(String str) {
        return getDefaultFactory().newInstance(str);
    }
}
