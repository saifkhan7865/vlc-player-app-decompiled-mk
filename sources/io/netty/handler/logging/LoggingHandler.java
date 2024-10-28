package io.netty.handler.logging;

import io.ktor.http.ContentDisposition;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import okhttp3.internal.cache.DiskLruCache;

@ChannelHandler.Sharable
public class LoggingHandler extends ChannelDuplexHandler {
    private static final LogLevel DEFAULT_LEVEL = LogLevel.DEBUG;
    private final ByteBufFormat byteBufFormat;
    protected final InternalLogLevel internalLevel;
    private final LogLevel level;
    protected final InternalLogger logger;

    public LoggingHandler() {
        this(DEFAULT_LEVEL);
    }

    public LoggingHandler(ByteBufFormat byteBufFormat2) {
        this(DEFAULT_LEVEL, byteBufFormat2);
    }

    public LoggingHandler(LogLevel logLevel) {
        this(logLevel, ByteBufFormat.HEX_DUMP);
    }

    public LoggingHandler(LogLevel logLevel, ByteBufFormat byteBufFormat2) {
        this.level = (LogLevel) ObjectUtil.checkNotNull(logLevel, "level");
        this.byteBufFormat = (ByteBufFormat) ObjectUtil.checkNotNull(byteBufFormat2, "byteBufFormat");
        this.logger = InternalLoggerFactory.getInstance(getClass());
        this.internalLevel = logLevel.toInternalLevel();
    }

    public LoggingHandler(Class<?> cls) {
        this(cls, DEFAULT_LEVEL);
    }

    public LoggingHandler(Class<?> cls, LogLevel logLevel) {
        this(cls, logLevel, ByteBufFormat.HEX_DUMP);
    }

    public LoggingHandler(Class<?> cls, LogLevel logLevel, ByteBufFormat byteBufFormat2) {
        ObjectUtil.checkNotNull(cls, "clazz");
        this.level = (LogLevel) ObjectUtil.checkNotNull(logLevel, "level");
        this.byteBufFormat = (ByteBufFormat) ObjectUtil.checkNotNull(byteBufFormat2, "byteBufFormat");
        this.logger = InternalLoggerFactory.getInstance(cls);
        this.internalLevel = logLevel.toInternalLevel();
    }

    public LoggingHandler(String str) {
        this(str, DEFAULT_LEVEL);
    }

    public LoggingHandler(String str, LogLevel logLevel) {
        this(str, logLevel, ByteBufFormat.HEX_DUMP);
    }

    public LoggingHandler(String str, LogLevel logLevel, ByteBufFormat byteBufFormat2) {
        ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
        this.level = (LogLevel) ObjectUtil.checkNotNull(logLevel, "level");
        this.byteBufFormat = (ByteBufFormat) ObjectUtil.checkNotNull(byteBufFormat2, "byteBufFormat");
        this.logger = InternalLoggerFactory.getInstance(str);
        this.internalLevel = logLevel.toInternalLevel();
    }

    public LogLevel level() {
        return this.level;
    }

    public ByteBufFormat byteBufFormat() {
        return this.byteBufFormat;
    }

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "REGISTERED"));
        }
        channelHandlerContext.fireChannelRegistered();
    }

    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "UNREGISTERED"));
        }
        channelHandlerContext.fireChannelUnregistered();
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "ACTIVE"));
        }
        channelHandlerContext.fireChannelActive();
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "INACTIVE"));
        }
        channelHandlerContext.fireChannelInactive();
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "EXCEPTION", th), th);
        }
        channelHandlerContext.fireExceptionCaught(th);
    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "USER_EVENT", obj));
        }
        channelHandlerContext.fireUserEventTriggered(obj);
    }

    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "BIND", socketAddress));
        }
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "CONNECT", socketAddress, socketAddress2));
        }
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "DISCONNECT"));
        }
        channelHandlerContext.disconnect(channelPromise);
    }

    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "CLOSE"));
        }
        channelHandlerContext.close(channelPromise);
    }

    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "DEREGISTER"));
        }
        channelHandlerContext.deregister(channelPromise);
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "READ COMPLETE"));
        }
        channelHandlerContext.fireChannelReadComplete();
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, DiskLruCache.READ, obj));
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "WRITE", obj));
        }
        channelHandlerContext.write(obj, channelPromise);
    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "WRITABILITY CHANGED"));
        }
        channelHandlerContext.fireChannelWritabilityChanged();
    }

    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "FLUSH"));
        }
        channelHandlerContext.flush();
    }

    /* access modifiers changed from: protected */
    public String format(ChannelHandlerContext channelHandlerContext, String str) {
        String obj = channelHandlerContext.channel().toString();
        StringBuilder sb = new StringBuilder(obj.length() + 1 + str.length());
        sb.append(obj);
        sb.append(' ');
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String format(ChannelHandlerContext channelHandlerContext, String str, Object obj) {
        if (obj instanceof ByteBuf) {
            return formatByteBuf(channelHandlerContext, str, (ByteBuf) obj);
        }
        if (obj instanceof ByteBufHolder) {
            return formatByteBufHolder(channelHandlerContext, str, (ByteBufHolder) obj);
        }
        return formatSimple(channelHandlerContext, str, obj);
    }

    /* access modifiers changed from: protected */
    public String format(ChannelHandlerContext channelHandlerContext, String str, Object obj, Object obj2) {
        if (obj2 == null) {
            return formatSimple(channelHandlerContext, str, obj);
        }
        String obj3 = channelHandlerContext.channel().toString();
        String valueOf = String.valueOf(obj);
        String obj4 = obj2.toString();
        StringBuilder sb = new StringBuilder(obj3.length() + 1 + str.length() + 2 + valueOf.length() + 2 + obj4.length());
        sb.append(obj3);
        sb.append(' ');
        sb.append(str);
        sb.append(": ");
        sb.append(valueOf);
        sb.append(", ");
        sb.append(obj4);
        return sb.toString();
    }

    private String formatByteBuf(ChannelHandlerContext channelHandlerContext, String str, ByteBuf byteBuf) {
        String obj = channelHandlerContext.channel().toString();
        int readableBytes = byteBuf.readableBytes();
        int i = 1;
        if (readableBytes == 0) {
            StringBuilder sb = new StringBuilder(obj.length() + 1 + str.length() + 4);
            sb.append(obj);
            sb.append(' ');
            sb.append(str);
            sb.append(": 0B");
            return sb.toString();
        }
        int length = obj.length() + 1 + str.length() + 13;
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            int i2 = readableBytes / 16;
            if (readableBytes % 15 == 0) {
                i = 0;
            }
            length += ((i2 + i + 4) * 80) + 2;
        }
        StringBuilder sb2 = new StringBuilder(length);
        sb2.append(obj);
        sb2.append(' ');
        sb2.append(str);
        sb2.append(": ");
        sb2.append(readableBytes);
        sb2.append('B');
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            sb2.append(StringUtil.NEWLINE);
            ByteBufUtil.appendPrettyHexDump(sb2, byteBuf);
        }
        return sb2.toString();
    }

    private String formatByteBufHolder(ChannelHandlerContext channelHandlerContext, String str, ByteBufHolder byteBufHolder) {
        String obj = channelHandlerContext.channel().toString();
        String obj2 = byteBufHolder.toString();
        ByteBuf content = byteBufHolder.content();
        int readableBytes = content.readableBytes();
        int i = 1;
        if (readableBytes == 0) {
            StringBuilder sb = new StringBuilder(obj.length() + 1 + str.length() + 2 + obj2.length() + 4);
            sb.append(obj);
            sb.append(' ');
            sb.append(str);
            sb.append(", ");
            sb.append(obj2);
            sb.append(", 0B");
            return sb.toString();
        }
        int length = obj.length() + 1 + str.length() + 2 + obj2.length() + 13;
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            int i2 = readableBytes / 16;
            if (readableBytes % 15 == 0) {
                i = 0;
            }
            length += ((i2 + i + 4) * 80) + 2;
        }
        StringBuilder sb2 = new StringBuilder(length);
        sb2.append(obj);
        sb2.append(' ');
        sb2.append(str);
        sb2.append(": ");
        sb2.append(obj2);
        sb2.append(", ");
        sb2.append(readableBytes);
        sb2.append('B');
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            sb2.append(StringUtil.NEWLINE);
            ByteBufUtil.appendPrettyHexDump(sb2, content);
        }
        return sb2.toString();
    }

    private static String formatSimple(ChannelHandlerContext channelHandlerContext, String str, Object obj) {
        String obj2 = channelHandlerContext.channel().toString();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(obj2.length() + 1 + str.length() + 2 + valueOf.length());
        sb.append(obj2);
        sb.append(' ');
        sb.append(str);
        sb.append(": ");
        sb.append(valueOf);
        return sb.toString();
    }
}
