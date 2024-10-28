package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;

public class MemoryAttribute extends AbstractMemoryHttpData implements Attribute {
    public MemoryAttribute(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public MemoryAttribute(String str, long j) {
        this(str, j, HttpConstants.DEFAULT_CHARSET);
    }

    public MemoryAttribute(String str, Charset charset) {
        super(str, charset, 0);
    }

    public MemoryAttribute(String str, long j, Charset charset) {
        super(str, charset, j);
    }

    public MemoryAttribute(String str, String str2) throws IOException {
        this(str, str2, HttpConstants.DEFAULT_CHARSET);
    }

    public MemoryAttribute(String str, String str2, Charset charset) throws IOException {
        super(str, charset, 0);
        setValue(str2);
    }

    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.Attribute;
    }

    public String getValue() {
        return getByteBuf().toString(getCharset());
    }

    public void setValue(String str) throws IOException {
        ObjectUtil.checkNotNull(str, "value");
        byte[] bytes = str.getBytes(getCharset());
        checkSize((long) bytes.length);
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bytes);
        if (this.definedSize > 0) {
            this.definedSize = (long) wrappedBuffer.readableBytes();
        }
        setContent(wrappedBuffer);
    }

    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        try {
            long readableBytes = (long) byteBuf.readableBytes();
            checkSize(this.size + readableBytes);
            if (this.definedSize > 0 && this.definedSize < this.size + readableBytes) {
                this.definedSize = this.size + readableBytes;
            }
            super.addContent(byteBuf, z);
        } catch (IOException e) {
            byteBuf.release();
            throw e;
        }
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Attribute)) {
            return false;
        }
        return getName().equalsIgnoreCase(((Attribute) obj).getName());
    }

    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (interfaceHttpData instanceof Attribute) {
            return compareTo((Attribute) interfaceHttpData);
        }
        throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
    }

    public int compareTo(Attribute attribute) {
        return getName().compareToIgnoreCase(attribute.getName());
    }

    public String toString() {
        return getName() + '=' + getValue();
    }

    public Attribute copy() {
        ByteBuf content = content();
        return replace(content != null ? content.copy() : null);
    }

    public Attribute duplicate() {
        ByteBuf content = content();
        return replace(content != null ? content.duplicate() : null);
    }

    public Attribute retainedDuplicate() {
        ByteBuf content = content();
        if (content == null) {
            return replace((ByteBuf) null);
        }
        ByteBuf retainedDuplicate = content.retainedDuplicate();
        try {
            return replace(retainedDuplicate);
        } catch (Throwable th) {
            retainedDuplicate.release();
            throw th;
        }
    }

    public Attribute replace(ByteBuf byteBuf) {
        MemoryAttribute memoryAttribute = new MemoryAttribute(getName());
        memoryAttribute.setCharset(getCharset());
        if (byteBuf != null) {
            try {
                memoryAttribute.setContent(byteBuf);
            } catch (IOException e) {
                throw new ChannelException((Throwable) e);
            }
        }
        memoryAttribute.setCompleted(isCompleted());
        return memoryAttribute;
    }

    public Attribute retain() {
        super.retain();
        return this;
    }

    public Attribute retain(int i) {
        super.retain(i);
        return this;
    }

    public Attribute touch() {
        super.touch();
        return this;
    }

    public Attribute touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
