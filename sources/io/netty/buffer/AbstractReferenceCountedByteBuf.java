package io.netty.buffer;

import io.netty.util.internal.ReferenceCountUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public abstract class AbstractReferenceCountedByteBuf extends AbstractByteBuf {
    /* access modifiers changed from: private */
    public static final AtomicIntegerFieldUpdater<AbstractReferenceCountedByteBuf> AIF_UPDATER;
    /* access modifiers changed from: private */
    public static final long REFCNT_FIELD_OFFSET;
    private static final ReferenceCountUpdater<AbstractReferenceCountedByteBuf> updater = new ReferenceCountUpdater<AbstractReferenceCountedByteBuf>() {
        /* access modifiers changed from: protected */
        public AtomicIntegerFieldUpdater<AbstractReferenceCountedByteBuf> updater() {
            return AbstractReferenceCountedByteBuf.AIF_UPDATER;
        }

        /* access modifiers changed from: protected */
        public long unsafeOffset() {
            return AbstractReferenceCountedByteBuf.REFCNT_FIELD_OFFSET;
        }
    };
    private volatile int refCnt;

    /* access modifiers changed from: protected */
    public abstract void deallocate();

    public ByteBuf touch() {
        return this;
    }

    public ByteBuf touch(Object obj) {
        return this;
    }

    static {
        Class<AbstractReferenceCountedByteBuf> cls = AbstractReferenceCountedByteBuf.class;
        REFCNT_FIELD_OFFSET = ReferenceCountUpdater.getUnsafeOffset(cls, "refCnt");
        AIF_UPDATER = AtomicIntegerFieldUpdater.newUpdater(cls, "refCnt");
    }

    protected AbstractReferenceCountedByteBuf(int i) {
        super(i);
        updater.setInitialValue(this);
    }

    /* access modifiers changed from: package-private */
    public boolean isAccessible() {
        return updater.isLiveNonVolatile(this);
    }

    public int refCnt() {
        return updater.refCnt(this);
    }

    /* access modifiers changed from: protected */
    public final void setRefCnt(int i) {
        updater.setRefCnt(this, i);
    }

    /* access modifiers changed from: protected */
    public final void resetRefCnt() {
        updater.resetRefCnt(this);
    }

    public ByteBuf retain() {
        return updater.retain(this);
    }

    public ByteBuf retain(int i) {
        return updater.retain(this, i);
    }

    public boolean release() {
        return handleRelease(updater.release(this));
    }

    public boolean release(int i) {
        return handleRelease(updater.release(this, i));
    }

    private boolean handleRelease(boolean z) {
        if (z) {
            deallocate();
        }
        return z;
    }
}
