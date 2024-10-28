package io.netty.channel.epoll;

import java.net.SocketAddress;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class VSockAddress extends SocketAddress {
    public static final int VMADDR_CID_ANY = -1;
    public static final int VMADDR_CID_HOST = 2;
    public static final int VMADDR_CID_HYPERVISOR = 0;
    public static final int VMADDR_CID_LOCAL = 1;
    public static final int VMADDR_PORT_ANY = -1;
    private static final long serialVersionUID = 8600894096347158429L;
    private final int cid;
    private final int port;

    public VSockAddress(int i, int i2) {
        this.cid = i;
        this.port = i2;
    }

    public int getCid() {
        return this.cid;
    }

    public int getPort() {
        return this.port;
    }

    public String toString() {
        return "VSockAddress{cid=" + this.cid + ", port=" + this.port + AbstractJsonLexerKt.END_OBJ;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VSockAddress)) {
            return false;
        }
        VSockAddress vSockAddress = (VSockAddress) obj;
        if (this.cid == vSockAddress.cid && this.port == vSockAddress.port) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.cid * 31) + this.port;
    }
}
