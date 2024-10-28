package io.netty.buffer;

import com.google.common.base.Ascii;
import io.netty.util.ByteProcessor;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.RecyclableArrayList;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CompositeByteBuf extends AbstractReferenceCountedByteBuf implements Iterable<ByteBuf> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final ByteWrapper<byte[]> BYTE_ARRAY_WRAPPER = new ByteWrapper<byte[]>() {
        public ByteBuf wrap(byte[] bArr) {
            return Unpooled.wrappedBuffer(bArr);
        }

        public boolean isEmpty(byte[] bArr) {
            return bArr.length == 0;
        }
    };
    static final ByteWrapper<ByteBuffer> BYTE_BUFFER_WRAPPER = new ByteWrapper<ByteBuffer>() {
        public ByteBuf wrap(ByteBuffer byteBuffer) {
            return Unpooled.wrappedBuffer(byteBuffer);
        }

        public boolean isEmpty(ByteBuffer byteBuffer) {
            return !byteBuffer.hasRemaining();
        }
    };
    private static final Iterator<ByteBuf> EMPTY_ITERATOR = Collections.emptyList().iterator();
    private static final ByteBuffer EMPTY_NIO_BUFFER = Unpooled.EMPTY_BUFFER.nioBuffer();
    private final ByteBufAllocator alloc;
    private int componentCount;
    /* access modifiers changed from: private */
    public Component[] components;
    private final boolean direct;
    private boolean freed;
    private Component lastAccessed;
    private final int maxNumComponents;

    interface ByteWrapper<T> {
        boolean isEmpty(T t);

        ByteBuf wrap(T t);
    }

    public CompositeByteBuf touch() {
        return this;
    }

    public CompositeByteBuf touch(Object obj) {
        return this;
    }

    public ByteBuf unwrap() {
        return null;
    }

    private CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, int i2) {
        super(Integer.MAX_VALUE);
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        if (i >= 1) {
            this.direct = z;
            this.maxNumComponents = i;
            this.components = newCompArray(i2, i);
            return;
        }
        throw new IllegalArgumentException("maxNumComponents: " + i + " (expected: >= 1)");
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i) {
        this(byteBufAllocator, z, i, 0);
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf... byteBufArr) {
        this(byteBufAllocator, z, i, byteBufArr, 0);
    }

    CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf[] byteBufArr, int i2) {
        this(byteBufAllocator, z, i, byteBufArr.length - i2);
        addComponents0(false, 0, byteBufArr, i2);
        consolidateIfNeeded();
        setIndex0(0, capacity());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, Iterable<ByteBuf> iterable) {
        this(byteBufAllocator, z, i, iterable instanceof Collection ? ((Collection) iterable).size() : 0);
        addComponents(false, 0, iterable);
        setIndex(0, capacity());
    }

    <T> CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteWrapper<T> byteWrapper, T[] tArr, int i2) {
        this(byteBufAllocator, z, i, tArr.length - i2);
        addComponents0(false, 0, byteWrapper, tArr, i2);
        consolidateIfNeeded();
        setIndex(0, capacity());
    }

    private static Component[] newCompArray(int i, int i2) {
        return new Component[Math.max(i, Math.min(16, i2))];
    }

    CompositeByteBuf(ByteBufAllocator byteBufAllocator) {
        super(Integer.MAX_VALUE);
        this.alloc = byteBufAllocator;
        this.direct = false;
        this.maxNumComponents = 0;
        this.components = null;
    }

    public CompositeByteBuf addComponent(ByteBuf byteBuf) {
        return addComponent(false, byteBuf);
    }

    public CompositeByteBuf addComponents(ByteBuf... byteBufArr) {
        return addComponents(false, byteBufArr);
    }

    public CompositeByteBuf addComponents(Iterable<ByteBuf> iterable) {
        return addComponents(false, iterable);
    }

    public CompositeByteBuf addComponent(int i, ByteBuf byteBuf) {
        return addComponent(false, i, byteBuf);
    }

    public CompositeByteBuf addComponent(boolean z, ByteBuf byteBuf) {
        return addComponent(z, this.componentCount, byteBuf);
    }

    public CompositeByteBuf addComponents(boolean z, ByteBuf... byteBufArr) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        addComponents0(z, this.componentCount, byteBufArr, 0);
        consolidateIfNeeded();
        return this;
    }

    public CompositeByteBuf addComponents(boolean z, Iterable<ByteBuf> iterable) {
        return addComponents(z, this.componentCount, iterable);
    }

    public CompositeByteBuf addComponent(boolean z, int i, ByteBuf byteBuf) {
        ObjectUtil.checkNotNull(byteBuf, "buffer");
        addComponent0(z, i, byteBuf);
        consolidateIfNeeded();
        return this;
    }

    private static void checkForOverflow(int i, int i2) {
        if (i + i2 < 0) {
            throw new IllegalArgumentException("Can't increase by " + i2 + " as capacity(" + i + ") would overflow 2147483647");
        }
    }

    private int addComponent0(boolean z, int i, ByteBuf byteBuf) {
        try {
            checkComponentIndex(i);
            Component newComponent = newComponent(ensureAccessible(byteBuf), 0);
            int length = newComponent.length();
            checkForOverflow(capacity(), length);
            addComp(i, newComponent);
            if (length > 0 && i < this.componentCount - 1) {
                updateComponentOffsets(i);
            } else if (i > 0) {
                newComponent.reposition(this.components[i - 1].endOffset);
            }
            if (z) {
                this.writerIndex += length;
            }
            return i;
        } catch (Throwable th) {
            if (0 == 0) {
                byteBuf.release();
            }
            throw th;
        }
    }

    private static ByteBuf ensureAccessible(ByteBuf byteBuf) {
        if (!checkAccessible || byteBuf.isAccessible()) {
            return byteBuf;
        }
        throw new IllegalReferenceCountException(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.netty.buffer.CompositeByteBuf.Component newComponent(io.netty.buffer.ByteBuf r10, int r11) {
        /*
            r9 = this;
            int r2 = r10.readerIndex()
            int r6 = r10.readableBytes()
            r0 = r10
        L_0x0009:
            boolean r1 = r0 instanceof io.netty.buffer.WrappedByteBuf
            if (r1 != 0) goto L_0x005f
            boolean r1 = r0 instanceof io.netty.buffer.SwappedByteBuf
            if (r1 == 0) goto L_0x0012
            goto L_0x005f
        L_0x0012:
            boolean r1 = r0 instanceof io.netty.buffer.AbstractUnpooledSlicedByteBuf
            if (r1 == 0) goto L_0x0025
            r1 = r0
            io.netty.buffer.AbstractUnpooledSlicedByteBuf r1 = (io.netty.buffer.AbstractUnpooledSlicedByteBuf) r1
            r3 = 0
            int r1 = r1.idx(r3)
            int r1 = r1 + r2
            io.netty.buffer.ByteBuf r0 = r0.unwrap()
        L_0x0023:
            r4 = r1
            goto L_0x0041
        L_0x0025:
            boolean r1 = r0 instanceof io.netty.buffer.PooledSlicedByteBuf
            if (r1 == 0) goto L_0x0034
            r1 = r0
            io.netty.buffer.PooledSlicedByteBuf r1 = (io.netty.buffer.PooledSlicedByteBuf) r1
            int r1 = r1.adjustment
            int r1 = r1 + r2
            io.netty.buffer.ByteBuf r0 = r0.unwrap()
            goto L_0x0023
        L_0x0034:
            boolean r1 = r0 instanceof io.netty.buffer.DuplicatedByteBuf
            if (r1 != 0) goto L_0x003c
            boolean r1 = r0 instanceof io.netty.buffer.PooledDuplicatedByteBuf
            if (r1 == 0) goto L_0x0040
        L_0x003c:
            io.netty.buffer.ByteBuf r0 = r0.unwrap()
        L_0x0040:
            r4 = r2
        L_0x0041:
            int r1 = r10.capacity()
            if (r1 != r6) goto L_0x0049
            r7 = r10
            goto L_0x004b
        L_0x0049:
            r1 = 0
            r7 = r1
        L_0x004b:
            io.netty.buffer.CompositeByteBuf$Component r8 = new io.netty.buffer.CompositeByteBuf$Component
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            io.netty.buffer.ByteBuf r1 = r10.order(r1)
            java.nio.ByteOrder r10 = java.nio.ByteOrder.BIG_ENDIAN
            io.netty.buffer.ByteBuf r3 = r0.order(r10)
            r0 = r8
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r8
        L_0x005f:
            io.netty.buffer.ByteBuf r0 = r0.unwrap()
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.newComponent(io.netty.buffer.ByteBuf, int):io.netty.buffer.CompositeByteBuf$Component");
    }

    public CompositeByteBuf addComponents(int i, ByteBuf... byteBufArr) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        addComponents0(false, i, byteBufArr, 0);
        consolidateIfNeeded();
        return this;
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private io.netty.buffer.CompositeByteBuf addComponents0(boolean r8, int r9, io.netty.buffer.ByteBuf[] r10, int r11) {
        /*
            r7 = this;
            int r0 = r10.length
            int r1 = r0 - r11
            int r2 = r7.capacity()
            r3 = 0
            r4 = r11
            r5 = 0
        L_0x000a:
            int r6 = r10.length
            if (r4 >= r6) goto L_0x001d
            r6 = r10[r4]
            if (r6 != 0) goto L_0x0012
            goto L_0x001d
        L_0x0012:
            int r6 = r6.readableBytes()
            int r5 = r5 + r6
            checkForOverflow(r2, r5)
            int r4 = r4 + 1
            goto L_0x000a
        L_0x001d:
            r2 = 2147483647(0x7fffffff, float:NaN)
            r7.checkComponentIndex(r9)     // Catch:{ all -> 0x007f }
            r7.shiftComps(r9, r1)     // Catch:{ all -> 0x007f }
            if (r9 <= 0) goto L_0x0030
            io.netty.buffer.CompositeByteBuf$Component[] r3 = r7.components     // Catch:{ all -> 0x007f }
            int r4 = r9 + -1
            r3 = r3[r4]     // Catch:{ all -> 0x007f }
            int r3 = r3.endOffset     // Catch:{ all -> 0x007f }
        L_0x0030:
            r2 = r9
        L_0x0031:
            if (r11 >= r0) goto L_0x004b
            r4 = r10[r11]     // Catch:{ all -> 0x007f }
            if (r4 != 0) goto L_0x0038
            goto L_0x004b
        L_0x0038:
            io.netty.buffer.ByteBuf r4 = ensureAccessible(r4)     // Catch:{ all -> 0x007f }
            io.netty.buffer.CompositeByteBuf$Component r3 = r7.newComponent(r4, r3)     // Catch:{ all -> 0x007f }
            io.netty.buffer.CompositeByteBuf$Component[] r4 = r7.components     // Catch:{ all -> 0x007f }
            r4[r2] = r3     // Catch:{ all -> 0x007f }
            int r3 = r3.endOffset     // Catch:{ all -> 0x007f }
            int r11 = r11 + 1
            int r2 = r2 + 1
            goto L_0x0031
        L_0x004b:
            int r3 = r7.componentCount
            if (r2 >= r3) goto L_0x0062
            int r1 = r1 + r9
            if (r2 >= r1) goto L_0x005f
            r7.removeCompRange(r2, r1)
        L_0x0055:
            if (r11 >= r0) goto L_0x005f
            r1 = r10[r11]
            io.netty.util.ReferenceCountUtil.safeRelease(r1)
            int r11 = r11 + 1
            goto L_0x0055
        L_0x005f:
            r7.updateComponentOffsets(r2)
        L_0x0062:
            if (r8 == 0) goto L_0x007e
            if (r2 <= r9) goto L_0x007e
            int r8 = r7.componentCount
            if (r2 > r8) goto L_0x007e
            int r8 = r7.writerIndex
            io.netty.buffer.CompositeByteBuf$Component[] r10 = r7.components
            int r2 = r2 + -1
            r10 = r10[r2]
            int r10 = r10.endOffset
            io.netty.buffer.CompositeByteBuf$Component[] r11 = r7.components
            r9 = r11[r9]
            int r9 = r9.offset
            int r10 = r10 - r9
            int r8 = r8 + r10
            r7.writerIndex = r8
        L_0x007e:
            return r7
        L_0x007f:
            r3 = move-exception
            int r4 = r7.componentCount
            if (r2 >= r4) goto L_0x0097
            int r1 = r1 + r9
            if (r2 >= r1) goto L_0x0094
            r7.removeCompRange(r2, r1)
        L_0x008a:
            if (r11 >= r0) goto L_0x0094
            r1 = r10[r11]
            io.netty.util.ReferenceCountUtil.safeRelease(r1)
            int r11 = r11 + 1
            goto L_0x008a
        L_0x0094:
            r7.updateComponentOffsets(r2)
        L_0x0097:
            if (r8 == 0) goto L_0x00b3
            if (r2 <= r9) goto L_0x00b3
            int r8 = r7.componentCount
            if (r2 > r8) goto L_0x00b3
            int r8 = r7.writerIndex
            io.netty.buffer.CompositeByteBuf$Component[] r10 = r7.components
            int r2 = r2 + -1
            r10 = r10[r2]
            int r10 = r10.endOffset
            io.netty.buffer.CompositeByteBuf$Component[] r11 = r7.components
            r9 = r11[r9]
            int r9 = r9.offset
            int r10 = r10 - r9
            int r8 = r8 + r10
            r7.writerIndex = r8
        L_0x00b3:
            goto L_0x00b5
        L_0x00b4:
            throw r3
        L_0x00b5:
            goto L_0x00b4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.addComponents0(boolean, int, io.netty.buffer.ByteBuf[], int):io.netty.buffer.CompositeByteBuf");
    }

    private <T> int addComponents0(boolean z, int i, ByteWrapper<T> byteWrapper, T[] tArr, int i2) {
        int i3;
        checkComponentIndex(i);
        int length = tArr.length;
        while (i2 < length) {
            T t = tArr[i2];
            if (t == null) {
                break;
            }
            if (!byteWrapper.isEmpty(t) && (i = addComponent0(z, i, byteWrapper.wrap(t)) + 1) > (i3 = this.componentCount)) {
                i = i3;
            }
            i2++;
        }
        return i;
    }

    public CompositeByteBuf addComponents(int i, Iterable<ByteBuf> iterable) {
        return addComponents(false, i, iterable);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.buffer.CompositeByteBuf addFlattenedComponents(boolean r25, io.netty.buffer.ByteBuf r26) {
        /*
            r24 = this;
            r1 = r24
            r2 = r25
            r3 = r26
            java.lang.String r0 = "buffer"
            io.netty.util.internal.ObjectUtil.checkNotNull(r3, r0)
            int r0 = r26.readerIndex()
            int r4 = r26.writerIndex()
            if (r0 != r4) goto L_0x0019
            r26.release()
            return r1
        L_0x0019:
            boolean r5 = r3 instanceof io.netty.buffer.CompositeByteBuf
            if (r5 != 0) goto L_0x0026
            int r0 = r1.componentCount
            r1.addComponent0(r2, r0, r3)
            r24.consolidateIfNeeded()
            return r1
        L_0x0026:
            boolean r5 = r3 instanceof io.netty.buffer.WrappedCompositeByteBuf
            if (r5 == 0) goto L_0x0031
            io.netty.buffer.ByteBuf r5 = r26.unwrap()
            io.netty.buffer.CompositeByteBuf r5 = (io.netty.buffer.CompositeByteBuf) r5
            goto L_0x0034
        L_0x0031:
            r5 = r3
            io.netty.buffer.CompositeByteBuf r5 = (io.netty.buffer.CompositeByteBuf) r5
        L_0x0034:
            int r6 = r4 - r0
            r5.checkIndex(r0, r6)
            io.netty.buffer.CompositeByteBuf$Component[] r7 = r5.components
            int r8 = r1.componentCount
            int r9 = r1.writerIndex
            int r5 = r5.toComponentIndex0(r0)     // Catch:{ all -> 0x00b0 }
            int r10 = r24.capacity()     // Catch:{ all -> 0x00b0 }
            r18 = r10
        L_0x0049:
            r10 = r7[r5]     // Catch:{ all -> 0x00b0 }
            int r11 = r10.offset     // Catch:{ all -> 0x00b0 }
            int r11 = java.lang.Math.max(r0, r11)     // Catch:{ all -> 0x00b0 }
            int r12 = r10.endOffset     // Catch:{ all -> 0x00b0 }
            int r15 = java.lang.Math.min(r4, r12)     // Catch:{ all -> 0x00b0 }
            int r19 = r15 - r11
            if (r19 <= 0) goto L_0x008e
            int r14 = r1.componentCount     // Catch:{ all -> 0x00b0 }
            io.netty.buffer.CompositeByteBuf$Component r13 = new io.netty.buffer.CompositeByteBuf$Component     // Catch:{ all -> 0x00b0 }
            io.netty.buffer.ByteBuf r12 = r10.srcBuf     // Catch:{ all -> 0x00b0 }
            io.netty.buffer.ByteBuf r12 = r12.retain()     // Catch:{ all -> 0x00b0 }
            int r16 = r10.srcIdx(r11)     // Catch:{ all -> 0x00b0 }
            r20 = r0
            io.netty.buffer.ByteBuf r0 = r10.buf     // Catch:{ all -> 0x00b0 }
            int r17 = r10.idx(r11)     // Catch:{ all -> 0x00b0 }
            r21 = 0
            r10 = r13
            r11 = r12
            r12 = r16
            r22 = r7
            r7 = r13
            r13 = r0
            r0 = r14
            r14 = r17
            r23 = r8
            r8 = r15
            r15 = r18
            r16 = r19
            r17 = r21
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x00a3 }
            r1.addComp(r0, r7)     // Catch:{ all -> 0x00a3 }
            goto L_0x0095
        L_0x008e:
            r20 = r0
            r22 = r7
            r23 = r8
            r8 = r15
        L_0x0095:
            if (r4 != r8) goto L_0x00a5
            if (r2 == 0) goto L_0x009c
            int r6 = r6 + r9
            r1.writerIndex = r6     // Catch:{ all -> 0x00a3 }
        L_0x009c:
            r24.consolidateIfNeeded()     // Catch:{ all -> 0x00a3 }
            r26.release()     // Catch:{ all -> 0x00a3 }
            return r1
        L_0x00a3:
            r0 = move-exception
            goto L_0x00b3
        L_0x00a5:
            int r18 = r18 + r19
            int r5 = r5 + 1
            r0 = r20
            r7 = r22
            r8 = r23
            goto L_0x0049
        L_0x00b0:
            r0 = move-exception
            r23 = r8
        L_0x00b3:
            if (r3 == 0) goto L_0x00ce
            if (r2 == 0) goto L_0x00b9
            r1.writerIndex = r9
        L_0x00b9:
            int r2 = r1.componentCount
            int r2 = r2 + -1
            r3 = r23
        L_0x00bf:
            if (r2 < r3) goto L_0x00ce
            io.netty.buffer.CompositeByteBuf$Component[] r4 = r1.components
            r4 = r4[r2]
            r4.free()
            r1.removeComp(r2)
            int r2 = r2 + -1
            goto L_0x00bf
        L_0x00ce:
            goto L_0x00d0
        L_0x00cf:
            throw r0
        L_0x00d0:
            goto L_0x00cf
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.addFlattenedComponents(boolean, io.netty.buffer.ByteBuf):io.netty.buffer.CompositeByteBuf");
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private io.netty.buffer.CompositeByteBuf addComponents(boolean r2, int r3, java.lang.Iterable<io.netty.buffer.ByteBuf> r4) {
        /*
            r1 = this;
            boolean r0 = r4 instanceof io.netty.buffer.ByteBuf
            if (r0 == 0) goto L_0x000b
            io.netty.buffer.ByteBuf r4 = (io.netty.buffer.ByteBuf) r4
            io.netty.buffer.CompositeByteBuf r2 = r1.addComponent(r2, r3, r4)
            return r2
        L_0x000b:
            java.lang.String r0 = "buffers"
            io.netty.util.internal.ObjectUtil.checkNotNull(r4, r0)
            java.util.Iterator r4 = r4.iterator()
            r1.checkComponentIndex(r3)     // Catch:{ all -> 0x0045 }
        L_0x0017:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x0033
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x0045 }
            io.netty.buffer.ByteBuf r0 = (io.netty.buffer.ByteBuf) r0     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x0026
            goto L_0x0033
        L_0x0026:
            int r3 = r1.addComponent0(r2, r3, r0)     // Catch:{ all -> 0x0045 }
            int r3 = r3 + 1
            int r0 = r1.componentCount     // Catch:{ all -> 0x0045 }
            int r3 = java.lang.Math.min(r3, r0)     // Catch:{ all -> 0x0045 }
            goto L_0x0017
        L_0x0033:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x0041
            java.lang.Object r2 = r4.next()
            io.netty.util.ReferenceCountUtil.safeRelease(r2)
            goto L_0x0033
        L_0x0041:
            r1.consolidateIfNeeded()
            return r1
        L_0x0045:
            r2 = move-exception
        L_0x0046:
            boolean r3 = r4.hasNext()
            if (r3 == 0) goto L_0x0054
            java.lang.Object r3 = r4.next()
            io.netty.util.ReferenceCountUtil.safeRelease(r3)
            goto L_0x0046
        L_0x0054:
            goto L_0x0056
        L_0x0055:
            throw r2
        L_0x0056:
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.addComponents(boolean, int, java.lang.Iterable):io.netty.buffer.CompositeByteBuf");
    }

    private void consolidateIfNeeded() {
        int i = this.componentCount;
        if (i > this.maxNumComponents) {
            consolidate0(0, i);
        }
    }

    private void checkComponentIndex(int i) {
        ensureAccessible();
        if (i < 0 || i > this.componentCount) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d (expected: >= 0 && <= numComponents(%d))", new Object[]{Integer.valueOf(i), Integer.valueOf(this.componentCount)}));
        }
    }

    private void checkComponentIndex(int i, int i2) {
        ensureAccessible();
        if (i < 0 || i + i2 > this.componentCount) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d, numComponents: %d (expected: cIndex >= 0 && cIndex + numComponents <= totalNumComponents(%d))", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.componentCount)}));
        }
    }

    private void updateComponentOffsets(int i) {
        int i2 = this.componentCount;
        if (i2 > i) {
            int i3 = i > 0 ? this.components[i - 1].endOffset : 0;
            while (i < i2) {
                Component component = this.components[i];
                component.reposition(i3);
                i3 = component.endOffset;
                i++;
            }
        }
    }

    public CompositeByteBuf removeComponent(int i) {
        checkComponentIndex(i);
        Component component = this.components[i];
        if (this.lastAccessed == component) {
            this.lastAccessed = null;
        }
        component.free();
        removeComp(i);
        if (component.length() > 0) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public CompositeByteBuf removeComponents(int i, int i2) {
        checkComponentIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int i3 = i2 + i;
        boolean z = false;
        for (int i4 = i; i4 < i3; i4++) {
            Component component = this.components[i4];
            if (component.length() > 0) {
                z = true;
            }
            if (this.lastAccessed == component) {
                this.lastAccessed = null;
            }
            component.free();
        }
        removeCompRange(i, i3);
        if (z) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public Iterator<ByteBuf> iterator() {
        ensureAccessible();
        return this.componentCount == 0 ? EMPTY_ITERATOR : new CompositeByteBufIterator();
    }

    /* access modifiers changed from: protected */
    public int forEachByteAsc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int i3;
        if (i2 <= i) {
            return -1;
        }
        int componentIndex0 = toComponentIndex0(i);
        int i4 = i2 - i;
        while (i4 > 0) {
            Component component = this.components[componentIndex0];
            if (component.offset != component.endOffset) {
                ByteBuf byteBuf = component.buf;
                int idx = component.idx(i);
                int min = Math.min(i4, component.endOffset - i);
                if (byteBuf instanceof AbstractByteBuf) {
                    i3 = ((AbstractByteBuf) byteBuf).forEachByteAsc0(idx, idx + min, byteProcessor);
                } else {
                    i3 = byteBuf.forEachByte(idx, min, byteProcessor);
                }
                if (i3 != -1) {
                    return i3 - component.adjustment;
                }
                i += min;
                i4 -= min;
            }
            componentIndex0++;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int forEachByteDesc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int i3;
        if (i2 > i) {
            return -1;
        }
        int componentIndex0 = toComponentIndex0(i);
        int i4 = (i + 1) - i2;
        while (i4 > 0) {
            Component component = this.components[componentIndex0];
            if (component.offset != component.endOffset) {
                ByteBuf byteBuf = component.buf;
                int idx = component.idx(i4 + i2);
                int min = Math.min(i4, idx);
                int i5 = idx - min;
                if (byteBuf instanceof AbstractByteBuf) {
                    i3 = ((AbstractByteBuf) byteBuf).forEachByteDesc0(idx - 1, i5, byteProcessor);
                } else {
                    i3 = byteBuf.forEachByteDesc(i5, min, byteProcessor);
                }
                if (i3 != -1) {
                    return i3 - component.adjustment;
                }
                i4 -= min;
            }
            componentIndex0--;
        }
        return -1;
    }

    public List<ByteBuf> decompose(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return Collections.emptyList();
        }
        int componentIndex0 = toComponentIndex0(i);
        Component component = this.components[componentIndex0];
        ByteBuf slice = component.srcBuf.slice(component.srcIdx(i), Math.min(component.endOffset - i, i2));
        int readableBytes = i2 - slice.readableBytes();
        if (readableBytes == 0) {
            return Collections.singletonList(slice);
        }
        ArrayList arrayList = new ArrayList(this.componentCount - componentIndex0);
        arrayList.add(slice);
        do {
            componentIndex0++;
            Component component2 = this.components[componentIndex0];
            ByteBuf slice2 = component2.srcBuf.slice(component2.srcIdx(component2.offset), Math.min(component2.length(), readableBytes));
            readableBytes -= slice2.readableBytes();
            arrayList.add(slice2);
        } while (readableBytes > 0);
        return arrayList;
    }

    public boolean isDirect() {
        int i = this.componentCount;
        if (i == 0) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!this.components[i2].buf.isDirect()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasArray() {
        int i = this.componentCount;
        if (i == 0) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        return this.components[0].buf.hasArray();
    }

    public byte[] array() {
        int i = this.componentCount;
        if (i == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        if (i == 1) {
            return this.components[0].buf.array();
        }
        throw new UnsupportedOperationException();
    }

    public int arrayOffset() {
        int i = this.componentCount;
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            Component component = this.components[0];
            return component.idx(component.buf.arrayOffset());
        }
        throw new UnsupportedOperationException();
    }

    public boolean hasMemoryAddress() {
        int i = this.componentCount;
        if (i == 0) {
            return Unpooled.EMPTY_BUFFER.hasMemoryAddress();
        }
        if (i != 1) {
            return false;
        }
        return this.components[0].buf.hasMemoryAddress();
    }

    public long memoryAddress() {
        int i = this.componentCount;
        if (i == 0) {
            return Unpooled.EMPTY_BUFFER.memoryAddress();
        }
        if (i == 1) {
            Component component = this.components[0];
            return component.buf.memoryAddress() + ((long) component.adjustment);
        }
        throw new UnsupportedOperationException();
    }

    public int capacity() {
        int i = this.componentCount;
        if (i > 0) {
            return this.components[i - 1].endOffset;
        }
        return 0;
    }

    public CompositeByteBuf capacity(int i) {
        checkNewCapacity(i);
        int i2 = this.componentCount;
        int capacity = capacity();
        if (i > capacity) {
            int i3 = i - capacity;
            addComponent0(false, i2, allocBuffer(i3).setIndex(0, i3));
            if (this.componentCount >= this.maxNumComponents) {
                consolidateIfNeeded();
            }
        } else if (i < capacity) {
            this.lastAccessed = null;
            int i4 = i2 - 1;
            int i5 = capacity - i;
            while (true) {
                if (i4 < 0) {
                    break;
                }
                Component component = this.components[i4];
                int length = component.length();
                if (i5 < length) {
                    component.endOffset -= i5;
                    ByteBuf access$100 = component.slice;
                    if (access$100 != null) {
                        ByteBuf unused = component.slice = access$100.slice(0, component.length());
                    }
                } else {
                    component.free();
                    i5 -= length;
                    i4--;
                }
            }
            removeCompRange(i4 + 1, i2);
            if (readerIndex() > i) {
                setIndex0(i, i);
            } else if (this.writerIndex > i) {
                this.writerIndex = i;
            }
        }
        return this;
    }

    public ByteBufAllocator alloc() {
        return this.alloc;
    }

    public ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    public int numComponents() {
        return this.componentCount;
    }

    public int maxNumComponents() {
        return this.maxNumComponents;
    }

    public int toComponentIndex(int i) {
        checkIndex(i);
        return toComponentIndex0(i);
    }

    private int toComponentIndex0(int i) {
        int i2 = this.componentCount;
        int i3 = 0;
        if (i == 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.components[i4].endOffset > 0) {
                    return i4;
                }
            }
        }
        if (i2 > 2) {
            while (i3 <= i2) {
                int i5 = (i3 + i2) >>> 1;
                Component component = this.components[i5];
                if (i >= component.endOffset) {
                    i3 = i5 + 1;
                } else if (i >= component.offset) {
                    return i5;
                } else {
                    i2 = i5 - 1;
                }
            }
            throw new Error("should not reach here");
        } else if (i2 == 1 || i < this.components[0].endOffset) {
            return 0;
        } else {
            return 1;
        }
    }

    public int toByteIndex(int i) {
        checkComponentIndex(i);
        return this.components[i].offset;
    }

    public byte getByte(int i) {
        Component findComponent = findComponent(i);
        return findComponent.buf.getByte(findComponent.idx(i));
    }

    /* access modifiers changed from: protected */
    public byte _getByte(int i) {
        Component findComponent0 = findComponent0(i);
        return findComponent0.buf.getByte(findComponent0.idx(i));
    }

    /* access modifiers changed from: protected */
    public short _getShort(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            return findComponent0.buf.getShort(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
        }
        return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
    }

    /* access modifiers changed from: protected */
    public short _getShortLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            return findComponent0.buf.getShortLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
        }
        return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
    }

    /* access modifiers changed from: protected */
    public int _getUnsignedMedium(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            return findComponent0.buf.getUnsignedMedium(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getByte(i + 2) & 255) | ((_getShort(i) & 65535) << 8);
        }
        return ((_getByte(i + 2) & 255) << Ascii.DLE) | (_getShort(i) & 65535);
    }

    /* access modifiers changed from: protected */
    public int _getUnsignedMediumLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            return findComponent0.buf.getUnsignedMediumLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getByte(i + 2) & 255) << Ascii.DLE) | (_getShortLE(i) & 65535);
        }
        return (_getByte(i + 2) & 255) | ((_getShortLE(i) & 65535) << 8);
    }

    /* access modifiers changed from: protected */
    public int _getInt(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            return findComponent0.buf.getInt(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getShort(i + 2) & 65535) | ((_getShort(i) & 65535) << 16);
        }
        return ((_getShort(i + 2) & 65535) << 16) | (_getShort(i) & 65535);
    }

    /* access modifiers changed from: protected */
    public int _getIntLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            return findComponent0.buf.getIntLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getShortLE(i + 2) & 65535) << 16) | (_getShortLE(i) & 65535);
        }
        return (_getShortLE(i + 2) & 65535) | ((_getShortLE(i) & 65535) << 16);
    }

    /* access modifiers changed from: protected */
    public long _getLong(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            return findComponent0.buf.getLong(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((((long) _getInt(i)) & 4294967295L) << 32) | (4294967295L & ((long) _getInt(i + 4)));
        }
        return (((long) _getInt(i)) & 4294967295L) | ((4294967295L & ((long) _getInt(i + 4))) << 32);
    }

    /* access modifiers changed from: protected */
    public long _getLongLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            return findComponent0.buf.getLongLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (((long) _getIntLE(i)) & 4294967295L) | ((4294967295L & ((long) _getIntLE(i + 4))) << 32);
        }
        return ((((long) _getIntLE(i)) & 4294967295L) << 32) | (4294967295L & ((long) _getIntLE(i + 4)));
    }

    public CompositeByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.getBytes(component.idx(i), bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    /* JADX INFO: finally extract failed */
    public CompositeByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (remaining > 0) {
            try {
                Component component = this.components[componentIndex0];
                int min = Math.min(remaining, component.endOffset - i);
                byteBuffer.limit(byteBuffer.position() + min);
                component.buf.getBytes(component.idx(i), byteBuffer);
                i += min;
                remaining -= min;
                componentIndex0++;
            } catch (Throwable th) {
                byteBuffer.limit(limit);
                throw th;
            }
        }
        byteBuffer.limit(limit);
        return this;
    }

    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.getBytes(component.idx(i), byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return gatheringByteChannel.write(internalNioBuffer(i, i2));
        }
        long write = gatheringByteChannel.write(nioBuffers(i, i2));
        if (write > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) write;
    }

    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return fileChannel.write(internalNioBuffer(i, i2), j);
        }
        long j2 = 0;
        for (ByteBuffer write : nioBuffers(i, i2)) {
            j2 += (long) fileChannel.write(write, j + j2);
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j2;
    }

    public CompositeByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i2 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i2, component.endOffset - i);
            component.buf.getBytes(component.idx(i), outputStream, min);
            i += min;
            i2 -= min;
            componentIndex0++;
        }
        return this;
    }

    public CompositeByteBuf setByte(int i, int i2) {
        Component findComponent = findComponent(i);
        findComponent.buf.setByte(findComponent.idx(i), i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setByte(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        findComponent0.buf.setByte(findComponent0.idx(i), i2);
    }

    public CompositeByteBuf setShort(int i, int i2) {
        checkIndex(i, 2);
        _setShort(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setShort(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            findComponent0.buf.setShort(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        } else {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        }
    }

    /* access modifiers changed from: protected */
    public void _setShortLE(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            findComponent0.buf.setShortLE(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        } else {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        }
    }

    public CompositeByteBuf setMedium(int i, int i2) {
        checkIndex(i, 3);
        _setMedium(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setMedium(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            findComponent0.buf.setMedium(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        } else {
            _setShort(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        }
    }

    /* access modifiers changed from: protected */
    public void _setMediumLE(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            findComponent0.buf.setMediumLE(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        }
    }

    public CompositeByteBuf setInt(int i, int i2) {
        checkIndex(i, 4);
        _setInt(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setInt(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            findComponent0.buf.setInt(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >>> 16));
            _setShort(i + 2, (short) i2);
        } else {
            _setShort(i, (short) i2);
            _setShort(i + 2, (short) (i2 >>> 16));
        }
    }

    /* access modifiers changed from: protected */
    public void _setIntLE(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            findComponent0.buf.setIntLE(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setShortLE(i + 2, (short) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >>> 16));
            _setShortLE(i + 2, (short) i2);
        }
    }

    public CompositeByteBuf setLong(int i, long j) {
        checkIndex(i, 8);
        _setLong(i, j);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setLong(int i, long j) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            findComponent0.buf.setLong(findComponent0.idx(i), j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setInt(i, (int) (j >>> 32));
            _setInt(i + 4, (int) j);
        } else {
            _setInt(i, (int) j);
            _setInt(i + 4, (int) (j >>> 32));
        }
    }

    /* access modifiers changed from: protected */
    public void _setLongLE(int i, long j) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            findComponent0.buf.setLongLE(findComponent0.idx(i), j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setIntLE(i, (int) j);
            _setIntLE(i + 4, (int) (j >>> 32));
        } else {
            _setIntLE(i, (int) (j >>> 32));
            _setIntLE(i + 4, (int) j);
        }
    }

    public CompositeByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        checkSrcIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.setBytes(component.idx(i), bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    /* JADX INFO: finally extract failed */
    public CompositeByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (remaining > 0) {
            try {
                Component component = this.components[componentIndex0];
                int min = Math.min(remaining, component.endOffset - i);
                byteBuffer.limit(byteBuffer.position() + min);
                component.buf.setBytes(component.idx(i), byteBuffer);
                i += min;
                remaining -= min;
                componentIndex0++;
            } catch (Throwable th) {
                byteBuffer.limit(limit);
                throw th;
            }
        }
        byteBuffer.limit(limit);
        return this;
    }

    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkSrcIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.setBytes(component.idx(i), byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039 A[EDGE_INSN: B:16:0x0039->B:15:0x0039 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setBytes(int r6, java.io.InputStream r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r5.checkIndex(r6, r8)
            if (r8 != 0) goto L_0x000c
            byte[] r6 = io.netty.util.internal.EmptyArrays.EMPTY_BYTES
            int r6 = r7.read(r6)
            return r6
        L_0x000c:
            int r0 = r5.toComponentIndex0(r6)
            r1 = 0
        L_0x0011:
            io.netty.buffer.CompositeByteBuf$Component[] r2 = r5.components
            r2 = r2[r0]
            int r3 = r2.endOffset
            int r3 = r3 - r6
            int r3 = java.lang.Math.min(r8, r3)
            if (r3 != 0) goto L_0x0021
        L_0x001e:
            int r0 = r0 + 1
            goto L_0x0037
        L_0x0021:
            io.netty.buffer.ByteBuf r4 = r2.buf
            int r2 = r2.idx(r6)
            int r2 = r4.setBytes((int) r2, (java.io.InputStream) r7, (int) r3)
            if (r2 >= 0) goto L_0x0031
            if (r1 != 0) goto L_0x0039
            r6 = -1
            return r6
        L_0x0031:
            int r6 = r6 + r2
            int r8 = r8 - r2
            int r1 = r1 + r2
            if (r2 != r3) goto L_0x0037
            goto L_0x001e
        L_0x0037:
            if (r8 > 0) goto L_0x0011
        L_0x0039:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.setBytes(int, java.io.InputStream, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c A[EDGE_INSN: B:17:0x003c->B:16:0x003c ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setBytes(int r6, java.nio.channels.ScatteringByteChannel r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r5.checkIndex(r6, r8)
            if (r8 != 0) goto L_0x000c
            java.nio.ByteBuffer r6 = EMPTY_NIO_BUFFER
            int r6 = r7.read(r6)
            return r6
        L_0x000c:
            int r0 = r5.toComponentIndex0(r6)
            r1 = 0
        L_0x0011:
            io.netty.buffer.CompositeByteBuf$Component[] r2 = r5.components
            r2 = r2[r0]
            int r3 = r2.endOffset
            int r3 = r3 - r6
            int r3 = java.lang.Math.min(r8, r3)
            if (r3 != 0) goto L_0x0021
        L_0x001e:
            int r0 = r0 + 1
            goto L_0x003a
        L_0x0021:
            io.netty.buffer.ByteBuf r4 = r2.buf
            int r2 = r2.idx(r6)
            int r2 = r4.setBytes((int) r2, (java.nio.channels.ScatteringByteChannel) r7, (int) r3)
            if (r2 != 0) goto L_0x002e
            goto L_0x003c
        L_0x002e:
            if (r2 >= 0) goto L_0x0034
            if (r1 != 0) goto L_0x003c
            r6 = -1
            return r6
        L_0x0034:
            int r6 = r6 + r2
            int r8 = r8 - r2
            int r1 = r1 + r2
            if (r2 != r3) goto L_0x003a
            goto L_0x001e
        L_0x003a:
            if (r8 > 0) goto L_0x0011
        L_0x003c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.setBytes(int, java.nio.channels.ScatteringByteChannel, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042 A[EDGE_INSN: B:17:0x0042->B:16:0x0042 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setBytes(int r11, java.nio.channels.FileChannel r12, long r13, int r15) throws java.io.IOException {
        /*
            r10 = this;
            r10.checkIndex(r11, r15)
            if (r15 != 0) goto L_0x000c
            java.nio.ByteBuffer r11 = EMPTY_NIO_BUFFER
            int r11 = r12.read(r11, r13)
            return r11
        L_0x000c:
            int r0 = r10.toComponentIndex0(r11)
            r1 = 0
        L_0x0011:
            io.netty.buffer.CompositeByteBuf$Component[] r2 = r10.components
            r2 = r2[r0]
            int r3 = r2.endOffset
            int r3 = r3 - r11
            int r3 = java.lang.Math.min(r15, r3)
            if (r3 != 0) goto L_0x0021
        L_0x001e:
            int r0 = r0 + 1
            goto L_0x0040
        L_0x0021:
            io.netty.buffer.ByteBuf r4 = r2.buf
            int r5 = r2.idx(r11)
            long r6 = (long) r1
            long r8 = r13 + r6
            r6 = r12
            r7 = r8
            r9 = r3
            int r2 = r4.setBytes((int) r5, (java.nio.channels.FileChannel) r6, (long) r7, (int) r9)
            if (r2 != 0) goto L_0x0034
            goto L_0x0042
        L_0x0034:
            if (r2 >= 0) goto L_0x003a
            if (r1 != 0) goto L_0x0042
            r11 = -1
            return r11
        L_0x003a:
            int r11 = r11 + r2
            int r15 = r15 - r2
            int r1 = r1 + r2
            if (r2 != r3) goto L_0x0040
            goto L_0x001e
        L_0x0040:
            if (r15 > 0) goto L_0x0011
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.CompositeByteBuf.setBytes(int, java.nio.channels.FileChannel, long, int):int");
    }

    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf allocBuffer = allocBuffer(i2);
        if (i2 != 0) {
            copyTo(i, i2, toComponentIndex0(i), allocBuffer);
        }
        return allocBuffer;
    }

    private void copyTo(int i, int i2, int i3, ByteBuf byteBuf) {
        int i4 = 0;
        while (i2 > 0) {
            Component component = this.components[i3];
            int min = Math.min(i2, component.endOffset - i);
            component.buf.getBytes(component.idx(i), byteBuf, i4, min);
            i += min;
            i4 += min;
            i2 -= min;
            i3++;
        }
        byteBuf.writerIndex(byteBuf.capacity());
    }

    public ByteBuf component(int i) {
        checkComponentIndex(i);
        return this.components[i].duplicate();
    }

    public ByteBuf componentAtOffset(int i) {
        return findComponent(i).duplicate();
    }

    public ByteBuf internalComponent(int i) {
        checkComponentIndex(i);
        return this.components[i].slice();
    }

    public ByteBuf internalComponentAtOffset(int i) {
        return findComponent(i).slice();
    }

    private Component findComponent(int i) {
        Component component = this.lastAccessed;
        if (component == null || i < component.offset || i >= component.endOffset) {
            checkIndex(i);
            return findIt(i);
        }
        ensureAccessible();
        return component;
    }

    private Component findComponent0(int i) {
        Component component = this.lastAccessed;
        if (component == null || i < component.offset || i >= component.endOffset) {
            return findIt(i);
        }
        return component;
    }

    private Component findIt(int i) {
        int i2 = this.componentCount;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            Component component = this.components[i4];
            if (component == null) {
                throw new IllegalStateException("No component found for offset. Composite buffer layout might be outdated, e.g. from a discardReadBytes call.");
            } else if (i >= component.endOffset) {
                i3 = i4 + 1;
            } else if (i < component.offset) {
                i2 = i4 - 1;
            } else {
                this.lastAccessed = component;
                return component;
            }
        }
        throw new Error("should not reach here");
    }

    public int nioBufferCount() {
        int i = this.componentCount;
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return this.components[0].buf.nioBufferCount();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += this.components[i3].buf.nioBufferCount();
        }
        return i2;
    }

    public ByteBuffer internalNioBuffer(int i, int i2) {
        int i3 = this.componentCount;
        if (i3 == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (i3 == 1) {
            return this.components[0].internalNioBuffer(i, i2);
        }
        throw new UnsupportedOperationException();
    }

    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        int i3 = this.componentCount;
        if (i3 == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (i3 == 1) {
            Component component = this.components[0];
            ByteBuf byteBuf = component.buf;
            if (byteBuf.nioBufferCount() == 1) {
                return byteBuf.nioBuffer(component.idx(i), i2);
            }
        }
        ByteBuffer[] nioBuffers = nioBuffers(i, i2);
        if (nioBuffers.length == 1) {
            return nioBuffers[0];
        }
        ByteBuffer order = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer put : nioBuffers) {
            order.put(put);
        }
        order.flip();
        return order;
    }

    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return new ByteBuffer[]{EMPTY_NIO_BUFFER};
        }
        RecyclableArrayList newInstance = RecyclableArrayList.newInstance(this.componentCount);
        try {
            int componentIndex0 = toComponentIndex0(i);
            while (i2 > 0) {
                Component component = this.components[componentIndex0];
                ByteBuf byteBuf = component.buf;
                int min = Math.min(i2, component.endOffset - i);
                int nioBufferCount = byteBuf.nioBufferCount();
                if (nioBufferCount != 0) {
                    if (nioBufferCount != 1) {
                        Collections.addAll(newInstance, byteBuf.nioBuffers(component.idx(i), min));
                    } else {
                        newInstance.add(byteBuf.nioBuffer(component.idx(i), min));
                    }
                    i += min;
                    i2 -= min;
                    componentIndex0++;
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            return (ByteBuffer[]) newInstance.toArray(EmptyArrays.EMPTY_BYTE_BUFFERS);
        } finally {
            newInstance.recycle();
        }
    }

    public CompositeByteBuf consolidate() {
        ensureAccessible();
        consolidate0(0, this.componentCount);
        return this;
    }

    public CompositeByteBuf consolidate(int i, int i2) {
        checkComponentIndex(i, i2);
        consolidate0(i, i2);
        return this;
    }

    private void consolidate0(int i, int i2) {
        if (i2 > 1) {
            int i3 = i + i2;
            ByteBuf allocBuffer = allocBuffer(this.components[i3 - 1].endOffset - (i != 0 ? this.components[i].offset : 0));
            for (int i4 = i; i4 < i3; i4++) {
                this.components[i4].transferTo(allocBuffer);
            }
            this.lastAccessed = null;
            removeCompRange(i + 1, i3);
            this.components[i] = newComponent(allocBuffer, 0);
            if (i != 0 || i2 != this.componentCount) {
                updateComponentOffsets(i);
            }
        }
    }

    public CompositeByteBuf discardReadComponents() {
        ensureAccessible();
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return this;
        }
        int writerIndex = writerIndex();
        if (readerIndex == writerIndex && writerIndex == capacity()) {
            int i = this.componentCount;
            for (int i2 = 0; i2 < i; i2++) {
                this.components[i2].free();
            }
            this.lastAccessed = null;
            clearComps();
            setIndex(0, 0);
            adjustMarkers(readerIndex);
            return this;
        }
        int i3 = this.componentCount;
        Component component = null;
        int i4 = 0;
        while (i4 < i3) {
            component = this.components[i4];
            if (component.endOffset > readerIndex) {
                break;
            }
            component.free();
            i4++;
        }
        if (i4 == 0) {
            return this;
        }
        Component component2 = this.lastAccessed;
        if (component2 != null && component2.endOffset <= readerIndex) {
            this.lastAccessed = null;
        }
        removeCompRange(0, i4);
        int i5 = component.offset;
        updateComponentOffsets(0);
        setIndex(readerIndex - i5, writerIndex - i5);
        adjustMarkers(i5);
        return this;
    }

    public CompositeByteBuf discardReadBytes() {
        ensureAccessible();
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return this;
        }
        int writerIndex = writerIndex();
        if (readerIndex == writerIndex && writerIndex == capacity()) {
            int i = this.componentCount;
            for (int i2 = 0; i2 < i; i2++) {
                this.components[i2].free();
            }
            this.lastAccessed = null;
            clearComps();
            setIndex(0, 0);
            adjustMarkers(readerIndex);
            return this;
        }
        int i3 = this.componentCount;
        Component component = null;
        int i4 = 0;
        while (i4 < i3) {
            component = this.components[i4];
            if (component.endOffset > readerIndex) {
                break;
            }
            component.free();
            i4++;
        }
        int i5 = readerIndex - component.offset;
        component.offset = 0;
        component.endOffset -= readerIndex;
        component.srcAdjustment += readerIndex;
        component.adjustment += readerIndex;
        ByteBuf access$100 = component.slice;
        if (access$100 != null) {
            ByteBuf unused = component.slice = access$100.slice(i5, component.length());
        }
        Component component2 = this.lastAccessed;
        if (component2 != null && component2.endOffset <= readerIndex) {
            this.lastAccessed = null;
        }
        removeCompRange(0, i4);
        updateComponentOffsets(0);
        setIndex(0, writerIndex - readerIndex);
        adjustMarkers(readerIndex);
        return this;
    }

    private ByteBuf allocBuffer(int i) {
        return this.direct ? alloc().directBuffer(i) : alloc().heapBuffer(i);
    }

    public String toString() {
        String abstractReferenceCountedByteBuf = super.toString();
        String substring = abstractReferenceCountedByteBuf.substring(0, abstractReferenceCountedByteBuf.length() - 1);
        return substring + ", components=" + this.componentCount + ')';
    }

    private static final class Component {
        int adjustment;
        final ByteBuf buf;
        int endOffset;
        int offset;
        /* access modifiers changed from: private */
        public ByteBuf slice;
        int srcAdjustment;
        final ByteBuf srcBuf;

        Component(ByteBuf byteBuf, int i, ByteBuf byteBuf2, int i2, int i3, int i4, ByteBuf byteBuf3) {
            this.srcBuf = byteBuf;
            this.srcAdjustment = i - i3;
            this.buf = byteBuf2;
            this.adjustment = i2 - i3;
            this.offset = i3;
            this.endOffset = i3 + i4;
            this.slice = byteBuf3;
        }

        /* access modifiers changed from: package-private */
        public int srcIdx(int i) {
            return i + this.srcAdjustment;
        }

        /* access modifiers changed from: package-private */
        public int idx(int i) {
            return i + this.adjustment;
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.endOffset - this.offset;
        }

        /* access modifiers changed from: package-private */
        public void reposition(int i) {
            int i2 = i - this.offset;
            this.endOffset += i2;
            this.srcAdjustment -= i2;
            this.adjustment -= i2;
            this.offset = i;
        }

        /* access modifiers changed from: package-private */
        public void transferTo(ByteBuf byteBuf) {
            byteBuf.writeBytes(this.buf, idx(this.offset), length());
            free();
        }

        /* access modifiers changed from: package-private */
        public ByteBuf slice() {
            ByteBuf byteBuf = this.slice;
            if (byteBuf != null) {
                return byteBuf;
            }
            ByteBuf slice2 = this.srcBuf.slice(srcIdx(this.offset), length());
            this.slice = slice2;
            return slice2;
        }

        /* access modifiers changed from: package-private */
        public ByteBuf duplicate() {
            return this.srcBuf.duplicate();
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer internalNioBuffer(int i, int i2) {
            return this.srcBuf.internalNioBuffer(srcIdx(i), i2);
        }

        /* access modifiers changed from: package-private */
        public void free() {
            this.slice = null;
            this.srcBuf.release();
        }
    }

    public CompositeByteBuf readerIndex(int i) {
        super.readerIndex(i);
        return this;
    }

    public CompositeByteBuf writerIndex(int i) {
        super.writerIndex(i);
        return this;
    }

    public CompositeByteBuf setIndex(int i, int i2) {
        super.setIndex(i, i2);
        return this;
    }

    public CompositeByteBuf clear() {
        super.clear();
        return this;
    }

    public CompositeByteBuf markReaderIndex() {
        super.markReaderIndex();
        return this;
    }

    public CompositeByteBuf resetReaderIndex() {
        super.resetReaderIndex();
        return this;
    }

    public CompositeByteBuf markWriterIndex() {
        super.markWriterIndex();
        return this;
    }

    public CompositeByteBuf resetWriterIndex() {
        super.resetWriterIndex();
        return this;
    }

    public CompositeByteBuf ensureWritable(int i) {
        super.ensureWritable(i);
        return this;
    }

    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf) {
        return getBytes(i, byteBuf, byteBuf.writableBytes());
    }

    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        getBytes(i, byteBuf, byteBuf.writerIndex(), i2);
        byteBuf.writerIndex(byteBuf.writerIndex() + i2);
        return this;
    }

    public CompositeByteBuf getBytes(int i, byte[] bArr) {
        return getBytes(i, bArr, 0, bArr.length);
    }

    public CompositeByteBuf setBoolean(int i, boolean z) {
        return setByte(i, z ? 1 : 0);
    }

    public CompositeByteBuf setChar(int i, int i2) {
        return setShort(i, i2);
    }

    public CompositeByteBuf setFloat(int i, float f) {
        return setInt(i, Float.floatToRawIntBits(f));
    }

    public CompositeByteBuf setDouble(int i, double d) {
        return setLong(i, Double.doubleToRawLongBits(d));
    }

    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf) {
        super.setBytes(i, byteBuf, byteBuf.readableBytes());
        return this;
    }

    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        super.setBytes(i, byteBuf, i2);
        return this;
    }

    public CompositeByteBuf setBytes(int i, byte[] bArr) {
        return setBytes(i, bArr, 0, bArr.length);
    }

    public CompositeByteBuf setZero(int i, int i2) {
        super.setZero(i, i2);
        return this;
    }

    public CompositeByteBuf readBytes(ByteBuf byteBuf) {
        super.readBytes(byteBuf, byteBuf.writableBytes());
        return this;
    }

    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i) {
        super.readBytes(byteBuf, i);
        return this;
    }

    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        super.readBytes(byteBuf, i, i2);
        return this;
    }

    public CompositeByteBuf readBytes(byte[] bArr) {
        super.readBytes(bArr, 0, bArr.length);
        return this;
    }

    public CompositeByteBuf readBytes(byte[] bArr, int i, int i2) {
        super.readBytes(bArr, i, i2);
        return this;
    }

    public CompositeByteBuf readBytes(ByteBuffer byteBuffer) {
        super.readBytes(byteBuffer);
        return this;
    }

    public CompositeByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        super.readBytes(outputStream, i);
        return this;
    }

    public CompositeByteBuf skipBytes(int i) {
        super.skipBytes(i);
        return this;
    }

    public CompositeByteBuf writeBoolean(boolean z) {
        writeByte(z ? 1 : 0);
        return this;
    }

    public CompositeByteBuf writeByte(int i) {
        ensureWritable0(1);
        int i2 = this.writerIndex;
        this.writerIndex = i2 + 1;
        _setByte(i2, i);
        return this;
    }

    public CompositeByteBuf writeShort(int i) {
        super.writeShort(i);
        return this;
    }

    public CompositeByteBuf writeMedium(int i) {
        super.writeMedium(i);
        return this;
    }

    public CompositeByteBuf writeInt(int i) {
        super.writeInt(i);
        return this;
    }

    public CompositeByteBuf writeLong(long j) {
        super.writeLong(j);
        return this;
    }

    public CompositeByteBuf writeChar(int i) {
        super.writeShort(i);
        return this;
    }

    public CompositeByteBuf writeFloat(float f) {
        super.writeInt(Float.floatToRawIntBits(f));
        return this;
    }

    public CompositeByteBuf writeDouble(double d) {
        super.writeLong(Double.doubleToRawLongBits(d));
        return this;
    }

    public CompositeByteBuf writeBytes(ByteBuf byteBuf) {
        super.writeBytes(byteBuf, byteBuf.readableBytes());
        return this;
    }

    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i) {
        super.writeBytes(byteBuf, i);
        return this;
    }

    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        super.writeBytes(byteBuf, i, i2);
        return this;
    }

    public CompositeByteBuf writeBytes(byte[] bArr) {
        super.writeBytes(bArr, 0, bArr.length);
        return this;
    }

    public CompositeByteBuf writeBytes(byte[] bArr, int i, int i2) {
        super.writeBytes(bArr, i, i2);
        return this;
    }

    public CompositeByteBuf writeBytes(ByteBuffer byteBuffer) {
        super.writeBytes(byteBuffer);
        return this;
    }

    public CompositeByteBuf writeZero(int i) {
        super.writeZero(i);
        return this;
    }

    public CompositeByteBuf retain(int i) {
        super.retain(i);
        return this;
    }

    public CompositeByteBuf retain() {
        super.retain();
        return this;
    }

    public ByteBuffer[] nioBuffers() {
        return nioBuffers(readerIndex(), readableBytes());
    }

    public CompositeByteBuf discardSomeReadBytes() {
        return discardReadComponents();
    }

    /* access modifiers changed from: protected */
    public void deallocate() {
        if (!this.freed) {
            this.freed = true;
            int i = this.componentCount;
            for (int i2 = 0; i2 < i; i2++) {
                this.components[i2].free();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAccessible() {
        return !this.freed;
    }

    private final class CompositeByteBufIterator implements Iterator<ByteBuf> {
        private int index;
        private final int size;

        private CompositeByteBufIterator() {
            this.size = CompositeByteBuf.this.numComponents();
        }

        public boolean hasNext() {
            return this.size > this.index;
        }

        public ByteBuf next() {
            if (this.size != CompositeByteBuf.this.numComponents()) {
                throw new ConcurrentModificationException();
            } else if (hasNext()) {
                try {
                    Component[] access$200 = CompositeByteBuf.this.components;
                    int i = this.index;
                    this.index = i + 1;
                    return access$200[i].slice();
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Read-Only");
        }
    }

    private void clearComps() {
        removeCompRange(0, this.componentCount);
    }

    private void removeComp(int i) {
        removeCompRange(i, i + 1);
    }

    private void removeCompRange(int i, int i2) {
        if (i < i2) {
            int i3 = this.componentCount;
            if (i2 < i3) {
                Component[] componentArr = this.components;
                System.arraycopy(componentArr, i2, componentArr, i, i3 - i2);
            }
            int i4 = (i3 - i2) + i;
            for (int i5 = i4; i5 < i3; i5++) {
                this.components[i5] = null;
            }
            this.componentCount = i4;
        }
    }

    private void addComp(int i, Component component) {
        shiftComps(i, 1);
        this.components[i] = component;
    }

    private void shiftComps(int i, int i2) {
        Component[] componentArr;
        int i3 = this.componentCount;
        int i4 = i3 + i2;
        Component[] componentArr2 = this.components;
        if (i4 > componentArr2.length) {
            int max = Math.max((i3 >> 1) + i3, i4);
            if (i == i3) {
                componentArr = (Component[]) Arrays.copyOf(this.components, max, Component[].class);
            } else {
                Component[] componentArr3 = new Component[max];
                if (i > 0) {
                    System.arraycopy(this.components, 0, componentArr3, 0, i);
                }
                if (i < i3) {
                    System.arraycopy(this.components, i, componentArr3, i2 + i, i3 - i);
                }
                componentArr = componentArr3;
            }
            this.components = componentArr;
        } else if (i < i3) {
            System.arraycopy(componentArr2, i, componentArr2, i2 + i, i3 - i);
        }
        this.componentCount = i4;
    }
}
