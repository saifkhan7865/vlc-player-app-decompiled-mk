package io.netty.handler.pcap;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class PcapWriter implements Closeable {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PcapWriter.class);
    private final OutputStream outputStream;
    private final PcapWriteHandler pcapWriteHandler;

    PcapWriter(PcapWriteHandler pcapWriteHandler2) throws IOException {
        this.pcapWriteHandler = pcapWriteHandler2;
        this.outputStream = pcapWriteHandler2.outputStream();
        if (!pcapWriteHandler2.sharedOutputStream()) {
            PcapHeaders.writeGlobalHeader(pcapWriteHandler2.outputStream());
        }
    }

    /* access modifiers changed from: package-private */
    public void writePacket(ByteBuf byteBuf, ByteBuf byteBuf2) throws IOException {
        if (this.pcapWriteHandler.state() == State.CLOSED) {
            logger.debug("Pcap Write attempted on closed PcapWriter");
        }
        long currentTimeMillis = System.currentTimeMillis();
        PcapHeaders.writePacketHeader(byteBuf, (int) (currentTimeMillis / 1000), (int) ((currentTimeMillis % 1000) * 1000), byteBuf2.readableBytes(), byteBuf2.readableBytes());
        if (this.pcapWriteHandler.sharedOutputStream()) {
            synchronized (this.outputStream) {
                byteBuf.readBytes(this.outputStream, byteBuf.readableBytes());
                byteBuf2.readBytes(this.outputStream, byteBuf2.readableBytes());
            }
            return;
        }
        byteBuf.readBytes(this.outputStream, byteBuf.readableBytes());
        byteBuf2.readBytes(this.outputStream, byteBuf2.readableBytes());
    }

    public String toString() {
        return "PcapWriter{outputStream=" + this.outputStream + AbstractJsonLexerKt.END_OBJ;
    }

    public void close() throws IOException {
        if (this.pcapWriteHandler.state() == State.CLOSED) {
            logger.debug("PcapWriter is already closed");
            return;
        }
        if (this.pcapWriteHandler.sharedOutputStream()) {
            synchronized (this.outputStream) {
                this.outputStream.flush();
            }
        } else {
            this.outputStream.flush();
            this.outputStream.close();
        }
        this.pcapWriteHandler.markClosed();
        logger.debug("PcapWriter is now closed");
    }
}
