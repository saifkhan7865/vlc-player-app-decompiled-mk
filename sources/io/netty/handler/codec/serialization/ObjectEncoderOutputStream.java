package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;

@Deprecated
public class ObjectEncoderOutputStream extends OutputStream implements ObjectOutput {
    private final int estimatedLength;
    private final DataOutputStream out;

    public ObjectEncoderOutputStream(OutputStream outputStream) {
        this(outputStream, 512);
    }

    public ObjectEncoderOutputStream(OutputStream outputStream, int i) {
        ObjectUtil.checkNotNull(outputStream, "out");
        ObjectUtil.checkPositiveOrZero(i, "estimatedLength");
        if (outputStream instanceof DataOutputStream) {
            this.out = (DataOutputStream) outputStream;
        } else {
            this.out = new DataOutputStream(outputStream);
        }
        this.estimatedLength = i;
    }

    public void writeObject(Object obj) throws IOException {
        CompactObjectOutputStream compactObjectOutputStream;
        ByteBuf buffer = Unpooled.buffer(this.estimatedLength);
        try {
            compactObjectOutputStream = new CompactObjectOutputStream(new ByteBufOutputStream(buffer));
            compactObjectOutputStream.writeObject(obj);
            compactObjectOutputStream.flush();
            compactObjectOutputStream.close();
            int readableBytes = buffer.readableBytes();
            writeInt(readableBytes);
            buffer.getBytes(0, (OutputStream) this, readableBytes);
            buffer.release();
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void close() throws IOException {
        this.out.close();
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public final int size() {
        return this.out.size();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
    }

    public final void writeBoolean(boolean z) throws IOException {
        this.out.writeBoolean(z);
    }

    public final void writeByte(int i) throws IOException {
        this.out.writeByte(i);
    }

    public final void writeBytes(String str) throws IOException {
        this.out.writeBytes(str);
    }

    public final void writeChar(int i) throws IOException {
        this.out.writeChar(i);
    }

    public final void writeChars(String str) throws IOException {
        this.out.writeChars(str);
    }

    public final void writeDouble(double d) throws IOException {
        this.out.writeDouble(d);
    }

    public final void writeFloat(float f) throws IOException {
        this.out.writeFloat(f);
    }

    public final void writeInt(int i) throws IOException {
        this.out.writeInt(i);
    }

    public final void writeLong(long j) throws IOException {
        this.out.writeLong(j);
    }

    public final void writeShort(int i) throws IOException {
        this.out.writeShort(i);
    }

    public final void writeUTF(String str) throws IOException {
        this.out.writeUTF(str);
    }
}
