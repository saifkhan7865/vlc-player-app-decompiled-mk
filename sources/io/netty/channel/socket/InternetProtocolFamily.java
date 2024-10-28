package io.netty.channel.socket;

import io.netty.util.NetUtil;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public enum InternetProtocolFamily {
    IPv4(Inet4Address.class, 1),
    IPv6(Inet6Address.class, 2);
    
    private final int addressNumber;
    private final Class<? extends InetAddress> addressType;

    private InternetProtocolFamily(Class<? extends InetAddress> cls, int i) {
        this.addressType = cls;
        this.addressNumber = i;
    }

    public Class<? extends InetAddress> addressType() {
        return this.addressType;
    }

    public int addressNumber() {
        return this.addressNumber;
    }

    /* renamed from: io.netty.channel.socket.InternetProtocolFamily$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$socket$InternetProtocolFamily = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.netty.channel.socket.InternetProtocolFamily[] r0 = io.netty.channel.socket.InternetProtocolFamily.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily = r0
                io.netty.channel.socket.InternetProtocolFamily r1 = io.netty.channel.socket.InternetProtocolFamily.IPv4     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$channel$socket$InternetProtocolFamily     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.channel.socket.InternetProtocolFamily r1 = io.netty.channel.socket.InternetProtocolFamily.IPv6     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.socket.InternetProtocolFamily.AnonymousClass1.<clinit>():void");
        }
    }

    public InetAddress localhost() {
        int i = AnonymousClass1.$SwitchMap$io$netty$channel$socket$InternetProtocolFamily[ordinal()];
        if (i == 1) {
            return NetUtil.LOCALHOST4;
        }
        if (i == 2) {
            return NetUtil.LOCALHOST6;
        }
        throw new IllegalStateException("Unsupported family " + this);
    }

    public static InternetProtocolFamily of(InetAddress inetAddress) {
        if (inetAddress instanceof Inet4Address) {
            return IPv4;
        }
        if (inetAddress instanceof Inet6Address) {
            return IPv6;
        }
        throw new IllegalArgumentException("address " + inetAddress + " not supported");
    }
}
