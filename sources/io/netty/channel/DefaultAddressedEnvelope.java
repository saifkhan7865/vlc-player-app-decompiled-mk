package io.netty.channel;

import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.SocketAddress;

public class DefaultAddressedEnvelope<M, A extends SocketAddress> implements AddressedEnvelope<M, A> {
    private final M message;
    private final A recipient;
    private final A sender;

    public DefaultAddressedEnvelope(M m, A a, A a2) {
        ObjectUtil.checkNotNull(m, "message");
        if (a == null && a2 == null) {
            throw new NullPointerException("recipient and sender");
        }
        this.message = m;
        this.sender = a2;
        this.recipient = a;
    }

    public DefaultAddressedEnvelope(M m, A a) {
        this(m, a, (A) null);
    }

    public M content() {
        return this.message;
    }

    public A sender() {
        return this.sender;
    }

    public A recipient() {
        return this.recipient;
    }

    public int refCnt() {
        M m = this.message;
        if (m instanceof ReferenceCounted) {
            return ((ReferenceCounted) m).refCnt();
        }
        return 1;
    }

    public AddressedEnvelope<M, A> retain() {
        ReferenceCountUtil.retain(this.message);
        return this;
    }

    public AddressedEnvelope<M, A> retain(int i) {
        ReferenceCountUtil.retain(this.message, i);
        return this;
    }

    public boolean release() {
        return ReferenceCountUtil.release(this.message);
    }

    public boolean release(int i) {
        return ReferenceCountUtil.release(this.message, i);
    }

    public AddressedEnvelope<M, A> touch() {
        ReferenceCountUtil.touch(this.message);
        return this;
    }

    public AddressedEnvelope<M, A> touch(Object obj) {
        ReferenceCountUtil.touch(this.message, obj);
        return this;
    }

    public String toString() {
        if (this.sender != null) {
            return StringUtil.simpleClassName((Object) this) + '(' + this.sender + " => " + this.recipient + ", " + this.message + ')';
        }
        return StringUtil.simpleClassName((Object) this) + "(=> " + this.recipient + ", " + this.message + ')';
    }
}
