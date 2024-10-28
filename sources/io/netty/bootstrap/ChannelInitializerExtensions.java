package io.netty.bootstrap;

import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ServiceLoader;

abstract class ChannelInitializerExtensions {
    private static volatile ChannelInitializerExtensions implementation;
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChannelInitializerExtensions.class);

    /* access modifiers changed from: package-private */
    public abstract Collection<ChannelInitializerExtension> extensions(ClassLoader classLoader);

    private ChannelInitializerExtensions() {
    }

    static ChannelInitializerExtensions getExtensions() {
        ChannelInitializerExtensions channelInitializerExtensions = implementation;
        if (channelInitializerExtensions == null) {
            synchronized (ChannelInitializerExtensions.class) {
                ChannelInitializerExtensions channelInitializerExtensions2 = implementation;
                if (channelInitializerExtensions2 != null) {
                    return channelInitializerExtensions2;
                }
                String str = SystemPropertyUtil.get(ChannelInitializerExtension.EXTENSIONS_SYSTEM_PROPERTY);
                logger.debug("-Dio.netty.bootstrap.extensions: {}", (Object) str);
                if ("serviceload".equalsIgnoreCase(str)) {
                    channelInitializerExtensions = new ServiceLoadingExtensions(InternalLogLevel.DEBUG, true);
                } else if ("log".equalsIgnoreCase(str)) {
                    channelInitializerExtensions = new ServiceLoadingExtensions(InternalLogLevel.INFO, false);
                } else {
                    channelInitializerExtensions = new EmptyExtensions();
                }
                implementation = channelInitializerExtensions;
            }
        }
        return channelInitializerExtensions;
    }

    private static final class EmptyExtensions extends ChannelInitializerExtensions {
        private EmptyExtensions() {
            super();
        }

        /* access modifiers changed from: package-private */
        public Collection<ChannelInitializerExtension> extensions(ClassLoader classLoader) {
            return Collections.emptyList();
        }
    }

    private static final class ServiceLoadingExtensions extends ChannelInitializerExtensions {
        private WeakReference<ClassLoader> classLoader;
        private Collection<ChannelInitializerExtension> extensions;
        private final boolean loadAndCache;
        private final InternalLogLevel logLevel;

        ServiceLoadingExtensions(InternalLogLevel internalLogLevel, boolean z) {
            super();
            this.logLevel = internalLogLevel;
            this.loadAndCache = z;
        }

        /* access modifiers changed from: package-private */
        public synchronized Collection<ChannelInitializerExtension> extensions(ClassLoader classLoader2) {
            WeakReference<ClassLoader> weakReference = this.classLoader;
            ClassLoader classLoader3 = weakReference == null ? null : (ClassLoader) weakReference.get();
            if (classLoader3 == null || classLoader3 != classLoader2) {
                Collection<ChannelInitializerExtension> serviceLoadExtensions = serviceLoadExtensions(this.logLevel, classLoader2);
                this.classLoader = new WeakReference<>(classLoader2);
                if (!this.loadAndCache) {
                    serviceLoadExtensions = Collections.emptyList();
                }
                this.extensions = serviceLoadExtensions;
            }
            return this.extensions;
        }

        private static Collection<ChannelInitializerExtension> serviceLoadExtensions(InternalLogLevel internalLogLevel, ClassLoader classLoader2) {
            ArrayList arrayList = new ArrayList();
            Iterator<S> it = ServiceLoader.load(ChannelInitializerExtension.class, classLoader2).iterator();
            while (it.hasNext()) {
                ChannelInitializerExtension channelInitializerExtension = (ChannelInitializerExtension) it.next();
                ChannelInitializerExtensions.logger.log(internalLogLevel, "Loaded extension: {}", (Object) channelInitializerExtension.getClass());
                arrayList.add(channelInitializerExtension);
            }
            if (arrayList.isEmpty()) {
                return Collections.emptyList();
            }
            Collections.sort(arrayList, new Comparator<ChannelInitializerExtension>() {
                public int compare(ChannelInitializerExtension channelInitializerExtension, ChannelInitializerExtension channelInitializerExtension2) {
                    return Double.compare(channelInitializerExtension.priority(), channelInitializerExtension2.priority());
                }
            });
            return Collections.unmodifiableList(arrayList);
        }
    }
}
