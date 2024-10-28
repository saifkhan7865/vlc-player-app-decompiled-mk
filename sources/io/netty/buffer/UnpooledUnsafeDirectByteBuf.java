package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class UnpooledUnsafeDirectByteBuf extends UnpooledDirectByteBuf {
    long memoryAddress;

    public boolean hasMemoryAddress() {
        return true;
    }

    public UnpooledUnsafeDirectByteBuf(ByteBufAllocator byteBufAllocator, int i, int i2) {
        super(byteBufAllocator, i, i2);
    }

    protected UnpooledUnsafeDirectByteBuf(ByteBufAllocator byteBufAllocator, ByteBuffer byteBuffer, int i) {
        super(byteBufAllocator, byteBuffer, i, false, true);
    }

    UnpooledUnsafeDirectByteBuf(ByteBufAllocator byteBufAllocator, ByteBuffer byteBuffer, int i, boolean z) {
        super(byteBufAllocator, byteBuffer, i, z, false);
    }

    /* access modifiers changed from: package-private */
    public final void setByteBuffer(ByteBuffer byteBuffer, boolean z) {
        super.setByteBuffer(byteBuffer, z);
        this.memoryAddress = PlatformDependent.directBufferAddress(byteBuffer);
    }

    public long memoryAddress() {
        ensureAccessible();
        return this.memoryAddress;
    }

    public byte getByte(int i) {
        checkIndex(i);
        return _getByte(i);
    }

    /* access modifiers changed from: protected */
    public byte _getByte(int i) {
        return UnsafeByteBufUtil.getByte(addr(i));
    }

    public short getShort(int i) {
        checkIndex(i, 2);
        return _getShort(i);
    }

    /* access modifiers changed from: protected */
    public short _getShort(int i) {
        return UnsafeByteBufUtil.getShort(addr(i));
    }

    /* access modifiers changed from: protected */
    public short _getShortLE(int i) {
        return UnsafeByteBufUtil.getShortLE(addr(i));
    }

    public int getUnsignedMedium(int i) {
        checkIndex(i, 3);
        return _getUnsignedMedium(i);
    }

    /* access modifiers changed from: protected */
    public int _getUnsignedMedium(int i) {
        return UnsafeByteBufUtil.getUnsignedMedium(addr(i));
    }

    /* access modifiers changed from: protected */
    public int _getUnsignedMediumLE(int i) {
        return UnsafeByteBufUtil.getUnsignedMediumLE(addr(i));
    }

    public int getInt(int i) {
        checkIndex(i, 4);
        return _getInt(i);
    }

    /* access modifiers changed from: protected */
    public int _getInt(int i) {
        return UnsafeByteBufUtil.getInt(addr(i));
    }

    /* access modifiers changed from: protected */
    public int _getIntLE(int i) {
        return UnsafeByteBufUtil.getIntLE(addr(i));
    }

    public long getLong(int i) {
        checkIndex(i, 8);
        return _getLong(i);
    }

    /* access modifiers changed from: protected */
    public long _getLong(int i) {
        return UnsafeByteBufUtil.getLong(addr(i));
    }

    /* access modifiers changed from: protected */
    public long _getLongLE(int i) {
        return UnsafeByteBufUtil.getLongLE(addr(i));
    }

    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        UnsafeByteBufUtil.getBytes((AbstractByteBuf) this, addr(i), i, byteBuf, i2, i3);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void getBytes(int i, byte[] bArr, int i2, int i3, boolean z) {
        UnsafeByteBufUtil.getBytes((AbstractByteBuf) this, addr(i), i, bArr, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public void getBytes(int i, ByteBuffer byteBuffer, boolean z) {
        UnsafeByteBufUtil.getBytes(this, addr(i), i, byteBuffer);
    }

    public ByteBuf setByte(int i, int i2) {
        checkIndex(i);
        _setByte(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setByte(int i, int i2) {
        UnsafeByteBufUtil.setByte(addr(i), i2);
    }

    public ByteBuf setShort(int i, int i2) {
        checkIndex(i, 2);
        _setShort(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setShort(int i, int i2) {
        UnsafeByteBufUtil.setShort(addr(i), i2);
    }

    /* access modifiers changed from: protected */
    public void _setShortLE(int i, int i2) {
        UnsafeByteBufUtil.setShortLE(addr(i), i2);
    }

    public ByteBuf setMedium(int i, int i2) {
        checkIndex(i, 3);
        _setMedium(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setMedium(int i, int i2) {
        UnsafeByteBufUtil.setMedium(addr(i), i2);
    }

    /* access modifiers changed from: protected */
    public void _setMediumLE(int i, int i2) {
        UnsafeByteBufUtil.setMediumLE(addr(i), i2);
    }

    public ByteBuf setInt(int i, int i2) {
        checkIndex(i, 4);
        _setInt(i, i2);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setInt(int i, int i2) {
        UnsafeByteBufUtil.setInt(addr(i), i2);
    }

    /* access modifiers changed from: protected */
    public void _setIntLE(int i, int i2) {
        UnsafeByteBufUtil.setIntLE(addr(i), i2);
    }

    public ByteBuf setLong(int i, long j) {
        checkIndex(i, 8);
        _setLong(i, j);
        return this;
    }

    /* access modifiers changed from: protected */
    public void _setLong(int i, long j) {
        UnsafeByteBufUtil.setLong(addr(i), j);
    }

    /* access modifiers changed from: protected */
    public void _setLongLE(int i, long j) {
        UnsafeByteBufUtil.setLongLE(addr(i), j);
    }

    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        UnsafeByteBufUtil.setBytes((AbstractByteBuf) this, addr(i), i, byteBuf, i2, i3);
        return this;
    }

    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        UnsafeByteBufUtil.setBytes((AbstractByteBuf) this, addr(i), i, bArr, i2, i3);
        return this;
    }

    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        UnsafeByteBufUtil.setBytes(this, addr(i), i, byteBuffer);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void getBytes(int i, OutputStream outputStream, int i2, boolean z) throws IOException {
        UnsafeByteBufUtil.getBytes(this, addr(i), i, outputStream, i2);
    }

    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        return UnsafeByteBufUtil.setBytes(this, addr(i), i, inputStream, i2);
    }

    public ByteBuf copy(int i, int i2) {
        return UnsafeByteBufUtil.copy(this, addr(i), i, i2);
    }

    /* access modifiers changed from: package-private */
    public final long addr(int i) {
        return this.memoryAddress + ((long) i);
    }

    /* access modifiers changed from: protected */
    public SwappedByteBuf newSwappedByteBuf() {
        if (PlatformDependent.isUnaligned()) {
            return new UnsafeDirectSwappedByteBuf(this);
        }
        return super.newSwappedByteBuf();
    }

    public ByteBuf setZero(int i, int i2) {
        checkIndex(i, i2);
        UnsafeByteBufUtil.setZero(addr(i), i2);
        return this;
    }

    public ByteBuf writeZero(int i) {
        ensureWritable(i);
        int i2 = this.writerIndex;
        UnsafeByteBufUtil.setZero(addr(i2), i);
        this.writerIndex = i2 + i;
        return this;
    }
}
