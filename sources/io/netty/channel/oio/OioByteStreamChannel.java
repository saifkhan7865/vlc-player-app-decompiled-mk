package io.netty.channel.oio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.internal.ObjectUtil;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.WritableByteChannel;

@Deprecated
public abstract class OioByteStreamChannel extends AbstractOioByteChannel {
    private static final InputStream CLOSED_IN = new InputStream() {
        public int read() {
            return -1;
        }
    };
    private static final OutputStream CLOSED_OUT = new OutputStream() {
        public void write(int i) throws IOException {
            throw new ClosedChannelException();
        }
    };
    private InputStream is;
    private OutputStream os;
    private WritableByteChannel outChannel;

    protected OioByteStreamChannel(Channel channel) {
        super(channel);
    }

    /* access modifiers changed from: protected */
    public final void activate(InputStream inputStream, OutputStream outputStream) {
        if (this.is != null) {
            throw new IllegalStateException("input was set already");
        } else if (this.os == null) {
            this.is = (InputStream) ObjectUtil.checkNotNull(inputStream, "is");
            this.os = (OutputStream) ObjectUtil.checkNotNull(outputStream, "os");
            if (this.readWhenInactive) {
                eventLoop().execute(this.readTask);
                this.readWhenInactive = false;
            }
        } else {
            throw new IllegalStateException("output was set already");
        }
    }

    public boolean isActive() {
        OutputStream outputStream;
        InputStream inputStream = this.is;
        if (inputStream == null || inputStream == CLOSED_IN || (outputStream = this.os) == null || outputStream == CLOSED_OUT) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int available() {
        try {
            return this.is.available();
        } catch (IOException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int doReadBytes(ByteBuf byteBuf) throws Exception {
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.attemptedBytesRead(Math.max(1, Math.min(available(), byteBuf.maxWritableBytes())));
        return byteBuf.writeBytes(this.is, recvBufAllocHandle.attemptedBytesRead());
    }

    /* access modifiers changed from: protected */
    public void doWriteBytes(ByteBuf byteBuf) throws Exception {
        OutputStream outputStream = this.os;
        if (outputStream != null) {
            byteBuf.readBytes(outputStream, byteBuf.readableBytes());
            return;
        }
        throw new NotYetConnectedException();
    }

    /* access modifiers changed from: protected */
    public void doWriteFileRegion(FileRegion fileRegion) throws Exception {
        OutputStream outputStream = this.os;
        if (outputStream != null) {
            if (this.outChannel == null) {
                this.outChannel = Channels.newChannel(outputStream);
            }
            long j = 0;
            do {
                long transferTo = fileRegion.transferTo(this.outChannel, j);
                if (transferTo == -1) {
                    checkEOF(fileRegion);
                    return;
                }
                j += transferTo;
            } while (j < fileRegion.count());
            return;
        }
        throw new NotYetConnectedException();
    }

    private static void checkEOF(FileRegion fileRegion) throws IOException {
        if (fileRegion.transferred() < fileRegion.count()) {
            throw new EOFException("Expected to be able to write " + fileRegion.count() + " bytes, but only wrote " + fileRegion.transferred());
        }
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        InputStream inputStream = this.is;
        OutputStream outputStream = this.os;
        this.is = CLOSED_IN;
        this.os = CLOSED_OUT;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable th) {
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }
}
