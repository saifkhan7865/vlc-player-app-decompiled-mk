package io.netty.channel.local;

import io.netty.channel.Channel;
import io.netty.util.internal.ObjectUtil;
import java.net.SocketAddress;
import java.util.UUID;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class LocalAddress extends SocketAddress implements Comparable<LocalAddress> {
    public static final LocalAddress ANY = new LocalAddress("ANY");
    private static final long serialVersionUID = 4644331421130916435L;
    private final String id;
    private final String strVal;

    LocalAddress(Channel channel) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("local:E");
        sb.append(Long.toHexString((((long) channel.hashCode()) & 4294967295L) | 4294967296L));
        sb.setCharAt(7, AbstractJsonLexerKt.COLON);
        this.id = sb.substring(6);
        this.strVal = sb.toString();
    }

    public LocalAddress(String str) {
        String lowerCase = ObjectUtil.checkNonEmptyAfterTrim(str, "id").toLowerCase();
        this.id = lowerCase;
        this.strVal = "local:" + lowerCase;
    }

    public LocalAddress(Class<?> cls) {
        this(cls.getSimpleName() + '/' + UUID.randomUUID());
    }

    public String id() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocalAddress)) {
            return false;
        }
        return this.id.equals(((LocalAddress) obj).id);
    }

    public int compareTo(LocalAddress localAddress) {
        return this.id.compareTo(localAddress.id);
    }

    public String toString() {
        return this.strVal;
    }
}
