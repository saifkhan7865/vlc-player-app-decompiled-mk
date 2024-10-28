package org.videolan.tools;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/videolan/tools/Connection;", "", "connected", "", "mobile", "vpn", "(ZZZ)V", "getConnected", "()Z", "getMobile", "getVpn", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkMonitor.kt */
public final class Connection {
    private final boolean connected;
    private final boolean mobile;
    private final boolean vpn;

    public Connection(boolean z, boolean z2, boolean z3) {
        this.connected = z;
        this.mobile = z2;
        this.vpn = z3;
    }

    public final boolean getConnected() {
        return this.connected;
    }

    public final boolean getMobile() {
        return this.mobile;
    }

    public final boolean getVpn() {
        return this.vpn;
    }
}
