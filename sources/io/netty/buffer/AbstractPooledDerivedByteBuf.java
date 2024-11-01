package io.netty.buffer;

import io.netty.util.Recycler;
import io.netty.util.internal.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

abstract class AbstractPooledDerivedByteBuf extends AbstractReferenceCountedByteBuf {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteBuf parent;
    private final Recycler.EnhancedHandle<AbstractPooledDerivedByteBuf> recyclerHandle;
    private AbstractByteBuf rootParent;

    AbstractPooledDerivedByteBuf(ObjectPool.Handle<? extends AbstractPooledDerivedByteBuf> handle) {
        super(0);
        this.recyclerHandle = (Recycler.EnhancedHandle) handle;
    }

    /* access modifiers changed from: package-private */
    public final void parent(ByteBuf byteBuf) {
        this.parent = byteBuf;
    }

    public final AbstractByteBuf unwrap() {
        return this.rootParent;
    }

    /* access modifiers changed from: package-private */
    public final <U extends AbstractPooledDerivedByteBuf> U init(AbstractByteBuf abstractByteBuf, ByteBuf byteBuf, int i, int i2, int i3) {
        byteBuf.retain();
        this.parent = byteBuf;
        this.rootParent = abstractByteBuf;
        try {
            maxCapacity(i3);
            setIndex0(i, i2);
            resetRefCnt();
            return this;
        } catch (Throwable th) {
            if (byteBuf != null) {
                this.rootParent = null;
                this.parent = null;
                byteBuf.release();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final void deallocate() {
        ByteBuf byteBuf = this.parent;
        this.recyclerHandle.unguardedRecycle(this);
        byteBuf.release();
    }

    public final ByteBufAllocator alloc() {
        return unwrap().alloc();
    }

    @Deprecated
    public final ByteOrder order() {
        return unwrap().order();
    }

    public boolean isReadOnly() {
        return unwrap().isReadOnly();
    }

    public final boolean isDirect() {
        return unwrap().isDirect();
    }

    public boolean hasArray() {
        return unwrap().hasArray();
    }

    public byte[] array() {
        return unwrap().array();
    }

    public boolean hasMemoryAddress() {
        return unwrap().hasMemoryAddress();
    }

    public boolean isContiguous() {
        return unwrap().isContiguous();
    }

    public final int nioBufferCount() {
        return unwrap().nioBufferCount();
    }

    public final ByteBuffer internalNioBuffer(int i, int i2) {
        return nioBuffer(i, i2);
    }

    public final ByteBuf retainedSlice() {
        int readerIndex = readerIndex();
        return retainedSlice(readerIndex, writerIndex() - readerIndex);
    }

    public ByteBuf slice(int i, int i2) {
        ensureAccessible();
        return new PooledNonRetainedSlicedByteBuf(this, unwrap(), i, i2);
    }

    /* access modifiers changed from: package-private */
    public final ByteBuf duplicate0() {
        ensureAccessible();
        return new PooledNonRetainedDuplicateByteBuf(this, unwrap());
    }

    private static final class PooledNonRetainedDuplicateByteBuf extends UnpooledDuplicatedByteBuf {
        private final ByteBuf referenceCountDelegate;

        PooledNonRetainedDuplicateByteBuf(ByteBuf byteBuf, AbstractByteBuf abstractByteBuf) {
            super(abstractByteBuf);
            this.referenceCountDelegate = byteBuf;
        }

        /* access modifiers changed from: package-private */
        public boolean isAccessible0() {
            return this.referenceCountDelegate.isAccessible();
        }

        /* access modifiers changed from: package-private */
        public int refCnt0() {
            return this.referenceCountDelegate.refCnt();
        }

        /* access modifiers changed from: package-private */
        public ByteBuf retain0() {
            this.referenceCountDelegate.retain();
            return this;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf retain0(int i) {
            this.referenceCountDelegate.retain(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf touch0() {
            this.referenceCountDelegate.touch();
            return this;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf touch0(Object obj) {
            this.referenceCountDelegate.touch(obj);
            return this;
        }

        /* access modifiers changed from: package-private */
        public boolean release0() {
            return this.referenceCountDelegate.release();
        }

        /* access modifiers changed from: package-private */
        public boolean release0(int i) {
            return this.referenceCountDelegate.release(i);
        }

        public ByteBuf duplicate() {
            ensureAccessible();
            return new PooledNonRetainedDuplicateByteBuf(this.referenceCountDelegate, this);
        }

        public ByteBuf retainedDuplicate() {
            return PooledDuplicatedByteBuf.newInstance(unwrap(), this, readerIndex(), writerIndex());
        }

        public ByteBuf slice(int i, int i2) {
            checkIndex(i, i2);
            return new PooledNonRetainedSlicedByteBuf(this.referenceCountDelegate, unwrap(), i, i2);
        }

        public ByteBuf retainedSlice() {
            return retainedSlice(readerIndex(), capacity());
        }

        public ByteBuf retainedSlice(int i, int i2) {
            return PooledSlicedByteBuf.newInstance(unwrap(), this, i, i2);
        }
    }

    private static final class PooledNonRetainedSlicedByteBuf extends UnpooledSlicedByteBuf {
        private final ByteBuf referenceCountDelegate;

        PooledNonRetainedSlicedByteBuf(ByteBuf byteBuf, AbstractByteBuf abstractByteBuf, int i, int i2) {
            super(abstractByteBuf, i, i2);
            this.referenceCountDelegate = byteBuf;
        }

        /* access modifiers changed from: package-private */
        public boolean isAccessible0() {
            return this.referenceCountDelegate.isAccessible();
        }

        /* access modifiers changed from: package-private */
        public int refCnt0() {
            return this.referenceCountDelegate.refCnt();
        }

        /* access modifiers changed from: package-private */
        public ByteBuf retain0() {
            this.referenceCountDelegate.retain();
            return this;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf retain0(int i) {
            this.referenceCountDelegate.retain(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf touch0() {
            this.referenceCountDelegate.touch();
            return this;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf touch0(Object obj) {
            this.referenceCountDelegate.touch(obj);
            return this;
        }

        /* access modifiers changed from: package-private */
        public boolean release0() {
            return this.referenceCountDelegate.release();
        }

        /* access modifiers changed from: package-private */
        public boolean release0(int i) {
            return this.referenceCountDelegate.release(i);
        }

        public ByteBuf duplicate() {
            ensureAccessible();
            return new PooledNonRetainedDuplicateByteBuf(this.referenceCountDelegate, unwrap()).setIndex(idx(readerIndex()), idx(writerIndex()));
        }

        public ByteBuf retainedDuplicate() {
            return PooledDuplicatedByteBuf.newInstance(unwrap(), this, idx(readerIndex()), idx(writerIndex()));
        }

        public ByteBuf slice(int i, int i2) {
            checkIndex(i, i2);
            return new PooledNonRetainedSlicedByteBuf(this.referenceCountDelegate, unwrap(), idx(i), i2);
        }

        public ByteBuf retainedSlice() {
            return retainedSlice(0, capacity());
        }

        public ByteBuf retainedSlice(int i, int i2) {
            return PooledSlicedByteBuf.newInstance(unwrap(), this, idx(i), i2);
        }
    }
}
