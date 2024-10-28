package io.netty.util;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.InjectAccessors;
import com.oracle.svm.core.annotate.TargetClass;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collection;

@TargetClass(NetUtil.class)
final class NetUtilSubstitutions {
    @InjectAccessors(NetUtilLocalhostAccessor.class)
    @Alias
    public static InetAddress LOCALHOST;
    @InjectAccessors(NetUtilLocalhost4Accessor.class)
    @Alias
    public static Inet4Address LOCALHOST4;
    @InjectAccessors(NetUtilLocalhost6Accessor.class)
    @Alias
    public static Inet6Address LOCALHOST6;
    @InjectAccessors(NetUtilNetworkInterfacesAccessor.class)
    @Alias
    public static Collection<NetworkInterface> NETWORK_INTERFACES;

    private NetUtilSubstitutions() {
    }

    private static final class NetUtilLocalhost4Accessor {
        static void set(Inet4Address inet4Address) {
        }

        private NetUtilLocalhost4Accessor() {
        }

        static Inet4Address get() {
            return NetUtilLocalhost4LazyHolder.LOCALHOST4;
        }
    }

    private static final class NetUtilLocalhost4LazyHolder {
        /* access modifiers changed from: private */
        public static final Inet4Address LOCALHOST4 = NetUtilInitializations.createLocalhost4();

        private NetUtilLocalhost4LazyHolder() {
        }
    }

    private static final class NetUtilLocalhost6Accessor {
        static void set(Inet6Address inet6Address) {
        }

        private NetUtilLocalhost6Accessor() {
        }

        static Inet6Address get() {
            return NetUtilLocalhost6LazyHolder.LOCALHOST6;
        }
    }

    private static final class NetUtilLocalhost6LazyHolder {
        /* access modifiers changed from: private */
        public static final Inet6Address LOCALHOST6 = NetUtilInitializations.createLocalhost6();

        private NetUtilLocalhost6LazyHolder() {
        }
    }

    private static final class NetUtilLocalhostAccessor {
        static void set(InetAddress inetAddress) {
        }

        private NetUtilLocalhostAccessor() {
        }

        static InetAddress get() {
            return NetUtilLocalhostLazyHolder.LOCALHOST;
        }
    }

    private static final class NetUtilLocalhostLazyHolder {
        /* access modifiers changed from: private */
        public static final InetAddress LOCALHOST = NetUtilInitializations.determineLoopback(NetUtilNetworkInterfacesLazyHolder.NETWORK_INTERFACES, NetUtilLocalhost4LazyHolder.LOCALHOST4, NetUtilLocalhost6LazyHolder.LOCALHOST6).address();

        private NetUtilLocalhostLazyHolder() {
        }
    }

    private static final class NetUtilNetworkInterfacesAccessor {
        static void set(Collection<NetworkInterface> collection) {
        }

        private NetUtilNetworkInterfacesAccessor() {
        }

        static Collection<NetworkInterface> get() {
            return NetUtilNetworkInterfacesLazyHolder.NETWORK_INTERFACES;
        }
    }

    private static final class NetUtilNetworkInterfacesLazyHolder {
        /* access modifiers changed from: private */
        public static final Collection<NetworkInterface> NETWORK_INTERFACES = NetUtilInitializations.networkInterfaces();

        private NetUtilNetworkInterfacesLazyHolder() {
        }
    }
}
